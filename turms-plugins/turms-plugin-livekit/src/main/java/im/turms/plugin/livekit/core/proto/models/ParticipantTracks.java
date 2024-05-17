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
 * Protobuf type {@code livekit.ParticipantTracks}
 */
public final class ParticipantTracks extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ParticipantTracks)
        ParticipantTracksOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ParticipantTracks.class.getName());
    }

    // Use ParticipantTracks.newBuilder() to construct.
    private ParticipantTracks(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ParticipantTracks() {
        participantSid_ = "";
        trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantTracks_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantTracks_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ParticipantTracks.class,
                        im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder.class);
    }

    public static final int PARTICIPANT_SID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantSid_ = "";

    /**
     * <pre>
     * participant ID of participant to whom the tracks belong
     * </pre>
     *
     * <code>string participant_sid = 1;</code>
     *
     * @return The participantSid.
     */
    @java.lang.Override
    public java.lang.String getParticipantSid() {
        java.lang.Object ref = participantSid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            participantSid_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * participant ID of participant to whom the tracks belong
     * </pre>
     *
     * <code>string participant_sid = 1;</code>
     *
     * @return The bytes for participantSid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getParticipantSidBytes() {
        java.lang.Object ref = participantSid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            participantSid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int TRACK_SIDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList trackSids_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @return A list containing the trackSids.
     */
    public com.google.protobuf.ProtocolStringList getTrackSidsList() {
        return trackSids_;
    }

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @return The count of trackSids.
     */
    public int getTrackSidsCount() {
        return trackSids_.size();
    }

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The trackSids at the given index.
     */
    public java.lang.String getTrackSids(int index) {
        return trackSids_.get(index);
    }

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the trackSids at the given index.
     */
    public com.google.protobuf.ByteString getTrackSidsBytes(int index) {
        return trackSids_.getByteString(index);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantSid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, participantSid_);
        }
        for (int i = 0; i < trackSids_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, trackSids_.getRaw(i));
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantSid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, participantSid_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < trackSids_.size(); i++) {
                dataSize += computeStringSizeNoTag(trackSids_.getRaw(i));
            }
            size += dataSize;
            size += getTrackSidsList().size();
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
        if (!(obj instanceof ParticipantTracks other)) {
            return super.equals(obj);
        }

        if (!getParticipantSid().equals(other.getParticipantSid())) {
            return false;
        }
        if (!getTrackSidsList().equals(other.getTrackSidsList())) {
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
        hash = (37 * hash) + PARTICIPANT_SID_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantSid().hashCode();
        if (getTrackSidsCount() > 0) {
            hash = (37 * hash) + TRACK_SIDS_FIELD_NUMBER;
            hash = (53 * hash) + getTrackSidsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ParticipantTracks prototype) {
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
     * Protobuf type {@code livekit.ParticipantTracks}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ParticipantTracks)
            im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantTracks_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantTracks_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ParticipantTracks.class,
                            im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.ParticipantTracks.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            participantSid_ = "";
            trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantTracks_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ParticipantTracks.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks build() {
            im.turms.plugin.livekit.core.proto.models.ParticipantTracks result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ParticipantTracks result =
                    new im.turms.plugin.livekit.core.proto.models.ParticipantTracks(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.participantSid_ = participantSid_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                trackSids_.makeImmutable();
                result.trackSids_ = trackSids_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ParticipantTracks) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.ParticipantTracks) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ParticipantTracks
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getParticipantSid()
                    .isEmpty()) {
                participantSid_ = other.participantSid_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.trackSids_.isEmpty()) {
                if (trackSids_.isEmpty()) {
                    trackSids_ = other.trackSids_;
                    bitField0_ |= 0x00000002;
                } else {
                    ensureTrackSidsIsMutable();
                    trackSids_.addAll(other.trackSids_);
                }
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
                            participantSid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            String s = input.readStringRequireUtf8();
                            ensureTrackSidsIsMutable();
                            trackSids_.add(s);
                        } // case 18
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

        private java.lang.Object participantSid_ = "";

        /**
         * <pre>
         * participant ID of participant to whom the tracks belong
         * </pre>
         *
         * <code>string participant_sid = 1;</code>
         *
         * @return The participantSid.
         */
        public java.lang.String getParticipantSid() {
            java.lang.Object ref = participantSid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                participantSid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * participant ID of participant to whom the tracks belong
         * </pre>
         *
         * <code>string participant_sid = 1;</code>
         *
         * @return The bytes for participantSid.
         */
        public com.google.protobuf.ByteString getParticipantSidBytes() {
            java.lang.Object ref = participantSid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                participantSid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * participant ID of participant to whom the tracks belong
         * </pre>
         *
         * <code>string participant_sid = 1;</code>
         *
         * @param value The participantSid to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantSid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * participant ID of participant to whom the tracks belong
         * </pre>
         *
         * <code>string participant_sid = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantSid() {
            participantSid_ = getDefaultInstance().getParticipantSid();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * participant ID of participant to whom the tracks belong
         * </pre>
         *
         * <code>string participant_sid = 1;</code>
         *
         * @param value The bytes for participantSid to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            participantSid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList trackSids_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureTrackSidsIsMutable() {
            if (!trackSids_.isModifiable()) {
                trackSids_ = new com.google.protobuf.LazyStringArrayList(trackSids_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @return A list containing the trackSids.
         */
        public com.google.protobuf.ProtocolStringList getTrackSidsList() {
            trackSids_.makeImmutable();
            return trackSids_;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @return The count of trackSids.
         */
        public int getTrackSidsCount() {
            return trackSids_.size();
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The trackSids at the given index.
         */
        public java.lang.String getTrackSids(int index) {
            return trackSids_.get(index);
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the trackSids at the given index.
         */
        public com.google.protobuf.ByteString getTrackSidsBytes(int index) {
            return trackSids_.getByteString(index);
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The trackSids to set.
         * @return This builder for chaining.
         */
        public Builder setTrackSids(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrackSidsIsMutable();
            trackSids_.set(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param value The trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addTrackSids(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrackSidsIsMutable();
            trackSids_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param values The trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addAllTrackSids(java.lang.Iterable<java.lang.String> values) {
            ensureTrackSidsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, trackSids_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTrackSids() {
            trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string track_sids = 2;</code>
         *
         * @param value The bytes of the trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addTrackSidsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureTrackSidsIsMutable();
            trackSids_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ParticipantTracks)
    }

    // @@protoc_insertion_point(class_scope:livekit.ParticipantTracks)
    private static final im.turms.plugin.livekit.core.proto.models.ParticipantTracks DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ParticipantTracks();
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantTracks getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ParticipantTracks> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ParticipantTracks parsePartialFrom(
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

    public static com.google.protobuf.Parser<ParticipantTracks> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ParticipantTracks> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantTracks getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}