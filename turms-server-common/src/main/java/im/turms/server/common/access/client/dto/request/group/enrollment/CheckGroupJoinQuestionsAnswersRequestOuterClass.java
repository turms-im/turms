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

public final class CheckGroupJoinQuestionsAnswersRequestOuterClass {
    private CheckGroupJoinQuestionsAnswersRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\nIrequest/group/enrollment/check_group_j"
                + "oin_questions_answers_request.proto\022\016im."
                + "turms.proto\"\320\001\n%CheckGroupJoinQuestionsA"
                + "nswersRequest\022l\n\025question_id_to_answer\030\001"
                + " \003(\0132M.im.turms.proto.CheckGroupJoinQues"
                + "tionsAnswersRequest.QuestionIdToAnswerEn"
                + "try\0329\n\027QuestionIdToAnswerEntry\022\013\n\003key\030\001 "
                + "\001(\003\022\r\n\005value\030\002 \001(\t:\0028\001BH\nAim.turms.serve"
                + "r.common.access.client.dto.request.group"
                + ".enrollmentP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor,
                        new java.lang.String[]{"QuestionIdToAnswer",});
        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_descriptor =
                internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor
                        .getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}