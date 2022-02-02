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

package im.turms.server.common.constant;

/**
 * @author James Chen
 */
public class BusinessConstant {

    private BusinessConstant() {
    }

    // We use wrapper types because they will be converted to wrapper types multiple times
    // if we use primitives because many methods use Object
    public static final Long RESERVED_ID = 0L;
    /**
     * Use the hard-coded account because it's immutable.
     */
    public static final String ROOT_ADMIN_ACCOUNT = "turms";
    public static final Long ADMIN_ROLE_ROOT_ID = RESERVED_ID;
    public static final Long ADMIN_REQUESTER_ID = RESERVED_ID;
    public static final Long ADMIN_REQUEST_ID = RESERVED_ID;
    public static final Long DEFAULT_USER_PERMISSION_GROUP_ID = RESERVED_ID;
    public static final Long DEFAULT_GROUP_TYPE_ID = RESERVED_ID;
    public static final String DEFAULT_GROUP_TYPE_NAME = "DEFAULT";
    public static final Integer DEFAULT_RELATIONSHIP_GROUP_INDEX = RESERVED_ID.intValue();

}
