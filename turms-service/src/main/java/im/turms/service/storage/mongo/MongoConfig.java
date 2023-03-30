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

import java.util.Collections;
import java.util.Set;

import com.mongodb.connection.ClusterType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.env.database.AdminMongoProperties;
import im.turms.server.common.infra.property.env.service.env.database.ConversationMongoProperties;
import im.turms.server.common.infra.property.env.service.env.database.GroupMongoProperties;
import im.turms.server.common.infra.property.env.service.env.database.MessageMongoProperties;
import im.turms.server.common.infra.property.env.service.env.database.UserMongoProperties;
import im.turms.server.common.storage.mongo.BaseMongoConfig;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.MongoCollectionOptions;
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

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Configuration
public class MongoConfig extends BaseMongoConfig {

    public MongoConfig(TurmsApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Bean
    public TurmsMongoClient adminMongoClient(TurmsPropertiesManager propertiesManager) {
        AdminMongoProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getAdmin();
        AdminMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties, "admin", Collections.emptySet());
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Admin.class, writeConcern.getAdmin()),
                MongoCollectionOptions.of(AdminRole.class, writeConcern.getAdminRole()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager propertiesManager) {
        UserMongoProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getUser();
        UserMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties,
                "user",
                Set.of(ClusterType.SHARDED, ClusterType.LOAD_BALANCED));
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(User.class, writeConcern.getUser()),
                MongoCollectionOptions.of(UserFriendRequest.class,
                        writeConcern.getUserFriendRequest()),
                MongoCollectionOptions.of(UserPermissionGroup.class,
                        writeConcern.getUserPermissionGroup()),
                MongoCollectionOptions.of(UserRelationship.class,
                        writeConcern.getUserRelationship()),
                MongoCollectionOptions.of(UserRelationshipGroup.class,
                        writeConcern.getUserRelationshipGroup()),
                MongoCollectionOptions.of(UserRelationshipGroupMember.class,
                        writeConcern.getUserRelationshipGroupMember()),
                MongoCollectionOptions.of(UserVersion.class, writeConcern.getUserVersion()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient groupMongoClient(TurmsPropertiesManager propertiesManager) {
        GroupMongoProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getGroup();
        GroupMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties,
                "group",
                Set.of(ClusterType.SHARDED, ClusterType.LOAD_BALANCED));
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Group.class, writeConcern.getGroup()),
                MongoCollectionOptions.of(GroupBlockedUser.class,
                        writeConcern.getGroupBlockedUser()),
                MongoCollectionOptions.of(GroupInvitation.class, writeConcern.getGroupInvitation()),
                MongoCollectionOptions.of(GroupJoinQuestion.class,
                        writeConcern.getGroupJoinQuestion()),
                MongoCollectionOptions.of(GroupJoinRequest.class,
                        writeConcern.getGroupJoinRequest()),
                MongoCollectionOptions.of(GroupMember.class, writeConcern.getGroupMember()),
                MongoCollectionOptions.of(GroupType.class, writeConcern.getGroupType()),
                MongoCollectionOptions.of(GroupVersion.class, writeConcern.getGroupVersion()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient conversationMongoClient(TurmsPropertiesManager propertiesManager) {
        ConversationMongoProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getConversation();
        ConversationMongoProperties.WriteConcernProperties writeConcern =
                properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties,
                "conversation",
                Set.of(ClusterType.SHARDED, ClusterType.LOAD_BALANCED));
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(PrivateConversation.class,
                        writeConcern.getConversation()),
                MongoCollectionOptions.of(GroupConversation.class, writeConcern.getConversation()));
        return mongoClient;
    }

    @Bean
    public TurmsMongoClient messageMongoClient(TurmsPropertiesManager propertiesManager) {
        MessageMongoProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getMessage();
        MessageMongoProperties.WriteConcernProperties writeConcern = properties.getWriteConcern();
        TurmsMongoClient mongoClient = getMongoClient(properties,
                "message",
                Set.of(ClusterType.SHARDED, ClusterType.LOAD_BALANCED));
        mongoClient.registerEntitiesByOptions(
                MongoCollectionOptions.of(Message.class, writeConcern.getMessage()));
        return mongoClient;
    }

}