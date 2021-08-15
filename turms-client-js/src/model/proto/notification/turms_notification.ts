/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { TurmsRequest } from "../request/turms_request";
import { Int64Values } from "../model/common/int64_values";
import { Int64ValuesWithVersion } from "../model/common/int64_values_with_version";
import { Conversations } from "../model/conversation/conversations";
import { Messages } from "../model/message/messages";
import { MessagesWithTotalList } from "../model/message/messages_with_total_list";
import { UserSession } from "../model/user/user_session";
import { UsersInfosWithVersion } from "../model/user/users_infos_with_version";
import { UsersOnlineStatuses } from "../model/user/users_online_statuses";
import { UserFriendRequestsWithVersion } from "../model/user/user_friend_requests_with_version";
import { UserRelationshipGroupsWithVersion } from "../model/user/user_relationship_groups_with_version";
import { UserRelationshipsWithVersion } from "../model/user/user_relationships_with_version";
import { NearbyUsers } from "../model/user/nearby_users";
import { GroupInvitationsWithVersion } from "../model/group/group_invitations_with_version";
import { GroupJoinQuestionsAnswerResult } from "../model/group/group_join_questions_answer_result";
import { GroupJoinRequestsWithVersion } from "../model/group/group_join_requests_with_version";
import { GroupJoinQuestionsWithVersion } from "../model/group/group_join_questions_with_version";
import { GroupMembersWithVersion } from "../model/group/group_members_with_version";
import { GroupsWithVersion } from "../model/group/groups_with_version";

export const protobufPackage = "im.turms.proto";

export interface TurmsNotification {
  /**
   * Response
   * request_id is used to tell the client that this notification is for the specific request
   */
  requestId?: string | undefined;
  code?: number | undefined;
  reason?: string | undefined;
  data?: TurmsNotification_Data;
  /**
   * Notification
   * requester_id only exists when a requester triggers a notification to its recipients
   * Note: Do not move requester_id to TurmsRequest because it requires rebuilding
   * a new TurmsNotification when recipients need the requester_id.
   */
  requesterId?: string | undefined;
  closeStatus?: number | undefined;
  relayedRequest?: TurmsRequest;
}

export interface TurmsNotification_Data {
  ids?: Int64Values | undefined;
  idsWithVersion?: Int64ValuesWithVersion | undefined;
  url: string | undefined;
  conversations?: Conversations | undefined;
  messages?: Messages | undefined;
  messagesWithTotalList?: MessagesWithTotalList | undefined;
  userSession?: UserSession | undefined;
  usersInfosWithVersion?: UsersInfosWithVersion | undefined;
  usersOnlineStatuses?: UsersOnlineStatuses | undefined;
  userFriendRequestsWithVersion?: UserFriendRequestsWithVersion | undefined;
  userRelationshipGroupsWithVersion?:
    | UserRelationshipGroupsWithVersion
    | undefined;
  userRelationshipsWithVersion?: UserRelationshipsWithVersion | undefined;
  nearbyUsers?: NearbyUsers | undefined;
  groupInvitationsWithVersion?: GroupInvitationsWithVersion | undefined;
  groupJoinQuestionAnswerResult?: GroupJoinQuestionsAnswerResult | undefined;
  groupJoinRequestsWithVersion?: GroupJoinRequestsWithVersion | undefined;
  groupJoinQuestionsWithVersion?: GroupJoinQuestionsWithVersion | undefined;
  groupMembersWithVersion?: GroupMembersWithVersion | undefined;
  groupsWithVersion?: GroupsWithVersion | undefined;
}

const baseTurmsNotification: object = {};

export const TurmsNotification = {
  encode(
    message: TurmsNotification,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.requestId !== undefined) {
      writer.uint32(8).int64(message.requestId);
    }
    if (message.code !== undefined) {
      writer.uint32(16).int32(message.code);
    }
    if (message.reason !== undefined) {
      writer.uint32(26).string(message.reason);
    }
    if (message.data !== undefined) {
      TurmsNotification_Data.encode(
        message.data,
        writer.uint32(34).fork()
      ).ldelim();
    }
    if (message.requesterId !== undefined) {
      writer.uint32(40).int64(message.requesterId);
    }
    if (message.closeStatus !== undefined) {
      writer.uint32(48).int32(message.closeStatus);
    }
    if (message.relayedRequest !== undefined) {
      TurmsRequest.encode(
        message.relayedRequest,
        writer.uint32(58).fork()
      ).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): TurmsNotification {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseTurmsNotification } as TurmsNotification;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.requestId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.code = reader.int32();
          break;
        case 3:
          message.reason = reader.string();
          break;
        case 4:
          message.data = TurmsNotification_Data.decode(reader, reader.uint32());
          break;
        case 5:
          message.requesterId = longToString(reader.int64() as Long);
          break;
        case 6:
          message.closeStatus = reader.int32();
          break;
        case 7:
          message.relayedRequest = TurmsRequest.decode(reader, reader.uint32());
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

const baseTurmsNotification_Data: object = {};

export const TurmsNotification_Data = {
  encode(
    message: TurmsNotification_Data,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.ids !== undefined) {
      Int64Values.encode(message.ids, writer.uint32(10).fork()).ldelim();
    }
    if (message.idsWithVersion !== undefined) {
      Int64ValuesWithVersion.encode(
        message.idsWithVersion,
        writer.uint32(18).fork()
      ).ldelim();
    }
    if (message.url !== undefined) {
      writer.uint32(26).string(message.url);
    }
    if (message.conversations !== undefined) {
      Conversations.encode(
        message.conversations,
        writer.uint32(34).fork()
      ).ldelim();
    }
    if (message.messages !== undefined) {
      Messages.encode(message.messages, writer.uint32(42).fork()).ldelim();
    }
    if (message.messagesWithTotalList !== undefined) {
      MessagesWithTotalList.encode(
        message.messagesWithTotalList,
        writer.uint32(50).fork()
      ).ldelim();
    }
    if (message.userSession !== undefined) {
      UserSession.encode(
        message.userSession,
        writer.uint32(58).fork()
      ).ldelim();
    }
    if (message.usersInfosWithVersion !== undefined) {
      UsersInfosWithVersion.encode(
        message.usersInfosWithVersion,
        writer.uint32(66).fork()
      ).ldelim();
    }
    if (message.usersOnlineStatuses !== undefined) {
      UsersOnlineStatuses.encode(
        message.usersOnlineStatuses,
        writer.uint32(74).fork()
      ).ldelim();
    }
    if (message.userFriendRequestsWithVersion !== undefined) {
      UserFriendRequestsWithVersion.encode(
        message.userFriendRequestsWithVersion,
        writer.uint32(82).fork()
      ).ldelim();
    }
    if (message.userRelationshipGroupsWithVersion !== undefined) {
      UserRelationshipGroupsWithVersion.encode(
        message.userRelationshipGroupsWithVersion,
        writer.uint32(90).fork()
      ).ldelim();
    }
    if (message.userRelationshipsWithVersion !== undefined) {
      UserRelationshipsWithVersion.encode(
        message.userRelationshipsWithVersion,
        writer.uint32(98).fork()
      ).ldelim();
    }
    if (message.nearbyUsers !== undefined) {
      NearbyUsers.encode(
        message.nearbyUsers,
        writer.uint32(106).fork()
      ).ldelim();
    }
    if (message.groupInvitationsWithVersion !== undefined) {
      GroupInvitationsWithVersion.encode(
        message.groupInvitationsWithVersion,
        writer.uint32(114).fork()
      ).ldelim();
    }
    if (message.groupJoinQuestionAnswerResult !== undefined) {
      GroupJoinQuestionsAnswerResult.encode(
        message.groupJoinQuestionAnswerResult,
        writer.uint32(122).fork()
      ).ldelim();
    }
    if (message.groupJoinRequestsWithVersion !== undefined) {
      GroupJoinRequestsWithVersion.encode(
        message.groupJoinRequestsWithVersion,
        writer.uint32(130).fork()
      ).ldelim();
    }
    if (message.groupJoinQuestionsWithVersion !== undefined) {
      GroupJoinQuestionsWithVersion.encode(
        message.groupJoinQuestionsWithVersion,
        writer.uint32(138).fork()
      ).ldelim();
    }
    if (message.groupMembersWithVersion !== undefined) {
      GroupMembersWithVersion.encode(
        message.groupMembersWithVersion,
        writer.uint32(146).fork()
      ).ldelim();
    }
    if (message.groupsWithVersion !== undefined) {
      GroupsWithVersion.encode(
        message.groupsWithVersion,
        writer.uint32(154).fork()
      ).ldelim();
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): TurmsNotification_Data {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseTurmsNotification_Data } as TurmsNotification_Data;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.ids = Int64Values.decode(reader, reader.uint32());
          break;
        case 2:
          message.idsWithVersion = Int64ValuesWithVersion.decode(
            reader,
            reader.uint32()
          );
          break;
        case 3:
          message.url = reader.string();
          break;
        case 4:
          message.conversations = Conversations.decode(reader, reader.uint32());
          break;
        case 5:
          message.messages = Messages.decode(reader, reader.uint32());
          break;
        case 6:
          message.messagesWithTotalList = MessagesWithTotalList.decode(
            reader,
            reader.uint32()
          );
          break;
        case 7:
          message.userSession = UserSession.decode(reader, reader.uint32());
          break;
        case 8:
          message.usersInfosWithVersion = UsersInfosWithVersion.decode(
            reader,
            reader.uint32()
          );
          break;
        case 9:
          message.usersOnlineStatuses = UsersOnlineStatuses.decode(
            reader,
            reader.uint32()
          );
          break;
        case 10:
          message.userFriendRequestsWithVersion =
            UserFriendRequestsWithVersion.decode(reader, reader.uint32());
          break;
        case 11:
          message.userRelationshipGroupsWithVersion =
            UserRelationshipGroupsWithVersion.decode(reader, reader.uint32());
          break;
        case 12:
          message.userRelationshipsWithVersion =
            UserRelationshipsWithVersion.decode(reader, reader.uint32());
          break;
        case 13:
          message.nearbyUsers = NearbyUsers.decode(reader, reader.uint32());
          break;
        case 14:
          message.groupInvitationsWithVersion =
            GroupInvitationsWithVersion.decode(reader, reader.uint32());
          break;
        case 15:
          message.groupJoinQuestionAnswerResult =
            GroupJoinQuestionsAnswerResult.decode(reader, reader.uint32());
          break;
        case 16:
          message.groupJoinRequestsWithVersion =
            GroupJoinRequestsWithVersion.decode(reader, reader.uint32());
          break;
        case 17:
          message.groupJoinQuestionsWithVersion =
            GroupJoinQuestionsWithVersion.decode(reader, reader.uint32());
          break;
        case 18:
          message.groupMembersWithVersion = GroupMembersWithVersion.decode(
            reader,
            reader.uint32()
          );
          break;
        case 19:
          message.groupsWithVersion = GroupsWithVersion.decode(
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
