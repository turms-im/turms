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

package im.turms.gateway.infra.ldap.element.operation.modify;

import java.util.List;

import im.turms.gateway.infra.ldap.asn1.Asn1IdConst;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapTagConst;
import im.turms.gateway.infra.ldap.element.common.Attribute;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.6">4.6. Modify
 *           Operation</a>
 */
public class ModifyRequest implements ProtocolOperation<ModifyRequest> {

    private final String dn;
    private final List<ModifyOperationChange> changes;

    public ModifyRequest(String dn, List<ModifyOperationChange> changes) {
        this.dn = dn;
        this.changes = changes;
    }

    @Override
    public int estimateSize() {
        return dn.length() + changes.size() * 32;
    }

    @Override
    public void writeTo(BerBuffer buffer) {
        buffer.beginSequence(LdapTagConst.MODIFY_REQUEST)
                .writeOctetString(dn);

        buffer.beginSequence();
        for (ModifyOperationChange change : changes) {
            buffer.beginSequence();
            ModifyOperationType type = change.getType();
            buffer.writeEnumeration(type.getValue());

            Attribute attribute = change.getAttribute();
            buffer.beginSequence()
                    .writeOctetString(attribute.getType());

            buffer.beginSequence(Asn1IdConst.TAG_SEQUENCE | Asn1IdConst.FORM_CONSTRUCTED);

            attribute.getValues()
                    .forEach(buffer::writeOctetString);

            buffer.endSequence();
            buffer.endSequence();

            buffer.endSequence();
        }
        buffer.endSequence();
        buffer.endSequence();
    }

}