import TurmsClient from '../turms-client';
import RequestUtil from '../util/request-util';
import {ParsedModel} from '../model/parsed-model';
import NotificationUtil from '../util/notification-util';
import SystemUtil from '../util/system-util';
import UserLocation from '../model/user-location';
import TurmsBusinessError from '../model/turms-business-error';
import TurmsStatusCode from '../model/turms-status-code';
import StateStore from '../driver/state-store';
import {SessionCloseInfo} from '../model/session-close-info';
import TurmsCloseStatus from '../model/turms-close-status';
import {UserStatus} from '../model/proto/constant/user_status';
import {DeviceType} from '../model/proto/constant/device_type';
import {ProfileAccessStrategy} from '../model/proto/constant/profile_access_strategy';
import {ResponseAction} from '../model/proto/constant/response_action';

export interface UserInfo {
    userId?: string;
    password?: string;
    deviceType?: DeviceType;
    onlineStatus?: UserStatus;
    location?: UserLocation;
}

export interface LoginOptions {
    userId: string;
    password?: string;
    deviceType?: DeviceType;
    onlineStatus?: UserStatus;
    location?: UserLocation;
    storePassword?: boolean;
}

export default class UserService {
    private _turmsClient: TurmsClient;
    private _stateStore: StateStore;
    private _userInfo: UserInfo = {};
    private _storePassword = false;

    private _onOnlineListeners: (() => void)[] = [];
    private _onOfflineListeners: ((sessionCloseInfo?: SessionCloseInfo) => void)[] = [];

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._stateStore = turmsClient.driver.stateStore();
        turmsClient.driver.addOnDisconnectedListener(() => this._changeToOffline());
        turmsClient.driver.addNotificationListener(notification => {
            if (notification.closeStatus && this.isLoggedIn) {
                this._changeToOffline({
                    closeStatus: notification.closeStatus,
                    businessStatus: notification.code,
                    reason: notification.reason
                });
            }
        });
    }

    get userInfo(): UserInfo {
        return JSON.parse(JSON.stringify(this._userInfo));
    }

    get isLoggedIn(): boolean {
        return this._userInfo.onlineStatus >= 0 && this._userInfo.onlineStatus !== UserStatus.OFFLINE;
    }

    addOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners.push(listener);
    }

    addOnOfflineListener(listener: (sessionCloseInfo?: SessionCloseInfo) => void): void {
        this._onOfflineListeners.push(listener);
    }

    removeOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners = this._onOnlineListeners.filter(cur => cur !== listener);
    }

    removeOnOfflineListener(listener: () => void): void {
        this._onOfflineListeners = this._onOfflineListeners.filter(cur => cur !== listener);
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
            }, e => {
                reject(e);
            });
        });
    }

    login(
        userId: string,
        password?: string,
        deviceType?: string | DeviceType,
        onlineStatus?: string | UserStatus,
        location?: GeolocationPosition | UserLocation,
        storePassword?: boolean): Promise<void>

    login(options: LoginOptions): Promise<void>

    login(
        userIdOrOptions: string | LoginOptions,
        password?: string,
        deviceType?: string | DeviceType,
        onlineStatus?: string | UserStatus,
        location?: GeolocationPosition | UserLocation,
        storePassword = false): Promise<void> {
        const userId = userIdOrOptions['userId'] || userIdOrOptions;
        if (typeof userIdOrOptions === 'object') {
            password = userIdOrOptions.password;
            deviceType = userIdOrOptions.deviceType;
            onlineStatus = userIdOrOptions.onlineStatus;
            location = userIdOrOptions.location;
            storePassword = userIdOrOptions.storePassword;
        }
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        const userInfo: UserInfo = {};
        userInfo.userId = userId;
        userInfo.password = storePassword ? password : null;
        try {
            userInfo.deviceType = UserService._parseDeviceType(deviceType);
        } catch (e) {
            return Promise.reject(e);
        }
        try {
            userInfo.onlineStatus = UserService._parseUserStatus(onlineStatus) || UserStatus.AVAILABLE;
        } catch (e) {
            return Promise.reject(e);
        }
        const isPosition = location && location['coords'];
        if (isPosition) {
            const position = location as GeolocationPosition;
            userInfo.location = new UserLocation(position.coords.longitude, position.coords.latitude);
        }
        this._storePassword = storePassword
        return new Promise((resolve, reject) => {
            const connect = this._turmsClient.driver.isConnected
                ? Promise.resolve()
                : this._turmsClient.driver.connect();
            connect
                .then(() => {
                    return this._turmsClient.driver.send({
                        createSessionRequest: {
                            userId,
                            password,
                            deviceType: userInfo.deviceType,
                            userStatus: userInfo.onlineStatus,
                            location: userInfo.location
                        }
                    }).then(() => {
                        this._changeToOnline();
                        this._userInfo = userInfo;
                        resolve();
                    }).catch(e => reject(e));
                })
                .catch(e => reject(e));
        });
    }

    logout(disconnect = true): Promise<void> {
        let promise: Promise<any>;
        if (disconnect) {
            promise = this._turmsClient.driver.disconnect();
        } else {
            promise = this._turmsClient.driver.send({
                deleteSessionRequest: {}
            });
        }
        return promise.then(() => {
            this._changeToOffline({
                closeStatus: TurmsCloseStatus.DISCONNECTED_BY_CLIENT
            });
        });
    }

    updateOnlineStatus(onlineStatus: string | UserStatus): Promise<void> {
        if (RequestUtil.isFalsy(onlineStatus)) {
            return TurmsBusinessError.notFalsyPromise('onlineStatus');
        }
        try {
            onlineStatus = UserService._parseUserStatus(onlineStatus);
        } catch (e) {
            return Promise.reject(e);
        }
        return this._turmsClient.driver.send({
            updateUserOnlineStatusRequest: {
                deviceTypes: [],
                userStatus: onlineStatus
            }
        }).then(() => {
            this._userInfo.onlineStatus = onlineStatus as UserStatus;
        });
    }

    disconnectOnlineDevices(deviceTypes: string[] | DeviceType[]): Promise<void> {
        if (RequestUtil.isFalsy(deviceTypes)) {
            return TurmsBusinessError.notFalsyPromise('deviceTypes', true);
        }
        try {
            deviceTypes = deviceTypes.map((type: string | DeviceType) => UserService._parseDeviceType(type));
        } catch (e) {
            return Promise.reject(e);
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
            return TurmsBusinessError.notFalsyPromise('password');
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                password
            }
        }).then(() => {
            if (this._storePassword) {
                this._userInfo.password = password;
            }
        });
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
                return TurmsBusinessError.notFalsyPromise('profileAccessStrategy');
            }
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                name,
                intro,
                profileAccessStrategy
            }
        }).then(() => null);
    }

    queryUserProfile(userId: string, lastUpdatedDate?: Date): Promise<ParsedModel.UserInfoWithVersion | undefined> {
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            queryUserProfileRequest: {
                userId: userId,
                lastUpdatedDate: RequestUtil.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => {
            const usersInfosWithVersion = n.data?.usersInfosWithVersion;
            const userInfo = NotificationUtil.transform(usersInfosWithVersion?.userInfos?.[0]);
            if (userInfo) {
                return {
                    userInfo,
                    lastUpdatedDate: NotificationUtil.transformDate(usersInfosWithVersion.lastUpdatedDate)
                }
            }
        });
    }

    queryNearbyUsers(latitude: number,
                     longitude: number,
                     distance?: number,
                     maxNumber?: number,
                     withCoordinates?: boolean,
                     withDistance?: boolean,
                     withInfo?: boolean): Promise<ParsedModel.NearbyUser[]> {
        if (RequestUtil.isFalsy(latitude)) {
            return TurmsBusinessError.notFalsyPromise('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsyPromise('longitude');
        }
        return this._turmsClient.driver.send({
            queryNearbyUsersRequest: {
                latitude,
                longitude,
                distance,
                maxNumber,
                withCoordinates,
                withDistance,
                withInfo
            }
        }).then(n => NotificationUtil.transformOrEmpty(n.data?.nearbyUsers?.nearbyUsers));
    }

    queryOnlineStatusesRequest(userIds: string[]): Promise<ParsedModel.UserStatusDetail[]> {
        if (RequestUtil.isFalsy(userIds)) {
            return TurmsBusinessError.notFalsyPromise('userIds', true);
        }
        return this._turmsClient.driver.send({
            queryUserOnlineStatusesRequest: {
                userIds
            }
        }).then(n => NotificationUtil.transformOrEmpty(n.data?.usersOnlineStatuses?.userStatuses));
    }

    // Relationship

    queryRelationships(
        relatedUserIds?: string[],
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryRelationshipsRequest: {
                userIds: relatedUserIds || [],
                blocked: isBlocked,
                groupIndex,
                lastUpdatedDate: RequestUtil.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.transform(n.data?.userRelationshipsWithVersion));
    }

    queryRelatedUserIds(
        isBlocked?: boolean,
        groupIndex?: number,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryRelatedUserIdsRequest: {
                blocked: isBlocked,
                groupIndex,
                lastUpdatedDate: RequestUtil.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryFriends(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this.queryRelationships(undefined, false, groupIndex, lastUpdatedDate);
    }

    queryBlockedUsers(groupIndex?: number, lastUpdatedDate?: Date): Promise<ParsedModel.UserRelationshipsWithVersion | undefined> {
        return this.queryRelationships(undefined, true, groupIndex, lastUpdatedDate);
    }

    createRelationship(userId: string, isBlocked: boolean, groupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        if (RequestUtil.isFalsy(isBlocked)) {
            return TurmsBusinessError.notFalsyPromise('isBlocked');
        }
        return this._turmsClient.driver.send({
            createRelationshipRequest: {
                userId,
                blocked: isBlocked,
                groupIndex
            }
        }).then(() => null);
    }

    createFriendRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, false, groupIndex);
    }

    createBlockedUserRelationship(userId: string, groupIndex?: number): Promise<void> {
        return this.createRelationship(userId, true, groupIndex);
    }

    deleteRelationship(relatedUserId: string, deleteGroupIndex?: number, targetGroupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsyPromise('relatedUserId');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipRequest: {
                userId: relatedUserId,
                groupIndex: deleteGroupIndex,
                targetGroupIndex
            }
        }).then(() => null);
    }

    updateRelationship(relatedUserId: string, isBlocked?: boolean, groupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsyPromise('relatedUserId');
        }
        if (RequestUtil.areAllFalsy(isBlocked, groupIndex)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                blocked: isBlocked,
                newGroupIndex: groupIndex
            }
        }).then(() => null);
    }

    sendFriendRequest(recipientId: string, content: string): Promise<string> {
        if (RequestUtil.isFalsy(recipientId)) {
            return TurmsBusinessError.notFalsyPromise('recipientId');
        }
        if (RequestUtil.isFalsy(content)) {
            return TurmsBusinessError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createFriendRequestRequest: {
                recipientId,
                content
            }
        }).then(n => NotificationUtil.getFirstIdOrThrow(n));
    }

    replyFriendRequest(requestId: string, responseAction: string | ResponseAction, reason?: string): Promise<void> {
        if (RequestUtil.isFalsy(requestId)) {
            return TurmsBusinessError.notFalsyPromise('requestId');
        }
        if (RequestUtil.isFalsy(responseAction)) {
            return TurmsBusinessError.notFalsyPromise('responseAction');
        }
        if (typeof responseAction === 'string') {
            responseAction = ResponseAction[responseAction] as ResponseAction;
            if (RequestUtil.isFalsy(responseAction)) {
                return TurmsBusinessError.notFalsyPromise('reponseAction');
            }
        }
        return this._turmsClient.driver.send({
            updateFriendRequestRequest: {
                requestId: requestId,
                responseAction: responseAction,
                reason
            }
        }).then(() => null);
    }

    queryFriendRequests(areSentByMe: boolean, lastUpdatedDate?: Date): Promise<ParsedModel.UserFriendRequestsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryFriendRequestsRequest: {
                areSentByMe,
                lastUpdatedDate: RequestUtil.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.transform(n.data?.userFriendRequestsWithVersion));
    }

    createRelationshipGroup(name: string): Promise<string> {
        if (RequestUtil.isFalsy(name)) {
            return TurmsBusinessError.notFalsyPromise('name');
        }
        return this._turmsClient.driver.send({
            createRelationshipGroupRequest: {
                name
            }
        }).then(n => NotificationUtil.getFirstIdOrThrow(n));
    }

    deleteRelationshipGroups(groupIndex: number, targetGroupIndex?: number): Promise<void> {
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsyPromise('groupIndex');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipGroupRequest: {
                groupIndex,
                targetGroupIndex
            }
        }).then(() => null);
    }

    updateRelationshipGroup(groupIndex: number, newName: string): Promise<void> {
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsyPromise('groupIndex');
        }
        if (RequestUtil.isFalsy(newName)) {
            return TurmsBusinessError.notFalsyPromise('newName');
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
                lastUpdatedDate: RequestUtil.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.transform(n.data?.userRelationshipGroupsWithVersion));
    }

    moveRelatedUserToGroup(relatedUserId: string, groupIndex: number): Promise<void> {
        if (RequestUtil.isFalsy(relatedUserId)) {
            return TurmsBusinessError.notFalsyPromise('relatedUserId');
        }
        if (RequestUtil.isFalsy(groupIndex)) {
            return TurmsBusinessError.notFalsyPromise('groupIndex');
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                newGroupIndex: groupIndex
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
            return TurmsBusinessError.notFalsyPromise('latitude');
        }
        if (RequestUtil.isFalsy(longitude)) {
            return TurmsBusinessError.notFalsyPromise('longitude');
        }
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        return this._turmsClient.driver.send({
            updateUserLocationRequest: {
                latitude,
                longitude,
                name,
                address
            }
        }).then(() => null);
    }

    private static _parseDeviceType(deviceType: string | DeviceType): DeviceType {
        if (typeof deviceType === 'string') {
            deviceType = DeviceType[deviceType] as DeviceType
            if (RequestUtil.isFalsy(deviceType)) {
                throw TurmsBusinessError.from(TurmsStatusCode.ILLEGAL_ARGUMENT, 'illegal DeviceType');
            }
            return deviceType;
        } else if (typeof deviceType === 'number') {
            if (deviceType >= 0 && deviceType <= DeviceType.UNKNOWN) {
                return deviceType;
            } else {
                throw TurmsBusinessError.from(TurmsStatusCode.ILLEGAL_ARGUMENT, 'illegal DeviceType');
            }
        } else {
            return SystemUtil.getDeviceType();
        }
    }

    private static _parseUserStatus(onlineStatus: string | UserStatus): UserStatus {
        if (typeof onlineStatus === 'string') {
            onlineStatus = UserStatus[onlineStatus] as UserStatus;
            if (RequestUtil.isFalsy(onlineStatus)) {
                TurmsBusinessError.notFalsy('onlineStatus');
            }
        }
        if (onlineStatus === UserStatus.OFFLINE) {
            throw TurmsBusinessError.illegalParam('onlineStatus cannot be OFFLINE');
        }
        return onlineStatus;
    }

    private _changeToOnline(): void {
        if (!this.isLoggedIn) {
            this._stateStore.isSessionOpen = true;
            this._onOnlineListeners.forEach(listener => listener());
        }
    }

    private _changeToOffline(sessionCloseInfo?: SessionCloseInfo): void {
        if (this.isLoggedIn) {
            this._userInfo.onlineStatus = UserStatus.OFFLINE;
            this._stateStore.isSessionOpen = false;
            this._onOfflineListeners.forEach(listener => listener(sessionCloseInfo));
        }
    }
}