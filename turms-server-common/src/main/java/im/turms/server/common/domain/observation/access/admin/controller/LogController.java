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

package im.turms.server.common.domain.observation.access.admin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.appender.file.RollingFileAppender;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
@RestController("logs")
public class LogController {

    private final Path logDir;

    public LogController() {
        Path logDir = null;
        List<Appender> appenders = LoggerFactory.getDefaultAppenders();
        for (Appender appender : appenders) {
            if (appender instanceof RollingFileAppender fileAppender) {
                // We use the same dir for log files,
                // so we just need to find the first dir.
                logDir = fileAppender.getFileDirectory()
                        .normalize()
                        .toAbsolutePath();
                break;
            }
        }
        this.logDir = logDir;
    }

    @GetMapping
    @RequiredPermission(AdminPermission.LOG_QUERY)
    public FileResource getLogFile(@QueryParam String path) {
        if (null == logDir || Files.notExists(logDir)) {
            throw ResponseException.get(ResponseStatusCode.RESOURCE_NOT_FOUND);
        }
        Path logFilePath = Path.of(path);
        logFilePath = logDir.resolve(logFilePath)
                .normalize();
        if (!logFilePath.startsWith(logDir)) {
            throw ResponseException.get(ResponseStatusCode.RESOURCE_NOT_FOUND);
        }
        String filename = logFilePath.getFileName()
                .toString();
        return new FileResource(filename, logFilePath);
    }

    @GetMapping("paths")
    @RequiredPermission(AdminPermission.LOG_QUERY)
    public HttpHandlerResult<ResponseDTO<Collection<String>>> getLogPaths() {
        if (null == logDir || Files.notExists(logDir)) {
            return HttpHandlerResult.okIfTruthy(Collections.emptyList());
        }
        try (Stream<Path> stream = Files.list(logDir)) {
            List<String> logFiles = stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toList();
            return HttpHandlerResult.okIfTruthy(logFiles);
        } catch (IOException e) {
            throw new InputOutputException("Failed to list log files", e);
        }
    }

}