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

package im.turms.server.common.access.client.dto.model.conference;

public final class MeetingsOuterClass {
    private MeetingsOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                MeetingsOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Meetings_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_Meetings_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\037model/conference/meetings.proto\022\016im.tu"
                + "rms.proto\032\036model/conference/meeting.prot"
                + "o\"5\n\010Meetings\022)\n\010meetings\030\001 \003(\0132\027.im.tur"
                + "ms.proto.MeetingB@\n9im.turms.server.comm"
                + "on.access.client.dto.model.conferenceP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_Meetings_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_Meetings_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_Meetings_descriptor,
                        new java.lang.String[]{"Meetings",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}