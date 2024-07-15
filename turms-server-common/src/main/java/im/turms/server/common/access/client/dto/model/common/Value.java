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

package im.turms.server.common.access.client.dto.model.common;

/**
 * Protobuf type {@code im.turms.proto.Value}
 */
public final class Value extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Value)
        ValueOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                Value.class.getName());
    }

    // Use Value.newBuilder() to construct.
    private Value(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private Value() {
        listValue_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.common.ValueOuterClass.internal_static_im_turms_proto_Value_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.common.ValueOuterClass.internal_static_im_turms_proto_Value_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.common.Value.class,
                        im.turms.server.common.access.client.dto.model.common.Value.Builder.class);
    }

    private int kindCase_;
    @SuppressWarnings("serial")
    private java.lang.Object kind_;

    public enum KindCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        INT32_VALUE(1),
        INT64_VALUE(2),
        FLOAT_VALUE(3),
        DOUBLE_VALUE(4),
        BOOL_VALUE(5),
        BYTES_VALUE(6),
        STRING_VALUE(7),
        KIND_NOT_SET(0);

        private final int value;

        KindCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static KindCase valueOf(int value) {
            return forNumber(value);
        }

        public static KindCase forNumber(int value) {
            return switch (value) {
                case 1 -> INT32_VALUE;
                case 2 -> INT64_VALUE;
                case 3 -> FLOAT_VALUE;
                case 4 -> DOUBLE_VALUE;
                case 5 -> BOOL_VALUE;
                case 6 -> BYTES_VALUE;
                case 7 -> STRING_VALUE;
                case 0 -> KIND_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public KindCase getKindCase() {
        return KindCase.forNumber(kindCase_);
    }

    public static final int INT32_VALUE_FIELD_NUMBER = 1;

    /**
     * <code>int32 int32_value = 1;</code>
     *
     * @return Whether the int32Value field is set.
     */
    @java.lang.Override
    public boolean hasInt32Value() {
        return kindCase_ == 1;
    }

    /**
     * <code>int32 int32_value = 1;</code>
     *
     * @return The int32Value.
     */
    @java.lang.Override
    public int getInt32Value() {
        if (kindCase_ == 1) {
            return (java.lang.Integer) kind_;
        }
        return 0;
    }

    public static final int INT64_VALUE_FIELD_NUMBER = 2;

    /**
     * <code>int64 int64_value = 2;</code>
     *
     * @return Whether the int64Value field is set.
     */
    @java.lang.Override
    public boolean hasInt64Value() {
        return kindCase_ == 2;
    }

    /**
     * <code>int64 int64_value = 2;</code>
     *
     * @return The int64Value.
     */
    @java.lang.Override
    public long getInt64Value() {
        if (kindCase_ == 2) {
            return (java.lang.Long) kind_;
        }
        return 0L;
    }

    public static final int FLOAT_VALUE_FIELD_NUMBER = 3;

    /**
     * <code>float float_value = 3;</code>
     *
     * @return Whether the floatValue field is set.
     */
    @java.lang.Override
    public boolean hasFloatValue() {
        return kindCase_ == 3;
    }

    /**
     * <code>float float_value = 3;</code>
     *
     * @return The floatValue.
     */
    @java.lang.Override
    public float getFloatValue() {
        if (kindCase_ == 3) {
            return (java.lang.Float) kind_;
        }
        return 0.0F;
    }

    public static final int DOUBLE_VALUE_FIELD_NUMBER = 4;

    /**
     * <code>double double_value = 4;</code>
     *
     * @return Whether the doubleValue field is set.
     */
    @java.lang.Override
    public boolean hasDoubleValue() {
        return kindCase_ == 4;
    }

    /**
     * <code>double double_value = 4;</code>
     *
     * @return The doubleValue.
     */
    @java.lang.Override
    public double getDoubleValue() {
        if (kindCase_ == 4) {
            return (java.lang.Double) kind_;
        }
        return 0.0D;
    }

    public static final int BOOL_VALUE_FIELD_NUMBER = 5;

    /**
     * <code>bool bool_value = 5;</code>
     *
     * @return Whether the boolValue field is set.
     */
    @java.lang.Override
    public boolean hasBoolValue() {
        return kindCase_ == 5;
    }

    /**
     * <code>bool bool_value = 5;</code>
     *
     * @return The boolValue.
     */
    @java.lang.Override
    public boolean getBoolValue() {
        return kindCase_ == 5 && (Boolean) kind_;
    }

    public static final int BYTES_VALUE_FIELD_NUMBER = 6;

    /**
     * <code>bytes bytes_value = 6;</code>
     *
     * @return Whether the bytesValue field is set.
     */
    @java.lang.Override
    public boolean hasBytesValue() {
        return kindCase_ == 6;
    }

    /**
     * <code>bytes bytes_value = 6;</code>
     *
     * @return The bytesValue.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getBytesValue() {
        if (kindCase_ == 6) {
            return (com.google.protobuf.ByteString) kind_;
        }
        return com.google.protobuf.ByteString.EMPTY;
    }

    public static final int STRING_VALUE_FIELD_NUMBER = 7;

    /**
     * <code>string string_value = 7;</code>
     *
     * @return Whether the stringValue field is set.
     */
    public boolean hasStringValue() {
        return kindCase_ == 7;
    }

    /**
     * <code>string string_value = 7;</code>
     *
     * @return The stringValue.
     */
    public java.lang.String getStringValue() {
        java.lang.Object ref = "";
        if (kindCase_ == 7) {
            ref = kind_;
        }
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            if (kindCase_ == 7) {
                kind_ = s;
            }
            return s;
        }
    }

    /**
     * <code>string string_value = 7;</code>
     *
     * @return The bytes for stringValue.
     */
    public com.google.protobuf.ByteString getStringValueBytes() {
        java.lang.Object ref = "";
        if (kindCase_ == 7) {
            ref = kind_;
        }
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            if (kindCase_ == 7) {
                kind_ = b;
            }
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LIST_VALUE_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> listValue_;

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getListValueList() {
        return listValue_;
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getListValueOrBuilderList() {
        return listValue_;
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public int getListValueCount() {
        return listValue_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getListValue(int index) {
        return listValue_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getListValueOrBuilder(
            int index) {
        return listValue_.get(index);
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
        if (kindCase_ == 1) {
            output.writeInt32(1, (Integer) kind_);
        }
        if (kindCase_ == 2) {
            output.writeInt64(2, (Long) kind_);
        }
        if (kindCase_ == 3) {
            output.writeFloat(3, (Float) kind_);
        }
        if (kindCase_ == 4) {
            output.writeDouble(4, (Double) kind_);
        }
        if (kindCase_ == 5) {
            output.writeBool(5, (Boolean) kind_);
        }
        if (kindCase_ == 6) {
            output.writeBytes(6, (com.google.protobuf.ByteString) kind_);
        }
        if (kindCase_ == 7) {
            com.google.protobuf.GeneratedMessage.writeString(output, 7, kind_);
        }
        for (Value value : listValue_) {
            output.writeMessage(8, value);
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
        if (kindCase_ == 1) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, (Integer) kind_);
        }
        if (kindCase_ == 2) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, (Long) kind_);
        }
        if (kindCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeFloatSize(3, (Float) kind_);
        }
        if (kindCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(4, (Double) kind_);
        }
        if (kindCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(5, (Boolean) kind_);
        }
        if (kindCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(6,
                    (com.google.protobuf.ByteString) kind_);
        }
        if (kindCase_ == 7) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(7, kind_);
        }
        for (Value value : listValue_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8, value);
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
        if (!(obj instanceof Value other)) {
            return super.equals(obj);
        }

        if (!getListValueList().equals(other.getListValueList())) {
            return false;
        }
        if (!getKindCase().equals(other.getKindCase())) {
            return false;
        }
        switch (kindCase_) {
            case 1 -> {
                if (getInt32Value() != other.getInt32Value()) {
                    return false;
                }
            }
            case 2 -> {
                if (getInt64Value() != other.getInt64Value()) {
                    return false;
                }
            }
            case 3 -> {
                if (Float.floatToIntBits(getFloatValue()) != Float
                        .floatToIntBits(other.getFloatValue())) {
                    return false;
                }
            }
            case 4 -> {
                if (Double.doubleToLongBits(getDoubleValue()) != Double
                        .doubleToLongBits(other.getDoubleValue())) {
                    return false;
                }
            }
            case 5 -> {
                if (getBoolValue() != other.getBoolValue()) {
                    return false;
                }
            }
            case 6 -> {
                if (!getBytesValue().equals(other.getBytesValue())) {
                    return false;
                }
            }
            case 7 -> {
                if (!getStringValue().equals(other.getStringValue())) {
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
        if (getListValueCount() > 0) {
            hash = (37 * hash) + LIST_VALUE_FIELD_NUMBER;
            hash = (53 * hash) + getListValueList().hashCode();
        }
        switch (kindCase_) {
            case 1 -> {
                hash = (37 * hash) + INT32_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + getInt32Value();
            }
            case 2 -> {
                hash = (37 * hash) + INT64_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getInt64Value());
            }
            case 3 -> {
                hash = (37 * hash) + FLOAT_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + Float.floatToIntBits(getFloatValue());
            }
            case 4 -> {
                hash = (37 * hash) + DOUBLE_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + com.google.protobuf.Internal
                        .hashLong(Double.doubleToLongBits(getDoubleValue()));
            }
            case 5 -> {
                hash = (37 * hash) + BOOL_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getBoolValue());
            }
            case 6 -> {
                hash = (37 * hash) + BYTES_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + getBytesValue().hashCode();
            }
            case 7 -> {
                hash = (37 * hash) + STRING_VALUE_FIELD_NUMBER;
                hash = (53 * hash) + getStringValue().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.Value parseFrom(
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
            im.turms.server.common.access.client.dto.model.common.Value prototype) {
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
     * Protobuf type {@code im.turms.proto.Value}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Value)
            im.turms.server.common.access.client.dto.model.common.ValueOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.common.ValueOuterClass.internal_static_im_turms_proto_Value_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.common.ValueOuterClass.internal_static_im_turms_proto_Value_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.common.Value.class,
                            im.turms.server.common.access.client.dto.model.common.Value.Builder.class);
        }

        // Construct using im.turms.server.common.access.client.dto.model.common.Value.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (listValueBuilder_ == null) {
                listValue_ = java.util.Collections.emptyList();
            } else {
                listValue_ = null;
                listValueBuilder_.clear();
            }
            bitField0_ &= ~0x00000080;
            kindCase_ = 0;
            kind_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.common.ValueOuterClass.internal_static_im_turms_proto_Value_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.Value getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.common.Value.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.Value build() {
            im.turms.server.common.access.client.dto.model.common.Value result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.Value buildPartial() {
            im.turms.server.common.access.client.dto.model.common.Value result =
                    new im.turms.server.common.access.client.dto.model.common.Value(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.common.Value result) {
            if (listValueBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)) {
                    listValue_ = java.util.Collections.unmodifiableList(listValue_);
                    bitField0_ &= ~0x00000080;
                }
                result.listValue_ = listValue_;
            } else {
                result.listValue_ = listValueBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.common.Value result) {
            int from_bitField0_ = bitField0_;
        }

        private void buildPartialOneofs(
                im.turms.server.common.access.client.dto.model.common.Value result) {
            result.kindCase_ = kindCase_;
            result.kind_ = this.kind_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.common.Value) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.common.Value) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.common.Value other) {
            if (other == im.turms.server.common.access.client.dto.model.common.Value
                    .getDefaultInstance()) {
                return this;
            }
            if (listValueBuilder_ == null) {
                if (!other.listValue_.isEmpty()) {
                    if (listValue_.isEmpty()) {
                        listValue_ = other.listValue_;
                        bitField0_ &= ~0x00000080;
                    } else {
                        ensureListValueIsMutable();
                        listValue_.addAll(other.listValue_);
                    }
                    onChanged();
                }
            } else {
                if (!other.listValue_.isEmpty()) {
                    if (listValueBuilder_.isEmpty()) {
                        listValueBuilder_.dispose();
                        listValueBuilder_ = null;
                        listValue_ = other.listValue_;
                        bitField0_ &= ~0x00000080;
                        listValueBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getListValueFieldBuilder()
                                        : null;
                    } else {
                        listValueBuilder_.addAllMessages(other.listValue_);
                    }
                }
            }
            switch (other.getKindCase()) {
                case INT32_VALUE -> {
                    setInt32Value(other.getInt32Value());
                }
                case INT64_VALUE -> {
                    setInt64Value(other.getInt64Value());
                }
                case FLOAT_VALUE -> {
                    setFloatValue(other.getFloatValue());
                }
                case DOUBLE_VALUE -> {
                    setDoubleValue(other.getDoubleValue());
                }
                case BOOL_VALUE -> {
                    setBoolValue(other.getBoolValue());
                }
                case BYTES_VALUE -> {
                    setBytesValue(other.getBytesValue());
                }
                case STRING_VALUE -> {
                    kindCase_ = 7;
                    kind_ = other.kind_;
                    onChanged();
                }
                case KIND_NOT_SET -> {
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
                            kind_ = input.readInt32();
                            kindCase_ = 1;
                        } // case 8
                        case 16 -> {
                            kind_ = input.readInt64();
                            kindCase_ = 2;
                        } // case 16
                        case 29 -> {
                            kind_ = input.readFloat();
                            kindCase_ = 3;
                        } // case 29
                        case 33 -> {
                            kind_ = input.readDouble();
                            kindCase_ = 4;
                        } // case 33
                        case 40 -> {
                            kind_ = input.readBool();
                            kindCase_ = 5;
                        } // case 40
                        case 50 -> {
                            kind_ = input.readBytes();
                            kindCase_ = 6;
                        } // case 50
                        case 58 -> {
                            String s = input.readStringRequireUtf8();
                            kindCase_ = 7;
                            kind_ = s;
                        } // case 58
                        case 66 -> {
                            Value m = input.readMessage(Value.parser(), extensionRegistry);
                            if (listValueBuilder_ == null) {
                                ensureListValueIsMutable();
                                listValue_.add(m);
                            } else {
                                listValueBuilder_.addMessage(m);
                            }
                        } // case 66
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

        private int kindCase_;
        private java.lang.Object kind_;

        public KindCase getKindCase() {
            return KindCase.forNumber(kindCase_);
        }

        public Builder clearKind() {
            kindCase_ = 0;
            kind_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return Whether the int32Value field is set.
         */
        public boolean hasInt32Value() {
            return kindCase_ == 1;
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return The int32Value.
         */
        public int getInt32Value() {
            if (kindCase_ == 1) {
                return (java.lang.Integer) kind_;
            }
            return 0;
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @param value The int32Value to set.
         * @return This builder for chaining.
         */
        public Builder setInt32Value(int value) {

            kindCase_ = 1;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInt32Value() {
            if (kindCase_ == 1) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return Whether the int64Value field is set.
         */
        public boolean hasInt64Value() {
            return kindCase_ == 2;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return The int64Value.
         */
        public long getInt64Value() {
            if (kindCase_ == 2) {
                return (java.lang.Long) kind_;
            }
            return 0L;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @param value The int64Value to set.
         * @return This builder for chaining.
         */
        public Builder setInt64Value(long value) {

            kindCase_ = 2;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInt64Value() {
            if (kindCase_ == 2) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return Whether the floatValue field is set.
         */
        public boolean hasFloatValue() {
            return kindCase_ == 3;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return The floatValue.
         */
        public float getFloatValue() {
            if (kindCase_ == 3) {
                return (java.lang.Float) kind_;
            }
            return 0.0F;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @param value The floatValue to set.
         * @return This builder for chaining.
         */
        public Builder setFloatValue(float value) {

            kindCase_ = 3;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFloatValue() {
            if (kindCase_ == 3) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return Whether the doubleValue field is set.
         */
        public boolean hasDoubleValue() {
            return kindCase_ == 4;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return The doubleValue.
         */
        public double getDoubleValue() {
            if (kindCase_ == 4) {
                return (java.lang.Double) kind_;
            }
            return 0.0D;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @param value The doubleValue to set.
         * @return This builder for chaining.
         */
        public Builder setDoubleValue(double value) {

            kindCase_ = 4;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDoubleValue() {
            if (kindCase_ == 4) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return Whether the boolValue field is set.
         */
        public boolean hasBoolValue() {
            return kindCase_ == 5;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return The boolValue.
         */
        public boolean getBoolValue() {
            return kindCase_ == 5 && (Boolean) kind_;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @param value The boolValue to set.
         * @return This builder for chaining.
         */
        public Builder setBoolValue(boolean value) {

            kindCase_ = 5;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBoolValue() {
            if (kindCase_ == 5) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return Whether the bytesValue field is set.
         */
        public boolean hasBytesValue() {
            return kindCase_ == 6;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return The bytesValue.
         */
        public com.google.protobuf.ByteString getBytesValue() {
            if (kindCase_ == 6) {
                return (com.google.protobuf.ByteString) kind_;
            }
            return com.google.protobuf.ByteString.EMPTY;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @param value The bytesValue to set.
         * @return This builder for chaining.
         */
        public Builder setBytesValue(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            kindCase_ = 6;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBytesValue() {
            if (kindCase_ == 6) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return Whether the stringValue field is set.
         */
        @java.lang.Override
        public boolean hasStringValue() {
            return kindCase_ == 7;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return The stringValue.
         */
        @java.lang.Override
        public java.lang.String getStringValue() {
            java.lang.Object ref = "";
            if (kindCase_ == 7) {
                ref = kind_;
            }
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                if (kindCase_ == 7) {
                    kind_ = s;
                }
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return The bytes for stringValue.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getStringValueBytes() {
            java.lang.Object ref = "";
            if (kindCase_ == 7) {
                ref = kind_;
            }
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                if (kindCase_ == 7) {
                    kind_ = b;
                }
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @param value The stringValue to set.
         * @return This builder for chaining.
         */
        public Builder setStringValue(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            kindCase_ = 7;
            kind_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStringValue() {
            if (kindCase_ == 7) {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
            }
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @param value The bytes for stringValue to set.
         * @return This builder for chaining.
         */
        public Builder setStringValueBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            kindCase_ = 7;
            kind_ = value;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> listValue_ =
                java.util.Collections.emptyList();

        private void ensureListValueIsMutable() {
            if ((bitField0_ & 0x00000080) == 0) {
                listValue_ = new java.util.ArrayList<>(listValue_);
                bitField0_ |= 0x00000080;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> listValueBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getListValueList() {
            if (listValueBuilder_ == null) {
                return java.util.Collections.unmodifiableList(listValue_);
            } else {
                return listValueBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public int getListValueCount() {
            if (listValueBuilder_ == null) {
                return listValue_.size();
            } else {
                return listValueBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getListValue(int index) {
            if (listValueBuilder_ == null) {
                return listValue_.get(index);
            } else {
                return listValueBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder setListValue(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (listValueBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureListValueIsMutable();
                listValue_.set(index, value);
                onChanged();
            } else {
                listValueBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder setListValue(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (listValueBuilder_ == null) {
                ensureListValueIsMutable();
                listValue_.set(index, builderForValue.build());
                onChanged();
            } else {
                listValueBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (listValueBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureListValueIsMutable();
                listValue_.add(value);
                onChanged();
            } else {
                listValueBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (listValueBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureListValueIsMutable();
                listValue_.add(index, value);
                onChanged();
            } else {
                listValueBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (listValueBuilder_ == null) {
                ensureListValueIsMutable();
                listValue_.add(builderForValue.build());
                onChanged();
            } else {
                listValueBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (listValueBuilder_ == null) {
                ensureListValueIsMutable();
                listValue_.add(index, builderForValue.build());
                onChanged();
            } else {
                listValueBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addAllListValue(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (listValueBuilder_ == null) {
                ensureListValueIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, listValue_);
                onChanged();
            } else {
                listValueBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder clearListValue() {
            if (listValueBuilder_ == null) {
                listValue_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000080;
                onChanged();
            } else {
                listValueBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder removeListValue(int index) {
            if (listValueBuilder_ == null) {
                ensureListValueIsMutable();
                listValue_.remove(index);
                onChanged();
            } else {
                listValueBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getListValueBuilder(
                int index) {
            return getListValueFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getListValueOrBuilder(
                int index) {
            if (listValueBuilder_ == null) {
                return listValue_.get(index);
            } else {
                return listValueBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getListValueOrBuilderList() {
            if (listValueBuilder_ != null) {
                return listValueBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(listValue_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addListValueBuilder() {
            return getListValueFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addListValueBuilder(
                int index) {
            return getListValueFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getListValueBuilderList() {
            return getListValueFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getListValueFieldBuilder() {
            if (listValueBuilder_ == null) {
                listValueBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        listValue_,
                        ((bitField0_ & 0x00000080) != 0),
                        getParentForChildren(),
                        isClean());
                listValue_ = null;
            }
            return listValueBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Value)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Value)
    private static final im.turms.server.common.access.client.dto.model.common.Value DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.common.Value();
    }

    public static im.turms.server.common.access.client.dto.model.common.Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Value> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Value parsePartialFrom(
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

    public static com.google.protobuf.Parser<Value> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Value> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}