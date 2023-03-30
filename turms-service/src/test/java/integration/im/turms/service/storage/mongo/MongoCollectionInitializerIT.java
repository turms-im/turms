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

package integration.im.turms.service.storage.mongo;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.CaseFormat;
import helper.SpringAwareIntegrationTest;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.service.domain.conversation.po.GroupConversation;
import im.turms.service.domain.conversation.po.PrivateConversation;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.po.GroupBlockedUser;
import im.turms.service.domain.group.po.GroupInvitation;
import im.turms.service.domain.group.po.GroupJoinQuestion;
import im.turms.service.domain.group.po.GroupJoinRequest;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.po.GroupVersion;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.user.po.UserFriendRequest;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.po.UserRelationship;
import im.turms.service.domain.user.po.UserRelationshipGroup;
import im.turms.service.domain.user.po.UserRelationshipGroupMember;
import im.turms.service.domain.user.po.UserVersion;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class MongoCollectionInitializerIT extends SpringAwareIntegrationTest {

    static final TurmsMongoClient MONGO_CLIENT;
    static final List<Class<?>> MODEL_CLASSES = List.of(
            // Admin
            Admin.class,
            AdminRole.class,
            // Group
            Group.class,
            GroupBlockedUser.class,
            GroupInvitation.class,
            GroupJoinQuestion.class,
            GroupJoinRequest.class,
            GroupMember.class,
            GroupType.class,
            GroupVersion.class,
            // Message
            Message.class,
            // Conversation
            GroupConversation.class,
            PrivateConversation.class,
            // User
            User.class,
            UserFriendRequest.class,
            UserPermissionGroup.class,
            UserRelationship.class,
            UserRelationshipGroup.class,
            UserRelationshipGroupMember.class,
            UserVersion.class);

    static {
        TurmsMongoProperties mongoProperties = new TurmsMongoProperties(getMongoUri());
        MONGO_CLIENT = TurmsMongoClient.of(mongoProperties, "test")
                .block(Duration.ofMinutes(1));
        MONGO_CLIENT.registerEntitiesByClasses(MODEL_CLASSES);
    }

    @Test
    void validateSchemas() {
        for (Class<?> modelClass : MODEL_CLASSES) {
            String schemaFileName = getFileName(modelClass);
            String jsonSchema = readText("schema/"
                    + schemaFileName
                    + ".json");
            StepVerifier.create(MONGO_CLIENT.validate(modelClass, jsonSchema))
                    .expectNext(true)
                    .as("Entity ("
                            + modelClass.getName()
                            + ") must have a valid schema")
                    .expectComplete()
                    .verify(DEFAULT_IO_TIMEOUT);
        }
    }

    @Test
    void validateIndexes() {
        for (Class<?> modelClass : MODEL_CLASSES) {
            StepVerifier.create(MONGO_CLIENT.listIndexes(modelClass)
                    .collect(CollectorUtil.toList(8)))
                    .assertNext(indexes -> {
                        String schemaFileName = getFileName(modelClass);
                        String json = readText("index/"
                                + schemaFileName
                                + ".json");
                        List<Document> expectedIndexes = (List<Document>) Document
                                .parse("{\"json\":"
                                        + json
                                        + "}")
                                .get("json");
                        assertThat(indexes).as("Entity ("
                                + modelClass.getName()
                                + ") must have the expected size")
                                .hasSize(expectedIndexes.size());
                        expectedIndexes.forEach(expectedIndex -> {
                            Object expectedIndexName = expectedIndex.get("name");
                            Optional<Document> found = indexes.stream()
                                    .filter(document -> document.get("name")
                                            .equals(expectedIndexName))
                                    .findFirst();
                            assertThat(found).as("Must contain the index: \""
                                    + expectedIndexName
                                    + "\"")
                                    .isPresent();
                            Document index = found.get();
                            for (Map.Entry<String, Object> entry : expectedIndex.entrySet()) {
                                assertThat(index).containsEntry(entry.getKey(), entry.getValue());
                            }
                        });
                    })
                    .as("Entity ("
                            + modelClass.getName()
                            + ") must have valid index models")
                    .expectComplete()
                    .verify(DEFAULT_IO_TIMEOUT);
        }
    }

    private String getFileName(Class<?> modelClass) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, modelClass.getSimpleName());
    }

}