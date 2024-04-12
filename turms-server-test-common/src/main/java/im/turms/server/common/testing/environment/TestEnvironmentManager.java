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

package im.turms.server.common.testing.environment;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import im.turms.server.common.testing.environment.elasticsearch.ElasticsearchTestEnvironmentAware;
import im.turms.server.common.testing.environment.elasticsearch.ElasticsearchTestEnvironmentManager;
import im.turms.server.common.testing.environment.minio.MinioTestEnvironmentAware;
import im.turms.server.common.testing.environment.minio.MinioTestEnvironmentManager;
import im.turms.server.common.testing.environment.mongo.MongoTestEnvironmentAware;
import im.turms.server.common.testing.environment.mongo.MongoTestEnvironmentManager;
import im.turms.server.common.testing.environment.redis.RedisTestEnvironmentAware;
import im.turms.server.common.testing.environment.redis.RedisTestEnvironmentManager;
import im.turms.server.common.testing.environment.turmsgateway.TurmsGatewayTestEnvironmentAware;
import im.turms.server.common.testing.environment.turmsgateway.TurmsGatewayTestEnvironmentManager;
import im.turms.server.common.testing.environment.turmsservice.TurmsServiceTestEnvironmentAware;
import im.turms.server.common.testing.environment.turmsservice.TurmsServiceTestEnvironmentManager;
import im.turms.server.common.testing.properties.ElasticsearchTestEnvironmentProperties;
import im.turms.server.common.testing.properties.MinioTestEnvironmentProperties;
import im.turms.server.common.testing.properties.MongoTestEnvironmentProperties;
import im.turms.server.common.testing.properties.RedisTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TestProperties;
import im.turms.server.common.testing.properties.TurmsAdminTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TurmsGatewayTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TurmsServiceTestEnvironmentProperties;

/**
 * @author James Chen
 * @implNote Our properties design supports mixing local services with container services, but our
 *           implementation does NOT fully support just because we don't have these test cases.
 */
@Slf4j
public class TestEnvironmentManager implements Closeable, ElasticsearchTestEnvironmentAware,
        MinioTestEnvironmentAware, MongoTestEnvironmentAware, RedisTestEnvironmentAware,
        TurmsGatewayTestEnvironmentAware, TurmsServiceTestEnvironmentAware {

    public static final String DEFAULT_PROPERTIES_FILE = "application-test.yaml";

    private final TestEnvironmentContainer testEnvironmentContainer;

    @Delegate
    private final ElasticsearchTestEnvironmentManager elasticsearchTestEnvironmentManager;
    @Delegate
    private final MinioTestEnvironmentManager minioTestEnvironmentManager;
    @Delegate
    private final MongoTestEnvironmentManager mongoTestEnvironmentManager;
    @Delegate
    private final RedisTestEnvironmentManager redisTestEnvironmentManager;
    @Delegate
    private final TurmsGatewayTestEnvironmentManager turmsGatewayTestEnvironmentManager;
    @Delegate
    private final TurmsServiceTestEnvironmentManager turmsServiceTestEnvironmentManager;

    private TestEnvironmentManager(TestProperties testProperties) {
        ElasticsearchTestEnvironmentProperties elasticsearchTestEnvironmentProperties =
                testProperties.getElasticsearch();
        MinioTestEnvironmentProperties minioTestEnvironmentProperties = testProperties.getMinio();
        MongoTestEnvironmentProperties mongoTestEnvironmentProperties = testProperties.getMongo();
        RedisTestEnvironmentProperties redisTestEnvironmentProperties = testProperties.getRedis();
        TurmsAdminTestEnvironmentProperties turmsAdminTestEnvironmentProperties =
                testProperties.getTurmsAdmin();
        TurmsGatewayTestEnvironmentProperties turmsGatewayTestEnvironmentProperties =
                testProperties.getTurmsGateway();
        TurmsServiceTestEnvironmentProperties turmsServiceTestEnvironmentProperties =
                testProperties.getTurmsService();

        testEnvironmentContainer = TestEnvironmentContainer.create(
                elasticsearchTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                minioTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                mongoTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                redisTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                turmsAdminTestEnvironmentProperties.isSetupContainer(),
                turmsGatewayTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                turmsGatewayTestEnvironmentProperties.getContainer()
                        .getJvmOptions(),
                turmsServiceTestEnvironmentProperties.getType()
                        .equals(ServiceTestEnvironmentType.CONTAINER),
                turmsServiceTestEnvironmentProperties.getContainer()
                        .getJvmOptions());

        elasticsearchTestEnvironmentManager = new ElasticsearchTestEnvironmentManager(
                elasticsearchTestEnvironmentProperties,
                testEnvironmentContainer);
        minioTestEnvironmentManager = new MinioTestEnvironmentManager(
                minioTestEnvironmentProperties,
                testEnvironmentContainer);
        mongoTestEnvironmentManager = new MongoTestEnvironmentManager(
                mongoTestEnvironmentProperties,
                testEnvironmentContainer);
        redisTestEnvironmentManager = new RedisTestEnvironmentManager(
                redisTestEnvironmentProperties,
                testEnvironmentContainer);
        turmsGatewayTestEnvironmentManager = new TurmsGatewayTestEnvironmentManager(
                turmsGatewayTestEnvironmentProperties,
                testEnvironmentContainer);
        turmsServiceTestEnvironmentManager = new TurmsServiceTestEnvironmentManager(
                turmsServiceTestEnvironmentProperties,
                testEnvironmentContainer);
    }

    public static TestEnvironmentManager fromProperties(TestProperties testProperties) {
        return new TestEnvironmentManager(testProperties);
    }

    public static TestEnvironmentManager fromPropertiesFile() throws FileNotFoundException {
        return fromPropertiesFile(DEFAULT_PROPERTIES_FILE);
    }

    public static TestEnvironmentManager fromPropertiesFile(String testPropertiesResourcePath)
            throws FileNotFoundException {
        InputStream resource = TestEnvironmentManager.class.getClassLoader()
                .getResourceAsStream(testPropertiesResourcePath);
        if (resource == null) {
            throw new FileNotFoundException(
                    "The resource is not found: "
                            + testPropertiesResourcePath);
        }
        YAMLMapper yamlMapper = YAMLMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
        JsonNode node;
        try {
            node = yamlMapper.readTree(resource);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read the resource: "
                            + testPropertiesResourcePath,
                    e);
        }
        for (String propertyName : StringUtils.tokenizeToStringArray(TestProperties.PREFIX, ".")) {
            node = node.get(propertyName);
            if (null == node) {
                // Use default properties if the properties file is found,
                // but it doesn't specify test properties.
                return fromProperties(new TestProperties());
            }
        }
        TestProperties testProperties;
        try {
            testProperties = yamlMapper.treeToValue(node, TestProperties.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert to test properties", e);
        }
        return fromProperties(testProperties);
    }

    public void start() {
        testEnvironmentContainer.start();
        // TODO: Support checking the running state of local services
        if (getElasticsearchTestEnvironmentType().equals(ServiceTestEnvironmentType.CONTAINER)
                && !isElasticsearchRunning()) {
            throw new IllegalStateException("The Elasticsearch container is not running");
        }
        if (getMinioTestEnvironmentType().equals(ServiceTestEnvironmentType.CONTAINER)
                && !isMongoRunning()) {
            throw new IllegalStateException("The MinIO container is not running");
        }
        if (getMongoTestEnvironmentType().equals(ServiceTestEnvironmentType.CONTAINER)
                && !isMongoRunning()) {
            throw new IllegalStateException("The MongoDB container is not running");
        }
        if (getRedisTestEnvironmentType().equals(ServiceTestEnvironmentType.CONTAINER)
                && !isRedisRunning()) {
            throw new IllegalStateException("The Redis container is not running");
        }
        log.info("Elasticsearch server URI: \"{}\"", getElasticsearchUri());
        log.info("MinIO server URI: \"{}\"", getMinioUri());
        log.info("MongoDB server URI: \"{}\"", getMongoUri());
        log.info("Redis server URI: \"{}\"", getRedisUri());
    }

    @Override
    public void close() {
        testEnvironmentContainer.stop();
    }

}