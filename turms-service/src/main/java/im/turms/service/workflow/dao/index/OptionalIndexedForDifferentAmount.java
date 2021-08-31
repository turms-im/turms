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

package im.turms.service.workflow.dao.index;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Not indexed by default because the domain marked as OptionalIndexedForDifferentAmount
 * usually has only a few (or some) documents and has a low index selectivity.
 * <p>
 * No need to add an index unless your application really has a lot of records
 * and you are sure that it has a medium or high index selectivity.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface OptionalIndexedForDifferentAmount {
}
