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

public interface UpdateMeetingInvitationRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateMeetingInvitationRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 meeting_id = 1;</code>
     *
     * @return The meetingId.
     */
    long getMeetingId();

    /**
     * <code>optional string password = 2;</code>
     *
     * @return Whether the password field is set.
     */
    boolean hasPassword();

    /**
     * <code>optional string password = 2;</code>
     *
     * @return The password.
     */
    java.lang.String getPassword();

    /**
     * <code>optional string password = 2;</code>
     *
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString getPasswordBytes();

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
     *
     * @return The enum numeric value on the wire for responseAction.
     */
    int getResponseActionValue();

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>.im.turms.proto.ResponseAction response_action = 5;</code>
     *
     * @return The responseAction.
     */
    im.turms.client.model.proto.constant.ResponseAction getResponseAction();

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
