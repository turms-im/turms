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

public interface VideoLayerOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.VideoLayer)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * for tracks with a single layer, this should be HIGH
     * </pre>
     *
     * <code>.livekit.VideoQuality quality = 1;</code>
     *
     * @return The enum numeric value on the wire for quality.
     */
    int getQualityValue();

    /**
     * <pre>
     * for tracks with a single layer, this should be HIGH
     * </pre>
     *
     * <code>.livekit.VideoQuality quality = 1;</code>
     *
     * @return The quality.
     */
    im.turms.plugin.livekit.core.proto.models.VideoQuality getQuality();

    /**
     * <code>uint32 width = 2;</code>
     *
     * @return The width.
     */
    int getWidth();

    /**
     * <code>uint32 height = 3;</code>
     *
     * @return The height.
     */
    int getHeight();

    /**
     * <pre>
     * target bitrate in bit per second (bps), server will measure actual
     * </pre>
     *
     * <code>uint32 bitrate = 4;</code>
     *
     * @return The bitrate.
     */
    int getBitrate();

    /**
     * <code>uint32 ssrc = 5;</code>
     *
     * @return The ssrc.
     */
    int getSsrc();
}
