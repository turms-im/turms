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

import im.turms.server.common.infra.lang.StringUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public final class InetAddressUtil {

    public static final int IPV4_BYTE_LENGTH = 4;
    public static final int IPV6_BYTE_LENGTH = 16;

    private static final int IPV4_PART_COUNT = 4;
    private static final int IPV6_PART_COUNT = 8;

    private static final byte IPV4_DELIMITER = '.';
    private static final byte IPV6_DELIMITER = ':';

    private InetAddressUtil() {
    }

    public static byte[] ipStringToBytes(String ip) {
        try {
            return ipStringToBytes0(ip);
        } catch (Exception e) {
            throw new IllegalArgumentException("The IP " + ip + " is invalid", e);
        }
    }

    public static InetAddress ipStringToAddress(String ipString) {
        byte[] addr = ipStringToBytes0(ipString);
        // The argument was malformed, i.e. not an IP string literal.
        if (addr == null) {
            throw new IllegalArgumentException("The IP " + ipString + " is invalid");
        }
        return ipBytesToInetAddress(addr);
    }

    public static InetAddress ipBytesToInetAddress(byte[] ip) {
        try {
            return InetAddress.getByAddress(ip);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("The IP bytes " + Arrays.toString(ip) + " is invalid", e);
        }
    }

    public static String ipBytesToString(byte[] ip) {
        try {
            return InetAddress.getByAddress(ip).getHostAddress();
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("The IP bytes " + Arrays.toString(ip) + " is invalid", e);
        }
    }

    @Nullable
    public static Integer ipBytesToInt(byte[] ip) {
        if (ip.length != IPV4_BYTE_LENGTH) {
            return null;
        }
        return ip[3] & 0xFF
                | ((ip[2] << 8) & 0xFF00)
                | ((ip[1] << 16) & 0xFF_0000)
                | ((ip[0] << 24) & 0xFF00_0000);
    }

    public static byte[] ipIntToBytes(int ip) {
        return new byte[]{
                (byte) (ip >>> 24),
                (byte) (ip >>> 16),
                (byte) (ip >>> 8),
                (byte) ip
        };
    }

    public static boolean isIpV4OrV6(@Nullable byte[] ip) {
        return ip != null && (ip.length == IPV4_BYTE_LENGTH || ip.length == IPV6_BYTE_LENGTH);
    }

    public static boolean isInetAddress(String ipString) {
        return ipStringToBytes0(ipString) != null;
    }

    private static byte[] ipStringToBytes0(String ipString) {
        // Make a first pass to categorize the characters in this string.
        boolean hasColon = false;
        boolean hasDot = false;
        int percentIndex = -1;
        for (int i = 0; i < ipString.length(); i++) {
            char c = ipString.charAt(i);
            if (c == '.') {
                hasDot = true;
            } else if (c == ':') {
                if (hasDot) {
                    return null; // Colons must not appear after dots.
                }
                hasColon = true;
            } else if (c == '%') {
                percentIndex = i;
                break; // everything after a '%' is ignored (it's a Scope ID): http://superuser.com/a/99753
            } else if (Character.digit(c, 16) == -1) {
                return null; // Everything else must be a decimal or hex digit.
            }
        }

        // Now decide which address family to parse.
        if (hasColon) {
            if (hasDot) {
                ipString = convertDottedQuadToHex(ipString);
                if (ipString == null) {
                    return null;
                }
            }
            if (percentIndex != -1) {
                ipString = ipString.substring(0, percentIndex);
            }
            return textToNumericFormatV6(ipString);
        } else if (hasDot) {
            if (percentIndex != -1) {
                return null; // Scope IDs are not supported for IPV4
            }
            return textToNumericFormatV4(ipString);
        }
        return null;
    }

    private static byte[] textToNumericFormatV4(String ipString) {
        if (StringUtil.countOccurrencesLatin1(ipString, IPV4_DELIMITER) + 1 != IPV4_PART_COUNT) {
            return null; // Wrong number of parts
        }

        byte[] bytes = new byte[IPV4_PART_COUNT];
        int start = 0;
        // Iterate through the parts of the ip string.
        // Invariant: start is always the beginning of an octet.
        for (int i = 0; i < IPV4_PART_COUNT; i++) {
            int end = ipString.indexOf(IPV4_DELIMITER, start);
            if (end == -1) {
                end = ipString.length();
            }
            try {
                bytes[i] = parseOctet(ipString, start, end);
            } catch (NumberFormatException ex) {
                return null;
            }
            start = end + 1;
        }

        return bytes;
    }

    private static byte[] textToNumericFormatV6(String ipString) {
        // An address can have [2..8] colons.
        int delimiterCount = StringUtil.countOccurrencesLatin1(ipString, IPV6_DELIMITER);
        if (delimiterCount < 2 || delimiterCount > IPV6_PART_COUNT) {
            return null;
        }
        int partsSkipped = IPV6_PART_COUNT - (delimiterCount + 1); // estimate; may be modified later
        boolean hasSkip = false;
        // Scan for the appearance of ::, to mark a skip-format IPV6 string and adjust the partsSkipped
        // estimate.
        for (int i = 0; i < ipString.length() - 1; i++) {
            if (ipString.charAt(i) == IPV6_DELIMITER && ipString.charAt(i + 1) == IPV6_DELIMITER) {
                if (hasSkip) {
                    return null; // Can't have more than one ::
                }
                hasSkip = true;
                partsSkipped++; // :: means we skipped an extra part in between the two delimiters.
                if (i == 0) {
                    partsSkipped++; // Begins with ::, so we skipped the part preceding the first :
                }
                if (i == ipString.length() - 2) {
                    partsSkipped++; // Ends with ::, so we skipped the part after the last :
                }
            }
        }
        if (ipString.charAt(0) == IPV6_DELIMITER && ipString.charAt(1) != IPV6_DELIMITER) {
            return null; // ^: requires ^::
        }
        if (ipString.charAt(ipString.length() - 1) == IPV6_DELIMITER
                && ipString.charAt(ipString.length() - 2) != IPV6_DELIMITER) {
            return null; // :$ requires ::$
        }
        if (hasSkip && partsSkipped <= 0) {
            return null; // :: must expand to at least one '0'
        }
        if (!hasSkip && delimiterCount + 1 != IPV6_PART_COUNT) {
            return null; // Incorrect number of parts
        }

        ByteBuffer rawBytes = ByteBuffer.allocate(2 * IPV6_PART_COUNT);
        try {
            // Iterate through the parts of the ip string.
            // Invariant: start is always the beginning of a hextet, or the second ':' of the skip
            // sequence "::"
            int start = 0;
            if (ipString.charAt(0) == IPV6_DELIMITER) {
                start = 1;
            }
            while (start < ipString.length()) {
                int end = ipString.indexOf(IPV6_DELIMITER, start);
                if (end == -1) {
                    end = ipString.length();
                }
                if (ipString.charAt(start) == IPV6_DELIMITER) {
                    // expand zeroes
                    for (int i = 0; i < partsSkipped; i++) {
                        rawBytes.putShort((short) 0);
                    }

                } else {
                    rawBytes.putShort(parseHextet(ipString, start, end));
                }
                start = end + 1;
            }
        } catch (NumberFormatException ex) {
            return null;
        }
        return rawBytes.array();
    }

    private static String convertDottedQuadToHex(String ipString) {
        int lastColon = ipString.lastIndexOf(':');
        String initialPart = ipString.substring(0, lastColon + 1);
        String dottedQuad = ipString.substring(lastColon + 1);
        byte[] quad = textToNumericFormatV4(dottedQuad);
        if (quad == null) {
            return null;
        }
        String penultimate = Integer.toHexString(((quad[0] & 0xff) << 8) | (quad[1] & 0xff));
        String ultimate = Integer.toHexString(((quad[2] & 0xff) << 8) | (quad[3] & 0xff));
        return initialPart + penultimate + ":" + ultimate;
    }

    private static byte parseOctet(String ipString, int start, int end) {
        // Note: we already verified that this string contains only hex digits, but the string may still
        // contain non-decimal characters.
        int length = end - start;
        if (length <= 0 || length > 3) {
            throw new NumberFormatException();
        }
        // Disallow leading zeroes, because no clear standard exists on
        // whether these should be interpreted as decimal or octal.
        if (length > 1 && ipString.charAt(start) == '0') {
            throw new NumberFormatException();
        }
        int octet = 0;
        for (int i = start; i < end; i++) {
            octet *= 10;
            int digit = Character.digit(ipString.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException();
            }
            octet += digit;
        }
        if (octet > 255) {
            throw new NumberFormatException();
        }
        return (byte) octet;
    }

    // Parse a hextet out of the ipString from start (inclusive) to end (exclusive)
    private static short parseHextet(String ipString, int start, int end) {
        // Note: we already verified that this string contains only hex digits.
        int length = end - start;
        if (length <= 0 || length > 4) {
            throw new NumberFormatException();
        }
        int hextet = 0;
        for (int i = start; i < end; i++) {
            hextet = hextet << 4;
            hextet |= Character.digit(ipString.charAt(i), 16);
        }
        return (short) hextet;
    }

}
