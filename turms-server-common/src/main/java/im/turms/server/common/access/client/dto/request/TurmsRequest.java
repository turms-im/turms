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

package im.turms.server.common.access.client.dto.request;

/**
 * <pre>
 * Client -&gt; Server -&gt; Client
 * </pre>
 * <p>
 * Protobuf type {@code im.turms.proto.TurmsRequest}
 */
public final class TurmsRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsRequest)
        TurmsRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use TurmsRequest.newBuilder() to construct.
    private TurmsRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private TurmsRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new TurmsRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.internal_static_im_turms_proto_TurmsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.TurmsRequest.class,
                        im.turms.server.common.access.client.dto.request.TurmsRequest.Builder.class);
    }

    private int bitField0_;
    private int kindCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object kind_;

    public enum KindCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
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
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static KindCase valueOf(int value) {
            return forNumber(value);
        }

        public static KindCase forNumber(int value) {
            return switch (value) {
                case 3 -> CREATE_SESSION_REQUEST;
                case 4 -> DELETE_SESSION_REQUEST;
                case 5 -> QUERY_CONVERSATIONS_REQUEST;
                case 6 -> UPDATE_CONVERSATION_REQUEST;
                case 7 -> UPDATE_TYPING_STATUS_REQUEST;
                case 8 -> CREATE_MESSAGE_REQUEST;
                case 9 -> QUERY_MESSAGES_REQUEST;
                case 10 -> UPDATE_MESSAGE_REQUEST;
                case 11 -> CREATE_GROUP_MEMBERS_REQUEST;
                case 12 -> DELETE_GROUP_MEMBERS_REQUEST;
                case 13 -> QUERY_GROUP_MEMBERS_REQUEST;
                case 14 -> UPDATE_GROUP_MEMBER_REQUEST;
                case 100 -> QUERY_USER_PROFILES_REQUEST;
                case 101 -> QUERY_NEARBY_USERS_REQUEST;
                case 102 -> QUERY_USER_ONLINE_STATUSES_REQUEST;
                case 103 -> UPDATE_USER_LOCATION_REQUEST;
                case 104 -> UPDATE_USER_ONLINE_STATUS_REQUEST;
                case 105 -> UPDATE_USER_REQUEST;
                case 200 -> CREATE_FRIEND_REQUEST_REQUEST;
                case 201 -> CREATE_RELATIONSHIP_GROUP_REQUEST;
                case 202 -> CREATE_RELATIONSHIP_REQUEST;
                case 203 -> DELETE_RELATIONSHIP_GROUP_REQUEST;
                case 204 -> DELETE_RELATIONSHIP_REQUEST;
                case 205 -> QUERY_FRIEND_REQUESTS_REQUEST;
                case 206 -> QUERY_RELATED_USER_IDS_REQUEST;
                case 207 -> QUERY_RELATIONSHIP_GROUPS_REQUEST;
                case 208 -> QUERY_RELATIONSHIPS_REQUEST;
                case 209 -> UPDATE_FRIEND_REQUEST_REQUEST;
                case 210 -> UPDATE_RELATIONSHIP_GROUP_REQUEST;
                case 211 -> UPDATE_RELATIONSHIP_REQUEST;
                case 300 -> CREATE_GROUP_REQUEST;
                case 301 -> DELETE_GROUP_REQUEST;
                case 302 -> QUERY_GROUPS_REQUEST;
                case 303 -> QUERY_JOINED_GROUP_IDS_REQUEST;
                case 304 -> QUERY_JOINED_GROUP_INFOS_REQUEST;
                case 305 -> UPDATE_GROUP_REQUEST;
                case 400 -> CREATE_GROUP_BLOCKED_USER_REQUEST;
                case 401 -> DELETE_GROUP_BLOCKED_USER_REQUEST;
                case 402 -> QUERY_GROUP_BLOCKED_USER_IDS_REQUEST;
                case 403 -> QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST;
                case 500 -> CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
                case 501 -> CREATE_GROUP_INVITATION_REQUEST;
                case 502 -> CREATE_GROUP_JOIN_REQUEST_REQUEST;
                case 503 -> CREATE_GROUP_JOIN_QUESTIONS_REQUEST;
                case 504 -> DELETE_GROUP_INVITATION_REQUEST;
                case 505 -> DELETE_GROUP_JOIN_REQUEST_REQUEST;
                case 506 -> DELETE_GROUP_JOIN_QUESTIONS_REQUEST;
                case 507 -> QUERY_GROUP_INVITATIONS_REQUEST;
                case 508 -> QUERY_GROUP_JOIN_REQUESTS_REQUEST;
                case 509 -> QUERY_GROUP_JOIN_QUESTIONS_REQUEST;
                case 510 -> UPDATE_GROUP_JOIN_QUESTION_REQUEST;
                case 1000 -> DELETE_RESOURCE_REQUEST;
                case 1001 -> QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST;
                case 1002 -> QUERY_RESOURCE_UPLOAD_INFO_REQUEST;
                case 1003 -> QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST;
                case 1004 -> UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST;
                case 0 -> KIND_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public KindCase getKindCase() {
        return KindCase.forNumber(kindCase_);
    }

    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    private long requestId_ = 0L;

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

    public static final int CREATE_SESSION_REQUEST_FIELD_NUMBER = 3;

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     *
     * @return Whether the createSessionRequest field is set.
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
     *
     * @return The createSessionRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getCreateSessionRequest() {
        if (kindCase_ == 3) {
            return (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder getCreateSessionRequestOrBuilder() {
        if (kindCase_ == 3) {
            return (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                .getDefaultInstance();
    }

    public static final int DELETE_SESSION_REQUEST_FIELD_NUMBER = 4;

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     *
     * @return Whether the deleteSessionRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteSessionRequest() {
        return kindCase_ == 4;
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     *
     * @return The deleteSessionRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest getDeleteSessionRequest() {
        if (kindCase_ == 4) {
            return (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOrBuilder getDeleteSessionRequestOrBuilder() {
        if (kindCase_ == 4) {
            return (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                .getDefaultInstance();
    }

    public static final int QUERY_CONVERSATIONS_REQUEST_FIELD_NUMBER = 5;

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     *
     * @return Whether the queryConversationsRequest field is set.
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
     *
     * @return The queryConversationsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getQueryConversationsRequest() {
        if (kindCase_ == 5) {
            return (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder getQueryConversationsRequestOrBuilder() {
        if (kindCase_ == 5) {
            return (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_CONVERSATION_REQUEST_FIELD_NUMBER = 6;

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     *
     * @return Whether the updateConversationRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateConversationRequest() {
        return kindCase_ == 6;
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     *
     * @return The updateConversationRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest getUpdateConversationRequest() {
        if (kindCase_ == 6) {
            return (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOrBuilder getUpdateConversationRequestOrBuilder() {
        if (kindCase_ == 6) {
            return (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_TYPING_STATUS_REQUEST_FIELD_NUMBER = 7;

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     *
     * @return Whether the updateTypingStatusRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateTypingStatusRequest() {
        return kindCase_ == 7;
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     *
     * @return The updateTypingStatusRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getUpdateTypingStatusRequest() {
        if (kindCase_ == 7) {
            return (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder getUpdateTypingStatusRequestOrBuilder() {
        if (kindCase_ == 7) {
            return (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                .getDefaultInstance();
    }

    public static final int CREATE_MESSAGE_REQUEST_FIELD_NUMBER = 8;

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     *
     * @return Whether the createMessageRequest field is set.
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
     *
     * @return The createMessageRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.CreateMessageRequest getCreateMessageRequest() {
        if (kindCase_ == 8) {
            return (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOrBuilder getCreateMessageRequestOrBuilder() {
        if (kindCase_ == 8) {
            return (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                .getDefaultInstance();
    }

    public static final int QUERY_MESSAGES_REQUEST_FIELD_NUMBER = 9;

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     *
     * @return Whether the queryMessagesRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryMessagesRequest() {
        return kindCase_ == 9;
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     *
     * @return The queryMessagesRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getQueryMessagesRequest() {
        if (kindCase_ == 9) {
            return (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder getQueryMessagesRequestOrBuilder() {
        if (kindCase_ == 9) {
            return (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_MESSAGE_REQUEST_FIELD_NUMBER = 10;

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     *
     * @return Whether the updateMessageRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateMessageRequest() {
        return kindCase_ == 10;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     *
     * @return The updateMessageRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getUpdateMessageRequest() {
        if (kindCase_ == 10) {
            return (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder getUpdateMessageRequestOrBuilder() {
        if (kindCase_ == 10) {
            return (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 11;

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     *
     * @return Whether the createGroupMembersRequest field is set.
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
     *
     * @return The createGroupMembersRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest getCreateGroupMembersRequest() {
        if (kindCase_ == 11) {
            return (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOrBuilder getCreateGroupMembersRequestOrBuilder() {
        if (kindCase_ == 11) {
            return (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 12;

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     *
     * @return Whether the deleteGroupMembersRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupMembersRequest() {
        return kindCase_ == 12;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     *
     * @return The deleteGroupMembersRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDeleteGroupMembersRequest() {
        if (kindCase_ == 12) {
            return (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder getDeleteGroupMembersRequestOrBuilder() {
        if (kindCase_ == 12) {
            return (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_MEMBERS_REQUEST_FIELD_NUMBER = 13;

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     *
     * @return Whether the queryGroupMembersRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupMembersRequest() {
        return kindCase_ == 13;
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     *
     * @return The queryGroupMembersRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest getQueryGroupMembersRequest() {
        if (kindCase_ == 13) {
            return (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOrBuilder getQueryGroupMembersRequestOrBuilder() {
        if (kindCase_ == 13) {
            return (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_GROUP_MEMBER_REQUEST_FIELD_NUMBER = 14;

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     *
     * @return Whether the updateGroupMemberRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateGroupMemberRequest() {
        return kindCase_ == 14;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     *
     * @return The updateGroupMemberRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest getUpdateGroupMemberRequest() {
        if (kindCase_ == 14) {
            return (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOrBuilder getUpdateGroupMemberRequestOrBuilder() {
        if (kindCase_ == 14) {
            return (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                .getDefaultInstance();
    }

    public static final int QUERY_USER_PROFILES_REQUEST_FIELD_NUMBER = 100;

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     *
     * @return Whether the queryUserProfilesRequest field is set.
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
     *
     * @return The queryUserProfilesRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getQueryUserProfilesRequest() {
        if (kindCase_ == 100) {
            return (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder getQueryUserProfilesRequestOrBuilder() {
        if (kindCase_ == 100) {
            return (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                .getDefaultInstance();
    }

    public static final int QUERY_NEARBY_USERS_REQUEST_FIELD_NUMBER = 101;

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     *
     * @return Whether the queryNearbyUsersRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryNearbyUsersRequest() {
        return kindCase_ == 101;
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     *
     * @return The queryNearbyUsersRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getQueryNearbyUsersRequest() {
        if (kindCase_ == 101) {
            return (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder getQueryNearbyUsersRequestOrBuilder() {
        if (kindCase_ == 101) {
            return (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                .getDefaultInstance();
    }

    public static final int QUERY_USER_ONLINE_STATUSES_REQUEST_FIELD_NUMBER = 102;

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     *
     * @return Whether the queryUserOnlineStatusesRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryUserOnlineStatusesRequest() {
        return kindCase_ == 102;
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     *
     * @return The queryUserOnlineStatusesRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest getQueryUserOnlineStatusesRequest() {
        if (kindCase_ == 102) {
            return (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOrBuilder getQueryUserOnlineStatusesRequestOrBuilder() {
        if (kindCase_ == 102) {
            return (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_USER_LOCATION_REQUEST_FIELD_NUMBER = 103;

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     *
     * @return Whether the updateUserLocationRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateUserLocationRequest() {
        return kindCase_ == 103;
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     *
     * @return The updateUserLocationRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest getUpdateUserLocationRequest() {
        if (kindCase_ == 103) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOrBuilder getUpdateUserLocationRequestOrBuilder() {
        if (kindCase_ == 103) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_USER_ONLINE_STATUS_REQUEST_FIELD_NUMBER = 104;

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     *
     * @return Whether the updateUserOnlineStatusRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateUserOnlineStatusRequest() {
        return kindCase_ == 104;
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     *
     * @return The updateUserOnlineStatusRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getUpdateUserOnlineStatusRequest() {
        if (kindCase_ == 104) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder getUpdateUserOnlineStatusRequestOrBuilder() {
        if (kindCase_ == 104) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_USER_REQUEST_FIELD_NUMBER = 105;

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     *
     * @return Whether the updateUserRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateUserRequest() {
        return kindCase_ == 105;
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     *
     * @return The updateUserRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserRequest getUpdateUserRequest() {
        if (kindCase_ == 105) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOrBuilder getUpdateUserRequestOrBuilder() {
        if (kindCase_ == 105) {
            return (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                .getDefaultInstance();
    }

    public static final int CREATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER = 200;

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     *
     * @return Whether the createFriendRequestRequest field is set.
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
     *
     * @return The createFriendRequestRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest getCreateFriendRequestRequest() {
        if (kindCase_ == 200) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOrBuilder getCreateFriendRequestRequestOrBuilder() {
        if (kindCase_ == 200) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                .getDefaultInstance();
    }

    public static final int CREATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 201;

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     *
     * @return Whether the createRelationshipGroupRequest field is set.
     */
    @java.lang.Override
    public boolean hasCreateRelationshipGroupRequest() {
        return kindCase_ == 201;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     *
     * @return The createRelationshipGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest getCreateRelationshipGroupRequest() {
        if (kindCase_ == 201) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOrBuilder getCreateRelationshipGroupRequestOrBuilder() {
        if (kindCase_ == 201) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                .getDefaultInstance();
    }

    public static final int CREATE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 202;

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     *
     * @return Whether the createRelationshipRequest field is set.
     */
    @java.lang.Override
    public boolean hasCreateRelationshipRequest() {
        return kindCase_ == 202;
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     *
     * @return The createRelationshipRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest getCreateRelationshipRequest() {
        if (kindCase_ == 202) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOrBuilder getCreateRelationshipRequestOrBuilder() {
        if (kindCase_ == 202) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                .getDefaultInstance();
    }

    public static final int DELETE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 203;

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     *
     * @return Whether the deleteRelationshipGroupRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteRelationshipGroupRequest() {
        return kindCase_ == 203;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     *
     * @return The deleteRelationshipGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest getDeleteRelationshipGroupRequest() {
        if (kindCase_ == 203) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOrBuilder getDeleteRelationshipGroupRequestOrBuilder() {
        if (kindCase_ == 203) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                .getDefaultInstance();
    }

    public static final int DELETE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 204;

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     *
     * @return Whether the deleteRelationshipRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteRelationshipRequest() {
        return kindCase_ == 204;
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     *
     * @return The deleteRelationshipRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest getDeleteRelationshipRequest() {
        if (kindCase_ == 204) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOrBuilder getDeleteRelationshipRequestOrBuilder() {
        if (kindCase_ == 204) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                .getDefaultInstance();
    }

    public static final int QUERY_FRIEND_REQUESTS_REQUEST_FIELD_NUMBER = 205;

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     *
     * @return Whether the queryFriendRequestsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryFriendRequestsRequest() {
        return kindCase_ == 205;
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     *
     * @return The queryFriendRequestsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest getQueryFriendRequestsRequest() {
        if (kindCase_ == 205) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOrBuilder getQueryFriendRequestsRequestOrBuilder() {
        if (kindCase_ == 205) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_RELATED_USER_IDS_REQUEST_FIELD_NUMBER = 206;

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     *
     * @return Whether the queryRelatedUserIdsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryRelatedUserIdsRequest() {
        return kindCase_ == 206;
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     *
     * @return The queryRelatedUserIdsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest getQueryRelatedUserIdsRequest() {
        if (kindCase_ == 206) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOrBuilder getQueryRelatedUserIdsRequestOrBuilder() {
        if (kindCase_ == 206) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_RELATIONSHIP_GROUPS_REQUEST_FIELD_NUMBER = 207;

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     *
     * @return Whether the queryRelationshipGroupsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryRelationshipGroupsRequest() {
        return kindCase_ == 207;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     *
     * @return The queryRelationshipGroupsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest getQueryRelationshipGroupsRequest() {
        if (kindCase_ == 207) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOrBuilder getQueryRelationshipGroupsRequestOrBuilder() {
        if (kindCase_ == 207) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_RELATIONSHIPS_REQUEST_FIELD_NUMBER = 208;

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     *
     * @return Whether the queryRelationshipsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryRelationshipsRequest() {
        return kindCase_ == 208;
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     *
     * @return The queryRelationshipsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest getQueryRelationshipsRequest() {
        if (kindCase_ == 208) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOrBuilder getQueryRelationshipsRequestOrBuilder() {
        if (kindCase_ == 208) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER = 209;

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     *
     * @return Whether the updateFriendRequestRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateFriendRequestRequest() {
        return kindCase_ == 209;
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     *
     * @return The updateFriendRequestRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest getUpdateFriendRequestRequest() {
        if (kindCase_ == 209) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOrBuilder getUpdateFriendRequestRequestOrBuilder() {
        if (kindCase_ == 209) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER = 210;

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     *
     * @return Whether the updateRelationshipGroupRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateRelationshipGroupRequest() {
        return kindCase_ == 210;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     *
     * @return The updateRelationshipGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest getUpdateRelationshipGroupRequest() {
        if (kindCase_ == 210) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder getUpdateRelationshipGroupRequestOrBuilder() {
        if (kindCase_ == 210) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_RELATIONSHIP_REQUEST_FIELD_NUMBER = 211;

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     *
     * @return Whether the updateRelationshipRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateRelationshipRequest() {
        return kindCase_ == 211;
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     *
     * @return The updateRelationshipRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getUpdateRelationshipRequest() {
        if (kindCase_ == 211) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder getUpdateRelationshipRequestOrBuilder() {
        if (kindCase_ == 211) {
            return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_REQUEST_FIELD_NUMBER = 300;

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     *
     * @return Whether the createGroupRequest field is set.
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
     *
     * @return The createGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getCreateGroupRequest() {
        if (kindCase_ == 300) {
            return (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder getCreateGroupRequestOrBuilder() {
        if (kindCase_ == 300) {
            return (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_REQUEST_FIELD_NUMBER = 301;

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     *
     * @return Whether the deleteGroupRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupRequest() {
        return kindCase_ == 301;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     *
     * @return The deleteGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest getDeleteGroupRequest() {
        if (kindCase_ == 301) {
            return (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOrBuilder getDeleteGroupRequestOrBuilder() {
        if (kindCase_ == 301) {
            return (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUPS_REQUEST_FIELD_NUMBER = 302;

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     *
     * @return Whether the queryGroupsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupsRequest() {
        return kindCase_ == 302;
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     *
     * @return The queryGroupsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest getQueryGroupsRequest() {
        if (kindCase_ == 302) {
            return (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOrBuilder getQueryGroupsRequestOrBuilder() {
        if (kindCase_ == 302) {
            return (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_JOINED_GROUP_IDS_REQUEST_FIELD_NUMBER = 303;

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     *
     * @return Whether the queryJoinedGroupIdsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryJoinedGroupIdsRequest() {
        return kindCase_ == 303;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     *
     * @return The queryJoinedGroupIdsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest getQueryJoinedGroupIdsRequest() {
        if (kindCase_ == 303) {
            return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOrBuilder getQueryJoinedGroupIdsRequestOrBuilder() {
        if (kindCase_ == 303) {
            return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_JOINED_GROUP_INFOS_REQUEST_FIELD_NUMBER = 304;

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     *
     * @return Whether the queryJoinedGroupInfosRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryJoinedGroupInfosRequest() {
        return kindCase_ == 304;
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     *
     * @return The queryJoinedGroupInfosRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest getQueryJoinedGroupInfosRequest() {
        if (kindCase_ == 304) {
            return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOrBuilder getQueryJoinedGroupInfosRequestOrBuilder() {
        if (kindCase_ == 304) {
            return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_GROUP_REQUEST_FIELD_NUMBER = 305;

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     *
     * @return Whether the updateGroupRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateGroupRequest() {
        return kindCase_ == 305;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     *
     * @return The updateGroupRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest getUpdateGroupRequest() {
        if (kindCase_ == 305) {
            return (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOrBuilder getUpdateGroupRequestOrBuilder() {
        if (kindCase_ == 305) {
            return (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER = 400;

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     *
     * @return Whether the createGroupBlockedUserRequest field is set.
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
     *
     * @return The createGroupBlockedUserRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest getCreateGroupBlockedUserRequest() {
        if (kindCase_ == 400) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOrBuilder getCreateGroupBlockedUserRequestOrBuilder() {
        if (kindCase_ == 400) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER = 401;

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     *
     * @return Whether the deleteGroupBlockedUserRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupBlockedUserRequest() {
        return kindCase_ == 401;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     *
     * @return The deleteGroupBlockedUserRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest getDeleteGroupBlockedUserRequest() {
        if (kindCase_ == 401) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOrBuilder getDeleteGroupBlockedUserRequestOrBuilder() {
        if (kindCase_ == 401) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_BLOCKED_USER_IDS_REQUEST_FIELD_NUMBER = 402;

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     *
     * @return Whether the queryGroupBlockedUserIdsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupBlockedUserIdsRequest() {
        return kindCase_ == 402;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     *
     * @return The queryGroupBlockedUserIdsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest getQueryGroupBlockedUserIdsRequest() {
        if (kindCase_ == 402) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOrBuilder getQueryGroupBlockedUserIdsRequestOrBuilder() {
        if (kindCase_ == 402) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST_FIELD_NUMBER = 403;

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     *
     * @return Whether the queryGroupBlockedUserInfosRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupBlockedUserInfosRequest() {
        return kindCase_ == 403;
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     *
     * @return The queryGroupBlockedUserInfosRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest getQueryGroupBlockedUserInfosRequest() {
        if (kindCase_ == 403) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOrBuilder getQueryGroupBlockedUserInfosRequestOrBuilder() {
        if (kindCase_ == 403) {
            return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                .getDefaultInstance();
    }

    public static final int CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST_FIELD_NUMBER = 500;

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     *
     * @return Whether the checkGroupJoinQuestionsAnswersRequest field is set.
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
     *
     * @return The checkGroupJoinQuestionsAnswersRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getCheckGroupJoinQuestionsAnswersRequest() {
        if (kindCase_ == 500) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder getCheckGroupJoinQuestionsAnswersRequestOrBuilder() {
        if (kindCase_ == 500) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_INVITATION_REQUEST_FIELD_NUMBER = 501;

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     *
     * @return Whether the createGroupInvitationRequest field is set.
     */
    @java.lang.Override
    public boolean hasCreateGroupInvitationRequest() {
        return kindCase_ == 501;
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     *
     * @return The createGroupInvitationRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest getCreateGroupInvitationRequest() {
        if (kindCase_ == 501) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOrBuilder getCreateGroupInvitationRequestOrBuilder() {
        if (kindCase_ == 501) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER = 502;

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     *
     * @return Whether the createGroupJoinRequestRequest field is set.
     */
    @java.lang.Override
    public boolean hasCreateGroupJoinRequestRequest() {
        return kindCase_ == 502;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     *
     * @return The createGroupJoinRequestRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest getCreateGroupJoinRequestRequest() {
        if (kindCase_ == 502) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOrBuilder getCreateGroupJoinRequestRequestOrBuilder() {
        if (kindCase_ == 502) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                .getDefaultInstance();
    }

    public static final int CREATE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 503;

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     *
     * @return Whether the createGroupJoinQuestionsRequest field is set.
     */
    @java.lang.Override
    public boolean hasCreateGroupJoinQuestionsRequest() {
        return kindCase_ == 503;
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     *
     * @return The createGroupJoinQuestionsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getCreateGroupJoinQuestionsRequest() {
        if (kindCase_ == 503) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder getCreateGroupJoinQuestionsRequestOrBuilder() {
        if (kindCase_ == 503) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_INVITATION_REQUEST_FIELD_NUMBER = 504;

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     *
     * @return Whether the deleteGroupInvitationRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupInvitationRequest() {
        return kindCase_ == 504;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     *
     * @return The deleteGroupInvitationRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest getDeleteGroupInvitationRequest() {
        if (kindCase_ == 504) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOrBuilder getDeleteGroupInvitationRequestOrBuilder() {
        if (kindCase_ == 504) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER = 505;

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     *
     * @return Whether the deleteGroupJoinRequestRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupJoinRequestRequest() {
        return kindCase_ == 505;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     *
     * @return The deleteGroupJoinRequestRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest getDeleteGroupJoinRequestRequest() {
        if (kindCase_ == 505) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder getDeleteGroupJoinRequestRequestOrBuilder() {
        if (kindCase_ == 505) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                .getDefaultInstance();
    }

    public static final int DELETE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 506;

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     *
     * @return Whether the deleteGroupJoinQuestionsRequest field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupJoinQuestionsRequest() {
        return kindCase_ == 506;
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     *
     * @return The deleteGroupJoinQuestionsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDeleteGroupJoinQuestionsRequest() {
        if (kindCase_ == 506) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder getDeleteGroupJoinQuestionsRequestOrBuilder() {
        if (kindCase_ == 506) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_INVITATIONS_REQUEST_FIELD_NUMBER = 507;

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     *
     * @return Whether the queryGroupInvitationsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupInvitationsRequest() {
        return kindCase_ == 507;
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     *
     * @return The queryGroupInvitationsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest getQueryGroupInvitationsRequest() {
        if (kindCase_ == 507) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOrBuilder getQueryGroupInvitationsRequestOrBuilder() {
        if (kindCase_ == 507) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_JOIN_REQUESTS_REQUEST_FIELD_NUMBER = 508;

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     *
     * @return Whether the queryGroupJoinRequestsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupJoinRequestsRequest() {
        return kindCase_ == 508;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     *
     * @return The queryGroupJoinRequestsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest getQueryGroupJoinRequestsRequest() {
        if (kindCase_ == 508) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOrBuilder getQueryGroupJoinRequestsRequestOrBuilder() {
        if (kindCase_ == 508) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                .getDefaultInstance();
    }

    public static final int QUERY_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER = 509;

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     *
     * @return Whether the queryGroupJoinQuestionsRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryGroupJoinQuestionsRequest() {
        return kindCase_ == 509;
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     *
     * @return The queryGroupJoinQuestionsRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest getQueryGroupJoinQuestionsRequest() {
        if (kindCase_ == 509) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOrBuilder getQueryGroupJoinQuestionsRequestOrBuilder() {
        if (kindCase_ == 509) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_GROUP_JOIN_QUESTION_REQUEST_FIELD_NUMBER = 510;

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     *
     * @return Whether the updateGroupJoinQuestionRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateGroupJoinQuestionRequest() {
        return kindCase_ == 510;
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     *
     * @return The updateGroupJoinQuestionRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getUpdateGroupJoinQuestionRequest() {
        if (kindCase_ == 510) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder getUpdateGroupJoinQuestionRequestOrBuilder() {
        if (kindCase_ == 510) {
            return (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                .getDefaultInstance();
    }

    public static final int DELETE_RESOURCE_REQUEST_FIELD_NUMBER = 1000;

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     *
     * @return Whether the deleteResourceRequest field is set.
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
     *
     * @return The deleteResourceRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest getDeleteResourceRequest() {
        if (kindCase_ == 1000) {
            return (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                .getDefaultInstance();
    }

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOrBuilder getDeleteResourceRequestOrBuilder() {
        if (kindCase_ == 1000) {
            return (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                .getDefaultInstance();
    }

    public static final int QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST_FIELD_NUMBER = 1001;

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     *
     * @return Whether the queryResourceDownloadInfoRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryResourceDownloadInfoRequest() {
        return kindCase_ == 1001;
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     *
     * @return The queryResourceDownloadInfoRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest getQueryResourceDownloadInfoRequest() {
        if (kindCase_ == 1001) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOrBuilder getQueryResourceDownloadInfoRequestOrBuilder() {
        if (kindCase_ == 1001) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                .getDefaultInstance();
    }

    public static final int QUERY_RESOURCE_UPLOAD_INFO_REQUEST_FIELD_NUMBER = 1002;

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     *
     * @return Whether the queryResourceUploadInfoRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryResourceUploadInfoRequest() {
        return kindCase_ == 1002;
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     *
     * @return The queryResourceUploadInfoRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getQueryResourceUploadInfoRequest() {
        if (kindCase_ == 1002) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder getQueryResourceUploadInfoRequestOrBuilder() {
        if (kindCase_ == 1002) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                .getDefaultInstance();
    }

    public static final int QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST_FIELD_NUMBER = 1003;

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     *
     * @return Whether the queryMessageAttachmentInfosRequest field is set.
     */
    @java.lang.Override
    public boolean hasQueryMessageAttachmentInfosRequest() {
        return kindCase_ == 1003;
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     *
     * @return The queryMessageAttachmentInfosRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getQueryMessageAttachmentInfosRequest() {
        if (kindCase_ == 1003) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder getQueryMessageAttachmentInfosRequestOrBuilder() {
        if (kindCase_ == 1003) {
            return (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                .getDefaultInstance();
    }

    public static final int UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST_FIELD_NUMBER = 1004;

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     *
     * @return Whether the updateMessageAttachmentInfoRequest field is set.
     */
    @java.lang.Override
    public boolean hasUpdateMessageAttachmentInfoRequest() {
        return kindCase_ == 1004;
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     *
     * @return The updateMessageAttachmentInfoRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getUpdateMessageAttachmentInfoRequest() {
        if (kindCase_ == 1004) {
            return (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                .getDefaultInstance();
    }

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder getUpdateMessageAttachmentInfoRequestOrBuilder() {
        if (kindCase_ == 1004) {
            return (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_;
        }
        return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                .getDefaultInstance();
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, requestId_);
        }
        if (kindCase_ == 3) {
            output.writeMessage(3,
                    (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_);
        }
        if (kindCase_ == 4) {
            output.writeMessage(4,
                    (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_);
        }
        if (kindCase_ == 5) {
            output.writeMessage(5,
                    (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_);
        }
        if (kindCase_ == 6) {
            output.writeMessage(6,
                    (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_);
        }
        if (kindCase_ == 7) {
            output.writeMessage(7,
                    (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_);
        }
        if (kindCase_ == 8) {
            output.writeMessage(8,
                    (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_);
        }
        if (kindCase_ == 9) {
            output.writeMessage(9,
                    (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_);
        }
        if (kindCase_ == 10) {
            output.writeMessage(10,
                    (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_);
        }
        if (kindCase_ == 11) {
            output.writeMessage(11,
                    (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_);
        }
        if (kindCase_ == 12) {
            output.writeMessage(12,
                    (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_);
        }
        if (kindCase_ == 13) {
            output.writeMessage(13,
                    (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_);
        }
        if (kindCase_ == 14) {
            output.writeMessage(14,
                    (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_);
        }
        if (kindCase_ == 100) {
            output.writeMessage(100,
                    (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_);
        }
        if (kindCase_ == 101) {
            output.writeMessage(101,
                    (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_);
        }
        if (kindCase_ == 102) {
            output.writeMessage(102,
                    (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_);
        }
        if (kindCase_ == 103) {
            output.writeMessage(103,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_);
        }
        if (kindCase_ == 104) {
            output.writeMessage(104,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_);
        }
        if (kindCase_ == 105) {
            output.writeMessage(105,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_);
        }
        if (kindCase_ == 200) {
            output.writeMessage(200,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_);
        }
        if (kindCase_ == 201) {
            output.writeMessage(201,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 202) {
            output.writeMessage(202,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_);
        }
        if (kindCase_ == 203) {
            output.writeMessage(203,
                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 204) {
            output.writeMessage(204,
                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_);
        }
        if (kindCase_ == 205) {
            output.writeMessage(205,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_);
        }
        if (kindCase_ == 206) {
            output.writeMessage(206,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_);
        }
        if (kindCase_ == 207) {
            output.writeMessage(207,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_);
        }
        if (kindCase_ == 208) {
            output.writeMessage(208,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_);
        }
        if (kindCase_ == 209) {
            output.writeMessage(209,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_);
        }
        if (kindCase_ == 210) {
            output.writeMessage(210,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 211) {
            output.writeMessage(211,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_);
        }
        if (kindCase_ == 300) {
            output.writeMessage(300,
                    (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_);
        }
        if (kindCase_ == 301) {
            output.writeMessage(301,
                    (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_);
        }
        if (kindCase_ == 302) {
            output.writeMessage(302,
                    (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_);
        }
        if (kindCase_ == 303) {
            output.writeMessage(303,
                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_);
        }
        if (kindCase_ == 304) {
            output.writeMessage(304,
                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_);
        }
        if (kindCase_ == 305) {
            output.writeMessage(305,
                    (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_);
        }
        if (kindCase_ == 400) {
            output.writeMessage(400,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_);
        }
        if (kindCase_ == 401) {
            output.writeMessage(401,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_);
        }
        if (kindCase_ == 402) {
            output.writeMessage(402,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_);
        }
        if (kindCase_ == 403) {
            output.writeMessage(403,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_);
        }
        if (kindCase_ == 500) {
            output.writeMessage(500,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_);
        }
        if (kindCase_ == 501) {
            output.writeMessage(501,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_);
        }
        if (kindCase_ == 502) {
            output.writeMessage(502,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_);
        }
        if (kindCase_ == 503) {
            output.writeMessage(503,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 504) {
            output.writeMessage(504,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_);
        }
        if (kindCase_ == 505) {
            output.writeMessage(505,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_);
        }
        if (kindCase_ == 506) {
            output.writeMessage(506,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 507) {
            output.writeMessage(507,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_);
        }
        if (kindCase_ == 508) {
            output.writeMessage(508,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_);
        }
        if (kindCase_ == 509) {
            output.writeMessage(509,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 510) {
            output.writeMessage(510,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_);
        }
        if (kindCase_ == 1000) {
            output.writeMessage(1000,
                    (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_);
        }
        if (kindCase_ == 1001) {
            output.writeMessage(1001,
                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_);
        }
        if (kindCase_ == 1002) {
            output.writeMessage(1002,
                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_);
        }
        if (kindCase_ == 1003) {
            output.writeMessage(1003,
                    (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_);
        }
        if (kindCase_ == 1004) {
            output.writeMessage(1004,
                    (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_);
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, requestId_);
        }
        if (kindCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3,
                    (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_);
        }
        if (kindCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_);
        }
        if (kindCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                    (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_);
        }
        if (kindCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_);
        }
        if (kindCase_ == 7) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
                    (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_);
        }
        if (kindCase_ == 8) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                    (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_);
        }
        if (kindCase_ == 9) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                    (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_);
        }
        if (kindCase_ == 10) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10,
                    (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_);
        }
        if (kindCase_ == 11) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(11,
                    (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_);
        }
        if (kindCase_ == 12) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(12,
                    (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_);
        }
        if (kindCase_ == 13) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(13,
                    (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_);
        }
        if (kindCase_ == 14) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(14,
                    (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_);
        }
        if (kindCase_ == 100) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(100,
                    (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_);
        }
        if (kindCase_ == 101) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(101,
                    (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_);
        }
        if (kindCase_ == 102) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(102,
                    (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_);
        }
        if (kindCase_ == 103) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(103,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_);
        }
        if (kindCase_ == 104) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(104,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_);
        }
        if (kindCase_ == 105) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(105,
                    (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_);
        }
        if (kindCase_ == 200) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(200,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_);
        }
        if (kindCase_ == 201) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(201,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 202) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(202,
                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_);
        }
        if (kindCase_ == 203) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(203,
                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 204) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(204,
                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_);
        }
        if (kindCase_ == 205) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(205,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_);
        }
        if (kindCase_ == 206) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(206,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_);
        }
        if (kindCase_ == 207) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(207,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_);
        }
        if (kindCase_ == 208) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(208,
                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_);
        }
        if (kindCase_ == 209) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(209,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_);
        }
        if (kindCase_ == 210) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(210,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_);
        }
        if (kindCase_ == 211) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(211,
                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_);
        }
        if (kindCase_ == 300) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(300,
                    (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_);
        }
        if (kindCase_ == 301) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(301,
                    (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_);
        }
        if (kindCase_ == 302) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(302,
                    (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_);
        }
        if (kindCase_ == 303) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(303,
                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_);
        }
        if (kindCase_ == 304) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(304,
                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_);
        }
        if (kindCase_ == 305) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(305,
                    (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_);
        }
        if (kindCase_ == 400) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(400,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_);
        }
        if (kindCase_ == 401) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(401,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_);
        }
        if (kindCase_ == 402) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(402,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_);
        }
        if (kindCase_ == 403) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(403,
                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_);
        }
        if (kindCase_ == 500) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(500,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_);
        }
        if (kindCase_ == 501) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(501,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_);
        }
        if (kindCase_ == 502) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(502,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_);
        }
        if (kindCase_ == 503) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(503,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 504) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(504,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_);
        }
        if (kindCase_ == 505) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(505,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_);
        }
        if (kindCase_ == 506) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(506,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 507) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(507,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_);
        }
        if (kindCase_ == 508) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(508,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_);
        }
        if (kindCase_ == 509) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(509,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_);
        }
        if (kindCase_ == 510) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(510,
                    (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_);
        }
        if (kindCase_ == 1000) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1000,
                    (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_);
        }
        if (kindCase_ == 1001) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1001,
                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_);
        }
        if (kindCase_ == 1002) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1002,
                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_);
        }
        if (kindCase_ == 1003) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1003,
                    (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_);
        }
        if (kindCase_ == 1004) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1004,
                    (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TurmsRequest other)) {
            return super.equals(obj);
        }

        if (hasRequestId() != other.hasRequestId()) {
            return false;
        }
        if (hasRequestId()) {
            if (getRequestId() != other.getRequestId()) {
                return false;
            }
        }
        if (!getKindCase().equals(other.getKindCase())) {
            return false;
        }
        switch (kindCase_) {
            case 3 -> {
                if (!getCreateSessionRequest().equals(other.getCreateSessionRequest())) {
                    return false;
                }
            }
            case 4 -> {
                if (!getDeleteSessionRequest().equals(other.getDeleteSessionRequest())) {
                    return false;
                }
            }
            case 5 -> {
                if (!getQueryConversationsRequest().equals(other.getQueryConversationsRequest())) {
                    return false;
                }
            }
            case 6 -> {
                if (!getUpdateConversationRequest().equals(other.getUpdateConversationRequest())) {
                    return false;
                }
            }
            case 7 -> {
                if (!getUpdateTypingStatusRequest().equals(other.getUpdateTypingStatusRequest())) {
                    return false;
                }
            }
            case 8 -> {
                if (!getCreateMessageRequest().equals(other.getCreateMessageRequest())) {
                    return false;
                }
            }
            case 9 -> {
                if (!getQueryMessagesRequest().equals(other.getQueryMessagesRequest())) {
                    return false;
                }
            }
            case 10 -> {
                if (!getUpdateMessageRequest().equals(other.getUpdateMessageRequest())) {
                    return false;
                }
            }
            case 11 -> {
                if (!getCreateGroupMembersRequest().equals(other.getCreateGroupMembersRequest())) {
                    return false;
                }
            }
            case 12 -> {
                if (!getDeleteGroupMembersRequest().equals(other.getDeleteGroupMembersRequest())) {
                    return false;
                }
            }
            case 13 -> {
                if (!getQueryGroupMembersRequest().equals(other.getQueryGroupMembersRequest())) {
                    return false;
                }
            }
            case 14 -> {
                if (!getUpdateGroupMemberRequest().equals(other.getUpdateGroupMemberRequest())) {
                    return false;
                }
            }
            case 100 -> {
                if (!getQueryUserProfilesRequest().equals(other.getQueryUserProfilesRequest())) {
                    return false;
                }
            }
            case 101 -> {
                if (!getQueryNearbyUsersRequest().equals(other.getQueryNearbyUsersRequest())) {
                    return false;
                }
            }
            case 102 -> {
                if (!getQueryUserOnlineStatusesRequest()
                        .equals(other.getQueryUserOnlineStatusesRequest())) {
                    return false;
                }
            }
            case 103 -> {
                if (!getUpdateUserLocationRequest().equals(other.getUpdateUserLocationRequest())) {
                    return false;
                }
            }
            case 104 -> {
                if (!getUpdateUserOnlineStatusRequest()
                        .equals(other.getUpdateUserOnlineStatusRequest())) {
                    return false;
                }
            }
            case 105 -> {
                if (!getUpdateUserRequest().equals(other.getUpdateUserRequest())) {
                    return false;
                }
            }
            case 200 -> {
                if (!getCreateFriendRequestRequest()
                        .equals(other.getCreateFriendRequestRequest())) {
                    return false;
                }
            }
            case 201 -> {
                if (!getCreateRelationshipGroupRequest()
                        .equals(other.getCreateRelationshipGroupRequest())) {
                    return false;
                }
            }
            case 202 -> {
                if (!getCreateRelationshipRequest().equals(other.getCreateRelationshipRequest())) {
                    return false;
                }
            }
            case 203 -> {
                if (!getDeleteRelationshipGroupRequest()
                        .equals(other.getDeleteRelationshipGroupRequest())) {
                    return false;
                }
            }
            case 204 -> {
                if (!getDeleteRelationshipRequest().equals(other.getDeleteRelationshipRequest())) {
                    return false;
                }
            }
            case 205 -> {
                if (!getQueryFriendRequestsRequest()
                        .equals(other.getQueryFriendRequestsRequest())) {
                    return false;
                }
            }
            case 206 -> {
                if (!getQueryRelatedUserIdsRequest()
                        .equals(other.getQueryRelatedUserIdsRequest())) {
                    return false;
                }
            }
            case 207 -> {
                if (!getQueryRelationshipGroupsRequest()
                        .equals(other.getQueryRelationshipGroupsRequest())) {
                    return false;
                }
            }
            case 208 -> {
                if (!getQueryRelationshipsRequest().equals(other.getQueryRelationshipsRequest())) {
                    return false;
                }
            }
            case 209 -> {
                if (!getUpdateFriendRequestRequest()
                        .equals(other.getUpdateFriendRequestRequest())) {
                    return false;
                }
            }
            case 210 -> {
                if (!getUpdateRelationshipGroupRequest()
                        .equals(other.getUpdateRelationshipGroupRequest())) {
                    return false;
                }
            }
            case 211 -> {
                if (!getUpdateRelationshipRequest().equals(other.getUpdateRelationshipRequest())) {
                    return false;
                }
            }
            case 300 -> {
                if (!getCreateGroupRequest().equals(other.getCreateGroupRequest())) {
                    return false;
                }
            }
            case 301 -> {
                if (!getDeleteGroupRequest().equals(other.getDeleteGroupRequest())) {
                    return false;
                }
            }
            case 302 -> {
                if (!getQueryGroupsRequest().equals(other.getQueryGroupsRequest())) {
                    return false;
                }
            }
            case 303 -> {
                if (!getQueryJoinedGroupIdsRequest()
                        .equals(other.getQueryJoinedGroupIdsRequest())) {
                    return false;
                }
            }
            case 304 -> {
                if (!getQueryJoinedGroupInfosRequest()
                        .equals(other.getQueryJoinedGroupInfosRequest())) {
                    return false;
                }
            }
            case 305 -> {
                if (!getUpdateGroupRequest().equals(other.getUpdateGroupRequest())) {
                    return false;
                }
            }
            case 400 -> {
                if (!getCreateGroupBlockedUserRequest()
                        .equals(other.getCreateGroupBlockedUserRequest())) {
                    return false;
                }
            }
            case 401 -> {
                if (!getDeleteGroupBlockedUserRequest()
                        .equals(other.getDeleteGroupBlockedUserRequest())) {
                    return false;
                }
            }
            case 402 -> {
                if (!getQueryGroupBlockedUserIdsRequest()
                        .equals(other.getQueryGroupBlockedUserIdsRequest())) {
                    return false;
                }
            }
            case 403 -> {
                if (!getQueryGroupBlockedUserInfosRequest()
                        .equals(other.getQueryGroupBlockedUserInfosRequest())) {
                    return false;
                }
            }
            case 500 -> {
                if (!getCheckGroupJoinQuestionsAnswersRequest()
                        .equals(other.getCheckGroupJoinQuestionsAnswersRequest())) {
                    return false;
                }
            }
            case 501 -> {
                if (!getCreateGroupInvitationRequest()
                        .equals(other.getCreateGroupInvitationRequest())) {
                    return false;
                }
            }
            case 502 -> {
                if (!getCreateGroupJoinRequestRequest()
                        .equals(other.getCreateGroupJoinRequestRequest())) {
                    return false;
                }
            }
            case 503 -> {
                if (!getCreateGroupJoinQuestionsRequest()
                        .equals(other.getCreateGroupJoinQuestionsRequest())) {
                    return false;
                }
            }
            case 504 -> {
                if (!getDeleteGroupInvitationRequest()
                        .equals(other.getDeleteGroupInvitationRequest())) {
                    return false;
                }
            }
            case 505 -> {
                if (!getDeleteGroupJoinRequestRequest()
                        .equals(other.getDeleteGroupJoinRequestRequest())) {
                    return false;
                }
            }
            case 506 -> {
                if (!getDeleteGroupJoinQuestionsRequest()
                        .equals(other.getDeleteGroupJoinQuestionsRequest())) {
                    return false;
                }
            }
            case 507 -> {
                if (!getQueryGroupInvitationsRequest()
                        .equals(other.getQueryGroupInvitationsRequest())) {
                    return false;
                }
            }
            case 508 -> {
                if (!getQueryGroupJoinRequestsRequest()
                        .equals(other.getQueryGroupJoinRequestsRequest())) {
                    return false;
                }
            }
            case 509 -> {
                if (!getQueryGroupJoinQuestionsRequest()
                        .equals(other.getQueryGroupJoinQuestionsRequest())) {
                    return false;
                }
            }
            case 510 -> {
                if (!getUpdateGroupJoinQuestionRequest()
                        .equals(other.getUpdateGroupJoinQuestionRequest())) {
                    return false;
                }
            }
            case 1000 -> {
                if (!getDeleteResourceRequest().equals(other.getDeleteResourceRequest())) {
                    return false;
                }
            }
            case 1001 -> {
                if (!getQueryResourceDownloadInfoRequest()
                        .equals(other.getQueryResourceDownloadInfoRequest())) {
                    return false;
                }
            }
            case 1002 -> {
                if (!getQueryResourceUploadInfoRequest()
                        .equals(other.getQueryResourceUploadInfoRequest())) {
                    return false;
                }
            }
            case 1003 -> {
                if (!getQueryMessageAttachmentInfosRequest()
                        .equals(other.getQueryMessageAttachmentInfosRequest())) {
                    return false;
                }
            }
            case 1004 -> {
                if (!getUpdateMessageAttachmentInfoRequest()
                        .equals(other.getUpdateMessageAttachmentInfoRequest())) {
                    return false;
                }
            }
            case 0 -> {
            }
            default -> {
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (hasRequestId()) {
            hash = (37 * hash) + REQUEST_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRequestId());
        }
        switch (kindCase_) {
            case 3 -> {
                hash = (37 * hash) + CREATE_SESSION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateSessionRequest().hashCode();
            }
            case 4 -> {
                hash = (37 * hash) + DELETE_SESSION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteSessionRequest().hashCode();
            }
            case 5 -> {
                hash = (37 * hash) + QUERY_CONVERSATIONS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryConversationsRequest().hashCode();
            }
            case 6 -> {
                hash = (37 * hash) + UPDATE_CONVERSATION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateConversationRequest().hashCode();
            }
            case 7 -> {
                hash = (37 * hash) + UPDATE_TYPING_STATUS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateTypingStatusRequest().hashCode();
            }
            case 8 -> {
                hash = (37 * hash) + CREATE_MESSAGE_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateMessageRequest().hashCode();
            }
            case 9 -> {
                hash = (37 * hash) + QUERY_MESSAGES_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryMessagesRequest().hashCode();
            }
            case 10 -> {
                hash = (37 * hash) + UPDATE_MESSAGE_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateMessageRequest().hashCode();
            }
            case 11 -> {
                hash = (37 * hash) + CREATE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupMembersRequest().hashCode();
            }
            case 12 -> {
                hash = (37 * hash) + DELETE_GROUP_MEMBERS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupMembersRequest().hashCode();
            }
            case 13 -> {
                hash = (37 * hash) + QUERY_GROUP_MEMBERS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupMembersRequest().hashCode();
            }
            case 14 -> {
                hash = (37 * hash) + UPDATE_GROUP_MEMBER_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateGroupMemberRequest().hashCode();
            }
            case 100 -> {
                hash = (37 * hash) + QUERY_USER_PROFILES_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryUserProfilesRequest().hashCode();
            }
            case 101 -> {
                hash = (37 * hash) + QUERY_NEARBY_USERS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryNearbyUsersRequest().hashCode();
            }
            case 102 -> {
                hash = (37 * hash) + QUERY_USER_ONLINE_STATUSES_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryUserOnlineStatusesRequest().hashCode();
            }
            case 103 -> {
                hash = (37 * hash) + UPDATE_USER_LOCATION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateUserLocationRequest().hashCode();
            }
            case 104 -> {
                hash = (37 * hash) + UPDATE_USER_ONLINE_STATUS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateUserOnlineStatusRequest().hashCode();
            }
            case 105 -> {
                hash = (37 * hash) + UPDATE_USER_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateUserRequest().hashCode();
            }
            case 200 -> {
                hash = (37 * hash) + CREATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateFriendRequestRequest().hashCode();
            }
            case 201 -> {
                hash = (37 * hash) + CREATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateRelationshipGroupRequest().hashCode();
            }
            case 202 -> {
                hash = (37 * hash) + CREATE_RELATIONSHIP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateRelationshipRequest().hashCode();
            }
            case 203 -> {
                hash = (37 * hash) + DELETE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteRelationshipGroupRequest().hashCode();
            }
            case 204 -> {
                hash = (37 * hash) + DELETE_RELATIONSHIP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteRelationshipRequest().hashCode();
            }
            case 205 -> {
                hash = (37 * hash) + QUERY_FRIEND_REQUESTS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryFriendRequestsRequest().hashCode();
            }
            case 206 -> {
                hash = (37 * hash) + QUERY_RELATED_USER_IDS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryRelatedUserIdsRequest().hashCode();
            }
            case 207 -> {
                hash = (37 * hash) + QUERY_RELATIONSHIP_GROUPS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryRelationshipGroupsRequest().hashCode();
            }
            case 208 -> {
                hash = (37 * hash) + QUERY_RELATIONSHIPS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryRelationshipsRequest().hashCode();
            }
            case 209 -> {
                hash = (37 * hash) + UPDATE_FRIEND_REQUEST_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateFriendRequestRequest().hashCode();
            }
            case 210 -> {
                hash = (37 * hash) + UPDATE_RELATIONSHIP_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateRelationshipGroupRequest().hashCode();
            }
            case 211 -> {
                hash = (37 * hash) + UPDATE_RELATIONSHIP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateRelationshipRequest().hashCode();
            }
            case 300 -> {
                hash = (37 * hash) + CREATE_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupRequest().hashCode();
            }
            case 301 -> {
                hash = (37 * hash) + DELETE_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupRequest().hashCode();
            }
            case 302 -> {
                hash = (37 * hash) + QUERY_GROUPS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupsRequest().hashCode();
            }
            case 303 -> {
                hash = (37 * hash) + QUERY_JOINED_GROUP_IDS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryJoinedGroupIdsRequest().hashCode();
            }
            case 304 -> {
                hash = (37 * hash) + QUERY_JOINED_GROUP_INFOS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryJoinedGroupInfosRequest().hashCode();
            }
            case 305 -> {
                hash = (37 * hash) + UPDATE_GROUP_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateGroupRequest().hashCode();
            }
            case 400 -> {
                hash = (37 * hash) + CREATE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupBlockedUserRequest().hashCode();
            }
            case 401 -> {
                hash = (37 * hash) + DELETE_GROUP_BLOCKED_USER_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupBlockedUserRequest().hashCode();
            }
            case 402 -> {
                hash = (37 * hash) + QUERY_GROUP_BLOCKED_USER_IDS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupBlockedUserIdsRequest().hashCode();
            }
            case 403 -> {
                hash = (37 * hash) + QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupBlockedUserInfosRequest().hashCode();
            }
            case 500 -> {
                hash = (37 * hash) + CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCheckGroupJoinQuestionsAnswersRequest().hashCode();
            }
            case 501 -> {
                hash = (37 * hash) + CREATE_GROUP_INVITATION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupInvitationRequest().hashCode();
            }
            case 502 -> {
                hash = (37 * hash) + CREATE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupJoinRequestRequest().hashCode();
            }
            case 503 -> {
                hash = (37 * hash) + CREATE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getCreateGroupJoinQuestionsRequest().hashCode();
            }
            case 504 -> {
                hash = (37 * hash) + DELETE_GROUP_INVITATION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupInvitationRequest().hashCode();
            }
            case 505 -> {
                hash = (37 * hash) + DELETE_GROUP_JOIN_REQUEST_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupJoinRequestRequest().hashCode();
            }
            case 506 -> {
                hash = (37 * hash) + DELETE_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteGroupJoinQuestionsRequest().hashCode();
            }
            case 507 -> {
                hash = (37 * hash) + QUERY_GROUP_INVITATIONS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupInvitationsRequest().hashCode();
            }
            case 508 -> {
                hash = (37 * hash) + QUERY_GROUP_JOIN_REQUESTS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupJoinRequestsRequest().hashCode();
            }
            case 509 -> {
                hash = (37 * hash) + QUERY_GROUP_JOIN_QUESTIONS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryGroupJoinQuestionsRequest().hashCode();
            }
            case 510 -> {
                hash = (37 * hash) + UPDATE_GROUP_JOIN_QUESTION_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateGroupJoinQuestionRequest().hashCode();
            }
            case 1000 -> {
                hash = (37 * hash) + DELETE_RESOURCE_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getDeleteResourceRequest().hashCode();
            }
            case 1001 -> {
                hash = (37 * hash) + QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryResourceDownloadInfoRequest().hashCode();
            }
            case 1002 -> {
                hash = (37 * hash) + QUERY_RESOURCE_UPLOAD_INFO_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryResourceUploadInfoRequest().hashCode();
            }
            case 1003 -> {
                hash = (37 * hash) + QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getQueryMessageAttachmentInfosRequest().hashCode();
            }
            case 1004 -> {
                hash = (37 * hash) + UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST_FIELD_NUMBER;
                hash = (53 * hash) + getUpdateMessageAttachmentInfoRequest().hashCode();
            }
            case 0 -> {
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
            im.turms.server.common.access.client.dto.request.TurmsRequest prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * <pre>
     * Client -&gt; Server -&gt; Client
     * </pre>
     * <p>
     * Protobuf type {@code im.turms.proto.TurmsRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsRequest)
            im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.internal_static_im_turms_proto_TurmsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.TurmsRequest.class,
                            im.turms.server.common.access.client.dto.request.TurmsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.TurmsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            bitField1_ = 0;
            requestId_ = 0L;
            if (createSessionRequestBuilder_ != null) {
                createSessionRequestBuilder_.clear();
            }
            if (deleteSessionRequestBuilder_ != null) {
                deleteSessionRequestBuilder_.clear();
            }
            if (queryConversationsRequestBuilder_ != null) {
                queryConversationsRequestBuilder_.clear();
            }
            if (updateConversationRequestBuilder_ != null) {
                updateConversationRequestBuilder_.clear();
            }
            if (updateTypingStatusRequestBuilder_ != null) {
                updateTypingStatusRequestBuilder_.clear();
            }
            if (createMessageRequestBuilder_ != null) {
                createMessageRequestBuilder_.clear();
            }
            if (queryMessagesRequestBuilder_ != null) {
                queryMessagesRequestBuilder_.clear();
            }
            if (updateMessageRequestBuilder_ != null) {
                updateMessageRequestBuilder_.clear();
            }
            if (createGroupMembersRequestBuilder_ != null) {
                createGroupMembersRequestBuilder_.clear();
            }
            if (deleteGroupMembersRequestBuilder_ != null) {
                deleteGroupMembersRequestBuilder_.clear();
            }
            if (queryGroupMembersRequestBuilder_ != null) {
                queryGroupMembersRequestBuilder_.clear();
            }
            if (updateGroupMemberRequestBuilder_ != null) {
                updateGroupMemberRequestBuilder_.clear();
            }
            if (queryUserProfilesRequestBuilder_ != null) {
                queryUserProfilesRequestBuilder_.clear();
            }
            if (queryNearbyUsersRequestBuilder_ != null) {
                queryNearbyUsersRequestBuilder_.clear();
            }
            if (queryUserOnlineStatusesRequestBuilder_ != null) {
                queryUserOnlineStatusesRequestBuilder_.clear();
            }
            if (updateUserLocationRequestBuilder_ != null) {
                updateUserLocationRequestBuilder_.clear();
            }
            if (updateUserOnlineStatusRequestBuilder_ != null) {
                updateUserOnlineStatusRequestBuilder_.clear();
            }
            if (updateUserRequestBuilder_ != null) {
                updateUserRequestBuilder_.clear();
            }
            if (createFriendRequestRequestBuilder_ != null) {
                createFriendRequestRequestBuilder_.clear();
            }
            if (createRelationshipGroupRequestBuilder_ != null) {
                createRelationshipGroupRequestBuilder_.clear();
            }
            if (createRelationshipRequestBuilder_ != null) {
                createRelationshipRequestBuilder_.clear();
            }
            if (deleteRelationshipGroupRequestBuilder_ != null) {
                deleteRelationshipGroupRequestBuilder_.clear();
            }
            if (deleteRelationshipRequestBuilder_ != null) {
                deleteRelationshipRequestBuilder_.clear();
            }
            if (queryFriendRequestsRequestBuilder_ != null) {
                queryFriendRequestsRequestBuilder_.clear();
            }
            if (queryRelatedUserIdsRequestBuilder_ != null) {
                queryRelatedUserIdsRequestBuilder_.clear();
            }
            if (queryRelationshipGroupsRequestBuilder_ != null) {
                queryRelationshipGroupsRequestBuilder_.clear();
            }
            if (queryRelationshipsRequestBuilder_ != null) {
                queryRelationshipsRequestBuilder_.clear();
            }
            if (updateFriendRequestRequestBuilder_ != null) {
                updateFriendRequestRequestBuilder_.clear();
            }
            if (updateRelationshipGroupRequestBuilder_ != null) {
                updateRelationshipGroupRequestBuilder_.clear();
            }
            if (updateRelationshipRequestBuilder_ != null) {
                updateRelationshipRequestBuilder_.clear();
            }
            if (createGroupRequestBuilder_ != null) {
                createGroupRequestBuilder_.clear();
            }
            if (deleteGroupRequestBuilder_ != null) {
                deleteGroupRequestBuilder_.clear();
            }
            if (queryGroupsRequestBuilder_ != null) {
                queryGroupsRequestBuilder_.clear();
            }
            if (queryJoinedGroupIdsRequestBuilder_ != null) {
                queryJoinedGroupIdsRequestBuilder_.clear();
            }
            if (queryJoinedGroupInfosRequestBuilder_ != null) {
                queryJoinedGroupInfosRequestBuilder_.clear();
            }
            if (updateGroupRequestBuilder_ != null) {
                updateGroupRequestBuilder_.clear();
            }
            if (createGroupBlockedUserRequestBuilder_ != null) {
                createGroupBlockedUserRequestBuilder_.clear();
            }
            if (deleteGroupBlockedUserRequestBuilder_ != null) {
                deleteGroupBlockedUserRequestBuilder_.clear();
            }
            if (queryGroupBlockedUserIdsRequestBuilder_ != null) {
                queryGroupBlockedUserIdsRequestBuilder_.clear();
            }
            if (queryGroupBlockedUserInfosRequestBuilder_ != null) {
                queryGroupBlockedUserInfosRequestBuilder_.clear();
            }
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ != null) {
                checkGroupJoinQuestionsAnswersRequestBuilder_.clear();
            }
            if (createGroupInvitationRequestBuilder_ != null) {
                createGroupInvitationRequestBuilder_.clear();
            }
            if (createGroupJoinRequestRequestBuilder_ != null) {
                createGroupJoinRequestRequestBuilder_.clear();
            }
            if (createGroupJoinQuestionsRequestBuilder_ != null) {
                createGroupJoinQuestionsRequestBuilder_.clear();
            }
            if (deleteGroupInvitationRequestBuilder_ != null) {
                deleteGroupInvitationRequestBuilder_.clear();
            }
            if (deleteGroupJoinRequestRequestBuilder_ != null) {
                deleteGroupJoinRequestRequestBuilder_.clear();
            }
            if (deleteGroupJoinQuestionsRequestBuilder_ != null) {
                deleteGroupJoinQuestionsRequestBuilder_.clear();
            }
            if (queryGroupInvitationsRequestBuilder_ != null) {
                queryGroupInvitationsRequestBuilder_.clear();
            }
            if (queryGroupJoinRequestsRequestBuilder_ != null) {
                queryGroupJoinRequestsRequestBuilder_.clear();
            }
            if (queryGroupJoinQuestionsRequestBuilder_ != null) {
                queryGroupJoinQuestionsRequestBuilder_.clear();
            }
            if (updateGroupJoinQuestionRequestBuilder_ != null) {
                updateGroupJoinQuestionRequestBuilder_.clear();
            }
            if (deleteResourceRequestBuilder_ != null) {
                deleteResourceRequestBuilder_.clear();
            }
            if (queryResourceDownloadInfoRequestBuilder_ != null) {
                queryResourceDownloadInfoRequestBuilder_.clear();
            }
            if (queryResourceUploadInfoRequestBuilder_ != null) {
                queryResourceUploadInfoRequestBuilder_.clear();
            }
            if (queryMessageAttachmentInfosRequestBuilder_ != null) {
                queryMessageAttachmentInfosRequestBuilder_.clear();
            }
            if (updateMessageAttachmentInfoRequestBuilder_ != null) {
                updateMessageAttachmentInfoRequestBuilder_.clear();
            }
            kindCase_ = 0;
            kind_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.internal_static_im_turms_proto_TurmsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.TurmsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.TurmsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.TurmsRequest build() {
            im.turms.server.common.access.client.dto.request.TurmsRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.TurmsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.TurmsRequest result =
                    new im.turms.server.common.access.client.dto.request.TurmsRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            if (bitField1_ != 0) {
                buildPartial1(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.TurmsRequest result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.requestId_ = requestId_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        private void buildPartial1(
                im.turms.server.common.access.client.dto.request.TurmsRequest result) {
            int from_bitField1_ = bitField1_;
        }

        private void buildPartialOneofs(
                im.turms.server.common.access.client.dto.request.TurmsRequest result) {
            result.kindCase_ = kindCase_;
            result.kind_ = this.kind_;
            if (kindCase_ == 3 && createSessionRequestBuilder_ != null) {
                result.kind_ = createSessionRequestBuilder_.build();
            }
            if (kindCase_ == 4 && deleteSessionRequestBuilder_ != null) {
                result.kind_ = deleteSessionRequestBuilder_.build();
            }
            if (kindCase_ == 5 && queryConversationsRequestBuilder_ != null) {
                result.kind_ = queryConversationsRequestBuilder_.build();
            }
            if (kindCase_ == 6 && updateConversationRequestBuilder_ != null) {
                result.kind_ = updateConversationRequestBuilder_.build();
            }
            if (kindCase_ == 7 && updateTypingStatusRequestBuilder_ != null) {
                result.kind_ = updateTypingStatusRequestBuilder_.build();
            }
            if (kindCase_ == 8 && createMessageRequestBuilder_ != null) {
                result.kind_ = createMessageRequestBuilder_.build();
            }
            if (kindCase_ == 9 && queryMessagesRequestBuilder_ != null) {
                result.kind_ = queryMessagesRequestBuilder_.build();
            }
            if (kindCase_ == 10 && updateMessageRequestBuilder_ != null) {
                result.kind_ = updateMessageRequestBuilder_.build();
            }
            if (kindCase_ == 11 && createGroupMembersRequestBuilder_ != null) {
                result.kind_ = createGroupMembersRequestBuilder_.build();
            }
            if (kindCase_ == 12 && deleteGroupMembersRequestBuilder_ != null) {
                result.kind_ = deleteGroupMembersRequestBuilder_.build();
            }
            if (kindCase_ == 13 && queryGroupMembersRequestBuilder_ != null) {
                result.kind_ = queryGroupMembersRequestBuilder_.build();
            }
            if (kindCase_ == 14 && updateGroupMemberRequestBuilder_ != null) {
                result.kind_ = updateGroupMemberRequestBuilder_.build();
            }
            if (kindCase_ == 100 && queryUserProfilesRequestBuilder_ != null) {
                result.kind_ = queryUserProfilesRequestBuilder_.build();
            }
            if (kindCase_ == 101 && queryNearbyUsersRequestBuilder_ != null) {
                result.kind_ = queryNearbyUsersRequestBuilder_.build();
            }
            if (kindCase_ == 102 && queryUserOnlineStatusesRequestBuilder_ != null) {
                result.kind_ = queryUserOnlineStatusesRequestBuilder_.build();
            }
            if (kindCase_ == 103 && updateUserLocationRequestBuilder_ != null) {
                result.kind_ = updateUserLocationRequestBuilder_.build();
            }
            if (kindCase_ == 104 && updateUserOnlineStatusRequestBuilder_ != null) {
                result.kind_ = updateUserOnlineStatusRequestBuilder_.build();
            }
            if (kindCase_ == 105 && updateUserRequestBuilder_ != null) {
                result.kind_ = updateUserRequestBuilder_.build();
            }
            if (kindCase_ == 200 && createFriendRequestRequestBuilder_ != null) {
                result.kind_ = createFriendRequestRequestBuilder_.build();
            }
            if (kindCase_ == 201 && createRelationshipGroupRequestBuilder_ != null) {
                result.kind_ = createRelationshipGroupRequestBuilder_.build();
            }
            if (kindCase_ == 202 && createRelationshipRequestBuilder_ != null) {
                result.kind_ = createRelationshipRequestBuilder_.build();
            }
            if (kindCase_ == 203 && deleteRelationshipGroupRequestBuilder_ != null) {
                result.kind_ = deleteRelationshipGroupRequestBuilder_.build();
            }
            if (kindCase_ == 204 && deleteRelationshipRequestBuilder_ != null) {
                result.kind_ = deleteRelationshipRequestBuilder_.build();
            }
            if (kindCase_ == 205 && queryFriendRequestsRequestBuilder_ != null) {
                result.kind_ = queryFriendRequestsRequestBuilder_.build();
            }
            if (kindCase_ == 206 && queryRelatedUserIdsRequestBuilder_ != null) {
                result.kind_ = queryRelatedUserIdsRequestBuilder_.build();
            }
            if (kindCase_ == 207 && queryRelationshipGroupsRequestBuilder_ != null) {
                result.kind_ = queryRelationshipGroupsRequestBuilder_.build();
            }
            if (kindCase_ == 208 && queryRelationshipsRequestBuilder_ != null) {
                result.kind_ = queryRelationshipsRequestBuilder_.build();
            }
            if (kindCase_ == 209 && updateFriendRequestRequestBuilder_ != null) {
                result.kind_ = updateFriendRequestRequestBuilder_.build();
            }
            if (kindCase_ == 210 && updateRelationshipGroupRequestBuilder_ != null) {
                result.kind_ = updateRelationshipGroupRequestBuilder_.build();
            }
            if (kindCase_ == 211 && updateRelationshipRequestBuilder_ != null) {
                result.kind_ = updateRelationshipRequestBuilder_.build();
            }
            if (kindCase_ == 300 && createGroupRequestBuilder_ != null) {
                result.kind_ = createGroupRequestBuilder_.build();
            }
            if (kindCase_ == 301 && deleteGroupRequestBuilder_ != null) {
                result.kind_ = deleteGroupRequestBuilder_.build();
            }
            if (kindCase_ == 302 && queryGroupsRequestBuilder_ != null) {
                result.kind_ = queryGroupsRequestBuilder_.build();
            }
            if (kindCase_ == 303 && queryJoinedGroupIdsRequestBuilder_ != null) {
                result.kind_ = queryJoinedGroupIdsRequestBuilder_.build();
            }
            if (kindCase_ == 304 && queryJoinedGroupInfosRequestBuilder_ != null) {
                result.kind_ = queryJoinedGroupInfosRequestBuilder_.build();
            }
            if (kindCase_ == 305 && updateGroupRequestBuilder_ != null) {
                result.kind_ = updateGroupRequestBuilder_.build();
            }
            if (kindCase_ == 400 && createGroupBlockedUserRequestBuilder_ != null) {
                result.kind_ = createGroupBlockedUserRequestBuilder_.build();
            }
            if (kindCase_ == 401 && deleteGroupBlockedUserRequestBuilder_ != null) {
                result.kind_ = deleteGroupBlockedUserRequestBuilder_.build();
            }
            if (kindCase_ == 402 && queryGroupBlockedUserIdsRequestBuilder_ != null) {
                result.kind_ = queryGroupBlockedUserIdsRequestBuilder_.build();
            }
            if (kindCase_ == 403 && queryGroupBlockedUserInfosRequestBuilder_ != null) {
                result.kind_ = queryGroupBlockedUserInfosRequestBuilder_.build();
            }
            if (kindCase_ == 500 && checkGroupJoinQuestionsAnswersRequestBuilder_ != null) {
                result.kind_ = checkGroupJoinQuestionsAnswersRequestBuilder_.build();
            }
            if (kindCase_ == 501 && createGroupInvitationRequestBuilder_ != null) {
                result.kind_ = createGroupInvitationRequestBuilder_.build();
            }
            if (kindCase_ == 502 && createGroupJoinRequestRequestBuilder_ != null) {
                result.kind_ = createGroupJoinRequestRequestBuilder_.build();
            }
            if (kindCase_ == 503 && createGroupJoinQuestionsRequestBuilder_ != null) {
                result.kind_ = createGroupJoinQuestionsRequestBuilder_.build();
            }
            if (kindCase_ == 504 && deleteGroupInvitationRequestBuilder_ != null) {
                result.kind_ = deleteGroupInvitationRequestBuilder_.build();
            }
            if (kindCase_ == 505 && deleteGroupJoinRequestRequestBuilder_ != null) {
                result.kind_ = deleteGroupJoinRequestRequestBuilder_.build();
            }
            if (kindCase_ == 506 && deleteGroupJoinQuestionsRequestBuilder_ != null) {
                result.kind_ = deleteGroupJoinQuestionsRequestBuilder_.build();
            }
            if (kindCase_ == 507 && queryGroupInvitationsRequestBuilder_ != null) {
                result.kind_ = queryGroupInvitationsRequestBuilder_.build();
            }
            if (kindCase_ == 508 && queryGroupJoinRequestsRequestBuilder_ != null) {
                result.kind_ = queryGroupJoinRequestsRequestBuilder_.build();
            }
            if (kindCase_ == 509 && queryGroupJoinQuestionsRequestBuilder_ != null) {
                result.kind_ = queryGroupJoinQuestionsRequestBuilder_.build();
            }
            if (kindCase_ == 510 && updateGroupJoinQuestionRequestBuilder_ != null) {
                result.kind_ = updateGroupJoinQuestionRequestBuilder_.build();
            }
            if (kindCase_ == 1000 && deleteResourceRequestBuilder_ != null) {
                result.kind_ = deleteResourceRequestBuilder_.build();
            }
            if (kindCase_ == 1001 && queryResourceDownloadInfoRequestBuilder_ != null) {
                result.kind_ = queryResourceDownloadInfoRequestBuilder_.build();
            }
            if (kindCase_ == 1002 && queryResourceUploadInfoRequestBuilder_ != null) {
                result.kind_ = queryResourceUploadInfoRequestBuilder_.build();
            }
            if (kindCase_ == 1003 && queryMessageAttachmentInfosRequestBuilder_ != null) {
                result.kind_ = queryMessageAttachmentInfosRequestBuilder_.build();
            }
            if (kindCase_ == 1004 && updateMessageAttachmentInfoRequestBuilder_ != null) {
                result.kind_ = updateMessageAttachmentInfoRequestBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.TurmsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.TurmsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.TurmsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.TurmsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasRequestId()) {
                setRequestId(other.getRequestId());
            }
            switch (other.getKindCase()) {
                case CREATE_SESSION_REQUEST -> {
                    mergeCreateSessionRequest(other.getCreateSessionRequest());
                }
                case DELETE_SESSION_REQUEST -> {
                    mergeDeleteSessionRequest(other.getDeleteSessionRequest());
                }
                case QUERY_CONVERSATIONS_REQUEST -> {
                    mergeQueryConversationsRequest(other.getQueryConversationsRequest());
                }
                case UPDATE_CONVERSATION_REQUEST -> {
                    mergeUpdateConversationRequest(other.getUpdateConversationRequest());
                }
                case UPDATE_TYPING_STATUS_REQUEST -> {
                    mergeUpdateTypingStatusRequest(other.getUpdateTypingStatusRequest());
                }
                case CREATE_MESSAGE_REQUEST -> {
                    mergeCreateMessageRequest(other.getCreateMessageRequest());
                }
                case QUERY_MESSAGES_REQUEST -> {
                    mergeQueryMessagesRequest(other.getQueryMessagesRequest());
                }
                case UPDATE_MESSAGE_REQUEST -> {
                    mergeUpdateMessageRequest(other.getUpdateMessageRequest());
                }
                case CREATE_GROUP_MEMBERS_REQUEST -> {
                    mergeCreateGroupMembersRequest(other.getCreateGroupMembersRequest());
                }
                case DELETE_GROUP_MEMBERS_REQUEST -> {
                    mergeDeleteGroupMembersRequest(other.getDeleteGroupMembersRequest());
                }
                case QUERY_GROUP_MEMBERS_REQUEST -> {
                    mergeQueryGroupMembersRequest(other.getQueryGroupMembersRequest());
                }
                case UPDATE_GROUP_MEMBER_REQUEST -> {
                    mergeUpdateGroupMemberRequest(other.getUpdateGroupMemberRequest());
                }
                case QUERY_USER_PROFILES_REQUEST -> {
                    mergeQueryUserProfilesRequest(other.getQueryUserProfilesRequest());
                }
                case QUERY_NEARBY_USERS_REQUEST -> {
                    mergeQueryNearbyUsersRequest(other.getQueryNearbyUsersRequest());
                }
                case QUERY_USER_ONLINE_STATUSES_REQUEST -> {
                    mergeQueryUserOnlineStatusesRequest(other.getQueryUserOnlineStatusesRequest());
                }
                case UPDATE_USER_LOCATION_REQUEST -> {
                    mergeUpdateUserLocationRequest(other.getUpdateUserLocationRequest());
                }
                case UPDATE_USER_ONLINE_STATUS_REQUEST -> {
                    mergeUpdateUserOnlineStatusRequest(other.getUpdateUserOnlineStatusRequest());
                }
                case UPDATE_USER_REQUEST -> {
                    mergeUpdateUserRequest(other.getUpdateUserRequest());
                }
                case CREATE_FRIEND_REQUEST_REQUEST -> {
                    mergeCreateFriendRequestRequest(other.getCreateFriendRequestRequest());
                }
                case CREATE_RELATIONSHIP_GROUP_REQUEST -> {
                    mergeCreateRelationshipGroupRequest(other.getCreateRelationshipGroupRequest());
                }
                case CREATE_RELATIONSHIP_REQUEST -> {
                    mergeCreateRelationshipRequest(other.getCreateRelationshipRequest());
                }
                case DELETE_RELATIONSHIP_GROUP_REQUEST -> {
                    mergeDeleteRelationshipGroupRequest(other.getDeleteRelationshipGroupRequest());
                }
                case DELETE_RELATIONSHIP_REQUEST -> {
                    mergeDeleteRelationshipRequest(other.getDeleteRelationshipRequest());
                }
                case QUERY_FRIEND_REQUESTS_REQUEST -> {
                    mergeQueryFriendRequestsRequest(other.getQueryFriendRequestsRequest());
                }
                case QUERY_RELATED_USER_IDS_REQUEST -> {
                    mergeQueryRelatedUserIdsRequest(other.getQueryRelatedUserIdsRequest());
                }
                case QUERY_RELATIONSHIP_GROUPS_REQUEST -> {
                    mergeQueryRelationshipGroupsRequest(other.getQueryRelationshipGroupsRequest());
                }
                case QUERY_RELATIONSHIPS_REQUEST -> {
                    mergeQueryRelationshipsRequest(other.getQueryRelationshipsRequest());
                }
                case UPDATE_FRIEND_REQUEST_REQUEST -> {
                    mergeUpdateFriendRequestRequest(other.getUpdateFriendRequestRequest());
                }
                case UPDATE_RELATIONSHIP_GROUP_REQUEST -> {
                    mergeUpdateRelationshipGroupRequest(other.getUpdateRelationshipGroupRequest());
                }
                case UPDATE_RELATIONSHIP_REQUEST -> {
                    mergeUpdateRelationshipRequest(other.getUpdateRelationshipRequest());
                }
                case CREATE_GROUP_REQUEST -> {
                    mergeCreateGroupRequest(other.getCreateGroupRequest());
                }
                case DELETE_GROUP_REQUEST -> {
                    mergeDeleteGroupRequest(other.getDeleteGroupRequest());
                }
                case QUERY_GROUPS_REQUEST -> {
                    mergeQueryGroupsRequest(other.getQueryGroupsRequest());
                }
                case QUERY_JOINED_GROUP_IDS_REQUEST -> {
                    mergeQueryJoinedGroupIdsRequest(other.getQueryJoinedGroupIdsRequest());
                }
                case QUERY_JOINED_GROUP_INFOS_REQUEST -> {
                    mergeQueryJoinedGroupInfosRequest(other.getQueryJoinedGroupInfosRequest());
                }
                case UPDATE_GROUP_REQUEST -> {
                    mergeUpdateGroupRequest(other.getUpdateGroupRequest());
                }
                case CREATE_GROUP_BLOCKED_USER_REQUEST -> {
                    mergeCreateGroupBlockedUserRequest(other.getCreateGroupBlockedUserRequest());
                }
                case DELETE_GROUP_BLOCKED_USER_REQUEST -> {
                    mergeDeleteGroupBlockedUserRequest(other.getDeleteGroupBlockedUserRequest());
                }
                case QUERY_GROUP_BLOCKED_USER_IDS_REQUEST -> {
                    mergeQueryGroupBlockedUserIdsRequest(
                            other.getQueryGroupBlockedUserIdsRequest());
                }
                case QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST -> {
                    mergeQueryGroupBlockedUserInfosRequest(
                            other.getQueryGroupBlockedUserInfosRequest());
                }
                case CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST -> {
                    mergeCheckGroupJoinQuestionsAnswersRequest(
                            other.getCheckGroupJoinQuestionsAnswersRequest());
                }
                case CREATE_GROUP_INVITATION_REQUEST -> {
                    mergeCreateGroupInvitationRequest(other.getCreateGroupInvitationRequest());
                }
                case CREATE_GROUP_JOIN_REQUEST_REQUEST -> {
                    mergeCreateGroupJoinRequestRequest(other.getCreateGroupJoinRequestRequest());
                }
                case CREATE_GROUP_JOIN_QUESTIONS_REQUEST -> {
                    mergeCreateGroupJoinQuestionsRequest(
                            other.getCreateGroupJoinQuestionsRequest());
                }
                case DELETE_GROUP_INVITATION_REQUEST -> {
                    mergeDeleteGroupInvitationRequest(other.getDeleteGroupInvitationRequest());
                }
                case DELETE_GROUP_JOIN_REQUEST_REQUEST -> {
                    mergeDeleteGroupJoinRequestRequest(other.getDeleteGroupJoinRequestRequest());
                }
                case DELETE_GROUP_JOIN_QUESTIONS_REQUEST -> {
                    mergeDeleteGroupJoinQuestionsRequest(
                            other.getDeleteGroupJoinQuestionsRequest());
                }
                case QUERY_GROUP_INVITATIONS_REQUEST -> {
                    mergeQueryGroupInvitationsRequest(other.getQueryGroupInvitationsRequest());
                }
                case QUERY_GROUP_JOIN_REQUESTS_REQUEST -> {
                    mergeQueryGroupJoinRequestsRequest(other.getQueryGroupJoinRequestsRequest());
                }
                case QUERY_GROUP_JOIN_QUESTIONS_REQUEST -> {
                    mergeQueryGroupJoinQuestionsRequest(other.getQueryGroupJoinQuestionsRequest());
                }
                case UPDATE_GROUP_JOIN_QUESTION_REQUEST -> {
                    mergeUpdateGroupJoinQuestionRequest(other.getUpdateGroupJoinQuestionRequest());
                }
                case DELETE_RESOURCE_REQUEST -> {
                    mergeDeleteResourceRequest(other.getDeleteResourceRequest());
                }
                case QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST -> {
                    mergeQueryResourceDownloadInfoRequest(
                            other.getQueryResourceDownloadInfoRequest());
                }
                case QUERY_RESOURCE_UPLOAD_INFO_REQUEST -> {
                    mergeQueryResourceUploadInfoRequest(other.getQueryResourceUploadInfoRequest());
                }
                case QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST -> {
                    mergeQueryMessageAttachmentInfosRequest(
                            other.getQueryMessageAttachmentInfosRequest());
                }
                case UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST -> {
                    mergeUpdateMessageAttachmentInfoRequest(
                            other.getUpdateMessageAttachmentInfoRequest());
                }
                case KIND_NOT_SET -> {
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 8 -> {
                            requestId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 26 -> {
                            input.readMessage(getCreateSessionRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 3;
                        } // case 26
                        case 34 -> {
                            input.readMessage(getDeleteSessionRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 4;
                        } // case 34
                        case 42 -> {
                            input.readMessage(
                                    getQueryConversationsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 5;
                        } // case 42
                        case 50 -> {
                            input.readMessage(
                                    getUpdateConversationRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 6;
                        } // case 50
                        case 58 -> {
                            input.readMessage(
                                    getUpdateTypingStatusRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 7;
                        } // case 58
                        case 66 -> {
                            input.readMessage(getCreateMessageRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 8;
                        } // case 66
                        case 74 -> {
                            input.readMessage(getQueryMessagesRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 9;
                        } // case 74
                        case 82 -> {
                            input.readMessage(getUpdateMessageRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 10;
                        } // case 82
                        case 90 -> {
                            input.readMessage(
                                    getCreateGroupMembersRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 11;
                        } // case 90
                        case 98 -> {
                            input.readMessage(
                                    getDeleteGroupMembersRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 12;
                        } // case 98
                        case 106 -> {
                            input.readMessage(
                                    getQueryGroupMembersRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 13;
                        } // case 106
                        case 114 -> {
                            input.readMessage(
                                    getUpdateGroupMemberRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 14;
                        } // case 114
                        case 802 -> {
                            input.readMessage(
                                    getQueryUserProfilesRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 100;
                        } // case 802
                        case 810 -> {
                            input.readMessage(getQueryNearbyUsersRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 101;
                        } // case 810
                        case 818 -> {
                            input.readMessage(
                                    getQueryUserOnlineStatusesRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 102;
                        } // case 818
                        case 826 -> {
                            input.readMessage(
                                    getUpdateUserLocationRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 103;
                        } // case 826
                        case 834 -> {
                            input.readMessage(
                                    getUpdateUserOnlineStatusRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 104;
                        } // case 834
                        case 842 -> {
                            input.readMessage(getUpdateUserRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 105;
                        } // case 842
                        case 1602 -> {
                            input.readMessage(
                                    getCreateFriendRequestRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 200;
                        } // case 1602
                        case 1610 -> {
                            input.readMessage(
                                    getCreateRelationshipGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 201;
                        } // case 1610
                        case 1618 -> {
                            input.readMessage(
                                    getCreateRelationshipRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 202;
                        } // case 1618
                        case 1626 -> {
                            input.readMessage(
                                    getDeleteRelationshipGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 203;
                        } // case 1626
                        case 1634 -> {
                            input.readMessage(
                                    getDeleteRelationshipRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 204;
                        } // case 1634
                        case 1642 -> {
                            input.readMessage(
                                    getQueryFriendRequestsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 205;
                        } // case 1642
                        case 1650 -> {
                            input.readMessage(
                                    getQueryRelatedUserIdsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 206;
                        } // case 1650
                        case 1658 -> {
                            input.readMessage(
                                    getQueryRelationshipGroupsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 207;
                        } // case 1658
                        case 1666 -> {
                            input.readMessage(
                                    getQueryRelationshipsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 208;
                        } // case 1666
                        case 1674 -> {
                            input.readMessage(
                                    getUpdateFriendRequestRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 209;
                        } // case 1674
                        case 1682 -> {
                            input.readMessage(
                                    getUpdateRelationshipGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 210;
                        } // case 1682
                        case 1690 -> {
                            input.readMessage(
                                    getUpdateRelationshipRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 211;
                        } // case 1690
                        case 2402 -> {
                            input.readMessage(getCreateGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 300;
                        } // case 2402
                        case 2410 -> {
                            input.readMessage(getDeleteGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 301;
                        } // case 2410
                        case 2418 -> {
                            input.readMessage(getQueryGroupsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 302;
                        } // case 2418
                        case 2426 -> {
                            input.readMessage(
                                    getQueryJoinedGroupIdsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 303;
                        } // case 2426
                        case 2434 -> {
                            input.readMessage(
                                    getQueryJoinedGroupInfosRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 304;
                        } // case 2434
                        case 2442 -> {
                            input.readMessage(getUpdateGroupRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 305;
                        } // case 2442
                        case 3202 -> {
                            input.readMessage(
                                    getCreateGroupBlockedUserRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 400;
                        } // case 3202
                        case 3210 -> {
                            input.readMessage(
                                    getDeleteGroupBlockedUserRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 401;
                        } // case 3210
                        case 3218 -> {
                            input.readMessage(
                                    getQueryGroupBlockedUserIdsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 402;
                        } // case 3218
                        case 3226 -> {
                            input.readMessage(
                                    getQueryGroupBlockedUserInfosRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 403;
                        } // case 3226
                        case 4002 -> {
                            input.readMessage(
                                    getCheckGroupJoinQuestionsAnswersRequestFieldBuilder()
                                            .getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 500;
                        } // case 4002
                        case 4010 -> {
                            input.readMessage(
                                    getCreateGroupInvitationRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 501;
                        } // case 4010
                        case 4018 -> {
                            input.readMessage(
                                    getCreateGroupJoinRequestRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 502;
                        } // case 4018
                        case 4026 -> {
                            input.readMessage(
                                    getCreateGroupJoinQuestionsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 503;
                        } // case 4026
                        case 4034 -> {
                            input.readMessage(
                                    getDeleteGroupInvitationRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 504;
                        } // case 4034
                        case 4042 -> {
                            input.readMessage(
                                    getDeleteGroupJoinRequestRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 505;
                        } // case 4042
                        case 4050 -> {
                            input.readMessage(
                                    getDeleteGroupJoinQuestionsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 506;
                        } // case 4050
                        case 4058 -> {
                            input.readMessage(
                                    getQueryGroupInvitationsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 507;
                        } // case 4058
                        case 4066 -> {
                            input.readMessage(
                                    getQueryGroupJoinRequestsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 508;
                        } // case 4066
                        case 4074 -> {
                            input.readMessage(
                                    getQueryGroupJoinQuestionsRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 509;
                        } // case 4074
                        case 4082 -> {
                            input.readMessage(
                                    getUpdateGroupJoinQuestionRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 510;
                        } // case 4082
                        case 8002 -> {
                            input.readMessage(getDeleteResourceRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 1000;
                        } // case 8002
                        case 8010 -> {
                            input.readMessage(
                                    getQueryResourceDownloadInfoRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 1001;
                        } // case 8010
                        case 8018 -> {
                            input.readMessage(
                                    getQueryResourceUploadInfoRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 1002;
                        } // case 8018
                        case 8026 -> {
                            input.readMessage(
                                    getQueryMessageAttachmentInfosRequestFieldBuilder()
                                            .getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 1003;
                        } // case 8026
                        case 8034 -> {
                            input.readMessage(
                                    getUpdateMessageAttachmentInfoRequestFieldBuilder()
                                            .getBuilder(),
                                    extensionRegistry);
                            kindCase_ = 1004;
                        } // case 8034
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int kindCase_ = 0;
        private java.lang.Object kind_;

        public KindCase getKindCase() {
            return KindCase.forNumber(kindCase_);
        }

        public Builder clearKind() {
            kindCase_ = 0;
            kind_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;
        private int bitField1_;

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
         * @return This builder for chaining.
         */
        public Builder setRequestId(long value) {

            requestId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
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
            bitField0_ &= ~0x00000001;
            requestId_ = 0L;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.CreateSessionRequest, im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder, im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder> createSessionRequestBuilder_;

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         *
         * @return Whether the createSessionRequest field is set.
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
         *
         * @return The createSessionRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getCreateSessionRequest() {
            if (createSessionRequestBuilder_ == null) {
                if (kindCase_ == 3) {
                    return (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 3) {
                    return createSessionRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public Builder setCreateSessionRequest(
                im.turms.server.common.access.client.dto.request.user.CreateSessionRequest value) {
            if (createSessionRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createSessionRequestBuilder_.setMessage(value);
            }
            kindCase_ = 3;
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
                im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder builderForValue) {
            if (createSessionRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createSessionRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 3;
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
                im.turms.server.common.access.client.dto.request.user.CreateSessionRequest value) {
            if (createSessionRequestBuilder_ == null) {
                if (kindCase_ == 3
                        && kind_ != im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 3) {
                    createSessionRequestBuilder_.mergeFrom(value);
                } else {
                    createSessionRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 3;
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
            if (createSessionRequestBuilder_ == null) {
                if (kindCase_ == 3) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 3) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createSessionRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder getCreateSessionRequestBuilder() {
            return getCreateSessionRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder getCreateSessionRequestOrBuilder() {
            if ((kindCase_ == 3) && (createSessionRequestBuilder_ != null)) {
                return createSessionRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 3) {
                    return (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User - Session
         * </pre>
         * 
         * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.CreateSessionRequest, im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder, im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder> getCreateSessionRequestFieldBuilder() {
            if (createSessionRequestBuilder_ == null) {
                if (!(kindCase_ == 3)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                            .getDefaultInstance();
                }
                createSessionRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 3;
            onChanged();
            return createSessionRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest, im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest.Builder, im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOrBuilder> deleteSessionRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         *
         * @return Whether the deleteSessionRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteSessionRequest() {
            return kindCase_ == 4;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         *
         * @return The deleteSessionRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest getDeleteSessionRequest() {
            if (deleteSessionRequestBuilder_ == null) {
                if (kindCase_ == 4) {
                    return (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 4) {
                    return deleteSessionRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder setDeleteSessionRequest(
                im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest value) {
            if (deleteSessionRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteSessionRequestBuilder_.setMessage(value);
            }
            kindCase_ = 4;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder setDeleteSessionRequest(
                im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest.Builder builderForValue) {
            if (deleteSessionRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteSessionRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 4;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder mergeDeleteSessionRequest(
                im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest value) {
            if (deleteSessionRequestBuilder_ == null) {
                if (kindCase_ == 4
                        && kind_ != im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 4) {
                    deleteSessionRequestBuilder_.mergeFrom(value);
                } else {
                    deleteSessionRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 4;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public Builder clearDeleteSessionRequest() {
            if (deleteSessionRequestBuilder_ == null) {
                if (kindCase_ == 4) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 4) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteSessionRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest.Builder getDeleteSessionRequestBuilder() {
            return getDeleteSessionRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOrBuilder getDeleteSessionRequestOrBuilder() {
            if ((kindCase_ == 4) && (deleteSessionRequestBuilder_ != null)) {
                return deleteSessionRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 4) {
                    return (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest, im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest.Builder, im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOrBuilder> getDeleteSessionRequestFieldBuilder() {
            if (deleteSessionRequestBuilder_ == null) {
                if (!(kindCase_ == 4)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest
                            .getDefaultInstance();
                }
                deleteSessionRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 4;
            onChanged();
            return deleteSessionRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest, im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder> queryConversationsRequestBuilder_;

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         *
         * @return Whether the queryConversationsRequest field is set.
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
         *
         * @return The queryConversationsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getQueryConversationsRequest() {
            if (queryConversationsRequestBuilder_ == null) {
                if (kindCase_ == 5) {
                    return (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 5) {
                    return queryConversationsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public Builder setQueryConversationsRequest(
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest value) {
            if (queryConversationsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryConversationsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 5;
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
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder builderForValue) {
            if (queryConversationsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryConversationsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 5;
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
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest value) {
            if (queryConversationsRequestBuilder_ == null) {
                if (kindCase_ == 5
                        && kind_ != im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 5) {
                    queryConversationsRequestBuilder_.mergeFrom(value);
                } else {
                    queryConversationsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 5;
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
            if (queryConversationsRequestBuilder_ == null) {
                if (kindCase_ == 5) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 5) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryConversationsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder getQueryConversationsRequestBuilder() {
            return getQueryConversationsRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder getQueryConversationsRequestOrBuilder() {
            if ((kindCase_ == 5) && (queryConversationsRequestBuilder_ != null)) {
                return queryConversationsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 5) {
                    return (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest, im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder> getQueryConversationsRequestFieldBuilder() {
            if (queryConversationsRequestBuilder_ == null) {
                if (!(kindCase_ == 5)) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                            .getDefaultInstance();
                }
                queryConversationsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 5;
            onChanged();
            return queryConversationsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest, im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOrBuilder> updateConversationRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         *
         * @return Whether the updateConversationRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateConversationRequest() {
            return kindCase_ == 6;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         *
         * @return The updateConversationRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest getUpdateConversationRequest() {
            if (updateConversationRequestBuilder_ == null) {
                if (kindCase_ == 6) {
                    return (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 6) {
                    return updateConversationRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder setUpdateConversationRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest value) {
            if (updateConversationRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateConversationRequestBuilder_.setMessage(value);
            }
            kindCase_ = 6;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder setUpdateConversationRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest.Builder builderForValue) {
            if (updateConversationRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateConversationRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 6;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder mergeUpdateConversationRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest value) {
            if (updateConversationRequestBuilder_ == null) {
                if (kindCase_ == 6
                        && kind_ != im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 6) {
                    updateConversationRequestBuilder_.mergeFrom(value);
                } else {
                    updateConversationRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 6;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public Builder clearUpdateConversationRequest() {
            if (updateConversationRequestBuilder_ == null) {
                if (kindCase_ == 6) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 6) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateConversationRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest.Builder getUpdateConversationRequestBuilder() {
            return getUpdateConversationRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOrBuilder getUpdateConversationRequestOrBuilder() {
            if ((kindCase_ == 6) && (updateConversationRequestBuilder_ != null)) {
                return updateConversationRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 6) {
                    return (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest, im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOrBuilder> getUpdateConversationRequestFieldBuilder() {
            if (updateConversationRequestBuilder_ == null) {
                if (!(kindCase_ == 6)) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest
                            .getDefaultInstance();
                }
                updateConversationRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 6;
            onChanged();
            return updateConversationRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest, im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder> updateTypingStatusRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         *
         * @return Whether the updateTypingStatusRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateTypingStatusRequest() {
            return kindCase_ == 7;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         *
         * @return The updateTypingStatusRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getUpdateTypingStatusRequest() {
            if (updateTypingStatusRequestBuilder_ == null) {
                if (kindCase_ == 7) {
                    return (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 7) {
                    return updateTypingStatusRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder setUpdateTypingStatusRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest value) {
            if (updateTypingStatusRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateTypingStatusRequestBuilder_.setMessage(value);
            }
            kindCase_ = 7;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder setUpdateTypingStatusRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder builderForValue) {
            if (updateTypingStatusRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateTypingStatusRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 7;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder mergeUpdateTypingStatusRequest(
                im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest value) {
            if (updateTypingStatusRequestBuilder_ == null) {
                if (kindCase_ == 7
                        && kind_ != im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 7) {
                    updateTypingStatusRequestBuilder_.mergeFrom(value);
                } else {
                    updateTypingStatusRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 7;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public Builder clearUpdateTypingStatusRequest() {
            if (updateTypingStatusRequestBuilder_ == null) {
                if (kindCase_ == 7) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 7) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateTypingStatusRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder getUpdateTypingStatusRequestBuilder() {
            return getUpdateTypingStatusRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder getUpdateTypingStatusRequestOrBuilder() {
            if ((kindCase_ == 7) && (updateTypingStatusRequestBuilder_ != null)) {
                return updateTypingStatusRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 7) {
                    return (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest, im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder, im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder> getUpdateTypingStatusRequestFieldBuilder() {
            if (updateTypingStatusRequestBuilder_ == null) {
                if (!(kindCase_ == 7)) {
                    kind_ = im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                            .getDefaultInstance();
                }
                updateTypingStatusRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 7;
            onChanged();
            return updateTypingStatusRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.CreateMessageRequest, im.turms.server.common.access.client.dto.request.message.CreateMessageRequest.Builder, im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOrBuilder> createMessageRequestBuilder_;

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         *
         * @return Whether the createMessageRequest field is set.
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
         *
         * @return The createMessageRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.CreateMessageRequest getCreateMessageRequest() {
            if (createMessageRequestBuilder_ == null) {
                if (kindCase_ == 8) {
                    return (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 8) {
                    return createMessageRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public Builder setCreateMessageRequest(
                im.turms.server.common.access.client.dto.request.message.CreateMessageRequest value) {
            if (createMessageRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createMessageRequestBuilder_.setMessage(value);
            }
            kindCase_ = 8;
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
                im.turms.server.common.access.client.dto.request.message.CreateMessageRequest.Builder builderForValue) {
            if (createMessageRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createMessageRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 8;
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
                im.turms.server.common.access.client.dto.request.message.CreateMessageRequest value) {
            if (createMessageRequestBuilder_ == null) {
                if (kindCase_ == 8
                        && kind_ != im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 8) {
                    createMessageRequestBuilder_.mergeFrom(value);
                } else {
                    createMessageRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 8;
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
            if (createMessageRequestBuilder_ == null) {
                if (kindCase_ == 8) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 8) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createMessageRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        public im.turms.server.common.access.client.dto.request.message.CreateMessageRequest.Builder getCreateMessageRequestBuilder() {
            return getCreateMessageRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOrBuilder getCreateMessageRequestOrBuilder() {
            if ((kindCase_ == 8) && (createMessageRequestBuilder_ != null)) {
                return createMessageRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 8) {
                    return (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.CreateMessageRequest, im.turms.server.common.access.client.dto.request.message.CreateMessageRequest.Builder, im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOrBuilder> getCreateMessageRequestFieldBuilder() {
            if (createMessageRequestBuilder_ == null) {
                if (!(kindCase_ == 8)) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.CreateMessageRequest
                            .getDefaultInstance();
                }
                createMessageRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.message.CreateMessageRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 8;
            onChanged();
            return createMessageRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest, im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder, im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder> queryMessagesRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         *
         * @return Whether the queryMessagesRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryMessagesRequest() {
            return kindCase_ == 9;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         *
         * @return The queryMessagesRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getQueryMessagesRequest() {
            if (queryMessagesRequestBuilder_ == null) {
                if (kindCase_ == 9) {
                    return (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 9) {
                    return queryMessagesRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder setQueryMessagesRequest(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest value) {
            if (queryMessagesRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryMessagesRequestBuilder_.setMessage(value);
            }
            kindCase_ = 9;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder setQueryMessagesRequest(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder builderForValue) {
            if (queryMessagesRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryMessagesRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 9;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder mergeQueryMessagesRequest(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest value) {
            if (queryMessagesRequestBuilder_ == null) {
                if (kindCase_ == 9
                        && kind_ != im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 9) {
                    queryMessagesRequestBuilder_.mergeFrom(value);
                } else {
                    queryMessagesRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 9;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public Builder clearQueryMessagesRequest() {
            if (queryMessagesRequestBuilder_ == null) {
                if (kindCase_ == 9) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 9) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryMessagesRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder getQueryMessagesRequestBuilder() {
            return getQueryMessagesRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder getQueryMessagesRequestOrBuilder() {
            if ((kindCase_ == 9) && (queryMessagesRequestBuilder_ != null)) {
                return queryMessagesRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 9) {
                    return (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest, im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder, im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder> getQueryMessagesRequestFieldBuilder() {
            if (queryMessagesRequestBuilder_ == null) {
                if (!(kindCase_ == 9)) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                            .getDefaultInstance();
                }
                queryMessagesRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 9;
            onChanged();
            return queryMessagesRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest, im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder, im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder> updateMessageRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         *
         * @return Whether the updateMessageRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateMessageRequest() {
            return kindCase_ == 10;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         *
         * @return The updateMessageRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getUpdateMessageRequest() {
            if (updateMessageRequestBuilder_ == null) {
                if (kindCase_ == 10) {
                    return (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 10) {
                    return updateMessageRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder setUpdateMessageRequest(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest value) {
            if (updateMessageRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateMessageRequestBuilder_.setMessage(value);
            }
            kindCase_ = 10;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder setUpdateMessageRequest(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder builderForValue) {
            if (updateMessageRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateMessageRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 10;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder mergeUpdateMessageRequest(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest value) {
            if (updateMessageRequestBuilder_ == null) {
                if (kindCase_ == 10
                        && kind_ != im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 10) {
                    updateMessageRequestBuilder_.mergeFrom(value);
                } else {
                    updateMessageRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 10;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public Builder clearUpdateMessageRequest() {
            if (updateMessageRequestBuilder_ == null) {
                if (kindCase_ == 10) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 10) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateMessageRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder getUpdateMessageRequestBuilder() {
            return getUpdateMessageRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder getUpdateMessageRequestOrBuilder() {
            if ((kindCase_ == 10) && (updateMessageRequestBuilder_ != null)) {
                return updateMessageRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 10) {
                    return (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest, im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder, im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder> getUpdateMessageRequestFieldBuilder() {
            if (updateMessageRequestBuilder_ == null) {
                if (!(kindCase_ == 10)) {
                    kind_ = im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                            .getDefaultInstance();
                }
                updateMessageRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 10;
            onChanged();
            return updateMessageRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOrBuilder> createGroupMembersRequestBuilder_;

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         *
         * @return Whether the createGroupMembersRequest field is set.
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
         *
         * @return The createGroupMembersRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest getCreateGroupMembersRequest() {
            if (createGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 11) {
                    return (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 11) {
                    return createGroupMembersRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public Builder setCreateGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest value) {
            if (createGroupMembersRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupMembersRequestBuilder_.setMessage(value);
            }
            kindCase_ = 11;
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
                im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest.Builder builderForValue) {
            if (createGroupMembersRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupMembersRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 11;
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
                im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest value) {
            if (createGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 11
                        && kind_ != im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 11) {
                    createGroupMembersRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupMembersRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 11;
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
            if (createGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 11) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 11) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupMembersRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest.Builder getCreateGroupMembersRequestBuilder() {
            return getCreateGroupMembersRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOrBuilder getCreateGroupMembersRequestOrBuilder() {
            if ((kindCase_ == 11) && (createGroupMembersRequestBuilder_ != null)) {
                return createGroupMembersRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 11) {
                    return (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Member
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOrBuilder> getCreateGroupMembersRequestFieldBuilder() {
            if (createGroupMembersRequestBuilder_ == null) {
                if (!(kindCase_ == 11)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest
                            .getDefaultInstance();
                }
                createGroupMembersRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 11;
            onChanged();
            return createGroupMembersRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder> deleteGroupMembersRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         *
         * @return Whether the deleteGroupMembersRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupMembersRequest() {
            return kindCase_ == 12;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         *
         * @return The deleteGroupMembersRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDeleteGroupMembersRequest() {
            if (deleteGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 12) {
                    return (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 12) {
                    return deleteGroupMembersRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder setDeleteGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest value) {
            if (deleteGroupMembersRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupMembersRequestBuilder_.setMessage(value);
            }
            kindCase_ = 12;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder setDeleteGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder builderForValue) {
            if (deleteGroupMembersRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupMembersRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 12;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder mergeDeleteGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest value) {
            if (deleteGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 12
                        && kind_ != im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 12) {
                    deleteGroupMembersRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupMembersRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 12;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public Builder clearDeleteGroupMembersRequest() {
            if (deleteGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 12) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 12) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupMembersRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder getDeleteGroupMembersRequestBuilder() {
            return getDeleteGroupMembersRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder getDeleteGroupMembersRequestOrBuilder() {
            if ((kindCase_ == 12) && (deleteGroupMembersRequestBuilder_ != null)) {
                return deleteGroupMembersRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 12) {
                    return (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder> getDeleteGroupMembersRequestFieldBuilder() {
            if (deleteGroupMembersRequestBuilder_ == null) {
                if (!(kindCase_ == 12)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                            .getDefaultInstance();
                }
                deleteGroupMembersRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 12;
            onChanged();
            return deleteGroupMembersRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOrBuilder> queryGroupMembersRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         *
         * @return Whether the queryGroupMembersRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupMembersRequest() {
            return kindCase_ == 13;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         *
         * @return The queryGroupMembersRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest getQueryGroupMembersRequest() {
            if (queryGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 13) {
                    return (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 13) {
                    return queryGroupMembersRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder setQueryGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest value) {
            if (queryGroupMembersRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupMembersRequestBuilder_.setMessage(value);
            }
            kindCase_ = 13;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder setQueryGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest.Builder builderForValue) {
            if (queryGroupMembersRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupMembersRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 13;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder mergeQueryGroupMembersRequest(
                im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest value) {
            if (queryGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 13
                        && kind_ != im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 13) {
                    queryGroupMembersRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupMembersRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 13;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public Builder clearQueryGroupMembersRequest() {
            if (queryGroupMembersRequestBuilder_ == null) {
                if (kindCase_ == 13) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 13) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupMembersRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest.Builder getQueryGroupMembersRequestBuilder() {
            return getQueryGroupMembersRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOrBuilder getQueryGroupMembersRequestOrBuilder() {
            if ((kindCase_ == 13) && (queryGroupMembersRequestBuilder_ != null)) {
                return queryGroupMembersRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 13) {
                    return (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest, im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOrBuilder> getQueryGroupMembersRequestFieldBuilder() {
            if (queryGroupMembersRequestBuilder_ == null) {
                if (!(kindCase_ == 13)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest
                            .getDefaultInstance();
                }
                queryGroupMembersRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 13;
            onChanged();
            return queryGroupMembersRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest, im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOrBuilder> updateGroupMemberRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         *
         * @return Whether the updateGroupMemberRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateGroupMemberRequest() {
            return kindCase_ == 14;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         *
         * @return The updateGroupMemberRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest getUpdateGroupMemberRequest() {
            if (updateGroupMemberRequestBuilder_ == null) {
                if (kindCase_ == 14) {
                    return (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 14) {
                    return updateGroupMemberRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder setUpdateGroupMemberRequest(
                im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest value) {
            if (updateGroupMemberRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateGroupMemberRequestBuilder_.setMessage(value);
            }
            kindCase_ = 14;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder setUpdateGroupMemberRequest(
                im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest.Builder builderForValue) {
            if (updateGroupMemberRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateGroupMemberRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 14;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder mergeUpdateGroupMemberRequest(
                im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest value) {
            if (updateGroupMemberRequestBuilder_ == null) {
                if (kindCase_ == 14
                        && kind_ != im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 14) {
                    updateGroupMemberRequestBuilder_.mergeFrom(value);
                } else {
                    updateGroupMemberRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 14;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public Builder clearUpdateGroupMemberRequest() {
            if (updateGroupMemberRequestBuilder_ == null) {
                if (kindCase_ == 14) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 14) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateGroupMemberRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest.Builder getUpdateGroupMemberRequestBuilder() {
            return getUpdateGroupMemberRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOrBuilder getUpdateGroupMemberRequestOrBuilder() {
            if ((kindCase_ == 14) && (updateGroupMemberRequestBuilder_ != null)) {
                return updateGroupMemberRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 14) {
                    return (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest, im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest.Builder, im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOrBuilder> getUpdateGroupMemberRequestFieldBuilder() {
            if (updateGroupMemberRequestBuilder_ == null) {
                if (!(kindCase_ == 14)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest
                            .getDefaultInstance();
                }
                updateGroupMemberRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 14;
            onChanged();
            return updateGroupMemberRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest, im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder> queryUserProfilesRequestBuilder_;

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         *
         * @return Whether the queryUserProfilesRequest field is set.
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
         *
         * @return The queryUserProfilesRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getQueryUserProfilesRequest() {
            if (queryUserProfilesRequestBuilder_ == null) {
                if (kindCase_ == 100) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 100) {
                    return queryUserProfilesRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public Builder setQueryUserProfilesRequest(
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest value) {
            if (queryUserProfilesRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryUserProfilesRequestBuilder_.setMessage(value);
            }
            kindCase_ = 100;
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
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder builderForValue) {
            if (queryUserProfilesRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryUserProfilesRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 100;
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
                im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest value) {
            if (queryUserProfilesRequestBuilder_ == null) {
                if (kindCase_ == 100
                        && kind_ != im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 100) {
                    queryUserProfilesRequestBuilder_.mergeFrom(value);
                } else {
                    queryUserProfilesRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 100;
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
            if (queryUserProfilesRequestBuilder_ == null) {
                if (kindCase_ == 100) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 100) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryUserProfilesRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder getQueryUserProfilesRequestBuilder() {
            return getQueryUserProfilesRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder getQueryUserProfilesRequestOrBuilder() {
            if ((kindCase_ == 100) && (queryUserProfilesRequestBuilder_ != null)) {
                return queryUserProfilesRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 100) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest, im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder> getQueryUserProfilesRequestFieldBuilder() {
            if (queryUserProfilesRequestBuilder_ == null) {
                if (!(kindCase_ == 100)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest
                            .getDefaultInstance();
                }
                queryUserProfilesRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 100;
            onChanged();
            return queryUserProfilesRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest, im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder> queryNearbyUsersRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         *
         * @return Whether the queryNearbyUsersRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryNearbyUsersRequest() {
            return kindCase_ == 101;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         *
         * @return The queryNearbyUsersRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getQueryNearbyUsersRequest() {
            if (queryNearbyUsersRequestBuilder_ == null) {
                if (kindCase_ == 101) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 101) {
                    return queryNearbyUsersRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder setQueryNearbyUsersRequest(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest value) {
            if (queryNearbyUsersRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryNearbyUsersRequestBuilder_.setMessage(value);
            }
            kindCase_ = 101;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder setQueryNearbyUsersRequest(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder builderForValue) {
            if (queryNearbyUsersRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryNearbyUsersRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 101;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder mergeQueryNearbyUsersRequest(
                im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest value) {
            if (queryNearbyUsersRequestBuilder_ == null) {
                if (kindCase_ == 101
                        && kind_ != im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 101) {
                    queryNearbyUsersRequestBuilder_.mergeFrom(value);
                } else {
                    queryNearbyUsersRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 101;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public Builder clearQueryNearbyUsersRequest() {
            if (queryNearbyUsersRequestBuilder_ == null) {
                if (kindCase_ == 101) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 101) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryNearbyUsersRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder getQueryNearbyUsersRequestBuilder() {
            return getQueryNearbyUsersRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder getQueryNearbyUsersRequestOrBuilder() {
            if ((kindCase_ == 101) && (queryNearbyUsersRequestBuilder_ != null)) {
                return queryNearbyUsersRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 101) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest, im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder> getQueryNearbyUsersRequestFieldBuilder() {
            if (queryNearbyUsersRequestBuilder_ == null) {
                if (!(kindCase_ == 101)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest
                            .getDefaultInstance();
                }
                queryNearbyUsersRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 101;
            onChanged();
            return queryNearbyUsersRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest, im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOrBuilder> queryUserOnlineStatusesRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         *
         * @return Whether the queryUserOnlineStatusesRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryUserOnlineStatusesRequest() {
            return kindCase_ == 102;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         *
         * @return The queryUserOnlineStatusesRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest getQueryUserOnlineStatusesRequest() {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                if (kindCase_ == 102) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 102) {
                    return queryUserOnlineStatusesRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder setQueryUserOnlineStatusesRequest(
                im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest value) {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryUserOnlineStatusesRequestBuilder_.setMessage(value);
            }
            kindCase_ = 102;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder setQueryUserOnlineStatusesRequest(
                im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest.Builder builderForValue) {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryUserOnlineStatusesRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 102;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder mergeQueryUserOnlineStatusesRequest(
                im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest value) {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                if (kindCase_ == 102
                        && kind_ != im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 102) {
                    queryUserOnlineStatusesRequestBuilder_.mergeFrom(value);
                } else {
                    queryUserOnlineStatusesRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 102;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public Builder clearQueryUserOnlineStatusesRequest() {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                if (kindCase_ == 102) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 102) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryUserOnlineStatusesRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest.Builder getQueryUserOnlineStatusesRequestBuilder() {
            return getQueryUserOnlineStatusesRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOrBuilder getQueryUserOnlineStatusesRequestOrBuilder() {
            if ((kindCase_ == 102) && (queryUserOnlineStatusesRequestBuilder_ != null)) {
                return queryUserOnlineStatusesRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 102) {
                    return (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest, im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest.Builder, im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOrBuilder> getQueryUserOnlineStatusesRequestFieldBuilder() {
            if (queryUserOnlineStatusesRequestBuilder_ == null) {
                if (!(kindCase_ == 102)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest
                            .getDefaultInstance();
                }
                queryUserOnlineStatusesRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 102;
            onChanged();
            return queryUserOnlineStatusesRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOrBuilder> updateUserLocationRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         *
         * @return Whether the updateUserLocationRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateUserLocationRequest() {
            return kindCase_ == 103;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         *
         * @return The updateUserLocationRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest getUpdateUserLocationRequest() {
            if (updateUserLocationRequestBuilder_ == null) {
                if (kindCase_ == 103) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 103) {
                    return updateUserLocationRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder setUpdateUserLocationRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest value) {
            if (updateUserLocationRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateUserLocationRequestBuilder_.setMessage(value);
            }
            kindCase_ = 103;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder setUpdateUserLocationRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest.Builder builderForValue) {
            if (updateUserLocationRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateUserLocationRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 103;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder mergeUpdateUserLocationRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest value) {
            if (updateUserLocationRequestBuilder_ == null) {
                if (kindCase_ == 103
                        && kind_ != im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 103) {
                    updateUserLocationRequestBuilder_.mergeFrom(value);
                } else {
                    updateUserLocationRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 103;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public Builder clearUpdateUserLocationRequest() {
            if (updateUserLocationRequestBuilder_ == null) {
                if (kindCase_ == 103) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 103) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateUserLocationRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest.Builder getUpdateUserLocationRequestBuilder() {
            return getUpdateUserLocationRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOrBuilder getUpdateUserLocationRequestOrBuilder() {
            if ((kindCase_ == 103) && (updateUserLocationRequestBuilder_ != null)) {
                return updateUserLocationRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 103) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOrBuilder> getUpdateUserLocationRequestFieldBuilder() {
            if (updateUserLocationRequestBuilder_ == null) {
                if (!(kindCase_ == 103)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest
                            .getDefaultInstance();
                }
                updateUserLocationRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 103;
            onChanged();
            return updateUserLocationRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder> updateUserOnlineStatusRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         *
         * @return Whether the updateUserOnlineStatusRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateUserOnlineStatusRequest() {
            return kindCase_ == 104;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         *
         * @return The updateUserOnlineStatusRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getUpdateUserOnlineStatusRequest() {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                if (kindCase_ == 104) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 104) {
                    return updateUserOnlineStatusRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder setUpdateUserOnlineStatusRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest value) {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateUserOnlineStatusRequestBuilder_.setMessage(value);
            }
            kindCase_ = 104;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder setUpdateUserOnlineStatusRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder builderForValue) {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateUserOnlineStatusRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 104;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder mergeUpdateUserOnlineStatusRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest value) {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                if (kindCase_ == 104
                        && kind_ != im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 104) {
                    updateUserOnlineStatusRequestBuilder_.mergeFrom(value);
                } else {
                    updateUserOnlineStatusRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 104;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public Builder clearUpdateUserOnlineStatusRequest() {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                if (kindCase_ == 104) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 104) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateUserOnlineStatusRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder getUpdateUserOnlineStatusRequestBuilder() {
            return getUpdateUserOnlineStatusRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder getUpdateUserOnlineStatusRequestOrBuilder() {
            if ((kindCase_ == 104) && (updateUserOnlineStatusRequestBuilder_ != null)) {
                return updateUserOnlineStatusRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 104) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder> getUpdateUserOnlineStatusRequestFieldBuilder() {
            if (updateUserOnlineStatusRequestBuilder_ == null) {
                if (!(kindCase_ == 104)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                            .getDefaultInstance();
                }
                updateUserOnlineStatusRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 104;
            onChanged();
            return updateUserOnlineStatusRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOrBuilder> updateUserRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         *
         * @return Whether the updateUserRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateUserRequest() {
            return kindCase_ == 105;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         *
         * @return The updateUserRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserRequest getUpdateUserRequest() {
            if (updateUserRequestBuilder_ == null) {
                if (kindCase_ == 105) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 105) {
                    return updateUserRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder setUpdateUserRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserRequest value) {
            if (updateUserRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateUserRequestBuilder_.setMessage(value);
            }
            kindCase_ = 105;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder setUpdateUserRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserRequest.Builder builderForValue) {
            if (updateUserRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateUserRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 105;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder mergeUpdateUserRequest(
                im.turms.server.common.access.client.dto.request.user.UpdateUserRequest value) {
            if (updateUserRequestBuilder_ == null) {
                if (kindCase_ == 105
                        && kind_ != im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 105) {
                    updateUserRequestBuilder_.mergeFrom(value);
                } else {
                    updateUserRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 105;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public Builder clearUpdateUserRequest() {
            if (updateUserRequestBuilder_ == null) {
                if (kindCase_ == 105) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 105) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateUserRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.UpdateUserRequest.Builder getUpdateUserRequestBuilder() {
            return getUpdateUserRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOrBuilder getUpdateUserRequestOrBuilder() {
            if ((kindCase_ == 105) && (updateUserRequestBuilder_ != null)) {
                return updateUserRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 105) {
                    return (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.UpdateUserRequest, im.turms.server.common.access.client.dto.request.user.UpdateUserRequest.Builder, im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOrBuilder> getUpdateUserRequestFieldBuilder() {
            if (updateUserRequestBuilder_ == null) {
                if (!(kindCase_ == 105)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.UpdateUserRequest
                            .getDefaultInstance();
                }
                updateUserRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.UpdateUserRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 105;
            onChanged();
            return updateUserRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOrBuilder> createFriendRequestRequestBuilder_;

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         *
         * @return Whether the createFriendRequestRequest field is set.
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
         *
         * @return The createFriendRequestRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest getCreateFriendRequestRequest() {
            if (createFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 200) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 200) {
                    return createFriendRequestRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public Builder setCreateFriendRequestRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest value) {
            if (createFriendRequestRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createFriendRequestRequestBuilder_.setMessage(value);
            }
            kindCase_ = 200;
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
                im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest.Builder builderForValue) {
            if (createFriendRequestRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createFriendRequestRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 200;
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
                im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest value) {
            if (createFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 200
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 200) {
                    createFriendRequestRequestBuilder_.mergeFrom(value);
                } else {
                    createFriendRequestRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 200;
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
            if (createFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 200) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 200) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createFriendRequestRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest.Builder getCreateFriendRequestRequestBuilder() {
            return getCreateFriendRequestRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOrBuilder getCreateFriendRequestRequestOrBuilder() {
            if ((kindCase_ == 200) && (createFriendRequestRequestBuilder_ != null)) {
                return createFriendRequestRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 200) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * User Relationship
         * </pre>
         * 
         * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOrBuilder> getCreateFriendRequestRequestFieldBuilder() {
            if (createFriendRequestRequestBuilder_ == null) {
                if (!(kindCase_ == 200)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest
                            .getDefaultInstance();
                }
                createFriendRequestRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 200;
            onChanged();
            return createFriendRequestRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOrBuilder> createRelationshipGroupRequestBuilder_;

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         *
         * @return Whether the createRelationshipGroupRequest field is set.
         */
        @java.lang.Override
        public boolean hasCreateRelationshipGroupRequest() {
            return kindCase_ == 201;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         *
         * @return The createRelationshipGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest getCreateRelationshipGroupRequest() {
            if (createRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 201) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 201) {
                    return createRelationshipGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder setCreateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest value) {
            if (createRelationshipGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createRelationshipGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 201;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder setCreateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest.Builder builderForValue) {
            if (createRelationshipGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createRelationshipGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 201;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder mergeCreateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest value) {
            if (createRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 201
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 201) {
                    createRelationshipGroupRequestBuilder_.mergeFrom(value);
                } else {
                    createRelationshipGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 201;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public Builder clearCreateRelationshipGroupRequest() {
            if (createRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 201) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 201) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createRelationshipGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest.Builder getCreateRelationshipGroupRequestBuilder() {
            return getCreateRelationshipGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOrBuilder getCreateRelationshipGroupRequestOrBuilder() {
            if ((kindCase_ == 201) && (createRelationshipGroupRequestBuilder_ != null)) {
                return createRelationshipGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 201) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOrBuilder> getCreateRelationshipGroupRequestFieldBuilder() {
            if (createRelationshipGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 201)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest
                            .getDefaultInstance();
                }
                createRelationshipGroupRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 201;
            onChanged();
            return createRelationshipGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOrBuilder> createRelationshipRequestBuilder_;

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         *
         * @return Whether the createRelationshipRequest field is set.
         */
        @java.lang.Override
        public boolean hasCreateRelationshipRequest() {
            return kindCase_ == 202;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         *
         * @return The createRelationshipRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest getCreateRelationshipRequest() {
            if (createRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 202) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 202) {
                    return createRelationshipRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder setCreateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest value) {
            if (createRelationshipRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createRelationshipRequestBuilder_.setMessage(value);
            }
            kindCase_ = 202;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder setCreateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest.Builder builderForValue) {
            if (createRelationshipRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createRelationshipRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 202;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder mergeCreateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest value) {
            if (createRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 202
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 202) {
                    createRelationshipRequestBuilder_.mergeFrom(value);
                } else {
                    createRelationshipRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 202;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public Builder clearCreateRelationshipRequest() {
            if (createRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 202) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 202) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createRelationshipRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest.Builder getCreateRelationshipRequestBuilder() {
            return getCreateRelationshipRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOrBuilder getCreateRelationshipRequestOrBuilder() {
            if ((kindCase_ == 202) && (createRelationshipRequestBuilder_ != null)) {
                return createRelationshipRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 202) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOrBuilder> getCreateRelationshipRequestFieldBuilder() {
            if (createRelationshipRequestBuilder_ == null) {
                if (!(kindCase_ == 202)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest
                            .getDefaultInstance();
                }
                createRelationshipRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 202;
            onChanged();
            return createRelationshipRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOrBuilder> deleteRelationshipGroupRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         *
         * @return Whether the deleteRelationshipGroupRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteRelationshipGroupRequest() {
            return kindCase_ == 203;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         *
         * @return The deleteRelationshipGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest getDeleteRelationshipGroupRequest() {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 203) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 203) {
                    return deleteRelationshipGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder setDeleteRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest value) {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteRelationshipGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 203;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder setDeleteRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest.Builder builderForValue) {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteRelationshipGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 203;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder mergeDeleteRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest value) {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 203
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 203) {
                    deleteRelationshipGroupRequestBuilder_.mergeFrom(value);
                } else {
                    deleteRelationshipGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 203;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public Builder clearDeleteRelationshipGroupRequest() {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 203) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 203) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteRelationshipGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest.Builder getDeleteRelationshipGroupRequestBuilder() {
            return getDeleteRelationshipGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOrBuilder getDeleteRelationshipGroupRequestOrBuilder() {
            if ((kindCase_ == 203) && (deleteRelationshipGroupRequestBuilder_ != null)) {
                return deleteRelationshipGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 203) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOrBuilder> getDeleteRelationshipGroupRequestFieldBuilder() {
            if (deleteRelationshipGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 203)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest
                            .getDefaultInstance();
                }
                deleteRelationshipGroupRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 203;
            onChanged();
            return deleteRelationshipGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOrBuilder> deleteRelationshipRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         *
         * @return Whether the deleteRelationshipRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteRelationshipRequest() {
            return kindCase_ == 204;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         *
         * @return The deleteRelationshipRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest getDeleteRelationshipRequest() {
            if (deleteRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 204) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 204) {
                    return deleteRelationshipRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder setDeleteRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest value) {
            if (deleteRelationshipRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteRelationshipRequestBuilder_.setMessage(value);
            }
            kindCase_ = 204;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder setDeleteRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest.Builder builderForValue) {
            if (deleteRelationshipRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteRelationshipRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 204;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder mergeDeleteRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest value) {
            if (deleteRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 204
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 204) {
                    deleteRelationshipRequestBuilder_.mergeFrom(value);
                } else {
                    deleteRelationshipRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 204;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public Builder clearDeleteRelationshipRequest() {
            if (deleteRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 204) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 204) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteRelationshipRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest.Builder getDeleteRelationshipRequestBuilder() {
            return getDeleteRelationshipRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOrBuilder getDeleteRelationshipRequestOrBuilder() {
            if ((kindCase_ == 204) && (deleteRelationshipRequestBuilder_ != null)) {
                return deleteRelationshipRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 204) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOrBuilder> getDeleteRelationshipRequestFieldBuilder() {
            if (deleteRelationshipRequestBuilder_ == null) {
                if (!(kindCase_ == 204)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest
                            .getDefaultInstance();
                }
                deleteRelationshipRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 204;
            onChanged();
            return deleteRelationshipRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOrBuilder> queryFriendRequestsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         *
         * @return Whether the queryFriendRequestsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryFriendRequestsRequest() {
            return kindCase_ == 205;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         *
         * @return The queryFriendRequestsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest getQueryFriendRequestsRequest() {
            if (queryFriendRequestsRequestBuilder_ == null) {
                if (kindCase_ == 205) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 205) {
                    return queryFriendRequestsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder setQueryFriendRequestsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest value) {
            if (queryFriendRequestsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryFriendRequestsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 205;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder setQueryFriendRequestsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest.Builder builderForValue) {
            if (queryFriendRequestsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryFriendRequestsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 205;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder mergeQueryFriendRequestsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest value) {
            if (queryFriendRequestsRequestBuilder_ == null) {
                if (kindCase_ == 205
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 205) {
                    queryFriendRequestsRequestBuilder_.mergeFrom(value);
                } else {
                    queryFriendRequestsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 205;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public Builder clearQueryFriendRequestsRequest() {
            if (queryFriendRequestsRequestBuilder_ == null) {
                if (kindCase_ == 205) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 205) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryFriendRequestsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest.Builder getQueryFriendRequestsRequestBuilder() {
            return getQueryFriendRequestsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOrBuilder getQueryFriendRequestsRequestOrBuilder() {
            if ((kindCase_ == 205) && (queryFriendRequestsRequestBuilder_ != null)) {
                return queryFriendRequestsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 205) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOrBuilder> getQueryFriendRequestsRequestFieldBuilder() {
            if (queryFriendRequestsRequestBuilder_ == null) {
                if (!(kindCase_ == 205)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest
                            .getDefaultInstance();
                }
                queryFriendRequestsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 205;
            onChanged();
            return queryFriendRequestsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOrBuilder> queryRelatedUserIdsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         *
         * @return Whether the queryRelatedUserIdsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryRelatedUserIdsRequest() {
            return kindCase_ == 206;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         *
         * @return The queryRelatedUserIdsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest getQueryRelatedUserIdsRequest() {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 206) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 206) {
                    return queryRelatedUserIdsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder setQueryRelatedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest value) {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryRelatedUserIdsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 206;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder setQueryRelatedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest.Builder builderForValue) {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryRelatedUserIdsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 206;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder mergeQueryRelatedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest value) {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 206
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 206) {
                    queryRelatedUserIdsRequestBuilder_.mergeFrom(value);
                } else {
                    queryRelatedUserIdsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 206;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public Builder clearQueryRelatedUserIdsRequest() {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 206) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 206) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryRelatedUserIdsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest.Builder getQueryRelatedUserIdsRequestBuilder() {
            return getQueryRelatedUserIdsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOrBuilder getQueryRelatedUserIdsRequestOrBuilder() {
            if ((kindCase_ == 206) && (queryRelatedUserIdsRequestBuilder_ != null)) {
                return queryRelatedUserIdsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 206) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOrBuilder> getQueryRelatedUserIdsRequestFieldBuilder() {
            if (queryRelatedUserIdsRequestBuilder_ == null) {
                if (!(kindCase_ == 206)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest
                            .getDefaultInstance();
                }
                queryRelatedUserIdsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 206;
            onChanged();
            return queryRelatedUserIdsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOrBuilder> queryRelationshipGroupsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         *
         * @return Whether the queryRelationshipGroupsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryRelationshipGroupsRequest() {
            return kindCase_ == 207;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         *
         * @return The queryRelationshipGroupsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest getQueryRelationshipGroupsRequest() {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                if (kindCase_ == 207) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 207) {
                    return queryRelationshipGroupsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder setQueryRelationshipGroupsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest value) {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryRelationshipGroupsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 207;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder setQueryRelationshipGroupsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest.Builder builderForValue) {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryRelationshipGroupsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 207;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder mergeQueryRelationshipGroupsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest value) {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                if (kindCase_ == 207
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 207) {
                    queryRelationshipGroupsRequestBuilder_.mergeFrom(value);
                } else {
                    queryRelationshipGroupsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 207;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public Builder clearQueryRelationshipGroupsRequest() {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                if (kindCase_ == 207) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 207) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryRelationshipGroupsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest.Builder getQueryRelationshipGroupsRequestBuilder() {
            return getQueryRelationshipGroupsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOrBuilder getQueryRelationshipGroupsRequestOrBuilder() {
            if ((kindCase_ == 207) && (queryRelationshipGroupsRequestBuilder_ != null)) {
                return queryRelationshipGroupsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 207) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOrBuilder> getQueryRelationshipGroupsRequestFieldBuilder() {
            if (queryRelationshipGroupsRequestBuilder_ == null) {
                if (!(kindCase_ == 207)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest
                            .getDefaultInstance();
                }
                queryRelationshipGroupsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 207;
            onChanged();
            return queryRelationshipGroupsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOrBuilder> queryRelationshipsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         *
         * @return Whether the queryRelationshipsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryRelationshipsRequest() {
            return kindCase_ == 208;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         *
         * @return The queryRelationshipsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest getQueryRelationshipsRequest() {
            if (queryRelationshipsRequestBuilder_ == null) {
                if (kindCase_ == 208) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 208) {
                    return queryRelationshipsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder setQueryRelationshipsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest value) {
            if (queryRelationshipsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryRelationshipsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 208;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder setQueryRelationshipsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest.Builder builderForValue) {
            if (queryRelationshipsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryRelationshipsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 208;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder mergeQueryRelationshipsRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest value) {
            if (queryRelationshipsRequestBuilder_ == null) {
                if (kindCase_ == 208
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 208) {
                    queryRelationshipsRequestBuilder_.mergeFrom(value);
                } else {
                    queryRelationshipsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 208;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public Builder clearQueryRelationshipsRequest() {
            if (queryRelationshipsRequestBuilder_ == null) {
                if (kindCase_ == 208) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 208) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryRelationshipsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest.Builder getQueryRelationshipsRequestBuilder() {
            return getQueryRelationshipsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOrBuilder getQueryRelationshipsRequestOrBuilder() {
            if ((kindCase_ == 208) && (queryRelationshipsRequestBuilder_ != null)) {
                return queryRelationshipsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 208) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOrBuilder> getQueryRelationshipsRequestFieldBuilder() {
            if (queryRelationshipsRequestBuilder_ == null) {
                if (!(kindCase_ == 208)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest
                            .getDefaultInstance();
                }
                queryRelationshipsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 208;
            onChanged();
            return queryRelationshipsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOrBuilder> updateFriendRequestRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         *
         * @return Whether the updateFriendRequestRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateFriendRequestRequest() {
            return kindCase_ == 209;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         *
         * @return The updateFriendRequestRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest getUpdateFriendRequestRequest() {
            if (updateFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 209) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 209) {
                    return updateFriendRequestRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder setUpdateFriendRequestRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest value) {
            if (updateFriendRequestRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateFriendRequestRequestBuilder_.setMessage(value);
            }
            kindCase_ = 209;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder setUpdateFriendRequestRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest.Builder builderForValue) {
            if (updateFriendRequestRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateFriendRequestRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 209;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder mergeUpdateFriendRequestRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest value) {
            if (updateFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 209
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 209) {
                    updateFriendRequestRequestBuilder_.mergeFrom(value);
                } else {
                    updateFriendRequestRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 209;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public Builder clearUpdateFriendRequestRequest() {
            if (updateFriendRequestRequestBuilder_ == null) {
                if (kindCase_ == 209) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 209) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateFriendRequestRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest.Builder getUpdateFriendRequestRequestBuilder() {
            return getUpdateFriendRequestRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOrBuilder getUpdateFriendRequestRequestOrBuilder() {
            if ((kindCase_ == 209) && (updateFriendRequestRequestBuilder_ != null)) {
                return updateFriendRequestRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 209) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOrBuilder> getUpdateFriendRequestRequestFieldBuilder() {
            if (updateFriendRequestRequestBuilder_ == null) {
                if (!(kindCase_ == 209)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest
                            .getDefaultInstance();
                }
                updateFriendRequestRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 209;
            onChanged();
            return updateFriendRequestRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder> updateRelationshipGroupRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         *
         * @return Whether the updateRelationshipGroupRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateRelationshipGroupRequest() {
            return kindCase_ == 210;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         *
         * @return The updateRelationshipGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest getUpdateRelationshipGroupRequest() {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 210) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 210) {
                    return updateRelationshipGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder setUpdateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest value) {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateRelationshipGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 210;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder setUpdateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest.Builder builderForValue) {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateRelationshipGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 210;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder mergeUpdateRelationshipGroupRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest value) {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 210
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 210) {
                    updateRelationshipGroupRequestBuilder_.mergeFrom(value);
                } else {
                    updateRelationshipGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 210;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public Builder clearUpdateRelationshipGroupRequest() {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                if (kindCase_ == 210) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 210) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateRelationshipGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest.Builder getUpdateRelationshipGroupRequestBuilder() {
            return getUpdateRelationshipGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder getUpdateRelationshipGroupRequestOrBuilder() {
            if ((kindCase_ == 210) && (updateRelationshipGroupRequestBuilder_ != null)) {
                return updateRelationshipGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 210) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder> getUpdateRelationshipGroupRequestFieldBuilder() {
            if (updateRelationshipGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 210)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest
                            .getDefaultInstance();
                }
                updateRelationshipGroupRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 210;
            onChanged();
            return updateRelationshipGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder> updateRelationshipRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         *
         * @return Whether the updateRelationshipRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateRelationshipRequest() {
            return kindCase_ == 211;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         *
         * @return The updateRelationshipRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getUpdateRelationshipRequest() {
            if (updateRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 211) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 211) {
                    return updateRelationshipRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder setUpdateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest value) {
            if (updateRelationshipRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateRelationshipRequestBuilder_.setMessage(value);
            }
            kindCase_ = 211;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder setUpdateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder builderForValue) {
            if (updateRelationshipRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateRelationshipRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 211;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder mergeUpdateRelationshipRequest(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest value) {
            if (updateRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 211
                        && kind_ != im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 211) {
                    updateRelationshipRequestBuilder_.mergeFrom(value);
                } else {
                    updateRelationshipRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 211;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public Builder clearUpdateRelationshipRequest() {
            if (updateRelationshipRequestBuilder_ == null) {
                if (kindCase_ == 211) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 211) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateRelationshipRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder getUpdateRelationshipRequestBuilder() {
            return getUpdateRelationshipRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder getUpdateRelationshipRequestOrBuilder() {
            if ((kindCase_ == 211) && (updateRelationshipRequestBuilder_ != null)) {
                return updateRelationshipRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 211) {
                    return (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder, im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder> getUpdateRelationshipRequestFieldBuilder() {
            if (updateRelationshipRequestBuilder_ == null) {
                if (!(kindCase_ == 211)) {
                    kind_ = im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                            .getDefaultInstance();
                }
                updateRelationshipRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 211;
            onChanged();
            return updateRelationshipRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.CreateGroupRequest, im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder> createGroupRequestBuilder_;

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         *
         * @return Whether the createGroupRequest field is set.
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
         *
         * @return The createGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getCreateGroupRequest() {
            if (createGroupRequestBuilder_ == null) {
                if (kindCase_ == 300) {
                    return (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 300) {
                    return createGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public Builder setCreateGroupRequest(
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest value) {
            if (createGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 300;
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
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder builderForValue) {
            if (createGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 300;
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
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest value) {
            if (createGroupRequestBuilder_ == null) {
                if (kindCase_ == 300
                        && kind_ != im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 300) {
                    createGroupRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 300;
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
            if (createGroupRequestBuilder_ == null) {
                if (kindCase_ == 300) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 300) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder getCreateGroupRequestBuilder() {
            return getCreateGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder getCreateGroupRequestOrBuilder() {
            if ((kindCase_ == 300) && (createGroupRequestBuilder_ != null)) {
                return createGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 300) {
                    return (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.CreateGroupRequest, im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder> getCreateGroupRequestFieldBuilder() {
            if (createGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 300)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                            .getDefaultInstance();
                }
                createGroupRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 300;
            onChanged();
            return createGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest, im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOrBuilder> deleteGroupRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         *
         * @return Whether the deleteGroupRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupRequest() {
            return kindCase_ == 301;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         *
         * @return The deleteGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest getDeleteGroupRequest() {
            if (deleteGroupRequestBuilder_ == null) {
                if (kindCase_ == 301) {
                    return (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 301) {
                    return deleteGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder setDeleteGroupRequest(
                im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest value) {
            if (deleteGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 301;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder setDeleteGroupRequest(
                im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest.Builder builderForValue) {
            if (deleteGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 301;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder mergeDeleteGroupRequest(
                im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest value) {
            if (deleteGroupRequestBuilder_ == null) {
                if (kindCase_ == 301
                        && kind_ != im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 301) {
                    deleteGroupRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 301;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public Builder clearDeleteGroupRequest() {
            if (deleteGroupRequestBuilder_ == null) {
                if (kindCase_ == 301) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 301) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest.Builder getDeleteGroupRequestBuilder() {
            return getDeleteGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOrBuilder getDeleteGroupRequestOrBuilder() {
            if ((kindCase_ == 301) && (deleteGroupRequestBuilder_ != null)) {
                return deleteGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 301) {
                    return (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest, im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOrBuilder> getDeleteGroupRequestFieldBuilder() {
            if (deleteGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 301)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest
                            .getDefaultInstance();
                }
                deleteGroupRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 301;
            onChanged();
            return deleteGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest, im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOrBuilder> queryGroupsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         *
         * @return Whether the queryGroupsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupsRequest() {
            return kindCase_ == 302;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         *
         * @return The queryGroupsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest getQueryGroupsRequest() {
            if (queryGroupsRequestBuilder_ == null) {
                if (kindCase_ == 302) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 302) {
                    return queryGroupsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder setQueryGroupsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest value) {
            if (queryGroupsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 302;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder setQueryGroupsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest.Builder builderForValue) {
            if (queryGroupsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 302;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder mergeQueryGroupsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest value) {
            if (queryGroupsRequestBuilder_ == null) {
                if (kindCase_ == 302
                        && kind_ != im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 302) {
                    queryGroupsRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 302;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public Builder clearQueryGroupsRequest() {
            if (queryGroupsRequestBuilder_ == null) {
                if (kindCase_ == 302) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 302) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest.Builder getQueryGroupsRequestBuilder() {
            return getQueryGroupsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOrBuilder getQueryGroupsRequestOrBuilder() {
            if ((kindCase_ == 302) && (queryGroupsRequestBuilder_ != null)) {
                return queryGroupsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 302) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest, im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOrBuilder> getQueryGroupsRequestFieldBuilder() {
            if (queryGroupsRequestBuilder_ == null) {
                if (!(kindCase_ == 302)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest
                            .getDefaultInstance();
                }
                queryGroupsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 302;
            onChanged();
            return queryGroupsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOrBuilder> queryJoinedGroupIdsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         *
         * @return Whether the queryJoinedGroupIdsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryJoinedGroupIdsRequest() {
            return kindCase_ == 303;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         *
         * @return The queryJoinedGroupIdsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest getQueryJoinedGroupIdsRequest() {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                if (kindCase_ == 303) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 303) {
                    return queryJoinedGroupIdsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder setQueryJoinedGroupIdsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest value) {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryJoinedGroupIdsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 303;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder setQueryJoinedGroupIdsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest.Builder builderForValue) {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryJoinedGroupIdsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 303;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder mergeQueryJoinedGroupIdsRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest value) {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                if (kindCase_ == 303
                        && kind_ != im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 303) {
                    queryJoinedGroupIdsRequestBuilder_.mergeFrom(value);
                } else {
                    queryJoinedGroupIdsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 303;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public Builder clearQueryJoinedGroupIdsRequest() {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                if (kindCase_ == 303) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 303) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryJoinedGroupIdsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest.Builder getQueryJoinedGroupIdsRequestBuilder() {
            return getQueryJoinedGroupIdsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOrBuilder getQueryJoinedGroupIdsRequestOrBuilder() {
            if ((kindCase_ == 303) && (queryJoinedGroupIdsRequestBuilder_ != null)) {
                return queryJoinedGroupIdsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 303) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOrBuilder> getQueryJoinedGroupIdsRequestFieldBuilder() {
            if (queryJoinedGroupIdsRequestBuilder_ == null) {
                if (!(kindCase_ == 303)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest
                            .getDefaultInstance();
                }
                queryJoinedGroupIdsRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 303;
            onChanged();
            return queryJoinedGroupIdsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOrBuilder> queryJoinedGroupInfosRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         *
         * @return Whether the queryJoinedGroupInfosRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryJoinedGroupInfosRequest() {
            return kindCase_ == 304;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         *
         * @return The queryJoinedGroupInfosRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest getQueryJoinedGroupInfosRequest() {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                if (kindCase_ == 304) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 304) {
                    return queryJoinedGroupInfosRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder setQueryJoinedGroupInfosRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest value) {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryJoinedGroupInfosRequestBuilder_.setMessage(value);
            }
            kindCase_ = 304;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder setQueryJoinedGroupInfosRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest.Builder builderForValue) {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryJoinedGroupInfosRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 304;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder mergeQueryJoinedGroupInfosRequest(
                im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest value) {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                if (kindCase_ == 304
                        && kind_ != im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 304) {
                    queryJoinedGroupInfosRequestBuilder_.mergeFrom(value);
                } else {
                    queryJoinedGroupInfosRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 304;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public Builder clearQueryJoinedGroupInfosRequest() {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                if (kindCase_ == 304) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 304) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryJoinedGroupInfosRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest.Builder getQueryJoinedGroupInfosRequestBuilder() {
            return getQueryJoinedGroupInfosRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOrBuilder getQueryJoinedGroupInfosRequestOrBuilder() {
            if ((kindCase_ == 304) && (queryJoinedGroupInfosRequestBuilder_ != null)) {
                return queryJoinedGroupInfosRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 304) {
                    return (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest.Builder, im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOrBuilder> getQueryJoinedGroupInfosRequestFieldBuilder() {
            if (queryJoinedGroupInfosRequestBuilder_ == null) {
                if (!(kindCase_ == 304)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest
                            .getDefaultInstance();
                }
                queryJoinedGroupInfosRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 304;
            onChanged();
            return queryJoinedGroupInfosRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest, im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOrBuilder> updateGroupRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         *
         * @return Whether the updateGroupRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateGroupRequest() {
            return kindCase_ == 305;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         *
         * @return The updateGroupRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest getUpdateGroupRequest() {
            if (updateGroupRequestBuilder_ == null) {
                if (kindCase_ == 305) {
                    return (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 305) {
                    return updateGroupRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder setUpdateGroupRequest(
                im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest value) {
            if (updateGroupRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateGroupRequestBuilder_.setMessage(value);
            }
            kindCase_ = 305;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder setUpdateGroupRequest(
                im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest.Builder builderForValue) {
            if (updateGroupRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateGroupRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 305;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder mergeUpdateGroupRequest(
                im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest value) {
            if (updateGroupRequestBuilder_ == null) {
                if (kindCase_ == 305
                        && kind_ != im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 305) {
                    updateGroupRequestBuilder_.mergeFrom(value);
                } else {
                    updateGroupRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 305;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public Builder clearUpdateGroupRequest() {
            if (updateGroupRequestBuilder_ == null) {
                if (kindCase_ == 305) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 305) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateGroupRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest.Builder getUpdateGroupRequestBuilder() {
            return getUpdateGroupRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOrBuilder getUpdateGroupRequestOrBuilder() {
            if ((kindCase_ == 305) && (updateGroupRequestBuilder_ != null)) {
                return updateGroupRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 305) {
                    return (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest, im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest.Builder, im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOrBuilder> getUpdateGroupRequestFieldBuilder() {
            if (updateGroupRequestBuilder_ == null) {
                if (!(kindCase_ == 305)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest
                            .getDefaultInstance();
                }
                updateGroupRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 305;
            onChanged();
            return updateGroupRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest, im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOrBuilder> createGroupBlockedUserRequestBuilder_;

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         *
         * @return Whether the createGroupBlockedUserRequest field is set.
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
         *
         * @return The createGroupBlockedUserRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest getCreateGroupBlockedUserRequest() {
            if (createGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 400) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 400) {
                    return createGroupBlockedUserRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public Builder setCreateGroupBlockedUserRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
            if (createGroupBlockedUserRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupBlockedUserRequestBuilder_.setMessage(value);
            }
            kindCase_ = 400;
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
                im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest.Builder builderForValue) {
            if (createGroupBlockedUserRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupBlockedUserRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 400;
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
                im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest value) {
            if (createGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 400
                        && kind_ != im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 400) {
                    createGroupBlockedUserRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupBlockedUserRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 400;
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
            if (createGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 400) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 400) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupBlockedUserRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest.Builder getCreateGroupBlockedUserRequestBuilder() {
            return getCreateGroupBlockedUserRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOrBuilder getCreateGroupBlockedUserRequestOrBuilder() {
            if ((kindCase_ == 400) && (createGroupBlockedUserRequestBuilder_ != null)) {
                return createGroupBlockedUserRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 400) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Blocklist
         * </pre>
         * 
         * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest, im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOrBuilder> getCreateGroupBlockedUserRequestFieldBuilder() {
            if (createGroupBlockedUserRequestBuilder_ == null) {
                if (!(kindCase_ == 400)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest
                            .getDefaultInstance();
                }
                createGroupBlockedUserRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 400;
            onChanged();
            return createGroupBlockedUserRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest, im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOrBuilder> deleteGroupBlockedUserRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         *
         * @return Whether the deleteGroupBlockedUserRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupBlockedUserRequest() {
            return kindCase_ == 401;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         *
         * @return The deleteGroupBlockedUserRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest getDeleteGroupBlockedUserRequest() {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 401) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 401) {
                    return deleteGroupBlockedUserRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder setDeleteGroupBlockedUserRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupBlockedUserRequestBuilder_.setMessage(value);
            }
            kindCase_ = 401;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder setDeleteGroupBlockedUserRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest.Builder builderForValue) {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupBlockedUserRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 401;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder mergeDeleteGroupBlockedUserRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest value) {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 401
                        && kind_ != im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 401) {
                    deleteGroupBlockedUserRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupBlockedUserRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 401;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public Builder clearDeleteGroupBlockedUserRequest() {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                if (kindCase_ == 401) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 401) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupBlockedUserRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest.Builder getDeleteGroupBlockedUserRequestBuilder() {
            return getDeleteGroupBlockedUserRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOrBuilder getDeleteGroupBlockedUserRequestOrBuilder() {
            if ((kindCase_ == 401) && (deleteGroupBlockedUserRequestBuilder_ != null)) {
                return deleteGroupBlockedUserRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 401) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest, im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOrBuilder> getDeleteGroupBlockedUserRequestFieldBuilder() {
            if (deleteGroupBlockedUserRequestBuilder_ == null) {
                if (!(kindCase_ == 401)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest
                            .getDefaultInstance();
                }
                deleteGroupBlockedUserRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 401;
            onChanged();
            return deleteGroupBlockedUserRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOrBuilder> queryGroupBlockedUserIdsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         *
         * @return Whether the queryGroupBlockedUserIdsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupBlockedUserIdsRequest() {
            return kindCase_ == 402;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         *
         * @return The queryGroupBlockedUserIdsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest getQueryGroupBlockedUserIdsRequest() {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 402) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 402) {
                    return queryGroupBlockedUserIdsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder setQueryGroupBlockedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupBlockedUserIdsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 402;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder setQueryGroupBlockedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.Builder builderForValue) {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupBlockedUserIdsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 402;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder mergeQueryGroupBlockedUserIdsRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest value) {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 402
                        && kind_ != im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 402) {
                    queryGroupBlockedUserIdsRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupBlockedUserIdsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 402;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public Builder clearQueryGroupBlockedUserIdsRequest() {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                if (kindCase_ == 402) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 402) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupBlockedUserIdsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.Builder getQueryGroupBlockedUserIdsRequestBuilder() {
            return getQueryGroupBlockedUserIdsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOrBuilder getQueryGroupBlockedUserIdsRequestOrBuilder() {
            if ((kindCase_ == 402) && (queryGroupBlockedUserIdsRequestBuilder_ != null)) {
                return queryGroupBlockedUserIdsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 402) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOrBuilder> getQueryGroupBlockedUserIdsRequestFieldBuilder() {
            if (queryGroupBlockedUserIdsRequestBuilder_ == null) {
                if (!(kindCase_ == 402)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest
                            .getDefaultInstance();
                }
                queryGroupBlockedUserIdsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 402;
            onChanged();
            return queryGroupBlockedUserIdsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOrBuilder> queryGroupBlockedUserInfosRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         *
         * @return Whether the queryGroupBlockedUserInfosRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupBlockedUserInfosRequest() {
            return kindCase_ == 403;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         *
         * @return The queryGroupBlockedUserInfosRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest getQueryGroupBlockedUserInfosRequest() {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                if (kindCase_ == 403) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 403) {
                    return queryGroupBlockedUserInfosRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder setQueryGroupBlockedUserInfosRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupBlockedUserInfosRequestBuilder_.setMessage(value);
            }
            kindCase_ = 403;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder setQueryGroupBlockedUserInfosRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.Builder builderForValue) {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupBlockedUserInfosRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 403;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder mergeQueryGroupBlockedUserInfosRequest(
                im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest value) {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                if (kindCase_ == 403
                        && kind_ != im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 403) {
                    queryGroupBlockedUserInfosRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupBlockedUserInfosRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 403;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public Builder clearQueryGroupBlockedUserInfosRequest() {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                if (kindCase_ == 403) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 403) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupBlockedUserInfosRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.Builder getQueryGroupBlockedUserInfosRequestBuilder() {
            return getQueryGroupBlockedUserInfosRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOrBuilder getQueryGroupBlockedUserInfosRequestOrBuilder() {
            if ((kindCase_ == 403) && (queryGroupBlockedUserInfosRequestBuilder_ != null)) {
                return queryGroupBlockedUserInfosRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 403) {
                    return (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest.Builder, im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOrBuilder> getQueryGroupBlockedUserInfosRequestFieldBuilder() {
            if (queryGroupBlockedUserInfosRequestBuilder_ == null) {
                if (!(kindCase_ == 403)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest
                            .getDefaultInstance();
                }
                queryGroupBlockedUserInfosRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 403;
            onChanged();
            return queryGroupBlockedUserInfosRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder> checkGroupJoinQuestionsAnswersRequestBuilder_;

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         *
         * @return Whether the checkGroupJoinQuestionsAnswersRequest field is set.
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
         *
         * @return The checkGroupJoinQuestionsAnswersRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getCheckGroupJoinQuestionsAnswersRequest() {
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                if (kindCase_ == 500) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 500) {
                    return checkGroupJoinQuestionsAnswersRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public Builder setCheckGroupJoinQuestionsAnswersRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                checkGroupJoinQuestionsAnswersRequestBuilder_.setMessage(value);
            }
            kindCase_ = 500;
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
                im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder builderForValue) {
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                checkGroupJoinQuestionsAnswersRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 500;
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
                im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest value) {
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                if (kindCase_ == 500
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 500) {
                    checkGroupJoinQuestionsAnswersRequestBuilder_.mergeFrom(value);
                } else {
                    checkGroupJoinQuestionsAnswersRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 500;
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
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                if (kindCase_ == 500) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 500) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                checkGroupJoinQuestionsAnswersRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder getCheckGroupJoinQuestionsAnswersRequestBuilder() {
            return getCheckGroupJoinQuestionsAnswersRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder getCheckGroupJoinQuestionsAnswersRequestOrBuilder() {
            if ((kindCase_ == 500) && (checkGroupJoinQuestionsAnswersRequestBuilder_ != null)) {
                return checkGroupJoinQuestionsAnswersRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 500) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Group Enrollment
         * </pre>
         * 
         * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder> getCheckGroupJoinQuestionsAnswersRequestFieldBuilder() {
            if (checkGroupJoinQuestionsAnswersRequestBuilder_ == null) {
                if (!(kindCase_ == 500)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                            .getDefaultInstance();
                }
                checkGroupJoinQuestionsAnswersRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 500;
            onChanged();
            return checkGroupJoinQuestionsAnswersRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOrBuilder> createGroupInvitationRequestBuilder_;

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         *
         * @return Whether the createGroupInvitationRequest field is set.
         */
        @java.lang.Override
        public boolean hasCreateGroupInvitationRequest() {
            return kindCase_ == 501;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         *
         * @return The createGroupInvitationRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest getCreateGroupInvitationRequest() {
            if (createGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 501) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 501) {
                    return createGroupInvitationRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder setCreateGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest value) {
            if (createGroupInvitationRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupInvitationRequestBuilder_.setMessage(value);
            }
            kindCase_ = 501;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder setCreateGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest.Builder builderForValue) {
            if (createGroupInvitationRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupInvitationRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 501;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder mergeCreateGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest value) {
            if (createGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 501
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 501) {
                    createGroupInvitationRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupInvitationRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 501;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public Builder clearCreateGroupInvitationRequest() {
            if (createGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 501) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 501) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupInvitationRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest.Builder getCreateGroupInvitationRequestBuilder() {
            return getCreateGroupInvitationRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOrBuilder getCreateGroupInvitationRequestOrBuilder() {
            if ((kindCase_ == 501) && (createGroupInvitationRequestBuilder_ != null)) {
                return createGroupInvitationRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 501) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOrBuilder> getCreateGroupInvitationRequestFieldBuilder() {
            if (createGroupInvitationRequestBuilder_ == null) {
                if (!(kindCase_ == 501)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest
                            .getDefaultInstance();
                }
                createGroupInvitationRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 501;
            onChanged();
            return createGroupInvitationRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOrBuilder> createGroupJoinRequestRequestBuilder_;

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         *
         * @return Whether the createGroupJoinRequestRequest field is set.
         */
        @java.lang.Override
        public boolean hasCreateGroupJoinRequestRequest() {
            return kindCase_ == 502;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         *
         * @return The createGroupJoinRequestRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest getCreateGroupJoinRequestRequest() {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 502) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 502) {
                    return createGroupJoinRequestRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder setCreateGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupJoinRequestRequestBuilder_.setMessage(value);
            }
            kindCase_ = 502;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder setCreateGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest.Builder builderForValue) {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupJoinRequestRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 502;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder mergeCreateGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest value) {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 502
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 502) {
                    createGroupJoinRequestRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupJoinRequestRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 502;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public Builder clearCreateGroupJoinRequestRequest() {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 502) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 502) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupJoinRequestRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest.Builder getCreateGroupJoinRequestRequestBuilder() {
            return getCreateGroupJoinRequestRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOrBuilder getCreateGroupJoinRequestRequestOrBuilder() {
            if ((kindCase_ == 502) && (createGroupJoinRequestRequestBuilder_ != null)) {
                return createGroupJoinRequestRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 502) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOrBuilder> getCreateGroupJoinRequestRequestFieldBuilder() {
            if (createGroupJoinRequestRequestBuilder_ == null) {
                if (!(kindCase_ == 502)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest
                            .getDefaultInstance();
                }
                createGroupJoinRequestRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 502;
            onChanged();
            return createGroupJoinRequestRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder> createGroupJoinQuestionsRequestBuilder_;

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         *
         * @return Whether the createGroupJoinQuestionsRequest field is set.
         */
        @java.lang.Override
        public boolean hasCreateGroupJoinQuestionsRequest() {
            return kindCase_ == 503;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         *
         * @return The createGroupJoinQuestionsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getCreateGroupJoinQuestionsRequest() {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 503) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 503) {
                    return createGroupJoinQuestionsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder setCreateGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                createGroupJoinQuestionsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 503;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder setCreateGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder builderForValue) {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                createGroupJoinQuestionsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 503;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder mergeCreateGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest value) {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 503
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 503) {
                    createGroupJoinQuestionsRequestBuilder_.mergeFrom(value);
                } else {
                    createGroupJoinQuestionsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 503;
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public Builder clearCreateGroupJoinQuestionsRequest() {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 503) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 503) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                createGroupJoinQuestionsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder getCreateGroupJoinQuestionsRequestBuilder() {
            return getCreateGroupJoinQuestionsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder getCreateGroupJoinQuestionsRequestOrBuilder() {
            if ((kindCase_ == 503) && (createGroupJoinQuestionsRequestBuilder_ != null)) {
                return createGroupJoinQuestionsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 503) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder> getCreateGroupJoinQuestionsRequestFieldBuilder() {
            if (createGroupJoinQuestionsRequestBuilder_ == null) {
                if (!(kindCase_ == 503)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                            .getDefaultInstance();
                }
                createGroupJoinQuestionsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 503;
            onChanged();
            return createGroupJoinQuestionsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOrBuilder> deleteGroupInvitationRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         *
         * @return Whether the deleteGroupInvitationRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupInvitationRequest() {
            return kindCase_ == 504;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         *
         * @return The deleteGroupInvitationRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest getDeleteGroupInvitationRequest() {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 504) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 504) {
                    return deleteGroupInvitationRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder setDeleteGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest value) {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupInvitationRequestBuilder_.setMessage(value);
            }
            kindCase_ = 504;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder setDeleteGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest.Builder builderForValue) {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupInvitationRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 504;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder mergeDeleteGroupInvitationRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest value) {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 504
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 504) {
                    deleteGroupInvitationRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupInvitationRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 504;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public Builder clearDeleteGroupInvitationRequest() {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                if (kindCase_ == 504) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 504) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupInvitationRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest.Builder getDeleteGroupInvitationRequestBuilder() {
            return getDeleteGroupInvitationRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOrBuilder getDeleteGroupInvitationRequestOrBuilder() {
            if ((kindCase_ == 504) && (deleteGroupInvitationRequestBuilder_ != null)) {
                return deleteGroupInvitationRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 504) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOrBuilder> getDeleteGroupInvitationRequestFieldBuilder() {
            if (deleteGroupInvitationRequestBuilder_ == null) {
                if (!(kindCase_ == 504)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest
                            .getDefaultInstance();
                }
                deleteGroupInvitationRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 504;
            onChanged();
            return deleteGroupInvitationRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder> deleteGroupJoinRequestRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         *
         * @return Whether the deleteGroupJoinRequestRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupJoinRequestRequest() {
            return kindCase_ == 505;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         *
         * @return The deleteGroupJoinRequestRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest getDeleteGroupJoinRequestRequest() {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 505) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 505) {
                    return deleteGroupJoinRequestRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder setDeleteGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupJoinRequestRequestBuilder_.setMessage(value);
            }
            kindCase_ = 505;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder setDeleteGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest.Builder builderForValue) {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupJoinRequestRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 505;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder mergeDeleteGroupJoinRequestRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest value) {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 505
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 505) {
                    deleteGroupJoinRequestRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupJoinRequestRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 505;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public Builder clearDeleteGroupJoinRequestRequest() {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                if (kindCase_ == 505) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 505) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupJoinRequestRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest.Builder getDeleteGroupJoinRequestRequestBuilder() {
            return getDeleteGroupJoinRequestRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder getDeleteGroupJoinRequestRequestOrBuilder() {
            if ((kindCase_ == 505) && (deleteGroupJoinRequestRequestBuilder_ != null)) {
                return deleteGroupJoinRequestRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 505) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder> getDeleteGroupJoinRequestRequestFieldBuilder() {
            if (deleteGroupJoinRequestRequestBuilder_ == null) {
                if (!(kindCase_ == 505)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest
                            .getDefaultInstance();
                }
                deleteGroupJoinRequestRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 505;
            onChanged();
            return deleteGroupJoinRequestRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder> deleteGroupJoinQuestionsRequestBuilder_;

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         *
         * @return Whether the deleteGroupJoinQuestionsRequest field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupJoinQuestionsRequest() {
            return kindCase_ == 506;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         *
         * @return The deleteGroupJoinQuestionsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDeleteGroupJoinQuestionsRequest() {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 506) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 506) {
                    return deleteGroupJoinQuestionsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder setDeleteGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteGroupJoinQuestionsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 506;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder setDeleteGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder builderForValue) {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteGroupJoinQuestionsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 506;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder mergeDeleteGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest value) {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 506
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 506) {
                    deleteGroupJoinQuestionsRequestBuilder_.mergeFrom(value);
                } else {
                    deleteGroupJoinQuestionsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 506;
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public Builder clearDeleteGroupJoinQuestionsRequest() {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 506) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 506) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteGroupJoinQuestionsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder getDeleteGroupJoinQuestionsRequestBuilder() {
            return getDeleteGroupJoinQuestionsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder getDeleteGroupJoinQuestionsRequestOrBuilder() {
            if ((kindCase_ == 506) && (deleteGroupJoinQuestionsRequestBuilder_ != null)) {
                return deleteGroupJoinQuestionsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 506) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder> getDeleteGroupJoinQuestionsRequestFieldBuilder() {
            if (deleteGroupJoinQuestionsRequestBuilder_ == null) {
                if (!(kindCase_ == 506)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                            .getDefaultInstance();
                }
                deleteGroupJoinQuestionsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 506;
            onChanged();
            return deleteGroupJoinQuestionsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOrBuilder> queryGroupInvitationsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         *
         * @return Whether the queryGroupInvitationsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupInvitationsRequest() {
            return kindCase_ == 507;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         *
         * @return The queryGroupInvitationsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest getQueryGroupInvitationsRequest() {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                if (kindCase_ == 507) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 507) {
                    return queryGroupInvitationsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder setQueryGroupInvitationsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest value) {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupInvitationsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 507;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder setQueryGroupInvitationsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest.Builder builderForValue) {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupInvitationsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 507;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder mergeQueryGroupInvitationsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest value) {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                if (kindCase_ == 507
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 507) {
                    queryGroupInvitationsRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupInvitationsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 507;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public Builder clearQueryGroupInvitationsRequest() {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                if (kindCase_ == 507) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 507) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupInvitationsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest.Builder getQueryGroupInvitationsRequestBuilder() {
            return getQueryGroupInvitationsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOrBuilder getQueryGroupInvitationsRequestOrBuilder() {
            if ((kindCase_ == 507) && (queryGroupInvitationsRequestBuilder_ != null)) {
                return queryGroupInvitationsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 507) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOrBuilder> getQueryGroupInvitationsRequestFieldBuilder() {
            if (queryGroupInvitationsRequestBuilder_ == null) {
                if (!(kindCase_ == 507)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest
                            .getDefaultInstance();
                }
                queryGroupInvitationsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 507;
            onChanged();
            return queryGroupInvitationsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOrBuilder> queryGroupJoinRequestsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         *
         * @return Whether the queryGroupJoinRequestsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupJoinRequestsRequest() {
            return kindCase_ == 508;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         *
         * @return The queryGroupJoinRequestsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest getQueryGroupJoinRequestsRequest() {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                if (kindCase_ == 508) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 508) {
                    return queryGroupJoinRequestsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder setQueryGroupJoinRequestsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupJoinRequestsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 508;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder setQueryGroupJoinRequestsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest.Builder builderForValue) {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupJoinRequestsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 508;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder mergeQueryGroupJoinRequestsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest value) {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                if (kindCase_ == 508
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 508) {
                    queryGroupJoinRequestsRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupJoinRequestsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 508;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public Builder clearQueryGroupJoinRequestsRequest() {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                if (kindCase_ == 508) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 508) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupJoinRequestsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest.Builder getQueryGroupJoinRequestsRequestBuilder() {
            return getQueryGroupJoinRequestsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOrBuilder getQueryGroupJoinRequestsRequestOrBuilder() {
            if ((kindCase_ == 508) && (queryGroupJoinRequestsRequestBuilder_ != null)) {
                return queryGroupJoinRequestsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 508) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOrBuilder> getQueryGroupJoinRequestsRequestFieldBuilder() {
            if (queryGroupJoinRequestsRequestBuilder_ == null) {
                if (!(kindCase_ == 508)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest
                            .getDefaultInstance();
                }
                queryGroupJoinRequestsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 508;
            onChanged();
            return queryGroupJoinRequestsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOrBuilder> queryGroupJoinQuestionsRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         *
         * @return Whether the queryGroupJoinQuestionsRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryGroupJoinQuestionsRequest() {
            return kindCase_ == 509;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         *
         * @return The queryGroupJoinQuestionsRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest getQueryGroupJoinQuestionsRequest() {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 509) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 509) {
                    return queryGroupJoinQuestionsRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder setQueryGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryGroupJoinQuestionsRequestBuilder_.setMessage(value);
            }
            kindCase_ = 509;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder setQueryGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest.Builder builderForValue) {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryGroupJoinQuestionsRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 509;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder mergeQueryGroupJoinQuestionsRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest value) {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 509
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 509) {
                    queryGroupJoinQuestionsRequestBuilder_.mergeFrom(value);
                } else {
                    queryGroupJoinQuestionsRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 509;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public Builder clearQueryGroupJoinQuestionsRequest() {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                if (kindCase_ == 509) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 509) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryGroupJoinQuestionsRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest.Builder getQueryGroupJoinQuestionsRequestBuilder() {
            return getQueryGroupJoinQuestionsRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOrBuilder getQueryGroupJoinQuestionsRequestOrBuilder() {
            if ((kindCase_ == 509) && (queryGroupJoinQuestionsRequestBuilder_ != null)) {
                return queryGroupJoinQuestionsRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 509) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOrBuilder> getQueryGroupJoinQuestionsRequestFieldBuilder() {
            if (queryGroupJoinQuestionsRequestBuilder_ == null) {
                if (!(kindCase_ == 509)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest
                            .getDefaultInstance();
                }
                queryGroupJoinQuestionsRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 509;
            onChanged();
            return queryGroupJoinQuestionsRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest, im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder> updateGroupJoinQuestionRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         *
         * @return Whether the updateGroupJoinQuestionRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateGroupJoinQuestionRequest() {
            return kindCase_ == 510;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         *
         * @return The updateGroupJoinQuestionRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getUpdateGroupJoinQuestionRequest() {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                if (kindCase_ == 510) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 510) {
                    return updateGroupJoinQuestionRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder setUpdateGroupJoinQuestionRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateGroupJoinQuestionRequestBuilder_.setMessage(value);
            }
            kindCase_ = 510;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder setUpdateGroupJoinQuestionRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder builderForValue) {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateGroupJoinQuestionRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 510;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder mergeUpdateGroupJoinQuestionRequest(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest value) {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                if (kindCase_ == 510
                        && kind_ != im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 510) {
                    updateGroupJoinQuestionRequestBuilder_.mergeFrom(value);
                } else {
                    updateGroupJoinQuestionRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 510;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public Builder clearUpdateGroupJoinQuestionRequest() {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                if (kindCase_ == 510) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 510) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateGroupJoinQuestionRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder getUpdateGroupJoinQuestionRequestBuilder() {
            return getUpdateGroupJoinQuestionRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder getUpdateGroupJoinQuestionRequestOrBuilder() {
            if ((kindCase_ == 510) && (updateGroupJoinQuestionRequestBuilder_ != null)) {
                return updateGroupJoinQuestionRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 510) {
                    return (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest, im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder, im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder> getUpdateGroupJoinQuestionRequestFieldBuilder() {
            if (updateGroupJoinQuestionRequestBuilder_ == null) {
                if (!(kindCase_ == 510)) {
                    kind_ = im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                            .getDefaultInstance();
                }
                updateGroupJoinQuestionRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 510;
            onChanged();
            return updateGroupJoinQuestionRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest, im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest.Builder, im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOrBuilder> deleteResourceRequestBuilder_;

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         *
         * @return Whether the deleteResourceRequest field is set.
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
         *
         * @return The deleteResourceRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest getDeleteResourceRequest() {
            if (deleteResourceRequestBuilder_ == null) {
                if (kindCase_ == 1000) {
                    return (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 1000) {
                    return deleteResourceRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public Builder setDeleteResourceRequest(
                im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest value) {
            if (deleteResourceRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                deleteResourceRequestBuilder_.setMessage(value);
            }
            kindCase_ = 1000;
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
                im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest.Builder builderForValue) {
            if (deleteResourceRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                deleteResourceRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 1000;
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
                im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest value) {
            if (deleteResourceRequestBuilder_ == null) {
                if (kindCase_ == 1000
                        && kind_ != im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 1000) {
                    deleteResourceRequestBuilder_.mergeFrom(value);
                } else {
                    deleteResourceRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 1000;
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
            if (deleteResourceRequestBuilder_ == null) {
                if (kindCase_ == 1000) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 1000) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                deleteResourceRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        public im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest.Builder getDeleteResourceRequestBuilder() {
            return getDeleteResourceRequestFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOrBuilder getDeleteResourceRequestOrBuilder() {
            if ((kindCase_ == 1000) && (deleteResourceRequestBuilder_ != null)) {
                return deleteResourceRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 1000) {
                    return (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest, im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest.Builder, im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOrBuilder> getDeleteResourceRequestFieldBuilder() {
            if (deleteResourceRequestBuilder_ == null) {
                if (!(kindCase_ == 1000)) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest
                            .getDefaultInstance();
                }
                deleteResourceRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        (im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest) kind_,
                        getParentForChildren(),
                        isClean());
                kind_ = null;
            }
            kindCase_ = 1000;
            onChanged();
            return deleteResourceRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest, im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOrBuilder> queryResourceDownloadInfoRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         *
         * @return Whether the queryResourceDownloadInfoRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryResourceDownloadInfoRequest() {
            return kindCase_ == 1001;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         *
         * @return The queryResourceDownloadInfoRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest getQueryResourceDownloadInfoRequest() {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1001) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 1001) {
                    return queryResourceDownloadInfoRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder setQueryResourceDownloadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest value) {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryResourceDownloadInfoRequestBuilder_.setMessage(value);
            }
            kindCase_ = 1001;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder setQueryResourceDownloadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest.Builder builderForValue) {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryResourceDownloadInfoRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 1001;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder mergeQueryResourceDownloadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest value) {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1001
                        && kind_ != im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 1001) {
                    queryResourceDownloadInfoRequestBuilder_.mergeFrom(value);
                } else {
                    queryResourceDownloadInfoRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 1001;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public Builder clearQueryResourceDownloadInfoRequest() {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1001) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 1001) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryResourceDownloadInfoRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest.Builder getQueryResourceDownloadInfoRequestBuilder() {
            return getQueryResourceDownloadInfoRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOrBuilder getQueryResourceDownloadInfoRequestOrBuilder() {
            if ((kindCase_ == 1001) && (queryResourceDownloadInfoRequestBuilder_ != null)) {
                return queryResourceDownloadInfoRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 1001) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest, im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOrBuilder> getQueryResourceDownloadInfoRequestFieldBuilder() {
            if (queryResourceDownloadInfoRequestBuilder_ == null) {
                if (!(kindCase_ == 1001)) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest
                            .getDefaultInstance();
                }
                queryResourceDownloadInfoRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 1001;
            onChanged();
            return queryResourceDownloadInfoRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest, im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder> queryResourceUploadInfoRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         *
         * @return Whether the queryResourceUploadInfoRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryResourceUploadInfoRequest() {
            return kindCase_ == 1002;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         *
         * @return The queryResourceUploadInfoRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getQueryResourceUploadInfoRequest() {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1002) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 1002) {
                    return queryResourceUploadInfoRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder setQueryResourceUploadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest value) {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryResourceUploadInfoRequestBuilder_.setMessage(value);
            }
            kindCase_ = 1002;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder setQueryResourceUploadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder builderForValue) {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryResourceUploadInfoRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 1002;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder mergeQueryResourceUploadInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest value) {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1002
                        && kind_ != im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 1002) {
                    queryResourceUploadInfoRequestBuilder_.mergeFrom(value);
                } else {
                    queryResourceUploadInfoRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 1002;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public Builder clearQueryResourceUploadInfoRequest() {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                if (kindCase_ == 1002) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 1002) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryResourceUploadInfoRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder getQueryResourceUploadInfoRequestBuilder() {
            return getQueryResourceUploadInfoRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder getQueryResourceUploadInfoRequestOrBuilder() {
            if ((kindCase_ == 1002) && (queryResourceUploadInfoRequestBuilder_ != null)) {
                return queryResourceUploadInfoRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 1002) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest, im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder> getQueryResourceUploadInfoRequestFieldBuilder() {
            if (queryResourceUploadInfoRequestBuilder_ == null) {
                if (!(kindCase_ == 1002)) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                            .getDefaultInstance();
                }
                queryResourceUploadInfoRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 1002;
            onChanged();
            return queryResourceUploadInfoRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest, im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder> queryMessageAttachmentInfosRequestBuilder_;

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         *
         * @return Whether the queryMessageAttachmentInfosRequest field is set.
         */
        @java.lang.Override
        public boolean hasQueryMessageAttachmentInfosRequest() {
            return kindCase_ == 1003;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         *
         * @return The queryMessageAttachmentInfosRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getQueryMessageAttachmentInfosRequest() {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                if (kindCase_ == 1003) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 1003) {
                    return queryMessageAttachmentInfosRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder setQueryMessageAttachmentInfosRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest value) {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                queryMessageAttachmentInfosRequestBuilder_.setMessage(value);
            }
            kindCase_ = 1003;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder setQueryMessageAttachmentInfosRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder builderForValue) {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                queryMessageAttachmentInfosRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 1003;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder mergeQueryMessageAttachmentInfosRequest(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest value) {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                if (kindCase_ == 1003
                        && kind_ != im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 1003) {
                    queryMessageAttachmentInfosRequestBuilder_.mergeFrom(value);
                } else {
                    queryMessageAttachmentInfosRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 1003;
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public Builder clearQueryMessageAttachmentInfosRequest() {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                if (kindCase_ == 1003) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 1003) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                queryMessageAttachmentInfosRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder getQueryMessageAttachmentInfosRequestBuilder() {
            return getQueryMessageAttachmentInfosRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder getQueryMessageAttachmentInfosRequestOrBuilder() {
            if ((kindCase_ == 1003) && (queryMessageAttachmentInfosRequestBuilder_ != null)) {
                return queryMessageAttachmentInfosRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 1003) {
                    return (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest, im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder, im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder> getQueryMessageAttachmentInfosRequestFieldBuilder() {
            if (queryMessageAttachmentInfosRequestBuilder_ == null) {
                if (!(kindCase_ == 1003)) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                            .getDefaultInstance();
                }
                queryMessageAttachmentInfosRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 1003;
            onChanged();
            return queryMessageAttachmentInfosRequestBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest, im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder> updateMessageAttachmentInfoRequestBuilder_;

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         *
         * @return Whether the updateMessageAttachmentInfoRequest field is set.
         */
        @java.lang.Override
        public boolean hasUpdateMessageAttachmentInfoRequest() {
            return kindCase_ == 1004;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         *
         * @return The updateMessageAttachmentInfoRequest.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getUpdateMessageAttachmentInfoRequest() {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                if (kindCase_ == 1004) {
                    return (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                        .getDefaultInstance();
            } else {
                if (kindCase_ == 1004) {
                    return updateMessageAttachmentInfoRequestBuilder_.getMessage();
                }
                return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder setUpdateMessageAttachmentInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest value) {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kind_ = value;
                onChanged();
            } else {
                updateMessageAttachmentInfoRequestBuilder_.setMessage(value);
            }
            kindCase_ = 1004;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder setUpdateMessageAttachmentInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder builderForValue) {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                kind_ = builderForValue.build();
                onChanged();
            } else {
                updateMessageAttachmentInfoRequestBuilder_.setMessage(builderForValue.build());
            }
            kindCase_ = 1004;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder mergeUpdateMessageAttachmentInfoRequest(
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest value) {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                if (kindCase_ == 1004
                        && kind_ != im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                                .getDefaultInstance()) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                            .newBuilder(
                                    (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    kind_ = value;
                }
                onChanged();
            } else {
                if (kindCase_ == 1004) {
                    updateMessageAttachmentInfoRequestBuilder_.mergeFrom(value);
                } else {
                    updateMessageAttachmentInfoRequestBuilder_.setMessage(value);
                }
            }
            kindCase_ = 1004;
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public Builder clearUpdateMessageAttachmentInfoRequest() {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                if (kindCase_ == 1004) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
            } else {
                if (kindCase_ == 1004) {
                    kindCase_ = 0;
                    kind_ = null;
                }
                updateMessageAttachmentInfoRequestBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder getUpdateMessageAttachmentInfoRequestBuilder() {
            return getUpdateMessageAttachmentInfoRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder getUpdateMessageAttachmentInfoRequestOrBuilder() {
            if ((kindCase_ == 1004) && (updateMessageAttachmentInfoRequestBuilder_ != null)) {
                return updateMessageAttachmentInfoRequestBuilder_.getMessageOrBuilder();
            } else {
                if (kindCase_ == 1004) {
                    return (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_;
                }
                return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest, im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder, im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder> getUpdateMessageAttachmentInfoRequestFieldBuilder() {
            if (updateMessageAttachmentInfoRequestBuilder_ == null) {
                if (!(kindCase_ == 1004)) {
                    kind_ = im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                            .getDefaultInstance();
                }
                updateMessageAttachmentInfoRequestBuilder_ =
                        new com.google.protobuf.SingleFieldBuilderV3<>(
                                (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) kind_,
                                getParentForChildren(),
                                isClean());
                kind_ = null;
            }
            kindCase_ = 1004;
            onChanged();
            return updateMessageAttachmentInfoRequestBuilder_;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsRequest)
    private static final im.turms.server.common.access.client.dto.request.TurmsRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.request.TurmsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.TurmsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TurmsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public TurmsRequest parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<TurmsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TurmsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.TurmsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}