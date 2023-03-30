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

package unit.im.turms.server.common.infra.memory;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.memory.ByteBufferUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class ByteBufferUtilTests {

    @Test
    void freeDirectBuffer() {
        BufferPoolMXBean pool = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class)
                .stream()
                .filter(bean -> "direct".equals(bean.getName()))
                .findFirst()
                .get();
        long memoryUsed = pool.getMemoryUsed();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1);
        assertThat(pool.getMemoryUsed()).isEqualTo(memoryUsed + 1);

        ByteBufferUtil.freeDirectBuffer(buffer);
        assertThat(pool.getMemoryUsed()).isEqualTo(memoryUsed);
    }

}
