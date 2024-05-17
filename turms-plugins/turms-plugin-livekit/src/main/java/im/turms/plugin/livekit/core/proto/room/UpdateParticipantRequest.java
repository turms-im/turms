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

package im.turms.plugin.livekit.core.proto.room;

/**
 * Protobuf type {@code livekit.UpdateParticipantRequest}
 */
public final class UpdateParticipantRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.UpdateParticipantRequest)
        UpdateParticipantRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                UpdateParticipantRequest.class.getName());
    }

    // Use UpdateParticipantRequest.newBuilder() to construct.
    private UpdateParticipantRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateParticipantRequest() {
        room_ = "";
        identity_ = "";
        metadata_ = "";
        name_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateParticipantRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateParticipantRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest.class,
                        im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest.Builder.class);
    }

    private int bitField0_;
    public static final int ROOM_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object room_ = "";

    /**
     * <code>string room = 1;</code>
     *
     * @return The room.
     */
    @java.lang.Override
    public java.lang.String getRoom() {
        java.lang.Object ref = room_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            room_ = s;
            return s;
        }
    }

    /**
     * <code>string room = 1;</code>
     *
     * @return The bytes for room.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomBytes() {
        java.lang.Object ref = room_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            room_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int IDENTITY_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object identity_ = "";

    /**
     * <code>string identity = 2;</code>
     *
     * @return The identity.
     */
    @java.lang.Override
    public java.lang.String getIdentity() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            identity_ = s;
            return s;
        }
    }

    /**
     * <code>string identity = 2;</code>
     *
     * @return The bytes for identity.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdentityBytes() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            identity_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int METADATA_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object metadata_ = "";

    /**
     * <pre>
     * metadata to update. skipping updates if left empty
     * </pre>
     *
     * <code>string metadata = 3;</code>
     *
     * @return The metadata.
     */
    @java.lang.Override
    public java.lang.String getMetadata() {
        java.lang.Object ref = metadata_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            metadata_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * metadata to update. skipping updates if left empty
     * </pre>
     *
     * <code>string metadata = 3;</code>
     *
     * @return The bytes for metadata.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMetadataBytes() {
        java.lang.Object ref = metadata_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            metadata_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PERMISSION_FIELD_NUMBER = 4;
    private im.turms.plugin.livekit.core.proto.models.ParticipantPermission permission_;

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     *
     * @return Whether the permission field is set.
     */
    @java.lang.Override
    public boolean hasPermission() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     *
     * @return The permission.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantPermission getPermission() {
        return permission_ == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                        .getDefaultInstance()
                : permission_;
    }

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder getPermissionOrBuilder() {
        return permission_ == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                        .getDefaultInstance()
                : permission_;
    }

    public static final int NAME_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <pre>
     * display name to update
     * </pre>
     *
     * <code>string name = 5;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * display name to update
     * </pre>
     *
     * <code>string name = 5;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(room_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, room_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, identity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, metadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(4, getPermission());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, name_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(room_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, room_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, identity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, metadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, getPermission());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, name_);
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
        if (!(obj instanceof UpdateParticipantRequest other)) {
            return super.equals(obj);
        }

        if (!getRoom().equals(other.getRoom())) {
            return false;
        }
        if (!getIdentity().equals(other.getIdentity())) {
            return false;
        }
        if (!getMetadata().equals(other.getMetadata())) {
            return false;
        }
        if (hasPermission() != other.hasPermission()) {
            return false;
        }
        if (hasPermission()) {
            if (!getPermission().equals(other.getPermission())) {
                return false;
            }
        }
        if (!getName().equals(other.getName())) {
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
        hash = (37 * hash) + ROOM_FIELD_NUMBER;
        hash = (53 * hash) + getRoom().hashCode();
        hash = (37 * hash) + IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getIdentity().hashCode();
        hash = (37 * hash) + METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getMetadata().hashCode();
        if (hasPermission()) {
            hash = (37 * hash) + PERMISSION_FIELD_NUMBER;
            hash = (53 * hash) + getPermission().hashCode();
        }
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest prototype) {
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
     * Protobuf type {@code livekit.UpdateParticipantRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.UpdateParticipantRequest)
            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateParticipantRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateParticipantRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest.class,
                            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getPermissionFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            room_ = "";
            identity_ = "";
            metadata_ = "";
            permission_ = null;
            if (permissionBuilder_ != null) {
                permissionBuilder_.dispose();
                permissionBuilder_ = null;
            }
            name_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateParticipantRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest build() {
            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest result =
                    new im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.room_ = room_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.identity_ = identity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.metadata_ = metadata_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.permission_ = permissionBuilder_ == null
                        ? permission_
                        : permissionBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.name_ = name_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoom()
                    .isEmpty()) {
                room_ = other.room_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getIdentity()
                    .isEmpty()) {
                identity_ = other.identity_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getMetadata()
                    .isEmpty()) {
                metadata_ = other.metadata_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasPermission()) {
                mergePermission(other.getPermission());
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000010;
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
                        case 10 -> {
                            room_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            identity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            metadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            input.readMessage(getPermissionFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
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

        private java.lang.Object room_ = "";

        /**
         * <code>string room = 1;</code>
         *
         * @return The room.
         */
        public java.lang.String getRoom() {
            java.lang.Object ref = room_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                room_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string room = 1;</code>
         *
         * @return The bytes for room.
         */
        public com.google.protobuf.ByteString getRoomBytes() {
            java.lang.Object ref = room_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                room_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string room = 1;</code>
         *
         * @param value The room to set.
         * @return This builder for chaining.
         */
        public Builder setRoom(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            room_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string room = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoom() {
            room_ = getDefaultInstance().getRoom();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string room = 1;</code>
         *
         * @param value The bytes for room to set.
         * @return This builder for chaining.
         */
        public Builder setRoomBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            room_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object identity_ = "";

        /**
         * <code>string identity = 2;</code>
         *
         * @return The identity.
         */
        public java.lang.String getIdentity() {
            java.lang.Object ref = identity_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                identity_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @return The bytes for identity.
         */
        public com.google.protobuf.ByteString getIdentityBytes() {
            java.lang.Object ref = identity_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                identity_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @param value The identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdentity() {
            identity_ = getDefaultInstance().getIdentity();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @param value The bytes for identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentityBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object metadata_ = "";

        /**
         * <pre>
         * metadata to update. skipping updates if left empty
         * </pre>
         *
         * <code>string metadata = 3;</code>
         *
         * @return The metadata.
         */
        public java.lang.String getMetadata() {
            java.lang.Object ref = metadata_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                metadata_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * metadata to update. skipping updates if left empty
         * </pre>
         *
         * <code>string metadata = 3;</code>
         *
         * @return The bytes for metadata.
         */
        public com.google.protobuf.ByteString getMetadataBytes() {
            java.lang.Object ref = metadata_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                metadata_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * metadata to update. skipping updates if left empty
         * </pre>
         *
         * <code>string metadata = 3;</code>
         *
         * @param value The metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            metadata_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * metadata to update. skipping updates if left empty
         * </pre>
         *
         * <code>string metadata = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMetadata() {
            metadata_ = getDefaultInstance().getMetadata();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * metadata to update. skipping updates if left empty
         * </pre>
         *
         * <code>string metadata = 3;</code>
         *
         * @param value The bytes for metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadataBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            metadata_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.ParticipantPermission permission_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantPermission, im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder> permissionBuilder_;

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         *
         * @return Whether the permission field is set.
         */
        public boolean hasPermission() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         *
         * @return The permission.
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission getPermission() {
            if (permissionBuilder_ == null) {
                return permission_ == null
                        ? im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                                .getDefaultInstance()
                        : permission_;
            } else {
                return permissionBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public Builder setPermission(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission value) {
            if (permissionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                permission_ = value;
            } else {
                permissionBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public Builder setPermission(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder builderForValue) {
            if (permissionBuilder_ == null) {
                permission_ = builderForValue.build();
            } else {
                permissionBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public Builder mergePermission(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission value) {
            if (permissionBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)
                        && permission_ != null
                        && permission_ != im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                                .getDefaultInstance()) {
                    getPermissionBuilder().mergeFrom(value);
                } else {
                    permission_ = value;
                }
            } else {
                permissionBuilder_.mergeFrom(value);
            }
            if (permission_ != null) {
                bitField0_ |= 0x00000008;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public Builder clearPermission() {
            bitField0_ &= ~0x00000008;
            permission_ = null;
            if (permissionBuilder_ != null) {
                permissionBuilder_.dispose();
                permissionBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder getPermissionBuilder() {
            bitField0_ |= 0x00000008;
            onChanged();
            return getPermissionFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder getPermissionOrBuilder() {
            if (permissionBuilder_ != null) {
                return permissionBuilder_.getMessageOrBuilder();
            } else {
                return permission_ == null
                        ? im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                                .getDefaultInstance()
                        : permission_;
            }
        }

        /**
         * <pre>
         * set to update the participant's permissions
         * </pre>
         *
         * <code>.livekit.ParticipantPermission permission = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantPermission, im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder> getPermissionFieldBuilder() {
            if (permissionBuilder_ == null) {
                permissionBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getPermission(),
                        getParentForChildren(),
                        isClean());
                permission_ = null;
            }
            return permissionBuilder_;
        }

        private java.lang.Object name_ = "";

        /**
         * <pre>
         * display name to update
         * </pre>
         *
         * <code>string name = 5;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * display name to update
         * </pre>
         *
         * <code>string name = 5;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * display name to update
         * </pre>
         *
         * <code>string name = 5;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * display name to update
         * </pre>
         *
         * <code>string name = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * display name to update
         * </pre>
         *
         * <code>string name = 5;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.UpdateParticipantRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.UpdateParticipantRequest)
    private static final im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest();
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateParticipantRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateParticipantRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateParticipantRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateParticipantRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}