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

package im.turms.server.common.access.servicerequest.dto;

import java.util.Arrays;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.util.NetUtil;
import lombok.Data;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.net.InetAddressUtil;

/**
 * @author James Chen
 */
@Data
public final class ServiceRequest {

    // client information
    private final byte[] ip;
    // user information
    private final Long userId;
    private final DeviceType deviceType;
    // request information
    /**
     * Null in turms-service but not null in turms-gateway
     */
    @Nullable
    private final Long requestId;
    /**
     * Null in turms-service but not null in turms-gateway
     */
    @Nullable
    private final TurmsRequest.KindCase type;

    /**
     * @implNote Note that turms-gateway doesn't parse and validate the request because 1. For
     *           better performance (zero copy) 2. Decouple the business logic so that turms-service
     *           servers can change the structure of DTOs without the need to upgrade and restart
     *           turms-gateway servers turms-service servers should validate it by themselves
     */
    private final ByteBuf turmsRequestBuffer;

    // computed properties
    private String ipStr;

    public ServiceRequest(
            byte[] ip,
            Long userId,
            DeviceType deviceType,
            @Nullable Long requestId,
            @Nullable TurmsRequest.KindCase type,
            ByteBuf turmsRequestBuffer) {
        if (!InetAddressUtil.isIpV4OrV6(ip)) {
            throw new IllegalArgumentException(
                    "Illegal IP bytes: "
                            + Arrays.toString(ip));
        }
        this.ip = ip;
        this.userId = userId;
        this.deviceType = deviceType;
        this.requestId = requestId;
        this.type = type;
        this.turmsRequestBuffer = turmsRequestBuffer;
    }

    public String getIpStr() {
        if (ipStr == null) {
            ipStr = NetUtil.bytesToIpAddress(ip);
        }
        return ipStr;
    }

    @Override
    public String toString() {
        return "ServiceRequest{"
                + "ip="
                + getIpStr()
                + ", userId="
                + userId
                + ", deviceType="
                + deviceType
                + ", requestId="
                + requestId
                + ", type="
                + type
                + ", turmsRequestBuffer="
                + turmsRequestBuffer
                + '}';
    }

}