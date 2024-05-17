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

package im.turms.plugin.livekit.core.proto.egress;

public interface ProxyConfigOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ProxyConfig)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string url = 1;</code>
     *
     * @return The url.
     */
    java.lang.String getUrl();

    /**
     * <code>string url = 1;</code>
     *
     * @return The bytes for url.
     */
    com.google.protobuf.ByteString getUrlBytes();

    /**
     * <code>string username = 2;</code>
     *
     * @return The username.
     */
    java.lang.String getUsername();

    /**
     * <code>string username = 2;</code>
     *
     * @return The bytes for username.
     */
    com.google.protobuf.ByteString getUsernameBytes();

    /**
     * <code>string password = 3;</code>
     *
     * @return The password.
     */
    java.lang.String getPassword();

    /**
     * <code>string password = 3;</code>
     *
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString getPasswordBytes();
}
