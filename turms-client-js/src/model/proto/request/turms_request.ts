/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { QueryConversationsRequest } from "./conversation/query_conversations_request";
import { UpdateConversationRequest } from "./conversation/update_conversation_request";
import { UpdateTypingStatusRequest } from "./conversation/update_typing_status_request";
import { CreateGroupBlockedUserRequest } from "./group/blocklist/create_group_blocked_user_request";
import { DeleteGroupBlockedUserRequest } from "./group/blocklist/delete_group_blocked_user_request";
import { QueryGroupBlockedUserIdsRequest } from "./group/blocklist/query_group_blocked_user_ids_request";
import { QueryGroupBlockedUserInfosRequest } from "./group/blocklist/query_group_blocked_user_infos_request";
import { CreateGroupRequest } from "./group/create_group_request";
import { DeleteGroupRequest } from "./group/delete_group_request";
import { CheckGroupJoinQuestionsAnswersRequest } from "./group/enrollment/check_group_join_questions_answers_request";
import { CreateGroupInvitationRequest } from "./group/enrollment/create_group_invitation_request";
import { CreateGroupJoinQuestionsRequest } from "./group/enrollment/create_group_join_questions_request";
import { CreateGroupJoinRequestRequest } from "./group/enrollment/create_group_join_request_request";
import { DeleteGroupInvitationRequest } from "./group/enrollment/delete_group_invitation_request";
import { DeleteGroupJoinQuestionsRequest } from "./group/enrollment/delete_group_join_questions_request";
import { DeleteGroupJoinRequestRequest } from "./group/enrollment/delete_group_join_request_request";
import { QueryGroupInvitationsRequest } from "./group/enrollment/query_group_invitations_request";
import { QueryGroupJoinQuestionsRequest } from "./group/enrollment/query_group_join_questions_request";
import { QueryGroupJoinRequestsRequest } from "./group/enrollment/query_group_join_requests_request";
import { UpdateGroupInvitationRequest } from "./group/enrollment/update_group_invitation_request";
import { UpdateGroupJoinQuestionRequest } from "./group/enrollment/update_group_join_question_request";
import { UpdateGroupJoinRequestRequest } from "./group/enrollment/update_group_join_request_request";
import { CreateGroupMembersRequest } from "./group/member/create_group_members_request";
import { DeleteGroupMembersRequest } from "./group/member/delete_group_members_request";
import { QueryGroupMembersRequest } from "./group/member/query_group_members_request";
import { UpdateGroupMemberRequest } from "./group/member/update_group_member_request";
import { QueryGroupsRequest } from "./group/query_groups_request";
import { QueryJoinedGroupIdsRequest } from "./group/query_joined_group_ids_request";
import { QueryJoinedGroupInfosRequest } from "./group/query_joined_group_infos_request";
import { UpdateGroupRequest } from "./group/update_group_request";
import { CreateMessageRequest } from "./message/create_message_request";
import { QueryMessagesRequest } from "./message/query_messages_request";
import { UpdateMessageRequest } from "./message/update_message_request";
import { DeleteResourceRequest } from "./storage/delete_resource_request";
import { QueryMessageAttachmentInfosRequest } from "./storage/query_message_attachment_infos_request";
import { QueryResourceDownloadInfoRequest } from "./storage/query_resource_download_info_request";
import { QueryResourceUploadInfoRequest } from "./storage/query_resource_upload_info_request";
import { UpdateMessageAttachmentInfoRequest } from "./storage/update_message_attachment_info_request";
import { CreateSessionRequest } from "./user/create_session_request";
import { DeleteSessionRequest } from "./user/delete_session_request";
import { QueryNearbyUsersRequest } from "./user/query_nearby_users_request";
import { QueryUserOnlineStatusesRequest } from "./user/query_user_online_statuses_request";
import { QueryUserProfilesRequest } from "./user/query_user_profiles_request";
import { CreateFriendRequestRequest } from "./user/relationship/create_friend_request_request";
import { CreateRelationshipGroupRequest } from "./user/relationship/create_relationship_group_request";
import { CreateRelationshipRequest } from "./user/relationship/create_relationship_request";
import { DeleteFriendRequestRequest } from "./user/relationship/delete_friend_request_request";
import { DeleteRelationshipGroupRequest } from "./user/relationship/delete_relationship_group_request";
import { DeleteRelationshipRequest } from "./user/relationship/delete_relationship_request";
import { QueryFriendRequestsRequest } from "./user/relationship/query_friend_requests_request";
import { QueryRelatedUserIdsRequest } from "./user/relationship/query_related_user_ids_request";
import { QueryRelationshipGroupsRequest } from "./user/relationship/query_relationship_groups_request";
import { QueryRelationshipsRequest } from "./user/relationship/query_relationships_request";
import { UpdateFriendRequestRequest } from "./user/relationship/update_friend_request_request";
import { UpdateRelationshipGroupRequest } from "./user/relationship/update_relationship_group_request";
import { UpdateRelationshipRequest } from "./user/relationship/update_relationship_request";
import { UpdateUserLocationRequest } from "./user/update_user_location_request";
import { UpdateUserOnlineStatusRequest } from "./user/update_user_online_status_request";
import { UpdateUserRequest } from "./user/update_user_request";

export const protobufPackage = "im.turms.proto";

/** Client -> Server -> Client */
export interface TurmsRequest {
  /**
   * Note: "request_id" is allowed to be duplicate because
   * it is used for clients to identify the response of the same request id in a session
   */
  requestId?:
    | string
    | undefined;
  /** User - Session */
  createSessionRequest?: CreateSessionRequest | undefined;
  deleteSessionRequest?:
    | DeleteSessionRequest
    | undefined;
  /** Conversation */
  queryConversationsRequest?: QueryConversationsRequest | undefined;
  updateConversationRequest?: UpdateConversationRequest | undefined;
  updateTypingStatusRequest?:
    | UpdateTypingStatusRequest
    | undefined;
  /** Message */
  createMessageRequest?: CreateMessageRequest | undefined;
  queryMessagesRequest?: QueryMessagesRequest | undefined;
  updateMessageRequest?:
    | UpdateMessageRequest
    | undefined;
  /** Group Member */
  createGroupMembersRequest?: CreateGroupMembersRequest | undefined;
  deleteGroupMembersRequest?: DeleteGroupMembersRequest | undefined;
  queryGroupMembersRequest?: QueryGroupMembersRequest | undefined;
  updateGroupMemberRequest?:
    | UpdateGroupMemberRequest
    | undefined;
  /** User */
  queryUserProfilesRequest?: QueryUserProfilesRequest | undefined;
  queryNearbyUsersRequest?: QueryNearbyUsersRequest | undefined;
  queryUserOnlineStatusesRequest?: QueryUserOnlineStatusesRequest | undefined;
  updateUserLocationRequest?: UpdateUserLocationRequest | undefined;
  updateUserOnlineStatusRequest?: UpdateUserOnlineStatusRequest | undefined;
  updateUserRequest?:
    | UpdateUserRequest
    | undefined;
  /** User Relationship */
  createFriendRequestRequest?: CreateFriendRequestRequest | undefined;
  createRelationshipGroupRequest?: CreateRelationshipGroupRequest | undefined;
  createRelationshipRequest?: CreateRelationshipRequest | undefined;
  deleteFriendRequestRequest?: DeleteFriendRequestRequest | undefined;
  deleteRelationshipGroupRequest?: DeleteRelationshipGroupRequest | undefined;
  deleteRelationshipRequest?: DeleteRelationshipRequest | undefined;
  queryFriendRequestsRequest?: QueryFriendRequestsRequest | undefined;
  queryRelatedUserIdsRequest?: QueryRelatedUserIdsRequest | undefined;
  queryRelationshipGroupsRequest?: QueryRelationshipGroupsRequest | undefined;
  queryRelationshipsRequest?: QueryRelationshipsRequest | undefined;
  updateFriendRequestRequest?: UpdateFriendRequestRequest | undefined;
  updateRelationshipGroupRequest?: UpdateRelationshipGroupRequest | undefined;
  updateRelationshipRequest?:
    | UpdateRelationshipRequest
    | undefined;
  /** Group */
  createGroupRequest?: CreateGroupRequest | undefined;
  deleteGroupRequest?: DeleteGroupRequest | undefined;
  queryGroupsRequest?: QueryGroupsRequest | undefined;
  queryJoinedGroupIdsRequest?: QueryJoinedGroupIdsRequest | undefined;
  queryJoinedGroupInfosRequest?: QueryJoinedGroupInfosRequest | undefined;
  updateGroupRequest?:
    | UpdateGroupRequest
    | undefined;
  /** Group Blocklist */
  createGroupBlockedUserRequest?: CreateGroupBlockedUserRequest | undefined;
  deleteGroupBlockedUserRequest?: DeleteGroupBlockedUserRequest | undefined;
  queryGroupBlockedUserIdsRequest?: QueryGroupBlockedUserIdsRequest | undefined;
  queryGroupBlockedUserInfosRequest?:
    | QueryGroupBlockedUserInfosRequest
    | undefined;
  /** Group Enrollment */
  checkGroupJoinQuestionsAnswersRequest?: CheckGroupJoinQuestionsAnswersRequest | undefined;
  createGroupInvitationRequest?: CreateGroupInvitationRequest | undefined;
  createGroupJoinRequestRequest?: CreateGroupJoinRequestRequest | undefined;
  createGroupJoinQuestionsRequest?: CreateGroupJoinQuestionsRequest | undefined;
  deleteGroupInvitationRequest?: DeleteGroupInvitationRequest | undefined;
  deleteGroupJoinRequestRequest?: DeleteGroupJoinRequestRequest | undefined;
  deleteGroupJoinQuestionsRequest?: DeleteGroupJoinQuestionsRequest | undefined;
  queryGroupInvitationsRequest?: QueryGroupInvitationsRequest | undefined;
  queryGroupJoinRequestsRequest?: QueryGroupJoinRequestsRequest | undefined;
  queryGroupJoinQuestionsRequest?: QueryGroupJoinQuestionsRequest | undefined;
  updateGroupInvitationRequest?: UpdateGroupInvitationRequest | undefined;
  updateGroupJoinQuestionRequest?: UpdateGroupJoinQuestionRequest | undefined;
  updateGroupJoinRequestRequest?:
    | UpdateGroupJoinRequestRequest
    | undefined;
  /** Storage */
  deleteResourceRequest?: DeleteResourceRequest | undefined;
  queryResourceDownloadInfoRequest?: QueryResourceDownloadInfoRequest | undefined;
  queryResourceUploadInfoRequest?: QueryResourceUploadInfoRequest | undefined;
  queryMessageAttachmentInfosRequest?: QueryMessageAttachmentInfosRequest | undefined;
  updateMessageAttachmentInfoRequest?: UpdateMessageAttachmentInfoRequest | undefined;
}

function createBaseTurmsRequest(): TurmsRequest {
  return {
    requestId: undefined,
    createSessionRequest: undefined,
    deleteSessionRequest: undefined,
    queryConversationsRequest: undefined,
    updateConversationRequest: undefined,
    updateTypingStatusRequest: undefined,
    createMessageRequest: undefined,
    queryMessagesRequest: undefined,
    updateMessageRequest: undefined,
    createGroupMembersRequest: undefined,
    deleteGroupMembersRequest: undefined,
    queryGroupMembersRequest: undefined,
    updateGroupMemberRequest: undefined,
    queryUserProfilesRequest: undefined,
    queryNearbyUsersRequest: undefined,
    queryUserOnlineStatusesRequest: undefined,
    updateUserLocationRequest: undefined,
    updateUserOnlineStatusRequest: undefined,
    updateUserRequest: undefined,
    createFriendRequestRequest: undefined,
    createRelationshipGroupRequest: undefined,
    createRelationshipRequest: undefined,
    deleteFriendRequestRequest: undefined,
    deleteRelationshipGroupRequest: undefined,
    deleteRelationshipRequest: undefined,
    queryFriendRequestsRequest: undefined,
    queryRelatedUserIdsRequest: undefined,
    queryRelationshipGroupsRequest: undefined,
    queryRelationshipsRequest: undefined,
    updateFriendRequestRequest: undefined,
    updateRelationshipGroupRequest: undefined,
    updateRelationshipRequest: undefined,
    createGroupRequest: undefined,
    deleteGroupRequest: undefined,
    queryGroupsRequest: undefined,
    queryJoinedGroupIdsRequest: undefined,
    queryJoinedGroupInfosRequest: undefined,
    updateGroupRequest: undefined,
    createGroupBlockedUserRequest: undefined,
    deleteGroupBlockedUserRequest: undefined,
    queryGroupBlockedUserIdsRequest: undefined,
    queryGroupBlockedUserInfosRequest: undefined,
    checkGroupJoinQuestionsAnswersRequest: undefined,
    createGroupInvitationRequest: undefined,
    createGroupJoinRequestRequest: undefined,
    createGroupJoinQuestionsRequest: undefined,
    deleteGroupInvitationRequest: undefined,
    deleteGroupJoinRequestRequest: undefined,
    deleteGroupJoinQuestionsRequest: undefined,
    queryGroupInvitationsRequest: undefined,
    queryGroupJoinRequestsRequest: undefined,
    queryGroupJoinQuestionsRequest: undefined,
    updateGroupInvitationRequest: undefined,
    updateGroupJoinQuestionRequest: undefined,
    updateGroupJoinRequestRequest: undefined,
    deleteResourceRequest: undefined,
    queryResourceDownloadInfoRequest: undefined,
    queryResourceUploadInfoRequest: undefined,
    queryMessageAttachmentInfosRequest: undefined,
    updateMessageAttachmentInfoRequest: undefined,
  };
}

export const TurmsRequest = {
  encode(message: TurmsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.requestId !== undefined) {
      writer.uint32(8).int64(message.requestId);
    }
    if (message.createSessionRequest !== undefined) {
      CreateSessionRequest.encode(message.createSessionRequest, writer.uint32(26).fork()).ldelim();
    }
    if (message.deleteSessionRequest !== undefined) {
      DeleteSessionRequest.encode(message.deleteSessionRequest, writer.uint32(34).fork()).ldelim();
    }
    if (message.queryConversationsRequest !== undefined) {
      QueryConversationsRequest.encode(message.queryConversationsRequest, writer.uint32(42).fork()).ldelim();
    }
    if (message.updateConversationRequest !== undefined) {
      UpdateConversationRequest.encode(message.updateConversationRequest, writer.uint32(50).fork()).ldelim();
    }
    if (message.updateTypingStatusRequest !== undefined) {
      UpdateTypingStatusRequest.encode(message.updateTypingStatusRequest, writer.uint32(58).fork()).ldelim();
    }
    if (message.createMessageRequest !== undefined) {
      CreateMessageRequest.encode(message.createMessageRequest, writer.uint32(66).fork()).ldelim();
    }
    if (message.queryMessagesRequest !== undefined) {
      QueryMessagesRequest.encode(message.queryMessagesRequest, writer.uint32(74).fork()).ldelim();
    }
    if (message.updateMessageRequest !== undefined) {
      UpdateMessageRequest.encode(message.updateMessageRequest, writer.uint32(82).fork()).ldelim();
    }
    if (message.createGroupMembersRequest !== undefined) {
      CreateGroupMembersRequest.encode(message.createGroupMembersRequest, writer.uint32(90).fork()).ldelim();
    }
    if (message.deleteGroupMembersRequest !== undefined) {
      DeleteGroupMembersRequest.encode(message.deleteGroupMembersRequest, writer.uint32(98).fork()).ldelim();
    }
    if (message.queryGroupMembersRequest !== undefined) {
      QueryGroupMembersRequest.encode(message.queryGroupMembersRequest, writer.uint32(106).fork()).ldelim();
    }
    if (message.updateGroupMemberRequest !== undefined) {
      UpdateGroupMemberRequest.encode(message.updateGroupMemberRequest, writer.uint32(114).fork()).ldelim();
    }
    if (message.queryUserProfilesRequest !== undefined) {
      QueryUserProfilesRequest.encode(message.queryUserProfilesRequest, writer.uint32(802).fork()).ldelim();
    }
    if (message.queryNearbyUsersRequest !== undefined) {
      QueryNearbyUsersRequest.encode(message.queryNearbyUsersRequest, writer.uint32(810).fork()).ldelim();
    }
    if (message.queryUserOnlineStatusesRequest !== undefined) {
      QueryUserOnlineStatusesRequest.encode(message.queryUserOnlineStatusesRequest, writer.uint32(818).fork()).ldelim();
    }
    if (message.updateUserLocationRequest !== undefined) {
      UpdateUserLocationRequest.encode(message.updateUserLocationRequest, writer.uint32(826).fork()).ldelim();
    }
    if (message.updateUserOnlineStatusRequest !== undefined) {
      UpdateUserOnlineStatusRequest.encode(message.updateUserOnlineStatusRequest, writer.uint32(834).fork()).ldelim();
    }
    if (message.updateUserRequest !== undefined) {
      UpdateUserRequest.encode(message.updateUserRequest, writer.uint32(842).fork()).ldelim();
    }
    if (message.createFriendRequestRequest !== undefined) {
      CreateFriendRequestRequest.encode(message.createFriendRequestRequest, writer.uint32(1602).fork()).ldelim();
    }
    if (message.createRelationshipGroupRequest !== undefined) {
      CreateRelationshipGroupRequest.encode(message.createRelationshipGroupRequest, writer.uint32(1610).fork())
        .ldelim();
    }
    if (message.createRelationshipRequest !== undefined) {
      CreateRelationshipRequest.encode(message.createRelationshipRequest, writer.uint32(1618).fork()).ldelim();
    }
    if (message.deleteFriendRequestRequest !== undefined) {
      DeleteFriendRequestRequest.encode(message.deleteFriendRequestRequest, writer.uint32(1626).fork()).ldelim();
    }
    if (message.deleteRelationshipGroupRequest !== undefined) {
      DeleteRelationshipGroupRequest.encode(message.deleteRelationshipGroupRequest, writer.uint32(1634).fork())
        .ldelim();
    }
    if (message.deleteRelationshipRequest !== undefined) {
      DeleteRelationshipRequest.encode(message.deleteRelationshipRequest, writer.uint32(1642).fork()).ldelim();
    }
    if (message.queryFriendRequestsRequest !== undefined) {
      QueryFriendRequestsRequest.encode(message.queryFriendRequestsRequest, writer.uint32(1650).fork()).ldelim();
    }
    if (message.queryRelatedUserIdsRequest !== undefined) {
      QueryRelatedUserIdsRequest.encode(message.queryRelatedUserIdsRequest, writer.uint32(1658).fork()).ldelim();
    }
    if (message.queryRelationshipGroupsRequest !== undefined) {
      QueryRelationshipGroupsRequest.encode(message.queryRelationshipGroupsRequest, writer.uint32(1666).fork())
        .ldelim();
    }
    if (message.queryRelationshipsRequest !== undefined) {
      QueryRelationshipsRequest.encode(message.queryRelationshipsRequest, writer.uint32(1674).fork()).ldelim();
    }
    if (message.updateFriendRequestRequest !== undefined) {
      UpdateFriendRequestRequest.encode(message.updateFriendRequestRequest, writer.uint32(1682).fork()).ldelim();
    }
    if (message.updateRelationshipGroupRequest !== undefined) {
      UpdateRelationshipGroupRequest.encode(message.updateRelationshipGroupRequest, writer.uint32(1690).fork())
        .ldelim();
    }
    if (message.updateRelationshipRequest !== undefined) {
      UpdateRelationshipRequest.encode(message.updateRelationshipRequest, writer.uint32(1698).fork()).ldelim();
    }
    if (message.createGroupRequest !== undefined) {
      CreateGroupRequest.encode(message.createGroupRequest, writer.uint32(2402).fork()).ldelim();
    }
    if (message.deleteGroupRequest !== undefined) {
      DeleteGroupRequest.encode(message.deleteGroupRequest, writer.uint32(2410).fork()).ldelim();
    }
    if (message.queryGroupsRequest !== undefined) {
      QueryGroupsRequest.encode(message.queryGroupsRequest, writer.uint32(2418).fork()).ldelim();
    }
    if (message.queryJoinedGroupIdsRequest !== undefined) {
      QueryJoinedGroupIdsRequest.encode(message.queryJoinedGroupIdsRequest, writer.uint32(2426).fork()).ldelim();
    }
    if (message.queryJoinedGroupInfosRequest !== undefined) {
      QueryJoinedGroupInfosRequest.encode(message.queryJoinedGroupInfosRequest, writer.uint32(2434).fork()).ldelim();
    }
    if (message.updateGroupRequest !== undefined) {
      UpdateGroupRequest.encode(message.updateGroupRequest, writer.uint32(2442).fork()).ldelim();
    }
    if (message.createGroupBlockedUserRequest !== undefined) {
      CreateGroupBlockedUserRequest.encode(message.createGroupBlockedUserRequest, writer.uint32(3202).fork()).ldelim();
    }
    if (message.deleteGroupBlockedUserRequest !== undefined) {
      DeleteGroupBlockedUserRequest.encode(message.deleteGroupBlockedUserRequest, writer.uint32(3210).fork()).ldelim();
    }
    if (message.queryGroupBlockedUserIdsRequest !== undefined) {
      QueryGroupBlockedUserIdsRequest.encode(message.queryGroupBlockedUserIdsRequest, writer.uint32(3218).fork())
        .ldelim();
    }
    if (message.queryGroupBlockedUserInfosRequest !== undefined) {
      QueryGroupBlockedUserInfosRequest.encode(message.queryGroupBlockedUserInfosRequest, writer.uint32(3226).fork())
        .ldelim();
    }
    if (message.checkGroupJoinQuestionsAnswersRequest !== undefined) {
      CheckGroupJoinQuestionsAnswersRequest.encode(
        message.checkGroupJoinQuestionsAnswersRequest,
        writer.uint32(4002).fork(),
      ).ldelim();
    }
    if (message.createGroupInvitationRequest !== undefined) {
      CreateGroupInvitationRequest.encode(message.createGroupInvitationRequest, writer.uint32(4010).fork()).ldelim();
    }
    if (message.createGroupJoinRequestRequest !== undefined) {
      CreateGroupJoinRequestRequest.encode(message.createGroupJoinRequestRequest, writer.uint32(4018).fork()).ldelim();
    }
    if (message.createGroupJoinQuestionsRequest !== undefined) {
      CreateGroupJoinQuestionsRequest.encode(message.createGroupJoinQuestionsRequest, writer.uint32(4026).fork())
        .ldelim();
    }
    if (message.deleteGroupInvitationRequest !== undefined) {
      DeleteGroupInvitationRequest.encode(message.deleteGroupInvitationRequest, writer.uint32(4034).fork()).ldelim();
    }
    if (message.deleteGroupJoinRequestRequest !== undefined) {
      DeleteGroupJoinRequestRequest.encode(message.deleteGroupJoinRequestRequest, writer.uint32(4042).fork()).ldelim();
    }
    if (message.deleteGroupJoinQuestionsRequest !== undefined) {
      DeleteGroupJoinQuestionsRequest.encode(message.deleteGroupJoinQuestionsRequest, writer.uint32(4050).fork())
        .ldelim();
    }
    if (message.queryGroupInvitationsRequest !== undefined) {
      QueryGroupInvitationsRequest.encode(message.queryGroupInvitationsRequest, writer.uint32(4058).fork()).ldelim();
    }
    if (message.queryGroupJoinRequestsRequest !== undefined) {
      QueryGroupJoinRequestsRequest.encode(message.queryGroupJoinRequestsRequest, writer.uint32(4066).fork()).ldelim();
    }
    if (message.queryGroupJoinQuestionsRequest !== undefined) {
      QueryGroupJoinQuestionsRequest.encode(message.queryGroupJoinQuestionsRequest, writer.uint32(4074).fork())
        .ldelim();
    }
    if (message.updateGroupInvitationRequest !== undefined) {
      UpdateGroupInvitationRequest.encode(message.updateGroupInvitationRequest, writer.uint32(4082).fork()).ldelim();
    }
    if (message.updateGroupJoinQuestionRequest !== undefined) {
      UpdateGroupJoinQuestionRequest.encode(message.updateGroupJoinQuestionRequest, writer.uint32(4090).fork())
        .ldelim();
    }
    if (message.updateGroupJoinRequestRequest !== undefined) {
      UpdateGroupJoinRequestRequest.encode(message.updateGroupJoinRequestRequest, writer.uint32(4098).fork()).ldelim();
    }
    if (message.deleteResourceRequest !== undefined) {
      DeleteResourceRequest.encode(message.deleteResourceRequest, writer.uint32(8002).fork()).ldelim();
    }
    if (message.queryResourceDownloadInfoRequest !== undefined) {
      QueryResourceDownloadInfoRequest.encode(message.queryResourceDownloadInfoRequest, writer.uint32(8010).fork())
        .ldelim();
    }
    if (message.queryResourceUploadInfoRequest !== undefined) {
      QueryResourceUploadInfoRequest.encode(message.queryResourceUploadInfoRequest, writer.uint32(8018).fork())
        .ldelim();
    }
    if (message.queryMessageAttachmentInfosRequest !== undefined) {
      QueryMessageAttachmentInfosRequest.encode(message.queryMessageAttachmentInfosRequest, writer.uint32(8026).fork())
        .ldelim();
    }
    if (message.updateMessageAttachmentInfoRequest !== undefined) {
      UpdateMessageAttachmentInfoRequest.encode(message.updateMessageAttachmentInfoRequest, writer.uint32(8034).fork())
        .ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): TurmsRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseTurmsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.requestId = longToString(reader.int64() as Long);
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.createSessionRequest = CreateSessionRequest.decode(reader, reader.uint32());
          continue;
        case 4:
          if (tag !== 34) {
            break;
          }

          message.deleteSessionRequest = DeleteSessionRequest.decode(reader, reader.uint32());
          continue;
        case 5:
          if (tag !== 42) {
            break;
          }

          message.queryConversationsRequest = QueryConversationsRequest.decode(reader, reader.uint32());
          continue;
        case 6:
          if (tag !== 50) {
            break;
          }

          message.updateConversationRequest = UpdateConversationRequest.decode(reader, reader.uint32());
          continue;
        case 7:
          if (tag !== 58) {
            break;
          }

          message.updateTypingStatusRequest = UpdateTypingStatusRequest.decode(reader, reader.uint32());
          continue;
        case 8:
          if (tag !== 66) {
            break;
          }

          message.createMessageRequest = CreateMessageRequest.decode(reader, reader.uint32());
          continue;
        case 9:
          if (tag !== 74) {
            break;
          }

          message.queryMessagesRequest = QueryMessagesRequest.decode(reader, reader.uint32());
          continue;
        case 10:
          if (tag !== 82) {
            break;
          }

          message.updateMessageRequest = UpdateMessageRequest.decode(reader, reader.uint32());
          continue;
        case 11:
          if (tag !== 90) {
            break;
          }

          message.createGroupMembersRequest = CreateGroupMembersRequest.decode(reader, reader.uint32());
          continue;
        case 12:
          if (tag !== 98) {
            break;
          }

          message.deleteGroupMembersRequest = DeleteGroupMembersRequest.decode(reader, reader.uint32());
          continue;
        case 13:
          if (tag !== 106) {
            break;
          }

          message.queryGroupMembersRequest = QueryGroupMembersRequest.decode(reader, reader.uint32());
          continue;
        case 14:
          if (tag !== 114) {
            break;
          }

          message.updateGroupMemberRequest = UpdateGroupMemberRequest.decode(reader, reader.uint32());
          continue;
        case 100:
          if (tag !== 802) {
            break;
          }

          message.queryUserProfilesRequest = QueryUserProfilesRequest.decode(reader, reader.uint32());
          continue;
        case 101:
          if (tag !== 810) {
            break;
          }

          message.queryNearbyUsersRequest = QueryNearbyUsersRequest.decode(reader, reader.uint32());
          continue;
        case 102:
          if (tag !== 818) {
            break;
          }

          message.queryUserOnlineStatusesRequest = QueryUserOnlineStatusesRequest.decode(reader, reader.uint32());
          continue;
        case 103:
          if (tag !== 826) {
            break;
          }

          message.updateUserLocationRequest = UpdateUserLocationRequest.decode(reader, reader.uint32());
          continue;
        case 104:
          if (tag !== 834) {
            break;
          }

          message.updateUserOnlineStatusRequest = UpdateUserOnlineStatusRequest.decode(reader, reader.uint32());
          continue;
        case 105:
          if (tag !== 842) {
            break;
          }

          message.updateUserRequest = UpdateUserRequest.decode(reader, reader.uint32());
          continue;
        case 200:
          if (tag !== 1602) {
            break;
          }

          message.createFriendRequestRequest = CreateFriendRequestRequest.decode(reader, reader.uint32());
          continue;
        case 201:
          if (tag !== 1610) {
            break;
          }

          message.createRelationshipGroupRequest = CreateRelationshipGroupRequest.decode(reader, reader.uint32());
          continue;
        case 202:
          if (tag !== 1618) {
            break;
          }

          message.createRelationshipRequest = CreateRelationshipRequest.decode(reader, reader.uint32());
          continue;
        case 203:
          if (tag !== 1626) {
            break;
          }

          message.deleteFriendRequestRequest = DeleteFriendRequestRequest.decode(reader, reader.uint32());
          continue;
        case 204:
          if (tag !== 1634) {
            break;
          }

          message.deleteRelationshipGroupRequest = DeleteRelationshipGroupRequest.decode(reader, reader.uint32());
          continue;
        case 205:
          if (tag !== 1642) {
            break;
          }

          message.deleteRelationshipRequest = DeleteRelationshipRequest.decode(reader, reader.uint32());
          continue;
        case 206:
          if (tag !== 1650) {
            break;
          }

          message.queryFriendRequestsRequest = QueryFriendRequestsRequest.decode(reader, reader.uint32());
          continue;
        case 207:
          if (tag !== 1658) {
            break;
          }

          message.queryRelatedUserIdsRequest = QueryRelatedUserIdsRequest.decode(reader, reader.uint32());
          continue;
        case 208:
          if (tag !== 1666) {
            break;
          }

          message.queryRelationshipGroupsRequest = QueryRelationshipGroupsRequest.decode(reader, reader.uint32());
          continue;
        case 209:
          if (tag !== 1674) {
            break;
          }

          message.queryRelationshipsRequest = QueryRelationshipsRequest.decode(reader, reader.uint32());
          continue;
        case 210:
          if (tag !== 1682) {
            break;
          }

          message.updateFriendRequestRequest = UpdateFriendRequestRequest.decode(reader, reader.uint32());
          continue;
        case 211:
          if (tag !== 1690) {
            break;
          }

          message.updateRelationshipGroupRequest = UpdateRelationshipGroupRequest.decode(reader, reader.uint32());
          continue;
        case 212:
          if (tag !== 1698) {
            break;
          }

          message.updateRelationshipRequest = UpdateRelationshipRequest.decode(reader, reader.uint32());
          continue;
        case 300:
          if (tag !== 2402) {
            break;
          }

          message.createGroupRequest = CreateGroupRequest.decode(reader, reader.uint32());
          continue;
        case 301:
          if (tag !== 2410) {
            break;
          }

          message.deleteGroupRequest = DeleteGroupRequest.decode(reader, reader.uint32());
          continue;
        case 302:
          if (tag !== 2418) {
            break;
          }

          message.queryGroupsRequest = QueryGroupsRequest.decode(reader, reader.uint32());
          continue;
        case 303:
          if (tag !== 2426) {
            break;
          }

          message.queryJoinedGroupIdsRequest = QueryJoinedGroupIdsRequest.decode(reader, reader.uint32());
          continue;
        case 304:
          if (tag !== 2434) {
            break;
          }

          message.queryJoinedGroupInfosRequest = QueryJoinedGroupInfosRequest.decode(reader, reader.uint32());
          continue;
        case 305:
          if (tag !== 2442) {
            break;
          }

          message.updateGroupRequest = UpdateGroupRequest.decode(reader, reader.uint32());
          continue;
        case 400:
          if (tag !== 3202) {
            break;
          }

          message.createGroupBlockedUserRequest = CreateGroupBlockedUserRequest.decode(reader, reader.uint32());
          continue;
        case 401:
          if (tag !== 3210) {
            break;
          }

          message.deleteGroupBlockedUserRequest = DeleteGroupBlockedUserRequest.decode(reader, reader.uint32());
          continue;
        case 402:
          if (tag !== 3218) {
            break;
          }

          message.queryGroupBlockedUserIdsRequest = QueryGroupBlockedUserIdsRequest.decode(reader, reader.uint32());
          continue;
        case 403:
          if (tag !== 3226) {
            break;
          }

          message.queryGroupBlockedUserInfosRequest = QueryGroupBlockedUserInfosRequest.decode(reader, reader.uint32());
          continue;
        case 500:
          if (tag !== 4002) {
            break;
          }

          message.checkGroupJoinQuestionsAnswersRequest = CheckGroupJoinQuestionsAnswersRequest.decode(
            reader,
            reader.uint32(),
          );
          continue;
        case 501:
          if (tag !== 4010) {
            break;
          }

          message.createGroupInvitationRequest = CreateGroupInvitationRequest.decode(reader, reader.uint32());
          continue;
        case 502:
          if (tag !== 4018) {
            break;
          }

          message.createGroupJoinRequestRequest = CreateGroupJoinRequestRequest.decode(reader, reader.uint32());
          continue;
        case 503:
          if (tag !== 4026) {
            break;
          }

          message.createGroupJoinQuestionsRequest = CreateGroupJoinQuestionsRequest.decode(reader, reader.uint32());
          continue;
        case 504:
          if (tag !== 4034) {
            break;
          }

          message.deleteGroupInvitationRequest = DeleteGroupInvitationRequest.decode(reader, reader.uint32());
          continue;
        case 505:
          if (tag !== 4042) {
            break;
          }

          message.deleteGroupJoinRequestRequest = DeleteGroupJoinRequestRequest.decode(reader, reader.uint32());
          continue;
        case 506:
          if (tag !== 4050) {
            break;
          }

          message.deleteGroupJoinQuestionsRequest = DeleteGroupJoinQuestionsRequest.decode(reader, reader.uint32());
          continue;
        case 507:
          if (tag !== 4058) {
            break;
          }

          message.queryGroupInvitationsRequest = QueryGroupInvitationsRequest.decode(reader, reader.uint32());
          continue;
        case 508:
          if (tag !== 4066) {
            break;
          }

          message.queryGroupJoinRequestsRequest = QueryGroupJoinRequestsRequest.decode(reader, reader.uint32());
          continue;
        case 509:
          if (tag !== 4074) {
            break;
          }

          message.queryGroupJoinQuestionsRequest = QueryGroupJoinQuestionsRequest.decode(reader, reader.uint32());
          continue;
        case 510:
          if (tag !== 4082) {
            break;
          }

          message.updateGroupInvitationRequest = UpdateGroupInvitationRequest.decode(reader, reader.uint32());
          continue;
        case 511:
          if (tag !== 4090) {
            break;
          }

          message.updateGroupJoinQuestionRequest = UpdateGroupJoinQuestionRequest.decode(reader, reader.uint32());
          continue;
        case 512:
          if (tag !== 4098) {
            break;
          }

          message.updateGroupJoinRequestRequest = UpdateGroupJoinRequestRequest.decode(reader, reader.uint32());
          continue;
        case 1000:
          if (tag !== 8002) {
            break;
          }

          message.deleteResourceRequest = DeleteResourceRequest.decode(reader, reader.uint32());
          continue;
        case 1001:
          if (tag !== 8010) {
            break;
          }

          message.queryResourceDownloadInfoRequest = QueryResourceDownloadInfoRequest.decode(reader, reader.uint32());
          continue;
        case 1002:
          if (tag !== 8018) {
            break;
          }

          message.queryResourceUploadInfoRequest = QueryResourceUploadInfoRequest.decode(reader, reader.uint32());
          continue;
        case 1003:
          if (tag !== 8026) {
            break;
          }

          message.queryMessageAttachmentInfosRequest = QueryMessageAttachmentInfosRequest.decode(
            reader,
            reader.uint32(),
          );
          continue;
        case 1004:
          if (tag !== 8034) {
            break;
          }

          message.updateMessageAttachmentInfoRequest = UpdateMessageAttachmentInfoRequest.decode(
            reader,
            reader.uint32(),
          );
          continue;
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skipType(tag & 7);
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