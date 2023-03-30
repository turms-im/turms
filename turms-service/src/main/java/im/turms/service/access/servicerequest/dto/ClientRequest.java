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

package im.turms.service.access.servicerequest.dto;

import java.util.Arrays;
import java.util.Objects;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.proto.ProtoFormatter;

/**
 * @author James Chen
 */
public final class ClientRequest {
    private final Long userId;
    private final DeviceType deviceType;
    private final byte[] clientIp;
    private final Long requestId;
    private final TurmsRequest.Builder turmsRequestBuilder;
    private TurmsRequest turmsRequest;

    public ClientRequest(
            Long userId,
            DeviceType deviceType,
            byte[] clientIp,
            Long requestId,
            TurmsRequest.Builder turmsRequestBuilder,
            TurmsRequest turmsRequest) {
        this.userId = userId;
        this.deviceType = deviceType;
        this.clientIp = clientIp;
        this.requestId = requestId;
        this.turmsRequestBuilder = turmsRequestBuilder;
        this.turmsRequest = turmsRequest;
    }

    public ClientRequest(
            Long userId,
            DeviceType deviceType,
            byte[] clientIp,
            Long requestId,
            TurmsRequest turmsRequest) {
        this.userId = userId;
        this.deviceType = deviceType;
        this.clientIp = clientIp;
        this.requestId = requestId;
        this.turmsRequestBuilder = null;
        this.turmsRequest = turmsRequest;
    }

    @Override
    public String toString() {
        return "ClientRequest["
                + "userId="
                + userId
                + ", deviceType="
                + deviceType
                + ", clientIp="
                + Arrays.toString(clientIp)
                + ", requestId="
                + requestId
                + ", turmsRequest="
                + ProtoFormatter.toLogString(turmsRequest())
                + ']';
    }

    public TurmsRequest turmsRequest() {
        if (turmsRequest == null) {
            turmsRequest = turmsRequestBuilder.build();
        }
        return turmsRequest;
    }

    public Long userId() {
        return userId;
    }

    public DeviceType deviceType() {
        return deviceType;
    }

    public byte[] clientIp() {
        return clientIp;
    }

    public Long requestId() {
        return requestId;
    }

    public TurmsRequest.Builder turmsRequestBuilder() {
        return turmsRequestBuilder;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (ClientRequest) obj;
        return Objects.equals(this.userId, that.userId)
                && Objects.equals(this.deviceType, that.deviceType)
                && Arrays.equals(this.clientIp, that.clientIp)
                && Objects.equals(this.requestId, that.requestId)
                && Objects.equals(this.turmsRequestBuilder, that.turmsRequestBuilder)
                && Objects.equals(this.turmsRequest, that.turmsRequest);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(userId, deviceType, requestId, turmsRequestBuilder, turmsRequest)
                + Arrays.hashCode(clientIp);
    }

}