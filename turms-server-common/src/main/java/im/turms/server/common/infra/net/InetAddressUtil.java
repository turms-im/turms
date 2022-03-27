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

package im.turms.server.common.infra.net;

import com.google.common.net.InetAddresses;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author James Chen
 */
public final class InetAddressUtil {

    public static final int IPV4_BYTE_LENGTH = 4;
    public static final int IPV6_BYTE_LENGTH = 16;

    private InetAddressUtil() {
    }

    public static byte[] ipStringToBytes(String ip) {
        try {
            return InetAddresses.forString(ip).getAddress();
        } catch (Exception e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The IP " + ip + " is invalid");
        }
    }

    public static String ipBytesToString(byte[] ip) {
        try {
            return InetAddress.getByAddress(ip).getHostAddress();
        } catch (UnknownHostException e) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The IP " + Arrays.toString(ip) + " is invalid");
        }
    }

    public static boolean isIpV4OrV6(byte[] ip) {
        return ip != null && (ip.length == IPV4_BYTE_LENGTH || ip.length == IPV6_BYTE_LENGTH);
    }

}
