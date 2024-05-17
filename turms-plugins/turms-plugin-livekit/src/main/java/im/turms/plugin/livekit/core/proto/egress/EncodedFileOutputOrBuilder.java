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

public interface EncodedFileOutputOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.EncodedFileOutput)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodedFileType file_type = 1;</code>
     *
     * @return The enum numeric value on the wire for fileType.
     */
    int getFileTypeValue();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodedFileType file_type = 1;</code>
     *
     * @return The fileType.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileType getFileType();

    /**
     * <pre>
     * see egress docs for templating (default {room_name}-{time})
     * </pre>
     *
     * <code>string filepath = 2;</code>
     *
     * @return The filepath.
     */
    java.lang.String getFilepath();

    /**
     * <pre>
     * see egress docs for templating (default {room_name}-{time})
     * </pre>
     *
     * <code>string filepath = 2;</code>
     *
     * @return The bytes for filepath.
     */
    com.google.protobuf.ByteString getFilepathBytes();

    /**
     * <pre>
     * disable upload of manifest file (default false)
     * </pre>
     *
     * <code>bool disable_manifest = 6;</code>
     *
     * @return The disableManifest.
     */
    boolean getDisableManifest();

    /**
     * <code>.livekit.S3Upload s3 = 3;</code>
     *
     * @return Whether the s3 field is set.
     */
    boolean hasS3();

    /**
     * <code>.livekit.S3Upload s3 = 3;</code>
     *
     * @return The s3.
     */
    im.turms.plugin.livekit.core.proto.egress.S3Upload getS3();

    /**
     * <code>.livekit.S3Upload s3 = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder();

    /**
     * <code>.livekit.GCPUpload gcp = 4;</code>
     *
     * @return Whether the gcp field is set.
     */
    boolean hasGcp();

    /**
     * <code>.livekit.GCPUpload gcp = 4;</code>
     *
     * @return The gcp.
     */
    im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp();

    /**
     * <code>.livekit.GCPUpload gcp = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder();

    /**
     * <code>.livekit.AzureBlobUpload azure = 5;</code>
     *
     * @return Whether the azure field is set.
     */
    boolean hasAzure();

    /**
     * <code>.livekit.AzureBlobUpload azure = 5;</code>
     *
     * @return The azure.
     */
    im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure();

    /**
     * <code>.livekit.AzureBlobUpload azure = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 7;</code>
     *
     * @return Whether the aliOSS field is set.
     */
    boolean hasAliOSS();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 7;</code>
     *
     * @return The aliOSS.
     */
    im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder();

    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.OutputCase getOutputCase();
}
