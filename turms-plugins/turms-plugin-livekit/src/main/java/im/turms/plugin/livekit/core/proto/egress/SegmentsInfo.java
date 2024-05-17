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
 * Protobuf type {@code livekit.SegmentsInfo}
 */
public final class SegmentsInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SegmentsInfo)
        SegmentsInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SegmentsInfo.class.getName());
    }

    // Use SegmentsInfo.newBuilder() to construct.
    private SegmentsInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SegmentsInfo() {
        playlistName_ = "";
        livePlaylistName_ = "";
        playlistLocation_ = "";
        livePlaylistLocation_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentsInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentsInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.class,
                        im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder.class);
    }

    public static final int PLAYLIST_NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object playlistName_ = "";

    /**
     * <code>string playlist_name = 1;</code>
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
     * <code>string playlist_name = 1;</code>
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

    public static final int LIVE_PLAYLIST_NAME_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object livePlaylistName_ = "";

    /**
     * <code>string live_playlist_name = 8;</code>
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
     * <code>string live_playlist_name = 8;</code>
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

    public static final int DURATION_FIELD_NUMBER = 2;
    private long duration_ = 0L;

    /**
     * <code>int64 duration = 2;</code>
     *
     * @return The duration.
     */
    @java.lang.Override
    public long getDuration() {
        return duration_;
    }

    public static final int SIZE_FIELD_NUMBER = 3;
    private long size_ = 0L;

    /**
     * <code>int64 size = 3;</code>
     *
     * @return The size.
     */
    @java.lang.Override
    public long getSize() {
        return size_;
    }

    public static final int PLAYLIST_LOCATION_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object playlistLocation_ = "";

    /**
     * <code>string playlist_location = 4;</code>
     *
     * @return The playlistLocation.
     */
    @java.lang.Override
    public java.lang.String getPlaylistLocation() {
        java.lang.Object ref = playlistLocation_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            playlistLocation_ = s;
            return s;
        }
    }

    /**
     * <code>string playlist_location = 4;</code>
     *
     * @return The bytes for playlistLocation.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPlaylistLocationBytes() {
        java.lang.Object ref = playlistLocation_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            playlistLocation_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LIVE_PLAYLIST_LOCATION_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object livePlaylistLocation_ = "";

    /**
     * <code>string live_playlist_location = 9;</code>
     *
     * @return The livePlaylistLocation.
     */
    @java.lang.Override
    public java.lang.String getLivePlaylistLocation() {
        java.lang.Object ref = livePlaylistLocation_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            livePlaylistLocation_ = s;
            return s;
        }
    }

    /**
     * <code>string live_playlist_location = 9;</code>
     *
     * @return The bytes for livePlaylistLocation.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getLivePlaylistLocationBytes() {
        java.lang.Object ref = livePlaylistLocation_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            livePlaylistLocation_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SEGMENT_COUNT_FIELD_NUMBER = 5;
    private long segmentCount_ = 0L;

    /**
     * <code>int64 segment_count = 5;</code>
     *
     * @return The segmentCount.
     */
    @java.lang.Override
    public long getSegmentCount() {
        return segmentCount_;
    }

    public static final int STARTED_AT_FIELD_NUMBER = 6;
    private long startedAt_ = 0L;

    /**
     * <code>int64 started_at = 6;</code>
     *
     * @return The startedAt.
     */
    @java.lang.Override
    public long getStartedAt() {
        return startedAt_;
    }

    public static final int ENDED_AT_FIELD_NUMBER = 7;
    private long endedAt_ = 0L;

    /**
     * <code>int64 ended_at = 7;</code>
     *
     * @return The endedAt.
     */
    @java.lang.Override
    public long getEndedAt() {
        return endedAt_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, playlistName_);
        }
        if (duration_ != 0L) {
            output.writeInt64(2, duration_);
        }
        if (size_ != 0L) {
            output.writeInt64(3, size_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistLocation_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, playlistLocation_);
        }
        if (segmentCount_ != 0L) {
            output.writeInt64(5, segmentCount_);
        }
        if (startedAt_ != 0L) {
            output.writeInt64(6, startedAt_);
        }
        if (endedAt_ != 0L) {
            output.writeInt64(7, endedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, livePlaylistName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistLocation_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, livePlaylistLocation_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, playlistName_);
        }
        if (duration_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, duration_);
        }
        if (size_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, size_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(playlistLocation_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, playlistLocation_);
        }
        if (segmentCount_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, segmentCount_);
        }
        if (startedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, startedAt_);
        }
        if (endedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, endedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, livePlaylistName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(livePlaylistLocation_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9,
                    livePlaylistLocation_);
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
        if (!(obj instanceof SegmentsInfo other)) {
            return super.equals(obj);
        }

        if (!getPlaylistName().equals(other.getPlaylistName())) {
            return false;
        }
        if (!getLivePlaylistName().equals(other.getLivePlaylistName())) {
            return false;
        }
        if (getDuration() != other.getDuration()) {
            return false;
        }
        if (getSize() != other.getSize()) {
            return false;
        }
        if (!getPlaylistLocation().equals(other.getPlaylistLocation())) {
            return false;
        }
        if (!getLivePlaylistLocation().equals(other.getLivePlaylistLocation())) {
            return false;
        }
        if (getSegmentCount() != other.getSegmentCount()) {
            return false;
        }
        if (getStartedAt() != other.getStartedAt()) {
            return false;
        }
        if (getEndedAt() != other.getEndedAt()) {
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
        hash = (37 * hash) + PLAYLIST_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getPlaylistName().hashCode();
        hash = (37 * hash) + LIVE_PLAYLIST_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getLivePlaylistName().hashCode();
        hash = (37 * hash) + DURATION_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDuration());
        hash = (37 * hash) + SIZE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getSize());
        hash = (37 * hash) + PLAYLIST_LOCATION_FIELD_NUMBER;
        hash = (53 * hash) + getPlaylistLocation().hashCode();
        hash = (37 * hash) + LIVE_PLAYLIST_LOCATION_FIELD_NUMBER;
        hash = (53 * hash) + getLivePlaylistLocation().hashCode();
        hash = (37 * hash) + SEGMENT_COUNT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getSegmentCount());
        hash = (37 * hash) + STARTED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartedAt());
        hash = (37 * hash) + ENDED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndedAt());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.SegmentsInfo prototype) {
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
     * Protobuf type {@code livekit.SegmentsInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SegmentsInfo)
            im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentsInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentsInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.class,
                            im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            playlistName_ = "";
            livePlaylistName_ = "";
            duration_ = 0L;
            size_ = 0L;
            playlistLocation_ = "";
            livePlaylistLocation_ = "";
            segmentCount_ = 0L;
            startedAt_ = 0L;
            endedAt_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_SegmentsInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo build() {
            im.turms.plugin.livekit.core.proto.egress.SegmentsInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.SegmentsInfo result =
                    new im.turms.plugin.livekit.core.proto.egress.SegmentsInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.SegmentsInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.playlistName_ = playlistName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.livePlaylistName_ = livePlaylistName_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.duration_ = duration_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.size_ = size_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.playlistLocation_ = playlistLocation_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.livePlaylistLocation_ = livePlaylistLocation_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.segmentCount_ = segmentCount_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.startedAt_ = startedAt_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.endedAt_ = endedAt_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.SegmentsInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.SegmentsInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getPlaylistName()
                    .isEmpty()) {
                playlistName_ = other.playlistName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getLivePlaylistName()
                    .isEmpty()) {
                livePlaylistName_ = other.livePlaylistName_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getDuration() != 0L) {
                setDuration(other.getDuration());
            }
            if (other.getSize() != 0L) {
                setSize(other.getSize());
            }
            if (!other.getPlaylistLocation()
                    .isEmpty()) {
                playlistLocation_ = other.playlistLocation_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (!other.getLivePlaylistLocation()
                    .isEmpty()) {
                livePlaylistLocation_ = other.livePlaylistLocation_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (other.getSegmentCount() != 0L) {
                setSegmentCount(other.getSegmentCount());
            }
            if (other.getStartedAt() != 0L) {
                setStartedAt(other.getStartedAt());
            }
            if (other.getEndedAt() != 0L) {
                setEndedAt(other.getEndedAt());
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
                            playlistName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            duration_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 16
                        case 24 -> {
                            size_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 24
                        case 34 -> {
                            playlistLocation_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 34
                        case 40 -> {
                            segmentCount_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 40
                        case 48 -> {
                            startedAt_ = input.readInt64();
                            bitField0_ |= 0x00000080;
                        } // case 48
                        case 56 -> {
                            endedAt_ = input.readInt64();
                            bitField0_ |= 0x00000100;
                        } // case 56
                        case 66 -> {
                            livePlaylistName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 66
                        case 74 -> {
                            livePlaylistLocation_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 74
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

        private java.lang.Object playlistName_ = "";

        /**
         * <code>string playlist_name = 1;</code>
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
         * <code>string playlist_name = 1;</code>
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
         * <code>string playlist_name = 1;</code>
         *
         * @param value The playlistName to set.
         * @return This builder for chaining.
         */
        public Builder setPlaylistName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            playlistName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string playlist_name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPlaylistName() {
            playlistName_ = getDefaultInstance().getPlaylistName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string playlist_name = 1;</code>
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
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object livePlaylistName_ = "";

        /**
         * <code>string live_playlist_name = 8;</code>
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
         * <code>string live_playlist_name = 8;</code>
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
         * <code>string live_playlist_name = 8;</code>
         *
         * @param value The livePlaylistName to set.
         * @return This builder for chaining.
         */
        public Builder setLivePlaylistName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            livePlaylistName_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string live_playlist_name = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLivePlaylistName() {
            livePlaylistName_ = getDefaultInstance().getLivePlaylistName();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string live_playlist_name = 8;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private long duration_;

        /**
         * <code>int64 duration = 2;</code>
         *
         * @return The duration.
         */
        @java.lang.Override
        public long getDuration() {
            return duration_;
        }

        /**
         * <code>int64 duration = 2;</code>
         *
         * @param value The duration to set.
         * @return This builder for chaining.
         */
        public Builder setDuration(long value) {

            duration_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int64 duration = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDuration() {
            bitField0_ &= ~0x00000004;
            duration_ = 0L;
            onChanged();
            return this;
        }

        private long size_;

        /**
         * <code>int64 size = 3;</code>
         *
         * @return The size.
         */
        @java.lang.Override
        public long getSize() {
            return size_;
        }

        /**
         * <code>int64 size = 3;</code>
         *
         * @param value The size to set.
         * @return This builder for chaining.
         */
        public Builder setSize(long value) {

            size_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>int64 size = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSize() {
            bitField0_ &= ~0x00000008;
            size_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object playlistLocation_ = "";

        /**
         * <code>string playlist_location = 4;</code>
         *
         * @return The playlistLocation.
         */
        public java.lang.String getPlaylistLocation() {
            java.lang.Object ref = playlistLocation_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                playlistLocation_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string playlist_location = 4;</code>
         *
         * @return The bytes for playlistLocation.
         */
        public com.google.protobuf.ByteString getPlaylistLocationBytes() {
            java.lang.Object ref = playlistLocation_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                playlistLocation_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string playlist_location = 4;</code>
         *
         * @param value The playlistLocation to set.
         * @return This builder for chaining.
         */
        public Builder setPlaylistLocation(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            playlistLocation_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string playlist_location = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPlaylistLocation() {
            playlistLocation_ = getDefaultInstance().getPlaylistLocation();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string playlist_location = 4;</code>
         *
         * @param value The bytes for playlistLocation to set.
         * @return This builder for chaining.
         */
        public Builder setPlaylistLocationBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            playlistLocation_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object livePlaylistLocation_ = "";

        /**
         * <code>string live_playlist_location = 9;</code>
         *
         * @return The livePlaylistLocation.
         */
        public java.lang.String getLivePlaylistLocation() {
            java.lang.Object ref = livePlaylistLocation_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                livePlaylistLocation_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string live_playlist_location = 9;</code>
         *
         * @return The bytes for livePlaylistLocation.
         */
        public com.google.protobuf.ByteString getLivePlaylistLocationBytes() {
            java.lang.Object ref = livePlaylistLocation_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                livePlaylistLocation_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string live_playlist_location = 9;</code>
         *
         * @param value The livePlaylistLocation to set.
         * @return This builder for chaining.
         */
        public Builder setLivePlaylistLocation(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            livePlaylistLocation_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string live_playlist_location = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLivePlaylistLocation() {
            livePlaylistLocation_ = getDefaultInstance().getLivePlaylistLocation();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string live_playlist_location = 9;</code>
         *
         * @param value The bytes for livePlaylistLocation to set.
         * @return This builder for chaining.
         */
        public Builder setLivePlaylistLocationBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            livePlaylistLocation_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        private long segmentCount_;

        /**
         * <code>int64 segment_count = 5;</code>
         *
         * @return The segmentCount.
         */
        @java.lang.Override
        public long getSegmentCount() {
            return segmentCount_;
        }

        /**
         * <code>int64 segment_count = 5;</code>
         *
         * @param value The segmentCount to set.
         * @return This builder for chaining.
         */
        public Builder setSegmentCount(long value) {

            segmentCount_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>int64 segment_count = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSegmentCount() {
            bitField0_ &= ~0x00000040;
            segmentCount_ = 0L;
            onChanged();
            return this;
        }

        private long startedAt_;

        /**
         * <code>int64 started_at = 6;</code>
         *
         * @return The startedAt.
         */
        @java.lang.Override
        public long getStartedAt() {
            return startedAt_;
        }

        /**
         * <code>int64 started_at = 6;</code>
         *
         * @param value The startedAt to set.
         * @return This builder for chaining.
         */
        public Builder setStartedAt(long value) {

            startedAt_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>int64 started_at = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartedAt() {
            bitField0_ &= ~0x00000080;
            startedAt_ = 0L;
            onChanged();
            return this;
        }

        private long endedAt_;

        /**
         * <code>int64 ended_at = 7;</code>
         *
         * @return The endedAt.
         */
        @java.lang.Override
        public long getEndedAt() {
            return endedAt_;
        }

        /**
         * <code>int64 ended_at = 7;</code>
         *
         * @param value The endedAt to set.
         * @return This builder for chaining.
         */
        public Builder setEndedAt(long value) {

            endedAt_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>int64 ended_at = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndedAt() {
            bitField0_ &= ~0x00000100;
            endedAt_ = 0L;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SegmentsInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.SegmentsInfo)
    private static final im.turms.plugin.livekit.core.proto.egress.SegmentsInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.SegmentsInfo();
    }

    public static im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SegmentsInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SegmentsInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<SegmentsInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SegmentsInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}