package com.mydomain;

import com.google.protobuf.StringValue;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.server.common.plugin.base.TurmsPlugin;
import im.turms.turms.plugin.extension.handler.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

public class MyPlugin extends TurmsPlugin {

    private static final Logger logger = LoggerFactory.getLogger(MyPlugin.class);

    public MyPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class MyTurmsRequestHandler extends ClientRequestHandler {
        @Override
        public Mono<ClientRequest> transform(@NotNull ClientRequest clientRequest) {
            TurmsRequest turmsRequest = clientRequest.getTurmsRequest();
            if (turmsRequest.getKindCase() == TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
                CreateMessageRequest request = turmsRequest.getCreateMessageRequest();
                CreateMessageRequest.Builder builder = request.toBuilder()
                        .setText(StringValue.newBuilder().setValue("Hi Turms, I have changed the text of the request")
                                .build());
                turmsRequest = turmsRequest.toBuilder()
                        .setCreateMessageRequest(builder)
                        .build();
                clientRequest.setTurmsRequest(turmsRequest);
            }
            logger.info("Hi Turms, I have handled the request");
            return Mono.just(clientRequest);
        }

        @Override
        public Mono<RequestHandlerResult> handleClientRequest(@NotNull ClientRequest clientRequest) {
            return Mono.empty();
        }
    }

}