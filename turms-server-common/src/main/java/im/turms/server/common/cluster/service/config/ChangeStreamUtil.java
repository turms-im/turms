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

package im.turms.server.common.cluster.service.config;

import org.springframework.data.mongodb.core.ChangeStreamEvent;

/**
 * @author James Chen
 */
public class ChangeStreamUtil {

    private ChangeStreamUtil() {
    }

    public static String getStringFromId(ChangeStreamEvent<?> event, String key) {
        return event.getRaw()
                .getDocumentKey()
                .get("_id")
                .asDocument()
                .get(key)
                .asString()
                .getValue();
    }

    public static long getIdAsLong(ChangeStreamEvent<?> event) {
        return event.getRaw()
                .getDocumentKey()
                .get("_id")
                .asInt64()
                .getValue();
    }

    public static String getIdAsString(ChangeStreamEvent<?> event) {
        return event.getRaw()
                .getDocumentKey()
                .get("_id")
                .asString()
                .getValue();
    }

}
