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

package im.turms.server.common.client;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.netty.resources.LoopResources;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
public abstract class TurmsClient {

    private final Map<Long, Sinks.One<TurmsNotification>> pendingRequestMap = new ConcurrentHashMap<>(128);

    public static TurmsTcpClient tcp() {
        return new TurmsTcpClient();
    }

    public abstract Mono<Void> connect(String host, int port, LoopResources loopResources);

    public abstract Mono<TurmsNotification> login(long userId, DeviceType deviceType, @Nullable String password);

    public abstract Mono<Void> logout();

    public abstract Mono<TurmsNotification> sendRequest(TurmsRequest.Builder requestBuilder);

    Mono<TurmsNotification> waitForResponse(TurmsRequest request) {
        long requestId = request.getRequestId();
        Sinks.One<TurmsNotification> sink = Sinks.one();
        pendingRequestMap.put(requestId, sink);
        return sink.asMono();
    }

    void handleResponse(TurmsNotification notification) {
        if (!notification.hasRequestId()) {
            return;
        }
        long requestId = notification.getRequestId();
        Sinks.One<TurmsNotification> sink = pendingRequestMap.remove(requestId);
        if (sink != null) {
            sink.tryEmitValue(notification);
        }
    }

}