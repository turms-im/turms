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

package im.turms.client.model.proto.notification;

public interface TurmsNotificationOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.TurmsNotification)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Common =&gt; [1, 3]
     * </pre>
     * 
     * <code>int64 timestamp = 1;</code>
     *
     * @return The timestamp.
     */
    long getTimestamp();

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     *
     * @return Whether the requestId field is set.
     */
    boolean hasRequestId();

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     *
     * @return The requestId.
     */
    long getRequestId();

    /**
     * <code>optional int32 code = 5;</code>
     *
     * @return Whether the code field is set.
     */
    boolean hasCode();

    /**
     * <code>optional int32 code = 5;</code>
     *
     * @return The code.
     */
    int getCode();

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return Whether the reason field is set.
     */
    boolean hasReason();

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return The reason.
     */
    java.lang.String getReason();

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return The bytes for reason.
     */
    com.google.protobuf.ByteString getReasonBytes();

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     *
     * @return Whether the data field is set.
     */
    boolean hasData();

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     *
     * @return The data.
     */
    im.turms.client.model.proto.notification.TurmsNotification.Data getData();

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     *
     * @return Whether the requesterId field is set.
     */
    boolean hasRequesterId();

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     *
     * @return The requesterId.
     */
    long getRequesterId();

    /**
     * <code>optional int32 close_status = 11;</code>
     *
     * @return Whether the closeStatus field is set.
     */
    boolean hasCloseStatus();

    /**
     * <code>optional int32 close_status = 11;</code>
     *
     * @return The closeStatus.
     */
    int getCloseStatus();

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     *
     * @return Whether the relayedRequest field is set.
     */
    boolean hasRelayedRequest();

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     *
     * @return The relayedRequest.
     */
    im.turms.client.model.proto.request.TurmsRequest getRelayedRequest();
}