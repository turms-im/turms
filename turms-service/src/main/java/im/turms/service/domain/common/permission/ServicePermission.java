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

package im.turms.service.domain.common.permission;

import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
public record ServicePermission(
        ResponseStatusCode code,
        String reason
) {

    public static final ServicePermission OK = new ServicePermission(ResponseStatusCode.OK, null);

    public static ServicePermission get(ResponseStatusCode code) {
        return new ServicePermission(code, null);
    }

    public static ServicePermission get(ResponseStatusCode code, String reason) {
        return new ServicePermission(code, reason);
    }

}