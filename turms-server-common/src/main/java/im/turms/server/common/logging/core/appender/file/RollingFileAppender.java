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

package im.turms.server.common.logging.core.appender.file;

import im.turms.server.common.constant.TimeZoneConstant;
import im.turms.server.common.logging.core.appender.Appender;
import im.turms.server.common.logging.core.model.LogLevel;
import im.turms.server.common.logging.core.model.LogRecord;
import lombok.SneakyThrows;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
public class RollingFileAppender extends Appender {

    public static final char FIELD_DELIMITER = '_';

    private final String filePrefix;
    private final String fileSuffix;
    private final String fileMiddle = "yyyyMMdd";

    private final Path fileDirectory;

    private final DateTimeFormatter fileDateTimeFormatter = DateTimeFormatter
            .ofPattern(fileMiddle)
            .withZone(TimeZoneConstant.ZONE_ID);

    private final int maxFiles;
    private final long maxFileSize;

    private final ArrayDeque<LogFile> files;
    private LogFile currentFile;

    private long nextFileSize;
    private long nextIndex;
    private long nextDay = Long.MIN_VALUE;

    @SneakyThrows
    public RollingFileAppender(LogLevel level,
                               String file,
                               int maxFiles,
                               long maxFileMb) {
        super(level);
        Path filePath = Paths.get(file).toAbsolutePath();
        String fileName = filePath.getFileName().toString();
        int index = fileName.lastIndexOf('.');

        this.filePrefix = index == -1 ? fileName : fileName.substring(0, index);
        this.fileSuffix = index == -1 ? "" : fileName.substring(index);
        this.fileDirectory = filePath.getParent();

        this.maxFiles = Math.max(maxFiles, 0);
        this.maxFileSize = (maxFileMb > 0) ? maxFileMb * 1024 * 1024 : Long.MAX_VALUE;

        Files.createDirectories(fileDirectory);
        files = LogDirectoryVisitor.visit(fileDirectory, filePrefix, fileSuffix, fileMiddle, fileDateTimeFormatter, maxFiles);

        LogFile logFile = files.peekLast();

        if (logFile == null) {
            openNewFile();
        } else {
            openExistingFile(logFile);
        }
    }

    @SneakyThrows
    private FileChannel openChannel(Path file) {
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
     * because there is always only one thread will call roll() and append()
     */
    @SneakyThrows
    private void roll() {
        closeFile();
        openNewFile();
        clean();
    }

    @SneakyThrows
    private void openNewFile() {
        ZonedDateTime now = ZonedDateTime.now(TimeZoneConstant.ZONE_ID);
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS)
                .truncatedTo(ChronoUnit.DAYS);

        Path filePath = getFilePath(now);

        channel = openChannel(filePath);
        currentFile = new LogFile(filePath, now, nextIndex);
        files.add(currentFile);

        nextFileSize = channel.size();
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex++;
    }

    @SneakyThrows
    private void openExistingFile(LogFile existingFile) {
        ZonedDateTime now = existingFile.dateTime();
        ZonedDateTime next = now.plus(1, ChronoUnit.DAYS)
                .truncatedTo(ChronoUnit.DAYS);

        Path filePath = existingFile.path();

        channel = openChannel(filePath);
        currentFile = existingFile;

        nextFileSize = channel.size();
        nextDay = TimeUnit.SECONDS.toNanos(next.toEpochSecond());
        nextIndex = existingFile.index() + 1;
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
                try {
                    Files.deleteIfExists(file.path());
                } catch (Exception ignored) {
                }
            }
        }
    }

    private Path getFilePath(ZonedDateTime currentDay) {
        String name = filePrefix
                + FIELD_DELIMITER
                + fileDateTimeFormatter.format(currentDay)
                + FIELD_DELIMITER
                + nextIndex
                + fileSuffix;
        return fileDirectory.resolve(name);
    }

}