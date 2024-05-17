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

public interface EncodingOptionsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.EncodingOptions)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * (default 1920)
     * </pre>
     *
     * <code>int32 width = 1;</code>
     *
     * @return The width.
     */
    int getWidth();

    /**
     * <pre>
     * (default 1080)
     * </pre>
     *
     * <code>int32 height = 2;</code>
     *
     * @return The height.
     */
    int getHeight();

    /**
     * <pre>
     * (default 24)
     * </pre>
     *
     * <code>int32 depth = 3;</code>
     *
     * @return The depth.
     */
    int getDepth();

    /**
     * <pre>
     * (default 30)
     * </pre>
     *
     * <code>int32 framerate = 4;</code>
     *
     * @return The framerate.
     */
    int getFramerate();

    /**
     * <pre>
     * (default OPUS)
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 5;</code>
     *
     * @return The enum numeric value on the wire for audioCodec.
     */
    int getAudioCodecValue();

    /**
     * <pre>
     * (default OPUS)
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 5;</code>
     *
     * @return The audioCodec.
     */
    im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec();

    /**
     * <pre>
     * (default 128)
     * </pre>
     *
     * <code>int32 audio_bitrate = 6;</code>
     *
     * @return The audioBitrate.
     */
    int getAudioBitrate();

    /**
     * <pre>
     * quality setting on audio encoder
     * </pre>
     *
     * <code>int32 audio_quality = 11;</code>
     *
     * @return The audioQuality.
     */
    int getAudioQuality();

    /**
     * <pre>
     * (default 44100)
     * </pre>
     *
     * <code>int32 audio_frequency = 7;</code>
     *
     * @return The audioFrequency.
     */
    int getAudioFrequency();

    /**
     * <pre>
     * (default H264_MAIN)
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 8;</code>
     *
     * @return The enum numeric value on the wire for videoCodec.
     */
    int getVideoCodecValue();

    /**
     * <pre>
     * (default H264_MAIN)
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 8;</code>
     *
     * @return The videoCodec.
     */
    im.turms.plugin.livekit.core.proto.models.VideoCodec getVideoCodec();

    /**
     * <pre>
     * (default 4500)
     * </pre>
     *
     * <code>int32 video_bitrate = 9;</code>
     *
     * @return The videoBitrate.
     */
    int getVideoBitrate();

    /**
     * <pre>
     * quality setting on video encoder
     * </pre>
     *
     * <code>int32 video_quality = 12;</code>
     *
     * @return The videoQuality.
     */
    int getVideoQuality();

    /**
     * <pre>
     * in seconds (default 4s for streaming, segment duration for segmented output, encoder default for files)
     * </pre>
     *
     * <code>double key_frame_interval = 10;</code>
     *
     * @return The keyFrameInterval.
     */
    double getKeyFrameInterval();
}
