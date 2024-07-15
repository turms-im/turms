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

public final class UserInfosWithVersionOuterClass {
    private UserInfosWithVersionOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserInfosWithVersionOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserInfosWithVersion_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserInfosWithVersion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n(model/user/user_infos_with_version.pro"
                + "to\022\016im.turms.proto\032\032model/user/user_info"
                + ".proto\"z\n\024UserInfosWithVersion\022,\n\nuser_i"
                + "nfos\030\001 \003(\0132\030.im.turms.proto.UserInfo\022\036\n\021"
                + "last_updated_date\030\002 \001(\003H\000\210\001\001B\024\n\022_last_up"
                + "dated_dateB:\n3im.turms.server.common.acc"
                + "ess.client.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserInfosWithVersion_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_UserInfosWithVersion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserInfosWithVersion_descriptor,
                        new java.lang.String[]{"UserInfos", "LastUpdatedDate",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}