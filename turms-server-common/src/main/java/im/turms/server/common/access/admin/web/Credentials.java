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

package im.turms.server.common.access.admin.web;

import im.turms.server.common.domain.admin.constant.AdminConst;

/**
 * @author James Chen
 */
public record Credentials(
        String account,
        String password
) {

    public static final Credentials EMPTY = new Credentials(null, null);
    public static final Credentials ROOT = new Credentials(AdminConst.ROOT_ADMIN_ACCOUNT, null);

}
