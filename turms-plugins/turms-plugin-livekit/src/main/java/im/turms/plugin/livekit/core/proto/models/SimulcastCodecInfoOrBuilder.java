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

public interface SimulcastCodecInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.SimulcastCodecInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string mime_type = 1;</code>
     *
     * @return The mimeType.
     */
    java.lang.String getMimeType();

    /**
     * <code>string mime_type = 1;</code>
     *
     * @return The bytes for mimeType.
     */
    com.google.protobuf.ByteString getMimeTypeBytes();

    /**
     * <code>string mid = 2;</code>
     *
     * @return The mid.
     */
    java.lang.String getMid();

    /**
     * <code>string mid = 2;</code>
     *
     * @return The bytes for mid.
     */
    com.google.protobuf.ByteString getMidBytes();

    /**
     * <code>string cid = 3;</code>
     *
     * @return The cid.
     */
    java.lang.String getCid();

    /**
     * <code>string cid = 3;</code>
     *
     * @return The bytes for cid.
     */
    com.google.protobuf.ByteString getCidBytes();

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList();

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index);

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    int getLayersCount();

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList();

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(int index);
}
