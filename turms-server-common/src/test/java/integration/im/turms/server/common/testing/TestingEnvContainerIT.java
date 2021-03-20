package integration.im.turms.server.common.testing;

import im.turms.server.common.testing.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class TestingEnvContainerIT extends BaseIntegrationTest {

    @Test
    void mongoContainerShouldBeRunning() {
        assertThat(ENV.getMongoContainer().isRunning()).isTrue();
    }

    @Test
    void redisContainerShouldBeRunning() {
        assertThat(ENV.getRedisContainer().isRunning()).isTrue();
    }

}