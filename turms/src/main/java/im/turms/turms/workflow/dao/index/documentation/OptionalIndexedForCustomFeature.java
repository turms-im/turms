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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Not indexed by default because if your application really has some features that
 * needs to be implemented by these queries that uses the fields marked as OptionalIndexedForCustomFeature
 * (in most cases these queries should be able to eliminate and should be eliminated
 * especially in the sharded collections), you may need to index these fields.
 * <p>
 * By the way, to use the targeted queries in sharded collections, you may need to create a new auxiliary collection.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface OptionalIndexedForCustomFeature {
}
