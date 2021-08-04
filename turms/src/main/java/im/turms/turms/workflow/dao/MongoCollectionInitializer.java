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

package im.turms.turms.workflow.dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.BsonPool;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.entity.MongoEntity;
import im.turms.server.common.mongo.entity.Zone;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.MultiTemperatureProperties;
import im.turms.server.common.util.ReactorUtil;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import im.turms.turms.workflow.dao.domain.admin.AdminRole;
import im.turms.turms.workflow.dao.domain.conversation.GroupConversation;
import im.turms.turms.workflow.dao.domain.conversation.PrivateConversation;
import im.turms.turms.workflow.dao.domain.group.Group;
import im.turms.turms.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.turms.workflow.dao.domain.group.GroupInvitation;
import im.turms.turms.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.turms.workflow.dao.domain.group.GroupJoinRequest;
import im.turms.turms.workflow.dao.domain.group.GroupMember;
import im.turms.turms.workflow.dao.domain.group.GroupType;
import im.turms.turms.workflow.dao.domain.group.GroupVersion;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.dao.domain.user.UserPermissionGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.turms.workflow.dao.domain.user.UserVersion;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static im.turms.server.common.property.env.service.env.database.MultiTemperatureProperties.TemperatureProperties;

/**
 * @author James Chen
 */
@Log4j2
@Component(IMongoCollectionInitializer.BEAN_NAME)
public class MongoCollectionInitializer implements IMongoCollectionInitializer {

    private final TurmsMongoClient adminMongoClient;
    private final TurmsMongoClient userMongoClient;
    private final TurmsMongoClient groupMongoClient;
    private final TurmsMongoClient conversationMongoClient;
    private final TurmsMongoClient messageMongoClient;
    private final List<TurmsMongoClient> clients;

    private final TurmsApplicationContext context;
    private final MongoFakingManager fakingManager;
    private final MultiTemperatureProperties messageTemperatureProperties;

    public MongoCollectionInitializer(
            TurmsMongoClient adminMongoClient,
            TurmsMongoClient userMongoClient,
            TurmsMongoClient groupMongoClient,
            TurmsMongoClient conversationMongoClient,
            TurmsMongoClient messageMongoClient,
            PasswordManager passwordManager,
            TurmsApplicationContext context,
            TurmsPropertiesManager turmsPropertiesManager) {
        this.adminMongoClient = adminMongoClient;
        this.userMongoClient = userMongoClient;
        this.groupMongoClient = groupMongoClient;
        this.conversationMongoClient = conversationMongoClient;
        this.messageMongoClient = messageMongoClient;
        clients = List.of(adminMongoClient,
                userMongoClient,
                groupMongoClient,
                conversationMongoClient,
                messageMongoClient);
        this.context = context;
        fakingManager = new MongoFakingManager(turmsPropertiesManager.getLocalProperties().getService().getFake(),
                passwordManager,
                adminMongoClient,
                userMongoClient,
                groupMongoClient,
                conversationMongoClient,
                messageMongoClient);
        messageTemperatureProperties = turmsPropertiesManager.getLocalProperties()
                .getService()
                .getMongo()
                .getMessage()
                .getTemperature();

        initCollections();
    }

    private void initCollections() {
        Duration timeout = Duration.ofMinutes(1);
        if (!context.isProduction() && fakingManager.isClearAllCollectionsBeforeFaking()) {
            log.info("Start dropping databases...");
            dropAllDatabases().block(timeout);
            log.info("All collections are cleared");
        }
        log.info("Start creating collections...");
        createCollectionsIfNotExist()
                .doOnError(t -> log.error("Failed to create collections", t))
                .doOnSuccess(ignored -> log.info("All collections are created"))
                .flatMap(exists -> {
                    if (exists && !fakingManager.isFakeIfCollectionExists()) {
                        return Mono.empty();
                    }
                    return Mono.defer(() -> ensureZones()
                            .then(Mono.defer(this::ensureIndexesAndShard)
                                    .doOnError(t -> log.error("Failed to ensure indexes and shard", t)))
                            .then(Mono.defer(() -> !context.isProduction() && fakingManager.isFakingEnabled()
                                    ? fakingManager.fakeData()
                                    : Mono.empty())));
                })
                .block(timeout);
    }

    /**
     * @return True if all collections have existed
     */
    private Mono<Boolean> createCollectionsIfNotExist() {
        return ReactorUtil.areAllTrue(
                createCollectionIfNotExist(Admin.class),
                createCollectionIfNotExist(AdminRole.class),

                createCollectionIfNotExist(Group.class),
                createCollectionIfNotExist(GroupBlockedUser.class),
                createCollectionIfNotExist(GroupInvitation.class),
                createCollectionIfNotExist(GroupJoinQuestion.class),
                createCollectionIfNotExist(GroupMember.class),
                createCollectionIfNotExist(GroupType.class),
                createCollectionIfNotExist(GroupVersion.class),

                createCollectionIfNotExist(PrivateConversation.class),
                createCollectionIfNotExist(GroupConversation.class),

                createCollectionIfNotExist(Message.class),

                createCollectionIfNotExist(User.class),
                createCollectionIfNotExist(UserFriendRequest.class),
                createCollectionIfNotExist(UserPermissionGroup.class),
                createCollectionIfNotExist(UserRelationship.class),
                createCollectionIfNotExist(UserRelationshipGroup.class),
                createCollectionIfNotExist(UserRelationshipGroupMember.class),
                createCollectionIfNotExist(UserVersion.class));
    }

    /**
     * @return whether the collection has already existed
     */
    private <T> Mono<Boolean> createCollectionIfNotExist(Class<T> clazz) {
        TurmsMongoClient mongoClient;
        if (clazz == Admin.class || clazz == AdminRole.class) {
            mongoClient = adminMongoClient;
        } else if (clazz == User.class || clazz == UserFriendRequest.class
                || clazz == UserPermissionGroup.class || clazz == UserRelationship.class
                || clazz == UserRelationshipGroup.class || clazz == UserRelationshipGroupMember.class || clazz == UserVersion.class) {
            mongoClient = userMongoClient;
        } else if (clazz == Group.class || clazz == GroupBlockedUser.class || clazz == GroupInvitation.class
                || clazz == GroupJoinQuestion.class || clazz == GroupJoinRequest.class || clazz == GroupMember.class
                || clazz == GroupType.class || clazz == GroupVersion.class) {
            mongoClient = groupMongoClient;
        } else if (clazz == PrivateConversation.class || clazz == GroupConversation.class) {
            mongoClient = conversationMongoClient;
        } else if (clazz == Message.class) {
            mongoClient = messageMongoClient;
        } else {
            return Mono.error(new IllegalArgumentException("Unknown collection " + clazz.getName()));
        }
        return mongoClient.collectionExists(clazz)
                .flatMap(exists -> exists
                        ? Mono.just(exists)
                        // Note that we do NOT assign a validator to collections
                        // because it's very common that business scenarios change over time
                        // and some new fields need to be added
                        : mongoClient.createCollection(clazz).thenReturn(exists));
    }

    private Mono<Void> dropAllDatabases() {
        Mono<Void> dropDatabase = Mono.empty();
        for (TurmsMongoClient client : clients) {
            dropDatabase = dropDatabase
                    .then(Mono.defer(client::dropDatabase));
        }
        return dropDatabase;
    }

    private Mono<Void> ensureIndexesAndShard() {
        Multimap<TurmsMongoClient, MongoEntity<?>> entityMap = HashMultimap.create(clients.size(), 8);
        for (TurmsMongoClient client : clients) {
            entityMap.putAll(client, client.getRegisteredEntities());
        }
        return Mono.when(entityMap.asMap().entrySet().stream()
                .map(entry -> entry.getKey().ensureIndexesAndShard(entry.getValue().stream()
                        .map(MongoEntity::getEntityClass)
                        .collect(Collectors.toList())))
                .toList());
    }

    private Mono<Void> ensureZones() {
        Mono<Void> ensureZones = Mono.empty();
        var map = Map.of(messageTemperatureProperties, messageMongoClient);
        for (var entry : map.entrySet()) {
            MultiTemperatureProperties properties = entry.getKey();
            if (!properties.isEnabled()) {
                continue;
            }
            TurmsMongoClient client = entry.getValue();
            for (MongoEntity<?> entity : client.getRegisteredEntities()) {
                ensureZones = ensureZones
                        .then(Mono.defer(() -> ensureZones(properties, client, entity)));
            }
        }

        return ensureZones;
    }

    private Mono<Void> ensureZones(MultiTemperatureProperties temperatureProperties,
                                   TurmsMongoClient mongoClient,
                                   MongoEntity<?> entity) {
        Zone zone = entity.getZone();
        if (zone == null) {
            return Mono.empty();
        }
        String collectionName = entity.getCollectionName();
        log.info("Adding the shards of the {} collection to zones...", collectionName);
        Map<String, TemperatureProperties> temperatures = new LinkedHashMap<>(8);
        temperatures.put(collectionName + "_hot", temperatureProperties.getHot());
        temperatures.put(collectionName + "_warm", temperatureProperties.getWarm());
        temperatures.put(collectionName + "_cold", temperatureProperties.getCold());
        temperatures.put(collectionName + "_frozen", temperatureProperties.getFrozen());

        Mono<Void> ensureZones = Mono.empty();
        Date startDate = new Date();
        int currentDay = 0;
        String creationDateFieldName = zone.getCreationDateFieldName();
        for (Map.Entry<String, TemperatureProperties> entry : temperatures.entrySet()) {
            String zoneName = entry.getKey();
            TemperatureProperties properties = entry.getValue();
            int days = properties.getDays();
            if (days <= 0) {
                throw new IllegalArgumentException("The days of temperature properties must be more than 0");
            }
            List<String> shards = properties.getShards();
            for (String shard : shards) {
                if (!StringUtils.hasText(shard)) {
                    continue;
                }
                Object min = zoneName.endsWith("frozen")
                        ? BsonPool.MIN_KEY
                        : DateUtils.addDays(startDate, -(currentDay + days));
                Object max = zoneName.endsWith("hot")
                        ? BsonPool.MAX_KEY
                        : DateUtils.addDays(startDate, -currentDay);
                ensureZones = ensureZones
                        .then(Mono.defer(() -> mongoClient.addShardToZone(shard, zoneName)
                                        .doOnError(t -> log.error("Failed to add a shard {} to the zone {}", shard, zoneName, t)))
                                .doOnSuccess(unused -> log.info("Added a shard {} to the zone {}", shard, zoneName)))
                        .then(Mono.defer(() -> {
                            // TODO: support the shard key consisting of multiple fields
                            Document minimum = new Document(creationDateFieldName, min);
                            Document maximum = new Document(creationDateFieldName, max);
                            return mongoClient.updateZoneKeyRange(collectionName,
                                            zoneName,
                                            minimum,
                                            maximum)
                                    .doOnError(t -> log.error("Failed to update the zone {} with the key ranges: {} -->> {}",
                                            zoneName,
                                            minimum.toJson(),
                                            maximum.toJson(),
                                            t))
                                    .doOnSuccess(unused -> log.info("Updated the zone {} with the key ranges: {} -->> {}",
                                            zoneName,
                                            minimum.toJson(),
                                            maximum.toJson()));
                        }));
            }
            currentDay += days;
        }
        return ensureZones
                .doOnSuccess(unused -> log.info("Added the shards of the {} collection to zones", collectionName));
    }

}