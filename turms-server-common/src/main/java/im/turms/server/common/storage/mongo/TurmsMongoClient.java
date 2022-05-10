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

import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.reactivestreams.client.MongoCollection;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.storage.mongo.entity.MongoEntity;
import im.turms.server.common.storage.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.storage.mongo.operation.MongoOperationsSupport;
import im.turms.server.common.storage.mongo.operation.TurmsMongoOperations;
import lombok.experimental.Delegate;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author James Chen
 */
public final class TurmsMongoClient implements MongoOperationsSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsMongoClient.class);

    private final MongoContext context;
    @Delegate
    private final TurmsMongoOperations operations;

    public List<MongoEntity<?>> getRegisteredEntities() {
        return context.getEntities();
    }

    public static Mono<TurmsMongoClient> of(TurmsMongoProperties properties) {
        Sinks.One<Void> connect = Sinks.one();
        TurmsMongoClient client = new TurmsMongoClient(properties, connect);
        return connect.asMono().thenReturn(client);
    }

    private TurmsMongoClient(TurmsMongoProperties properties, Sinks.One<Void> connect) {
        context = new MongoContext(properties.getUri(), descriptions -> {
            for (ServerDescription description : descriptions) {
                if (description.getState() == ServerConnectionState.CONNECTING) {
                    return;
                }
            }
            try {
                verifyServerVersion(descriptions);
                connect.tryEmitValue(null);
            } catch (Exception e) {
                Sinks.EmitResult result = connect.tryEmitError(e);
                if (result.isFailure()) {
                    LOGGER.fatal("Connected to a MongoDB server with a version less than 4.0, which cannot work well with turms servers");
                }
            }
        });
        operations = new TurmsMongoOperations(context);
    }

    public Mono<Void> destroy(long timeoutMillis) {
        return context.destroy(timeoutMillis);
    }

    public List<MongoEntity<?>> registerEntitiesByOptions(MongoCollectionOptions... optionsList) {
        return registerEntitiesByOptions(Arrays.stream(optionsList)
                .collect(CollectorUtil.toList(optionsList.length)));
    }

    public List<MongoEntity<?>> registerEntitiesByOptions(Collection<MongoCollectionOptions> optionsList) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs = context.registerEntitiesByOptions(optionsList);
        return pairs.stream()
                .map(Pair::first)
                .collect(CollectorUtil.toList(pairs.size()));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Class<?>... classes) {
        return registerEntitiesByClasses(Arrays.stream(classes)
                .collect(CollectorUtil.toList(classes.length)));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Collection<Class<?>> classes) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs = context.registerEntitiesByClasses(classes);
        return pairs.stream()
                .map(Pair::first)
                .collect(CollectorUtil.toList(pairs.size()));
    }

    private void verifyServerVersion(List<ServerDescription> descriptions) {
        for (ServerDescription description : descriptions) {
            if (description.getMaxWireVersion() < ServerVersionHelper.FOUR_DOT_ZERO_WIRE_VERSION) {
                throw new IllegalStateException("The version of MongoDB server should be at least 4.0. " +
                        "Note that Turms cannot work with Amazon DocumentDB. " +
                        "The description of the unsupported server: " + description.getShortDescription());
            }
        }
    }

}