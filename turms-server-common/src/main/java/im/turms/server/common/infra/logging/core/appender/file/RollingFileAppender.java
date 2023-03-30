/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.server.common.infra.logging.core.appender.file;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Deque;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.compression.FastGzipOutputStream;
import im.turms.server.common.infra.logging.core.logger.InternalLogger;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.memory.ByteBufferUtil;
import im.turms.server.common.infra.time.TimeZoneConst;

import static im.turms.server.common.infra.unit.ByteSizeUnit.GB;
import static im.turms.server.common.infra.unit.ByteSizeUnit.MB;

/**
 * @author James Chen
 */
public class RollingFileAppender extends Appender {

    public static final char FIELD_DELIMITER = '_';
    private static final String FILE_MIDDLE = "yyyyMMdd";

    public static final String ARCHIVE_FILE_SUFFIX = ".gz";
    // 3 is a good trade-off between speed and compression ratio
    private static final int COMPRESSION_LEVEL = 3;

    private static final Set<StandardOpenOption> READ_OPTIONS = Set.of(StandardOpenOption.READ);
    private static final Set<StandardOpenOption> CREATE_NEW_OPTIONS =
            Set.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    /**
     * Don't add {@link StandardOpenOption#READ} because READ + APPEND isn't allowed
     */
    private static final Set<StandardOpenOption> APPEND_OPTIONS =
            Set.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

    private final String filePrefix;
    private final String fileSuffix;

    private final Path fileDirectory;
    private final File fileDirectoryFile;

    private final DateTimeFormatter fileDateTimeFormatter = DateTimeFormatter.ofPattern(FILE_MIDDLE)
            .withZone(TimeZoneConst.ZONE_ID);

    private final int maxFiles;
    private final long maxFileBytes;
    private final long minUsableSpaceBytes;

    private final Deque<LogFile> files;
    private LogFile currentFile;

    private long nextFileBytes;
    private long nextIndex;
    private long nextDay = Long.MIN_VALUE;

    private final boolean enableCompression;
    private final FastGzipOutputStream gzipOutputStream;

    public RollingFileAppender(
            LogLevel level,
            String file,
            int maxFiles,
            long maxFileMb,
            boolean enableCompression) {
        super(level);
        Path filePath = Paths.get(file)
                .toAbsolutePath();
        String fileName = filePath.getFileName()
                .toString();
        int index = fileName.lastIndexOf('.');

        this.filePrefix = index == -1
                ? fileName
                : fileName.substring(0, index);
        this.fileSuffix = index == -1
                ? ""
                : fileName.substring(index);
        this.fileDirectory = filePath.getParent();
        fileDirectoryFile = fileDirectory.toFile();

        this.maxFiles = Math.max(maxFiles, 0);
        this.maxFileBytes = maxFileMb > 0
                ? maxFileMb * MB
                : GB;
        minUsableSpaceBytes = (long) (maxFileBytes * 2.5);

        this.enableCompression = enableCompression;
        gzipOutputStream = enableCompression
                // Use large buffer to reduce the number of JNI calls
                ? new FastGzipOutputStream(COMPRESSION_LEVEL, (int) Math.min(MB, maxFileBytes / 10))
                : null;

        try {
            Files.createDirectories(fileDirectory);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the directory ("
                            + fileDirectory
                            + ") for log files",
                    e);
        }
        files = LogDirectoryVisitor.visit(fileDirectory,
                filePrefix,
                fileSuffix,
                FILE_MIDDLE,
                fileDateTimeFormatter,
                maxFiles);

        LogFile logFile = files.peekLast();
        if (logFile == null) {
            openNewFile(false);
        } else {
            openExistingFile(logFile);
        }
    }

    private FileChannel openFileChannel(Path file) {
        Path directory = file.getParent();
        if (directory != null) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new InputOutputException(
                        "Failed to create the directory ("
                                + directory
                                + ") for log files",
                        e);
            }
        }
        try {
            return FileChannel.open(file, APPEND_OPTIONS);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to open the file: "
                            + file,
                    e);
        }
    }

    private boolean needRoll(LogRecord record) {
        return record.timestamp() >= nextDay || nextFileBytes >= maxFileBytes;
    }

    @Override
    public void close() {
        super.close();
        try {
            gzipOutputStream.destroy();
        } catch (IOException e) {
            throw new InputOutputException(
                    "Caught an error while destroying the GZIP output stream",
                    e);
        }
    }

    @Override
    public int append(LogRecord record) {
        if (needRoll(record)) {
            roll();
        }
        int bytes = super.append(record);
        nextFileBytes += bytes;
        return bytes;
    }

    /**
     * Note that it is fine to close file channel and open a new one not atomically because there is
     * always only one thread will call roll() and append()
     */
    private void roll() {
        cleanFilesUntilSpaceEnough();
        closeCurrentFileChannel();
        if (enableCompression) {
            try {
                compress();
                try {
                    Files.deleteIfExists(currentFile.path());
                } catch (Exception e) {
                    InternalLogger.INSTANCE.error("Caught an error while deleting the log file: "
                            + currentFile.path(), e);
                }
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while compressing the log file: "
                        + currentFile.path(), e);
            }
        }
        openNewFile(true);
        cleanExceededFiles();
    }

    private void openNewFile(boolean recoverFromError) {
        ZonedDateTime now = ZonedDateTime.now(TimeZoneConst.ZONE_ID);
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS)
                .truncatedTo(ChronoUnit.DAYS);
        Path dir = fileDirectory;
        if (recoverFromError) {
            try {
                Files.createDirectories(dir);
            } catch (FileAlreadyExistsException e) {
                InternalLogger.INSTANCE.error("The path ("
                        + dir
                        + ") is pointing to a file instead of a directory. "
                        + "This may happen if the directory has been replaced with a file unexpectedly by users. "
                        + "Start deleting the file to recover");
                try {
                    Files.deleteIfExists(dir);
                } catch (Exception ignored) {
                }
                try {
                    Files.createDirectories(dir);
                } catch (IOException exception) {
                    throw new InputOutputException(exception);
                }
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while creating the directory: "
                        + dir, e);
            }
        } else {
            try {
                Files.createDirectories(dir);
            } catch (Exception e) {
                throw new InputOutputException(
                        "Caught an error while creating the directory: "
                                + dir,
                        e);
            }
        }
        String fileName;
        Path filePath;
        while (true) {
            fileName = filePrefix + FIELD_DELIMITER + fileDateTimeFormatter.format(now)
                    + FIELD_DELIMITER + nextIndex + fileSuffix;
            filePath = fileDirectory.resolve(fileName);
            try {
                channel = FileChannel.open(filePath, APPEND_OPTIONS);
                break;
            } catch (IOException e) {
                String message = "Failed to open a file channel for the file: "
                        + filePath;
                if (recoverFromError) {
                    InternalLogger.INSTANCE.error(message, e);
                    nextIndex++;
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException exc) {
                        throw new InputOutputException(message, e);
                    }
                } else {
                    throw new InputOutputException(message, e);
                }
            }
        }
        Path archivePath = enableCompression
                ? filePath.resolveSibling(fileName + ARCHIVE_FILE_SUFFIX)
                : null;
        currentFile = new LogFile(filePath, archivePath, now, nextIndex);
        files.add(currentFile);

        try {
            nextFileBytes = channel.size();
        } catch (IOException e) {
            String message = "Failed to read the file channel size: "
                    + filePath;
            if (recoverFromError) {
                message += ". Fallback to 0";
                InternalLogger.INSTANCE.error(message, e);
                nextFileBytes = 0;
            } else {
                throw new InputOutputException(message, e);
            }
        }
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex++;
    }

    private void openExistingFile(LogFile existingFile) {
        ZonedDateTime now = existingFile.dateTime();
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS)
                .truncatedTo(ChronoUnit.DAYS);

        Path filePath = existingFile.path();

        channel = openFileChannel(filePath);
        currentFile = existingFile;

        try {
            nextFileBytes = channel.size();
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to read the size of the file: "
                            + filePath,
                    e);
        }
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex = existingFile.index() + 1;
    }

    private void closeCurrentFileChannel() {
        try {
            channel.force(true);
        } catch (IOException e) {
            InternalLogger.INSTANCE.error("Caught an error while forcing updates to be written: "
                    + currentFile.path(), e);
        }
        try {
            channel.close();
        } catch (Exception e) {
            InternalLogger.INSTANCE.error("Caught an error while closing the file channel: "
                    + currentFile.path(), e);
        }
    }

    private void compress() {
        MappedByteBuffer logFileBuffer;
        try (FileChannel fileChannel = FileChannel.open(currentFile.path(), READ_OPTIONS)) {
            logFileBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        } catch (Exception e) {
            throw new InputOutputException(
                    "Failed to read the log file: "
                            + currentFile.path(),
                    e);
        }
        FileChannel archiveFileChannel = null;
        Exception exceptionFromCatch = null;
        try {
            try {
                archiveFileChannel =
                        FileChannel.open(currentFile.archivePath(), CREATE_NEW_OPTIONS);
            } catch (FileAlreadyExistsException ignored) {
                return;
            } catch (Exception e) {
                throw new InputOutputException(
                        "Failed to open the archive path: "
                                + currentFile.archivePath(),
                        e);
            }
            try {
                gzipOutputStream.init(archiveFileChannel);
            } catch (Exception e) {
                throw new InputOutputException("Failed to initialize the GZIP output stream", e);
            }
            InputOutputException writeException = null;
            try {
                gzipOutputStream.write(logFileBuffer);
            } catch (Exception e) {
                writeException = new InputOutputException(
                        "Failed to write the log file to the GZIP output stream",
                        e);
                throw writeException;
            } finally {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    InputOutputException closeException =
                            new InputOutputException("Failed to close the GZIP output stream", e);
                    if (writeException != null) {
                        closeException.addSuppressed(writeException);
                    }
                    throw closeException;
                }
            }
        } catch (Exception e) {
            exceptionFromCatch = e;
            throw e;
        } finally {
            ByteBufferUtil.freeDirectBuffer(logFileBuffer);
            if (archiveFileChannel != null) {
                try {
                    archiveFileChannel.close();
                } catch (IOException e) {
                    InputOutputException closeException =
                            new InputOutputException("Failed to close the archive file channel", e);
                    if (exceptionFromCatch != null) {
                        closeException.addSuppressed(exceptionFromCatch);
                    }
                    throw closeException;
                }
            }
        }
    }

    private void cleanExceededFiles() {
        while (files.size() > maxFiles) {
            LogFile file = files.remove();
            if (maxFiles > 0) {
                deleteLogFile(file);
            }
        }
    }

    private void cleanFilesUntilSpaceEnough() {
        File file = fileDirectoryFile;
        if (file.getUsableSpace() > minUsableSpaceBytes) {
            return;
        }
        int lastElementIndex = files.size() - 1;
        int i = 0;
        for (LogFile logFile : files) {
            if (i == lastElementIndex) {
                break;
            }
            deleteLogFile(logFile);
            if (file.getUsableSpace() > minUsableSpaceBytes) {
                return;
            }
            i++;
        }
        InternalLogger.INSTANCE.fatal("Freeze to wait for the space to be available");
        while (true) {
            try {
                // TODO: don't freeze other appenders
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (file.getUsableSpace() > minUsableSpaceBytes) {
                break;
            }
        }
        InternalLogger.INSTANCE.info("The space has become available");
    }

    private void deleteLogFile(LogFile file) {
        Path path = file.path();
        Path archivePath = file.archivePath();
        try {
            Files.deleteIfExists(path);
        } catch (Exception e) {
            InternalLogger.INSTANCE.error("Caught an error while delete the log file: "
                    + path);
        }
        if (archivePath != null) {
            try {
                Files.deleteIfExists(archivePath);
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while delete the log file: "
                        + archivePath);
            }
        }
    }

}