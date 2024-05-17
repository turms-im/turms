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

package im.turms.plugin.livekit.core.proto.egress;

public interface UpdateLayoutRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UpdateLayoutRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The egressId.
     */
    java.lang.String getEgressId();

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The bytes for egressId.
     */
    com.google.protobuf.ByteString getEgressIdBytes();

    /**
     * <code>string layout = 2;</code>
     *
     * @return The layout.
     */
    java.lang.String getLayout();

    /**
     * <code>string layout = 2;</code>
     *
     * @return The bytes for layout.
     */
    com.google.protobuf.ByteString getLayoutBytes();
}
