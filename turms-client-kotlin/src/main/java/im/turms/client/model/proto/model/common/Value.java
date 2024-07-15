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

package im.turms.client.model.proto.model.common;

/**
 * Protobuf type {@code im.turms.proto.Value}
 */
public final class Value extends com.google.protobuf.GeneratedMessageLite<Value, Value.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Value)
        ValueOrBuilder {
    private Value() {
        listValue_ = emptyProtobufList();
    }

    private int kindCase_ = 0;
    private java.lang.Object kind_;

    public enum KindCase {
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
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static KindCase valueOf(int value) {
            return forNumber(value);
        }

        public static KindCase forNumber(int value) {
            switch (value) {
                case 1:
                    return INT32_VALUE;
                case 2:
                    return INT64_VALUE;
                case 3:
                    return FLOAT_VALUE;
                case 4:
                    return DOUBLE_VALUE;
                case 5:
                    return BOOL_VALUE;
                case 6:
                    return BYTES_VALUE;
                case 7:
                    return STRING_VALUE;
                case 0:
                    return KIND_NOT_SET;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @java.lang.Override
    public KindCase getKindCase() {
        return KindCase.forNumber(kindCase_);
    }

    private void clearKind() {
        kindCase_ = 0;
        kind_ = null;
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

    /**
     * <code>int32 int32_value = 1;</code>
     *
     * @param value The int32Value to set.
     */
    private void setInt32Value(int value) {
        kindCase_ = 1;
        kind_ = value;
    }

    /**
     * <code>int32 int32_value = 1;</code>
     */
    private void clearInt32Value() {
        if (kindCase_ == 1) {
            kindCase_ = 0;
            kind_ = null;
        }
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

    /**
     * <code>int64 int64_value = 2;</code>
     *
     * @param value The int64Value to set.
     */
    private void setInt64Value(long value) {
        kindCase_ = 2;
        kind_ = value;
    }

    /**
     * <code>int64 int64_value = 2;</code>
     */
    private void clearInt64Value() {
        if (kindCase_ == 2) {
            kindCase_ = 0;
            kind_ = null;
        }
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
        return 0F;
    }

    /**
     * <code>float float_value = 3;</code>
     *
     * @param value The floatValue to set.
     */
    private void setFloatValue(float value) {
        kindCase_ = 3;
        kind_ = value;
    }

    /**
     * <code>float float_value = 3;</code>
     */
    private void clearFloatValue() {
        if (kindCase_ == 3) {
            kindCase_ = 0;
            kind_ = null;
        }
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
        return 0D;
    }

    /**
     * <code>double double_value = 4;</code>
     *
     * @param value The doubleValue to set.
     */
    private void setDoubleValue(double value) {
        kindCase_ = 4;
        kind_ = value;
    }

    /**
     * <code>double double_value = 4;</code>
     */
    private void clearDoubleValue() {
        if (kindCase_ == 4) {
            kindCase_ = 0;
            kind_ = null;
        }
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
        if (kindCase_ == 5) {
            return (java.lang.Boolean) kind_;
        }
        return false;
    }

    /**
     * <code>bool bool_value = 5;</code>
     *
     * @param value The boolValue to set.
     */
    private void setBoolValue(boolean value) {
        kindCase_ = 5;
        kind_ = value;
    }

    /**
     * <code>bool bool_value = 5;</code>
     */
    private void clearBoolValue() {
        if (kindCase_ == 5) {
            kindCase_ = 0;
            kind_ = null;
        }
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

    /**
     * <code>bytes bytes_value = 6;</code>
     *
     * @param value The bytesValue to set.
     */
    private void setBytesValue(com.google.protobuf.ByteString value) {
        java.lang.Class<?> valueClass = value.getClass();
        kindCase_ = 6;
        kind_ = value;
    }

    /**
     * <code>bytes bytes_value = 6;</code>
     */
    private void clearBytesValue() {
        if (kindCase_ == 6) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int STRING_VALUE_FIELD_NUMBER = 7;

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
        java.lang.String ref = "";
        if (kindCase_ == 7) {
            ref = (java.lang.String) kind_;
        }
        return ref;
    }

    /**
     * <code>string string_value = 7;</code>
     *
     * @return The bytes for stringValue.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getStringValueBytes() {
        java.lang.String ref = "";
        if (kindCase_ == 7) {
            ref = (java.lang.String) kind_;
        }
        return com.google.protobuf.ByteString.copyFromUtf8(ref);
    }

    /**
     * <code>string string_value = 7;</code>
     *
     * @param value The stringValue to set.
     */
    private void setStringValue(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        kindCase_ = 7;
        kind_ = value;
    }

    /**
     * <code>string string_value = 7;</code>
     */
    private void clearStringValue() {
        if (kindCase_ == 7) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    /**
     * <code>string string_value = 7;</code>
     *
     * @param value The bytes for stringValue to set.
     */
    private void setStringValueBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        kind_ = value.toStringUtf8();
        kindCase_ = 7;
    }

    public static final int LIST_VALUE_FIELD_NUMBER = 8;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> listValue_;

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getListValueList() {
        return listValue_;
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getListValueOrBuilderList() {
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
    public im.turms.client.model.proto.model.common.Value getListValue(int index) {
        return listValue_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getListValueOrBuilder(
            int index) {
        return listValue_.get(index);
    }

    private void ensureListValueIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                listValue_;
        if (!tmp.isModifiable()) {
            listValue_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void setListValue(int index, im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureListValueIsMutable();
        listValue_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void addListValue(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureListValueIsMutable();
        listValue_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void addListValue(int index, im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureListValueIsMutable();
        listValue_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void addAllListValue(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureListValueIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, listValue_);
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void clearListValue() {
        listValue_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    private void removeListValue(int index) {
        ensureListValueIsMutable();
        listValue_.remove(index);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.Value parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.Value parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.Value parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.common.Value prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.Value}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.common.Value, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Value)
            im.turms.client.model.proto.model.common.ValueOrBuilder {
        // Construct using im.turms.client.model.proto.model.common.Value.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        @java.lang.Override
        public KindCase getKindCase() {
            return instance.getKindCase();
        }

        public Builder clearKind() {
            copyOnWrite();
            instance.clearKind();
            return this;
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return Whether the int32Value field is set.
         */
        @java.lang.Override
        public boolean hasInt32Value() {
            return instance.hasInt32Value();
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return The int32Value.
         */
        @java.lang.Override
        public int getInt32Value() {
            return instance.getInt32Value();
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @param value The int32Value to set.
         * @return This builder for chaining.
         */
        public Builder setInt32Value(int value) {
            copyOnWrite();
            instance.setInt32Value(value);
            return this;
        }

        /**
         * <code>int32 int32_value = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInt32Value() {
            copyOnWrite();
            instance.clearInt32Value();
            return this;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return Whether the int64Value field is set.
         */
        @java.lang.Override
        public boolean hasInt64Value() {
            return instance.hasInt64Value();
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return The int64Value.
         */
        @java.lang.Override
        public long getInt64Value() {
            return instance.getInt64Value();
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @param value The int64Value to set.
         * @return This builder for chaining.
         */
        public Builder setInt64Value(long value) {
            copyOnWrite();
            instance.setInt64Value(value);
            return this;
        }

        /**
         * <code>int64 int64_value = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInt64Value() {
            copyOnWrite();
            instance.clearInt64Value();
            return this;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return Whether the floatValue field is set.
         */
        @java.lang.Override
        public boolean hasFloatValue() {
            return instance.hasFloatValue();
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return The floatValue.
         */
        @java.lang.Override
        public float getFloatValue() {
            return instance.getFloatValue();
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @param value The floatValue to set.
         * @return This builder for chaining.
         */
        public Builder setFloatValue(float value) {
            copyOnWrite();
            instance.setFloatValue(value);
            return this;
        }

        /**
         * <code>float float_value = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFloatValue() {
            copyOnWrite();
            instance.clearFloatValue();
            return this;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return Whether the doubleValue field is set.
         */
        @java.lang.Override
        public boolean hasDoubleValue() {
            return instance.hasDoubleValue();
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return The doubleValue.
         */
        @java.lang.Override
        public double getDoubleValue() {
            return instance.getDoubleValue();
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @param value The doubleValue to set.
         * @return This builder for chaining.
         */
        public Builder setDoubleValue(double value) {
            copyOnWrite();
            instance.setDoubleValue(value);
            return this;
        }

        /**
         * <code>double double_value = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDoubleValue() {
            copyOnWrite();
            instance.clearDoubleValue();
            return this;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return Whether the boolValue field is set.
         */
        @java.lang.Override
        public boolean hasBoolValue() {
            return instance.hasBoolValue();
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return The boolValue.
         */
        @java.lang.Override
        public boolean getBoolValue() {
            return instance.getBoolValue();
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @param value The boolValue to set.
         * @return This builder for chaining.
         */
        public Builder setBoolValue(boolean value) {
            copyOnWrite();
            instance.setBoolValue(value);
            return this;
        }

        /**
         * <code>bool bool_value = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBoolValue() {
            copyOnWrite();
            instance.clearBoolValue();
            return this;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return Whether the bytesValue field is set.
         */
        @java.lang.Override
        public boolean hasBytesValue() {
            return instance.hasBytesValue();
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return The bytesValue.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getBytesValue() {
            return instance.getBytesValue();
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @param value The bytesValue to set.
         * @return This builder for chaining.
         */
        public Builder setBytesValue(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setBytesValue(value);
            return this;
        }

        /**
         * <code>bytes bytes_value = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBytesValue() {
            copyOnWrite();
            instance.clearBytesValue();
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return Whether the stringValue field is set.
         */
        @java.lang.Override
        public boolean hasStringValue() {
            return instance.hasStringValue();
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return The stringValue.
         */
        @java.lang.Override
        public java.lang.String getStringValue() {
            return instance.getStringValue();
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return The bytes for stringValue.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getStringValueBytes() {
            return instance.getStringValueBytes();
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @param value The stringValue to set.
         * @return This builder for chaining.
         */
        public Builder setStringValue(java.lang.String value) {
            copyOnWrite();
            instance.setStringValue(value);
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStringValue() {
            copyOnWrite();
            instance.clearStringValue();
            return this;
        }

        /**
         * <code>string string_value = 7;</code>
         *
         * @param value The bytes for stringValue to set.
         * @return This builder for chaining.
         */
        public Builder setStringValueBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setStringValueBytes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getListValueList() {
            return java.util.Collections.unmodifiableList(instance.getListValueList());
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        @java.lang.Override
        public int getListValueCount() {
            return instance.getListValueCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getListValue(int index) {
            return instance.getListValue(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder setListValue(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setListValue(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder setListValue(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setListValue(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addListValue(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addListValue(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addListValue(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addListValue(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addListValue(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder addAllListValue(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllListValue(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder clearListValue() {
            copyOnWrite();
            instance.clearListValue();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value list_value = 8;</code>
         */
        public Builder removeListValue(int index) {
            copyOnWrite();
            instance.removeListValue(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Value)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.common.Value();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"kind_",
                        "kindCase_",
                        "listValue_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\b\u0001\u0000\u0001\b\b\u0000\u0001\u0000\u00017\u0000\u00025\u0000\u0003"
                                + "4\u0000\u00043\u0000\u0005:\u0000\u0006=\u0000\u0007\u023b\u0000\b\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.common.Value> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.common.Value.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            }
            case GET_MEMOIZED_IS_INITIALIZED: {
                return (byte) 1;
            }
            case SET_MEMOIZED_IS_INITIALIZED: {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Value)
    private static final im.turms.client.model.proto.model.common.Value DEFAULT_INSTANCE;
    static {
        Value defaultInstance = new Value();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Value.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.common.Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Value> PARSER;

    public static com.google.protobuf.Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}