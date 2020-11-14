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

import lombok.Data;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
public final class UpdateGroupDTO {
    private final Long typeId;
    private final Long creatorId;
    private final Long ownerId;
    private final String name;
    private final String intro;
    private final String announcement;
    private final Integer minimumScore;
    private final Boolean isActive;
    private final Date creationDate;
    private final Date deletionDate;
    private final Date muteEndDate;
    private final Long successorId;
    private final Boolean quitAfterTransfer;
}
