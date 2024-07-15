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

public final class NearbyUserOuterClass {
    private NearbyUserOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                NearbyUserOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_NearbyUser_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_NearbyUser_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\034model/user/nearby_user.proto\022\016im.turms"
                + ".proto\032\032constant/device_type.proto\032\030mode"
                + "l/common/value.proto\032\032model/user/user_in"
                + "fo.proto\032\036model/user/user_location.proto"
                + "\"\261\002\n\nNearbyUser\022\017\n\007user_id\030\001 \001(\003\0224\n\013devi"
                + "ce_type\030\002 \001(\0162\032.im.turms.proto.DeviceTyp"
                + "eH\000\210\001\001\022+\n\004info\030\003 \001(\0132\030.im.turms.proto.Us"
                + "erInfoH\001\210\001\001\022\025\n\010distance\030\004 \001(\005H\002\210\001\001\0223\n\010lo"
                + "cation\030\005 \001(\0132\034.im.turms.proto.UserLocati"
                + "onH\003\210\001\001\0220\n\021custom_attributes\030\017 \003(\0132\025.im."
                + "turms.proto.ValueB\016\n\014_device_typeB\007\n\005_in"
                + "foB\013\n\t_distanceB\013\n\t_locationB:\n3im.turms"
                + ".server.common.access.client.dto.model.u"
                + "serP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_NearbyUser_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_NearbyUser_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_NearbyUser_descriptor,
                        new java.lang.String[]{"UserId",
                                "DeviceType",
                                "Info",
                                "Distance",
                                "Location",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}