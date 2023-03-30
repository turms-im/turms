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

package im.turms.service.infra.logging;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.netty.ByteBufUtil;

import static im.turms.server.common.infra.logging.CommonLogger.LOG_FIELD_DELIMITER;
import static im.turms.server.common.infra.logging.CommonLogger.NOTIFICATION_LOGGER;

/**
 * @author James Chen
 * @implNote Don't use StringBuilder because String#join is more efficient
 */
public final class NotificationLogging {

    private NotificationLogging() {
    }

    /**
     * Note that although TurmsNotification can represent a "response" or "notification", the method
     * is only designed to log "notification" instead of "response"
     */
    public static void log(
            int recipientCount,
            int onlineRecipientCount,
            TurmsNotification notification) {
        TurmsRequest relayedRequest = notification.getRelayedRequest();
        ByteBuf buffer = ByteBufUtil.join(64,
                LOG_FIELD_DELIMITER,
                // Requester info
                NumberFormatter.toCharBytes(notification.getRequesterId()),
                // Recipient info
                NumberFormatter.toCharBytes(recipientCount),
                NumberFormatter.toCharBytes(onlineRecipientCount),
                // Notification info: self info + meta info
                notification.hasCloseStatus()
                        ? NumberFormatter.toCharBytes(notification.getCloseStatus())
                        : null,
                NumberFormatter.toCharBytes(notification.getSerializedSize()),
                // Relayed request info
                // ID is most important, so we put it first
                NumberFormatter.toCharBytes(relayedRequest.getRequestId()),
                relayedRequest.getKindCase()
                        .name());
        NOTIFICATION_LOGGER.info(buffer);
    }

}