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

package im.turms.client.model.proto.request.user.relationship;

/**
 * Protobuf type {@code im.turms.proto.UpdateFriendRequestRequest}
 */
public final class UpdateFriendRequestRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateFriendRequestRequest, UpdateFriendRequestRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateFriendRequestRequest)
        UpdateFriendRequestRequestOrBuilder {
    private UpdateFriendRequestRequest() {
        reason_ = "";
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

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 request_id = 1;</code>
     *
     * @param value The requestId to set.
     */
    private void setRequestId(long value) {

        requestId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 request_id = 1;</code>
     */
    private void clearRequestId() {

        requestId_ = 0L;
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
    public im.turms.client.model.proto.constant.ResponseAction getResponseAction() {
        im.turms.client.model.proto.constant.ResponseAction result =
                im.turms.client.model.proto.constant.ResponseAction.forNumber(responseAction_);
        return result == null
                ? im.turms.client.model.proto.constant.ResponseAction.UNRECOGNIZED
                : result;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @param value The enum numeric value on the wire for responseAction to set.
     */
    private void setResponseActionValue(int value) {
        responseAction_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @param value The responseAction to set.
     */
    private void setResponseAction(im.turms.client.model.proto.constant.ResponseAction value) {
        responseAction_ = value.getNumber();

    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     */
    private void clearResponseAction() {

        responseAction_ = 0;
    }

    public static final int REASON_FIELD_NUMBER = 3;
    private java.lang.String reason_;

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
        return reason_;
    }

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(reason_);
    }

    /**
     * <code>optional string reason = 3;</code>
     *
     * @param value The reason to set.
     */
    private void setReason(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        reason_ = value;
    }

    /**
     * <code>optional string reason = 3;</code>
     */
    private void clearReason() {
        bitField0_ &= ~0x00000001;
        reason_ = getDefaultInstance().getReason();
    }

    /**
     * <code>optional string reason = 3;</code>
     *
     * @param value The bytes for reason to set.
     */
    private void setReasonBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        reason_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateFriendRequestRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateFriendRequestRequest)
            im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

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
            return instance.getRequestId();
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
            copyOnWrite();
            instance.setRequestId(value);
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
            copyOnWrite();
            instance.clearRequestId();
            return this;
        }

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
            return instance.getResponseActionValue();
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
        public Builder setResponseActionValue(int value) {
            copyOnWrite();
            instance.setResponseActionValue(value);
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
        public im.turms.client.model.proto.constant.ResponseAction getResponseAction() {
            return instance.getResponseAction();
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
        public Builder setResponseAction(
                im.turms.client.model.proto.constant.ResponseAction value) {
            copyOnWrite();
            instance.setResponseAction(value);
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
            copyOnWrite();
            instance.clearResponseAction();
            return this;
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return Whether the reason field is set.
         */
        @java.lang.Override
        public boolean hasReason() {
            return instance.hasReason();
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return The reason.
         */
        @java.lang.Override
        public java.lang.String getReason() {
            return instance.getReason();
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return The bytes for reason.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getReasonBytes() {
            return instance.getReasonBytes();
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            copyOnWrite();
            instance.setReason(value);
            return this;
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            copyOnWrite();
            instance.clearReason();
            return this;
        }

        /**
         * <code>optional string reason = 3;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setReasonBytes(value);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateFriendRequestRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "requestId_",
                        "responseAction_",
                        "reason_",};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0002\u0002\f"
                                + "\u0003\u1208\u0000";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateFriendRequestRequest)
    private static final im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest DEFAULT_INSTANCE;

    static {
        UpdateFriendRequestRequest defaultInstance = new UpdateFriendRequestRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateFriendRequestRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateFriendRequestRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateFriendRequestRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}