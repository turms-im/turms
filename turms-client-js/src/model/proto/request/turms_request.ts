/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { CreateSessionRequest } from "../request/user/create_session_request";
import { DeleteSessionRequest } from "../request/user/delete_session_request";
import { QueryConversationsRequest } from "../request/conversation/query_conversations_request";
import { UpdateConversationRequest } from "../request/conversation/update_conversation_request";
import { UpdateTypingStatusRequest } from "../request/conversation/update_typing_status_request";
import { CreateMessageRequest } from "../request/message/create_message_request";
import { QueryMessagesRequest } from "../request/message/query_messages_request";
import { UpdateMessageRequest } from "../request/message/update_message_request";
import { CreateGroupMemberRequest } from "../request/group/member/create_group_member_request";
import { DeleteGroupMemberRequest } from "../request/group/member/delete_group_member_request";
import { QueryGroupMembersRequest } from "../request/group/member/query_group_members_request";
import { UpdateGroupMemberRequest } from "../request/group/member/update_group_member_request";
import { QueryUserProfileRequest } from "../request/user/query_user_profile_request";
import { QueryUserIdsNearbyRequest } from "../request/user/query_user_ids_nearby_request";
import { QueryUserInfosNearbyRequest } from "../request/user/query_user_infos_nearby_request";
import { QueryUserOnlineStatusesRequest } from "../request/user/query_user_online_statuses_request";
import { UpdateUserLocationRequest } from "../request/user/update_user_location_request";
import { UpdateUserOnlineStatusRequest } from "../request/user/update_user_online_status_request";
import { UpdateUserRequest } from "../request/user/update_user_request";
import { CreateFriendRequestRequest } from "../request/user/relationship/create_friend_request_request";
import { CreateRelationshipGroupRequest } from "../request/user/relationship/create_relationship_group_request";
import { CreateRelationshipRequest } from "../request/user/relationship/create_relationship_request";
import { DeleteRelationshipGroupRequest } from "../request/user/relationship/delete_relationship_group_request";
import { DeleteRelationshipRequest } from "../request/user/relationship/delete_relationship_request";
import { QueryFriendRequestsRequest } from "../request/user/relationship/query_friend_requests_request";
import { QueryRelatedUserIdsRequest } from "../request/user/relationship/query_related_user_ids_request";
import { QueryRelationshipGroupsRequest } from "../request/user/relationship/query_relationship_groups_request";
import { QueryRelationshipsRequest } from "../request/user/relationship/query_relationships_request";
import { UpdateFriendRequestRequest } from "../request/user/relationship/update_friend_request_request";
import { UpdateRelationshipGroupRequest } from "../request/user/relationship/update_relationship_group_request";
import { UpdateRelationshipRequest } from "../request/user/relationship/update_relationship_request";
import { CreateGroupRequest } from "../request/group/create_group_request";
import { DeleteGroupRequest } from "../request/group/delete_group_request";
import { QueryGroupRequest } from "../request/group/query_group_request";
import { QueryJoinedGroupIdsRequest } from "../request/group/query_joined_group_ids_request";
import { QueryJoinedGroupInfosRequest } from "../request/group/query_joined_group_infos_request";
import { UpdateGroupRequest } from "../request/group/update_group_request";
import { CreateGroupBlockedUserRequest } from "../request/group/blocklist/create_group_blocked_user_request";
import { DeleteGroupBlockedUserRequest } from "../request/group/blocklist/delete_group_blocked_user_request";
import { QueryGroupBlockedUserIdsRequest } from "../request/group/blocklist/query_group_blocked_user_ids_request";
import { QueryGroupBlockedUserInfosRequest } from "../request/group/blocklist/query_group_blocked_user_infos_request";
import { CheckGroupJoinQuestionsAnswersRequest } from "../request/group/enrollment/check_group_join_questions_answers_request";
import { CreateGroupInvitationRequest } from "../request/group/enrollment/create_group_invitation_request";
import { CreateGroupJoinRequestRequest } from "../request/group/enrollment/create_group_join_request_request";
import { CreateGroupJoinQuestionRequest } from "../request/group/enrollment/create_group_join_question_request";
import { DeleteGroupInvitationRequest } from "../request/group/enrollment/delete_group_invitation_request";
import { DeleteGroupJoinRequestRequest } from "../request/group/enrollment/delete_group_join_request_request";
import { DeleteGroupJoinQuestionRequest } from "../request/group/enrollment/delete_group_join_question_request";
import { QueryGroupInvitationsRequest } from "../request/group/enrollment/query_group_invitations_request";
import { QueryGroupJoinRequestsRequest } from "../request/group/enrollment/query_group_join_requests_request";
import { QueryGroupJoinQuestionsRequest } from "../request/group/enrollment/query_group_join_questions_request";
import { UpdateGroupJoinQuestionRequest } from "../request/group/enrollment/update_group_join_question_request";
import { DeleteResourceRequest } from "../request/storage/delete_resource_request";
import { QuerySignedGetUrlRequest } from "../request/storage/query_signed_get_url_request";
import { QuerySignedPutUrlRequest } from "../request/storage/query_signed_put_url_request";

export const protobufPackage = "im.turms.proto";

/** Client -> Server -> Client */
export interface TurmsRequest {
  /**
   * Note: request_id is allowed to be duplicate because
   * it is used for clients to identify the response of the same request id in a session
   */
  requestId?: string | undefined;
  /** User - Session */
  createSessionRequest?: CreateSessionRequest | undefined;
  deleteSessionRequest?: DeleteSessionRequest | undefined;
  /** Conversation */
  queryConversationsRequest?: QueryConversationsRequest | undefined;
  updateConversationRequest?: UpdateConversationRequest | undefined;
  updateTypingStatusRequest?: UpdateTypingStatusRequest | undefined;
  /** Message */
  createMessageRequest?: CreateMessageRequest | undefined;
  queryMessagesRequest?: QueryMessagesRequest | undefined;
  updateMessageRequest?: UpdateMessageRequest | undefined;
  /** Group Member */
  createGroupMemberRequest?: CreateGroupMemberRequest | undefined;
  deleteGroupMemberRequest?: DeleteGroupMemberRequest | undefined;
  queryGroupMembersRequest?: QueryGroupMembersRequest | undefined;
  updateGroupMemberRequest?: UpdateGroupMemberRequest | undefined;
  /** User */
  queryUserProfileRequest?: QueryUserProfileRequest | undefined;
  queryUserIdsNearbyRequest?: QueryUserIdsNearbyRequest | undefined;
  queryUserInfosNearbyRequest?: QueryUserInfosNearbyRequest | undefined;
  queryUserOnlineStatusesRequest?: QueryUserOnlineStatusesRequest | undefined;
  updateUserLocationRequest?: UpdateUserLocationRequest | undefined;
  updateUserOnlineStatusRequest?: UpdateUserOnlineStatusRequest | undefined;
  updateUserRequest?: UpdateUserRequest | undefined;
  /** User Relationship */
  createFriendRequestRequest?: CreateFriendRequestRequest | undefined;
  createRelationshipGroupRequest?: CreateRelationshipGroupRequest | undefined;
  createRelationshipRequest?: CreateRelationshipRequest | undefined;
  deleteRelationshipGroupRequest?: DeleteRelationshipGroupRequest | undefined;
  deleteRelationshipRequest?: DeleteRelationshipRequest | undefined;
  queryFriendRequestsRequest?: QueryFriendRequestsRequest | undefined;
  queryRelatedUserIdsRequest?: QueryRelatedUserIdsRequest | undefined;
  queryRelationshipGroupsRequest?: QueryRelationshipGroupsRequest | undefined;
  queryRelationshipsRequest?: QueryRelationshipsRequest | undefined;
  updateFriendRequestRequest?: UpdateFriendRequestRequest | undefined;
  updateRelationshipGroupRequest?: UpdateRelationshipGroupRequest | undefined;
  updateRelationshipRequest?: UpdateRelationshipRequest | undefined;
  /** Group */
  createGroupRequest?: CreateGroupRequest | undefined;
  deleteGroupRequest?: DeleteGroupRequest | undefined;
  queryGroupRequest?: QueryGroupRequest | undefined;
  queryJoinedGroupIdsRequest?: QueryJoinedGroupIdsRequest | undefined;
  queryJoinedGroupInfosRequest?: QueryJoinedGroupInfosRequest | undefined;
  updateGroupRequest?: UpdateGroupRequest | undefined;
  /** Group Blocklist */
  createGroupBlockedUserRequest?: CreateGroupBlockedUserRequest | undefined;
  deleteGroupBlockedUserRequest?: DeleteGroupBlockedUserRequest | undefined;
  queryGroupBlockedUserIdsRequest?: QueryGroupBlockedUserIdsRequest | undefined;
  queryGroupBlockedUserInfosRequest?:
    | QueryGroupBlockedUserInfosRequest
    | undefined;
  /** Group Enrollment */
  checkGroupJoinQuestionsAnswersRequest?:
    | CheckGroupJoinQuestionsAnswersRequest
    | undefined;
  createGroupInvitationRequest?: CreateGroupInvitationRequest | undefined;
  createGroupJoinRequestRequest?: CreateGroupJoinRequestRequest | undefined;
  createGroupJoinQuestionRequest?: CreateGroupJoinQuestionRequest | undefined;
  deleteGroupInvitationRequest?: DeleteGroupInvitationRequest | undefined;
  deleteGroupJoinRequestRequest?: DeleteGroupJoinRequestRequest | undefined;
  deleteGroupJoinQuestionRequest?: DeleteGroupJoinQuestionRequest | undefined;
  queryGroupInvitationsRequest?: QueryGroupInvitationsRequest | undefined;
  queryGroupJoinRequestsRequest?: QueryGroupJoinRequestsRequest | undefined;
  queryGroupJoinQuestionsRequest?: QueryGroupJoinQuestionsRequest | undefined;
  updateGroupJoinQuestionRequest?: UpdateGroupJoinQuestionRequest | undefined;
  /** Storage */
  deleteResourceRequest?: DeleteResourceRequest | undefined;
  querySignedGetUrlRequest?: QuerySignedGetUrlRequest | undefined;
  querySignedPutUrlRequest?: QuerySignedPutUrlRequest | undefined;
}

const baseTurmsRequest: object = {};

export const TurmsRequest = {
  encode(
    message: TurmsRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.requestId !== undefined) {
      writer.uint32(8).int64(message.requestId);
    }
    if (message.createSessionRequest !== undefined) {
      CreateSessionRequest.encode(
        message.createSessionRequest,
        writer.uint32(26).fork()
      ).ldelim();
    }
    if (message.deleteSessionRequest !== undefined) {
      DeleteSessionRequest.encode(
        message.deleteSessionRequest,
        writer.uint32(34).fork()
      ).ldelim();
    }
    if (message.queryConversationsRequest !== undefined) {
      QueryConversationsRequest.encode(
        message.queryConversationsRequest,
        writer.uint32(42).fork()
      ).ldelim();
    }
    if (message.updateConversationRequest !== undefined) {
      UpdateConversationRequest.encode(
        message.updateConversationRequest,
        writer.uint32(50).fork()
      ).ldelim();
    }
    if (message.updateTypingStatusRequest !== undefined) {
      UpdateTypingStatusRequest.encode(
        message.updateTypingStatusRequest,
        writer.uint32(58).fork()
      ).ldelim();
    }
    if (message.createMessageRequest !== undefined) {
      CreateMessageRequest.encode(
        message.createMessageRequest,
        writer.uint32(66).fork()
      ).ldelim();
    }
    if (message.queryMessagesRequest !== undefined) {
      QueryMessagesRequest.encode(
        message.queryMessagesRequest,
        writer.uint32(74).fork()
      ).ldelim();
    }
    if (message.updateMessageRequest !== undefined) {
      UpdateMessageRequest.encode(
        message.updateMessageRequest,
        writer.uint32(82).fork()
      ).ldelim();
    }
    if (message.createGroupMemberRequest !== undefined) {
      CreateGroupMemberRequest.encode(
        message.createGroupMemberRequest,
        writer.uint32(90).fork()
      ).ldelim();
    }
    if (message.deleteGroupMemberRequest !== undefined) {
      DeleteGroupMemberRequest.encode(
        message.deleteGroupMemberRequest,
        writer.uint32(98).fork()
      ).ldelim();
    }
    if (message.queryGroupMembersRequest !== undefined) {
      QueryGroupMembersRequest.encode(
        message.queryGroupMembersRequest,
        writer.uint32(106).fork()
      ).ldelim();
    }
    if (message.updateGroupMemberRequest !== undefined) {
      UpdateGroupMemberRequest.encode(
        message.updateGroupMemberRequest,
        writer.uint32(114).fork()
      ).ldelim();
    }
    if (message.queryUserProfileRequest !== undefined) {
      QueryUserProfileRequest.encode(
        message.queryUserProfileRequest,
        writer.uint32(802).fork()
      ).ldelim();
    }
    if (message.queryUserIdsNearbyRequest !== undefined) {
      QueryUserIdsNearbyRequest.encode(
        message.queryUserIdsNearbyRequest,
        writer.uint32(810).fork()
      ).ldelim();
    }
    if (message.queryUserInfosNearbyRequest !== undefined) {
      QueryUserInfosNearbyRequest.encode(
        message.queryUserInfosNearbyRequest,
        writer.uint32(818).fork()
      ).ldelim();
    }
    if (message.queryUserOnlineStatusesRequest !== undefined) {
      QueryUserOnlineStatusesRequest.encode(
        message.queryUserOnlineStatusesRequest,
        writer.uint32(826).fork()
      ).ldelim();
    }
    if (message.updateUserLocationRequest !== undefined) {
      UpdateUserLocationRequest.encode(
        message.updateUserLocationRequest,
        writer.uint32(834).fork()
      ).ldelim();
    }
    if (message.updateUserOnlineStatusRequest !== undefined) {
      UpdateUserOnlineStatusRequest.encode(
        message.updateUserOnlineStatusRequest,
        writer.uint32(842).fork()
      ).ldelim();
    }
    if (message.updateUserRequest !== undefined) {
      UpdateUserRequest.encode(
        message.updateUserRequest,
        writer.uint32(850).fork()
      ).ldelim();
    }
    if (message.createFriendRequestRequest !== undefined) {
      CreateFriendRequestRequest.encode(
        message.createFriendRequestRequest,
        writer.uint32(1602).fork()
      ).ldelim();
    }
    if (message.createRelationshipGroupRequest !== undefined) {
      CreateRelationshipGroupRequest.encode(
        message.createRelationshipGroupRequest,
        writer.uint32(1610).fork()
      ).ldelim();
    }
    if (message.createRelationshipRequest !== undefined) {
      CreateRelationshipRequest.encode(
        message.createRelationshipRequest,
        writer.uint32(1618).fork()
      ).ldelim();
    }
    if (message.deleteRelationshipGroupRequest !== undefined) {
      DeleteRelationshipGroupRequest.encode(
        message.deleteRelationshipGroupRequest,
        writer.uint32(1626).fork()
      ).ldelim();
    }
    if (message.deleteRelationshipRequest !== undefined) {
      DeleteRelationshipRequest.encode(
        message.deleteRelationshipRequest,
        writer.uint32(1634).fork()
      ).ldelim();
    }
    if (message.queryFriendRequestsRequest !== undefined) {
      QueryFriendRequestsRequest.encode(
        message.queryFriendRequestsRequest,
        writer.uint32(1642).fork()
      ).ldelim();
    }
    if (message.queryRelatedUserIdsRequest !== undefined) {
      QueryRelatedUserIdsRequest.encode(
        message.queryRelatedUserIdsRequest,
        writer.uint32(1650).fork()
      ).ldelim();
    }
    if (message.queryRelationshipGroupsRequest !== undefined) {
      QueryRelationshipGroupsRequest.encode(
        message.queryRelationshipGroupsRequest,
        writer.uint32(1658).fork()
      ).ldelim();
    }
    if (message.queryRelationshipsRequest !== undefined) {
      QueryRelationshipsRequest.encode(
        message.queryRelationshipsRequest,
        writer.uint32(1666).fork()
      ).ldelim();
    }
    if (message.updateFriendRequestRequest !== undefined) {
      UpdateFriendRequestRequest.encode(
        message.updateFriendRequestRequest,
        writer.uint32(1674).fork()
      ).ldelim();
    }
    if (message.updateRelationshipGroupRequest !== undefined) {
      UpdateRelationshipGroupRequest.encode(
        message.updateRelationshipGroupRequest,
        writer.uint32(1682).fork()
      ).ldelim();
    }
    if (message.updateRelationshipRequest !== undefined) {
      UpdateRelationshipRequest.encode(
        message.updateRelationshipRequest,
        writer.uint32(1690).fork()
      ).ldelim();
    }
    if (message.createGroupRequest !== undefined) {
      CreateGroupRequest.encode(
        message.createGroupRequest,
        writer.uint32(2402).fork()
      ).ldelim();
    }
    if (message.deleteGroupRequest !== undefined) {
      DeleteGroupRequest.encode(
        message.deleteGroupRequest,
        writer.uint32(2410).fork()
      ).ldelim();
    }
    if (message.queryGroupRequest !== undefined) {
      QueryGroupRequest.encode(
        message.queryGroupRequest,
        writer.uint32(2418).fork()
      ).ldelim();
    }
    if (message.queryJoinedGroupIdsRequest !== undefined) {
      QueryJoinedGroupIdsRequest.encode(
        message.queryJoinedGroupIdsRequest,
        writer.uint32(2426).fork()
      ).ldelim();
    }
    if (message.queryJoinedGroupInfosRequest !== undefined) {
      QueryJoinedGroupInfosRequest.encode(
        message.queryJoinedGroupInfosRequest,
        writer.uint32(2434).fork()
      ).ldelim();
    }
    if (message.updateGroupRequest !== undefined) {
      UpdateGroupRequest.encode(
        message.updateGroupRequest,
        writer.uint32(2442).fork()
      ).ldelim();
    }
    if (message.createGroupBlockedUserRequest !== undefined) {
      CreateGroupBlockedUserRequest.encode(
        message.createGroupBlockedUserRequest,
        writer.uint32(3202).fork()
      ).ldelim();
    }
    if (message.deleteGroupBlockedUserRequest !== undefined) {
      DeleteGroupBlockedUserRequest.encode(
        message.deleteGroupBlockedUserRequest,
        writer.uint32(3210).fork()
      ).ldelim();
    }
    if (message.queryGroupBlockedUserIdsRequest !== undefined) {
      QueryGroupBlockedUserIdsRequest.encode(
        message.queryGroupBlockedUserIdsRequest,
        writer.uint32(3218).fork()
      ).ldelim();
    }
    if (message.queryGroupBlockedUserInfosRequest !== undefined) {
      QueryGroupBlockedUserInfosRequest.encode(
        message.queryGroupBlockedUserInfosRequest,
        writer.uint32(3226).fork()
      ).ldelim();
    }
    if (message.checkGroupJoinQuestionsAnswersRequest !== undefined) {
      CheckGroupJoinQuestionsAnswersRequest.encode(
        message.checkGroupJoinQuestionsAnswersRequest,
        writer.uint32(4002).fork()
      ).ldelim();
    }
    if (message.createGroupInvitationRequest !== undefined) {
      CreateGroupInvitationRequest.encode(
        message.createGroupInvitationRequest,
        writer.uint32(4010).fork()
      ).ldelim();
    }
    if (message.createGroupJoinRequestRequest !== undefined) {
      CreateGroupJoinRequestRequest.encode(
        message.createGroupJoinRequestRequest,
        writer.uint32(4018).fork()
      ).ldelim();
    }
    if (message.createGroupJoinQuestionRequest !== undefined) {
      CreateGroupJoinQuestionRequest.encode(
        message.createGroupJoinQuestionRequest,
        writer.uint32(4026).fork()
      ).ldelim();
    }
    if (message.deleteGroupInvitationRequest !== undefined) {
      DeleteGroupInvitationRequest.encode(
        message.deleteGroupInvitationRequest,
        writer.uint32(4034).fork()
      ).ldelim();
    }
    if (message.deleteGroupJoinRequestRequest !== undefined) {
      DeleteGroupJoinRequestRequest.encode(
        message.deleteGroupJoinRequestRequest,
        writer.uint32(4042).fork()
      ).ldelim();
    }
    if (message.deleteGroupJoinQuestionRequest !== undefined) {
      DeleteGroupJoinQuestionRequest.encode(
        message.deleteGroupJoinQuestionRequest,
        writer.uint32(4050).fork()
      ).ldelim();
    }
    if (message.queryGroupInvitationsRequest !== undefined) {
      QueryGroupInvitationsRequest.encode(
        message.queryGroupInvitationsRequest,
        writer.uint32(4058).fork()
      ).ldelim();
    }
    if (message.queryGroupJoinRequestsRequest !== undefined) {
      QueryGroupJoinRequestsRequest.encode(
        message.queryGroupJoinRequestsRequest,
        writer.uint32(4066).fork()
      ).ldelim();
    }
    if (message.queryGroupJoinQuestionsRequest !== undefined) {
      QueryGroupJoinQuestionsRequest.encode(
        message.queryGroupJoinQuestionsRequest,
        writer.uint32(4074).fork()
      ).ldelim();
    }
    if (message.updateGroupJoinQuestionRequest !== undefined) {
      UpdateGroupJoinQuestionRequest.encode(
        message.updateGroupJoinQuestionRequest,
        writer.uint32(4082).fork()
      ).ldelim();
    }
    if (message.deleteResourceRequest !== undefined) {
      DeleteResourceRequest.encode(
        message.deleteResourceRequest,
        writer.uint32(8002).fork()
      ).ldelim();
    }
    if (message.querySignedGetUrlRequest !== undefined) {
      QuerySignedGetUrlRequest.encode(
        message.querySignedGetUrlRequest,
        writer.uint32(8010).fork()
      ).ldelim();
    }
    if (message.querySignedPutUrlRequest !== undefined) {
      QuerySignedPutUrlRequest.encode(
        message.querySignedPutUrlRequest,
        writer.uint32(8018).fork()
      ).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): TurmsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseTurmsRequest } as TurmsRequest;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.requestId = longToString(reader.int64() as Long);
          break;
        case 3:
          message.createSessionRequest = CreateSessionRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 4:
          message.deleteSessionRequest = DeleteSessionRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 5:
          message.queryConversationsRequest = QueryConversationsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 6:
          message.updateConversationRequest = UpdateConversationRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 7:
          message.updateTypingStatusRequest = UpdateTypingStatusRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 8:
          message.createMessageRequest = CreateMessageRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 9:
          message.queryMessagesRequest = QueryMessagesRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 10:
          message.updateMessageRequest = UpdateMessageRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 11:
          message.createGroupMemberRequest = CreateGroupMemberRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 12:
          message.deleteGroupMemberRequest = DeleteGroupMemberRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 13:
          message.queryGroupMembersRequest = QueryGroupMembersRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 14:
          message.updateGroupMemberRequest = UpdateGroupMemberRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 100:
          message.queryUserProfileRequest = QueryUserProfileRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 101:
          message.queryUserIdsNearbyRequest = QueryUserIdsNearbyRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 102:
          message.queryUserInfosNearbyRequest = QueryUserInfosNearbyRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 103:
          message.queryUserOnlineStatusesRequest = QueryUserOnlineStatusesRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 104:
          message.updateUserLocationRequest = UpdateUserLocationRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 105:
          message.updateUserOnlineStatusRequest = UpdateUserOnlineStatusRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 106:
          message.updateUserRequest = UpdateUserRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 200:
          message.createFriendRequestRequest = CreateFriendRequestRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 201:
          message.createRelationshipGroupRequest = CreateRelationshipGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 202:
          message.createRelationshipRequest = CreateRelationshipRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 203:
          message.deleteRelationshipGroupRequest = DeleteRelationshipGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 204:
          message.deleteRelationshipRequest = DeleteRelationshipRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 205:
          message.queryFriendRequestsRequest = QueryFriendRequestsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 206:
          message.queryRelatedUserIdsRequest = QueryRelatedUserIdsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 207:
          message.queryRelationshipGroupsRequest = QueryRelationshipGroupsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 208:
          message.queryRelationshipsRequest = QueryRelationshipsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 209:
          message.updateFriendRequestRequest = UpdateFriendRequestRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 210:
          message.updateRelationshipGroupRequest = UpdateRelationshipGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 211:
          message.updateRelationshipRequest = UpdateRelationshipRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 300:
          message.createGroupRequest = CreateGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 301:
          message.deleteGroupRequest = DeleteGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 302:
          message.queryGroupRequest = QueryGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 303:
          message.queryJoinedGroupIdsRequest = QueryJoinedGroupIdsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 304:
          message.queryJoinedGroupInfosRequest = QueryJoinedGroupInfosRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 305:
          message.updateGroupRequest = UpdateGroupRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 400:
          message.createGroupBlockedUserRequest = CreateGroupBlockedUserRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 401:
          message.deleteGroupBlockedUserRequest = DeleteGroupBlockedUserRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 402:
          message.queryGroupBlockedUserIdsRequest = QueryGroupBlockedUserIdsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 403:
          message.queryGroupBlockedUserInfosRequest = QueryGroupBlockedUserInfosRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 500:
          message.checkGroupJoinQuestionsAnswersRequest = CheckGroupJoinQuestionsAnswersRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 501:
          message.createGroupInvitationRequest = CreateGroupInvitationRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 502:
          message.createGroupJoinRequestRequest = CreateGroupJoinRequestRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 503:
          message.createGroupJoinQuestionRequest = CreateGroupJoinQuestionRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 504:
          message.deleteGroupInvitationRequest = DeleteGroupInvitationRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 505:
          message.deleteGroupJoinRequestRequest = DeleteGroupJoinRequestRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 506:
          message.deleteGroupJoinQuestionRequest = DeleteGroupJoinQuestionRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 507:
          message.queryGroupInvitationsRequest = QueryGroupInvitationsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 508:
          message.queryGroupJoinRequestsRequest = QueryGroupJoinRequestsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 509:
          message.queryGroupJoinQuestionsRequest = QueryGroupJoinQuestionsRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 510:
          message.updateGroupJoinQuestionRequest = UpdateGroupJoinQuestionRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 1000:
          message.deleteResourceRequest = DeleteResourceRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 1001:
          message.querySignedGetUrlRequest = QuerySignedGetUrlRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        case 1002:
          message.querySignedPutUrlRequest = QuerySignedPutUrlRequest.decode(
            reader,
            reader.uint32()
          );
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function longToString(long: Long) {
  return long.toString();
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
