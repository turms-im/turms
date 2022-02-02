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

package im.turms.server.common.access.http.throttle;

import im.turms.server.common.constant.CronConstant;
import im.turms.server.common.property.env.common.adminapi.AdminApiRateLimitingProperties;
import im.turms.server.common.task.TrivialTaskManager;
import im.turms.server.common.throttle.TokenBucket;
import im.turms.server.common.throttle.TokenBucketContext;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
public abstract class BaseAdminApiRateLimitingManager {

    private final Map<String, TokenBucket> ipTokenBucketMap = new ConcurrentHashMap<>(32);
    private final TokenBucketContext tokenBucketContext;

    protected BaseAdminApiRateLimitingManager(TrivialTaskManager taskManager, AdminApiRateLimitingProperties properties) {
        tokenBucketContext = new TokenBucketContext(properties);
        taskManager.reschedule("expiredAdminApiAccessInfoCleaner", CronConstant.EXPIRED_ADMIN_API_ACCESS_INFO_CLEANUP_CRON,
                () -> {
                    Iterator<Map.Entry<String, TokenBucket>> iterator = ipTokenBucketMap.entrySet().iterator();
                    long now = System.currentTimeMillis();
                    while (iterator.hasNext()) {
                        Map.Entry<String, TokenBucket> entry = iterator.next();
                        TokenBucket bucket = entry.getValue();
                        bucket.refill(now);
                        // We assume idle sessionMap will have the max number of tokens "capacity" finally
                        if (bucket.getTokens() == tokenBucketContext.getCapacity()) {
                            iterator.remove();
                        }
                    }
                });
    }

    public boolean tryAcquireTokenByIp(String ip) {
        TokenBucket bucket = ipTokenBucketMap.computeIfAbsent(ip, key -> new TokenBucket(tokenBucketContext));
        return bucket.tryAcquire(System.currentTimeMillis());
    }

}