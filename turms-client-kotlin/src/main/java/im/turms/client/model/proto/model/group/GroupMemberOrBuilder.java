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

package im.turms.client.model.proto.model.group;

public interface GroupMemberOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.GroupMember)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>optional int64 group_id = 1;</code>
     *
     * @return Whether the groupId field is set.
     */
    boolean hasGroupId();

    /**
     * <code>optional int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return Whether the userId field is set.
     */
    boolean hasUserId();

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return The userId.
     */
    long getUserId();

    /**
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
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
     * <code>optional int64 join_date = 5;</code>
     *
     * @return Whether the joinDate field is set.
     */
    boolean hasJoinDate();

    /**
     * <code>optional int64 join_date = 5;</code>
     *
     * @return The joinDate.
     */
    long getJoinDate();

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    boolean hasMuteEndDate();

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return The muteEndDate.
     */
    long getMuteEndDate();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return Whether the userStatus field is set.
     */
    boolean hasUserStatus();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    int getUserStatusValue();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return The userStatus.
     */
    im.turms.client.model.proto.constant.UserStatus getUserStatus();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return A list containing the usingDeviceTypes.
     */
    java.util.List<im.turms.client.model.proto.constant.DeviceType> getUsingDeviceTypesList();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return The count of usingDeviceTypes.
     */
    int getUsingDeviceTypesCount();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param index The index of the element to return.
     * @return The usingDeviceTypes at the given index.
     */
    im.turms.client.model.proto.constant.DeviceType getUsingDeviceTypes(int index);

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
     */
    java.util.List<java.lang.Integer> getUsingDeviceTypesValueList();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
     */
    int getUsingDeviceTypesValue(int index);
}