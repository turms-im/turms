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

public interface AliOSSUploadOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.AliOSSUpload)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The accessKey.
     */
    java.lang.String getAccessKey();

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The bytes for accessKey.
     */
    com.google.protobuf.ByteString getAccessKeyBytes();

    /**
     * <code>string secret = 2;</code>
     *
     * @return The secret.
     */
    java.lang.String getSecret();

    /**
     * <code>string secret = 2;</code>
     *
     * @return The bytes for secret.
     */
    com.google.protobuf.ByteString getSecretBytes();

    /**
     * <code>string region = 3;</code>
     *
     * @return The region.
     */
    java.lang.String getRegion();

    /**
     * <code>string region = 3;</code>
     *
     * @return The bytes for region.
     */
    com.google.protobuf.ByteString getRegionBytes();

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The endpoint.
     */
    java.lang.String getEndpoint();

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The bytes for endpoint.
     */
    com.google.protobuf.ByteString getEndpointBytes();

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bucket.
     */
    java.lang.String getBucket();

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bytes for bucket.
     */
    com.google.protobuf.ByteString getBucketBytes();
}
