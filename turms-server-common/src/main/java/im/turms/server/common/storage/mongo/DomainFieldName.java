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

/**
 * @author James Chen
 */
public final class DomainFieldName {

    private DomainFieldName() {
    }

    public static final String ID = "_id";

    /**
     * @implNote Don't use "_" because:
     *           <p>
     *           1. MongoDB has use "_" for "_id";
     *           <p>
     *           2. To not mix with the default index name (The default index name uses "_" as
     *           separator);
     *           <p>
     *           Don't use "$" because MongoDB uses it for internal operators (e.g., "$set").
     */
    public static final String USER_DEFINED_ATTRIBUTE_PREFIX = "#";
    public static final int USER_DEFINED_ATTRIBUTE_PREFIX_LENGTH = 1;

}