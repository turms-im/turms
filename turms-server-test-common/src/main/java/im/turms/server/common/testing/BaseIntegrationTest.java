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

package im.turms.server.common.testing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;

import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.environment.TestEnvironmentManager;
import im.turms.server.common.testing.properties.ElasticsearchTestEnvironmentProperties;
import im.turms.server.common.testing.properties.MinioTestEnvironmentProperties;
import im.turms.server.common.testing.properties.MongoTestEnvironmentProperties;
import im.turms.server.common.testing.properties.RedisTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TestProperties;
import im.turms.server.common.testing.properties.TurmsGatewayTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TurmsServiceTestEnvironmentProperties;

/**
 * @author James Chen
 */
@Slf4j
public abstract class BaseIntegrationTest {

    protected static final Duration DEFAULT_IO_TIMEOUT = Duration.ofSeconds(15);

    protected static long waitMillis = 1000L;
    protected static TestEnvironmentManager testEnvironmentManager;

    public static synchronized TestEnvironmentManager setupTestEnvironment() {
        if (testEnvironmentManager != null) {
            return testEnvironmentManager;
        }
        boolean setupTestEnvironmentFromPropertiesFile = true;
        try {
            testEnvironmentManager = TestEnvironmentManager.fromPropertiesFile();
        } catch (FileNotFoundException e) {
            testEnvironmentManager = TestEnvironmentManager.fromProperties(new TestProperties());
            setupTestEnvironmentFromPropertiesFile = false;
        }
        testEnvironmentManager.start();
        if (setupTestEnvironmentFromPropertiesFile) {
            log.info("Set up the test environment from the properties file: "
                    + TestEnvironmentManager.DEFAULT_PROPERTIES_FILE);
        } else {
            log.info("Set up the test environment from default properties");
        }
        return testEnvironmentManager;
    }

    public static synchronized TestEnvironmentManager setupTestEnvironment(
            TestProperties testProperties) {
        if (testEnvironmentManager != null) {
            return testEnvironmentManager;
        }
        testEnvironmentManager = TestEnvironmentManager.fromProperties(testProperties);
        testEnvironmentManager.start();
        log.info("Set up the test environment from properties");
        return testEnvironmentManager;
    }

    public static synchronized TestEnvironmentManager setupLocalTestEnvironment() {
        return setupTestEnvironment(new TestProperties().toBuilder()
                .elasticsearch(new ElasticsearchTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .minio(new MinioTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .mongo(new MongoTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .redis(new RedisTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .turmsGateway(new TurmsGatewayTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .turmsService(new TurmsServiceTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.LOCAL)
                        .build())
                .build());
    }

    protected String readText(String path) {
        InputStream resource = BaseIntegrationTest.class.getClassLoader()
                .getResourceAsStream(path);
        if (resource == null) {
            throw new RuntimeException(
                    "Could not find the resource: "
                            + path);
        }
        try {
            return new String(resource.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitToSync() {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}