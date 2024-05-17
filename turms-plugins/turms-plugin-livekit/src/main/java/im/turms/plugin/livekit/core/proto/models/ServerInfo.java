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

/**
 * <pre>
 * details about the server
 * </pre>
 *
 * Protobuf type {@code livekit.ServerInfo}
 */
public final class ServerInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ServerInfo)
        ServerInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ServerInfo.class.getName());
    }

    // Use ServerInfo.newBuilder() to construct.
    private ServerInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ServerInfo() {
        edition_ = 0;
        version_ = "";
        region_ = "";
        nodeId_ = "";
        debugInfo_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ServerInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ServerInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ServerInfo.class,
                        im.turms.plugin.livekit.core.proto.models.ServerInfo.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.ServerInfo.Edition}
     */
    public enum Edition implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <code>Standard = 0;</code>
         */
        Standard(0),
        /**
         * <code>Cloud = 1;</code>
         */
        Cloud(1),
        UNRECOGNIZED(-1),;

        static {
            com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                    com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                    /* major= */ 4,
                    /* minor= */ 26,
                    /* patch= */ 1,
                    /* suffix= */ "",
                    Edition.class.getName());
        }
        /**
         * <code>Standard = 0;</code>
         */
        public static final int Standard_VALUE = 0;
        /**
         * <code>Cloud = 1;</code>
         */
        public static final int Cloud_VALUE = 1;

        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new java.lang.IllegalArgumentException(
                        "Can't get the number of an unknown enum value.");
            }
            return value;
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static Edition valueOf(int value) {
            return forNumber(value);
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         */
        public static Edition forNumber(int value) {
            return switch (value) {
                case 0 -> Standard;
                case 1 -> Cloud;
                default -> null;
            };
        }

        public static com.google.protobuf.Internal.EnumLiteMap<Edition> internalGetValueMap() {
            return internalValueMap;
        }

        private static final com.google.protobuf.Internal.EnumLiteMap<Edition> internalValueMap =
                Edition::forNumber;

        public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new java.lang.IllegalStateException(
                        "Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues()
                    .get(ordinal());
        }

        public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.ServerInfo.getDescriptor()
                    .getEnumTypes()
                    .get(0);
        }

        private static final Edition[] VALUES = values();

        public static Edition valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new java.lang.IllegalArgumentException(
                        "EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private final int value;

        Edition(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:livekit.ServerInfo.Edition)
    }

    public static final int EDITION_FIELD_NUMBER = 1;
    private int edition_ = 0;

    /**
     * <code>.livekit.ServerInfo.Edition edition = 1;</code>
     *
     * @return The enum numeric value on the wire for edition.
     */
    @java.lang.Override
    public int getEditionValue() {
        return edition_;
    }

    /**
     * <code>.livekit.ServerInfo.Edition edition = 1;</code>
     *
     * @return The edition.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition getEdition() {
        im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition result =
                im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition.forNumber(edition_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition.UNRECOGNIZED
                : result;
    }

    public static final int VERSION_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object version_ = "";

    /**
     * <code>string version = 2;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public java.lang.String getVersion() {
        java.lang.Object ref = version_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            version_ = s;
            return s;
        }
    }

    /**
     * <code>string version = 2;</code>
     *
     * @return The bytes for version.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getVersionBytes() {
        java.lang.Object ref = version_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            version_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PROTOCOL_FIELD_NUMBER = 3;
    private int protocol_ = 0;

    /**
     * <code>int32 protocol = 3;</code>
     *
     * @return The protocol.
     */
    @java.lang.Override
    public int getProtocol() {
        return protocol_;
    }

    public static final int REGION_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object region_ = "";

    /**
     * <code>string region = 4;</code>
     *
     * @return The region.
     */
    @java.lang.Override
    public java.lang.String getRegion() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            region_ = s;
            return s;
        }
    }

    /**
     * <code>string region = 4;</code>
     *
     * @return The bytes for region.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRegionBytes() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            region_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NODE_ID_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object nodeId_ = "";

    /**
     * <code>string node_id = 5;</code>
     *
     * @return The nodeId.
     */
    @java.lang.Override
    public java.lang.String getNodeId() {
        java.lang.Object ref = nodeId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            nodeId_ = s;
            return s;
        }
    }

    /**
     * <code>string node_id = 5;</code>
     *
     * @return The bytes for nodeId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNodeIdBytes() {
        java.lang.Object ref = nodeId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            nodeId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int DEBUG_INFO_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object debugInfo_ = "";

    /**
     * <pre>
     * additional debugging information. sent only if server is in development mode
     * </pre>
     *
     * <code>string debug_info = 6;</code>
     *
     * @return The debugInfo.
     */
    @java.lang.Override
    public java.lang.String getDebugInfo() {
        java.lang.Object ref = debugInfo_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            debugInfo_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * additional debugging information. sent only if server is in development mode
     * </pre>
     *
     * <code>string debug_info = 6;</code>
     *
     * @return The bytes for debugInfo.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getDebugInfoBytes() {
        java.lang.Object ref = debugInfo_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            debugInfo_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (edition_ != im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition.Standard
                .getNumber()) {
            output.writeEnum(1, edition_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(version_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, version_);
        }
        if (protocol_ != 0) {
            output.writeInt32(3, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(nodeId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, nodeId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(debugInfo_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, debugInfo_);
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
        if (edition_ != im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition.Standard
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, edition_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(version_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, version_);
        }
        if (protocol_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(nodeId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, nodeId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(debugInfo_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, debugInfo_);
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
        if (!(obj instanceof ServerInfo other)) {
            return super.equals(obj);
        }

        if (edition_ != other.edition_) {
            return false;
        }
        if (!getVersion().equals(other.getVersion())) {
            return false;
        }
        if (getProtocol() != other.getProtocol()) {
            return false;
        }
        if (!getRegion().equals(other.getRegion())) {
            return false;
        }
        if (!getNodeId().equals(other.getNodeId())) {
            return false;
        }
        if (!getDebugInfo().equals(other.getDebugInfo())) {
            return false;
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
        hash = (37 * hash) + EDITION_FIELD_NUMBER;
        hash = (53 * hash) + edition_;
        hash = (37 * hash) + VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getVersion().hashCode();
        hash = (37 * hash) + PROTOCOL_FIELD_NUMBER;
        hash = (53 * hash) + getProtocol();
        hash = (37 * hash) + REGION_FIELD_NUMBER;
        hash = (53 * hash) + getRegion().hashCode();
        hash = (37 * hash) + NODE_ID_FIELD_NUMBER;
        hash = (53 * hash) + getNodeId().hashCode();
        hash = (37 * hash) + DEBUG_INFO_FIELD_NUMBER;
        hash = (53 * hash) + getDebugInfo().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ServerInfo prototype) {
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
     * details about the server
     * </pre>
     *
     * Protobuf type {@code livekit.ServerInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ServerInfo)
            im.turms.plugin.livekit.core.proto.models.ServerInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ServerInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ServerInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ServerInfo.class,
                            im.turms.plugin.livekit.core.proto.models.ServerInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.ServerInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            edition_ = 0;
            version_ = "";
            protocol_ = 0;
            region_ = "";
            nodeId_ = "";
            debugInfo_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ServerInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ServerInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ServerInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ServerInfo build() {
            im.turms.plugin.livekit.core.proto.models.ServerInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ServerInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ServerInfo result =
                    new im.turms.plugin.livekit.core.proto.models.ServerInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.ServerInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.edition_ = edition_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.version_ = version_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.protocol_ = protocol_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.region_ = region_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.nodeId_ = nodeId_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.debugInfo_ = debugInfo_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ServerInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.ServerInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.ServerInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ServerInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (other.edition_ != 0) {
                setEditionValue(other.getEditionValue());
            }
            if (!other.getVersion()
                    .isEmpty()) {
                version_ = other.version_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getProtocol() != 0) {
                setProtocol(other.getProtocol());
            }
            if (!other.getRegion()
                    .isEmpty()) {
                region_ = other.region_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (!other.getNodeId()
                    .isEmpty()) {
                nodeId_ = other.nodeId_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (!other.getDebugInfo()
                    .isEmpty()) {
                debugInfo_ = other.debugInfo_;
                bitField0_ |= 0x00000020;
                onChanged();
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
                            edition_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            version_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            protocol_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            region_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            nodeId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            debugInfo_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
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

        private int bitField0_;

        private int edition_ = 0;

        /**
         * <code>.livekit.ServerInfo.Edition edition = 1;</code>
         *
         * @return The enum numeric value on the wire for edition.
         */
        @java.lang.Override
        public int getEditionValue() {
            return edition_;
        }

        /**
         * <code>.livekit.ServerInfo.Edition edition = 1;</code>
         *
         * @param value The enum numeric value on the wire for edition to set.
         * @return This builder for chaining.
         */
        public Builder setEditionValue(int value) {
            edition_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ServerInfo.Edition edition = 1;</code>
         *
         * @return The edition.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition getEdition() {
            im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition result =
                    im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition
                            .forNumber(edition_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ServerInfo.Edition edition = 1;</code>
         *
         * @param value The edition to set.
         * @return This builder for chaining.
         */
        public Builder setEdition(
                im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            edition_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ServerInfo.Edition edition = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEdition() {
            bitField0_ &= ~0x00000001;
            edition_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object version_ = "";

        /**
         * <code>string version = 2;</code>
         *
         * @return The version.
         */
        public java.lang.String getVersion() {
            java.lang.Object ref = version_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                version_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string version = 2;</code>
         *
         * @return The bytes for version.
         */
        public com.google.protobuf.ByteString getVersionBytes() {
            java.lang.Object ref = version_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                version_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string version = 2;</code>
         *
         * @param value The version to set.
         * @return This builder for chaining.
         */
        public Builder setVersion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            version_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string version = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVersion() {
            version_ = getDefaultInstance().getVersion();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string version = 2;</code>
         *
         * @param value The bytes for version to set.
         * @return This builder for chaining.
         */
        public Builder setVersionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            version_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private int protocol_;

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @return The protocol.
         */
        @java.lang.Override
        public int getProtocol() {
            return protocol_;
        }

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @param value The protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocol(int value) {

            protocol_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProtocol() {
            bitField0_ &= ~0x00000004;
            protocol_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object region_ = "";

        /**
         * <code>string region = 4;</code>
         *
         * @return The region.
         */
        public java.lang.String getRegion() {
            java.lang.Object ref = region_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                region_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string region = 4;</code>
         *
         * @return The bytes for region.
         */
        public com.google.protobuf.ByteString getRegionBytes() {
            java.lang.Object ref = region_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                region_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string region = 4;</code>
         *
         * @param value The region to set.
         * @return This builder for chaining.
         */
        public Builder setRegion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            region_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegion() {
            region_ = getDefaultInstance().getRegion();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 4;</code>
         *
         * @param value The bytes for region to set.
         * @return This builder for chaining.
         */
        public Builder setRegionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            region_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object nodeId_ = "";

        /**
         * <code>string node_id = 5;</code>
         *
         * @return The nodeId.
         */
        public java.lang.String getNodeId() {
            java.lang.Object ref = nodeId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                nodeId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string node_id = 5;</code>
         *
         * @return The bytes for nodeId.
         */
        public com.google.protobuf.ByteString getNodeIdBytes() {
            java.lang.Object ref = nodeId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                nodeId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string node_id = 5;</code>
         *
         * @param value The nodeId to set.
         * @return This builder for chaining.
         */
        public Builder setNodeId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            nodeId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string node_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNodeId() {
            nodeId_ = getDefaultInstance().getNodeId();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string node_id = 5;</code>
         *
         * @param value The bytes for nodeId to set.
         * @return This builder for chaining.
         */
        public Builder setNodeIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            nodeId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object debugInfo_ = "";

        /**
         * <pre>
         * additional debugging information. sent only if server is in development mode
         * </pre>
         *
         * <code>string debug_info = 6;</code>
         *
         * @return The debugInfo.
         */
        public java.lang.String getDebugInfo() {
            java.lang.Object ref = debugInfo_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                debugInfo_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * additional debugging information. sent only if server is in development mode
         * </pre>
         *
         * <code>string debug_info = 6;</code>
         *
         * @return The bytes for debugInfo.
         */
        public com.google.protobuf.ByteString getDebugInfoBytes() {
            java.lang.Object ref = debugInfo_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                debugInfo_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * additional debugging information. sent only if server is in development mode
         * </pre>
         *
         * <code>string debug_info = 6;</code>
         *
         * @param value The debugInfo to set.
         * @return This builder for chaining.
         */
        public Builder setDebugInfo(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            debugInfo_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * additional debugging information. sent only if server is in development mode
         * </pre>
         *
         * <code>string debug_info = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDebugInfo() {
            debugInfo_ = getDefaultInstance().getDebugInfo();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * additional debugging information. sent only if server is in development mode
         * </pre>
         *
         * <code>string debug_info = 6;</code>
         *
         * @param value The bytes for debugInfo to set.
         * @return This builder for chaining.
         */
        public Builder setDebugInfoBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            debugInfo_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ServerInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.ServerInfo)
    private static final im.turms.plugin.livekit.core.proto.models.ServerInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ServerInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.ServerInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ServerInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ServerInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<ServerInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ServerInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ServerInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}