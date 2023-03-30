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

package im.turms.server.common.access.client.dto.model.group;

public final class GroupJoinQuestionsWithVersionOuterClass {
    private GroupJoinQuestionsWithVersionOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n3model/group/group_join_questions_with_"
                + "version.proto\022\016im.turms.proto\032%model/gro"
                + "up/group_join_question.proto\"\226\001\n\035GroupJo"
                + "inQuestionsWithVersion\022?\n\024group_join_que"
                + "stions\030\001 \003(\0132!.im.turms.proto.GroupJoinQ"
                + "uestion\022\036\n\021last_updated_date\030\002 \001(\003H\000\210\001\001B"
                + "\024\n\022_last_updated_dateB;\n4im.turms.server"
                + ".common.access.client.dto.model.groupP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor,
                        new java.lang.String[]{"GroupJoinQuestions",
                                "LastUpdatedDate",
                                "LastUpdatedDate",});
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}