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

import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.compression.FastGzipOutputStream;
import im.turms.server.common.infra.logging.core.logger.InternalLogger;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.memory.ByteBufferUtil;
import im.turms.server.common.infra.time.TimeZoneConst;
import lombok.SneakyThrows;

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

/**
 * @author James Chen
 */
public class RollingFileAppender extends Appender {

    public static final char FIELD_DELIMITER = '_';
    private static final String FILE_MIDDLE = "yyyyMMdd";

    public static final String ARCHIVE_FILE_SUFFIX = ".gz";
    // 3 is a good trade-off between speed and compression ratio
    private static final int COMPRESSION_LEVEL = 3;

    private static final Set<StandardOpenOption> READ_OPTIONS = Set
            .of(StandardOpenOption.READ);
    private static final Set<StandardOpenOption> CREATE_NEW_OPTIONS = Set
            .of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    /**
     * Don't add {@link StandardOpenOption#READ} because READ + APPEND isn't allowed
     */
    private static final Set<StandardOpenOption> APPEND_OPTIONS = Set
            .of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

    private final String filePrefix;
    private final String fileSuffix;

    private final Path fileDirectory;

    private final DateTimeFormatter fileDateTimeFormatter = DateTimeFormatter
            .ofPattern(FILE_MIDDLE)
            .withZone(TimeZoneConst.ZONE_ID);

    private final int maxFiles;
    private final long maxFileSize;

    private final Deque<LogFile> files;
    private LogFile currentFile;

    private long nextFileSize;
    private long nextIndex;
    private long nextDay = Long.MIN_VALUE;

    private final boolean enableCompression;
    private final FastGzipOutputStream gzipOutputStream;

    @SneakyThrows
    public RollingFileAppender(LogLevel level,
                               String file,
                               int maxFiles,
                               long maxFileMb,
                               boolean enableCompression) {
        super(level);
        Path filePath = Paths.get(file).toAbsolutePath();
        String fileName = filePath.getFileName().toString();
        int index = fileName.lastIndexOf('.');

        this.filePrefix = index == -1 ? fileName : fileName.substring(0, index);
        this.fileSuffix = index == -1 ? "" : fileName.substring(index);
        this.fileDirectory = filePath.getParent();

        this.maxFiles = Math.max(maxFiles, 0);
        this.maxFileSize = maxFileMb > 0
                ? maxFileMb * 1024 * 1024
                : Long.MAX_VALUE;

        this.enableCompression = enableCompression;
        gzipOutputStream = enableCompression
                // Use large buffer to reduce the number of JNI calls
                ? new FastGzipOutputStream(COMPRESSION_LEVEL, (int) Math.min(1024L * 1024, maxFileSize / 10))
                : null;

        Files.createDirectories(fileDirectory);
        files = LogDirectoryVisitor.visit(fileDirectory, filePrefix, fileSuffix, FILE_MIDDLE, fileDateTimeFormatter, maxFiles);

        LogFile logFile = files.peekLast();
        if (logFile == null) {
            openNewFile(false);
        } else {
            openExistingFile(logFile);
        }
    }

    private FileChannel openFileChannel(Path file) throws IOException {
        Path directory = file.getParent();
        if (directory != null) {
            Files.createDirectories(directory);
        }
        return FileChannel.open(file, APPEND_OPTIONS);
    }

    private boolean needRoll(LogRecord record) {
        return record.timestamp() >= nextDay || nextFileSize >= maxFileSize;
    }

    @Override
    public int append(LogRecord record) {
        if (needRoll(record)) {
            roll();
        }
        int bytes = super.append(record);
        nextFileSize += bytes;
        return bytes;
    }

    /**
     * Note that it's fine to close file channel and open a new one not atomically
     * because there is always only one thread will call roll() and append()
     */
    private void roll() {
        closeCurrentFileChannel();
        if (enableCompression) {
            try {
                compress();
                try {
                    Files.deleteIfExists(currentFile.path());
                } catch (Exception e) {
                    InternalLogger.INSTANCE.error("Caught an error while deleting the log file: " + currentFile.path(),
                            e);
                }
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while compressing the log file: " + currentFile.path(),
                        e);
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
                InternalLogger.INSTANCE.error("The directory \"" + dir + "\" is a file instead of a directory. " +
                        "This may happen if the directory has been replaced with a file unexpectedly by users. Removing...");
                try {
                    Files.deleteIfExists(dir);
                } catch (Exception ignored) {
                }
                try {
                    Files.createDirectories(dir);
                } catch (IOException exception) {
                    throw new IllegalStateException(exception);
                }
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while creating the directory: " + dir, e);
            }
        } else {
            try {
                Files.createDirectories(dir);
            } catch (Exception e) {
                throw new IllegalStateException("Caught an error while creating the directory: " + dir, e);
            }
        }
        String fileName;
        Path filePath;
        while (true) {
            fileName = filePrefix
                    + FIELD_DELIMITER
                    + fileDateTimeFormatter.format(now)
                    + FIELD_DELIMITER
                    + nextIndex
                    + fileSuffix;
            filePath = fileDirectory.resolve(fileName);
            try {
                channel = FileChannel.open(filePath, APPEND_OPTIONS);
                break;
            } catch (IOException e) {
                String message = "Failed to open a file channel for the file: " + filePath;
                if (recoverFromError) {
                    InternalLogger.INSTANCE.error(message, e);
                    nextIndex++;
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException exc) {
                        throw new IllegalStateException(message, e);
                    }
                } else {
                    throw new IllegalStateException(message, e);
                }
            }
        }
        Path archivePath = enableCompression
                ? filePath.resolveSibling(fileName + ARCHIVE_FILE_SUFFIX)
                : null;
        currentFile = new LogFile(filePath, archivePath, now, nextIndex);
        files.add(currentFile);

        try {
            nextFileSize = channel.size();
        } catch (IOException e) {
            String message = "Failed to read the file channel size: " + filePath;
            if (recoverFromError) {
                message += ". Fallback to 0";
                InternalLogger.INSTANCE.error(message, e);
                nextFileSize = 0;
            } else {
                throw new IllegalStateException(message, e);
            }
        }
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex++;
    }

    @SneakyThrows
    private void openExistingFile(LogFile existingFile) {
        ZonedDateTime now = existingFile.dateTime();
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS)
                .truncatedTo(ChronoUnit.DAYS);

        Path filePath = existingFile.path();

        channel = openFileChannel(filePath);
        currentFile = existingFile;

        nextFileSize = channel.size();
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex = existingFile.index() + 1;
    }

    private void closeCurrentFileChannel() {
        try {
            channel.force(true);
        } catch (IOException e) {
            InternalLogger.INSTANCE
                    .error("Caught an error while forcing updates to be written: " + currentFile.path(),
                            e);
        }
        try {
            channel.close();
        } catch (Exception e) {
            InternalLogger.INSTANCE.error("Caught an error while closing the file channel: " + currentFile.path(),
                    e);
        }
    }

    private void compress() {
        MappedByteBuffer logFileBuffer;
        try (FileChannel fileChannel = FileChannel.open(currentFile.path(), READ_OPTIONS)) {
            logFileBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read the log file: " + currentFile.path(), e);
        }
        try {
            FileChannel outputFileChannel;
            try {
                outputFileChannel = FileChannel.open(currentFile.archivePath(), CREATE_NEW_OPTIONS);
            } catch (FileAlreadyExistsException ignored) {
                return;
            } catch (Exception e) {
                throw new IllegalStateException("Failed to open the archive path: " + currentFile.archivePath(), e);
            }
            try {
                gzipOutputStream.init(outputFileChannel);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to initialize the GZIP output stream", e);
            }
            try (gzipOutputStream) {
                try {
                    gzipOutputStream.write(logFileBuffer);
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to write the log file to the GZIP output stream", e);
                }
            }
        } finally {
            ByteBufferUtil.freeDirectBuffer(logFileBuffer);
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

    private void deleteLogFile(LogFile file) {
        Path path = file.path();
        Path archivePath = file.archivePath();
        try {
            Files.deleteIfExists(path);
        } catch (Exception e) {
            InternalLogger.INSTANCE.error("Caught an error while delete the log file: " + path);
        }
        if (archivePath != null) {
            try {
                Files.deleteIfExists(archivePath);
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while delete the log file: " + archivePath);
            }
        }
    }

}