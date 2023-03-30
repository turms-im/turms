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

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

/**
 * @author James Chen
 */
public class JsContext {

    // language=JS
    private static final String PLUGIN_CONTEXT_PATCH_STR = """
            class TurmsPlugin {
                static get isTurmsPlugin() {
                    return true;
                }
            }
            class TurmsExtension {
                static get isTurmsExtension() {
                    return true;
                }
            }
            globalThis.TurmsPlugin = TurmsPlugin;
            globalThis.TurmsExtension = TurmsExtension;
            """;
    // language=JS
    private static final String FETCH_PATCH_STR = "globalThis.fetch = turms.http.fetch;";

    private static final Source PLUGIN_CONTEXT_PATCH =
            Source.newBuilder("js", PLUGIN_CONTEXT_PATCH_STR, "plugin-context-patch")
                    .cached(true)
                    .buildLiteral();
    private static final Source FETCH_PATCH =
            Source.newBuilder("js", FETCH_PATCH_STR, "fetch-patch")
                    .cached(true)
                    .buildLiteral();

    public final JsLogger log;
    public final JsHttp http;

    private JsContext(String name) {
        this.log = new JsLogger(name);
        this.http = new JsHttp();
    }

    public static void bindBeforeInitialization(Context context) {
        context.eval(PLUGIN_CONTEXT_PATCH);
    }

    public static void bindAfterInitialization(Context context, Value bindings, String name) {
        bindings.putMember("turms", new JsContext(name));
        context.eval(FETCH_PATCH);
    }

}
