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

import org.graalvm.polyglot.Value;

/**
 * @author James Chen
 */
public class ValueInspector {

    private ValueInspector() {
    }

    public static boolean isTrue(Value bool) {
        return bool != null && bool.isBoolean() && bool.asBoolean();
    }

    public static boolean isFalse(Value bool) {
        return !isTrue(bool);
    }

    public static Value returnIfFunction(Value value) {
        return value != null && value.canExecute()
                ? value
                : null;
    }

}
