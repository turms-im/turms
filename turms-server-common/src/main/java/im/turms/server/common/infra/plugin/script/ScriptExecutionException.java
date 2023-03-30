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

package im.turms.server.common.infra.plugin.script;

import lombok.Getter;

import im.turms.server.common.infra.plugin.PluginExecutionException;

/**
 * @author James Chen
 */
@Getter
public class ScriptExecutionException extends PluginExecutionException {

    private final ScriptExceptionSource source;

    public ScriptExecutionException(String message, Throwable cause, ScriptExceptionSource source) {
        super(message, cause);
        this.source = source;
    }

    public ScriptExecutionException(String message, ScriptExceptionSource source) {
        this(message, null, source);
    }

    public ScriptExecutionException(Throwable cause, ScriptExceptionSource source) {
        this(null, cause, source);
    }

}