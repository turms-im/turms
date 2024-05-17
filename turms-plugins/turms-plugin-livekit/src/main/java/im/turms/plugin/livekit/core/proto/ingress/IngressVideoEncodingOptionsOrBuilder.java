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

public interface IngressVideoEncodingOptionsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.IngressVideoEncodingOptions)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * desired codec to publish to room
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 1;</code>
     *
     * @return The enum numeric value on the wire for videoCodec.
     */
    int getVideoCodecValue();

    /**
     * <pre>
     * desired codec to publish to room
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 1;</code>
     *
     * @return The videoCodec.
     */
    im.turms.plugin.livekit.core.proto.models.VideoCodec getVideoCodec();

    /**
     * <code>double frame_rate = 2;</code>
     *
     * @return The frameRate.
     */
    double getFrameRate();

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList();

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index);

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    int getLayersCount();

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList();

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(int index);
}
