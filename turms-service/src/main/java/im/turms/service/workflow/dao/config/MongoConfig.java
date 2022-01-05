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

package im.turms.service.workflow.dao.config;

import com.google.common.collect.Maps;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.AdminMongoProperties;
import im.turms.server.common.property.env.service.env.database.ConversationMongoProperties;
import im.turms.server.common.property.env.service.env.database.GroupMongoProperties;
import im.turms.server.common.property.env.service.env.database.MessageMongoProperties;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.property.env.service.env.database.UserMongoProperties;
import im.turms.service.workflow.dao.domain.admin.Admin;
import im.turms.service.workflow.dao.domain.admin.AdminRole;
import im.turms.service.workflow.dao.domain.conversation.GroupConversation;
import im.turms.service.workflow.dao.domain.conversation.PrivateConversation;
import im.turms.service.workflow.dao.domain.group.Group;
import im.turms.service.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.service.workflow.dao.domain.group.GroupInvitation;
import im.turms.service.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.service.workflow.dao.domain.group.GroupJoinRequest;
import im.turms.service.workflow.dao.domain.group.GroupMember;
import im.turms.service.workflow.dao.domain.group.GroupType;
import im.turms.service.workflow.dao.domain.group.GroupVersion;
import im.turms.service.workflow.dao.domain.message.Message;
import im.turms.service.workflow.dao.domain.user.UserFriendRequest;
import im.turms.service.workflow.dao.domain.user.UserPermissionGroup;
import im.turms.service.workflow.dao.domain.user.UserRelationship;
import im.turms.service.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.service.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.service.workflow.dao.domain.user.UserVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Map;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Configuration
public class MongoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfig.class);

    private static final int SERVICE_TYPES_NUMBER = 5;
    private static final Map<String, TurmsMongoClient> CLIENT_MAP = Maps.newHashMapWithExpectedSize(SERVICE_TYPES_NUMBER);

    @PreDestroy
    public void destroy() {
        for (TurmsMongoClient client : CLIENT_MAP.values()) {
            try {
                client.destroy();
            } catch (Exception ignored) {
                LOGGER.error("Failed to destroy a mongo client");
            }
        }
    }

    @Bean
    public TurmsMongoClient adminMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        AdminMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getAdmin();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Admin.class, properties.getAdminWriteConcern()),
                MongoCollectionOptions.of(AdminRole.class, properties.getAdminRoleWriteConcern()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        UserMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getUser();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(User.class, properties.getUserWriteConcern()),
                MongoCollectionOptions.of(UserFriendRequest.class, properties.getUserFriendRequestWriteConcern()),
                MongoCollectionOptions.of(UserPermissionGroup.class, properties.getUserPermissionGroupWriteConcern()),
                MongoCollectionOptions.of(UserRelationship.class, properties.getUserRelationshipWriteConcern()),
                MongoCollectionOptions.of(UserRelationshipGroup.class, properties.getUserRelationshipGroupWriteConcern()),
                MongoCollectionOptions.of(UserRelationshipGroupMember.class, properties.getUserRelationshipGroupMemberWriteConcern()),
                MongoCollectionOptions.of(UserVersion.class, properties.getUserVersionWriteConcern()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient groupMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        GroupMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getGroup();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Group.class, properties.getGroupWriteConcern()),
                MongoCollectionOptions.of(GroupBlockedUser.class, properties.getGroupBlockedUserWriteConcern()),
                MongoCollectionOptions.of(GroupInvitation.class, properties.getGroupInvitationWriteConcern()),
                MongoCollectionOptions.of(GroupJoinQuestion.class, properties.getGroupJoinQuestionWriteConcern()),
                MongoCollectionOptions.of(GroupJoinRequest.class, properties.getGroupJoinRequestWriteConcern()),
                MongoCollectionOptions.of(GroupMember.class, properties.getGroupMemberWriteConcern()),
                MongoCollectionOptions.of(GroupType.class, properties.getGroupTypeWriteConcern()),
                MongoCollectionOptions.of(GroupVersion.class, properties.getGroupVersionWriteConcern()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient conversationMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        ConversationMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getConversation();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(PrivateConversation.class, properties.getConversationWriteConcern()),
                MongoCollectionOptions.of(GroupConversation.class, properties.getConversationWriteConcern()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient messageMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        MessageMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getMessage();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Message.class, properties.getMessageWriteConcern()));
        return mongoClient;
    }

    private synchronized TurmsMongoClient getMongoClient(TurmsMongoProperties properties) {
        return CLIENT_MAP.computeIfAbsent(properties.getUri(), key -> {
            try {
                return TurmsMongoClient.of(properties)
                        .block(Duration.ofMinutes(1));
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create the mongo client", e);
            }
        });
    }

}