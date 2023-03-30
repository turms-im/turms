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

package im.turms.plugin.minio.po;

import java.util.Date;
import java.util.List;

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.IndexType;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * @author James Chen
 */
@Data
@Document(MessageAttachment.COLLECTION_NAME)
@Sharded
public class MessageAttachment extends BaseEntity {

    public static final String COLLECTION_NAME = "minioMessageAttachment";

    /**
     * It is secure to use int64 ID because even if crawlers can predict resource IDs, they don't
     * have permission to download them (We use signed URLs)
     */
    @Id
    private final Long id;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.MEDIA_TYPE)
    private final String mediaType;

    @Field(Fields.UPLOADER_ID)
    @Indexed(IndexType.HASH)
    private final Long uploaderId;

    @Field(Fields.CREATION_DATE)
    @Indexed
    private final Date creationDate;

    /**
     * We use an array instead of an independent collection for message attachment sharing because:
     * 1. Creating message attachment and share it with a user or group at the same time to avoid
     * using transaction while keeping atomic 2. For most attachments, they only attached with one
     * conversation, so it is far more efficient than using two collections in most cases.
     * <p>
     * But we may support using an independent collection in the future to support more granular
     * control.
     */
    @Field(Fields.SHARED_WITH_USER_IDS)
    @Indexed
    private final List<Long> sharedWithUserIds;

    @Field(Fields.SHARED_WITH_GROUP_IDS)
    @Indexed
    private final List<Long> sharedWithGroupIds;

    public static final class Fields {
        public static final String NAME = "n";
        public static final String MEDIA_TYPE = "mt";
        public static final String UPLOADER_ID = "uid";
        public static final String CREATION_DATE = "cd";
        public static final String SHARED_WITH_USER_IDS = "suid";
        public static final String SHARED_WITH_GROUP_IDS = "sgid";

        private Fields() {
        }
    }
}