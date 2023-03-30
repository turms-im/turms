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

package unit.im.turms.server.common.infra.cluster.service.idgen;

import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.cluster.service.idgen.SnowflakeIdGenerator;

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
            assertThat(newId).as("ID should be greater than 0")
                    .isPositive();
            assertThat(newId).as("ID should increment")
                    .isGreaterThan(previousId);
            boolean newIdAlreadyExists = ids.add(newId);
            assertThat(newIdAlreadyExists).as("ID should not be duplicate")
                    .isTrue();
            previousId = newId;
        }
    }

    @Test
    void nextLargeGapId_shouldGeneratePositiveAndUniqueAndLargeGapId() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0, 0);
        int number = 100_000;
        LongHashSet ids = new LongHashSet(number);
        long previousId = -1;
        boolean isMonotonicallyIncreasing = true;
        for (int i = 0; i < number; i++) {
            long newId = generator.nextLargeGapId();
            assertThat(newId).as("ID should be greater than 0")
                    .isPositive();
            if (newId < previousId) {
                isMonotonicallyIncreasing = false;
            }
            boolean newIdAlreadyExists = ids.add(newId);
            assertThat(newIdAlreadyExists).as("ID should not be duplicate")
                    .isTrue();
            previousId = newId;
        }
        assertThat(isMonotonicallyIncreasing).as("ID should not be monotonically increasing")
                .isFalse();
    }

}