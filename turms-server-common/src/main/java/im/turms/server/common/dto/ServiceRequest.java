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

package im.turms.server.common.dto;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import io.netty.buffer.ByteBuf;
import lombok.Data;

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
    private final Long traceId;
    private final Long requestId;
    private final TurmsRequest.KindCase type;

    /**
     * Note that turms-gateway doesn't parse and validate the request for better performance (zero copy)
     * and turms services should validate it by themselves
     */
    private final ByteBuf turmsRequestBuffer;
    private String ipStr;

    public ServiceRequest(byte[] ip,
                          Long userId,
                          DeviceType deviceType,
                          Long traceId,
                          Long requestId,
                          TurmsRequest.KindCase type,
                          ByteBuf turmsRequestBuffer) {
        this.ip = ip;
        this.userId = userId;
        this.deviceType = deviceType;
        this.traceId = traceId;
        this.requestId = requestId;
        this.type = type;
        this.turmsRequestBuffer = turmsRequestBuffer;
    }

    public String getIpStr() {
        if (ipStr == null) {
            StringBuilder ipStrBuilder = new StringBuilder();
            for (int i = 0; i < ip.length; i++) {
                ipStrBuilder.append(ip[i] & 0xFF);
                if (i != ip.length - 1) {
                    ipStrBuilder.append('.');
                }
            }
            ipStr = ipStrBuilder.toString();
        }
        return ipStr;
    }

}