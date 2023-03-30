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

package im.turms.gateway.access.client.common;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.BiFunction;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.connection.NetConnection;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.infra.tracing.TracingContext;

/**
 * @author James Chen
 */
@Data
public final class UserSession {

    private static final AtomicIntegerFieldUpdater<UserSession> IS_DELETE_SESSION_LOCK_ACQUIRED_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(UserSession.class, "isDeleteSessionLockAcquired");

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSession.class);

    private final int id = RandomUtil.nextPositiveInt();

    private final int version;
    private final Set<TurmsRequest.KindCase> permissions;

    private final Long userId;
    private final DeviceType deviceType;
    /**
     * Mainly used for push notification and statistics. e.g. "deviceToken" and "registrationId".
     */
    private final Map<String, String> deviceDetails;
    private final Date loginDate;
    @Nullable
    private Location loginLocation;
    /**
     * 1. Use {@link ByteBuf} instead of {@link TurmsNotification} so that turms-gateway can: a.
     * Transfer data through zero copy without parsing (if SSL is disabled) b. Send the same ByteBuf
     * without duplicating to multiple clients c. Decouple business logic from turms-service servers
     * d. {@link ByteBuf} isn't {@link TurmsNotification} if the connection is UDP (will be
     * supported in the future)
     * <p>
     * 2. The ByteBuf (TurmsNotification) comes from turms-services servers in most scenarios.
     */
    @Getter(AccessLevel.PRIVATE)
    private BiFunction<ByteBuf, TracingContext, Mono<Void>> notificationConsumer;
    /**
     * Only record the timestamp of the last heartbeat request, and do NOT record the timestamp of
     * other types of requests
     */
    private volatile long lastHeartbeatRequestTimestampMillis;
    /**
     * Record the timestamp of the last requests except heartbeat requests
     */
    private volatile long lastRequestTimestampMillis;
    // No need to add volatile because it can only be accessed by one thread
    // (the thread "turms-client-heartbeat-refresher" in HeartbeatManager)
    private long lastHeartbeatUpdateTimestampMillis;

    /**
     * Note that it is acceptable that the session is still open even if the connection is closed
     * because the client can send heartbeats over UDP to keep the session open
     *
     * @implNote For better performance, it is acceptable for our scenarios to not update
     *           isSessionOpen atomically.
     */
    private volatile boolean isSessionOpen = true;
    /**
     * Used to avoid logging DeleteSessionRequest twice in a session
     */
    private volatile int isDeleteSessionLockAcquired = 0;
    @Nullable
    private NetConnection connection;
    @Nullable
    private ByteArrayWrapper ip;

    public UserSession(
            int version,
            Set<TurmsRequest.KindCase> permissions,
            Long userId,
            DeviceType loggingInDeviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable Location loginLocation) {
        Date now = new Date();
        this.version = version;
        this.permissions = permissions;
        this.userId = userId;
        this.deviceType = loggingInDeviceType;
        this.deviceDetails = deviceDetails == null
                ? Collections.emptyMap()
                : deviceDetails;
        this.loginLocation = loginLocation;
        this.loginDate = now;
        this.lastHeartbeatRequestTimestampMillis = now.getTime();
        this.lastRequestTimestampMillis = now.getTime();
    }

    public void setConnection(NetConnection connection, ByteArrayWrapper ip) {
        this.connection = connection;
        this.ip = ip;
    }

    /**
     * A session cannot reopen once closed, but the connection can close and reconnect.
     *
     * @return true if the session was online
     */
    public boolean close(@NotNull CloseReason closeReason) {
        if (isSessionOpen) {
            // Note that it is acceptable to complete/close the connection multiple times
            // so that it is unnecessary to update isSessionOpen atomically
            isSessionOpen = false;
            if (connection == null) {
                LOGGER.warn("The connection is missing for the user session: {}", this);
            } else {
                connection.close(closeReason);
            }
            return true;
        }
        return false;
    }

    public boolean isOpen() {
        return isSessionOpen;
    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    public boolean supportsSwitchingToUdp() {
        return deviceType != DeviceType.BROWSER;
    }

    public Mono<Void> sendNotification(ByteBuf byteBuf) {
        // Note that we do not check if the consumer is null
        return notificationConsumer.apply(byteBuf, TracingContext.NOOP);
    }

    public Mono<Void> sendNotification(ByteBuf byteBuf, TracingContext tracingContext) {
        // Note that we do not check if the consumer is null
        return notificationConsumer.apply(byteBuf, tracingContext);
    }

    public boolean acquireDeleteSessionRequestLoggingLock() {
        return IS_DELETE_SESSION_LOCK_ACQUIRED_UPDATER.compareAndSet(this, 0, 1);
    }

    public boolean hasPermission(TurmsRequest.KindCase requestType) {
        return permissions.contains(requestType);
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "id="
                + id
                + ", version="
                + version
                + ", userId="
                + userId
                + ", deviceType="
                + deviceType
                + ", loginDate="
                + loginDate
                + ", loginLocation="
                + loginLocation
                + ", isSessionOpen="
                + isSessionOpen
                + ", connection="
                + connection
                + '}';
    }

}