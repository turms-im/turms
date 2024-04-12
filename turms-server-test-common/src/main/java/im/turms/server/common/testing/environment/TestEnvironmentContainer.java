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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileSystemUtils;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.DOCKER_COMPOSE_TEST_FILE;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.ELASTICSEARCH;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.ELASTICSEARCH_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MINIO;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MINIO_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MONGO;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MONGO_CONFIG;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MONGO_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.MONGO_SHARD;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.REDIS;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.REDIS_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_ADMIN;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY_JVM_OPTION_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_SERVICE;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_SERVICE_JVM_OPTION_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_SERVICE_SERVICE_NAME;

/**
 * @author James Chen
 */
@Slf4j
public class TestEnvironmentContainer extends ComposeContainer implements Closeable {

    private static final Path TEMP_DIR;

    private final Path workingDir;
    private final String dockerComposeConfig;
    private final boolean hasContainer;
    private volatile boolean started;

    static {
        String tempDir = System.getProperty("java.io.tmpdir");
        if (tempDir == null) {
            throw new RuntimeException("Failed to get the temporary directory");
        }
        TEMP_DIR = Path.of(tempDir)
                .toAbsolutePath()
                .normalize();
    }

    /**
     * @param dockerCompose TODO: Use InputStream instead of File once DockerComposeContainer
     *                      supports
     *                      (https://github.com/testcontainers/testcontainers-java/issues/3431)
     */
    public TestEnvironmentContainer(
            Path workingDir,
            File dockerCompose,
            String dockerComposeConfig,
            boolean hasContainer) {
        super(dockerCompose);
        this.workingDir = workingDir;
        this.dockerComposeConfig = dockerComposeConfig;
        this.hasContainer = hasContainer;
    }

    public static TestEnvironmentContainer create(
            boolean setupElasticsearch,
            boolean setupMinio,
            boolean setupMongo,
            boolean setupRedis,
            boolean setupTurmsAdmin,
            boolean setupTurmsGateway,
            List<String> turmsGatewayJvmOptions,
            boolean setupTurmsService,
            List<String> turmsServiceJvmOptions) {
        Path workingDir = TEMP_DIR
                // Use a subfolder in the temporary directory
                // to avoid unnecessary file copying:
                // "org.testcontainers.containers.GenericContainer.tryStart"
                // will run "copyToFileContainerPathMap.forEach(this::copyFileToContainer);"
                // to copy files under the working directory to the container.
                .resolve(UUID.randomUUID()
                        .toString());
        try {
            Files.createDirectories(workingDir);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to create the working directory: "
                            + workingDir,
                    e);
        }
        File dockerComposeFile;
        try {
            dockerComposeFile = workingDir.resolve("docker-compose.yaml")
                    .toFile();
            dockerComposeFile.deleteOnExit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create the temp docker compose file", e);
        }
        String config = buildDockerComposeConfig(setupElasticsearch,
                setupMinio,
                setupMongo,
                setupRedis,
                setupTurmsAdmin,
                setupTurmsGateway,
                turmsGatewayJvmOptions,
                setupTurmsService,
                turmsServiceJvmOptions);
        try {
            FileUtils.writeStringToFile(dockerComposeFile, config, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to the temp docker compose file", e);
        }
        return new TestEnvironmentContainer(
                workingDir,
                dockerComposeFile,
                config,
                setupElasticsearch
                        || setupMinio
                        || setupMongo
                        || setupRedis
                        || setupTurmsAdmin
                        || setupTurmsGateway
                        || setupTurmsService);
    }

    private static String buildDockerComposeConfig(
            boolean setupElasticsearch,
            boolean setupMinio,
            boolean setupMongo,
            boolean setupRedis,
            boolean setupTurmsAdmin,
            boolean setupTurmsGateway,
            List<String> turmsGatewayJvmOptions,
            boolean setupTurmsService,
            List<String> turmsServiceJvmOptions) {
        InputStream resource = TestEnvironmentContainer.class.getClassLoader()
                .getResourceAsStream(DOCKER_COMPOSE_TEST_FILE);
        if (resource == null) {
            throw new RuntimeException(
                    "Could not find the resource: "
                            + DOCKER_COMPOSE_TEST_FILE);
        }
        YAMLMapper yamlMapper = YAMLMapper.builder()
                .build();
        Map<String, Object> config;
        try {
            config = yamlMapper.readValue(resource, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse the docker compose config", e);
        }
        Map<String, Object> services = (Map<String, Object>) config.get("services");
        if (!setupElasticsearch) {
            removeService(services, ELASTICSEARCH);
        }
        if (!setupMinio) {
            removeService(services, MINIO);
        }
        if (!setupMongo) {
            removeService(services, MONGO);
            removeService(services, MONGO_SHARD);
            removeService(services, MONGO_CONFIG);
        }
        if (!setupRedis) {
            removeService(services, REDIS);
        }
        if (!setupTurmsAdmin) {
            removeService(services, TURMS_ADMIN);
        }
        if (setupTurmsGateway) {
            appendCustomJvmOptions(turmsGatewayJvmOptions,
                    services,
                    TURMS_GATEWAY,
                    TURMS_GATEWAY_JVM_OPTION_NAME);
        } else {
            removeService(services, TURMS_GATEWAY);
        }
        if (setupTurmsService) {
            appendCustomJvmOptions(turmsServiceJvmOptions,
                    services,
                    TURMS_SERVICE,
                    TURMS_SERVICE_JVM_OPTION_NAME);
        } else {
            removeService(services, TURMS_SERVICE);
        }
        try {
            return yamlMapper.writeValueAsString(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize the docker compose config", e);
        }
    }

    private static void appendCustomJvmOptions(
            List<String> customJvmOptions,
            Map<String, Object> services,
            String serviceName,
            String variableName) {
        String jvmOptions = parseJvmOptions(customJvmOptions);
        if (jvmOptions.isBlank()) {
            return;
        }
        Map<String, Object> service = (Map<String, Object>) services.get(serviceName);
        Map<String, Object> env = (Map<String, Object>) service.get("environment");
        String jvmOpts = (String) env.getOrDefault(variableName, "");
        jvmOpts += jvmOptions;
        env.put(variableName, jvmOpts);
    }

    private static String parseJvmOptions(List<String> jvmOptions) {
        if (jvmOptions == null || jvmOptions.isEmpty()) {
            return "";
        }
        int size = 0;
        for (String option : jvmOptions) {
            size += 3;
            size += option.length();
        }
        StringBuilder builder = new StringBuilder(size);
        for (String option : jvmOptions) {
            option = option.trim();
            if (option.startsWith("-")) {
                builder.append(" ")
                        .append(option);
            } else {
                builder.append(" -D")
                        .append(option);
            }
        }
        return builder.toString();
    }

    private static void removeService(Map<String, Object> services, String serviceName) {
        Object removed = services.remove(serviceName);
        if (null == removed) {
            throw new RuntimeException(
                    "The service \""
                            + serviceName
                            + "\" is not specified");
        }
        for (Object value : services.values()) {
            if (value instanceof Map<?, ?> service
                    && service.get("depends_on") instanceof Map<?, ?> dependencies) {
                dependencies.remove(serviceName);
            }
        }
    }

    @Override
    public void start() {
        if (!hasContainer || started) {
            return;
        }
        synchronized (this) {
            if (started) {
                return;
            }
            log.info("The docker compose config: \n{}", dockerComposeConfig);
            try {
                Files.createDirectories(workingDir);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Failed to create the working directory: "
                                + workingDir,
                        e);
            }
            try {
                super.start();
            } catch (Exception e) {
                StringBuilder builder = new StringBuilder(1024);
                builder.append("Failed to start the docker compose: ");
                appendContainerInfo(builder,
                        "minio",
                        getContainerByServiceName(MINIO_SERVICE_NAME));
                appendContainerInfo(builder,
                        "mongo",
                        getContainerByServiceName(MONGO_SERVICE_NAME));
                appendContainerInfo(builder,
                        "redis",
                        getContainerByServiceName(REDIS_SERVICE_NAME));
                appendContainerInfo(builder,
                        "elasticsearch",
                        getContainerByServiceName(ELASTICSEARCH_SERVICE_NAME));
                appendContainerInfo(builder,
                        "turms-gateway",
                        getContainerByServiceName(TURMS_GATEWAY_SERVICE_NAME));
                appendContainerInfo(builder,
                        "turms-service",
                        getContainerByServiceName(TURMS_SERVICE_SERVICE_NAME));

                throw new RuntimeException(builder.toString(), e);
            }
            started = true;
        }
    }

    @Override
    public void close() {
        if (!hasContainer || !started) {
            return;
        }
        synchronized (this) {
            if (!started) {
                return;
            }
            try {
                stop();
                started = false;
            } catch (Exception e) {
                try {
                    FileSystemUtils.deleteRecursively(workingDir);
                } catch (Exception exception) {
                    e.addSuppressed(new RuntimeException(
                            "Caught an error while deleting: "
                                    + workingDir,
                            exception));
                }
                throw e;
            }
            try {
                FileSystemUtils.deleteRecursively(workingDir);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Caught an error while deleting: "
                                + workingDir,
                        e);
            }
        }
    }

    private void appendContainerInfo(
            StringBuilder output,
            String name,
            Optional<ContainerState> containerState) {
        output.append("\n")
                .append(name)
                .append(": ")
                .append(containerState.isPresent()
                        ? containerState.get()
                                .getCurrentContainerInfo()
                                .toString()
                        : "not started");
    }

}