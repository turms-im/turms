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

public interface UserInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UserInfo)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    boolean hasId();

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    long getId();

    /**
     * <code>optional string name = 2;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 2;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>optional string name = 2;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return Whether the intro field is set.
     */
    boolean hasIntro();

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return The intro.
     */
    java.lang.String getIntro();

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return The bytes for intro.
     */
    com.google.protobuf.ByteString getIntroBytes();

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return Whether the profilePicture field is set.
     */
    boolean hasProfilePicture();

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return The profilePicture.
     */
    java.lang.String getProfilePicture();

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return The bytes for profilePicture.
     */
    com.google.protobuf.ByteString getProfilePictureBytes();

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return Whether the profileAccessStrategy field is set.
     */
    boolean hasProfileAccessStrategy();

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return The enum numeric value on the wire for profileAccessStrategy.
     */
    int getProfileAccessStrategyValue();

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return The profileAccessStrategy.
     */
    im.turms.client.model.proto.constant.ProfileAccessStrategy getProfileAccessStrategy();

    /**
     * <code>optional int64 registration_date = 6;</code>
     *
     * @return Whether the registrationDate field is set.
     */
    boolean hasRegistrationDate();

    /**
     * <code>optional int64 registration_date = 6;</code>
     *
     * @return The registrationDate.
     */
    long getRegistrationDate();

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    boolean hasLastUpdatedDate();

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     *
     * @return The lastUpdatedDate.
     */
    long getLastUpdatedDate();

    /**
     * <code>optional bool active = 8;</code>
     *
     * @return Whether the active field is set.
     */
    boolean hasActive();

    /**
     * <code>optional bool active = 8;</code>
     *
     * @return The active.
     */
    boolean getActive();
}