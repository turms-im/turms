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

package im.turms.gateway.infra.proto;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.CreateSessionRequest;
import im.turms.server.common.infra.proto.ProtoFormatter;

/**
 * @author James Chen
 */
public record SimpleTurmsRequest(
        long requestId,
        TurmsRequest.KindCase type,
        CreateSessionRequest createSessionRequest
) {
    @Override
    public String toString() {
        return "SimpleTurmsRequest["
                + "requestId="
                + requestId
                + ", type="
                + type
                + ", createSessionRequest="
                + ProtoFormatter.toLogString(createSessionRequest)
                + ']';
    }
}