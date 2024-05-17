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

/**
 * Protobuf enum {@code livekit.AudioTrackFeature}
 */
public enum AudioTrackFeature implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>TF_STEREO = 0;</code>
     */
    TF_STEREO(0),
    /**
     * <code>TF_NO_DTX = 1;</code>
     */
    TF_NO_DTX(1),
    /**
     * <code>TF_AUTO_GAIN_CONTROL = 2;</code>
     */
    TF_AUTO_GAIN_CONTROL(2),
    /**
     * <code>TF_ECHO_CANCELLATION = 3;</code>
     */
    TF_ECHO_CANCELLATION(3),
    /**
     * <code>TF_NOISE_SUPPRESSION = 4;</code>
     */
    TF_NOISE_SUPPRESSION(4),
    /**
     * <code>TF_ENHANCED_NOISE_CANCELLATION = 5;</code>
     */
    TF_ENHANCED_NOISE_CANCELLATION(5),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                AudioTrackFeature.class.getName());
    }
    /**
     * <code>TF_STEREO = 0;</code>
     */
    public static final int TF_STEREO_VALUE = 0;
    /**
     * <code>TF_NO_DTX = 1;</code>
     */
    public static final int TF_NO_DTX_VALUE = 1;
    /**
     * <code>TF_AUTO_GAIN_CONTROL = 2;</code>
     */
    public static final int TF_AUTO_GAIN_CONTROL_VALUE = 2;
    /**
     * <code>TF_ECHO_CANCELLATION = 3;</code>
     */
    public static final int TF_ECHO_CANCELLATION_VALUE = 3;
    /**
     * <code>TF_NOISE_SUPPRESSION = 4;</code>
     */
    public static final int TF_NOISE_SUPPRESSION_VALUE = 4;
    /**
     * <code>TF_ENHANCED_NOISE_CANCELLATION = 5;</code>
     */
    public static final int TF_ENHANCED_NOISE_CANCELLATION_VALUE = 5;

    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new java.lang.IllegalArgumentException(
                    "Can't get the number of an unknown enum value.");
        }
        return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static AudioTrackFeature valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static AudioTrackFeature forNumber(int value) {
        return switch (value) {
            case 0 -> TF_STEREO;
            case 1 -> TF_NO_DTX;
            case 2 -> TF_AUTO_GAIN_CONTROL;
            case 3 -> TF_ECHO_CANCELLATION;
            case 4 -> TF_NOISE_SUPPRESSION;
            case 5 -> TF_ENHANCED_NOISE_CANCELLATION;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<AudioTrackFeature> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<AudioTrackFeature> internalValueMap =
            AudioTrackFeature::forNumber;

    public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED) {
            throw new java.lang.IllegalStateException(
                    "Can't get the descriptor of an unrecognized enum value.");
        }
        return getDescriptor().getValues()
                .get(ordinal());
    }

    public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor()
                .getEnumTypes()
                .get(11);
    }

    private static final AudioTrackFeature[] VALUES = values();

    public static AudioTrackFeature valueOf(
            com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
                    "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
    }

    private final int value;

    AudioTrackFeature(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.AudioTrackFeature)
}