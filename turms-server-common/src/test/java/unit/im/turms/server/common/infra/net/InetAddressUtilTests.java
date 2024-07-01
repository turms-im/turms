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

package unit.im.turms.server.common.infra.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.net.InetAddressUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class InetAddressUtilTests {

    private static final List<IpV4TestCase> IPV4_TEST_CASES =
            List.of(new IpV4TestCase("0.0.0.0", Integer.MIN_VALUE),
                    new IpV4TestCase("1.0.0.0", -2130706432),
                    new IpV4TestCase("1.0.0.1", -2130706431),
                    new IpV4TestCase("1.2.3.4", -2130574588),
                    new IpV4TestCase("8.8.8.8", -2012739576),
                    new IpV4TestCase("128.0.0.0", 0),
                    new IpV4TestCase("200.201.189.192", 1221180864),
                    new IpV4TestCase("255.255.255.1", 2147483393),
                    new IpV4TestCase("255.255.255.255", Integer.MAX_VALUE));

    @Test
    void ipIntBytesConversion() throws UnknownHostException {
        for (IpV4TestCase testCase : IPV4_TEST_CASES) {
            InetAddress inetAddress = InetAddress.getByName(testCase.ip);
            byte[] address = inetAddress.getAddress();
            byte[] actual = InetAddressUtil.ipV4UnsignedIntToBytes(
                    InetAddressUtil.ipV4BytesToUnsignedInt(inetAddress.getAddress()));
            assertThat(actual).isEqualTo(address);
        }
    }

    @Test
    void ipV4BytesToUnsignedInt() throws UnknownHostException {
        for (IpV4TestCase testCase : IPV4_TEST_CASES) {
            InetAddress inetAddress = InetAddress.getByName(testCase.ip);
            int i = InetAddressUtil.ipV4BytesToUnsignedInt(inetAddress.getAddress());
            assertThat(i).isEqualTo(testCase.unsignedInt);
        }
    }

    private record IpV4TestCase(
            String ip,
            int unsignedInt
    ) {
    }

}