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

package im.turms.client.model.proto.request.conference;

public interface UpdateMeetingRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateMeetingRequest)
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
     * <code>optional string password = 4;</code>
     *
     * @return Whether the password field is set.
     */
    boolean hasPassword();

    /**
     * <code>optional string password = 4;</code>
     *
     * @return The password.
     */
    java.lang.String getPassword();

    /**
     * <code>optional string password = 4;</code>
     *
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString getPasswordBytes();

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
