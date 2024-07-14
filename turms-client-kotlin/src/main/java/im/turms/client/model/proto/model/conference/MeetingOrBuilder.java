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

package im.turms.client.model.proto.model.conference;

public interface MeetingOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.Meeting)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>int64 id = 1;</code>
     *
     * @return The id.
     */
    long getId();

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
     * <code>optional int64 group_id = 3;</code>
     *
     * @return Whether the groupId field is set.
     */
    boolean hasGroupId();

    /**
     * <code>optional int64 group_id = 3;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>int64 creator_id = 4;</code>
     *
     * @return The creatorId.
     */
    long getCreatorId();

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return Whether the accessToken field is set.
     */
    boolean hasAccessToken();

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return The accessToken.
     */
    java.lang.String getAccessToken();

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return The bytes for accessToken.
     */
    com.google.protobuf.ByteString getAccessTokenBytes();

    /**
     * <code>optional string name = 6;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 6;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>optional string name = 6;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return Whether the intro field is set.
     */
    boolean hasIntro();

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return The intro.
     */
    java.lang.String getIntro();

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return The bytes for intro.
     */
    com.google.protobuf.ByteString getIntroBytes();

    /**
     * <code>optional string password = 8;</code>
     *
     * @return Whether the password field is set.
     */
    boolean hasPassword();

    /**
     * <code>optional string password = 8;</code>
     *
     * @return The password.
     */
    java.lang.String getPassword();

    /**
     * <code>optional string password = 8;</code>
     *
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString getPasswordBytes();

    /**
     * <code>int64 start_date = 9;</code>
     *
     * @return The startDate.
     */
    long getStartDate();

    /**
     * <code>optional int64 end_date = 10;</code>
     *
     * @return Whether the endDate field is set.
     */
    boolean hasEndDate();

    /**
     * <code>optional int64 end_date = 10;</code>
     *
     * @return The endDate.
     */
    long getEndDate();

    /**
     * <code>optional int64 cancel_date = 11;</code>
     *
     * @return Whether the cancelDate field is set.
     */
    boolean hasCancelDate();

    /**
     * <code>optional int64 cancel_date = 11;</code>
     *
     * @return The cancelDate.
     */
    long getCancelDate();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.client.model.proto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();
}
