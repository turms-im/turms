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

package im.turms.client.model.proto.model.user;

public interface UserOnlineStatusOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UserOnlineStatus)
        com.google.protobuf.MessageLiteOrBuilder {

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
    im.turms.client.model.proto.constant.UserStatus getUserStatus();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @return A list containing the usingDeviceTypes.
     */
    java.util.List<im.turms.client.model.proto.constant.DeviceType> getUsingDeviceTypesList();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @return The count of usingDeviceTypes.
     */
    int getUsingDeviceTypesCount();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The usingDeviceTypes at the given index.
     */
    im.turms.client.model.proto.constant.DeviceType getUsingDeviceTypes(int index);

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
     */
    java.util.List<java.lang.Integer> getUsingDeviceTypesValueList();

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
     */
    int getUsingDeviceTypesValue(int index);
}