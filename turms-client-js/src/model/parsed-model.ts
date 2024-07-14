import { DeviceType } from './proto/constant/device_type';
import { GroupMemberRole } from './proto/constant/group_member_role';
import { ProfileAccessStrategy } from './proto/constant/profile_access_strategy';
import { RequestStatus } from './proto/constant/request_status';
import { UserLocation } from './proto/model/user/user_location';
import { UserStatus } from './proto/constant/user_status';
import { Value } from './proto/model/common/value';

export declare namespace ParsedModel {

    interface Data {
        data: Uint8Array;
    }

    interface LongsWithVersion {
        longs?: string[];
        lastUpdatedDate?: Date;
    }

    interface UserRelationshipGroupsWithVersion {
        userRelationshipGroups?: UserRelationshipGroup;
        lastUpdatedDate?: Date;
    }

    interface UserRelationshipGroup {
        index?: number;
        name?: string;
        customAttributes: Value[];
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
        // TODO: missing
        // customAttributes: Value[];
    }

    interface UserInfosWithVersion {
        userInfos?: UserInfo[];
        lastUpdatedDate?: Date;
    }

    interface NearbyUser {
        userId: string;
        deviceType?: DeviceType;
        info?: UserInfo;
        distance?: number;
        location?: UserLocation;
        customAttributes: Value[];
    }

    interface UserSettings {
        settings: { [key: string]: Value };
        lastUpdatedDate?: Date;
        customAttributes: Value[];
    }

    interface PrivateConversation {
        ownerId: string;
        targetId: string;
        readDate: Date;
        customAttributes: Value[];
    }

    interface GroupConversation {
        groupId: string;
        memberIdToReadDate: { [k: string]: Date };
        customAttributes: Value[];
    }

    interface ConversationSettings {
        userId?: string | undefined;
        groupId?: string | undefined;
        settings: { [key: string]: Value };
        lastUpdatedDate?: Date;
        customAttributes: Value[];
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
        preMessageId?: string;
        customAttributes: Value[];
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
        customAttributes: Value[];
    }

    interface UserRelationshipsWithVersion {
        userRelationships?: UserRelationship;
        lastUpdatedDate?: Date;
    }

    interface UserInfo {
        id?: string;
        name?: string;
        intro?: string;
        profilePicture?: string;
        profileAccessStrategy?: ProfileAccessStrategy;
        registrationDate?: Date;
        lastUpdatedDate?: string;
        active?: boolean;
        customAttributes: Value[];
    }

    interface UserOnlineStatus {
        userId?: string;
        userStatus?: UserStatus;
        deviceTypes?: DeviceType[];
        customAttributes: Value[];
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
        customAttributes: Value[];
    }

    interface GroupMember {
        groupId?: string;
        userId?: string;
        name?: string;
        role?: GroupMemberRole;
        joinDate?: Date;
        muteEndDate?: Date;
        userStatus?: UserStatus;
        // TODO: rename
        usingDeviceTypes?: DeviceType[];
        customAttributes: Value[];
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
        customAttributes: Value[];
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
        customAttributes: Value[];
    }

    interface GroupJoinRequestsWithVersion {
        groupJoinRequests?: GroupJoinRequest[];
        lastUpdatedDate?: Date;
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
        lastUpdatedDate?: Date;
        muteEndDate?: Date;
        active?: boolean;
        customAttributes: Value[];
    }

    interface StorageResourceInfo {
        idNum?: string;
        idStr?: string;
        name?: string;
        mediaType?: string;
        uploaderId: string;
        creationDate: Data;
        customAttributes: Value[];
    }

    interface Meeting {
        id: string;
        userId?: string | undefined;
        groupId?: string | undefined;
        creatorId: string;
        accessToken?: string | undefined;
        name?: string | undefined;
        intro?: string | undefined;
        password?: string | undefined;
        startDate: Date;
        endDate?: Date;
        cancelDate?: Date;
        customAttributes: Value[];
    }
}