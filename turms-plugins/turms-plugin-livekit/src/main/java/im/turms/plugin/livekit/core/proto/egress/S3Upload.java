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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf type {@code livekit.S3Upload}
 */
public final class S3Upload extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.S3Upload)
        S3UploadOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                S3Upload.class.getName());
    }

    // Use S3Upload.newBuilder() to construct.
    private S3Upload(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private S3Upload() {
        accessKey_ = "";
        secret_ = "";
        region_ = "";
        endpoint_ = "";
        bucket_ = "";
        tagging_ = "";
        contentDisposition_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 7 -> internalGetMetadata();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.S3Upload.class,
                        im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder.class);
    }

    private int bitField0_;
    public static final int ACCESS_KEY_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object accessKey_ = "";

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The accessKey.
     */
    @java.lang.Override
    public java.lang.String getAccessKey() {
        java.lang.Object ref = accessKey_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            accessKey_ = s;
            return s;
        }
    }

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The bytes for accessKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccessKeyBytes() {
        java.lang.Object ref = accessKey_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            accessKey_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SECRET_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object secret_ = "";

    /**
     * <code>string secret = 2;</code>
     *
     * @return The secret.
     */
    @java.lang.Override
    public java.lang.String getSecret() {
        java.lang.Object ref = secret_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            secret_ = s;
            return s;
        }
    }

    /**
     * <code>string secret = 2;</code>
     *
     * @return The bytes for secret.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSecretBytes() {
        java.lang.Object ref = secret_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            secret_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int REGION_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object region_ = "";

    /**
     * <code>string region = 3;</code>
     *
     * @return The region.
     */
    @java.lang.Override
    public java.lang.String getRegion() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            region_ = s;
            return s;
        }
    }

    /**
     * <code>string region = 3;</code>
     *
     * @return The bytes for region.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRegionBytes() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            region_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ENDPOINT_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object endpoint_ = "";

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The endpoint.
     */
    @java.lang.Override
    public java.lang.String getEndpoint() {
        java.lang.Object ref = endpoint_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            endpoint_ = s;
            return s;
        }
    }

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The bytes for endpoint.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEndpointBytes() {
        java.lang.Object ref = endpoint_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            endpoint_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BUCKET_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object bucket_ = "";

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bucket.
     */
    @java.lang.Override
    public java.lang.String getBucket() {
        java.lang.Object ref = bucket_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            bucket_ = s;
            return s;
        }
    }

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bytes for bucket.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getBucketBytes() {
        java.lang.Object ref = bucket_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            bucket_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int FORCE_PATH_STYLE_FIELD_NUMBER = 6;
    private boolean forcePathStyle_ = false;

    /**
     * <code>bool force_path_style = 6;</code>
     *
     * @return The forcePathStyle.
     */
    @java.lang.Override
    public boolean getForcePathStyle() {
        return forcePathStyle_;
    }

    public static final int METADATA_FIELD_NUMBER = 7;

    private static final class MetadataDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_MetadataEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, java.lang.String> metadata_;

    private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMetadata() {
        if (metadata_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(MetadataDefaultEntryHolder.defaultEntry);
        }
        return metadata_;
    }

    public int getMetadataCount() {
        return internalGetMetadata().getMap()
                .size();
    }

    /**
     * <code>map&lt;string, string&gt; metadata = 7;</code>
     */
    @java.lang.Override
    public boolean containsMetadata(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetMetadata().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getMetadataMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getMetadata() {
        return getMetadataMap();
    }

    /**
     * <code>map&lt;string, string&gt; metadata = 7;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getMetadataMap() {
        return internalGetMetadata().getMap();
    }

    /**
     * <code>map&lt;string, string&gt; metadata = 7;</code>
     */
    @java.lang.Override
    public /* nullable */
    java.lang.String getMetadataOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetMetadata().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;string, string&gt; metadata = 7;</code>
     */
    @java.lang.Override
    public java.lang.String getMetadataOrThrow(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetMetadata().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int TAGGING_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object tagging_ = "";

    /**
     * <code>string tagging = 8;</code>
     *
     * @return The tagging.
     */
    @java.lang.Override
    public java.lang.String getTagging() {
        java.lang.Object ref = tagging_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            tagging_ = s;
            return s;
        }
    }

    /**
     * <code>string tagging = 8;</code>
     *
     * @return The bytes for tagging.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTaggingBytes() {
        java.lang.Object ref = tagging_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            tagging_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int CONTENT_DISPOSITION_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object contentDisposition_ = "";

    /**
     * <pre>
     * Content-Disposition header
     * </pre>
     *
     * <code>string content_disposition = 9;</code>
     *
     * @return The contentDisposition.
     */
    @java.lang.Override
    public java.lang.String getContentDisposition() {
        java.lang.Object ref = contentDisposition_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            contentDisposition_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * Content-Disposition header
     * </pre>
     *
     * <code>string content_disposition = 9;</code>
     *
     * @return The bytes for contentDisposition.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContentDispositionBytes() {
        java.lang.Object ref = contentDisposition_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            contentDisposition_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PROXY_FIELD_NUMBER = 10;
    private im.turms.plugin.livekit.core.proto.egress.ProxyConfig proxy_;

    /**
     * <code>.livekit.ProxyConfig proxy = 10;</code>
     *
     * @return Whether the proxy field is set.
     */
    @java.lang.Override
    public boolean hasProxy() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.ProxyConfig proxy = 10;</code>
     *
     * @return The proxy.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getProxy() {
        return proxy_ == null
                ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                : proxy_;
    }

    /**
     * <code>.livekit.ProxyConfig proxy = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder getProxyOrBuilder() {
        return proxy_ == null
                ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                : proxy_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accessKey_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, accessKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(secret_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, secret_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(endpoint_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, endpoint_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, bucket_);
        }
        if (forcePathStyle_) {
            output.writeBool(6, forcePathStyle_);
        }
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetMetadata(),
                MetadataDefaultEntryHolder.defaultEntry,
                7);
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(tagging_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, tagging_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(contentDisposition_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, contentDisposition_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(10, getProxy());
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accessKey_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, accessKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(secret_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, secret_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(endpoint_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, endpoint_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, bucket_);
        }
        if (forcePathStyle_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(6, forcePathStyle_);
        }
        for (java.util.Map.Entry<java.lang.String, java.lang.String> entry : internalGetMetadata()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String> metadata__ =
                    MetadataDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, metadata__);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(tagging_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, tagging_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(contentDisposition_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, contentDisposition_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10, getProxy());
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
        if (!(obj instanceof S3Upload other)) {
            return super.equals(obj);
        }

        if (!getAccessKey().equals(other.getAccessKey())) {
            return false;
        }
        if (!getSecret().equals(other.getSecret())) {
            return false;
        }
        if (!getRegion().equals(other.getRegion())) {
            return false;
        }
        if (!getEndpoint().equals(other.getEndpoint())) {
            return false;
        }
        if (!getBucket().equals(other.getBucket())) {
            return false;
        }
        if (getForcePathStyle() != other.getForcePathStyle()) {
            return false;
        }
        if (!internalGetMetadata().equals(other.internalGetMetadata())) {
            return false;
        }
        if (!getTagging().equals(other.getTagging())) {
            return false;
        }
        if (!getContentDisposition().equals(other.getContentDisposition())) {
            return false;
        }
        if (hasProxy() != other.hasProxy()) {
            return false;
        }
        if (hasProxy()) {
            if (!getProxy().equals(other.getProxy())) {
                return false;
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + ACCESS_KEY_FIELD_NUMBER;
        hash = (53 * hash) + getAccessKey().hashCode();
        hash = (37 * hash) + SECRET_FIELD_NUMBER;
        hash = (53 * hash) + getSecret().hashCode();
        hash = (37 * hash) + REGION_FIELD_NUMBER;
        hash = (53 * hash) + getRegion().hashCode();
        hash = (37 * hash) + ENDPOINT_FIELD_NUMBER;
        hash = (53 * hash) + getEndpoint().hashCode();
        hash = (37 * hash) + BUCKET_FIELD_NUMBER;
        hash = (53 * hash) + getBucket().hashCode();
        hash = (37 * hash) + FORCE_PATH_STYLE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getForcePathStyle());
        if (!internalGetMetadata().getMap()
                .isEmpty()) {
            hash = (37 * hash) + METADATA_FIELD_NUMBER;
            hash = (53 * hash) + internalGetMetadata().hashCode();
        }
        hash = (37 * hash) + TAGGING_FIELD_NUMBER;
        hash = (53 * hash) + getTagging().hashCode();
        hash = (37 * hash) + CONTENT_DISPOSITION_FIELD_NUMBER;
        hash = (53 * hash) + getContentDisposition().hashCode();
        if (hasProxy()) {
            hash = (37 * hash) + PROXY_FIELD_NUMBER;
            hash = (53 * hash) + getProxy().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.egress.S3Upload prototype) {
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
     * Protobuf type {@code livekit.S3Upload}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.S3Upload)
            im.turms.plugin.livekit.core.proto.egress.S3UploadOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 7 -> internalGetMetadata();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 7 -> internalGetMutableMetadata();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.S3Upload.class,
                            im.turms.plugin.livekit.core.proto.egress.S3Upload.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.S3Upload.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getProxyFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            accessKey_ = "";
            secret_ = "";
            region_ = "";
            endpoint_ = "";
            bucket_ = "";
            forcePathStyle_ = false;
            internalGetMutableMetadata().clear();
            tagging_ = "";
            contentDisposition_ = "";
            proxy_ = null;
            if (proxyBuilder_ != null) {
                proxyBuilder_.dispose();
                proxyBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_S3Upload_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload build() {
            im.turms.plugin.livekit.core.proto.egress.S3Upload result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.S3Upload buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.S3Upload result =
                    new im.turms.plugin.livekit.core.proto.egress.S3Upload(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.S3Upload result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.accessKey_ = accessKey_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.secret_ = secret_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.region_ = region_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.endpoint_ = endpoint_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.bucket_ = bucket_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.forcePathStyle_ = forcePathStyle_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.metadata_ = internalGetMetadata();
                result.metadata_.makeImmutable();
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.tagging_ = tagging_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.contentDisposition_ = contentDisposition_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.proxy_ = proxyBuilder_ == null
                        ? proxy_
                        : proxyBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.S3Upload) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.S3Upload) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.S3Upload other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.S3Upload.getDefaultInstance()) {
                return this;
            }
            if (!other.getAccessKey()
                    .isEmpty()) {
                accessKey_ = other.accessKey_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getSecret()
                    .isEmpty()) {
                secret_ = other.secret_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getRegion()
                    .isEmpty()) {
                region_ = other.region_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (!other.getEndpoint()
                    .isEmpty()) {
                endpoint_ = other.endpoint_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (!other.getBucket()
                    .isEmpty()) {
                bucket_ = other.bucket_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (other.getForcePathStyle()) {
                setForcePathStyle(other.getForcePathStyle());
            }
            internalGetMutableMetadata().mergeFrom(other.internalGetMetadata());
            bitField0_ |= 0x00000040;
            if (!other.getTagging()
                    .isEmpty()) {
                tagging_ = other.tagging_;
                bitField0_ |= 0x00000080;
                onChanged();
            }
            if (!other.getContentDisposition()
                    .isEmpty()) {
                contentDisposition_ = other.contentDisposition_;
                bitField0_ |= 0x00000100;
                onChanged();
            }
            if (other.hasProxy()) {
                mergeProxy(other.getProxy());
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
                        case 10 -> {
                            accessKey_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            secret_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            region_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            endpoint_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            bucket_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 48 -> {
                            forcePathStyle_ = input.readBool();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 58 -> {
                            com.google.protobuf.MapEntry<String, String> metadata__ =
                                    input.readMessage(
                                            MetadataDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableMetadata().getMutableMap()
                                    .put(metadata__.getKey(), metadata__.getValue());
                            bitField0_ |= 0x00000040;
                        } // case 58
                        case 66 -> {
                            tagging_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000080;
                        } // case 66
                        case 74 -> {
                            contentDisposition_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000100;
                        } // case 74
                        case 82 -> {
                            input.readMessage(getProxyFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000200;
                        } // case 82
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

        private java.lang.Object accessKey_ = "";

        /**
         * <code>string access_key = 1;</code>
         *
         * @return The accessKey.
         */
        public java.lang.String getAccessKey() {
            java.lang.Object ref = accessKey_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                accessKey_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @return The bytes for accessKey.
         */
        public com.google.protobuf.ByteString getAccessKeyBytes() {
            java.lang.Object ref = accessKey_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                accessKey_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @param value The accessKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccessKey(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            accessKey_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccessKey() {
            accessKey_ = getDefaultInstance().getAccessKey();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @param value The bytes for accessKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccessKeyBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            accessKey_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object secret_ = "";

        /**
         * <code>string secret = 2;</code>
         *
         * @return The secret.
         */
        public java.lang.String getSecret() {
            java.lang.Object ref = secret_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                secret_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @return The bytes for secret.
         */
        public com.google.protobuf.ByteString getSecretBytes() {
            java.lang.Object ref = secret_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                secret_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @param value The secret to set.
         * @return This builder for chaining.
         */
        public Builder setSecret(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            secret_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSecret() {
            secret_ = getDefaultInstance().getSecret();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @param value The bytes for secret to set.
         * @return This builder for chaining.
         */
        public Builder setSecretBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            secret_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object region_ = "";

        /**
         * <code>string region = 3;</code>
         *
         * @return The region.
         */
        public java.lang.String getRegion() {
            java.lang.Object ref = region_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                region_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string region = 3;</code>
         *
         * @return The bytes for region.
         */
        public com.google.protobuf.ByteString getRegionBytes() {
            java.lang.Object ref = region_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                region_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string region = 3;</code>
         *
         * @param value The region to set.
         * @return This builder for chaining.
         */
        public Builder setRegion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            region_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegion() {
            region_ = getDefaultInstance().getRegion();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 3;</code>
         *
         * @param value The bytes for region to set.
         * @return This builder for chaining.
         */
        public Builder setRegionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            region_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object endpoint_ = "";

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return The endpoint.
         */
        public java.lang.String getEndpoint() {
            java.lang.Object ref = endpoint_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                endpoint_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return The bytes for endpoint.
         */
        public com.google.protobuf.ByteString getEndpointBytes() {
            java.lang.Object ref = endpoint_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                endpoint_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @param value The endpoint to set.
         * @return This builder for chaining.
         */
        public Builder setEndpoint(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            endpoint_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndpoint() {
            endpoint_ = getDefaultInstance().getEndpoint();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @param value The bytes for endpoint to set.
         * @return This builder for chaining.
         */
        public Builder setEndpointBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            endpoint_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object bucket_ = "";

        /**
         * <code>string bucket = 5;</code>
         *
         * @return The bucket.
         */
        public java.lang.String getBucket() {
            java.lang.Object ref = bucket_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                bucket_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @return The bytes for bucket.
         */
        public com.google.protobuf.ByteString getBucketBytes() {
            java.lang.Object ref = bucket_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                bucket_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @param value The bucket to set.
         * @return This builder for chaining.
         */
        public Builder setBucket(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bucket_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBucket() {
            bucket_ = getDefaultInstance().getBucket();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @param value The bytes for bucket to set.
         * @return This builder for chaining.
         */
        public Builder setBucketBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            bucket_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private boolean forcePathStyle_;

        /**
         * <code>bool force_path_style = 6;</code>
         *
         * @return The forcePathStyle.
         */
        @java.lang.Override
        public boolean getForcePathStyle() {
            return forcePathStyle_;
        }

        /**
         * <code>bool force_path_style = 6;</code>
         *
         * @param value The forcePathStyle to set.
         * @return This builder for chaining.
         */
        public Builder setForcePathStyle(boolean value) {

            forcePathStyle_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>bool force_path_style = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearForcePathStyle() {
            bitField0_ &= ~0x00000020;
            forcePathStyle_ = false;
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> metadata_;

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMetadata() {
            if (metadata_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(MetadataDefaultEntryHolder.defaultEntry);
            }
            return metadata_;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMutableMetadata() {
            if (metadata_ == null) {
                metadata_ = com.google.protobuf.MapField
                        .newMapField(MetadataDefaultEntryHolder.defaultEntry);
            }
            if (!metadata_.isMutable()) {
                metadata_ = metadata_.copy();
            }
            bitField0_ |= 0x00000040;
            onChanged();
            return metadata_;
        }

        public int getMetadataCount() {
            return internalGetMetadata().getMap()
                    .size();
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        @java.lang.Override
        public boolean containsMetadata(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetMetadata().getMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getMetadataMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getMetadata() {
            return getMetadataMap();
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, java.lang.String> getMetadataMap() {
            return internalGetMetadata().getMap();
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        @java.lang.Override
        public /* nullable */
        java.lang.String getMetadataOrDefault(
                java.lang.String key,
                /* nullable */
                java.lang.String defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetMetadata().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        @java.lang.Override
        public java.lang.String getMetadataOrThrow(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetMetadata().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearMetadata() {
            bitField0_ &= ~0x00000040;
            internalGetMutableMetadata().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        public Builder removeMetadata(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableMetadata().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getMutableMetadata() {
            bitField0_ |= 0x00000040;
            return internalGetMutableMetadata().getMutableMap();
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        public Builder putMetadata(java.lang.String key, java.lang.String value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableMetadata().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000040;
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; metadata = 7;</code>
         */
        public Builder putAllMetadata(java.util.Map<java.lang.String, java.lang.String> values) {
            internalGetMutableMetadata().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000040;
            return this;
        }

        private java.lang.Object tagging_ = "";

        /**
         * <code>string tagging = 8;</code>
         *
         * @return The tagging.
         */
        public java.lang.String getTagging() {
            java.lang.Object ref = tagging_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                tagging_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string tagging = 8;</code>
         *
         * @return The bytes for tagging.
         */
        public com.google.protobuf.ByteString getTaggingBytes() {
            java.lang.Object ref = tagging_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                tagging_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string tagging = 8;</code>
         *
         * @param value The tagging to set.
         * @return This builder for chaining.
         */
        public Builder setTagging(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            tagging_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string tagging = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTagging() {
            tagging_ = getDefaultInstance().getTagging();
            bitField0_ &= ~0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string tagging = 8;</code>
         *
         * @param value The bytes for tagging to set.
         * @return This builder for chaining.
         */
        public Builder setTaggingBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            tagging_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        private java.lang.Object contentDisposition_ = "";

        /**
         * <pre>
         * Content-Disposition header
         * </pre>
         *
         * <code>string content_disposition = 9;</code>
         *
         * @return The contentDisposition.
         */
        public java.lang.String getContentDisposition() {
            java.lang.Object ref = contentDisposition_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                contentDisposition_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * Content-Disposition header
         * </pre>
         *
         * <code>string content_disposition = 9;</code>
         *
         * @return The bytes for contentDisposition.
         */
        public com.google.protobuf.ByteString getContentDispositionBytes() {
            java.lang.Object ref = contentDisposition_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                contentDisposition_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * Content-Disposition header
         * </pre>
         *
         * <code>string content_disposition = 9;</code>
         *
         * @param value The contentDisposition to set.
         * @return This builder for chaining.
         */
        public Builder setContentDisposition(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            contentDisposition_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Content-Disposition header
         * </pre>
         *
         * <code>string content_disposition = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContentDisposition() {
            contentDisposition_ = getDefaultInstance().getContentDisposition();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Content-Disposition header
         * </pre>
         *
         * <code>string content_disposition = 9;</code>
         *
         * @param value The bytes for contentDisposition to set.
         * @return This builder for chaining.
         */
        public Builder setContentDispositionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            contentDisposition_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.egress.ProxyConfig proxy_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ProxyConfig, im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder, im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder> proxyBuilder_;

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         *
         * @return Whether the proxy field is set.
         */
        public boolean hasProxy() {
            return ((bitField0_ & 0x00000200) != 0);
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         *
         * @return The proxy.
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getProxy() {
            if (proxyBuilder_ == null) {
                return proxy_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                        : proxy_;
            } else {
                return proxyBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public Builder setProxy(im.turms.plugin.livekit.core.proto.egress.ProxyConfig value) {
            if (proxyBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                proxy_ = value;
            } else {
                proxyBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public Builder setProxy(
                im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder builderForValue) {
            if (proxyBuilder_ == null) {
                proxy_ = builderForValue.build();
            } else {
                proxyBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public Builder mergeProxy(im.turms.plugin.livekit.core.proto.egress.ProxyConfig value) {
            if (proxyBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)
                        && proxy_ != null
                        && proxy_ != im.turms.plugin.livekit.core.proto.egress.ProxyConfig
                                .getDefaultInstance()) {
                    getProxyBuilder().mergeFrom(value);
                } else {
                    proxy_ = value;
                }
            } else {
                proxyBuilder_.mergeFrom(value);
            }
            if (proxy_ != null) {
                bitField0_ |= 0x00000200;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public Builder clearProxy() {
            bitField0_ &= ~0x00000200;
            proxy_ = null;
            if (proxyBuilder_ != null) {
                proxyBuilder_.dispose();
                proxyBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder getProxyBuilder() {
            bitField0_ |= 0x00000200;
            onChanged();
            return getProxyFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder getProxyOrBuilder() {
            if (proxyBuilder_ != null) {
                return proxyBuilder_.getMessageOrBuilder();
            } else {
                return proxy_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                        : proxy_;
            }
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 10;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ProxyConfig, im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder, im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder> getProxyFieldBuilder() {
            if (proxyBuilder_ == null) {
                proxyBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getProxy(),
                        getParentForChildren(),
                        isClean());
                proxy_ = null;
            }
            return proxyBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.S3Upload)
    }

    // @@protoc_insertion_point(class_scope:livekit.S3Upload)
    private static final im.turms.plugin.livekit.core.proto.egress.S3Upload DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.S3Upload();
    }

    public static im.turms.plugin.livekit.core.proto.egress.S3Upload getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<S3Upload> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public S3Upload parsePartialFrom(
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

    public static com.google.protobuf.Parser<S3Upload> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<S3Upload> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.S3Upload getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}