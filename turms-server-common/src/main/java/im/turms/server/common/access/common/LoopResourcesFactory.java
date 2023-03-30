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

package im.turms.server.common.access.common;

import java.util.concurrent.ThreadFactory;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import reactor.netty.resources.LoopResources;

/**
 * @author James Chen
 */
public final class LoopResourcesFactory {

    private static final int DEFAULT_ACCEPTOR_THREADS = 1;
    private static final int DEFAULT_WORKER_THREADS = Math.max(Runtime.getRuntime()
            .availableProcessors(), 1);

    private LoopResourcesFactory() {
    }

    public static LoopResources createForServer(String prefix) {
        return new LoopResources() {
            @Override
            public EventLoopGroup onServerSelect(boolean useNative) {
                ThreadFactory threadFactory = new DefaultThreadFactory(
                        prefix
                                + "-acceptor",
                        false);
                return new NioEventLoopGroup(DEFAULT_ACCEPTOR_THREADS, threadFactory);
            }

            @Override
            public EventLoopGroup onServer(boolean useNative) {
                ThreadFactory threadFactory = new DefaultThreadFactory(
                        prefix
                                + "-worker",
                        false);
                return new NioEventLoopGroup(DEFAULT_WORKER_THREADS, threadFactory);
            }
        };
    }
}