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

package im.turms.turms.workflow.access.servicerequest.dto;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.util.ProtoUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientRequest {
    private final Long userId;
    private final DeviceType deviceType;
    private final Long requestId;
    private final TurmsRequest turmsRequest;

    @Override
    public String toString() {
        return "ClientRequest{" +
                "userId=" + userId +
                ", deviceType=" + deviceType +
                ", requestId=" + requestId +
                ", turmsRequest=" + ProtoUtil.toLogString(turmsRequest) +
                '}';
    }
}