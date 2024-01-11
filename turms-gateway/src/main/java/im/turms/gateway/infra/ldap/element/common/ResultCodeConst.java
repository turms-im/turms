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

package im.turms.gateway.infra.ldap.element.common;

import im.turms.gateway.infra.ldap.element.LdapElement;

/**
 * @author James Chen
 */
public final class ResultCodeConst implements LdapElement {
    public static final int SUCCESS = 0;
    public static final int PROTOCOL_ERROR = 2;
    public static final int INVALID_ATTRIBUTE_SYNTAX = 21;
    public static final int INVALID_CREDENTIALS = 49;
    public static final int FILTER_ERROR = 87;

    private ResultCodeConst() {
    }

}