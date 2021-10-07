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

package im.turms.gateway.pojo.bo.session;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.util.RandomUtil;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.server.common.throttle.TokenBucket;
import im.turms.server.common.throttle.TokenBucketContext;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.tracing.TracingContext;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/**
 * @author James Chen
 */
@Data
@Log4j2
public final class UserSession {

    private final int id = RandomUtil.nextPositiveInt();

    private final int version;

    private final Long userId;
    private final DeviceType deviceType;
    private final Date loginDate;
    @Nullable
    private Point loginLocation;
    /**
     * 1. Use {@link ByteBuf} instead of {@link TurmsNotification}
     * so that turms-gateway can:
     * a. Transfer data through zero copy without parsing (if SSL is disabled)
     * b. Send the same ByteBuf without duplicating to multiple clients
     * c. Decouple business logic from turms servers
     * d. {@link ByteBuf} isn't {@link TurmsNotification} if the connection is UDP (will be supported in the future)
     * <p>
     * 2. The ByteBuf (TurmsNotification) comes from turms servers in most scenarios.
     * 3. We never emit an error in the sink
     */
    @Getter(AccessLevel.PRIVATE)
    private BiConsumer<ByteBuf, TracingContext> notificationConsumer;
    private volatile long lastHeartbeatRequestTimestampMillis;
    private volatile long lastRequestTimestampMillis;
    // No need to add volatile because it can only be accessed by one thread
    // (the thread "heartbeat-update" in HeartbeatManager)
    private long lastHeartbeatUpdateTimestampMillis;

    /**
     * Rate limiting
     */
    private final TokenBucket requestTokenBucket;

    /**
     * Note that it's acceptable that the session is still open even if the connection is closed
     * because the client can send heartbeats over UDP to keep the session open
     *
     * @implNote For better performance, it's acceptable for our scenarios to not update isSessionOpen atomically.
     */
    private volatile boolean isSessionOpen = true;
    /**
     * Used to avoid logging DeleteSessionRequest twice in a session
     */
    private AtomicBoolean isDeleteSessionLockAcquired = new AtomicBoolean(false);
    @Nullable
    private NetConnection connection;

    public UserSession(int version,
                       Long userId,
                       DeviceType loggingInDeviceType,
                       @Nullable Point loginLocation,
                       TokenBucketContext tokenBucketContext) {
        Date now = new Date();
        this.version = version;
        this.userId = userId;
        this.deviceType = loggingInDeviceType;
        this.loginDate = now;
        this.loginLocation = loginLocation;
        this.lastHeartbeatRequestTimestampMillis = now.getTime();
        requestTokenBucket = new TokenBucket(tokenBucketContext);
    }

    /**
     * A session cannot reopen once closed, unlike the connection which is allowed to close and reconnect.
     */
    public void close(@NotNull CloseReason closeReason) {
        if (isSessionOpen) {
            isSessionOpen = false;
            // Note that it is acceptable to complete/close the connection multiple times
            // so that it's unnecessary to update isSessionOpen atomically
            if (connection != null) {
                connection.close(closeReason);
            } else {
                log.warn("The connection is missing for the user session: {}", this);
            }
        }
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

    public byte[] getIp() {
        if (connection == null) {
            return null;
        }
        return connection.getAddress().getAddress().getAddress();
    }

    public void sendNotification(ByteBuf byteBuf) {
        // Note that we do not check if the consumer is null
        notificationConsumer.accept(byteBuf, TracingContext.NOOP);
    }

    public void sendNotification(ByteBuf byteBuf, TracingContext tracingContext) {
        // Note that we do not check if the consumer is null
        notificationConsumer.accept(byteBuf, tracingContext);
    }

    public boolean tryAcquireToken(long time) {
        return requestTokenBucket.tryAcquire(time);
    }

    public boolean acquireDeleteSessionRequestLoggingLock() {
        return isDeleteSessionLockAcquired.compareAndSet(false, true);
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", version=" + version +
                ", userId=" + userId +
                ", deviceType=" + deviceType +
                ", loginDate=" + loginDate +
                ", loginLocation=" + loginLocation +
                ", isSessionOpen=" + isSessionOpen +
                ", connection=" + connection +
                '}';
    }

}