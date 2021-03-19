package im.turms.server.common.testing;

import org.testcontainers.junit.jupiter.Container;

import java.time.Duration;

/**
 * @author James Chen
 */
public abstract class BaseIntegrationTest {

    protected static final Duration DEFAULT_IO_TIMEOUT = Duration.ofSeconds(15);

    @Container
    protected static final TestingEnvContainer ENV = new TestingEnvContainer();

}