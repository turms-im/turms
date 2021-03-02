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

package im.turms.server.common.cluster.service.config.domain.discovery;

import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/**
 * @author James Chen
 */
@Document
@FieldNameConstants
@Data
public final class Leader {

    private static final int LEADER_MAX_TTL = 60;

    @Id
    private final String clusterId;

    private final String nodeId;

    @Indexed(expireAfterSeconds = LEADER_MAX_TTL)
    private final Date lastHeartbeatDate;

}