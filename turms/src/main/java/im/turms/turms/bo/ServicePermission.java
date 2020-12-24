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

package im.turms.turms.bo;

import im.turms.server.common.constant.TurmsStatusCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServicePermission {

    public static final ServicePermission OK = new ServicePermission(TurmsStatusCode.OK, null);

    private final TurmsStatusCode code;
    private final String reason;

    public static ServicePermission get(TurmsStatusCode code) {
        return new ServicePermission(code, null);
    }

    public static ServicePermission get(TurmsStatusCode code, String reason) {
        return new ServicePermission(code, reason);
    }

}