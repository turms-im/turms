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
package system.im.turms.service.workflow.access.servicerequest;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.conversation.QueryConversationsRequest;
import im.turms.common.model.dto.request.conversation.UpdateConversationRequest;
import im.turms.common.model.dto.request.conversation.UpdateTypingStatusRequest;
import im.turms.service.workflow.access.servicerequest.controller.ConversationServiceController;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;

import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
class ConversationServiceControllerST extends BaseServiceControllerTest<ConversationServiceController> {

    private static final long USER_ID = 1;
    private static final DeviceType USER_DEVICE = DeviceType.DESKTOP;
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
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
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryConversationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.dataForRequester().hasConversations()).isTrue());
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleQueryConversationsRequest_queryGroupConversations_shouldReturnNotEmptyConversations() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryConversationsRequest(QueryConversationsRequest.newBuilder()
                        .addGroupIds(GROUP_ID))
                .build();
        ClientRequest clientRequest = new ClientRequest(USER_ID, USER_DEVICE, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryConversationsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono, result -> assertThat(result.dataForRequester().hasConversations()).isTrue());
    }

}