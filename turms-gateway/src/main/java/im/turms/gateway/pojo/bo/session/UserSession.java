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
import im.turms.server.common.dto.CloseReason;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author James Chen
 */
@Data
@Log4j2
public final class UserSession {

    private final int id = RandomUtil.nextPositiveInt();

    private final Long userId;
    private final DeviceType deviceType;
    private final Date loginDate;
    @Nullable
    private Point loginLocation;
    /**
     * 1. Use {@link ByteBuf} instead of {@link TurmsNotification}
     * so that turms-gateway can:
     * a. Transfer data through zero copy without parsing (if SSL is disabled)
     * b. Decouple business logic from turms servers
     * <p>
     * 2. Although we can forward the same WebSocketMessage when there are different recipients connecting to the local turms-gateway,
     * we still use ByteBuf for code clarity and extensibility (we will integrate UDP in the future)
     * and ByteBuf won't be copied in the scenario so it's acceptable.
     * Note that the ByteBuf (TurmsNotification) comes from turms servers in most scenarios.
     * 3. We never emit an error in the sink
     */
    @Getter(AccessLevel.PRIVATE)
    private final Sinks.Many<ByteBuf> notificationSink = Sinks.many().unicast()
            .onBackpressureBuffer(Queues.<ByteBuf>unbounded(64).get());
    @Nullable
    private Long logId;
    private volatile long lastHeartbeatRequestTimestampMillis;
    private volatile long lastRequestTimestampMillis;
    // No need to add volatile because it can only be accessed by one thread
    // (the thread "heartbeat-update" in HeartbeatManager)
    private long lastHeartbeatUpdateTimestampMillis;

    // Rate limiting
    /**
     * We don't use AtomicInteger for better performance,
     * and it's acceptable that the count isn't accurate when ++
     */
    private volatile int currentProcessedRequestCount;
    private long firstRequestTimestampMillis;

    /**
     * Note that it's acceptable that the session is still open even if the connection is closed
     * because the client can send heartbeats over UDP to keep the session open
     *
     * @implNote For better performance, it's acceptable for our scenarios to not update isSessionOpen atomically.
     */
    private volatile boolean isSessionOpen = true;
    @Nullable
    private NetConnection connection;

    public UserSession(Long userId,
                       DeviceType loggingInDeviceType,
                       @Nullable Point loginLocation,
                       @Nullable Long logId) {
        Date now = new Date();
        this.userId = userId;
        this.deviceType = loggingInDeviceType;
        this.loginDate = now;
        this.loginLocation = loginLocation;
        this.logId = logId;
        this.lastHeartbeatRequestTimestampMillis = now.getTime();
    }

    /**
     * A session cannot reopen once closed, unlike the connection which is allowed to close and reconnect.
     */
    public void close(@NotNull CloseReason closeReason) {
        if (isSessionOpen) {
            isSessionOpen = false;
            // Note that it acceptable to complete/close the following objects multiple times
            // so that it's unnecessary to update isSessionOpen atomically
            notificationSink.tryEmitComplete();
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

    public Flux<ByteBuf> getNotificationFlux() {
        return notificationSink.asFlux();
    }

    public Sinks.EmitResult tryEmitNextNotification(ByteBuf byteBuf) {
        Sinks.EmitResult result = this.notificationSink.tryEmitNext(byteBuf);
        if (result != Sinks.EmitResult.OK) {
            // Release once because the subscriber of the sink
            // hasn't released it due to the emitting failure.
            byteBuf.release();
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", userId=" + userId +
                ", deviceType=" + deviceType +
                ", loginDate=" + loginDate +
                ", loginLocation=" + loginLocation +
                ", logId=" + logId +
                ", isSessionOpen=" + isSessionOpen +
                '}';
    }

}