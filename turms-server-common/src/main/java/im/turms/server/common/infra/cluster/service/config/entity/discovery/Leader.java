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

package im.turms.server.common.infra.cluster.service.config.entity.discovery;

import java.util.Date;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;

/**
 * @author James Chen
 */
@Data
@Document
@FieldNameConstants
public final class Leader {

    private static final int LEASE_DURATION = 60;

    @Id
    private final String clusterId;

    private final String nodeId;

    @Indexed(expireAfterSeconds = LEASE_DURATION)
    private final Date renewDate;

    /**
     * Start from 1. Used to prevent the previous leader tries to renew itself if it hasn't detected
     * the leader change.
     */
    private final int generation;

}