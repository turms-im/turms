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

public interface ParticipantEgressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ParticipantEgressRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string identity = 2;</code>
     *
     * @return The identity.
     */
    java.lang.String getIdentity();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string identity = 2;</code>
     *
     * @return The bytes for identity.
     */
    com.google.protobuf.ByteString getIdentityBytes();

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool screen_share = 3;</code>
     *
     * @return The screenShare.
     */
    boolean getScreenShare();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return Whether the preset field is set.
     */
    boolean hasPreset();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    int getPresetValue();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return The preset.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     *
     * @return Whether the advanced field is set.
     */
    boolean hasAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     *
     * @return The advanced.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index);

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    int getFileOutputsCount();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index);

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    int getStreamOutputsCount();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    int getSegmentOutputsCount();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index);

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    int getImageOutputsCount();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder getImageOutputsOrBuilder(
            int index);

    im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.OptionsCase getOptionsCase();
}
