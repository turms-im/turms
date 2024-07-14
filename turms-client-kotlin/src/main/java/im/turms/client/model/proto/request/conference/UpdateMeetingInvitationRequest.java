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

package im.turms.client.model.proto.request.conference;

/**
 * Protobuf type {@code im.turms.proto.UpdateMeetingInvitationRequest}
 */
public final class UpdateMeetingInvitationRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateMeetingInvitationRequest, UpdateMeetingInvitationRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateMeetingInvitationRequest)
        UpdateMeetingInvitationRequestOrBuilder {
    private UpdateMeetingInvitationRequest() {
        password_ = "";
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int MEETING_ID_FIELD_NUMBER = 1;
    private long meetingId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 meeting_id = 1;</code>
     *
     * @return The meetingId.
     */
    @java.lang.Override
    public long getMeetingId() {
        return meetingId_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 meeting_id = 1;</code>
     *
     * @param value The meetingId to set.
     */
    private void setMeetingId(long value) {

        meetingId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 meeting_id = 1;</code>
     */
    private void clearMeetingId() {

        meetingId_ = 0L;
    }

    public static final int PASSWORD_FIELD_NUMBER = 2;
    private java.lang.String password_;

    /**
     * <code>optional string password = 2;</code>
     *
     * @return Whether the password field is set.
     */
    @java.lang.Override
    public boolean hasPassword() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string password = 2;</code>
     *
     * @return The password.
     */
    @java.lang.Override
    public java.lang.String getPassword() {
        return password_;
    }

    /**
     * <code>optional string password = 2;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(password_);
    }

    /**
     * <code>optional string password = 2;</code>
     *
     * @param value The password to set.
     */
    private void setPassword(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        password_ = value;
    }

    /**
     * <code>optional string password = 2;</code>
     */
    private void clearPassword() {
        bitField0_ &= ~0x00000001;
        password_ = getDefaultInstance().getPassword();
    }

    /**
     * <code>optional string password = 2;</code>
     *
     * @param value The bytes for password to set.
     */
    private void setPasswordBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        password_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int RESPONSE_ACTION_FIELD_NUMBER = 5;
    private int responseAction_;

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
     */
    private void clearResponseAction() {

        responseAction_ = 0;
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateMeetingInvitationRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateMeetingInvitationRequest)
            im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 meeting_id = 1;</code>
         *
         * @return The meetingId.
         */
        @java.lang.Override
        public long getMeetingId() {
            return instance.getMeetingId();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 meeting_id = 1;</code>
         *
         * @param value The meetingId to set.
         * @return This builder for chaining.
         */
        public Builder setMeetingId(long value) {
            copyOnWrite();
            instance.setMeetingId(value);
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 meeting_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMeetingId() {
            copyOnWrite();
            instance.clearMeetingId();
            return this;
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @return Whether the password field is set.
         */
        @java.lang.Override
        public boolean hasPassword() {
            return instance.hasPassword();
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @return The password.
         */
        @java.lang.Override
        public java.lang.String getPassword() {
            return instance.getPassword();
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @return The bytes for password.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getPasswordBytes() {
            return instance.getPasswordBytes();
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @param value The password to set.
         * @return This builder for chaining.
         */
        public Builder setPassword(java.lang.String value) {
            copyOnWrite();
            instance.setPassword(value);
            return this;
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            copyOnWrite();
            instance.clearPassword();
            return this;
        }

        /**
         * <code>optional string password = 2;</code>
         *
         * @param value The bytes for password to set.
         * @return This builder for chaining.
         */
        public Builder setPasswordBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setPasswordBytes(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
         * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
         * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
         * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
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
         * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearResponseAction() {
            copyOnWrite();
            instance.clearResponseAction();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateMeetingInvitationRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "meetingId_",
                        "password_",
                        "responseAction_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u000f\u0004\u0000\u0001\u0000\u0001\u0002\u0002\u1208"
                                + "\u0000\u0005\f\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateMeetingInvitationRequest)
    private static final im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest DEFAULT_INSTANCE;
    static {
        UpdateMeetingInvitationRequest defaultInstance = new UpdateMeetingInvitationRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateMeetingInvitationRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateMeetingInvitationRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateMeetingInvitationRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}