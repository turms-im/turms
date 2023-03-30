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

package system.im.turms.service.domain.conversation.access.servicerequest.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest;
import im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest;
import im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.conversation.access.servicerequest.controller.ConversationServiceController;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

@TestMethodOrder(OrderAnnotation.class)
class ConversationServiceControllerST
        extends BaseServiceControllerTest<ConversationServiceController> {

    private static final long USER_ID = 1;
    private static final DeviceType USER_DEVICE = DeviceType.DESKTOP;
    private static final byte[] USER_IP = new byte[]{127, 0, 0, 1};
    private static final long RELATED_USER_ID = 2;
    private static final long GROUP_ID = 1;
    private static final long REQUEST_ID = 9999;

    // Update

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateConversationRequest_updatePrivateConversationReadDate_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateConversationRequest(UpdateConversationRequest.newBuilder()
                        .setReadDate(System.currentTimeMillis())
                        .setTargetId(RELATED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateConversationRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateConversationRequest_updateGroupConversationReadDate_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateConversationRequest(UpdateConversationRequest.newBuilder()
                        .setReadDate(System.currentTimeMillis())
                        .setGroupId(GROUP_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateConversationRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateTypingStatusRequest_updatePrivateConversationTypingStatus_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateTypingStatusRequest(UpdateTypingStatusRequest.newBuilder()
                        .setIsGroupMessage(false)
                        .setToId(RELATED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateTypingStatusRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleUpdateTypingStatusRequest_updateGroupConversationTypingStatus_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateTypingStatusRequest(UpdateTypingStatusRequest.newBuilder()
                        .setIsGroupMessage(true)
                        .setToId(GROUP_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateTypingStatusRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    // Query

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryConversationsRequest_queryPrivateConversations_shouldReturnNotEmptyConversations() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryConversationsRequest(QueryConversationsRequest.newBuilder()
                        .addTargetIds(RELATED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryConversationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getConversations()
                        .getPrivateConversationsCount()).isZero());

        request = TurmsRequest.newBuilder()
                .setQueryConversationsRequest(QueryConversationsRequest.newBuilder()
                        .addTargetIds(USER_ID))
                .build();
        clientRequest =
                new ClientRequest(RELATED_USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        resultMono = getController().handleQueryConversationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getConversations()
                        .getPrivateConversationsCount()).isPositive());
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryConversationsRequest_queryGroupConversations_shouldReturnNotEmptyConversations() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryConversationsRequest(QueryConversationsRequest.newBuilder()
                        .addGroupIds(GROUP_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryConversationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getConversations()
                        .getGroupConversationsCount()).isPositive());
    }

}