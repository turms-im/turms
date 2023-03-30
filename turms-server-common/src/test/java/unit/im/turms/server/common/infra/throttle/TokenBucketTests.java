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

package unit.im.turms.server.common.infra.throttle;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.throttle.TokenBucket;
import im.turms.server.common.infra.throttle.TokenBucketContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class TokenBucketTests {

    @Test
    void shouldNotAcquire_ifNoTokensAndNoRefill() {
        TokenBucketContext context = new TokenBucketContext();
        context.setInitialTokens(0);
        TokenBucket bucket = new TokenBucket(context);
        boolean acquired = bucket.tryAcquire(System.currentTimeMillis());
        assertThat(acquired).isFalse();
    }

    @Test
    void shouldAcquire_ifTokensAreEnough() {
        TokenBucketContext context = new TokenBucketContext();
        context.setCapacity(1);
        context.setInitialTokens(1);
        TokenBucket bucket = new TokenBucket(context);
        boolean acquired = bucket.tryAcquire(System.currentTimeMillis());
        assertThat(acquired).isTrue();
    }

    @Test
    void shouldAcquire_afterRefill() {
        int tokensPerPeriod = 10;
        int refillIntervalMillis = 1;

        TokenBucketContext context = new TokenBucketContext();
        context.setTokensPerPeriod(tokensPerPeriod);
        context.setCapacity(tokensPerPeriod);
        context.setRefillIntervalMillis(refillIntervalMillis);
        TokenBucket bucket = new TokenBucket(context);
        long time = System.currentTimeMillis();
        drain(bucket, time);
        time += refillIntervalMillis;
        boolean acquired;
        for (int i = 0; i < tokensPerPeriod; i++) {
            acquired = bucket.tryAcquire(time);
            assertThat(acquired).isTrue();
        }
        acquired = bucket.tryAcquire(time);
        assertThat(acquired).isFalse();
    }

    private void drain(TokenBucket bucket, long time) {
        boolean acquired;
        do {
            acquired = bucket.tryAcquire(time);
        } while (acquired);
    }

}
