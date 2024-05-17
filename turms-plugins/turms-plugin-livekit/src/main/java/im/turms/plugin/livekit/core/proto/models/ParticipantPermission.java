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
 * Protobuf type {@code livekit.ParticipantPermission}
 */
public final class ParticipantPermission extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ParticipantPermission)
        ParticipantPermissionOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ParticipantPermission.class.getName());
    }

    // Use ParticipantPermission.newBuilder() to construct.
    private ParticipantPermission(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ParticipantPermission() {
        canPublishSources_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantPermission_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantPermission_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ParticipantPermission.class,
                        im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder.class);
    }

    public static final int CAN_SUBSCRIBE_FIELD_NUMBER = 1;
    private boolean canSubscribe_ = false;

    /**
     * <pre>
     * allow participant to subscribe to other tracks in the room
     * </pre>
     *
     * <code>bool can_subscribe = 1;</code>
     *
     * @return The canSubscribe.
     */
    @java.lang.Override
    public boolean getCanSubscribe() {
        return canSubscribe_;
    }

    public static final int CAN_PUBLISH_FIELD_NUMBER = 2;
    private boolean canPublish_ = false;

    /**
     * <pre>
     * allow participant to publish new tracks to room
     * </pre>
     *
     * <code>bool can_publish = 2;</code>
     *
     * @return The canPublish.
     */
    @java.lang.Override
    public boolean getCanPublish() {
        return canPublish_;
    }

    public static final int CAN_PUBLISH_DATA_FIELD_NUMBER = 3;
    private boolean canPublishData_ = false;

    /**
     * <pre>
     * allow participant to publish data
     * </pre>
     *
     * <code>bool can_publish_data = 3;</code>
     *
     * @return The canPublishData.
     */
    @java.lang.Override
    public boolean getCanPublishData() {
        return canPublishData_;
    }

    public static final int CAN_PUBLISH_SOURCES_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private java.util.List<java.lang.Integer> canPublishSources_;
    private static final com.google.protobuf.Internal.ListAdapter.Converter<java.lang.Integer, im.turms.plugin.livekit.core.proto.models.TrackSource> canPublishSources_converter_ =
            from -> {
                TrackSource result = TrackSource.forNumber(from);
                return result == null
                        ? TrackSource.UNRECOGNIZED
                        : result;
            };

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return A list containing the canPublishSources.
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackSource> getCanPublishSourcesList() {
        return new com.google.protobuf.Internal.ListAdapter<>(
                canPublishSources_,
                canPublishSources_converter_);
    }

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return The count of canPublishSources.
     */
    @java.lang.Override
    public int getCanPublishSourcesCount() {
        return canPublishSources_.size();
    }

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @param index The index of the element to return.
     * @return The canPublishSources at the given index.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackSource getCanPublishSources(int index) {
        return canPublishSources_converter_.convert(canPublishSources_.get(index));
    }

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return A list containing the enum numeric values on the wire for canPublishSources.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getCanPublishSourcesValueList() {
        return canPublishSources_;
    }

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of canPublishSources at the given index.
     */
    @java.lang.Override
    public int getCanPublishSourcesValue(int index) {
        return canPublishSources_.get(index);
    }

    private int canPublishSourcesMemoizedSerializedSize;

    public static final int HIDDEN_FIELD_NUMBER = 7;
    private boolean hidden_ = false;

    /**
     * <pre>
     * indicates that it's hidden to others
     * </pre>
     *
     * <code>bool hidden = 7;</code>
     *
     * @return The hidden.
     */
    @java.lang.Override
    public boolean getHidden() {
        return hidden_;
    }

    public static final int RECORDER_FIELD_NUMBER = 8;
    private boolean recorder_ = false;

    /**
     * <pre>
     * indicates it's a recorder instance
     * </pre>
     *
     * <code>bool recorder = 8;</code>
     *
     * @return The recorder.
     */
    @java.lang.Override
    public boolean getRecorder() {
        return recorder_;
    }

    public static final int CAN_UPDATE_METADATA_FIELD_NUMBER = 10;
    private boolean canUpdateMetadata_ = false;

    /**
     * <pre>
     * indicates that participant can update own metadata
     * </pre>
     *
     * <code>bool can_update_metadata = 10;</code>
     *
     * @return The canUpdateMetadata.
     */
    @java.lang.Override
    public boolean getCanUpdateMetadata() {
        return canUpdateMetadata_;
    }

    public static final int AGENT_FIELD_NUMBER = 11;
    private boolean agent_ = false;

    /**
     * <pre>
     * indicates that participant is an agent
     * </pre>
     *
     * <code>bool agent = 11;</code>
     *
     * @return The agent.
     */
    @java.lang.Override
    public boolean getAgent() {
        return agent_;
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
        getSerializedSize();
        if (canSubscribe_) {
            output.writeBool(1, canSubscribe_);
        }
        if (canPublish_) {
            output.writeBool(2, canPublish_);
        }
        if (canPublishData_) {
            output.writeBool(3, canPublishData_);
        }
        if (hidden_) {
            output.writeBool(7, hidden_);
        }
        if (recorder_) {
            output.writeBool(8, recorder_);
        }
        if (getCanPublishSourcesList().size() > 0) {
            output.writeUInt32NoTag(74);
            output.writeUInt32NoTag(canPublishSourcesMemoizedSerializedSize);
        }
        for (Integer integer : canPublishSources_) {
            output.writeEnumNoTag(integer);
        }
        if (canUpdateMetadata_) {
            output.writeBool(10, canUpdateMetadata_);
        }
        if (agent_) {
            output.writeBool(11, agent_);
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
        if (canSubscribe_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(1, canSubscribe_);
        }
        if (canPublish_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(2, canPublish_);
        }
        if (canPublishData_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, canPublishData_);
        }
        if (hidden_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(7, hidden_);
        }
        if (recorder_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, recorder_);
        }
        {
            int dataSize = 0;
            for (Integer integer : canPublishSources_) {
                dataSize += com.google.protobuf.CodedOutputStream.computeEnumSizeNoTag(integer);
            }
            size += dataSize;
            if (!getCanPublishSourcesList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(dataSize);
            }
            canPublishSourcesMemoizedSerializedSize = dataSize;
        }
        if (canUpdateMetadata_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(10, canUpdateMetadata_);
        }
        if (agent_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(11, agent_);
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
        if (!(obj instanceof ParticipantPermission other)) {
            return super.equals(obj);
        }

        if (getCanSubscribe() != other.getCanSubscribe()) {
            return false;
        }
        if (getCanPublish() != other.getCanPublish()) {
            return false;
        }
        if (getCanPublishData() != other.getCanPublishData()) {
            return false;
        }
        if (!canPublishSources_.equals(other.canPublishSources_)) {
            return false;
        }
        if (getHidden() != other.getHidden()) {
            return false;
        }
        if (getRecorder() != other.getRecorder()) {
            return false;
        }
        if (getCanUpdateMetadata() != other.getCanUpdateMetadata()) {
            return false;
        }
        if (getAgent() != other.getAgent()) {
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
        hash = (37 * hash) + CAN_SUBSCRIBE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getCanSubscribe());
        hash = (37 * hash) + CAN_PUBLISH_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getCanPublish());
        hash = (37 * hash) + CAN_PUBLISH_DATA_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getCanPublishData());
        if (getCanPublishSourcesCount() > 0) {
            hash = (37 * hash) + CAN_PUBLISH_SOURCES_FIELD_NUMBER;
            hash = (53 * hash) + canPublishSources_.hashCode();
        }
        hash = (37 * hash) + HIDDEN_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getHidden());
        hash = (37 * hash) + RECORDER_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getRecorder());
        hash = (37 * hash) + CAN_UPDATE_METADATA_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getCanUpdateMetadata());
        hash = (37 * hash) + AGENT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAgent());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ParticipantPermission prototype) {
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
     * Protobuf type {@code livekit.ParticipantPermission}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ParticipantPermission)
            im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantPermission_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantPermission_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ParticipantPermission.class,
                            im.turms.plugin.livekit.core.proto.models.ParticipantPermission.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.models.ParticipantPermission.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            canSubscribe_ = false;
            canPublish_ = false;
            canPublishData_ = false;
            canPublishSources_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000008;
            hidden_ = false;
            recorder_ = false;
            canUpdateMetadata_ = false;
            agent_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ParticipantPermission_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission build() {
            im.turms.plugin.livekit.core.proto.models.ParticipantPermission result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ParticipantPermission buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ParticipantPermission result =
                    new im.turms.plugin.livekit.core.proto.models.ParticipantPermission(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission result) {
            if (((bitField0_ & 0x00000008) != 0)) {
                canPublishSources_ = java.util.Collections.unmodifiableList(canPublishSources_);
                bitField0_ &= ~0x00000008;
            }
            result.canPublishSources_ = canPublishSources_;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.canSubscribe_ = canSubscribe_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.canPublish_ = canPublish_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.canPublishData_ = canPublishData_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.hidden_ = hidden_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.recorder_ = recorder_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.canUpdateMetadata_ = canUpdateMetadata_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.agent_ = agent_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ParticipantPermission) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.ParticipantPermission) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.ParticipantPermission other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ParticipantPermission
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getCanSubscribe()) {
                setCanSubscribe(other.getCanSubscribe());
            }
            if (other.getCanPublish()) {
                setCanPublish(other.getCanPublish());
            }
            if (other.getCanPublishData()) {
                setCanPublishData(other.getCanPublishData());
            }
            if (!other.canPublishSources_.isEmpty()) {
                if (canPublishSources_.isEmpty()) {
                    canPublishSources_ = other.canPublishSources_;
                    bitField0_ &= ~0x00000008;
                } else {
                    ensureCanPublishSourcesIsMutable();
                    canPublishSources_.addAll(other.canPublishSources_);
                }
                onChanged();
            }
            if (other.getHidden()) {
                setHidden(other.getHidden());
            }
            if (other.getRecorder()) {
                setRecorder(other.getRecorder());
            }
            if (other.getCanUpdateMetadata()) {
                setCanUpdateMetadata(other.getCanUpdateMetadata());
            }
            if (other.getAgent()) {
                setAgent(other.getAgent());
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
                            canSubscribe_ = input.readBool();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            canPublish_ = input.readBool();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            canPublishData_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 56 -> {
                            hidden_ = input.readBool();
                            bitField0_ |= 0x00000010;
                        } // case 56
                        case 64 -> {
                            recorder_ = input.readBool();
                            bitField0_ |= 0x00000020;
                        } // case 64
                        case 72 -> {
                            int tmpRaw = input.readEnum();
                            ensureCanPublishSourcesIsMutable();
                            canPublishSources_.add(tmpRaw);
                        } // case 72
                        case 74 -> {
                            int length = input.readRawVarint32();
                            int oldLimit = input.pushLimit(length);
                            while (input.getBytesUntilLimit() > 0) {
                                int tmpRaw = input.readEnum();
                                ensureCanPublishSourcesIsMutable();
                                canPublishSources_.add(tmpRaw);
                            }
                            input.popLimit(oldLimit);
                        } // case 74
                        case 80 -> {
                            canUpdateMetadata_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 80
                        case 88 -> {
                            agent_ = input.readBool();
                            bitField0_ |= 0x00000080;
                        } // case 88
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

        private boolean canSubscribe_;

        /**
         * <pre>
         * allow participant to subscribe to other tracks in the room
         * </pre>
         *
         * <code>bool can_subscribe = 1;</code>
         *
         * @return The canSubscribe.
         */
        @java.lang.Override
        public boolean getCanSubscribe() {
            return canSubscribe_;
        }

        /**
         * <pre>
         * allow participant to subscribe to other tracks in the room
         * </pre>
         *
         * <code>bool can_subscribe = 1;</code>
         *
         * @param value The canSubscribe to set.
         * @return This builder for chaining.
         */
        public Builder setCanSubscribe(boolean value) {

            canSubscribe_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * allow participant to subscribe to other tracks in the room
         * </pre>
         *
         * <code>bool can_subscribe = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCanSubscribe() {
            bitField0_ &= ~0x00000001;
            canSubscribe_ = false;
            onChanged();
            return this;
        }

        private boolean canPublish_;

        /**
         * <pre>
         * allow participant to publish new tracks to room
         * </pre>
         *
         * <code>bool can_publish = 2;</code>
         *
         * @return The canPublish.
         */
        @java.lang.Override
        public boolean getCanPublish() {
            return canPublish_;
        }

        /**
         * <pre>
         * allow participant to publish new tracks to room
         * </pre>
         *
         * <code>bool can_publish = 2;</code>
         *
         * @param value The canPublish to set.
         * @return This builder for chaining.
         */
        public Builder setCanPublish(boolean value) {

            canPublish_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * allow participant to publish new tracks to room
         * </pre>
         *
         * <code>bool can_publish = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCanPublish() {
            bitField0_ &= ~0x00000002;
            canPublish_ = false;
            onChanged();
            return this;
        }

        private boolean canPublishData_;

        /**
         * <pre>
         * allow participant to publish data
         * </pre>
         *
         * <code>bool can_publish_data = 3;</code>
         *
         * @return The canPublishData.
         */
        @java.lang.Override
        public boolean getCanPublishData() {
            return canPublishData_;
        }

        /**
         * <pre>
         * allow participant to publish data
         * </pre>
         *
         * <code>bool can_publish_data = 3;</code>
         *
         * @param value The canPublishData to set.
         * @return This builder for chaining.
         */
        public Builder setCanPublishData(boolean value) {

            canPublishData_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * allow participant to publish data
         * </pre>
         *
         * <code>bool can_publish_data = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCanPublishData() {
            bitField0_ &= ~0x00000004;
            canPublishData_ = false;
            onChanged();
            return this;
        }

        private java.util.List<java.lang.Integer> canPublishSources_ =
                java.util.Collections.emptyList();

        private void ensureCanPublishSourcesIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                canPublishSources_ = new java.util.ArrayList<>(canPublishSources_);
                bitField0_ |= 0x00000008;
            }
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @return A list containing the canPublishSources.
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.TrackSource> getCanPublishSourcesList() {
            return new com.google.protobuf.Internal.ListAdapter<>(
                    canPublishSources_,
                    canPublishSources_converter_);
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @return The count of canPublishSources.
         */
        public int getCanPublishSourcesCount() {
            return canPublishSources_.size();
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param index The index of the element to return.
         * @return The canPublishSources at the given index.
         */
        public im.turms.plugin.livekit.core.proto.models.TrackSource getCanPublishSources(
                int index) {
            return canPublishSources_converter_.convert(canPublishSources_.get(index));
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param index The index to set the value at.
         * @param value The canPublishSources to set.
         * @return This builder for chaining.
         */
        public Builder setCanPublishSources(
                int index,
                im.turms.plugin.livekit.core.proto.models.TrackSource value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCanPublishSourcesIsMutable();
            canPublishSources_.set(index, value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param value The canPublishSources to add.
         * @return This builder for chaining.
         */
        public Builder addCanPublishSources(
                im.turms.plugin.livekit.core.proto.models.TrackSource value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureCanPublishSourcesIsMutable();
            canPublishSources_.add(value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param values The canPublishSources to add.
         * @return This builder for chaining.
         */
        public Builder addAllCanPublishSources(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.TrackSource> values) {
            ensureCanPublishSourcesIsMutable();
            for (im.turms.plugin.livekit.core.proto.models.TrackSource value : values) {
                canPublishSources_.add(value.getNumber());
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCanPublishSources() {
            canPublishSources_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @return A list containing the enum numeric values on the wire for canPublishSources.
         */
        public java.util.List<java.lang.Integer> getCanPublishSourcesValueList() {
            return java.util.Collections.unmodifiableList(canPublishSources_);
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of canPublishSources at the given index.
         */
        public int getCanPublishSourcesValue(int index) {
            return canPublishSources_.get(index);
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param index The index to set the value at.
         * @param value The enum numeric value on the wire for canPublishSources to set.
         * @return This builder for chaining.
         */
        public Builder setCanPublishSourcesValue(int index, int value) {
            ensureCanPublishSourcesIsMutable();
            canPublishSources_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param value The enum numeric value on the wire for canPublishSources to add.
         * @return This builder for chaining.
         */
        public Builder addCanPublishSourcesValue(int value) {
            ensureCanPublishSourcesIsMutable();
            canPublishSources_.add(value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sources that are allowed to be published
         * </pre>
         *
         * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
         *
         * @param values The enum numeric values on the wire for canPublishSources to add.
         * @return This builder for chaining.
         */
        public Builder addAllCanPublishSourcesValue(java.lang.Iterable<java.lang.Integer> values) {
            ensureCanPublishSourcesIsMutable();
            for (int value : values) {
                canPublishSources_.add(value);
            }
            onChanged();
            return this;
        }

        private boolean hidden_;

        /**
         * <pre>
         * indicates that it's hidden to others
         * </pre>
         *
         * <code>bool hidden = 7;</code>
         *
         * @return The hidden.
         */
        @java.lang.Override
        public boolean getHidden() {
            return hidden_;
        }

        /**
         * <pre>
         * indicates that it's hidden to others
         * </pre>
         *
         * <code>bool hidden = 7;</code>
         *
         * @param value The hidden to set.
         * @return This builder for chaining.
         */
        public Builder setHidden(boolean value) {

            hidden_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * indicates that it's hidden to others
         * </pre>
         *
         * <code>bool hidden = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHidden() {
            bitField0_ &= ~0x00000010;
            hidden_ = false;
            onChanged();
            return this;
        }

        private boolean recorder_;

        /**
         * <pre>
         * indicates it's a recorder instance
         * </pre>
         *
         * <code>bool recorder = 8;</code>
         *
         * @return The recorder.
         */
        @java.lang.Override
        public boolean getRecorder() {
            return recorder_;
        }

        /**
         * <pre>
         * indicates it's a recorder instance
         * </pre>
         *
         * <code>bool recorder = 8;</code>
         *
         * @param value The recorder to set.
         * @return This builder for chaining.
         */
        public Builder setRecorder(boolean value) {

            recorder_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * indicates it's a recorder instance
         * </pre>
         *
         * <code>bool recorder = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecorder() {
            bitField0_ &= ~0x00000020;
            recorder_ = false;
            onChanged();
            return this;
        }

        private boolean canUpdateMetadata_;

        /**
         * <pre>
         * indicates that participant can update own metadata
         * </pre>
         *
         * <code>bool can_update_metadata = 10;</code>
         *
         * @return The canUpdateMetadata.
         */
        @java.lang.Override
        public boolean getCanUpdateMetadata() {
            return canUpdateMetadata_;
        }

        /**
         * <pre>
         * indicates that participant can update own metadata
         * </pre>
         *
         * <code>bool can_update_metadata = 10;</code>
         *
         * @param value The canUpdateMetadata to set.
         * @return This builder for chaining.
         */
        public Builder setCanUpdateMetadata(boolean value) {

            canUpdateMetadata_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * indicates that participant can update own metadata
         * </pre>
         *
         * <code>bool can_update_metadata = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCanUpdateMetadata() {
            bitField0_ &= ~0x00000040;
            canUpdateMetadata_ = false;
            onChanged();
            return this;
        }

        private boolean agent_;

        /**
         * <pre>
         * indicates that participant is an agent
         * </pre>
         *
         * <code>bool agent = 11;</code>
         *
         * @return The agent.
         */
        @java.lang.Override
        public boolean getAgent() {
            return agent_;
        }

        /**
         * <pre>
         * indicates that participant is an agent
         * </pre>
         *
         * <code>bool agent = 11;</code>
         *
         * @param value The agent to set.
         * @return This builder for chaining.
         */
        public Builder setAgent(boolean value) {

            agent_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * indicates that participant is an agent
         * </pre>
         *
         * <code>bool agent = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAgent() {
            bitField0_ &= ~0x00000080;
            agent_ = false;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ParticipantPermission)
    }

    // @@protoc_insertion_point(class_scope:livekit.ParticipantPermission)
    private static final im.turms.plugin.livekit.core.proto.models.ParticipantPermission DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ParticipantPermission();
    }

    public static im.turms.plugin.livekit.core.proto.models.ParticipantPermission getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ParticipantPermission> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ParticipantPermission parsePartialFrom(
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

    public static com.google.protobuf.Parser<ParticipantPermission> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ParticipantPermission> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantPermission getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}