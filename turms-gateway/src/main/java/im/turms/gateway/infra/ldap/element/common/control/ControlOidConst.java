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

package im.turms.gateway.infra.ldap.element.common.control;

/**
 * @author James Chen
 */
public final class ControlOidConst {

    public static final String FAST_BIND = "1.2.840.113556.1.4.1781";
    public static final String MANAGE_DSA_IT = "2.16.840.1.113730.3.4.2";

    private ControlOidConst() {
    }
}