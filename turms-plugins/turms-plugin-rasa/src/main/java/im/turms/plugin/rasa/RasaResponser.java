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

package im.turms.plugin.rasa;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.plugin.rasa.core.RasaClient;
import im.turms.plugin.rasa.core.RasaRequest;
import im.turms.plugin.rasa.core.RasaResponse;
import im.turms.plugin.rasa.property.InstanceFindStrategy;
import im.turms.plugin.rasa.property.RasaProperties;
import im.turms.plugin.rasa.property.RasaResponseFormat;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.infra.plugin.extension.RequestHandlerResultHandler;

/**
 * @author James Chen
 */
public class RasaResponser extends TurmsExtension implements RequestHandlerResultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RasaResponser.class);

    private MessageService messageService;
    private Set<Long> chatbotUserIds;
    private Map<Long, RasaClientInfo> idToClientInfo;

    @Override
    public void onStarted() {
        setUp();
    }

    private void setUp() {
        RasaProperties properties = loadProperties(RasaProperties.class);
        if (!properties.isEnabled()
                || properties.getInstanceFindStrategy() != InstanceFindStrategy.PROPERTY) {
            return;
        }
        List<RasaProperties.InstanceProperties> instancePropertiesList = properties.getInstances();
        if (instancePropertiesList.isEmpty()) {
            return;
        }
        int size = instancePropertiesList.size();
        Map<URI, RasaClientInfo> uriToClientInfo = CollectionUtil.newMapWithExpectedSize(size);
        Map<Long, RasaClientInfo> idToClientInfo = CollectionUtil.newMapWithExpectedSize(size);
        for (RasaProperties.InstanceProperties instanceProperties : instancePropertiesList) {
            String url = instanceProperties.getUrl();
            URI uri;
            try {
                uri = new URI(url);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(
                        "Illegal endpoint URL: "
                                + url,
                        e);
            }
            int requestTimeoutMillis = instanceProperties.getRequest()
                    .getTimeoutMillis();
            RasaClientInfo newClientInfo = uriToClientInfo.computeIfAbsent(uri,
                    key -> new RasaClientInfo(
                            new RasaClient(key, requestTimeoutMillis),
                            instanceProperties));
            Long chatbotUserId = instanceProperties.getChatbotUserId();
            RasaClientInfo existingClientInfo = idToClientInfo.put(chatbotUserId, newClientInfo);
            if (existingClientInfo != null) {
                throw new IllegalArgumentException(
                        "Found a duplicate chatbot user ID: "
                                + chatbotUserId);
            }
        }
        this.idToClientInfo = Map.copyOf(idToClientInfo);
        chatbotUserIds = CollectionUtil.toImmutableSet(idToClientInfo.keySet());
        ApplicationContext context = getContext();
        messageService = context.getBean(MessageService.class);
    }

    @Override
    public Mono<RequestHandlerResult> beforeNotify(
            @NotNull RequestHandlerResult result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice) {
        // 1. Validate
        TurmsRequest request = result.dataForRecipients();
        if (request == null
                || request.getKindCase() != TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
            return Mono.just(result);
        }
        CreateMessageRequest createMessageRequest = request.getCreateMessageRequest();
        String text = createMessageRequest.getText();
        if (StringUtil.isEmpty(text)) {
            return Mono.just(result);
        }
        Set<Long> recipients = result.recipients();
        if (CollectionUtil.isEmpty(recipients)) {
            return Mono.just(result);
        }
        Set<Long> specifiedChatbotUserIds = CollectionUtil.intersection(recipients, chatbotUserIds);
        if (specifiedChatbotUserIds.isEmpty()) {
            return Mono.just(result);
        }
        // 2. Send requests
        boolean isGroupMessage = createMessageRequest.hasGroupId();
        long targetId = isGroupMessage
                ? createMessageRequest.getGroupId()
                : createMessageRequest.getRecipientId();
        List<Mono<Void>> sendRequests = new ArrayList<>(specifiedChatbotUserIds.size());
        for (Long chatbotUserId : specifiedChatbotUserIds) {
            RasaClientInfo clientInfo = idToClientInfo.get(chatbotUserId);
            Mono<Void> sendRequest =
                    clientInfo.client.sendRequest(new RasaRequest(requesterId, text))
                            .flatMap(responses -> {
                                if (responses.isEmpty()) {
                                    return Mono.empty();
                                }
                                // 3. Send rasa responses to the requester
                                RasaProperties.ResponseProperties properties =
                                        clientInfo.properties.getResponse();
                                String responseText = formatResponse(properties, responses);
                                return messageService.authAndSaveAndSendMessage(true,
                                        properties.getPersist()
                                                .getBool(),
                                        chatbotUserId,
                                        null,
                                        null,
                                        null,
                                        isGroupMessage,
                                        true,
                                        responseText,
                                        null,
                                        isGroupMessage
                                                ? targetId
                                                : requesterId,
                                        null,
                                        null,
                                        null);
                            });
            sendRequests.add(sendRequest);
        }
        return Mono.deferContextual(context -> {
            // Don't need to wait responses
            Mono.whenDelayError(sendRequests)
                    .subscribe(null, t -> {
                        try (TracingCloseableContext ignored =
                                TracingContext.getCloseableContext(context)) {
                            LOGGER.error("Caught an error while sending requests to Rasa servers",
                                    t);
                        }
                    });
            // 4. Return final handler result
            Set<Long> recipientIds = CollectionUtil.remove(recipients, specifiedChatbotUserIds);
            return Mono.just(result.withRecipients(recipientIds));
        });
    }

    private String formatResponse(
            RasaProperties.ResponseProperties properties,
            List<RasaResponse> responses) {
        if (properties.getFormat() == RasaResponseFormat.PLAIN) {
            if (responses.size() == 1) {
                return responses.get(0)
                        .text();
            } else {
                StringJoiner joiner = new StringJoiner(properties.getDelimiter());
                for (RasaResponse response : responses) {
                    joiner.add(response.text());
                }
                return joiner.toString();
            }
        }
        try {
            return JsonCodecPool.MAPPER.writeValueAsString(responses);
        } catch (JsonProcessingException e) {
            throw new InputOutputException("Failed to write the responses", e);
        }
    }

    private record RasaClientInfo(
            RasaClient client,
            RasaProperties.InstanceProperties properties
    ) {
    }

}