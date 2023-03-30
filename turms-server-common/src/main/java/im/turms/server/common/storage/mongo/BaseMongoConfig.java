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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.connection.ClusterType;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.infra.time.DurationConst;

/**
 * @author James Chen
 */
public abstract class BaseMongoConfig {

    private final Map<String, TurmsMongoClient> uriToClient = new HashMap<>(8);

    protected BaseMongoConfig(TurmsApplicationContext applicationContext) {
        applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_MONGODB_CONNECTIONS,
                this::destroy);
    }

    public Mono<Void> destroy(long timeoutMillis) {
        Collection<TurmsMongoClient> clients = uriToClient.values();
        int size = clients.size();
        if (size == 0) {
            return Mono.empty();
        }
        List<Mono<Void>> monos = new ArrayList<>(size);
        for (TurmsMongoClient client : clients) {
            monos.add(client.destroy(timeoutMillis));
        }
        return Mono.whenDelayError(monos);
    }

    protected synchronized TurmsMongoClient getMongoClient(
            TurmsMongoProperties properties,
            String name,
            Set<ClusterType> requiredClusterTypes) {
        return uriToClient.compute(properties.getUri(), (key, mongoClient) -> {
            if (mongoClient == null) {
                try {
                    return TurmsMongoClient.of(properties, name, requiredClusterTypes)
                            .block(DurationConst.ONE_MINUTE);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create the mongo client", e);
                }
            }
            mongoClient.verifyClusterTypes(name, requiredClusterTypes);
            return mongoClient;
        });
    }

}
