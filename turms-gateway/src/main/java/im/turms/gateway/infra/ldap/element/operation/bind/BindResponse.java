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

import java.util.Collections;
import java.util.List;

import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.common.LdapResult;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.2.2">4.2.2. Bind
 *           Response</a>
 */
public class BindResponse extends LdapResult implements ProtocolOperation<BindResponse> {

    public static final BindResponse DECODER =
            new BindResponse(0, null, null, Collections.emptyList());

    public BindResponse(
            int resultCode,
            String matchedDn,
            String diagnosticMessage,
            List<String> referrals) {
        super(resultCode, matchedDn, diagnosticMessage, referrals);
    }

    @Override
    public BindResponse decode(BerBuffer buffer) {
        buffer.skipTagAndLength();
        LdapResult result = LdapResult.decodeResult(buffer);
        return new BindResponse(
                result.getResultCode(),
                result.getMatchedDn(),
                result.getDiagnosticMessage(),
                result.getReferrals());
    }
}