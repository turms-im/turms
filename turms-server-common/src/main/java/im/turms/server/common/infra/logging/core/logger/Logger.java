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

package im.turms.server.common.infra.logging.core.logger;

import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.logging.core.model.LogLevel;

/**
 * @author James Chen
 */
public interface Logger {
    boolean isTraceEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();

    boolean isFatalEnabled();

    boolean isEnabled(LogLevel logLevel);

    void log(LogLevel level, String message);

    void log(LogLevel level, String message, Object... objects);

    void log(LogLevel level, String message, Throwable throwable);

    void debug(String message, Object... args);

    void info(String message, Object... args);

    void info(ByteBuf message);

    void warn(String message);

    void warn(String message, Object... args);

    void error(Throwable throwable);

    void error(String message, @Nullable Throwable throwable);

    void error(String message, Object... args);

    void error(ByteBuf message);

    void fatal(String message, Object... args);

    void fatal(String message, Throwable throwable);

    void fatal(String message);
}
