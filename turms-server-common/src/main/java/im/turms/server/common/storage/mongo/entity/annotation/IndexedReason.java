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

package im.turms.server.common.storage.mongo.entity.annotation;

/**
 * The annotation has no effect and is just used to tell why a field may need to be indexed or not
 *
 * @author James Chen
 */
public enum IndexedReason {
    NO,
    /**
     * The field marked as EXPIRABLE is used to distinguish whether documents are expired or not
     * e.g. expiration date and deletion date.
     */
    EXPIRABLE,
    /**
     * The field marked as EXTENDED_FEATURE is used to implement extended IM features. Note that if
     * your application really has some extended features that depend on the index on these fields,
     * you may need to create a new auxiliary collection to use the targeted queries in sharded
     * collections.
     */
    EXTENDED_FEATURE,
    /**
     * The field marked as SMALL_COLLECTION usually belongs to a small collection. No need to add an
     * index on it unless you are sure your application has a lot of documents.
     */
    SMALL_COLLECTION,
//    STATISTICS
}
