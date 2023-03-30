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

package system.im.turms.service.domain.user.access.servicerequest.controller;

import helper.NotificationUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest;
import im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.user.access.servicerequest.controller.UserRelationshipServiceController;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.server.common.testing.Constants.ORDER_HIGHEST_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_LOW_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRelationshipServiceControllerST
        extends BaseServiceControllerTest<UserRelationshipServiceController> {

    private static final long USER_ID = 1;
    private static final DeviceType USER_DEVICE_TYPE = DeviceType.DESKTOP;
    private static final byte[] USER_IP = new byte[]{127, 0, 0, 1};
    private static final long REQUEST_ID = 1;

    private static final long BLOCKED_USER_ID = Integer.MAX_VALUE;
    private static final long FRIENDED_USER_ID = Integer.MAX_VALUE + 1L;
    private static final long USER_ID_FOR_FRIEND_REQUEST = Integer.MAX_VALUE + 2L;

    private static Integer relationshipGroupIndex;

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateRelationshipRequest_blockUser_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateRelationshipRequest(CreateRelationshipRequest.newBuilder()
                        .setUserId(BLOCKED_USER_ID)
                        .setBlocked(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateRelationshipRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateRelationshipRequest_friendUser_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateRelationshipRequest(CreateRelationshipRequest.newBuilder()
                        .setUserId(FRIENDED_USER_ID)
                        .setBlocked(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateRelationshipRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateRelationshipGroupRequest_createRelationshipGroup_shouldReturnRelationshipGroupIndex() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateRelationshipGroupRequest(CreateRelationshipGroupRequest.newBuilder()
                        .setName("newGroup"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleCreateRelationshipGroupRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono, result -> {
            relationshipGroupIndex =
                    (int) NotificationUtil.getLongOrThrow(result.dataForRequester());
        });
    }

    @Test
    @Order(ORDER_HIGHEST_PRIORITY)
    void handleCreateFriendRequestRequest_sendFriendRequest_shouldReturnFriendRequestId() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setCreateFriendRequestRequest(CreateFriendRequestRequest.newBuilder()
                        .setRecipientId(USER_ID_FOR_FRIEND_REQUEST)
                        .setContent("content"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleCreateFriendRequestRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .hasLong()).isTrue());
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateFriendRequestRequest_replyFriendRequest_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateFriendRequestRequest(UpdateFriendRequestRequest.newBuilder()
                        .setRequestId(USER_ID)
                        .setResponseAction(ResponseAction.ACCEPT)
                        .setReason("reason"))
                .build();
        ClientRequest clientRequest = new ClientRequest(
                USER_ID_FOR_FRIEND_REQUEST,
                USER_DEVICE_TYPE,
                USER_IP,
                REQUEST_ID,
                request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateFriendRequestRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateRelationshipGroupRequest_updateRelationshipGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateRelationshipGroupRequest(UpdateRelationshipGroupRequest.newBuilder()
                        .setGroupIndex(relationshipGroupIndex)
                        .setNewName("newGroupName"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleUpdateRelationshipGroupRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateRelationshipRequest_moveRelatedUserToGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateRelationshipRequest(UpdateRelationshipRequest.newBuilder()
                        .setNewGroupIndex(1)
                        .setUserId(FRIENDED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateRelationshipRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);

        request = TurmsRequest.newBuilder()
                .setUpdateRelationshipRequest(UpdateRelationshipRequest.newBuilder()
                        .setNewGroupIndex(0)
                        .setUserId(FRIENDED_USER_ID))
                .build();
        clientRequest = new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        resultMono = getController().handleUpdateRelationshipRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryRelationshipsRequest_queryRelationships_shouldReturnUserRelationshipsWithVersion() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryRelationshipsRequest(QueryRelationshipsRequest.newBuilder()
                        .addUserIds(FRIENDED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryRelationshipsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserRelationshipsWithVersion()
                        .getUserRelationshipsCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryRelatedUserIdsRequest_queryRelatedUserIds_shouldReturnRelatedUserIds() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryRelatedUserIdsRequest(QueryRelatedUserIdsRequest.newBuilder())
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryRelatedUserIdsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getLongsWithVersion()
                        .getLongsCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryRelationshipsRequest_queryFriends_shouldReturnFriendRelationships() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryRelationshipsRequest(QueryRelationshipsRequest.newBuilder()
                        .setBlocked(false))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryRelationshipsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserRelationshipsWithVersion()
                        .getUserRelationshipsCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryRelationshipsRequest_queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryRelationshipsRequest(QueryRelationshipsRequest.newBuilder()
                        .setBlocked(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryRelationshipsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserRelationshipsWithVersion()
                        .getUserRelationshipsCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryFriendRequestsRequest_queryFriendRequests_shouldReturnFriendRequests() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryFriendRequestsRequest(QueryFriendRequestsRequest.newBuilder()
                        .setAreSentByMe(true))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryFriendRequestsRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserFriendRequestsWithVersion()
                        .getUserFriendRequestsCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryRelationshipGroupsRequest_queryRelationshipGroups_shouldReturnRelationshipGroups() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryRelationshipGroupsRequest(QueryRelationshipGroupsRequest.newBuilder())
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryRelationshipGroupsRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserRelationshipGroupsWithVersion()
                        .getUserRelationshipGroupsCount()).isPositive());
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleDeleteRelationshipRequest_deleteRelationship_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteRelationshipRequest(DeleteRelationshipRequest.newBuilder()
                        .setUserId(FRIENDED_USER_ID))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleDeleteRelationshipRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_LOW_PRIORITY)
    void handleDeleteRelationshipGroupRequest_deleteRelationshipGroup_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setDeleteRelationshipGroupRequest(DeleteRelationshipGroupRequest.newBuilder()
                        .setGroupIndex(relationshipGroupIndex))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleDeleteRelationshipGroupRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

}
