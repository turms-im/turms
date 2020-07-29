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

package im.turms.turms.workflow.access.http.dto.request.group;

import im.turms.turms.workflow.access.http.dto.response.StatisticsRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public final class GroupStatisticsDTO {
    private Long deletedGroups;
    private Long groupsThatSentMessages;
    private Long createdGroups;

    private List<StatisticsRecordDTO> deletedGroupsRecords;
    private List<StatisticsRecordDTO> groupsThatSentMessagesRecords;
    private List<StatisticsRecordDTO> createdGroupsRecords;
}
