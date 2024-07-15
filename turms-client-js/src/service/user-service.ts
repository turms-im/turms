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

    /**
     * Add an online listener that will be called when the user becomes online.
     * A session is considered online when it has a TCP connection with the server,
     * and the user is logged in by {@link login}.
     */
    addOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners.push(listener);
    }

    /**
     * Add an offline listener that will be called when the user becomes offline.
     * A session is considered offline when it has no TCP connection with the server,
     * or has a connected TCP connection with the server, but the user is not logged in by {@link login}.
     */
    addOnOfflineListener(listener: (sessionCloseInfo: SessionCloseInfo) => void): void {
        this._onOfflineListeners.push(listener);
    }

    /**
     * Remove an online listener.
     */
    removeOnOnlineListener(listener: () => void): void {
        this._onOnlineListeners = this._onOnlineListeners.filter(cur => cur !== listener);
    }

    /**
     * Remove an offline listener.
     */
    removeOnOfflineListener(listener: () => void): void {
        this._onOfflineListeners = this._onOfflineListeners.filter(cur => cur !== listener);
    }

    /**
     * Note: Because of user privacy policies, most modern browsers request Geolocation APIs to run in HTTPS,
     * this method cannot get the location of users in insecure sites.
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

    /**
     * Log in.
     *
     * @remarks
     * * If the underlying TCP connection is not connected,
     *   the method will connect it first under the hood.
     * * If log in successfully, the session is considered online.
     *   And the listener registered by {@link addOnOnlineListener} will be called.
     *
     * Related docs:
     * * {@link https://turms-im.github.io/docs/server/module/identity-access-management.html | Turms Identity and Access Management}
     *
     * @param userId - the user ID
     * @param password - the user password.
     * @param deviceType - the device type.
     * If null, the detected device type will be used.
     * Note: The device types of online session that conflicts with {@link deviceType}
     * will be closed by the server if logged in successfully.
     * @param deviceDetails - the device details.
     * Some plugins use this to pass additional information about the device.
     * e.g. Push notification token.
     * @param onlineStatus - the online status.
     * @param location - the location of the user.
     * @param storePassword - whether to store the password in {@link userInfo}.
     * @throws {@link ResponseError} if an error occurs.
     * 1. If the client is not compatible with the server, throws
     * with the code {@link ResponseStatusCode#UNSUPPORTED_CLIENT_VERSION}.
     * 2. Depending on the server property `turms.gateway.simultaneous-login.strategy`,
     * throws with the code {@link ResponseStatusCode#LOGIN_FROM_FORBIDDEN_DEVICE_TYPE}
     * if the specified device type is forbidden.
     * 3. If provided credentials are invalid,
     * throws with the code {@link ResponseStatusCode#LOGIN_AUTHENTICATION_FAILED}.
     */
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
                (parsedLocation as UserLocation).details,
                []);
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
                            location: userInfo.location,
                            customAttributes: []
                        },
                        customAttributes: []
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

    /**
     * Log out.
     *
     * @remarks
     * After logging out, the session is considered offline.
     * And the listener registered by {@link addOnOfflineListener} will be called.
     *
     * @param disconnect - whether to close the underlying TCP connection immediately
     * rather than sending a delete session request first and then closing the connection.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                deleteSessionRequest: {
                    customAttributes: []
                },
                customAttributes: []
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

    /**
     * Update the online status of the logged-in user.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.user-online-status-updated.notify-requester-other-online-sessions`
     *   is true （true by default）,
     *   the server will send an update online status notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.user-online-status-updated.notify-non-blocked-related-users`,
     *   is true (false by default),
     *   the server will send an update online status notification to all non-blocked related users of the logged-in user actively.
     *
     * @param onlineStatus - the new online status.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                userStatus: onlineStatus,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => {
            this._userInfo.onlineStatus = onlineStatus as UserStatus;
            this._updateSharedUserInfo();
            return Response.fromNotification(n);
        });
    }

    /**
     * Disconnect the online devices of the logged-in user.
     *
     * @remarks
     * If the specified device types are not online, nothing will happen and
     * no exception will be thrown.
     *
     * @param deviceTypes - the device types to disconnect.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                deviceTypes: deviceTypes as DeviceType[],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the password of the logged-in user.
     *
     * @param password - the new password.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                password,
                userDefinedAttributes: {},
                customAttributes: []
            },
            customAttributes: []
        }).then(n => {
            if (this._storePassword) {
                this._userInfo.password = password;
                this._updateSharedUserInfo();
            }
            return Response.fromNotification(n);
        });
    }

    /**
     * Update the profile of the logged-in user.
     *
     * @param name - the new name.
     * If null, the name will not be updated.
     * @param intro - the new intro.
     * If null, the intro will not be updated.
     * @param profilePicture - the new profile picture.
     * If null, the profile picture will not be updated.
     * The profile picture can be anything you want.
     * e.g. an image URL or a base64 encoded string.
     * Note: You can use {@link StorageService#uploadUserProfilePicture}
     * to upload the profile picture and use the returned URL as {@link profilePicture}.
     * @param profileAccessStrategy - the new profile access strategy.
     * If null, the profile access strategy will not be updated.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                profileAccessStrategy,
                userDefinedAttributes: {},
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find user profiles.
     *
     * @param userIds - the target user IDs.
     * @param lastUpdatedDate - the last updated date of user profiles stored locally.
     * The server will only return user profiles that are updated after {@link lastUpdatedDate}.
     * If null, all user profiles will be returned.
     * @returns a list of user profiles.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                fieldsToHighlight: [],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data =>
            NotificationUtil.transformOrEmpty(data.userInfosWithVersion?.userInfos)));
    }

    /**
     * Search for user profiles.
     *
     * @param name - search for user profiles whose name matches {@link name}.
     * @param highlight - whether to highlight the name.
     * If true, the highlighted parts of the name will be paired with '\u0002' and '\u0003'.
     * @param skip - the number of user profiles to skip.
     * @param limit - the max number of user profiles to return.
     * @returns a list of user profiles sorted in descending relevance.
     * @throws {@link ResponseError} if an error occurs.
     */
    searchUserProfiles({
                     name, highlight, skip, limit
                 }: {
        name: string,
        highlight?: boolean, skip?: number, limit?: number
    }): Promise<Response<ParsedModel.UserInfo[]>> {
        if (!name) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryUserProfilesRequest: {
                userIds: [],
                name: name,
                fieldsToHighlight: highlight ? [1] : [],
                skip: skip,
                limit: limit,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, (data) =>
            NotificationUtil.transform(data.userInfosWithVersion?.userInfos)));
    }

    /**
     * Upsert user settings, such as "preferred language", "new message alert", etc.
     * Note that only the settings specified in `turms.service.user.settings.allowed-settings` can be upserted.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.user-setting-updated.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a user settings updated notification to all other online sessions of the logged-in user actively.
     *
     * @param settings - the user settings to upsert.
     * @throws {@link ResponseError} if an error occurs.
     * * If trying to update any existing immutable setting, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}
     * * If trying to upsert an unknown setting and the server property `turms.service.user.settings.ignore-unknown-settings-on-upsert` is
     *   false (false by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}.
     */
    upsertUserSettings(settings: Record<string, any>): Promise<Response<void>> {
        if (Object.keys(settings).length === 0) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateUserSettingsRequest: {
                settings,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Delete user settings.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.user-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a user settings deleted notification to all other online sessions of the logged-in user actively.
     *
     * @param names - the names of the user settings to delete. If null, all deletable user settings will be deleted.
     * @throws {@link ResponseError} if an error occurs.
     * * If trying to delete any non-deletable setting, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}.
     */
    deleteUserSettings(names?: string[]): Promise<Response<void>> {
        return this._turmsClient.driver.send({
            deleteUserSettingsRequest: {
                names: CollectionUtil.uniqueArray(names),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find user settings.
     *
     * @param names - the names of the user settings to query. If null, all user settings will be returned.
     * @param lastUpdatedDate - the last updated date of user settings stored locally.
     * The server will only return user settings if a setting has been updated after {@link lastUpdatedDate}.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryUserSettings(names?: string[], lastUpdatedDate?: Date): Promise<Response<ParsedModel.UserSettings | undefined>> {
        return this._turmsClient.driver.send({
            queryUserSettingsRequest: {
                names: CollectionUtil.uniqueArray(names),
                lastUpdatedDateStart: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data =>
            NotificationUtil.transform(data.userSettings)));
    }

    /**
     * Find nearby users.
     *
     * @param latitude - the latitude.
     * @param longitude - the longitude.
     * @param maxCount - the max count.
     * @param maxDistance - the max distance.
     * @param withCoordinates - whether to include coordinates.
     * @param withDistance - whether to include distance.
     * @param withUserInfo - whether to include user info.
     * @returns a list of nearby users.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                withUserInfo,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.nearbyUsers?.nearbyUsers)));
    }

    /**
     * Find online status of users.
     *
     * @param userIds - the target user IDs.
     * @returns a list of online status of users.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryOnlineStatuses({
        userIds
    }: {
        userIds: string[]
    }): Promise<Response<ParsedModel.UserOnlineStatus[]>> {
        if (Validator.isFalsy(userIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryUserOnlineStatusesRequest: {
                userIds,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data =>
            NotificationUtil.transformOrEmpty(data.userOnlineStatuses?.statuses)));
    }

    // Relationship

    /**
     * Find relationships.
     *
     * @param relatedUserIds - the target related user IDs.
     * @param isBlocked - whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes - the target group indexes for querying.
     * @param lastUpdatedDate - the last updated date of user relationships stored locally.
     * The server will only return relationships that are created after {@link lastUpdatedDate}.
     * If null, all relationships will be returned.
     * @returns relationships and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userRelationshipsWithVersion)));
    }

    /**
     * Find related user IDs.
     *
     * @param isBlocked - whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes - the target group indexes for querying.
     * @param lastUpdatedDate - the last updated date of related user IDs stored locally.
     * The server will only return related user IDs that are created after {@link lastUpdatedDate}.
     * If null, all related user IDs will be returned.
     * @returns related user IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
    }

    /**
     * Find friends.
     *
     * @param groupIndexes - the target group indexes for finding.
     * @param lastUpdatedDate - the last updated date of friends stored locally.
     * The server will only return friends that are created after {@link lastUpdatedDate}.
     * If null, all friends will be returned.
     * @returns friends and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Find blocked users.
     *
     * @param groupIndexes - the target group indexes for finding.
     * @param lastUpdatedDate - the last updated date of blocked users stored locally.
     * The server will only return friends that are created after {@link lastUpdatedDate}.
     * If null, all blocked users will be returned.
     * @returns blocked users and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Create a relationship.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to {@link userId} actively.
     *
     * @param userId - the target user ID.
     * @param isBlocked - whether to create a blocked relationship.
     * If true, a blocked relationship will be created,
     * and the target user will not be able to send messages to the logged-in user.
     * @param groupIndex - the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                groupIndex,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Create a friend (non-blocked) relationship.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to {@link userId} actively.
     *
     * @param userId - the target user ID.
     * @param groupIndex - the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Create a blocked user relationship.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to {@link userId} actively.
     *
     * @param userId - the target user ID.
     * @param groupIndex - the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Delete a relationship.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`,
     *   is true (true by default), the server will send a delete relationship notification to all group members in groups.
     *
     * @param relatedUserId - the target user ID.
     * @param deleteGroupIndex - the target group index in which delete the relationship.
     * If null, the relationship will be deleted in all groups.
     * @param targetGroupIndex - TODO: not implemented yet.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                targetGroupIndex,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update a relationship.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to {@link relatedUserId} actively.
     *
     * @param relatedUserId - the target user ID.
     * @param isBlocked - whether to update a blocked relationship.
     * If null, the relationship will not be updated.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                newGroupIndex: groupIndex,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Send a friend request.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-created.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a new friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-created.notify-friend-request-recipient`,
     *   is true (true by default), the server will send a new friend request notification to {@link recipientId} actively.
     *
     * @param recipientId - the target user ID.
     * @param content - the content of the friend request.
     * @returns the request ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                content,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Delete/Recall a friend request.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.user.friend-request.allow-recall-pending-friend-request-by-sender`
     *   is true (false by default), the logged-in user can recall pending friend requests sent by themselves.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALLING_FRIEND_REQUEST_IS_DISABLED}.
     * * If the logged-in user is not the sender of the friend request,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_SENDER_TO_RECALL_FRIEND_REQUEST}.
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALL_NON_PENDING_FRIEND_REQUEST}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-recalled.notify-friend-request-recipient`
     *   is true (true by default), the server will send a delete friend request notification to the recipient of the friend request actively.
     * @throws {@link ResponseError} if an error occurs.
     */
    deleteFriendRequest({
        requestId
    }: {
        requestId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(requestId)) {
            return ResponseError.notFalsyPromise('requestId');
        }
        return this._turmsClient.driver.send({
            deleteFriendRequestRequest: {
                requestId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Reply to a friend request.
     *
     * @remarks
     * If the logged-in user accepts a friend request sent by another user,
     * the server will create a relationship between the logged-in user and the friend request sender.
     *
     * Authorization:
     * * If the logged-in user is not the recipient of the friend request,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_RECIPIENT_TO_UPDATE_FRIEND_REQUEST}.
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_NON_PENDING_FRIEND_REQUEST}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-replied.notify-friend-request-sender`,
     *   is true (true by default), the server will send a reply friend request notification to the friend request sender actively.
     *
     * @param requestId - the target friend request ID.
     * @param responseAction - the response action.
     * @param reason - the reason of the response.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                reason,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find friend requests.
     *
     * @param areSentByMe - whether to find the friend requests sent by the logged-in user.
     * If true, find the friend requests sent by the logged-in user.
     * If false, find the friend requests not sent to the logged-in user.
     * @param lastUpdatedDate - the last updated date of friend requests stored locally.
     * The server will only return friend requests that are updated after {@link lastUpdatedDate}.
     * If null, all friend requests will be returned.
     * @returns friend requests and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userFriendRequestsWithVersion)));
    }

    /**
     * Create a relationship group.
     *
     * @param name - the name of the group.
     * @returns the index of the created group.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                name,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => parseInt(NotificationUtil.getLongOrThrow(data))));
    }

    /**
     * Delete relationship groups.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-relationship-group-members`,
     *   is true (false by default), the server will send a delete relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex - the target group index to delete.
     * @param targetGroupIndex - move the group members of {@link groupIndex} to {@link targetGroupIndex}
     * when the group is deleted.
     * If null, the group members of {@link groupIndex} will be moved to the default group.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                targetGroupIndex,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update a relationship group.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a updated relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-relationship-group-members`,
     *   is true (false by default), the server will send a updated relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex - the target group index.
     * @param newName - the new name of the group.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                newName,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find relationship groups.
     *
     * @param lastUpdatedDate - the last updated date of relationship groups stored locally.
     * The server will only return relationship groups that are updated after {@link lastUpdatedDate}.
     * If null, all relationship groups will be returned.
     * @returns relationship groups and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryRelationshipGroups({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.UserRelationshipGroupsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryRelationshipGroupsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userRelationshipGroupsWithVersion)));
    }

    /**
     * Move a related user to a group.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to {@link relatedUserId} actively.
     *
     * @param relatedUserId - the target user ID.
     * @param groupIndex - the target group index to which move the user.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                newGroupIndex: groupIndex,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the location of the logged-in user.
     *
     * @remarks
     * Note:
     * * {@link UserService#updateLocation} is different from
     *   {@link MessageService#sendMessage} with records of location.
     *   {@link UserService#updateLocation} sends the location of user to
     *   the server only.
     *   {@link MessageService#sendMessage} with records of location sends the user's location
     *   to both server and its recipients.
     *
     * @param latitude - the latitude.
     * @param longitude - the longitude.
     * @param details - the location details
     * @throws {@link ResponseError} if an error occurs.
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
                details: details || {},
                customAttributes: []
            },
            customAttributes: []
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