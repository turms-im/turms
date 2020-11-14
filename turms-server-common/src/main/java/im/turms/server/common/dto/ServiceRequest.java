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

    private final Long userId;
    private final DeviceType deviceType;
    private final Long requestId;
    private final TurmsRequest.KindCase type;

    /**
     * Note that turms-gateway doesn't parse and validate the request for better performance (zero copy)
     * and turms services should validate it by themselves
     */
    private final ByteBuf turmsRequestBuffer;

}