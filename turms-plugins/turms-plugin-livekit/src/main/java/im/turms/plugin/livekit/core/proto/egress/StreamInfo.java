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
 * Protobuf type {@code livekit.StreamInfo}
 */
public final class StreamInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.StreamInfo)
        StreamInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                StreamInfo.class.getName());
    }

    // Use StreamInfo.newBuilder() to construct.
    private StreamInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private StreamInfo() {
        url_ = "";
        status_ = 0;
        error_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.StreamInfo.class,
                        im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.StreamInfo.Status}
     */
    public enum Status implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <code>ACTIVE = 0;</code>
         */
        ACTIVE(0),
        /**
         * <code>FINISHED = 1;</code>
         */
        FINISHED(1),
        /**
         * <code>FAILED = 2;</code>
         */
        FAILED(2),
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
         * <code>ACTIVE = 0;</code>
         */
        public static final int ACTIVE_VALUE = 0;
        /**
         * <code>FINISHED = 1;</code>
         */
        public static final int FINISHED_VALUE = 1;
        /**
         * <code>FAILED = 2;</code>
         */
        public static final int FAILED_VALUE = 2;

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
                case 0 -> ACTIVE;
                case 1 -> FINISHED;
                case 2 -> FAILED;
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
            return im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDescriptor()
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

        // @@protoc_insertion_point(enum_scope:livekit.StreamInfo.Status)
    }

    public static final int URL_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object url_ = "";

    /**
     * <code>string url = 1;</code>
     *
     * @return The url.
     */
    @java.lang.Override
    public java.lang.String getUrl() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            url_ = s;
            return s;
        }
    }

    /**
     * <code>string url = 1;</code>
     *
     * @return The bytes for url.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUrlBytes() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            url_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int STARTED_AT_FIELD_NUMBER = 2;
    private long startedAt_ = 0L;

    /**
     * <code>int64 started_at = 2;</code>
     *
     * @return The startedAt.
     */
    @java.lang.Override
    public long getStartedAt() {
        return startedAt_;
    }

    public static final int ENDED_AT_FIELD_NUMBER = 3;
    private long endedAt_ = 0L;

    /**
     * <code>int64 ended_at = 3;</code>
     *
     * @return The endedAt.
     */
    @java.lang.Override
    public long getEndedAt() {
        return endedAt_;
    }

    public static final int DURATION_FIELD_NUMBER = 4;
    private long duration_ = 0L;

    /**
     * <code>int64 duration = 4;</code>
     *
     * @return The duration.
     */
    @java.lang.Override
    public long getDuration() {
        return duration_;
    }

    public static final int STATUS_FIELD_NUMBER = 5;
    private int status_ = 0;

    /**
     * <code>.livekit.StreamInfo.Status status = 5;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override
    public int getStatusValue() {
        return status_;
    }

    /**
     * <code>.livekit.StreamInfo.Status status = 5;</code>
     *
     * @return The status.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status getStatus() {
        im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status result =
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.forNumber(status_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.UNRECOGNIZED
                : result;
    }

    public static final int ERROR_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object error_ = "";

    /**
     * <code>string error = 6;</code>
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
     * <code>string error = 6;</code>
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, url_);
        }
        if (startedAt_ != 0L) {
            output.writeInt64(2, startedAt_);
        }
        if (endedAt_ != 0L) {
            output.writeInt64(3, endedAt_);
        }
        if (duration_ != 0L) {
            output.writeInt64(4, duration_);
        }
        if (status_ != im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.ACTIVE
                .getNumber()) {
            output.writeEnum(5, status_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, error_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, url_);
        }
        if (startedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, startedAt_);
        }
        if (endedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, endedAt_);
        }
        if (duration_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, duration_);
        }
        if (status_ != im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.ACTIVE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, status_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(error_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, error_);
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
        if (!(obj instanceof StreamInfo other)) {
            return super.equals(obj);
        }

        if (!getUrl().equals(other.getUrl())) {
            return false;
        }
        if (getStartedAt() != other.getStartedAt()) {
            return false;
        }
        if (getEndedAt() != other.getEndedAt()) {
            return false;
        }
        if (getDuration() != other.getDuration()) {
            return false;
        }
        if (status_ != other.status_) {
            return false;
        }
        if (!getError().equals(other.getError())) {
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
        hash = (37 * hash) + URL_FIELD_NUMBER;
        hash = (53 * hash) + getUrl().hashCode();
        hash = (37 * hash) + STARTED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartedAt());
        hash = (37 * hash) + ENDED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndedAt());
        hash = (37 * hash) + DURATION_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDuration());
        hash = (37 * hash) + STATUS_FIELD_NUMBER;
        hash = (53 * hash) + status_;
        hash = (37 * hash) + ERROR_FIELD_NUMBER;
        hash = (53 * hash) + getError().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.StreamInfo prototype) {
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
     * Protobuf type {@code livekit.StreamInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.StreamInfo)
            im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.StreamInfo.class,
                            im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.StreamInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            url_ = "";
            startedAt_ = 0L;
            endedAt_ = 0L;
            duration_ = 0L;
            status_ = 0;
            error_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo build() {
            im.turms.plugin.livekit.core.proto.egress.StreamInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.StreamInfo result =
                    new im.turms.plugin.livekit.core.proto.egress.StreamInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.StreamInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.url_ = url_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.startedAt_ = startedAt_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.endedAt_ = endedAt_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.duration_ = duration_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.status_ = status_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.error_ = error_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.StreamInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.StreamInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.StreamInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.StreamInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getUrl()
                    .isEmpty()) {
                url_ = other.url_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getStartedAt() != 0L) {
                setStartedAt(other.getStartedAt());
            }
            if (other.getEndedAt() != 0L) {
                setEndedAt(other.getEndedAt());
            }
            if (other.getDuration() != 0L) {
                setDuration(other.getDuration());
            }
            if (other.status_ != 0) {
                setStatusValue(other.getStatusValue());
            }
            if (!other.getError()
                    .isEmpty()) {
                error_ = other.error_;
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
                            url_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            startedAt_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            endedAt_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            duration_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            status_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 50 -> {
                            error_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
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

        private java.lang.Object url_ = "";

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        public com.google.protobuf.ByteString getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The url to set.
         * @return This builder for chaining.
         */
        public Builder setUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUrl() {
            url_ = getDefaultInstance().getUrl();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The bytes for url to set.
         * @return This builder for chaining.
         */
        public Builder setUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private long startedAt_;

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @return The startedAt.
         */
        @java.lang.Override
        public long getStartedAt() {
            return startedAt_;
        }

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @param value The startedAt to set.
         * @return This builder for chaining.
         */
        public Builder setStartedAt(long value) {

            startedAt_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartedAt() {
            bitField0_ &= ~0x00000002;
            startedAt_ = 0L;
            onChanged();
            return this;
        }

        private long endedAt_;

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @return The endedAt.
         */
        @java.lang.Override
        public long getEndedAt() {
            return endedAt_;
        }

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @param value The endedAt to set.
         * @return This builder for chaining.
         */
        public Builder setEndedAt(long value) {

            endedAt_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndedAt() {
            bitField0_ &= ~0x00000004;
            endedAt_ = 0L;
            onChanged();
            return this;
        }

        private long duration_;

        /**
         * <code>int64 duration = 4;</code>
         *
         * @return The duration.
         */
        @java.lang.Override
        public long getDuration() {
            return duration_;
        }

        /**
         * <code>int64 duration = 4;</code>
         *
         * @param value The duration to set.
         * @return This builder for chaining.
         */
        public Builder setDuration(long value) {

            duration_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>int64 duration = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDuration() {
            bitField0_ &= ~0x00000008;
            duration_ = 0L;
            onChanged();
            return this;
        }

        private int status_ = 0;

        /**
         * <code>.livekit.StreamInfo.Status status = 5;</code>
         *
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }

        /**
         * <code>.livekit.StreamInfo.Status status = 5;</code>
         *
         * @param value The enum numeric value on the wire for status to set.
         * @return This builder for chaining.
         */
        public Builder setStatusValue(int value) {
            status_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.StreamInfo.Status status = 5;</code>
         *
         * @return The status.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status getStatus() {
            im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status result =
                    im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.forNumber(status_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.StreamInfo.Status status = 5;</code>
         *
         * @param value The status to set.
         * @return This builder for chaining.
         */
        public Builder setStatus(
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            status_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.StreamInfo.Status status = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStatus() {
            bitField0_ &= ~0x00000010;
            status_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object error_ = "";

        /**
         * <code>string error = 6;</code>
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
         * <code>string error = 6;</code>
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
         * <code>string error = 6;</code>
         *
         * @param value The error to set.
         * @return This builder for chaining.
         */
        public Builder setError(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            error_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string error = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearError() {
            error_ = getDefaultInstance().getError();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string error = 6;</code>
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
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.StreamInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.StreamInfo)
    private static final im.turms.plugin.livekit.core.proto.egress.StreamInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.StreamInfo();
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StreamInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public StreamInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<StreamInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StreamInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}