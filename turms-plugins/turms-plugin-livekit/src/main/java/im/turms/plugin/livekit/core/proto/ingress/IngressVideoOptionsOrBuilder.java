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

public interface IngressVideoOptionsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.IngressVideoOptions)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>.livekit.TrackSource source = 2;</code>
     *
     * @return The enum numeric value on the wire for source.
     */
    int getSourceValue();

    /**
     * <code>.livekit.TrackSource source = 2;</code>
     *
     * @return The source.
     */
    im.turms.plugin.livekit.core.proto.models.TrackSource getSource();

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return Whether the preset field is set.
     */
    boolean hasPreset();

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    int getPresetValue();

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return The preset.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset getPreset();

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     *
     * @return Whether the options field is set.
     */
    boolean hasOptions();

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     *
     * @return The options.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getOptions();

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder getOptionsOrBuilder();

    im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.EncodingOptionsCase getEncodingOptionsCase();
}
