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

public final class QueryUserProfilesRequestOuterClass {
    private QueryUserProfilesRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryUserProfilesRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryUserProfilesRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n.request/user/query_user_profiles_reque"
                + "st.proto\022\016im.turms.proto\032\030model/common/v"
                + "alue.proto\"\207\002\n\030QueryUserProfilesRequest\022"
                + "\020\n\010user_ids\030\001 \003(\003\022\036\n\021last_updated_date\030\002"
                + " \001(\003H\000\210\001\001\022\021\n\004name\030\003 \001(\tH\001\210\001\001\022\021\n\004skip\030\n \001"
                + "(\005H\002\210\001\001\022\022\n\005limit\030\013 \001(\005H\003\210\001\001\022\033\n\023fields_to"
                + "_highlight\030\014 \003(\005\0220\n\021custom_attributes\030\017 "
                + "\003(\0132\025.im.turms.proto.ValueB\024\n\022_last_upda"
                + "ted_dateB\007\n\005_nameB\007\n\005_skipB\010\n\006_limitB<\n5"
                + "im.turms.server.common.access.client.dto"
                + ".request.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QueryUserProfilesRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryUserProfilesRequest_descriptor,
                        new java.lang.String[]{"UserIds",
                                "LastUpdatedDate",
                                "Name",
                                "Skip",
                                "Limit",
                                "FieldsToHighlight",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}