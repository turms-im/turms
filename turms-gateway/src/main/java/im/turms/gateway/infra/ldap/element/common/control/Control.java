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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

import im.turms.gateway.infra.ldap.asn1.Asn1IdConst;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapElement;
import im.turms.gateway.infra.ldap.element.LdapTagConst;

/**
 * @author James Chen
 */
@Data
public class Control implements LdapElement {
    protected final String oid;
    protected final boolean criticality;

    public static List<Control> decode(BerBuffer buffer) {
        if (!buffer.isReadable() || !buffer.peekAndCheckTag(LdapTagConst.CONTROLS)) {
            return Collections.emptyList();
        }
        List<Control> controls = new ArrayList<>(4);
        String oid;
        boolean criticality = false;

        buffer.readTag();
        int length = buffer.readLength();

        int end = buffer.readerIndex() + length;
        while (buffer.isReadableWithEnd(end)) {

            buffer.skipTagAndLength();

            oid = buffer.readOctetString();

            if (buffer.isReadable()) {
                if (buffer.peekAndCheckTag(Asn1IdConst.TAG_BOOLEAN)) {
                    criticality = buffer.readBoolean();
                    if (buffer.isReadable()
                            && buffer.peekAndCheckTag(Asn1IdConst.TAG_OCTET_STRING)) {
                        // We don't need the value,
                        // so we just skip it.
                        buffer.skipTagAndLengthAndValue();
                    }
                } else if (buffer.peekAndCheckTag(Asn1IdConst.TAG_OCTET_STRING)) {
                    // We don't need the value,
                    // so we just skip it.
                    buffer.skipTagAndLengthAndValue();
                }
            }
            if (oid != null) {
                controls.add(new Control(oid, criticality));
            }
        }
        return controls;
    }
}