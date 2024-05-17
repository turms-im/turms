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
 * record tracks individually, without transcoding
 * </pre>
 *
 * Protobuf type {@code livekit.TrackEgressRequest}
 */
public final class TrackEgressRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.TrackEgressRequest)
        TrackEgressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                TrackEgressRequest.class.getName());
    }

    // Use TrackEgressRequest.newBuilder() to construct.
    private TrackEgressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private TrackEgressRequest() {
        roomName_ = "";
        trackId_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_TrackEgressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_TrackEgressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        FILE(3),
        WEBSOCKET_URL(4),
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
                case 3 -> FILE;
                case 4 -> WEBSOCKET_URL;
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

    public static final int ROOM_NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <pre>
     * required
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
     * required
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

    public static final int TRACK_ID_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object trackId_ = "";

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string track_id = 2;</code>
     *
     * @return The trackId.
     */
    @java.lang.Override
    public java.lang.String getTrackId() {
        java.lang.Object ref = trackId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            trackId_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string track_id = 2;</code>
     *
     * @return The bytes for trackId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTrackIdBytes() {
        java.lang.Object ref = trackId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            trackId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int FILE_FIELD_NUMBER = 3;

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     *
     * @return Whether the file field is set.
     */
    @java.lang.Override
    public boolean hasFile() {
        return outputCase_ == 3;
    }

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     *
     * @return The file.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getFile() {
        if (outputCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder getFileOrBuilder() {
        if (outputCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.getDefaultInstance();
    }

    public static final int WEBSOCKET_URL_FIELD_NUMBER = 4;

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return Whether the websocketUrl field is set.
     */
    public boolean hasWebsocketUrl() {
        return outputCase_ == 4;
    }

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return The websocketUrl.
     */
    public java.lang.String getWebsocketUrl() {
        java.lang.Object ref = "";
        if (outputCase_ == 4) {
            ref = output_;
        }
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            if (outputCase_ == 4) {
                output_ = s;
            }
            return s;
        }
    }

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return The bytes for websocketUrl.
     */
    public com.google.protobuf.ByteString getWebsocketUrlBytes() {
        java.lang.Object ref = "";
        if (outputCase_ == 4) {
            ref = output_;
        }
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            if (outputCase_ == 4) {
                output_ = b;
            }
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(trackId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, trackId_);
        }
        if (outputCase_ == 3) {
            output.writeMessage(3,
                    (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_);
        }
        if (outputCase_ == 4) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, output_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(trackId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, trackId_);
        }
        if (outputCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3,
                    (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_);
        }
        if (outputCase_ == 4) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, output_);
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
        if (!(obj instanceof TrackEgressRequest other)) {
            return super.equals(obj);
        }

        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (!getTrackId().equals(other.getTrackId())) {
            return false;
        }
        if (!getOutputCase().equals(other.getOutputCase())) {
            return false;
        }
        switch (outputCase_) {
            case 3 -> {
                if (!getFile().equals(other.getFile())) {
                    return false;
                }
            }
            case 4 -> {
                if (!getWebsocketUrl().equals(other.getWebsocketUrl())) {
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
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + TRACK_ID_FIELD_NUMBER;
        hash = (53 * hash) + getTrackId().hashCode();
        switch (outputCase_) {
            case 3 -> {
                hash = (37 * hash) + FILE_FIELD_NUMBER;
                hash = (53 * hash) + getFile().hashCode();
            }
            case 4 -> {
                hash = (37 * hash) + WEBSOCKET_URL_FIELD_NUMBER;
                hash = (53 * hash) + getWebsocketUrl().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest prototype) {
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
     * record tracks individually, without transcoding
     * </pre>
     *
     * Protobuf type {@code livekit.TrackEgressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.TrackEgressRequest)
            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_TrackEgressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_TrackEgressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.newBuilder()
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
            trackId_ = "";
            if (fileBuilder_ != null) {
                fileBuilder_.clear();
            }
            outputCase_ = 0;
            output_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_TrackEgressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest build() {
            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.trackId_ = trackId_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 3 && fileBuilder_ != null) {
                result.output_ = fileBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getTrackId()
                    .isEmpty()) {
                trackId_ = other.trackId_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            switch (other.getOutputCase()) {
                case FILE -> mergeFile(other.getFile());
                case WEBSOCKET_URL -> {
                    outputCase_ = 4;
                    output_ = other.output_;
                    onChanged();
                }
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
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            trackId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getFileFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 3;
                        } // case 26
                        case 34 -> {
                            String s = input.readStringRequireUtf8();
                            outputCase_ = 4;
                            output_ = s;
                        } // case 34
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

        private java.lang.Object roomName_ = "";

        /**
         * <pre>
         * required
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
         * required
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
         * required
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
         * required
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
         * required
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

        private java.lang.Object trackId_ = "";

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string track_id = 2;</code>
         *
         * @return The trackId.
         */
        public java.lang.String getTrackId() {
            java.lang.Object ref = trackId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                trackId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string track_id = 2;</code>
         *
         * @return The bytes for trackId.
         */
        public com.google.protobuf.ByteString getTrackIdBytes() {
            java.lang.Object ref = trackId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                trackId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string track_id = 2;</code>
         *
         * @param value The trackId to set.
         * @return This builder for chaining.
         */
        public Builder setTrackId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            trackId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string track_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTrackId() {
            trackId_ = getDefaultInstance().getTrackId();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string track_id = 2;</code>
         *
         * @param value The bytes for trackId to set.
         * @return This builder for chaining.
         */
        public Builder setTrackIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            trackId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.DirectFileOutput, im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder> fileBuilder_;

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         *
         * @return Whether the file field is set.
         */
        @java.lang.Override
        public boolean hasFile() {
            return outputCase_ == 3;
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         *
         * @return The file.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getFile() {
            if (fileBuilder_ == null) {
                if (outputCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 3) {
                    return fileBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        public Builder setFile(im.turms.plugin.livekit.core.proto.egress.DirectFileOutput value) {
            if (fileBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                fileBuilder_.setMessage(value);
            }
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        public Builder setFile(
                im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder builderForValue) {
            if (fileBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                fileBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        public Builder mergeFile(im.turms.plugin.livekit.core.proto.egress.DirectFileOutput value) {
            if (fileBuilder_ == null) {
                if (outputCase_ == 3
                        && output_ != im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 3) {
                    fileBuilder_.mergeFrom(value);
                } else {
                    fileBuilder_.setMessage(value);
                }
            }
            outputCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        public Builder clearFile() {
            if (fileBuilder_ == null) {
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
                fileBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder getFileBuilder() {
            return getFileFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder getFileOrBuilder() {
            if ((outputCase_ == 3) && (fileBuilder_ != null)) {
                return fileBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.DirectFileOutput file = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.DirectFileOutput, im.turms.plugin.livekit.core.proto.egress.DirectFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder> getFileFieldBuilder() {
            if (fileBuilder_ == null) {
                if (!(outputCase_ == 3)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.DirectFileOutput
                            .getDefaultInstance();
                }
                fileBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.DirectFileOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 3;
            onChanged();
            return fileBuilder_;
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @return Whether the websocketUrl field is set.
         */
        @java.lang.Override
        public boolean hasWebsocketUrl() {
            return outputCase_ == 4;
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @return The websocketUrl.
         */
        @java.lang.Override
        public java.lang.String getWebsocketUrl() {
            java.lang.Object ref = "";
            if (outputCase_ == 4) {
                ref = output_;
            }
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                if (outputCase_ == 4) {
                    output_ = s;
                }
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @return The bytes for websocketUrl.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getWebsocketUrlBytes() {
            java.lang.Object ref = "";
            if (outputCase_ == 4) {
                ref = output_;
            }
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                if (outputCase_ == 4) {
                    output_ = b;
                }
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @param value The websocketUrl to set.
         * @return This builder for chaining.
         */
        public Builder setWebsocketUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            outputCase_ = 4;
            output_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWebsocketUrl() {
            if (outputCase_ == 4) {
                outputCase_ = 0;
                output_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>string websocket_url = 4;</code>
         *
         * @param value The bytes for websocketUrl to set.
         * @return This builder for chaining.
         */
        public Builder setWebsocketUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            outputCase_ = 4;
            output_ = value;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.TrackEgressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.TrackEgressRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TrackEgressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public TrackEgressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<TrackEgressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TrackEgressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}