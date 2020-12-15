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

package im.turms.turms.workflow.dao.config;

import com.google.common.collect.Maps;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import im.turms.server.common.bo.log.UserLocationLog;
import im.turms.server.common.dao.context.TurmsMongoMappingContext;
import im.turms.server.common.dao.converter.EnumToIntegerConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverterFactory;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.dao.util.MongoUtil;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.DatabaseProperties;
import im.turms.turms.workflow.dao.domain.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.*;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Log4j2
@Configuration
public class MongoConfig {

    private static final int SERVICE_TYPES_NUMBER = 4;
    // hash code of MongoProperties -> ReactiveMongoTemplate
    // because MongoProperties doesn't have a custom hashcode implementation but a native implementation
    private static final Map<Integer, ReactiveMongoTemplate> TEMPLATE_MAP = Maps.newHashMapWithExpectedSize(SERVICE_TYPES_NUMBER);
    private static final int DEFAULT_MONGO_PROPERTIES_HASHCODE = getPropertiesHashCode(new MongoProperties());
    private final TurmsPropertiesManager turmsPropertiesManager;
    private final Map<Class<?>, WriteConcern> writeConcernMap;

    public MongoConfig(TurmsPropertiesManager turmsPropertiesManager) {
        this.turmsPropertiesManager = turmsPropertiesManager;
        writeConcernMap = initWriteConcernMap(turmsPropertiesManager.getLocalProperties());
    }

    private static int getPropertiesHashCode(MongoProperties properties) {
        int result = Objects.hash(properties.getHost(), properties.getPort(), properties.getUri(),
                properties.getDatabase(), properties.getAuthenticationDatabase(),
                properties.getGridFsDatabase(), properties.getUsername(),
                properties.getReplicaSetName(), properties.getFieldNamingStrategy(),
                properties.getUuidRepresentation(), properties.isAutoIndexCreation());
        result = 31 * result + Arrays.hashCode(properties.getPassword());
        return result;
    }

    private Map<Class<?>, WriteConcern> initWriteConcernMap(TurmsProperties turmsProperties) {
        DatabaseProperties.WriteConcern writeConcern = turmsProperties.getService().getDatabase().getWriteConcern();
        Map<Class<?>, WriteConcern> map = new IdentityHashMap<>();

        map.put(Admin.class, writeConcern.getAdmin());
        map.put(AdminRole.class, writeConcern.getAdminRole());

        map.put(Group.class, writeConcern.getGroup());
        map.put(GroupBlacklistedUser.class, writeConcern.getGroupBlacklistedUser());
        map.put(GroupInvitation.class, writeConcern.getGroupInvitation());
        map.put(GroupJoinQuestion.class, writeConcern.getGroupJoinQuestion());
        map.put(GroupJoinRequest.class, writeConcern.getGroupJoinRequest());
        map.put(GroupMember.class, writeConcern.getGroupMember());
        map.put(GroupType.class, writeConcern.getGroupType());
        map.put(GroupVersion.class, writeConcern.getGroupVersion());

        map.put(Message.class, writeConcern.getMessage());
        map.put(MessageStatus.class, writeConcern.getMessageStatus());

        map.put(User.class, writeConcern.getUser());
        map.put(UserFriendRequest.class, writeConcern.getUserFriendRequest());
        map.put(UserLocationLog.class, writeConcern.getUserLocation());
        map.put(UserPermissionGroup.class, writeConcern.getUserPermissionGroup());
        map.put(UserRelationship.class, writeConcern.getUserRelationship());
        map.put(UserRelationshipGroup.class, writeConcern.getUserRelationshipGroup());
        map.put(UserRelationshipGroupMember.class, writeConcern.getUserRelationshipGroupMember());
        map.put(UserVersion.class, writeConcern.getUserVersion());

        return map;
    }

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> {
            Class<?> entityType = action.getEntityType();
            if (entityType == null) {
                return WriteConcern.ACKNOWLEDGED;
            }
            WriteConcern writeConcern = writeConcernMap.get(entityType);
            if (writeConcern == null) {
                log.warn("An unknown entity type {} attempts to store documents", entityType.getName());
                return action.getDefaultWriteConcern();
            } else {
                return writeConcern;
            }
        };
    }

    @Bean
    public ReactiveMongoTemplate adminMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            WriteConcernResolver writeConcernResolver) {
        ReactiveMongoTemplate template = getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getAdmin(), writeConcernResolver);
        MongoUtil.createIndexes(template, Set.of(Admin.class, AdminRole.class));
        return template;
    }

    @Bean
    public ReactiveMongoTemplate groupMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            WriteConcernResolver writeConcernResolver) {
        ReactiveMongoTemplate template = getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getGroup(), writeConcernResolver);
        MongoUtil.createIndexes(template, Set.of(Group.class,
                GroupBlacklistedUser.class,
                GroupInvitation.class,
                GroupJoinQuestion.class,
                GroupJoinRequest.class,
                GroupMember.class,
                GroupType.class,
                GroupVersion.class));
        return template;
    }

    @Bean
    public ReactiveMongoTemplate messageMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            WriteConcernResolver writeConcernResolver) {
        ReactiveMongoTemplate template = getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getMessage(), writeConcernResolver);
        MongoUtil.createIndexes(template, Set.of(Message.class, MessageStatus.class));
        return template;
    }

    @Bean
    public ReactiveMongoTemplate userMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            WriteConcernResolver writeConcernResolver) {
        ReactiveMongoTemplate template = getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getUser(), writeConcernResolver);
        MongoUtil.createIndexes(template, Set.of(User.class,
                UserFriendRequest.class,
                UserLocationLog.class,
                UserPermissionGroup.class,
                UserRelationship.class,
                UserRelationshipGroup.class,
                UserRelationshipGroupMember.class,
                UserVersion.class));
        return template;
    }

    public MappingMongoConverter newMongoConverter(MongoMappingContext mongoMappingContext) {
        List<Converter<?, ?>> converters = new ArrayList<>();
        // Rather than saving enum values in string, we save them in integer to avoid unnecessary space and performance
        converters.add(new EnumToIntegerConverter());
        converters.add(new IntegerToEnumConverter(null));

        // To avoid saving the class information in MongoDB
        CustomConversions customConversions = new CustomConversions(CustomConversions.StoreConversions.NONE, converters);
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.setCustomConversions(customConversions);

        ConversionService conversionService = converter.getConversionService();
        ((GenericConversionService) conversionService)
                .addConverterFactory(new IntegerToEnumConverterFactory());
        converter.afterPropertiesSet();
        return converter;
    }

    private ReactiveMongoTemplate getMongoTemplate(
            MongoProperties properties,
            WriteConcernResolver writeConcernResolver) {
        return TEMPLATE_MAP.computeIfAbsent(getPropertiesHashCode(properties), key -> {
            MongoProperties currentProperties = key == DEFAULT_MONGO_PROPERTIES_HASHCODE
                    ? turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getDefaultProperties()
                    : properties;
            // SimpleReactiveMongoDatabaseFactory
            MongoPropertiesClientSettingsBuilderCustomizer customizer = new MongoPropertiesClientSettingsBuilderCustomizer(currentProperties, null);
            ReactiveMongoClientFactory clientFactory = new ReactiveMongoClientFactory(List.of(customizer));
            MongoClient mongoClient = clientFactory.createMongoClient(MongoClientSettings.builder().build());
            SimpleReactiveMongoDatabaseFactory databaseFactory = new SimpleReactiveMongoDatabaseFactory(mongoClient, currentProperties.getMongoClientDatabase());

            // MongoMappingContext
            // Note that we don't use the field naming strategy specified by developer
            TurmsMongoMappingContext context = new TurmsMongoMappingContext();
            // We check and create indexes ourselves
            context.setAutoIndexCreation(false);

            // MappingMongoConverter
            MappingMongoConverter converter = newMongoConverter(context);

            // ReactiveMongoTemplate
            ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(databaseFactory, converter);
            mongoTemplate.setWriteConcernResolver(writeConcernResolver);
            mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

            return mongoTemplate;
        });
    }

}