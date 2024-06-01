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

package im.turms.server.common.access.client.dto.request.conference;

public final class CreateMeetingRequestOuterClass {
    private CreateMeetingRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 0,
                /* suffix= */ "",
                CreateMeetingRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateMeetingRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateMeetingRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n/request/conference/create_meeting_requ"
                + "est.proto\022\016im.turms.proto\"\342\001\n\024CreateMeet"
                + "ingRequest\022\024\n\007user_id\030\001 \001(\003H\000\210\001\001\022\025\n\010grou"
                + "p_id\030\002 \001(\003H\001\210\001\001\022\021\n\004name\030\003 \001(\tH\002\210\001\001\022\022\n\005in"
                + "tro\030\004 \001(\tH\003\210\001\001\022\025\n\010password\030\005 \001(\tH\004\210\001\001\022\027\n"
                + "\nstart_date\030\006 \001(\003H\005\210\001\001B\n\n\010_user_idB\013\n\t_g"
                + "roup_idB\007\n\005_nameB\010\n\006_introB\013\n\t_passwordB"
                + "\r\n\013_start_dateBB\n;im.turms.server.common"
                + ".access.client.dto.request.conferenceP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_CreateMeetingRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_CreateMeetingRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateMeetingRequest_descriptor,
                        new java.lang.String[]{"UserId",
                                "GroupId",
                                "Name",
                                "Intro",
                                "Password",
                                "StartDate",});
        descriptor.resolveAllFeaturesImmutable();
    }

    // @@protoc_insertion_point(outer_class_scope)
}