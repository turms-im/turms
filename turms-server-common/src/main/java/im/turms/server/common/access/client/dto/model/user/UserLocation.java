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

/**
 * Protobuf type {@code im.turms.proto.UserLocation}
 */
public final class UserLocation extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserLocation)
        UserLocationOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserLocation.class.getName());
    }

    // Use UserLocation.newBuilder() to construct.
    private UserLocation(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserLocation() {
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
    }

    @SuppressWarnings("rawtypes")
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 4 -> internalGetDetails();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserLocation.class,
                        im.turms.server.common.access.client.dto.model.user.UserLocation.Builder.class);
    }

    private int bitField0_;
    public static final int LATITUDE_FIELD_NUMBER = 1;
    private float latitude_;

    /**
     * <code>float latitude = 1;</code>
     *
     * @return The latitude.
     */
    @java.lang.Override
    public float getLatitude() {
        return latitude_;
    }

    public static final int LONGITUDE_FIELD_NUMBER = 2;
    private float longitude_;

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    @java.lang.Override
    public float getLongitude() {
        return longitude_;
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private long timestamp_;

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return Whether the timestamp field is set.
     */
    @java.lang.Override
    public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
        return timestamp_;
    }

    public static final int DETAILS_FIELD_NUMBER = 4;

    private static final class DetailsDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_DetailsEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, java.lang.String> details_;

    private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetDetails() {
        if (details_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(DetailsDefaultEntryHolder.defaultEntry);
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
    @java.lang.Override
    public boolean containsDetails(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetDetails().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getDetailsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getDetails() {
        return getDetailsMap();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getDetailsMap() {
        return internalGetDetails().getMap();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override
    public /* nullable */
    java.lang.String getDetailsOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     *
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override
    public java.lang.String getDetailsOrThrow(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
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
        if (java.lang.Float.floatToRawIntBits(latitude_) != 0) {
            output.writeFloat(1, latitude_);
        }
        if (java.lang.Float.floatToRawIntBits(longitude_) != 0) {
            output.writeFloat(2, longitude_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(3, timestamp_);
        }
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetDetails(),
                DetailsDefaultEntryHolder.defaultEntry,
                4);
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
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
        if (java.lang.Float.floatToRawIntBits(latitude_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeFloatSize(1, latitude_);
        }
        if (java.lang.Float.floatToRawIntBits(longitude_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeFloatSize(2, longitude_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, timestamp_);
        }
        for (java.util.Map.Entry<java.lang.String, java.lang.String> entry : internalGetDetails()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String> details__ =
                    DetailsDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, details__);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
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
        if (!(obj instanceof UserLocation other)) {
            return super.equals(obj);
        }

        if (java.lang.Float.floatToIntBits(getLatitude()) != java.lang.Float
                .floatToIntBits(other.getLatitude())) {
            return false;
        }
        if (java.lang.Float.floatToIntBits(getLongitude()) != java.lang.Float
                .floatToIntBits(other.getLongitude())) {
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

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + LATITUDE_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(getLatitude());
        hash = (37 * hash) + LONGITUDE_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(getLongitude());
        if (hasTimestamp()) {
            hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getTimestamp());
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

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserLocation prototype) {
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
     * Protobuf type {@code im.turms.proto.UserLocation}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserLocation)
            im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
        }

        @SuppressWarnings("rawtypes")
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 4 -> internalGetDetails();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings("rawtypes")
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 4 -> internalGetMutableDetails();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserLocation.class,
                            im.turms.server.common.access.client.dto.model.user.UserLocation.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserLocation.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            latitude_ = 0.0F;
            longitude_ = 0.0F;
            timestamp_ = 0L;
            internalGetMutableDetails().clear();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000010;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.internal_static_im_turms_proto_UserLocation_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserLocation getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserLocation
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserLocation build() {
            im.turms.server.common.access.client.dto.model.user.UserLocation result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserLocation buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserLocation result =
                    new im.turms.server.common.access.client.dto.model.user.UserLocation(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserLocation result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000010;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserLocation result) {
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

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserLocation) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserLocation) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserLocation other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserLocation
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getLatitude() != 0.0F) {
                setLatitude(other.getLatitude());
            }
            if (other.getLongitude() != 0.0F) {
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
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
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
                            com.google.protobuf.MapEntry<String, String> details__ =
                                    input.readMessage(
                                            DetailsDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableDetails().getMutableMap()
                                    .put(details__.getKey(), details__.getValue());
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
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
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
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
        @java.lang.Override
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
            latitude_ = 0.0F;
            onChanged();
            return this;
        }

        private float longitude_;

        /**
         * <code>float longitude = 2;</code>
         *
         * @return The longitude.
         */
        @java.lang.Override
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
            longitude_ = 0.0F;
            onChanged();
            return this;
        }

        private long timestamp_;

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return Whether the timestamp field is set.
         */
        @java.lang.Override
        public boolean hasTimestamp() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return The timestamp.
         */
        @java.lang.Override
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

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> details_;

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetDetails() {
            if (details_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(DetailsDefaultEntryHolder.defaultEntry);
            }
            return details_;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMutableDetails() {
            if (details_ == null) {
                details_ = com.google.protobuf.MapField
                        .newMapField(DetailsDefaultEntryHolder.defaultEntry);
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
        @java.lang.Override
        public boolean containsDetails(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetDetails().getMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getDetailsMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getDetails() {
            return getDetailsMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, java.lang.String> getDetailsMap() {
            return internalGetDetails().getMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override
        public /* nullable */
        java.lang.String getDetailsOrDefault(
                java.lang.String key,
                /* nullable */
                java.lang.String defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         *
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override
        public java.lang.String getDetailsOrThrow(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
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
        public Builder removeDetails(java.lang.String key) {
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
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getMutableDetails() {
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
        public Builder putDetails(java.lang.String key, java.lang.String value) {
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
        public Builder putAllDetails(java.util.Map<java.lang.String, java.lang.String> values) {
            internalGetMutableDetails().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000008;
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000010) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000010;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
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
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
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
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
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
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
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
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
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
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
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
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
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
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
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
                customAttributes_ = java.util.Collections.emptyList();
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
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
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
    private static final im.turms.server.common.access.client.dto.model.user.UserLocation DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.user.UserLocation();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserLocation> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserLocation parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserLocation> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserLocation> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserLocation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}