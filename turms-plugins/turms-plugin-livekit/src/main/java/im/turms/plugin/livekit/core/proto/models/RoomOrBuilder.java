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

public interface RoomOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.Room)
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
     * <code>string name = 2;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 2;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>uint32 empty_timeout = 3;</code>
     *
     * @return The emptyTimeout.
     */
    int getEmptyTimeout();

    /**
     * <code>uint32 departure_timeout = 14;</code>
     *
     * @return The departureTimeout.
     */
    int getDepartureTimeout();

    /**
     * <code>uint32 max_participants = 4;</code>
     *
     * @return The maxParticipants.
     */
    int getMaxParticipants();

    /**
     * <code>int64 creation_time = 5;</code>
     *
     * @return The creationTime.
     */
    long getCreationTime();

    /**
     * <code>string turn_password = 6;</code>
     *
     * @return The turnPassword.
     */
    java.lang.String getTurnPassword();

    /**
     * <code>string turn_password = 6;</code>
     *
     * @return The bytes for turnPassword.
     */
    com.google.protobuf.ByteString getTurnPasswordBytes();

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getEnabledCodecsList();

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.models.Codec getEnabledCodecs(int index);

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    int getEnabledCodecsCount();

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getEnabledCodecsOrBuilderList();

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getEnabledCodecsOrBuilder(int index);

    /**
     * <code>string metadata = 8;</code>
     *
     * @return The metadata.
     */
    java.lang.String getMetadata();

    /**
     * <code>string metadata = 8;</code>
     *
     * @return The bytes for metadata.
     */
    com.google.protobuf.ByteString getMetadataBytes();

    /**
     * <code>uint32 num_participants = 9;</code>
     *
     * @return The numParticipants.
     */
    int getNumParticipants();

    /**
     * <code>uint32 num_publishers = 11;</code>
     *
     * @return The numPublishers.
     */
    int getNumPublishers();

    /**
     * <code>bool active_recording = 10;</code>
     *
     * @return The activeRecording.
     */
    boolean getActiveRecording();

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     *
     * @return Whether the version field is set.
     */
    boolean hasVersion();

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     *
     * @return The version.
     */
    im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion();

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder();
}
