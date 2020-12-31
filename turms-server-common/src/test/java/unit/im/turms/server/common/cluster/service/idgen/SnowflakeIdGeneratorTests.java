package unit.im.turms.server.common.cluster.service.idgen;

import im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator;
import im.turms.server.common.util.MapUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SnowflakeIdGeneratorTests {

    @Test
    void nextIncreasingId_shouldGeneratePositiveAndUniqueAndIncrementingId() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0, 0);
        int number = 100_000;
        Set<Long> ids = new HashSet<>(MapUtil.getCapability(number));
        long previousId = -1;
        for (int i = 0; i < number; i++) {
            long newId = generator.nextIncreasingId();
            assertThat("ID should be greater than 0", newId, greaterThan(0L));
            assertThat("ID should increment", newId, greaterThan(previousId));
            boolean added = ids.add(newId);
            assertTrue(added, "ID is duplicate");
            previousId = newId;
        }
    }

    @Test
    void nextRandomId_shouldGeneratePositiveAndUniqueAndRandomId() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0, 0);
        int number = 100_000;
        Set<Long> ids = new HashSet<>(MapUtil.getCapability(number));
        long previousId = -1;
        boolean isMonotonicallyIncreasing = true;
        for (int i = 0; i < number; i++) {
            long newId = generator.nextRandomId();
            assertThat("ID should be greater than 0", newId, greaterThan(0L));
            if (newId < previousId) {
                isMonotonicallyIncreasing = false;
            }
            boolean added = ids.add(newId);
            assertTrue(added, "ID is duplicate");
            System.out.println(newId);
            previousId = newId;
        }
        assertFalse(isMonotonicallyIncreasing, "ID should not be monotonically increasing");
    }

}