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

package im.turms.server.common.service.session;


import im.turms.common.constant.DeviceType;
import im.turms.server.common.bo.location.Coordinates;
import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.location.LocationProperties;
import im.turms.server.common.property.env.common.location.UsersNearbyRequestProperties;
import im.turms.server.common.redis.RedisEntryId;
import im.turms.server.common.redis.TurmsRedisClientManager;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author James Chen
 */
@Service
public class SessionLocationService {

    private final Node node;
    @Getter
    private final boolean locationEnabled;
    @Getter
    private final boolean treatUserIdAndDeviceTypeAsUniqueUser;
    private final TurmsRedisClientManager locationRedisClientManager;

    public SessionLocationService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            TurmsRedisClientManager locationRedisClientManager) {
        this.node = node;
        this.locationRedisClientManager = locationRedisClientManager;
        LocationProperties locationProperties = turmsPropertiesManager.getLocalProperties().getLocation();
        locationEnabled = locationProperties.isEnabled();
        treatUserIdAndDeviceTypeAsUniqueUser = locationProperties.isTreatUserIdAndDeviceTypeAsUniqueUser();
    }

    /**
     * Usually used when a user just go online.
     */
    public Mono<Void> upsertUserLocation(@NotNull Long userId,
                                         @NotNull @ValidDeviceType DeviceType deviceType,
                                         @NotNull Coordinates coordinates,
                                         @NotNull Date timestamp) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.notNull(coordinates, "coordinates");
            AssertUtil.inRange(coordinates.longitude(), "longitude", Coordinates.LONGITUDE_MIN, Coordinates.LONGITUDE_MAX);
            AssertUtil.inRange(coordinates.latitude(), "latitude", Coordinates.LATITUDE_MIN, Coordinates.LATITUDE_MAX);
            AssertUtil.notNull(timestamp, "timestamp");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geoadd(userId, RedisEntryId.LOCATION_BUFFER, coordinates, member)
                .then();
    }

    public Mono<Void> removeUserLocation(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.georem(userId, RedisEntryId.LOCATION_BUFFER, member)
                .then();
    }

    /**
     * @return GeoWithin<Long> or GeoWithin<UserSessionId>
     */
    public Flux<GeoWithin<Object>> queryNearbyUsers(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @Nullable Short maxNumber,
            @Nullable Integer maxDistance,
            boolean withCoordinates,
            boolean withDistance) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        if (!locationEnabled) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        UsersNearbyRequestProperties usersNearbyRequest = node.getSharedProperties().getLocation().getUsersNearbyRequest();
        if (maxNumber == null) {
            maxNumber = usersNearbyRequest.getDefaultMaxAvailableNearbyUsersNumber();
        }
        short maxAvailableUsersNearbyNumberLimitPerQuery = usersNearbyRequest.getMaxAvailableUsersNearbyNumberLimit();
        if (maxNumber > maxAvailableUsersNearbyNumberLimitPerQuery) {
            String reason = "The maximum available users nearby number number is " + maxAvailableUsersNearbyNumberLimitPerQuery;
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
        }
        if (maxDistance == null) {
            maxDistance = usersNearbyRequest.getDefaultMaxDistanceMeters();
        }
        double maxDistanceMeters = usersNearbyRequest.getMaxDistanceMeters();
        if (maxDistance > maxDistanceMeters) {
            String reason = "The maximum allowed distance in meters is " + maxDistanceMeters;
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
        }
        Object currentUserSessionId = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        GeoArgs geoArgs = GeoArgs.Builder.count(maxNumber);
        if (withCoordinates) {
            geoArgs.withCoordinates();
        }
        if (withDistance) {
            geoArgs.withDistance();
        }
        Flux<GeoWithin<Object>> georadiusbymember = locationRedisClientManager.georadiusbymember(userId,
                RedisEntryId.LOCATION_BUFFER,
                currentUserSessionId,
                maxDistance,
                geoArgs);
        return georadiusbymember
                .filter(geo -> !geo.getMember().equals(currentUserSessionId));
    }

    public Mono<GeoCoordinates> getUserLocation(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geopos(userId, RedisEntryId.LOCATION_BUFFER, member)
                .singleOrEmpty();
    }

}