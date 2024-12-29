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

package im.turms.server.common.access.client.dto.model.user;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.WireFormat;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.client.dto.model.common.ValueOrBuilder;

/**
 * Protobuf type {@code im.turms.proto.UserLocation}
 */
public final class UserLocation extends GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserLocation)
        UserLocationOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                UserLocation.class.getName());
    }

    // Use UserLocation.newBuilder() to construct.
    private UserLocation(GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserLocation() {
        customAttributes_ = Collections.emptyList();
    }

    public static Descriptors.Descriptor getDescriptor() {
        return UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
        if (number == 4) {
            return internalGetDetails();
        }
        throw new RuntimeException(
                "Invalid map field number: "
                        + number);
    }

    @Override
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_fieldAccessorTable
                .ensureFieldAccessorsInitialized(UserLocation.class, UserLocation.Builder.class);
    }

    private int bitField0_;
    public static final int LATITUDE_FIELD_NUMBER = 1;
    private float latitude_ = 0F;

    /**
     * <code>float latitude = 1;</code>
     *
     * @return The latitude.
     */
    @Override
    public float getLatitude() {
        return latitude_;
    }

    public static final int LONGITUDE_FIELD_NUMBER = 2;
    private float longitude_ = 0F;

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    @Override
    public float getLongitude() {
        return longitude_;
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private long timestamp_ = 0L;

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return Whether the timestamp field is set.
     */
    @Override
    public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return The timestamp.
     */
    @Override
    public long getTimestamp() {
        return timestamp_;
    }

    public static final int DETAILS_FIELD_NUMBER = 4;

    private static final class DetailsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(
                UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_DetailsEntry_descriptor,
                WireFormat.FieldType.STRING,
                "",
                WireFormat.FieldType.STRING,
                "");
    }

    @SuppressWarnings("serial")
    private MapField<String, String> details_;

    private MapField<String, String> internalGetDetails() {
        if (details_ == null) {
            return MapField.emptyMapField(DetailsDefaultEntryHolder.defaultEntry);
        }
        return details_;
    }

    public int getDetailsCount() {
        return internalGetDetails().getMap()
                .size();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @Override
    public boolean containsDetails(String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetDetails().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getDetailsMap()} instead.
     */
    @Override
    @Deprecated
    public Map<String, String> getDetails() {
        return getDetailsMap();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @Override
    public Map<String, String> getDetailsMap() {
        return internalGetDetails().getMap();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @Override
    public /* nullable */
    String getDetailsOrDefault(
            String key,
            /* nullable */
            String defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetDetails().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @Override
    public String getDetailsOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        Map<String, String> map = internalGetDetails().getMap();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private List<Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @Override
    public List<Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @Override
    public List<? extends ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @Override
    public Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @Override
    public ValueOrBuilder getCustomAttributesOrBuilder(int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @Override
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

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (Float.floatToRawIntBits(latitude_) != 0) {
            output.writeFloat(1, latitude_);
        }
        if (Float.floatToRawIntBits(longitude_) != 0) {
            output.writeFloat(2, longitude_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(3, timestamp_);
        }
        GeneratedMessage.serializeStringMapTo(output,
                internalGetDetails(),
                DetailsDefaultEntryHolder.defaultEntry,
                4);
        for (Value value : customAttributes_) {
            output.writeMessage(15, value);
        }
        getUnknownFields().writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (Float.floatToRawIntBits(latitude_) != 0) {
            size += CodedOutputStream.computeFloatSize(1, latitude_);
        }
        if (Float.floatToRawIntBits(longitude_) != 0) {
            size += CodedOutputStream.computeFloatSize(2, longitude_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += CodedOutputStream.computeInt64Size(3, timestamp_);
        }
        for (Map.Entry<String, String> entry : internalGetDetails().getMap()
                .entrySet()) {
            MapEntry<String, String> details__ =
                    DetailsDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += CodedOutputStream.computeMessageSize(4, details__);
        }
        for (Value value : customAttributes_) {
            size += CodedOutputStream.computeMessageSize(15, value);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserLocation other)) {
            return super.equals(obj);
        }

        if (Float.floatToIntBits(getLatitude()) != Float.floatToIntBits(other.getLatitude())) {
            return false;
        }
        if (Float.floatToIntBits(getLongitude()) != Float.floatToIntBits(other.getLongitude())) {
            return false;
        }
        if (hasTimestamp() != other.hasTimestamp()) {
            return false;
        }
        if (hasTimestamp()) {
            if (getTimestamp() != other.getTimestamp()) {
                return false;
            }
        }
        return internalGetDetails().equals(other.internalGetDetails())
                && getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + LATITUDE_FIELD_NUMBER;
        hash = (53 * hash) + Float.floatToIntBits(getLatitude());
        hash = (37 * hash) + LONGITUDE_FIELD_NUMBER;
        hash = (53 * hash) + Float.floatToIntBits(getLongitude());
        if (hasTimestamp()) {
            hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
            hash = (53 * hash) + Internal.hashLong(getTimestamp());
        }
        if (!internalGetDetails().getMap()
                .isEmpty()) {
            hash = (37 * hash) + DETAILS_FIELD_NUMBER;
            hash = (53 * hash) + internalGetDetails().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static UserLocation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UserLocation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry)
            throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UserLocation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UserLocation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry)
            throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UserLocation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static UserLocation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry)
            throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static UserLocation parseFrom(InputStream input) throws IOException {
        return GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static UserLocation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry)
            throws IOException {
        return GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static UserLocation parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static UserLocation parseDelimitedFrom(
            InputStream input,
            ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static UserLocation parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static UserLocation parseFrom(
            CodedInputStream input,
            ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UserLocation prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserLocation}
     */
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserLocation)
            UserLocationOrBuilder {
        public static Descriptors.Descriptor getDescriptor() {
            return UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
            if (number == 4) {
                return internalGetDetails();
            }
            throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        }

        @SuppressWarnings({"rawtypes"})
        protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
            if (number == 4) {
                return internalGetMutableDetails();
            }
            throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(UserLocation.class,
                            UserLocation.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserLocation.newBuilder()
        private Builder() {

        }

        private Builder(GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            latitude_ = 0F;
            longitude_ = 0F;
            timestamp_ = 0L;
            internalGetMutableDetails().clear();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000010;
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
        }

        @Override
        public UserLocation getDefaultInstanceForType() {
            return UserLocation.getDefaultInstance();
        }

        @Override
        public UserLocation build() {
            UserLocation result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public UserLocation buildPartial() {
            UserLocation result = new UserLocation(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(UserLocation result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)) {
                    customAttributes_ = Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000010;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(UserLocation result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.latitude_ = latitude_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.longitude_ = longitude_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.timestamp_ = timestamp_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.details_ = internalGetDetails();
                result.details_.makeImmutable();
            }
            result.bitField0_ |= to_bitField0_;
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof UserLocation) {
                return mergeFrom((UserLocation) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(UserLocation other) {
            if (other == UserLocation.getDefaultInstance()) {
                return this;
            }
            if (other.getLatitude() != 0F) {
                setLatitude(other.getLatitude());
            }
            if (other.getLongitude() != 0F) {
                setLongitude(other.getLongitude());
            }
            if (other.hasTimestamp()) {
                setTimestamp(other.getTimestamp());
            }
            internalGetMutableDetails().mergeFrom(other.internalGetDetails());
            bitField0_ |= 0x00000008;
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000010;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000010;
                        customAttributesBuilder_ = GeneratedMessage.alwaysUseFieldBuilders
                                ? getCustomAttributesFieldBuilder()
                                : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @Override
        public boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry)
                throws IOException {
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 13 -> {
                            latitude_ = input.readFloat();
                            bitField0_ |= 0x00000001;
                        } // case 13
                        case 21 -> {
                            longitude_ = input.readFloat();
                            bitField0_ |= 0x00000002;
                        } // case 21
                        case 24 -> {
                            timestamp_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            MapEntry<String, String> details__ = input.readMessage(
                                    DetailsDefaultEntryHolder.defaultEntry.getParserForType(),
                                    extensionRegistry);
                            internalGetMutableDetails().getMutableMap()
                                    .put(details__.getKey(), details__.getValue());
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 122 -> {
                            Value m = input.readMessage(Value.parser(), extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int bitField0_;

        private float latitude_;

        /**
         * <code>float latitude = 1;</code>
         *
         * @return The latitude.
         */
        @Override
        public float getLatitude() {
            return latitude_;
        }

        /**
         * <code>float latitude = 1;</code>
         *
         * @param value The latitude to set.
         * @return This builder for chaining.
         */
        public Builder setLatitude(float value) {

            latitude_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>float latitude = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLatitude() {
            bitField0_ &= ~0x00000001;
            latitude_ = 0F;
            onChanged();
            return this;
        }

        private float longitude_;

        /**
         * <code>float longitude = 2;</code>
         *
         * @return The longitude.
         */
        @Override
        public float getLongitude() {
            return longitude_;
        }

        /**
         * <code>float longitude = 2;</code>
         *
         * @param value The longitude to set.
         * @return This builder for chaining.
         */
        public Builder setLongitude(float value) {

            longitude_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>float longitude = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLongitude() {
            bitField0_ &= ~0x00000002;
            longitude_ = 0F;
            onChanged();
            return this;
        }

        private long timestamp_;

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return Whether the timestamp field is set.
         */
        @Override
        public boolean hasTimestamp() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return The timestamp.
         */
        @Override
        public long getTimestamp() {
            return timestamp_;
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @param value The timestamp to set.
         * @return This builder for chaining.
         */
        public Builder setTimestamp(long value) {

            timestamp_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTimestamp() {
            bitField0_ &= ~0x00000004;
            timestamp_ = 0L;
            onChanged();
            return this;
        }

        private MapField<String, String> details_;

        private MapField<String, String> internalGetDetails() {
            if (details_ == null) {
                return MapField.emptyMapField(DetailsDefaultEntryHolder.defaultEntry);
            }
            return details_;
        }

        private MapField<String, String> internalGetMutableDetails() {
            if (details_ == null) {
                details_ = MapField.newMapField(DetailsDefaultEntryHolder.defaultEntry);
            }
            if (!details_.isMutable()) {
                details_ = details_.copy();
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return details_;
        }

        public int getDetailsCount() {
            return internalGetDetails().getMap()
                    .size();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @Override
        public boolean containsDetails(String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetDetails().getMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getDetailsMap()} instead.
         */
        @Override
        @Deprecated
        public Map<String, String> getDetails() {
            return getDetailsMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @Override
        public Map<String, String> getDetailsMap() {
            return internalGetDetails().getMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @Override
        public /* nullable */
        String getDetailsOrDefault(
                String key,
                /* nullable */
                String defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetDetails().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @Override
        public String getDetailsOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            Map<String, String> map = internalGetDetails().getMap();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearDetails() {
            bitField0_ &= ~0x00000008;
            internalGetMutableDetails().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        public Builder removeDetails(String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableDetails().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @Deprecated
        public Map<String, String> getMutableDetails() {
            bitField0_ |= 0x00000008;
            return internalGetMutableDetails().getMutableMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        public Builder putDetails(String key, String value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableDetails().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000008;
            return this;
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        public Builder putAllDetails(Map<String, String> values) {
            internalGetMutableDetails().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000008;
            return this;
        }

        private List<Value> customAttributes_ = Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000010) == 0) {
                customAttributes_ = new ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000010;
            }
        }

        private RepeatedFieldBuilder<Value, Value.Builder, ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public List<Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Value getCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(int index, Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(int index, Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(int index, Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(int index, Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(Iterable<? extends Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = Collections.emptyList();
                bitField0_ &= ~0x00000010;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Value.Builder getCustomAttributesBuilder(int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public ValueOrBuilder getCustomAttributesOrBuilder(int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public List<? extends ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder().addBuilder(Value.getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Value.Builder addCustomAttributesBuilder(int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index, Value.getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public List<Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilder<Value, Value.Builder, ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000010) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserLocation)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserLocation)
    private static final UserLocation DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new UserLocation();
    }

    public static UserLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final Parser<UserLocation> PARSER = new AbstractParser<>() {
        @Override
        public UserLocation parsePartialFrom(
                CodedInputStream input,
                ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = newBuilder();
            try {
                builder.mergeFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(builder.buildPartial());
            } catch (UninitializedMessageException e) {
                throw e.asInvalidProtocolBufferException()
                        .setUnfinishedMessage(builder.buildPartial());
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e)
                        .setUnfinishedMessage(builder.buildPartial());
            }
            return builder.buildPartial();
        }
    };

    public static Parser<UserLocation> parser() {
        return PARSER;
    }

    @Override
    public Parser<UserLocation> getParserForType() {
        return PARSER;
    }

    @Override
    public UserLocation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}