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

package unit.im.turms.server.common.access.common;

import java.util.concurrent.ExecutionException;

import io.netty.channel.EventLoopGroup;
import org.junit.jupiter.api.Test;
import reactor.netty.resources.LoopResources;

import im.turms.server.common.access.common.LoopResourcesFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class LoopResourcesFactoryTests {

    @Test
    void createForServer_threadShouldStartsWithPrefix()
            throws ExecutionException, InterruptedException {
        String prefix = "my-prefix-for-test";
        LoopResources resources = LoopResourcesFactory.createForServer(prefix);
        EventLoopGroup eventLoopGroup = resources.onServer(false);
        String threadName = eventLoopGroup.submit(() -> Thread.currentThread()
                .getName())
                .get();
        assertThat(threadName).startsWith(prefix);
    }

}
