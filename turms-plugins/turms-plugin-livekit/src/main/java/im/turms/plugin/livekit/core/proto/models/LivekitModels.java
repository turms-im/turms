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

public final class LivekitModels {
    private LivekitModels() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                LivekitModels.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_Room_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_Room_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_Codec_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_Codec_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_PlayoutDelay_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_PlayoutDelay_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ParticipantPermission_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ParticipantPermission_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ParticipantInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ParticipantInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_Encryption_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_Encryption_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SimulcastCodecInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SimulcastCodecInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_TrackInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_TrackInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_VideoLayer_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_VideoLayer_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DataPacket_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DataPacket_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ActiveSpeakerUpdate_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ActiveSpeakerUpdate_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SpeakerInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SpeakerInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UserPacket_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UserPacket_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SipDTMF_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SipDTMF_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ParticipantTracks_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ParticipantTracks_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ServerInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ServerInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ClientInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ClientInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ClientConfiguration_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ClientConfiguration_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_VideoConfiguration_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_VideoConfiguration_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DisabledCodecs_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DisabledCodecs_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RTPDrift_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RTPDrift_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RTPStats_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RTPStats_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RTPStats_GapHistogramEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RTPStats_GapHistogramEntry_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_TimedVersion_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_TimedVersion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData =
                {"\n\024livekit_models.proto\022\007livekit\032\037google/"
                        + "protobuf/timestamp.proto\"\311\002\n\004Room\022\013\n\003sid"
                        + "\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\025\n\rempty_timeout\030\003 "
                        + "\001(\r\022\031\n\021departure_timeout\030\016 \001(\r\022\030\n\020max_pa"
                        + "rticipants\030\004 \001(\r\022\025\n\rcreation_time\030\005 \001(\003\022"
                        + "\025\n\rturn_password\030\006 \001(\t\022&\n\016enabled_codecs"
                        + "\030\007 \003(\0132\016.livekit.Codec\022\020\n\010metadata\030\010 \001(\t"
                        + "\022\030\n\020num_participants\030\t \001(\r\022\026\n\016num_publis"
                        + "hers\030\013 \001(\r\022\030\n\020active_recording\030\n \001(\010\022&\n\007"
                        + "version\030\r \001(\0132\025.livekit.TimedVersion\"(\n\005"
                        + "Codec\022\014\n\004mime\030\001 \001(\t\022\021\n\tfmtp_line\030\002 \001(\t\"9"
                        + "\n\014PlayoutDelay\022\017\n\007enabled\030\001 \001(\010\022\013\n\003min\030\002"
                        + " \001(\r\022\013\n\003max\030\003 \001(\r\"\336\001\n\025ParticipantPermiss"
                        + "ion\022\025\n\rcan_subscribe\030\001 \001(\010\022\023\n\013can_publis"
                        + "h\030\002 \001(\010\022\030\n\020can_publish_data\030\003 \001(\010\0221\n\023can"
                        + "_publish_sources\030\t \003(\0162\024.livekit.TrackSo"
                        + "urce\022\016\n\006hidden\030\007 \001(\010\022\020\n\010recorder\030\010 \001(\010\022\033"
                        + "\n\023can_update_metadata\030\n \001(\010\022\r\n\005agent\030\013 \001"
                        + "(\010\"\321\003\n\017ParticipantInfo\022\013\n\003sid\030\001 \001(\t\022\020\n\010i"
                        + "dentity\030\002 \001(\t\022-\n\005state\030\003 \001(\0162\036.livekit.P"
                        + "articipantInfo.State\022\"\n\006tracks\030\004 \003(\0132\022.l"
                        + "ivekit.TrackInfo\022\020\n\010metadata\030\005 \001(\t\022\021\n\tjo"
                        + "ined_at\030\006 \001(\003\022\014\n\004name\030\t \001(\t\022\017\n\007version\030\n"
                        + " \001(\r\0222\n\npermission\030\013 \001(\0132\036.livekit.Parti"
                        + "cipantPermission\022\016\n\006region\030\014 \001(\t\022\024\n\014is_p"
                        + "ublisher\030\r \001(\010\022+\n\004kind\030\016 \001(\0162\035.livekit.P"
                        + "articipantInfo.Kind\">\n\005State\022\013\n\007JOINING\020"
                        + "\000\022\n\n\006JOINED\020\001\022\n\n\006ACTIVE\020\002\022\020\n\014DISCONNECTE"
                        + "D\020\003\"A\n\004Kind\022\014\n\010STANDARD\020\000\022\013\n\007INGRESS\020\001\022\n"
                        + "\n\006EGRESS\020\002\022\007\n\003SIP\020\003\022\t\n\005AGENT\020\004\"3\n\nEncryp"
                        + "tion\"%\n\004Type\022\010\n\004NONE\020\000\022\007\n\003GCM\020\001\022\n\n\006CUSTO"
                        + "M\020\002\"f\n\022SimulcastCodecInfo\022\021\n\tmime_type\030\001"
                        + " \001(\t\022\013\n\003mid\030\002 \001(\t\022\013\n\003cid\030\003 \001(\t\022#\n\006layers"
                        + "\030\004 \003(\0132\023.livekit.VideoLayer\"\301\003\n\tTrackInf"
                        + "o\022\013\n\003sid\030\001 \001(\t\022 \n\004type\030\002 \001(\0162\022.livekit.T"
                        + "rackType\022\014\n\004name\030\003 \001(\t\022\r\n\005muted\030\004 \001(\010\022\r\n"
                        + "\005width\030\005 \001(\r\022\016\n\006height\030\006 \001(\r\022\021\n\tsimulcas"
                        + "t\030\007 \001(\010\022\023\n\013disable_dtx\030\010 \001(\010\022$\n\006source\030\t"
                        + " \001(\0162\024.livekit.TrackSource\022#\n\006layers\030\n \003"
                        + "(\0132\023.livekit.VideoLayer\022\021\n\tmime_type\030\013 \001"
                        + "(\t\022\013\n\003mid\030\014 \001(\t\022+\n\006codecs\030\r \003(\0132\033.liveki"
                        + "t.SimulcastCodecInfo\022\016\n\006stereo\030\016 \001(\010\022\023\n\013"
                        + "disable_red\030\017 \001(\010\022,\n\nencryption\030\020 \001(\0162\030."
                        + "livekit.Encryption.Type\022\016\n\006stream\030\021 \001(\t\022"
                        + "&\n\007version\030\022 \001(\0132\025.livekit.TimedVersion\""
                        + "r\n\nVideoLayer\022&\n\007quality\030\001 \001(\0162\025.livekit"
                        + ".VideoQuality\022\r\n\005width\030\002 \001(\r\022\016\n\006height\030\003"
                        + " \001(\r\022\017\n\007bitrate\030\004 \001(\r\022\014\n\004ssrc\030\005 \001(\r\"\240\002\n\n"
                        + "DataPacket\022*\n\004kind\030\001 \001(\0162\030.livekit.DataP"
                        + "acket.KindB\002\030\001\022\034\n\024participant_identity\030\004"
                        + " \001(\t\022\036\n\026destination_identities\030\005 \003(\t\022#\n\004"
                        + "user\030\002 \001(\0132\023.livekit.UserPacketH\000\0223\n\007spe"
                        + "aker\030\003 \001(\0132\034.livekit.ActiveSpeakerUpdate"
                        + "B\002\030\001H\000\022$\n\010sip_dtmf\030\006 \001(\0132\020.livekit.SipDT"
                        + "MFH\000\"\037\n\004Kind\022\014\n\010RELIABLE\020\000\022\t\n\005LOSSY\020\001B\007\n"
                        + "\005value\"=\n\023ActiveSpeakerUpdate\022&\n\010speaker"
                        + "s\030\001 \003(\0132\024.livekit.SpeakerInfo\"9\n\013Speaker"
                        + "Info\022\013\n\003sid\030\001 \001(\t\022\r\n\005level\030\002 \001(\002\022\016\n\006acti"
                        + "ve\030\003 \001(\010\"\274\001\n\nUserPacket\022\033\n\017participant_s"
                        + "id\030\001 \001(\tB\002\030\001\022 \n\024participant_identity\030\005 \001"
                        + "(\tB\002\030\001\022\017\n\007payload\030\002 \001(\014\022\034\n\020destination_s"
                        + "ids\030\003 \003(\tB\002\030\001\022\"\n\026destination_identities\030"
                        + "\006 \003(\tB\002\030\001\022\022\n\005topic\030\004 \001(\tH\000\210\001\001B\010\n\006_topic\""
                        + "&\n\007SipDTMF\022\014\n\004code\030\003 \001(\r\022\r\n\005digit\030\004 \001(\t\""
                        + "@\n\021ParticipantTracks\022\027\n\017participant_sid\030"
                        + "\001 \001(\t\022\022\n\ntrack_sids\030\002 \003(\t\"\266\001\n\nServerInfo"
                        + "\022,\n\007edition\030\001 \001(\0162\033.livekit.ServerInfo.E"
                        + "dition\022\017\n\007version\030\002 \001(\t\022\020\n\010protocol\030\003 \001("
                        + "\005\022\016\n\006region\030\004 \001(\t\022\017\n\007node_id\030\005 \001(\t\022\022\n\nde"
                        + "bug_info\030\006 \001(\t\"\"\n\007Edition\022\014\n\010Standard\020\000\022"
                        + "\t\n\005Cloud\020\001\"\335\002\n\nClientInfo\022$\n\003sdk\030\001 \001(\0162\027"
                        + ".livekit.ClientInfo.SDK\022\017\n\007version\030\002 \001(\t"
                        + "\022\020\n\010protocol\030\003 \001(\005\022\n\n\002os\030\004 \001(\t\022\022\n\nos_ver"
                        + "sion\030\005 \001(\t\022\024\n\014device_model\030\006 \001(\t\022\017\n\007brow"
                        + "ser\030\007 \001(\t\022\027\n\017browser_version\030\010 \001(\t\022\017\n\007ad"
                        + "dress\030\t \001(\t\022\017\n\007network\030\n \001(\t\"\203\001\n\003SDK\022\013\n\007"
                        + "UNKNOWN\020\000\022\006\n\002JS\020\001\022\t\n\005SWIFT\020\002\022\013\n\007ANDROID\020"
                        + "\003\022\013\n\007FLUTTER\020\004\022\006\n\002GO\020\005\022\t\n\005UNITY\020\006\022\020\n\014REA"
                        + "CT_NATIVE\020\007\022\010\n\004RUST\020\010\022\n\n\006PYTHON\020\t\022\007\n\003CPP"
                        + "\020\n\"\214\002\n\023ClientConfiguration\022*\n\005video\030\001 \001("
                        + "\0132\033.livekit.VideoConfiguration\022+\n\006screen"
                        + "\030\002 \001(\0132\033.livekit.VideoConfiguration\0227\n\021r"
                        + "esume_connection\030\003 \001(\0162\034.livekit.ClientC"
                        + "onfigSetting\0220\n\017disabled_codecs\030\004 \001(\0132\027."
                        + "livekit.DisabledCodecs\0221\n\013force_relay\030\005 "
                        + "\001(\0162\034.livekit.ClientConfigSetting\"L\n\022Vid"
                        + "eoConfiguration\0226\n\020hardware_encoder\030\001 \001("
                        + "\0162\034.livekit.ClientConfigSetting\"Q\n\016Disab"
                        + "ledCodecs\022\036\n\006codecs\030\001 \003(\0132\016.livekit.Code"
                        + "c\022\037\n\007publish\030\002 \003(\0132\016.livekit.Codec\"\200\002\n\010R"
                        + "TPDrift\022.\n\nstart_time\030\001 \001(\0132\032.google.pro"
                        + "tobuf.Timestamp\022,\n\010end_time\030\002 \001(\0132\032.goog"
                        + "le.protobuf.Timestamp\022\020\n\010duration\030\003 \001(\001\022"
                        + "\027\n\017start_timestamp\030\004 \001(\004\022\025\n\rend_timestam"
                        + "p\030\005 \001(\004\022\027\n\017rtp_clock_ticks\030\006 \001(\004\022\025\n\rdrif"
                        + "t_samples\030\007 \001(\003\022\020\n\010drift_ms\030\010 \001(\001\022\022\n\nclo"
                        + "ck_rate\030\t \001(\001\"\240\n\n\010RTPStats\022.\n\nstart_time"
                        + "\030\001 \001(\0132\032.google.protobuf.Timestamp\022,\n\010en"
                        + "d_time\030\002 \001(\0132\032.google.protobuf.Timestamp"
                        + "\022\020\n\010duration\030\003 \001(\001\022\017\n\007packets\030\004 \001(\r\022\023\n\013p"
                        + "acket_rate\030\005 \001(\001\022\r\n\005bytes\030\006 \001(\004\022\024\n\014heade"
                        + "r_bytes\030\' \001(\004\022\017\n\007bitrate\030\007 \001(\001\022\024\n\014packet"
                        + "s_lost\030\010 \001(\r\022\030\n\020packet_loss_rate\030\t \001(\001\022\036"
                        + "\n\026packet_loss_percentage\030\n \001(\002\022\031\n\021packet"
                        + "s_duplicate\030\013 \001(\r\022\035\n\025packet_duplicate_ra"
                        + "te\030\014 \001(\001\022\027\n\017bytes_duplicate\030\r \001(\004\022\036\n\026hea"
                        + "der_bytes_duplicate\030( \001(\004\022\031\n\021bitrate_dup"
                        + "licate\030\016 \001(\001\022\027\n\017packets_padding\030\017 \001(\r\022\033\n"
                        + "\023packet_padding_rate\030\020 \001(\001\022\025\n\rbytes_padd"
                        + "ing\030\021 \001(\004\022\034\n\024header_bytes_padding\030) \001(\004\022"
                        + "\027\n\017bitrate_padding\030\022 \001(\001\022\034\n\024packets_out_"
                        + "of_order\030\023 \001(\r\022\016\n\006frames\030\024 \001(\r\022\022\n\nframe_"
                        + "rate\030\025 \001(\001\022\026\n\016jitter_current\030\026 \001(\001\022\022\n\nji"
                        + "tter_max\030\027 \001(\001\022:\n\rgap_histogram\030\030 \003(\0132#."
                        + "livekit.RTPStats.GapHistogramEntry\022\r\n\005na"
                        + "cks\030\031 \001(\r\022\021\n\tnack_acks\030% \001(\r\022\023\n\013nack_mis"
                        + "ses\030\032 \001(\r\022\025\n\rnack_repeated\030& \001(\r\022\014\n\004plis"
                        + "\030\033 \001(\r\022,\n\010last_pli\030\034 \001(\0132\032.google.protob"
                        + "uf.Timestamp\022\014\n\004firs\030\035 \001(\r\022,\n\010last_fir\030\036"
                        + " \001(\0132\032.google.protobuf.Timestamp\022\023\n\013rtt_"
                        + "current\030\037 \001(\r\022\017\n\007rtt_max\030  \001(\r\022\022\n\nkey_fr"
                        + "ames\030! \001(\r\0222\n\016last_key_frame\030\" \001(\0132\032.goo"
                        + "gle.protobuf.Timestamp\022\027\n\017layer_lock_pli"
                        + "s\030# \001(\r\0227\n\023last_layer_lock_pli\030$ \001(\0132\032.g"
                        + "oogle.protobuf.Timestamp\022\'\n\014packet_drift"
                        + "\030, \001(\0132\021.livekit.RTPDrift\022\'\n\014report_drif"
                        + "t\030- \001(\0132\021.livekit.RTPDrift\022/\n\024rebased_re"
                        + "port_drift\030. \001(\0132\021.livekit.RTPDrift\0323\n\021G"
                        + "apHistogramEntry\022\013\n\003key\030\001 \001(\005\022\r\n\005value\030\002"
                        + " \001(\r:\0028\001\"1\n\014TimedVersion\022\022\n\nunix_micro\030\001"
                        + " \001(\003\022\r\n\005ticks\030\002 \001(\005*/\n\nAudioCodec\022\016\n\nDEF"
                        + "AULT_AC\020\000\022\010\n\004OPUS\020\001\022\007\n\003AAC\020\002*V\n\nVideoCod"
                        + "ec\022\016\n\nDEFAULT_VC\020\000\022\021\n\rH264_BASELINE\020\001\022\r\n"
                        + "\tH264_MAIN\020\002\022\r\n\tH264_HIGH\020\003\022\007\n\003VP8\020\004*)\n\n"
                        + "ImageCodec\022\016\n\nIC_DEFAULT\020\000\022\013\n\007IC_JPEG\020\001*"
                        + "+\n\tTrackType\022\t\n\005AUDIO\020\000\022\t\n\005VIDEO\020\001\022\010\n\004DA"
                        + "TA\020\002*`\n\013TrackSource\022\013\n\007UNKNOWN\020\000\022\n\n\006CAME"
                        + "RA\020\001\022\016\n\nMICROPHONE\020\002\022\020\n\014SCREEN_SHARE\020\003\022\026"
                        + "\n\022SCREEN_SHARE_AUDIO\020\004*6\n\014VideoQuality\022\007"
                        + "\n\003LOW\020\000\022\n\n\006MEDIUM\020\001\022\010\n\004HIGH\020\002\022\007\n\003OFF\020\003*@"
                        + "\n\021ConnectionQuality\022\010\n\004POOR\020\000\022\010\n\004GOOD\020\001\022"
                        + "\r\n\tEXCELLENT\020\002\022\010\n\004LOST\020\003*;\n\023ClientConfig"
                        + "Setting\022\t\n\005UNSET\020\000\022\014\n\010DISABLED\020\001\022\013\n\007ENAB"
                        + "LED\020\002*\333\001\n\020DisconnectReason\022\022\n\016UNKNOWN_RE"
                        + "ASON\020\000\022\024\n\020CLIENT_INITIATED\020\001\022\026\n\022DUPLICAT"
                        + "E_IDENTITY\020\002\022\023\n\017SERVER_SHUTDOWN\020\003\022\027\n\023PAR"
                        + "TICIPANT_REMOVED\020\004\022\020\n\014ROOM_DELETED\020\005\022\022\n\016"
                        + "STATE_MISMATCH\020\006\022\020\n\014JOIN_FAILURE\020\007\022\r\n\tMI"
                        + "GRATION\020\010\022\020\n\014SIGNAL_CLOSE\020\t*\211\001\n\017Reconnec"
                        + "tReason\022\016\n\nRR_UNKNOWN\020\000\022\032\n\026RR_SIGNAL_DIS"
                        + "CONNECTED\020\001\022\027\n\023RR_PUBLISHER_FAILED\020\002\022\030\n\024"
                        + "RR_SUBSCRIBER_FAILED\020\003\022\027\n\023RR_SWITCH_CAND"
                        + "IDATE\020\004*T\n\021SubscriptionError\022\016\n\nSE_UNKNO"
                        + "WN\020\000\022\030\n\024SE_CODEC_UNSUPPORTED\020\001\022\025\n\021SE_TRA"
                        + "CK_NOTFOUND\020\002*\243\001\n\021AudioTrackFeature\022\r\n\tT"
                        + "F_STEREO\020\000\022\r\n\tTF_NO_DTX\020\001\022\030\n\024TF_AUTO_GAI"
                        + "N_CONTROL\020\002\022\030\n\024TF_ECHO_CANCELLATION\020\003\022\030\n"
                        + "\024TF_NOISE_SUPPRESSION\020\004\022\"\n\036TF_ENHANCED_N"
                        + "OISE_CANCELLATION\020\005Bs\n)im.turms.plugin.l"
                        + "ivekit.core.proto.modelsP\001Z#github.com/l"
                        + "ivekit/protocol/livekit\252\002\rLiveKit.Proto\352"
                        + "\002\016LiveKit::Protob\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        com.google.protobuf.TimestampProto.getDescriptor(),});
        internal_static_livekit_Room_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_livekit_Room_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_Room_descriptor,
                        new java.lang.String[]{"Sid",
                                "Name",
                                "EmptyTimeout",
                                "DepartureTimeout",
                                "MaxParticipants",
                                "CreationTime",
                                "TurnPassword",
                                "EnabledCodecs",
                                "Metadata",
                                "NumParticipants",
                                "NumPublishers",
                                "ActiveRecording",
                                "Version",});
        internal_static_livekit_Codec_descriptor = getDescriptor().getMessageTypes()
                .get(1);
        internal_static_livekit_Codec_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_Codec_descriptor,
                        new java.lang.String[]{"Mime", "FmtpLine",});
        internal_static_livekit_PlayoutDelay_descriptor = getDescriptor().getMessageTypes()
                .get(2);
        internal_static_livekit_PlayoutDelay_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_PlayoutDelay_descriptor,
                        new java.lang.String[]{"Enabled", "Min", "Max",});
        internal_static_livekit_ParticipantPermission_descriptor = getDescriptor().getMessageTypes()
                .get(3);
        internal_static_livekit_ParticipantPermission_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ParticipantPermission_descriptor,
                        new java.lang.String[]{"CanSubscribe",
                                "CanPublish",
                                "CanPublishData",
                                "CanPublishSources",
                                "Hidden",
                                "Recorder",
                                "CanUpdateMetadata",
                                "Agent",});
        internal_static_livekit_ParticipantInfo_descriptor = getDescriptor().getMessageTypes()
                .get(4);
        internal_static_livekit_ParticipantInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ParticipantInfo_descriptor,
                        new java.lang.String[]{"Sid",
                                "Identity",
                                "State",
                                "Tracks",
                                "Metadata",
                                "JoinedAt",
                                "Name",
                                "Version",
                                "Permission",
                                "Region",
                                "IsPublisher",
                                "Kind",});
        internal_static_livekit_Encryption_descriptor = getDescriptor().getMessageTypes()
                .get(5);
        internal_static_livekit_Encryption_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_Encryption_descriptor,
                        new java.lang.String[]{});
        internal_static_livekit_SimulcastCodecInfo_descriptor = getDescriptor().getMessageTypes()
                .get(6);
        internal_static_livekit_SimulcastCodecInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SimulcastCodecInfo_descriptor,
                        new java.lang.String[]{"MimeType", "Mid", "Cid", "Layers",});
        internal_static_livekit_TrackInfo_descriptor = getDescriptor().getMessageTypes()
                .get(7);
        internal_static_livekit_TrackInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_TrackInfo_descriptor,
                        new java.lang.String[]{"Sid",
                                "Type",
                                "Name",
                                "Muted",
                                "Width",
                                "Height",
                                "Simulcast",
                                "DisableDtx",
                                "Source",
                                "Layers",
                                "MimeType",
                                "Mid",
                                "Codecs",
                                "Stereo",
                                "DisableRed",
                                "Encryption",
                                "Stream",
                                "Version",});
        internal_static_livekit_VideoLayer_descriptor = getDescriptor().getMessageTypes()
                .get(8);
        internal_static_livekit_VideoLayer_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_VideoLayer_descriptor,
                        new java.lang.String[]{"Quality", "Width", "Height", "Bitrate", "Ssrc",});
        internal_static_livekit_DataPacket_descriptor = getDescriptor().getMessageTypes()
                .get(9);
        internal_static_livekit_DataPacket_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DataPacket_descriptor,
                        new java.lang.String[]{"Kind",
                                "ParticipantIdentity",
                                "DestinationIdentities",
                                "User",
                                "Speaker",
                                "SipDtmf",
                                "Value",});
        internal_static_livekit_ActiveSpeakerUpdate_descriptor = getDescriptor().getMessageTypes()
                .get(10);
        internal_static_livekit_ActiveSpeakerUpdate_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ActiveSpeakerUpdate_descriptor,
                        new java.lang.String[]{"Speakers",});
        internal_static_livekit_SpeakerInfo_descriptor = getDescriptor().getMessageTypes()
                .get(11);
        internal_static_livekit_SpeakerInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SpeakerInfo_descriptor,
                        new java.lang.String[]{"Sid", "Level", "Active",});
        internal_static_livekit_UserPacket_descriptor = getDescriptor().getMessageTypes()
                .get(12);
        internal_static_livekit_UserPacket_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UserPacket_descriptor,
                        new java.lang.String[]{"ParticipantSid",
                                "ParticipantIdentity",
                                "Payload",
                                "DestinationSids",
                                "DestinationIdentities",
                                "Topic",});
        internal_static_livekit_SipDTMF_descriptor = getDescriptor().getMessageTypes()
                .get(13);
        internal_static_livekit_SipDTMF_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SipDTMF_descriptor,
                        new java.lang.String[]{"Code", "Digit",});
        internal_static_livekit_ParticipantTracks_descriptor = getDescriptor().getMessageTypes()
                .get(14);
        internal_static_livekit_ParticipantTracks_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ParticipantTracks_descriptor,
                        new java.lang.String[]{"ParticipantSid", "TrackSids",});
        internal_static_livekit_ServerInfo_descriptor = getDescriptor().getMessageTypes()
                .get(15);
        internal_static_livekit_ServerInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ServerInfo_descriptor,
                        new java.lang.String[]{"Edition",
                                "Version",
                                "Protocol",
                                "Region",
                                "NodeId",
                                "DebugInfo",});
        internal_static_livekit_ClientInfo_descriptor = getDescriptor().getMessageTypes()
                .get(16);
        internal_static_livekit_ClientInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ClientInfo_descriptor,
                        new java.lang.String[]{"Sdk",
                                "Version",
                                "Protocol",
                                "Os",
                                "OsVersion",
                                "DeviceModel",
                                "Browser",
                                "BrowserVersion",
                                "Address",
                                "Network",});
        internal_static_livekit_ClientConfiguration_descriptor = getDescriptor().getMessageTypes()
                .get(17);
        internal_static_livekit_ClientConfiguration_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ClientConfiguration_descriptor,
                        new java.lang.String[]{"Video",
                                "Screen",
                                "ResumeConnection",
                                "DisabledCodecs",
                                "ForceRelay",});
        internal_static_livekit_VideoConfiguration_descriptor = getDescriptor().getMessageTypes()
                .get(18);
        internal_static_livekit_VideoConfiguration_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_VideoConfiguration_descriptor,
                        new java.lang.String[]{"HardwareEncoder",});
        internal_static_livekit_DisabledCodecs_descriptor = getDescriptor().getMessageTypes()
                .get(19);
        internal_static_livekit_DisabledCodecs_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DisabledCodecs_descriptor,
                        new java.lang.String[]{"Codecs", "Publish",});
        internal_static_livekit_RTPDrift_descriptor = getDescriptor().getMessageTypes()
                .get(20);
        internal_static_livekit_RTPDrift_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RTPDrift_descriptor,
                        new java.lang.String[]{"StartTime",
                                "EndTime",
                                "Duration",
                                "StartTimestamp",
                                "EndTimestamp",
                                "RtpClockTicks",
                                "DriftSamples",
                                "DriftMs",
                                "ClockRate",});
        internal_static_livekit_RTPStats_descriptor = getDescriptor().getMessageTypes()
                .get(21);
        internal_static_livekit_RTPStats_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RTPStats_descriptor,
                        new java.lang.String[]{"StartTime",
                                "EndTime",
                                "Duration",
                                "Packets",
                                "PacketRate",
                                "Bytes",
                                "HeaderBytes",
                                "Bitrate",
                                "PacketsLost",
                                "PacketLossRate",
                                "PacketLossPercentage",
                                "PacketsDuplicate",
                                "PacketDuplicateRate",
                                "BytesDuplicate",
                                "HeaderBytesDuplicate",
                                "BitrateDuplicate",
                                "PacketsPadding",
                                "PacketPaddingRate",
                                "BytesPadding",
                                "HeaderBytesPadding",
                                "BitratePadding",
                                "PacketsOutOfOrder",
                                "Frames",
                                "FrameRate",
                                "JitterCurrent",
                                "JitterMax",
                                "GapHistogram",
                                "Nacks",
                                "NackAcks",
                                "NackMisses",
                                "NackRepeated",
                                "Plis",
                                "LastPli",
                                "Firs",
                                "LastFir",
                                "RttCurrent",
                                "RttMax",
                                "KeyFrames",
                                "LastKeyFrame",
                                "LayerLockPlis",
                                "LastLayerLockPli",
                                "PacketDrift",
                                "ReportDrift",
                                "RebasedReportDrift",});
        internal_static_livekit_RTPStats_GapHistogramEntry_descriptor =
                internal_static_livekit_RTPStats_descriptor.getNestedTypes()
                        .get(0);
        internal_static_livekit_RTPStats_GapHistogramEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RTPStats_GapHistogramEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        internal_static_livekit_TimedVersion_descriptor = getDescriptor().getMessageTypes()
                .get(22);
        internal_static_livekit_TimedVersion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_TimedVersion_descriptor,
                        new java.lang.String[]{"UnixMicro", "Ticks",});
        descriptor.resolveAllFeaturesImmutable();
        com.google.protobuf.TimestampProto.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}