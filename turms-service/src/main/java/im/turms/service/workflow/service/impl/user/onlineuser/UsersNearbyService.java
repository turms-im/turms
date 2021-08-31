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

package im.turms.service.workflow.service.impl.user.onlineuser;

import im.turms.common.constant.DeviceType;
import im.turms.server.common.bo.location.NearbyUser;
import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.service.workflow.service.impl.user.UserService;
import io.lettuce.core.GeoWithin;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Service
public class UsersNearbyService {

    private final UserService userService;
    private final SessionLocationService sessionLocationService;

    public UsersNearbyService(
            UserService userService,
            SessionLocationService sessionLocationService) {
        this.sessionLocationService = sessionLocationService;
        this.userService = userService;
    }

    public Mono<Collection<NearbyUser>> queryNearbyUsers(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Point coordinates,
            @Nullable Short maxNumber,
            @Nullable Integer maxDistance,
            boolean withCoordinates,
            boolean withDistance,
            boolean withUserInfo) {
        Mono<Void> upsertMono = coordinates == null
                ? Mono.empty()
                : sessionLocationService.upsertUserLocation(
                userId,
                deviceType,
                coordinates,
                new Date());
        Flux<GeoWithin<Object>> nearbyUserFlux = sessionLocationService.queryNearbyUsers(
                userId,
                deviceType,
                maxNumber,
                maxDistance,
                withCoordinates,
                withDistance);
        Mono<Collection<NearbyUser>> resultMono;
        if (withUserInfo) {
            resultMono = nearbyUserFlux
                    .collectMap(geo -> {
                        if (geo.getMember() instanceof UserSessionId sessionId) {
                            return sessionId.userId();
                        }
                        return (Long) geo.getMember();
                    }, geo -> geo)
                    .flatMap(geoMap -> {
                        if (geoMap.isEmpty()) {
                            return Mono.empty();
                        }
                        return userService.queryUsersProfiles(geoMap.keySet(), false)
                                .collectList()
                                .map(users -> {
                                    List<NearbyUser> nearbyUsers = new ArrayList<>(users.size());
                                    for (User user : users) {
                                        GeoWithin<Object> geo = geoMap.get(user.getId());
                                        if (geo == null) {
                                            continue;
                                        }
                                        Double distance = geo.getDistance();
                                        Integer dis = distance == null ? null : distance.intValue();
                                        NearbyUser nearbyUser = new NearbyUser(getUserSessionId(geo.getMember()),
                                                geo.getCoordinates(), dis, user);
                                        nearbyUsers.add(nearbyUser);
                                    }
                                    return nearbyUsers;
                                });
                    });
        } else {
            resultMono = nearbyUserFlux
                    .map(geo -> new NearbyUser(getUserSessionId(geo.getMember()),
                            geo.getCoordinates(),
                            geo.getDistance().intValue(),
                            null))
                    .collectList()
                    .map(users -> users);
        }
        return upsertMono.then(resultMono);
    }

    private UserSessionId getUserSessionId(Object value) {
        return value instanceof UserSessionId sessionId
                ? sessionId
                : new UserSessionId((Long) value, null);
    }

}
