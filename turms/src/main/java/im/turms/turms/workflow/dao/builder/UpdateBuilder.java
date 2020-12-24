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

package im.turms.turms.workflow.dao.builder;

import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * @author James Chen
 */
public class UpdateBuilder {

    private final Update update = new Update();

    private UpdateBuilder() {
    }

    public static UpdateBuilder newBuilder() {
        return new UpdateBuilder();
    }

    public UpdateBuilder setIfNotNull(@NotNull String key, @Nullable Object value) {
        if (value != null) {
            if (value instanceof Collection) {
                if (!((Collection<?>) value).isEmpty()) {
                    update.set(key, value);
                }
            } else {
                update.set(key, value);
            }
        }
        return this;
    }

    public UpdateBuilder setOrUnsetDate(String key, Date date) {
        if (date != null) {
            if (date.getTime() > 0) {
                update.set(key, date);
            } else {
                update.unset(key);
            }
        }
        return this;
    }

    public Update build() {
        return update;
    }

}