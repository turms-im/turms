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

package im.turms.service.storage.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInvalidOperationException;
import org.bson.BsonValue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.exception.CorruptedDocumentException;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.service.domain.user.po.UserRole;

/**
 * @author James Chen
 */
public final class MongoCollectionMigrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoCollectionMigrator.class);

    private final TurmsMongoClient adminMongoClient;
    private final TurmsMongoClient userMongoClient;

    public MongoCollectionMigrator(
            TurmsMongoClient adminMongoClient,
            TurmsMongoClient userMongoClient) {
        this.adminMongoClient = adminMongoClient;
        this.userMongoClient = userMongoClient;
    }

    public Mono<Void> migrate(Set<String> existingCollectionNames) {
        return Flux
                .concat(List.of(migrateAdminDocs(),
                        existingCollectionNames.contains(UserRole.LEGACY_COLLECTION_NAME)
                                ? migrateUserRoleDocs()
                                : Mono.empty()))
                .then();
    }

    private Mono<Void> migrateAdminDocs() {
        return adminMongoClient.findMany(Admin.COLLECTION_NAME,
                Filter.newBuilder(3)
                        .type(DomainFieldName.ID, "string"))
                .collect(CollectorUtil.toChunkedList())
                .flatMap(legacyAdminDocs -> {
                    if (legacyAdminDocs.isEmpty()) {
                        return Mono.empty();
                    }
                    return adminMongoClient.inTransaction(clientSession -> {
                        int size = legacyAdminDocs.size();
                        LOGGER.info("Migrating "
                                + size
                                + " legacy admin documents");
                        List<Admin> admins = new ArrayList<>(size);
                        Set<Long> ids = CollectionUtil.newSetWithExpectedSize(size);
                        for (BsonDocument doc : legacyAdminDocs) {
                            admins.add(convertLegacyAdminRecord(doc, ids));
                        }
                        return adminMongoClient.insertAllOfSameType(clientSession, admins)
                                .doOnSuccess(unused -> LOGGER
                                        .info("Migrated {} legacy admin documents", size))
                                .onErrorMap(t -> new RuntimeException(
                                        "Failed to migrate legacy admin documents",
                                        t));
                    });
                });
    }

    private Admin convertLegacyAdminRecord(BsonDocument doc, Set<Long> ids) {
        long id;
        do {
            id = RandomUtil.nextPositiveLong();
        } while (ids.add(id));
        String oldRecordId = doc.getString(DomainFieldName.ID)
                .getValue();
        BsonArray rawRoleIds = doc.getArray(Admin.Fields.ROLE_IDS, BsonPool.BSON_ARRAY_EMPTY);
        if (rawRoleIds.isEmpty()) {
            throw new CorruptedDocumentException(
                    "The admin must have at least one role. Admin ID: "
                            + oldRecordId);
        }
        Set<Long> roleIds = CollectionUtil.newSetWithExpectedSize(rawRoleIds.size());
        for (BsonValue roleId : rawRoleIds) {
            roleIds.add(roleId.asInt64()
                    .getValue());
        }
        try {
            return new Admin(
                    id,
                    oldRecordId,
                    doc.getBinary(Admin.Fields.PASSWORD)
                            .getData(),
                    doc.getString(Admin.Fields.DISPLAY_NAME)
                            .getValue(),
                    roleIds,
                    new Date(
                            doc.getDateTime(Admin.Fields.REGISTRATION_DATE)
                                    .getValue()));
        } catch (BsonInvalidOperationException e) {
            throw new CorruptedDocumentException(
                    "The admin must have at least one role. Admin ID: "
                            + oldRecordId,
                    e);
        }
    }

    private Mono<Void> migrateUserRoleDocs() {
        return userMongoClient
                .renameCollection(UserRole.LEGACY_COLLECTION_NAME, UserRole.COLLECTION_NAME)
                .doOnSuccess(unused -> LOGGER.info("Renamed the legacy collection \""
                        + UserRole.LEGACY_COLLECTION_NAME
                        + "\" to \""
                        + UserRole.COLLECTION_NAME
                        + "\""))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to the legacy collection \""
                                + UserRole.LEGACY_COLLECTION_NAME
                                + "\" to \""
                                + UserRole.COLLECTION_NAME
                                + "\"",
                        t))
                .doOnSubscribe(subscription -> LOGGER.info("Renaming the legacy collection \""
                        + UserRole.LEGACY_COLLECTION_NAME
                        + "\" to \""
                        + UserRole.COLLECTION_NAME
                        + "\""));
    }

}