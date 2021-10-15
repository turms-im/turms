package com.mydomain;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.plugin.TurmsExtension;
import im.turms.service.plugin.extension.ClientRequestTransformer;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
public class MyTurmsRequestHandler extends TurmsExtension implements ClientRequestTransformer {

    private static final Logger logger = LogManager.getLogger(MyTurmsRequestHandler.class);

    @Override
    public Mono<ClientRequest> transform(@NotNull ClientRequest clientRequest) {
        TurmsRequest.Builder builder = clientRequest.turmsRequestBuilder();
        if (builder.getKindCase() == TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
            builder.getCreateMessageRequestBuilder()
                    .setText("Hi Turms, I have changed the text of the request");
        }
        logger.info("Hi Turms, I have handled the request");
        return Mono.just(clientRequest);
    }

}