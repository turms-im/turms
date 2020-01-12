import {im} from "./proto-bundle";

export declare namespace ParsedModel {
    import ChatType = im.turms.proto.ChatType;
    import MessageDeliveryStatus = im.turms.proto.MessageDeliveryStatus;
    import ProfileAccessStrategy = im.turms.proto.ProfileAccessStrategy;
    import RequestStatus = im.turms.proto.RequestStatus;
    import GroupMemberRole = im.turms.proto.GroupMemberRole;
    import UserStatus = im.turms.proto.UserStatus;
    import DeviceType = im.turms.proto.DeviceType;

    interface Data {
        data: Uint8Array;
    }

    interface IdsWithVersion {
        ids?: number[];
        lastUpdatedDate?: Date;
    }

    interface UserRelationshipGroupsWithVersion {
        userRelationshipGroups?: UserRelationshipGroup;
        lastUpdatedDate?: number;
    }

    interface UserRelationshipGroup {
        index?: number;
        name?: string;
    }

    interface UserFriendRequestsWithVersion {
        userFriendRequests?: UserFriendRequest[];
        lastUpdatedDate?: number;
    }

    interface UserFriendRequest {
        id?: number;
        creationDate?: number;
        content?: string;
        requestStatus?: RequestStatus;
        reason?: string;
        expirationDate?: number;
        requesterId?: number;
        recipientId?: number;
    }

    interface UsersInfosWithVersion {
        userInfos?: UserInfo[];
        lastUpdatedDate?: Date;
    }

    interface Message {
        id?: number;
        deliveryDate?: Date;
        deletionDate?: Date;
        text?: string;
        senderId?: number;
        groupId?: number;
        recipientId?: number;
        records?: Uint8Array[];
    }

    interface MessageStatus {
        messageId?: number;
        toUserId?: number;
        deliveryStatus?: MessageDeliveryStatus;
        receptionDate?: Date;
        readDate?: Date;
        recallDate?: Date;
    }

    interface MessagesWithTotal {
        total?: number;
        chatType?: ChatType;
        fromId?: number;
        messages?: Message;
    }

    interface UserRelationship {
        ownerId?: number;
        relatedUserId?: number;
        isBlocked?: boolean;
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
        id?: number;
        name?: string;
        intro?: string;
        profilePictureUrl?: string;
        registrationDate?: Date;
        deletionDate?: Date;
        active?: boolean;
        profileAccessStrategy?: ProfileAccessStrategy;
    }

    interface UserStatusDetail {
        userId?: number;
        userStatus?: UserStatus;
        usingDeviceTypes?: DeviceType[];
    }

    interface GroupInvitationsWithVersion {
        groupInvitations?: GroupInvitation[];
        lastUpdatedDate?: Date;
    }

    interface GroupInvitation {
        id?: number;
        creationDate?: Date;
        content?: string;
        status?: RequestStatus;
        expirationDate?: Date;
        groupId?: number;
        inviterId?: number;
        inviteeId?: number;
    }

    interface GroupMember {
        groupId?: number;
        userId?: number;
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
        id?: number;
        groupId?: number;
        question?: string;
        answers?: string;
        score?: number;
    }

    interface GroupJoinQuestionsWithVersion {
        groupJoinQuestions?: GroupJoinQuestion[];
        lastUpdatedDate?: Date;
    }

    interface GroupJoinRequest {
        id?: number;
        creationDate?: Date;
        content?: string;
        status?: RequestStatus;
        expirationDate?: Date;
        groupId?: number;
        requesterId?: number;
        responderId?: number;
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
        id?: number;
        typeId?: number;
        creatorId?: number;
        ownerId?: number;
        name?: string;
        intro?: string;
        announcement?: string;
        profilePictureUrl?: string;
        creationDate?: Date;
        deletionDate?: Date;
        muteEndDate?: Date;
        active?: boolean;
    }
}