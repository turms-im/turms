import TurmsClient from "../turms-client";
import { im } from "../model/proto-bundle";
import { ParsedModel } from "../model/parsed-model";
import UserStatus = im.turms.proto.UserStatus;
import ProfileAccessStrategy = im.turms.proto.ProfileAccessStrategy;
import ResponseAction = im.turms.proto.ResponseAction;
import DeviceType = im.turms.proto.DeviceType;
import UserSessionId = im.turms.proto.UserSessionId;
export default class UserService {
    private _turmsClient;
    private _userId?;
    private _password?;
    private _location;
    private _userOnlineStatus;
    private _deviceType;
    constructor(turmsClient: TurmsClient);
    get password(): string;
    get userId(): string;
    static getUserLocationFromBrowser(): Promise<Position>;
    login(userId: string, password: string, location?: string | Position, userOnlineStatus?: UserStatus, deviceType?: DeviceType): Promise<void>;
    relogin(): Promise<void>;
    logout(): Promise<void>;
    updateUserOnlineStatus(onlineStatus: string | UserStatus): Promise<void>;
    disconnectOnlineDevices(deviceTypes: string[] | DeviceType[]): Promise<void>;
    updatePassword(password: string): Promise<void>;
    updateProfile(name?: string, intro?: string, profileAccessStrategy?: string | ProfileAccessStrategy): Promise<void>;
    queryUserGroupInvitations(lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion>;
    queryUserProfile(userId: string, lastUpdatedDate?: Date): Promise<ParsedModel.UserInfoWithVersion>;
    queryUserIdsNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<string[]>;
    queryUserSessionIdsNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<UserSessionId[]>;
    queryUsersInfosNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<ParsedModel.UserInfo[]>;
    queryUsersOnlineStatusRequest(usersIds: string[]): Promise<ParsedModel.UserStatusDetail[]>;
    queryRelationships(relatedUsersIds?: string[], isBlocked?: boolean, groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion>;
    queryRelatedUsersIds(isBlocked?: boolean, groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion>;
    queryFriends(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion>;
    queryBlacklistedUsers(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion>;
    createRelationship(userId: string, isBlocked: boolean, groupIndex?: number): Promise<void>;
    createFriendRelationship(userId: string, groupIndex?: number): Promise<void>;
    createBlacklistedUserRelationship(userId: string, groupIndex?: number): Promise<void>;
    deleteRelationship(relatedUserId: string, deleteGroupIndex?: string, targetGroupIndex?: string): Promise<void>;
    updateRelationship(relatedUserId: string, isBlocked?: boolean, groupIndex?: number): Promise<void>;
    sendFriendRequest(recipientId: string, content: string): Promise<string>;
    replyFriendRequest(requestId: string, responseAction: string | ResponseAction, reason?: string): Promise<void>;
    queryFriendRequests(lastUpdatedDate?: Date): Promise<ParsedModel.UserFriendRequestsWithVersion>;
    createRelationshipGroup(name: string): Promise<string>;
    deleteRelationshipGroups(groupIndex: number, targetGroupIndex?: number): Promise<void>;
    updateRelationshipGroup(groupIndex: number, newName: string): Promise<void>;
    queryRelationshipGroups(lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipGroupsWithVersion>;
    moveRelatedUserToGroup(relatedUserId: string, groupIndex: number): Promise<void>;
    updateLocation(latitude: number, longitude: number, name?: string, address?: string): Promise<void>;
}
