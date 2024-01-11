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

package im.turms.gateway.infra.ldap.element;

import static im.turms.gateway.infra.ldap.asn1.Asn1IdConst.FORM_CONSTRUCTED;
import static im.turms.gateway.infra.ldap.asn1.Asn1IdConst.TAG_CLASS_APPLICATION;
import static im.turms.gateway.infra.ldap.asn1.Asn1IdConst.TAG_CLASS_CONTEXT;

/**
 * @author James Chen
 */
public final class LdapTagConst {

    public static final int CONTROLS = TAG_CLASS_CONTEXT | FORM_CONSTRUCTED;
    public static final int REFERRAL = 3 | TAG_CLASS_CONTEXT | FORM_CONSTRUCTED;

    public static final int BIND_REQUEST = TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;
    public static final int BIND_RESPONSE = 1 | TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;
    public static final int SEARCH_REQUEST = 3 | TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;
    public static final int SEARCH_RESULT_ENTRY = 4 | TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;
    public static final int SEARCH_RESULT_DONE = 5 | TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;
    public static final int MODIFY_REQUEST = 6 | TAG_CLASS_APPLICATION | FORM_CONSTRUCTED;

    private LdapTagConst() {
    }

}