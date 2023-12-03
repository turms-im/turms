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

package im.turms.server.common.infra.plugin;

import java.util.List;

import lombok.Getter;
import org.graalvm.polyglot.Value;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.plugin.script.ValueDecoder;
import im.turms.server.common.infra.plugin.script.ValueInspector;

/**
 * @author James Chen
 */
public class JsTurmsExtensionAdaptor extends TurmsExtension {

    @Getter
    private final ExtensionPoint proxy;
    @Getter
    private final List<Class<? extends ExtensionPoint>> extensionPointClasses;
    private final Value onStarted;
    private final Value onStopped;
    private final Value onResumed;
    private final Value onPaused;

    public JsTurmsExtensionAdaptor(
            ExtensionPoint proxy,
            List<Class<? extends ExtensionPoint>> extensionPointClasses,
            Value extension) {
        this.proxy = proxy;
        this.extensionPointClasses = extensionPointClasses;
        onStarted = ValueInspector.returnIfFunction(extension.getMember("onStarted"));
        onStopped = ValueInspector.returnIfFunction(extension.getMember("onStopped"));
        onResumed = ValueInspector.returnIfFunction(extension.getMember("onResumed"));
        onPaused = ValueInspector.returnIfFunction(extension.getMember("onPaused"));
    }

    @Override
    ExtensionPoint getExtensionPoint() {
        return proxy;
    }

    @Override
    protected Mono<Void> onStarted() {
        return execute(onStarted);
    }

    @Override
    protected Mono<Void> onStopped() {
        return execute(onStopped);
    }

    @Override
    protected Mono<Void> onResumed() {
        return execute(onResumed);
    }

    @Override
    protected Mono<Void> onPaused() {
        return execute(onPaused);
    }

    private Mono<Void> execute(Value callback) {
        if (callback == null) {
            return Mono.empty();
        }
        Mono<?> mono = ValueDecoder.decodeAsMonoIfPromise(callback.execute(), false);
        if (mono == null) {
            return Mono.empty();
        }
        return mono.then();
    }

}