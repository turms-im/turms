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

package im.turms.server.common.logging.core.appender;

import im.turms.server.common.logging.core.logger.InternalLogger;
import im.turms.server.common.logging.core.model.LogLevel;
import im.turms.server.common.logging.core.model.LogRecord;
import lombok.SneakyThrows;

import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
public class RollingFileAppender extends Appender {

    private static final char FIELD_DELIMITER = '_';
    private static final ZoneId UTC = ZoneId.of("UTC");

    private final String filePrefix;
    private final String fileSuffix;
    private final String fileMiddle = "yyyyMMdd";

    private final Path fileDirectory;

    private final DateTimeFormatter fileMiddleTemplate = DateTimeFormatter.ofPattern(fileMiddle)
            .withZone(UTC);

    private final int maxFiles;
    private final long maxFileSize;

    private final ArrayDeque<LogFile> files;
    private LogFile file;

    private long nextFileSize;
    private long nextIndex;
    private long nextDay = Long.MIN_VALUE;

    @SneakyThrows
    public RollingFileAppender(LogLevel level,
                               String file,
                               int maxFiles,
                               long maxFileMb) {
        super("file", level);
        Path filePath = Paths.get(file).toAbsolutePath();

        String fileName = filePath.getFileName().toString();
        int index = fileName.lastIndexOf('.');

        this.filePrefix = index == -1 ? fileName : fileName.substring(0, index);
        this.fileSuffix = index == -1 ? "" : fileName.substring(index);

        this.fileDirectory = filePath.getParent();

        this.maxFiles = Math.max(maxFiles, 0);
        this.maxFileSize = (maxFileMb > 0) ? maxFileMb * 1024 * 1024 : Long.MAX_VALUE;

        Files.createDirectories(fileDirectory);
        files = LogDirectoryVisitor.visit(fileDirectory, filePrefix, fileSuffix, fileMiddle, fileMiddleTemplate, maxFiles);

        LogFile logFile = files.peekLast();

        if (logFile == null) {
            openNewFile();
        } else {
            openExistingFile(logFile);
        }
    }

    private FileChannel openChannel(Path file) throws Exception {
        Path directory = file.getParent();
        if (directory != null) {
            Files.createDirectories(directory);
        }
        return FileChannel.open(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        );
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
     * because there is always one thread will call roll()
     */
    @SneakyThrows
    private void roll() {
        closeFile();
        openNewFile();
        clean();
    }

    private void openNewFile() throws Exception {
        ZonedDateTime now = ZonedDateTime.now(UTC);
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);

        Path filePath = getFilePath(now);
        FileChannel fileChannel = openChannel(filePath);

        channel = fileChannel;
        file = new LogFile(filePath, now, nextIndex);
        files.add(file);

        nextFileSize = fileChannel.size();
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex++;
    }

    private void openExistingFile(LogFile existingFile) throws Exception {
        ZonedDateTime now = existingFile.dateTime;
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);

        Path filePath = existingFile.path;
        FileChannel fileChannel = openChannel(filePath);

        channel = fileChannel;
        file = existingFile;

        nextFileSize = fileChannel.size();
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex = existingFile.index + 1;
    }

    @SneakyThrows
    private void closeFile() {
        channel.force(true);
        channel.close();
    }

    private void clean() {
        while (files.size() > maxFiles) {
            LogFile file = files.remove();

            if (maxFiles > 0) {
                deleteFile(file);
            }
        }
    }

    private Path getFilePath(ZonedDateTime currentDay) {
        String name = filePrefix + FIELD_DELIMITER + fileMiddleTemplate.format(currentDay) + FIELD_DELIMITER + nextIndex + fileSuffix;
        return fileDirectory.resolve(name);
    }

    private static void deleteFile(LogFile file) {
        Path path = file.path;
        try {
            Files.deleteIfExists(path);
        } catch (Throwable e) {
        }
    }

    private record LogFile(Path path, ZonedDateTime dateTime, long index) {
    }

    private static class LogDirectoryVisitor extends SimpleFileVisitor<Path> {

        private final TreeSet<LogFile> files = new TreeSet<>(Comparator.comparingLong(o -> o.index));

        private final String prefix;
        private final String suffix;
        private final String middle;

        private final DateTimeFormatter template;

        private final int maxFilesToKeep;
        private final boolean deleteExceedFiles;

        public LogDirectoryVisitor(String prefix, String suffix, String middle, DateTimeFormatter template, int maxFiles) {
            this.prefix = prefix;
            this.suffix = suffix;
            this.middle = middle;
            this.template = template;
            this.maxFilesToKeep = Math.max(1, maxFiles);
            this.deleteExceedFiles = maxFiles > 0;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            try {
                String name = path.getFileName().toString();
                boolean isLogFile = (name.length() > prefix.length() + suffix.length() + middle.length() + 1) && name.startsWith(prefix) && name.endsWith(suffix);
                if (!isLogFile) {
                    return FileVisitResult.CONTINUE;
                }
                int indexEnd = name.length() - suffix.length();
                int indexStart = name.lastIndexOf(FIELD_DELIMITER, indexEnd - 1);
                if (indexStart == prefix.length() + middle.length() + 1) {
                    long index = Long.parseUnsignedLong(name.substring(indexStart + 1, indexEnd));
                    TemporalAccessor time = template.parse(name.substring(prefix.length() + 1, indexStart));
                    ZonedDateTime timestamp = LocalDate.from(time).atStartOfDay(UTC);
                    handleLogFile(path, timestamp, index);
                }
            } catch (Throwable e) {
                InternalLogger.INSTANCE.warn("Cannot figure out if a file matches the template: " + path, e);
            }
            return FileVisitResult.CONTINUE;
        }

        private void handleLogFile(Path path, ZonedDateTime timestamp, long index) {
            LogFile file = new LogFile(path, timestamp, index);
            files.add(file);

            if (files.size() > maxFilesToKeep) {
                LogFile firstLogFile = files.first();
                files.remove(firstLogFile);
                if (deleteExceedFiles) {
                    deleteFile(firstLogFile);
                }
            }
        }

        public static ArrayDeque<LogFile> visit(Path directory, String prefix, String suffix, String middle, DateTimeFormatter template, int maxFiles) throws Exception {
            LogDirectoryVisitor visitor = new LogDirectoryVisitor(prefix, suffix, middle, template, maxFiles);
            Files.walkFileTree(directory, Collections.emptySet(), 1, visitor);
            return new ArrayDeque<>(visitor.files);
        }

    }

}
