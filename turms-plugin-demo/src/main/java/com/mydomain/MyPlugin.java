package com.mydomain;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.server.common.plugin.base.TurmsPlugin;
import im.turms.service.plugin.extension.handler.ClientRequestHandler;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

public class MyPlugin extends TurmsPlugin {

    private static final Logger logger = LogManager.getLogger(MyPlugin.class);

    public MyPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class MyTurmsRequestHandler extends ClientRequestHandler {
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

}