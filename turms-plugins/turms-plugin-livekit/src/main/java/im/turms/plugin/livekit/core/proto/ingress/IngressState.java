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

package im.turms.plugin.livekit.core.proto.ingress;

/**
 * Protobuf type {@code livekit.IngressState}
 */
public final class IngressState extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.IngressState)
        IngressStateOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressState.class.getName());
    }

    // Use IngressState.newBuilder() to construct.
    private IngressState(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private IngressState() {
        status_ = 0;
        error_ = "";
        roomId_ = "";
        resourceId_ = "";
        tracks_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressState_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.IngressState.class,
                        im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.IngressState.Status}
     */
    public enum Status implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <code>ENDPOINT_INACTIVE = 0;</code>
         */
        ENDPOINT_INACTIVE(0),
        /**
         * <code>ENDPOINT_BUFFERING = 1;</code>
         */
        ENDPOINT_BUFFERING(1),
        /**
         * <code>ENDPOINT_PUBLISHING = 2;</code>
         */
        ENDPOINT_PUBLISHING(2),
        /**
         * <code>ENDPOINT_ERROR = 3;</code>
         */
        ENDPOINT_ERROR(3),
        /**
         * <code>ENDPOINT_COMPLETE = 4;</code>
         */
        ENDPOINT_COMPLETE(4),
        UNRECOGNIZED(-1),;

        static {
            com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                    com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                    /* major= */ 4,
                    /* minor= */ 26,
                    /* patch= */ 1,
                    /* suffix= */ "",
                    Status.class.getName());
        }
        /**
         * <code>ENDPOINT_INACTIVE = 0;</code>
         */
        public static final int ENDPOINT_INACTIVE_VALUE = 0;
        /**
         * <code>ENDPOINT_BUFFERING = 1;</code>
         */
        public static final int ENDPOINT_BUFFERING_VALUE = 1;
        /**
         * <code>ENDPOINT_PUBLISHING = 2;</code>
         */
        public static final int ENDPOINT_PUBLISHING_VALUE = 2;
        /**
         * <code>ENDPOINT_ERROR = 3;</code>
         */
        public static final int ENDPOINT_ERROR_VALUE = 3;
        /**
         * <code>ENDPOINT_COMPLETE = 4;</code>
         */
        public static final int ENDPOINT_COMPLETE_VALUE = 4;

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
        public static Status valueOf(int value) {
            return forNumber(value);
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         */
        public static Status forNumber(int value) {
            return switch (value) {
                case 0 -> ENDPOINT_INACTIVE;
                case 1 -> ENDPOINT_BUFFERING;
                case 2 -> ENDPOINT_PUBLISHING;
                case 3 -> ENDPOINT_ERROR;
                case 4 -> ENDPOINT_COMPLETE;
                default -> null;
            };
        }

        public static com.google.protobuf.Internal.EnumLiteMap<Status> internalGetValueMap() {
            return internalValueMap;
        }

        private static final com.google.protobuf.Internal.EnumLiteMap<Status> internalValueMap =
                Status::forNumber;

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
            return im.turms.plugin.livekit.core.proto.ingress.IngressState.getDescriptor()
                    .getEnumTypes()
                    .get(0);
        }

        private static final Status[] VALUES = values();

        public static Status valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

        Status(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:livekit.IngressState.Status)
    }

    private int bitField0_;
    public static final int STATUS_FIELD_NUMBER = 1;
    private int status_ = 0;

    /**
     * <code>.livekit.IngressState.Status status = 1;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override
    public int getStatusValue() {
        return status_;
    }

    /**
     * <code>.livekit.IngressState.Status status = 1;</code>
     *
     * @return The status.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressState.Status getStatus() {
        im.turms.plugin.livekit.core.proto.ingress.IngressState.Status result =
                im.turms.plugin.livekit.core.proto.ingress.IngressState.Status.forNumber(status_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressState.Status.UNRECOGNIZED
                : result;
    }

    public static final int ERROR_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object error_ = "";

    /**
     * <pre>
     * Error/non compliance description if any
     * </pre>
     *
     * <code>string error = 2;</code>
     *
     * @return The error.
     */
    @java.lang.Override
    public java.lang.String getError() {
        java.lang.Object ref = error_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            error_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * Error/non compliance description if any
     * </pre>
     *
     * <code>string error = 2;</code>
     *
     * @return The bytes for error.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getErrorBytes() {
        java.lang.Object ref = error_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            error_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int VIDEO_FIELD_NUMBER = 3;
    private im.turms.plugin.livekit.core.proto.ingress.InputVideoState video_;

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     *
     * @return Whether the video field is set.
     */
    @java.lang.Override
    public boolean hasVideo() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     *
     * @return The video.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputVideoState getVideo() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.InputVideoState.getDefaultInstance()
                : video_;
    }

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder getVideoOrBuilder() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.InputVideoState.getDefaultInstance()
                : video_;
    }

    public static final int AUDIO_FIELD_NUMBER = 4;
    private im.turms.plugin.livekit.core.proto.ingress.InputAudioState audio_;

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     *
     * @return Whether the audio field is set.
     */
    @java.lang.Override
    public boolean hasAudio() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     *
     * @return The audio.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputAudioState getAudio() {
        return audio_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.InputAudioState.getDefaultInstance()
                : audio_;
    }

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder getAudioOrBuilder() {
        return audio_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.InputAudioState.getDefaultInstance()
                : audio_;
    }

    public static final int ROOM_ID_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomId_ = "";

    /**
     * <pre>
     * ID of the current/previous room published to
     * </pre>
     *
     * <code>string room_id = 5;</code>
     *
     * @return The roomId.
     */
    @java.lang.Override
    public java.lang.String getRoomId() {
        java.lang.Object ref = roomId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            roomId_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * ID of the current/previous room published to
     * </pre>
     *
     * <code>string room_id = 5;</code>
     *
     * @return The bytes for roomId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomIdBytes() {
        java.lang.Object ref = roomId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            roomId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int STARTED_AT_FIELD_NUMBER = 7;
    private long startedAt_ = 0L;

    /**
     * <code>int64 started_at = 7;</code>
     *
     * @return The startedAt.
     */
    @java.lang.Override
    public long getStartedAt() {
        return startedAt_;
    }

    public static final int ENDED_AT_FIELD_NUMBER = 8;
    private long endedAt_ = 0L;

    /**
     * <code>int64 ended_at = 8;</code>
     *
     * @return The endedAt.
     */
    @java.lang.Override
    public long getEndedAt() {
        return endedAt_;
    }

    public static final int UPDATED_AT_FIELD_NUMBER = 10;
    private long updatedAt_ = 0L;

    /**
     * <code>int64 updated_at = 10;</code>
     *
     * @return The updatedAt.
     */
    @java.lang.Override
    public long getUpdatedAt() {
        return updatedAt_;
    }

    public static final int RESOURCE_ID_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object resourceId_ = "";

    /**
     * <code>string resource_id = 9;</code>
     *
     * @return The resourceId.
     */
    @java.lang.Override
    public java.lang.String getResourceId() {
        java.lang.Object ref = resourceId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            resourceId_ = s;
            return s;
        }
    }

    /**
     * <code>string resource_id = 9;</code>
     *
     * @return The bytes for resourceId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getResourceIdBytes() {
        java.lang.Object ref = resourceId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            resourceId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int TRACKS_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> tracks_;

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList() {
        return tracks_;
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList() {
        return tracks_;
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    @java.lang.Override
    public int getTracksCount() {
        return tracks_.size();
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index) {
        return tracks_.get(index);
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTracksOrBuilder(
            int index) {
        return tracks_.get(index);
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
        if (status_ != im.turms.plugin.livekit.core.proto.ingress.IngressState.Status.ENDPOINT_INACTIVE
                .getNumber()) {
            output.writeEnum(1, status_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, error_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(3, getVideo());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(4, getAudio());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, roomId_);
        }
        for (im.turms.plugin.livekit.core.proto.models.TrackInfo trackInfo : tracks_) {
            output.writeMessage(6, trackInfo);
        }
        if (startedAt_ != 0L) {
            output.writeInt64(7, startedAt_);
        }
        if (endedAt_ != 0L) {
            output.writeInt64(8, endedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(resourceId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, resourceId_);
        }
        if (updatedAt_ != 0L) {
            output.writeInt64(10, updatedAt_);
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
        if (status_ != im.turms.plugin.livekit.core.proto.ingress.IngressState.Status.ENDPOINT_INACTIVE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, status_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, error_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, getVideo());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, getAudio());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, roomId_);
        }
        for (im.turms.plugin.livekit.core.proto.models.TrackInfo trackInfo : tracks_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, trackInfo);
        }
        if (startedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, startedAt_);
        }
        if (endedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(8, endedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(resourceId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, resourceId_);
        }
        if (updatedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(10, updatedAt_);
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
        if (!(obj instanceof IngressState other)) {
            return super.equals(obj);
        }

        if (status_ != other.status_) {
            return false;
        }
        if (!getError().equals(other.getError())) {
            return false;
        }
        if (hasVideo() != other.hasVideo()) {
            return false;
        }
        if (hasVideo()) {
            if (!getVideo().equals(other.getVideo())) {
                return false;
            }
        }
        if (hasAudio() != other.hasAudio()) {
            return false;
        }
        if (hasAudio()) {
            if (!getAudio().equals(other.getAudio())) {
                return false;
            }
        }
        if (!getRoomId().equals(other.getRoomId())) {
            return false;
        }
        if (getStartedAt() != other.getStartedAt()) {
            return false;
        }
        if (getEndedAt() != other.getEndedAt()) {
            return false;
        }
        if (getUpdatedAt() != other.getUpdatedAt()) {
            return false;
        }
        if (!getResourceId().equals(other.getResourceId())) {
            return false;
        }
        if (!getTracksList().equals(other.getTracksList())) {
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
        hash = (37 * hash) + STATUS_FIELD_NUMBER;
        hash = (53 * hash) + status_;
        hash = (37 * hash) + ERROR_FIELD_NUMBER;
        hash = (53 * hash) + getError().hashCode();
        if (hasVideo()) {
            hash = (37 * hash) + VIDEO_FIELD_NUMBER;
            hash = (53 * hash) + getVideo().hashCode();
        }
        if (hasAudio()) {
            hash = (37 * hash) + AUDIO_FIELD_NUMBER;
            hash = (53 * hash) + getAudio().hashCode();
        }
        hash = (37 * hash) + ROOM_ID_FIELD_NUMBER;
        hash = (53 * hash) + getRoomId().hashCode();
        hash = (37 * hash) + STARTED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartedAt());
        hash = (37 * hash) + ENDED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndedAt());
        hash = (37 * hash) + UPDATED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUpdatedAt());
        hash = (37 * hash) + RESOURCE_ID_FIELD_NUMBER;
        hash = (53 * hash) + getResourceId().hashCode();
        if (getTracksCount() > 0) {
            hash = (37 * hash) + TRACKS_FIELD_NUMBER;
            hash = (53 * hash) + getTracksList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.IngressState prototype) {
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
     * Protobuf type {@code livekit.IngressState}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.IngressState)
            im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressState_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressState_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.IngressState.class,
                            im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.ingress.IngressState.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getVideoFieldBuilder();
                getAudioFieldBuilder();
                getTracksFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            status_ = 0;
            error_ = "";
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            audio_ = null;
            if (audioBuilder_ != null) {
                audioBuilder_.dispose();
                audioBuilder_ = null;
            }
            roomId_ = "";
            startedAt_ = 0L;
            endedAt_ = 0L;
            updatedAt_ = 0L;
            resourceId_ = "";
            if (tracksBuilder_ == null) {
                tracks_ = java.util.Collections.emptyList();
            } else {
                tracks_ = null;
                tracksBuilder_.clear();
            }
            bitField0_ &= ~0x00000200;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressState_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressState getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.IngressState.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressState build() {
            im.turms.plugin.livekit.core.proto.ingress.IngressState result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressState buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.IngressState result =
                    new im.turms.plugin.livekit.core.proto.ingress.IngressState(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.ingress.IngressState result) {
            if (tracksBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)) {
                    tracks_ = java.util.Collections.unmodifiableList(tracks_);
                    bitField0_ &= ~0x00000200;
                }
                result.tracks_ = tracks_;
            } else {
                result.tracks_ = tracksBuilder_.build();
            }
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.ingress.IngressState result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.status_ = status_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.error_ = error_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.video_ = videoBuilder_ == null
                        ? video_
                        : videoBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.audio_ = audioBuilder_ == null
                        ? audio_
                        : audioBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.roomId_ = roomId_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.startedAt_ = startedAt_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.endedAt_ = endedAt_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.updatedAt_ = updatedAt_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.resourceId_ = resourceId_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.IngressState) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.ingress.IngressState) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.ingress.IngressState other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.IngressState
                    .getDefaultInstance()) {
                return this;
            }
            if (other.status_ != 0) {
                setStatusValue(other.getStatusValue());
            }
            if (!other.getError()
                    .isEmpty()) {
                error_ = other.error_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.hasVideo()) {
                mergeVideo(other.getVideo());
            }
            if (other.hasAudio()) {
                mergeAudio(other.getAudio());
            }
            if (!other.getRoomId()
                    .isEmpty()) {
                roomId_ = other.roomId_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (other.getStartedAt() != 0L) {
                setStartedAt(other.getStartedAt());
            }
            if (other.getEndedAt() != 0L) {
                setEndedAt(other.getEndedAt());
            }
            if (other.getUpdatedAt() != 0L) {
                setUpdatedAt(other.getUpdatedAt());
            }
            if (!other.getResourceId()
                    .isEmpty()) {
                resourceId_ = other.resourceId_;
                bitField0_ |= 0x00000100;
                onChanged();
            }
            if (tracksBuilder_ == null) {
                if (!other.tracks_.isEmpty()) {
                    if (tracks_.isEmpty()) {
                        tracks_ = other.tracks_;
                        bitField0_ &= ~0x00000200;
                    } else {
                        ensureTracksIsMutable();
                        tracks_.addAll(other.tracks_);
                    }
                    onChanged();
                }
            } else {
                if (!other.tracks_.isEmpty()) {
                    if (tracksBuilder_.isEmpty()) {
                        tracksBuilder_.dispose();
                        tracksBuilder_ = null;
                        tracks_ = other.tracks_;
                        bitField0_ &= ~0x00000200;
                        tracksBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getTracksFieldBuilder()
                                : null;
                    } else {
                        tracksBuilder_.addAllMessages(other.tracks_);
                    }
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
                            status_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            error_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getVideoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            input.readMessage(getAudioFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            roomId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            im.turms.plugin.livekit.core.proto.models.TrackInfo m =
                                    input.readMessage(
                                            im.turms.plugin.livekit.core.proto.models.TrackInfo
                                                    .parser(),
                                            extensionRegistry);
                            if (tracksBuilder_ == null) {
                                ensureTracksIsMutable();
                                tracks_.add(m);
                            } else {
                                tracksBuilder_.addMessage(m);
                            }
                        } // case 50
                        case 56 -> {
                            startedAt_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 56
                        case 64 -> {
                            endedAt_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 64
                        case 74 -> {
                            resourceId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000100;
                        } // case 74
                        case 80 -> {
                            updatedAt_ = input.readInt64();
                            bitField0_ |= 0x00000080;
                        } // case 80
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

        private int status_ = 0;

        /**
         * <code>.livekit.IngressState.Status status = 1;</code>
         *
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }

        /**
         * <code>.livekit.IngressState.Status status = 1;</code>
         *
         * @param value The enum numeric value on the wire for status to set.
         * @return This builder for chaining.
         */
        public Builder setStatusValue(int value) {
            status_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressState.Status status = 1;</code>
         *
         * @return The status.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressState.Status getStatus() {
            im.turms.plugin.livekit.core.proto.ingress.IngressState.Status result =
                    im.turms.plugin.livekit.core.proto.ingress.IngressState.Status
                            .forNumber(status_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.ingress.IngressState.Status.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.IngressState.Status status = 1;</code>
         *
         * @param value The status to set.
         * @return This builder for chaining.
         */
        public Builder setStatus(
                im.turms.plugin.livekit.core.proto.ingress.IngressState.Status value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            status_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressState.Status status = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStatus() {
            bitField0_ &= ~0x00000001;
            status_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object error_ = "";

        /**
         * <pre>
         * Error/non compliance description if any
         * </pre>
         *
         * <code>string error = 2;</code>
         *
         * @return The error.
         */
        public java.lang.String getError() {
            java.lang.Object ref = error_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                error_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * Error/non compliance description if any
         * </pre>
         *
         * <code>string error = 2;</code>
         *
         * @return The bytes for error.
         */
        public com.google.protobuf.ByteString getErrorBytes() {
            java.lang.Object ref = error_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                error_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * Error/non compliance description if any
         * </pre>
         *
         * <code>string error = 2;</code>
         *
         * @param value The error to set.
         * @return This builder for chaining.
         */
        public Builder setError(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            error_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Error/non compliance description if any
         * </pre>
         *
         * <code>string error = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearError() {
            error_ = getDefaultInstance().getError();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Error/non compliance description if any
         * </pre>
         *
         * <code>string error = 2;</code>
         *
         * @param value The bytes for error to set.
         * @return This builder for chaining.
         */
        public Builder setErrorBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            error_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.ingress.InputVideoState video_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.InputVideoState, im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder, im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder> videoBuilder_;

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         *
         * @return Whether the video field is set.
         */
        public boolean hasVideo() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         *
         * @return The video.
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoState getVideo() {
            if (videoBuilder_ == null) {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.InputVideoState
                                .getDefaultInstance()
                        : video_;
            } else {
                return videoBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public Builder setVideo(im.turms.plugin.livekit.core.proto.ingress.InputVideoState value) {
            if (videoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                video_ = value;
            } else {
                videoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public Builder setVideo(
                im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder builderForValue) {
            if (videoBuilder_ == null) {
                video_ = builderForValue.build();
            } else {
                videoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public Builder mergeVideo(
                im.turms.plugin.livekit.core.proto.ingress.InputVideoState value) {
            if (videoBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)
                        && video_ != null
                        && video_ != im.turms.plugin.livekit.core.proto.ingress.InputVideoState
                                .getDefaultInstance()) {
                    getVideoBuilder().mergeFrom(value);
                } else {
                    video_ = value;
                }
            } else {
                videoBuilder_.mergeFrom(value);
            }
            if (video_ != null) {
                bitField0_ |= 0x00000004;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public Builder clearVideo() {
            bitField0_ &= ~0x00000004;
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder getVideoBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getVideoFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder getVideoOrBuilder() {
            if (videoBuilder_ != null) {
                return videoBuilder_.getMessageOrBuilder();
            } else {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.InputVideoState
                                .getDefaultInstance()
                        : video_;
            }
        }

        /**
         * <code>.livekit.InputVideoState video = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.InputVideoState, im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder, im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder> getVideoFieldBuilder() {
            if (videoBuilder_ == null) {
                videoBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getVideo(),
                        getParentForChildren(),
                        isClean());
                video_ = null;
            }
            return videoBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.ingress.InputAudioState audio_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.InputAudioState, im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder, im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder> audioBuilder_;

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         *
         * @return Whether the audio field is set.
         */
        public boolean hasAudio() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         *
         * @return The audio.
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioState getAudio() {
            if (audioBuilder_ == null) {
                return audio_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.InputAudioState
                                .getDefaultInstance()
                        : audio_;
            } else {
                return audioBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public Builder setAudio(im.turms.plugin.livekit.core.proto.ingress.InputAudioState value) {
            if (audioBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                audio_ = value;
            } else {
                audioBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public Builder setAudio(
                im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder builderForValue) {
            if (audioBuilder_ == null) {
                audio_ = builderForValue.build();
            } else {
                audioBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public Builder mergeAudio(
                im.turms.plugin.livekit.core.proto.ingress.InputAudioState value) {
            if (audioBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)
                        && audio_ != null
                        && audio_ != im.turms.plugin.livekit.core.proto.ingress.InputAudioState
                                .getDefaultInstance()) {
                    getAudioBuilder().mergeFrom(value);
                } else {
                    audio_ = value;
                }
            } else {
                audioBuilder_.mergeFrom(value);
            }
            if (audio_ != null) {
                bitField0_ |= 0x00000008;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public Builder clearAudio() {
            bitField0_ &= ~0x00000008;
            audio_ = null;
            if (audioBuilder_ != null) {
                audioBuilder_.dispose();
                audioBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder getAudioBuilder() {
            bitField0_ |= 0x00000008;
            onChanged();
            return getAudioFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder getAudioOrBuilder() {
            if (audioBuilder_ != null) {
                return audioBuilder_.getMessageOrBuilder();
            } else {
                return audio_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.InputAudioState
                                .getDefaultInstance()
                        : audio_;
            }
        }

        /**
         * <code>.livekit.InputAudioState audio = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.InputAudioState, im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder, im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder> getAudioFieldBuilder() {
            if (audioBuilder_ == null) {
                audioBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getAudio(),
                        getParentForChildren(),
                        isClean());
                audio_ = null;
            }
            return audioBuilder_;
        }

        private java.lang.Object roomId_ = "";

        /**
         * <pre>
         * ID of the current/previous room published to
         * </pre>
         *
         * <code>string room_id = 5;</code>
         *
         * @return The roomId.
         */
        public java.lang.String getRoomId() {
            java.lang.Object ref = roomId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                roomId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * ID of the current/previous room published to
         * </pre>
         *
         * <code>string room_id = 5;</code>
         *
         * @return The bytes for roomId.
         */
        public com.google.protobuf.ByteString getRoomIdBytes() {
            java.lang.Object ref = roomId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                roomId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * ID of the current/previous room published to
         * </pre>
         *
         * <code>string room_id = 5;</code>
         *
         * @param value The roomId to set.
         * @return This builder for chaining.
         */
        public Builder setRoomId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * ID of the current/previous room published to
         * </pre>
         *
         * <code>string room_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomId() {
            roomId_ = getDefaultInstance().getRoomId();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * ID of the current/previous room published to
         * </pre>
         *
         * <code>string room_id = 5;</code>
         *
         * @param value The bytes for roomId to set.
         * @return This builder for chaining.
         */
        public Builder setRoomIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            roomId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private long startedAt_;

        /**
         * <code>int64 started_at = 7;</code>
         *
         * @return The startedAt.
         */
        @java.lang.Override
        public long getStartedAt() {
            return startedAt_;
        }

        /**
         * <code>int64 started_at = 7;</code>
         *
         * @param value The startedAt to set.
         * @return This builder for chaining.
         */
        public Builder setStartedAt(long value) {

            startedAt_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>int64 started_at = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartedAt() {
            bitField0_ &= ~0x00000020;
            startedAt_ = 0L;
            onChanged();
            return this;
        }

        private long endedAt_;

        /**
         * <code>int64 ended_at = 8;</code>
         *
         * @return The endedAt.
         */
        @java.lang.Override
        public long getEndedAt() {
            return endedAt_;
        }

        /**
         * <code>int64 ended_at = 8;</code>
         *
         * @param value The endedAt to set.
         * @return This builder for chaining.
         */
        public Builder setEndedAt(long value) {

            endedAt_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>int64 ended_at = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndedAt() {
            bitField0_ &= ~0x00000040;
            endedAt_ = 0L;
            onChanged();
            return this;
        }

        private long updatedAt_;

        /**
         * <code>int64 updated_at = 10;</code>
         *
         * @return The updatedAt.
         */
        @java.lang.Override
        public long getUpdatedAt() {
            return updatedAt_;
        }

        /**
         * <code>int64 updated_at = 10;</code>
         *
         * @param value The updatedAt to set.
         * @return This builder for chaining.
         */
        public Builder setUpdatedAt(long value) {

            updatedAt_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>int64 updated_at = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUpdatedAt() {
            bitField0_ &= ~0x00000080;
            updatedAt_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object resourceId_ = "";

        /**
         * <code>string resource_id = 9;</code>
         *
         * @return The resourceId.
         */
        public java.lang.String getResourceId() {
            java.lang.Object ref = resourceId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                resourceId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string resource_id = 9;</code>
         *
         * @return The bytes for resourceId.
         */
        public com.google.protobuf.ByteString getResourceIdBytes() {
            java.lang.Object ref = resourceId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                resourceId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string resource_id = 9;</code>
         *
         * @param value The resourceId to set.
         * @return This builder for chaining.
         */
        public Builder setResourceId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            resourceId_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string resource_id = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearResourceId() {
            resourceId_ = getDefaultInstance().getResourceId();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string resource_id = 9;</code>
         *
         * @param value The bytes for resourceId to set.
         * @return This builder for chaining.
         */
        public Builder setResourceIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            resourceId_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> tracks_ =
                java.util.Collections.emptyList();

        private void ensureTracksIsMutable() {
            if ((bitField0_ & 0x00000200) == 0) {
                tracks_ = new java.util.ArrayList<>(tracks_);
                bitField0_ |= 0x00000200;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> tracksBuilder_;

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList() {
            if (tracksBuilder_ == null) {
                return java.util.Collections.unmodifiableList(tracks_);
            } else {
                return tracksBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public int getTracksCount() {
            if (tracksBuilder_ == null) {
                return tracks_.size();
            } else {
                return tracksBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index) {
            if (tracksBuilder_ == null) {
                return tracks_.get(index);
            } else {
                return tracksBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder setTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (tracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureTracksIsMutable();
                tracks_.set(index, value);
                onChanged();
            } else {
                tracksBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder setTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder builderForValue) {
            if (tracksBuilder_ == null) {
                ensureTracksIsMutable();
                tracks_.set(index, builderForValue.build());
                onChanged();
            } else {
                tracksBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder addTracks(im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (tracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureTracksIsMutable();
                tracks_.add(value);
                onChanged();
            } else {
                tracksBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder addTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (tracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureTracksIsMutable();
                tracks_.add(index, value);
                onChanged();
            } else {
                tracksBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder addTracks(
                im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder builderForValue) {
            if (tracksBuilder_ == null) {
                ensureTracksIsMutable();
                tracks_.add(builderForValue.build());
                onChanged();
            } else {
                tracksBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder addTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder builderForValue) {
            if (tracksBuilder_ == null) {
                ensureTracksIsMutable();
                tracks_.add(index, builderForValue.build());
                onChanged();
            } else {
                tracksBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder addAllTracks(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.TrackInfo> values) {
            if (tracksBuilder_ == null) {
                ensureTracksIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, tracks_);
                onChanged();
            } else {
                tracksBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder clearTracks() {
            if (tracksBuilder_ == null) {
                tracks_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000200;
                onChanged();
            } else {
                tracksBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public Builder removeTracks(int index) {
            if (tracksBuilder_ == null) {
                ensureTracksIsMutable();
                tracks_.remove(index);
                onChanged();
            } else {
                tracksBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder getTracksBuilder(
                int index) {
            return getTracksFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTracksOrBuilder(
                int index) {
            if (tracksBuilder_ == null) {
                return tracks_.get(index);
            } else {
                return tracksBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList() {
            if (tracksBuilder_ != null) {
                return tracksBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(tracks_);
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder addTracksBuilder() {
            return getTracksFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder addTracksBuilder(
                int index) {
            return getTracksFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 6;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder> getTracksBuilderList() {
            return getTracksFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksFieldBuilder() {
            if (tracksBuilder_ == null) {
                tracksBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        tracks_,
                        ((bitField0_ & 0x00000200) != 0),
                        getParentForChildren(),
                        isClean());
                tracks_ = null;
            }
            return tracksBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.IngressState)
    }

    // @@protoc_insertion_point(class_scope:livekit.IngressState)
    private static final im.turms.plugin.livekit.core.proto.ingress.IngressState DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.IngressState();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<IngressState> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public IngressState parsePartialFrom(
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

    public static com.google.protobuf.Parser<IngressState> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<IngressState> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressState getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}