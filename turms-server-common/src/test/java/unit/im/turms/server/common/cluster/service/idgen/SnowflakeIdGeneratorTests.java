package unit.im.turms.server.common.cluster.service.idgen;

import im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnowflakeIdGeneratorTests {

    @Test
    void nextIncreasingId_shouldGeneratePositiveAndUniqueAndIncrementingId() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0, 0);
        int number = 100_000;
        LongHashSet ids = new LongHashSet(number);
        long previousId = -1;
        for (int i = 0; i < number; i++) {
            long newId = generator.nextIncreasingId();
            assertThat(newId)
                    .as("ID should be greater than 0")
                    .isPositive();
            assertThat(newId)
                    .as("ID should increment")
                    .isGreaterThan(previousId);
            boolean newIdAlreadyExists = ids.add(newId);
            assertThat(newIdAlreadyExists)
                    .as("ID should not be duplicate")
                    .isTrue();
            previousId = newId;
        }
    }

    @Test
    void nextRandomId_shouldGeneratePositiveAndUniqueAndRandomId() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0, 0);
        int number = 100_000;
        LongHashSet ids = new LongHashSet(number);
        long previousId = -1;
        boolean isMonotonicallyIncreasing = true;
        for (int i = 0; i < number; i++) {
            long newId = generator.nextRandomId();
            assertThat(newId)
                    .as("ID should be greater than 0")
                    .isPositive();
            if (newId < previousId) {
                isMonotonicallyIncreasing = false;
            }
            boolean newIdAlreadyExists = ids.add(newId);
            assertThat(newIdAlreadyExists)
                    .as("ID should not be duplicate")
                    .isTrue();
            previousId = newId;
        }
        assertThat(isMonotonicallyIncreasing)
                .as("ID should not be monotonically increasing")
                .isFalse();
    }

}