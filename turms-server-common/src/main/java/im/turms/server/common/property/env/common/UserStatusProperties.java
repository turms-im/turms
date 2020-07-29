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

package im.turms.server.common.property.env.common;

import im.turms.server.common.property.metadata.annotation.Description;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class UserStatusProperties {

    @Description("Whether to cache the user sessions status")
    private boolean cacheUserSessionsStatus = true;

    @Description("The maximum size of the cache of users' sessions status")
    @Min(-1)
    private int userSessionsStatusCacheMaxSize = -1;

    @Description("The life duration of each remote user's sessions status in the cache. " +
            "Note that the cache will make the presentation of users' sessions status inconsistent during the time")
    @Min(1)
    private int userSessionsStatusExpireAfter = 60;

}
