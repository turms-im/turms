package com.mydomain;

import com.google.protobuf.StringValue;
import im.turms.turms.plugin.ClientRequestHandler;
import im.turms.turms.plugin.TurmsPlugin;
import im.turms.turms.pojo.bo.RequestResult;
import im.turms.turms.pojo.bo.TurmsRequestWrapper;
import im.turms.turms.pojo.request.TurmsRequest;
import im.turms.turms.pojo.request.message.CreateMessageRequest;
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

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Extension
    public static class MyTurmsRequestHandler implements ClientRequestHandler {
        @Override
        public Mono<TurmsRequestWrapper> transform(@NotNull TurmsRequestWrapper turmsRequestWrapper) {
            TurmsRequest turmsRequest = turmsRequestWrapper.getTurmsRequest();
            if (turmsRequest.getKindCase() == TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
                CreateMessageRequest request = turmsRequest.getCreateMessageRequest();
                CreateMessageRequest.Builder builder = request.toBuilder()
                        .setText(StringValue.newBuilder().setValue("Hi Turms, I have changed the text of the request")
                                .build());
                turmsRequest = turmsRequest.toBuilder()
                        .setCreateMessageRequest(builder)
                        .build();
                turmsRequestWrapper.setTurmsRequest(turmsRequest);
            }
            logger.info("Hi Turms, I have handled the request");
            return Mono.just(turmsRequestWrapper);
        }

        @Override
        public Mono<RequestResult> handleTurmsRequest(@NotNull TurmsRequestWrapper turmsRequestWrapper) {
            return Mono.empty();
        }
    }
}