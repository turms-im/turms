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

/**
 * Protobuf enum {@code livekit.IngressVideoEncodingPreset}
 */
public enum IngressVideoEncodingPreset implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * 1280x720,  30fps, 1900kbps main layer, 3 layers total
     * </pre>
     *
     * <code>H264_720P_30FPS_3_LAYERS = 0;</code>
     */
    H264_720P_30FPS_3_LAYERS(0),
    /**
     * <pre>
     * 1980x1080, 30fps, 3500kbps main layer, 3 layers total
     * </pre>
     *
     * <code>H264_1080P_30FPS_3_LAYERS = 1;</code>
     */
    H264_1080P_30FPS_3_LAYERS(1),
    /**
     * <pre>
     * 960x540,  25fps, 1000kbps  main layer, 2 layers total
     * </pre>
     *
     * <code>H264_540P_25FPS_2_LAYERS = 2;</code>
     */
    H264_540P_25FPS_2_LAYERS(2),
    /**
     * <pre>
     * 1280x720,  30fps, 1900kbps, no simulcast
     * </pre>
     *
     * <code>H264_720P_30FPS_1_LAYER = 3;</code>
     */
    H264_720P_30FPS_1_LAYER(3),
    /**
     * <pre>
     * 1980x1080, 30fps, 3500kbps, no simulcast
     * </pre>
     *
     * <code>H264_1080P_30FPS_1_LAYER = 4;</code>
     */
    H264_1080P_30FPS_1_LAYER(4),
    /**
     * <pre>
     * 1280x720,  30fps, 2500kbps main layer, 3 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_720P_30FPS_3_LAYERS_HIGH_MOTION = 5;</code>
     */
    H264_720P_30FPS_3_LAYERS_HIGH_MOTION(5),
    /**
     * <pre>
     * 1980x1080, 30fps, 4500kbps main layer, 3 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_1080P_30FPS_3_LAYERS_HIGH_MOTION = 6;</code>
     */
    H264_1080P_30FPS_3_LAYERS_HIGH_MOTION(6),
    /**
     * <pre>
     * 960x540,  25fps, 1300kbps  main layer, 2 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_540P_25FPS_2_LAYERS_HIGH_MOTION = 7;</code>
     */
    H264_540P_25FPS_2_LAYERS_HIGH_MOTION(7),
    /**
     * <pre>
     * 1280x720,  30fps, 2500kbps, no simulcast, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_720P_30FPS_1_LAYER_HIGH_MOTION = 8;</code>
     */
    H264_720P_30FPS_1_LAYER_HIGH_MOTION(8),
    /**
     * <pre>
     * 1980x1080, 30fps, 4500kbps, no simulcast, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_1080P_30FPS_1_LAYER_HIGH_MOTION = 9;</code>
     */
    H264_1080P_30FPS_1_LAYER_HIGH_MOTION(9),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressVideoEncodingPreset.class.getName());
    }
    /**
     * <pre>
     * 1280x720,  30fps, 1900kbps main layer, 3 layers total
     * </pre>
     *
     * <code>H264_720P_30FPS_3_LAYERS = 0;</code>
     */
    public static final int H264_720P_30FPS_3_LAYERS_VALUE = 0;
    /**
     * <pre>
     * 1980x1080, 30fps, 3500kbps main layer, 3 layers total
     * </pre>
     *
     * <code>H264_1080P_30FPS_3_LAYERS = 1;</code>
     */
    public static final int H264_1080P_30FPS_3_LAYERS_VALUE = 1;
    /**
     * <pre>
     * 960x540,  25fps, 1000kbps  main layer, 2 layers total
     * </pre>
     *
     * <code>H264_540P_25FPS_2_LAYERS = 2;</code>
     */
    public static final int H264_540P_25FPS_2_LAYERS_VALUE = 2;
    /**
     * <pre>
     * 1280x720,  30fps, 1900kbps, no simulcast
     * </pre>
     *
     * <code>H264_720P_30FPS_1_LAYER = 3;</code>
     */
    public static final int H264_720P_30FPS_1_LAYER_VALUE = 3;
    /**
     * <pre>
     * 1980x1080, 30fps, 3500kbps, no simulcast
     * </pre>
     *
     * <code>H264_1080P_30FPS_1_LAYER = 4;</code>
     */
    public static final int H264_1080P_30FPS_1_LAYER_VALUE = 4;
    /**
     * <pre>
     * 1280x720,  30fps, 2500kbps main layer, 3 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_720P_30FPS_3_LAYERS_HIGH_MOTION = 5;</code>
     */
    public static final int H264_720P_30FPS_3_LAYERS_HIGH_MOTION_VALUE = 5;
    /**
     * <pre>
     * 1980x1080, 30fps, 4500kbps main layer, 3 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_1080P_30FPS_3_LAYERS_HIGH_MOTION = 6;</code>
     */
    public static final int H264_1080P_30FPS_3_LAYERS_HIGH_MOTION_VALUE = 6;
    /**
     * <pre>
     * 960x540,  25fps, 1300kbps  main layer, 2 layers total, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_540P_25FPS_2_LAYERS_HIGH_MOTION = 7;</code>
     */
    public static final int H264_540P_25FPS_2_LAYERS_HIGH_MOTION_VALUE = 7;
    /**
     * <pre>
     * 1280x720,  30fps, 2500kbps, no simulcast, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_720P_30FPS_1_LAYER_HIGH_MOTION = 8;</code>
     */
    public static final int H264_720P_30FPS_1_LAYER_HIGH_MOTION_VALUE = 8;
    /**
     * <pre>
     * 1980x1080, 30fps, 4500kbps, no simulcast, higher bitrate for high motion, harder to encode content
     * </pre>
     *
     * <code>H264_1080P_30FPS_1_LAYER_HIGH_MOTION = 9;</code>
     */
    public static final int H264_1080P_30FPS_1_LAYER_HIGH_MOTION_VALUE = 9;

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
    public static IngressVideoEncodingPreset valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static IngressVideoEncodingPreset forNumber(int value) {
        return switch (value) {
            case 0 -> H264_720P_30FPS_3_LAYERS;
            case 1 -> H264_1080P_30FPS_3_LAYERS;
            case 2 -> H264_540P_25FPS_2_LAYERS;
            case 3 -> H264_720P_30FPS_1_LAYER;
            case 4 -> H264_1080P_30FPS_1_LAYER;
            case 5 -> H264_720P_30FPS_3_LAYERS_HIGH_MOTION;
            case 6 -> H264_1080P_30FPS_3_LAYERS_HIGH_MOTION;
            case 7 -> H264_540P_25FPS_2_LAYERS_HIGH_MOTION;
            case 8 -> H264_720P_30FPS_1_LAYER_HIGH_MOTION;
            case 9 -> H264_1080P_30FPS_1_LAYER_HIGH_MOTION;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<IngressVideoEncodingPreset> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<IngressVideoEncodingPreset> internalValueMap =
            IngressVideoEncodingPreset::forNumber;

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
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.getDescriptor()
                .getEnumTypes()
                .get(2);
    }

    private static final IngressVideoEncodingPreset[] VALUES = values();

    public static IngressVideoEncodingPreset valueOf(
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

    IngressVideoEncodingPreset(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.IngressVideoEncodingPreset)
}