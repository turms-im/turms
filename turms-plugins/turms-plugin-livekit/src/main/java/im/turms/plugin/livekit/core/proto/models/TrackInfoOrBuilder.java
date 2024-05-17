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

public interface TrackInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.TrackInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    java.lang.String getSid();

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    com.google.protobuf.ByteString getSidBytes();

    /**
     * <code>.livekit.TrackType type = 2;</code>
     *
     * @return The enum numeric value on the wire for type.
     */
    int getTypeValue();

    /**
     * <code>.livekit.TrackType type = 2;</code>
     *
     * @return The type.
     */
    im.turms.plugin.livekit.core.proto.models.TrackType getType();

    /**
     * <code>string name = 3;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 3;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>bool muted = 4;</code>
     *
     * @return The muted.
     */
    boolean getMuted();

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
    int getWidth();

    /**
     * <pre>
     * original height of video (unset for audio)
     * </pre>
     *
     * <code>uint32 height = 6;</code>
     *
     * @return The height.
     */
    int getHeight();

    /**
     * <pre>
     * true if track is simulcasted
     * </pre>
     *
     * <code>bool simulcast = 7;</code>
     *
     * @return The simulcast.
     */
    boolean getSimulcast();

    /**
     * <pre>
     * true if DTX (Discontinuous Transmission) is disabled for audio
     * </pre>
     *
     * <code>bool disable_dtx = 8;</code>
     *
     * @return The disableDtx.
     */
    boolean getDisableDtx();

    /**
     * <pre>
     * source of media
     * </pre>
     *
     * <code>.livekit.TrackSource source = 9;</code>
     *
     * @return The enum numeric value on the wire for source.
     */
    int getSourceValue();

    /**
     * <pre>
     * source of media
     * </pre>
     *
     * <code>.livekit.TrackSource source = 9;</code>
     *
     * @return The source.
     */
    im.turms.plugin.livekit.core.proto.models.TrackSource getSource();

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList();

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index);

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    int getLayersCount();

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList();

    /**
     * <code>repeated .livekit.VideoLayer layers = 10;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(int index);

    /**
     * <pre>
     * mime type of codec
     * </pre>
     *
     * <code>string mime_type = 11;</code>
     *
     * @return The mimeType.
     */
    java.lang.String getMimeType();

    /**
     * <pre>
     * mime type of codec
     * </pre>
     *
     * <code>string mime_type = 11;</code>
     *
     * @return The bytes for mimeType.
     */
    com.google.protobuf.ByteString getMimeTypeBytes();

    /**
     * <code>string mid = 12;</code>
     *
     * @return The mid.
     */
    java.lang.String getMid();

    /**
     * <code>string mid = 12;</code>
     *
     * @return The bytes for mid.
     */
    com.google.protobuf.ByteString getMidBytes();

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo> getCodecsList();

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getCodecs(int index);

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    int getCodecsCount();

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder> getCodecsOrBuilderList();

    /**
     * <code>repeated .livekit.SimulcastCodecInfo codecs = 13;</code>
     */
    im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder getCodecsOrBuilder(
            int index);

    /**
     * <code>bool stereo = 14;</code>
     *
     * @return The stereo.
     */
    boolean getStereo();

    /**
     * <pre>
     * true if RED (Redundant Encoding) is disabled for audio
     * </pre>
     *
     * <code>bool disable_red = 15;</code>
     *
     * @return The disableRed.
     */
    boolean getDisableRed();

    /**
     * <code>.livekit.Encryption.Type encryption = 16;</code>
     *
     * @return The enum numeric value on the wire for encryption.
     */
    int getEncryptionValue();

    /**
     * <code>.livekit.Encryption.Type encryption = 16;</code>
     *
     * @return The encryption.
     */
    im.turms.plugin.livekit.core.proto.models.Encryption.Type getEncryption();

    /**
     * <code>string stream = 17;</code>
     *
     * @return The stream.
     */
    java.lang.String getStream();

    /**
     * <code>string stream = 17;</code>
     *
     * @return The bytes for stream.
     */
    com.google.protobuf.ByteString getStreamBytes();

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     *
     * @return Whether the version field is set.
     */
    boolean hasVersion();

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     *
     * @return The version.
     */
    im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion();

    /**
     * <code>.livekit.TimedVersion version = 18;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder();
}
