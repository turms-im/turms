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

package im.turms.gateway.infra.ldap.element.operation.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

import im.turms.gateway.infra.ldap.LdapException;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.common.Attribute;
import im.turms.gateway.infra.ldap.element.common.LdapResult;
import im.turms.gateway.infra.ldap.element.common.ResultCode;
import im.turms.gateway.infra.ldap.element.common.control.Control;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

import static im.turms.gateway.infra.ldap.element.LdapTagConst.SEARCH_RESULT_DONE;
import static im.turms.gateway.infra.ldap.element.LdapTagConst.SEARCH_RESULT_ENTRY;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.5.2">4.5.2. Search
 *           Result</a>
 */
public class SearchResult extends LdapResult implements ProtocolOperation<SearchResult> {

    public static final SearchResult DECODER =
            new SearchResult(0, null, null, Collections.emptyList(), Collections.emptyList());

    @Getter
    private final List<SearchResultEntry> entries;

    public SearchResult(
            int resultCode,
            String matchedDn,
            String diagnosticMessage,
            List<String> referrals,
            List<SearchResultEntry> entries) {
        super(resultCode, matchedDn, diagnosticMessage, referrals);
        this.entries = entries;
    }

    @Override
    public SearchResult decode(BerBuffer buffer) {
        byte tag = buffer.readTag();
        buffer.skipLength();
        List<Attribute> attributes;
        String objectName;
        SearchResultEntry entry;
        switch (tag) {
            case SEARCH_RESULT_ENTRY -> {
                List<SearchResultEntry> resultEntries = new ArrayList<>(8);
                attributes = new ArrayList<>(16);
                objectName = buffer.readOctetString();

                buffer.skipTag();
                int length = buffer.readLength();
                int end = buffer.readerIndex() + length;
                while (buffer.isReadableWithEnd(end)) {
                    attributes.add(Attribute.decode(buffer));
                }

                List<Control> controls = Control.decode(buffer);
                entry = new SearchResultEntry(objectName, attributes, controls);
                resultEntries.add(entry);
                return new SearchResult(0, null, null, null, resultEntries) {
                    @Override
                    public boolean isComplete() {
                        return false;
                    }
                };
            }
            case SEARCH_RESULT_DONE -> {
                if (entries == null) {
                    throw new LdapException(
                            ResultCode.PROTOCOL_ERROR,
                            "The search result is not complete yet");
                }
                LdapResult result = LdapResult.decodeResult(buffer);
                return new SearchResult(
                        result.getResultCode(),
                        result.getMatchedDn(),
                        result.getDiagnosticMessage(),
                        result.getReferrals(),
                        entries);
            }
            default -> throw new LdapException(
                    ResultCode.PROTOCOL_ERROR,
                    "Unexpected tag for the search result: "
                            + tag);
        }
    }

}