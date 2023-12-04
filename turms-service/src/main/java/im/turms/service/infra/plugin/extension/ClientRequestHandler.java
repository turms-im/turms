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

package im.turms.service.infra.plugin.extension;

import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;

/**
 * @author James Chen
 */
public interface ClientRequestHandler extends ExtensionPoint {

    /**
     * @return 1. Return a {@link Mono} that completes without emitting any request handler result
     *         if you want to pass the original client request to the next handler to handle.
     *         <p>
     *         2. Return a {@link Mono} that emits a request handler result as the final handle
     *         result if you don't want to pass the client request to the next handler to handle.
     */
    Mono<RequestHandlerResult> handle(@NotNull ClientRequest clientRequest);

}