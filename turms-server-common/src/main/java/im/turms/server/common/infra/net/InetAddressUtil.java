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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import io.netty.util.NetUtil;

/**
 * @author James Chen
 */
public final class InetAddressUtil {

    public static final int IPV4_BYTE_LENGTH = 4;
    public static final int IPV6_BYTE_LENGTH = 16;

    private InetAddressUtil() {
    }

    public static byte[] ipStringToBytes(String ip) {
        byte[] bytes = NetUtil.createByteArrayFromIpAddressString(ip);
        if (bytes == null) {
            throw new IllegalArgumentException(
                    "Invalid IP address: "
                            + ip);
        }
        return bytes;
    }

    public static InetAddress ipStringToAddress(String ipString) {
        InetAddress address = NetUtil.createInetAddressFromIpAddressString(ipString);
        if (address == null) {
            throw new IllegalArgumentException(
                    "Invalid IP address: "
                            + ipString);
        }
        return address;
    }

    public static InetAddress ipBytesToInetAddress(byte[] ip) {
        try {
            return InetAddress.getByAddress(ip);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(
                    "Invalid IP bytes: "
                            + Arrays.toString(ip),
                    e);
        }
    }

    public static String ipBytesToString(byte[] ip) {
        try {
            return NetUtil.bytesToIpAddress(ip);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Invalid IP bytes: "
                            + Arrays.toString(ip),
                    e);
        }
    }

    /**
     * The min value (i.e., {@link Integer.MIN_VALUE}) represents "0.0.0.0", and the max value
     * (i.e., {@link Integer.MAX_VALUE}) represents "255.255.255.255".
     */
    public static int ipV4BytesToUnsignedInt(byte[] ip) {
        return (ip[3] & 0xFF | ((ip[2] << 8) & 0xFF00) | ((ip[1] << 16) & 0xFF_0000)
                | ((ip[0] << 24) & 0xFF00_0000)) ^ 0x80000000;
    }

    public static byte[] ipV4UnsignedIntToBytes(int ip) {
        return new byte[]{(byte) ((ip ^ 0x80000000) >>> 24),
                (byte) (ip >>> 16),
                (byte) (ip >>> 8),
                (byte) ip};
    }

    public static boolean isIp(byte[] ip) {
        if (ip == null) {
            return false;
        }
        int length = ip.length;
        return length == IPV4_BYTE_LENGTH || length == IPV6_BYTE_LENGTH;
    }

    public static boolean isIp(String ipString) {
        return NetUtil.isValidIpV4Address(ipString) || NetUtil.isValidIpV6Address(ipString);
    }

}