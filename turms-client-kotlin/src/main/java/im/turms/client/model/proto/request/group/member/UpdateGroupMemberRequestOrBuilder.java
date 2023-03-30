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

package im.turms.client.model.proto.request.group.member;

public interface UpdateGroupMemberRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateGroupMemberRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>int64 member_id = 2;</code>
     *
     * @return The memberId.
     */
    long getMemberId();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return Whether the role field is set.
     */
    boolean hasRole();

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return The enum numeric value on the wire for role.
     */
    int getRoleValue();

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return The role.
     */
    im.turms.client.model.proto.constant.GroupMemberRole getRole();

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    boolean hasMuteEndDate();

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     *
     * @return The muteEndDate.
     */
    long getMuteEndDate();
}