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

package im.turms.server.common.infra.throttle;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import lombok.Getter;

import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * @author James Chen
 */
@ThreadSafe
public class TokenBucket {

    private static final AtomicIntegerFieldUpdater<TokenBucket> TOKENS_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(TokenBucket.class, "tokens");

    private final TokenBucketContext context;

    @Getter
    private volatile int tokens;
    @Getter
    private volatile long lastRefillTime;

    /**
     * @implNote We don't validate properties here, and it should be validated when the properties
     *           are updated
     */
    public TokenBucket(TokenBucketContext context) {
        this.context = context;
        tokens = context.initialTokens;
        lastRefillTime = System.currentTimeMillis();
    }

    public boolean tryAcquire(long time) {
        int tokenCount = tokens;
        if (tokenCount > 0) {
            if (TOKENS_UPDATER.compareAndSet(this, tokenCount, tokenCount - 1)) {
                return true;
            }
            return tryAcquire(time);
        }
        int refillInterval = context.refillIntervalMillis;
        if (refillInterval <= 0) {
            return false;
        }
        int periods = (int) (time - lastRefillTime) / refillInterval;
        if (periods <= 0) {
            return false;
        }
        // Try to refill.
        // We expect tokensPerPeriod is always greater than 0,
        // so tokenCount can always be greater than or equal 0.
        int capacity = context.capacity;
        tokenCount = MathUtil.multiply(periods, context.tokensPerPeriod, capacity) - 1;
        if (tokenCount > capacity) {
            tokenCount = capacity - 1;
        }
        if (TOKENS_UPDATER.compareAndSet(this, 0, tokenCount)) {
            lastRefillTime = time;
            return true;
        }
        return tryAcquire(time);
    }

    public void refill(long time) {
        int refillInterval = context.refillIntervalMillis;
        if (refillInterval <= 0) {
            return;
        }
        int periods = (int) (time - lastRefillTime) / refillInterval;
        if (periods <= 0) {
            return;
        }
        int tokenCount = tokens;
        int capacity = context.capacity;
        int newTokenCount = MathUtil.add(tokenCount,
                MathUtil.multiply(periods, context.tokensPerPeriod, capacity),
                capacity);
        if (newTokenCount > capacity) {
            newTokenCount = capacity;
        }
        if (TOKENS_UPDATER.compareAndSet(this, tokenCount, newTokenCount)) {
            lastRefillTime = time;
        } else {
            refill(time);
        }
    }

    public boolean isTokensMoreThanOrEqualsToInitialTokens() {
        return tokens >= context.initialTokens;
    }

}