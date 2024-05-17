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

public interface DisabledCodecsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.DisabledCodecs)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getCodecsList();

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    im.turms.plugin.livekit.core.proto.models.Codec getCodecs(int index);

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    int getCodecsCount();

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getCodecsOrBuilderList();

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getCodecsOrBuilder(int index);

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getPublishList();

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.models.Codec getPublish(int index);

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    int getPublishCount();

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getPublishOrBuilderList();

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getPublishOrBuilder(int index);
}
