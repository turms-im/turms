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
package system.im.turms.turms.workflow.access.servicerequest;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.common.model.dto.request.message.QueryMessagesRequest;
import im.turms.common.model.dto.request.message.UpdateMessageRequest;
import im.turms.turms.workflow.access.servicerequest.controller.MessageServiceController;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class MessageServiceControllerST extends BaseServiceControllerTest<MessageServiceController> {

    private static final long SENDER_ID = 1;
    private static final DeviceType SENDER_DEVICE_TYPE = DeviceType.DESKTOP;
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
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOkAndRecipients(resultMono,
                result -> privateMessageId = result.getDataForRequester().getIds().getValues(0),
                RECIPIENT_ID);
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateMessageRequest_sendGroupMessage_shouldReturnMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setRecipientId(TARGET_GROUP_ID)
                        .setDeliveryDate(System.currentTimeMillis())
                        .setText("hello"))
                .build();
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> groupMessageId = result.getDataForRequester().getIds().getValues(0));
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateMessageRequest_forwardPrivateMessage_shouldReturnForwardedMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setMessageId(privateMessageId)
                        .setRecipientId(RECIPIENT_ID))
                .build();
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getIds().getValuesList()).isNotEmpty());
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleCreateMessageRequest_forwardGroupMessage_shouldReturnForwardedMessageId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateMessageRequest(CreateMessageRequest.newBuilder()
                        .setMessageId(privateMessageId)
                        .setGroupId(TARGET_GROUP_ID))
                .build();
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateMessageRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getIds().getValuesList()).isNotEmpty());
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
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
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
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
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
                        .setFromId(SENDER_ID)
                        .setSize(10))
                .build();
        ClientRequest clientRequest = new ClientRequest(RECIPIENT_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryMessagesRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.getDataForRequester().getMessages().getMessagesList()).isNotEmpty());
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryMessagesRequest_queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryMessagesRequest(QueryMessagesRequest.newBuilder()
                        .setSize(1)
                        .setWithTotal(true))
                .build();
        ClientRequest clientRequest = new ClientRequest(SENDER_ID, SENDER_DEVICE_TYPE, REQUEST_ID, request, null);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryMessagesRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.getDataForRequester().getMessagesWithTotalList().getMessagesWithTotalListList()).isNotEmpty());
    }


}