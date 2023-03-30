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

package im.turms.server.common.access.admin.throttle;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import im.turms.server.common.infra.property.env.common.adminapi.AdminApiRateLimitingProperties;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.throttle.TokenBucket;
import im.turms.server.common.infra.throttle.TokenBucketContext;

/**
 * @author James Chen
 */
public abstract class BaseAdminApiRateLimitingManager {

    private final Map<String, TokenBucket> ipToTokenBucket = new ConcurrentHashMap<>(32);
    private final TokenBucketContext tokenBucketContext;

    protected BaseAdminApiRateLimitingManager(
            TaskManager taskManager,
            AdminApiRateLimitingProperties properties) {
        tokenBucketContext = new TokenBucketContext(properties);
        taskManager.reschedule("expiredAdminApiAccessInfoCleaner",
                CronConst.EXPIRED_ADMIN_API_ACCESS_INFO_CLEANUP_CRON,
                () -> {
                    Iterator<TokenBucket> iterator = ipToTokenBucket.values()
                            .iterator();
                    long now = System.currentTimeMillis();
                    while (iterator.hasNext()) {
                        TokenBucket bucket = iterator.next();
                        bucket.refill(now);
                        // We assume idle sessions will have the max number of tokens "capacity"
                        // finally
                        if (bucket.getTokens() >= tokenBucketContext.getCapacity()) {
                            iterator.remove();
                        }
                    }
                });
    }

    public boolean tryAcquireTokenByIp(String ip) {
        TokenBucket bucket =
                ipToTokenBucket.computeIfAbsent(ip, key -> new TokenBucket(tokenBucketContext));
        return bucket.tryAcquire(System.currentTimeMillis());
    }

}