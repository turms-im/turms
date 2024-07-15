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

package im.turms.server.common.access.client.dto.request.user;

/**
 * Protobuf type {@code im.turms.proto.QueryNearbyUsersRequest}
 */
public final class QueryNearbyUsersRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryNearbyUsersRequest)
        QueryNearbyUsersRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryNearbyUsersRequest.class.getName());
    }

    // Use QueryNearbyUsersRequest.newBuilder() to construct.
    private QueryNearbyUsersRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private QueryNearbyUsersRequest() {
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass.internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass.internal_static_im_turms_proto_QueryNearbyUsersRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.class,
                        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder.class);
    }

    private int bitField0_;
    public static final int LATITUDE_FIELD_NUMBER = 1;
    private float latitude_ = 0F;

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
    private float longitude_ = 0F;

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    @java.lang.Override
    public float getLongitude() {
        return longitude_;
    }

    public static final int MAX_COUNT_FIELD_NUMBER = 3;
    private int maxCount_ = 0;

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return Whether the maxCount field is set.
     */
    @java.lang.Override
    public boolean hasMaxCount() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return The maxCount.
     */
    @java.lang.Override
    public int getMaxCount() {
        return maxCount_;
    }

    public static final int MAX_DISTANCE_FIELD_NUMBER = 4;
    private int maxDistance_ = 0;

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return Whether the maxDistance field is set.
     */
    @java.lang.Override
    public boolean hasMaxDistance() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return The maxDistance.
     */
    @java.lang.Override
    public int getMaxDistance() {
        return maxDistance_;
    }

    public static final int WITH_COORDINATES_FIELD_NUMBER = 5;
    private boolean withCoordinates_ = false;

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return Whether the withCoordinates field is set.
     */
    @java.lang.Override
    public boolean hasWithCoordinates() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return The withCoordinates.
     */
    @java.lang.Override
    public boolean getWithCoordinates() {
        return withCoordinates_;
    }

    public static final int WITH_DISTANCE_FIELD_NUMBER = 6;
    private boolean withDistance_ = false;

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return Whether the withDistance field is set.
     */
    @java.lang.Override
    public boolean hasWithDistance() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return The withDistance.
     */
    @java.lang.Override
    public boolean getWithDistance() {
        return withDistance_;
    }

    public static final int WITH_USER_INFO_FIELD_NUMBER = 7;
    private boolean withUserInfo_ = false;

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return Whether the withUserInfo field is set.
     */
    @java.lang.Override
    public boolean hasWithUserInfo() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return The withUserInfo.
     */
    @java.lang.Override
    public boolean getWithUserInfo() {
        return withUserInfo_;
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
            output.writeInt32(3, maxCount_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt32(4, maxDistance_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeBool(5, withCoordinates_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeBool(6, withDistance_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeBool(7, withUserInfo_);
        }
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
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, maxCount_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, maxDistance_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(5, withCoordinates_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(6, withDistance_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(7, withUserInfo_);
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
        if (!(obj instanceof QueryNearbyUsersRequest other)) {
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
        if (hasMaxCount() != other.hasMaxCount()) {
            return false;
        }
        if (hasMaxCount()) {
            if (getMaxCount() != other.getMaxCount()) {
                return false;
            }
        }
        if (hasMaxDistance() != other.hasMaxDistance()) {
            return false;
        }
        if (hasMaxDistance()) {
            if (getMaxDistance() != other.getMaxDistance()) {
                return false;
            }
        }
        if (hasWithCoordinates() != other.hasWithCoordinates()) {
            return false;
        }
        if (hasWithCoordinates()) {
            if (getWithCoordinates() != other.getWithCoordinates()) {
                return false;
            }
        }
        if (hasWithDistance() != other.hasWithDistance()) {
            return false;
        }
        if (hasWithDistance()) {
            if (getWithDistance() != other.getWithDistance()) {
                return false;
            }
        }
        if (hasWithUserInfo() != other.hasWithUserInfo()) {
            return false;
        }
        if (hasWithUserInfo()) {
            if (getWithUserInfo() != other.getWithUserInfo()) {
                return false;
            }
        }
        return getCustomAttributesList().equals(other.getCustomAttributesList())
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
        if (hasMaxCount()) {
            hash = (37 * hash) + MAX_COUNT_FIELD_NUMBER;
            hash = (53 * hash) + getMaxCount();
        }
        if (hasMaxDistance()) {
            hash = (37 * hash) + MAX_DISTANCE_FIELD_NUMBER;
            hash = (53 * hash) + getMaxDistance();
        }
        if (hasWithCoordinates()) {
            hash = (37 * hash) + WITH_COORDINATES_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getWithCoordinates());
        }
        if (hasWithDistance()) {
            hash = (37 * hash) + WITH_DISTANCE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getWithDistance());
        }
        if (hasWithUserInfo()) {
            hash = (37 * hash) + WITH_USER_INFO_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getWithUserInfo());
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.QueryNearbyUsersRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryNearbyUsersRequest)
            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass.internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass.internal_static_im_turms_proto_QueryNearbyUsersRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.class,
                            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            latitude_ = 0F;
            longitude_ = 0F;
            maxCount_ = 0;
            maxDistance_ = 0;
            withCoordinates_ = false;
            withDistance_ = false;
            withUserInfo_ = false;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000080;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass.internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest build() {
            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest result =
                    new im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000080;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.latitude_ = latitude_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.longitude_ = longitude_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.maxCount_ = maxCount_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.maxDistance_ = maxDistance_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.withCoordinates_ = withCoordinates_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.withDistance_ = withDistance_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.withUserInfo_ = withUserInfo_;
                to_bitField0_ |= 0x00000010;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getLatitude() != 0F) {
                setLatitude(other.getLatitude());
            }
            if (other.getLongitude() != 0F) {
                setLongitude(other.getLongitude());
            }
            if (other.hasMaxCount()) {
                setMaxCount(other.getMaxCount());
            }
            if (other.hasMaxDistance()) {
                setMaxDistance(other.getMaxDistance());
            }
            if (other.hasWithCoordinates()) {
                setWithCoordinates(other.getWithCoordinates());
            }
            if (other.hasWithDistance()) {
                setWithDistance(other.getWithDistance());
            }
            if (other.hasWithUserInfo()) {
                setWithUserInfo(other.getWithUserInfo());
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000080;
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
                        bitField0_ &= ~0x00000080;
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
                            maxCount_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            maxDistance_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            withCoordinates_ = input.readBool();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            withDistance_ = input.readBool();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            withUserInfo_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 56
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
            longitude_ = 0F;
            onChanged();
            return this;
        }

        private int maxCount_;

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return Whether the maxCount field is set.
         */
        @java.lang.Override
        public boolean hasMaxCount() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return The maxCount.
         */
        @java.lang.Override
        public int getMaxCount() {
            return maxCount_;
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @param value The maxCount to set.
         * @return This builder for chaining.
         */
        public Builder setMaxCount(int value) {

            maxCount_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxCount() {
            bitField0_ &= ~0x00000004;
            maxCount_ = 0;
            onChanged();
            return this;
        }

        private int maxDistance_;

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return Whether the maxDistance field is set.
         */
        @java.lang.Override
        public boolean hasMaxDistance() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return The maxDistance.
         */
        @java.lang.Override
        public int getMaxDistance() {
            return maxDistance_;
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @param value The maxDistance to set.
         * @return This builder for chaining.
         */
        public Builder setMaxDistance(int value) {

            maxDistance_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxDistance() {
            bitField0_ &= ~0x00000008;
            maxDistance_ = 0;
            onChanged();
            return this;
        }

        private boolean withCoordinates_;

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return Whether the withCoordinates field is set.
         */
        @java.lang.Override
        public boolean hasWithCoordinates() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return The withCoordinates.
         */
        @java.lang.Override
        public boolean getWithCoordinates() {
            return withCoordinates_;
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @param value The withCoordinates to set.
         * @return This builder for chaining.
         */
        public Builder setWithCoordinates(boolean value) {

            withCoordinates_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithCoordinates() {
            bitField0_ &= ~0x00000010;
            withCoordinates_ = false;
            onChanged();
            return this;
        }

        private boolean withDistance_;

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return Whether the withDistance field is set.
         */
        @java.lang.Override
        public boolean hasWithDistance() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return The withDistance.
         */
        @java.lang.Override
        public boolean getWithDistance() {
            return withDistance_;
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @param value The withDistance to set.
         * @return This builder for chaining.
         */
        public Builder setWithDistance(boolean value) {

            withDistance_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithDistance() {
            bitField0_ &= ~0x00000020;
            withDistance_ = false;
            onChanged();
            return this;
        }

        private boolean withUserInfo_;

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return Whether the withUserInfo field is set.
         */
        @java.lang.Override
        public boolean hasWithUserInfo() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return The withUserInfo.
         */
        @java.lang.Override
        public boolean getWithUserInfo() {
            return withUserInfo_;
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @param value The withUserInfo to set.
         * @return This builder for chaining.
         */
        public Builder setWithUserInfo(boolean value) {

            withUserInfo_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithUserInfo() {
            bitField0_ &= ~0x00000040;
            withUserInfo_ = false;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000080) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000080;
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
                bitField0_ &= ~0x00000080;
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
                        ((bitField0_ & 0x00000080) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryNearbyUsersRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryNearbyUsersRequest)
    private static final im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryNearbyUsersRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryNearbyUsersRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryNearbyUsersRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryNearbyUsersRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}