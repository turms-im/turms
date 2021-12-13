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

package im.turms.gateway.logging;

import im.turms.gateway.pojo.dto.SimpleTurmsNotification;
import im.turms.server.common.util.StringUtil;

import static im.turms.server.common.logging.CommonLogger.LOG_FIELD_DELIMITER;
import static im.turms.server.common.logging.CommonLogger.NOTIFICATION_LOGGER;

/**
 * @author James Chen
 * @implNote Don't use StringBuilder because String#join is more efficient
 */
public final class NotificationLogging {

    private NotificationLogging() {
    }

    public static void log(boolean sent, SimpleTurmsNotification notification, int size, int recipientCount) {
        Integer closeStatus = notification.closeStatus();
        String message = String.join(LOG_FIELD_DELIMITER,
                // User info
                String.valueOf(notification.requesterId()),
                // Notification info
                sent ? "SENT" : "UNSENT",
                String.valueOf(recipientCount),
                StringUtil.toString(closeStatus),
                String.valueOf(size),
                // Relayed request info
                notification.relayedRequestType().name());
        NOTIFICATION_LOGGER.info(message);
    }

}