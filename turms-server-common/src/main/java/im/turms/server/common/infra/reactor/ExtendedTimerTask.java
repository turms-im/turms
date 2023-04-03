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

package im.turms.server.common.infra.reactor;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * Use our efficient cancel implementation instead of
 * {@link HashedWheelTimer.HashedWheelTimeout#cancel}
 *
 * @author James Chen
 */
public class ExtendedTimerTask implements TimerTask {

    private final Runnable task;
    private volatile boolean cancelled;

    public ExtendedTimerTask(Runnable task) {
        this.task = task;
    }

    @Override
    public void run(Timeout t) throws Exception {
        if (!cancelled) {
            task.run();
        }
    }

    public void cancel() {
        cancelled = true;
    }

}