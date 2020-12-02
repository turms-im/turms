import TurmsClient from "../turms-client";
import {im} from "../model/proto-bundle";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import SystemUtil from "../util/system-util";
import UserLocation from "../model/user-location";
import TurmsBusinessError from "../model/turms-business-error";
import UserStatus = im.turms.proto.UserStatus;
import ProfileAccessStrategy = im.turms.proto.ProfileAccessStrategy;
import ResponseAction = im.turms.proto.ResponseAction;
import DeviceType = im.turms.proto.DeviceType;
import UserSessionId = im.turms.proto.UserSessionId;

export default class UserService {
    private _turmsClient: TurmsClient;
    private _userId: string;
    private _password: string;
    private _deviceType: DeviceType;
    private _userOnlineStatus: UserStatus;
    private _location?: UserLocation;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    get password(): string {
        return this._password;
    }

    get userId(): string {
        return this._userId;
    }

    get location(): UserLocation {
        return this._location;
    }

    get userOnlineStatus(): UserStatus {
        return this._userOnlineStatus;
    }

    get deviceType(): DeviceType {
        return this._deviceType;
    }

    /**
     * Note: Because of user privacy policies, most of modern browsers request Geolocation APIs to run in HTTPS,
     * getUserLocation() cannot get the location of users in insecure sites.
     * FYI: https://stackoverflow.com/questions/37835805/http-sites-does-not-detect-the-location-in-chrome-issue
     * https://caniuse.com/#search=Geolocation
     */
    static getUserLocationFromBrowser(): Promise<GeolocationPosition> {
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
        deviceType?: string | DeviceType,
        userOnlineStatus = UserStatus.AVAILABLE,
        location?: GeolocationPosition | UserLocation): Promise<void> {
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsy('userId');
        }
        if (RequestUtil.isFalsy(password)) {
            return TurmsBusinessError.notFalsy('password');
        }
        this._userId = userId;
        this._password = password;
        if (typeof deviceType === 'string') {
            deviceType = DeviceType[deviceType] as DeviceType;
            if (RequestUtil.isFalsy(deviceType)) {
                return TurmsBusinessError.notFalsy("deviceType");
            }
            this._deviceType = deviceType;
        } else if (typeof deviceType === 'number') {
            if (deviceType >= 0 && deviceType <= DeviceType.UNKNOWN) {
                this._deviceType = deviceType;
            } else {
                return TurmsBusinessError.illegalParam('illegal DeviceType');
            }
        } else {
            this._deviceType = SystemUtil.getDeviceType();
        }
        this._userOnlineStatus = userOnlineStatus;
        const isPosition = location && location['coords'];
        if (isPosition) {
            const position = location as GeolocationPosition;
            this._location = new UserLocation(position.coords.longitude, position.coords.latitude);
        }
        return this._turmsClient.driver.connect(this._userId, this._password,
            this._deviceType, this._userOnlineStatus, this._location);
    }

    relogin(): Promise<void> {
        return this._turmsClient.driver.reconnect();
    }

    logout(): Promise<void> {
        return this._turmsClient.driver.disconnect();
    }

    updateUserOnlineStatus(onlineStatus: string | UserStatus): Promise<void> {
        if (RequestUtil.isFalsy(onlineStatus)) {
            return TurmsBusinessError.notFalsy('onlineStatus');
        }
        if (typeof onlineStatus === 'string') {
            onlineStatus = UserStatus[onlineStatus] as UserStatus;
            if (RequestUtil.isFalsy(onlineStatus)) {
                return TurmsBusinessError.notFalsy("onlineStatus");
            }
        }
        if (onlineStatus === UserStatus.OFFLINE) {
            return Promise.reject('onlineStatus cannot be OFFLINE');
        }
        return this._turmsClient.driver.send({
            updateUserOnlineStatusRequest: {
                userStatus: onlineStatus
            }
        }).then(() => null);
    }

    disconnectOnlineDevices(deviceTypes: string[] | DeviceType[]): Promise<void> {
        if (RequestUtil.isFalsy(deviceTypes)) {
            return TurmsBusinessError.notFalsy('deviceTypes', true);
        }
        try {
            // @ts-ignore
            deviceTypes = deviceTypes.map((type: string | DeviceType) => {
                if (typeof type === 'string') {
                    type = DeviceType[type] as DeviceType;
                    if (RequestUtil.isFalsy(type)) {
                        return TurmsBusinessError.notFalsy("deviceType");
                    }
                } else {
                    if (type >= 0 && type <= DeviceType.UNKNOWN) {
                        return type;
                    } else {
                        throw TurmsBusinessError.illegalParam('illegal DeviceType');
                    }
                }
            });
        } catch (e) {
            return e;
        }
        return this._turmsClient.driver.send({
            updateUserOnlineStatusRequest: {
                userStatus: UserStatus.OFFLINE,
                deviceTypes: deviceTypes as DeviceType[]
            }
        }).then(() => null);
    }

    updatePassword(password: string): Promise<void> {
        if (RequestUtil.isFalsy(password)) {
            return TurmsBusinessError.notFalsy('password');
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                password: RequestUtil.wrapValueIfNotNull(password)
            }
        }).then(() => null);
    }

    updateProfile(
        name?: string,
        intro?: string,
        profileAccessStrategy?: string | ProfileAccessStrategy): Promise<void> {
        if (RequestUtil.areAllFalsy(name, intro, profileAccessStrategy)) {
            return Promise.resolve();
        }
        if (typeof profileAccessStrategy === 'string') {
            profileAccessStrategy = ProfileAccessStrategy[profileAccessStrategy] as ProfileAccessStrategy;
            if (RequestUtil.isFalsy(profileAccessStrategy)) {
                return TurmsBusinessError.notFalsy("profileAccessStrategy");
            }
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                name: RequestUtil.wrapValueIfNotNull(name),
                intro: RequestUtil.wrapValueIfNotNull(intro),
                profileAccessStrategy: profileAccessStrategy
            }
        }).then(() => null);
    }

    queryUserProfile(userId: string, lastUpdatedDate?: Date): Promise<ParsedModel.UserInfoWithVersion | undefined> {
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsy('userId');
        }
        return this._turmsClient.driver.send({
            queryUserProfileRequest: {
                userId: userId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => {
            const userInfo = NotificationUtil.getAndTransform(n, 'usersInfosWithVersion.userInfos.0');
            if (userInfo) {
                return {
                    userInfo,
                    lastUpdatedDate: NotificationUtil.transformDate(n.data.usersInfosWithVersion.lastUpdatedDate)
                }
            }
        });
    }

    queryUserIdsNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<string[]> {
        if (RequestUtil.isFalsy(latitude)) {
            return TurmsBusinessError.notFalsy('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsy('longitude');
        }
        return this._turmsClient.driver.send({
            queryUserIdsNearbyRequest: {
                latitude: latitude,
                longitude: longitude,
                distance: RequestUtil.wrapValueIfNotNull(distance),
                maxNumber: RequestUtil.wrapValueIfNotNull(maxNumber)
            }
        }).then(n => NotificationUtil.getArr(n, 'ids.values'));
    }

    queryUserSessionIdsNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<UserSessionId[]> {
        if (RequestUtil.isFalsy(latitude)) {
            return TurmsBusinessError.notFalsy('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsy('longitude');
        }
        return this._turmsClient.driver.send({
            queryUserIdsNearbyRequest: {
                latitude: latitude,
                longitude: longitude,
                distance: RequestUtil.wrapValueIfNotNull(distance),
                maxNumber: RequestUtil.wrapValueIfNotNull(maxNumber)
            }
        }).then(n => NotificationUtil.getArr(n, 'userSessionIds.userSessionIds'));
    }

    queryUserInfosNearby(latitude: number, longitude: number, distance?: number, maxNumber?: number): Promise<ParsedModel.UserInfo[]> {
        if (RequestUtil.isFalsy(latitude)) {
            return TurmsBusinessError.notFalsy('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsy('longitude');
        }
        return this._turmsClient.driver.send({
            queryUserInfosNearbyRequest: {
                latitude: latitude,
                longitude: longitude,
                distance: RequestUtil.wrapValueIfNotNull(distance),
                maxNumber: RequestUtil.wrapValueIfNotNull(maxNumber)
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'usersInfosWithVersion.userInfos'));
    }

    queryUserOnlineStatusesRequest(userIds: string[]): Promise<ParsedModel.UserStatusDetail[]> {
        if (RequestUtil.isFalsy(userIds)) {
            return TurmsBusinessError.notFalsy('userIds', true);
        }
        return this._turmsClient.driver.send({
            queryUserOnlineStatusesRequest: {
                userIds
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'usersOnlineStatuses.userStatuses'));
    }

    // Relationship

    queryRelationships(
        relatedUserIds?: string[],
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryRelationshipsRequest: {
                userIds: relatedUserIds,
                blocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userRelationshipsWithVersion'));
    }

    queryRelatedUserIds(
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryRelatedUserIdsRequest: {
                blocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryFriends(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this.queryRelationships(undefined, false, groupIndex, lastUpdatedDate);
    }

    queryBlacklistedUsers(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this.queryRelationships(undefined, true, groupIndex, lastUpdatedDate);
    }

    createRelationship(userId: string, isBlocked: boolean, groupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsy('userId');
        }
        if (RequestUtil.isFalsy(isBlocked)) {
            return TurmsBusinessError.notFalsy('isBlocked');
        }
        return this._turmsClient.driver.send({
            createRelationshipRequest: {
                userId,
                blocked: isBlocked,
                groupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(() => null);
    }

    createFriendRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, false, groupIndex);
    }

    createBlacklistedUserRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, true, groupIndex);
    }

    deleteRelationship(relatedUserId: string, deleteGroupIndex?: string, targetGroupIndex?: string): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsy('relatedUserId');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipRequest: {
                userId: relatedUserId,
                groupIndex: RequestUtil.wrapValueIfNotNull(deleteGroupIndex),
                targetGroupIndex: RequestUtil.wrapValueIfNotNull(targetGroupIndex)
            }
        }).then(() => null);
    }

    updateRelationship(relatedUserId: string, isBlocked?: boolean, groupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsy('relatedUserId');
        }
        if (RequestUtil.areAllFalsy(isBlocked, groupIndex)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                blocked: RequestUtil.wrapValueIfNotNull(isBlocked),
                newGroupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(() => null);
    }

    sendFriendRequest(recipientId: string, content: string): Promise<string> {
        if (RequestUtil.isFalsy(recipientId)) {
            return TurmsBusinessError.notFalsy('recipientId');
        }
        if (RequestUtil.isFalsy(content)) {
            return TurmsBusinessError.notFalsy('content');
        }
        return this._turmsClient.driver.send({
            createFriendRequestRequest: {
                recipientId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    replyFriendRequest(requestId: string, responseAction: string | ResponseAction, reason?: string): Promise<void> {
        if (RequestUtil.isFalsy(requestId)) {
            return TurmsBusinessError.notFalsy('requestId');
        }
        if (RequestUtil.isFalsy(responseAction)) {
            return TurmsBusinessError.notFalsy('responseAction');
        }
        if (typeof responseAction === 'string') {
            responseAction = ResponseAction[responseAction] as ResponseAction;
            if (RequestUtil.isFalsy(responseAction)) {
                return TurmsBusinessError.notFalsy("reponseAction");
            }
        }
        return this._turmsClient.driver.send({
            updateFriendRequestRequest: {
                requestId: requestId,
                responseAction: responseAction,
                reason: RequestUtil.wrapValueIfNotNull(reason)
            }
        }).then(() => null);
    }

    queryFriendRequests(areSentByMe: boolean, lastUpdatedDate?: Date): Promise<ParsedModel.UserFriendRequestsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryFriendRequestsRequest: {
                areSentByMe: areSentByMe,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userFriendRequestsWithVersion'));
    }

    createRelationshipGroup(name: string): Promise<string> {
        if (RequestUtil.isFalsy(name)) {
            return TurmsBusinessError.notFalsy('name');
        }
        return this._turmsClient.driver.send({
            createRelationshipGroupRequest: {
                name
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteRelationshipGroups(groupIndex: number, targetGroupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsy('groupIndex');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipGroupRequest: {
                groupIndex,
                targetGroupIndex: RequestUtil.wrapValueIfNotNull(targetGroupIndex)
            }
        }).then(() => null);
    }

    updateRelationshipGroup(groupIndex: number, newName: string): Promise<void> {
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsy('groupIndex');
        }
        if (RequestUtil.isFalsy(newName)) {
            return TurmsBusinessError.notFalsy('newName');
        }
        return this._turmsClient.driver.send({
            updateRelationshipGroupRequest: {
                groupIndex,
                newName
            }
        }).then(() => null);
    }

    queryRelationshipGroups(lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipGroupsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryRelationshipGroupsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'userRelationshipGroupsWithVersion'));
    }

    moveRelatedUserToGroup(relatedUserId: string, groupIndex: number): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsy('relatedUserId');
        }
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsy('groupIndex');
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                newGroupIndex: RequestUtil.wrapValueIfNotNull(groupIndex)
            }
        }).then(() => null);
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    updateLocation(latitude: number, longitude: number, name?: string, address?: string): Promise<void> {
        if (RequestUtil.isFalsy(latitude)) {
            return TurmsBusinessError.notFalsy('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsy('longitude');
        }
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        return this._turmsClient.driver.send({
            updateUserLocationRequest: {
                latitude,
                longitude,
                name: RequestUtil.wrapValueIfNotNull(name),
                address: RequestUtil.wrapValueIfNotNull(address)
            }
        }).then(() => null);
    }
}
