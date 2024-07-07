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

public final class QueryNearbyUsersRequestOuterClass {
    private QueryNearbyUsersRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryNearbyUsersRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryNearbyUsersRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n-request/user/query_nearby_users_reques"
                + "t.proto\022\016im.turms.proto\032\030model/common/va"
                + "lue.proto\"\324\002\n\027QueryNearbyUsersRequest\022\020\n"
                + "\010latitude\030\001 \001(\002\022\021\n\tlongitude\030\002 \001(\002\022\026\n\tma"
                + "x_count\030\003 \001(\005H\000\210\001\001\022\031\n\014max_distance\030\004 \001(\005"
                + "H\001\210\001\001\022\035\n\020with_coordinates\030\005 \001(\010H\002\210\001\001\022\032\n\r"
                + "with_distance\030\006 \001(\010H\003\210\001\001\022\033\n\016with_user_in"
                + "fo\030\007 \001(\010H\004\210\001\001\0220\n\021custom_attributes\030\017 \003(\013"
                + "2\025.im.turms.proto.ValueB\014\n\n_max_countB\017\n"
                + "\r_max_distanceB\023\n\021_with_coordinatesB\020\n\016_"
                + "with_distanceB\021\n\017_with_user_infoB<\n5im.t"
                + "urms.server.common.access.client.dto.req"
                + "uest.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_QueryNearbyUsersRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryNearbyUsersRequest_descriptor,
                        new java.lang.String[]{"Latitude",
                                "Longitude",
                                "MaxCount",
                                "MaxDistance",
                                "WithCoordinates",
                                "WithDistance",
                                "WithUserInfo",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}