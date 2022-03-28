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
package im.turms.client.model

import im.turms.client.model.proto.model.group.Group
import im.turms.client.model.proto.notification.TurmsNotification

/**
 * @author James Chen
 */
class GroupWithVersion {
    var group: Group? = null
    var lastUpdatedDate: Long = 0

    companion object {
        fun from(notification: TurmsNotification?): GroupWithVersion? {
            if (notification == null) {
                return null
            }
            val data = notification.data
            if (!notification.hasData() || !data.hasGroupsWithVersion()) {
                return null
            }
            val groupsWithVersion = data.groupsWithVersion
            val groupWithVersion = GroupWithVersion()
            if (groupsWithVersion.groupsCount > 0) {
                groupWithVersion.group = groupsWithVersion.getGroups(0)
            }
            groupWithVersion.lastUpdatedDate = groupsWithVersion.lastUpdatedDate
            return groupWithVersion
        }
    }
}