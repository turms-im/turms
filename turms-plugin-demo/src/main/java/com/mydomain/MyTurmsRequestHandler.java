package com.mydomain;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.service.infra.plugin.extension.ClientRequestTransformer;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

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