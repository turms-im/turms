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

package im.turms.server.common.access.client.dto.request.user.relationship;

public interface UpdateFriendRequestRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateFriendRequestRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 request_id = 1;</code>
     *
     * @return The requestId.
     */
    long getRequestId();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @return The enum numeric value on the wire for responseAction.
     */
    int getResponseActionValue();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.ResponseAction response_action = 2;</code>
     *
     * @return The responseAction.
     */
    im.turms.server.common.access.client.dto.constant.ResponseAction getResponseAction();

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return Whether the reason field is set.
     */
    boolean hasReason();

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return The reason.
     */
    java.lang.String getReason();

    /**
     * <code>optional string reason = 3;</code>
     *
     * @return The bytes for reason.
     */
    com.google.protobuf.ByteString getReasonBytes();
}