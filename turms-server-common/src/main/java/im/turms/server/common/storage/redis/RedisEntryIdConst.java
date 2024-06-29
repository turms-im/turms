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

package im.turms.server.common.storage.redis;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.netty.ByteBufUtil;

/**
 * @author James Chen
 */
public final class RedisEntryIdConst {

    private RedisEntryIdConst() {
    }

    // region: key
    public static final ByteBuf KEY_LOCATION_BUFFER =
            ByteBufUtil.getUnreleasableDirectBuffer(new byte[]{'l'});

    public static final ByteBuf KEY_GROUP_MESSAGE_SEQUENCE_ID_BUFFER =
            ByteBufUtil.getUnreleasableDirectBuffer(new byte[]{'g', 's'});

    public static final String KEY_PRIVATE_MESSAGE_SEQUENCE_ID = "ps";
    public static final String KEY_RELATED_USER_IDS = "ru";
    // endregion

    // region: hash field
    public static final byte FIELD_SESSIONS_STATUS = '$';
    // endregion

}