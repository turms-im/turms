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

package im.turms.gateway.infra.plugin.extension;

import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.server.common.infra.plugin.ExtensionPoint;

/**
 * @author James Chen
 */
public interface UserAuthenticator extends ExtensionPoint {

    /**
     * @return 1. Return Mono.just(true) if the user is authenticated.
     *         <p>
     *         2. Return Mono.just(false) if the user is unauthenticated.
     *         <p>
     *         3. Return Mono.empty() if the authentication should be processed by the next handler.
     */
    Mono<Boolean> authenticate(@NotNull UserLoginInfo userLoginInfo);

}