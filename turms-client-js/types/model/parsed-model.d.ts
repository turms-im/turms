import { im } from "./proto-bundle";
export declare namespace ParsedModel {
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
    interface Message {
        id?: string;
        deliveryDate?: Date;
        deletionDate?: Date;
        text?: string;
        senderId?: string;
        groupId?: string;
        recipientId?: string;
        records?: Uint8Array[];
    }
    interface MessageStatus {
        messageId?: string;
        toUserId?: string;
        deliveryStatus?: MessageDeliveryStatus;
        receptionDate?: Date;
        readDate?: Date;
        recallDate?: Date;
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
        id?: string;
        name?: string;
        intro?: string;
        registrationDate?: Date;
        deletionDate?: Date;
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
        deletionDate?: Date;
        muteEndDate?: Date;
        active?: boolean;
    }
}
