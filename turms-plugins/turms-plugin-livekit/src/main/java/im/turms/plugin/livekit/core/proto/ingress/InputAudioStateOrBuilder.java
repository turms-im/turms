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

public interface InputAudioStateOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.InputAudioState)
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
     * <code>uint32 average_bitrate = 2;</code>
     *
     * @return The averageBitrate.
     */
    int getAverageBitrate();

    /**
     * <code>uint32 channels = 3;</code>
     *
     * @return The channels.
     */
    int getChannels();

    /**
     * <code>uint32 sample_rate = 4;</code>
     *
     * @return The sampleRate.
     */
    int getSampleRate();
}
