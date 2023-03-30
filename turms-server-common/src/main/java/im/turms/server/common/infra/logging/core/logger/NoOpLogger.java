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
public class NoOpLogger implements Logger {

    public static final NoOpLogger INSTANCE = new NoOpLogger();

    private NoOpLogger() {
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(LogLevel logLevel) {
        return false;
    }

    @Override
    public void log(LogLevel level, String message) {
    }

    @Override
    public void log(LogLevel level, String message, Object... objects) {
    }

    @Override
    public void log(LogLevel level, String message, Throwable throwable) {
    }

    @Override
    public void debug(String message, Object... args) {
    }

    @Override
    public void info(String message, Object... args) {
    }

    @Override
    public void info(ByteBuf message) {
    }

    @Override
    public void warn(String message) {
    }

    @Override
    public void warn(String message, Object... args) {
    }

    @Override
    public void error(Throwable throwable) {
    }

    @Override
    public void error(String message, @Nullable Throwable throwable) {
    }

    @Override
    public void error(String message, Object... args) {
    }

    @Override
    public void error(ByteBuf message) {
    }

    @Override
    public void fatal(String message, Object... args) {
    }

    @Override
    public void fatal(String message, Throwable throwable) {
    }

    @Override
    public void fatal(String message) {
    }
}
