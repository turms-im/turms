import { NotificationType, RequestType } from '../driver/service/shared-context-service';
import DataParser from '../util/data-parser';
import { DeviceType } from '../model/proto/constant/device_type';
import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import { ProfileAccessStrategy } from '../model/proto/constant/profile_access_strategy';
import { ResponseAction } from '../model/proto/constant/response_action';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import SessionCloseInfo from '../model/session-close-info';
import SessionCloseStatus from '../model/session-close-status';
import StateStore from '../driver/state-store';
import SystemUtil from '../util/system-util';
import TurmsClient from '../turms-client';
import UserLocation from '../model/user-location';
import { UserStatus } from '../model/proto/constant/user_status';
import Validator from '../util/validator';
import CollectionUtil from '../util/collection-util';

export interface UserInfo {
    userId?: string;
    password?: string;
    deviceType?: DeviceType;
    deviceDetails?: Record<string, string>;
    onlineStatus?: UserStatus;
    location?: UserLocation;
}

export default class UserService {
    private _turmsClient: TurmsClient;
    private _stateStore: StateStore;
    private _userInfo: UserInfo = {};
    private _storePassword = false;

    private _onOnlineListeners: (() => void)[] = [];
    private _onOfflineListeners: ((sessionCloseInfo: SessionCloseInfo) => void)[] = [];

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._stateStore = turmsClient.driver.stateStore();
        turmsClient.driver.addOnDisconnectedListener(() => this._changeToOffline({
            closeStatus: SessionCloseStatus.CONNECTION_CLOSED
        }));
        turmsClient.driver.addNotificationListener(notification => {
            if (notification.closeStatus && this.isLoggedIn) {
                this._changeToOffline({
                    closeStatus: notification.closeStatus,
                    businessStatus: notification.code,
                    reason: notification.reason
                });
            }
        });
        turmsClient.driver.addSharedContextNotificationListener(NotificationType.UPDATE_LOGGED_IN_USER_INFO,
            notification => {
                const wasLoggedIn = this.isLoggedIn;
                this._userInfo = notification.data;
                const isLoggedIn = this.isLoggedIn;
                if (wasLoggedIn) {
                    if (!isLoggedIn) {
                        this._changeToOffline({
                            closeStatus: SessionCloseStatus.UNKNOWN_ERROR
                        }, false);
                    }
                } else {
                    if (isLoggedIn) {
                        this._changeToOnline(false);
                    }
                }
            });
    }

    get userInfo(): UserInfo {
        return JSON.parse(JSON.stringify(this._userInfo));
    }

    get isLoggedIn(): boolean {
        const info = this._userInfo;
        return info && info.onlineStatus >= 0 && info.onlineStatus !== UserStatus.OFFLINE;
    }

    addOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners.push(listener);
    }

    addOnOfflineListener(listener: (sessionCloseInfo: SessionCloseInfo) => void): void {
        this._onOfflineListeners.push(listener);
    }

    removeOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners = this._onOnlineListeners.filter(cur => cur !== listener);
    }

    removeOnOfflineListener(listener: () => void): void {
        this._onOfflineListeners = this._onOfflineListeners.filter(cur => cur !== listener);
    }

    /**
     * Note: Because of user privacy policies, most modern browsers request Geolocation APIs to run in HTTPS,
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

    login({
        userId,
        password,
        deviceType,
        deviceDetails,
        onlineStatus,
        location,
        storePassword
    }: {
        userId: string,
        password?: string,
        deviceType?: string | DeviceType,
        deviceDetails?: Record<string, string>,
        onlineStatus?: string | UserStatus,
        location?: GeolocationPosition | UserLocation,
        storePassword?: boolean
    }): Promise<Response<void>> {
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        const userInfo: UserInfo = {};
        userInfo.userId = userId;
        userInfo.password = storePassword ? password : null;
        try {
            userInfo.deviceType = UserService._parseDeviceType(deviceType) ?? SystemUtil.getDeviceType();
        } catch (e) {
            return Promise.reject(e);
        }
        userInfo.deviceDetails = deviceDetails;
        try {
            userInfo.onlineStatus = UserService._parseUserStatus(onlineStatus) || UserStatus.AVAILABLE;
        } catch (e) {
            return Promise.reject(e);
        }
        if (location) {
            const parsedLocation = location['coords'] as GeolocationCoordinates || location as UserLocation;
            if (Validator.isFalsy(parsedLocation.longitude)) {
                return ResponseError.notFalsyPromise('longitude');
            }
            if (Validator.isFalsy(parsedLocation.latitude)) {
                return ResponseError.notFalsyPromise('latitude');
            }
            userInfo.location = new UserLocation(parsedLocation.longitude,
                parsedLocation.latitude,
                (parsedLocation as UserLocation).details);
        }
        this._storePassword = storePassword;
        return new Promise((resolve, reject) => {
            const driver = this._turmsClient.driver;
            const useSharedContext = this._stateStore.useSharedContext;
            this._connect(useSharedContext, userId, userInfo.deviceType)
                .then(() => {
                    const isLoggedInUser = this.isLoggedIn
                        // TODO: handle undefined and null
                        && JSON.stringify(this._userInfo) === JSON.stringify(userInfo);
                    if (isLoggedInUser) {
                        return false;
                    }
                    if (!useSharedContext) {
                        return true;
                    }
                    return driver.requestSharedContext({ type: RequestType.REQUEST_LOGIN })
                        .then(authorized => {
                            if (!authorized) {
                                throw new Error('Another session is logging in');
                            }
                            return true;
                        });
                })
                .then(needLogin => {
                    if (!needLogin) {
                        this._changeToOnline();
                        return resolve(Response.nullValue());
                    }
                    return driver.send({
                        createSessionRequest: {
                            version: 1,
                            userId,
                            password,
                            deviceType: userInfo.deviceType,
                            deviceDetails: userInfo.deviceDetails || {},
                            userStatus: userInfo.onlineStatus,
                            location: userInfo.location
                        }
                    }).then(n => {
                        this._changeToOnline();
                        this._userInfo = userInfo;
                        this._updateSharedUserInfo();
                        return useSharedContext
                            ? driver.requestSharedContext({
                                type: RequestType.FINISH_LOGIN_REQUEST
                            }).finally(() => resolve(Response.fromNotification(n)))
                            : resolve(Response.fromNotification(n));
                    }).catch(e => useSharedContext
                        ? driver.requestSharedContext({
                            type: RequestType.FINISH_LOGIN_REQUEST
                        }).finally(() => reject(e))
                        : reject(e));
                })
                .catch(e => reject(e));
        });
    }

    private _connect(useSharedContext: boolean, userId: string, deviceType: DeviceType): Promise<void> {
        let connect: Promise<void>;
        const driver = this._turmsClient.driver;
        if (driver.isConnected) {
            connect = Promise.resolve();
        } else if (useSharedContext) {
            connect = driver.requestSharedContext({
                type: RequestType.REBIND_CONTEXT_ID,
                data: {
                    userId,
                    deviceType
                }
            }).then(() => driver.connect());
        } else {
            connect = driver.connect();
        }
        return connect;
    }

    logout({
        immediate = true
    }: {
        immediate?: boolean
    } = {}): Promise<Response<void>> {
        let promise: Promise<any>;
        if (immediate) {
            promise = this._turmsClient.driver.disconnect();
        } else {
            promise = this._turmsClient.driver.send({
                deleteSessionRequest: {}
            }).catch(e => {
                if (e?.code !== ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED) {
                    throw e;
                }
            });
        }
        return promise.then(() => {
            this._changeToOffline({
                closeStatus: SessionCloseStatus.DISCONNECTED_BY_CLIENT
            });
            return Response.nullValue();
        });
    }

    updateOnlineStatus({
        onlineStatus
    }: {
        onlineStatus: string | UserStatus
    }): Promise<Response<void>> {
        if (Validator.isFalsy(onlineStatus)) {
            return ResponseError.notFalsyPromise('onlineStatus');
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
        }).then(n => {
            this._userInfo.onlineStatus = onlineStatus as UserStatus;
            this._updateSharedUserInfo();
            return Response.fromNotification(n);
        });
    }

    disconnectOnlineDevices({
        deviceTypes
    }: {
        deviceTypes: string[] | DeviceType[]
    }): Promise<Response<void>> {
        if (Validator.isFalsy(deviceTypes)) {
            return ResponseError.notFalsyPromise('deviceTypes', true);
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
        }).then(n => Response.fromNotification(n));
    }

    updatePassword({
        password
    }: {
        password: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(password)) {
            return ResponseError.notFalsyPromise('password');
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                password
            }
        }).then(n => {
            if (this._storePassword) {
                this._userInfo.password = password;
                this._updateSharedUserInfo();
            }
            return Response.fromNotification(n);
        });
    }

    updateProfile({
        name,
        intro,
        profilePicture,
        profileAccessStrategy
    }: {
        name?: string,
        intro?: string,
        profilePicture?: string,
        profileAccessStrategy?: string | ProfileAccessStrategy
    }): Promise<Response<void>> {
        if (Validator.areAllFalsy(name, intro, profileAccessStrategy)) {
            return Promise.resolve(Response.nullValue());
        }
        if (typeof profileAccessStrategy === 'string') {
            profileAccessStrategy = ProfileAccessStrategy[profileAccessStrategy] as ProfileAccessStrategy;
            if (Validator.isFalsy(profileAccessStrategy)) {
                return ResponseError.notFalsyPromise('profileAccessStrategy');
            }
        }
        return this._turmsClient.driver.send({
            updateUserRequest: {
                name,
                intro,
                profilePicture,
                profileAccessStrategy
            }
        }).then(n => Response.fromNotification(n));
    }

    queryUserProfiles({
        userIds,
        lastUpdatedDate
    }: {
        userIds: string[],
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.UserInfo[]>> {
        if (!userIds?.length) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryUserProfilesRequest: {
                userIds: CollectionUtil.uniqueArray(userIds),
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data =>
            NotificationUtil.transformOrEmpty(data.userInfosWithVersion?.userInfos)));
    }

    queryNearbyUsers({
        latitude,
        longitude,
        maxCount,
        maxDistance,
        withCoordinates,
        withDistance,
        withUserInfo
    }: {
        latitude: number,
        longitude: number,
        maxCount?: number,
        maxDistance?: number,
        withCoordinates?: boolean,
        withDistance?: boolean,
        withUserInfo?: boolean
    }): Promise<Response<ParsedModel.NearbyUser[]>> {
        if (Validator.isFalsy(latitude)) {
            return ResponseError.notFalsyPromise('latitude');
        }
        if (Validator.isFalsy(longitude)) {
            return ResponseError.notFalsyPromise('longitude');
        }
        return this._turmsClient.driver.send({
            queryNearbyUsersRequest: {
                latitude,
                longitude,
                maxCount,
                maxDistance,
                withCoordinates,
                withDistance,
                withUserInfo
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.nearbyUsers?.nearbyUsers)));
    }

    queryOnlineStatusesRequest({
        userIds
    }: {
        userIds: string[]
    }): Promise<Response<ParsedModel.UserOnlineStatus[]>> {
        if (Validator.isFalsy(userIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryUserOnlineStatusesRequest: {
                userIds
            }
        }).then(n => Response.fromNotification(n, data =>
            NotificationUtil.transformOrEmpty(data.userOnlineStatuses?.statuses)));
    }

    // Relationship

    queryRelationships({
        relatedUserIds,
        isBlocked,
        groupIndexes,
        lastUpdatedDate
    }: {
        relatedUserIds?: string[],
        isBlocked?: boolean,
        groupIndexes?: number[],
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.UserRelationshipsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryRelationshipsRequest: {
                userIds: relatedUserIds || [],
                blocked: isBlocked,
                groupIndexes: groupIndexes || [],
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userRelationshipsWithVersion)));
    }

    queryRelatedUserIds({
        isBlocked,
        groupIndexes,
        lastUpdatedDate
    }: {
        isBlocked?: boolean,
        groupIndexes?: number[],
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.LongsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryRelatedUserIdsRequest: {
                blocked: isBlocked,
                groupIndexes: groupIndexes || [],
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
    }

    queryFriends({
        groupIndexes,
        lastUpdatedDate
    }: {
        groupIndexes?: number[],
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.UserRelationshipsWithVersion | undefined>> {
        return this.queryRelationships({
            isBlocked: false,
            groupIndexes,
            lastUpdatedDate
        });
    }

    queryBlockedUsers({
        groupIndexes,
        lastUpdatedDate
    }: {
        groupIndexes?: number[],
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.UserRelationshipsWithVersion | undefined>> {
        return this.queryRelationships({
            isBlocked: true,
            groupIndexes,
            lastUpdatedDate
        });
    }

    createRelationship({
        userId,
        isBlocked,
        groupIndex
    }: {
        userId: string,
        isBlocked: boolean,
        groupIndex?: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        if (Validator.isFalsy(isBlocked)) {
            return ResponseError.notFalsyPromise('isBlocked');
        }
        return this._turmsClient.driver.send({
            createRelationshipRequest: {
                userId,
                blocked: isBlocked,
                groupIndex
            }
        }).then(n => Response.fromNotification(n));
    }

    createFriendRelationship({
        userId,
        groupIndex
    }: {
        userId: string,
        groupIndex?: number
    }): Promise<Response<void>> {
        return this.createRelationship({
            userId,
            groupIndex,
            isBlocked: false
        });
    }

    createBlockedUserRelationship({
        userId,
        groupIndex
    }: {
        userId: string,
        groupIndex?: number
    }): Promise<Response<void>> {
        return this.createRelationship({
            userId,
            groupIndex,
            isBlocked: true
        });
    }

    deleteRelationship({
        relatedUserId,
        deleteGroupIndex,
        targetGroupIndex
    }: {
        relatedUserId: string,
        deleteGroupIndex?: number,
        targetGroupIndex?: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(relatedUserId)) {
            return ResponseError.notFalsyPromise('relatedUserId');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipRequest: {
                userId: relatedUserId,
                groupIndex: deleteGroupIndex,
                targetGroupIndex
            }
        }).then(n => Response.fromNotification(n));
    }

    updateRelationship({
        relatedUserId,
        isBlocked,
        groupIndex
    }: {
        relatedUserId: string,
        isBlocked?: boolean,
        groupIndex?: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(relatedUserId)) {
            return ResponseError.notFalsyPromise('relatedUserId');
        }
        if (Validator.areAllFalsy(isBlocked, groupIndex)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                blocked: isBlocked,
                newGroupIndex: groupIndex
            }
        }).then(n => Response.fromNotification(n));
    }

    sendFriendRequest({
        recipientId,
        content
    }: {
        recipientId: string,
        content: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(recipientId)) {
            return ResponseError.notFalsyPromise('recipientId');
        }
        if (Validator.isFalsy(content)) {
            return ResponseError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createFriendRequestRequest: {
                recipientId,
                content
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    replyFriendRequest({
        requestId,
        responseAction,
        reason
    }: {
        requestId: string,
        responseAction: string | ResponseAction,
        reason?: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(requestId)) {
            return ResponseError.notFalsyPromise('requestId');
        }
        if (Validator.isFalsy(responseAction)) {
            return ResponseError.notFalsyPromise('responseAction');
        }
        if (typeof responseAction === 'string') {
            responseAction = ResponseAction[responseAction] as ResponseAction;
            if (Validator.isFalsy(responseAction)) {
                return ResponseError.notFalsyPromise('reponseAction');
            }
        }
        return this._turmsClient.driver.send({
            updateFriendRequestRequest: {
                requestId: requestId,
                responseAction: responseAction,
                reason
            }
        }).then(n => Response.fromNotification(n));
    }

    queryFriendRequests({
        areSentByMe,
        lastUpdatedDate
    }: {
        areSentByMe: boolean,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.UserFriendRequestsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryFriendRequestsRequest: {
                areSentByMe,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userFriendRequestsWithVersion)));
    }

    createRelationshipGroup({
        name
    }: {
        name: string
    }): Promise<Response<number>> {
        if (Validator.isFalsy(name)) {
            return ResponseError.notFalsyPromise('name');
        }
        return this._turmsClient.driver.send({
            createRelationshipGroupRequest: {
                name
            }
        }).then(n => Response.fromNotification(n, data => parseInt(NotificationUtil.getLongOrThrow(data))));
    }

    deleteRelationshipGroups({
        groupIndex,
        targetGroupIndex
    }: {
        groupIndex: number,
        targetGroupIndex?: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupIndex)) {
            return ResponseError.notFalsyPromise('groupIndex');
        }
        return this._turmsClient.driver.send({
            deleteRelationshipGroupRequest: {
                groupIndex,
                targetGroupIndex
            }
        }).then(n => Response.fromNotification(n));
    }

    updateRelationshipGroup({
        groupIndex,
        newName
    }: {
        groupIndex: number,
        newName: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupIndex)) {
            return ResponseError.notFalsyPromise('groupIndex');
        }
        if (Validator.isFalsy(newName)) {
            return ResponseError.notFalsyPromise('newName');
        }
        return this._turmsClient.driver.send({
            updateRelationshipGroupRequest: {
                groupIndex,
                newName
            }
        }).then(n => Response.fromNotification(n));
    }

    queryRelationshipGroups({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.UserRelationshipGroupsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryRelationshipGroupsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userRelationshipGroupsWithVersion)));
    }

    moveRelatedUserToGroup({
        relatedUserId,
        groupIndex
    }: {
        relatedUserId: string,
        groupIndex: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(relatedUserId)) {
            return ResponseError.notFalsyPromise('relatedUserId');
        }
        if (Validator.isFalsy(groupIndex)) {
            return ResponseError.notFalsyPromise('groupIndex');
        }
        return this._turmsClient.driver.send({
            updateRelationshipRequest: {
                userId: relatedUserId,
                newGroupIndex: groupIndex
            }
        }).then(n => Response.fromNotification(n));
    }

    /**
     * updateLocation() in UserService is different from sendMessage() with records of location in MessageService
     * updateLocation() in UserService sends the location of user to the server only.
     * sendMessage() with records of location sends user's location to both server and its recipients.
     */
    updateLocation({
        latitude,
        longitude,
        details
    }: {
        latitude: number,
        longitude: number,
        details?: Record<string, string>
    }): Promise<Response<void>> {
        if (Validator.isFalsy(latitude)) {
            return ResponseError.notFalsyPromise('latitude');
        }
        if (Validator.isFalsy(longitude)) {
            return ResponseError.notFalsyPromise('longitude');
        }
        return this._turmsClient.driver.send({
            updateUserLocationRequest: {
                latitude,
                longitude,
                details: details || {}
            }
        }).then(n => Response.fromNotification(n));
    }

    private static _parseDeviceType(deviceType: string | DeviceType): DeviceType {
        if (typeof deviceType === 'string') {
            deviceType = DeviceType[deviceType] as DeviceType;
            if (Validator.isFalsy(deviceType)) {
                throw ResponseError.from({
                    code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                    reason: 'illegal DeviceType'
                });
            }
            return deviceType;
        } else if (typeof deviceType === 'number') {
            if (deviceType >= 0 && deviceType <= DeviceType.UNKNOWN) {
                return deviceType;
            } else {
                throw ResponseError.from({
                    code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                    reason: 'illegal DeviceType'
                });
            }
        }
    }

    private static _parseUserStatus(onlineStatus: string | UserStatus): UserStatus {
        if (typeof onlineStatus === 'string') {
            onlineStatus = UserStatus[onlineStatus] as UserStatus;
            if (Validator.isFalsy(onlineStatus)) {
                ResponseError.notFalsy('onlineStatus');
            }
        }
        if (onlineStatus === UserStatus.OFFLINE) {
            throw ResponseError.illegalParam('onlineStatus cannot be OFFLINE');
        }
        return onlineStatus;
    }

    private _updateSharedUserInfo(): void {
        if (this._stateStore.useSharedContext) {
            this._turmsClient.driver.requestSharedContext({
                type: RequestType.UPDATE_LOGGED_IN_USER_INFO,
                data: this._userInfo
            });
        }
    }

    private _changeToOnline(check = true): void {
        if (!check || !this.isLoggedIn) {
            this._stateStore.isSessionOpen = true;
            this._onOnlineListeners.forEach(listener => listener());
        }
    }

    private _changeToOffline(sessionCloseInfo: SessionCloseInfo, check = true): void {
        if (!check || this.isLoggedIn) {
            this._userInfo.onlineStatus = UserStatus.OFFLINE;
            this._stateStore.isSessionOpen = false;
            this._onOfflineListeners.forEach(listener => listener(sessionCloseInfo));
        }
    }
}