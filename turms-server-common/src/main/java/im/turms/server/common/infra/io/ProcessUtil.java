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

package im.turms.server.common.infra.io;

import java.io.IOException;
import java.io.InputStream;

import reactor.core.publisher.Mono;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.reactor.PublisherUtil;

/**
 * @author James Chen
 */
public final class ProcessUtil {

    private ProcessUtil() {
    }

    public static Mono<Void> run(String... command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        return PublisherUtil.fromFuture(() -> builder.start()
                .onExit())
                .flatMap(process -> {
                    int exitValue = process.exitValue();
                    if (exitValue == 0) {
                        return Mono.empty();
                    }
                    InputStream inputStream = process.getInputStream();
                    InputStream errorStream = process.getErrorStream();
                    String info = null;
                    String error = null;
                    Exception suppressedException = null;
                    try {
                        info = inputStream.available() == 0
                                ? null
                                : new String(inputStream.readAllBytes());
                        error = errorStream.available() == 0
                                ? null
                                : new String(errorStream.readAllBytes());
                    } catch (IOException e) {
                        suppressedException = e;
                    }
                    String message;
                    if (StringUtil.isNotBlank(info)) {
                        if (StringUtil.isNotBlank(error)) {
                            message = "Exit value: "
                                    + exitValue
                                    + ". Info: "
                                    + info
                                    + ". Error: "
                                    + error;
                        } else {
                            message = "Exit value: "
                                    + exitValue
                                    + ". Info: "
                                    + info;
                        }
                    } else if (StringUtil.isNotBlank(error)) {
                        message = "Exit value: "
                                + exitValue
                                + ". Error: "
                                + error;
                    } else {
                        message = "Exit value: "
                                + exitValue;
                    }
                    RuntimeException exception = new RuntimeException(message);
                    if (suppressedException != null) {
                        exception.addSuppressed(suppressedException);
                    }
                    return Mono.error(exception);
                });
    }

}