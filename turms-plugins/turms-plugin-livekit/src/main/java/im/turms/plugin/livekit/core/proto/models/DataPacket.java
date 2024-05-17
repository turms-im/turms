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
 * <pre>
 * new DataPacket API
 * </pre>
 *
 * Protobuf type {@code livekit.DataPacket}
 */
public final class DataPacket extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.DataPacket)
        DataPacketOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                DataPacket.class.getName());
    }

    // Use DataPacket.newBuilder() to construct.
    private DataPacket(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private DataPacket() {
        kind_ = 0;
        participantIdentity_ = "";
        destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DataPacket_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DataPacket_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.DataPacket.class,
                        im.turms.plugin.livekit.core.proto.models.DataPacket.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.DataPacket.Kind}
     */
    public enum Kind implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <code>RELIABLE = 0;</code>
         */
        RELIABLE(0),
        /**
         * <code>LOSSY = 1;</code>
         */
        LOSSY(1),
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
         * <code>RELIABLE = 0;</code>
         */
        public static final int RELIABLE_VALUE = 0;
        /**
         * <code>LOSSY = 1;</code>
         */
        public static final int LOSSY_VALUE = 1;

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
                case 0 -> RELIABLE;
                case 1 -> LOSSY;
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
            return im.turms.plugin.livekit.core.proto.models.DataPacket.getDescriptor()
                    .getEnumTypes()
                    .get(0);
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

        // @@protoc_insertion_point(enum_scope:livekit.DataPacket.Kind)
    }

    private int valueCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object value_;

    public enum ValueCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        USER(2),
        @java.lang.Deprecated
        SPEAKER(3),
        SIP_DTMF(6),
        VALUE_NOT_SET(0);

        private final int value;

        ValueCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static ValueCase valueOf(int value) {
            return forNumber(value);
        }

        public static ValueCase forNumber(int value) {
            return switch (value) {
                case 2 -> USER;
                case 3 -> SPEAKER;
                case 6 -> SIP_DTMF;
                case 0 -> VALUE_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ValueCase getValueCase() {
        return ValueCase.forNumber(valueCase_);
    }

    public static final int KIND_FIELD_NUMBER = 1;
    private int kind_ = 0;

    /**
     * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
     * @return The enum numeric value on the wire for kind.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public int getKindValue() {
        return kind_;
    }

    /**
     * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
     * @return The kind.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind() {
        im.turms.plugin.livekit.core.proto.models.DataPacket.Kind result =
                im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.forNumber(kind_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.UNRECOGNIZED
                : result;
    }

    public static final int PARTICIPANT_IDENTITY_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantIdentity_ = "";

    /**
     * <pre>
     * participant identity of user that sent the message
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The participantIdentity.
     */
    @java.lang.Override
    public java.lang.String getParticipantIdentity() {
        java.lang.Object ref = participantIdentity_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            participantIdentity_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * participant identity of user that sent the message
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The bytes for participantIdentity.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getParticipantIdentityBytes() {
        java.lang.Object ref = participantIdentity_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            participantIdentity_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int DESTINATION_IDENTITIES_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList destinationIdentities_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @return A list containing the destinationIdentities.
     */
    public com.google.protobuf.ProtocolStringList getDestinationIdentitiesList() {
        return destinationIdentities_;
    }

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @return The count of destinationIdentities.
     */
    public int getDestinationIdentitiesCount() {
        return destinationIdentities_.size();
    }

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @param index The index of the element to return.
     * @return The destinationIdentities at the given index.
     */
    public java.lang.String getDestinationIdentities(int index) {
        return destinationIdentities_.get(index);
    }

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the destinationIdentities at the given index.
     */
    public com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index) {
        return destinationIdentities_.getByteString(index);
    }

    public static final int USER_FIELD_NUMBER = 2;

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     *
     * @return Whether the user field is set.
     */
    @java.lang.Override
    public boolean hasUser() {
        return valueCase_ == 2;
    }

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     *
     * @return The user.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.UserPacket getUser() {
        if (valueCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.models.UserPacket) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.UserPacket.getDefaultInstance();
    }

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.UserPacketOrBuilder getUserOrBuilder() {
        if (valueCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.models.UserPacket) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.UserPacket.getDefaultInstance();
    }

    public static final int SPEAKER_FIELD_NUMBER = 3;

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
     * @return Whether the speaker field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasSpeaker() {
        return valueCase_ == 3;
    }

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
     * @return The speaker.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getSpeaker() {
        if (valueCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.getDefaultInstance();
    }

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder getSpeakerOrBuilder() {
        if (valueCase_ == 3) {
            return (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.getDefaultInstance();
    }

    public static final int SIP_DTMF_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     *
     * @return Whether the sipDtmf field is set.
     */
    @java.lang.Override
    public boolean hasSipDtmf() {
        return valueCase_ == 6;
    }

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     *
     * @return The sipDtmf.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SipDTMF getSipDtmf() {
        if (valueCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
    }

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder getSipDtmfOrBuilder() {
        if (valueCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_;
        }
        return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
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
        if (kind_ != im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.RELIABLE
                .getNumber()) {
            output.writeEnum(1, kind_);
        }
        if (valueCase_ == 2) {
            output.writeMessage(2, (im.turms.plugin.livekit.core.proto.models.UserPacket) value_);
        }
        if (valueCase_ == 3) {
            output.writeMessage(3,
                    (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, participantIdentity_);
        }
        for (int i = 0; i < destinationIdentities_.size(); i++) {
            com.google.protobuf.GeneratedMessage
                    .writeString(output, 5, destinationIdentities_.getRaw(i));
        }
        if (valueCase_ == 6) {
            output.writeMessage(6, (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_);
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
        if (kind_ != im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.RELIABLE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, kind_);
        }
        if (valueCase_ == 2) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2,
                    (im.turms.plugin.livekit.core.proto.models.UserPacket) value_);
        }
        if (valueCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3,
                    (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, participantIdentity_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < destinationIdentities_.size(); i++) {
                dataSize += computeStringSizeNoTag(destinationIdentities_.getRaw(i));
            }
            size += dataSize;
            size += getDestinationIdentitiesList().size();
        }
        if (valueCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_);
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
        if (!(obj instanceof DataPacket other)) {
            return super.equals(obj);
        }

        if (kind_ != other.kind_) {
            return false;
        }
        if (!getParticipantIdentity().equals(other.getParticipantIdentity())) {
            return false;
        }
        if (!getDestinationIdentitiesList().equals(other.getDestinationIdentitiesList())) {
            return false;
        }
        if (!getValueCase().equals(other.getValueCase())) {
            return false;
        }
        switch (valueCase_) {
            case 2 -> {
                if (!getUser().equals(other.getUser())) {
                    return false;
                }
            }
            case 3 -> {
                if (!getSpeaker().equals(other.getSpeaker())) {
                    return false;
                }
            }
            case 6 -> {
                if (!getSipDtmf().equals(other.getSipDtmf())) {
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
        hash = (37 * hash) + KIND_FIELD_NUMBER;
        hash = (53 * hash) + kind_;
        hash = (37 * hash) + PARTICIPANT_IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantIdentity().hashCode();
        if (getDestinationIdentitiesCount() > 0) {
            hash = (37 * hash) + DESTINATION_IDENTITIES_FIELD_NUMBER;
            hash = (53 * hash) + getDestinationIdentitiesList().hashCode();
        }
        switch (valueCase_) {
            case 2 -> {
                hash = (37 * hash) + USER_FIELD_NUMBER;
                hash = (53 * hash) + getUser().hashCode();
            }
            case 3 -> {
                hash = (37 * hash) + SPEAKER_FIELD_NUMBER;
                hash = (53 * hash) + getSpeaker().hashCode();
            }
            case 6 -> {
                hash = (37 * hash) + SIP_DTMF_FIELD_NUMBER;
                hash = (53 * hash) + getSipDtmf().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.DataPacket prototype) {
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
     * new DataPacket API
     * </pre>
     *
     * Protobuf type {@code livekit.DataPacket}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.DataPacket)
            im.turms.plugin.livekit.core.proto.models.DataPacketOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DataPacket_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DataPacket_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.DataPacket.class,
                            im.turms.plugin.livekit.core.proto.models.DataPacket.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.DataPacket.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            kind_ = 0;
            participantIdentity_ = "";
            destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
            if (userBuilder_ != null) {
                userBuilder_.clear();
            }
            if (speakerBuilder_ != null) {
                speakerBuilder_.clear();
            }
            if (sipDtmfBuilder_ != null) {
                sipDtmfBuilder_.clear();
            }
            valueCase_ = 0;
            value_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DataPacket_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DataPacket getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.DataPacket.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DataPacket build() {
            im.turms.plugin.livekit.core.proto.models.DataPacket result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DataPacket buildPartial() {
            im.turms.plugin.livekit.core.proto.models.DataPacket result =
                    new im.turms.plugin.livekit.core.proto.models.DataPacket(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.DataPacket result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.kind_ = kind_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.participantIdentity_ = participantIdentity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                destinationIdentities_.makeImmutable();
                result.destinationIdentities_ = destinationIdentities_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.models.DataPacket result) {
            result.valueCase_ = valueCase_;
            result.value_ = this.value_;
            if (valueCase_ == 2 && userBuilder_ != null) {
                result.value_ = userBuilder_.build();
            }
            if (valueCase_ == 3 && speakerBuilder_ != null) {
                result.value_ = speakerBuilder_.build();
            }
            if (valueCase_ == 6 && sipDtmfBuilder_ != null) {
                result.value_ = sipDtmfBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.DataPacket) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.DataPacket) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.DataPacket other) {
            if (other == im.turms.plugin.livekit.core.proto.models.DataPacket
                    .getDefaultInstance()) {
                return this;
            }
            if (other.kind_ != 0) {
                setKindValue(other.getKindValue());
            }
            if (!other.getParticipantIdentity()
                    .isEmpty()) {
                participantIdentity_ = other.participantIdentity_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.destinationIdentities_.isEmpty()) {
                if (destinationIdentities_.isEmpty()) {
                    destinationIdentities_ = other.destinationIdentities_;
                    bitField0_ |= 0x00000004;
                } else {
                    ensureDestinationIdentitiesIsMutable();
                    destinationIdentities_.addAll(other.destinationIdentities_);
                }
                onChanged();
            }
            switch (other.getValueCase()) {
                case USER -> mergeUser(other.getUser());
                case SPEAKER -> mergeSpeaker(other.getSpeaker());
                case SIP_DTMF -> mergeSipDtmf(other.getSipDtmf());
                case VALUE_NOT_SET -> {
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
                            kind_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            input.readMessage(getUserFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            valueCase_ = 2;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getSpeakerFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            valueCase_ = 3;
                        } // case 26
                        case 34 -> {
                            participantIdentity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 34
                        case 42 -> {
                            String s = input.readStringRequireUtf8();
                            ensureDestinationIdentitiesIsMutable();
                            destinationIdentities_.add(s);
                        } // case 42
                        case 50 -> {
                            input.readMessage(getSipDtmfFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            valueCase_ = 6;
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

        private int valueCase_ = 0;
        private java.lang.Object value_;

        public ValueCase getValueCase() {
            return ValueCase.forNumber(valueCase_);
        }

        public Builder clearValue() {
            valueCase_ = 0;
            value_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        private int kind_ = 0;

        /**
         * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
         * @return The enum numeric value on the wire for kind.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public int getKindValue() {
            return kind_;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
         * @param value The enum numeric value on the wire for kind to set.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder setKindValue(int value) {
            kind_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
         * @return The kind.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind() {
            im.turms.plugin.livekit.core.proto.models.DataPacket.Kind result =
                    im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.forNumber(kind_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.DataPacket.Kind.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
         * @param value The kind to set.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder setKind(im.turms.plugin.livekit.core.proto.models.DataPacket.Kind value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            kind_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder clearKind() {
            bitField0_ &= ~0x00000001;
            kind_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object participantIdentity_ = "";

        /**
         * <pre>
         * participant identity of user that sent the message
         * </pre>
         *
         * <code>string participant_identity = 4;</code>
         *
         * @return The participantIdentity.
         */
        public java.lang.String getParticipantIdentity() {
            java.lang.Object ref = participantIdentity_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                participantIdentity_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * participant identity of user that sent the message
         * </pre>
         *
         * <code>string participant_identity = 4;</code>
         *
         * @return The bytes for participantIdentity.
         */
        public com.google.protobuf.ByteString getParticipantIdentityBytes() {
            java.lang.Object ref = participantIdentity_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                participantIdentity_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * participant identity of user that sent the message
         * </pre>
         *
         * <code>string participant_identity = 4;</code>
         *
         * @param value The participantIdentity to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantIdentity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * participant identity of user that sent the message
         * </pre>
         *
         * <code>string participant_identity = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantIdentity() {
            participantIdentity_ = getDefaultInstance().getParticipantIdentity();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * participant identity of user that sent the message
         * </pre>
         *
         * <code>string participant_identity = 4;</code>
         *
         * @param value The bytes for participantIdentity to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantIdentityBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            participantIdentity_ = value;
            bitField0_ |= 0x00000002;
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
            bitField0_ |= 0x00000004;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @return A list containing the destinationIdentities.
         */
        public com.google.protobuf.ProtocolStringList getDestinationIdentitiesList() {
            destinationIdentities_.makeImmutable();
            return destinationIdentities_;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @return The count of destinationIdentities.
         */
        public int getDestinationIdentitiesCount() {
            return destinationIdentities_.size();
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @param index The index of the element to return.
         * @return The destinationIdentities at the given index.
         */
        public java.lang.String getDestinationIdentities(int index) {
            return destinationIdentities_.get(index);
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the destinationIdentities at the given index.
         */
        public com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index) {
            return destinationIdentities_.getByteString(index);
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
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
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
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
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @param values The destinationIdentities to add.
         * @return This builder for chaining.
         */
        public Builder addAllDestinationIdentities(java.lang.Iterable<java.lang.String> values) {
            ensureDestinationIdentitiesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, destinationIdentities_);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDestinationIdentities() {
            destinationIdentities_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * identities of participants who will receive the message (sent to all by default)
         * </pre>
         *
         * <code>repeated string destination_identities = 5;</code>
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
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.UserPacket, im.turms.plugin.livekit.core.proto.models.UserPacket.Builder, im.turms.plugin.livekit.core.proto.models.UserPacketOrBuilder> userBuilder_;

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         *
         * @return Whether the user field is set.
         */
        @java.lang.Override
        public boolean hasUser() {
            return valueCase_ == 2;
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         *
         * @return The user.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.UserPacket getUser() {
            if (userBuilder_ == null) {
                if (valueCase_ == 2) {
                    return (im.turms.plugin.livekit.core.proto.models.UserPacket) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.UserPacket.getDefaultInstance();
            } else {
                if (valueCase_ == 2) {
                    return userBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.models.UserPacket.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        public Builder setUser(im.turms.plugin.livekit.core.proto.models.UserPacket value) {
            if (userBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                value_ = value;
                onChanged();
            } else {
                userBuilder_.setMessage(value);
            }
            valueCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        public Builder setUser(
                im.turms.plugin.livekit.core.proto.models.UserPacket.Builder builderForValue) {
            if (userBuilder_ == null) {
                value_ = builderForValue.build();
                onChanged();
            } else {
                userBuilder_.setMessage(builderForValue.build());
            }
            valueCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        public Builder mergeUser(im.turms.plugin.livekit.core.proto.models.UserPacket value) {
            if (userBuilder_ == null) {
                if (valueCase_ == 2
                        && value_ != im.turms.plugin.livekit.core.proto.models.UserPacket
                                .getDefaultInstance()) {
                    value_ = im.turms.plugin.livekit.core.proto.models.UserPacket
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.models.UserPacket) value_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    value_ = value;
                }
                onChanged();
            } else {
                if (valueCase_ == 2) {
                    userBuilder_.mergeFrom(value);
                } else {
                    userBuilder_.setMessage(value);
                }
            }
            valueCase_ = 2;
            return this;
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        public Builder clearUser() {
            if (userBuilder_ == null) {
                if (valueCase_ == 2) {
                    valueCase_ = 0;
                    value_ = null;
                    onChanged();
                }
            } else {
                if (valueCase_ == 2) {
                    valueCase_ = 0;
                    value_ = null;
                }
                userBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.UserPacket.Builder getUserBuilder() {
            return getUserFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.UserPacketOrBuilder getUserOrBuilder() {
            if ((valueCase_ == 2) && (userBuilder_ != null)) {
                return userBuilder_.getMessageOrBuilder();
            } else {
                if (valueCase_ == 2) {
                    return (im.turms.plugin.livekit.core.proto.models.UserPacket) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.UserPacket.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.UserPacket user = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.UserPacket, im.turms.plugin.livekit.core.proto.models.UserPacket.Builder, im.turms.plugin.livekit.core.proto.models.UserPacketOrBuilder> getUserFieldBuilder() {
            if (userBuilder_ == null) {
                if (!(valueCase_ == 2)) {
                    value_ = im.turms.plugin.livekit.core.proto.models.UserPacket
                            .getDefaultInstance();
                }
                userBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.models.UserPacket) value_,
                        getParentForChildren(),
                        isClean());
                value_ = null;
            }
            valueCase_ = 2;
            onChanged();
            return userBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate, im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder, im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder> speakerBuilder_;

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
         * @return Whether the speaker field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasSpeaker() {
            return valueCase_ == 3;
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         *
         * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
         * @return The speaker.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getSpeaker() {
            if (speakerBuilder_ == null) {
                if (valueCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                        .getDefaultInstance();
            } else {
                if (valueCase_ == 3) {
                    return speakerBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSpeaker(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate value) {
            if (speakerBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                value_ = value;
                onChanged();
            } else {
                speakerBuilder_.setMessage(value);
            }
            valueCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSpeaker(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder builderForValue) {
            if (speakerBuilder_ == null) {
                value_ = builderForValue.build();
                onChanged();
            } else {
                speakerBuilder_.setMessage(builderForValue.build());
            }
            valueCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeSpeaker(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate value) {
            if (speakerBuilder_ == null) {
                if (valueCase_ == 3
                        && value_ != im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                                .getDefaultInstance()) {
                    value_ = im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    value_ = value;
                }
                onChanged();
            } else {
                if (valueCase_ == 3) {
                    speakerBuilder_.mergeFrom(value);
                } else {
                    speakerBuilder_.setMessage(value);
                }
            }
            valueCase_ = 3;
            return this;
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearSpeaker() {
            if (speakerBuilder_ == null) {
                if (valueCase_ == 3) {
                    valueCase_ = 0;
                    value_ = null;
                    onChanged();
                }
            } else {
                if (valueCase_ == 3) {
                    valueCase_ = 0;
                    value_ = null;
                }
                speakerBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder getSpeakerBuilder() {
            return getSpeakerFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder getSpeakerOrBuilder() {
            if ((valueCase_ == 3) && (speakerBuilder_ != null)) {
                return speakerBuilder_.getMessageOrBuilder();
            } else {
                if (valueCase_ == 3) {
                    return (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate, im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder, im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder> getSpeakerFieldBuilder() {
            if (speakerBuilder_ == null) {
                if (!(valueCase_ == 3)) {
                    value_ = im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                            .getDefaultInstance();
                }
                speakerBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) value_,
                        getParentForChildren(),
                        isClean());
                value_ = null;
            }
            valueCase_ = 3;
            onChanged();
            return speakerBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.SipDTMF, im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder, im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder> sipDtmfBuilder_;

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         *
         * @return Whether the sipDtmf field is set.
         */
        @java.lang.Override
        public boolean hasSipDtmf() {
            return valueCase_ == 6;
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         *
         * @return The sipDtmf.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SipDTMF getSipDtmf() {
            if (sipDtmfBuilder_ == null) {
                if (valueCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
            } else {
                if (valueCase_ == 6) {
                    return sipDtmfBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        public Builder setSipDtmf(im.turms.plugin.livekit.core.proto.models.SipDTMF value) {
            if (sipDtmfBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                value_ = value;
                onChanged();
            } else {
                sipDtmfBuilder_.setMessage(value);
            }
            valueCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        public Builder setSipDtmf(
                im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder builderForValue) {
            if (sipDtmfBuilder_ == null) {
                value_ = builderForValue.build();
                onChanged();
            } else {
                sipDtmfBuilder_.setMessage(builderForValue.build());
            }
            valueCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        public Builder mergeSipDtmf(im.turms.plugin.livekit.core.proto.models.SipDTMF value) {
            if (sipDtmfBuilder_ == null) {
                if (valueCase_ == 6
                        && value_ != im.turms.plugin.livekit.core.proto.models.SipDTMF
                                .getDefaultInstance()) {
                    value_ = im.turms.plugin.livekit.core.proto.models.SipDTMF
                            .newBuilder((im.turms.plugin.livekit.core.proto.models.SipDTMF) value_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    value_ = value;
                }
                onChanged();
            } else {
                if (valueCase_ == 6) {
                    sipDtmfBuilder_.mergeFrom(value);
                } else {
                    sipDtmfBuilder_.setMessage(value);
                }
            }
            valueCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        public Builder clearSipDtmf() {
            if (sipDtmfBuilder_ == null) {
                if (valueCase_ == 6) {
                    valueCase_ = 0;
                    value_ = null;
                    onChanged();
                }
            } else {
                if (valueCase_ == 6) {
                    valueCase_ = 0;
                    value_ = null;
                }
                sipDtmfBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder getSipDtmfBuilder() {
            return getSipDtmfFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder getSipDtmfOrBuilder() {
            if ((valueCase_ == 6) && (sipDtmfBuilder_ != null)) {
                return sipDtmfBuilder_.getMessageOrBuilder();
            } else {
                if (valueCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_;
                }
                return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.SipDTMF, im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder, im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder> getSipDtmfFieldBuilder() {
            if (sipDtmfBuilder_ == null) {
                if (!(valueCase_ == 6)) {
                    value_ = im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
                }
                sipDtmfBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.models.SipDTMF) value_,
                        getParentForChildren(),
                        isClean());
                value_ = null;
            }
            valueCase_ = 6;
            onChanged();
            return sipDtmfBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.DataPacket)
    }

    // @@protoc_insertion_point(class_scope:livekit.DataPacket)
    private static final im.turms.plugin.livekit.core.proto.models.DataPacket DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.DataPacket();
    }

    public static im.turms.plugin.livekit.core.proto.models.DataPacket getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DataPacket> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DataPacket parsePartialFrom(
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

    public static com.google.protobuf.Parser<DataPacket> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DataPacket> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.DataPacket getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}