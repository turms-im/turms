package im.turms.server.common.testing;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author James Chen
 */
@Slf4j
public class TestingEnvContainer extends DockerComposeContainer<TestingEnvContainer> implements Closeable {

    private static final String MONGO_SERVICE_NAME = "mongodb-router_1";
    private static final int MONGO_SERVICE_PORT = 27017;
    private static final String PASSWORD = "turms";

    private static final String REDIS_SERVICE_NAME = "redis_1";
    private static final int REDIS_SERVICE_PORT = 6379;

    private static final String TURMS_SERVICE = "turms-service";
    private static final String TURMS_SERVICE_SERVICE_NAME = "turms-service_1";
    private static final int TURMS_SERVICE_ADMIN_PORT = 8510;

    private static final String TURMS_GATEWAY = "turms-gateway";
    private static final String TURMS_GATEWAY_SERVICE_NAME = "turms-gateway_1";
    private static final int TURMS_GATEWAY_SERVICE_ADMIN_PORT = 9510;
    private static final int TURMS_GATEWAY_SERVICE_WS_PORT = 10510;

    public TestingEnvContainer(TestingEnvContainerOptions options) {
        super(getComposeFile(options));
        withExposedService(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
        // FIXME: testcontainers emits the actual STDOUT string as the STDERR string
        withLogConsumer(MONGO_SERVICE_NAME, new ServiceLogConsumer(MONGO_SERVICE_NAME));

        withExposedService(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
        withLogConsumer(REDIS_SERVICE_NAME, new ServiceLogConsumer(REDIS_SERVICE_NAME));

        if (options.isSetupTurmsGateway()) {
            withExposedService(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_ADMIN_PORT);
            withExposedService(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_WS_PORT);
            withLogConsumer(TURMS_GATEWAY_SERVICE_NAME, new ServiceLogConsumer(TURMS_GATEWAY_SERVICE_NAME));
        }

        if (options.isSetupTurmsService()) {
            withExposedService(TURMS_SERVICE_SERVICE_NAME, TURMS_SERVICE_ADMIN_PORT);
            withLogConsumer(TURMS_SERVICE_SERVICE_NAME, new ServiceLogConsumer(TURMS_SERVICE_SERVICE_NAME));
        }
    }

    @Override
    public void start() {
        super.start();
        log.info("MongoDB server URI: {}:{}", getMongoHost(), getMongoPort());
        log.info("Redis server URI: {}:{}", getRedisHost(), getRedisPort());
    }

    @Override
    public void close() {
        stop();
    }

    /**
     * Fixme: Return InputStream instead of File once DockerComposeContainer supports
     * (https://github.com/testcontainers/testcontainers-java/issues/3431)
     */
    private static File getComposeFile(TestingEnvContainerOptions options) {
        String name = "docker-compose.test.yml";
        InputStream resource = TestingEnvContainer.class.getClassLoader().getResourceAsStream(name);
        if (resource == null) {
            throw new IllegalStateException("Cannot find the resource " + name);
        }
        try {
            File tempFile = File.createTempFile("docker-compose", "yml");
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(resource);
            Map<String, Object> services = (Map<String, Object>) config.get("services");
            if (options.isSetupTurmsGateway()) {
                appendCustomJvmOptions(options.getTurmsGatewayJvmOptions(), services, TURMS_GATEWAY, "TURMS_GATEWAY_JVM_OPTS");
                if (!options.isSetupTurmsService()) {
                    removeDependency(services, TURMS_GATEWAY, TURMS_SERVICE);
                }
            } else {
                services.remove(TURMS_GATEWAY);
            }
            if (options.isSetupTurmsService()) {
                appendCustomJvmOptions(options.getTurmsGatewayJvmOptions(), services, TURMS_SERVICE, "TURMS_SERVICE_JVM_OPTS");
            } else {
                services.remove(TURMS_SERVICE);
            }
            if (!options.isSetupTurmsAdmin()) {
                services.remove("turms-admin");
            }
            String finalConfig = yaml.dump(config);
            FileUtils.writeStringToFile(tempFile, finalConfig, StandardCharsets.UTF_8);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendCustomJvmOptions(List<String> customJvmOptions,
                                               Map<String, Object> services,
                                               String serviceName,
                                               String variableName) {
        String jvmOptions = parseJvmOptions(customJvmOptions);
        if (jvmOptions.isBlank()) {
            return;
        }
        Map<String, Object> service = (Map<String, Object>) services.get(serviceName);
        Map<String, Object> env = (Map<String, Object>) service.get("environment");
        String turmsGatewayJvmOpts = (String) env.getOrDefault(variableName, "");
        turmsGatewayJvmOpts += jvmOptions;
        env.put(variableName, turmsGatewayJvmOpts);
    }

    private static String parseJvmOptions(List<String> jvmOptions) {
        if (jvmOptions == null || jvmOptions.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String option : jvmOptions) {
            option = option.trim();
            if (option.startsWith("-")) {
                builder.append(" ").append(option);
            } else {
                builder.append(" -D").append(option);
            }
        }
        return builder.toString();
    }

    private static void removeDependency(Map<String, Object> services, String serviceName, String dependencyName) {
        Map<String, Object> service = (Map<String, Object>) services.get(serviceName);
        Map<String, Object> dependencies = (Map<String, Object>) service.get("depends_on");
        dependencies.remove(dependencyName);
    }

    // Mongo

    public ContainerState getMongoContainer() {
        Optional<ContainerState> container = getContainerByServiceName(MONGO_SERVICE_NAME);
        if (container.isPresent()) {
            return container.get();
        }
        throw new IllegalStateException("Cannot find the mongo container by the service name " + MONGO_SERVICE_NAME);
    }

    public String getMongoHost() {
        return getServiceHost(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
    }

    public int getMongoPort() {
        return getServicePort(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
    }

    public String getMongoPassword() {
        return PASSWORD;
    }

    public String getMongoUri(String dbName) {
        return "mongodb://root:%s@%s:%d/%s?authSource=admin".formatted(
                getMongoPassword(),
                getMongoHost(),
                getMongoPort(),
                dbName);
    }

    // Redis

    public ContainerState getRedisContainer() {
        Optional<ContainerState> container = getContainerByServiceName(REDIS_SERVICE_NAME);
        if (container.isPresent()) {
            return container.get();
        }
        throw new IllegalStateException("Cannot find the redis container by the service name " + REDIS_SERVICE_NAME);
    }

    public String getRedisHost() {
        return getServiceHost(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
    }

    public int getRedisPort() {
        return getServicePort(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
    }

    public String getRedisUri() {
        return "redis://%s:%d".formatted(getRedisHost(), getRedisPort());
    }

    // turms-service

    public String getTurmsServiceHost() {
        return getServiceHost(TURMS_SERVICE_SERVICE_NAME, TURMS_SERVICE_ADMIN_PORT);
    }

    public int getTurmsServicePort() {
        return getServicePort(TURMS_SERVICE_SERVICE_NAME, TURMS_SERVICE_ADMIN_PORT);
    }

    // turms-gateway

    public String getTurmsGatewayAdminHost() {
        return getServiceHost(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_ADMIN_PORT);
    }

    public int getTurmsGatewayAdminPort() {
        return getServicePort(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_ADMIN_PORT);
    }

    public String getTurmsGatewayWsHost() {
        return getServiceHost(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_WS_PORT);
    }

    public int getTurmsGatewayWsPort() {
        return getServicePort(TURMS_GATEWAY_SERVICE_NAME, TURMS_GATEWAY_SERVICE_WS_PORT);
    }

    public String getTurmsGatewayWebSocketServerUri() {
        return "ws://%s:%d".formatted(getTurmsGatewayWsHost(), getTurmsGatewayWsPort());
    }
}