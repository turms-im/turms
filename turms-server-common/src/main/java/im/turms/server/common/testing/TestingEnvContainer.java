package im.turms.server.common.testing;

import lombok.extern.log4j.Log4j2;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.util.Optional;

/**
 * @author James Chen
 */
@Log4j2
public class TestingEnvContainer extends DockerComposeContainer {

    private static final String MONGO_SERVICE_NAME = "mongodb-router_1";
    private static final int MONGO_SERVICE_PORT = 27017;

    private static final String REDIS_SERVICE_NAME = "redis_1";
    private static final int REDIS_SERVICE_PORT = 6379;

    public TestingEnvContainer() {
        super(new File(TestingEnvContainer.class.getClassLoader().getResource("docker-compose.test.yml").getFile()));
        withExposedService(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
        withExposedService(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
        // FIXME: testcontainers emits the actual STDIN string as the STDERR string
        withLogConsumer(MONGO_SERVICE_NAME, new ServiceLogConsumer(MONGO_SERVICE_NAME));
        withLogConsumer(REDIS_SERVICE_NAME, new ServiceLogConsumer(REDIS_SERVICE_NAME));
    }

    public ContainerState getMongoContainer() {
        Optional<ContainerState> container = getContainerByServiceName(MONGO_SERVICE_NAME);
        if (container.isPresent()) {
            return container.get();
        }
        throw new IllegalStateException("Cannot find the mongo container by the service name " + MONGO_SERVICE_NAME);
    }

    public ContainerState getRedisContainer() {
        Optional<ContainerState> container = getContainerByServiceName(REDIS_SERVICE_NAME);
        if (container.isPresent()) {
            return container.get();
        }
        throw new IllegalStateException("Cannot find the redis container by the service name " + REDIS_SERVICE_NAME);
    }

    public String getMongoHost() {
        return getServiceHost(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
    }

    public int getMongoPort() {
        return getServicePort(MONGO_SERVICE_NAME, MONGO_SERVICE_PORT);
    }

    public String getRedisHost() {
        return getServiceHost(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
    }

    public int getRedisPort() {
        return getServicePort(REDIS_SERVICE_NAME, REDIS_SERVICE_PORT);
    }

}