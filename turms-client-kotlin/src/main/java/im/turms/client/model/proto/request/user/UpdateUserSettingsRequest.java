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

package im.turms.client.model.proto.request.user;

/**
 * Protobuf type {@code im.turms.proto.UpdateUserSettingsRequest}
 */
public final class UpdateUserSettingsRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateUserSettingsRequest, UpdateUserSettingsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateUserSettingsRequest)
        UpdateUserSettingsRequestOrBuilder {
    private UpdateUserSettingsRequest() {
        customAttributes_ = emptyProtobufList();
    }

    public static final int SETTINGS_FIELD_NUMBER = 1;

    private static final class SettingsDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.String, im.turms.client.model.proto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntryLite.newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.MESSAGE,
                        im.turms.client.model.proto.model.common.Value.getDefaultInstance());
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> settings_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> internalGetSettings() {
        return settings_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> internalGetMutableSettings() {
        if (!settings_.isMutable()) {
            settings_ = settings_.mutableCopy();
        }
        return settings_;
    }

    @java.lang.Override

    public int getSettingsCount() {
        return internalGetSettings().size();
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    @java.lang.Override

    public boolean containsSettings(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        return internalGetSettings().containsKey(key);
    }

    /**
     * Use {@link #getSettingsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getSettings() {
        return getSettingsMap();
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getSettingsMap() {
        return java.util.Collections.unmodifiableMap(internalGetSettings());
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    @java.lang.Override

    public /* nullable */
    im.turms.client.model.proto.model.common.Value getSettingsOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.client.model.proto.model.common.Value defaultValue) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                internalGetSettings();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    @java.lang.Override

    public im.turms.client.model.proto.model.common.Value getSettingsOrThrow(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                internalGetSettings();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    private java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getMutableSettingsMap() {
        return internalGetMutableSettings();
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

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest parseFrom(
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
            im.turms.client.model.proto.request.user.UpdateUserSettingsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateUserSettingsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.UpdateUserSettingsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateUserSettingsRequest)
            im.turms.client.model.proto.request.user.UpdateUserSettingsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.UpdateUserSettingsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        @java.lang.Override

        public int getSettingsCount() {
            return instance.getSettingsMap()
                    .size();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        @java.lang.Override

        public boolean containsSettings(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            return instance.getSettingsMap()
                    .containsKey(key);
        }

        public Builder clearSettings() {
            copyOnWrite();
            instance.getMutableSettingsMap()
                    .clear();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */

        public Builder removeSettings(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            copyOnWrite();
            instance.getMutableSettingsMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getSettingsMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getSettings() {
            return getSettingsMap();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getSettingsMap() {
            return java.util.Collections.unmodifiableMap(instance.getSettingsMap());
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        @java.lang.Override

        public /* nullable */
        im.turms.client.model.proto.model.common.Value getSettingsOrDefault(
                java.lang.String key,
                /* nullable */
                im.turms.client.model.proto.model.common.Value defaultValue) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                    instance.getSettingsMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        @java.lang.Override

        public im.turms.client.model.proto.model.common.Value getSettingsOrThrow(
                java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                    instance.getSettingsMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        public Builder putSettings(
                java.lang.String key,
                im.turms.client.model.proto.model.common.Value value) {
            java.lang.Class<?> keyClass = key.getClass();
            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableSettingsMap()
                    .put(key, value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        public Builder putAllSettings(
                java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.getMutableSettingsMap()
                    .putAll(values);
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateUserSettingsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.UpdateUserSettingsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"settings_",
                        SettingsDefaultEntryHolder.defaultEntry,
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u000f\u0002\u0001\u0001\u0000\u00012\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.UpdateUserSettingsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.UpdateUserSettingsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateUserSettingsRequest)
    private static final im.turms.client.model.proto.request.user.UpdateUserSettingsRequest DEFAULT_INSTANCE;
    static {
        UpdateUserSettingsRequest defaultInstance = new UpdateUserSettingsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateUserSettingsRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserSettingsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateUserSettingsRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateUserSettingsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}