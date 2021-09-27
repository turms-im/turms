package com.mydomain;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.server.common.plugin.TurmsExtension;
import im.turms.service.plugin.extension.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
public class MyTurmsRequestHandler extends TurmsExtension implements ClientRequestHandler {

    private static final Logger logger = LogManager.getLogger(MyTurmsRequestHandler.class);

    @Override
    public Mono<ClientRequest> transform(@NotNull ClientRequest clientRequest) {
        TurmsRequest turmsRequest = clientRequest.turmsRequest();
        if (turmsRequest.getKindCase() == TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
            CreateMessageRequest request = turmsRequest.getCreateMessageRequest();
            CreateMessageRequest.Builder builder = request.toBuilder()
                    .setText("Hi Turms, I have changed the text of the request");
            turmsRequest = turmsRequest.toBuilder()
                    .setCreateMessageRequest(builder)
                    .build();
        }
        logger.info("Hi Turms, I have handled the request");
        ClientRequest newRequest = new ClientRequest(
                clientRequest.userId(),
                clientRequest.deviceType(),
                clientRequest.requestId(),
                turmsRequest);
        return Mono.just(newRequest);
    }

    @Override
    public Mono<RequestHandlerResult> handleClientRequest(@NotNull ClientRequest clientRequest) {
        return Mono.empty();
    }

}