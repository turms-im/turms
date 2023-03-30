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

package system.im.turms.service.domain.message.access.servicerequest.controller;

import helper.NotificationUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest;
import im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.message.access.servicerequest.controller.MessageServiceController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

@TestMethodOrder(OrderAnnotation.class)
class MessageServiceControllerST extends BaseServiceControllerTest<MessageServiceController> {

    private static final long SENDER_ID = 1;
    private static final DeviceType SENDER_DEVICE_TYPE = DeviceType.DESKTOP;
    private static final byte[] SENDER_IP = new byte[]{127, 0, 0, 1};
    private static final long RECIPIENT_ID = 2;
    private static final long TARGET_GROUP_ID = 1;
    private static final long REQUEST_ID = 1;

    private static Long privateMessageId;
    private static Long groupMessageId;

    // Create

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateMessageRequest_sendPrivateMessage_shouldReturnMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setRecipientId(RECIPIENT_ID)
                        .setDeliveryDate(System.currentTimeMillis())
                        .setText("hello"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOkAndRecipients(resultMono,
                result -> privateMessageId =
                        NotificationUtil.getLongOrThrow(result.dataForRequester()),
                RECIPIENT_ID);
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateMessageRequest_sendGroupMessage_shouldReturnMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setGroupId(TARGET_GROUP_ID)
                        .setDeliveryDate(System.currentTimeMillis())
                        .setText("hello"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> groupMessageId =
                        NotificationUtil.getLongOrThrow(result.dataForRequester()));
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateMessageRequest_forwardPrivateMessage_shouldReturnForwardedMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setMessageId(privateMessageId)
                        .setRecipientId(RECIPIENT_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .hasLong()).isTrue());
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateMessageRequest_forwardGroupMessage_shouldReturnForwardedMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setMessageId(privateMessageId)
                        .setGroupId(TARGET_GROUP_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .hasLong()).isTrue());
    }

    // Update

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateMessageRequest_recallMessage_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateMessageRequest(UpdateMessageRequest.newBuilder()
                        .setMessageId(groupMessageId)
                        .setRecallDate(System.currentTimeMillis()))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateMessageRequest_updateSentMessage_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateMessageRequest(UpdateMessageRequest.newBuilder()
                        .setMessageId(privateMessageId)
                        .setText("I have modified the message"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    // Query

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryMessagesRequest_queryMessages_shouldReturnNotEmptyMessages() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryMessagesRequest(QueryMessagesRequest.newBuilder()
                        .setAreGroupMessages(false)
                        .addFromIds(SENDER_ID)
                        .setMaxCount(10))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(RECIPIENT_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryMessagesRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getMessages()
                        .getMessagesList()).isNotEmpty());
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryMessagesRequest_queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryMessagesRequest(QueryMessagesRequest.newBuilder()
                        .setAreGroupMessages(true)
                        .setMaxCount(1)
                        .setWithTotal(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, SENDER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryMessagesRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getMessagesWithTotalList()
                        .getMessagesWithTotalListList()).isNotEmpty());
    }

}