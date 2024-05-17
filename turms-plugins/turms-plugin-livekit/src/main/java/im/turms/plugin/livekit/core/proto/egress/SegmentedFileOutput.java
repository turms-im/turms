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

/**
 * <pre>
 * Used to generate HLS segments or other kind of segmented output
 * </pre>
 *
 * Protobuf type {@code livekit.SegmentedFileOutput}
 */
public final class SegmentedFileOutput extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SegmentedFileOutput)
        SegmentedFileOutputOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SegmentedFileOutput.class.getName());
    }

    // Use SegmentedFileOutput.newBuilder() to construct.
    private SegmentedFileOutput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SegmentedFileOutput() {
        protocol_ = 0;
        filenamePrefix_ = "";
        playlistName_ = "";
        livePlaylistName_ = "";
        filenameSuffix_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentedFileOutput_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentedFileOutput_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.class,
                        im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        S3(5),
        GCP(6),
        AZURE(7),
        ALIOSS(9),
        OUTPUT_NOT_SET(0);

        private final int value;

        OutputCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static OutputCase valueOf(int value) {
            return forNumber(value);
        }

        public static OutputCase forNumber(int value) {
            return switch (value) {
                case 5 -> S3;
                case 6 -> GCP;
                case 7 -> AZURE;
                case 9 -> ALIOSS;
                case 0 -> OUTPUT_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public OutputCase getOutputCase() {
        return OutputCase.forNumber(outputCase_);
    }

    public static final int PROTOCOL_FIELD_NUMBER = 1;
    private int protocol_ = 0;

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
     *
     * @return The enum numeric value on the wire for protocol.
     */
    @java.lang.Override
    public int getProtocolValue() {
        return protocol_;
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
     *
     * @return The protocol.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol getProtocol() {
        im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol result =
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol
                        .forNumber(protocol_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol.UNRECOGNIZED
                : result;
    }

    public static final int FILENAME_PREFIX_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object filenamePrefix_ = "";

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string filename_prefix = 2;</code>
     *
     * @return The filenamePrefix.
     */
    @java.lang.Override
    public java.lang.String getFilenamePrefix() {
        java.lang.Object ref = filenamePrefix_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            filenamePrefix_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string filename_prefix = 2;</code>
     *
     * @return The bytes for filenamePrefix.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getFilenamePrefixBytes() {
        java.lang.Object ref = filenamePrefix_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            filenamePrefix_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PLAYLIST_NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object playlistName_ = "";

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string playlist_name = 3;</code>
     *
     * @return The playlistName.
     */
    @java.lang.Override
    public java.lang.String getPlaylistName() {
        java.lang.Object ref = playlistName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            playlistName_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string playlist_name = 3;</code>
     *
     * @return The bytes for playlistName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPlaylistNameBytes() {
        java.lang.Object ref = playlistName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            playlistName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LIVE_PLAYLIST_NAME_FIELD_NUMBER = 11;
    @SuppressWarnings("serial")
    private volatile java.lang.Object livePlaylistName_ = "";

    /**
     * <pre>
     * (optional, disabled if not provided). Path of a live playlist
     * </pre>
     *
     * <code>string live_playlist_name = 11;</code>
     *
     * @return The livePlaylistName.
     */
    @java.lang.Override
    public java.lang.String getLivePlaylistName() {
        java.lang.Object ref = livePlaylistName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            livePlaylistName_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional, disabled if not provided). Path of a live playlist
     * </pre>
     *
     * <code>string live_playlist_name = 11;</code>
     *
     * @return The bytes for livePlaylistName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getLivePlaylistNameBytes() {
        java.lang.Object ref = livePlaylistName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            livePlaylistName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SEGMENT_DURATION_FIELD_NUMBER = 4;
    private int segmentDuration_ = 0;

    /**
     * <pre>
     * in seconds (optional)
     * </pre>
     *
     * <code>uint32 segment_duration = 4;</code>
     *
     * @return The segmentDuration.
     */
    @java.lang.Override
    public int getSegmentDuration() {
        return segmentDuration_;
    }

    public static final int FILENAME_SUFFIX_FIELD_NUMBER = 10;
    private int filenameSuffix_ = 0;

    /**
     * <pre>
     * (optional, default INDEX)
     * </pre>
     *
     * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
     *
     * @return The enum numeric value on the wire for filenameSuffix.
     */
    @java.lang.Override
    public int getFilenameSuffixValue() {
        return filenameSuffix_;
    }

    /**
     * <pre>
     * (optional, default INDEX)
     * </pre>
     *
     * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
     *
     * @return The filenameSuffix.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix getFilenameSuffix() {
        im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix result =
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix
                        .forNumber(filenameSuffix_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix.UNRECOGNIZED
                : result;
    }

    public static final int DISABLE_MANIFEST_FIELD_NUMBER = 8;
    private boolean disableManifest_ = false;

    /**
     * <pre>
     * disable upload of manifest file (default false)
     * </pre>
     *
     * <code>bool disable_manifest = 8;</code>
     *
     * @return The disableManifest.
     */
    @java.lang.Override
    public boolean getDisableManifest() {
        return disableManifest_;
    }

    public static final int S3_FIELD_NUMBER = 5;

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     *
     * @return Whether the s3 field is set.
     */
    @java.lang.Override
    public boolean hasS3() {
        return outputCase_ == 5;
    }

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     *
     * @return The s3.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
        if (outputCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    /**
     * <code>.livekit.S3Upload s3 = 5;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
        if (outputCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    public static final int GCP_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     *
     * @return Whether the gcp field is set.
     */
    @java.lang.Override
    public boolean hasGcp() {
        return outputCase_ == 6;
    }

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     *
     * @return The gcp.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.GCPUpload gcp = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    public static final int AZURE_FIELD_NUMBER = 7;

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     *
     * @return Whether the azure field is set.
     */
    @java.lang.Override
    public boolean hasAzure() {
        return outputCase_ == 7;
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     *
     * @return The azure.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
        if (outputCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
        if (outputCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    public static final int ALIOSS_FIELD_NUMBER = 9;

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     *
     * @return Whether the aliOSS field is set.
     */
    @java.lang.Override
    public boolean hasAliOSS() {
        return outputCase_ == 9;
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     *
     * @return The aliOSS.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
        if (outputCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
        if (outputCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (protocol_ != im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol.DEFAULT_SEGMENTED_FILE_PROTOCOL
                .getNumber()) {
            output.writeEnum(1, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filenamePrefix_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, filenamePrefix_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, playlistName_);
        }
        if (segmentDuration_ != 0) {
            output.writeUInt32(4, segmentDuration_);
        }
        if (outputCase_ == 5) {
            output.writeMessage(5, (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 6) {
            output.writeMessage(6, (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 7) {
            output.writeMessage(7,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (disableManifest_) {
            output.writeBool(8, disableManifest_);
        }
        if (outputCase_ == 9) {
            output.writeMessage(9,
                    (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_);
        }
        if (filenameSuffix_ != im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix.INDEX
                .getNumber()) {
            output.writeEnum(10, filenameSuffix_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 11, livePlaylistName_);
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (protocol_ != im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol.DEFAULT_SEGMENTED_FILE_PROTOCOL
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filenamePrefix_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, filenamePrefix_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, playlistName_);
        }
        if (segmentDuration_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, segmentDuration_);
        }
        if (outputCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                    (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 7) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (disableManifest_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, disableManifest_);
        }
        if (outputCase_ == 9) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                    (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_);
        }
        if (filenameSuffix_ != im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix.INDEX
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(10, filenameSuffix_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(11, livePlaylistName_);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SegmentedFileOutput other)) {
            return super.equals(obj);
        }

        if (protocol_ != other.protocol_) {
            return false;
        }
        if (!getFilenamePrefix().equals(other.getFilenamePrefix())) {
            return false;
        }
        if (!getPlaylistName().equals(other.getPlaylistName())) {
            return false;
        }
        if (!getLivePlaylistName().equals(other.getLivePlaylistName())) {
            return false;
        }
        if (getSegmentDuration() != other.getSegmentDuration()) {
            return false;
        }
        if (filenameSuffix_ != other.filenameSuffix_) {
            return false;
        }
        if (getDisableManifest() != other.getDisableManifest()) {
            return false;
        }
        if (!getOutputCase().equals(other.getOutputCase())) {
            return false;
        }
        switch (outputCase_) {
            case 5 -> {
                if (!getS3().equals(other.getS3())) {
                    return false;
                }
            }
            case 6 -> {
                if (!getGcp().equals(other.getGcp())) {
                    return false;
                }
            }
            case 7 -> {
                if (!getAzure().equals(other.getAzure())) {
                    return false;
                }
            }
            case 9 -> {
                if (!getAliOSS().equals(other.getAliOSS())) {
                    return false;
                }
            }
            default -> {
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + PROTOCOL_FIELD_NUMBER;
        hash = (53 * hash) + protocol_;
        hash = (37 * hash) + FILENAME_PREFIX_FIELD_NUMBER;
        hash = (53 * hash) + getFilenamePrefix().hashCode();
        hash = (37 * hash) + PLAYLIST_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getPlaylistName().hashCode();
        hash = (37 * hash) + LIVE_PLAYLIST_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getLivePlaylistName().hashCode();
        hash = (37 * hash) + SEGMENT_DURATION_FIELD_NUMBER;
        hash = (53 * hash) + getSegmentDuration();
        hash = (37 * hash) + FILENAME_SUFFIX_FIELD_NUMBER;
        hash = (53 * hash) + filenameSuffix_;
        hash = (37 * hash) + DISABLE_MANIFEST_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableManifest());
        switch (outputCase_) {
            case 5 -> {
                hash = (37 * hash) + S3_FIELD_NUMBER;
                hash = (53 * hash) + getS3().hashCode();
            }
            case 6 -> {
                hash = (37 * hash) + GCP_FIELD_NUMBER;
                hash = (53 * hash) + getGcp().hashCode();
            }
            case 7 -> {
                hash = (37 * hash) + AZURE_FIELD_NUMBER;
                hash = (53 * hash) + getAzure().hashCode();
            }
            case 9 -> {
                hash = (37 * hash) + ALIOSS_FIELD_NUMBER;
                hash = (53 * hash) + getAliOSS().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * <pre>
     * Used to generate HLS segments or other kind of segmented output
     * </pre>
     *
     * Protobuf type {@code livekit.SegmentedFileOutput}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SegmentedFileOutput)
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentedFileOutput_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentedFileOutput_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.class,
                            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            protocol_ = 0;
            filenamePrefix_ = "";
            playlistName_ = "";
            livePlaylistName_ = "";
            segmentDuration_ = 0;
            filenameSuffix_ = 0;
            disableManifest_ = false;
            if (s3Builder_ != null) {
                s3Builder_.clear();
            }
            if (gcpBuilder_ != null) {
                gcpBuilder_.clear();
            }
            if (azureBuilder_ != null) {
                azureBuilder_.clear();
            }
            if (aliOSSBuilder_ != null) {
                aliOSSBuilder_.clear();
            }
            outputCase_ = 0;
            output_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentedFileOutput_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput build() {
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput result =
                    new im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.protocol_ = protocol_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.filenamePrefix_ = filenamePrefix_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.playlistName_ = playlistName_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.livePlaylistName_ = livePlaylistName_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.segmentDuration_ = segmentDuration_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.filenameSuffix_ = filenameSuffix_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.disableManifest_ = disableManifest_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 5 && s3Builder_ != null) {
                result.output_ = s3Builder_.build();
            }
            if (outputCase_ == 6 && gcpBuilder_ != null) {
                result.output_ = gcpBuilder_.build();
            }
            if (outputCase_ == 7 && azureBuilder_ != null) {
                result.output_ = azureBuilder_.build();
            }
            if (outputCase_ == 9 && aliOSSBuilder_ != null) {
                result.output_ = aliOSSBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                    .getDefaultInstance()) {
                return this;
            }
            if (other.protocol_ != 0) {
                setProtocolValue(other.getProtocolValue());
            }
            if (!other.getFilenamePrefix()
                    .isEmpty()) {
                filenamePrefix_ = other.filenamePrefix_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getPlaylistName()
                    .isEmpty()) {
                playlistName_ = other.playlistName_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (!other.getLivePlaylistName()
                    .isEmpty()) {
                livePlaylistName_ = other.livePlaylistName_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.getSegmentDuration() != 0) {
                setSegmentDuration(other.getSegmentDuration());
            }
            if (other.filenameSuffix_ != 0) {
                setFilenameSuffixValue(other.getFilenameSuffixValue());
            }
            if (other.getDisableManifest()) {
                setDisableManifest(other.getDisableManifest());
            }
            switch (other.getOutputCase()) {
                case S3 -> mergeS3(other.getS3());
                case GCP -> mergeGcp(other.getGcp());
                case AZURE -> mergeAzure(other.getAzure());
                case ALIOSS -> mergeAliOSS(other.getAliOSS());
                case OUTPUT_NOT_SET -> {
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 8 -> {
                            protocol_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            filenamePrefix_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            playlistName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            segmentDuration_ = input.readUInt32();
                            bitField0_ |= 0x00000010;
                        } // case 32
                        case 42 -> {
                            input.readMessage(getS3FieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 5;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getGcpFieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 6;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getAzureFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 7;
                        } // case 58
                        case 64 -> {
                            disableManifest_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 64
                        case 74 -> {
                            input.readMessage(getAliOSSFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 9;
                        } // case 74
                        case 80 -> {
                            filenameSuffix_ = input.readEnum();
                            bitField0_ |= 0x00000020;
                        } // case 80
                        case 90 -> {
                            livePlaylistName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 90
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int outputCase_ = 0;
        private java.lang.Object output_;

        public OutputCase getOutputCase() {
            return OutputCase.forNumber(outputCase_);
        }

        public Builder clearOutput() {
            outputCase_ = 0;
            output_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        private int protocol_ = 0;

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
         *
         * @return The enum numeric value on the wire for protocol.
         */
        @java.lang.Override
        public int getProtocolValue() {
            return protocol_;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
         *
         * @param value The enum numeric value on the wire for protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocolValue(int value) {
            protocol_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
         *
         * @return The protocol.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol getProtocol() {
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol result =
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol
                            .forNumber(protocol_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
         *
         * @param value The protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocol(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileProtocol value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            protocol_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.SegmentedFileProtocol protocol = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProtocol() {
            bitField0_ &= ~0x00000001;
            protocol_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object filenamePrefix_ = "";

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 2;</code>
         *
         * @return The filenamePrefix.
         */
        public java.lang.String getFilenamePrefix() {
            java.lang.Object ref = filenamePrefix_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                filenamePrefix_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 2;</code>
         *
         * @return The bytes for filenamePrefix.
         */
        public com.google.protobuf.ByteString getFilenamePrefixBytes() {
            java.lang.Object ref = filenamePrefix_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                filenamePrefix_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 2;</code>
         *
         * @param value The filenamePrefix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenamePrefix(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            filenamePrefix_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilenamePrefix() {
            filenamePrefix_ = getDefaultInstance().getFilenamePrefix();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 2;</code>
         *
         * @param value The bytes for filenamePrefix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenamePrefixBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            filenamePrefix_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object playlistName_ = "";

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string playlist_name = 3;</code>
         *
         * @return The playlistName.
         */
        public java.lang.String getPlaylistName() {
            java.lang.Object ref = playlistName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                playlistName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string playlist_name = 3;</code>
         *
         * @return The bytes for playlistName.
         */
        public com.google.protobuf.ByteString getPlaylistNameBytes() {
            java.lang.Object ref = playlistName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                playlistName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string playlist_name = 3;</code>
         *
         * @param value The playlistName to set.
         * @return This builder for chaining.
         */
        public Builder setPlaylistName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            playlistName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string playlist_name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPlaylistName() {
            playlistName_ = getDefaultInstance().getPlaylistName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string playlist_name = 3;</code>
         *
         * @param value The bytes for playlistName to set.
         * @return This builder for chaining.
         */
        public Builder setPlaylistNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            playlistName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object livePlaylistName_ = "";

        /**
         * <pre>
         * (optional, disabled if not provided). Path of a live playlist
         * </pre>
         *
         * <code>string live_playlist_name = 11;</code>
         *
         * @return The livePlaylistName.
         */
        public java.lang.String getLivePlaylistName() {
            java.lang.Object ref = livePlaylistName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                livePlaylistName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional, disabled if not provided). Path of a live playlist
         * </pre>
         *
         * <code>string live_playlist_name = 11;</code>
         *
         * @return The bytes for livePlaylistName.
         */
        public com.google.protobuf.ByteString getLivePlaylistNameBytes() {
            java.lang.Object ref = livePlaylistName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                livePlaylistName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional, disabled if not provided). Path of a live playlist
         * </pre>
         *
         * <code>string live_playlist_name = 11;</code>
         *
         * @param value The livePlaylistName to set.
         * @return This builder for chaining.
         */
        public Builder setLivePlaylistName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            livePlaylistName_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, disabled if not provided). Path of a live playlist
         * </pre>
         *
         * <code>string live_playlist_name = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLivePlaylistName() {
            livePlaylistName_ = getDefaultInstance().getLivePlaylistName();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, disabled if not provided). Path of a live playlist
         * </pre>
         *
         * <code>string live_playlist_name = 11;</code>
         *
         * @param value The bytes for livePlaylistName to set.
         * @return This builder for chaining.
         */
        public Builder setLivePlaylistNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            livePlaylistName_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private int segmentDuration_;

        /**
         * <pre>
         * in seconds (optional)
         * </pre>
         *
         * <code>uint32 segment_duration = 4;</code>
         *
         * @return The segmentDuration.
         */
        @java.lang.Override
        public int getSegmentDuration() {
            return segmentDuration_;
        }

        /**
         * <pre>
         * in seconds (optional)
         * </pre>
         *
         * <code>uint32 segment_duration = 4;</code>
         *
         * @param value The segmentDuration to set.
         * @return This builder for chaining.
         */
        public Builder setSegmentDuration(int value) {

            segmentDuration_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * in seconds (optional)
         * </pre>
         *
         * <code>uint32 segment_duration = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSegmentDuration() {
            bitField0_ &= ~0x00000010;
            segmentDuration_ = 0;
            onChanged();
            return this;
        }

        private int filenameSuffix_ = 0;

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
         *
         * @return The enum numeric value on the wire for filenameSuffix.
         */
        @java.lang.Override
        public int getFilenameSuffixValue() {
            return filenameSuffix_;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
         *
         * @param value The enum numeric value on the wire for filenameSuffix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenameSuffixValue(int value) {
            filenameSuffix_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
         *
         * @return The filenameSuffix.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix getFilenameSuffix() {
            im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix result =
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix
                            .forNumber(filenameSuffix_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
         *
         * @param value The filenameSuffix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenameSuffix(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileSuffix value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000020;
            filenameSuffix_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.SegmentedFileSuffix filename_suffix = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilenameSuffix() {
            bitField0_ &= ~0x00000020;
            filenameSuffix_ = 0;
            onChanged();
            return this;
        }

        private boolean disableManifest_;

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 8;</code>
         *
         * @return The disableManifest.
         */
        @java.lang.Override
        public boolean getDisableManifest() {
            return disableManifest_;
        }

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 8;</code>
         *
         * @param value The disableManifest to set.
         * @return This builder for chaining.
         */
        public Builder setDisableManifest(boolean value) {

            disableManifest_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDisableManifest() {
            bitField0_ &= ~0x00000040;
            disableManifest_ = false;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.S3Upload, im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder, im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder> s3Builder_;

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         *
         * @return Whether the s3 field is set.
         */
        @java.lang.Override
        public boolean hasS3() {
            return outputCase_ == 5;
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         *
         * @return The s3.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            } else {
                if (outputCase_ == 5) {
                    return s3Builder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        public Builder setS3(im.turms.plugin.livekit.core.proto.egress.S3Upload value) {
            if (s3Builder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                s3Builder_.setMessage(value);
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        public Builder setS3(
                im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder builderForValue) {
            if (s3Builder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                s3Builder_.setMessage(builderForValue.build());
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        public Builder mergeS3(im.turms.plugin.livekit.core.proto.egress.S3Upload value) {
            if (s3Builder_ == null) {
                if (outputCase_ == 5
                        && output_ != im.turms.plugin.livekit.core.proto.egress.S3Upload
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.S3Upload
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 5) {
                    s3Builder_.mergeFrom(value);
                } else {
                    s3Builder_.setMessage(value);
                }
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        public Builder clearS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 5) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 5) {
                    outputCase_ = 0;
                    output_ = null;
                }
                s3Builder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder getS3Builder() {
            return getS3FieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
            if ((outputCase_ == 5) && (s3Builder_ != null)) {
                return s3Builder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.S3Upload, im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder, im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder> getS3FieldBuilder() {
            if (s3Builder_ == null) {
                if (!(outputCase_ == 5)) {
                    output_ =
                            im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
                }
                s3Builder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 5;
            onChanged();
            return s3Builder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> gcpBuilder_;

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         *
         * @return Whether the gcp field is set.
         */
        @java.lang.Override
        public boolean hasGcp() {
            return outputCase_ == 6;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         *
         * @return The gcp.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 6) {
                    return gcpBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        public Builder setGcp(im.turms.plugin.livekit.core.proto.egress.GCPUpload value) {
            if (gcpBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                gcpBuilder_.setMessage(value);
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        public Builder setGcp(
                im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder builderForValue) {
            if (gcpBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                gcpBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        public Builder mergeGcp(im.turms.plugin.livekit.core.proto.egress.GCPUpload value) {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 6
                        && output_ != im.turms.plugin.livekit.core.proto.egress.GCPUpload
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.GCPUpload
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 6) {
                    gcpBuilder_.mergeFrom(value);
                } else {
                    gcpBuilder_.setMessage(value);
                }
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        public Builder clearGcp() {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 6) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 6) {
                    outputCase_ = 0;
                    output_ = null;
                }
                gcpBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder getGcpBuilder() {
            return getGcpFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
            if ((outputCase_ == 6) && (gcpBuilder_ != null)) {
                return gcpBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> getGcpFieldBuilder() {
            if (gcpBuilder_ == null) {
                if (!(outputCase_ == 6)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.GCPUpload
                            .getDefaultInstance();
                }
                gcpBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 6;
            onChanged();
            return gcpBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> azureBuilder_;

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         *
         * @return Whether the azure field is set.
         */
        @java.lang.Override
        public boolean hasAzure() {
            return outputCase_ == 7;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         *
         * @return The azure.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 7) {
                    return azureBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        public Builder setAzure(im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload value) {
            if (azureBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                azureBuilder_.setMessage(value);
            }
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        public Builder setAzure(
                im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder builderForValue) {
            if (azureBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                azureBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        public Builder mergeAzure(im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload value) {
            if (azureBuilder_ == null) {
                if (outputCase_ == 7
                        && output_ != im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 7) {
                    azureBuilder_.mergeFrom(value);
                } else {
                    azureBuilder_.setMessage(value);
                }
            }
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        public Builder clearAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 7) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 7) {
                    outputCase_ = 0;
                    output_ = null;
                }
                azureBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder getAzureBuilder() {
            return getAzureFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
            if ((outputCase_ == 7) && (azureBuilder_ != null)) {
                return azureBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 7;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> getAzureFieldBuilder() {
            if (azureBuilder_ == null) {
                if (!(outputCase_ == 7)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                            .getDefaultInstance();
                }
                azureBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 7;
            onChanged();
            return azureBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> aliOSSBuilder_;

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         *
         * @return Whether the aliOSS field is set.
         */
        @java.lang.Override
        public boolean hasAliOSS() {
            return outputCase_ == 9;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         *
         * @return The aliOSS.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 9) {
                    return aliOSSBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        public Builder setAliOSS(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload value) {
            if (aliOSSBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                aliOSSBuilder_.setMessage(value);
            }
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        public Builder setAliOSS(
                im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder builderForValue) {
            if (aliOSSBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                aliOSSBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        public Builder mergeAliOSS(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload value) {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 9
                        && output_ != im.turms.plugin.livekit.core.proto.egress.AliOSSUpload
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 9) {
                    aliOSSBuilder_.mergeFrom(value);
                } else {
                    aliOSSBuilder_.setMessage(value);
                }
            }
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        public Builder clearAliOSS() {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 9) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 9) {
                    outputCase_ = 0;
                    output_ = null;
                }
                aliOSSBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder getAliOSSBuilder() {
            return getAliOSSFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
            if ((outputCase_ == 9) && (aliOSSBuilder_ != null)) {
                return aliOSSBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 9;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> getAliOSSFieldBuilder() {
            if (aliOSSBuilder_ == null) {
                if (!(outputCase_ == 9)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AliOSSUpload
                            .getDefaultInstance();
                }
                aliOSSBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 9;
            onChanged();
            return aliOSSBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SegmentedFileOutput)
    }

    // @@protoc_insertion_point(class_scope:livekit.SegmentedFileOutput)
    private static final im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput();
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SegmentedFileOutput> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SegmentedFileOutput parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<SegmentedFileOutput> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SegmentedFileOutput> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}