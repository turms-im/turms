import TurmsClient from "../turms-client";
import ConstantTransformer from "../util/constant-transformer";
import {im} from "../model/proto-bundle";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import UserStatus = im.turms.proto.UserStatus;
import ProfileAccessStrategy = im.turms.proto.ProfileAccessStrategy;
import ResponseAction = im.turms.proto.ResponseAction;
import DeviceType = im.turms.proto.DeviceType;

export default class UserService {
    private _turmsClient: TurmsClient;
    private _userId?: string;
    private _password?: string;
    private _location: string;
    private _userOnlineStatus: im.turms.proto.UserStatus;
    private _deviceType: im.turms.proto.DeviceType;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    get password(): string {
        return this._password;
    }

    get userId(): string {
        return this._userId;
    }

    /**
     * Note: Because of user privacy, most of modern browsers request Geolocation APIs to run in HTTPS,
     * getUserLocation() cannot get the location of user in insecure sites.
     * FYI: https://stackoverflow.com/questions/37835805/http-sites-does-not-detect-the-location-in-chrome-issue
     * https://caniuse.com/#search=Geolocation
     */
    static getUserLocationFromBrowser(): Promise<Position> {
        return new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(position => {
                resolve(position);
            }, positionError => {
                reject(positionError);
            });
        });
    }

    /**
     * Note: Make sure to run turms-client in the http(s) protocol rather than the file protocol,
     * or the browsers usually cannot pass the cookies which carry the userId and password to servers.
     * @deviceType: When DeviceType.UNKNOWN, the turms server will try to detect the user's device automatically
     */
    login(
        userId: string,
        password: string,
        location?: string | Position,
        userOnlineStatus = UserStatus.AVAILABLE,
        deviceType = DeviceType.UNKNOWN): Promise<void> {
        RequestUtil.throwIfAnyFalsy(userId, password);
        this._userId = userId;
        this._password = password;
        this._userOnlineStatus = userOnlineStatus;
        this._deviceType = deviceType;
        if (location) {
            if (typeof location !== 'string') {
                location = `${location.coords.latitude}:${location.coords.longitude}`;
            }
            this._location = location;
        }
        return this._turmsClient.driver.connect(userId, password, location as string, userOnlineStatus, deviceType);
    }

    relogin(): Promise<void> {
        if (!this._userId || !this._password) {
            return Promise.reject();
        } else {
            return this.login(this._userId, this._password, this._location,
                this._userOnlineStatus, this._deviceType);
        }
    }

    logout(): Promise<void> {
        return this._turmsClient.driver.disconnect();
    }

    updateUserOnlineStatus(onlineStatus: string | UserStatus): Promise<void> {
        RequestUtil.throwIfAnyFalsy(onlineStatus);
        if (typeof onlineStatus === 'string') {
            onlineStatus = ConstantTransformer.string2UserOnlineStatus(onlineStatus);
        }
        if (onlineStatus === UserStatus.OFFLINE) {
            throw new Error('illegal params');
        }
        return this._turmsClient.driver.send({
            updateUserOnlineStatusRequest: {
                userStatus: onlineStatus
            }
        }).then(_ => {
        });
    }

    updatePassword(password: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(password);
        return this._turmsClient.driver.send({
            updateUserRequest: {
                password: RequestUtil.wrapValueIfNotNull(password)
            }
        }).then(_ => {
        });
    }

    updateProfile(
        name?: string,
        intro?: string,
        profilePictureUrl?: string,
        profileAccessStrategy?: string | ProfileAccessStrategy): Promise<void> {
        if (RequestUtil.areAllFalsy(name, intro, profilePictureUrl, profileAccessStrategy)) {
            return Promise.resolve();
        }
        if (typeof profileAccessStrategy === 'string') {
            profileAccessStrategy = ConstantTransformer.string2ProfileAccessStrategy(profileAccessStrategy);
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                name: RequestUtil.wrapValueIfNotNull(name),
                intro: RequestUtil.wrapValueIfNotNull(intro),
                profilePictureUrl: RequestUtil.wrapValueIfNotNull(profilePictureUrl),
                profileAccessStrategy: profileAccessStrategy
            }
        }).then(_ => {
        });
    }

    queryUserGroupInvitations(lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion> {
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryUserGroupInvitationsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupInvitationsWithVersion'));
    }

    queryUserProfile(userId: string, lastUpdatedDate?: Date): Promise<ParsedModel.UserInfoWithVersion> {
        RequestUtil.throwIfAnyFalsy(userId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryUserProfileRequest: {
                userId: userId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => {
            return {
                userInfo: NotificationUtil.getAndTransform(n, 'usersInfosWithVersion.userInfos.0'),
                lastUpdatedDate: NotificationUtil.transformDate(n.data.usersInfosWithVersion.lastUpdatedDate)
            }
        });
    }

    queryUsersIdsNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<string[]> {
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        return this._turmsClient.driver.send({
            queryUsersIdsNearbyRequest: {
                latitude: latitude,
                longitude: longitude,
                distance: RequestUtil.wrapValueIfNotNull(distance),
                maxNumber: RequestUtil.wrapValueIfNotNull(maxNumber)
            }
        }).then(n => NotificationUtil.getArr(n, 'ids.values'));
    }

    queryUsersInfosNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<ParsedModel.UserInfo[]> {
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryUsersInfosNearbyRequest: {
                latitude: latitude,
                longitude: longitude,
                distance: RequestUtil.wrapValueIfNotNull(distance),
                maxNumber: RequestUtil.wrapValueIfNotNull(maxNumber)
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'usersInfosWithVersion.userInfos'));
    }

    queryUsersOnlineStatusRequest(usersIds: string[]): Promise<ParsedModel.UserStatusDetail[]> {
        RequestUtil.throwIfAnyFalsy(usersIds);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryUsersOnlineStatusRequest: {
                usersIds
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'usersOnlineStatuses.userStatuses'));
    }

    // Relationship

    queryRelationships(
        relatedUsersIds?: string[],
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion> {
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryRelationshipsRequest: {
                relatedUsersIds,
                isBlocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userRelationshipsWithVersion'));
    }

    queryRelatedUsersIds(
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion> {
        return this._turmsClient.driver.send({
            queryRelatedUsersIdsRequest: {
                isBlocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryFriends(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion> {
        return this.queryRelationships(undefined, false, groupIndex, lastUpdatedDate);
    }

    queryBlacklistedUsers(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion> {
        return this.queryRelationships(undefined, true, groupIndex, lastUpdatedDate);
    }

    createRelationship(userId: string, isBlocked: boolean, groupIndex?: number): Promise<void> {
        RequestUtil.throwIfAnyFalsy(userId, isBlocked);
        return this._turmsClient.driver.send({
            createRelationshipRequest: {
                userId,
                isBlocked: isBlocked,
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(_ => {
        });
    }

    createFriendRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, false, groupIndex);
    }

    createBlacklistedUserRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, true, groupIndex);
    }

    deleteRelationship(relatedUserId: string, deleteGroupIndex?: string, targetGroupIndex?: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(relatedUserId);
        return this._turmsClient.driver.send({
            deleteRelationshipRequest: {
                relatedUserId,
                groupIndex: RequestUtil.wrapValueIfNotNull(deleteGroupIndex),
                targetGroupIndex: RequestUtil.wrapValueIfNotNull(targetGroupIndex)
            }
        }).then(_ => {
        });
    }

    updateRelationship(relatedUserId: string, isBlocked?: boolean, groupIndex?: number): Promise<void> {
        RequestUtil.throwIfAnyFalsy(relatedUserId);
        if (RequestUtil.areAllFalsy(isBlocked, groupIndex)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                relatedUserId,
                blocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                newGroupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(_ => {
        });
    }

    sendFriendRequest(recipientId: string, content: string): Promise<string> {
        RequestUtil.throwIfAnyFalsy(recipientId, content);
        return this._turmsClient.driver.send({
            createFriendRequestRequest: {
                recipientId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    replyFriendRequest(requestId: string, responseAction: string | ResponseAction, reason?: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(requestId, responseAction);
        if (typeof responseAction === 'string') {
            responseAction = ConstantTransformer.string2ResponseAction(responseAction);
        }
        return this._turmsClient.driver.send({
            updateFriendRequestRequest: {
                requestId: requestId,
                responseAction: responseAction,
                reason: RequestUtil.wrapValueIfNotNull(reason)
            }
        }).then(_ => {
        });
    }

    queryFriendRequests(lastUpdatedDate?: Date): Promise<ParsedModel.UserFriendRequestsWithVersion> {
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryFriendRequestsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userFriendRequestsWithVersion'));
    }

    createRelationshipGroup(name: string): Promise<string> {
        RequestUtil.throwIfAnyFalsy(name);
        return this._turmsClient.driver.send({
            createRelationshipGroupRequest: {
                name
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    deleteRelationshipGroups(groupIndex: number, targetGroupIndex?: number): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupIndex);
        return this._turmsClient.driver.send({
            deleteRelationshipGroupRequest: {
                groupIndex,
                targetGroupIndex: RequestUtil.wrapValueIfNotNull(targetGroupIndex)
            }
        }).then(_ => {
        });
    }

    updateRelationshipGroup(groupIndex: number, newName: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupIndex, newName);
        return this._turmsClient.driver.send({
            updateRelationshipGroupRequest: {
                groupIndex,
                newName
            }
        }).then(_ => {
        });
    }

    queryRelationshipGroups(lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipGroupsWithVersion> {
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryRelationshipGroupsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userRelationshipGroupsWithVersion'));
    }

    moveRelatedUserToGroup(relatedUserId: string, groupIndex: number): Promise<void> {
        RequestUtil.throwIfAnyFalsy(relatedUserId, groupIndex);
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                relatedUserId,
                newGroupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(_ => {
        });
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    updateLocation(latitude: number, longitude: number, name?: string, address?: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        return this._turmsClient.driver.send({
            updateUserLocationRequest: {
                latitude,
                longitude,
                name: RequestUtil.wrapValueIfNotNull(name),
                address: RequestUtil.wrapValueIfNotNull(address)
            }
        }).then(_ => {
        });
    }
}
