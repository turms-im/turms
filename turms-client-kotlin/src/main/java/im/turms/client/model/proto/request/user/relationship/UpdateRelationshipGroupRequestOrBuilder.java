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

package im.turms.client.model.proto.request.user.relationship;

public interface UpdateRelationshipGroupRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateRelationshipGroupRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int32 group_index = 1;</code>
     *
     * @return The groupIndex.
     */
    int getGroupIndex();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @return The newName.
     */
    java.lang.String getNewName();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>string new_name = 2;</code>
     *
     * @return The bytes for newName.
     */
    com.google.protobuf.ByteString getNewNameBytes();
}