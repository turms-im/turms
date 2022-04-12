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

package im.turms.server.common.storage.mongo;

import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public abstract class BaseMongoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMongoConfig.class);

    private final Map<String, TurmsMongoClient> uriToClient = new HashMap<>(8);

    @PreDestroy
    public void destroy() {
        for (TurmsMongoClient client : uriToClient.values()) {
            try {
                client.destroy();
            } catch (Exception ignored) {
                LOGGER.error("Failed to destroy a mongo client");
            }
        }
    }

    protected synchronized TurmsMongoClient getMongoClient(TurmsMongoProperties properties) {
        return uriToClient.computeIfAbsent(properties.getUri(), key -> {
            try {
                return TurmsMongoClient.of(properties)
                        .block(Duration.ofMinutes(1));
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create the mongo client", e);
            }
        });
    }

}
