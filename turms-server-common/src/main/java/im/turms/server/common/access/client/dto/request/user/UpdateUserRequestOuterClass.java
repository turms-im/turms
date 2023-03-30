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

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n&request/user/update_user_request.proto"
                + "\022\016im.turms.proto\032&constant/profile_acces"
                + "s_strategy.proto\"\214\002\n\021UpdateUserRequest\022\025"
                + "\n\010password\030\001 \001(\tH\000\210\001\001\022\021\n\004name\030\002 \001(\tH\001\210\001\001"
                + "\022\022\n\005intro\030\003 \001(\tH\002\210\001\001\022\034\n\017profile_picture\030"
                + "\004 \001(\tH\003\210\001\001\022K\n\027profile_access_strategy\030\005 "
                + "\001(\0162%.im.turms.proto.ProfileAccessStrate"
                + "gyH\004\210\001\001B\013\n\t_passwordB\007\n\005_nameB\010\n\006_introB"
                + "\022\n\020_profile_pictureB\032\n\030_profile_access_s"
                + "trategyB<\n5im.turms.server.common.access"
                + ".client.dto.request.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateUserRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserRequest_descriptor,
                        new java.lang.String[]{"Password",
                                "Name",
                                "Intro",
                                "ProfilePicture",
                                "ProfileAccessStrategy",
                                "Password",
                                "Name",
                                "Intro",
                                "ProfilePicture",
                                "ProfileAccessStrategy",});
        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}