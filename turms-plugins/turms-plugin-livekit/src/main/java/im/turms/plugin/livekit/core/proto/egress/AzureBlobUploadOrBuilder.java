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

public interface AzureBlobUploadOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.AzureBlobUpload)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string account_name = 1;</code>
     *
     * @return The accountName.
     */
    java.lang.String getAccountName();

    /**
     * <code>string account_name = 1;</code>
     *
     * @return The bytes for accountName.
     */
    com.google.protobuf.ByteString getAccountNameBytes();

    /**
     * <code>string account_key = 2;</code>
     *
     * @return The accountKey.
     */
    java.lang.String getAccountKey();

    /**
     * <code>string account_key = 2;</code>
     *
     * @return The bytes for accountKey.
     */
    com.google.protobuf.ByteString getAccountKeyBytes();

    /**
     * <code>string container_name = 3;</code>
     *
     * @return The containerName.
     */
    java.lang.String getContainerName();

    /**
     * <code>string container_name = 3;</code>
     *
     * @return The bytes for containerName.
     */
    com.google.protobuf.ByteString getContainerNameBytes();
}
