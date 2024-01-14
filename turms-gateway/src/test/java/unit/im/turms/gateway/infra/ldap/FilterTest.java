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

package unit.im.turms.gateway.infra.ldap;

import com.unboundid.asn1.ASN1Element;
import com.unboundid.ldap.sdk.LDAPException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.operation.search.Filter;

import static com.unboundid.ldap.sdk.Filter.create;
import static com.unboundid.ldap.sdk.Filter.decode;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 *         <p>
 *         TODO: Add tests for invalid filters.
 */
class FilterTest {

    @Test
    void testEqualFilter() {
        test("a=1");
        test("(a=1)");

        test("displayName=John Smith");
        test("(displayName=John Smith)");
    }

    @Test
    void testPresentFilter() {
        test("a=*");
        test("(a=*)");
    }

    @Test
    void testNotFilter() {
        test("(!(a=1))");
        test("(!(&(a=1)(b=2)))");
    }

    @Test
    void testAndFilter() throws LDAPException {
        String filterString = "(&(a=1)(b=2))";
        test(filterString);
    }

    @Test
    void testOrFilter() {
        test("(|(a=1)(b=2))");
    }

    @Test
    void testApproxMatchFilter() {
        test("(a~=1)");
        test("a~=1");
    }

    @Test
    void testSubstrMatchFilter() {
        test("(a=*1)");
        test("a=*1");
    }

    @Test
    void testExtensibleMatchFilter() {
        test("(cn:=Betty Rubble)");
        test("(cn:caseExactMatch:=Fred Flintstone)");
        test("(sn:dn:2.4.6.8.10:=Barney Rubble)");
        test("(&(ou:dn:=sales)(objectclass=user))");
        test("(:1.2.3:=Wilma Flintstone)");

//        test("(:DN:2.4.6.8.10:=Dino)");
    }

    @Test
    void testEscapedFilter() {
        test("CN=Rees\\5C, John,OU=Tier3,DC=big,DC=com");
        test("(o=Parens R Us \\28for all your parenthetical needs\\29)");
        test("(cn=*\\2a*)");
        test("(filename=C:\\5cMyFile)");
        test("(bin=\\00\\00\\00\\04)");
        test("(sn=Lu\\c4\\8di\\c4\\87)");
        test("(1.3.6.1.4.1.1466.0=\\04\\02\\48\\69)");
    }

    @Test
    void testFilterSet() {
        test("(&(objectClass=User)(|(department=Commercial)(department=Financial)))");
        test("(&(ObjectClass=User)(!(ObjectClass=Computer))(|(department=Commercial)(department=Financial))(department=*)(title=*))");
        test("(&(objectClass=Person)(|(sn=Jensen)(cn=Babs J*)))");
    }

    @SneakyThrows
    private static void test(String filterString) {
        ASN1Element expectedElement = create(filterString).encode();
        byte[] expectedBytes = expectedElement.encode();

        byte[] actualBytes;
        try (BerBuffer buffer = new BerBuffer()) {
            Filter.write(buffer, filterString);
            actualBytes = buffer.getBytes();
        }
        // We encode the sequence in different ways,
        // so we need to normalize their representation.
        com.unboundid.ldap.sdk.Filter filter = decode(ASN1Element.decode(actualBytes));
        ASN1Element actualElement = filter.encode();
        actualBytes = actualElement.encode();

        assertThat(actualBytes).isEqualTo(expectedBytes);
    }

}