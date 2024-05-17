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

public interface ClientConfigurationOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ClientConfiguration)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     *
     * @return Whether the video field is set.
     */
    boolean hasVideo();

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     *
     * @return The video.
     */
    im.turms.plugin.livekit.core.proto.models.VideoConfiguration getVideo();

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getVideoOrBuilder();

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     *
     * @return Whether the screen field is set.
     */
    boolean hasScreen();

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     *
     * @return The screen.
     */
    im.turms.plugin.livekit.core.proto.models.VideoConfiguration getScreen();

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getScreenOrBuilder();

    /**
     * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
     *
     * @return The enum numeric value on the wire for resumeConnection.
     */
    int getResumeConnectionValue();

    /**
     * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
     *
     * @return The resumeConnection.
     */
    im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getResumeConnection();

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     *
     * @return Whether the disabledCodecs field is set.
     */
    boolean hasDisabledCodecs();

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     *
     * @return The disabledCodecs.
     */
    im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDisabledCodecs();

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder getDisabledCodecsOrBuilder();

    /**
     * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
     *
     * @return The enum numeric value on the wire for forceRelay.
     */
    int getForceRelayValue();

    /**
     * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
     *
     * @return The forceRelay.
     */
    im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getForceRelay();
}
