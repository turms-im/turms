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

package im.turms.gateway.manager;

import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.env.gateway.clientapi.RateLimitingProperties;

/**
 * @author James Chen
 */
public class RateLimitingManager {
    private final Node node;

    private int minClientRequestIntervalMillis;
    private int permits;
    private int perMillis;

    public RateLimitingManager(Node node) {
        this.node = node;
        updateProperties();
        node.addPropertiesChangeListener(turmsProperties -> updateProperties());
    }

    public boolean areRequestsTooFrequent(long now, UserSession session) {
        boolean areRequestsTooFrequent = areRequestsTooFrequentByRequestInterval(now, session);
        if (areRequestsTooFrequent) {
            return true;
        }
        return areRequestsTooFrequentByTimePeriod(now, session);
    }

    private boolean areRequestsTooFrequentByRequestInterval(long now, UserSession session) {
        if (minClientRequestIntervalMillis <= 0) {
            return false;
        }
        long lastRequestTimestamp = session.getLastRequestTimestampMillis();
        boolean areTooFrequent = now - lastRequestTimestamp < minClientRequestIntervalMillis;
        if (!areTooFrequent) {
            session.setLastRequestTimestampMillis(now);
        }
        return areTooFrequent;
    }

    private boolean areRequestsTooFrequentByTimePeriod(long now, UserSession session) {
        if (permits <= 0 || perMillis <= 0) {
            return false;
        }
        long firstRequestTimestamp = session.getFirstRequestTimestampMillis();
        long elapsedTime = now - firstRequestTimestamp;
        if (elapsedTime > perMillis) {
            session.setFirstRequestTimestampMillis(now);
            session.setCurrentProcessedRequestCount(1);
            return false;
        }
        int currentRequestCount = session.getCurrentProcessedRequestCount();
        if (currentRequestCount < permits) {
            session.setCurrentProcessedRequestCount(++currentRequestCount);
            return false;
        }
        return true;
    }

    private void updateProperties() {
        RateLimitingProperties rateLimiting = node.getSharedProperties().getGateway().getClientApi()
                .getRateLimiting();
        minClientRequestIntervalMillis = rateLimiting.getMinClientRequestIntervalMillis();
        permits = rateLimiting.getPermits();
        perMillis = rateLimiting.getPerSeconds() * 1000;
    }

}