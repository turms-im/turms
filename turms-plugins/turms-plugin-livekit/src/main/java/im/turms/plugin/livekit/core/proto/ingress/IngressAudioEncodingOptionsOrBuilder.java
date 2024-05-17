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

package im.turms.plugin.livekit.core.proto.ingress;

public interface IngressAudioEncodingOptionsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.IngressAudioEncodingOptions)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * desired audio codec to publish to room
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 1;</code>
     *
     * @return The enum numeric value on the wire for audioCodec.
     */
    int getAudioCodecValue();

    /**
     * <pre>
     * desired audio codec to publish to room
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 1;</code>
     *
     * @return The audioCodec.
     */
    im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec();

    /**
     * <code>uint32 bitrate = 2;</code>
     *
     * @return The bitrate.
     */
    int getBitrate();

    /**
     * <code>bool disable_dtx = 3;</code>
     *
     * @return The disableDtx.
     */
    boolean getDisableDtx();

    /**
     * <code>uint32 channels = 4;</code>
     *
     * @return The channels.
     */
    int getChannels();
}
