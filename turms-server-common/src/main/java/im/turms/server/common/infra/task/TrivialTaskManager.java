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

package im.turms.server.common.infra.task;

import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Handle trivial tasks in a thread
 *
 * @author James Chen
 */
@Component
public class TrivialTaskManager {

    private final Map<String, ScheduledFuture<?>> scheduledTaskMap;

    private final TaskScheduler taskScheduler;

    public TrivialTaskManager() {
        scheduledTaskMap = new ConcurrentHashMap<>(32);
        NamedThreadFactory threadFactory = new NamedThreadFactory(ThreadNameConst.TRIVIAL_TASK_MANAGER, true);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(threadFactory);
        taskScheduler = new ConcurrentTaskScheduler(executor);
    }

    public void reschedule(String key, String cronExpression, Runnable runnable) {
        ScheduledFuture<?> task = taskScheduler.schedule(runnable, new CronTrigger(cronExpression));
        ScheduledFuture<?> previousTask = scheduledTaskMap.put(key, task);
        if (previousTask != null) {
            previousTask.cancel(false);
        }
    }

}
