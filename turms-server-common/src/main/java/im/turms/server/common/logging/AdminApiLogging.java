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

package im.turms.server.common.logging;

import im.turms.server.common.util.ByteBufUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.server.common.util.Formatter;
import io.netty.buffer.ByteBuf;

import java.util.Map;

import static im.turms.server.common.logging.CommonLogger.ADMIN_API_LOGGER;
import static im.turms.server.common.logging.CommonLogger.LOG_FIELD_DELIMITER;

/**
 * @author James Chen
 */
public final class AdminApiLogging {

    private AdminApiLogging() {
    }

    public static void log(String account,
                           String ip,
                           String requestId,
                           long requestTime,
                           String action,
                           Map<String, Object> params,
                           int processingTime,
                           Throwable throwable) {
        boolean isSuccessful = throwable == null;
        int estimatedSize = 64 + (throwable == null ? 0 : 128);
        ByteBuf buffer = ByteBufUtil.join(estimatedSize, LOG_FIELD_DELIMITER,
                // Session
                account,
                ip,
                // Request
                requestId,
                DateUtil.toStr(requestTime),
                action,
                params.toString(),
                // Response
                isSuccessful ? "TRUE" : "FALSE",
                Formatter.toCharacterBytes(processingTime),
                isSuccessful ? "" : throwable.toString());
        ADMIN_API_LOGGER.info(buffer);
    }

}