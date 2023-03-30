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

public final class RequestStatusOuterClass {
    private RequestStatusOuterClass() {
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
        java.lang.String[] descriptorData = {"\n\035constant/request_status.proto\022\016im.turm"
                + "s.proto*~\n\rRequestStatus\022\013\n\007PENDING\020\000\022\014\n"
                + "\010ACCEPTED\020\001\022\034\n\030ACCEPTED_WITHOUT_CONFIRM\020"
                + "\002\022\014\n\010DECLINED\020\003\022\013\n\007IGNORED\020\004\022\013\n\007EXPIRED\020"
                + "\005\022\014\n\010CANCELED\020\006B8\n1im.turms.server.commo"
                + "n.access.client.dto.constantP\001\272\002\000b\006proto"
                + "3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
    }

    // @@protoc_insertion_point(outer_class_scope)
}