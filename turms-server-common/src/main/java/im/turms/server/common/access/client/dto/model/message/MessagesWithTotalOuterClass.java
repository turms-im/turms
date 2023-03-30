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

package im.turms.server.common.access.client.dto.model.message;

public final class MessagesWithTotalOuterClass {
    private MessagesWithTotalOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_MessagesWithTotal_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_MessagesWithTotal_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\'model/message/messages_with_total.prot"
                + "o\022\016im.turms.proto\032\033model/message/message"
                + ".proto\"x\n\021MessagesWithTotal\022\r\n\005total\030\001 \001"
                + "(\005\022\030\n\020is_group_message\030\002 \001(\010\022\017\n\007from_id\030"
                + "\003 \001(\003\022)\n\010messages\030\004 \003(\0132\027.im.turms.proto"
                + ".MessageB=\n6im.turms.server.common.acces"
                + "s.client.dto.model.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.message.MessageOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_MessagesWithTotal_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_MessagesWithTotal_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_MessagesWithTotal_descriptor,
                        new java.lang.String[]{"Total", "IsGroupMessage", "FromId", "Messages",});
        im.turms.server.common.access.client.dto.model.message.MessageOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}