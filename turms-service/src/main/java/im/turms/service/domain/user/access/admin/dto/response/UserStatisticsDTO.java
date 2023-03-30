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

package im.turms.service.domain.user.access.admin.dto.response;

import java.util.List;

import lombok.Builder;

import im.turms.server.common.domain.common.access.dto.ControllerDTO;
import im.turms.service.domain.common.access.admin.dto.response.StatisticsRecordDTO;

/**
 * @author James Chen
 */
@Builder
public record UserStatisticsDTO(
        Long deletedUsers,
        Long usersWhoSentMessages,
        Long loggedInUsers,
        Long maxOnlineUsers,
        Long registeredUsers,
        List<StatisticsRecordDTO> deletedUsersRecords,
        List<StatisticsRecordDTO> usersWhoSentMessagesRecords,
        List<StatisticsRecordDTO> loggedInUsersRecords,
        List<StatisticsRecordDTO> maxOnlineUsersRecords,
        List<StatisticsRecordDTO> registeredUsersRecords
) implements ControllerDTO {
}