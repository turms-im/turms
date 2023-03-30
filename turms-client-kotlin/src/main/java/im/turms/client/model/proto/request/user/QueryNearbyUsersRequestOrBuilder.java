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

public interface QueryNearbyUsersRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryNearbyUsersRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>float latitude = 1;</code>
     *
     * @return The latitude.
     */
    float getLatitude();

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    float getLongitude();

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return Whether the maxCount field is set.
     */
    boolean hasMaxCount();

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return The maxCount.
     */
    int getMaxCount();

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return Whether the maxDistance field is set.
     */
    boolean hasMaxDistance();

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return The maxDistance.
     */
    int getMaxDistance();

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return Whether the withCoordinates field is set.
     */
    boolean hasWithCoordinates();

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return The withCoordinates.
     */
    boolean getWithCoordinates();

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return Whether the withDistance field is set.
     */
    boolean hasWithDistance();

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return The withDistance.
     */
    boolean getWithDistance();

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return Whether the withUserInfo field is set.
     */
    boolean hasWithUserInfo();

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return The withUserInfo.
     */
    boolean getWithUserInfo();
}