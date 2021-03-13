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

package im.turms.server.common.mongo;

import com.mongodb.reactivestreams.client.MongoCollection;
import im.turms.server.common.mongo.entity.MongoEntity;
import im.turms.server.common.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.mongo.operation.MongoOperationsSupport;
import im.turms.server.common.mongo.operation.TurmsMongoOperations;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.util.CollectorUtil;
import lombok.experimental.Delegate;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author James Chen
 */
public final class TurmsMongoClient implements MongoOperationsSupport {

    private final MongoContext context;
    @Delegate
    private final TurmsMongoOperations operations;

    public List<MongoEntity<?>> getRegisteredEntities() {
        return context.getEntities();
    }

    public static TurmsMongoClient of(TurmsMongoProperties properties) {
        return new TurmsMongoClient(properties);
    }


    private TurmsMongoClient(TurmsMongoProperties properties) {
        context = new MongoContext(properties.getUri());
        operations = new TurmsMongoOperations(context);
    }

    public void destroy() {
        context.destroy();
    }

    public List<MongoEntity<?>> registerEntitiesByOptions(MongoCollectionOptions... optionsList) {
        return registerEntitiesByOptions(Arrays.stream(optionsList)
                .collect(CollectorUtil.toList(optionsList.length)));
    }

    public List<MongoEntity<?>> registerEntitiesByOptions(Collection<MongoCollectionOptions> optionsList) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs = context.registerEntitiesByOptions(optionsList);
        return pairs.stream()
                .map(Pair::getFirst)
                .collect(CollectorUtil.toList(pairs.size()));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Class<?>... classes) {
        return registerEntitiesByClasses(Arrays.stream(classes)
                .collect(CollectorUtil.toList(classes.length)));
    }

    public List<MongoEntity<?>> registerEntitiesByClasses(Collection<Class<?>> classes) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs = context.registerEntitiesByClasses(classes);
        return pairs.stream()
                .map(Pair::getFirst)
                .collect(CollectorUtil.toList(pairs.size()));
    }

}