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
package im.turms.client

import im.turms.client.driver.TurmsDriver
import im.turms.client.service.*

/**
 * @author James Chen
 */
class TurmsClient constructor(
    url: String? = null,
    connectTimeout: Int? = null,
    requestTimeout: Int? = null,
    minRequestInterval: Int? = null,
    heartbeatInterval: Int? = null,
    storageServerUrl: String? = null
) {
    val driver: TurmsDriver =
        TurmsDriver(url, connectTimeout, requestTimeout, minRequestInterval, heartbeatInterval)
    val userService: UserService = UserService(this)
    val groupService: GroupService = GroupService(this)
    val conversationService: ConversationService = ConversationService(this)
    val messageService: MessageService = MessageService(this)
    val storageService: StorageService = StorageService(this, storageServerUrl)
    val notificationService: NotificationService = NotificationService(this)

}