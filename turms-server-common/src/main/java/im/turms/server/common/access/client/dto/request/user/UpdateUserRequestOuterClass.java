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

package im.turms.server.common.access.client.dto.request.user;

public final class UpdateUserRequestOuterClass {
    private UpdateUserRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateUserRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserRequest_UserDefinedAttributesEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserRequest_UserDefinedAttributesEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n&request/user/update_user_request.proto"
                + "\022\016im.turms.proto\032&constant/profile_acces"
                + "s_strategy.proto\032\030model/common/value.pro"
                + "to\"\362\003\n\021UpdateUserRequest\022\025\n\010password\030\001 \001"
                + "(\tH\000\210\001\001\022\021\n\004name\030\002 \001(\tH\001\210\001\001\022\022\n\005intro\030\003 \001("
                + "\tH\002\210\001\001\022\034\n\017profile_picture\030\004 \001(\tH\003\210\001\001\022K\n\027"
                + "profile_access_strategy\030\005 \001(\0162%.im.turms"
                + ".proto.ProfileAccessStrategyH\004\210\001\001\022]\n\027use"
                + "r_defined_attributes\030\006 \003(\0132<.im.turms.pr"
                + "oto.UpdateUserRequest.UserDefinedAttribu"
                + "tesEntry\0220\n\021custom_attributes\030\017 \003(\0132\025.im"
                + ".turms.proto.Value\032S\n\032UserDefinedAttribu"
                + "tesEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value\030\002 \001(\0132\025.i"
                + "m.turms.proto.Value:\0028\001B\013\n\t_passwordB\007\n\005"
                + "_nameB\010\n\006_introB\022\n\020_profile_pictureB\032\n\030_"
                + "profile_access_strategyB<\n5im.turms.serv"
                + "er.common.access.client.dto.request.user"
                + "P\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateUserRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserRequest_descriptor,
                        new java.lang.String[]{"Password",
                                "Name",
                                "Intro",
                                "ProfilePicture",
                                "ProfileAccessStrategy",
                                "UserDefinedAttributes",
                                "CustomAttributes",});
        internal_static_im_turms_proto_UpdateUserRequest_UserDefinedAttributesEntry_descriptor =
                internal_static_im_turms_proto_UpdateUserRequest_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserRequest_UserDefinedAttributesEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserRequest_UserDefinedAttributesEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}