package im.turms.server.common.testing;

import lombok.extern.log4j.Log4j2;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.testcontainers.utility.DockerImageName;
import org.yaml.snakeyaml.Yaml;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author James Chen
 */
@Log4j2
public class TestingEnvContainer extends DockerComposeContainer<TestingEnvContainer> implements Closeable {

    private static final String MONGO_SERVICE_NAME = "mongodb-router_1";
    private static final int MONGO_SERVICE_PORT = 27017;
    private static final String PASSWORD = "turms";

    private static final String REDIS_SERVICE_NAME = "redis_1";
    private static final int REDIS_SERVICE_PORT = 6379;

    private static final String TURMS_SERVICE_SERVICE_NAME = "turms-service_1";
    private static final int TURMS_SERVICE_ADMIN_PORT = 8510;

    private static final String TURMS_GATEWAY_SERVICE_NAME = "turms-gateway_1";
    private static final int TURMS_GATEWAY_SERVICE_ADMIN_PORT = 9510;
    private static final int TURMS_GATEWAY_SERVICE_WS_PORT = 10510;

    static {
        try {
            // FIXME: Remove the hacky code once DockerComposeContainer supports
            //  using a custom version of docker-compose
            // https://github.com/testcontainers/testcontainers-java/issues/4164
            Class<?> compose = Class.forName("org.testcontainers.containers.ContainerisedDockerCompose");
            Field field = compose.getDeclaredField("DEFAULT_IMAGE_NAME");
            field.setAccessible(true);
            VarHandle modifiersHandle = MethodHandles
                    .privateLookupIn(Field.class, MethodHandles.lookup())
                    .findVarHandle(Field.class, "modifiers", int.class);
            modifiersHandle.set(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, DockerImageName.parse("docker/compose:1.29.1"));
        } catch (Exception e) {
            log.error("Cannot change the version of docker-compose", e);
        }
    }

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
                String jvmOptions = parseJvmOptions(options.getTurmsGatewayJvmOptions());
                if (!jvmOptions.isBlank()) {
                    Map<String, Object> turms = (Map<String, Object>) services.get("turms-gateway");
                    String turmsGatewayJvmOpts = (String) turms.get("TURMS_GATEWAY_JVM_OPTS");
                    turmsGatewayJvmOpts += jvmOptions;
                    turms.put("TURMS_GATEWAY_JVM_OPTS", turmsGatewayJvmOpts);
                }
            } else {
                services.remove("turms-gateway");
            }
            if (options.isSetupTurmsService()) {
                String jvmOptions = parseJvmOptions(options.getTurmsServiceJvmOptions());
                if (!jvmOptions.isBlank()) {
                    Map<String, Object> turms = (Map<String, Object>) services.get("turms-service");
                    String turmsServiceJvmOpts = (String) turms.get("TURMS_SERVICE_JVM_OPTS");
                    turmsServiceJvmOpts += jvmOptions;
                    turms.put("TURMS_SERVICE_JVM_OPTS", turmsServiceJvmOpts);
                }
            } else {
                services.remove("turms-service");
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