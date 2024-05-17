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

package im.turms.plugin.livekit.core.proto.models;

public interface ClientInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ClientInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
     *
     * @return The enum numeric value on the wire for sdk.
     */
    int getSdkValue();

    /**
     * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
     *
     * @return The sdk.
     */
    im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK getSdk();

    /**
     * <code>string version = 2;</code>
     *
     * @return The version.
     */
    java.lang.String getVersion();

    /**
     * <code>string version = 2;</code>
     *
     * @return The bytes for version.
     */
    com.google.protobuf.ByteString getVersionBytes();

    /**
     * <code>int32 protocol = 3;</code>
     *
     * @return The protocol.
     */
    int getProtocol();

    /**
     * <code>string os = 4;</code>
     *
     * @return The os.
     */
    java.lang.String getOs();

    /**
     * <code>string os = 4;</code>
     *
     * @return The bytes for os.
     */
    com.google.protobuf.ByteString getOsBytes();

    /**
     * <code>string os_version = 5;</code>
     *
     * @return The osVersion.
     */
    java.lang.String getOsVersion();

    /**
     * <code>string os_version = 5;</code>
     *
     * @return The bytes for osVersion.
     */
    com.google.protobuf.ByteString getOsVersionBytes();

    /**
     * <code>string device_model = 6;</code>
     *
     * @return The deviceModel.
     */
    java.lang.String getDeviceModel();

    /**
     * <code>string device_model = 6;</code>
     *
     * @return The bytes for deviceModel.
     */
    com.google.protobuf.ByteString getDeviceModelBytes();

    /**
     * <code>string browser = 7;</code>
     *
     * @return The browser.
     */
    java.lang.String getBrowser();

    /**
     * <code>string browser = 7;</code>
     *
     * @return The bytes for browser.
     */
    com.google.protobuf.ByteString getBrowserBytes();

    /**
     * <code>string browser_version = 8;</code>
     *
     * @return The browserVersion.
     */
    java.lang.String getBrowserVersion();

    /**
     * <code>string browser_version = 8;</code>
     *
     * @return The bytes for browserVersion.
     */
    com.google.protobuf.ByteString getBrowserVersionBytes();

    /**
     * <code>string address = 9;</code>
     *
     * @return The address.
     */
    java.lang.String getAddress();

    /**
     * <code>string address = 9;</code>
     *
     * @return The bytes for address.
     */
    com.google.protobuf.ByteString getAddressBytes();

    /**
     * <pre>
     * wifi, wired, cellular, vpn, empty if not known
     * </pre>
     *
     * <code>string network = 10;</code>
     *
     * @return The network.
     */
    java.lang.String getNetwork();

    /**
     * <pre>
     * wifi, wired, cellular, vpn, empty if not known
     * </pre>
     *
     * <code>string network = 10;</code>
     *
     * @return The bytes for network.
     */
    com.google.protobuf.ByteString getNetworkBytes();
}
