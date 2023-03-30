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

package im.turms.client.model.proto.request.group.enrollment;

/**
 * Protobuf type {@code im.turms.proto.DeleteGroupJoinRequestRequest}
 */
public final class DeleteGroupJoinRequestRequest extends
        com.google.protobuf.GeneratedMessageLite<DeleteGroupJoinRequestRequest, DeleteGroupJoinRequestRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteGroupJoinRequestRequest)
        DeleteGroupJoinRequestRequestOrBuilder {
    private DeleteGroupJoinRequestRequest() {
    }

    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    private long requestId_;

    /**
     * <code>int64 request_id = 1;</code>
     *
     * @return The requestId.
     */
    @java.lang.Override
    public long getRequestId() {
        return requestId_;
    }

    /**
     * <code>int64 request_id = 1;</code>
     *
     * @param value The requestId to set.
     */
    private void setRequestId(long value) {

        requestId_ = value;
    }

    /**
     * <code>int64 request_id = 1;</code>
     */
    private void clearRequestId() {

        requestId_ = 0L;
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest parseFrom(
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
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.DeleteGroupJoinRequestRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteGroupJoinRequestRequest)
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 request_id = 1;</code>
         *
         * @return The requestId.
         */
        @java.lang.Override
        public long getRequestId() {
            return instance.getRequestId();
        }

        /**
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
         * <code>int64 request_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestId() {
            copyOnWrite();
            instance.clearRequestId();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteGroupJoinRequestRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"requestId_",};
                java.lang.String info =
                        "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0002";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteGroupJoinRequestRequest)
    private static final im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest DEFAULT_INSTANCE;

    static {
        DeleteGroupJoinRequestRequest defaultInstance = new DeleteGroupJoinRequestRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(DeleteGroupJoinRequestRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<DeleteGroupJoinRequestRequest> PARSER;

    public static com.google.protobuf.Parser<DeleteGroupJoinRequestRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}