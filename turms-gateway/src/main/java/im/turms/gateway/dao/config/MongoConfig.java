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

package im.turms.gateway.dao.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import im.turms.server.common.dao.converter.EnumToIntegerConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverter;
import im.turms.server.common.dao.converter.IntegerToEnumConverterFactory;
import im.turms.server.common.property.TurmsPropertiesManager;
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
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Log4j2
@Configuration
public class MongoConfig {

    @Bean
    public ReactiveMongoTemplate userMongoTemplate(
            TurmsPropertiesManager turmsPropertiesManager,
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            ObjectProvider<MongoClientSettings> settings) {
        MongoProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getDatabase().getMongoProperties().getUser();
        // ReactiveMongoClientFactory
        ReactiveMongoClientFactory factory = new ReactiveMongoClientFactory(properties, null,
                builderCustomizers.orderedStream().collect(Collectors.toList()));
        MongoClient mongoClient = factory.createMongoClient(settings.getIfAvailable());
        SimpleReactiveMongoDatabaseFactory databaseFactory = new SimpleReactiveMongoDatabaseFactory(mongoClient, properties.getMongoClientDatabase());

        // MongoMappingContext
        MongoMappingContext context = new MongoMappingContext();
        context.setAutoIndexCreation(true);

        // MappingMongoConverter
        MappingMongoConverter converter = newMongoConverter(context);

        // ReactiveMongoTemplate
        ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(databaseFactory, converter);
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

        return mongoTemplate;
    }

    public MappingMongoConverter newMongoConverter(MongoMappingContext mongoMappingContext) {
        // Rather than saving enum values in string, we save them in integer to avoid unnecessary space and performance
        List<Converter<?, ?>> converters = List.of(
                new EnumToIntegerConverter(),
                new IntegerToEnumConverter(null));

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

}