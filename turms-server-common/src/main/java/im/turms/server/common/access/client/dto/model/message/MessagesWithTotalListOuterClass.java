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

public final class MessagesWithTotalListOuterClass {
    private MessagesWithTotalListOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_MessagesWithTotalList_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_MessagesWithTotalList_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n,model/message/messages_with_total_list"
                + ".proto\022\016im.turms.proto\032\'model/message/me"
                + "ssages_with_total.proto\"\\\n\025MessagesWithT"
                + "otalList\022C\n\030messages_with_total_list\030\001 \003"
                + "(\0132!.im.turms.proto.MessagesWithTotalB=\n"
                + "6im.turms.server.common.access.client.dt"
                + "o.model.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_MessagesWithTotalList_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_MessagesWithTotalList_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_MessagesWithTotalList_descriptor,
                        new java.lang.String[]{"MessagesWithTotalList",});
        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}