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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.mongodb.connection.ClusterType;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.reactivestreams.client.MongoCollection;
import lombok.experimental.Delegate;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.storage.mongo.entity.MongoEntity;
import im.turms.server.common.storage.mongo.exception.IncompatibleMongoException;
import im.turms.server.common.storage.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.storage.mongo.operation.MongoOperationsSupport;
import im.turms.server.common.storage.mongo.operation.TurmsMongoOperations;

/**
 * @author James Chen
 */
public final class TurmsMongoClient implements MongoOperationsSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsMongoClient.class);

    private final Set<String> names = UnifiedSet.newSet(8);
    private List<ServerDescription> descriptions;

    private final MongoContext context;
    @Delegate
    private final TurmsMongoOperations operations;

    public List<MongoEntity<?>> getRegisteredEntities() {
        return context.getEntities();
    }

    public static Mono<TurmsMongoClient> of(TurmsMongoProperties properties, String name) {
        return of(properties, name, Collections.emptySet());
    }

    public static Mono<TurmsMongoClient> of(
            TurmsMongoProperties properties,
            String name,
            Set<ClusterType> requiredClusterTypes) {
        Sinks.One<Void> connect = Sinks.one();
        TurmsMongoClient client =
                new TurmsMongoClient(properties, name, requiredClusterTypes, connect);
        return connect.asMono()
                .thenReturn(client);
    }

    private TurmsMongoClient(
            TurmsMongoProperties properties,
            String name,
            Set<ClusterType> requiredClusterTypes,
            Sinks.One<Void> connect) {
        names.add(name);
        context = new MongoContext(properties.getUri(), descriptions -> {
            for (ServerDescription description : descriptions) {
                if (description.getState() == ServerConnectionState.CONNECTING) {
                    return;
                }
            }
            this.descriptions = descriptions;
            try {
                verifyServers(descriptions, name, requiredClusterTypes);
                connect.tryEmitValue(null);
            } catch (Exception e) {
                Sinks.EmitResult result = connect.tryEmitError(e);
                if (result.isFailure()) {
                    LOGGER.fatal(e.getMessage());
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

    public List<MongoEntity<?>> registerEntitiesByOptions(
            Collection<MongoCollectionOptions> optionsList) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs =
                context.registerEntitiesByOptions(optionsList);
        return pairs.stream()
                .map(Pair::first)
                .collect(CollectorUtil.toList(pairs.size()));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Class<?>... classes) {
        return registerEntitiesByClasses(Arrays.stream(classes)
                .collect(CollectorUtil.toList(classes.length)));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Collection<Class<?>> classes) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs =
                context.registerEntitiesByClasses(classes);
        return pairs.stream()
                .map(Pair::first)
                .collect(CollectorUtil.toList(pairs.size()));
    }

    public void verifyClusterTypes(String name, Set<ClusterType> requiredClusterTypes) {
        if (descriptions == null) {
            throw new IllegalStateException(
                    "Verification can only work after the mongo client has been initialized");
        }
        names.add(name);
        if (requiredClusterTypes.isEmpty()) {
            return;
        }
        for (ServerDescription description : descriptions) {
            if (!requiredClusterTypes.contains(description.getClusterType())) {
                throw new IncompatibleMongoException(
                        "The cluster types for the mongo clients "
                                + names
                                + " must be one of the types: "
                                + requiredClusterTypes);
            }
        }
    }

    private void verifyServers(
            List<ServerDescription> descriptions,
            String name,
            Set<ClusterType> requiredClusterTypes) {
        for (ServerDescription description : descriptions) {
            if (description.getMaxWireVersion() < ServerVersionHelper.FOUR_DOT_TWO_WIRE_VERSION) {
                throw new IncompatibleMongoException(
                        "The version of MongoDB server should be at least 4.2. "
                                + "Note that Turms cannot work with Amazon DocumentDB. "
                                + "The description of the unsupported server: "
                                + description.getShortDescription());
            }
            if (!requiredClusterTypes.isEmpty()
                    && !requiredClusterTypes.contains(description.getClusterType())) {
                throw new IncompatibleMongoException(
                        "The cluster types for the mongo client \""
                                + name
                                + "\" must be one of the types: "
                                + requiredClusterTypes);
            }
        }
    }

}