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

public interface GCPUploadOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.GCPUpload)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * service account credentials serialized in JSON "credentials.json"
     * </pre>
     *
     * <code>string credentials = 1;</code>
     *
     * @return The credentials.
     */
    java.lang.String getCredentials();

    /**
     * <pre>
     * service account credentials serialized in JSON "credentials.json"
     * </pre>
     *
     * <code>string credentials = 1;</code>
     *
     * @return The bytes for credentials.
     */
    com.google.protobuf.ByteString getCredentialsBytes();

    /**
     * <code>string bucket = 2;</code>
     *
     * @return The bucket.
     */
    java.lang.String getBucket();

    /**
     * <code>string bucket = 2;</code>
     *
     * @return The bytes for bucket.
     */
    com.google.protobuf.ByteString getBucketBytes();

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     *
     * @return Whether the proxy field is set.
     */
    boolean hasProxy();

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     *
     * @return The proxy.
     */
    im.turms.plugin.livekit.core.proto.egress.ProxyConfig getProxy();

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder getProxyOrBuilder();
}
