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

package im.turms.server.common.cluster.service.serialization.serializer;

import lombok.Getter;

/**
 * Use enum so that we can use EnumMap in code
 *
 * @author James Chen
 * @implNote Note that the ID value is integer for better performance and convenience in JVM
 * but they are converted to signed short when transferring across servers.
 */
public enum SerializerId {

    PRIMITIVE_BYTE(1),
    PRIMITIVE_SHORT(2),
    PRIMITIVE_INTEGER(3),
    PRIMITIVE_LONG(4),
    PRIMITIVE_FLOAT(5),
    PRIMITIVE_DOUBLE(6),
    PRIMITIVE_CHAR(7),
    PRIMITIVE_BOOL(8),

    STRING(10),

    COLLECTION_LIST(100),

    BO_USER_ONLINE_INFO(201),

    // RPC
    RPC_HANDLE_SERVICE_REQUEST(1000),
    RPC_SEND_NOTIFICATION(1001),

    RPC_SET_USER_OFFLINE(1100),
    RPC_UPDATE_ONLINE_USER_STATUS(1101),

    // RPC - Statistics
    RPC_COUNT_ONLINE_USERS(1200),

    DTO_SERVICE_RESPONSE(2000);

    @Getter
    private final int id;

    SerializerId(int id) {
        this.id = id;
    }
}
