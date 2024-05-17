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
 * Protobuf type {@code livekit.ImageOutput}
 */
public final class ImageOutput extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ImageOutput)
        ImageOutputOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ImageOutput.class.getName());
    }

    // Use ImageOutput.newBuilder() to construct.
    private ImageOutput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ImageOutput() {
        filenamePrefix_ = "";
        filenameSuffix_ = 0;
        imageCodec_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ImageOutput_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ImageOutput_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.ImageOutput.class,
                        im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        S3(8),
        GCP(9),
        AZURE(10),
        ALIOSS(11),
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
                case 8 -> S3;
                case 9 -> GCP;
                case 10 -> AZURE;
                case 11 -> ALIOSS;
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

    public static final int CAPTURE_INTERVAL_FIELD_NUMBER = 1;
    private int captureInterval_ = 0;

    /**
     * <pre>
     * in seconds (required)
     * </pre>
     *
     * <code>uint32 capture_interval = 1;</code>
     *
     * @return The captureInterval.
     */
    @java.lang.Override
    public int getCaptureInterval() {
        return captureInterval_;
    }

    public static final int WIDTH_FIELD_NUMBER = 2;
    private int width_ = 0;

    /**
     * <pre>
     * (optional, defaults to track width)
     * </pre>
     *
     * <code>int32 width = 2;</code>
     *
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
        return width_;
    }

    public static final int HEIGHT_FIELD_NUMBER = 3;
    private int height_ = 0;

    /**
     * <pre>
     * (optional, defaults to track height)
     * </pre>
     *
     * <code>int32 height = 3;</code>
     *
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
        return height_;
    }

    public static final int FILENAME_PREFIX_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object filenamePrefix_ = "";

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string filename_prefix = 4;</code>
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
     * <code>string filename_prefix = 4;</code>
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

    public static final int FILENAME_SUFFIX_FIELD_NUMBER = 5;
    private int filenameSuffix_ = 0;

    /**
     * <pre>
     * (optional, default INDEX)
     * </pre>
     *
     * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
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
     * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
     *
     * @return The filenameSuffix.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix getFilenameSuffix() {
        im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix result =
                im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix
                        .forNumber(filenameSuffix_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix.UNRECOGNIZED
                : result;
    }

    public static final int IMAGE_CODEC_FIELD_NUMBER = 6;
    private int imageCodec_ = 0;

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.ImageCodec image_codec = 6;</code>
     *
     * @return The enum numeric value on the wire for imageCodec.
     */
    @java.lang.Override
    public int getImageCodecValue() {
        return imageCodec_;
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.ImageCodec image_codec = 6;</code>
     *
     * @return The imageCodec.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ImageCodec getImageCodec() {
        im.turms.plugin.livekit.core.proto.models.ImageCodec result =
                im.turms.plugin.livekit.core.proto.models.ImageCodec.forNumber(imageCodec_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ImageCodec.UNRECOGNIZED
                : result;
    }

    public static final int DISABLE_MANIFEST_FIELD_NUMBER = 7;
    private boolean disableManifest_ = false;

    /**
     * <pre>
     * disable upload of manifest file (default false)
     * </pre>
     *
     * <code>bool disable_manifest = 7;</code>
     *
     * @return The disableManifest.
     */
    @java.lang.Override
    public boolean getDisableManifest() {
        return disableManifest_;
    }

    public static final int S3_FIELD_NUMBER = 8;

    /**
     * <code>.livekit.S3Upload s3 = 8;</code>
     *
     * @return Whether the s3 field is set.
     */
    @java.lang.Override
    public boolean hasS3() {
        return outputCase_ == 8;
    }

    /**
     * <code>.livekit.S3Upload s3 = 8;</code>
     *
     * @return The s3.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
        if (outputCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    /**
     * <code>.livekit.S3Upload s3 = 8;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
        if (outputCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    public static final int GCP_FIELD_NUMBER = 9;

    /**
     * <code>.livekit.GCPUpload gcp = 9;</code>
     *
     * @return Whether the gcp field is set.
     */
    @java.lang.Override
    public boolean hasGcp() {
        return outputCase_ == 9;
    }

    /**
     * <code>.livekit.GCPUpload gcp = 9;</code>
     *
     * @return The gcp.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
        if (outputCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.GCPUpload gcp = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
        if (outputCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    public static final int AZURE_FIELD_NUMBER = 10;

    /**
     * <code>.livekit.AzureBlobUpload azure = 10;</code>
     *
     * @return Whether the azure field is set.
     */
    @java.lang.Override
    public boolean hasAzure() {
        return outputCase_ == 10;
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 10;</code>
     *
     * @return The azure.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
        if (outputCase_ == 10) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
        if (outputCase_ == 10) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    public static final int ALIOSS_FIELD_NUMBER = 11;

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
     *
     * @return Whether the aliOSS field is set.
     */
    @java.lang.Override
    public boolean hasAliOSS() {
        return outputCase_ == 11;
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
     *
     * @return The aliOSS.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
        if (outputCase_ == 11) {
            return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
        if (outputCase_ == 11) {
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
        if (captureInterval_ != 0) {
            output.writeUInt32(1, captureInterval_);
        }
        if (width_ != 0) {
            output.writeInt32(2, width_);
        }
        if (height_ != 0) {
            output.writeInt32(3, height_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filenamePrefix_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, filenamePrefix_);
        }
        if (filenameSuffix_ != im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix.IMAGE_SUFFIX_INDEX
                .getNumber()) {
            output.writeEnum(5, filenameSuffix_);
        }
        if (imageCodec_ != im.turms.plugin.livekit.core.proto.models.ImageCodec.IC_DEFAULT
                .getNumber()) {
            output.writeEnum(6, imageCodec_);
        }
        if (disableManifest_) {
            output.writeBool(7, disableManifest_);
        }
        if (outputCase_ == 8) {
            output.writeMessage(8, (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 9) {
            output.writeMessage(9, (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 10) {
            output.writeMessage(10,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (outputCase_ == 11) {
            output.writeMessage(11,
                    (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_);
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
        if (captureInterval_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(1, captureInterval_);
        }
        if (width_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, width_);
        }
        if (height_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, height_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filenamePrefix_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, filenamePrefix_);
        }
        if (filenameSuffix_ != im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix.IMAGE_SUFFIX_INDEX
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, filenameSuffix_);
        }
        if (imageCodec_ != im.turms.plugin.livekit.core.proto.models.ImageCodec.IC_DEFAULT
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(6, imageCodec_);
        }
        if (disableManifest_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(7, disableManifest_);
        }
        if (outputCase_ == 8) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                    (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 9) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                    (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 10) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (outputCase_ == 11) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(11,
                    (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_);
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
        if (!(obj instanceof ImageOutput other)) {
            return super.equals(obj);
        }

        if (getCaptureInterval() != other.getCaptureInterval()) {
            return false;
        }
        if (getWidth() != other.getWidth()) {
            return false;
        }
        if (getHeight() != other.getHeight()) {
            return false;
        }
        if (!getFilenamePrefix().equals(other.getFilenamePrefix())) {
            return false;
        }
        if (filenameSuffix_ != other.filenameSuffix_) {
            return false;
        }
        if (imageCodec_ != other.imageCodec_) {
            return false;
        }
        if (getDisableManifest() != other.getDisableManifest()) {
            return false;
        }
        if (!getOutputCase().equals(other.getOutputCase())) {
            return false;
        }
        switch (outputCase_) {
            case 8 -> {
                if (!getS3().equals(other.getS3())) {
                    return false;
                }
            }
            case 9 -> {
                if (!getGcp().equals(other.getGcp())) {
                    return false;
                }
            }
            case 10 -> {
                if (!getAzure().equals(other.getAzure())) {
                    return false;
                }
            }
            case 11 -> {
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
        hash = (37 * hash) + CAPTURE_INTERVAL_FIELD_NUMBER;
        hash = (53 * hash) + getCaptureInterval();
        hash = (37 * hash) + WIDTH_FIELD_NUMBER;
        hash = (53 * hash) + getWidth();
        hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getHeight();
        hash = (37 * hash) + FILENAME_PREFIX_FIELD_NUMBER;
        hash = (53 * hash) + getFilenamePrefix().hashCode();
        hash = (37 * hash) + FILENAME_SUFFIX_FIELD_NUMBER;
        hash = (53 * hash) + filenameSuffix_;
        hash = (37 * hash) + IMAGE_CODEC_FIELD_NUMBER;
        hash = (53 * hash) + imageCodec_;
        hash = (37 * hash) + DISABLE_MANIFEST_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableManifest());
        switch (outputCase_) {
            case 8 -> {
                hash = (37 * hash) + S3_FIELD_NUMBER;
                hash = (53 * hash) + getS3().hashCode();
            }
            case 9 -> {
                hash = (37 * hash) + GCP_FIELD_NUMBER;
                hash = (53 * hash) + getGcp().hashCode();
            }
            case 10 -> {
                hash = (37 * hash) + AZURE_FIELD_NUMBER;
                hash = (53 * hash) + getAzure().hashCode();
            }
            case 11 -> {
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

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.ImageOutput prototype) {
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
     * Protobuf type {@code livekit.ImageOutput}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ImageOutput)
            im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ImageOutput_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ImageOutput_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.ImageOutput.class,
                            im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.ImageOutput.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            captureInterval_ = 0;
            width_ = 0;
            height_ = 0;
            filenamePrefix_ = "";
            filenameSuffix_ = 0;
            imageCodec_ = 0;
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
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ImageOutput_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput build() {
            im.turms.plugin.livekit.core.proto.egress.ImageOutput result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.ImageOutput result =
                    new im.turms.plugin.livekit.core.proto.egress.ImageOutput(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.ImageOutput result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.captureInterval_ = captureInterval_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.width_ = width_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.height_ = height_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.filenamePrefix_ = filenamePrefix_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.filenameSuffix_ = filenameSuffix_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.imageCodec_ = imageCodec_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.disableManifest_ = disableManifest_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.ImageOutput result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 8 && s3Builder_ != null) {
                result.output_ = s3Builder_.build();
            }
            if (outputCase_ == 9 && gcpBuilder_ != null) {
                result.output_ = gcpBuilder_.build();
            }
            if (outputCase_ == 10 && azureBuilder_ != null) {
                result.output_ = azureBuilder_.build();
            }
            if (outputCase_ == 11 && aliOSSBuilder_ != null) {
                result.output_ = aliOSSBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.ImageOutput) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.ImageOutput) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.ImageOutput other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.ImageOutput
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getCaptureInterval() != 0) {
                setCaptureInterval(other.getCaptureInterval());
            }
            if (other.getWidth() != 0) {
                setWidth(other.getWidth());
            }
            if (other.getHeight() != 0) {
                setHeight(other.getHeight());
            }
            if (!other.getFilenamePrefix()
                    .isEmpty()) {
                filenamePrefix_ = other.filenamePrefix_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.filenameSuffix_ != 0) {
                setFilenameSuffixValue(other.getFilenameSuffixValue());
            }
            if (other.imageCodec_ != 0) {
                setImageCodecValue(other.getImageCodecValue());
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
                            captureInterval_ = input.readUInt32();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            width_ = input.readInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            height_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            filenamePrefix_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 40 -> {
                            filenameSuffix_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            imageCodec_ = input.readEnum();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            disableManifest_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 66 -> {
                            input.readMessage(getS3FieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 8;
                        } // case 66
                        case 74 -> {
                            input.readMessage(getGcpFieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 9;
                        } // case 74
                        case 82 -> {
                            input.readMessage(getAzureFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 10;
                        } // case 82
                        case 90 -> {
                            input.readMessage(getAliOSSFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 11;
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

        private int captureInterval_;

        /**
         * <pre>
         * in seconds (required)
         * </pre>
         *
         * <code>uint32 capture_interval = 1;</code>
         *
         * @return The captureInterval.
         */
        @java.lang.Override
        public int getCaptureInterval() {
            return captureInterval_;
        }

        /**
         * <pre>
         * in seconds (required)
         * </pre>
         *
         * <code>uint32 capture_interval = 1;</code>
         *
         * @param value The captureInterval to set.
         * @return This builder for chaining.
         */
        public Builder setCaptureInterval(int value) {

            captureInterval_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * in seconds (required)
         * </pre>
         *
         * <code>uint32 capture_interval = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCaptureInterval() {
            bitField0_ &= ~0x00000001;
            captureInterval_ = 0;
            onChanged();
            return this;
        }

        private int width_;

        /**
         * <pre>
         * (optional, defaults to track width)
         * </pre>
         *
         * <code>int32 width = 2;</code>
         *
         * @return The width.
         */
        @java.lang.Override
        public int getWidth() {
            return width_;
        }

        /**
         * <pre>
         * (optional, defaults to track width)
         * </pre>
         *
         * <code>int32 width = 2;</code>
         *
         * @param value The width to set.
         * @return This builder for chaining.
         */
        public Builder setWidth(int value) {

            width_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, defaults to track width)
         * </pre>
         *
         * <code>int32 width = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWidth() {
            bitField0_ &= ~0x00000002;
            width_ = 0;
            onChanged();
            return this;
        }

        private int height_;

        /**
         * <pre>
         * (optional, defaults to track height)
         * </pre>
         *
         * <code>int32 height = 3;</code>
         *
         * @return The height.
         */
        @java.lang.Override
        public int getHeight() {
            return height_;
        }

        /**
         * <pre>
         * (optional, defaults to track height)
         * </pre>
         *
         * <code>int32 height = 3;</code>
         *
         * @param value The height to set.
         * @return This builder for chaining.
         */
        public Builder setHeight(int value) {

            height_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, defaults to track height)
         * </pre>
         *
         * <code>int32 height = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeight() {
            bitField0_ &= ~0x00000004;
            height_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object filenamePrefix_ = "";

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 4;</code>
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
         * <code>string filename_prefix = 4;</code>
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
         * <code>string filename_prefix = 4;</code>
         *
         * @param value The filenamePrefix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenamePrefix(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            filenamePrefix_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilenamePrefix() {
            filenamePrefix_ = getDefaultInstance().getFilenamePrefix();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string filename_prefix = 4;</code>
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
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private int filenameSuffix_ = 0;

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
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
         * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
         *
         * @param value The enum numeric value on the wire for filenameSuffix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenameSuffixValue(int value) {
            filenameSuffix_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
         *
         * @return The filenameSuffix.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix getFilenameSuffix() {
            im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix result =
                    im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix
                            .forNumber(filenameSuffix_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
         *
         * @param value The filenameSuffix to set.
         * @return This builder for chaining.
         */
        public Builder setFilenameSuffix(
                im.turms.plugin.livekit.core.proto.egress.ImageFileSuffix value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            filenameSuffix_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, default INDEX)
         * </pre>
         *
         * <code>.livekit.ImageFileSuffix filename_suffix = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilenameSuffix() {
            bitField0_ &= ~0x00000010;
            filenameSuffix_ = 0;
            onChanged();
            return this;
        }

        private int imageCodec_ = 0;

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.ImageCodec image_codec = 6;</code>
         *
         * @return The enum numeric value on the wire for imageCodec.
         */
        @java.lang.Override
        public int getImageCodecValue() {
            return imageCodec_;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.ImageCodec image_codec = 6;</code>
         *
         * @param value The enum numeric value on the wire for imageCodec to set.
         * @return This builder for chaining.
         */
        public Builder setImageCodecValue(int value) {
            imageCodec_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.ImageCodec image_codec = 6;</code>
         *
         * @return The imageCodec.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ImageCodec getImageCodec() {
            im.turms.plugin.livekit.core.proto.models.ImageCodec result =
                    im.turms.plugin.livekit.core.proto.models.ImageCodec.forNumber(imageCodec_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ImageCodec.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.ImageCodec image_codec = 6;</code>
         *
         * @param value The imageCodec to set.
         * @return This builder for chaining.
         */
        public Builder setImageCodec(im.turms.plugin.livekit.core.proto.models.ImageCodec value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000020;
            imageCodec_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.ImageCodec image_codec = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearImageCodec() {
            bitField0_ &= ~0x00000020;
            imageCodec_ = 0;
            onChanged();
            return this;
        }

        private boolean disableManifest_;

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 7;</code>
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
         * <code>bool disable_manifest = 7;</code>
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
         * <code>bool disable_manifest = 7;</code>
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
         * <code>.livekit.S3Upload s3 = 8;</code>
         *
         * @return Whether the s3 field is set.
         */
        @java.lang.Override
        public boolean hasS3() {
            return outputCase_ == 8;
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         *
         * @return The s3.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            } else {
                if (outputCase_ == 8) {
                    return s3Builder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
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
            outputCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        public Builder setS3(
                im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder builderForValue) {
            if (s3Builder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                s3Builder_.setMessage(builderForValue.build());
            }
            outputCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        public Builder mergeS3(im.turms.plugin.livekit.core.proto.egress.S3Upload value) {
            if (s3Builder_ == null) {
                if (outputCase_ == 8
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
                if (outputCase_ == 8) {
                    s3Builder_.mergeFrom(value);
                } else {
                    s3Builder_.setMessage(value);
                }
            }
            outputCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        public Builder clearS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 8) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 8) {
                    outputCase_ = 0;
                    output_ = null;
                }
                s3Builder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder getS3Builder() {
            return getS3FieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
            if ((outputCase_ == 8) && (s3Builder_ != null)) {
                return s3Builder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 8;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.S3Upload, im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder, im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder> getS3FieldBuilder() {
            if (s3Builder_ == null) {
                if (!(outputCase_ == 8)) {
                    output_ =
                            im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
                }
                s3Builder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 8;
            onChanged();
            return s3Builder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> gcpBuilder_;

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         *
         * @return Whether the gcp field is set.
         */
        @java.lang.Override
        public boolean hasGcp() {
            return outputCase_ == 9;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         *
         * @return The gcp.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 9) {
                    return gcpBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
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
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        public Builder setGcp(
                im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder builderForValue) {
            if (gcpBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                gcpBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        public Builder mergeGcp(im.turms.plugin.livekit.core.proto.egress.GCPUpload value) {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 9
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
                if (outputCase_ == 9) {
                    gcpBuilder_.mergeFrom(value);
                } else {
                    gcpBuilder_.setMessage(value);
                }
            }
            outputCase_ = 9;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        public Builder clearGcp() {
            if (gcpBuilder_ == null) {
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
                gcpBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder getGcpBuilder() {
            return getGcpFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
            if ((outputCase_ == 9) && (gcpBuilder_ != null)) {
                return gcpBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 9;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> getGcpFieldBuilder() {
            if (gcpBuilder_ == null) {
                if (!(outputCase_ == 9)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.GCPUpload
                            .getDefaultInstance();
                }
                gcpBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 9;
            onChanged();
            return gcpBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> azureBuilder_;

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         *
         * @return Whether the azure field is set.
         */
        @java.lang.Override
        public boolean hasAzure() {
            return outputCase_ == 10;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         *
         * @return The azure.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 10) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 10) {
                    return azureBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
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
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        public Builder setAzure(
                im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder builderForValue) {
            if (azureBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                azureBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        public Builder mergeAzure(im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload value) {
            if (azureBuilder_ == null) {
                if (outputCase_ == 10
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
                if (outputCase_ == 10) {
                    azureBuilder_.mergeFrom(value);
                } else {
                    azureBuilder_.setMessage(value);
                }
            }
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        public Builder clearAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 10) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 10) {
                    outputCase_ = 0;
                    output_ = null;
                }
                azureBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder getAzureBuilder() {
            return getAzureFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
            if ((outputCase_ == 10) && (azureBuilder_ != null)) {
                return azureBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 10) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 10;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> getAzureFieldBuilder() {
            if (azureBuilder_ == null) {
                if (!(outputCase_ == 10)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                            .getDefaultInstance();
                }
                azureBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 10;
            onChanged();
            return azureBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> aliOSSBuilder_;

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         *
         * @return Whether the aliOSS field is set.
         */
        @java.lang.Override
        public boolean hasAliOSS() {
            return outputCase_ == 11;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         *
         * @return The aliOSS.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 11) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 11) {
                    return aliOSSBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
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
            outputCase_ = 11;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        public Builder setAliOSS(
                im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder builderForValue) {
            if (aliOSSBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                aliOSSBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 11;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        public Builder mergeAliOSS(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload value) {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 11
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
                if (outputCase_ == 11) {
                    aliOSSBuilder_.mergeFrom(value);
                } else {
                    aliOSSBuilder_.setMessage(value);
                }
            }
            outputCase_ = 11;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        public Builder clearAliOSS() {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 11) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 11) {
                    outputCase_ = 0;
                    output_ = null;
                }
                aliOSSBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder getAliOSSBuilder() {
            return getAliOSSFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
            if ((outputCase_ == 11) && (aliOSSBuilder_ != null)) {
                return aliOSSBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 11) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 11;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> getAliOSSFieldBuilder() {
            if (aliOSSBuilder_ == null) {
                if (!(outputCase_ == 11)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AliOSSUpload
                            .getDefaultInstance();
                }
                aliOSSBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 11;
            onChanged();
            return aliOSSBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ImageOutput)
    }

    // @@protoc_insertion_point(class_scope:livekit.ImageOutput)
    private static final im.turms.plugin.livekit.core.proto.egress.ImageOutput DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.ImageOutput();
    }

    public static im.turms.plugin.livekit.core.proto.egress.ImageOutput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ImageOutput> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ImageOutput parsePartialFrom(
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

    public static com.google.protobuf.Parser<ImageOutput> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ImageOutput> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageOutput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}