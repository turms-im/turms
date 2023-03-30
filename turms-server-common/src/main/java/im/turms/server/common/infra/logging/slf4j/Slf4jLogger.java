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

package im.turms.server.common.infra.logging.slf4j;

import org.slf4j.helpers.MarkerIgnoringBase;

import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.model.LogLevel;

/**
 * @author James Chen
 */
public class Slf4jLogger extends MarkerIgnoringBase {

    private final Logger logger;

    public Slf4jLogger(String name, Logger logger) {
        this.name = name;
        this.logger = logger;
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String message) {
        logger.log(LogLevel.TRACE, message);
    }

    @Override
    public void trace(String message, Object object) {
        logger.log(LogLevel.TRACE, message, object);
    }

    @Override
    public void trace(String message, Object object1, Object object2) {
        logger.log(LogLevel.TRACE, message, object1, object2);
    }

    @Override
    public void trace(String message, Object[] objects) {
        logger.log(LogLevel.TRACE, message, objects);
    }

    @Override
    public void trace(String message, Throwable exception) {
        logger.log(LogLevel.TRACE, message, exception);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String message) {
        logger.log(LogLevel.DEBUG, message);
    }

    @Override
    public void debug(String message, Object object) {
        logger.log(LogLevel.DEBUG, message, object);
    }

    @Override
    public void debug(String message, Object object1, Object object2) {
        logger.log(LogLevel.DEBUG, message, object1, object2);
    }

    @Override
    public void debug(String message, Object[] objects) {
        logger.log(LogLevel.DEBUG, message, objects);
    }

    @Override
    public void debug(String message, Throwable exception) {
        logger.log(LogLevel.DEBUG, message, exception);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String message) {
        logger.log(LogLevel.INFO, message);
    }

    @Override
    public void info(String message, Object object) {
        logger.log(LogLevel.INFO, message, object);
    }

    @Override
    public void info(String message, Object object1, Object object2) {
        logger.log(LogLevel.INFO, message, object1, object2);
    }

    @Override
    public void info(String message, Object[] objects) {
        logger.log(LogLevel.INFO, message, objects);
    }

    @Override
    public void info(String message, Throwable exception) {
        logger.log(LogLevel.INFO, message, exception);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String message) {
        logger.log(LogLevel.WARN, message);
    }

    @Override
    public void warn(String message, Object object) {
        logger.log(LogLevel.WARN, message, object);
    }

    @Override
    public void warn(String message, Object object1, Object object2) {
        logger.log(LogLevel.WARN, message, object1, object2);
    }

    @Override
    public void warn(String message, Object[] objects) {
        logger.log(LogLevel.WARN, message, objects);
    }

    @Override
    public void warn(String message, Throwable exception) {
        logger.log(LogLevel.WARN, message, exception);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String message) {
        logger.log(LogLevel.ERROR, message);
    }

    @Override
    public void error(String message, Object object) {
        logger.log(LogLevel.ERROR, message, object);
    }

    @Override
    public void error(String message, Object object1, Object object2) {
        logger.log(LogLevel.ERROR, message, object1, object2);
    }

    @Override
    public void error(String message, Object[] objects) {
        logger.log(LogLevel.ERROR, message, objects);
    }

    @Override
    public void error(String message, Throwable exception) {
        logger.log(LogLevel.ERROR, message, exception);
    }

}