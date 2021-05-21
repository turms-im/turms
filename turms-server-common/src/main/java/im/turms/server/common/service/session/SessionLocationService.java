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
import im.turms.server.common.bo.log.UserLocationLog;
import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.log4j.UserActivityLogging;
import im.turms.server.common.plugin.base.AbstractTurmsPluginManager;
import im.turms.server.common.plugin.extension.UserLocationLogHandler;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.LocationProperties;
import im.turms.server.common.redis.RedisEntryId;
import im.turms.server.common.redis.TurmsRedisClientManager;
import im.turms.server.common.redis.codec.context.RedisCodecContext;
import im.turms.server.common.redis.codec.context.RedisCodecContextPool;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import lombok.Getter;
import org.springframework.data.geo.Point;
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
    private final AbstractTurmsPluginManager turmsPluginManager;
    @Getter
    private final boolean locationEnabled;
    @Getter
    private final boolean treatUserIdAndDeviceTypeAsUniqueUser;
    private final TurmsRedisClientManager locationRedisClientManager;

    public SessionLocationService(
            Node node,
            AbstractTurmsPluginManager turmsPluginManager,
            TurmsPropertiesManager turmsPropertiesManager,
            TurmsRedisClientManager locationRedisClientManager) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        this.locationRedisClientManager = locationRedisClientManager;
        LocationProperties locationProperties = turmsPropertiesManager.getLocalProperties().getLocation();
        locationEnabled = locationProperties.isEnabled();
        treatUserIdAndDeviceTypeAsUniqueUser = locationProperties.isTreatUserIdAndDeviceTypeAsUniqueUser();
        RedisCodecContext serializationContext = treatUserIdAndDeviceTypeAsUniqueUser
                ? RedisCodecContextPool.GEO_USER_SESSION_ID_CODEC_CONTEXT
                : RedisCodecContextPool.GEO_USER_ID_CODEC_CONTEXT;
        locationRedisClientManager.setSerializationContext(serializationContext);
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
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.geoadd(userId, RedisEntryId.LOCATION_BUFFER, position, member)
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
        Object member = treatUserIdAndDeviceTypeAsUniqueUser
                ? new UserSessionId(userId, deviceType)
                : userId;
        return locationRedisClientManager.georem(userId, RedisEntryId.LOCATION_BUFFER, member)
                .then();
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
                String reason = "The maximum available users nearby number number is " + maxAvailableUsersNearbyNumberLimitPerQuery;
                return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
            }
            if (maxDistance == null) {
                maxDistance = location.getDefaultMaxDistancePerQuery();
            }
            double maxDistanceMeters = location.getMaxDistanceMeters();
            if (maxDistance > maxDistanceMeters) {
                String reason = "The maximum allowed distance in meters is " + maxDistanceMeters;
                return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
            }
            return locationRedisClientManager.georadiusbymember(userId,
                    RedisEntryId.LOCATION_BUFFER,
                    userId,
                    maxDistance,
                    GeoArgs.Builder.count(maxPeopleNumber))
                    .map(geo -> ((UserSessionId) geo.getMember()).getUserId())
                    .filter(uid -> !uid.equals(userId));
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
        if (!treatUserIdAndDeviceTypeAsUniqueUser) {
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED));
        }
        LocationProperties location = node.getSharedProperties().getLocation();
        if (maxPeopleNumber == null) {
            maxPeopleNumber = location.getDefaultMaxAvailableUsersNearbyNumberPerQuery();
        }
        short maxAvailableUsersNearbyNumberLimitPerQuery = location.getMaxAvailableUsersNearbyNumberLimitPerQuery();
        if (maxPeopleNumber > maxAvailableUsersNearbyNumberLimitPerQuery) {
            String reason = "The maximum available users nearby number number is " + maxAvailableUsersNearbyNumberLimitPerQuery;
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
        }
        if (maxDistance == null) {
            maxDistance = location.getDefaultMaxDistancePerQuery();
        }
        double maxDistanceMeters = location.getMaxDistanceMeters();
        if (maxDistance > maxDistanceMeters) {
            String reason = "The maximum allowed distance in meters is " + maxDistanceMeters;
            return Flux.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, reason));
        }
        UserSessionId currentUserSessionId = new UserSessionId(userId, deviceType);
        Flux<UserSessionId> georadiusbymember = locationRedisClientManager.georadiusbymember(userId,
                RedisEntryId.LOCATION_BUFFER,
                currentUserSessionId,
                maxDistance,
                GeoArgs.Builder.count(maxPeopleNumber))
                .map(geo -> (UserSessionId) geo.getMember());
        return georadiusbymember
                .filter(geo -> !geo.equals(currentUserSessionId));
    }

    public Mono<GeoCoordinates> getUserLocation(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (locationEnabled) {
            Object member = treatUserIdAndDeviceTypeAsUniqueUser
                    ? new UserSessionId(userId, deviceType)
                    : userId;
            return locationRedisClientManager.geopos(userId, RedisEntryId.LOCATION_BUFFER, member)
                    .singleOrEmpty();
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

}