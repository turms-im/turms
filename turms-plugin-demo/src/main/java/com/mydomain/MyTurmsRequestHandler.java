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

package com.mydomain;

import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.infra.plugin.extension.ClientRequestTransformer;

/**
 * @author James Chen
 */
public class MyTurmsRequestHandler extends TurmsExtension implements ClientRequestTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTurmsRequestHandler.class);

    @Override
    public Mono<ClientRequest> transform(@NotNull ClientRequest clientRequest) {
        TurmsRequest.Builder builder = clientRequest.turmsRequestBuilder();
        if (builder.getKindCase() == TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
            builder.getCreateMessageRequestBuilder()
                    .setText("Hi Turms, I have changed the text of the request");
        }
        LOGGER.info("Hi Turms, I have handled the request");
        return Mono.just(clientRequest);
    }

}