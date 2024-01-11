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

package im.turms.gateway.infra.ldap.element.operation.bind;

import im.turms.gateway.infra.ldap.asn1.Asn1IdConst;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapTagConst;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.2.1">4.2.1. Processing
 *           of the Bind Request</a>
 */
public class BindRequest implements ProtocolOperation<BindRequest> {

    private static final int LDAP_VERSION3 = 0x03;

    private final String dn;
    private final String password;

    public BindRequest(String dn, String password) {
        this.dn = dn;
        this.password = password;
    }

    @Override
    public int estimateSize() {
        return dn.length() + password.length() + 16;
    }

    @Override
    public void writeTo(BerBuffer buffer) {
        buffer.beginSequence(LdapTagConst.BIND_REQUEST)
                .writeInteger(LDAP_VERSION3)
                .writeOctetString(dn)
                .writeOctetString(Asn1IdConst.TAG_CLASS_CONTEXT, password)
                .endSequence();
    }

}