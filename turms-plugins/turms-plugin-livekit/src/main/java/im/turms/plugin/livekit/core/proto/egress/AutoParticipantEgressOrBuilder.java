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

public interface AutoParticipantEgressOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.AutoParticipantEgress)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return Whether the preset field is set.
     */
    boolean hasPreset();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    int getPresetValue();

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return The preset.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     *
     * @return Whether the advanced field is set.
     */
    boolean hasAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     *
     * @return The advanced.
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced();

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index);

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    int getFileOutputsCount();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(int index);

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    int getSegmentOutputsCount();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList();

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index);

    im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.OptionsCase getOptionsCase();
}
