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
 * Protobuf type {@code im.turms.proto.QueryUserProfilesRequest}
 */
public final class QueryUserProfilesRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryUserProfilesRequest)
        QueryUserProfilesRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryUserProfilesRequest.class.getName());
    }

    // Use QueryUserProfilesRequest.newBuilder() to construct.
    private QueryUserProfilesRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private QueryUserProfilesRequest() {
        userIds_ = emptyLongList();
        name_ = "";
        fieldsToHighlight_ = emptyIntList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass.internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass.internal_static_im_turms_proto_QueryUserProfilesRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.class,
                        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder.class);
    }

    private int bitField0_;
    public static final int USER_IDS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return A list containing the userIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getUserIdsList() {
        return userIds_;
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return The count of userIds.
     */
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private int userIdsMemoizedSerializedSize = -1;

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    public static final int NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SKIP_FIELD_NUMBER = 10;
    private int skip_;

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return Whether the skip field is set.
     */
    @java.lang.Override
    public boolean hasSkip() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return The skip.
     */
    @java.lang.Override
    public int getSkip() {
        return skip_;
    }

    public static final int LIMIT_FIELD_NUMBER = 11;
    private int limit_;

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return Whether the limit field is set.
     */
    @java.lang.Override
    public boolean hasLimit() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return The limit.
     */
    @java.lang.Override
    public int getLimit() {
        return limit_;
    }

    public static final int FIELDS_TO_HIGHLIGHT_FIELD_NUMBER = 12;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.IntList fieldsToHighlight_ = emptyIntList();

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return A list containing the fieldsToHighlight.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getFieldsToHighlightList() {
        return fieldsToHighlight_;
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return The count of fieldsToHighlight.
     */
    public int getFieldsToHighlightCount() {
        return fieldsToHighlight_.size();
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param index The index of the element to return.
     * @return The fieldsToHighlight at the given index.
     */
    public int getFieldsToHighlight(int index) {
        return fieldsToHighlight_.getInt(index);
    }

    private int fieldsToHighlightMemoizedSerializedSize = -1;

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
        getSerializedSize();
        if (!getUserIdsList().isEmpty()) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(userIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < userIds_.size(); i++) {
            output.writeInt64NoTag(userIds_.getLong(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, lastUpdatedDate_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, name_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt32(10, skip_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt32(11, limit_);
        }
        if (!getFieldsToHighlightList().isEmpty()) {
            output.writeUInt32NoTag(98);
            output.writeUInt32NoTag(fieldsToHighlightMemoizedSerializedSize);
        }
        for (int i = 0; i < fieldsToHighlight_.size(); i++) {
            output.writeInt32NoTag(fieldsToHighlight_.getInt(i));
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
        {
            int dataSize = 0;
            for (int i = 0; i < userIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(userIds_.getLong(i));
            }
            size += dataSize;
            if (!getUserIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            userIdsMemoizedSerializedSize = dataSize;
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, lastUpdatedDate_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, name_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(10, skip_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(11, limit_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < fieldsToHighlight_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt32SizeNoTag(fieldsToHighlight_.getInt(i));
            }
            size += dataSize;
            if (!getFieldsToHighlightList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            fieldsToHighlightMemoizedSerializedSize = dataSize;
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
        if (!(obj instanceof QueryUserProfilesRequest other)) {
            return super.equals(obj);
        }

        if (!getUserIdsList().equals(other.getUserIdsList())) {
            return false;
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
                return false;
            }
        }
        if (hasName() != other.hasName()) {
            return false;
        }
        if (hasName()) {
            if (!getName().equals(other.getName())) {
                return false;
            }
        }
        if (hasSkip() != other.hasSkip()) {
            return false;
        }
        if (hasSkip()) {
            if (getSkip() != other.getSkip()) {
                return false;
            }
        }
        if (hasLimit() != other.hasLimit()) {
            return false;
        }
        if (hasLimit()) {
            if (getLimit() != other.getLimit()) {
                return false;
            }
        }
        return getFieldsToHighlightList().equals(other.getFieldsToHighlightList())
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
        if (getUserIdsCount() > 0) {
            hash = (37 * hash) + USER_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getUserIdsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (hasSkip()) {
            hash = (37 * hash) + SKIP_FIELD_NUMBER;
            hash = (53 * hash) + getSkip();
        }
        if (hasLimit()) {
            hash = (37 * hash) + LIMIT_FIELD_NUMBER;
            hash = (53 * hash) + getLimit();
        }
        if (getFieldsToHighlightCount() > 0) {
            hash = (37 * hash) + FIELDS_TO_HIGHLIGHT_FIELD_NUMBER;
            hash = (53 * hash) + getFieldsToHighlightList().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.QueryUserProfilesRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryUserProfilesRequest)
            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass.internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass.internal_static_im_turms_proto_QueryUserProfilesRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.class,
                            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userIds_ = emptyLongList();
            lastUpdatedDate_ = 0L;
            name_ = "";
            skip_ = 0;
            limit_ = 0;
            fieldsToHighlight_ = emptyIntList();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000040;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass.internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest build() {
            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest result =
                    new im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000040;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                userIds_.makeImmutable();
                result.userIds_ = userIds_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.skip_ = skip_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.limit_ = limit_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                fieldsToHighlight_.makeImmutable();
                result.fieldsToHighlight_ = fieldsToHighlight_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.userIds_.isEmpty()) {
                if (userIds_.isEmpty()) {
                    userIds_ = other.userIds_;
                    userIds_.makeImmutable();
                    bitField0_ |= 0x00000001;
                } else {
                    ensureUserIdsIsMutable();
                    userIds_.addAll(other.userIds_);
                }
                onChanged();
            }
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasSkip()) {
                setSkip(other.getSkip());
            }
            if (other.hasLimit()) {
                setLimit(other.getLimit());
            }
            if (!other.fieldsToHighlight_.isEmpty()) {
                if (fieldsToHighlight_.isEmpty()) {
                    fieldsToHighlight_ = other.fieldsToHighlight_;
                    fieldsToHighlight_.makeImmutable();
                    bitField0_ |= 0x00000020;
                } else {
                    ensureFieldsToHighlightIsMutable();
                    fieldsToHighlight_.addAll(other.fieldsToHighlight_);
                }
                onChanged();
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000040;
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
                        bitField0_ &= ~0x00000040;
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
                        case 8 -> {
                            long v = input.readInt64();
                            ensureUserIdsIsMutable();
                            userIds_.addLong(v);
                        } // case 8
                        case 10 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureUserIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                userIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 10
                        case 16 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 80 -> {
                            skip_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 80
                        case 88 -> {
                            limit_ = input.readInt32();
                            bitField0_ |= 0x00000010;
                        } // case 88
                        case 96 -> {
                            int v = input.readInt32();
                            ensureFieldsToHighlightIsMutable();
                            fieldsToHighlight_.addInt(v);
                        } // case 96
                        case 98 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureFieldsToHighlightIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                fieldsToHighlight_.addInt(input.readInt32());
                            }
                            input.popLimit(limit);
                        } // case 98
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

        private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

        private void ensureUserIdsIsMutable() {
            if (!userIds_.isModifiable()) {
                userIds_ = makeMutableCopy(userIds_);
            }
            bitField0_ |= 0x00000001;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return A list containing the userIds.
         */
        public java.util.List<java.lang.Long> getUserIdsList() {
            userIds_.makeImmutable();
            return userIds_;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return The count of userIds.
         */
        public int getUserIdsCount() {
            return userIds_.size();
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        public long getUserIds(int index) {
            return userIds_.getLong(index);
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The userIds to set.
         * @return This builder for chaining.
         */
        public Builder setUserIds(int index, long value) {

            ensureUserIdsIsMutable();
            userIds_.setLong(index, value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param value The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addUserIds(long value) {

            ensureUserIdsIsMutable();
            userIds_.addLong(value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param values The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureUserIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userIds_);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            userIds_ = emptyLongList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        private long lastUpdatedDate_;

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return lastUpdatedDate_;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000002;
            lastUpdatedDate_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>optional string name = 3;</code>
         *
         * @return Whether the name field is set.
         */
        public boolean hasName() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int skip_;

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return Whether the skip field is set.
         */
        @java.lang.Override
        public boolean hasSkip() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return The skip.
         */
        @java.lang.Override
        public int getSkip() {
            return skip_;
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @param value The skip to set.
         * @return This builder for chaining.
         */
        public Builder setSkip(int value) {

            skip_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSkip() {
            bitField0_ &= ~0x00000008;
            skip_ = 0;
            onChanged();
            return this;
        }

        private int limit_;

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return Whether the limit field is set.
         */
        @java.lang.Override
        public boolean hasLimit() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return The limit.
         */
        @java.lang.Override
        public int getLimit() {
            return limit_;
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @param value The limit to set.
         * @return This builder for chaining.
         */
        public Builder setLimit(int value) {

            limit_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLimit() {
            bitField0_ &= ~0x00000010;
            limit_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.IntList fieldsToHighlight_ = emptyIntList();

        private void ensureFieldsToHighlightIsMutable() {
            if (!fieldsToHighlight_.isModifiable()) {
                fieldsToHighlight_ = makeMutableCopy(fieldsToHighlight_);
            }
            bitField0_ |= 0x00000020;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return A list containing the fieldsToHighlight.
         */
        public java.util.List<java.lang.Integer> getFieldsToHighlightList() {
            fieldsToHighlight_.makeImmutable();
            return fieldsToHighlight_;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return The count of fieldsToHighlight.
         */
        public int getFieldsToHighlightCount() {
            return fieldsToHighlight_.size();
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param index The index of the element to return.
         * @return The fieldsToHighlight at the given index.
         */
        public int getFieldsToHighlight(int index) {
            return fieldsToHighlight_.getInt(index);
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param index The index to set the value at.
         * @param value The fieldsToHighlight to set.
         * @return This builder for chaining.
         */
        public Builder setFieldsToHighlight(int index, int value) {

            ensureFieldsToHighlightIsMutable();
            fieldsToHighlight_.setInt(index, value);
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param value The fieldsToHighlight to add.
         * @return This builder for chaining.
         */
        public Builder addFieldsToHighlight(int value) {

            ensureFieldsToHighlightIsMutable();
            fieldsToHighlight_.addInt(value);
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param values The fieldsToHighlight to add.
         * @return This builder for chaining.
         */
        public Builder addAllFieldsToHighlight(
                java.lang.Iterable<? extends java.lang.Integer> values) {
            ensureFieldsToHighlightIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, fieldsToHighlight_);
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFieldsToHighlight() {
            fieldsToHighlight_ = emptyIntList();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000040) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000040;
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
                bitField0_ &= ~0x00000040;
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
                        ((bitField0_ & 0x00000040) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryUserProfilesRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryUserProfilesRequest)
    private static final im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryUserProfilesRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryUserProfilesRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryUserProfilesRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryUserProfilesRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}