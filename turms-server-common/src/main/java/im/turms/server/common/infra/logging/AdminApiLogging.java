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

package im.turms.server.common.infra.logging;

import java.util.Map;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.time.DateUtil;

/**
 * @author James Chen
 */
public final class AdminApiLogging {

    private AdminApiLogging() {
    }

    public static void log(
            String account,
            String ip,
            String requestId,
            long requestTime,
            String action,
            Map<String, Object> params,
            int processingTime,
            Throwable throwable) {
        boolean isSuccessful = throwable == null;
        int estimatedSize = 64;
        String reason;
        if (isSuccessful) {
            reason = "";
        } else {
            reason = StringUtil
                    .replaceLatin1(throwable.toString(), AsciiCode.NEW_LINE, AsciiCode.SPACE);
            estimatedSize += reason.length();
        }
        ByteBuf buffer = ByteBufUtil.join(estimatedSize,
                CommonLogger.LOG_FIELD_DELIMITER,
                // Session
                account,
                ip,
                // Request
                requestId,
                DateUtil.toBytes(requestTime),
                action,
                params.toString(),
                // Response
                isSuccessful
                        ? "TRUE"
                        : "FALSE",
                NumberFormatter.toCharBytes(processingTime),
                reason);
        CommonLogger.ADMIN_API_LOGGER.info(buffer);
    }

}