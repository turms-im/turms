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

public final class LivekitIngress {
    private LivekitIngress() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                LivekitIngress.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_CreateIngressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_CreateIngressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressAudioOptions_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressAudioOptions_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressVideoOptions_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressVideoOptions_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressAudioEncodingOptions_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressAudioEncodingOptions_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressVideoEncodingOptions_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressVideoEncodingOptions_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_IngressState_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_IngressState_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_InputVideoState_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_InputVideoState_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_InputAudioState_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_InputAudioState_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateIngressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateIngressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListIngressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListIngressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListIngressResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListIngressResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DeleteIngressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DeleteIngressRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData =
                {"\n\025livekit_ingress.proto\022\007livekit\032\024liveki"
                        + "t_models.proto\"\367\002\n\024CreateIngressRequest\022"
                        + ")\n\ninput_type\030\001 \001(\0162\025.livekit.IngressInp"
                        + "ut\022\013\n\003url\030\t \001(\t\022\014\n\004name\030\002 \001(\t\022\021\n\troom_na"
                        + "me\030\003 \001(\t\022\034\n\024participant_identity\030\004 \001(\t\022\030"
                        + "\n\020participant_name\030\005 \001(\t\022\034\n\024participant_"
                        + "metadata\030\n \001(\t\022\036\n\022bypass_transcoding\030\010 \001"
                        + "(\010B\002\030\001\022\037\n\022enable_transcoding\030\013 \001(\010H\000\210\001\001\022"
                        + "+\n\005audio\030\006 \001(\0132\034.livekit.IngressAudioOpt"
                        + "ions\022+\n\005video\030\007 \001(\0132\034.livekit.IngressVid"
                        + "eoOptionsB\025\n\023_enable_transcoding\"\315\001\n\023Ing"
                        + "ressAudioOptions\022\014\n\004name\030\001 \001(\t\022$\n\006source"
                        + "\030\002 \001(\0162\024.livekit.TrackSource\0225\n\006preset\030\003"
                        + " \001(\0162#.livekit.IngressAudioEncodingPrese"
                        + "tH\000\0227\n\007options\030\004 \001(\0132$.livekit.IngressAu"
                        + "dioEncodingOptionsH\000B\022\n\020encoding_options"
                        + "\"\315\001\n\023IngressVideoOptions\022\014\n\004name\030\001 \001(\t\022$"
                        + "\n\006source\030\002 \001(\0162\024.livekit.TrackSource\0225\n\006"
                        + "preset\030\003 \001(\0162#.livekit.IngressVideoEncod"
                        + "ingPresetH\000\0227\n\007options\030\004 \001(\0132$.livekit.I"
                        + "ngressVideoEncodingOptionsH\000B\022\n\020encoding"
                        + "_options\"\177\n\033IngressAudioEncodingOptions\022"
                        + "(\n\013audio_codec\030\001 \001(\0162\023.livekit.AudioCode"
                        + "c\022\017\n\007bitrate\030\002 \001(\r\022\023\n\013disable_dtx\030\003 \001(\010\022"
                        + "\020\n\010channels\030\004 \001(\r\"\200\001\n\033IngressVideoEncodi"
                        + "ngOptions\022(\n\013video_codec\030\001 \001(\0162\023.livekit"
                        + ".VideoCodec\022\022\n\nframe_rate\030\002 \001(\001\022#\n\006layer"
                        + "s\030\003 \003(\0132\023.livekit.VideoLayer\"\316\003\n\013Ingress"
                        + "Info\022\022\n\ningress_id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\022"
                        + "\n\nstream_key\030\003 \001(\t\022\013\n\003url\030\004 \001(\t\022)\n\ninput"
                        + "_type\030\005 \001(\0162\025.livekit.IngressInput\022\036\n\022by"
                        + "pass_transcoding\030\r \001(\010B\002\030\001\022\037\n\022enable_tra"
                        + "nscoding\030\017 \001(\010H\000\210\001\001\022+\n\005audio\030\006 \001(\0132\034.liv"
                        + "ekit.IngressAudioOptions\022+\n\005video\030\007 \001(\0132"
                        + "\034.livekit.IngressVideoOptions\022\021\n\troom_na"
                        + "me\030\010 \001(\t\022\034\n\024participant_identity\030\t \001(\t\022\030"
                        + "\n\020participant_name\030\n \001(\t\022\034\n\024participant_"
                        + "metadata\030\016 \001(\t\022\020\n\010reusable\030\013 \001(\010\022$\n\005stat"
                        + "e\030\014 \001(\0132\025.livekit.IngressStateB\025\n\023_enabl"
                        + "e_transcoding\"\236\003\n\014IngressState\022,\n\006status"
                        + "\030\001 \001(\0162\034.livekit.IngressState.Status\022\r\n\005"
                        + "error\030\002 \001(\t\022\'\n\005video\030\003 \001(\0132\030.livekit.Inp"
                        + "utVideoState\022\'\n\005audio\030\004 \001(\0132\030.livekit.In"
                        + "putAudioState\022\017\n\007room_id\030\005 \001(\t\022\022\n\nstarte"
                        + "d_at\030\007 \001(\003\022\020\n\010ended_at\030\010 \001(\003\022\022\n\nupdated_"
                        + "at\030\n \001(\003\022\023\n\013resource_id\030\t \001(\t\022\"\n\006tracks\030"
                        + "\006 \003(\0132\022.livekit.TrackInfo\"{\n\006Status\022\025\n\021E"
                        + "NDPOINT_INACTIVE\020\000\022\026\n\022ENDPOINT_BUFFERING"
                        + "\020\001\022\027\n\023ENDPOINT_PUBLISHING\020\002\022\022\n\016ENDPOINT_"
                        + "ERROR\020\003\022\025\n\021ENDPOINT_COMPLETE\020\004\"o\n\017InputV"
                        + "ideoState\022\021\n\tmime_type\030\001 \001(\t\022\027\n\017average_"
                        + "bitrate\030\002 \001(\r\022\r\n\005width\030\003 \001(\r\022\016\n\006height\030\004"
                        + " \001(\r\022\021\n\tframerate\030\005 \001(\001\"d\n\017InputAudioSta"
                        + "te\022\021\n\tmime_type\030\001 \001(\t\022\027\n\017average_bitrate"
                        + "\030\002 \001(\r\022\020\n\010channels\030\003 \001(\r\022\023\n\013sample_rate\030"
                        + "\004 \001(\r\"\357\002\n\024UpdateIngressRequest\022\022\n\ningres"
                        + "s_id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\021\n\troom_name\030\003 "
                        + "\001(\t\022\034\n\024participant_identity\030\004 \001(\t\022\030\n\020par"
                        + "ticipant_name\030\005 \001(\t\022\034\n\024participant_metad"
                        + "ata\030\t \001(\t\022#\n\022bypass_transcoding\030\010 \001(\010B\002\030"
                        + "\001H\000\210\001\001\022\037\n\022enable_transcoding\030\n \001(\010H\001\210\001\001\022"
                        + "+\n\005audio\030\006 \001(\0132\034.livekit.IngressAudioOpt"
                        + "ions\022+\n\005video\030\007 \001(\0132\034.livekit.IngressVid"
                        + "eoOptionsB\025\n\023_bypass_transcodingB\025\n\023_ena"
                        + "ble_transcoding\";\n\022ListIngressRequest\022\021\n"
                        + "\troom_name\030\001 \001(\t\022\022\n\ningress_id\030\002 \001(\t\":\n\023"
                        + "ListIngressResponse\022#\n\005items\030\001 \003(\0132\024.liv"
                        + "ekit.IngressInfo\"*\n\024DeleteIngressRequest"
                        + "\022\022\n\ningress_id\030\001 \001(\t*=\n\014IngressInput\022\016\n\n"
                        + "RTMP_INPUT\020\000\022\016\n\nWHIP_INPUT\020\001\022\r\n\tURL_INPU"
                        + "T\020\002*I\n\032IngressAudioEncodingPreset\022\026\n\022OPU"
                        + "S_STEREO_96KBPS\020\000\022\023\n\017OPUS_MONO_64KBS\020\001*\204"
                        + "\003\n\032IngressVideoEncodingPreset\022\034\n\030H264_72"
                        + "0P_30FPS_3_LAYERS\020\000\022\035\n\031H264_1080P_30FPS_"
                        + "3_LAYERS\020\001\022\034\n\030H264_540P_25FPS_2_LAYERS\020\002"
                        + "\022\033\n\027H264_720P_30FPS_1_LAYER\020\003\022\034\n\030H264_10"
                        + "80P_30FPS_1_LAYER\020\004\022(\n$H264_720P_30FPS_3"
                        + "_LAYERS_HIGH_MOTION\020\005\022)\n%H264_1080P_30FP"
                        + "S_3_LAYERS_HIGH_MOTION\020\006\022(\n$H264_540P_25"
                        + "FPS_2_LAYERS_HIGH_MOTION\020\007\022\'\n#H264_720P_"
                        + "30FPS_1_LAYER_HIGH_MOTION\020\010\022(\n$H264_1080"
                        + "P_30FPS_1_LAYER_HIGH_MOTION\020\tBt\n*im.turm"
                        + "s.plugin.livekit.core.proto.ingressP\001Z#g"
                        + "ithub.com/livekit/protocol/livekit\252\002\rLiv"
                        + "eKit.Proto\352\002\016LiveKit::Protob\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor(),});
        internal_static_livekit_CreateIngressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_livekit_CreateIngressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_CreateIngressRequest_descriptor,
                        new java.lang.String[]{"InputType",
                                "Url",
                                "Name",
                                "RoomName",
                                "ParticipantIdentity",
                                "ParticipantName",
                                "ParticipantMetadata",
                                "BypassTranscoding",
                                "EnableTranscoding",
                                "Audio",
                                "Video",});
        internal_static_livekit_IngressAudioOptions_descriptor = getDescriptor().getMessageTypes()
                .get(1);
        internal_static_livekit_IngressAudioOptions_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressAudioOptions_descriptor,
                        new java.lang.String[]{"Name",
                                "Source",
                                "Preset",
                                "Options",
                                "EncodingOptions",});
        internal_static_livekit_IngressVideoOptions_descriptor = getDescriptor().getMessageTypes()
                .get(2);
        internal_static_livekit_IngressVideoOptions_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressVideoOptions_descriptor,
                        new java.lang.String[]{"Name",
                                "Source",
                                "Preset",
                                "Options",
                                "EncodingOptions",});
        internal_static_livekit_IngressAudioEncodingOptions_descriptor =
                getDescriptor().getMessageTypes()
                        .get(3);
        internal_static_livekit_IngressAudioEncodingOptions_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressAudioEncodingOptions_descriptor,
                        new java.lang.String[]{"AudioCodec", "Bitrate", "DisableDtx", "Channels",});
        internal_static_livekit_IngressVideoEncodingOptions_descriptor =
                getDescriptor().getMessageTypes()
                        .get(4);
        internal_static_livekit_IngressVideoEncodingOptions_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressVideoEncodingOptions_descriptor,
                        new java.lang.String[]{"VideoCodec", "FrameRate", "Layers",});
        internal_static_livekit_IngressInfo_descriptor = getDescriptor().getMessageTypes()
                .get(5);
        internal_static_livekit_IngressInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressInfo_descriptor,
                        new java.lang.String[]{"IngressId",
                                "Name",
                                "StreamKey",
                                "Url",
                                "InputType",
                                "BypassTranscoding",
                                "EnableTranscoding",
                                "Audio",
                                "Video",
                                "RoomName",
                                "ParticipantIdentity",
                                "ParticipantName",
                                "ParticipantMetadata",
                                "Reusable",
                                "State",});
        internal_static_livekit_IngressState_descriptor = getDescriptor().getMessageTypes()
                .get(6);
        internal_static_livekit_IngressState_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_IngressState_descriptor,
                        new java.lang.String[]{"Status",
                                "Error",
                                "Video",
                                "Audio",
                                "RoomId",
                                "StartedAt",
                                "EndedAt",
                                "UpdatedAt",
                                "ResourceId",
                                "Tracks",});
        internal_static_livekit_InputVideoState_descriptor = getDescriptor().getMessageTypes()
                .get(7);
        internal_static_livekit_InputVideoState_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_InputVideoState_descriptor,
                        new java.lang.String[]{"MimeType",
                                "AverageBitrate",
                                "Width",
                                "Height",
                                "Framerate",});
        internal_static_livekit_InputAudioState_descriptor = getDescriptor().getMessageTypes()
                .get(8);
        internal_static_livekit_InputAudioState_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_InputAudioState_descriptor,
                        new java.lang.String[]{"MimeType",
                                "AverageBitrate",
                                "Channels",
                                "SampleRate",});
        internal_static_livekit_UpdateIngressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(9);
        internal_static_livekit_UpdateIngressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateIngressRequest_descriptor,
                        new java.lang.String[]{"IngressId",
                                "Name",
                                "RoomName",
                                "ParticipantIdentity",
                                "ParticipantName",
                                "ParticipantMetadata",
                                "BypassTranscoding",
                                "EnableTranscoding",
                                "Audio",
                                "Video",});
        internal_static_livekit_ListIngressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(10);
        internal_static_livekit_ListIngressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListIngressRequest_descriptor,
                        new java.lang.String[]{"RoomName", "IngressId",});
        internal_static_livekit_ListIngressResponse_descriptor = getDescriptor().getMessageTypes()
                .get(11);
        internal_static_livekit_ListIngressResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListIngressResponse_descriptor,
                        new java.lang.String[]{"Items",});
        internal_static_livekit_DeleteIngressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(12);
        internal_static_livekit_DeleteIngressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DeleteIngressRequest_descriptor,
                        new java.lang.String[]{"IngressId",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}