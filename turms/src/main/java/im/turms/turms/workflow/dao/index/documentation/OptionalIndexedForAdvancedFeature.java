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

package im.turms.turms.workflow.dao.index.documentation;

import org.springframework.data.mongodb.core.index.Indexed;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indexed by default because the index is used to support the widely used and advanced features of turms.
 * e.g.
 * 1. physically delete the data with a "deleted" flag,
 * <p>
 * 2. expiration date.
 * <p>
 * Remove the index if you don't need these features.
 */
@Indexed
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalIndexedForAdvancedFeature {
}
