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

import im.turms.server.common.dao.BaseMongoConfig;
import im.turms.server.common.dao.domain.Admin;
import im.turms.server.common.dao.domain.AdminRole;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.AdminMongoProperties;
import im.turms.server.common.property.env.service.env.database.ConversationMongoProperties;
import im.turms.server.common.property.env.service.env.database.GroupMongoProperties;
import im.turms.server.common.property.env.service.env.database.MessageMongoProperties;
import im.turms.server.common.property.env.service.env.database.UserMongoProperties;
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

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Configuration
public class MongoConfig extends BaseMongoConfig {

    @Bean
    public TurmsMongoClient adminMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        AdminMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getAdmin();
        AdminMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Admin.class, writeConcern.getAdmin()),
                MongoCollectionOptions.of(AdminRole.class, writeConcern.getAdminRole()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        UserMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getUser();
        UserMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(User.class, writeConcern.getUser()),
                MongoCollectionOptions.of(UserFriendRequest.class, writeConcern.getUserFriendRequest()),
                MongoCollectionOptions.of(UserPermissionGroup.class, writeConcern.getUserPermissionGroup()),
                MongoCollectionOptions.of(UserRelationship.class, writeConcern.getUserRelationship()),
                MongoCollectionOptions.of(UserRelationshipGroup.class, writeConcern.getUserRelationshipGroup()),
                MongoCollectionOptions.of(UserRelationshipGroupMember.class, writeConcern.getUserRelationshipGroupMember()),
                MongoCollectionOptions.of(UserVersion.class, writeConcern.getUserVersion()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient groupMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        GroupMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getGroup();
        GroupMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Group.class, writeConcern.getGroup()),
                MongoCollectionOptions.of(GroupBlockedUser.class, writeConcern.getGroupBlockedUser()),
                MongoCollectionOptions.of(GroupInvitation.class, writeConcern.getGroupInvitation()),
                MongoCollectionOptions.of(GroupJoinQuestion.class, writeConcern.getGroupJoinQuestion()),
                MongoCollectionOptions.of(GroupJoinRequest.class, writeConcern.getGroupJoinRequest()),
                MongoCollectionOptions.of(GroupMember.class, writeConcern.getGroupMember()),
                MongoCollectionOptions.of(GroupType.class, writeConcern.getGroupType()),
                MongoCollectionOptions.of(GroupVersion.class, writeConcern.getGroupVersion()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient conversationMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        ConversationMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getConversation();
        ConversationMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(PrivateConversation.class, writeConcern.getConversation()),
                MongoCollectionOptions.of(GroupConversation.class, writeConcern.getConversation()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient messageMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        MessageMongoProperties properties = turmsPropertiesManager.getLocalProperties().getService().getMongo().getMessage();
        MessageMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Message.class, writeConcern.getMessage()));
        return mongoClient;
    }

}