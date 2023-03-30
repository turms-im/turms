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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import system.im.turms.service.domain.common.access.servicerequest.controller.BaseServiceControllerTest;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest;
import im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserRequest;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.user.access.servicerequest.controller.UserServiceController;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.server.common.testing.Constants.ORDER_HIGH_PRIORITY;
import static im.turms.server.common.testing.Constants.ORDER_MIDDLE_PRIORITY;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceControllerST extends BaseServiceControllerTest<UserServiceController> {

    private static final long USER_ID = 1;
    private static final long USER_ID_2 = 2;
    private static final DeviceType USER_DEVICE_TYPE = DeviceType.DESKTOP;
    private static final byte[] USER_IP = new byte[]{127, 0, 0, 1};
    private static final long REQUEST_ID = 1;

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateUserOnlineStatusRequest_updateOnlineStatus_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateUserOnlineStatusRequest(UpdateUserOnlineStatusRequest.newBuilder()
                        .setUserStatus(UserStatus.BUSY))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleUpdateUserOnlineStatusRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateUserRequest_updateProfile_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateUserRequest(UpdateUserRequest.newBuilder()
                        .setName("123")
                        .setIntro("123"))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateUserRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_HIGH_PRIORITY)
    void handleUpdateUserLocationRequest_updateLocation_shouldSucceed() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setUpdateUserLocationRequest(UpdateUserLocationRequest.newBuilder()
                        .setLatitude(2)
                        .setLongitude(2))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID_2, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleUpdateUserLocationRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono);
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryUserProfilesRequest_queryUserProfiles_shouldReturnUserInfos() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryUserProfilesRequest(QueryUserProfilesRequest.newBuilder()
                        .addUserIds(1))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryUserProfilesRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserInfosWithVersion()
                        .getUserInfosCount()).isEqualTo(1));
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    // TODO
    @Disabled("The test passes in manual testing, but fails in Junit. We need to fix it later")
    void handleQueryNearbyUsersRequest_queryNearbyUsers_shouldReturnUserInfos() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryNearbyUsersRequest(QueryNearbyUsersRequest.newBuilder()
                        .setLatitude(1)
                        .setLongitude(1))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono = getController().handleQueryNearbyUsersRequest()
                .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getNearbyUsers()
                        .getNearbyUsersCount()).isPositive());
    }

    @Test
    @Order(ORDER_MIDDLE_PRIORITY)
    void handleQueryUserOnlineStatusesRequest_queryOnlineStatusesRequest_shouldUserOnlineStatuses() {
        TurmsRequest request = TurmsRequest.newBuilder()
                .setQueryUserOnlineStatusesRequest(QueryUserOnlineStatusesRequest.newBuilder()
                        .addUserIds(1))
                .build();
        ClientRequest clientRequest =
                new ClientRequest(USER_ID, USER_DEVICE_TYPE, USER_IP, REQUEST_ID, request);
        Mono<RequestHandlerResult> resultMono =
                getController().handleQueryUserOnlineStatusesRequest()
                        .handle(clientRequest);
        assertResultIsOk(resultMono,
                result -> assertThat(result.dataForRequester()
                        .getUserOnlineStatuses()
                        .getStatuses(0)
                        .getUserStatus()).isEqualTo(UserStatus.OFFLINE));
    }

}
