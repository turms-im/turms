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

package im.turms.client.model.proto.request;

/**
 * <pre>
 * Client -&gt; Server -&gt; Client
 * </pre>
 * <p>
 * Protobuf type {@code im.turms.proto.TurmsRequest}
 */
public final class TurmsRequest extends
        com.google.protobuf.GeneratedMessageLite<TurmsRequest, TurmsRequest.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsRequest)
        TurmsRequestOrBuilder {
    private TurmsRequest() {
    }

    private int bitField0_;
    private int kindCase_ = 0;
    private java.lang.Object kind_;

    public enum KindCase {
        CREATE_SESSION_REQUEST(3),
        DELETE_SESSION_REQUEST(4),
        QUERY_CONVERSATIONS_REQUEST(5),
        UPDATE_CONVERSATION_REQUEST(6),
        UPDATE_TYPING_STATUS_REQUEST(7),
        CREATE_MESSAGE_REQUEST(8),
        QUERY_MESSAGES_REQUEST(9),
        UPDATE_MESSAGE_REQUEST(10),
        CREATE_GROUP_MEMBERS_REQUEST(11),
        DELETE_GROUP_MEMBERS_REQUEST(12),
        QUERY_GROUP_MEMBERS_REQUEST(13),
        UPDATE_GROUP_MEMBER_REQUEST(14),
        QUERY_USER_PROFILES_REQUEST(100),
        QUERY_NEARBY_USERS_REQUEST(101),
        QUERY_USER_ONLINE_STATUSES_REQUEST(102),
        UPDATE_USER_LOCATION_REQUEST(103),
        UPDATE_USER_ONLINE_STATUS_REQUEST(104),
        UPDATE_USER_REQUEST(105),
        CREATE_FRIEND_REQUEST_REQUEST(200),
        CREATE_RELATIONSHIP_GROUP_REQUEST(201),
        CREATE_RELATIONSHIP_REQUEST(202),
        DELETE_RELATIONSHIP_GROUP_REQUEST(203),
        DELETE_RELATIONSHIP_REQUEST(204),
        QUERY_FRIEND_REQUESTS_REQUEST(205),
        QUERY_RELATED_USER_IDS_REQUEST(206),
        QUERY_RELATIONSHIP_GROUPS_REQUEST(207),
        QUERY_RELATIONSHIPS_REQUEST(208),
        UPDATE_FRIEND_REQUEST_REQUEST(209),
        UPDATE_RELATIONSHIP_GROUP_REQUEST(210),
        UPDATE_RELATIONSHIP_REQUEST(211),
        CREATE_GROUP_REQUEST(300),
        DELETE_GROUP_REQUEST(301),
        QUERY_GROUPS_REQUEST(302),
        QUERY_JOINED_GROUP_IDS_REQUEST(303),
        QUERY_JOINED_GROUP_INFOS_REQUEST(304),
        UPDATE_GROUP_REQUEST(305),
        CREATE_GROUP_BLOCKED_USER_REQUEST(400),
        DELETE_GROUP_BLOCKED_USER_REQUEST(401),
        QUERY_GROUP_BLOCKED_USER_IDS_REQUEST(402),
        QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST(403),
        CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST(500),
        CREATE_GROUP_INVITATION_REQUEST(501),
        CREATE_GROUP_JOIN_REQUEST_REQUEST(502),
        CREATE_GROUP_JOIN_QUESTIONS_REQUEST(503),
        DELETE_GROUP_INVITATION_REQUEST(504),
        DELETE_GROUP_JOIN_REQUEST_REQUEST(505),
        DELETE_GROUP_JOIN_QUESTIONS_REQUEST(506),
        QUERY_GROUP_INVITATIONS_REQUEST(507),
        QUERY_GROUP_JOIN_REQUESTS_REQUEST(508),
        QUERY_GROUP_JOIN_QUESTIONS_REQUEST(509),
        UPDATE_GROUP_JOIN_QUESTION_REQUEST(510),
        DELETE_RESOURCE_REQUEST(1000),
        QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST(1001),
        QUERY_RESOURCE_UPLOAD_INFO_REQUEST(1002),
        QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST(1003),
        UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST(1004),
        KIND_NOT_SET(0);

        private final int value;

        KindCase(int value) {
            this.value = value;
        }

        /**
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static KindCase valueOf(int value) {
            return forNumber(value);
        }

        public static KindCase forNumber(int value) {
            switch (value) {
                case 3:
                    return CREATE_SESSION_REQUEST;
                case 4:
                    return DELETE_SESSION_REQUEST;
                case 5:
                    return QUERY_CONVERSATIONS_REQUEST;
                case 6:
                    return UPDATE_CONVERSATION_REQUEST;
                case 7:
                    return UPDATE_TYPING_STATUS_REQUEST;
                case 8:
                    return CREATE_MESSAGE_REQUEST;
                case 9:
                    return QUERY_MESSAGES_REQUEST;
                case 10:
                    return UPDATE_MESSAGE_REQUEST;
                case 11:
                    return CREATE_GROUP_MEMBERS_REQUEST;
                case 12:
                    return DELETE_GROUP_MEMBERS_REQUEST;
                case 13:
                    return QUERY_GROUP_MEMBERS_REQUEST;
                case 14:
                    return UPDATE_GROUP_MEMBER_REQUEST;
                case 100:
                    return QUERY_USER_PROFILES_REQUEST;
                case 101:
                    return QUERY_NEARBY_USERS_REQUEST;
                case 102:
                    return QUERY_USER_ONLINE_STATUSES_REQUEST;
                case 103:
                    return UPDATE_USER_LOCATION_REQUEST;
                case 104:
                    return UPDATE_USER_ONLINE_STATUS_REQUEST;
                case 105:
                    return UPDATE_USER_REQUEST;
                case 200:
                    return CREATE_FRIEND_REQUEST_REQUEST;
                case 201:
                    return CREATE_RELATIONSHIP_GROUP_REQUEST;
                case 202:
                    return CREATE_RELATIONSHIP_REQUEST;
                case 203:
                    return DELETE_RELATIONSHIP_GROUP_REQUEST;
                case 204:
                    return DELETE_RELATIONSHIP_REQUEST;
                case 205:
                    return QUERY_FRIEND_REQUESTS_REQUEST;
                case 206:
                    return QUERY_RELATED_USER_IDS_REQUEST;
                case 207:
                    return QUERY_RELATIONSHIP_GROUPS_REQUEST;
                case 208:
                    return QUERY_RELATIONSHIPS_REQUEST;
                case 209:
                    return UPDATE_FRIEND_REQUEST_REQUEST;
                case 210:
                    return UPDATE_RELATIONSHIP_GROUP_REQUEST;
                case 211:
                    return UPDATE_RELATIONSHIP_REQUEST;
                case 300:
                    return CREATE_GROUP_REQUEST;
                case 301:
                    return DELETE_GROUP_REQUEST;
                case 302:
                    return QUERY_GROUPS_REQUEST;
                case 303:
                    return QUERY_JOINED_GROUP_IDS_REQUEST;
                case 304:
                    return QUERY_JOINED_GROUP_INFOS_REQUEST;
                case 305:
                    return UPDATE_GROUP_REQUEST;
                case 400:
                    return CREATE_GROUP_BLOCKED_USER_REQUEST;
                case 401:
                    return DELETE_GROUP_BLOCKED_USER_REQUEST;
                case 402:
                    return QUERY_GROUP_BLOCKED_USER_IDS_REQUEST;
                case 403:
                    return QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST;
                case 500:
                    return CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
                case 501:
                    return CREATE_GROUP_INVITATION_REQUEST;
                case 502:
                    return CREATE_GROUP_JOIN_REQUEST_REQUEST;
                case 503:
                    return CREATE_GROUP_JOIN_QUESTIONS_REQUEST;
                case 504:
                    return DELETE_GROUP_INVITATION_REQUEST;
                case 505:
                    return DELETE_GROUP_JOIN_REQUEST_REQUEST;
                case 506:
                    return DELETE_GROUP_JOIN_QUESTIONS_REQUEST;
                case 507:
                    return QUERY_GROUP_INVITATIONS_REQUEST;
                case 508:
                    return QUERY_GROUP_JOIN_REQUESTS_REQUEST;
                case 509:
                    return QUERY_GROUP_JOIN_QUESTIONS_REQUEST;
                case 510:
                    return UPDATE_GROUP_JOIN_QUESTION_REQUEST;
                case 1000:
                    return DELETE_RESOURCE_REQUEST;
                case 1001:
                    return QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST;
                case 1002:
                    return QUERY_RESOURCE_UPLOAD_INFO_REQUEST;
                case 1003:
                    return QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST;
                case 1004:
                    return UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST;
                case 0:
                    return KIND_NOT_SET;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @java.lang.Override
    public KindCase getKindCase() {
        return KindCase.forNumber(kindCase_);
    }

    private void clearKind() {
        kindCase_ = 0;
        kind_ = null;
    }

    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    private long requestId_;

    /**
     * <pre>
     * Note: "request_id" is allowed to be duplicate because
     * it is used for clients to identify the response of the same request id in a session
     * </pre>
     * 
     * <code>optional int64 request_id = 1;</code>
     *
     * @return Whether the requestId field is set.
     */
    @java.lang.Override
    public boolean hasRequestId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Note: "request_id" is allowed to be duplicate because
     * it is used for clients to identify the response of the same request id in a session
     * </pre>
     * 
     * <code>optional int64 request_id = 1;</code>
     *
     * @return The requestId.
     */
    @java.lang.Override
    public long getRequestId() {
        return requestId_;
    }

    /**
     * <pre>
     * Note: "request_id" is allowed to be duplicate because
     * it is used for clients to identify the response of the same request id in a session
     * </pre>
     * 
     * <code>optional int64 request_id = 1;</code>
     *
     * @param value The requestId to set.
     */
    private void setRequestId(long value) {
        bitField0_ |= 0x00000001;
        requestId_ = value;
    }

    /**
     * <pre>
     * Note: "request_id" is allowed to be duplicate because
     * it is used for clients to identify the response of the same request id in a session
     * </pre>
     * 
     * <code>optional int64 request_id = 1;</code>
     */
    private void clearRequestId() {
        bitField0_ &= ~0x00000001;
        requestId_ = 0L;
    }

    public static final int CREATE_SESSION_REQUEST_FIELD_NUMBER = 3;

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    @java.lang.Override
    public boolean hasCreateSessionRequest() {
        return kindCase_ == 3;
    }

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.CreateSessionRequest getCreateSessionRequest() {
        if (kindCase_ == 3) {
            return (im.turms.client.model.proto.request.user.CreateSessionRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.CreateSessionRequest.getDefaultInstance();
    }

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    private void setCreateSessionRequest(
            im.turms.client.model.proto.request.user.CreateSessionRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 3;
    }

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    private void mergeCreateSessionRequest(
            im.turms.client.model.proto.request.user.CreateSessionRequest value) {
        value.getClass();
        if (kindCase_ == 3
                && kind_ != im.turms.client.model.proto.request.user.CreateSessionRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.CreateSessionRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.CreateSessionRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 3;
    }

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    private void clearCreateSessionRequest() {
        if (kindCase_ == 3) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_SESSION_REQUEST_FIELD_NUMBER = 4;

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    @java.lang.Override
    public boolean hasDeleteSessionRequest() {
        return kindCase_ == 4;
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.DeleteSessionRequest getDeleteSessionRequest() {
        if (kindCase_ == 4) {
            return (im.turms.client.model.proto.request.user.DeleteSessionRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.DeleteSessionRequest.getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    private void setDeleteSessionRequest(
            im.turms.client.model.proto.request.user.DeleteSessionRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 4;
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    private void mergeDeleteSessionRequest(
            im.turms.client.model.proto.request.user.DeleteSessionRequest value) {
        value.getClass();
        if (kindCase_ == 4
                && kind_ != im.turms.client.model.proto.request.user.DeleteSessionRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.DeleteSessionRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.DeleteSessionRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 4;
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    private void clearDeleteSessionRequest() {
        if (kindCase_ == 4) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_CONVERSATIONS_REQUEST_FIELD_NUMBER = 5;

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    @java.lang.Override
    public boolean hasQueryConversationsRequest() {
        return kindCase_ == 5;
    }

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.conversation.QueryConversationsRequest getQueryConversationsRequest() {
        if (kindCase_ == 5) {
            return (im.turms.client.model.proto.request.conversation.QueryConversationsRequest) kind_;
        }
        return im.turms.client.model.proto.request.conversation.QueryConversationsRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    private void setQueryConversationsRequest(
            im.turms.client.model.proto.request.conversation.QueryConversationsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 5;
    }

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    private void mergeQueryConversationsRequest(
            im.turms.client.model.proto.request.conversation.QueryConversationsRequest value) {
        value.getClass();
        if (kindCase_ == 5
                && kind_ != im.turms.client.model.proto.request.conversation.QueryConversationsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.conversation.QueryConversationsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.conversation.QueryConversationsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 5;
    }

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    private void clearQueryConversationsRequest() {
        if (kindCase_ == 5) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_CONVERSATION_REQUEST_FIELD_NUMBER = 6;

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    @java.lang.Override
    public boolean hasUpdateConversationRequest() {
        return kindCase_ == 6;
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.conversation.UpdateConversationRequest getUpdateConversationRequest() {
        if (kindCase_ == 6) {
            return (im.turms.client.model.proto.request.conversation.UpdateConversationRequest) kind_;
        }
        return im.turms.client.model.proto.request.conversation.UpdateConversationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    private void setUpdateConversationRequest(
            im.turms.client.model.proto.request.conversation.UpdateConversationRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 6;
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    private void mergeUpdateConversationRequest(
            im.turms.client.model.proto.request.conversation.UpdateConversationRequest value) {
        value.getClass();
        if (kindCase_ == 6
                && kind_ != im.turms.client.model.proto.request.conversation.UpdateConversationRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.conversation.UpdateConversationRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.conversation.UpdateConversationRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 6;
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    private void clearUpdateConversationRequest() {
        if (kindCase_ == 6) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_TYPING_STATUS_REQUEST_FIELD_NUMBER = 7;

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    @java.lang.Override
    public boolean hasUpdateTypingStatusRequest() {
        return kindCase_ == 7;
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest getUpdateTypingStatusRequest() {
        if (kindCase_ == 7) {
            return (im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest) kind_;
        }
        return im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    private void setUpdateTypingStatusRequest(
            im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 7;
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    private void mergeUpdateTypingStatusRequest(
            im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest value) {
        value.getClass();
        if (kindCase_ == 7
                && kind_ != im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 7;
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    private void clearUpdateTypingStatusRequest() {
        if (kindCase_ == 7) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_MESSAGE_REQUEST_FIELD_NUMBER = 8;

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    @java.lang.Override
    public boolean hasCreateMessageRequest() {
        return kindCase_ == 8;
    }

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.message.CreateMessageRequest getCreateMessageRequest() {
        if (kindCase_ == 8) {
            return (im.turms.client.model.proto.request.message.CreateMessageRequest) kind_;
        }
        return im.turms.client.model.proto.request.message.CreateMessageRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    private void setCreateMessageRequest(
            im.turms.client.model.proto.request.message.CreateMessageRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 8;
    }

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    private void mergeCreateMessageRequest(
            im.turms.client.model.proto.request.message.CreateMessageRequest value) {
        value.getClass();
        if (kindCase_ == 8
                && kind_ != im.turms.client.model.proto.request.message.CreateMessageRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.message.CreateMessageRequest.newBuilder(
                    (im.turms.client.model.proto.request.message.CreateMessageRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 8;
    }

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    private void clearCreateMessageRequest() {
        if (kindCase_ == 8) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_MESSAGES_REQUEST_FIELD_NUMBER = 9;

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    @java.lang.Override
    public boolean hasQueryMessagesRequest() {
        return kindCase_ == 9;
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.message.QueryMessagesRequest getQueryMessagesRequest() {
        if (kindCase_ == 9) {
            return (im.turms.client.model.proto.request.message.QueryMessagesRequest) kind_;
        }
        return im.turms.client.model.proto.request.message.QueryMessagesRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    private void setQueryMessagesRequest(
            im.turms.client.model.proto.request.message.QueryMessagesRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 9;
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    private void mergeQueryMessagesRequest(
            im.turms.client.model.proto.request.message.QueryMessagesRequest value) {
        value.getClass();
        if (kindCase_ == 9
                && kind_ != im.turms.client.model.proto.request.message.QueryMessagesRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.message.QueryMessagesRequest.newBuilder(
                    (im.turms.client.model.proto.request.message.QueryMessagesRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 9;
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    private void clearQueryMessagesRequest() {
        if (kindCase_ == 9) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_MESSAGE_REQUEST_FIELD_NUMBER = 10;

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    @java.lang.Override
    public boolean hasUpdateMessageRequest() {
        return kindCase_ == 10;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.message.UpdateMessageRequest getUpdateMessageRequest() {
        if (kindCase_ == 10) {
            return (im.turms.client.model.proto.request.message.UpdateMessageRequest) kind_;
        }
        return im.turms.client.model.proto.request.message.UpdateMessageRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    private void setUpdateMessageRequest(
            im.turms.client.model.proto.request.message.UpdateMessageRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 10;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    private void mergeUpdateMessageRequest(
            im.turms.client.model.proto.request.message.UpdateMessageRequest value) {
        value.getClass();
        if (kindCase_ == 10
                && kind_ != im.turms.client.model.proto.request.message.UpdateMessageRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.message.UpdateMessageRequest.newBuilder(
                    (im.turms.client.model.proto.request.message.UpdateMessageRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 10;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    private void clearUpdateMessageRequest() {
        if (kindCase_ == 10) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 11;

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupMembersRequest() {
        return kindCase_ == 11;
    }

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest getCreateGroupMembersRequest() {
        if (kindCase_ == 11) {
            return (im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    private void setCreateGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 11;
    }

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    private void mergeCreateGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest value) {
        value.getClass();
        if (kindCase_ == 11
                && kind_ != im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 11;
    }

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    private void clearCreateGroupMembersRequest() {
        if (kindCase_ == 11) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 12;

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupMembersRequest() {
        return kindCase_ == 12;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest getDeleteGroupMembersRequest() {
        if (kindCase_ == 12) {
            return (im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    private void setDeleteGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 12;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    private void mergeDeleteGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest value) {
        value.getClass();
        if (kindCase_ == 12
                && kind_ != im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 12;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    private void clearDeleteGroupMembersRequest() {
        if (kindCase_ == 12) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 13;

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupMembersRequest() {
        return kindCase_ == 13;
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest getQueryGroupMembersRequest() {
        if (kindCase_ == 13) {
            return (im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    private void setQueryGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 13;
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    private void mergeQueryGroupMembersRequest(
            im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest value) {
        value.getClass();
        if (kindCase_ == 13
                && kind_ != im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 13;
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    private void clearQueryGroupMembersRequest() {
        if (kindCase_ == 13) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_GROUP_MEMBER_REQUEST_FIELD_NUMBER = 14;

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    @java.lang.Override
    public boolean hasUpdateGroupMemberRequest() {
        return kindCase_ == 14;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest getUpdateGroupMemberRequest() {
        if (kindCase_ == 14) {
            return (im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    private void setUpdateGroupMemberRequest(
            im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 14;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    private void mergeUpdateGroupMemberRequest(
            im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest value) {
        value.getClass();
        if (kindCase_ == 14
                && kind_ != im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 14;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    private void clearUpdateGroupMemberRequest() {
        if (kindCase_ == 14) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_USER_PROFILES_REQUEST_FIELD_NUMBER = 100;

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    @java.lang.Override
    public boolean hasQueryUserProfilesRequest() {
        return kindCase_ == 100;
    }

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.QueryUserProfilesRequest getQueryUserProfilesRequest() {
        if (kindCase_ == 100) {
            return (im.turms.client.model.proto.request.user.QueryUserProfilesRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.QueryUserProfilesRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    private void setQueryUserProfilesRequest(
            im.turms.client.model.proto.request.user.QueryUserProfilesRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 100;
    }

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    private void mergeQueryUserProfilesRequest(
            im.turms.client.model.proto.request.user.QueryUserProfilesRequest value) {
        value.getClass();
        if (kindCase_ == 100
                && kind_ != im.turms.client.model.proto.request.user.QueryUserProfilesRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.QueryUserProfilesRequest.newBuilder(
                    (im.turms.client.model.proto.request.user.QueryUserProfilesRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 100;
    }

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    private void clearQueryUserProfilesRequest() {
        if (kindCase_ == 100) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_NEARBY_USERS_REQUEST_FIELD_NUMBER = 101;

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    @java.lang.Override
    public boolean hasQueryNearbyUsersRequest() {
        return kindCase_ == 101;
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.QueryNearbyUsersRequest getQueryNearbyUsersRequest() {
        if (kindCase_ == 101) {
            return (im.turms.client.model.proto.request.user.QueryNearbyUsersRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.QueryNearbyUsersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    private void setQueryNearbyUsersRequest(
            im.turms.client.model.proto.request.user.QueryNearbyUsersRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 101;
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    private void mergeQueryNearbyUsersRequest(
            im.turms.client.model.proto.request.user.QueryNearbyUsersRequest value) {
        value.getClass();
        if (kindCase_ == 101
                && kind_ != im.turms.client.model.proto.request.user.QueryNearbyUsersRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.QueryNearbyUsersRequest.newBuilder(
                    (im.turms.client.model.proto.request.user.QueryNearbyUsersRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 101;
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    private void clearQueryNearbyUsersRequest() {
        if (kindCase_ == 101) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_USER_ONLINE_STATUSES_REQUEST_FIELD_NUMBER = 102;

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    @java.lang.Override
    public boolean hasQueryUserOnlineStatusesRequest() {
        return kindCase_ == 102;
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest getQueryUserOnlineStatusesRequest() {
        if (kindCase_ == 102) {
            return (im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    private void setQueryUserOnlineStatusesRequest(
            im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 102;
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    private void mergeQueryUserOnlineStatusesRequest(
            im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest value) {
        value.getClass();
        if (kindCase_ == 102
                && kind_ != im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 102;
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    private void clearQueryUserOnlineStatusesRequest() {
        if (kindCase_ == 102) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_USER_LOCATION_REQUEST_FIELD_NUMBER = 103;

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    @java.lang.Override
    public boolean hasUpdateUserLocationRequest() {
        return kindCase_ == 103;
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.UpdateUserLocationRequest getUpdateUserLocationRequest() {
        if (kindCase_ == 103) {
            return (im.turms.client.model.proto.request.user.UpdateUserLocationRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.UpdateUserLocationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    private void setUpdateUserLocationRequest(
            im.turms.client.model.proto.request.user.UpdateUserLocationRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 103;
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    private void mergeUpdateUserLocationRequest(
            im.turms.client.model.proto.request.user.UpdateUserLocationRequest value) {
        value.getClass();
        if (kindCase_ == 103
                && kind_ != im.turms.client.model.proto.request.user.UpdateUserLocationRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.UpdateUserLocationRequest.newBuilder(
                    (im.turms.client.model.proto.request.user.UpdateUserLocationRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 103;
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    private void clearUpdateUserLocationRequest() {
        if (kindCase_ == 103) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_USER_ONLINE_STATUS_REQUEST_FIELD_NUMBER = 104;

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    @java.lang.Override
    public boolean hasUpdateUserOnlineStatusRequest() {
        return kindCase_ == 104;
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest getUpdateUserOnlineStatusRequest() {
        if (kindCase_ == 104) {
            return (im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    private void setUpdateUserOnlineStatusRequest(
            im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 104;
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    private void mergeUpdateUserOnlineStatusRequest(
            im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest value) {
        value.getClass();
        if (kindCase_ == 104
                && kind_ != im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 104;
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    private void clearUpdateUserOnlineStatusRequest() {
        if (kindCase_ == 104) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_USER_REQUEST_FIELD_NUMBER = 105;

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    @java.lang.Override
    public boolean hasUpdateUserRequest() {
        return kindCase_ == 105;
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.UpdateUserRequest getUpdateUserRequest() {
        if (kindCase_ == 105) {
            return (im.turms.client.model.proto.request.user.UpdateUserRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.UpdateUserRequest.getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    private void setUpdateUserRequest(
            im.turms.client.model.proto.request.user.UpdateUserRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 105;
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    private void mergeUpdateUserRequest(
            im.turms.client.model.proto.request.user.UpdateUserRequest value) {
        value.getClass();
        if (kindCase_ == 105
                && kind_ != im.turms.client.model.proto.request.user.UpdateUserRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.UpdateUserRequest
                    .newBuilder((im.turms.client.model.proto.request.user.UpdateUserRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 105;
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    private void clearUpdateUserRequest() {
        if (kindCase_ == 105) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER = 200;

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    @java.lang.Override
    public boolean hasCreateFriendRequestRequest() {
        return kindCase_ == 200;
    }

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest getCreateFriendRequestRequest() {
        if (kindCase_ == 200) {
            return (im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    private void setCreateFriendRequestRequest(
            im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 200;
    }

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    private void mergeCreateFriendRequestRequest(
            im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest value) {
        value.getClass();
        if (kindCase_ == 200
                && kind_ != im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 200;
    }

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    private void clearCreateFriendRequestRequest() {
        if (kindCase_ == 200) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 201;

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    @java.lang.Override
    public boolean hasCreateRelationshipGroupRequest() {
        return kindCase_ == 201;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest getCreateRelationshipGroupRequest() {
        if (kindCase_ == 201) {
            return (im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    private void setCreateRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 201;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    private void mergeCreateRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest value) {
        value.getClass();
        if (kindCase_ == 201
                && kind_ != im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 201;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    private void clearCreateRelationshipGroupRequest() {
        if (kindCase_ == 201) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 202;

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    @java.lang.Override
    public boolean hasCreateRelationshipRequest() {
        return kindCase_ == 202;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest getCreateRelationshipRequest() {
        if (kindCase_ == 202) {
            return (im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    private void setCreateRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 202;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    private void mergeCreateRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest value) {
        value.getClass();
        if (kindCase_ == 202
                && kind_ != im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 202;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    private void clearCreateRelationshipRequest() {
        if (kindCase_ == 202) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 203;

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    @java.lang.Override
    public boolean hasDeleteRelationshipGroupRequest() {
        return kindCase_ == 203;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest getDeleteRelationshipGroupRequest() {
        if (kindCase_ == 203) {
            return (im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    private void setDeleteRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 203;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    private void mergeDeleteRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest value) {
        value.getClass();
        if (kindCase_ == 203
                && kind_ != im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 203;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    private void clearDeleteRelationshipGroupRequest() {
        if (kindCase_ == 203) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 204;

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    @java.lang.Override
    public boolean hasDeleteRelationshipRequest() {
        return kindCase_ == 204;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest getDeleteRelationshipRequest() {
        if (kindCase_ == 204) {
            return (im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    private void setDeleteRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 204;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    private void mergeDeleteRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest value) {
        value.getClass();
        if (kindCase_ == 204
                && kind_ != im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 204;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    private void clearDeleteRelationshipRequest() {
        if (kindCase_ == 204) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_FRIEND_REQUESTS_REQUEST_FIELD_NUMBER = 205;

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    @java.lang.Override
    public boolean hasQueryFriendRequestsRequest() {
        return kindCase_ == 205;
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest getQueryFriendRequestsRequest() {
        if (kindCase_ == 205) {
            return (im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    private void setQueryFriendRequestsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 205;
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    private void mergeQueryFriendRequestsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest value) {
        value.getClass();
        if (kindCase_ == 205
                && kind_ != im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 205;
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    private void clearQueryFriendRequestsRequest() {
        if (kindCase_ == 205) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_RELATED_USER_IDS_REQUEST_FIELD_NUMBER = 206;

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    @java.lang.Override
    public boolean hasQueryRelatedUserIdsRequest() {
        return kindCase_ == 206;
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest getQueryRelatedUserIdsRequest() {
        if (kindCase_ == 206) {
            return (im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    private void setQueryRelatedUserIdsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 206;
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    private void mergeQueryRelatedUserIdsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest value) {
        value.getClass();
        if (kindCase_ == 206
                && kind_ != im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 206;
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    private void clearQueryRelatedUserIdsRequest() {
        if (kindCase_ == 206) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_RELATIONSHIP_GROUPS_REQUEST_FIELD_NUMBER = 207;

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    @java.lang.Override
    public boolean hasQueryRelationshipGroupsRequest() {
        return kindCase_ == 207;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest getQueryRelationshipGroupsRequest() {
        if (kindCase_ == 207) {
            return (im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    private void setQueryRelationshipGroupsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 207;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    private void mergeQueryRelationshipGroupsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest value) {
        value.getClass();
        if (kindCase_ == 207
                && kind_ != im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 207;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    private void clearQueryRelationshipGroupsRequest() {
        if (kindCase_ == 207) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_RELATIONSHIPS_REQUEST_FIELD_NUMBER = 208;

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    @java.lang.Override
    public boolean hasQueryRelationshipsRequest() {
        return kindCase_ == 208;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest getQueryRelationshipsRequest() {
        if (kindCase_ == 208) {
            return (im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    private void setQueryRelationshipsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 208;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    private void mergeQueryRelationshipsRequest(
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest value) {
        value.getClass();
        if (kindCase_ == 208
                && kind_ != im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 208;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    private void clearQueryRelationshipsRequest() {
        if (kindCase_ == 208) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER = 209;

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    @java.lang.Override
    public boolean hasUpdateFriendRequestRequest() {
        return kindCase_ == 209;
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest getUpdateFriendRequestRequest() {
        if (kindCase_ == 209) {
            return (im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    private void setUpdateFriendRequestRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 209;
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    private void mergeUpdateFriendRequestRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest value) {
        value.getClass();
        if (kindCase_ == 209
                && kind_ != im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 209;
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    private void clearUpdateFriendRequestRequest() {
        if (kindCase_ == 209) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 210;

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    @java.lang.Override
    public boolean hasUpdateRelationshipGroupRequest() {
        return kindCase_ == 210;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest getUpdateRelationshipGroupRequest() {
        if (kindCase_ == 210) {
            return (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    private void setUpdateRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 210;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    private void mergeUpdateRelationshipGroupRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest value) {
        value.getClass();
        if (kindCase_ == 210
                && kind_ != im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 210;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    private void clearUpdateRelationshipGroupRequest() {
        if (kindCase_ == 210) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 211;

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    @java.lang.Override
    public boolean hasUpdateRelationshipRequest() {
        return kindCase_ == 211;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest getUpdateRelationshipRequest() {
        if (kindCase_ == 211) {
            return (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest) kind_;
        }
        return im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    private void setUpdateRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 211;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    private void mergeUpdateRelationshipRequest(
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest value) {
        value.getClass();
        if (kindCase_ == 211
                && kind_ != im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 211;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    private void clearUpdateRelationshipRequest() {
        if (kindCase_ == 211) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_REQUEST_FIELD_NUMBER = 300;

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupRequest() {
        return kindCase_ == 300;
    }

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.CreateGroupRequest getCreateGroupRequest() {
        if (kindCase_ == 300) {
            return (im.turms.client.model.proto.request.group.CreateGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.CreateGroupRequest.getDefaultInstance();
    }

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    private void setCreateGroupRequest(
            im.turms.client.model.proto.request.group.CreateGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 300;
    }

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    private void mergeCreateGroupRequest(
            im.turms.client.model.proto.request.group.CreateGroupRequest value) {
        value.getClass();
        if (kindCase_ == 300
                && kind_ != im.turms.client.model.proto.request.group.CreateGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.CreateGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.CreateGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 300;
    }

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    private void clearCreateGroupRequest() {
        if (kindCase_ == 300) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_REQUEST_FIELD_NUMBER = 301;

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupRequest() {
        return kindCase_ == 301;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.DeleteGroupRequest getDeleteGroupRequest() {
        if (kindCase_ == 301) {
            return (im.turms.client.model.proto.request.group.DeleteGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.DeleteGroupRequest.getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    private void setDeleteGroupRequest(
            im.turms.client.model.proto.request.group.DeleteGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 301;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    private void mergeDeleteGroupRequest(
            im.turms.client.model.proto.request.group.DeleteGroupRequest value) {
        value.getClass();
        if (kindCase_ == 301
                && kind_ != im.turms.client.model.proto.request.group.DeleteGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.DeleteGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.DeleteGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 301;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    private void clearDeleteGroupRequest() {
        if (kindCase_ == 301) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUPS_REQUEST_FIELD_NUMBER = 302;

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupsRequest() {
        return kindCase_ == 302;
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.QueryGroupsRequest getQueryGroupsRequest() {
        if (kindCase_ == 302) {
            return (im.turms.client.model.proto.request.group.QueryGroupsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.QueryGroupsRequest.getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    private void setQueryGroupsRequest(
            im.turms.client.model.proto.request.group.QueryGroupsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 302;
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    private void mergeQueryGroupsRequest(
            im.turms.client.model.proto.request.group.QueryGroupsRequest value) {
        value.getClass();
        if (kindCase_ == 302
                && kind_ != im.turms.client.model.proto.request.group.QueryGroupsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.QueryGroupsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.QueryGroupsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 302;
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    private void clearQueryGroupsRequest() {
        if (kindCase_ == 302) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_JOINED_GROUP_IDS_REQUEST_FIELD_NUMBER = 303;

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    @java.lang.Override
    public boolean hasQueryJoinedGroupIdsRequest() {
        return kindCase_ == 303;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest getQueryJoinedGroupIdsRequest() {
        if (kindCase_ == 303) {
            return (im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    private void setQueryJoinedGroupIdsRequest(
            im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 303;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    private void mergeQueryJoinedGroupIdsRequest(
            im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest value) {
        value.getClass();
        if (kindCase_ == 303
                && kind_ != im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest.newBuilder(
                    (im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 303;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    private void clearQueryJoinedGroupIdsRequest() {
        if (kindCase_ == 303) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_JOINED_GROUP_INFOS_REQUEST_FIELD_NUMBER = 304;

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    @java.lang.Override
    public boolean hasQueryJoinedGroupInfosRequest() {
        return kindCase_ == 304;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest getQueryJoinedGroupInfosRequest() {
        if (kindCase_ == 304) {
            return (im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    private void setQueryJoinedGroupInfosRequest(
            im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 304;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    private void mergeQueryJoinedGroupInfosRequest(
            im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest value) {
        value.getClass();
        if (kindCase_ == 304
                && kind_ != im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 304;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    private void clearQueryJoinedGroupInfosRequest() {
        if (kindCase_ == 304) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_GROUP_REQUEST_FIELD_NUMBER = 305;

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    @java.lang.Override
    public boolean hasUpdateGroupRequest() {
        return kindCase_ == 305;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.UpdateGroupRequest getUpdateGroupRequest() {
        if (kindCase_ == 305) {
            return (im.turms.client.model.proto.request.group.UpdateGroupRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.UpdateGroupRequest.getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    private void setUpdateGroupRequest(
            im.turms.client.model.proto.request.group.UpdateGroupRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 305;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    private void mergeUpdateGroupRequest(
            im.turms.client.model.proto.request.group.UpdateGroupRequest value) {
        value.getClass();
        if (kindCase_ == 305
                && kind_ != im.turms.client.model.proto.request.group.UpdateGroupRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.UpdateGroupRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.UpdateGroupRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 305;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    private void clearUpdateGroupRequest() {
        if (kindCase_ == 305) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER = 400;

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupBlockedUserRequest() {
        return kindCase_ == 400;
    }

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest getCreateGroupBlockedUserRequest() {
        if (kindCase_ == 400) {
            return (im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    private void setCreateGroupBlockedUserRequest(
            im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 400;
    }

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    private void mergeCreateGroupBlockedUserRequest(
            im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
        value.getClass();
        if (kindCase_ == 400
                && kind_ != im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 400;
    }

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    private void clearCreateGroupBlockedUserRequest() {
        if (kindCase_ == 400) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER = 401;

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupBlockedUserRequest() {
        return kindCase_ == 401;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest getDeleteGroupBlockedUserRequest() {
        if (kindCase_ == 401) {
            return (im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    private void setDeleteGroupBlockedUserRequest(
            im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 401;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    private void mergeDeleteGroupBlockedUserRequest(
            im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
        value.getClass();
        if (kindCase_ == 401
                && kind_ != im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 401;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    private void clearDeleteGroupBlockedUserRequest() {
        if (kindCase_ == 401) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_BLOCKED_USER_IDS_REQUEST_FIELD_NUMBER = 402;

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupBlockedUserIdsRequest() {
        return kindCase_ == 402;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest getQueryGroupBlockedUserIdsRequest() {
        if (kindCase_ == 402) {
            return (im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    private void setQueryGroupBlockedUserIdsRequest(
            im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 402;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    private void mergeQueryGroupBlockedUserIdsRequest(
            im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
        value.getClass();
        if (kindCase_ == 402
                && kind_ != im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 402;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    private void clearQueryGroupBlockedUserIdsRequest() {
        if (kindCase_ == 402) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST_FIELD_NUMBER = 403;

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupBlockedUserInfosRequest() {
        return kindCase_ == 403;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest getQueryGroupBlockedUserInfosRequest() {
        if (kindCase_ == 403) {
            return (im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    private void setQueryGroupBlockedUserInfosRequest(
            im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 403;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    private void mergeQueryGroupBlockedUserInfosRequest(
            im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
        value.getClass();
        if (kindCase_ == 403
                && kind_ != im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 403;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    private void clearQueryGroupBlockedUserInfosRequest() {
        if (kindCase_ == 403) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST_FIELD_NUMBER = 500;

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    @java.lang.Override
    public boolean hasCheckGroupJoinQuestionsAnswersRequest() {
        return kindCase_ == 500;
    }

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getCheckGroupJoinQuestionsAnswersRequest() {
        if (kindCase_ == 500) {
            return (im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    private void setCheckGroupJoinQuestionsAnswersRequest(
            im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 500;
    }

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    private void mergeCheckGroupJoinQuestionsAnswersRequest(
            im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
        value.getClass();
        if (kindCase_ == 500
                && kind_ != im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 500;
    }

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    private void clearCheckGroupJoinQuestionsAnswersRequest() {
        if (kindCase_ == 500) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_INVITATION_REQUEST_FIELD_NUMBER = 501;

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupInvitationRequest() {
        return kindCase_ == 501;
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest getCreateGroupInvitationRequest() {
        if (kindCase_ == 501) {
            return (im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    private void setCreateGroupInvitationRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 501;
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    private void mergeCreateGroupInvitationRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest value) {
        value.getClass();
        if (kindCase_ == 501
                && kind_ != im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 501;
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    private void clearCreateGroupInvitationRequest() {
        if (kindCase_ == 501) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER = 502;

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupJoinRequestRequest() {
        return kindCase_ == 502;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest getCreateGroupJoinRequestRequest() {
        if (kindCase_ == 502) {
            return (im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    private void setCreateGroupJoinRequestRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 502;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    private void mergeCreateGroupJoinRequestRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
        value.getClass();
        if (kindCase_ == 502
                && kind_ != im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 502;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    private void clearCreateGroupJoinRequestRequest() {
        if (kindCase_ == 502) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int CREATE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 503;

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    @java.lang.Override
    public boolean hasCreateGroupJoinQuestionsRequest() {
        return kindCase_ == 503;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest getCreateGroupJoinQuestionsRequest() {
        if (kindCase_ == 503) {
            return (im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    private void setCreateGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 503;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    private void mergeCreateGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
        value.getClass();
        if (kindCase_ == 503
                && kind_ != im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 503;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    private void clearCreateGroupJoinQuestionsRequest() {
        if (kindCase_ == 503) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_INVITATION_REQUEST_FIELD_NUMBER = 504;

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupInvitationRequest() {
        return kindCase_ == 504;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest getDeleteGroupInvitationRequest() {
        if (kindCase_ == 504) {
            return (im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    private void setDeleteGroupInvitationRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 504;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    private void mergeDeleteGroupInvitationRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest value) {
        value.getClass();
        if (kindCase_ == 504
                && kind_ != im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 504;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    private void clearDeleteGroupInvitationRequest() {
        if (kindCase_ == 504) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER = 505;

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupJoinRequestRequest() {
        return kindCase_ == 505;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest getDeleteGroupJoinRequestRequest() {
        if (kindCase_ == 505) {
            return (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    private void setDeleteGroupJoinRequestRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 505;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    private void mergeDeleteGroupJoinRequestRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
        value.getClass();
        if (kindCase_ == 505
                && kind_ != im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 505;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    private void clearDeleteGroupJoinRequestRequest() {
        if (kindCase_ == 505) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 506;

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    @java.lang.Override
    public boolean hasDeleteGroupJoinQuestionsRequest() {
        return kindCase_ == 506;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDeleteGroupJoinQuestionsRequest() {
        if (kindCase_ == 506) {
            return (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    private void setDeleteGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 506;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    private void mergeDeleteGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
        value.getClass();
        if (kindCase_ == 506
                && kind_ != im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 506;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    private void clearDeleteGroupJoinQuestionsRequest() {
        if (kindCase_ == 506) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_INVITATIONS_REQUEST_FIELD_NUMBER = 507;

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupInvitationsRequest() {
        return kindCase_ == 507;
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest getQueryGroupInvitationsRequest() {
        if (kindCase_ == 507) {
            return (im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    private void setQueryGroupInvitationsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 507;
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    private void mergeQueryGroupInvitationsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest value) {
        value.getClass();
        if (kindCase_ == 507
                && kind_ != im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 507;
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    private void clearQueryGroupInvitationsRequest() {
        if (kindCase_ == 507) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_JOIN_REQUESTS_REQUEST_FIELD_NUMBER = 508;

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupJoinRequestsRequest() {
        return kindCase_ == 508;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest getQueryGroupJoinRequestsRequest() {
        if (kindCase_ == 508) {
            return (im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    private void setQueryGroupJoinRequestsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 508;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    private void mergeQueryGroupJoinRequestsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
        value.getClass();
        if (kindCase_ == 508
                && kind_ != im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 508;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    private void clearQueryGroupJoinRequestsRequest() {
        if (kindCase_ == 508) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 509;

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    @java.lang.Override
    public boolean hasQueryGroupJoinQuestionsRequest() {
        return kindCase_ == 509;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest getQueryGroupJoinQuestionsRequest() {
        if (kindCase_ == 509) {
            return (im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    private void setQueryGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 509;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    private void mergeQueryGroupJoinQuestionsRequest(
            im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
        value.getClass();
        if (kindCase_ == 509
                && kind_ != im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 509;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    private void clearQueryGroupJoinQuestionsRequest() {
        if (kindCase_ == 509) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_GROUP_JOIN_QUESTION_REQUEST_FIELD_NUMBER = 510;

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    @java.lang.Override
    public boolean hasUpdateGroupJoinQuestionRequest() {
        return kindCase_ == 510;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest getUpdateGroupJoinQuestionRequest() {
        if (kindCase_ == 510) {
            return (im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_;
        }
        return im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    private void setUpdateGroupJoinQuestionRequest(
            im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 510;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    private void mergeUpdateGroupJoinQuestionRequest(
            im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
        value.getClass();
        if (kindCase_ == 510
                && kind_ != im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 510;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    private void clearUpdateGroupJoinQuestionRequest() {
        if (kindCase_ == 510) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int DELETE_RESOURCE_REQUEST_FIELD_NUMBER = 1000;

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    @java.lang.Override
    public boolean hasDeleteResourceRequest() {
        return kindCase_ == 1000;
    }

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.storage.DeleteResourceRequest getDeleteResourceRequest() {
        if (kindCase_ == 1000) {
            return (im.turms.client.model.proto.request.storage.DeleteResourceRequest) kind_;
        }
        return im.turms.client.model.proto.request.storage.DeleteResourceRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    private void setDeleteResourceRequest(
            im.turms.client.model.proto.request.storage.DeleteResourceRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 1000;
    }

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    private void mergeDeleteResourceRequest(
            im.turms.client.model.proto.request.storage.DeleteResourceRequest value) {
        value.getClass();
        if (kindCase_ == 1000
                && kind_ != im.turms.client.model.proto.request.storage.DeleteResourceRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.storage.DeleteResourceRequest.newBuilder(
                    (im.turms.client.model.proto.request.storage.DeleteResourceRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 1000;
    }

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    private void clearDeleteResourceRequest() {
        if (kindCase_ == 1000) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST_FIELD_NUMBER = 1001;

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    @java.lang.Override
    public boolean hasQueryResourceDownloadInfoRequest() {
        return kindCase_ == 1001;
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest getQueryResourceDownloadInfoRequest() {
        if (kindCase_ == 1001) {
            return (im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest) kind_;
        }
        return im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    private void setQueryResourceDownloadInfoRequest(
            im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 1001;
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    private void mergeQueryResourceDownloadInfoRequest(
            im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest value) {
        value.getClass();
        if (kindCase_ == 1001
                && kind_ != im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 1001;
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    private void clearQueryResourceDownloadInfoRequest() {
        if (kindCase_ == 1001) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_RESOURCE_UPLOAD_INFO_REQUEST_FIELD_NUMBER = 1002;

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    @java.lang.Override
    public boolean hasQueryResourceUploadInfoRequest() {
        return kindCase_ == 1002;
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest getQueryResourceUploadInfoRequest() {
        if (kindCase_ == 1002) {
            return (im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest) kind_;
        }
        return im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    private void setQueryResourceUploadInfoRequest(
            im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 1002;
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    private void mergeQueryResourceUploadInfoRequest(
            im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest value) {
        value.getClass();
        if (kindCase_ == 1002
                && kind_ != im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 1002;
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    private void clearQueryResourceUploadInfoRequest() {
        if (kindCase_ == 1002) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST_FIELD_NUMBER = 1003;

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    @java.lang.Override
    public boolean hasQueryMessageAttachmentInfosRequest() {
        return kindCase_ == 1003;
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest getQueryMessageAttachmentInfosRequest() {
        if (kindCase_ == 1003) {
            return (im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest) kind_;
        }
        return im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    private void setQueryMessageAttachmentInfosRequest(
            im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 1003;
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    private void mergeQueryMessageAttachmentInfosRequest(
            im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest value) {
        value.getClass();
        if (kindCase_ == 1003
                && kind_ != im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 1003;
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    private void clearQueryMessageAttachmentInfosRequest() {
        if (kindCase_ == 1003) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static final int UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST_FIELD_NUMBER = 1004;

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    @java.lang.Override
    public boolean hasUpdateMessageAttachmentInfoRequest() {
        return kindCase_ == 1004;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest getUpdateMessageAttachmentInfoRequest() {
        if (kindCase_ == 1004) {
            return (im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest) kind_;
        }
        return im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    private void setUpdateMessageAttachmentInfoRequest(
            im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest value) {
        value.getClass();
        kind_ = value;
        kindCase_ = 1004;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    private void mergeUpdateMessageAttachmentInfoRequest(
            im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest value) {
        value.getClass();
        if (kindCase_ == 1004
                && kind_ != im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest
                        .getDefaultInstance()) {
            kind_ = im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest
                    .newBuilder(
                            (im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest) kind_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            kind_ = value;
        }
        kindCase_ = 1004;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    private void clearUpdateMessageAttachmentInfoRequest() {
        if (kindCase_ == 1004) {
            kindCase_ = 0;
            kind_ = null;
        }
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.TurmsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.request.TurmsRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * <pre>
     * Client -&gt; Server -&gt; Client
     * </pre>
     * <p>
     * Protobuf type {@code im.turms.proto.TurmsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.TurmsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsRequest)
            im.turms.client.model.proto.request.TurmsRequestOrBuilder {
        // Construct using im.turms.client.model.proto.request.TurmsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        @java.lang.Override
        public KindCase getKindCase() {
            return instance.getKindCase();
        }

        public Builder clearKind() {
            copyOnWrite();
            instance.clearKind();
            return this;
        }

        /**
         * <pre>
         * Note: "request_id" is allowed to be duplicate because
         * it is used for clients to identify the response of the same request id in a session
         * </pre>
         * 
         * <code>optional int64 request_id = 1;</code>
         *
         * @return Whether the requestId field is set.
         */
        @java.lang.Override
        public boolean hasRequestId() {
            return instance.hasRequestId();
        }

        /**
         * <pre>
         * Note: "request_id" is allowed to be duplicate because
         * it is used for clients to identify the response of the same request id in a session
         * </pre>
         * 
         * <code>optional int64 request_id = 1;</code>
         *
         * @return The requestId.
         */
        @java.lang.Override
        public long getRequestId() {
            return instance.getRequestId();
        }

        /**
         * <pre>
         * Note: "request_id" is allowed to be duplicate because
         * it is used for clients to identify the response of the same request id in a session
         * </pre>
         * 
         * <code>optional int64 request_id = 1;</code>
         *
         * @param value The requestId to set.
         * @return This builder for chaining.
         */
        public Builder setRequestId(long value) {
            copyOnWrite();
            instance.setRequestId(value);
            return this;
        }

        /**
         * <pre>
         * Note: "request_id" is allowed to be duplicate because
         * it is used for clients to identify the response of the same request id in a session
         * </pre>
         * 
         * <code>optional int64 request_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestId() {
            copyOnWrite();
            instance.clearRequestId();
            return this;
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        @java.lang.Override
        public boolean hasCreateSessionRequest() {
            return instance.hasCreateSessionRequest();
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.CreateSessionRequest getCreateSessionRequest() {
            return instance.getCreateSessionRequest();
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public Builder setCreateSessionRequest(
                im.turms.client.model.proto.request.user.CreateSessionRequest value) {
            copyOnWrite();
            instance.setCreateSessionRequest(value);
            return this;
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public Builder setCreateSessionRequest(
                im.turms.client.model.proto.request.user.CreateSessionRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateSessionRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public Builder mergeCreateSessionRequest(
                im.turms.client.model.proto.request.user.CreateSessionRequest value) {
            copyOnWrite();
            instance.mergeCreateSessionRequest(value);
            return this;
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public Builder clearCreateSessionRequest() {
            copyOnWrite();
            instance.clearCreateSessionRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        @java.lang.Override
        public boolean hasDeleteSessionRequest() {
            return instance.hasDeleteSessionRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.DeleteSessionRequest getDeleteSessionRequest() {
            return instance.getDeleteSessionRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder setDeleteSessionRequest(
                im.turms.client.model.proto.request.user.DeleteSessionRequest value) {
            copyOnWrite();
            instance.setDeleteSessionRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder setDeleteSessionRequest(
                im.turms.client.model.proto.request.user.DeleteSessionRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteSessionRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder mergeDeleteSessionRequest(
                im.turms.client.model.proto.request.user.DeleteSessionRequest value) {
            copyOnWrite();
            instance.mergeDeleteSessionRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder clearDeleteSessionRequest() {
            copyOnWrite();
            instance.clearDeleteSessionRequest();
            return this;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        @java.lang.Override
        public boolean hasQueryConversationsRequest() {
            return instance.hasQueryConversationsRequest();
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.conversation.QueryConversationsRequest getQueryConversationsRequest() {
            return instance.getQueryConversationsRequest();
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public Builder setQueryConversationsRequest(
                im.turms.client.model.proto.request.conversation.QueryConversationsRequest value) {
            copyOnWrite();
            instance.setQueryConversationsRequest(value);
            return this;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public Builder setQueryConversationsRequest(
                im.turms.client.model.proto.request.conversation.QueryConversationsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryConversationsRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public Builder mergeQueryConversationsRequest(
                im.turms.client.model.proto.request.conversation.QueryConversationsRequest value) {
            copyOnWrite();
            instance.mergeQueryConversationsRequest(value);
            return this;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public Builder clearQueryConversationsRequest() {
            copyOnWrite();
            instance.clearQueryConversationsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        @java.lang.Override
        public boolean hasUpdateConversationRequest() {
            return instance.hasUpdateConversationRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.conversation.UpdateConversationRequest getUpdateConversationRequest() {
            return instance.getUpdateConversationRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder setUpdateConversationRequest(
                im.turms.client.model.proto.request.conversation.UpdateConversationRequest value) {
            copyOnWrite();
            instance.setUpdateConversationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder setUpdateConversationRequest(
                im.turms.client.model.proto.request.conversation.UpdateConversationRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateConversationRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder mergeUpdateConversationRequest(
                im.turms.client.model.proto.request.conversation.UpdateConversationRequest value) {
            copyOnWrite();
            instance.mergeUpdateConversationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder clearUpdateConversationRequest() {
            copyOnWrite();
            instance.clearUpdateConversationRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        @java.lang.Override
        public boolean hasUpdateTypingStatusRequest() {
            return instance.hasUpdateTypingStatusRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest getUpdateTypingStatusRequest() {
            return instance.getUpdateTypingStatusRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder setUpdateTypingStatusRequest(
                im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest value) {
            copyOnWrite();
            instance.setUpdateTypingStatusRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder setUpdateTypingStatusRequest(
                im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateTypingStatusRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder mergeUpdateTypingStatusRequest(
                im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest value) {
            copyOnWrite();
            instance.mergeUpdateTypingStatusRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder clearUpdateTypingStatusRequest() {
            copyOnWrite();
            instance.clearUpdateTypingStatusRequest();
            return this;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        @java.lang.Override
        public boolean hasCreateMessageRequest() {
            return instance.hasCreateMessageRequest();
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.message.CreateMessageRequest getCreateMessageRequest() {
            return instance.getCreateMessageRequest();
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public Builder setCreateMessageRequest(
                im.turms.client.model.proto.request.message.CreateMessageRequest value) {
            copyOnWrite();
            instance.setCreateMessageRequest(value);
            return this;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public Builder setCreateMessageRequest(
                im.turms.client.model.proto.request.message.CreateMessageRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateMessageRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public Builder mergeCreateMessageRequest(
                im.turms.client.model.proto.request.message.CreateMessageRequest value) {
            copyOnWrite();
            instance.mergeCreateMessageRequest(value);
            return this;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public Builder clearCreateMessageRequest() {
            copyOnWrite();
            instance.clearCreateMessageRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        @java.lang.Override
        public boolean hasQueryMessagesRequest() {
            return instance.hasQueryMessagesRequest();
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.message.QueryMessagesRequest getQueryMessagesRequest() {
            return instance.getQueryMessagesRequest();
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder setQueryMessagesRequest(
                im.turms.client.model.proto.request.message.QueryMessagesRequest value) {
            copyOnWrite();
            instance.setQueryMessagesRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder setQueryMessagesRequest(
                im.turms.client.model.proto.request.message.QueryMessagesRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryMessagesRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder mergeQueryMessagesRequest(
                im.turms.client.model.proto.request.message.QueryMessagesRequest value) {
            copyOnWrite();
            instance.mergeQueryMessagesRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder clearQueryMessagesRequest() {
            copyOnWrite();
            instance.clearQueryMessagesRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        @java.lang.Override
        public boolean hasUpdateMessageRequest() {
            return instance.hasUpdateMessageRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.message.UpdateMessageRequest getUpdateMessageRequest() {
            return instance.getUpdateMessageRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder setUpdateMessageRequest(
                im.turms.client.model.proto.request.message.UpdateMessageRequest value) {
            copyOnWrite();
            instance.setUpdateMessageRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder setUpdateMessageRequest(
                im.turms.client.model.proto.request.message.UpdateMessageRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateMessageRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder mergeUpdateMessageRequest(
                im.turms.client.model.proto.request.message.UpdateMessageRequest value) {
            copyOnWrite();
            instance.mergeUpdateMessageRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder clearUpdateMessageRequest() {
            copyOnWrite();
            instance.clearUpdateMessageRequest();
            return this;
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupMembersRequest() {
            return instance.hasCreateGroupMembersRequest();
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest getCreateGroupMembersRequest() {
            return instance.getCreateGroupMembersRequest();
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public Builder setCreateGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest value) {
            copyOnWrite();
            instance.setCreateGroupMembersRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public Builder setCreateGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupMembersRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public Builder mergeCreateGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupMembersRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public Builder clearCreateGroupMembersRequest() {
            copyOnWrite();
            instance.clearCreateGroupMembersRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupMembersRequest() {
            return instance.hasDeleteGroupMembersRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest getDeleteGroupMembersRequest() {
            return instance.getDeleteGroupMembersRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder setDeleteGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest value) {
            copyOnWrite();
            instance.setDeleteGroupMembersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder setDeleteGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupMembersRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder mergeDeleteGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupMembersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder clearDeleteGroupMembersRequest() {
            copyOnWrite();
            instance.clearDeleteGroupMembersRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupMembersRequest() {
            return instance.hasQueryGroupMembersRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest getQueryGroupMembersRequest() {
            return instance.getQueryGroupMembersRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder setQueryGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest value) {
            copyOnWrite();
            instance.setQueryGroupMembersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder setQueryGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupMembersRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder mergeQueryGroupMembersRequest(
                im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupMembersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder clearQueryGroupMembersRequest() {
            copyOnWrite();
            instance.clearQueryGroupMembersRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        @java.lang.Override
        public boolean hasUpdateGroupMemberRequest() {
            return instance.hasUpdateGroupMemberRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest getUpdateGroupMemberRequest() {
            return instance.getUpdateGroupMemberRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder setUpdateGroupMemberRequest(
                im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest value) {
            copyOnWrite();
            instance.setUpdateGroupMemberRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder setUpdateGroupMemberRequest(
                im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateGroupMemberRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder mergeUpdateGroupMemberRequest(
                im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest value) {
            copyOnWrite();
            instance.mergeUpdateGroupMemberRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder clearUpdateGroupMemberRequest() {
            copyOnWrite();
            instance.clearUpdateGroupMemberRequest();
            return this;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        @java.lang.Override
        public boolean hasQueryUserProfilesRequest() {
            return instance.hasQueryUserProfilesRequest();
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.QueryUserProfilesRequest getQueryUserProfilesRequest() {
            return instance.getQueryUserProfilesRequest();
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public Builder setQueryUserProfilesRequest(
                im.turms.client.model.proto.request.user.QueryUserProfilesRequest value) {
            copyOnWrite();
            instance.setQueryUserProfilesRequest(value);
            return this;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public Builder setQueryUserProfilesRequest(
                im.turms.client.model.proto.request.user.QueryUserProfilesRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryUserProfilesRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public Builder mergeQueryUserProfilesRequest(
                im.turms.client.model.proto.request.user.QueryUserProfilesRequest value) {
            copyOnWrite();
            instance.mergeQueryUserProfilesRequest(value);
            return this;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public Builder clearQueryUserProfilesRequest() {
            copyOnWrite();
            instance.clearQueryUserProfilesRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        @java.lang.Override
        public boolean hasQueryNearbyUsersRequest() {
            return instance.hasQueryNearbyUsersRequest();
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.QueryNearbyUsersRequest getQueryNearbyUsersRequest() {
            return instance.getQueryNearbyUsersRequest();
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder setQueryNearbyUsersRequest(
                im.turms.client.model.proto.request.user.QueryNearbyUsersRequest value) {
            copyOnWrite();
            instance.setQueryNearbyUsersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder setQueryNearbyUsersRequest(
                im.turms.client.model.proto.request.user.QueryNearbyUsersRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryNearbyUsersRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder mergeQueryNearbyUsersRequest(
                im.turms.client.model.proto.request.user.QueryNearbyUsersRequest value) {
            copyOnWrite();
            instance.mergeQueryNearbyUsersRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder clearQueryNearbyUsersRequest() {
            copyOnWrite();
            instance.clearQueryNearbyUsersRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        @java.lang.Override
        public boolean hasQueryUserOnlineStatusesRequest() {
            return instance.hasQueryUserOnlineStatusesRequest();
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest getQueryUserOnlineStatusesRequest() {
            return instance.getQueryUserOnlineStatusesRequest();
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder setQueryUserOnlineStatusesRequest(
                im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest value) {
            copyOnWrite();
            instance.setQueryUserOnlineStatusesRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder setQueryUserOnlineStatusesRequest(
                im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryUserOnlineStatusesRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder mergeQueryUserOnlineStatusesRequest(
                im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest value) {
            copyOnWrite();
            instance.mergeQueryUserOnlineStatusesRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder clearQueryUserOnlineStatusesRequest() {
            copyOnWrite();
            instance.clearQueryUserOnlineStatusesRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        @java.lang.Override
        public boolean hasUpdateUserLocationRequest() {
            return instance.hasUpdateUserLocationRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.UpdateUserLocationRequest getUpdateUserLocationRequest() {
            return instance.getUpdateUserLocationRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder setUpdateUserLocationRequest(
                im.turms.client.model.proto.request.user.UpdateUserLocationRequest value) {
            copyOnWrite();
            instance.setUpdateUserLocationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder setUpdateUserLocationRequest(
                im.turms.client.model.proto.request.user.UpdateUserLocationRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateUserLocationRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder mergeUpdateUserLocationRequest(
                im.turms.client.model.proto.request.user.UpdateUserLocationRequest value) {
            copyOnWrite();
            instance.mergeUpdateUserLocationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder clearUpdateUserLocationRequest() {
            copyOnWrite();
            instance.clearUpdateUserLocationRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        @java.lang.Override
        public boolean hasUpdateUserOnlineStatusRequest() {
            return instance.hasUpdateUserOnlineStatusRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest getUpdateUserOnlineStatusRequest() {
            return instance.getUpdateUserOnlineStatusRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder setUpdateUserOnlineStatusRequest(
                im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest value) {
            copyOnWrite();
            instance.setUpdateUserOnlineStatusRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder setUpdateUserOnlineStatusRequest(
                im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateUserOnlineStatusRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder mergeUpdateUserOnlineStatusRequest(
                im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest value) {
            copyOnWrite();
            instance.mergeUpdateUserOnlineStatusRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder clearUpdateUserOnlineStatusRequest() {
            copyOnWrite();
            instance.clearUpdateUserOnlineStatusRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        @java.lang.Override
        public boolean hasUpdateUserRequest() {
            return instance.hasUpdateUserRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.UpdateUserRequest getUpdateUserRequest() {
            return instance.getUpdateUserRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder setUpdateUserRequest(
                im.turms.client.model.proto.request.user.UpdateUserRequest value) {
            copyOnWrite();
            instance.setUpdateUserRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder setUpdateUserRequest(
                im.turms.client.model.proto.request.user.UpdateUserRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateUserRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder mergeUpdateUserRequest(
                im.turms.client.model.proto.request.user.UpdateUserRequest value) {
            copyOnWrite();
            instance.mergeUpdateUserRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder clearUpdateUserRequest() {
            copyOnWrite();
            instance.clearUpdateUserRequest();
            return this;
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        @java.lang.Override
        public boolean hasCreateFriendRequestRequest() {
            return instance.hasCreateFriendRequestRequest();
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest getCreateFriendRequestRequest() {
            return instance.getCreateFriendRequestRequest();
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public Builder setCreateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest value) {
            copyOnWrite();
            instance.setCreateFriendRequestRequest(value);
            return this;
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public Builder setCreateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateFriendRequestRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public Builder mergeCreateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest value) {
            copyOnWrite();
            instance.mergeCreateFriendRequestRequest(value);
            return this;
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public Builder clearCreateFriendRequestRequest() {
            copyOnWrite();
            instance.clearCreateFriendRequestRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        @java.lang.Override
        public boolean hasCreateRelationshipGroupRequest() {
            return instance.hasCreateRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest getCreateRelationshipGroupRequest() {
            return instance.getCreateRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder setCreateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest value) {
            copyOnWrite();
            instance.setCreateRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder setCreateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateRelationshipGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder mergeCreateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest value) {
            copyOnWrite();
            instance.mergeCreateRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder clearCreateRelationshipGroupRequest() {
            copyOnWrite();
            instance.clearCreateRelationshipGroupRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        @java.lang.Override
        public boolean hasCreateRelationshipRequest() {
            return instance.hasCreateRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest getCreateRelationshipRequest() {
            return instance.getCreateRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder setCreateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest value) {
            copyOnWrite();
            instance.setCreateRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder setCreateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateRelationshipRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder mergeCreateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest value) {
            copyOnWrite();
            instance.mergeCreateRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder clearCreateRelationshipRequest() {
            copyOnWrite();
            instance.clearCreateRelationshipRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        @java.lang.Override
        public boolean hasDeleteRelationshipGroupRequest() {
            return instance.hasDeleteRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest getDeleteRelationshipGroupRequest() {
            return instance.getDeleteRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder setDeleteRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest value) {
            copyOnWrite();
            instance.setDeleteRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder setDeleteRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteRelationshipGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder mergeDeleteRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest value) {
            copyOnWrite();
            instance.mergeDeleteRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder clearDeleteRelationshipGroupRequest() {
            copyOnWrite();
            instance.clearDeleteRelationshipGroupRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        @java.lang.Override
        public boolean hasDeleteRelationshipRequest() {
            return instance.hasDeleteRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest getDeleteRelationshipRequest() {
            return instance.getDeleteRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder setDeleteRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest value) {
            copyOnWrite();
            instance.setDeleteRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder setDeleteRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteRelationshipRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder mergeDeleteRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest value) {
            copyOnWrite();
            instance.mergeDeleteRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder clearDeleteRelationshipRequest() {
            copyOnWrite();
            instance.clearDeleteRelationshipRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        @java.lang.Override
        public boolean hasQueryFriendRequestsRequest() {
            return instance.hasQueryFriendRequestsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest getQueryFriendRequestsRequest() {
            return instance.getQueryFriendRequestsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder setQueryFriendRequestsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest value) {
            copyOnWrite();
            instance.setQueryFriendRequestsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder setQueryFriendRequestsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryFriendRequestsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder mergeQueryFriendRequestsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest value) {
            copyOnWrite();
            instance.mergeQueryFriendRequestsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder clearQueryFriendRequestsRequest() {
            copyOnWrite();
            instance.clearQueryFriendRequestsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        @java.lang.Override
        public boolean hasQueryRelatedUserIdsRequest() {
            return instance.hasQueryRelatedUserIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest getQueryRelatedUserIdsRequest() {
            return instance.getQueryRelatedUserIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder setQueryRelatedUserIdsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest value) {
            copyOnWrite();
            instance.setQueryRelatedUserIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder setQueryRelatedUserIdsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryRelatedUserIdsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder mergeQueryRelatedUserIdsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest value) {
            copyOnWrite();
            instance.mergeQueryRelatedUserIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder clearQueryRelatedUserIdsRequest() {
            copyOnWrite();
            instance.clearQueryRelatedUserIdsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        @java.lang.Override
        public boolean hasQueryRelationshipGroupsRequest() {
            return instance.hasQueryRelationshipGroupsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest getQueryRelationshipGroupsRequest() {
            return instance.getQueryRelationshipGroupsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder setQueryRelationshipGroupsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest value) {
            copyOnWrite();
            instance.setQueryRelationshipGroupsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder setQueryRelationshipGroupsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryRelationshipGroupsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder mergeQueryRelationshipGroupsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest value) {
            copyOnWrite();
            instance.mergeQueryRelationshipGroupsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder clearQueryRelationshipGroupsRequest() {
            copyOnWrite();
            instance.clearQueryRelationshipGroupsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        @java.lang.Override
        public boolean hasQueryRelationshipsRequest() {
            return instance.hasQueryRelationshipsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest getQueryRelationshipsRequest() {
            return instance.getQueryRelationshipsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder setQueryRelationshipsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest value) {
            copyOnWrite();
            instance.setQueryRelationshipsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder setQueryRelationshipsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryRelationshipsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder mergeQueryRelationshipsRequest(
                im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest value) {
            copyOnWrite();
            instance.mergeQueryRelationshipsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder clearQueryRelationshipsRequest() {
            copyOnWrite();
            instance.clearQueryRelationshipsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        @java.lang.Override
        public boolean hasUpdateFriendRequestRequest() {
            return instance.hasUpdateFriendRequestRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest getUpdateFriendRequestRequest() {
            return instance.getUpdateFriendRequestRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder setUpdateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest value) {
            copyOnWrite();
            instance.setUpdateFriendRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder setUpdateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateFriendRequestRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder mergeUpdateFriendRequestRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest value) {
            copyOnWrite();
            instance.mergeUpdateFriendRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder clearUpdateFriendRequestRequest() {
            copyOnWrite();
            instance.clearUpdateFriendRequestRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        @java.lang.Override
        public boolean hasUpdateRelationshipGroupRequest() {
            return instance.hasUpdateRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest getUpdateRelationshipGroupRequest() {
            return instance.getUpdateRelationshipGroupRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder setUpdateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest value) {
            copyOnWrite();
            instance.setUpdateRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder setUpdateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateRelationshipGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder mergeUpdateRelationshipGroupRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest value) {
            copyOnWrite();
            instance.mergeUpdateRelationshipGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder clearUpdateRelationshipGroupRequest() {
            copyOnWrite();
            instance.clearUpdateRelationshipGroupRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        @java.lang.Override
        public boolean hasUpdateRelationshipRequest() {
            return instance.hasUpdateRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest getUpdateRelationshipRequest() {
            return instance.getUpdateRelationshipRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder setUpdateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest value) {
            copyOnWrite();
            instance.setUpdateRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder setUpdateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateRelationshipRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder mergeUpdateRelationshipRequest(
                im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest value) {
            copyOnWrite();
            instance.mergeUpdateRelationshipRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder clearUpdateRelationshipRequest() {
            copyOnWrite();
            instance.clearUpdateRelationshipRequest();
            return this;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupRequest() {
            return instance.hasCreateGroupRequest();
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.CreateGroupRequest getCreateGroupRequest() {
            return instance.getCreateGroupRequest();
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public Builder setCreateGroupRequest(
                im.turms.client.model.proto.request.group.CreateGroupRequest value) {
            copyOnWrite();
            instance.setCreateGroupRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public Builder setCreateGroupRequest(
                im.turms.client.model.proto.request.group.CreateGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public Builder mergeCreateGroupRequest(
                im.turms.client.model.proto.request.group.CreateGroupRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public Builder clearCreateGroupRequest() {
            copyOnWrite();
            instance.clearCreateGroupRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupRequest() {
            return instance.hasDeleteGroupRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.DeleteGroupRequest getDeleteGroupRequest() {
            return instance.getDeleteGroupRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder setDeleteGroupRequest(
                im.turms.client.model.proto.request.group.DeleteGroupRequest value) {
            copyOnWrite();
            instance.setDeleteGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder setDeleteGroupRequest(
                im.turms.client.model.proto.request.group.DeleteGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder mergeDeleteGroupRequest(
                im.turms.client.model.proto.request.group.DeleteGroupRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder clearDeleteGroupRequest() {
            copyOnWrite();
            instance.clearDeleteGroupRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupsRequest() {
            return instance.hasQueryGroupsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.QueryGroupsRequest getQueryGroupsRequest() {
            return instance.getQueryGroupsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder setQueryGroupsRequest(
                im.turms.client.model.proto.request.group.QueryGroupsRequest value) {
            copyOnWrite();
            instance.setQueryGroupsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder setQueryGroupsRequest(
                im.turms.client.model.proto.request.group.QueryGroupsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder mergeQueryGroupsRequest(
                im.turms.client.model.proto.request.group.QueryGroupsRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder clearQueryGroupsRequest() {
            copyOnWrite();
            instance.clearQueryGroupsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        @java.lang.Override
        public boolean hasQueryJoinedGroupIdsRequest() {
            return instance.hasQueryJoinedGroupIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest getQueryJoinedGroupIdsRequest() {
            return instance.getQueryJoinedGroupIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder setQueryJoinedGroupIdsRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest value) {
            copyOnWrite();
            instance.setQueryJoinedGroupIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder setQueryJoinedGroupIdsRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryJoinedGroupIdsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder mergeQueryJoinedGroupIdsRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest value) {
            copyOnWrite();
            instance.mergeQueryJoinedGroupIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder clearQueryJoinedGroupIdsRequest() {
            copyOnWrite();
            instance.clearQueryJoinedGroupIdsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        @java.lang.Override
        public boolean hasQueryJoinedGroupInfosRequest() {
            return instance.hasQueryJoinedGroupInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest getQueryJoinedGroupInfosRequest() {
            return instance.getQueryJoinedGroupInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder setQueryJoinedGroupInfosRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest value) {
            copyOnWrite();
            instance.setQueryJoinedGroupInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder setQueryJoinedGroupInfosRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryJoinedGroupInfosRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder mergeQueryJoinedGroupInfosRequest(
                im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest value) {
            copyOnWrite();
            instance.mergeQueryJoinedGroupInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder clearQueryJoinedGroupInfosRequest() {
            copyOnWrite();
            instance.clearQueryJoinedGroupInfosRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        @java.lang.Override
        public boolean hasUpdateGroupRequest() {
            return instance.hasUpdateGroupRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.UpdateGroupRequest getUpdateGroupRequest() {
            return instance.getUpdateGroupRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder setUpdateGroupRequest(
                im.turms.client.model.proto.request.group.UpdateGroupRequest value) {
            copyOnWrite();
            instance.setUpdateGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder setUpdateGroupRequest(
                im.turms.client.model.proto.request.group.UpdateGroupRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateGroupRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder mergeUpdateGroupRequest(
                im.turms.client.model.proto.request.group.UpdateGroupRequest value) {
            copyOnWrite();
            instance.mergeUpdateGroupRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder clearUpdateGroupRequest() {
            copyOnWrite();
            instance.clearUpdateGroupRequest();
            return this;
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupBlockedUserRequest() {
            return instance.hasCreateGroupBlockedUserRequest();
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest getCreateGroupBlockedUserRequest() {
            return instance.getCreateGroupBlockedUserRequest();
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public Builder setCreateGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
            copyOnWrite();
            instance.setCreateGroupBlockedUserRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public Builder setCreateGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupBlockedUserRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public Builder mergeCreateGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupBlockedUserRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public Builder clearCreateGroupBlockedUserRequest() {
            copyOnWrite();
            instance.clearCreateGroupBlockedUserRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupBlockedUserRequest() {
            return instance.hasDeleteGroupBlockedUserRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest getDeleteGroupBlockedUserRequest() {
            return instance.getDeleteGroupBlockedUserRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder setDeleteGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
            copyOnWrite();
            instance.setDeleteGroupBlockedUserRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder setDeleteGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupBlockedUserRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder mergeDeleteGroupBlockedUserRequest(
                im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupBlockedUserRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder clearDeleteGroupBlockedUserRequest() {
            copyOnWrite();
            instance.clearDeleteGroupBlockedUserRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupBlockedUserIdsRequest() {
            return instance.hasQueryGroupBlockedUserIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest getQueryGroupBlockedUserIdsRequest() {
            return instance.getQueryGroupBlockedUserIdsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder setQueryGroupBlockedUserIdsRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
            copyOnWrite();
            instance.setQueryGroupBlockedUserIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder setQueryGroupBlockedUserIdsRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupBlockedUserIdsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder mergeQueryGroupBlockedUserIdsRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupBlockedUserIdsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder clearQueryGroupBlockedUserIdsRequest() {
            copyOnWrite();
            instance.clearQueryGroupBlockedUserIdsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupBlockedUserInfosRequest() {
            return instance.hasQueryGroupBlockedUserInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest getQueryGroupBlockedUserInfosRequest() {
            return instance.getQueryGroupBlockedUserInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder setQueryGroupBlockedUserInfosRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
            copyOnWrite();
            instance.setQueryGroupBlockedUserInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder setQueryGroupBlockedUserInfosRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupBlockedUserInfosRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder mergeQueryGroupBlockedUserInfosRequest(
                im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupBlockedUserInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder clearQueryGroupBlockedUserInfosRequest() {
            copyOnWrite();
            instance.clearQueryGroupBlockedUserInfosRequest();
            return this;
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        @java.lang.Override
        public boolean hasCheckGroupJoinQuestionsAnswersRequest() {
            return instance.hasCheckGroupJoinQuestionsAnswersRequest();
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getCheckGroupJoinQuestionsAnswersRequest() {
            return instance.getCheckGroupJoinQuestionsAnswersRequest();
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public Builder setCheckGroupJoinQuestionsAnswersRequest(
                im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
            copyOnWrite();
            instance.setCheckGroupJoinQuestionsAnswersRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public Builder setCheckGroupJoinQuestionsAnswersRequest(
                im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCheckGroupJoinQuestionsAnswersRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public Builder mergeCheckGroupJoinQuestionsAnswersRequest(
                im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
            copyOnWrite();
            instance.mergeCheckGroupJoinQuestionsAnswersRequest(value);
            return this;
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public Builder clearCheckGroupJoinQuestionsAnswersRequest() {
            copyOnWrite();
            instance.clearCheckGroupJoinQuestionsAnswersRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupInvitationRequest() {
            return instance.hasCreateGroupInvitationRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest getCreateGroupInvitationRequest() {
            return instance.getCreateGroupInvitationRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder setCreateGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest value) {
            copyOnWrite();
            instance.setCreateGroupInvitationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder setCreateGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupInvitationRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder mergeCreateGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupInvitationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder clearCreateGroupInvitationRequest() {
            copyOnWrite();
            instance.clearCreateGroupInvitationRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupJoinRequestRequest() {
            return instance.hasCreateGroupJoinRequestRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest getCreateGroupJoinRequestRequest() {
            return instance.getCreateGroupJoinRequestRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder setCreateGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
            copyOnWrite();
            instance.setCreateGroupJoinRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder setCreateGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupJoinRequestRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder mergeCreateGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupJoinRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder clearCreateGroupJoinRequestRequest() {
            copyOnWrite();
            instance.clearCreateGroupJoinRequestRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        @java.lang.Override
        public boolean hasCreateGroupJoinQuestionsRequest() {
            return instance.hasCreateGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest getCreateGroupJoinQuestionsRequest() {
            return instance.getCreateGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder setCreateGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.setCreateGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder setCreateGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setCreateGroupJoinQuestionsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder mergeCreateGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.mergeCreateGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder clearCreateGroupJoinQuestionsRequest() {
            copyOnWrite();
            instance.clearCreateGroupJoinQuestionsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupInvitationRequest() {
            return instance.hasDeleteGroupInvitationRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest getDeleteGroupInvitationRequest() {
            return instance.getDeleteGroupInvitationRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder setDeleteGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest value) {
            copyOnWrite();
            instance.setDeleteGroupInvitationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder setDeleteGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupInvitationRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder mergeDeleteGroupInvitationRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupInvitationRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder clearDeleteGroupInvitationRequest() {
            copyOnWrite();
            instance.clearDeleteGroupInvitationRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupJoinRequestRequest() {
            return instance.hasDeleteGroupJoinRequestRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest getDeleteGroupJoinRequestRequest() {
            return instance.getDeleteGroupJoinRequestRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder setDeleteGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
            copyOnWrite();
            instance.setDeleteGroupJoinRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder setDeleteGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupJoinRequestRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder mergeDeleteGroupJoinRequestRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupJoinRequestRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder clearDeleteGroupJoinRequestRequest() {
            copyOnWrite();
            instance.clearDeleteGroupJoinRequestRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        @java.lang.Override
        public boolean hasDeleteGroupJoinQuestionsRequest() {
            return instance.hasDeleteGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDeleteGroupJoinQuestionsRequest() {
            return instance.getDeleteGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder setDeleteGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.setDeleteGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder setDeleteGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteGroupJoinQuestionsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder mergeDeleteGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.mergeDeleteGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder clearDeleteGroupJoinQuestionsRequest() {
            copyOnWrite();
            instance.clearDeleteGroupJoinQuestionsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupInvitationsRequest() {
            return instance.hasQueryGroupInvitationsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest getQueryGroupInvitationsRequest() {
            return instance.getQueryGroupInvitationsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder setQueryGroupInvitationsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest value) {
            copyOnWrite();
            instance.setQueryGroupInvitationsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder setQueryGroupInvitationsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupInvitationsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder mergeQueryGroupInvitationsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupInvitationsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder clearQueryGroupInvitationsRequest() {
            copyOnWrite();
            instance.clearQueryGroupInvitationsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupJoinRequestsRequest() {
            return instance.hasQueryGroupJoinRequestsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest getQueryGroupJoinRequestsRequest() {
            return instance.getQueryGroupJoinRequestsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder setQueryGroupJoinRequestsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
            copyOnWrite();
            instance.setQueryGroupJoinRequestsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder setQueryGroupJoinRequestsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupJoinRequestsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder mergeQueryGroupJoinRequestsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupJoinRequestsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder clearQueryGroupJoinRequestsRequest() {
            copyOnWrite();
            instance.clearQueryGroupJoinRequestsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        @java.lang.Override
        public boolean hasQueryGroupJoinQuestionsRequest() {
            return instance.hasQueryGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest getQueryGroupJoinQuestionsRequest() {
            return instance.getQueryGroupJoinQuestionsRequest();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder setQueryGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.setQueryGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder setQueryGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryGroupJoinQuestionsRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder mergeQueryGroupJoinQuestionsRequest(
                im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
            copyOnWrite();
            instance.mergeQueryGroupJoinQuestionsRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder clearQueryGroupJoinQuestionsRequest() {
            copyOnWrite();
            instance.clearQueryGroupJoinQuestionsRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        @java.lang.Override
        public boolean hasUpdateGroupJoinQuestionRequest() {
            return instance.hasUpdateGroupJoinQuestionRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest getUpdateGroupJoinQuestionRequest() {
            return instance.getUpdateGroupJoinQuestionRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder setUpdateGroupJoinQuestionRequest(
                im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
            copyOnWrite();
            instance.setUpdateGroupJoinQuestionRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder setUpdateGroupJoinQuestionRequest(
                im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateGroupJoinQuestionRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder mergeUpdateGroupJoinQuestionRequest(
                im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
            copyOnWrite();
            instance.mergeUpdateGroupJoinQuestionRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder clearUpdateGroupJoinQuestionRequest() {
            copyOnWrite();
            instance.clearUpdateGroupJoinQuestionRequest();
            return this;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        @java.lang.Override
        public boolean hasDeleteResourceRequest() {
            return instance.hasDeleteResourceRequest();
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.storage.DeleteResourceRequest getDeleteResourceRequest() {
            return instance.getDeleteResourceRequest();
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public Builder setDeleteResourceRequest(
                im.turms.client.model.proto.request.storage.DeleteResourceRequest value) {
            copyOnWrite();
            instance.setDeleteResourceRequest(value);
            return this;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public Builder setDeleteResourceRequest(
                im.turms.client.model.proto.request.storage.DeleteResourceRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setDeleteResourceRequest(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public Builder mergeDeleteResourceRequest(
                im.turms.client.model.proto.request.storage.DeleteResourceRequest value) {
            copyOnWrite();
            instance.mergeDeleteResourceRequest(value);
            return this;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public Builder clearDeleteResourceRequest() {
            copyOnWrite();
            instance.clearDeleteResourceRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        @java.lang.Override
        public boolean hasQueryResourceDownloadInfoRequest() {
            return instance.hasQueryResourceDownloadInfoRequest();
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest getQueryResourceDownloadInfoRequest() {
            return instance.getQueryResourceDownloadInfoRequest();
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder setQueryResourceDownloadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest value) {
            copyOnWrite();
            instance.setQueryResourceDownloadInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder setQueryResourceDownloadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryResourceDownloadInfoRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder mergeQueryResourceDownloadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest value) {
            copyOnWrite();
            instance.mergeQueryResourceDownloadInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder clearQueryResourceDownloadInfoRequest() {
            copyOnWrite();
            instance.clearQueryResourceDownloadInfoRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        @java.lang.Override
        public boolean hasQueryResourceUploadInfoRequest() {
            return instance.hasQueryResourceUploadInfoRequest();
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest getQueryResourceUploadInfoRequest() {
            return instance.getQueryResourceUploadInfoRequest();
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder setQueryResourceUploadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest value) {
            copyOnWrite();
            instance.setQueryResourceUploadInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder setQueryResourceUploadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryResourceUploadInfoRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder mergeQueryResourceUploadInfoRequest(
                im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest value) {
            copyOnWrite();
            instance.mergeQueryResourceUploadInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder clearQueryResourceUploadInfoRequest() {
            copyOnWrite();
            instance.clearQueryResourceUploadInfoRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        @java.lang.Override
        public boolean hasQueryMessageAttachmentInfosRequest() {
            return instance.hasQueryMessageAttachmentInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest getQueryMessageAttachmentInfosRequest() {
            return instance.getQueryMessageAttachmentInfosRequest();
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder setQueryMessageAttachmentInfosRequest(
                im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest value) {
            copyOnWrite();
            instance.setQueryMessageAttachmentInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder setQueryMessageAttachmentInfosRequest(
                im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setQueryMessageAttachmentInfosRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder mergeQueryMessageAttachmentInfosRequest(
                im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest value) {
            copyOnWrite();
            instance.mergeQueryMessageAttachmentInfosRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder clearQueryMessageAttachmentInfosRequest() {
            copyOnWrite();
            instance.clearQueryMessageAttachmentInfosRequest();
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        @java.lang.Override
        public boolean hasUpdateMessageAttachmentInfoRequest() {
            return instance.hasUpdateMessageAttachmentInfoRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest getUpdateMessageAttachmentInfoRequest() {
            return instance.getUpdateMessageAttachmentInfoRequest();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder setUpdateMessageAttachmentInfoRequest(
                im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest value) {
            copyOnWrite();
            instance.setUpdateMessageAttachmentInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder setUpdateMessageAttachmentInfoRequest(
                im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setUpdateMessageAttachmentInfoRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder mergeUpdateMessageAttachmentInfoRequest(
                im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest value) {
            copyOnWrite();
            instance.mergeUpdateMessageAttachmentInfoRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder clearUpdateMessageAttachmentInfoRequest() {
            copyOnWrite();
            instance.clearUpdateMessageAttachmentInfoRequest();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.TurmsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"kind_",
                        "kindCase_",
                        "bitField0_",
                        "requestId_",
                        im.turms.client.model.proto.request.user.CreateSessionRequest.class,
                        im.turms.client.model.proto.request.user.DeleteSessionRequest.class,
                        im.turms.client.model.proto.request.conversation.QueryConversationsRequest.class,
                        im.turms.client.model.proto.request.conversation.UpdateConversationRequest.class,
                        im.turms.client.model.proto.request.conversation.UpdateTypingStatusRequest.class,
                        im.turms.client.model.proto.request.message.CreateMessageRequest.class,
                        im.turms.client.model.proto.request.message.QueryMessagesRequest.class,
                        im.turms.client.model.proto.request.message.UpdateMessageRequest.class,
                        im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest.class,
                        im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest.class,
                        im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest.class,
                        im.turms.client.model.proto.request.group.member.UpdateGroupMemberRequest.class,
                        im.turms.client.model.proto.request.user.QueryUserProfilesRequest.class,
                        im.turms.client.model.proto.request.user.QueryNearbyUsersRequest.class,
                        im.turms.client.model.proto.request.user.QueryUserOnlineStatusesRequest.class,
                        im.turms.client.model.proto.request.user.UpdateUserLocationRequest.class,
                        im.turms.client.model.proto.request.user.UpdateUserOnlineStatusRequest.class,
                        im.turms.client.model.proto.request.user.UpdateUserRequest.class,
                        im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest.class,
                        im.turms.client.model.proto.request.user.relationship.CreateRelationshipGroupRequest.class,
                        im.turms.client.model.proto.request.user.relationship.CreateRelationshipRequest.class,
                        im.turms.client.model.proto.request.user.relationship.DeleteRelationshipGroupRequest.class,
                        im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest.class,
                        im.turms.client.model.proto.request.user.relationship.QueryFriendRequestsRequest.class,
                        im.turms.client.model.proto.request.user.relationship.QueryRelatedUserIdsRequest.class,
                        im.turms.client.model.proto.request.user.relationship.QueryRelationshipGroupsRequest.class,
                        im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest.class,
                        im.turms.client.model.proto.request.user.relationship.UpdateFriendRequestRequest.class,
                        im.turms.client.model.proto.request.user.relationship.UpdateRelationshipGroupRequest.class,
                        im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest.class,
                        im.turms.client.model.proto.request.group.CreateGroupRequest.class,
                        im.turms.client.model.proto.request.group.DeleteGroupRequest.class,
                        im.turms.client.model.proto.request.group.QueryGroupsRequest.class,
                        im.turms.client.model.proto.request.group.QueryJoinedGroupIdsRequest.class,
                        im.turms.client.model.proto.request.group.QueryJoinedGroupInfosRequest.class,
                        im.turms.client.model.proto.request.group.UpdateGroupRequest.class,
                        im.turms.client.model.proto.request.group.blocklist.CreateGroupBlockedUserRequest.class,
                        im.turms.client.model.proto.request.group.blocklist.DeleteGroupBlockedUserRequest.class,
                        im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.class,
                        im.turms.client.model.proto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.CreateGroupInvitationRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinRequestRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.CreateGroupJoinQuestionsRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.DeleteGroupInvitationRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinRequestRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.QueryGroupInvitationsRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinRequestsRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.QueryGroupJoinQuestionsRequest.class,
                        im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest.class,
                        im.turms.client.model.proto.request.storage.DeleteResourceRequest.class,
                        im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest.class,
                        im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest.class,
                        im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest.class,
                        im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest.class,};
                java.lang.String info =
                        "\u00009\u0001\u0001\u0001\u03ec9\u0000\u0000\u0000\u0001\u1002\u0000\u0003<\u0000"
                                + "\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b"
                                + "<\u0000\f<\u0000\r<\u0000\u000e<\u0000d<\u0000e<\u0000f<\u0000g<\u0000h<\u0000i<"
                                + "\u0000\u00c8<\u0000\u00c9<\u0000\u00ca<\u0000\u00cb<\u0000\u00cc<\u0000\u00cd<\u0000"
                                + "\u00ce<\u0000\u00cf<\u0000\u00d0<\u0000\u00d1<\u0000\u00d2<\u0000\u00d3<\u0000\u012c"
                                + "<\u0000\u012d<\u0000\u012e<\u0000\u012f<\u0000\u0130<\u0000\u0131<\u0000\u0190<\u0000"
                                + "\u0191<\u0000\u0192<\u0000\u0193<\u0000\u01f4<\u0000\u01f5<\u0000\u01f6<\u0000\u01f7"
                                + "<\u0000\u01f8<\u0000\u01f9<\u0000\u01fa<\u0000\u01fb<\u0000\u01fc<\u0000\u01fd<\u0000"
                                + "\u01fe<\u0000\u03e8<\u0000\u03e9<\u0000\u03ea<\u0000\u03eb<\u0000\u03ec<\u0000";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.TurmsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.TurmsRequest.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            }
            case GET_MEMOIZED_IS_INITIALIZED: {
                return (byte) 1;
            }
            case SET_MEMOIZED_IS_INITIALIZED: {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsRequest)
    private static final im.turms.client.model.proto.request.TurmsRequest DEFAULT_INSTANCE;

    static {
        TurmsRequest defaultInstance = new TurmsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(TurmsRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.TurmsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<TurmsRequest> PARSER;

    public static com.google.protobuf.Parser<TurmsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}