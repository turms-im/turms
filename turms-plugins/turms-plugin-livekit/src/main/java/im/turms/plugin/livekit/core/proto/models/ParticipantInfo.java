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
 * Protobuf type {@code livekit.ParticipantInfo}
 */
public final class ParticipantInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ParticipantInfo)
        ParticipantInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ParticipantInfo.class.getName());
    }

    // Use ParticipantInfo.newBuilder() to construct.
    private ParticipantInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ParticipantInfo() {
        sid_ = "";
        identity_ = "";
        state_ = 0;
        tracks_ = java.util.Collections.emptyList();
        metadata_ = "";
        name_ = "";
        region_ = "";
        kind_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ParticipantInfo.class,
                        im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.ParticipantInfo.State}
     */
    public enum State implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <pre>
         * websocket' connected, but not offered yet
         * </pre>
         *
         * <code>JOINING = 0;</code>
         */
        JOINING(0),
        /**
         * <pre>
         * server received client offer
         * </pre>
         *
         * <code>JOINED = 1;</code>
         */
        JOINED(1),
        /**
         * <pre>
         * ICE connectivity established
         * </pre>
         *
         * <code>ACTIVE = 2;</code>
         */
        ACTIVE(2),
        /**
         * <pre>
         * WS disconnected
         * </pre>
         *
         * <code>DISCONNECTED = 3;</code>
         */
        DISCONNECTED(3),
        UNRECOGNIZED(-1),;

        static {
            com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                    com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                    /* major= */ 4,
                    /* minor= */ 26,
                    /* patch= */ 1,
                    /* suffix= */ "",
                    State.class.getName());
        }
        /**
         * <pre>
         * websocket' connected, but not offered yet
         * </pre>
         *
         * <code>JOINING = 0;</code>
         */
        public static final int JOINING_VALUE = 0;
        /**
         * <pre>
         * server received client offer
         * </pre>
         *
         * <code>JOINED = 1;</code>
         */
        public static final int JOINED_VALUE = 1;
        /**
         * <pre>
         * ICE connectivity established
         * </pre>
         *
         * <code>ACTIVE = 2;</code>
         */
        public static final int ACTIVE_VALUE = 2;
        /**
         * <pre>
         * WS disconnected
         * </pre>
         *
         * <code>DISCONNECTED = 3;</code>
         */
        public static final int DISCONNECTED_VALUE = 3;

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
        public static State valueOf(int value) {
            return forNumber(value);
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         */
        public static State forNumber(int value) {
            return switch (value) {
                case 0 -> JOINING;
                case 1 -> JOINED;
                case 2 -> ACTIVE;
                case 3 -> DISCONNECTED;
                default -> null;
            };
        }

        public static com.google.protobuf.Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private static final com.google.protobuf.Internal.EnumLiteMap<State> internalValueMap =
                State::forNumber;

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
            return im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDescriptor()
                    .getEnumTypes()
                    .get(0);
        }

        private static final State[] VALUES = values();

        public static State valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

        State(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:livekit.ParticipantInfo.State)
    }

    /**
     * Protobuf enum {@code livekit.ParticipantInfo.Kind}
     */
    public enum Kind implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <pre>
         * standard participants, e.g. web clients
         * </pre>
         *
         * <code>STANDARD = 0;</code>
         */
        STANDARD(0),
        /**
         * <pre>
         * only ingests streams
         * </pre>
         *
         * <code>INGRESS = 1;</code>
         */
        INGRESS(1),
        /**
         * <pre>
         * only consumes streams
         * </pre>
         *
         * <code>EGRESS = 2;</code>
         */
        EGRESS(2),
        /**
         * <pre>
         * SIP participants
         * </pre>
         *
         * <code>SIP = 3;</code>
         */
        SIP(3),
        /**
         * <pre>
         * LiveKit agents
         * </pre>
         *
         * <code>AGENT = 4;</code>
         */
        AGENT(4),
        UNRECOGNIZED(-1),;

        static {
            com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                    com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                    /* major= */ 4,
                    /* minor= */ 26,
                    /* patch= */ 1,
                    /* suffix= */ "",
                    Kind.class.getName());
        }
        /**
         * <pre>
         * standard participants, e.g. web clients
         * </pre>
         *
         * <code>STANDARD = 0;</code>
         */
        public static final int STANDARD_VALUE = 0;
        /**
         * <pre>
         * only ingests streams
         * </pre>
         *
         * <code>INGRESS = 1;</code>
         */
        public static final int INGRESS_VALUE = 1;
        /**
         * <pre>
         * only consumes streams
         * </pre>
         *
         * <code>EGRESS = 2;</code>
         */
        public static final int EGRESS_VALUE = 2;
        /**
         * <pre>
         * SIP participants
         * </pre>
         *
         * <code>SIP = 3;</code>
         */
        public static final int SIP_VALUE = 3;
        /**
         * <pre>
         * LiveKit agents
         * </pre>
         *
         * <code>AGENT = 4;</code>
         */
        public static final int AGENT_VALUE = 4;

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
        public static Kind valueOf(int value) {
            return forNumber(value);
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         */
        public static Kind forNumber(int value) {
            return switch (value) {
                case 0 -> STANDARD;
                case 1 -> INGRESS;
                case 2 -> EGRESS;
                case 3 -> SIP;
                case 4 -> AGENT;
                default -> null;
            };
        }

        public static com.google.protobuf.Internal.EnumLiteMap<Kind> internalGetValueMap() {
            return internalValueMap;
        }

        private static final com.google.protobuf.Internal.EnumLiteMap<Kind> internalValueMap =
                Kind::forNumber;

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
            return im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDescriptor()
                    .getEnumTypes()
                    .get(1);
        }

        private static final Kind[] VALUES = values();

        public static Kind valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

        Kind(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:livekit.ParticipantInfo.Kind)
    }

    private int bitField0_;
    public static final int SID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object sid_ = "";

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    @java.lang.Override
    public java.lang.String getSid() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            sid_ = s;
            return s;
        }
    }

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSidBytes() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            sid_ = b;
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

    public static final int STATE_FIELD_NUMBER = 3;
    private int state_ = 0;

    /**
     * <code>.livekit.ParticipantInfo.State state = 3;</code>
     *
     * @return The enum numeric value on the wire for state.
     */
    @java.lang.Override
    public int getStateValue() {
        return state_;
    }

    /**
     * <code>.livekit.ParticipantInfo.State state = 3;</code>
     *
     * @return The state.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State getState() {
        im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State result =
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State.forNumber(state_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State.UNRECOGNIZED
                : result;
    }

    public static final int TRACKS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> tracks_;

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList() {
        return tracks_;
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList() {
        return tracks_;
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    @java.lang.Override
    public int getTracksCount() {
        return tracks_.size();
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index) {
        return tracks_.get(index);
    }

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTracksOrBuilder(
            int index) {
        return tracks_.get(index);
    }

    public static final int METADATA_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object metadata_ = "";

    /**
     * <code>string metadata = 5;</code>
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
     * <code>string metadata = 5;</code>
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

    public static final int JOINED_AT_FIELD_NUMBER = 6;
    private long joinedAt_ = 0L;

    /**
     * <pre>
     * timestamp when participant joined room, in seconds
     * </pre>
     *
     * <code>int64 joined_at = 6;</code>
     *
     * @return The joinedAt.
     */
    @java.lang.Override
    public long getJoinedAt() {
        return joinedAt_;
    }

    public static final int NAME_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>string name = 9;</code>
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
     * <code>string name = 9;</code>
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

    public static final int VERSION_FIELD_NUMBER = 10;
    private int version_ = 0;

    /**
     * <code>uint32 version = 10;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public int getVersion() {
        return version_;
    }

    public static final int PERMISSION_FIELD_NUMBER = 11;
    private im.turms.plugin.livekit.core.proto.models.ParticipantPermission permission_;

    /**
     * <code>.livekit.ParticipantPermission permission = 11;</code>
     *
     * @return Whether the permission field is set.
     */
    @java.lang.Override
    public boolean hasPermission() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.ParticipantPermission permission = 11;</code>
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
     * <code>.livekit.ParticipantPermission permission = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder getPermissionOrBuilder() {
        return permission_ == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                        .getDefaultInstance()
                : permission_;
    }

    public static final int REGION_FIELD_NUMBER = 12;
    @SuppressWarnings("serial")
    private volatile java.lang.Object region_ = "";

    /**
     * <code>string region = 12;</code>
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
     * <code>string region = 12;</code>
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

    public static final int IS_PUBLISHER_FIELD_NUMBER = 13;
    private boolean isPublisher_ = false;

    /**
     * <pre>
     * indicates the participant has an active publisher connection
     * and can publish to the server
     * </pre>
     *
     * <code>bool is_publisher = 13;</code>
     *
     * @return The isPublisher.
     */
    @java.lang.Override
    public boolean getIsPublisher() {
        return isPublisher_;
    }

    public static final int KIND_FIELD_NUMBER = 14;
    private int kind_ = 0;

    /**
     * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
     *
     * @return The enum numeric value on the wire for kind.
     */
    @java.lang.Override
    public int getKindValue() {
        return kind_;
    }

    /**
     * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
     *
     * @return The kind.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind getKind() {
        im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind result =
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.forNumber(kind_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.UNRECOGNIZED
                : result;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, sid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, identity_);
        }
        if (state_ != im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State.JOINING
                .getNumber()) {
            output.writeEnum(3, state_);
        }
        for (TrackInfo trackInfo : tracks_) {
            output.writeMessage(4, trackInfo);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, metadata_);
        }
        if (joinedAt_ != 0L) {
            output.writeInt64(6, joinedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, name_);
        }
        if (version_ != 0) {
            output.writeUInt32(10, version_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(11, getPermission());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 12, region_);
        }
        if (isPublisher_) {
            output.writeBool(13, isPublisher_);
        }
        if (kind_ != im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.STANDARD
                .getNumber()) {
            output.writeEnum(14, kind_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, sid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, identity_);
        }
        if (state_ != im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State.JOINING
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(3, state_);
        }
        for (TrackInfo trackInfo : tracks_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, trackInfo);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, metadata_);
        }
        if (joinedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, joinedAt_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, name_);
        }
        if (version_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(10, version_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(11, getPermission());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(12, region_);
        }
        if (isPublisher_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(13, isPublisher_);
        }
        if (kind_ != im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.STANDARD
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(14, kind_);
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
        if (!(obj instanceof ParticipantInfo other)) {
            return super.equals(obj);
        }

        if (!getSid().equals(other.getSid())) {
            return false;
        }
        if (!getIdentity().equals(other.getIdentity())) {
            return false;
        }
        if (state_ != other.state_) {
            return false;
        }
        if (!getTracksList().equals(other.getTracksList())) {
            return false;
        }
        if (!getMetadata().equals(other.getMetadata())) {
            return false;
        }
        if (getJoinedAt() != other.getJoinedAt()) {
            return false;
        }
        if (!getName().equals(other.getName())) {
            return false;
        }
        if (getVersion() != other.getVersion()) {
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
        if (!getRegion().equals(other.getRegion())) {
            return false;
        }
        if (getIsPublisher() != other.getIsPublisher()) {
            return false;
        }
        if (kind_ != other.kind_) {
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
        hash = (37 * hash) + SID_FIELD_NUMBER;
        hash = (53 * hash) + getSid().hashCode();
        hash = (37 * hash) + IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getIdentity().hashCode();
        hash = (37 * hash) + STATE_FIELD_NUMBER;
        hash = (53 * hash) + state_;
        if (getTracksCount() > 0) {
            hash = (37 * hash) + TRACKS_FIELD_NUMBER;
            hash = (53 * hash) + getTracksList().hashCode();
        }
        hash = (37 * hash) + METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getMetadata().hashCode();
        hash = (37 * hash) + JOINED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getJoinedAt());
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getVersion();
        if (hasPermission()) {
            hash = (37 * hash) + PERMISSION_FIELD_NUMBER;
            hash = (53 * hash) + getPermission().hashCode();
        }
        hash = (37 * hash) + REGION_FIELD_NUMBER;
        hash = (53 * hash) + getRegion().hashCode();
        hash = (37 * hash) + IS_PUBLISHER_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getIsPublisher());
        hash = (37 * hash) + KIND_FIELD_NUMBER;
        hash = (53 * hash) + kind_;
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ParticipantInfo prototype) {
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
     * Protobuf type {@code livekit.ParticipantInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ParticipantInfo)
            im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ParticipantInfo.class,
                            im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.ParticipantInfo.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getTracksFieldBuilder();
                getPermissionFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            sid_ = "";
            identity_ = "";
            state_ = 0;
            if (tracksBuilder_ == null) {
                tracks_ = java.util.Collections.emptyList();
            } else {
                tracks_ = null;
                tracksBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            metadata_ = "";
            joinedAt_ = 0L;
            name_ = "";
            version_ = 0;
            permission_ = null;
            if (permissionBuilder_ != null) {
                permissionBuilder_.dispose();
                permissionBuilder_ = null;
            }
            region_ = "";
            isPublisher_ = false;
            kind_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo build() {
            im.turms.plugin.livekit.core.proto.models.ParticipantInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ParticipantInfo result =
                    new im.turms.plugin.livekit.core.proto.models.ParticipantInfo(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo result) {
            if (tracksBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    tracks_ = java.util.Collections.unmodifiableList(tracks_);
                    bitField0_ &= ~0x00000008;
                }
                result.tracks_ = tracks_;
            } else {
                result.tracks_ = tracksBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.sid_ = sid_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.identity_ = identity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.state_ = state_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.metadata_ = metadata_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.joinedAt_ = joinedAt_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.version_ = version_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.permission_ = permissionBuilder_ == null
                        ? permission_
                        : permissionBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.region_ = region_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.isPublisher_ = isPublisher_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.kind_ = kind_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ParticipantInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.ParticipantInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.ParticipantInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ParticipantInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getSid()
                    .isEmpty()) {
                sid_ = other.sid_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getIdentity()
                    .isEmpty()) {
                identity_ = other.identity_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.state_ != 0) {
                setStateValue(other.getStateValue());
            }
            if (tracksBuilder_ == null) {
                if (!other.tracks_.isEmpty()) {
                    if (tracks_.isEmpty()) {
                        tracks_ = other.tracks_;
                        bitField0_ &= ~0x00000008;
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
                        bitField0_ &= ~0x00000008;
                        tracksBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getTracksFieldBuilder()
                                : null;
                    } else {
                        tracksBuilder_.addAllMessages(other.tracks_);
                    }
                }
            }
            if (!other.getMetadata()
                    .isEmpty()) {
                metadata_ = other.metadata_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (other.getJoinedAt() != 0L) {
                setJoinedAt(other.getJoinedAt());
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000040;
                onChanged();
            }
            if (other.getVersion() != 0) {
                setVersion(other.getVersion());
            }
            if (other.hasPermission()) {
                mergePermission(other.getPermission());
            }
            if (!other.getRegion()
                    .isEmpty()) {
                region_ = other.region_;
                bitField0_ |= 0x00000200;
                onChanged();
            }
            if (other.getIsPublisher()) {
                setIsPublisher(other.getIsPublisher());
            }
            if (other.kind_ != 0) {
                setKindValue(other.getKindValue());
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
                            sid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            identity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            state_ = input.readEnum();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            TrackInfo m = input.readMessage(TrackInfo.parser(), extensionRegistry);
                            if (tracksBuilder_ == null) {
                                ensureTracksIsMutable();
                                tracks_.add(m);
                            } else {
                                tracksBuilder_.addMessage(m);
                            }
                        } // case 34
                        case 42 -> {
                            metadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 48 -> {
                            joinedAt_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 74 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000040;
                        } // case 74
                        case 80 -> {
                            version_ = input.readUInt32();
                            bitField0_ |= 0x00000080;
                        } // case 80
                        case 90 -> {
                            input.readMessage(getPermissionFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000100;
                        } // case 90
                        case 98 -> {
                            region_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000200;
                        } // case 98
                        case 104 -> {
                            isPublisher_ = input.readBool();
                            bitField0_ |= 0x00000400;
                        } // case 104
                        case 112 -> {
                            kind_ = input.readEnum();
                            bitField0_ |= 0x00000800;
                        } // case 112
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

        private java.lang.Object sid_ = "";

        /**
         * <code>string sid = 1;</code>
         *
         * @return The sid.
         */
        public java.lang.String getSid() {
            java.lang.Object ref = sid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                sid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return The bytes for sid.
         */
        public com.google.protobuf.ByteString getSidBytes() {
            java.lang.Object ref = sid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                sid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The sid to set.
         * @return This builder for chaining.
         */
        public Builder setSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSid() {
            sid_ = getDefaultInstance().getSid();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The bytes for sid to set.
         * @return This builder for chaining.
         */
        public Builder setSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            sid_ = value;
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

        private int state_ = 0;

        /**
         * <code>.livekit.ParticipantInfo.State state = 3;</code>
         *
         * @return The enum numeric value on the wire for state.
         */
        @java.lang.Override
        public int getStateValue() {
            return state_;
        }

        /**
         * <code>.livekit.ParticipantInfo.State state = 3;</code>
         *
         * @param value The enum numeric value on the wire for state to set.
         * @return This builder for chaining.
         */
        public Builder setStateValue(int value) {
            state_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantInfo.State state = 3;</code>
         *
         * @return The state.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State getState() {
            im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State result =
                    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State
                            .forNumber(state_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ParticipantInfo.State state = 3;</code>
         *
         * @param value The state to set.
         * @return This builder for chaining.
         */
        public Builder setState(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000004;
            state_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantInfo.State state = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearState() {
            bitField0_ &= ~0x00000004;
            state_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> tracks_ =
                java.util.Collections.emptyList();

        private void ensureTracksIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                tracks_ = new java.util.ArrayList<>(tracks_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> tracksBuilder_;

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList() {
            if (tracksBuilder_ == null) {
                return java.util.Collections.unmodifiableList(tracks_);
            } else {
                return tracksBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public int getTracksCount() {
            if (tracksBuilder_ == null) {
                return tracks_.size();
            } else {
                return tracksBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index) {
            if (tracksBuilder_ == null) {
                return tracks_.get(index);
            } else {
                return tracksBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public Builder clearTracks() {
            if (tracksBuilder_ == null) {
                tracks_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                tracksBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder getTracksBuilder(
                int index) {
            return getTracksFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
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
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList() {
            if (tracksBuilder_ != null) {
                return tracksBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(tracks_);
            }
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder addTracksBuilder() {
            return getTracksFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder addTracksBuilder(
                int index) {
            return getTracksFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.TrackInfo tracks = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder> getTracksBuilderList() {
            return getTracksFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksFieldBuilder() {
            if (tracksBuilder_ == null) {
                tracksBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        tracks_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                tracks_ = null;
            }
            return tracksBuilder_;
        }

        private java.lang.Object metadata_ = "";

        /**
         * <code>string metadata = 5;</code>
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
         * <code>string metadata = 5;</code>
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
         * <code>string metadata = 5;</code>
         *
         * @param value The metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            metadata_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string metadata = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMetadata() {
            metadata_ = getDefaultInstance().getMetadata();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string metadata = 5;</code>
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
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private long joinedAt_;

        /**
         * <pre>
         * timestamp when participant joined room, in seconds
         * </pre>
         *
         * <code>int64 joined_at = 6;</code>
         *
         * @return The joinedAt.
         */
        @java.lang.Override
        public long getJoinedAt() {
            return joinedAt_;
        }

        /**
         * <pre>
         * timestamp when participant joined room, in seconds
         * </pre>
         *
         * <code>int64 joined_at = 6;</code>
         *
         * @param value The joinedAt to set.
         * @return This builder for chaining.
         */
        public Builder setJoinedAt(long value) {

            joinedAt_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * timestamp when participant joined room, in seconds
         * </pre>
         *
         * <code>int64 joined_at = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJoinedAt() {
            bitField0_ &= ~0x00000020;
            joinedAt_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>string name = 9;</code>
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
         * <code>string name = 9;</code>
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
         * <code>string name = 9;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 9;</code>
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
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        private int version_;

        /**
         * <code>uint32 version = 10;</code>
         *
         * @return The version.
         */
        @java.lang.Override
        public int getVersion() {
            return version_;
        }

        /**
         * <code>uint32 version = 10;</code>
         *
         * @param value The version to set.
         * @return This builder for chaining.
         */
        public Builder setVersion(int value) {

            version_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 version = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVersion() {
            bitField0_ &= ~0x00000080;
            version_ = 0;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.ParticipantPermission permission_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantPermission, im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder> permissionBuilder_;

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
         *
         * @return Whether the permission field is set.
         */
        public boolean hasPermission() {
            return ((bitField0_ & 0x00000100) != 0);
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
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
         * <code>.livekit.ParticipantPermission permission = 11;</code>
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
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
         */
        public Builder setPermission(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder builderForValue) {
            if (permissionBuilder_ == null) {
                permission_ = builderForValue.build();
            } else {
                permissionBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
         */
        public Builder mergePermission(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission value) {
            if (permissionBuilder_ == null) {
                if (((bitField0_ & 0x00000100) != 0)
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
                bitField0_ |= 0x00000100;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
         */
        public Builder clearPermission() {
            bitField0_ &= ~0x00000100;
            permission_ = null;
            if (permissionBuilder_ != null) {
                permissionBuilder_.dispose();
                permissionBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder getPermissionBuilder() {
            bitField0_ |= 0x00000100;
            onChanged();
            return getPermissionFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.ParticipantPermission permission = 11;</code>
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
         * <code>.livekit.ParticipantPermission permission = 11;</code>
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

        private java.lang.Object region_ = "";

        /**
         * <code>string region = 12;</code>
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
         * <code>string region = 12;</code>
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
         * <code>string region = 12;</code>
         *
         * @param value The region to set.
         * @return This builder for chaining.
         */
        public Builder setRegion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            region_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegion() {
            region_ = getDefaultInstance().getRegion();
            bitField0_ &= ~0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 12;</code>
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
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        private boolean isPublisher_;

        /**
         * <pre>
         * indicates the participant has an active publisher connection
         * and can publish to the server
         * </pre>
         *
         * <code>bool is_publisher = 13;</code>
         *
         * @return The isPublisher.
         */
        @java.lang.Override
        public boolean getIsPublisher() {
            return isPublisher_;
        }

        /**
         * <pre>
         * indicates the participant has an active publisher connection
         * and can publish to the server
         * </pre>
         *
         * <code>bool is_publisher = 13;</code>
         *
         * @param value The isPublisher to set.
         * @return This builder for chaining.
         */
        public Builder setIsPublisher(boolean value) {

            isPublisher_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * indicates the participant has an active publisher connection
         * and can publish to the server
         * </pre>
         *
         * <code>bool is_publisher = 13;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIsPublisher() {
            bitField0_ &= ~0x00000400;
            isPublisher_ = false;
            onChanged();
            return this;
        }

        private int kind_ = 0;

        /**
         * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
         *
         * @return The enum numeric value on the wire for kind.
         */
        @java.lang.Override
        public int getKindValue() {
            return kind_;
        }

        /**
         * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
         *
         * @param value The enum numeric value on the wire for kind to set.
         * @return This builder for chaining.
         */
        public Builder setKindValue(int value) {
            kind_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
         *
         * @return The kind.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind getKind() {
            im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind result =
                    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.forNumber(kind_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
         *
         * @param value The kind to set.
         * @return This builder for chaining.
         */
        public Builder setKind(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000800;
            kind_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKind() {
            bitField0_ &= ~0x00000800;
            kind_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ParticipantInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.ParticipantInfo)
    private static final im.turms.plugin.livekit.core.proto.models.ParticipantInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ParticipantInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ParticipantInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ParticipantInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<ParticipantInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ParticipantInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}