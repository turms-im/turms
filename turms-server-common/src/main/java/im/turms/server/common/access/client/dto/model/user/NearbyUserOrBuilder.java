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

public interface NearbyUserOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.NearbyUser)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * session info
     * </pre>
     *
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    long getUserId();

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return Whether the deviceType field is set.
     */
    boolean hasDeviceType();

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return The enum numeric value on the wire for deviceType.
     */
    int getDeviceTypeValue();

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return The deviceType.
     */
    im.turms.server.common.access.client.dto.constant.DeviceType getDeviceType();

    /**
     * <pre>
     * user info
     * </pre>
     *
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     *
     * @return Whether the info field is set.
     */
    boolean hasInfo();

    /**
     * <pre>
     * user info
     * </pre>
     *
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     *
     * @return The info.
     */
    im.turms.server.common.access.client.dto.model.user.UserInfo getInfo();

    /**
     * <pre>
     * user info
     * </pre>
     *
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     */
    im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getInfoOrBuilder();

    /**
     * <pre>
     * geo info
     * </pre>
     *
     * <code>optional int32 distance = 4;</code>
     *
     * @return Whether the distance field is set.
     */
    boolean hasDistance();

    /**
     * <pre>
     * geo info
     * </pre>
     *
     * <code>optional int32 distance = 4;</code>
     *
     * @return The distance.
     */
    int getDistance();

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     *
     * @return Whether the location field is set.
     */
    boolean hasLocation();

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     *
     * @return The location.
     */
    im.turms.server.common.access.client.dto.model.user.UserLocation getLocation();

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder getLocationOrBuilder();

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
