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

public final class LivekitEgress {
    private LivekitEgress() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                LivekitEgress.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RoomCompositeEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RoomCompositeEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_WebEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_WebEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ParticipantEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ParticipantEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_TrackCompositeEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_TrackCompositeEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_TrackEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_TrackEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_EncodedFileOutput_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_EncodedFileOutput_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SegmentedFileOutput_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SegmentedFileOutput_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DirectFileOutput_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DirectFileOutput_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ImageOutput_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ImageOutput_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_S3Upload_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_S3Upload_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_S3Upload_MetadataEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_S3Upload_MetadataEntry_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_GCPUpload_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_GCPUpload_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_AzureBlobUpload_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_AzureBlobUpload_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_AliOSSUpload_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_AliOSSUpload_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ProxyConfig_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ProxyConfig_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_StreamOutput_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_StreamOutput_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_EncodingOptions_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_EncodingOptions_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateLayoutRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateLayoutRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateStreamRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateStreamRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListEgressResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListEgressResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_StopEgressRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_StopEgressRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_EgressInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_EgressInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_StreamInfoList_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_StreamInfoList_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_StreamInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_StreamInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_FileInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_FileInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SegmentsInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SegmentsInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ImagesInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ImagesInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_AutoParticipantEgress_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_AutoParticipantEgress_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_AutoTrackEgress_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_AutoTrackEgress_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData =
                {"\n\024livekit_egress.proto\022\007livekit\032\024livekit"
                        + "_models.proto\"\315\004\n\032RoomCompositeEgressReq"
                        + "uest\022\021\n\troom_name\030\001 \001(\t\022\016\n\006layout\030\002 \001(\t\022"
                        + "\022\n\naudio_only\030\003 \001(\010\022\022\n\nvideo_only\030\004 \001(\010\022"
                        + "\027\n\017custom_base_url\030\005 \001(\t\022.\n\004file\030\006 \001(\0132\032"
                        + ".livekit.EncodedFileOutputB\002\030\001H\000\022+\n\006stre"
                        + "am\030\007 \001(\0132\025.livekit.StreamOutputB\002\030\001H\000\0224\n"
                        + "\010segments\030\n \001(\0132\034.livekit.SegmentedFileO"
                        + "utputB\002\030\001H\000\0220\n\006preset\030\010 \001(\0162\036.livekit.En"
                        + "codingOptionsPresetH\001\022,\n\010advanced\030\t \001(\0132"
                        + "\030.livekit.EncodingOptionsH\001\0220\n\014file_outp"
                        + "uts\030\013 \003(\0132\032.livekit.EncodedFileOutput\022-\n"
                        + "\016stream_outputs\030\014 \003(\0132\025.livekit.StreamOu"
                        + "tput\0225\n\017segment_outputs\030\r \003(\0132\034.livekit."
                        + "SegmentedFileOutput\022+\n\rimage_outputs\030\016 \003"
                        + "(\0132\024.livekit.ImageOutputB\010\n\006outputB\t\n\007op"
                        + "tions\"\260\004\n\020WebEgressRequest\022\013\n\003url\030\001 \001(\t\022"
                        + "\022\n\naudio_only\030\002 \001(\010\022\022\n\nvideo_only\030\003 \001(\010\022"
                        + "\032\n\022await_start_signal\030\014 \001(\010\022.\n\004file\030\004 \001("
                        + "\0132\032.livekit.EncodedFileOutputB\002\030\001H\000\022+\n\006s"
                        + "tream\030\005 \001(\0132\025.livekit.StreamOutputB\002\030\001H\000"
                        + "\0224\n\010segments\030\006 \001(\0132\034.livekit.SegmentedFi"
                        + "leOutputB\002\030\001H\000\0220\n\006preset\030\007 \001(\0162\036.livekit"
                        + ".EncodingOptionsPresetH\001\022,\n\010advanced\030\010 \001"
                        + "(\0132\030.livekit.EncodingOptionsH\001\0220\n\014file_o"
                        + "utputs\030\t \003(\0132\032.livekit.EncodedFileOutput"
                        + "\022-\n\016stream_outputs\030\n \003(\0132\025.livekit.Strea"
                        + "mOutput\0225\n\017segment_outputs\030\013 \003(\0132\034.livek"
                        + "it.SegmentedFileOutput\022+\n\rimage_outputs\030"
                        + "\r \003(\0132\024.livekit.ImageOutputB\010\n\006outputB\t\n"
                        + "\007options\"\205\003\n\030ParticipantEgressRequest\022\021\n"
                        + "\troom_name\030\001 \001(\t\022\020\n\010identity\030\002 \001(\t\022\024\n\014sc"
                        + "reen_share\030\003 \001(\010\0220\n\006preset\030\004 \001(\0162\036.livek"
                        + "it.EncodingOptionsPresetH\000\022,\n\010advanced\030\005"
                        + " \001(\0132\030.livekit.EncodingOptionsH\000\0220\n\014file"
                        + "_outputs\030\006 \003(\0132\032.livekit.EncodedFileOutp"
                        + "ut\022-\n\016stream_outputs\030\007 \003(\0132\025.livekit.Str"
                        + "eamOutput\0225\n\017segment_outputs\030\010 \003(\0132\034.liv"
                        + "ekit.SegmentedFileOutput\022+\n\rimage_output"
                        + "s\030\t \003(\0132\024.livekit.ImageOutputB\t\n\007options"
                        + "\"\255\004\n\033TrackCompositeEgressRequest\022\021\n\troom"
                        + "_name\030\001 \001(\t\022\026\n\016audio_track_id\030\002 \001(\t\022\026\n\016v"
                        + "ideo_track_id\030\003 \001(\t\022.\n\004file\030\004 \001(\0132\032.live"
                        + "kit.EncodedFileOutputB\002\030\001H\000\022+\n\006stream\030\005 "
                        + "\001(\0132\025.livekit.StreamOutputB\002\030\001H\000\0224\n\010segm"
                        + "ents\030\010 \001(\0132\034.livekit.SegmentedFileOutput"
                        + "B\002\030\001H\000\0220\n\006preset\030\006 \001(\0162\036.livekit.Encodin"
                        + "gOptionsPresetH\001\022,\n\010advanced\030\007 \001(\0132\030.liv"
                        + "ekit.EncodingOptionsH\001\0220\n\014file_outputs\030\013"
                        + " \003(\0132\032.livekit.EncodedFileOutput\022-\n\016stre"
                        + "am_outputs\030\014 \003(\0132\025.livekit.StreamOutput\022"
                        + "5\n\017segment_outputs\030\r \003(\0132\034.livekit.Segme"
                        + "ntedFileOutput\022+\n\rimage_outputs\030\016 \003(\0132\024."
                        + "livekit.ImageOutputB\010\n\006outputB\t\n\007options"
                        + "\"\207\001\n\022TrackEgressRequest\022\021\n\troom_name\030\001 \001"
                        + "(\t\022\020\n\010track_id\030\002 \001(\t\022)\n\004file\030\003 \001(\0132\031.liv"
                        + "ekit.DirectFileOutputH\000\022\027\n\rwebsocket_url"
                        + "\030\004 \001(\tH\000B\010\n\006output\"\216\002\n\021EncodedFileOutput"
                        + "\022+\n\tfile_type\030\001 \001(\0162\030.livekit.EncodedFil"
                        + "eType\022\020\n\010filepath\030\002 \001(\t\022\030\n\020disable_manif"
                        + "est\030\006 \001(\010\022\037\n\002s3\030\003 \001(\0132\021.livekit.S3Upload"
                        + "H\000\022!\n\003gcp\030\004 \001(\0132\022.livekit.GCPUploadH\000\022)\n"
                        + "\005azure\030\005 \001(\0132\030.livekit.AzureBlobUploadH\000"
                        + "\022\'\n\006aliOSS\030\007 \001(\0132\025.livekit.AliOSSUploadH"
                        + "\000B\010\n\006output\"\240\003\n\023SegmentedFileOutput\0220\n\010p"
                        + "rotocol\030\001 \001(\0162\036.livekit.SegmentedFilePro"
                        + "tocol\022\027\n\017filename_prefix\030\002 \001(\t\022\025\n\rplayli"
                        + "st_name\030\003 \001(\t\022\032\n\022live_playlist_name\030\013 \001("
                        + "\t\022\030\n\020segment_duration\030\004 \001(\r\0225\n\017filename_"
                        + "suffix\030\n \001(\0162\034.livekit.SegmentedFileSuff"
                        + "ix\022\030\n\020disable_manifest\030\010 \001(\010\022\037\n\002s3\030\005 \001(\013"
                        + "2\021.livekit.S3UploadH\000\022!\n\003gcp\030\006 \001(\0132\022.liv"
                        + "ekit.GCPUploadH\000\022)\n\005azure\030\007 \001(\0132\030.liveki"
                        + "t.AzureBlobUploadH\000\022\'\n\006aliOSS\030\t \001(\0132\025.li"
                        + "vekit.AliOSSUploadH\000B\010\n\006output\"\340\001\n\020Direc"
                        + "tFileOutput\022\020\n\010filepath\030\001 \001(\t\022\030\n\020disable"
                        + "_manifest\030\005 \001(\010\022\037\n\002s3\030\002 \001(\0132\021.livekit.S3"
                        + "UploadH\000\022!\n\003gcp\030\003 \001(\0132\022.livekit.GCPUploa"
                        + "dH\000\022)\n\005azure\030\004 \001(\0132\030.livekit.AzureBlobUp"
                        + "loadH\000\022\'\n\006aliOSS\030\006 \001(\0132\025.livekit.AliOSSU"
                        + "ploadH\000B\010\n\006output\"\370\002\n\013ImageOutput\022\030\n\020cap"
                        + "ture_interval\030\001 \001(\r\022\r\n\005width\030\002 \001(\005\022\016\n\006he"
                        + "ight\030\003 \001(\005\022\027\n\017filename_prefix\030\004 \001(\t\0221\n\017f"
                        + "ilename_suffix\030\005 \001(\0162\030.livekit.ImageFile"
                        + "Suffix\022(\n\013image_codec\030\006 \001(\0162\023.livekit.Im"
                        + "ageCodec\022\030\n\020disable_manifest\030\007 \001(\010\022\037\n\002s3"
                        + "\030\010 \001(\0132\021.livekit.S3UploadH\000\022!\n\003gcp\030\t \001(\013"
                        + "2\022.livekit.GCPUploadH\000\022)\n\005azure\030\n \001(\0132\030."
                        + "livekit.AzureBlobUploadH\000\022\'\n\006aliOSS\030\013 \001("
                        + "\0132\025.livekit.AliOSSUploadH\000B\010\n\006output\"\261\002\n"
                        + "\010S3Upload\022\022\n\naccess_key\030\001 \001(\t\022\016\n\006secret\030"
                        + "\002 \001(\t\022\016\n\006region\030\003 \001(\t\022\020\n\010endpoint\030\004 \001(\t\022"
                        + "\016\n\006bucket\030\005 \001(\t\022\030\n\020force_path_style\030\006 \001("
                        + "\010\0221\n\010metadata\030\007 \003(\0132\037.livekit.S3Upload.M"
                        + "etadataEntry\022\017\n\007tagging\030\010 \001(\t\022\033\n\023content"
                        + "_disposition\030\t \001(\t\022#\n\005proxy\030\n \001(\0132\024.live"
                        + "kit.ProxyConfig\032/\n\rMetadataEntry\022\013\n\003key\030"
                        + "\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"U\n\tGCPUpload\022\023\n"
                        + "\013credentials\030\001 \001(\t\022\016\n\006bucket\030\002 \001(\t\022#\n\005pr"
                        + "oxy\030\003 \001(\0132\024.livekit.ProxyConfig\"T\n\017Azure"
                        + "BlobUpload\022\024\n\014account_name\030\001 \001(\t\022\023\n\013acco"
                        + "unt_key\030\002 \001(\t\022\026\n\016container_name\030\003 \001(\t\"d\n"
                        + "\014AliOSSUpload\022\022\n\naccess_key\030\001 \001(\t\022\016\n\006sec"
                        + "ret\030\002 \001(\t\022\016\n\006region\030\003 \001(\t\022\020\n\010endpoint\030\004 "
                        + "\001(\t\022\016\n\006bucket\030\005 \001(\t\">\n\013ProxyConfig\022\013\n\003ur"
                        + "l\030\001 \001(\t\022\020\n\010username\030\002 \001(\t\022\020\n\010password\030\003 "
                        + "\001(\t\"G\n\014StreamOutput\022)\n\010protocol\030\001 \001(\0162\027."
                        + "livekit.StreamProtocol\022\014\n\004urls\030\002 \003(\t\"\267\002\n"
                        + "\017EncodingOptions\022\r\n\005width\030\001 \001(\005\022\016\n\006heigh"
                        + "t\030\002 \001(\005\022\r\n\005depth\030\003 \001(\005\022\021\n\tframerate\030\004 \001("
                        + "\005\022(\n\013audio_codec\030\005 \001(\0162\023.livekit.AudioCo"
                        + "dec\022\025\n\raudio_bitrate\030\006 \001(\005\022\025\n\raudio_qual"
                        + "ity\030\013 \001(\005\022\027\n\017audio_frequency\030\007 \001(\005\022(\n\013vi"
                        + "deo_codec\030\010 \001(\0162\023.livekit.VideoCodec\022\025\n\r"
                        + "video_bitrate\030\t \001(\005\022\025\n\rvideo_quality\030\014 \001"
                        + "(\005\022\032\n\022key_frame_interval\030\n \001(\001\"8\n\023Update"
                        + "LayoutRequest\022\021\n\tegress_id\030\001 \001(\t\022\016\n\006layo"
                        + "ut\030\002 \001(\t\"]\n\023UpdateStreamRequest\022\021\n\tegres"
                        + "s_id\030\001 \001(\t\022\027\n\017add_output_urls\030\002 \003(\t\022\032\n\022r"
                        + "emove_output_urls\030\003 \003(\t\"I\n\021ListEgressReq"
                        + "uest\022\021\n\troom_name\030\001 \001(\t\022\021\n\tegress_id\030\002 \001"
                        + "(\t\022\016\n\006active\030\003 \001(\010\"8\n\022ListEgressResponse"
                        + "\022\"\n\005items\030\001 \003(\0132\023.livekit.EgressInfo\"&\n\021"
                        + "StopEgressRequest\022\021\n\tegress_id\030\001 \001(\t\"\242\006\n"
                        + "\nEgressInfo\022\021\n\tegress_id\030\001 \001(\t\022\017\n\007room_i"
                        + "d\030\002 \001(\t\022\021\n\troom_name\030\r \001(\t\022%\n\006status\030\003 \001"
                        + "(\0162\025.livekit.EgressStatus\022\022\n\nstarted_at\030"
                        + "\n \001(\003\022\020\n\010ended_at\030\013 \001(\003\022\022\n\nupdated_at\030\022 "
                        + "\001(\003\022\017\n\007details\030\025 \001(\t\022\r\n\005error\030\t \001(\t\022=\n\016r"
                        + "oom_composite\030\004 \001(\0132#.livekit.RoomCompos"
                        + "iteEgressRequestH\000\022(\n\003web\030\016 \001(\0132\031.liveki"
                        + "t.WebEgressRequestH\000\0228\n\013participant\030\023 \001("
                        + "\0132!.livekit.ParticipantEgressRequestH\000\022?"
                        + "\n\017track_composite\030\005 \001(\0132$.livekit.TrackC"
                        + "ompositeEgressRequestH\000\022,\n\005track\030\006 \001(\0132\033"
                        + ".livekit.TrackEgressRequestH\000\022-\n\006stream\030"
                        + "\007 \001(\0132\027.livekit.StreamInfoListB\002\030\001H\001\022%\n\004"
                        + "file\030\010 \001(\0132\021.livekit.FileInfoB\002\030\001H\001\022-\n\010s"
                        + "egments\030\014 \001(\0132\025.livekit.SegmentsInfoB\002\030\001"
                        + "H\001\022+\n\016stream_results\030\017 \003(\0132\023.livekit.Str"
                        + "eamInfo\022\'\n\014file_results\030\020 \003(\0132\021.livekit."
                        + "FileInfo\022.\n\017segment_results\030\021 \003(\0132\025.live"
                        + "kit.SegmentsInfo\022*\n\rimage_results\030\024 \003(\0132"
                        + "\023.livekit.ImagesInfoB\t\n\007requestB\010\n\006resul"
                        + "t\"7\n\016StreamInfoList\022!\n\004info\030\001 \003(\0132\023.live"
                        + "kit.StreamInfo:\002\030\001\"\274\001\n\nStreamInfo\022\013\n\003url"
                        + "\030\001 \001(\t\022\022\n\nstarted_at\030\002 \001(\003\022\020\n\010ended_at\030\003"
                        + " \001(\003\022\020\n\010duration\030\004 \001(\003\022*\n\006status\030\005 \001(\0162\032"
                        + ".livekit.StreamInfo.Status\022\r\n\005error\030\006 \001("
                        + "\t\".\n\006Status\022\n\n\006ACTIVE\020\000\022\014\n\010FINISHED\020\001\022\n\n"
                        + "\006FAILED\020\002\"t\n\010FileInfo\022\020\n\010filename\030\001 \001(\t\022"
                        + "\022\n\nstarted_at\030\002 \001(\003\022\020\n\010ended_at\030\003 \001(\003\022\020\n"
                        + "\010duration\030\006 \001(\003\022\014\n\004size\030\004 \001(\003\022\020\n\010locatio"
                        + "n\030\005 \001(\t\"\331\001\n\014SegmentsInfo\022\025\n\rplaylist_nam"
                        + "e\030\001 \001(\t\022\032\n\022live_playlist_name\030\010 \001(\t\022\020\n\010d"
                        + "uration\030\002 \001(\003\022\014\n\004size\030\003 \001(\003\022\031\n\021playlist_"
                        + "location\030\004 \001(\t\022\036\n\026live_playlist_location"
                        + "\030\t \001(\t\022\025\n\rsegment_count\030\005 \001(\003\022\022\n\nstarted"
                        + "_at\030\006 \001(\003\022\020\n\010ended_at\030\007 \001(\003\"G\n\nImagesInf"
                        + "o\022\023\n\013image_count\030\001 \001(\003\022\022\n\nstarted_at\030\002 \001"
                        + "(\003\022\020\n\010ended_at\030\003 \001(\003\"\353\001\n\025AutoParticipant"
                        + "Egress\0220\n\006preset\030\001 \001(\0162\036.livekit.Encodin"
                        + "gOptionsPresetH\000\022,\n\010advanced\030\002 \001(\0132\030.liv"
                        + "ekit.EncodingOptionsH\000\0220\n\014file_outputs\030\003"
                        + " \003(\0132\032.livekit.EncodedFileOutput\0225\n\017segm"
                        + "ent_outputs\030\004 \003(\0132\034.livekit.SegmentedFil"
                        + "eOutputB\t\n\007options\"\266\001\n\017AutoTrackEgress\022\020"
                        + "\n\010filepath\030\001 \001(\t\022\030\n\020disable_manifest\030\005 \001"
                        + "(\010\022\037\n\002s3\030\002 \001(\0132\021.livekit.S3UploadH\000\022!\n\003g"
                        + "cp\030\003 \001(\0132\022.livekit.GCPUploadH\000\022)\n\005azure\030"
                        + "\004 \001(\0132\030.livekit.AzureBlobUploadH\000B\010\n\006out"
                        + "put*9\n\017EncodedFileType\022\024\n\020DEFAULT_FILETY"
                        + "PE\020\000\022\007\n\003MP4\020\001\022\007\n\003OGG\020\002*N\n\025SegmentedFileP"
                        + "rotocol\022#\n\037DEFAULT_SEGMENTED_FILE_PROTOC"
                        + "OL\020\000\022\020\n\014HLS_PROTOCOL\020\001*/\n\023SegmentedFileS"
                        + "uffix\022\t\n\005INDEX\020\000\022\r\n\tTIMESTAMP\020\001*E\n\017Image"
                        + "FileSuffix\022\026\n\022IMAGE_SUFFIX_INDEX\020\000\022\032\n\026IM"
                        + "AGE_SUFFIX_TIMESTAMP\020\001*0\n\016StreamProtocol"
                        + "\022\024\n\020DEFAULT_PROTOCOL\020\000\022\010\n\004RTMP\020\001*\317\001\n\025Enc"
                        + "odingOptionsPreset\022\020\n\014H264_720P_30\020\000\022\020\n\014"
                        + "H264_720P_60\020\001\022\021\n\rH264_1080P_30\020\002\022\021\n\rH26"
                        + "4_1080P_60\020\003\022\031\n\025PORTRAIT_H264_720P_30\020\004\022"
                        + "\031\n\025PORTRAIT_H264_720P_60\020\005\022\032\n\026PORTRAIT_H"
                        + "264_1080P_30\020\006\022\032\n\026PORTRAIT_H264_1080P_60"
                        + "\020\007*\237\001\n\014EgressStatus\022\023\n\017EGRESS_STARTING\020\000"
                        + "\022\021\n\rEGRESS_ACTIVE\020\001\022\021\n\rEGRESS_ENDING\020\002\022\023"
                        + "\n\017EGRESS_COMPLETE\020\003\022\021\n\rEGRESS_FAILED\020\004\022\022"
                        + "\n\016EGRESS_ABORTED\020\005\022\030\n\024EGRESS_LIMIT_REACH"
                        + "ED\020\006Bs\n)im.turms.plugin.livekit.core.pro"
                        + "to.egressP\001Z#github.com/livekit/protocol"
                        + "/livekit\252\002\rLiveKit.Proto\352\002\016LiveKit::Prot"
                        + "ob\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor(),});
        internal_static_livekit_RoomCompositeEgressRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_livekit_RoomCompositeEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RoomCompositeEgressRequest_descriptor,
                        new java.lang.String[]{"RoomName",
                                "Layout",
                                "AudioOnly",
                                "VideoOnly",
                                "CustomBaseUrl",
                                "File",
                                "Stream",
                                "Segments",
                                "Preset",
                                "Advanced",
                                "FileOutputs",
                                "StreamOutputs",
                                "SegmentOutputs",
                                "ImageOutputs",
                                "Output",
                                "Options",});
        internal_static_livekit_WebEgressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(1);
        internal_static_livekit_WebEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_WebEgressRequest_descriptor,
                        new java.lang.String[]{"Url",
                                "AudioOnly",
                                "VideoOnly",
                                "AwaitStartSignal",
                                "File",
                                "Stream",
                                "Segments",
                                "Preset",
                                "Advanced",
                                "FileOutputs",
                                "StreamOutputs",
                                "SegmentOutputs",
                                "ImageOutputs",
                                "Output",
                                "Options",});
        internal_static_livekit_ParticipantEgressRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(2);
        internal_static_livekit_ParticipantEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ParticipantEgressRequest_descriptor,
                        new java.lang.String[]{"RoomName",
                                "Identity",
                                "ScreenShare",
                                "Preset",
                                "Advanced",
                                "FileOutputs",
                                "StreamOutputs",
                                "SegmentOutputs",
                                "ImageOutputs",
                                "Options",});
        internal_static_livekit_TrackCompositeEgressRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(3);
        internal_static_livekit_TrackCompositeEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_TrackCompositeEgressRequest_descriptor,
                        new java.lang.String[]{"RoomName",
                                "AudioTrackId",
                                "VideoTrackId",
                                "File",
                                "Stream",
                                "Segments",
                                "Preset",
                                "Advanced",
                                "FileOutputs",
                                "StreamOutputs",
                                "SegmentOutputs",
                                "ImageOutputs",
                                "Output",
                                "Options",});
        internal_static_livekit_TrackEgressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(4);
        internal_static_livekit_TrackEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_TrackEgressRequest_descriptor,
                        new java.lang.String[]{"RoomName",
                                "TrackId",
                                "File",
                                "WebsocketUrl",
                                "Output",});
        internal_static_livekit_EncodedFileOutput_descriptor = getDescriptor().getMessageTypes()
                .get(5);
        internal_static_livekit_EncodedFileOutput_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_EncodedFileOutput_descriptor,
                        new java.lang.String[]{"FileType",
                                "Filepath",
                                "DisableManifest",
                                "S3",
                                "Gcp",
                                "Azure",
                                "AliOSS",
                                "Output",});
        internal_static_livekit_SegmentedFileOutput_descriptor = getDescriptor().getMessageTypes()
                .get(6);
        internal_static_livekit_SegmentedFileOutput_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SegmentedFileOutput_descriptor,
                        new java.lang.String[]{"Protocol",
                                "FilenamePrefix",
                                "PlaylistName",
                                "LivePlaylistName",
                                "SegmentDuration",
                                "FilenameSuffix",
                                "DisableManifest",
                                "S3",
                                "Gcp",
                                "Azure",
                                "AliOSS",
                                "Output",});
        internal_static_livekit_DirectFileOutput_descriptor = getDescriptor().getMessageTypes()
                .get(7);
        internal_static_livekit_DirectFileOutput_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DirectFileOutput_descriptor,
                        new java.lang.String[]{"Filepath",
                                "DisableManifest",
                                "S3",
                                "Gcp",
                                "Azure",
                                "AliOSS",
                                "Output",});
        internal_static_livekit_ImageOutput_descriptor = getDescriptor().getMessageTypes()
                .get(8);
        internal_static_livekit_ImageOutput_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ImageOutput_descriptor,
                        new java.lang.String[]{"CaptureInterval",
                                "Width",
                                "Height",
                                "FilenamePrefix",
                                "FilenameSuffix",
                                "ImageCodec",
                                "DisableManifest",
                                "S3",
                                "Gcp",
                                "Azure",
                                "AliOSS",
                                "Output",});
        internal_static_livekit_S3Upload_descriptor = getDescriptor().getMessageTypes()
                .get(9);
        internal_static_livekit_S3Upload_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_S3Upload_descriptor,
                        new java.lang.String[]{"AccessKey",
                                "Secret",
                                "Region",
                                "Endpoint",
                                "Bucket",
                                "ForcePathStyle",
                                "Metadata",
                                "Tagging",
                                "ContentDisposition",
                                "Proxy",});
        internal_static_livekit_S3Upload_MetadataEntry_descriptor =
                internal_static_livekit_S3Upload_descriptor.getNestedTypes()
                        .get(0);
        internal_static_livekit_S3Upload_MetadataEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_S3Upload_MetadataEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        internal_static_livekit_GCPUpload_descriptor = getDescriptor().getMessageTypes()
                .get(10);
        internal_static_livekit_GCPUpload_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_GCPUpload_descriptor,
                        new java.lang.String[]{"Credentials", "Bucket", "Proxy",});
        internal_static_livekit_AzureBlobUpload_descriptor = getDescriptor().getMessageTypes()
                .get(11);
        internal_static_livekit_AzureBlobUpload_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_AzureBlobUpload_descriptor,
                        new java.lang.String[]{"AccountName", "AccountKey", "ContainerName",});
        internal_static_livekit_AliOSSUpload_descriptor = getDescriptor().getMessageTypes()
                .get(12);
        internal_static_livekit_AliOSSUpload_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_AliOSSUpload_descriptor,
                        new java.lang.String[]{"AccessKey",
                                "Secret",
                                "Region",
                                "Endpoint",
                                "Bucket",});
        internal_static_livekit_ProxyConfig_descriptor = getDescriptor().getMessageTypes()
                .get(13);
        internal_static_livekit_ProxyConfig_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ProxyConfig_descriptor,
                        new java.lang.String[]{"Url", "Username", "Password",});
        internal_static_livekit_StreamOutput_descriptor = getDescriptor().getMessageTypes()
                .get(14);
        internal_static_livekit_StreamOutput_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_StreamOutput_descriptor,
                        new java.lang.String[]{"Protocol", "Urls",});
        internal_static_livekit_EncodingOptions_descriptor = getDescriptor().getMessageTypes()
                .get(15);
        internal_static_livekit_EncodingOptions_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_EncodingOptions_descriptor,
                        new java.lang.String[]{"Width",
                                "Height",
                                "Depth",
                                "Framerate",
                                "AudioCodec",
                                "AudioBitrate",
                                "AudioQuality",
                                "AudioFrequency",
                                "VideoCodec",
                                "VideoBitrate",
                                "VideoQuality",
                                "KeyFrameInterval",});
        internal_static_livekit_UpdateLayoutRequest_descriptor = getDescriptor().getMessageTypes()
                .get(16);
        internal_static_livekit_UpdateLayoutRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateLayoutRequest_descriptor,
                        new java.lang.String[]{"EgressId", "Layout",});
        internal_static_livekit_UpdateStreamRequest_descriptor = getDescriptor().getMessageTypes()
                .get(17);
        internal_static_livekit_UpdateStreamRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateStreamRequest_descriptor,
                        new java.lang.String[]{"EgressId", "AddOutputUrls", "RemoveOutputUrls",});
        internal_static_livekit_ListEgressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(18);
        internal_static_livekit_ListEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListEgressRequest_descriptor,
                        new java.lang.String[]{"RoomName", "EgressId", "Active",});
        internal_static_livekit_ListEgressResponse_descriptor = getDescriptor().getMessageTypes()
                .get(19);
        internal_static_livekit_ListEgressResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListEgressResponse_descriptor,
                        new java.lang.String[]{"Items",});
        internal_static_livekit_StopEgressRequest_descriptor = getDescriptor().getMessageTypes()
                .get(20);
        internal_static_livekit_StopEgressRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_StopEgressRequest_descriptor,
                        new java.lang.String[]{"EgressId",});
        internal_static_livekit_EgressInfo_descriptor = getDescriptor().getMessageTypes()
                .get(21);
        internal_static_livekit_EgressInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_EgressInfo_descriptor,
                        new java.lang.String[]{"EgressId",
                                "RoomId",
                                "RoomName",
                                "Status",
                                "StartedAt",
                                "EndedAt",
                                "UpdatedAt",
                                "Details",
                                "Error",
                                "RoomComposite",
                                "Web",
                                "Participant",
                                "TrackComposite",
                                "Track",
                                "Stream",
                                "File",
                                "Segments",
                                "StreamResults",
                                "FileResults",
                                "SegmentResults",
                                "ImageResults",
                                "Request",
                                "Result",});
        internal_static_livekit_StreamInfoList_descriptor = getDescriptor().getMessageTypes()
                .get(22);
        internal_static_livekit_StreamInfoList_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_StreamInfoList_descriptor,
                        new java.lang.String[]{"Info",});
        internal_static_livekit_StreamInfo_descriptor = getDescriptor().getMessageTypes()
                .get(23);
        internal_static_livekit_StreamInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_StreamInfo_descriptor,
                        new java.lang.String[]{"Url",
                                "StartedAt",
                                "EndedAt",
                                "Duration",
                                "Status",
                                "Error",});
        internal_static_livekit_FileInfo_descriptor = getDescriptor().getMessageTypes()
                .get(24);
        internal_static_livekit_FileInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_FileInfo_descriptor,
                        new java.lang.String[]{"Filename",
                                "StartedAt",
                                "EndedAt",
                                "Duration",
                                "Size",
                                "Location",});
        internal_static_livekit_SegmentsInfo_descriptor = getDescriptor().getMessageTypes()
                .get(25);
        internal_static_livekit_SegmentsInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SegmentsInfo_descriptor,
                        new java.lang.String[]{"PlaylistName",
                                "LivePlaylistName",
                                "Duration",
                                "Size",
                                "PlaylistLocation",
                                "LivePlaylistLocation",
                                "SegmentCount",
                                "StartedAt",
                                "EndedAt",});
        internal_static_livekit_ImagesInfo_descriptor = getDescriptor().getMessageTypes()
                .get(26);
        internal_static_livekit_ImagesInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ImagesInfo_descriptor,
                        new java.lang.String[]{"ImageCount", "StartedAt", "EndedAt",});
        internal_static_livekit_AutoParticipantEgress_descriptor = getDescriptor().getMessageTypes()
                .get(27);
        internal_static_livekit_AutoParticipantEgress_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_AutoParticipantEgress_descriptor,
                        new java.lang.String[]{"Preset",
                                "Advanced",
                                "FileOutputs",
                                "SegmentOutputs",
                                "Options",});
        internal_static_livekit_AutoTrackEgress_descriptor = getDescriptor().getMessageTypes()
                .get(28);
        internal_static_livekit_AutoTrackEgress_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_AutoTrackEgress_descriptor,
                        new java.lang.String[]{"Filepath",
                                "DisableManifest",
                                "S3",
                                "Gcp",
                                "Azure",
                                "Output",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}