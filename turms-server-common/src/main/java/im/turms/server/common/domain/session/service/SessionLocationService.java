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

import java.util.Date;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.location.LocationProperties;
import im.turms.server.common.infra.property.env.common.location.NearbyUserRequestProperties;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.redis.RedisEntryId;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;

/**
 * @author James Chen
 */
@Service
public class SessionLocationService {

    private final TurmsRedisClientManager locationRedisClientManager;

    @Getter
    private final boolean locationEnabled;
    @Getter
    private final boolean treatUserIdAndDeviceTypeAsUniqueUser;
    private short defaultMaxNearbyUserCount;
    private int defaultMaxDistanceMeters;
    private int maxNearbyUserCount;
    private int maxDistanceMeters;

    public SessionLocationService(
            TurmsPropertiesManager propertiesManager,
            TurmsRedisClientManager locationRedisClientManager) {
        this.locationRedisClientManager = locationRedisClientManager;
        LocationProperties locationProperties = propertiesManager.getLocalProperties()
                .getLocation();
        locationEnabled = locationProperties.isEnabled();
        treatUserIdAndDeviceTypeAsUniqueUser =
                locationProperties.isTreatUserIdAndDeviceTypeAsUniqueUser();

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        NearbyUserRequestProperties requestProperties = properties.getLocation()
                .getNearbyUserRequest();
        short localDefaultMaxNearbyUserCount = requestProperties.getDefaultMaxNearbyUserCount();
        int localDefaultMaxDistanceMeters = requestProperties.getDefaultMaxDistanceMeters();
        short localMaxNearbyUserCount = requestProperties.getMaxNearbyUserCount();
        int localMaxDistanceMeters = requestProperties.getMaxDistanceMeters();
        defaultMaxNearbyUserCount = localDefaultMaxNearbyUserCount > 0
                ? localDefaultMaxNearbyUserCount
                : Short.MAX_VALUE;
        defaultMaxDistanceMeters = localDefaultMaxDistanceMeters > 0
                ? localDefaultMaxDistanceMeters
                : Integer.MAX_VALUE;
        maxNearbyUserCount = localMaxNearbyUserCount > 0
                ? localMaxNearbyUserCount
                : Integer.MAX_VALUE;
        maxDistanceMeters = localMaxDistanceMeters > 0
                ? localMaxDistanceMeters
                : Integer.MAX_VALUE;
    }

    /**
     * Usually used when a user just go online.
     */
    public Mono<Void> upsertUserLocation(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @NotNull Date timestamp,
            float longitude,
            float latitude) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.notNull(timestamp, "timestamp");
            Validator.inRange(longitude,
                    "longitude",
                    Location.LONGITUDE_MIN,
                    Location.LONGITUDE_MAX);
            Validator.inRange(latitude, "latitude", Location.LATITUDE_MIN, Location.LATITUDE_MAX);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException
                    .get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager
                .geoadd(userId, RedisEntryId.LOCATION_BUFFER, longitude, latitude, member)
                .then();
    }

    public Mono<Void> removeUserLocation(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException
                    .get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
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
            @Nullable Short maxCount,
            @Nullable Integer maxDistance,
            boolean withCoordinates,
            boolean withDistance) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            Validator.inRange(maxCount, "maxCount", 1, maxNearbyUserCount);
            Validator.inRange(maxDistance, "maxDistance", 1, maxDistanceMeters);
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (!locationEnabled) {
            return Flux.error(ResponseException
                    .get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        if (maxCount == null) {
            maxCount = (short) Math.min(defaultMaxNearbyUserCount, maxNearbyUserCount);
        }
        if (maxDistance == null) {
            maxDistance = Math.min(defaultMaxDistanceMeters, maxDistanceMeters);
        }
        Object currentUserSessionId = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        GeoArgs geoArgs = new GeoArgs().withCount(maxCount);
        if (withCoordinates) {
            geoArgs.withCoordinates();
        }
        if (withDistance) {
            geoArgs.withDistance();
        }
        Flux<GeoWithin<Object>> georadiusbymember =
                locationRedisClientManager.georadiusbymember(userId,
                        RedisEntryId.LOCATION_BUFFER,
                        currentUserSessionId,
                        maxDistance,
                        geoArgs);
        return georadiusbymember.filter(geo -> !geo.getMember()
                .equals(currentUserSessionId));
    }

    public Mono<GeoCoordinates> getUserLocation(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(ResponseException
                    .get(ResponseStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geopos(userId, RedisEntryId.LOCATION_BUFFER, member)
                .singleOrEmpty();
    }

}