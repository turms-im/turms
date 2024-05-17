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
 * Protobuf type {@code livekit.SendDataRequest}
 */
public final class SendDataRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SendDataRequest)
        SendDataRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SendDataRequest.class.getName());
    }

    // Use SendDataRequest.newBuilder() to construct.
    private SendDataRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SendDataRequest() {
        room_ = "";
        data_ = com.google.protobuf.ByteString.EMPTY;
        kind_ = 0;
        destinationSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
        destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
        topic_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_SendDataRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_SendDataRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.SendDataRequest.class,
                        im.turms.plugin.livekit.core.proto.room.SendDataRequest.Builder.class);
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

    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;

    /**
     * <code>bytes data = 2;</code>
     *
     * @return The data.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getData() {
        return data_;
    }

    public static final int KIND_FIELD_NUMBER = 3;
    private int kind_ = 0;

    /**
     * <code>.livekit.DataPacket.Kind kind = 3;</code>
     *
     * @return The enum numeric value on the wire for kind.
     */
    @java.lang.Override
    public int getKindValue() {
        return kind_;
    }

    /**
     * <code>.livekit.DataPacket.Kind kind = 3;</code>
     *
     * @return The kind.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind() {
        im.turms.plugin.livekit.core.proto.models.DataPacket.Kind result =
                im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.forNumber(kind_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.UNRECOGNIZED
                : result;
    }

    public static final int DESTINATION_SIDS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList destinationSids_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @return A list containing the destinationSids.
     */
    @java.lang.Deprecated
    public com.google.protobuf.ProtocolStringList getDestinationSidsList() {
        return destinationSids_;
    }

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @return The count of destinationSids.
     */
    @java.lang.Deprecated
    public int getDestinationSidsCount() {
        return destinationSids_.size();
    }

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @param index The index of the element to return.
     * @return The destinationSids at the given index.
     */
    @java.lang.Deprecated
    public java.lang.String getDestinationSids(int index) {
        return destinationSids_.get(index);
    }

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @param index The index of the value to return.
     * @return The bytes of the destinationSids at the given index.
     */
    @java.lang.Deprecated
    public com.google.protobuf.ByteString getDestinationSidsBytes(int index) {
        return destinationSids_.getByteString(index);
    }

    public static final int DESTINATION_IDENTITIES_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList destinationIdentities_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @return A list containing the destinationIdentities.
     */
    public com.google.protobuf.ProtocolStringList getDestinationIdentitiesList() {
        return destinationIdentities_;
    }

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @return The count of destinationIdentities.
     */
    public int getDestinationIdentitiesCount() {
        return destinationIdentities_.size();
    }

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @param index The index of the element to return.
     * @return The destinationIdentities at the given index.
     */
    public java.lang.String getDestinationIdentities(int index) {
        return destinationIdentities_.get(index);
    }

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the destinationIdentities at the given index.
     */
    public com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index) {
        return destinationIdentities_.getByteString(index);
    }

    public static final int TOPIC_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object topic_ = "";

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return Whether the topic field is set.
     */
    @java.lang.Override
    public boolean hasTopic() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return The topic.
     */
    @java.lang.Override
    public java.lang.String getTopic() {
        java.lang.Object ref = topic_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            topic_ = s;
            return s;
        }
    }

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return The bytes for topic.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTopicBytes() {
        java.lang.Object ref = topic_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            topic_ = b;
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
        if (!data_.isEmpty()) {
            output.writeBytes(2, data_);
        }
        if (kind_ != im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.RELIABLE
                .getNumber()) {
            output.writeEnum(3, kind_);
        }
        for (int i = 0; i < destinationSids_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, destinationSids_.getRaw(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, topic_);
        }
        for (int i = 0; i < destinationIdentities_.size(); i++) {
            com.google.protobuf.GeneratedMessage
                    .writeString(output, 6, destinationIdentities_.getRaw(i));
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
        if (!data_.isEmpty()) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(2, data_);
        }
        if (kind_ != im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.RELIABLE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(3, kind_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < destinationSids_.size(); i++) {
                dataSize += computeStringSizeNoTag(destinationSids_.getRaw(i));
            }
            size += dataSize;
            size += getDestinationSidsList().size();
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, topic_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < destinationIdentities_.size(); i++) {
                dataSize += computeStringSizeNoTag(destinationIdentities_.getRaw(i));
            }
            size += dataSize;
            size += getDestinationIdentitiesList().size();
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
        if (!(obj instanceof SendDataRequest other)) {
            return super.equals(obj);
        }

        if (!getRoom().equals(other.getRoom())) {
            return false;
        }
        if (!getData().equals(other.getData())) {
            return false;
        }
        if (kind_ != other.kind_) {
            return false;
        }
        if (!getDestinationSidsList().equals(other.getDestinationSidsList())) {
            return false;
        }
        if (!getDestinationIdentitiesList().equals(other.getDestinationIdentitiesList())) {
            return false;
        }
        if (hasTopic() != other.hasTopic()) {
            return false;
        }
        if (hasTopic()) {
            if (!getTopic().equals(other.getTopic())) {
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
        hash = (37 * hash) + ROOM_FIELD_NUMBER;
        hash = (53 * hash) + getRoom().hashCode();
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + getData().hashCode();
        hash = (37 * hash) + KIND_FIELD_NUMBER;
        hash = (53 * hash) + kind_;
        if (getDestinationSidsCount() > 0) {
            hash = (37 * hash) + DESTINATION_SIDS_FIELD_NUMBER;
            hash = (53 * hash) + getDestinationSidsList().hashCode();
        }
        if (getDestinationIdentitiesCount() > 0) {
            hash = (37 * hash) + DESTINATION_IDENTITIES_FIELD_NUMBER;
            hash = (53 * hash) + getDestinationIdentitiesList().hashCode();
        }
        if (hasTopic()) {
            hash = (37 * hash) + TOPIC_FIELD_NUMBER;
            hash = (53 * hash) + getTopic().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.SendDataRequest prototype) {
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
     * Protobuf type {@code livekit.SendDataRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SendDataRequest)
            im.turms.plugin.livekit.core.proto.room.SendDataRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_SendDataRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_SendDataRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.SendDataRequest.class,
                            im.turms.plugin.livekit.core.proto.room.SendDataRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.room.SendDataRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            room_ = "";
            data_ = com.google.protobuf.ByteString.EMPTY;
            kind_ = 0;
            destinationSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
            topic_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_SendDataRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.SendDataRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.SendDataRequest.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.SendDataRequest build() {
            im.turms.plugin.livekit.core.proto.room.SendDataRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.SendDataRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.room.SendDataRequest result =
                    new im.turms.plugin.livekit.core.proto.room.SendDataRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.room.SendDataRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.room_ = room_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.data_ = data_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.kind_ = kind_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                destinationSids_.makeImmutable();
                result.destinationSids_ = destinationSids_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                destinationIdentities_.makeImmutable();
                result.destinationIdentities_ = destinationIdentities_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.topic_ = topic_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.SendDataRequest) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.room.SendDataRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.room.SendDataRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.room.SendDataRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoom()
                    .isEmpty()) {
                room_ = other.room_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
                setData(other.getData());
            }
            if (other.kind_ != 0) {
                setKindValue(other.getKindValue());
            }
            if (!other.destinationSids_.isEmpty()) {
                if (destinationSids_.isEmpty()) {
                    destinationSids_ = other.destinationSids_;
                    bitField0_ |= 0x00000008;
                } else {
                    ensureDestinationSidsIsMutable();
                    destinationSids_.addAll(other.destinationSids_);
                }
                onChanged();
            }
            if (!other.destinationIdentities_.isEmpty()) {
                if (destinationIdentities_.isEmpty()) {
                    destinationIdentities_ = other.destinationIdentities_;
                    bitField0_ |= 0x00000010;
                } else {
                    ensureDestinationIdentitiesIsMutable();
                    destinationIdentities_.addAll(other.destinationIdentities_);
                }
                onChanged();
            }
            if (other.hasTopic()) {
                topic_ = other.topic_;
                bitField0_ |= 0x00000020;
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
                            data_ = input.readBytes();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            kind_ = input.readEnum();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            String s = input.readStringRequireUtf8();
                            ensureDestinationSidsIsMutable();
                            destinationSids_.add(s);
                        } // case 34
                        case 42 -> {
                            topic_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 42
                        case 50 -> {
                            String s = input.readStringRequireUtf8();
                            ensureDestinationIdentitiesIsMutable();
                            destinationIdentities_.add(s);
                        } // case 50
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

        private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;

        /**
         * <code>bytes data = 2;</code>
         *
         * @return The data.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getData() {
            return data_;
        }

        /**
         * <code>bytes data = 2;</code>
         *
         * @param value The data to set.
         * @return This builder for chaining.
         */
        public Builder setData(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            data_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>bytes data = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearData() {
            bitField0_ &= ~0x00000002;
            data_ = getDefaultInstance().getData();
            onChanged();
            return this;
        }

        private int kind_ = 0;

        /**
         * <code>.livekit.DataPacket.Kind kind = 3;</code>
         *
         * @return The enum numeric value on the wire for kind.
         */
        @java.lang.Override
        public int getKindValue() {
            return kind_;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 3;</code>
         *
         * @param value The enum numeric value on the wire for kind to set.
         * @return This builder for chaining.
         */
        public Builder setKindValue(int value) {
            kind_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 3;</code>
         *
         * @return The kind.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind() {
            im.turms.plugin.livekit.core.proto.models.DataPacket.Kind result =
                    im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.forNumber(kind_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 3;</code>
         *
         * @param value The kind to set.
         * @return This builder for chaining.
         */
        public Builder setKind(im.turms.plugin.livekit.core.proto.models.DataPacket.Kind value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000004;
            kind_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKind() {
            bitField0_ &= ~0x00000004;
            kind_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList destinationSids_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureDestinationSidsIsMutable() {
            if (!destinationSids_.isModifiable()) {
                destinationSids_ = new com.google.protobuf.LazyStringArrayList(destinationSids_);
            }
            bitField0_ |= 0x00000008;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @return A list containing the destinationSids.
         */
        @java.lang.Deprecated
        public com.google.protobuf.ProtocolStringList getDestinationSidsList() {
            destinationSids_.makeImmutable();
            return destinationSids_;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @return The count of destinationSids.
         */
        @java.lang.Deprecated
        public int getDestinationSidsCount() {
            return destinationSids_.size();
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param index The index of the element to return.
         * @return The destinationSids at the given index.
         */
        @java.lang.Deprecated
        public java.lang.String getDestinationSids(int index) {
            return destinationSids_.get(index);
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param index The index of the value to return.
         * @return The bytes of the destinationSids at the given index.
         */
        @java.lang.Deprecated
        public com.google.protobuf.ByteString getDestinationSidsBytes(int index) {
            return destinationSids_.getByteString(index);
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param index The index to set the value at.
         * @param value The destinationSids to set.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder setDestinationSids(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDestinationSidsIsMutable();
            destinationSids_.set(index, value);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param value The destinationSids to add.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder addDestinationSids(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDestinationSidsIsMutable();
            destinationSids_.add(value);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param values The destinationSids to add.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder addAllDestinationSids(java.lang.Iterable<java.lang.String> values) {
            ensureDestinationSidsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, destinationSids_);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder clearDestinationSids() {
            destinationSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mark deprecated
         * </pre>
         *
         * <code>repeated string destination_sids = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
         *             livekit_room.proto;l=139
         * @param value The bytes of the destinationSids to add.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder addDestinationSidsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureDestinationSidsIsMutable();
            destinationSids_.add(value);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList destinationIdentities_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureDestinationIdentitiesIsMutable() {
            if (!destinationIdentities_.isModifiable()) {
                destinationIdentities_ =
                        new com.google.protobuf.LazyStringArrayList(destinationIdentities_);
            }
            bitField0_ |= 0x00000010;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @return A list containing the destinationIdentities.
         */
        public com.google.protobuf.ProtocolStringList getDestinationIdentitiesList() {
            destinationIdentities_.makeImmutable();
            return destinationIdentities_;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @return The count of destinationIdentities.
         */
        public int getDestinationIdentitiesCount() {
            return destinationIdentities_.size();
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param index The index of the element to return.
         * @return The destinationIdentities at the given index.
         */
        public java.lang.String getDestinationIdentities(int index) {
            return destinationIdentities_.get(index);
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the destinationIdentities at the given index.
         */
        public com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index) {
            return destinationIdentities_.getByteString(index);
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param index The index to set the value at.
         * @param value The destinationIdentities to set.
         * @return This builder for chaining.
         */
        public Builder setDestinationIdentities(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDestinationIdentitiesIsMutable();
            destinationIdentities_.set(index, value);
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param value The destinationIdentities to add.
         * @return This builder for chaining.
         */
        public Builder addDestinationIdentities(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDestinationIdentitiesIsMutable();
            destinationIdentities_.add(value);
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param values The destinationIdentities to add.
         * @return This builder for chaining.
         */
        public Builder addAllDestinationIdentities(java.lang.Iterable<java.lang.String> values) {
            ensureDestinationIdentitiesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, destinationIdentities_);
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDestinationIdentities() {
            destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * when set, only forward to these identities
         * </pre>
         *
         * <code>repeated string destination_identities = 6;</code>
         *
         * @param value The bytes of the destinationIdentities to add.
         * @return This builder for chaining.
         */
        public Builder addDestinationIdentitiesBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureDestinationIdentitiesIsMutable();
            destinationIdentities_.add(value);
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object topic_ = "";

        /**
         * <code>optional string topic = 5;</code>
         *
         * @return Whether the topic field is set.
         */
        public boolean hasTopic() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional string topic = 5;</code>
         *
         * @return The topic.
         */
        public java.lang.String getTopic() {
            java.lang.Object ref = topic_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                topic_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string topic = 5;</code>
         *
         * @return The bytes for topic.
         */
        public com.google.protobuf.ByteString getTopicBytes() {
            java.lang.Object ref = topic_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                topic_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string topic = 5;</code>
         *
         * @param value The topic to set.
         * @return This builder for chaining.
         */
        public Builder setTopic(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            topic_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional string topic = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTopic() {
            topic_ = getDefaultInstance().getTopic();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional string topic = 5;</code>
         *
         * @param value The bytes for topic to set.
         * @return This builder for chaining.
         */
        public Builder setTopicBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            topic_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SendDataRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.SendDataRequest)
    private static final im.turms.plugin.livekit.core.proto.room.SendDataRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.SendDataRequest();
    }

    public static im.turms.plugin.livekit.core.proto.room.SendDataRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SendDataRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SendDataRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<SendDataRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SendDataRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.SendDataRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}