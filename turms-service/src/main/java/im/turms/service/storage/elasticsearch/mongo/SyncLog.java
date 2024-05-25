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

package im.turms.service.storage.elasticsearch.mongo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;

/**
 * @author James Chen
 */
@Data
@Document(SyncLog.COLLECTION_NAME)
@FieldNameConstants
public class SyncLog extends BaseEntity {

    public static final String COLLECTION_NAME = "syncLog";

    @Id
    private final Long id;

    private final String nodeId;

    private final String mongoCollection;

    private final String esIndex;

    private final SyncStatus status;

    @Indexed
    private final Date creationDate;

    private final Date lastUpdatedDate;

    /**
     * Our sync logic may change over time. This field records the version of the logic, so that we
     * can support smooth migration.
     */
    private final int version;

}