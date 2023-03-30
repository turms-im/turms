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

package im.turms.client.model.proto.request.user;

public interface UpdateUserOnlineStatusRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateUserOnlineStatusRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return A list containing the deviceTypes.
     */
    java.util.List<im.turms.client.model.proto.constant.DeviceType> getDeviceTypesList();

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return The count of deviceTypes.
     */
    int getDeviceTypesCount();

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The deviceTypes at the given index.
     */
    im.turms.client.model.proto.constant.DeviceType getDeviceTypes(int index);

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return A list containing the enum numeric values on the wire for deviceTypes.
     */
    java.util.List<java.lang.Integer> getDeviceTypesValueList();

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of deviceTypes at the given index.
     */
    int getDeviceTypesValue(int index);

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    int getUserStatusValue();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The userStatus.
     */
    im.turms.client.model.proto.constant.UserStatus getUserStatus();
}