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

public interface TurmsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.TurmsRequest)
        com.google.protobuf.MessageOrBuilder {

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
    boolean hasRequestId();

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
    long getRequestId();

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     *
     * @return Whether the createSessionRequest field is set.
     */
    boolean hasCreateSessionRequest();

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     *
     * @return The createSessionRequest.
     */
    im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getCreateSessionRequest();

    /**
     * <pre>
     * User - Session
     * </pre>
     * 
     * <code>.im.turms.proto.CreateSessionRequest create_session_request = 3;</code>
     */
    im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder getCreateSessionRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     *
     * @return Whether the deleteSessionRequest field is set.
     */
    boolean hasDeleteSessionRequest();

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     *
     * @return The deleteSessionRequest.
     */
    im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest getDeleteSessionRequest();

    /**
     * <code>.im.turms.proto.DeleteSessionRequest delete_session_request = 4;</code>
     */
    im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOrBuilder getDeleteSessionRequestOrBuilder();

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     *
     * @return Whether the queryConversationsRequest field is set.
     */
    boolean hasQueryConversationsRequest();

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     *
     * @return The queryConversationsRequest.
     */
    im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getQueryConversationsRequest();

    /**
     * <pre>
     * Conversation
     * </pre>
     * 
     * <code>.im.turms.proto.QueryConversationsRequest query_conversations_request = 5;</code>
     */
    im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder getQueryConversationsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     *
     * @return Whether the updateConversationRequest field is set.
     */
    boolean hasUpdateConversationRequest();

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     *
     * @return The updateConversationRequest.
     */
    im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequest getUpdateConversationRequest();

    /**
     * <code>.im.turms.proto.UpdateConversationRequest update_conversation_request = 6;</code>
     */
    im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOrBuilder getUpdateConversationRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     *
     * @return Whether the updateTypingStatusRequest field is set.
     */
    boolean hasUpdateTypingStatusRequest();

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     *
     * @return The updateTypingStatusRequest.
     */
    im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getUpdateTypingStatusRequest();

    /**
     * <code>.im.turms.proto.UpdateTypingStatusRequest update_typing_status_request = 7;</code>
     */
    im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder getUpdateTypingStatusRequestOrBuilder();

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     *
     * @return Whether the createMessageRequest field is set.
     */
    boolean hasCreateMessageRequest();

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     *
     * @return The createMessageRequest.
     */
    im.turms.server.common.access.client.dto.request.message.CreateMessageRequest getCreateMessageRequest();

    /**
     * <pre>
     * Message
     * </pre>
     * 
     * <code>.im.turms.proto.CreateMessageRequest create_message_request = 8;</code>
     */
    im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOrBuilder getCreateMessageRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     *
     * @return Whether the queryMessagesRequest field is set.
     */
    boolean hasQueryMessagesRequest();

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     *
     * @return The queryMessagesRequest.
     */
    im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getQueryMessagesRequest();

    /**
     * <code>.im.turms.proto.QueryMessagesRequest query_messages_request = 9;</code>
     */
    im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder getQueryMessagesRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     *
     * @return Whether the updateMessageRequest field is set.
     */
    boolean hasUpdateMessageRequest();

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     *
     * @return The updateMessageRequest.
     */
    im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getUpdateMessageRequest();

    /**
     * <code>.im.turms.proto.UpdateMessageRequest update_message_request = 10;</code>
     */
    im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder getUpdateMessageRequestOrBuilder();

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     *
     * @return Whether the createGroupMembersRequest field is set.
     */
    boolean hasCreateGroupMembersRequest();

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     *
     * @return The createGroupMembersRequest.
     */
    im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequest getCreateGroupMembersRequest();

    /**
     * <pre>
     * Group Member
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupMembersRequest create_group_members_request = 11;</code>
     */
    im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOrBuilder getCreateGroupMembersRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     *
     * @return Whether the deleteGroupMembersRequest field is set.
     */
    boolean hasDeleteGroupMembersRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     *
     * @return The deleteGroupMembersRequest.
     */
    im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDeleteGroupMembersRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupMembersRequest delete_group_members_request = 12;</code>
     */
    im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder getDeleteGroupMembersRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     *
     * @return Whether the queryGroupMembersRequest field is set.
     */
    boolean hasQueryGroupMembersRequest();

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     *
     * @return The queryGroupMembersRequest.
     */
    im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequest getQueryGroupMembersRequest();

    /**
     * <code>.im.turms.proto.QueryGroupMembersRequest query_group_members_request = 13;</code>
     */
    im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOrBuilder getQueryGroupMembersRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     *
     * @return Whether the updateGroupMemberRequest field is set.
     */
    boolean hasUpdateGroupMemberRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     *
     * @return The updateGroupMemberRequest.
     */
    im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequest getUpdateGroupMemberRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupMemberRequest update_group_member_request = 14;</code>
     */
    im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOrBuilder getUpdateGroupMemberRequestOrBuilder();

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     *
     * @return Whether the queryUserProfilesRequest field is set.
     */
    boolean hasQueryUserProfilesRequest();

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     *
     * @return The queryUserProfilesRequest.
     */
    im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequest getQueryUserProfilesRequest();

    /**
     * <pre>
     * User
     * </pre>
     * 
     * <code>.im.turms.proto.QueryUserProfilesRequest query_user_profiles_request = 100;</code>
     */
    im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOrBuilder getQueryUserProfilesRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     *
     * @return Whether the queryNearbyUsersRequest field is set.
     */
    boolean hasQueryNearbyUsersRequest();

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     *
     * @return The queryNearbyUsersRequest.
     */
    im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequest getQueryNearbyUsersRequest();

    /**
     * <code>.im.turms.proto.QueryNearbyUsersRequest query_nearby_users_request = 101;</code>
     */
    im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOrBuilder getQueryNearbyUsersRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     *
     * @return Whether the queryUserOnlineStatusesRequest field is set.
     */
    boolean hasQueryUserOnlineStatusesRequest();

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     *
     * @return The queryUserOnlineStatusesRequest.
     */
    im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequest getQueryUserOnlineStatusesRequest();

    /**
     * <code>.im.turms.proto.QueryUserOnlineStatusesRequest query_user_online_statuses_request = 102;</code>
     */
    im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOrBuilder getQueryUserOnlineStatusesRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     *
     * @return Whether the updateUserLocationRequest field is set.
     */
    boolean hasUpdateUserLocationRequest();

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     *
     * @return The updateUserLocationRequest.
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequest getUpdateUserLocationRequest();

    /**
     * <code>.im.turms.proto.UpdateUserLocationRequest update_user_location_request = 103;</code>
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOrBuilder getUpdateUserLocationRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     *
     * @return Whether the updateUserOnlineStatusRequest field is set.
     */
    boolean hasUpdateUserOnlineStatusRequest();

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     *
     * @return The updateUserOnlineStatusRequest.
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getUpdateUserOnlineStatusRequest();

    /**
     * <code>.im.turms.proto.UpdateUserOnlineStatusRequest update_user_online_status_request = 104;</code>
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder getUpdateUserOnlineStatusRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     *
     * @return Whether the updateUserRequest field is set.
     */
    boolean hasUpdateUserRequest();

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     *
     * @return The updateUserRequest.
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserRequest getUpdateUserRequest();

    /**
     * <code>.im.turms.proto.UpdateUserRequest update_user_request = 105;</code>
     */
    im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOrBuilder getUpdateUserRequestOrBuilder();

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     *
     * @return Whether the createFriendRequestRequest field is set.
     */
    boolean hasCreateFriendRequestRequest();

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     *
     * @return The createFriendRequestRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequest getCreateFriendRequestRequest();

    /**
     * <pre>
     * User Relationship
     * </pre>
     * 
     * <code>.im.turms.proto.CreateFriendRequestRequest create_friend_request_request = 200;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOrBuilder getCreateFriendRequestRequestOrBuilder();

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     *
     * @return Whether the createRelationshipGroupRequest field is set.
     */
    boolean hasCreateRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     *
     * @return The createRelationshipGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequest getCreateRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.CreateRelationshipGroupRequest create_relationship_group_request = 201;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOrBuilder getCreateRelationshipGroupRequestOrBuilder();

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     *
     * @return Whether the createRelationshipRequest field is set.
     */
    boolean hasCreateRelationshipRequest();

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     *
     * @return The createRelationshipRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequest getCreateRelationshipRequest();

    /**
     * <code>.im.turms.proto.CreateRelationshipRequest create_relationship_request = 202;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOrBuilder getCreateRelationshipRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     *
     * @return Whether the deleteRelationshipGroupRequest field is set.
     */
    boolean hasDeleteRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     *
     * @return The deleteRelationshipGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequest getDeleteRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.DeleteRelationshipGroupRequest delete_relationship_group_request = 203;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOrBuilder getDeleteRelationshipGroupRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     *
     * @return Whether the deleteRelationshipRequest field is set.
     */
    boolean hasDeleteRelationshipRequest();

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     *
     * @return The deleteRelationshipRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequest getDeleteRelationshipRequest();

    /**
     * <code>.im.turms.proto.DeleteRelationshipRequest delete_relationship_request = 204;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOrBuilder getDeleteRelationshipRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     *
     * @return Whether the queryFriendRequestsRequest field is set.
     */
    boolean hasQueryFriendRequestsRequest();

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     *
     * @return The queryFriendRequestsRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequest getQueryFriendRequestsRequest();

    /**
     * <code>.im.turms.proto.QueryFriendRequestsRequest query_friend_requests_request = 205;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOrBuilder getQueryFriendRequestsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     *
     * @return Whether the queryRelatedUserIdsRequest field is set.
     */
    boolean hasQueryRelatedUserIdsRequest();

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     *
     * @return The queryRelatedUserIdsRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequest getQueryRelatedUserIdsRequest();

    /**
     * <code>.im.turms.proto.QueryRelatedUserIdsRequest query_related_user_ids_request = 206;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOrBuilder getQueryRelatedUserIdsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     *
     * @return Whether the queryRelationshipGroupsRequest field is set.
     */
    boolean hasQueryRelationshipGroupsRequest();

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     *
     * @return The queryRelationshipGroupsRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequest getQueryRelationshipGroupsRequest();

    /**
     * <code>.im.turms.proto.QueryRelationshipGroupsRequest query_relationship_groups_request = 207;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOrBuilder getQueryRelationshipGroupsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     *
     * @return Whether the queryRelationshipsRequest field is set.
     */
    boolean hasQueryRelationshipsRequest();

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     *
     * @return The queryRelationshipsRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequest getQueryRelationshipsRequest();

    /**
     * <code>.im.turms.proto.QueryRelationshipsRequest query_relationships_request = 208;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOrBuilder getQueryRelationshipsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     *
     * @return Whether the updateFriendRequestRequest field is set.
     */
    boolean hasUpdateFriendRequestRequest();

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     *
     * @return The updateFriendRequestRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequest getUpdateFriendRequestRequest();

    /**
     * <code>.im.turms.proto.UpdateFriendRequestRequest update_friend_request_request = 209;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOrBuilder getUpdateFriendRequestRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     *
     * @return Whether the updateRelationshipGroupRequest field is set.
     */
    boolean hasUpdateRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     *
     * @return The updateRelationshipGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequest getUpdateRelationshipGroupRequest();

    /**
     * <code>.im.turms.proto.UpdateRelationshipGroupRequest update_relationship_group_request = 210;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOrBuilder getUpdateRelationshipGroupRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     *
     * @return Whether the updateRelationshipRequest field is set.
     */
    boolean hasUpdateRelationshipRequest();

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     *
     * @return The updateRelationshipRequest.
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getUpdateRelationshipRequest();

    /**
     * <code>.im.turms.proto.UpdateRelationshipRequest update_relationship_request = 211;</code>
     */
    im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder getUpdateRelationshipRequestOrBuilder();

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     *
     * @return Whether the createGroupRequest field is set.
     */
    boolean hasCreateGroupRequest();

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     *
     * @return The createGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getCreateGroupRequest();

    /**
     * <pre>
     * Group
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupRequest create_group_request = 300;</code>
     */
    im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder getCreateGroupRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     *
     * @return Whether the deleteGroupRequest field is set.
     */
    boolean hasDeleteGroupRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     *
     * @return The deleteGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.group.DeleteGroupRequest getDeleteGroupRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupRequest delete_group_request = 301;</code>
     */
    im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOrBuilder getDeleteGroupRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     *
     * @return Whether the queryGroupsRequest field is set.
     */
    boolean hasQueryGroupsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     *
     * @return The queryGroupsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.QueryGroupsRequest getQueryGroupsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupsRequest query_groups_request = 302;</code>
     */
    im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOrBuilder getQueryGroupsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     *
     * @return Whether the queryJoinedGroupIdsRequest field is set.
     */
    boolean hasQueryJoinedGroupIdsRequest();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     *
     * @return The queryJoinedGroupIdsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequest getQueryJoinedGroupIdsRequest();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupIdsRequest query_joined_group_ids_request = 303;</code>
     */
    im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOrBuilder getQueryJoinedGroupIdsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     *
     * @return Whether the queryJoinedGroupInfosRequest field is set.
     */
    boolean hasQueryJoinedGroupInfosRequest();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     *
     * @return The queryJoinedGroupInfosRequest.
     */
    im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequest getQueryJoinedGroupInfosRequest();

    /**
     * <code>.im.turms.proto.QueryJoinedGroupInfosRequest query_joined_group_infos_request = 304;</code>
     */
    im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOrBuilder getQueryJoinedGroupInfosRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     *
     * @return Whether the updateGroupRequest field is set.
     */
    boolean hasUpdateGroupRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     *
     * @return The updateGroupRequest.
     */
    im.turms.server.common.access.client.dto.request.group.UpdateGroupRequest getUpdateGroupRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupRequest update_group_request = 305;</code>
     */
    im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOrBuilder getUpdateGroupRequestOrBuilder();

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     *
     * @return Whether the createGroupBlockedUserRequest field is set.
     */
    boolean hasCreateGroupBlockedUserRequest();

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     *
     * @return The createGroupBlockedUserRequest.
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequest getCreateGroupBlockedUserRequest();

    /**
     * <pre>
     * Group Blocklist
     * </pre>
     * 
     * <code>.im.turms.proto.CreateGroupBlockedUserRequest create_group_blocked_user_request = 400;</code>
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOrBuilder getCreateGroupBlockedUserRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     *
     * @return Whether the deleteGroupBlockedUserRequest field is set.
     */
    boolean hasDeleteGroupBlockedUserRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     *
     * @return The deleteGroupBlockedUserRequest.
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequest getDeleteGroupBlockedUserRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupBlockedUserRequest delete_group_blocked_user_request = 401;</code>
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOrBuilder getDeleteGroupBlockedUserRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     *
     * @return Whether the queryGroupBlockedUserIdsRequest field is set.
     */
    boolean hasQueryGroupBlockedUserIdsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     *
     * @return The queryGroupBlockedUserIdsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequest getQueryGroupBlockedUserIdsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserIdsRequest query_group_blocked_user_ids_request = 402;</code>
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOrBuilder getQueryGroupBlockedUserIdsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     *
     * @return Whether the queryGroupBlockedUserInfosRequest field is set.
     */
    boolean hasQueryGroupBlockedUserInfosRequest();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     *
     * @return The queryGroupBlockedUserInfosRequest.
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequest getQueryGroupBlockedUserInfosRequest();

    /**
     * <code>.im.turms.proto.QueryGroupBlockedUserInfosRequest query_group_blocked_user_infos_request = 403;</code>
     */
    im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOrBuilder getQueryGroupBlockedUserInfosRequestOrBuilder();

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     *
     * @return Whether the checkGroupJoinQuestionsAnswersRequest field is set.
     */
    boolean hasCheckGroupJoinQuestionsAnswersRequest();

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     *
     * @return The checkGroupJoinQuestionsAnswersRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getCheckGroupJoinQuestionsAnswersRequest();

    /**
     * <pre>
     * Group Enrollment
     * </pre>
     * 
     * <code>.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest check_group_join_questions_answers_request = 500;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder getCheckGroupJoinQuestionsAnswersRequestOrBuilder();

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     *
     * @return Whether the createGroupInvitationRequest field is set.
     */
    boolean hasCreateGroupInvitationRequest();

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     *
     * @return The createGroupInvitationRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequest getCreateGroupInvitationRequest();

    /**
     * <code>.im.turms.proto.CreateGroupInvitationRequest create_group_invitation_request = 501;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOrBuilder getCreateGroupInvitationRequestOrBuilder();

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     *
     * @return Whether the createGroupJoinRequestRequest field is set.
     */
    boolean hasCreateGroupJoinRequestRequest();

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     *
     * @return The createGroupJoinRequestRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequest getCreateGroupJoinRequestRequest();

    /**
     * <code>.im.turms.proto.CreateGroupJoinRequestRequest create_group_join_request_request = 502;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOrBuilder getCreateGroupJoinRequestRequestOrBuilder();

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     *
     * @return Whether the createGroupJoinQuestionsRequest field is set.
     */
    boolean hasCreateGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     *
     * @return The createGroupJoinQuestionsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getCreateGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.CreateGroupJoinQuestionsRequest create_group_join_questions_request = 503;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder getCreateGroupJoinQuestionsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     *
     * @return Whether the deleteGroupInvitationRequest field is set.
     */
    boolean hasDeleteGroupInvitationRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     *
     * @return The deleteGroupInvitationRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequest getDeleteGroupInvitationRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupInvitationRequest delete_group_invitation_request = 504;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOrBuilder getDeleteGroupInvitationRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     *
     * @return Whether the deleteGroupJoinRequestRequest field is set.
     */
    boolean hasDeleteGroupJoinRequestRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     *
     * @return The deleteGroupJoinRequestRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequest getDeleteGroupJoinRequestRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinRequestRequest delete_group_join_request_request = 505;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOrBuilder getDeleteGroupJoinRequestRequestOrBuilder();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     *
     * @return Whether the deleteGroupJoinQuestionsRequest field is set.
     */
    boolean hasDeleteGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     *
     * @return The deleteGroupJoinQuestionsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDeleteGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.DeleteGroupJoinQuestionsRequest delete_group_join_questions_request = 506;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder getDeleteGroupJoinQuestionsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     *
     * @return Whether the queryGroupInvitationsRequest field is set.
     */
    boolean hasQueryGroupInvitationsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     *
     * @return The queryGroupInvitationsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequest getQueryGroupInvitationsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupInvitationsRequest query_group_invitations_request = 507;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOrBuilder getQueryGroupInvitationsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     *
     * @return Whether the queryGroupJoinRequestsRequest field is set.
     */
    boolean hasQueryGroupJoinRequestsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     *
     * @return The queryGroupJoinRequestsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequest getQueryGroupJoinRequestsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupJoinRequestsRequest query_group_join_requests_request = 508;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOrBuilder getQueryGroupJoinRequestsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     *
     * @return Whether the queryGroupJoinQuestionsRequest field is set.
     */
    boolean hasQueryGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     *
     * @return The queryGroupJoinQuestionsRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequest getQueryGroupJoinQuestionsRequest();

    /**
     * <code>.im.turms.proto.QueryGroupJoinQuestionsRequest query_group_join_questions_request = 509;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOrBuilder getQueryGroupJoinQuestionsRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     *
     * @return Whether the updateGroupJoinQuestionRequest field is set.
     */
    boolean hasUpdateGroupJoinQuestionRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     *
     * @return The updateGroupJoinQuestionRequest.
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getUpdateGroupJoinQuestionRequest();

    /**
     * <code>.im.turms.proto.UpdateGroupJoinQuestionRequest update_group_join_question_request = 510;</code>
     */
    im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder getUpdateGroupJoinQuestionRequestOrBuilder();

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     *
     * @return Whether the deleteResourceRequest field is set.
     */
    boolean hasDeleteResourceRequest();

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     *
     * @return The deleteResourceRequest.
     */
    im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest getDeleteResourceRequest();

    /**
     * <pre>
     * Storage
     * </pre>
     * 
     * <code>.im.turms.proto.DeleteResourceRequest delete_resource_request = 1000;</code>
     */
    im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOrBuilder getDeleteResourceRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     *
     * @return Whether the queryResourceDownloadInfoRequest field is set.
     */
    boolean hasQueryResourceDownloadInfoRequest();

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     *
     * @return The queryResourceDownloadInfoRequest.
     */
    im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest getQueryResourceDownloadInfoRequest();

    /**
     * <code>.im.turms.proto.QueryResourceDownloadInfoRequest query_resource_download_info_request = 1001;</code>
     */
    im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOrBuilder getQueryResourceDownloadInfoRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     *
     * @return Whether the queryResourceUploadInfoRequest field is set.
     */
    boolean hasQueryResourceUploadInfoRequest();

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     *
     * @return The queryResourceUploadInfoRequest.
     */
    im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getQueryResourceUploadInfoRequest();

    /**
     * <code>.im.turms.proto.QueryResourceUploadInfoRequest query_resource_upload_info_request = 1002;</code>
     */
    im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder getQueryResourceUploadInfoRequestOrBuilder();

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     *
     * @return Whether the queryMessageAttachmentInfosRequest field is set.
     */
    boolean hasQueryMessageAttachmentInfosRequest();

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     *
     * @return The queryMessageAttachmentInfosRequest.
     */
    im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getQueryMessageAttachmentInfosRequest();

    /**
     * <code>.im.turms.proto.QueryMessageAttachmentInfosRequest query_message_attachment_infos_request = 1003;</code>
     */
    im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder getQueryMessageAttachmentInfosRequestOrBuilder();

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     *
     * @return Whether the updateMessageAttachmentInfoRequest field is set.
     */
    boolean hasUpdateMessageAttachmentInfoRequest();

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     *
     * @return The updateMessageAttachmentInfoRequest.
     */
    im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getUpdateMessageAttachmentInfoRequest();

    /**
     * <code>.im.turms.proto.UpdateMessageAttachmentInfoRequest update_message_attachment_info_request = 1004;</code>
     */
    im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder getUpdateMessageAttachmentInfoRequestOrBuilder();

    im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase getKindCase();
}