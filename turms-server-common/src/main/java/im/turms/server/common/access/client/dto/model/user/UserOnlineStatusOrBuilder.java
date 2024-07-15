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

public interface UserOnlineStatusOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UserOnlineStatus)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    long getUserId();

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    int getUserStatusValue();

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The userStatus.
     */
    im.turms.server.common.access.client.dto.constant.UserStatus getUserStatus();

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return A list containing the deviceTypes.
     */
    java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getDeviceTypesList();

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return The count of deviceTypes.
     */
    int getDeviceTypesCount();

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The deviceTypes at the given index.
     */
    im.turms.server.common.access.client.dto.constant.DeviceType getDeviceTypes(int index);

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return A list containing the enum numeric values on the wire for deviceTypes.
     */
    java.util.List<java.lang.Integer> getDeviceTypesValueList();

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of deviceTypes at the given index.
     */
    int getDeviceTypesValue(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index);
}
