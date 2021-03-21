package im.turms.server.common.testing;

import lombok.extern.log4j.Log4j2;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author James Chen
 */
@Log4j2
public class TestingEnvContainer extends DockerComposeContainer<TestingEnvContainer> {

    private static final String MONGO_SERVICE_NAME = "mongodb-router_1";
    private static final int MONGO_SERVICE_PORT = 27017;
    private static final String PASSWORD = "turms";

    private static final String REDIS_SERVICE_NAME = "redis_1";
    private static final int REDIS_SERVICE_PORT = 6379;

    public TestingEnvContainer() {
        super(getComposeFile());
        withExposedService(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
        withExposedService(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
        // FIXME: testcontainers emits the actual STDOUT string as the STDERR string
        withLogConsumer(MONGO_SERVICE_NAME, new ServiceLogConsumer(MONGO_SERVICE_NAME));
        withLogConsumer(REDIS_SERVICE_NAME, new ServiceLogConsumer(REDIS_SERVICE_NAME));
        // Wait for the shard server to join the cluster, or it will throw "No Shards Found" error
        waitingFor(MONGO_SERVICE_NAME, new AbstractWaitStrategy() {
            @Override
            protected void waitUntilReady() {
                try {
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    log.error("Failed to wait", e);
                }
            }
        });
    }

    /**
     * Fixme: Return InputStream instead of File once DockerComposeContainer supports
     * (https://github.com/testcontainers/testcontainers-java/issues/3431)
     */
    private static File getComposeFile() {
        String name = "docker-compose.test.yml";
        InputStream resource = TestingEnvContainer.class.getClassLoader().getResourceAsStream(name);
        if (resource == null) {
            throw new IllegalStateException("Cannot find the resource " + name);
        }
        try {
            File tempFile = File.createTempFile("docker-compose", "yml");
            FileUtils.copyInputStreamToFile(resource, tempFile);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return String.format("mongodb://root:%s@%s:%d/%s?authSource=admin",
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

}