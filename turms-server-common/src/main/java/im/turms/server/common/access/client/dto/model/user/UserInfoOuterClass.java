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

package im.turms.server.common.access.client.dto.model.user;

public final class UserInfoOuterClass {
    private UserInfoOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserInfoOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserInfo_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserInfo_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\032model/user/user_info.proto\022\016im.turms.p"
                + "roto\032&constant/profile_access_strategy.p"
                + "roto\032\030model/common/value.proto\"\340\004\n\010UserI"
                + "nfo\022\017\n\002id\030\001 \001(\003H\000\210\001\001\022\021\n\004name\030\002 \001(\tH\001\210\001\001\022"
                + "\022\n\005intro\030\003 \001(\tH\002\210\001\001\022\034\n\017profile_picture\030\004"
                + " \001(\tH\003\210\001\001\022K\n\027profile_access_strategy\030\005 \001"
                + "(\0162%.im.turms.proto.ProfileAccessStrateg"
                + "yH\004\210\001\001\022\036\n\021registration_date\030\006 \001(\003H\005\210\001\001\022\036"
                + "\n\021last_updated_date\030\007 \001(\003H\006\210\001\001\022\023\n\006active"
                + "\030\010 \001(\010H\007\210\001\001\022T\n\027user_defined_attributes\030\t"
                + " \003(\01323.im.turms.proto.UserInfo.UserDefin"
                + "edAttributesEntry\0220\n\021custom_attributes\030\017"
                + " \003(\0132\025.im.turms.proto.Value\032S\n\032UserDefin"
                + "edAttributesEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value\030"
                + "\002 \001(\0132\025.im.turms.proto.Value:\0028\001B\005\n\003_idB"
                + "\007\n\005_nameB\010\n\006_introB\022\n\020_profile_pictureB\032"
                + "\n\030_profile_access_strategyB\024\n\022_registrat"
                + "ion_dateB\024\n\022_last_updated_dateB\t\n\007_activ"
                + "eB:\n3im.turms.server.common.access.clien"
                + "t.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserInfo_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_UserInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserInfo_descriptor,
                        new java.lang.String[]{"Id",
                                "Name",
                                "Intro",
                                "ProfilePicture",
                                "ProfileAccessStrategy",
                                "RegistrationDate",
                                "LastUpdatedDate",
                                "Active",
                                "UserDefinedAttributes",
                                "CustomAttributes",});
        internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_descriptor =
                internal_static_im_turms_proto_UserInfo_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}