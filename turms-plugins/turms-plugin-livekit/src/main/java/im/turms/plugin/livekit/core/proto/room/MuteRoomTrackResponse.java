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
 * Protobuf type {@code livekit.MuteRoomTrackResponse}
 */
public final class MuteRoomTrackResponse extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.MuteRoomTrackResponse)
        MuteRoomTrackResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                MuteRoomTrackResponse.class.getName());
    }

    // Use MuteRoomTrackResponse.newBuilder() to construct.
    private MuteRoomTrackResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private MuteRoomTrackResponse() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse.class,
                        im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse.Builder.class);
    }

    private int bitField0_;
    public static final int TRACK_FIELD_NUMBER = 1;
    private im.turms.plugin.livekit.core.proto.models.TrackInfo track_;

    /**
     * <code>.livekit.TrackInfo track = 1;</code>
     *
     * @return Whether the track field is set.
     */
    @java.lang.Override
    public boolean hasTrack() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.TrackInfo track = 1;</code>
     *
     * @return The track.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfo getTrack() {
        return track_ == null
                ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                : track_;
    }

    /**
     * <code>.livekit.TrackInfo track = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTrackOrBuilder() {
        return track_ == null
                ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                : track_;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(1, getTrack());
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, getTrack());
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
        if (!(obj instanceof MuteRoomTrackResponse other)) {
            return super.equals(obj);
        }

        if (hasTrack() != other.hasTrack()) {
            return false;
        }
        if (hasTrack()) {
            if (!getTrack().equals(other.getTrack())) {
                return false;
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
        if (hasTrack()) {
            hash = (37 * hash) + TRACK_FIELD_NUMBER;
            hash = (53 * hash) + getTrack().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse prototype) {
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
     * Protobuf type {@code livekit.MuteRoomTrackResponse}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.MuteRoomTrackResponse)
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponseOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackResponse_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse.class,
                            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getTrackFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            track_ = null;
            if (trackBuilder_ != null) {
                trackBuilder_.dispose();
                trackBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackResponse_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse build() {
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse buildPartial() {
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse result =
                    new im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.track_ = trackBuilder_ == null
                        ? track_
                        : trackBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse other) {
            if (other == im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasTrack()) {
                mergeTrack(other.getTrack());
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
                            input.readMessage(getTrackFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000001;
                        } // case 10
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

        private im.turms.plugin.livekit.core.proto.models.TrackInfo track_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> trackBuilder_;

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         *
         * @return Whether the track field is set.
         */
        public boolean hasTrack() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         *
         * @return The track.
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo getTrack() {
            if (trackBuilder_ == null) {
                return track_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                        : track_;
            } else {
                return trackBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public Builder setTrack(im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (trackBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                track_ = value;
            } else {
                trackBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public Builder setTrack(
                im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder builderForValue) {
            if (trackBuilder_ == null) {
                track_ = builderForValue.build();
            } else {
                trackBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public Builder mergeTrack(im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (trackBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)
                        && track_ != null
                        && track_ != im.turms.plugin.livekit.core.proto.models.TrackInfo
                                .getDefaultInstance()) {
                    getTrackBuilder().mergeFrom(value);
                } else {
                    track_ = value;
                }
            } else {
                trackBuilder_.mergeFrom(value);
            }
            if (track_ != null) {
                bitField0_ |= 0x00000001;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public Builder clearTrack() {
            bitField0_ &= ~0x00000001;
            track_ = null;
            if (trackBuilder_ != null) {
                trackBuilder_.dispose();
                trackBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder getTrackBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getTrackFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTrackOrBuilder() {
            if (trackBuilder_ != null) {
                return trackBuilder_.getMessageOrBuilder();
            } else {
                return track_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                        : track_;
            }
        }

        /**
         * <code>.livekit.TrackInfo track = 1;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTrackFieldBuilder() {
            if (trackBuilder_ == null) {
                trackBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getTrack(),
                        getParentForChildren(),
                        isClean());
                track_ = null;
            }
            return trackBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.MuteRoomTrackResponse)
    }

    // @@protoc_insertion_point(class_scope:livekit.MuteRoomTrackResponse)
    private static final im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse();
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MuteRoomTrackResponse> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public MuteRoomTrackResponse parsePartialFrom(
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

    public static com.google.protobuf.Parser<MuteRoomTrackResponse> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MuteRoomTrackResponse> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}