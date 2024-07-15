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

package im.turms.server.common.access.client.dto.request.group.enrollment;

public final class CreateGroupJoinQuestionsRequestOuterClass {
    private CreateGroupJoinQuestionsRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                CreateGroupJoinQuestionsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\nBrequest/group/enrollment/create_group_"
                + "join_questions_request.proto\022\016im.turms.p"
                + "roto\032\030model/common/value.proto\032%model/gr"
                + "oup/group_join_question.proto\"\233\001\n\037Create"
                + "GroupJoinQuestionsRequest\022\020\n\010group_id\030\001 "
                + "\001(\003\0224\n\tquestions\030\002 \003(\0132!.im.turms.proto."
                + "GroupJoinQuestion\0220\n\021custom_attributes\030\017"
                + " \003(\0132\025.im.turms.proto.ValueBH\nAim.turms."
                + "server.common.access.client.dto.request."
                + "group.enrollmentP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor,
                        new java.lang.String[]{"GroupId", "Questions", "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}