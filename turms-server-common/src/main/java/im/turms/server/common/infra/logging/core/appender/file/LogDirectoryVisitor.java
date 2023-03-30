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

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.TreeSet;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.InternalLogger;
import im.turms.server.common.infra.time.TimeZoneConst;

/**
 * @author James Chen
 */
public class LogDirectoryVisitor extends SimpleFileVisitor<Path> {

    private final TreeSet<LogFile> files = new TreeSet<>(Comparator.comparingLong(LogFile::index));

    private final String filePrefix;
    private final String fileSuffix;
    private final String fileMiddle;

    private final DateTimeFormatter fileDateTimeFormatter;

    private final int maxFilesToKeep;
    private final boolean deleteExceedFiles;

    public LogDirectoryVisitor(
            String filePrefix,
            String fileSuffix,
            String fileMiddle,
            DateTimeFormatter fileDateTimeFormatter,
            int maxFiles) {
        this.filePrefix = filePrefix;
        this.fileSuffix = fileSuffix;
        this.fileMiddle = fileMiddle;
        this.fileDateTimeFormatter = fileDateTimeFormatter;
        this.maxFilesToKeep = Math.max(1, maxFiles);
        this.deleteExceedFiles = maxFiles > 0;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        try {
            String name = path.getFileName()
                    .toString();
            if (!isLogFile(name)) {
                return FileVisitResult.CONTINUE;
            }
            int indexEnd = name.length() - fileSuffix.length();
            int indexStart = name.lastIndexOf(RollingFileAppender.FIELD_DELIMITER, indexEnd - 1);
            if (indexStart == filePrefix.length() + fileMiddle.length() + 1) {
                long index = Long.parseUnsignedLong(name.substring(indexStart + 1, indexEnd));
                TemporalAccessor time = fileDateTimeFormatter
                        .parse(name.substring(filePrefix.length() + 1, indexStart));
                ZonedDateTime timestamp = LocalDate.from(time)
                        .atStartOfDay(TimeZoneConst.ZONE_ID);
                handleNewLogFile(path, timestamp, index);
            }
        } catch (Exception e) {
            InternalLogger.INSTANCE.warn("Could not figure out if a file matches the template: "
                    + path, e);
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isLogFile(String name) {
        return (name.length() > filePrefix.length() + fileSuffix.length() + fileMiddle.length() + 1)
                && name.startsWith(filePrefix)
                && name.endsWith(fileSuffix);
    }

    private void handleNewLogFile(Path path, ZonedDateTime timestamp, long index) {
        String fileName = path.getFileName()
                .toString();
        boolean isArchive = fileName.endsWith(RollingFileAppender.ARCHIVE_FILE_SUFFIX);
        Path filePath;
        Path archivePath;
        if (isArchive) {
            filePath = path.resolveSibling(fileName.substring(0,
                    fileName.length() - RollingFileAppender.ARCHIVE_FILE_SUFFIX.length()));
            archivePath = path;
        } else {
            filePath = path;
            archivePath = path.resolveSibling(fileName + RollingFileAppender.ARCHIVE_FILE_SUFFIX);
        }
        LogFile file = new LogFile(filePath, archivePath, timestamp, index);
        files.add(file);
        if (files.size() > maxFilesToKeep) {
            LogFile firstLogFile = files.first();
            files.remove(firstLogFile);
            if (deleteExceedFiles) {
                try {
                    Files.deleteIfExists(firstLogFile.path());
                } catch (Exception ignored) {
                }
                try {
                    Path firstArchivePath = firstLogFile.archivePath();
                    if (firstArchivePath != null) {
                        Files.deleteIfExists(firstArchivePath);
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    public static Deque<LogFile> visit(
            Path directory,
            String prefix,
            String suffix,
            String middle,
            DateTimeFormatter template,
            int maxFiles) {
        LogDirectoryVisitor visitor =
                new LogDirectoryVisitor(prefix, suffix, middle, template, maxFiles);
        try {
            Files.walkFileTree(directory, Collections.emptySet(), 1, visitor);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to visit the directory: "
                            + directory,
                    e);
        }
        return new ArrayDeque<>(visitor.files);
    }

}