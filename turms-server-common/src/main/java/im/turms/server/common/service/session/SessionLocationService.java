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
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
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
import im.turms.server.common.redis.sharding.ShardingAlgorithm;
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
import java.util.stream.Collectors;

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
    private final ShardingAlgorithm shardingAlgorithmForLocation;
    private final List<ReactiveGeoOperations<String, Long>> geoByUserIdOperationsList;
    private final List<ReactiveGeoOperations<String, UserSessionId>> geoByUserSessionIdOperationsList;

    public SessionLocationService(
            Node node,
            ITurmsPluginManager turmsPluginManager,
            TurmsPropertiesManager turmsPropertiesManager,
            ShardingAlgorithm shardingAlgorithmForLocation,
            @Qualifier("locationRedisTemplates") List<ReactiveRedisTemplate<String, UserSessionId>> locationRedisTemplates) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        LocationProperties locationProperties = turmsPropertiesManager.getLocalProperties().getLocation();
        locationEnabled = locationProperties.isEnabled();
        treatUserIdAndDeviceTypeAsUniqueUser = locationProperties.isTreatUserIdAndDeviceTypeAsUniqueUser();
        this.shardingAlgorithmForLocation = shardingAlgorithmForLocation;
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            geoByUserSessionIdOperationsList = locationRedisTemplates
                    .stream()
                    .map(template -> template.opsForGeo(RedisSerializationContextPool.GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT))
                    .collect(Collectors.toList());
            geoByUserIdOperationsList = null;
        } else {
            geoByUserIdOperationsList = locationRedisTemplates
                    .stream()
                    .map(template -> template.opsForGeo(RedisSerializationContextPool.GEO_USER_ID_SERIALIZATION_CONTEXT))
                    .collect(Collectors.toList());
            geoByUserSessionIdOperationsList = null;
        }
    }

    /**
     * Usually used when a user just go online.
     */
    public Mono<Void> upsertUserLocation(@NotNull Long userId,
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
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        Mono<Long> upsertMono;
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            UserSessionId userSessionId = new UserSessionId(userId, deviceType);
            upsertMono = getGeoByUserSessionIdOperations(userId).add(RedisEntryId.LOCATION, position, userSessionId);
        } else {
            upsertMono = getGeoByUserIdOperations(userId).add(RedisEntryId.LOCATION, position, userId);
        }
        return upsertMono
                .doOnSuccess(o -> tryLogLocation(userId, deviceType, position, timestamp))
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
        Mono<Long> mono = treatUserIdAndDeviceTypeAsUniqueUser
                ? getGeoByUserSessionIdOperations(userId).remove(RedisEntryId.LOCATION, new UserSessionId(userId, deviceType))
                : getGeoByUserIdOperations(userId).remove(RedisEntryId.LOCATION, userId);
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
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        if (treatUserIdAndDeviceTypeAsUniqueUser) {
            return deviceType != null
                    ? queryNearestUserSessionIds(userId, deviceType, maxPeopleNumber, maxDistance)
                    .map(UserSessionId::getUserId)
                    : Flux.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
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
            return getGeoByUserIdOperations(userId).radius(
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
        if (!locationEnabled) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
        }
        if (geoByUserSessionIdOperationsList == null) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED));
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
        return getGeoByUserSessionIdOperations(userId).radius(
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
                    ? getGeoByUserSessionIdOperations(userId).position(RedisEntryId.LOCATION, new UserSessionId(userId, deviceType))
                    : getGeoByUserIdOperations(userId).position(RedisEntryId.LOCATION, userId);
        } else {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_LOCATION_RELATED_FEATURES_ARE_DISABLED));
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

    private ReactiveGeoOperations<String, UserSessionId> getGeoByUserSessionIdOperations(long userId) {
        return geoByUserSessionIdOperationsList.get(shardingAlgorithmForLocation.doSharding(userId, geoByUserSessionIdOperationsList.size()));
    }

    private ReactiveGeoOperations<String, Long> getGeoByUserIdOperations(long userId) {
        return geoByUserIdOperationsList.get(shardingAlgorithmForLocation.doSharding(userId, geoByUserIdOperationsList.size()));
    }

}