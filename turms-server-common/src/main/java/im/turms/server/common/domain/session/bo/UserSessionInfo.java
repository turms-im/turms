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

package im.turms.server.common.domain.session.bo;

import java.util.Date;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.infra.net.InetAddressUtil;

/**
 * @author James Chen
 */
public record UserSessionInfo(
        int id,
        int version,
        DeviceType deviceType,
        @Nullable Map<String, String> deviceDetails,
        Date loginDate,
        @Nullable Location loginLocation,
        Date lastHeartbeatRequestDate,
        Date lastRequestDate,
        boolean isSessionOpen,
        @JsonIgnore @Nullable byte[] ipBytes,
        @Nullable String ip
) {

    @Override
    public String ip() {
        return InetAddressUtil.ipBytesToString(ipBytes);
    }

}
