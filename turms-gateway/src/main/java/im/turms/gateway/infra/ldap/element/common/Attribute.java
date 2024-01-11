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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

import im.turms.gateway.infra.ldap.asn1.Asn1IdConst;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapElement;

/**
 * @author James Chen
 */
@Data
public class Attribute implements LdapElement {

    private final String type;
    private final List<String> values;

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public static Attribute decode(BerBuffer buffer) {
        buffer.skipTagAndLength();
        String type = buffer.readOctetString();

        int tag = buffer.readTag();
        if (tag != (Asn1IdConst.TAG_SET | Asn1IdConst.FORM_CONSTRUCTED)) {
            buffer.skipLengthAndValue();
            return new Attribute(type, Collections.emptyList());
        }
        int length = buffer.readLength();
        int end = buffer.readerIndex() + length;
        List<String> values = new ArrayList<>(8);
        do {
            // Note: Not all values are octet strings,
            // but we only use octet strings currently.
            values.add(buffer.readOctetString());
        } while (buffer.isReadableWithEnd(end));
        return new Attribute(type, values);
    }

}