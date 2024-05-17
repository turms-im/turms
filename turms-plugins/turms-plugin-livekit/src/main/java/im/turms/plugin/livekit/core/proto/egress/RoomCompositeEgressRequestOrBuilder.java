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

public interface RoomCompositeEgressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.RoomCompositeEgressRequest)
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
     * (optional)
     * </pre>
     *
     * <code>string layout = 2;</code>
     *
     * @return The layout.
     */
    java.lang.String getLayout();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string layout = 2;</code>
     *
     * @return The bytes for layout.
     */
    com.google.protobuf.ByteString getLayoutBytes();

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool audio_only = 3;</code>
     *
     * @return The audioOnly.
     */
    boolean getAudioOnly();

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool video_only = 4;</code>
     *
     * @return The videoOnly.
     */
    boolean getVideoOnly();

    /**
     * <pre>
     * template base url (default https://recorder.livekit.io)
     * </pre>
     *
     * <code>string custom_base_url = 5;</code>
     *
     * @return The customBaseUrl.
     */
    java.lang.String getCustomBaseUrl();

    /**
     * <pre>
     * template base url (default https://recorder.livekit.io)
     * </pre>
     *
     * <code>string custom_base_url = 5;</code>
     *
     * @return The bytes for customBaseUrl.
     */
    com.google.protobuf.ByteString getCustomBaseUrlBytes();

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
     *             livekit_egress.proto;l=35
     * @return Whether the file field is set.
     */
    @java.lang.Deprecated
    boolean hasFile();

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
     *             livekit_egress.proto;l=35
     * @return The file.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFile();

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOrBuilder();

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
     *             livekit_egress.proto;l=36
     * @return Whether the stream field is set.
     */
    @java.lang.Deprecated
    boolean hasStream();

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
     *             livekit_egress.proto;l=36
     * @return The stream.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.StreamOutput getStream();

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOrBuilder();

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
     *             livekit_egress.proto;l=37
     * @return Whether the segments field is set.
     */
    @java.lang.Deprecated
    boolean hasSegments();

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
     *             livekit_egress.proto;l=37
     * @return The segments.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegments();

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentsOrBuilder();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return Whether the preset field is set.
     */
    boolean hasPreset();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    int getPresetValue();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return The preset.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     *
     * @return Whether the advanced field is set.
     */
    boolean hasAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     *
     * @return The advanced.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index);

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    int getFileOutputsCount();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index);

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    int getStreamOutputsCount();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    int getSegmentOutputsCount();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index);

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    int getImageOutputsCount();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder getImageOutputsOrBuilder(
            int index);

    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.OutputCase getOutputCase();

    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.OptionsCase getOptionsCase();
}
