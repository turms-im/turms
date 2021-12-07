import {RequestStatus} from './proto/constant/request_status';
import {ProfileAccessStrategy} from './proto/constant/profile_access_strategy';
import {UserStatus} from './proto/constant/user_status';
import {DeviceType} from './proto/constant/device_type';
import {GroupMemberRole} from './proto/constant/group_member_role';
import {UserInfo} from "./proto/model/user/user_info";
import {UserLocation} from "./proto/model/user/user_location";

export declare namespace ParsedModel {

    interface Data {
        data: Uint8Array;
    }

    interface IdsWithVersion {
        ids?: string[];
        lastUpdatedDate?: Date;
    }

    interface UserRelationshipGroupsWithVersion {
        userRelationshipGroups?: UserRelationshipGroup;
        lastUpdatedDate?: Date;
    }

    interface UserRelationshipGroup {
        index?: number;
        name?: string;
    }

    interface UserFriendRequestsWithVersion {
        userFriendRequests?: UserFriendRequest[];
        lastUpdatedDate?: Date;
    }

    interface UserFriendRequest {
        id?: string;
        creationDate?: Date;
        content?: string;
        requestStatus?: RequestStatus;
        reason?: string;
        expirationDate?: Date;
        requesterId?: string;
        recipientId?: string;
    }

    interface UsersInfosWithVersion {
        userInfos?: UserInfo[];
        lastUpdatedDate?: Date;
    }

    interface NearbyUser {
        userId: string;
        deviceType?: DeviceType;
        info?: UserInfo;
        distance?: number;
        location?: UserLocation;
    }

    interface PrivateConversation {
        ownerId: string;
        targetId: string;
        readDate: Date;
    }

    interface GroupConversation {
        groupId: string;
        memberIdAndReadDate: { [k: string]: Date };
    }

    interface Message {
        id?: string;
        isSystemMessage?: boolean;
        deliveryDate?: Date;
        text?: string;
        senderId?: string;
        groupId?: string;
        recipientId?: string;
        records?: Uint8Array[];
        sequenceId?: number;
    }

    interface MessagesWithTotal {
        total?: number;
        isGroupMessage?: boolean;
        fromId?: string;
        messages?: Message;
    }

    interface UserRelationship {
        ownerId?: string;
        relatedUserId?: string;
        blockDate?: Date;
        groupIndex?: number;
        establishmentDate?: Date;
    }

    interface UserRelationshipsWithVersion {
        userRelationships?: UserRelationship;
        lastUpdatedDate?: Date;
    }

    interface UserInfoWithVersion {
        userInfo?: UserInfo;
        lastUpdatedDate?: Date;
    }

    interface UserInfo {
        id?: string;
        name?: string;
        intro?: string;
        registrationDate?: Date;
        active?: boolean;
        profileAccessStrategy?: ProfileAccessStrategy;
    }

    interface UserStatusDetail {
        userId?: string;
        userStatus?: UserStatus;
        usingDeviceTypes?: DeviceType[];
    }

    interface GroupInvitationsWithVersion {
        groupInvitations?: GroupInvitation[];
        lastUpdatedDate?: Date;
    }

    interface GroupInvitation {
        id?: string;
        creationDate?: Date;
        content?: string;
        status?: RequestStatus;
        expirationDate?: Date;
        groupId?: string;
        inviterId?: string;
        inviteeId?: string;
    }

    interface GroupMember {
        groupId?: string;
        userId?: string;
        name?: string;
        role?: GroupMemberRole;
        joinDate?: Date;
        muteEndDate?: Date;
        userStatus?: UserStatus;
        usingDeviceTypes?: DeviceType[];
    }

    interface GroupMembersWithVersion {
        groupMembers?: GroupMember[];
        lastUpdatedDate?: Date;
    }

    interface GroupJoinQuestion {
        id?: string;
        groupId?: string;
        question?: string;
        answers?: string;
        score?: number;
    }

    interface GroupJoinQuestionsWithVersion {
        groupJoinQuestions?: GroupJoinQuestion[];
        lastUpdatedDate?: Date;
    }

    interface GroupJoinRequest {
        id?: string;
        creationDate?: Date;
        content?: string;
        status?: RequestStatus;
        expirationDate?: Date;
        groupId?: string;
        requesterId?: string;
        responderId?: string;
    }

    interface GroupJoinRequestsWithVersion {
        groupJoinRequests?: GroupJoinRequest[];
        lastUpdatedDate?: Date;
    }

    interface GroupWithVersion {
        lastUpdatedDate?: Date;
        group?: Group;
    }

    interface GroupsWithVersion {
        lastUpdatedDate?: Date;
        groups?: Group[];
    }

    interface Group {
        id?: string;
        typeId?: string;
        creatorId?: string;
        ownerId?: string;
        name?: string;
        intro?: string;
        announcement?: string;
        creationDate?: Date;
        muteEndDate?: Date;
        active?: boolean;
    }
}