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
 * Protobuf type {@code livekit.ListEgressRequest}
 */
public final class ListEgressRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ListEgressRequest)
        ListEgressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ListEgressRequest.class.getName());
    }

    // Use ListEgressRequest.newBuilder() to construct.
    private ListEgressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ListEgressRequest() {
        roomName_ = "";
        egressId_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ListEgressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ListEgressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.Builder.class);
    }

    public static final int ROOM_NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <pre>
     * (optional, filter by room name)
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The roomName.
     */
    @java.lang.Override
    public java.lang.String getRoomName() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            roomName_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional, filter by room name)
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The bytes for roomName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomNameBytes() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            roomName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int EGRESS_ID_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object egressId_ = "";

    /**
     * <pre>
     * (optional, filter by egress ID)
     * </pre>
     *
     * <code>string egress_id = 2;</code>
     *
     * @return The egressId.
     */
    @java.lang.Override
    public java.lang.String getEgressId() {
        java.lang.Object ref = egressId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            egressId_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional, filter by egress ID)
     * </pre>
     *
     * <code>string egress_id = 2;</code>
     *
     * @return The bytes for egressId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEgressIdBytes() {
        java.lang.Object ref = egressId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            egressId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ACTIVE_FIELD_NUMBER = 3;
    private boolean active_ = false;

    /**
     * <pre>
     * (optional, list active egress only)
     * </pre>
     *
     * <code>bool active = 3;</code>
     *
     * @return The active.
     */
    @java.lang.Override
    public boolean getActive() {
        return active_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, egressId_);
        }
        if (active_) {
            output.writeBool(3, active_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, egressId_);
        }
        if (active_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, active_);
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
        if (!(obj instanceof ListEgressRequest other)) {
            return super.equals(obj);
        }

        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (!getEgressId().equals(other.getEgressId())) {
            return false;
        }
        if (getActive() != other.getActive()) {
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
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + EGRESS_ID_FIELD_NUMBER;
        hash = (53 * hash) + getEgressId().hashCode();
        hash = (37 * hash) + ACTIVE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getActive());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.ListEgressRequest prototype) {
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
     * Protobuf type {@code livekit.ListEgressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ListEgressRequest)
            im.turms.plugin.livekit.core.proto.egress.ListEgressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ListEgressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ListEgressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            roomName_ = "";
            egressId_ = "";
            active_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ListEgressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ListEgressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.ListEgressRequest.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ListEgressRequest build() {
            im.turms.plugin.livekit.core.proto.egress.ListEgressRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ListEgressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.ListEgressRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.ListEgressRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.ListEgressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.egressId_ = egressId_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.active_ = active_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.ListEgressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.ListEgressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.ListEgressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.ListEgressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getEgressId()
                    .isEmpty()) {
                egressId_ = other.egressId_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getActive()) {
                setActive(other.getActive());
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
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            egressId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            active_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
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

        private java.lang.Object roomName_ = "";

        /**
         * <pre>
         * (optional, filter by room name)
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return The roomName.
         */
        public java.lang.String getRoomName() {
            java.lang.Object ref = roomName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                roomName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional, filter by room name)
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return The bytes for roomName.
         */
        public com.google.protobuf.ByteString getRoomNameBytes() {
            java.lang.Object ref = roomName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                roomName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional, filter by room name)
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @param value The roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, filter by room name)
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomName() {
            roomName_ = getDefaultInstance().getRoomName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, filter by room name)
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @param value The bytes for roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            roomName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object egressId_ = "";

        /**
         * <pre>
         * (optional, filter by egress ID)
         * </pre>
         *
         * <code>string egress_id = 2;</code>
         *
         * @return The egressId.
         */
        public java.lang.String getEgressId() {
            java.lang.Object ref = egressId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                egressId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional, filter by egress ID)
         * </pre>
         *
         * <code>string egress_id = 2;</code>
         *
         * @return The bytes for egressId.
         */
        public com.google.protobuf.ByteString getEgressIdBytes() {
            java.lang.Object ref = egressId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                egressId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional, filter by egress ID)
         * </pre>
         *
         * <code>string egress_id = 2;</code>
         *
         * @param value The egressId to set.
         * @return This builder for chaining.
         */
        public Builder setEgressId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            egressId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, filter by egress ID)
         * </pre>
         *
         * <code>string egress_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEgressId() {
            egressId_ = getDefaultInstance().getEgressId();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, filter by egress ID)
         * </pre>
         *
         * <code>string egress_id = 2;</code>
         *
         * @param value The bytes for egressId to set.
         * @return This builder for chaining.
         */
        public Builder setEgressIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            egressId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private boolean active_;

        /**
         * <pre>
         * (optional, list active egress only)
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @return The active.
         */
        @java.lang.Override
        public boolean getActive() {
            return active_;
        }

        /**
         * <pre>
         * (optional, list active egress only)
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @param value The active to set.
         * @return This builder for chaining.
         */
        public Builder setActive(boolean value) {

            active_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional, list active egress only)
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearActive() {
            bitField0_ &= ~0x00000004;
            active_ = false;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ListEgressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.ListEgressRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.ListEgressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.ListEgressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.ListEgressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ListEgressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ListEgressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<ListEgressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ListEgressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ListEgressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}