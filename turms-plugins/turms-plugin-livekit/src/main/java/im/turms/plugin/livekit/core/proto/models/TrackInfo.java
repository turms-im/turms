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

package im.turms.plugin.livekit.core.proto.models;

/**
 * Protobuf type {@code livekit.TrackInfo}
 */
public final class TrackInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.TrackInfo)
        TrackInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                TrackInfo.class.getName());
    }

    // Use TrackInfo.newBuilder() to construct.
    private TrackInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private TrackInfo() {
        sid_ = "";
        type_ = 0;
        name_ = "";
        source_ = 0;
        layers_ = java.util.Collections.emptyList();
        mimeType_ = "";
        mid_ = "";
        codecs_ = java.util.Collections.emptyList();
        encryption_ = 0;
        stream_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TrackInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TrackInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.TrackInfo.class,
                        im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder.class);
    }

    private int bitField0_;
    public static final int SID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object sid_ = "";

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    @java.lang.Override
    public java.lang.String getSid() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            sid_ = s;
            return s;
        }
    }

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSidBytes() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            sid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    private int type_ = 0;

    /**
     * <code>.livekit.TrackType type = 2;</code>
     *
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override
    public int getTypeValue() {
        return type_;
    }

    /**
     * <code>.livekit.TrackType type = 2;</code>
     *
     * @return The type.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackType getType() {
        im.turms.plugin.livekit.core.proto.models.TrackType result =
                im.turms.plugin.livekit.core.proto.models.TrackType.forNumber(type_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.TrackType.UNRECOGNIZED
                : result;
    }

    public static final int NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>string name = 3;</code>
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
     * <code>string name = 3;</code>
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

    public static final int MUTED_FIELD_NUMBER = 4;
    private boolean muted_ = false;

    /**
     * <code>bool muted = 4;</code>
     *
     * @return The muted.
     */
    @java.lang.Override
    public boolean getMuted() {
        return muted_;
    }

    public static final int WIDTH_FIELD_NUMBER = 5;
    private int width_ = 0;

    /**
     * <pre>
     * original width of video (unset for audio)
     * clients may receive a lower resolution version with simulcast
     * </pre>
     *
     * <code>uint32 width = 5;</code>
     *
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
        return width_;
    }

    public static final int HEIGHT_FIELD_NUMBER = 6;
    private int height_ = 0;

    /**
     * <pre>
     * original height of video (unset for audio)
     * </pre>
     *
     * <code>uint32 height = 6;</code>
     *
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
        return height_;
    }

    public static final int SIMULCAST_FIELD_NUMBER = 7;
    private boolean simulcast_ = false;

    /**
     * <pre>
     * true if track is simulcasted
     * </pre>
     *
     * <code>bool simulcast = 7;</code>
     *
     * @return The simulcast.
     */
    @java.lang.Override
    public boolean getSimulcast() {
        return simulcast_;
    }

    public static final int DISABLE_DTX_FIELD_NUMBER = 8;
    private boolean disableDtx_ = false;

    /**
     * <pre>
     * true if DTX (Discontinuous Transmission) is disabled for audio
     * </pre>
     *
     * <code>bool disable_dtx = 8;</code>
     *
     * @return The disableDtx.
     */
    @java.lang.Override
    public boolean getDisableDtx() {
        return disableDtx_;
    }

    public static final int SOURCE_FIELD_NUMBER = 9;
    private int source_ = 0;

    /**
     * <pre>
     * source of media
     * </pre>
     *
     * <code>.livekit.TrackSource source = 9;</code>
     *
     * @return The enum numeric value on the wire for source.
     */
    @java.lang.Override
    public int getSourceValue() {
        return source_;
    }

    /**
     * <pre>
     * source of media
     * </pre>
     *
     * <code>.livekit.TrackSource source = 9;</code>
     *
     * @return The source.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackSource getSource() {
        im.turms.plugin.livekit.core.proto.models.TrackSource result =
                im.turms.plugin.livekit.core.proto.models.TrackSource.forNumber(source_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.TrackSource.UNRECOGNIZED
                : result;
    }

    public static final int LAYERS_FIELD_NUMBER = 10;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_;

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
        return layers_;
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
        return layers_;
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    @java.lang.Override
    public int getLayersCount() {
        return layers_.size();
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
        return layers_.get(index);
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(
            int index) {
        return layers_.get(index);
    }

    public static final int MIME_TYPE_FIELD_NUMBER = 11;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mimeType_ = "";

    /**
     * <pre>
     * mime type of codec
     * </pre>
     *
     * <code>string mime_type = 11;</code>
     *
     * @return The mimeType.
     */
    @java.lang.Override
    public java.lang.String getMimeType() {
        java.lang.Object ref = mimeType_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mimeType_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * mime type of codec
     * </pre>
     *
     * <code>string mime_type = 11;</code>
     *
     * @return The bytes for mimeType.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMimeTypeBytes() {
        java.lang.Object ref = mimeType_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mimeType_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int MID_FIELD_NUMBER = 12;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mid_ = "";

    /**
     * <code>string mid = 12;</code>
     *
     * @return The mid.
     */
    @java.lang.Override
    public java.lang.String getMid() {
        java.lang.Object ref = mid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mid_ = s;
            return s;
        }
    }

    /**
     * <code>string mid = 12;</code>
     *
     * @return The bytes for mid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMidBytes() {
        java.lang.Object ref = mid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int CODECS_FIELD_NUMBER = 13;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> codecs_;

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> getCodecsList() {
        return codecs_;
    }

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder> getCodecsOrBuilderList() {
        return codecs_;
    }

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    @java.lang.Override
    public int getCodecsCount() {
        return codecs_.size();
    }

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getCodecs(int index) {
        return codecs_.get(index);
    }

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder getCodecsOrBuilder(
            int index) {
        return codecs_.get(index);
    }

    public static final int STEREO_FIELD_NUMBER = 14;
    private boolean stereo_ = false;

    /**
     * <code>bool stereo = 14;</code>
     *
     * @return The stereo.
     */
    @java.lang.Override
    public boolean getStereo() {
        return stereo_;
    }

    public static final int DISABLE_RED_FIELD_NUMBER = 15;
    private boolean disableRed_ = false;

    /**
     * <pre>
     * true if RED (Redundant Encoding) is disabled for audio
     * </pre>
     *
     * <code>bool disable_red = 15;</code>
     *
     * @return The disableRed.
     */
    @java.lang.Override
    public boolean getDisableRed() {
        return disableRed_;
    }

    public static final int ENCRYPTION_FIELD_NUMBER = 16;
    private int encryption_ = 0;

    /**
     * <code>.livekit.Encryption.Type encryption = 16;</code>
     *
     * @return The enum numeric value on the wire for encryption.
     */
    @java.lang.Override
    public int getEncryptionValue() {
        return encryption_;
    }

    /**
     * <code>.livekit.Encryption.Type encryption = 16;</code>
     *
     * @return The encryption.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Encryption.Type getEncryption() {
        im.turms.plugin.livekit.core.proto.models.Encryption.Type result =
                im.turms.plugin.livekit.core.proto.models.Encryption.Type.forNumber(encryption_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.Encryption.Type.UNRECOGNIZED
                : result;
    }

    public static final int STREAM_FIELD_NUMBER = 17;
    @SuppressWarnings("serial")
    private volatile java.lang.Object stream_ = "";

    /**
     * <code>string stream = 17;</code>
     *
     * @return The stream.
     */
    @java.lang.Override
    public java.lang.String getStream() {
        java.lang.Object ref = stream_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            stream_ = s;
            return s;
        }
    }

    /**
     * <code>string stream = 17;</code>
     *
     * @return The bytes for stream.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getStreamBytes() {
        java.lang.Object ref = stream_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            stream_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int VERSION_FIELD_NUMBER = 18;
    private im.turms.plugin.livekit.core.proto.models.TimedVersion version_;

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     *
     * @return Whether the version field is set.
     */
    @java.lang.Override
    public boolean hasVersion() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion() {
        return version_ == null
                ? im.turms.plugin.livekit.core.proto.models.TimedVersion.getDefaultInstance()
                : version_;
    }

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder() {
        return version_ == null
                ? im.turms.plugin.livekit.core.proto.models.TimedVersion.getDefaultInstance()
                : version_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, sid_);
        }
        if (type_ != im.turms.plugin.livekit.core.proto.models.TrackType.AUDIO.getNumber()) {
            output.writeEnum(2, type_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, name_);
        }
        if (muted_) {
            output.writeBool(4, muted_);
        }
        if (width_ != 0) {
            output.writeUInt32(5, width_);
        }
        if (height_ != 0) {
            output.writeUInt32(6, height_);
        }
        if (simulcast_) {
            output.writeBool(7, simulcast_);
        }
        if (disableDtx_) {
            output.writeBool(8, disableDtx_);
        }
        if (source_ != im.turms.plugin.livekit.core.proto.models.TrackSource.UNKNOWN.getNumber()) {
            output.writeEnum(9, source_);
        }
        for (VideoLayer videoLayer : layers_) {
            output.writeMessage(10, videoLayer);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mimeType_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 11, mimeType_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 12, mid_);
        }
        for (SimulcastCodecInfo simulcastCodecInfo : codecs_) {
            output.writeMessage(13, simulcastCodecInfo);
        }
        if (stereo_) {
            output.writeBool(14, stereo_);
        }
        if (disableRed_) {
            output.writeBool(15, disableRed_);
        }
        if (encryption_ != im.turms.plugin.livekit.core.proto.models.Encryption.Type.NONE
                .getNumber()) {
            output.writeEnum(16, encryption_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(stream_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 17, stream_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(18, getVersion());
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, sid_);
        }
        if (type_ != im.turms.plugin.livekit.core.proto.models.TrackType.AUDIO.getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, type_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, name_);
        }
        if (muted_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(4, muted_);
        }
        if (width_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(5, width_);
        }
        if (height_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(6, height_);
        }
        if (simulcast_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(7, simulcast_);
        }
        if (disableDtx_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, disableDtx_);
        }
        if (source_ != im.turms.plugin.livekit.core.proto.models.TrackSource.UNKNOWN.getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(9, source_);
        }
        for (VideoLayer videoLayer : layers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10, videoLayer);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mimeType_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(11, mimeType_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(12, mid_);
        }
        for (SimulcastCodecInfo simulcastCodecInfo : codecs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(13,
                    simulcastCodecInfo);
        }
        if (stereo_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(14, stereo_);
        }
        if (disableRed_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(15, disableRed_);
        }
        if (encryption_ != im.turms.plugin.livekit.core.proto.models.Encryption.Type.NONE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(16, encryption_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(stream_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(17, stream_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(18, getVersion());
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
        if (!(obj instanceof TrackInfo other)) {
            return super.equals(obj);
        }

        if (!getSid().equals(other.getSid())) {
            return false;
        }
        if (type_ != other.type_) {
            return false;
        }
        if (!getName().equals(other.getName())) {
            return false;
        }
        if (getMuted() != other.getMuted()) {
            return false;
        }
        if (getWidth() != other.getWidth()) {
            return false;
        }
        if (getHeight() != other.getHeight()) {
            return false;
        }
        if (getSimulcast() != other.getSimulcast()) {
            return false;
        }
        if (getDisableDtx() != other.getDisableDtx()) {
            return false;
        }
        if (source_ != other.source_) {
            return false;
        }
        if (!getLayersList().equals(other.getLayersList())) {
            return false;
        }
        if (!getMimeType().equals(other.getMimeType())) {
            return false;
        }
        if (!getMid().equals(other.getMid())) {
            return false;
        }
        if (!getCodecsList().equals(other.getCodecsList())) {
            return false;
        }
        if (getStereo() != other.getStereo()) {
            return false;
        }
        if (getDisableRed() != other.getDisableRed()) {
            return false;
        }
        if (encryption_ != other.encryption_) {
            return false;
        }
        if (!getStream().equals(other.getStream())) {
            return false;
        }
        if (hasVersion() != other.hasVersion()) {
            return false;
        }
        if (hasVersion()) {
            if (!getVersion().equals(other.getVersion())) {
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
        hash = (37 * hash) + SID_FIELD_NUMBER;
        hash = (53 * hash) + getSid().hashCode();
        hash = (37 * hash) + TYPE_FIELD_NUMBER;
        hash = (53 * hash) + type_;
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + MUTED_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getMuted());
        hash = (37 * hash) + WIDTH_FIELD_NUMBER;
        hash = (53 * hash) + getWidth();
        hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getHeight();
        hash = (37 * hash) + SIMULCAST_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getSimulcast());
        hash = (37 * hash) + DISABLE_DTX_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableDtx());
        hash = (37 * hash) + SOURCE_FIELD_NUMBER;
        hash = (53 * hash) + source_;
        if (getLayersCount() > 0) {
            hash = (37 * hash) + LAYERS_FIELD_NUMBER;
            hash = (53 * hash) + getLayersList().hashCode();
        }
        hash = (37 * hash) + MIME_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getMimeType().hashCode();
        hash = (37 * hash) + MID_FIELD_NUMBER;
        hash = (53 * hash) + getMid().hashCode();
        if (getCodecsCount() > 0) {
            hash = (37 * hash) + CODECS_FIELD_NUMBER;
            hash = (53 * hash) + getCodecsList().hashCode();
        }
        hash = (37 * hash) + STEREO_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getStereo());
        hash = (37 * hash) + DISABLE_RED_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableRed());
        hash = (37 * hash) + ENCRYPTION_FIELD_NUMBER;
        hash = (53 * hash) + encryption_;
        hash = (37 * hash) + STREAM_FIELD_NUMBER;
        hash = (53 * hash) + getStream().hashCode();
        if (hasVersion()) {
            hash = (37 * hash) + VERSION_FIELD_NUMBER;
            hash = (53 * hash) + getVersion().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.TrackInfo prototype) {
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
     * Protobuf type {@code livekit.TrackInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.TrackInfo)
            im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TrackInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TrackInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.TrackInfo.class,
                            im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.TrackInfo.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getLayersFieldBuilder();
                getCodecsFieldBuilder();
                getVersionFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            sid_ = "";
            type_ = 0;
            name_ = "";
            muted_ = false;
            width_ = 0;
            height_ = 0;
            simulcast_ = false;
            disableDtx_ = false;
            source_ = 0;
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
            } else {
                layers_ = null;
                layersBuilder_.clear();
            }
            bitField0_ &= ~0x00000200;
            mimeType_ = "";
            mid_ = "";
            if (codecsBuilder_ == null) {
                codecs_ = java.util.Collections.emptyList();
            } else {
                codecs_ = null;
                codecsBuilder_.clear();
            }
            bitField0_ &= ~0x00001000;
            stereo_ = false;
            disableRed_ = false;
            encryption_ = 0;
            stream_ = "";
            version_ = null;
            if (versionBuilder_ != null) {
                versionBuilder_.dispose();
                versionBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TrackInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackInfo build() {
            im.turms.plugin.livekit.core.proto.models.TrackInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.TrackInfo result =
                    new im.turms.plugin.livekit.core.proto.models.TrackInfo(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.TrackInfo result) {
            if (layersBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)) {
                    layers_ = java.util.Collections.unmodifiableList(layers_);
                    bitField0_ &= ~0x00000200;
                }
                result.layers_ = layers_;
            } else {
                result.layers_ = layersBuilder_.build();
            }
            if (codecsBuilder_ == null) {
                if (((bitField0_ & 0x00001000) != 0)) {
                    codecs_ = java.util.Collections.unmodifiableList(codecs_);
                    bitField0_ &= ~0x00001000;
                }
                result.codecs_ = codecs_;
            } else {
                result.codecs_ = codecsBuilder_.build();
            }
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.TrackInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.sid_ = sid_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.type_ = type_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.muted_ = muted_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.width_ = width_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.height_ = height_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.simulcast_ = simulcast_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.disableDtx_ = disableDtx_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.source_ = source_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.mimeType_ = mimeType_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.mid_ = mid_;
            }
            if (((from_bitField0_ & 0x00002000) != 0)) {
                result.stereo_ = stereo_;
            }
            if (((from_bitField0_ & 0x00004000) != 0)) {
                result.disableRed_ = disableRed_;
            }
            if (((from_bitField0_ & 0x00008000) != 0)) {
                result.encryption_ = encryption_;
            }
            if (((from_bitField0_ & 0x00010000) != 0)) {
                result.stream_ = stream_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00020000) != 0)) {
                result.version_ = versionBuilder_ == null
                        ? version_
                        : versionBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.TrackInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.TrackInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.TrackInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()) {
                return this;
            }
            if (!other.getSid()
                    .isEmpty()) {
                sid_ = other.sid_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.type_ != 0) {
                setTypeValue(other.getTypeValue());
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.getMuted()) {
                setMuted(other.getMuted());
            }
            if (other.getWidth() != 0) {
                setWidth(other.getWidth());
            }
            if (other.getHeight() != 0) {
                setHeight(other.getHeight());
            }
            if (other.getSimulcast()) {
                setSimulcast(other.getSimulcast());
            }
            if (other.getDisableDtx()) {
                setDisableDtx(other.getDisableDtx());
            }
            if (other.source_ != 0) {
                setSourceValue(other.getSourceValue());
            }
            if (layersBuilder_ == null) {
                if (!other.layers_.isEmpty()) {
                    if (layers_.isEmpty()) {
                        layers_ = other.layers_;
                        bitField0_ &= ~0x00000200;
                    } else {
                        ensureLayersIsMutable();
                        layers_.addAll(other.layers_);
                    }
                    onChanged();
                }
            } else {
                if (!other.layers_.isEmpty()) {
                    if (layersBuilder_.isEmpty()) {
                        layersBuilder_.dispose();
                        layersBuilder_ = null;
                        layers_ = other.layers_;
                        bitField0_ &= ~0x00000200;
                        layersBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getLayersFieldBuilder()
                                : null;
                    } else {
                        layersBuilder_.addAllMessages(other.layers_);
                    }
                }
            }
            if (!other.getMimeType()
                    .isEmpty()) {
                mimeType_ = other.mimeType_;
                bitField0_ |= 0x00000400;
                onChanged();
            }
            if (!other.getMid()
                    .isEmpty()) {
                mid_ = other.mid_;
                bitField0_ |= 0x00000800;
                onChanged();
            }
            if (codecsBuilder_ == null) {
                if (!other.codecs_.isEmpty()) {
                    if (codecs_.isEmpty()) {
                        codecs_ = other.codecs_;
                        bitField0_ &= ~0x00001000;
                    } else {
                        ensureCodecsIsMutable();
                        codecs_.addAll(other.codecs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.codecs_.isEmpty()) {
                    if (codecsBuilder_.isEmpty()) {
                        codecsBuilder_.dispose();
                        codecsBuilder_ = null;
                        codecs_ = other.codecs_;
                        bitField0_ &= ~0x00001000;
                        codecsBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getCodecsFieldBuilder()
                                : null;
                    } else {
                        codecsBuilder_.addAllMessages(other.codecs_);
                    }
                }
            }
            if (other.getStereo()) {
                setStereo(other.getStereo());
            }
            if (other.getDisableRed()) {
                setDisableRed(other.getDisableRed());
            }
            if (other.encryption_ != 0) {
                setEncryptionValue(other.getEncryptionValue());
            }
            if (!other.getStream()
                    .isEmpty()) {
                stream_ = other.stream_;
                bitField0_ |= 0x00010000;
                onChanged();
            }
            if (other.hasVersion()) {
                mergeVersion(other.getVersion());
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
                            sid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            type_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            muted_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            width_ = input.readUInt32();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            height_ = input.readUInt32();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            simulcast_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            disableDtx_ = input.readBool();
                            bitField0_ |= 0x00000080;
                        } // case 64
                        case 72 -> {
                            source_ = input.readEnum();
                            bitField0_ |= 0x00000100;
                        } // case 72
                        case 82 -> {
                            VideoLayer m =
                                    input.readMessage(VideoLayer.parser(), extensionRegistry);
                            if (layersBuilder_ == null) {
                                ensureLayersIsMutable();
                                layers_.add(m);
                            } else {
                                layersBuilder_.addMessage(m);
                            }
                        } // case 82
                        case 90 -> {
                            mimeType_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000400;
                        } // case 90
                        case 98 -> {
                            mid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000800;
                        } // case 98
                        case 106 -> {
                            SimulcastCodecInfo m = input.readMessage(SimulcastCodecInfo.parser(),
                                    extensionRegistry);
                            if (codecsBuilder_ == null) {
                                ensureCodecsIsMutable();
                                codecs_.add(m);
                            } else {
                                codecsBuilder_.addMessage(m);
                            }
                        } // case 106
                        case 112 -> {
                            stereo_ = input.readBool();
                            bitField0_ |= 0x00002000;
                        } // case 112
                        case 120 -> {
                            disableRed_ = input.readBool();
                            bitField0_ |= 0x00004000;
                        } // case 120
                        case 128 -> {
                            encryption_ = input.readEnum();
                            bitField0_ |= 0x00008000;
                        } // case 128
                        case 138 -> {
                            stream_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00010000;
                        } // case 138
                        case 146 -> {
                            input.readMessage(getVersionFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00020000;
                        } // case 146
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

        private java.lang.Object sid_ = "";

        /**
         * <code>string sid = 1;</code>
         *
         * @return The sid.
         */
        public java.lang.String getSid() {
            java.lang.Object ref = sid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                sid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return The bytes for sid.
         */
        public com.google.protobuf.ByteString getSidBytes() {
            java.lang.Object ref = sid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                sid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The sid to set.
         * @return This builder for chaining.
         */
        public Builder setSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSid() {
            sid_ = getDefaultInstance().getSid();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The bytes for sid to set.
         * @return This builder for chaining.
         */
        public Builder setSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private int type_ = 0;

        /**
         * <code>.livekit.TrackType type = 2;</code>
         *
         * @return The enum numeric value on the wire for type.
         */
        @java.lang.Override
        public int getTypeValue() {
            return type_;
        }

        /**
         * <code>.livekit.TrackType type = 2;</code>
         *
         * @param value The enum numeric value on the wire for type to set.
         * @return This builder for chaining.
         */
        public Builder setTypeValue(int value) {
            type_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackType type = 2;</code>
         *
         * @return The type.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackType getType() {
            im.turms.plugin.livekit.core.proto.models.TrackType result =
                    im.turms.plugin.livekit.core.proto.models.TrackType.forNumber(type_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.TrackType.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.TrackType type = 2;</code>
         *
         * @param value The type to set.
         * @return This builder for chaining.
         */
        public Builder setType(im.turms.plugin.livekit.core.proto.models.TrackType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            type_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackType type = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearType() {
            bitField0_ &= ~0x00000002;
            type_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>string name = 3;</code>
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
         * <code>string name = 3;</code>
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
         * <code>string name = 3;</code>
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
         * <code>string name = 3;</code>
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
         * <code>string name = 3;</code>
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

        private boolean muted_;

        /**
         * <code>bool muted = 4;</code>
         *
         * @return The muted.
         */
        @java.lang.Override
        public boolean getMuted() {
            return muted_;
        }

        /**
         * <code>bool muted = 4;</code>
         *
         * @param value The muted to set.
         * @return This builder for chaining.
         */
        public Builder setMuted(boolean value) {

            muted_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>bool muted = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuted() {
            bitField0_ &= ~0x00000008;
            muted_ = false;
            onChanged();
            return this;
        }

        private int width_;

        /**
         * <pre>
         * original width of video (unset for audio)
         * clients may receive a lower resolution version with simulcast
         * </pre>
         *
         * <code>uint32 width = 5;</code>
         *
         * @return The width.
         */
        @java.lang.Override
        public int getWidth() {
            return width_;
        }

        /**
         * <pre>
         * original width of video (unset for audio)
         * clients may receive a lower resolution version with simulcast
         * </pre>
         *
         * <code>uint32 width = 5;</code>
         *
         * @param value The width to set.
         * @return This builder for chaining.
         */
        public Builder setWidth(int value) {

            width_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * original width of video (unset for audio)
         * clients may receive a lower resolution version with simulcast
         * </pre>
         *
         * <code>uint32 width = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWidth() {
            bitField0_ &= ~0x00000010;
            width_ = 0;
            onChanged();
            return this;
        }

        private int height_;

        /**
         * <pre>
         * original height of video (unset for audio)
         * </pre>
         *
         * <code>uint32 height = 6;</code>
         *
         * @return The height.
         */
        @java.lang.Override
        public int getHeight() {
            return height_;
        }

        /**
         * <pre>
         * original height of video (unset for audio)
         * </pre>
         *
         * <code>uint32 height = 6;</code>
         *
         * @param value The height to set.
         * @return This builder for chaining.
         */
        public Builder setHeight(int value) {

            height_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * original height of video (unset for audio)
         * </pre>
         *
         * <code>uint32 height = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeight() {
            bitField0_ &= ~0x00000020;
            height_ = 0;
            onChanged();
            return this;
        }

        private boolean simulcast_;

        /**
         * <pre>
         * true if track is simulcasted
         * </pre>
         *
         * <code>bool simulcast = 7;</code>
         *
         * @return The simulcast.
         */
        @java.lang.Override
        public boolean getSimulcast() {
            return simulcast_;
        }

        /**
         * <pre>
         * true if track is simulcasted
         * </pre>
         *
         * <code>bool simulcast = 7;</code>
         *
         * @param value The simulcast to set.
         * @return This builder for chaining.
         */
        public Builder setSimulcast(boolean value) {

            simulcast_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * true if track is simulcasted
         * </pre>
         *
         * <code>bool simulcast = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSimulcast() {
            bitField0_ &= ~0x00000040;
            simulcast_ = false;
            onChanged();
            return this;
        }

        private boolean disableDtx_;

        /**
         * <pre>
         * true if DTX (Discontinuous Transmission) is disabled for audio
         * </pre>
         *
         * <code>bool disable_dtx = 8;</code>
         *
         * @return The disableDtx.
         */
        @java.lang.Override
        public boolean getDisableDtx() {
            return disableDtx_;
        }

        /**
         * <pre>
         * true if DTX (Discontinuous Transmission) is disabled for audio
         * </pre>
         *
         * <code>bool disable_dtx = 8;</code>
         *
         * @param value The disableDtx to set.
         * @return This builder for chaining.
         */
        public Builder setDisableDtx(boolean value) {

            disableDtx_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * true if DTX (Discontinuous Transmission) is disabled for audio
         * </pre>
         *
         * <code>bool disable_dtx = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDisableDtx() {
            bitField0_ &= ~0x00000080;
            disableDtx_ = false;
            onChanged();
            return this;
        }

        private int source_ = 0;

        /**
         * <pre>
         * source of media
         * </pre>
         *
         * <code>.livekit.TrackSource source = 9;</code>
         *
         * @return The enum numeric value on the wire for source.
         */
        @java.lang.Override
        public int getSourceValue() {
            return source_;
        }

        /**
         * <pre>
         * source of media
         * </pre>
         *
         * <code>.livekit.TrackSource source = 9;</code>
         *
         * @param value The enum numeric value on the wire for source to set.
         * @return This builder for chaining.
         */
        public Builder setSourceValue(int value) {
            source_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * source of media
         * </pre>
         *
         * <code>.livekit.TrackSource source = 9;</code>
         *
         * @return The source.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackSource getSource() {
            im.turms.plugin.livekit.core.proto.models.TrackSource result =
                    im.turms.plugin.livekit.core.proto.models.TrackSource.forNumber(source_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.TrackSource.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * source of media
         * </pre>
         *
         * <code>.livekit.TrackSource source = 9;</code>
         *
         * @param value The source to set.
         * @return This builder for chaining.
         */
        public Builder setSource(im.turms.plugin.livekit.core.proto.models.TrackSource value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000100;
            source_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * source of media
         * </pre>
         *
         * <code>.livekit.TrackSource source = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSource() {
            bitField0_ &= ~0x00000100;
            source_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_ =
                java.util.Collections.emptyList();

        private void ensureLayersIsMutable() {
            if ((bitField0_ & 0x00000200) == 0) {
                layers_ = new java.util.ArrayList<>(layers_);
                bitField0_ |= 0x00000200;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> layersBuilder_;

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
            if (layersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(layers_);
            } else {
                return layersBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public int getLayersCount() {
            if (layersBuilder_ == null) {
                return layers_.size();
            } else {
                return layersBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
            if (layersBuilder_ == null) {
                return layers_.get(index);
            } else {
                return layersBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder setLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.set(index, value);
                onChanged();
            } else {
                layersBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder setLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.set(index, builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder addLayers(im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.add(value);
                onChanged();
            } else {
                layersBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder addLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.add(index, value);
                onChanged();
            } else {
                layersBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder addLayers(
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.add(builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder addLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.add(index, builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder addAllLayers(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.VideoLayer> values) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, layers_);
                onChanged();
            } else {
                layersBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder clearLayers() {
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000200;
                onChanged();
            } else {
                layersBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public Builder removeLayers(int index) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.remove(index);
                onChanged();
            } else {
                layersBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder getLayersBuilder(
                int index) {
            return getLayersFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(
                int index) {
            if (layersBuilder_ == null) {
                return layers_.get(index);
            } else {
                return layersBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
            if (layersBuilder_ != null) {
                return layersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(layers_);
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder() {
            return getLayersFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder(
                int index) {
            return getLayersFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 10;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder> getLayersBuilderList() {
            return getLayersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersFieldBuilder() {
            if (layersBuilder_ == null) {
                layersBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        layers_,
                        ((bitField0_ & 0x00000200) != 0),
                        getParentForChildren(),
                        isClean());
                layers_ = null;
            }
            return layersBuilder_;
        }

        private java.lang.Object mimeType_ = "";

        /**
         * <pre>
         * mime type of codec
         * </pre>
         *
         * <code>string mime_type = 11;</code>
         *
         * @return The mimeType.
         */
        public java.lang.String getMimeType() {
            java.lang.Object ref = mimeType_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mimeType_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * mime type of codec
         * </pre>
         *
         * <code>string mime_type = 11;</code>
         *
         * @return The bytes for mimeType.
         */
        public com.google.protobuf.ByteString getMimeTypeBytes() {
            java.lang.Object ref = mimeType_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mimeType_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * mime type of codec
         * </pre>
         *
         * <code>string mime_type = 11;</code>
         *
         * @param value The mimeType to set.
         * @return This builder for chaining.
         */
        public Builder setMimeType(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mimeType_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mime type of codec
         * </pre>
         *
         * <code>string mime_type = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMimeType() {
            mimeType_ = getDefaultInstance().getMimeType();
            bitField0_ &= ~0x00000400;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * mime type of codec
         * </pre>
         *
         * <code>string mime_type = 11;</code>
         *
         * @param value The bytes for mimeType to set.
         * @return This builder for chaining.
         */
        public Builder setMimeTypeBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mimeType_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        private java.lang.Object mid_ = "";

        /**
         * <code>string mid = 12;</code>
         *
         * @return The mid.
         */
        public java.lang.String getMid() {
            java.lang.Object ref = mid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string mid = 12;</code>
         *
         * @return The bytes for mid.
         */
        public com.google.protobuf.ByteString getMidBytes() {
            java.lang.Object ref = mid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string mid = 12;</code>
         *
         * @param value The mid to set.
         * @return This builder for chaining.
         */
        public Builder setMid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mid_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>string mid = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMid() {
            mid_ = getDefaultInstance().getMid();
            bitField0_ &= ~0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>string mid = 12;</code>
         *
         * @param value The bytes for mid to set.
         * @return This builder for chaining.
         */
        public Builder setMidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mid_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> codecs_ =
                java.util.Collections.emptyList();

        private void ensureCodecsIsMutable() {
            if ((bitField0_ & 0x00001000) == 0) {
                codecs_ = new java.util.ArrayList<>(codecs_);
                bitField0_ |= 0x00001000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo, im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder, im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder> codecsBuilder_;

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> getCodecsList() {
            if (codecsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(codecs_);
            } else {
                return codecsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public int getCodecsCount() {
            if (codecsBuilder_ == null) {
                return codecs_.size();
            } else {
                return codecsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getCodecs(int index) {
            if (codecsBuilder_ == null) {
                return codecs_.get(index);
            } else {
                return codecsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder setCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.set(index, value);
                onChanged();
            } else {
                codecsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder setCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.set(index, builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder addCodecs(
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.add(value);
                onChanged();
            } else {
                codecsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder addCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.add(index, value);
                onChanged();
            } else {
                codecsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder addCodecs(
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.add(builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder addCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.add(index, builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder addAllCodecs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> values) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, codecs_);
                onChanged();
            } else {
                codecsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder clearCodecs() {
            if (codecsBuilder_ == null) {
                codecs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00001000;
                onChanged();
            } else {
                codecsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public Builder removeCodecs(int index) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.remove(index);
                onChanged();
            } else {
                codecsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder getCodecsBuilder(
                int index) {
            return getCodecsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder getCodecsOrBuilder(
                int index) {
            if (codecsBuilder_ == null) {
                return codecs_.get(index);
            } else {
                return codecsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder> getCodecsOrBuilderList() {
            if (codecsBuilder_ != null) {
                return codecsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(codecs_);
            }
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder addCodecsBuilder() {
            return getCodecsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder addCodecsBuilder(
                int index) {
            return getCodecsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder> getCodecsBuilderList() {
            return getCodecsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo, im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder, im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder> getCodecsFieldBuilder() {
            if (codecsBuilder_ == null) {
                codecsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        codecs_,
                        ((bitField0_ & 0x00001000) != 0),
                        getParentForChildren(),
                        isClean());
                codecs_ = null;
            }
            return codecsBuilder_;
        }

        private boolean stereo_;

        /**
         * <code>bool stereo = 14;</code>
         *
         * @return The stereo.
         */
        @java.lang.Override
        public boolean getStereo() {
            return stereo_;
        }

        /**
         * <code>bool stereo = 14;</code>
         *
         * @param value The stereo to set.
         * @return This builder for chaining.
         */
        public Builder setStereo(boolean value) {

            stereo_ = value;
            bitField0_ |= 0x00002000;
            onChanged();
            return this;
        }

        /**
         * <code>bool stereo = 14;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStereo() {
            bitField0_ &= ~0x00002000;
            stereo_ = false;
            onChanged();
            return this;
        }

        private boolean disableRed_;

        /**
         * <pre>
         * true if RED (Redundant Encoding) is disabled for audio
         * </pre>
         *
         * <code>bool disable_red = 15;</code>
         *
         * @return The disableRed.
         */
        @java.lang.Override
        public boolean getDisableRed() {
            return disableRed_;
        }

        /**
         * <pre>
         * true if RED (Redundant Encoding) is disabled for audio
         * </pre>
         *
         * <code>bool disable_red = 15;</code>
         *
         * @param value The disableRed to set.
         * @return This builder for chaining.
         */
        public Builder setDisableRed(boolean value) {

            disableRed_ = value;
            bitField0_ |= 0x00004000;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * true if RED (Redundant Encoding) is disabled for audio
         * </pre>
         *
         * <code>bool disable_red = 15;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDisableRed() {
            bitField0_ &= ~0x00004000;
            disableRed_ = false;
            onChanged();
            return this;
        }

        private int encryption_ = 0;

        /**
         * <code>.livekit.Encryption.Type encryption = 16;</code>
         *
         * @return The enum numeric value on the wire for encryption.
         */
        @java.lang.Override
        public int getEncryptionValue() {
            return encryption_;
        }

        /**
         * <code>.livekit.Encryption.Type encryption = 16;</code>
         *
         * @param value The enum numeric value on the wire for encryption to set.
         * @return This builder for chaining.
         */
        public Builder setEncryptionValue(int value) {
            encryption_ = value;
            bitField0_ |= 0x00008000;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.Encryption.Type encryption = 16;</code>
         *
         * @return The encryption.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Encryption.Type getEncryption() {
            im.turms.plugin.livekit.core.proto.models.Encryption.Type result =
                    im.turms.plugin.livekit.core.proto.models.Encryption.Type
                            .forNumber(encryption_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.Encryption.Type.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.Encryption.Type encryption = 16;</code>
         *
         * @param value The encryption to set.
         * @return This builder for chaining.
         */
        public Builder setEncryption(
                im.turms.plugin.livekit.core.proto.models.Encryption.Type value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00008000;
            encryption_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.Encryption.Type encryption = 16;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEncryption() {
            bitField0_ &= ~0x00008000;
            encryption_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object stream_ = "";

        /**
         * <code>string stream = 17;</code>
         *
         * @return The stream.
         */
        public java.lang.String getStream() {
            java.lang.Object ref = stream_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                stream_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string stream = 17;</code>
         *
         * @return The bytes for stream.
         */
        public com.google.protobuf.ByteString getStreamBytes() {
            java.lang.Object ref = stream_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                stream_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string stream = 17;</code>
         *
         * @param value The stream to set.
         * @return This builder for chaining.
         */
        public Builder setStream(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            stream_ = value;
            bitField0_ |= 0x00010000;
            onChanged();
            return this;
        }

        /**
         * <code>string stream = 17;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStream() {
            stream_ = getDefaultInstance().getStream();
            bitField0_ &= ~0x00010000;
            onChanged();
            return this;
        }

        /**
         * <code>string stream = 17;</code>
         *
         * @param value The bytes for stream to set.
         * @return This builder for chaining.
         */
        public Builder setStreamBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            stream_ = value;
            bitField0_ |= 0x00010000;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.TimedVersion version_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TimedVersion, im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder, im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder> versionBuilder_;

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         *
         * @return Whether the version field is set.
         */
        public boolean hasVersion() {
            return ((bitField0_ & 0x00020000) != 0);
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         *
         * @return The version.
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion() {
            if (versionBuilder_ == null) {
                return version_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()
                        : version_;
            } else {
                return versionBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public Builder setVersion(im.turms.plugin.livekit.core.proto.models.TimedVersion value) {
            if (versionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                version_ = value;
            } else {
                versionBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00020000;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public Builder setVersion(
                im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder builderForValue) {
            if (versionBuilder_ == null) {
                version_ = builderForValue.build();
            } else {
                versionBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00020000;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public Builder mergeVersion(im.turms.plugin.livekit.core.proto.models.TimedVersion value) {
            if (versionBuilder_ == null) {
                if (((bitField0_ & 0x00020000) != 0)
                        && version_ != null
                        && version_ != im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()) {
                    getVersionBuilder().mergeFrom(value);
                } else {
                    version_ = value;
                }
            } else {
                versionBuilder_.mergeFrom(value);
            }
            if (version_ != null) {
                bitField0_ |= 0x00020000;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public Builder clearVersion() {
            bitField0_ &= ~0x00020000;
            version_ = null;
            if (versionBuilder_ != null) {
                versionBuilder_.dispose();
                versionBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder getVersionBuilder() {
            bitField0_ |= 0x00020000;
            onChanged();
            return getVersionFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder() {
            if (versionBuilder_ != null) {
                return versionBuilder_.getMessageOrBuilder();
            } else {
                return version_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()
                        : version_;
            }
        }

        /**
         * <code>.livekit.TimedVersion version = 18;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TimedVersion, im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder, im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder> getVersionFieldBuilder() {
            if (versionBuilder_ == null) {
                versionBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getVersion(),
                        getParentForChildren(),
                        isClean());
                version_ = null;
            }
            return versionBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.TrackInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.TrackInfo)
    private static final im.turms.plugin.livekit.core.proto.models.TrackInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.TrackInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.TrackInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TrackInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public TrackInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<TrackInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TrackInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}