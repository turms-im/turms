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

public interface SegmentedFileOutputOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.SegmentedFileOutput)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
     *
     * @return The enum numeric value on the wire for protocol.
     */
    int getProtocolValue();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
     *
     * @return The protocol.
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol getProtocol();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string filename_prefix = 2;</code>
     *
     * @return The filenamePrefix.
     */
    java.lang.String getFilenamePrefix();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string filename_prefix = 2;</code>
     *
     * @return The bytes for filenamePrefix.
     */
    com.google.protobuf.ByteString getFilenamePrefixBytes();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string playlist_name = 3;</code>
     *
     * @return The playlistName.
     */
    java.lang.String getPlaylistName();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string playlist_name = 3;</code>
     *
     * @return The bytes for playlistName.
     */
    com.google.protobuf.ByteString getPlaylistNameBytes();

    /**
     * <pre>
     * (optional, disabled if not provided). Path of a live playlist
     * </pre>
     *
     * <code>string live_playlist_name = 11;</code>
     *
     * @return The livePlaylistName.
     */
    java.lang.String getLivePlaylistName();

    /**
     * <pre>
     * (optional, disabled if not provided). Path of a live playlist
     * </pre>
     *
     * <code>string live_playlist_name = 11;</code>
     *
     * @return The bytes for livePlaylistName.
     */
    com.google.protobuf.ByteString getLivePlaylistNameBytes();

    /**
     * <pre>
     * in seconds (optional)
     * </pre>
     *
     * <code>uint32 segment_duration = 4;</code>
     *
     * @return The segmentDuration.
     */
    int getSegmentDuration();

    /**
     * <pre>
     * (optional, default INDEX)
     * </pre>
     *
     * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
     *
     * @return The enum numeric value on the wire for filenameSuffix.
     */
    int getFilenameSuffixValue();

    /**
     * <pre>
     * (optional, default INDEX)
     * </pre>
     *
     * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
     *
     * @return The filenameSuffix.
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix getFilenameSuffix();

    /**
     * <pre>
     * disable upload of manifest file (default false)
     * </pre>
     *
     * <code>bool disable_manifest = 8;</code>
     *
     * @return The disableManifest.
     */
    boolean getDisableManifest();

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     *
     * @return Whether the s3 field is set.
     */
    boolean hasS3();

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     *
     * @return The s3.
     */
    im.turms.plugin.livekit.core.proto.egress.S3Upload getS3();

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder();

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     *
     * @return Whether the gcp field is set.
     */
    boolean hasGcp();

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     *
     * @return The gcp.
     */
    im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp();

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder();

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     *
     * @return Whether the azure field is set.
     */
    boolean hasAzure();

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     *
     * @return The azure.
     */
    im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure();

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     *
     * @return Whether the aliOSS field is set.
     */
    boolean hasAliOSS();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     *
     * @return The aliOSS.
     */
    im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS();

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder();

    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.OutputCase getOutputCase();
}
