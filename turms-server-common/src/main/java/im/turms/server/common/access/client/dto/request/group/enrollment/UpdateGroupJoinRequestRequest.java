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

package im.turms.server.common.access.client.dto.request.group.enrollment;

/**
 * Protobuf type {@code im.turms.proto.UpdateGroupJoinRequestRequest}
 */
public final class UpdateGroupJoinRequestRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateGroupJoinRequestRequest)
        UpdateGroupJoinRequestRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateGroupJoinRequestRequest.class.getName());
    }

    // Use UpdateGroupJoinRequestRequest.newBuilder() to construct.
    private UpdateGroupJoinRequestRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateGroupJoinRequestRequest() {
        responseAction_ = 0;
        reason_ = "";
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest.class,
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest.Builder.class);
    }

    private int bitField0_;
    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    private long requestId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 request_id = 1;</code>
     *
     * @return The requestId.
     */
    @java.lang.Override
    public long getRequestId() {
        return requestId_;
    }

    public static final int RESPONSE_ACTION_FIELD_NUMBER = 2;
    private int responseAction_;

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @return The enum numeric value on the wire for responseAction.
     */
    @java.lang.Override
    public int getResponseActionValue() {
        return responseAction_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @return The responseAction.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.ResponseAction getResponseAction() {
        im.turms.server.common.access.client.dto.constant.ResponseAction result =
                im.turms.server.common.access.client.dto.constant.ResponseAction
                        .forNumber(responseAction_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.ResponseAction.UNRECOGNIZED
                : result;
    }

    public static final int REASON_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object reason_ = "";

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return Whether the reason field is set.
     */
    @java.lang.Override
    public boolean hasReason() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return The reason.
     */
    @java.lang.Override
    public java.lang.String getReason() {
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            reason_ = s;
            return s;
        }
    }

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            reason_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (requestId_ != 0L) {
            output.writeInt64(1, requestId_);
        }
        if (responseAction_ != im.turms.server.common.access.client.dto.constant.ResponseAction.ACCEPT
                .getNumber()) {
            output.writeEnum(2, responseAction_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, reason_);
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
        if (requestId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, requestId_);
        }
        if (responseAction_ != im.turms.server.common.access.client.dto.constant.ResponseAction.ACCEPT
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, responseAction_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, reason_);
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
        if (!(obj instanceof UpdateGroupJoinRequestRequest other)) {
            return super.equals(obj);
        }

        if (getRequestId() != other.getRequestId()) {
            return false;
        }
        if (responseAction_ != other.responseAction_) {
            return false;
        }
        if (hasReason() != other.hasReason()) {
            return false;
        }
        if (hasReason()) {
            if (!getReason().equals(other.getReason())) {
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
        hash = (37 * hash) + REQUEST_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRequestId());
        hash = (37 * hash) + RESPONSE_ACTION_FIELD_NUMBER;
        hash = (53 * hash) + responseAction_;
        if (hasReason()) {
            hash = (37 * hash) + REASON_FIELD_NUMBER;
            hash = (53 * hash) + getReason().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateGroupJoinRequestRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateGroupJoinRequestRequest)
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest.class,
                            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            requestId_ = 0L;
            responseAction_ = 0;
            reason_ = "";
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest build() {
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest result =
                    new im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000008;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.requestId_ = requestId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.responseAction_ = responseAction_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.reason_ = reason_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getRequestId() != 0L) {
                setRequestId(other.getRequestId());
            }
            if (other.responseAction_ != 0) {
                setResponseActionValue(other.getResponseActionValue());
            }
            if (other.hasReason()) {
                reason_ = other.reason_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
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
                        bitField0_ &= ~0x00000008;
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
                            requestId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            responseAction_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            reason_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
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

        private long requestId_;

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 request_id = 1;</code>
         *
         * @return The requestId.
         */
        @java.lang.Override
        public long getRequestId() {
            return requestId_;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 request_id = 1;</code>
         *
         * @param value The requestId to set.
         * @return This builder for chaining.
         */
        public Builder setRequestId(long value) {

            requestId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 request_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestId() {
            bitField0_ &= ~0x00000001;
            requestId_ = 0L;
            onChanged();
            return this;
        }

        private int responseAction_;

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
         *
         * @return The enum numeric value on the wire for responseAction.
         */
        @java.lang.Override
        public int getResponseActionValue() {
            return responseAction_;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
         *
         * @param value The enum numeric value on the wire for responseAction to set.
         * @return This builder for chaining.
         */
        public Builder setResponseActionValue(int value) {
            responseAction_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
         *
         * @return The responseAction.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.ResponseAction getResponseAction() {
            im.turms.server.common.access.client.dto.constant.ResponseAction result =
                    im.turms.server.common.access.client.dto.constant.ResponseAction
                            .forNumber(responseAction_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.ResponseAction.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
         *
         * @param value The responseAction to set.
         * @return This builder for chaining.
         */
        public Builder setResponseAction(
                im.turms.server.common.access.client.dto.constant.ResponseAction value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            responseAction_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearResponseAction() {
            bitField0_ &= ~0x00000002;
            responseAction_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object reason_ = "";

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return Whether the reason field is set.
         */
        public boolean hasReason() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return The reason.
         */
        public java.lang.String getReason() {
            java.lang.Object ref = reason_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                reason_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return The bytes for reason.
         */
        public com.google.protobuf.ByteString getReasonBytes() {
            java.lang.Object ref = reason_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                reason_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            reason_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            reason_ = getDefaultInstance().getReason();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            reason_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000008;
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
                bitField0_ &= ~0x00000008;
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
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateGroupJoinRequestRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateGroupJoinRequestRequest)
    private static final im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateGroupJoinRequestRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateGroupJoinRequestRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateGroupJoinRequestRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateGroupJoinRequestRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}