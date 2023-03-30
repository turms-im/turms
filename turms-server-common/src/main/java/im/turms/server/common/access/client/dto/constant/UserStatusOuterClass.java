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

package im.turms.server.common.access.client.dto.constant;

public final class UserStatusOuterClass {
    private UserStatusOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\032constant/user_status.proto\022\016im.turms.p"
                + "roto*r\n\nUserStatus\022\r\n\tAVAILABLE\020\000\022\013\n\007OFF"
                + "LINE\020\001\022\r\n\tINVISIBLE\020\002\022\010\n\004BUSY\020\003\022\022\n\016DO_NO"
                + "T_DISTURB\020\004\022\010\n\004AWAY\020\005\022\021\n\rBE_RIGHT_BACK\020\006"
                + "B8\n1im.turms.server.common.access.client"
                + ".dto.constantP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
    }

    // @@protoc_insertion_point(outer_class_scope)
}