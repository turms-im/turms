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

import im.turms.plugin.rasa.core.RasaClient;
import im.turms.plugin.rasa.core.RasaRequest;
import im.turms.plugin.rasa.core.RasaResponse;
import im.turms.plugin.rasa.property.InstanceFindStrategy;
import im.turms.plugin.rasa.property.RasaProperties;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.infra.plugin.extension.RequestHandlerResultNotifier;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import jakarta.validation.constraints.NotNull;

/**
 * @author James Chen
 */
public class RasaResponser extends TurmsExtension implements RequestHandlerResultNotifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(RasaResponser.class);

    private String responseDelimiter;

    private MessageService messageService;
    private Set<Long> chatbotUserIds;
    private Map<Long, RasaClient> idToClient;

    @Override
    public void onStarted() {
        setUp();
    }

    private void setUp() {
        RasaProperties properties = loadProperties(RasaProperties.class);
        if (!properties.isEnabled() || properties.getInstanceFindStrategy() != InstanceFindStrategy.PROPERTY) {
            return;
        }
        List<RasaProperties.InstanceProperties> instances = properties.getInstances();
        if (instances.isEmpty()) {
            return;
        }
        this.responseDelimiter = properties.getResponseDelimiter();
        int size = instances.size();
        Map<URI, RasaClient> uriToClient = CollectionUtil
                .newMapWithExpectedSize(size);
        Map<Long, RasaClient> idToClient = CollectionUtil
                .newMapWithExpectedSize(size);
        for (RasaProperties.InstanceProperties instance : instances) {
            String endpoint = instance.getUrl();
            URI uri;
            try {
                uri = new URI(endpoint);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Illegal endpoint URL: " + endpoint, e);
            }
            RasaClient newClient = uriToClient.computeIfAbsent(uri, RasaClient::new);
            Long chatbotUserId = instance.getChatbotUserId();
            RasaClient existingClient = idToClient.put(chatbotUserId, newClient);
            if (existingClient != null) {
                throw new IllegalArgumentException("Found a duplicate chatbot user ID: " + chatbotUserId);
            }
        }
        this.idToClient = Map.copyOf(idToClient);
        chatbotUserIds = CollectionUtil.toImmutableSet(idToClient.keySet());
        ApplicationContext context = getContext();
        messageService = context.getBean(MessageService.class);
    }

    @Override
    public Mono<RequestHandlerResult> notify(@NotNull RequestHandlerResult result,
                                             @NotNull Long requesterId,
                                             @NotNull DeviceType requesterDevice) {
        // 1. Validate
        TurmsRequest request = result.dataForRecipients();
        if (request.getKindCase() != TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
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
        Set<Long> currentChatbotUserIds = CollectionUtil
                .intersection(recipients, chatbotUserIds);
        if (currentChatbotUserIds.isEmpty()) {
            return Mono.just(result);
        }
        // 2. Send requests
        boolean isGroupMessage = createMessageRequest.hasGroupId();
        long targetId = isGroupMessage
                ? createMessageRequest.getGroupId()
                : createMessageRequest.getRecipientId();
        List<Mono<Void>> sendRequests = new ArrayList<>(currentChatbotUserIds.size());
        for (Long chatbotUserId : currentChatbotUserIds) {
            RasaClient client = idToClient.get(chatbotUserId);
            Mono<Void> sendRequest = client
                    .sendRequest(new RasaRequest(requesterId, text))
                    .flatMap(responses -> {
                        if (responses.isEmpty()) {
                            return Mono.empty();
                        }
                        String responseText;
                        if (responses.size() == 1) {
                            responseText = responses.iterator().next().text();
                        } else {
                            // TODO: introduce our own efficient impl
                            StringJoiner joiner = new StringJoiner(responseDelimiter);
                            for (RasaResponse response : responses) {
                                joiner.add(response.text());
                            }
                            responseText = joiner.toString();
                        }
                        return messageService.authAndSaveAndSendMessage(true,
                                chatbotUserId,
                                null,
                                null,
                                null,
                                isGroupMessage,
                                true,
                                responseText,
                                null,
                                isGroupMessage ? targetId : requesterId,
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
                        try (TracingCloseableContext ignored = TracingContext.getTraceIdFromContext(context).asCloseable()) {
                            LOGGER.error("Caught an error while sending requests to Rasa servers", t);
                        }
                    });
            // 3. Return final handler result
            Set<Long> recipientIds = CollectionUtil.remove(recipients, currentChatbotUserIds);
            return Mono.just(result.withRecipients(recipientIds));
        });
    }

}