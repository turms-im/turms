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

import java.util.List;

import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapTagConst;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.5.1">4.5.1. Search
 *           Request</a>
 */
public class SearchRequest implements ProtocolOperation<SearchRequest> {

    public static final List<String> ALL_USER_ATTRIBUTES = List.of("*");
    public static final List<String> ALL_OPERATIONAL_ATTRIBUTES = List.of("+");
    public static final List<String> NO_ATTRIBUTES = List.of("1.1");

    private final String baseDn;
    private final int scope;
    private final int derefAliases;
    private final int sizeLimit;
    private final int timeLimit;
    private final boolean typesOnly;
    private final List<String> attributes;
    private final String filter;

    public SearchRequest(
            String baseDn,
            Scope scope,
            DerefAliases derefAliases,
            int sizeLimit,
            int timeLimit,
            boolean typesOnly,
            List<String> attributes,
            String filter) {
        this.baseDn = baseDn;
        this.scope = scope.getValue();
        this.derefAliases = derefAliases.getValue();
        this.sizeLimit = sizeLimit;
        this.timeLimit = timeLimit;
        this.typesOnly = typesOnly;
        this.attributes = attributes;
        this.filter = filter;
    }

    @Override
    public int estimateSize() {
        return 128;
    }

    @Override
    public void writeTo(BerBuffer buffer) {
        buffer.beginSequence(LdapTagConst.SEARCH_REQUEST)
                .writeOctetString(baseDn)
                .writeEnumeration(scope)
                .writeEnumeration(derefAliases)
                .writeInteger(sizeLimit)
                .writeInteger(timeLimit)
                .writeBoolean(typesOnly);

        Filter.write(buffer, filter);

        buffer.beginSequence()
                .writeOctetStrings(attributes)
                .endSequence();

        buffer.endSequence();
    }
}