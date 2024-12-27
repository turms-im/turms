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

package im.turms.server.common.domain.user.rpc.dto;

import java.util.Date;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.domain.user.rpc.service.RpcUserService;
import im.turms.server.common.infra.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;

/**
 * @author James Chen
 */
@Data
public class CreateUserRequest extends RpcRequest<Long> {

    private static final String NAME = "createUserRequest";

    private static RpcUserService userService;

    private final Long id;
    private final String rawPassword;
    private final String name;
    private final String intro;
    private final String profilePicture;
    private final ProfileAccessStrategy profileAccessStrategy;
    private final Long roleId;
    private final Date registrationDate;
    private final Boolean isActive;

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRequest() {
        return NodeTypeToHandleRpc.GATEWAY;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRespond() {
        return NodeTypeToHandleRpc.SERVICE;
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        if (userService == null) {
            userService = getBean(RpcUserService.class);
        }
    }

    @Override
    public Mono<Long> callAsync() {
        return userService.createUser(id,
                rawPassword,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                roleId,
                registrationDate,
                isActive);
    }
}