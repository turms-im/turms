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
import im.turms.server.common.dao.converter.EnumToIntegerConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverterFactory;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.DatabaseProperties;
import im.turms.turms.workflow.dao.domain.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
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
import java.util.stream.Collectors;

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
    private final Map<Class<?>, WriteConcern> writeConcernMap = new IdentityHashMap<>();

    public MongoConfig(TurmsPropertiesManager turmsPropertiesManager) {
        this.turmsPropertiesManager = turmsPropertiesManager;
        initWriteConcernMap(turmsPropertiesManager.getLocalProperties());
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

    private void initWriteConcernMap(TurmsProperties turmsProperties) {
        DatabaseProperties.WriteConcern writeConcern = turmsProperties.getService().getDatabase().getWriteConcern();
        writeConcernMap.put(Admin.class, writeConcern.getAdmin());
        writeConcernMap.put(AdminRole.class, writeConcern.getAdminRole());

        writeConcernMap.put(Group.class, writeConcern.getGroup());
        writeConcernMap.put(GroupBlacklistedUser.class, writeConcern.getGroupBlacklistedUser());
        writeConcernMap.put(GroupInvitation.class, writeConcern.getGroupInvitation());
        writeConcernMap.put(GroupJoinQuestion.class, writeConcern.getGroupJoinQuestion());
        writeConcernMap.put(GroupJoinRequest.class, writeConcern.getGroupJoinRequest());
        writeConcernMap.put(GroupMember.class, writeConcern.getGroupMember());
        writeConcernMap.put(GroupType.class, writeConcern.getGroupType());
        writeConcernMap.put(GroupVersion.class, writeConcern.getGroupVersion());

        writeConcernMap.put(Message.class, writeConcern.getMessage());
        writeConcernMap.put(MessageStatus.class, writeConcern.getMessageStatus());

        writeConcernMap.put(User.class, writeConcern.getUser());
        writeConcernMap.put(UserFriendRequest.class, writeConcern.getUserFriendRequest());
        writeConcernMap.put(UserLocationLog.class, writeConcern.getUserLocation());
        writeConcernMap.put(UserPermissionGroup.class, writeConcern.getUserPermissionGroup());
        writeConcernMap.put(UserRelationship.class, writeConcern.getUserRelationship());
        writeConcernMap.put(UserRelationshipGroup.class, writeConcern.getUserRelationshipGroup());
        writeConcernMap.put(UserRelationshipGroupMember.class, writeConcern.getUserRelationshipGroupMember());
        writeConcernMap.put(UserVersion.class, writeConcern.getUserVersion());
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
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings,
            WriteConcernResolver writeConcernResolver) {
        return getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getAdmin(), builderCustomizers, settings, writeConcernResolver);
    }

    @Bean
    public ReactiveMongoTemplate groupMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings,
            WriteConcernResolver writeConcernResolver) {
        return getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getGroup(), builderCustomizers, settings, writeConcernResolver);
    }

    @Bean
    public ReactiveMongoTemplate messageMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings,
            WriteConcernResolver writeConcernResolver) {
        return getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getMessage(), builderCustomizers, settings, writeConcernResolver);
    }

    @Bean
    public ReactiveMongoTemplate userMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings,
            WriteConcernResolver writeConcernResolver) {
        return getMongoTemplate(turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getUser(), builderCustomizers, settings, writeConcernResolver);
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
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings,
            WriteConcernResolver writeConcernResolver) {
        return TEMPLATE_MAP.computeIfAbsent(getPropertiesHashCode(properties), key -> {
            MongoProperties currentProperties = key == DEFAULT_MONGO_PROPERTIES_HASHCODE
                    ? turmsPropertiesManager.getLocalProperties().getService().getDatabase().getMongoProperties().getDefaultProperties()
                    : properties;
            // ReactiveMongoClientFactory
            ReactiveMongoClientFactory factory = new ReactiveMongoClientFactory(builderCustomizers.orderedStream().collect(Collectors.toList()));
            MongoClient mongoClient = factory.createMongoClient(settings.getIfAvailable());
            SimpleReactiveMongoDatabaseFactory databaseFactory = new SimpleReactiveMongoDatabaseFactory(mongoClient, currentProperties.getMongoClientDatabase());

            // MongoMappingContext
            boolean autoIndexCreation = currentProperties.isAutoIndexCreation() != null
                    ? currentProperties.isAutoIndexCreation()
                    : true;
            // Note that we don't use the field naming strategy specified by developer
            MongoMappingContext context = new MongoMappingContext();
            context.setAutoIndexCreation(autoIndexCreation);

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