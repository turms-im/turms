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

package im.turms.server.common.infra.logging.core.model;

/**
 * @author James Chen
 */
public enum LogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    public static final int TRACE_VALUE = 0;
    public static final int DEBUG_VALUE = 1;
    public static final int INFO_VALUE = 2;
    public static final int WARN_VALUE = 3;
    public static final int ERROR_VALUE = 4;
    public static final int FATAL_VALUE = 5;

    public boolean isLoggable(LogLevel enabledLevel) {
        return enabledLevel.ordinal() <= ordinal();
    }

}
