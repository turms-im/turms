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
 * Protobuf type {@code im.turms.proto.UpdateRelationshipGroupRequest}
 */
public final class UpdateRelationshipGroupRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateRelationshipGroupRequest, UpdateRelationshipGroupRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateRelationshipGroupRequest)
        UpdateRelationshipGroupRequestOrBuilder {
    private UpdateRelationshipGroupRequest() {
        newName_ = "";
    }

    public static final int GROUP_INDEX_FIELD_NUMBER = 1;
    private int groupIndex_;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int32 group_index = 1;</code>
     *
     * @return The groupIndex.
     */
    @java.lang.Override
    public int getGroupIndex() {
        return groupIndex_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int32 group_index = 1;</code>
     *
     * @param value The groupIndex to set.
     */
    private void setGroupIndex(int value) {

        groupIndex_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int32 group_index = 1;</code>
     */
    private void clearGroupIndex() {

        groupIndex_ = 0;
    }

    public static final int NEW_NAME_FIELD_NUMBER = 2;
    private java.lang.String newName_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @return The newName.
     */
    @java.lang.Override
    public java.lang.String getNewName() {
        return newName_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @return The bytes for newName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNewNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(newName_);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @param value The newName to set.
     */
    private void setNewName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();

        newName_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     */
    private void clearNewName() {

        newName_ = getDefaultInstance().getNewName();
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @param value The bytes for newName to set.
     */
    private void setNewNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        newName_ = value.toStringUtf8();

    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest parseFrom(
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
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateRelationshipGroupRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateRelationshipGroupRequest)
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int32 group_index = 1;</code>
         *
         * @return The groupIndex.
         */
        @java.lang.Override
        public int getGroupIndex() {
            return instance.getGroupIndex();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int32 group_index = 1;</code>
         *
         * @param value The groupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndex(int value) {
            copyOnWrite();
            instance.setGroupIndex(value);
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int32 group_index = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndex() {
            copyOnWrite();
            instance.clearGroupIndex();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>string new_name = 2;</code>
         *
         * @return The newName.
         */
        @java.lang.Override
        public java.lang.String getNewName() {
            return instance.getNewName();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>string new_name = 2;</code>
         *
         * @return The bytes for newName.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNewNameBytes() {
            return instance.getNewNameBytes();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>string new_name = 2;</code>
         *
         * @param value The newName to set.
         * @return This builder for chaining.
         */
        public Builder setNewName(java.lang.String value) {
            copyOnWrite();
            instance.setNewName(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>string new_name = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNewName() {
            copyOnWrite();
            instance.clearNewName();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>string new_name = 2;</code>
         *
         * @param value The bytes for newName to set.
         * @return This builder for chaining.
         */
        public Builder setNewNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNewNameBytes(value);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateRelationshipGroupRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"groupIndex_", "newName_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\u0208"
                                + "";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateRelationshipGroupRequest)
    private static final im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest DEFAULT_INSTANCE;

    static {
        UpdateRelationshipGroupRequest defaultInstance = new UpdateRelationshipGroupRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateRelationshipGroupRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateRelationshipGroupRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateRelationshipGroupRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}