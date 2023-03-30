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

public interface CreateSessionRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.CreateSessionRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>int32 version = 1;</code>
     *
     * @return The version.
     */
    int getVersion();

    /**
     * <code>int64 user_id = 2;</code>
     *
     * @return The userId.
     */
    long getUserId();

    /**
     * <code>optional string password = 3;</code>
     *
     * @return Whether the password field is set.
     */
    boolean hasPassword();

    /**
     * <code>optional string password = 3;</code>
     *
     * @return The password.
     */
    java.lang.String getPassword();

    /**
     * <code>optional string password = 3;</code>
     *
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString getPasswordBytes();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @return Whether the userStatus field is set.
     */
    boolean hasUserStatus();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    int getUserStatusValue();

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @return The userStatus.
     */
    im.turms.client.model.proto.constant.UserStatus getUserStatus();

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     *
     * @return The enum numeric value on the wire for deviceType.
     */
    int getDeviceTypeValue();

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     *
     * @return The deviceType.
     */
    im.turms.client.model.proto.constant.DeviceType getDeviceType();

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    int getDeviceDetailsCount();

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    boolean containsDeviceDetails(java.lang.String key);

    /**
     * Use {@link #getDeviceDetailsMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String> getDeviceDetails();

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    java.util.Map<java.lang.String, java.lang.String> getDeviceDetailsMap();

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */

    /* nullable */
    java.lang.String getDeviceDetailsOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue);

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */

    java.lang.String getDeviceDetailsOrThrow(java.lang.String key);

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     *
     * @return Whether the location field is set.
     */
    boolean hasLocation();

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     *
     * @return The location.
     */
    im.turms.client.model.proto.model.user.UserLocation getLocation();
}