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

import im.turms.gateway.infra.ldap.LdapException;
import im.turms.gateway.infra.ldap.asn1.Asn1IdConst;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapTagConst;

/**
 * @author James Chen
 */
@Data
public class LdapResult {

    private final int resultCode;
    private final String matchedDn;
    private final String diagnosticMessage;
    private final List<String> referrals;

    public LdapResult(
            int resultCode,
            String matchedDn,
            String diagnosticMessage,
            List<String> referrals) {
        this.resultCode = resultCode;
        this.matchedDn = matchedDn;
        this.diagnosticMessage = diagnosticMessage;
        this.referrals = referrals;
    }

    protected static LdapResult decodeResult(BerBuffer buffer) {
        int status = buffer.readEnumeration();
        String matchedDn = buffer.readOctetString();
        String errorMessage = buffer.readOctetString();
        List<String> referrals;

        if (buffer.peekAndCheckTag(LdapTagConst.REFERRAL)) {
            buffer.skipTag();
            int remainingBytes = buffer.readLength();
            if (!buffer.isReadable(remainingBytes)) {
                throw new LdapException(ResultCode.PROTOCOL_ERROR, "Invalid LDAP response");
            }
            referrals = new ArrayList<>(8);
            do {
                if (buffer.readTag() != Asn1IdConst.TAG_OCTET_STRING) {
                    throw new LdapException(ResultCode.PROTOCOL_ERROR, "Invalid LDAP response");
                }
                int length = buffer.readLength();
                referrals.add(buffer.readOctetStringWithLength(length));
                remainingBytes -= length;
            } while (buffer.isReadable());
        } else {
            referrals = Collections.emptyList();
        }
        return new LdapResult(status, matchedDn, errorMessage, referrals);
    }

    public boolean isSuccess() {
        return resultCode == ResultCodeConst.SUCCESS;
    }
}