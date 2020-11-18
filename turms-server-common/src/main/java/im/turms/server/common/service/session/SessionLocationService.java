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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.server.common.bo.log.UserLocationLog;
import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.log4j.UserActivityLogging;
import im.turms.server.common.plugin.base.ITurmsPluginManager;
import im.turms.server.common.plugin.extension.UserLocationLogHandler;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.LocationProperties;
import im.turms.server.common.redis.RedisEntryId;
import im.turms.server.common.redis.RedisSerializationContextPool;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.ReactiveGeoOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Service
public class SessionLocationService {

    private final Node node;
    private final ITurmsPluginManager turmsPluginManager;
    @Getter
    private final boolean locationEnabled;
    @Getter
    private final boolean treatUserIdAndDeviceTypeAsUniqueUser;
    private final ReactiveGeoOperations<String, Long> geoByUserIdOperations;
    private final ReactiveGeoOperations<String, UserSessionId> geoByUserSessionIdOperations;

    public SessionLocationService(
            Node node,
            ITurmsPluginManager turmsPluginManager,
            TurmsPropertiesManager turmsPropertiesManager,
            @Qualifier("locationRedisTemplate") ReactiveRedisTemplate<String, UserSessionId> redisTemplate) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        LocationProperties locationProperties = turmsPropertiesManager.getLocalProperties().getLocation();
        locationEnabled = locationProperties.isEnabled();
        treatUserIdAndDeviceTypeAsUniqueUser = locationProperties.isTreatUserIdAndDeviceTypeAsUniqueUser();
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            geoByUserSessionIdOperations = redisTemplate.opsForGeo(RedisSerializationContextPool.GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT);
            geoByUserIdOperations = null;
        } else {
            geoByUserIdOperations = redisTemplate.opsForGeo(RedisSerializationContextPool.GEO_USER_ID_SERIALIZATION_CONTEXT);
            geoByUserSessionIdOperations = null;
        }
    }

    /**
     * Usually used when a user just go online.
     */
    public Mono<Boolean> upsertUserLocation(@NotNull Long userId,
                                            @NotNull @ValidDeviceType DeviceType deviceType,
                                            @NotNull Point position,
                                            @NotNull Date timestamp) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.notNull(position, "position");
            AssertUtil.notNull(timestamp, "timestamp");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!locationEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            UserSessionId userSessionId = new UserSessionId(userId, deviceType);
            return geoByUserSessionIdOperations.add(RedisEntryId.LOCATION, position, userSessionId)
                    .flatMap(o -> o > 0
                            ? Mono.just(true)
                            // Redis doesn't support atomic upsert operation for GeoHash
                            // so we do it in this way as a workaround
                            : geoByUserSessionIdOperations.remove(RedisEntryId.LOCATION, userSessionId)
                            .then(geoByUserSessionIdOperations.add(RedisEntryId.LOCATION, position, userSessionId))
                            .map(number -> number > 0))
                    .doOnSuccess(o -> tryLogLocation(userId, deviceType, position, timestamp));
        } else {
            return geoByUserIdOperations.add(RedisEntryId.LOCATION, position, userId)
                    .flatMap(o -> o > 0
                            ? Mono.just(true)
                            // Redis doesn't support atomic upsert operation for GeoHash
                            // so we do it in this way as a workaround
                            : geoByUserIdOperations.remove(RedisEntryId.LOCATION, userId)
                            .then(geoByUserIdOperations.add(RedisEntryId.LOCATION, position, userId))
                            .map(number -> number > 0))
                    .doOnSuccess(o -> tryLogLocation(userId, deviceType, position, timestamp));
        }
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
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
        Mono<Long> mono = treatUserIdAndDeviceTypeAsUniqueUser
                ? geoByUserSessionIdOperations.remove(RedisEntryId.LOCATION, new UserSessionId(userId, deviceType))
                : geoByUserIdOperations.remove(RedisEntryId.LOCATION, userId);
        return mono.then();
    }

    public Flux<Long> queryNearestUserIds(
            @NotNull Long userId,
            @Nullable DeviceType deviceType,
            @Nullable Short maxPeopleNumber,
            @Nullable Double maxDistance) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        if (!locationEnabled) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            return deviceType != null
                    ? queryNearestUserSessionIds(userId, deviceType, maxPeopleNumber, maxDistance)
                    .map(UserSessionId::getUserId)
                    : Flux.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        } else {
            LocationProperties location = node.getSharedProperties().getLocation();
            if (maxPeopleNumber == null) {
                maxPeopleNumber = location.getDefaultMaxAvailableUsersNearbyNumberPerQuery();
            }
            short maxAvailableUsersNearbyNumberLimitPerQuery = location.getMaxAvailableUsersNearbyNumberLimitPerQuery();
            if (maxPeopleNumber > maxAvailableUsersNearbyNumberLimitPerQuery) {
                maxPeopleNumber = maxAvailableUsersNearbyNumberLimitPerQuery;
            }
            if (maxDistance == null) {
                maxDistance = location.getDefaultMaxDistancePerQuery();
            }
            double maxDistanceLimitPerQuery = location.getMaxDistanceLimitPerQuery();
            if (maxDistance > maxDistanceLimitPerQuery) {
                maxDistance = maxDistanceLimitPerQuery;
            }
            return geoByUserIdOperations.radius(
                    RedisEntryId.LOCATION,
                    userId,
                    new Distance(maxDistance),
                    RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().limit(maxPeopleNumber))
                    .map(geoLocationGeoResult -> geoLocationGeoResult.getContent().getName());
        }
    }

    public Flux<UserSessionId> queryNearestUserSessionIds(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @Nullable Short maxPeopleNumber,
            @Nullable Double maxDistance) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        if (!locationEnabled || geoByUserSessionIdOperations == null) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
        LocationProperties location = node.getSharedProperties().getLocation();
        if (maxPeopleNumber == null) {
            maxPeopleNumber = location.getDefaultMaxAvailableUsersNearbyNumberPerQuery();
        }
        short maxAvailableUsersNearbyNumberLimitPerQuery = location.getMaxAvailableUsersNearbyNumberLimitPerQuery();
        if (maxPeopleNumber > maxAvailableUsersNearbyNumberLimitPerQuery) {
            maxPeopleNumber = maxAvailableUsersNearbyNumberLimitPerQuery;
        }
        if (maxDistance == null) {
            maxDistance = location.getDefaultMaxDistancePerQuery();
        }
        double maxDistanceLimitPerQuery = location.getMaxDistanceLimitPerQuery();
        if (maxDistance > maxDistanceLimitPerQuery) {
            maxDistance = maxDistanceLimitPerQuery;
        }
        return geoByUserSessionIdOperations.radius(
                RedisEntryId.LOCATION,
                new UserSessionId(userId, deviceType),
                new Distance(maxDistance),
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().limit(maxPeopleNumber))
                .map(geoLocationGeoResult -> geoLocationGeoResult.getContent().getName());
    }

    public Mono<Point> getUserLocation(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (locationEnabled) {
            return treatUserIdAndDeviceTypeAsUniqueUser
                    ? geoByUserSessionIdOperations.position(RedisEntryId.LOCATION, new UserSessionId(userId, deviceType))
                    : geoByUserIdOperations.position(RedisEntryId.LOCATION, userId);
        } else {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
    }

    private void tryLogLocation(Long userId, DeviceType deviceType, Point coordinates, Date timestamp) {
        boolean logUserLocation = node.getSharedProperties().getService().getLog().isLogUserLocation();
        List<UserLocationLogHandler> handlerList = turmsPluginManager.getUserLocationLogHandlerList();
        boolean triggerHandlers = turmsPluginManager.isEnabled() && !handlerList.isEmpty();
        if (logUserLocation || triggerHandlers) {
            UserLocationLog userLocationLog = new UserLocationLog(userId, deviceType, coordinates, timestamp);
            if (logUserLocation) {
                UserActivityLogging.log(userLocationLog);
            }
            if (triggerHandlers) {
                List<Mono<Void>> monos = new ArrayList<>(handlerList.size());
                for (UserLocationLogHandler handler : handlerList) {
                    monos.add(handler.handleUserLocationLog(userLocationLog));
                }
                Mono.when(monos).subscribe();
            }
        }
    }

}
