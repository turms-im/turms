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

package im.turms.gateway.access.common;

import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.lang.ByteArrayWrapper;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.property.env.gateway.clientapi.ClientApiProperties;
import im.turms.server.common.throttle.TokenBucket;
import im.turms.server.common.throttle.TokenBucketContext;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Component
public class IpRequestThrottler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpRequestThrottler.class);

    private static final long IDLE_ENTRY_TTL = 30 * 60 * 1000;
    private static final long INTERVAL_TO_CHECK = 30 * 60 * 1000;
    private static final int BATCH_SIZE = 10_000;

    /**
     * Share the context with all instances of token buckets
     * so that we can apply new properties easily
     */
    private final TokenBucketContext requestTokenBucketContext;
    /**
     * We don't add a skip list of access time for a fast iterate
     * because it requires inserting a new item to update the access time
     * for each client request and needs to store a lot of items.
     * So just using and iterating ConcurrentHashMap is enough
     */
    private final Map<ByteArrayWrapper, TokenBucket> ipRequestTokenBucketMap = new ConcurrentHashMap<>(256);

    public IpRequestThrottler(Node node, SessionService sessionService) {
        sessionService.addOnSessionClosedListeners(session -> {
            // Try to remove the buckets with enough tokens
            // because most of the clients won't log in again once them have gone offline,
            // so it's a good time to remove unused buckets
            ipRequestTokenBucketMap.computeIfPresent(session.getIp(), (key, bucket) ->
                    bucket.isTokensMoreThanOrEqualsToInitialTokens() ? null : bucket);
        });

        ClientApiProperties clientApiProperties = node.getSharedProperties().getGateway().getClientApi();
        requestTokenBucketContext = new TokenBucketContext(clientApiProperties.getRateLimiting());

        node.addPropertiesChangeListener(newProperties -> requestTokenBucketContext.updateRequestTokenBucket(clientApiProperties.getRateLimiting()));

        new DefaultThreadFactory("turms-ip-request-token-bucket-cleaner", true)
                .newThread(() -> {
                    Thread thread = Thread.currentThread();
                    while (!thread.isInterrupted()) {
                        try {
                            removeExpiredRequestTokenBuckets();
                        } catch (InterruptedException e) {
                            return;
                        } catch (Exception e) {
                            LOGGER.error("Failed to remove expired request token buckets", e);
                        }
                        try {
                            Thread.sleep(INTERVAL_TO_CHECK);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                })
                .start();
    }

    private void removeExpiredRequestTokenBuckets() throws InterruptedException {
        Iterator<Map.Entry<ByteArrayWrapper, TokenBucket>> iterator = ipRequestTokenBucketMap.entrySet().iterator();
        int processed = 0;
        long startTime = System.currentTimeMillis();
        while (iterator.hasNext()) {
            Map.Entry<ByteArrayWrapper, TokenBucket> entry = iterator.next();
            TokenBucket bucket = entry.getValue();
            long lastAccessTime = bucket.getLastRefillTime();
            if ((startTime - lastAccessTime) > IDLE_ENTRY_TTL && bucket.isTokensMoreThanOrEqualsToInitialTokens()) {
                iterator.remove();
            }
            processed++;
            // To avoid hogging the CPU for a long time
            // and cause the server cannot serve for users
            if (processed >= BATCH_SIZE) {
                processed = 0;
                if (System.currentTimeMillis() - startTime > 1000) {
                    Thread.sleep(1000);
                }
            }
        }
    }

    public boolean tryAcquireToken(ByteArrayWrapper ip, long timestamp) {
        TokenBucket bucket = ipRequestTokenBucketMap.computeIfAbsent(ip, key -> new TokenBucket(requestTokenBucketContext));
        return bucket.tryAcquire(timestamp);
    }

}