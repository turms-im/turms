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

public final class GroupJoinQuestionOuterClass {
    private GroupJoinQuestionOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupJoinQuestion_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupJoinQuestion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n%model/group/group_join_question.proto\022"
                + "\016im.turms.proto\"\242\001\n\021GroupJoinQuestion\022\017\n"
                + "\002id\030\001 \001(\003H\000\210\001\001\022\025\n\010group_id\030\002 \001(\003H\001\210\001\001\022\025\n"
                + "\010question\030\003 \001(\tH\002\210\001\001\022\017\n\007answers\030\004 \003(\t\022\022\n"
                + "\005score\030\005 \001(\005H\003\210\001\001B\005\n\003_idB\013\n\t_group_idB\013\n"
                + "\t_questionB\010\n\006_scoreB;\n4im.turms.server."
                + "common.access.client.dto.model.groupP\001\272\002"
                + "\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_GroupJoinQuestion_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupJoinQuestion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupJoinQuestion_descriptor,
                        new java.lang.String[]{"Id",
                                "GroupId",
                                "Question",
                                "Answers",
                                "Score",
                                "Id",
                                "GroupId",
                                "Question",
                                "Score",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}