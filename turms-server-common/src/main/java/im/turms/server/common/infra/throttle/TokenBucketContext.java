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

import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.env.common.CommonRateLimitingProperties;

/**
 * @author James Chen
 */
@Data
@NoArgsConstructor
public class TokenBucketContext {
    int capacity;
    int tokensPerPeriod;
    int refillIntervalMillis;
    int initialTokens;

    public TokenBucketContext(CommonRateLimitingProperties properties) {
        updateRequestTokenBucket(properties);
    }

    public void updateRequestTokenBucket(CommonRateLimitingProperties properties) {
        capacity = properties.getCapacity();
        initialTokens = properties.getInitialTokens();
        tokensPerPeriod = properties.getTokensPerPeriod();
        refillIntervalMillis = properties.getRefillIntervalMillis();
    }

}