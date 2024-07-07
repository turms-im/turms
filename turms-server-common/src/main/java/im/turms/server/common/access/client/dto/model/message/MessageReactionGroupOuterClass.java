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

public final class MessageReactionGroupOuterClass {
    private MessageReactionGroupOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                MessageReactionGroupOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_MessageReactionGroup_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_MessageReactionGroup_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n*model/message/message_reaction_group.p"
                + "roto\022\016im.turms.proto\032\030model/common/value"
                + ".proto\032\032model/user/user_info.proto\"\245\001\n\024M"
                + "essageReactionGroup\022\025\n\rreaction_type\030\001 \001"
                + "(\005\022\026\n\016reaction_count\030\002 \001(\005\022,\n\nuser_infos"
                + "\030\003 \003(\0132\030.im.turms.proto.UserInfo\0220\n\021cust"
                + "om_attributes\030\017 \003(\0132\025.im.turms.proto.Val"
                + "ueB=\n6im.turms.server.common.access.clie"
                + "nt.dto.model.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_MessageReactionGroup_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_MessageReactionGroup_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_MessageReactionGroup_descriptor,
                        new java.lang.String[]{"ReactionType",
                                "ReactionCount",
                                "UserInfos",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}