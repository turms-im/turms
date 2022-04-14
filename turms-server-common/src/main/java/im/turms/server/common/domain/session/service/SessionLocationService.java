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

package im.turms.server.common.domain.session.service;


import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.location.LocationProperties;
import im.turms.server.common.infra.property.env.common.location.UsersNearbyRequestProperties;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.redis.RedisEntryId;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
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
                                         @NotNull Date timestamp,
                                         float longitude,
                                         float latitude) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.notNull(timestamp, "timestamp");
            Validator.inRange(longitude, "longitude", Location.LONGITUDE_MIN, Location.LONGITUDE_MAX);
            Validator.inRange(longitude, "latitude", Location.LATITUDE_MIN, Location.LATITUDE_MAX);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException.get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geoadd(userId, RedisEntryId.LOCATION_BUFFER, longitude, latitude, member)
                .then();
    }

    public Mono<Void> removeUserLocation(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException.get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
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
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (!locationEnabled) {
            return Flux.error(ResponseException.get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        UsersNearbyRequestProperties usersNearbyRequest = node.getSharedProperties().getLocation().getUsersNearbyRequest();
        if (maxNumber == null) {
            maxNumber = usersNearbyRequest.getDefaultMaxAvailableNearbyUsersNumber();
        }
        short maxAvailableUsersNearbyNumberLimitPerQuery = usersNearbyRequest.getMaxAvailableUsersNearbyNumberLimit();
        if (maxNumber > maxAvailableUsersNearbyNumberLimitPerQuery) {
            String reason = "The maximum available users nearby number is " + maxAvailableUsersNearbyNumberLimitPerQuery;
            return Flux.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, reason));
        }
        if (maxDistance == null) {
            maxDistance = usersNearbyRequest.getDefaultMaxDistanceMeters();
        }
        double maxDistanceMeters = usersNearbyRequest.getMaxDistanceMeters();
        if (maxDistance > maxDistanceMeters) {
            String reason = "The maximum allowed distance in meters is " + maxDistanceMeters;
            return Flux.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, reason));
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
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException.get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geopos(userId, RedisEntryId.LOCATION_BUFFER, member)
                .singleOrEmpty();
    }

}