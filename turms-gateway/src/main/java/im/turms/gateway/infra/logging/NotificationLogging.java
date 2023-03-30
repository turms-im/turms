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

package im.turms.gateway.infra.logging;

import io.netty.buffer.ByteBuf;

import im.turms.gateway.infra.proto.SimpleTurmsNotification;
import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.netty.ByteBufUtil;

import static im.turms.server.common.infra.logging.CommonLogger.LOG_FIELD_DELIMITER;
import static im.turms.server.common.infra.logging.CommonLogger.NOTIFICATION_LOGGER;

/**
 * @author James Chen
 */
public final class NotificationLogging {

    private NotificationLogging() {
    }

    public static void log(
            SimpleTurmsNotification notification,
            int notificationBytes,
            int recipientCount,
            int onlineRecipientCount) {
        Integer closeStatus = notification.closeStatus();
        ByteBuf buffer = ByteBufUtil.join(64,
                LOG_FIELD_DELIMITER,
                // Requester info
                NumberFormatter.toCharBytes(notification.requesterId()),
                // Recipient info
                NumberFormatter.toCharBytes(recipientCount),
                NumberFormatter.toCharBytes(onlineRecipientCount),
                // Notification info: self info + meta info
                closeStatus,
                NumberFormatter.toCharBytes(notificationBytes),
                // Relayed request info
                notification.relayedRequestType()
                        .name());
        NOTIFICATION_LOGGER.info(buffer);
    }

}