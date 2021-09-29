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

package im.turms.plugin.spam;

import im.turms.server.common.plugin.TurmsExtension;
import im.turms.service.plugin.extension.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
public class SpamDetectionProvider extends TurmsExtension implements ClientRequestHandler {


    @Override
    public Mono<ClientRequest> transform(ClientRequest clientRequest) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<RequestHandlerResult> handleClientRequest(ClientRequest clientRequest) {
        // TODO
        throw new UnsupportedOperationException();
    }

}