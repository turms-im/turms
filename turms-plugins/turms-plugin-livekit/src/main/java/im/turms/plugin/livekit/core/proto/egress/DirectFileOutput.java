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
 * Protobuf type {@code livekit.DirectFileOutput}
 */
public final class DirectFileOutput extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.DirectFileOutput)
        DirectFileOutputOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                DirectFileOutput.class.getName());
    }

    // Use DirectFileOutput.newBuilder() to construct.
    private DirectFileOutput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private DirectFileOutput() {
        filepath_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_DirectFileOutput_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_DirectFileOutput_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.class,
                        im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        S3(2),
        GCP(3),
        AZURE(4),
        ALIOSS(6),
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
                case 2 -> S3;
                case 3 -> GCP;
                case 4 -> AZURE;
                case 6 -> ALIOSS;
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

    public static final int FILEPATH_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object filepath_ = "";

    /**
     * <pre>
     * see egress docs for templating (default {track_id}-{time})
     * </pre>
     *
     * <code>string filepath = 1;</code>
     *
     * @return The filepath.
     */
    @java.lang.Override
    public java.lang.String getFilepath() {
        java.lang.Object ref = filepath_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            filepath_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * see egress docs for templating (default {track_id}-{time})
     * </pre>
     *
     * <code>string filepath = 1;</code>
     *
     * @return The bytes for filepath.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getFilepathBytes() {
        java.lang.Object ref = filepath_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            filepath_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int DISABLE_MANIFEST_FIELD_NUMBER = 5;
    private boolean disableManifest_ = false;

    /**
     * <pre>
     * disable upload of manifest file (default false)
     * </pre>
     *
     * <code>bool disable_manifest = 5;</code>
     *
     * @return The disableManifest.
     */
    @java.lang.Override
    public boolean getDisableManifest() {
        return disableManifest_;
    }

    public static final int S3_FIELD_NUMBER = 2;

    /**
     * <code>.livekit.S3Upload s3 = 2;</code>
     *
     * @return Whether the s3 field is set.
     */
    @java.lang.Override
    public boolean hasS3() {
        return outputCase_ == 2;
    }

    /**
     * <code>.livekit.S3Upload s3 = 2;</code>
     *
     * @return The s3.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
        if (outputCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    /**
     * <code>.livekit.S3Upload s3 = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
        if (outputCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
    }

    public static final int GCP_FIELD_NUMBER = 3;

    /**
     * <code>.livekit.GCPUpload gcp = 3;</code>
     *
     * @return Whether the gcp field is set.
     */
    @java.lang.Override
    public boolean hasGcp() {
        return outputCase_ == 3;
    }

    /**
     * <code>.livekit.GCPUpload gcp = 3;</code>
     *
     * @return The gcp.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
        if (outputCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.GCPUpload gcp = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
        if (outputCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
    }

    public static final int AZURE_FIELD_NUMBER = 4;

    /**
     * <code>.livekit.AzureBlobUpload azure = 4;</code>
     *
     * @return Whether the azure field is set.
     */
    @java.lang.Override
    public boolean hasAzure() {
        return outputCase_ == 4;
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 4;</code>
     *
     * @return The azure.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
        if (outputCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AzureBlobUpload azure = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
        if (outputCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
    }

    public static final int ALIOSS_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
     *
     * @return Whether the aliOSS field is set.
     */
    @java.lang.Override
    public boolean hasAliOSS() {
        return outputCase_ == 6;
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
     *
     * @return The aliOSS.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
    }

    /**
     * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
        if (outputCase_ == 6) {
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filepath_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, filepath_);
        }
        if (outputCase_ == 2) {
            output.writeMessage(2, (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 3) {
            output.writeMessage(3, (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 4) {
            output.writeMessage(4,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (disableManifest_) {
            output.writeBool(5, disableManifest_);
        }
        if (outputCase_ == 6) {
            output.writeMessage(6,
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filepath_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, filepath_);
        }
        if (outputCase_ == 2) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2,
                    (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_);
        }
        if (outputCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3,
                    (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_);
        }
        if (outputCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_);
        }
        if (disableManifest_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(5, disableManifest_);
        }
        if (outputCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
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
        if (!(obj instanceof DirectFileOutput other)) {
            return super.equals(obj);
        }

        if (!getFilepath().equals(other.getFilepath())) {
            return false;
        }
        if (getDisableManifest() != other.getDisableManifest()) {
            return false;
        }
        if (!getOutputCase().equals(other.getOutputCase())) {
            return false;
        }
        switch (outputCase_) {
            case 2 -> {
                if (!getS3().equals(other.getS3())) {
                    return false;
                }
            }
            case 3 -> {
                if (!getGcp().equals(other.getGcp())) {
                    return false;
                }
            }
            case 4 -> {
                if (!getAzure().equals(other.getAzure())) {
                    return false;
                }
            }
            case 6 -> {
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
        hash = (37 * hash) + FILEPATH_FIELD_NUMBER;
        hash = (53 * hash) + getFilepath().hashCode();
        hash = (37 * hash) + DISABLE_MANIFEST_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableManifest());
        switch (outputCase_) {
            case 2 -> {
                hash = (37 * hash) + S3_FIELD_NUMBER;
                hash = (53 * hash) + getS3().hashCode();
            }
            case 3 -> {
                hash = (37 * hash) + GCP_FIELD_NUMBER;
                hash = (53 * hash) + getGcp().hashCode();
            }
            case 4 -> {
                hash = (37 * hash) + AZURE_FIELD_NUMBER;
                hash = (53 * hash) + getAzure().hashCode();
            }
            case 6 -> {
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

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.DirectFileOutput prototype) {
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
     * Protobuf type {@code livekit.DirectFileOutput}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.DirectFileOutput)
            im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_DirectFileOutput_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_DirectFileOutput_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.class,
                            im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            filepath_ = "";
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
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_DirectFileOutput_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput build() {
            im.turms.plugin.livekit.core.proto.egress.DirectFileOutput result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.DirectFileOutput result =
                    new im.turms.plugin.livekit.core.proto.egress.DirectFileOutput(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.DirectFileOutput result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.filepath_ = filepath_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.disableManifest_ = disableManifest_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.DirectFileOutput result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 2 && s3Builder_ != null) {
                result.output_ = s3Builder_.build();
            }
            if (outputCase_ == 3 && gcpBuilder_ != null) {
                result.output_ = gcpBuilder_.build();
            }
            if (outputCase_ == 4 && azureBuilder_ != null) {
                result.output_ = azureBuilder_.build();
            }
            if (outputCase_ == 6 && aliOSSBuilder_ != null) {
                result.output_ = aliOSSBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.DirectFileOutput other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getFilepath()
                    .isEmpty()) {
                filepath_ = other.filepath_;
                bitField0_ |= 0x00000001;
                onChanged();
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
                        case 10 -> {
                            filepath_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            input.readMessage(getS3FieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 2;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getGcpFieldBuilder().getBuilder(), extensionRegistry);
                            outputCase_ = 3;
                        } // case 26
                        case 34 -> {
                            input.readMessage(getAzureFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 4;
                        } // case 34
                        case 40 -> {
                            disableManifest_ = input.readBool();
                            bitField0_ |= 0x00000002;
                        } // case 40
                        case 50 -> {
                            input.readMessage(getAliOSSFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 6;
                        } // case 50
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

        private java.lang.Object filepath_ = "";

        /**
         * <pre>
         * see egress docs for templating (default {track_id}-{time})
         * </pre>
         *
         * <code>string filepath = 1;</code>
         *
         * @return The filepath.
         */
        public java.lang.String getFilepath() {
            java.lang.Object ref = filepath_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                filepath_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * see egress docs for templating (default {track_id}-{time})
         * </pre>
         *
         * <code>string filepath = 1;</code>
         *
         * @return The bytes for filepath.
         */
        public com.google.protobuf.ByteString getFilepathBytes() {
            java.lang.Object ref = filepath_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                filepath_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * see egress docs for templating (default {track_id}-{time})
         * </pre>
         *
         * <code>string filepath = 1;</code>
         *
         * @param value The filepath to set.
         * @return This builder for chaining.
         */
        public Builder setFilepath(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            filepath_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * see egress docs for templating (default {track_id}-{time})
         * </pre>
         *
         * <code>string filepath = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilepath() {
            filepath_ = getDefaultInstance().getFilepath();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * see egress docs for templating (default {track_id}-{time})
         * </pre>
         *
         * <code>string filepath = 1;</code>
         *
         * @param value The bytes for filepath to set.
         * @return This builder for chaining.
         */
        public Builder setFilepathBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            filepath_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private boolean disableManifest_;

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 5;</code>
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
         * <code>bool disable_manifest = 5;</code>
         *
         * @param value The disableManifest to set.
         * @return This builder for chaining.
         */
        public Builder setDisableManifest(boolean value) {

            disableManifest_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * disable upload of manifest file (default false)
         * </pre>
         *
         * <code>bool disable_manifest = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDisableManifest() {
            bitField0_ &= ~0x00000002;
            disableManifest_ = false;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.S3Upload, im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder, im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder> s3Builder_;

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         *
         * @return Whether the s3 field is set.
         */
        @java.lang.Override
        public boolean hasS3() {
            return outputCase_ == 2;
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         *
         * @return The s3.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload getS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 2) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            } else {
                if (outputCase_ == 2) {
                    return s3Builder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
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
            outputCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        public Builder setS3(
                im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder builderForValue) {
            if (s3Builder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                s3Builder_.setMessage(builderForValue.build());
            }
            outputCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        public Builder mergeS3(im.turms.plugin.livekit.core.proto.egress.S3Upload value) {
            if (s3Builder_ == null) {
                if (outputCase_ == 2
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
                if (outputCase_ == 2) {
                    s3Builder_.mergeFrom(value);
                } else {
                    s3Builder_.setMessage(value);
                }
            }
            outputCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        public Builder clearS3() {
            if (s3Builder_ == null) {
                if (outputCase_ == 2) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 2) {
                    outputCase_ = 0;
                    output_ = null;
                }
                s3Builder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder getS3Builder() {
            return getS3FieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder getS3OrBuilder() {
            if ((outputCase_ == 2) && (s3Builder_ != null)) {
                return s3Builder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 2) {
                    return (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.S3Upload s3 = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.S3Upload, im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder, im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder> getS3FieldBuilder() {
            if (s3Builder_ == null) {
                if (!(outputCase_ == 2)) {
                    output_ =
                            im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
                }
                s3Builder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.S3Upload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 2;
            onChanged();
            return s3Builder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> gcpBuilder_;

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         *
         * @return Whether the gcp field is set.
         */
        @java.lang.Override
        public boolean hasGcp() {
            return outputCase_ == 3;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         *
         * @return The gcp.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload getGcp() {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 3) {
                    return gcpBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
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
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        public Builder setGcp(
                im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder builderForValue) {
            if (gcpBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                gcpBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        public Builder mergeGcp(im.turms.plugin.livekit.core.proto.egress.GCPUpload value) {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 3
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
                if (outputCase_ == 3) {
                    gcpBuilder_.mergeFrom(value);
                } else {
                    gcpBuilder_.setMessage(value);
                }
            }
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        public Builder clearGcp() {
            if (gcpBuilder_ == null) {
                if (outputCase_ == 3) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 3) {
                    outputCase_ = 0;
                    output_ = null;
                }
                gcpBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder getGcpBuilder() {
            return getGcpFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder getGcpOrBuilder() {
            if ((outputCase_ == 3) && (gcpBuilder_ != null)) {
                return gcpBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.GCPUpload gcp = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.GCPUpload, im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder, im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder> getGcpFieldBuilder() {
            if (gcpBuilder_ == null) {
                if (!(outputCase_ == 3)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.GCPUpload
                            .getDefaultInstance();
                }
                gcpBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.GCPUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 3;
            onChanged();
            return gcpBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> azureBuilder_;

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         *
         * @return Whether the azure field is set.
         */
        @java.lang.Override
        public boolean hasAzure() {
            return outputCase_ == 4;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         *
         * @return The azure.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 4) {
                    return azureBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
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
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        public Builder setAzure(
                im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder builderForValue) {
            if (azureBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                azureBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        public Builder mergeAzure(im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload value) {
            if (azureBuilder_ == null) {
                if (outputCase_ == 4
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
                if (outputCase_ == 4) {
                    azureBuilder_.mergeFrom(value);
                } else {
                    azureBuilder_.setMessage(value);
                }
            }
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        public Builder clearAzure() {
            if (azureBuilder_ == null) {
                if (outputCase_ == 4) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 4) {
                    outputCase_ = 0;
                    output_ = null;
                }
                azureBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder getAzureBuilder() {
            return getAzureFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder getAzureOrBuilder() {
            if ((outputCase_ == 4) && (azureBuilder_ != null)) {
                return azureBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AzureBlobUpload azure = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload, im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder> getAzureFieldBuilder() {
            if (azureBuilder_ == null) {
                if (!(outputCase_ == 4)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                            .getDefaultInstance();
                }
                azureBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 4;
            onChanged();
            return azureBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> aliOSSBuilder_;

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         *
         * @return Whether the aliOSS field is set.
         */
        @java.lang.Override
        public boolean hasAliOSS() {
            return outputCase_ == 6;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         *
         * @return The aliOSS.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getAliOSS() {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            } else {
                if (outputCase_ == 6) {
                    return aliOSSBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
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
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        public Builder setAliOSS(
                im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder builderForValue) {
            if (aliOSSBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                aliOSSBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        public Builder mergeAliOSS(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload value) {
            if (aliOSSBuilder_ == null) {
                if (outputCase_ == 6
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
                if (outputCase_ == 6) {
                    aliOSSBuilder_.mergeFrom(value);
                } else {
                    aliOSSBuilder_.setMessage(value);
                }
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        public Builder clearAliOSS() {
            if (aliOSSBuilder_ == null) {
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
                aliOSSBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder getAliOSSBuilder() {
            return getAliOSSFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder getAliOSSOrBuilder() {
            if ((outputCase_ == 6) && (aliOSSBuilder_ != null)) {
                return aliOSSBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.AliOSSUpload aliOSS = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AliOSSUpload, im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder, im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder> getAliOSSFieldBuilder() {
            if (aliOSSBuilder_ == null) {
                if (!(outputCase_ == 6)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.AliOSSUpload
                            .getDefaultInstance();
                }
                aliOSSBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 6;
            onChanged();
            return aliOSSBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.DirectFileOutput)
    }

    // @@protoc_insertion_point(class_scope:livekit.DirectFileOutput)
    private static final im.turms.plugin.livekit.core.proto.egress.DirectFileOutput DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.DirectFileOutput();
    }

    public static im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DirectFileOutput> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DirectFileOutput parsePartialFrom(
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

    public static com.google.protobuf.Parser<DirectFileOutput> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DirectFileOutput> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}