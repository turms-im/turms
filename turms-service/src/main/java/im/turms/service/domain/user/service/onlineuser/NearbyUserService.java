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

package im.turms.service.domain.user.service.onlineuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.location.bo.NearbyUser;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.Validator;
import im.turms.service.domain.user.service.UserService;

/**
 * @author James Chen
 */
@Service
public class NearbyUserService {

    private final UserService userService;
    private final SessionLocationService sessionLocationService;

    public NearbyUserService(
            UserService userService,
            SessionLocationService sessionLocationService) {
        this.sessionLocationService = sessionLocationService;
        this.userService = userService;
    }

    public Mono<List<NearbyUser>> queryNearbyUsers(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Float longitude,
            @Nullable Float latitude,
            @Nullable Short maxCount,
            @Nullable Integer maxDistance,
            boolean withCoordinates,
            boolean withDistance,
            boolean withUserInfo) {
        if (!Validator.areBothNullOrNotNull(longitude, latitude)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "longitude and latitude must be both null or both not null"));
        }
        Mono<Void> upsertLocation = longitude == null
                ? Mono.empty()
                : sessionLocationService
                        .upsertUserLocation(userId, deviceType, new Date(), longitude, latitude);
        Flux<GeoWithin<Object>> nearbyUserFlux = sessionLocationService.queryNearbyUsers(userId,
                deviceType,
                maxCount,
                maxDistance,
                withCoordinates,
                withDistance);
        Mono<List<NearbyUser>> resultMono;
        if (withUserInfo) {
            resultMono = nearbyUserFlux.collectMap(geo -> {
                if (geo.getMember() instanceof UserSessionId sessionId) {
                    return sessionId.userId();
                }
                return (Long) geo.getMember();
            }, geo -> geo)
                    .flatMap(userIdToGeo -> {
                        if (userIdToGeo.isEmpty()) {
                            return PublisherPool.emptyList();
                        }
                        return userService.queryUsersProfile(userIdToGeo.keySet(), false)
                                .collect(CollectorUtil.toList(userIdToGeo.size()))
                                .map(users -> {
                                    List<NearbyUser> nearbyUsers = new ArrayList<>(users.size());
                                    for (User user : users) {
                                        GeoWithin<Object> geo = userIdToGeo.get(user.getId());
                                        if (geo == null) {
                                            continue;
                                        }
                                        nearbyUsers.add(getNearbyUser(geo, user));
                                    }
                                    return nearbyUsers;
                                });
                    });
        } else {
            resultMono = nearbyUserFlux.map(geo -> getNearbyUser(geo, null))
                    .collect(CollectorUtil.toChunkedList());
        }
        return upsertLocation.then(resultMono);
    }

    private NearbyUser getNearbyUser(GeoWithin<Object> geo, @Nullable User user) {
        Object id = geo.getMember();
        GeoCoordinates coordinates = geo.getCoordinates();
        Double distance = geo.getDistance();
        Long userId;
        DeviceType deviceType;
        Float longitude;
        Float latitude;
        if (id instanceof UserSessionId sessionId) {
            userId = sessionId.userId();
            deviceType = sessionId.deviceType();
        } else {
            userId = (Long) id;
            deviceType = null;
        }
        if (coordinates == null) {
            longitude = null;
            latitude = null;
        } else {
            longitude = coordinates.getX()
                    .floatValue();
            latitude = coordinates.getY()
                    .floatValue();
        }
        return new NearbyUser(
                userId,
                deviceType,
                longitude,
                latitude,
                distance == null
                        ? null
                        : distance.intValue(),
                user);
    }

}
