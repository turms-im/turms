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

package im.turms.server.common.storage.mongo.operation.option;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import im.turms.server.common.storage.mongo.BsonJsonUtil;

/**
 * @author James Chen
 */
public abstract class BaseBson implements Bson {

    /**
     * Use {@link BsonDocument} instead of {@link Document} because {@link Document} will be
     * converted to {@link BsonDocument} by mongo-java-driver finally, which is a huge waste of
     * system resources because both documents are heavy
     */
    final BsonDocument document;

    protected BaseBson(BsonDocument document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return BsonJsonUtil.toJson(document);
    }

    @Override
    public BsonDocument toBsonDocument() {
        return document;
    }

    @Override
    public <T> BsonDocument toBsonDocument(Class<T> documentClass, CodecRegistry codecRegistry) {
        return document;
    }

    public int size() {
        return document.size();
    }
}
