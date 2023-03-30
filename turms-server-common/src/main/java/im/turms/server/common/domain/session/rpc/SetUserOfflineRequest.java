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

package im.turms.server.common.domain.session.rpc;

import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.service.ISessionService;
import im.turms.server.common.infra.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;

/**
 * @author James Chen
 */
@Data
public class SetUserOfflineRequest extends RpcRequest<Boolean> {

    private static final String NAME = "setUserOffline";
    private static ISessionService sessionService;

    private final Long userId;
    private final Set<DeviceType> deviceTypes;
    private final SessionCloseStatus closeStatus;

    public SetUserOfflineRequest(
            @NotNull Long userId,
            @Nullable Set<DeviceType> deviceTypes,
            @NotNull SessionCloseStatus closeStatus) {
        this.userId = userId;
        this.deviceTypes = deviceTypes;
        this.closeStatus = closeStatus;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRequest() {
        return NodeTypeToHandleRpc.BOTH;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRespond() {
        return NodeTypeToHandleRpc.GATEWAY;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        if (sessionService == null) {
            sessionService = getBean(ISessionService.class);
        }
    }

    /**
     * @return true if the user had one online session at least.
     */
    @Override
    public Mono<Boolean> callAsync() {
        CloseReason reason = CloseReason.get(closeStatus);
        Mono<Integer> mono = deviceTypes == null || deviceTypes.isEmpty()
                ? sessionService.closeLocalSession(userId, reason)
                : sessionService.closeLocalSession(userId, deviceTypes, reason);
        return mono.map(count -> count > 0);
    }

}
