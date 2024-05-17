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
 * Protobuf type {@code livekit.EgressInfo}
 */
public final class EgressInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.EgressInfo)
        EgressInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                EgressInfo.class.getName());
    }

    // Use EgressInfo.newBuilder() to construct.
    private EgressInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private EgressInfo() {
        egressId_ = "";
        roomId_ = "";
        roomName_ = "";
        status_ = 0;
        details_ = "";
        error_ = "";
        streamResults_ = java.util.Collections.emptyList();
        fileResults_ = java.util.Collections.emptyList();
        segmentResults_ = java.util.Collections.emptyList();
        imageResults_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EgressInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EgressInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.EgressInfo.class,
                        im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder.class);
    }

    private int requestCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object request_;

    public enum RequestCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        ROOM_COMPOSITE(4),
        WEB(14),
        PARTICIPANT(19),
        TRACK_COMPOSITE(5),
        TRACK(6),
        REQUEST_NOT_SET(0);

        private final int value;

        RequestCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static RequestCase valueOf(int value) {
            return forNumber(value);
        }

        public static RequestCase forNumber(int value) {
            return switch (value) {
                case 4 -> ROOM_COMPOSITE;
                case 14 -> WEB;
                case 19 -> PARTICIPANT;
                case 5 -> TRACK_COMPOSITE;
                case 6 -> TRACK;
                case 0 -> REQUEST_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public RequestCase getRequestCase() {
        return RequestCase.forNumber(requestCase_);
    }

    private int resultCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object result_;

    public enum ResultCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        @java.lang.Deprecated
        STREAM(7),
        @java.lang.Deprecated
        FILE(8),
        @java.lang.Deprecated
        SEGMENTS(12),
        RESULT_NOT_SET(0);

        private final int value;

        ResultCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static ResultCase valueOf(int value) {
            return forNumber(value);
        }

        public static ResultCase forNumber(int value) {
            return switch (value) {
                case 7 -> STREAM;
                case 8 -> FILE;
                case 12 -> SEGMENTS;
                case 0 -> RESULT_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ResultCase getResultCase() {
        return ResultCase.forNumber(resultCase_);
    }

    public static final int EGRESS_ID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object egressId_ = "";

    /**
     * <code>string egress_id = 1;</code>
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
     * <code>string egress_id = 1;</code>
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

    public static final int ROOM_ID_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomId_ = "";

    /**
     * <code>string room_id = 2;</code>
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
     * <code>string room_id = 2;</code>
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

    public static final int ROOM_NAME_FIELD_NUMBER = 13;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <code>string room_name = 13;</code>
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
     * <code>string room_name = 13;</code>
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

    public static final int STATUS_FIELD_NUMBER = 3;
    private int status_ = 0;

    /**
     * <code>.livekit.EgressStatus status = 3;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override
    public int getStatusValue() {
        return status_;
    }

    /**
     * <code>.livekit.EgressStatus status = 3;</code>
     *
     * @return The status.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EgressStatus getStatus() {
        im.turms.plugin.livekit.core.proto.egress.EgressStatus result =
                im.turms.plugin.livekit.core.proto.egress.EgressStatus.forNumber(status_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.EgressStatus.UNRECOGNIZED
                : result;
    }

    public static final int STARTED_AT_FIELD_NUMBER = 10;
    private long startedAt_ = 0L;

    /**
     * <code>int64 started_at = 10;</code>
     *
     * @return The startedAt.
     */
    @java.lang.Override
    public long getStartedAt() {
        return startedAt_;
    }

    public static final int ENDED_AT_FIELD_NUMBER = 11;
    private long endedAt_ = 0L;

    /**
     * <code>int64 ended_at = 11;</code>
     *
     * @return The endedAt.
     */
    @java.lang.Override
    public long getEndedAt() {
        return endedAt_;
    }

    public static final int UPDATED_AT_FIELD_NUMBER = 18;
    private long updatedAt_ = 0L;

    /**
     * <code>int64 updated_at = 18;</code>
     *
     * @return The updatedAt.
     */
    @java.lang.Override
    public long getUpdatedAt() {
        return updatedAt_;
    }

    public static final int DETAILS_FIELD_NUMBER = 21;
    @SuppressWarnings("serial")
    private volatile java.lang.Object details_ = "";

    /**
     * <code>string details = 21;</code>
     *
     * @return The details.
     */
    @java.lang.Override
    public java.lang.String getDetails() {
        java.lang.Object ref = details_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            details_ = s;
            return s;
        }
    }

    /**
     * <code>string details = 21;</code>
     *
     * @return The bytes for details.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getDetailsBytes() {
        java.lang.Object ref = details_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            details_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ERROR_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object error_ = "";

    /**
     * <code>string error = 9;</code>
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
     * <code>string error = 9;</code>
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

    public static final int ROOM_COMPOSITE_FIELD_NUMBER = 4;

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     *
     * @return Whether the roomComposite field is set.
     */
    @java.lang.Override
    public boolean hasRoomComposite() {
        return requestCase_ == 4;
    }

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     *
     * @return The roomComposite.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoomComposite() {
        if (requestCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                .getDefaultInstance();
    }

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomCompositeOrBuilder() {
        if (requestCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                .getDefaultInstance();
    }

    public static final int WEB_FIELD_NUMBER = 14;

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     *
     * @return Whether the web field is set.
     */
    @java.lang.Override
    public boolean hasWeb() {
        return requestCase_ == 14;
    }

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     *
     * @return The web.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getWeb() {
        if (requestCase_ == 14) {
            return (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.getDefaultInstance();
    }

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder getWebOrBuilder() {
        if (requestCase_ == 14) {
            return (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.getDefaultInstance();
    }

    public static final int PARTICIPANT_FIELD_NUMBER = 19;

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     *
     * @return Whether the participant field is set.
     */
    @java.lang.Override
    public boolean hasParticipant() {
        return requestCase_ == 19;
    }

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     *
     * @return The participant.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getParticipant() {
        if (requestCase_ == 19) {
            return (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                .getDefaultInstance();
    }

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder getParticipantOrBuilder() {
        if (requestCase_ == 19) {
            return (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                .getDefaultInstance();
    }

    public static final int TRACK_COMPOSITE_FIELD_NUMBER = 5;

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     *
     * @return Whether the trackComposite field is set.
     */
    @java.lang.Override
    public boolean hasTrackComposite() {
        return requestCase_ == 5;
    }

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     *
     * @return The trackComposite.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest getTrackComposite() {
        if (requestCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                .getDefaultInstance();
    }

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequestOrBuilder getTrackCompositeOrBuilder() {
        if (requestCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                .getDefaultInstance();
    }

    public static final int TRACK_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     *
     * @return Whether the track field is set.
     */
    @java.lang.Override
    public boolean hasTrack() {
        return requestCase_ == 6;
    }

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     *
     * @return The track.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getTrack() {
        if (requestCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.getDefaultInstance();
    }

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder getTrackOrBuilder() {
        if (requestCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_;
        }
        return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.getDefaultInstance();
    }

    public static final int STREAM_FIELD_NUMBER = 7;

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
     * @return Whether the stream field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasStream() {
        return resultCase_ == 7;
    }

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
     * @return The stream.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamInfoList getStream() {
        if (resultCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamInfoList.getDefaultInstance();
    }

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder getStreamOrBuilder() {
        if (resultCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamInfoList.getDefaultInstance();
    }

    public static final int FILE_FIELD_NUMBER = 8;

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
     * @return Whether the file field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasFile() {
        return resultCase_ == 8;
    }

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
     * @return The file.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.FileInfo getFile() {
        if (resultCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
    }

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileOrBuilder() {
        if (resultCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
    }

    public static final int SEGMENTS_FIELD_NUMBER = 12;

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
     * @return Whether the segments field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasSegments() {
        return resultCase_ == 12;
    }

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
     * @return The segments.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegments() {
        if (resultCase_ == 12) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
    }

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentsOrBuilder() {
        if (resultCase_ == 12) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
    }

    public static final int STREAM_RESULTS_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> streamResults_;

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> getStreamResultsList() {
        return streamResults_;
    }

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getStreamResultsOrBuilderList() {
        return streamResults_;
    }

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    @java.lang.Override
    public int getStreamResultsCount() {
        return streamResults_.size();
    }

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfo getStreamResults(int index) {
        return streamResults_.get(index);
    }

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder getStreamResultsOrBuilder(
            int index) {
        return streamResults_.get(index);
    }

    public static final int FILE_RESULTS_FIELD_NUMBER = 16;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo> fileResults_;

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo> getFileResultsList() {
        return fileResults_;
    }

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> getFileResultsOrBuilderList() {
        return fileResults_;
    }

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    @java.lang.Override
    public int getFileResultsCount() {
        return fileResults_.size();
    }

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.FileInfo getFileResults(int index) {
        return fileResults_.get(index);
    }

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileResultsOrBuilder(
            int index) {
        return fileResults_.get(index);
    }

    public static final int SEGMENT_RESULTS_FIELD_NUMBER = 17;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> segmentResults_;

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> getSegmentResultsList() {
        return segmentResults_;
    }

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> getSegmentResultsOrBuilderList() {
        return segmentResults_;
    }

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    @java.lang.Override
    public int getSegmentResultsCount() {
        return segmentResults_.size();
    }

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegmentResults(int index) {
        return segmentResults_.get(index);
    }

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentResultsOrBuilder(
            int index) {
        return segmentResults_.get(index);
    }

    public static final int IMAGE_RESULTS_FIELD_NUMBER = 20;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo> imageResults_;

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo> getImageResultsList() {
        return imageResults_;
    }

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder> getImageResultsOrBuilderList() {
        return imageResults_;
    }

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    @java.lang.Override
    public int getImageResultsCount() {
        return imageResults_.size();
    }

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImagesInfo getImageResults(int index) {
        return imageResults_.get(index);
    }

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder getImageResultsOrBuilder(
            int index) {
        return imageResults_.get(index);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, egressId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, roomId_);
        }
        if (status_ != im.turms.plugin.livekit.core.proto.egress.EgressStatus.EGRESS_STARTING
                .getNumber()) {
            output.writeEnum(3, status_);
        }
        if (requestCase_ == 4) {
            output.writeMessage(4,
                    (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_);
        }
        if (requestCase_ == 5) {
            output.writeMessage(5,
                    (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_);
        }
        if (requestCase_ == 6) {
            output.writeMessage(6,
                    (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_);
        }
        if (resultCase_ == 7) {
            output.writeMessage(7,
                    (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_);
        }
        if (resultCase_ == 8) {
            output.writeMessage(8, (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, error_);
        }
        if (startedAt_ != 0L) {
            output.writeInt64(10, startedAt_);
        }
        if (endedAt_ != 0L) {
            output.writeInt64(11, endedAt_);
        }
        if (resultCase_ == 12) {
            output.writeMessage(12,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 13, roomName_);
        }
        if (requestCase_ == 14) {
            output.writeMessage(14,
                    (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_);
        }
        for (StreamInfo streamInfo : streamResults_) {
            output.writeMessage(15, streamInfo);
        }
        for (FileInfo fileInfo : fileResults_) {
            output.writeMessage(16, fileInfo);
        }
        for (SegmentsInfo segmentsInfo : segmentResults_) {
            output.writeMessage(17, segmentsInfo);
        }
        if (updatedAt_ != 0L) {
            output.writeInt64(18, updatedAt_);
        }
        if (requestCase_ == 19) {
            output.writeMessage(19,
                    (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_);
        }
        for (ImagesInfo imagesInfo : imageResults_) {
            output.writeMessage(20, imagesInfo);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(details_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 21, details_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, egressId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, roomId_);
        }
        if (status_ != im.turms.plugin.livekit.core.proto.egress.EgressStatus.EGRESS_STARTING
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(3, status_);
        }
        if (requestCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_);
        }
        if (requestCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                    (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_);
        }
        if (requestCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_);
        }
        if (resultCase_ == 7) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
                    (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_);
        }
        if (resultCase_ == 8) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                    (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, error_);
        }
        if (startedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(10, startedAt_);
        }
        if (endedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(11, endedAt_);
        }
        if (resultCase_ == 12) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(12,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(13, roomName_);
        }
        if (requestCase_ == 14) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(14,
                    (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_);
        }
        for (StreamInfo streamInfo : streamResults_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, streamInfo);
        }
        for (FileInfo fileInfo : fileResults_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(16, fileInfo);
        }
        for (SegmentsInfo segmentsInfo : segmentResults_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(17, segmentsInfo);
        }
        if (updatedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(18, updatedAt_);
        }
        if (requestCase_ == 19) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(19,
                    (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_);
        }
        for (ImagesInfo imagesInfo : imageResults_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(20, imagesInfo);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(details_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(21, details_);
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
        if (!(obj instanceof EgressInfo other)) {
            return super.equals(obj);
        }

        if (!getEgressId().equals(other.getEgressId())) {
            return false;
        }
        if (!getRoomId().equals(other.getRoomId())) {
            return false;
        }
        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (status_ != other.status_) {
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
        if (!getDetails().equals(other.getDetails())) {
            return false;
        }
        if (!getError().equals(other.getError())) {
            return false;
        }
        if (!getStreamResultsList().equals(other.getStreamResultsList())) {
            return false;
        }
        if (!getFileResultsList().equals(other.getFileResultsList())) {
            return false;
        }
        if (!getSegmentResultsList().equals(other.getSegmentResultsList())) {
            return false;
        }
        if (!getImageResultsList().equals(other.getImageResultsList())) {
            return false;
        }
        if (!getRequestCase().equals(other.getRequestCase())) {
            return false;
        }
        switch (requestCase_) {
            case 4 -> {
                if (!getRoomComposite().equals(other.getRoomComposite())) {
                    return false;
                }
            }
            case 14 -> {
                if (!getWeb().equals(other.getWeb())) {
                    return false;
                }
            }
            case 19 -> {
                if (!getParticipant().equals(other.getParticipant())) {
                    return false;
                }
            }
            case 5 -> {
                if (!getTrackComposite().equals(other.getTrackComposite())) {
                    return false;
                }
            }
            case 6 -> {
                if (!getTrack().equals(other.getTrack())) {
                    return false;
                }
            }
            default -> {
            }
        }
        if (!getResultCase().equals(other.getResultCase())) {
            return false;
        }
        switch (resultCase_) {
            case 7 -> {
                if (!getStream().equals(other.getStream())) {
                    return false;
                }
            }
            case 8 -> {
                if (!getFile().equals(other.getFile())) {
                    return false;
                }
            }
            case 12 -> {
                if (!getSegments().equals(other.getSegments())) {
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
        hash = (37 * hash) + EGRESS_ID_FIELD_NUMBER;
        hash = (53 * hash) + getEgressId().hashCode();
        hash = (37 * hash) + ROOM_ID_FIELD_NUMBER;
        hash = (53 * hash) + getRoomId().hashCode();
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + STATUS_FIELD_NUMBER;
        hash = (53 * hash) + status_;
        hash = (37 * hash) + STARTED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartedAt());
        hash = (37 * hash) + ENDED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndedAt());
        hash = (37 * hash) + UPDATED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUpdatedAt());
        hash = (37 * hash) + DETAILS_FIELD_NUMBER;
        hash = (53 * hash) + getDetails().hashCode();
        hash = (37 * hash) + ERROR_FIELD_NUMBER;
        hash = (53 * hash) + getError().hashCode();
        if (getStreamResultsCount() > 0) {
            hash = (37 * hash) + STREAM_RESULTS_FIELD_NUMBER;
            hash = (53 * hash) + getStreamResultsList().hashCode();
        }
        if (getFileResultsCount() > 0) {
            hash = (37 * hash) + FILE_RESULTS_FIELD_NUMBER;
            hash = (53 * hash) + getFileResultsList().hashCode();
        }
        if (getSegmentResultsCount() > 0) {
            hash = (37 * hash) + SEGMENT_RESULTS_FIELD_NUMBER;
            hash = (53 * hash) + getSegmentResultsList().hashCode();
        }
        if (getImageResultsCount() > 0) {
            hash = (37 * hash) + IMAGE_RESULTS_FIELD_NUMBER;
            hash = (53 * hash) + getImageResultsList().hashCode();
        }
        switch (requestCase_) {
            case 4 -> {
                hash = (37 * hash) + ROOM_COMPOSITE_FIELD_NUMBER;
                hash = (53 * hash) + getRoomComposite().hashCode();
            }
            case 14 -> {
                hash = (37 * hash) + WEB_FIELD_NUMBER;
                hash = (53 * hash) + getWeb().hashCode();
            }
            case 19 -> {
                hash = (37 * hash) + PARTICIPANT_FIELD_NUMBER;
                hash = (53 * hash) + getParticipant().hashCode();
            }
            case 5 -> {
                hash = (37 * hash) + TRACK_COMPOSITE_FIELD_NUMBER;
                hash = (53 * hash) + getTrackComposite().hashCode();
            }
            case 6 -> {
                hash = (37 * hash) + TRACK_FIELD_NUMBER;
                hash = (53 * hash) + getTrack().hashCode();
            }
            default -> {
            }
        }
        switch (resultCase_) {
            case 7 -> {
                hash = (37 * hash) + STREAM_FIELD_NUMBER;
                hash = (53 * hash) + getStream().hashCode();
            }
            case 8 -> {
                hash = (37 * hash) + FILE_FIELD_NUMBER;
                hash = (53 * hash) + getFile().hashCode();
            }
            case 12 -> {
                hash = (37 * hash) + SEGMENTS_FIELD_NUMBER;
                hash = (53 * hash) + getSegments().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.EgressInfo prototype) {
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
     * Protobuf type {@code livekit.EgressInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.EgressInfo)
            im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EgressInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EgressInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.EgressInfo.class,
                            im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.EgressInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            egressId_ = "";
            roomId_ = "";
            roomName_ = "";
            status_ = 0;
            startedAt_ = 0L;
            endedAt_ = 0L;
            updatedAt_ = 0L;
            details_ = "";
            error_ = "";
            if (roomCompositeBuilder_ != null) {
                roomCompositeBuilder_.clear();
            }
            if (webBuilder_ != null) {
                webBuilder_.clear();
            }
            if (participantBuilder_ != null) {
                participantBuilder_.clear();
            }
            if (trackCompositeBuilder_ != null) {
                trackCompositeBuilder_.clear();
            }
            if (trackBuilder_ != null) {
                trackBuilder_.clear();
            }
            if (streamBuilder_ != null) {
                streamBuilder_.clear();
            }
            if (fileBuilder_ != null) {
                fileBuilder_.clear();
            }
            if (segmentsBuilder_ != null) {
                segmentsBuilder_.clear();
            }
            if (streamResultsBuilder_ == null) {
                streamResults_ = java.util.Collections.emptyList();
            } else {
                streamResults_ = null;
                streamResultsBuilder_.clear();
            }
            bitField0_ &= ~0x00020000;
            if (fileResultsBuilder_ == null) {
                fileResults_ = java.util.Collections.emptyList();
            } else {
                fileResults_ = null;
                fileResultsBuilder_.clear();
            }
            bitField0_ &= ~0x00040000;
            if (segmentResultsBuilder_ == null) {
                segmentResults_ = java.util.Collections.emptyList();
            } else {
                segmentResults_ = null;
                segmentResultsBuilder_.clear();
            }
            bitField0_ &= ~0x00080000;
            if (imageResultsBuilder_ == null) {
                imageResults_ = java.util.Collections.emptyList();
            } else {
                imageResults_ = null;
                imageResultsBuilder_.clear();
            }
            bitField0_ &= ~0x00100000;
            requestCase_ = 0;
            request_ = null;
            resultCase_ = 0;
            result_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EgressInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EgressInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.EgressInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EgressInfo build() {
            im.turms.plugin.livekit.core.proto.egress.EgressInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EgressInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.EgressInfo result =
                    new im.turms.plugin.livekit.core.proto.egress.EgressInfo(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.EgressInfo result) {
            if (streamResultsBuilder_ == null) {
                if (((bitField0_ & 0x00020000) != 0)) {
                    streamResults_ = java.util.Collections.unmodifiableList(streamResults_);
                    bitField0_ &= ~0x00020000;
                }
                result.streamResults_ = streamResults_;
            } else {
                result.streamResults_ = streamResultsBuilder_.build();
            }
            if (fileResultsBuilder_ == null) {
                if (((bitField0_ & 0x00040000) != 0)) {
                    fileResults_ = java.util.Collections.unmodifiableList(fileResults_);
                    bitField0_ &= ~0x00040000;
                }
                result.fileResults_ = fileResults_;
            } else {
                result.fileResults_ = fileResultsBuilder_.build();
            }
            if (segmentResultsBuilder_ == null) {
                if (((bitField0_ & 0x00080000) != 0)) {
                    segmentResults_ = java.util.Collections.unmodifiableList(segmentResults_);
                    bitField0_ &= ~0x00080000;
                }
                result.segmentResults_ = segmentResults_;
            } else {
                result.segmentResults_ = segmentResultsBuilder_.build();
            }
            if (imageResultsBuilder_ == null) {
                if (((bitField0_ & 0x00100000) != 0)) {
                    imageResults_ = java.util.Collections.unmodifiableList(imageResults_);
                    bitField0_ &= ~0x00100000;
                }
                result.imageResults_ = imageResults_;
            } else {
                result.imageResults_ = imageResultsBuilder_.build();
            }
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.EgressInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.egressId_ = egressId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.roomId_ = roomId_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.status_ = status_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.startedAt_ = startedAt_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.endedAt_ = endedAt_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.updatedAt_ = updatedAt_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.details_ = details_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.error_ = error_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.EgressInfo result) {
            result.requestCase_ = requestCase_;
            result.request_ = this.request_;
            if (requestCase_ == 4 && roomCompositeBuilder_ != null) {
                result.request_ = roomCompositeBuilder_.build();
            }
            if (requestCase_ == 14 && webBuilder_ != null) {
                result.request_ = webBuilder_.build();
            }
            if (requestCase_ == 19 && participantBuilder_ != null) {
                result.request_ = participantBuilder_.build();
            }
            if (requestCase_ == 5 && trackCompositeBuilder_ != null) {
                result.request_ = trackCompositeBuilder_.build();
            }
            if (requestCase_ == 6 && trackBuilder_ != null) {
                result.request_ = trackBuilder_.build();
            }
            result.resultCase_ = resultCase_;
            result.result_ = this.result_;
            if (resultCase_ == 7 && streamBuilder_ != null) {
                result.result_ = streamBuilder_.build();
            }
            if (resultCase_ == 8 && fileBuilder_ != null) {
                result.result_ = fileBuilder_.build();
            }
            if (resultCase_ == 12 && segmentsBuilder_ != null) {
                result.result_ = segmentsBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.EgressInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.EgressInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.EgressInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.EgressInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getEgressId()
                    .isEmpty()) {
                egressId_ = other.egressId_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getRoomId()
                    .isEmpty()) {
                roomId_ = other.roomId_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.status_ != 0) {
                setStatusValue(other.getStatusValue());
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
            if (!other.getDetails()
                    .isEmpty()) {
                details_ = other.details_;
                bitField0_ |= 0x00000080;
                onChanged();
            }
            if (!other.getError()
                    .isEmpty()) {
                error_ = other.error_;
                bitField0_ |= 0x00000100;
                onChanged();
            }
            if (streamResultsBuilder_ == null) {
                if (!other.streamResults_.isEmpty()) {
                    if (streamResults_.isEmpty()) {
                        streamResults_ = other.streamResults_;
                        bitField0_ &= ~0x00020000;
                    } else {
                        ensureStreamResultsIsMutable();
                        streamResults_.addAll(other.streamResults_);
                    }
                    onChanged();
                }
            } else {
                if (!other.streamResults_.isEmpty()) {
                    if (streamResultsBuilder_.isEmpty()) {
                        streamResultsBuilder_.dispose();
                        streamResultsBuilder_ = null;
                        streamResults_ = other.streamResults_;
                        bitField0_ &= ~0x00020000;
                        streamResultsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getStreamResultsFieldBuilder()
                                        : null;
                    } else {
                        streamResultsBuilder_.addAllMessages(other.streamResults_);
                    }
                }
            }
            if (fileResultsBuilder_ == null) {
                if (!other.fileResults_.isEmpty()) {
                    if (fileResults_.isEmpty()) {
                        fileResults_ = other.fileResults_;
                        bitField0_ &= ~0x00040000;
                    } else {
                        ensureFileResultsIsMutable();
                        fileResults_.addAll(other.fileResults_);
                    }
                    onChanged();
                }
            } else {
                if (!other.fileResults_.isEmpty()) {
                    if (fileResultsBuilder_.isEmpty()) {
                        fileResultsBuilder_.dispose();
                        fileResultsBuilder_ = null;
                        fileResults_ = other.fileResults_;
                        bitField0_ &= ~0x00040000;
                        fileResultsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getFileResultsFieldBuilder()
                                        : null;
                    } else {
                        fileResultsBuilder_.addAllMessages(other.fileResults_);
                    }
                }
            }
            if (segmentResultsBuilder_ == null) {
                if (!other.segmentResults_.isEmpty()) {
                    if (segmentResults_.isEmpty()) {
                        segmentResults_ = other.segmentResults_;
                        bitField0_ &= ~0x00080000;
                    } else {
                        ensureSegmentResultsIsMutable();
                        segmentResults_.addAll(other.segmentResults_);
                    }
                    onChanged();
                }
            } else {
                if (!other.segmentResults_.isEmpty()) {
                    if (segmentResultsBuilder_.isEmpty()) {
                        segmentResultsBuilder_.dispose();
                        segmentResultsBuilder_ = null;
                        segmentResults_ = other.segmentResults_;
                        bitField0_ &= ~0x00080000;
                        segmentResultsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getSegmentResultsFieldBuilder()
                                        : null;
                    } else {
                        segmentResultsBuilder_.addAllMessages(other.segmentResults_);
                    }
                }
            }
            if (imageResultsBuilder_ == null) {
                if (!other.imageResults_.isEmpty()) {
                    if (imageResults_.isEmpty()) {
                        imageResults_ = other.imageResults_;
                        bitField0_ &= ~0x00100000;
                    } else {
                        ensureImageResultsIsMutable();
                        imageResults_.addAll(other.imageResults_);
                    }
                    onChanged();
                }
            } else {
                if (!other.imageResults_.isEmpty()) {
                    if (imageResultsBuilder_.isEmpty()) {
                        imageResultsBuilder_.dispose();
                        imageResultsBuilder_ = null;
                        imageResults_ = other.imageResults_;
                        bitField0_ &= ~0x00100000;
                        imageResultsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getImageResultsFieldBuilder()
                                        : null;
                    } else {
                        imageResultsBuilder_.addAllMessages(other.imageResults_);
                    }
                }
            }
            switch (other.getRequestCase()) {
                case ROOM_COMPOSITE -> mergeRoomComposite(other.getRoomComposite());
                case WEB -> mergeWeb(other.getWeb());
                case PARTICIPANT -> mergeParticipant(other.getParticipant());
                case TRACK_COMPOSITE -> mergeTrackComposite(other.getTrackComposite());
                case TRACK -> mergeTrack(other.getTrack());
                case REQUEST_NOT_SET -> {
                }
            }
            switch (other.getResultCase()) {
                case STREAM -> mergeStream(other.getStream());
                case FILE -> mergeFile(other.getFile());
                case SEGMENTS -> mergeSegments(other.getSegments());
                case RESULT_NOT_SET -> {
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
                            egressId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            roomId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            status_ = input.readEnum();
                            bitField0_ |= 0x00000008;
                        } // case 24
                        case 34 -> {
                            input.readMessage(getRoomCompositeFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            requestCase_ = 4;
                        } // case 34
                        case 42 -> {
                            input.readMessage(getTrackCompositeFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            requestCase_ = 5;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getTrackFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            requestCase_ = 6;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getStreamFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            resultCase_ = 7;
                        } // case 58
                        case 66 -> {
                            input.readMessage(getFileFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            resultCase_ = 8;
                        } // case 66
                        case 74 -> {
                            error_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000100;
                        } // case 74
                        case 80 -> {
                            startedAt_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 80
                        case 88 -> {
                            endedAt_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 88
                        case 98 -> {
                            input.readMessage(getSegmentsFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            resultCase_ = 12;
                        } // case 98
                        case 106 -> {
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 106
                        case 114 -> {
                            input.readMessage(getWebFieldBuilder().getBuilder(), extensionRegistry);
                            requestCase_ = 14;
                        } // case 114
                        case 122 -> {
                            StreamInfo m =
                                    input.readMessage(StreamInfo.parser(), extensionRegistry);
                            if (streamResultsBuilder_ == null) {
                                ensureStreamResultsIsMutable();
                                streamResults_.add(m);
                            } else {
                                streamResultsBuilder_.addMessage(m);
                            }
                        } // case 122
                        case 130 -> {
                            FileInfo m = input.readMessage(FileInfo.parser(), extensionRegistry);
                            if (fileResultsBuilder_ == null) {
                                ensureFileResultsIsMutable();
                                fileResults_.add(m);
                            } else {
                                fileResultsBuilder_.addMessage(m);
                            }
                        } // case 130
                        case 138 -> {
                            SegmentsInfo m =
                                    input.readMessage(SegmentsInfo.parser(), extensionRegistry);
                            if (segmentResultsBuilder_ == null) {
                                ensureSegmentResultsIsMutable();
                                segmentResults_.add(m);
                            } else {
                                segmentResultsBuilder_.addMessage(m);
                            }
                        } // case 138
                        case 144 -> {
                            updatedAt_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 144
                        case 154 -> {
                            input.readMessage(getParticipantFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            requestCase_ = 19;
                        } // case 154
                        case 162 -> {
                            ImagesInfo m =
                                    input.readMessage(ImagesInfo.parser(), extensionRegistry);
                            if (imageResultsBuilder_ == null) {
                                ensureImageResultsIsMutable();
                                imageResults_.add(m);
                            } else {
                                imageResultsBuilder_.addMessage(m);
                            }
                        } // case 162
                        case 170 -> {
                            details_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000080;
                        } // case 170
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

        private int requestCase_ = 0;
        private java.lang.Object request_;

        public RequestCase getRequestCase() {
            return RequestCase.forNumber(requestCase_);
        }

        public Builder clearRequest() {
            requestCase_ = 0;
            request_ = null;
            onChanged();
            return this;
        }

        private int resultCase_ = 0;
        private java.lang.Object result_;

        public ResultCase getResultCase() {
            return ResultCase.forNumber(resultCase_);
        }

        public Builder clearResult() {
            resultCase_ = 0;
            result_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        private java.lang.Object egressId_ = "";

        /**
         * <code>string egress_id = 1;</code>
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
         * <code>string egress_id = 1;</code>
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
         * <code>string egress_id = 1;</code>
         *
         * @param value The egressId to set.
         * @return This builder for chaining.
         */
        public Builder setEgressId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            egressId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string egress_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEgressId() {
            egressId_ = getDefaultInstance().getEgressId();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string egress_id = 1;</code>
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
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object roomId_ = "";

        /**
         * <code>string room_id = 2;</code>
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
         * <code>string room_id = 2;</code>
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
         * <code>string room_id = 2;</code>
         *
         * @param value The roomId to set.
         * @return This builder for chaining.
         */
        public Builder setRoomId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string room_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomId() {
            roomId_ = getDefaultInstance().getRoomId();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string room_id = 2;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object roomName_ = "";

        /**
         * <code>string room_name = 13;</code>
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
         * <code>string room_name = 13;</code>
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
         * <code>string room_name = 13;</code>
         *
         * @param value The roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 13;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomName() {
            roomName_ = getDefaultInstance().getRoomName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 13;</code>
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
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int status_ = 0;

        /**
         * <code>.livekit.EgressStatus status = 3;</code>
         *
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }

        /**
         * <code>.livekit.EgressStatus status = 3;</code>
         *
         * @param value The enum numeric value on the wire for status to set.
         * @return This builder for chaining.
         */
        public Builder setStatusValue(int value) {
            status_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.EgressStatus status = 3;</code>
         *
         * @return The status.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EgressStatus getStatus() {
            im.turms.plugin.livekit.core.proto.egress.EgressStatus result =
                    im.turms.plugin.livekit.core.proto.egress.EgressStatus.forNumber(status_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.EgressStatus.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.EgressStatus status = 3;</code>
         *
         * @param value The status to set.
         * @return This builder for chaining.
         */
        public Builder setStatus(im.turms.plugin.livekit.core.proto.egress.EgressStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000008;
            status_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.EgressStatus status = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStatus() {
            bitField0_ &= ~0x00000008;
            status_ = 0;
            onChanged();
            return this;
        }

        private long startedAt_;

        /**
         * <code>int64 started_at = 10;</code>
         *
         * @return The startedAt.
         */
        @java.lang.Override
        public long getStartedAt() {
            return startedAt_;
        }

        /**
         * <code>int64 started_at = 10;</code>
         *
         * @param value The startedAt to set.
         * @return This builder for chaining.
         */
        public Builder setStartedAt(long value) {

            startedAt_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>int64 started_at = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartedAt() {
            bitField0_ &= ~0x00000010;
            startedAt_ = 0L;
            onChanged();
            return this;
        }

        private long endedAt_;

        /**
         * <code>int64 ended_at = 11;</code>
         *
         * @return The endedAt.
         */
        @java.lang.Override
        public long getEndedAt() {
            return endedAt_;
        }

        /**
         * <code>int64 ended_at = 11;</code>
         *
         * @param value The endedAt to set.
         * @return This builder for chaining.
         */
        public Builder setEndedAt(long value) {

            endedAt_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>int64 ended_at = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndedAt() {
            bitField0_ &= ~0x00000020;
            endedAt_ = 0L;
            onChanged();
            return this;
        }

        private long updatedAt_;

        /**
         * <code>int64 updated_at = 18;</code>
         *
         * @return The updatedAt.
         */
        @java.lang.Override
        public long getUpdatedAt() {
            return updatedAt_;
        }

        /**
         * <code>int64 updated_at = 18;</code>
         *
         * @param value The updatedAt to set.
         * @return This builder for chaining.
         */
        public Builder setUpdatedAt(long value) {

            updatedAt_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>int64 updated_at = 18;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUpdatedAt() {
            bitField0_ &= ~0x00000040;
            updatedAt_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object details_ = "";

        /**
         * <code>string details = 21;</code>
         *
         * @return The details.
         */
        public java.lang.String getDetails() {
            java.lang.Object ref = details_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                details_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string details = 21;</code>
         *
         * @return The bytes for details.
         */
        public com.google.protobuf.ByteString getDetailsBytes() {
            java.lang.Object ref = details_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                details_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string details = 21;</code>
         *
         * @param value The details to set.
         * @return This builder for chaining.
         */
        public Builder setDetails(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            details_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string details = 21;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDetails() {
            details_ = getDefaultInstance().getDetails();
            bitField0_ &= ~0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string details = 21;</code>
         *
         * @param value The bytes for details to set.
         * @return This builder for chaining.
         */
        public Builder setDetailsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            details_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        private java.lang.Object error_ = "";

        /**
         * <code>string error = 9;</code>
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
         * <code>string error = 9;</code>
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
         * <code>string error = 9;</code>
         *
         * @param value The error to set.
         * @return This builder for chaining.
         */
        public Builder setError(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            error_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string error = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearError() {
            error_ = getDefaultInstance().getError();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string error = 9;</code>
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
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder> roomCompositeBuilder_;

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         *
         * @return Whether the roomComposite field is set.
         */
        @java.lang.Override
        public boolean hasRoomComposite() {
            return requestCase_ == 4;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         *
         * @return The roomComposite.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoomComposite() {
            if (roomCompositeBuilder_ == null) {
                if (requestCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                        .getDefaultInstance();
            } else {
                if (requestCase_ == 4) {
                    return roomCompositeBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        public Builder setRoomComposite(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest value) {
            if (roomCompositeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                request_ = value;
                onChanged();
            } else {
                roomCompositeBuilder_.setMessage(value);
            }
            requestCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        public Builder setRoomComposite(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder builderForValue) {
            if (roomCompositeBuilder_ == null) {
                request_ = builderForValue.build();
                onChanged();
            } else {
                roomCompositeBuilder_.setMessage(builderForValue.build());
            }
            requestCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        public Builder mergeRoomComposite(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest value) {
            if (roomCompositeBuilder_ == null) {
                if (requestCase_ == 4
                        && request_ != im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                                .getDefaultInstance()) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    request_ = value;
                }
                onChanged();
            } else {
                if (requestCase_ == 4) {
                    roomCompositeBuilder_.mergeFrom(value);
                } else {
                    roomCompositeBuilder_.setMessage(value);
                }
            }
            requestCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        public Builder clearRoomComposite() {
            if (roomCompositeBuilder_ == null) {
                if (requestCase_ == 4) {
                    requestCase_ = 0;
                    request_ = null;
                    onChanged();
                }
            } else {
                if (requestCase_ == 4) {
                    requestCase_ = 0;
                    request_ = null;
                }
                roomCompositeBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder getRoomCompositeBuilder() {
            return getRoomCompositeFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomCompositeOrBuilder() {
            if ((requestCase_ == 4) && (roomCompositeBuilder_ != null)) {
                return roomCompositeBuilder_.getMessageOrBuilder();
            } else {
                if (requestCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder> getRoomCompositeFieldBuilder() {
            if (roomCompositeBuilder_ == null) {
                if (!(requestCase_ == 4)) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                            .getDefaultInstance();
                }
                roomCompositeBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) request_,
                        getParentForChildren(),
                        isClean());
                request_ = null;
            }
            requestCase_ = 4;
            onChanged();
            return roomCompositeBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.WebEgressRequest, im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder> webBuilder_;

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         *
         * @return Whether the web field is set.
         */
        @java.lang.Override
        public boolean hasWeb() {
            return requestCase_ == 14;
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         *
         * @return The web.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getWeb() {
            if (webBuilder_ == null) {
                if (requestCase_ == 14) {
                    return (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                        .getDefaultInstance();
            } else {
                if (requestCase_ == 14) {
                    return webBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        public Builder setWeb(im.turms.plugin.livekit.core.proto.egress.WebEgressRequest value) {
            if (webBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                request_ = value;
                onChanged();
            } else {
                webBuilder_.setMessage(value);
            }
            requestCase_ = 14;
            return this;
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        public Builder setWeb(
                im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder builderForValue) {
            if (webBuilder_ == null) {
                request_ = builderForValue.build();
                onChanged();
            } else {
                webBuilder_.setMessage(builderForValue.build());
            }
            requestCase_ = 14;
            return this;
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        public Builder mergeWeb(im.turms.plugin.livekit.core.proto.egress.WebEgressRequest value) {
            if (webBuilder_ == null) {
                if (requestCase_ == 14
                        && request_ != im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                                .getDefaultInstance()) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    request_ = value;
                }
                onChanged();
            } else {
                if (requestCase_ == 14) {
                    webBuilder_.mergeFrom(value);
                } else {
                    webBuilder_.setMessage(value);
                }
            }
            requestCase_ = 14;
            return this;
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        public Builder clearWeb() {
            if (webBuilder_ == null) {
                if (requestCase_ == 14) {
                    requestCase_ = 0;
                    request_ = null;
                    onChanged();
                }
            } else {
                if (requestCase_ == 14) {
                    requestCase_ = 0;
                    request_ = null;
                }
                webBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder getWebBuilder() {
            return getWebFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder getWebOrBuilder() {
            if ((requestCase_ == 14) && (webBuilder_ != null)) {
                return webBuilder_.getMessageOrBuilder();
            } else {
                if (requestCase_ == 14) {
                    return (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.WebEgressRequest web = 14;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.WebEgressRequest, im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder> getWebFieldBuilder() {
            if (webBuilder_ == null) {
                if (!(requestCase_ == 14)) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                            .getDefaultInstance();
                }
                webBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) request_,
                        getParentForChildren(),
                        isClean());
                request_ = null;
            }
            requestCase_ = 14;
            onChanged();
            return webBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest, im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder> participantBuilder_;

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         *
         * @return Whether the participant field is set.
         */
        @java.lang.Override
        public boolean hasParticipant() {
            return requestCase_ == 19;
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         *
         * @return The participant.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getParticipant() {
            if (participantBuilder_ == null) {
                if (requestCase_ == 19) {
                    return (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                        .getDefaultInstance();
            } else {
                if (requestCase_ == 19) {
                    return participantBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest value) {
            if (participantBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                request_ = value;
                onChanged();
            } else {
                participantBuilder_.setMessage(value);
            }
            requestCase_ = 19;
            return this;
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder builderForValue) {
            if (participantBuilder_ == null) {
                request_ = builderForValue.build();
                onChanged();
            } else {
                participantBuilder_.setMessage(builderForValue.build());
            }
            requestCase_ = 19;
            return this;
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        public Builder mergeParticipant(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest value) {
            if (participantBuilder_ == null) {
                if (requestCase_ == 19
                        && request_ != im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                                .getDefaultInstance()) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    request_ = value;
                }
                onChanged();
            } else {
                if (requestCase_ == 19) {
                    participantBuilder_.mergeFrom(value);
                } else {
                    participantBuilder_.setMessage(value);
                }
            }
            requestCase_ = 19;
            return this;
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        public Builder clearParticipant() {
            if (participantBuilder_ == null) {
                if (requestCase_ == 19) {
                    requestCase_ = 0;
                    request_ = null;
                    onChanged();
                }
            } else {
                if (requestCase_ == 19) {
                    requestCase_ = 0;
                    request_ = null;
                }
                participantBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder getParticipantBuilder() {
            return getParticipantFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder getParticipantOrBuilder() {
            if ((requestCase_ == 19) && (participantBuilder_ != null)) {
                return participantBuilder_.getMessageOrBuilder();
            } else {
                if (requestCase_ == 19) {
                    return (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest, im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder> getParticipantFieldBuilder() {
            if (participantBuilder_ == null) {
                if (!(requestCase_ == 19)) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                            .getDefaultInstance();
                }
                participantBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) request_,
                        getParentForChildren(),
                        isClean());
                request_ = null;
            }
            requestCase_ = 19;
            onChanged();
            return participantBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequestOrBuilder> trackCompositeBuilder_;

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         *
         * @return Whether the trackComposite field is set.
         */
        @java.lang.Override
        public boolean hasTrackComposite() {
            return requestCase_ == 5;
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         *
         * @return The trackComposite.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest getTrackComposite() {
            if (trackCompositeBuilder_ == null) {
                if (requestCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                        .getDefaultInstance();
            } else {
                if (requestCase_ == 5) {
                    return trackCompositeBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        public Builder setTrackComposite(
                im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest value) {
            if (trackCompositeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                request_ = value;
                onChanged();
            } else {
                trackCompositeBuilder_.setMessage(value);
            }
            requestCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        public Builder setTrackComposite(
                im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest.Builder builderForValue) {
            if (trackCompositeBuilder_ == null) {
                request_ = builderForValue.build();
                onChanged();
            } else {
                trackCompositeBuilder_.setMessage(builderForValue.build());
            }
            requestCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        public Builder mergeTrackComposite(
                im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest value) {
            if (trackCompositeBuilder_ == null) {
                if (requestCase_ == 5
                        && request_ != im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                                .getDefaultInstance()) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    request_ = value;
                }
                onChanged();
            } else {
                if (requestCase_ == 5) {
                    trackCompositeBuilder_.mergeFrom(value);
                } else {
                    trackCompositeBuilder_.setMessage(value);
                }
            }
            requestCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        public Builder clearTrackComposite() {
            if (trackCompositeBuilder_ == null) {
                if (requestCase_ == 5) {
                    requestCase_ = 0;
                    request_ = null;
                    onChanged();
                }
            } else {
                if (requestCase_ == 5) {
                    requestCase_ = 0;
                    request_ = null;
                }
                trackCompositeBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest.Builder getTrackCompositeBuilder() {
            return getTrackCompositeFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequestOrBuilder getTrackCompositeOrBuilder() {
            if ((requestCase_ == 5) && (trackCompositeBuilder_ != null)) {
                return trackCompositeBuilder_.getMessageOrBuilder();
            } else {
                if (requestCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequestOrBuilder> getTrackCompositeFieldBuilder() {
            if (trackCompositeBuilder_ == null) {
                if (!(requestCase_ == 5)) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest
                            .getDefaultInstance();
                }
                trackCompositeBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest) request_,
                        getParentForChildren(),
                        isClean());
                request_ = null;
            }
            requestCase_ = 5;
            onChanged();
            return trackCompositeBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest, im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder> trackBuilder_;

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         *
         * @return Whether the track field is set.
         */
        @java.lang.Override
        public boolean hasTrack() {
            return requestCase_ == 6;
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         *
         * @return The track.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getTrack() {
            if (trackBuilder_ == null) {
                if (requestCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                        .getDefaultInstance();
            } else {
                if (requestCase_ == 6) {
                    return trackBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        public Builder setTrack(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest value) {
            if (trackBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                request_ = value;
                onChanged();
            } else {
                trackBuilder_.setMessage(value);
            }
            requestCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        public Builder setTrack(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder builderForValue) {
            if (trackBuilder_ == null) {
                request_ = builderForValue.build();
                onChanged();
            } else {
                trackBuilder_.setMessage(builderForValue.build());
            }
            requestCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        public Builder mergeTrack(
                im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest value) {
            if (trackBuilder_ == null) {
                if (requestCase_ == 6
                        && request_ != im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                                .getDefaultInstance()) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    request_ = value;
                }
                onChanged();
            } else {
                if (requestCase_ == 6) {
                    trackBuilder_.mergeFrom(value);
                } else {
                    trackBuilder_.setMessage(value);
                }
            }
            requestCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        public Builder clearTrack() {
            if (trackBuilder_ == null) {
                if (requestCase_ == 6) {
                    requestCase_ = 0;
                    request_ = null;
                    onChanged();
                }
            } else {
                if (requestCase_ == 6) {
                    requestCase_ = 0;
                    request_ = null;
                }
                trackBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder getTrackBuilder() {
            return getTrackFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder getTrackOrBuilder() {
            if ((requestCase_ == 6) && (trackBuilder_ != null)) {
                return trackBuilder_.getMessageOrBuilder();
            } else {
                if (requestCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_;
                }
                return im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.TrackEgressRequest track = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest, im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder> getTrackFieldBuilder() {
            if (trackBuilder_ == null) {
                if (!(requestCase_ == 6)) {
                    request_ = im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest
                            .getDefaultInstance();
                }
                trackBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest) request_,
                        getParentForChildren(),
                        isClean());
                request_ = null;
            }
            requestCase_ = 6;
            onChanged();
            return trackBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfoList, im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder> streamBuilder_;

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
         * @return Whether the stream field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasStream() {
            return resultCase_ == 7;
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
         * @return The stream.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoList getStream() {
            if (streamBuilder_ == null) {
                if (resultCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                        .getDefaultInstance();
            } else {
                if (resultCase_ == 7) {
                    return streamBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setStream(im.turms.plugin.livekit.core.proto.egress.StreamInfoList value) {
            if (streamBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result_ = value;
                onChanged();
            } else {
                streamBuilder_.setMessage(value);
            }
            resultCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setStream(
                im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder builderForValue) {
            if (streamBuilder_ == null) {
                result_ = builderForValue.build();
                onChanged();
            } else {
                streamBuilder_.setMessage(builderForValue.build());
            }
            resultCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeStream(im.turms.plugin.livekit.core.proto.egress.StreamInfoList value) {
            if (streamBuilder_ == null) {
                if (resultCase_ == 7
                        && result_ != im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                                .getDefaultInstance()) {
                    result_ = im.turms.plugin.livekit.core.proto.egress.StreamInfoList.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    result_ = value;
                }
                onChanged();
            } else {
                if (resultCase_ == 7) {
                    streamBuilder_.mergeFrom(value);
                } else {
                    streamBuilder_.setMessage(value);
                }
            }
            resultCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearStream() {
            if (streamBuilder_ == null) {
                if (resultCase_ == 7) {
                    resultCase_ = 0;
                    result_ = null;
                    onChanged();
                }
            } else {
                if (resultCase_ == 7) {
                    resultCase_ = 0;
                    result_ = null;
                }
                streamBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder getStreamBuilder() {
            return getStreamFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder getStreamOrBuilder() {
            if ((resultCase_ == 7) && (streamBuilder_ != null)) {
                return streamBuilder_.getMessageOrBuilder();
            } else {
                if (resultCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfoList, im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder> getStreamFieldBuilder() {
            if (streamBuilder_ == null) {
                if (!(resultCase_ == 7)) {
                    result_ = im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                            .getDefaultInstance();
                }
                streamBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.StreamInfoList) result_,
                        getParentForChildren(),
                        isClean());
                result_ = null;
            }
            resultCase_ = 7;
            onChanged();
            return streamBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.FileInfo, im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder, im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> fileBuilder_;

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
         * @return Whether the file field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasFile() {
            return resultCase_ == 8;
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
         * @return The file.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.FileInfo getFile() {
            if (fileBuilder_ == null) {
                if (resultCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
            } else {
                if (resultCase_ == 8) {
                    return fileBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setFile(im.turms.plugin.livekit.core.proto.egress.FileInfo value) {
            if (fileBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result_ = value;
                onChanged();
            } else {
                fileBuilder_.setMessage(value);
            }
            resultCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setFile(
                im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder builderForValue) {
            if (fileBuilder_ == null) {
                result_ = builderForValue.build();
                onChanged();
            } else {
                fileBuilder_.setMessage(builderForValue.build());
            }
            resultCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeFile(im.turms.plugin.livekit.core.proto.egress.FileInfo value) {
            if (fileBuilder_ == null) {
                if (resultCase_ == 8
                        && result_ != im.turms.plugin.livekit.core.proto.egress.FileInfo
                                .getDefaultInstance()) {
                    result_ = im.turms.plugin.livekit.core.proto.egress.FileInfo
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    result_ = value;
                }
                onChanged();
            } else {
                if (resultCase_ == 8) {
                    fileBuilder_.mergeFrom(value);
                } else {
                    fileBuilder_.setMessage(value);
                }
            }
            resultCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearFile() {
            if (fileBuilder_ == null) {
                if (resultCase_ == 8) {
                    resultCase_ = 0;
                    result_ = null;
                    onChanged();
                }
            } else {
                if (resultCase_ == 8) {
                    resultCase_ = 0;
                    result_ = null;
                }
                fileBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder getFileBuilder() {
            return getFileFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileOrBuilder() {
            if ((resultCase_ == 8) && (fileBuilder_ != null)) {
                return fileBuilder_.getMessageOrBuilder();
            } else {
                if (resultCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.FileInfo, im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder, im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> getFileFieldBuilder() {
            if (fileBuilder_ == null) {
                if (!(resultCase_ == 8)) {
                    result_ =
                            im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
                }
                fileBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.FileInfo) result_,
                        getParentForChildren(),
                        isClean());
                result_ = null;
            }
            resultCase_ = 8;
            onChanged();
            return fileBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo, im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> segmentsBuilder_;

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
         * @return Whether the segments field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasSegments() {
            return resultCase_ == 12;
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         *
         * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
         * @return The segments.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegments() {
            if (segmentsBuilder_ == null) {
                if (resultCase_ == 12) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
            } else {
                if (resultCase_ == 12) {
                    return segmentsBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSegments(im.turms.plugin.livekit.core.proto.egress.SegmentsInfo value) {
            if (segmentsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result_ = value;
                onChanged();
            } else {
                segmentsBuilder_.setMessage(value);
            }
            resultCase_ = 12;
            return this;
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSegments(
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder builderForValue) {
            if (segmentsBuilder_ == null) {
                result_ = builderForValue.build();
                onChanged();
            } else {
                segmentsBuilder_.setMessage(builderForValue.build());
            }
            resultCase_ = 12;
            return this;
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeSegments(im.turms.plugin.livekit.core.proto.egress.SegmentsInfo value) {
            if (segmentsBuilder_ == null) {
                if (resultCase_ == 12
                        && result_ != im.turms.plugin.livekit.core.proto.egress.SegmentsInfo
                                .getDefaultInstance()) {
                    result_ = im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    result_ = value;
                }
                onChanged();
            } else {
                if (resultCase_ == 12) {
                    segmentsBuilder_.mergeFrom(value);
                } else {
                    segmentsBuilder_.setMessage(value);
                }
            }
            resultCase_ = 12;
            return this;
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearSegments() {
            if (segmentsBuilder_ == null) {
                if (resultCase_ == 12) {
                    resultCase_ = 0;
                    result_ = null;
                    onChanged();
                }
            } else {
                if (resultCase_ == 12) {
                    resultCase_ = 0;
                    result_ = null;
                }
                segmentsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder getSegmentsBuilder() {
            return getSegmentsFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentsOrBuilder() {
            if ((resultCase_ == 12) && (segmentsBuilder_ != null)) {
                return segmentsBuilder_.getMessageOrBuilder();
            } else {
                if (resultCase_ == 12) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo, im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> getSegmentsFieldBuilder() {
            if (segmentsBuilder_ == null) {
                if (!(resultCase_ == 12)) {
                    result_ = im.turms.plugin.livekit.core.proto.egress.SegmentsInfo
                            .getDefaultInstance();
                }
                segmentsBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.SegmentsInfo) result_,
                        getParentForChildren(),
                        isClean());
                result_ = null;
            }
            resultCase_ = 12;
            onChanged();
            return segmentsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> streamResults_ =
                java.util.Collections.emptyList();

        private void ensureStreamResultsIsMutable() {
            if ((bitField0_ & 0x00020000) == 0) {
                streamResults_ = new java.util.ArrayList<>(streamResults_);
                bitField0_ |= 0x00020000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfo, im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> streamResultsBuilder_;

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> getStreamResultsList() {
            if (streamResultsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(streamResults_);
            } else {
                return streamResultsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public int getStreamResultsCount() {
            if (streamResultsBuilder_ == null) {
                return streamResults_.size();
            } else {
                return streamResultsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo getStreamResults(int index) {
            if (streamResultsBuilder_ == null) {
                return streamResults_.get(index);
            } else {
                return streamResultsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder setStreamResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (streamResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamResultsIsMutable();
                streamResults_.set(index, value);
                onChanged();
            } else {
                streamResultsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder setStreamResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (streamResultsBuilder_ == null) {
                ensureStreamResultsIsMutable();
                streamResults_.set(index, builderForValue.build());
                onChanged();
            } else {
                streamResultsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder addStreamResults(
                im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (streamResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamResultsIsMutable();
                streamResults_.add(value);
                onChanged();
            } else {
                streamResultsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder addStreamResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (streamResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamResultsIsMutable();
                streamResults_.add(index, value);
                onChanged();
            } else {
                streamResultsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder addStreamResults(
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (streamResultsBuilder_ == null) {
                ensureStreamResultsIsMutable();
                streamResults_.add(builderForValue.build());
                onChanged();
            } else {
                streamResultsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder addStreamResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (streamResultsBuilder_ == null) {
                ensureStreamResultsIsMutable();
                streamResults_.add(index, builderForValue.build());
                onChanged();
            } else {
                streamResultsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder addAllStreamResults(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfo> values) {
            if (streamResultsBuilder_ == null) {
                ensureStreamResultsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, streamResults_);
                onChanged();
            } else {
                streamResultsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder clearStreamResults() {
            if (streamResultsBuilder_ == null) {
                streamResults_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00020000;
                onChanged();
            } else {
                streamResultsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public Builder removeStreamResults(int index) {
            if (streamResultsBuilder_ == null) {
                ensureStreamResultsIsMutable();
                streamResults_.remove(index);
                onChanged();
            } else {
                streamResultsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder getStreamResultsBuilder(
                int index) {
            return getStreamResultsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder getStreamResultsOrBuilder(
                int index) {
            if (streamResultsBuilder_ == null) {
                return streamResults_.get(index);
            } else {
                return streamResultsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getStreamResultsOrBuilderList() {
            if (streamResultsBuilder_ != null) {
                return streamResultsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(streamResults_);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder addStreamResultsBuilder() {
            return getStreamResultsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder addStreamResultsBuilder(
                int index) {
            return getStreamResultsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder> getStreamResultsBuilderList() {
            return getStreamResultsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfo, im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getStreamResultsFieldBuilder() {
            if (streamResultsBuilder_ == null) {
                streamResultsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        streamResults_,
                        ((bitField0_ & 0x00020000) != 0),
                        getParentForChildren(),
                        isClean());
                streamResults_ = null;
            }
            return streamResultsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo> fileResults_ =
                java.util.Collections.emptyList();

        private void ensureFileResultsIsMutable() {
            if ((bitField0_ & 0x00040000) == 0) {
                fileResults_ = new java.util.ArrayList<>(fileResults_);
                bitField0_ |= 0x00040000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.FileInfo, im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder, im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> fileResultsBuilder_;

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo> getFileResultsList() {
            if (fileResultsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(fileResults_);
            } else {
                return fileResultsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public int getFileResultsCount() {
            if (fileResultsBuilder_ == null) {
                return fileResults_.size();
            } else {
                return fileResultsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.FileInfo getFileResults(int index) {
            if (fileResultsBuilder_ == null) {
                return fileResults_.get(index);
            } else {
                return fileResultsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder setFileResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.FileInfo value) {
            if (fileResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileResultsIsMutable();
                fileResults_.set(index, value);
                onChanged();
            } else {
                fileResultsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder setFileResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder builderForValue) {
            if (fileResultsBuilder_ == null) {
                ensureFileResultsIsMutable();
                fileResults_.set(index, builderForValue.build());
                onChanged();
            } else {
                fileResultsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder addFileResults(im.turms.plugin.livekit.core.proto.egress.FileInfo value) {
            if (fileResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileResultsIsMutable();
                fileResults_.add(value);
                onChanged();
            } else {
                fileResultsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder addFileResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.FileInfo value) {
            if (fileResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileResultsIsMutable();
                fileResults_.add(index, value);
                onChanged();
            } else {
                fileResultsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder addFileResults(
                im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder builderForValue) {
            if (fileResultsBuilder_ == null) {
                ensureFileResultsIsMutable();
                fileResults_.add(builderForValue.build());
                onChanged();
            } else {
                fileResultsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder addFileResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder builderForValue) {
            if (fileResultsBuilder_ == null) {
                ensureFileResultsIsMutable();
                fileResults_.add(index, builderForValue.build());
                onChanged();
            } else {
                fileResultsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder addAllFileResults(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.FileInfo> values) {
            if (fileResultsBuilder_ == null) {
                ensureFileResultsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, fileResults_);
                onChanged();
            } else {
                fileResultsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder clearFileResults() {
            if (fileResultsBuilder_ == null) {
                fileResults_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00040000;
                onChanged();
            } else {
                fileResultsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public Builder removeFileResults(int index) {
            if (fileResultsBuilder_ == null) {
                ensureFileResultsIsMutable();
                fileResults_.remove(index);
                onChanged();
            } else {
                fileResultsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder getFileResultsBuilder(
                int index) {
            return getFileResultsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileResultsOrBuilder(
                int index) {
            if (fileResultsBuilder_ == null) {
                return fileResults_.get(index);
            } else {
                return fileResultsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> getFileResultsOrBuilderList() {
            if (fileResultsBuilder_ != null) {
                return fileResultsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(fileResults_);
            }
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder addFileResultsBuilder() {
            return getFileResultsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder addFileResultsBuilder(
                int index) {
            return getFileResultsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.FileInfo file_results = 16;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder> getFileResultsBuilderList() {
            return getFileResultsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.FileInfo, im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder, im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> getFileResultsFieldBuilder() {
            if (fileResultsBuilder_ == null) {
                fileResultsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        fileResults_,
                        ((bitField0_ & 0x00040000) != 0),
                        getParentForChildren(),
                        isClean());
                fileResults_ = null;
            }
            return fileResultsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> segmentResults_ =
                java.util.Collections.emptyList();

        private void ensureSegmentResultsIsMutable() {
            if ((bitField0_ & 0x00080000) == 0) {
                segmentResults_ = new java.util.ArrayList<>(segmentResults_);
                bitField0_ |= 0x00080000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo, im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> segmentResultsBuilder_;

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> getSegmentResultsList() {
            if (segmentResultsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(segmentResults_);
            } else {
                return segmentResultsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public int getSegmentResultsCount() {
            if (segmentResultsBuilder_ == null) {
                return segmentResults_.size();
            } else {
                return segmentResultsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegmentResults(int index) {
            if (segmentResultsBuilder_ == null) {
                return segmentResults_.get(index);
            } else {
                return segmentResultsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder setSegmentResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo value) {
            if (segmentResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentResultsIsMutable();
                segmentResults_.set(index, value);
                onChanged();
            } else {
                segmentResultsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder setSegmentResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder builderForValue) {
            if (segmentResultsBuilder_ == null) {
                ensureSegmentResultsIsMutable();
                segmentResults_.set(index, builderForValue.build());
                onChanged();
            } else {
                segmentResultsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder addSegmentResults(
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo value) {
            if (segmentResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentResultsIsMutable();
                segmentResults_.add(value);
                onChanged();
            } else {
                segmentResultsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder addSegmentResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo value) {
            if (segmentResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentResultsIsMutable();
                segmentResults_.add(index, value);
                onChanged();
            } else {
                segmentResultsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder addSegmentResults(
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder builderForValue) {
            if (segmentResultsBuilder_ == null) {
                ensureSegmentResultsIsMutable();
                segmentResults_.add(builderForValue.build());
                onChanged();
            } else {
                segmentResultsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder addSegmentResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder builderForValue) {
            if (segmentResultsBuilder_ == null) {
                ensureSegmentResultsIsMutable();
                segmentResults_.add(index, builderForValue.build());
                onChanged();
            } else {
                segmentResultsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder addAllSegmentResults(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> values) {
            if (segmentResultsBuilder_ == null) {
                ensureSegmentResultsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, segmentResults_);
                onChanged();
            } else {
                segmentResultsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder clearSegmentResults() {
            if (segmentResultsBuilder_ == null) {
                segmentResults_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00080000;
                onChanged();
            } else {
                segmentResultsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public Builder removeSegmentResults(int index) {
            if (segmentResultsBuilder_ == null) {
                ensureSegmentResultsIsMutable();
                segmentResults_.remove(index);
                onChanged();
            } else {
                segmentResultsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder getSegmentResultsBuilder(
                int index) {
            return getSegmentResultsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentResultsOrBuilder(
                int index) {
            if (segmentResultsBuilder_ == null) {
                return segmentResults_.get(index);
            } else {
                return segmentResultsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> getSegmentResultsOrBuilderList() {
            if (segmentResultsBuilder_ != null) {
                return segmentResultsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(segmentResults_);
            }
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder addSegmentResultsBuilder() {
            return getSegmentResultsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder addSegmentResultsBuilder(
                int index) {
            return getSegmentResultsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder> getSegmentResultsBuilderList() {
            return getSegmentResultsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo, im.turms.plugin.livekit.core.proto.egress.SegmentsInfo.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> getSegmentResultsFieldBuilder() {
            if (segmentResultsBuilder_ == null) {
                segmentResultsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        segmentResults_,
                        ((bitField0_ & 0x00080000) != 0),
                        getParentForChildren(),
                        isClean());
                segmentResults_ = null;
            }
            return segmentResultsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo> imageResults_ =
                java.util.Collections.emptyList();

        private void ensureImageResultsIsMutable() {
            if ((bitField0_ & 0x00100000) == 0) {
                imageResults_ = new java.util.ArrayList<>(imageResults_);
                bitField0_ |= 0x00100000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImagesInfo, im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder, im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder> imageResultsBuilder_;

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo> getImageResultsList() {
            if (imageResultsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(imageResults_);
            } else {
                return imageResultsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public int getImageResultsCount() {
            if (imageResultsBuilder_ == null) {
                return imageResults_.size();
            } else {
                return imageResultsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImagesInfo getImageResults(int index) {
            if (imageResultsBuilder_ == null) {
                return imageResults_.get(index);
            } else {
                return imageResultsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder setImageResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImagesInfo value) {
            if (imageResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageResultsIsMutable();
                imageResults_.set(index, value);
                onChanged();
            } else {
                imageResultsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder setImageResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder builderForValue) {
            if (imageResultsBuilder_ == null) {
                ensureImageResultsIsMutable();
                imageResults_.set(index, builderForValue.build());
                onChanged();
            } else {
                imageResultsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder addImageResults(im.turms.plugin.livekit.core.proto.egress.ImagesInfo value) {
            if (imageResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageResultsIsMutable();
                imageResults_.add(value);
                onChanged();
            } else {
                imageResultsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder addImageResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImagesInfo value) {
            if (imageResultsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageResultsIsMutable();
                imageResults_.add(index, value);
                onChanged();
            } else {
                imageResultsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder addImageResults(
                im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder builderForValue) {
            if (imageResultsBuilder_ == null) {
                ensureImageResultsIsMutable();
                imageResults_.add(builderForValue.build());
                onChanged();
            } else {
                imageResultsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder addImageResults(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder builderForValue) {
            if (imageResultsBuilder_ == null) {
                ensureImageResultsIsMutable();
                imageResults_.add(index, builderForValue.build());
                onChanged();
            } else {
                imageResultsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder addAllImageResults(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.ImagesInfo> values) {
            if (imageResultsBuilder_ == null) {
                ensureImageResultsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, imageResults_);
                onChanged();
            } else {
                imageResultsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder clearImageResults() {
            if (imageResultsBuilder_ == null) {
                imageResults_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00100000;
                onChanged();
            } else {
                imageResultsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public Builder removeImageResults(int index) {
            if (imageResultsBuilder_ == null) {
                ensureImageResultsIsMutable();
                imageResults_.remove(index);
                onChanged();
            } else {
                imageResultsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder getImageResultsBuilder(
                int index) {
            return getImageResultsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder getImageResultsOrBuilder(
                int index) {
            if (imageResultsBuilder_ == null) {
                return imageResults_.get(index);
            } else {
                return imageResultsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder> getImageResultsOrBuilderList() {
            if (imageResultsBuilder_ != null) {
                return imageResultsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(imageResults_);
            }
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder addImageResultsBuilder() {
            return getImageResultsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.ImagesInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder addImageResultsBuilder(
                int index) {
            return getImageResultsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.ImagesInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder> getImageResultsBuilderList() {
            return getImageResultsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImagesInfo, im.turms.plugin.livekit.core.proto.egress.ImagesInfo.Builder, im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder> getImageResultsFieldBuilder() {
            if (imageResultsBuilder_ == null) {
                imageResultsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        imageResults_,
                        ((bitField0_ & 0x00100000) != 0),
                        getParentForChildren(),
                        isClean());
                imageResults_ = null;
            }
            return imageResultsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.EgressInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.EgressInfo)
    private static final im.turms.plugin.livekit.core.proto.egress.EgressInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.EgressInfo();
    }

    public static im.turms.plugin.livekit.core.proto.egress.EgressInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<EgressInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public EgressInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<EgressInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<EgressInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EgressInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}