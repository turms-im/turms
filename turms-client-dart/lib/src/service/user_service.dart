import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';
import '../model/proto/model/common/value.pb.dart';
import '../model/proto/model/user/user_settings.pb.dart';
import '../model/proto/request/user/delete_user_settings_request.pb.dart';
import '../model/proto/request/user/query_user_settings_request.pb.dart';
import '../model/proto/request/user/relationship/delete_friend_request_request.pb.dart';
import '../model/proto/request/user/update_user_settings_request.pb.dart';
import '../util/system.dart';

class Location {
  final double longitude;
  final double latitude;

  const Location(this.longitude, this.latitude);
}

class User {
  Int64 userId;
  String? password;
  DeviceType? deviceType;
  Map<String, String>? deviceDetails;
  UserStatus? onlineStatus;
  Location? location;

  User(this.userId, this.password, this.deviceType, this.deviceDetails,
      this.onlineStatus, this.location);
}

typedef OnOnlineListener = void Function();
typedef OnOfflineListener = void Function(SessionCloseInfo info);

class UserService {
  final TurmsClient _turmsClient;
  User? _userInfo;
  bool _storePassword = false;

  final List<OnOnlineListener> _onOnlineListeners = [];
  final List<OnOfflineListener> _onOfflineListeners = [];

  UserService(this._turmsClient) {
    _turmsClient.driver
      ..addOnDisconnectedListener(({error, stackTrace}) => _changeToOffline(
          SessionCloseInfo.from(
              closeStatus: SessionCloseStatus.connectionClosed,
              cause: error,
              stackTrace: stackTrace)))
      ..addNotificationListener((notification) {
        if (notification.hasCloseStatus() && isLoggedIn) {
          _changeToOffline(SessionCloseInfo.from(
              closeStatus: notification.closeStatus,
              businessStatus: notification.code,
              reason: notification.reason));
        }
      });
  }

  /// The user information of the currently logged-in user.
  User? get userInfo => _userInfo;

  bool get isLoggedIn =>
      _userInfo != null && _userInfo?.onlineStatus != UserStatus.OFFLINE;

  /// Add an online listener that will be called when the user becomes online.
  /// A session is considered online when it has a TCP connection with the server,
  /// and the user is logged in by [login].
  void addOnOnlineListener(OnOnlineListener listener) =>
      _onOnlineListeners.add(listener);

  /// Add an offline listener that will be called when the user becomes offline.
  /// A session is considered offline when it has no TCP connection with the server,
  /// or has a connected TCP connection with the server, but the user is not logged in by [login].
  void addOnOfflineListener(OnOfflineListener listener) =>
      _onOfflineListeners.add(listener);

  /// Remove an online listener.
  void removeOnOnlineListener(OnOnlineListener listener) =>
      _onOnlineListeners.remove(listener);

  /// Remove an offline listener.
  void removeOnOfflineListener(OnOfflineListener listener) =>
      _onOfflineListeners.remove(listener);

  /// Log in.
  ///
  /// * If the underlying TCP connection is not connected,
  ///   the method will connect it first under the hood.
  /// * If log in successfully, the session is considered online.
  ///   And the listener registered by [addOnOnlineListener] will be called.
  ///
  /// Related docs:
  /// * [Turms Identity and Access Management](https://turms-im.github.io/docs/server/module/identity-access-management.html)
  ///
  /// **Params**:
  /// * `userId`: The user ID
  /// * `password`: The user password.
  /// * `deviceType`: The device type.
  /// If null, the detected device type will be used.
  /// Note: The device types of online session that conflicts with [deviceType]
  /// will be closed by the server if logged in successfully.
  /// * `deviceDetails`: The device details.
  /// Some plugins use this to pass additional information about the device.
  /// e.g. Push notification token.
  /// * `onlineStatus`: The online status.
  /// * `location`: The location of the user.
  /// * `storePassword`: Whether to store the password in [userInfo].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// 1. If the client is not compatible with the server, throws
  /// with the code [ResponseStatusCode.unsupportedClientVersion].
  /// 2. Depending on the server property `turms.gateway.simultaneous-login.strategy`,
  /// throws with the code [ResponseStatusCode.loginFromForbiddenDeviceType]
  /// if the specified device type is forbidden.
  /// 3. If provided credentials are invalid,
  /// throws with the code [ResponseStatusCode.loginAuthenticationFailed].
  Future<Response<void>> login(Int64 userId,
      {String? password,
      DeviceType? deviceType,
      Map<String, String>? deviceDetails,
      UserStatus? onlineStatus,
      Location? location,
      bool storePassword = false}) async {
    final user = User(userId, storePassword ? password : null,
        deviceType ?? currentDeviceType, deviceDetails, onlineStatus, location);
    if (!_turmsClient.driver.isConnected) {
      await _turmsClient.driver.connect();
    }
    final n = await _turmsClient.driver.send(CreateSessionRequest(
        version: 1,
        userId: userId,
        password: password,
        deviceType: deviceType,
        deviceDetails: deviceDetails,
        userStatus: onlineStatus,
        location: location == null
            ? null
            : UserLocation(
                longitude: location.longitude, latitude: location.latitude)));
    _changeToOnline();
    _storePassword = storePassword;
    _userInfo = user;
    return n.toNullResponse();
  }

  /// Log out.
  ///
  /// After logging out, the session is considered offline.
  /// And the listener registered by [addOnOfflineListener] will be called.
  ///
  /// **Params**:
  /// * `disconnect`: Whether to close the underlying TCP connection immediately
  /// rather than sending a delete session request first and then closing the connection.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> logout({bool disconnect = true}) async {
    if (disconnect) {
      await _turmsClient.driver.disconnect();
    } else {
      await _turmsClient.driver.send(DeleteSessionRequest());
    }
    _changeToOffline(SessionCloseInfo.from(
        closeStatus: SessionCloseStatus.disconnectedByClient));
    return Response.nullValue();
  }

  /// Update the online status of the logged-in user.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.user-online-status-updated.notify-requester-other-online-sessions`
  ///   is true （true by default）,
  ///   the server will send an update online status notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.user-online-status-updated.notify-non-blocked-related-users`,
  ///   is true (false by default),
  ///   the server will send an update online status notification to all non-blocked related users of the logged-in user actively.
  ///
  /// **Params**:
  /// * `onlineStatus`: The new online status.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateOnlineStatus(UserStatus onlineStatus) async {
    final n = await _turmsClient.driver
        .send(UpdateUserOnlineStatusRequest(userStatus: onlineStatus));
    _userInfo?.onlineStatus = onlineStatus;
    return n.toNullResponse();
  }

  /// Disconnect the online devices of the logged-in user.
  ///
  /// If the specified device types are not online, nothing will happen and
  /// no exception will be thrown.
  ///
  /// **Params**:
  /// * `deviceTypes`: The device types to disconnect.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> disconnectOnlineDevices(
      List<DeviceType> deviceTypes) async {
    if (deviceTypes.isEmpty) {
      throw ResponseException(
          code: ResponseStatusCode.illegalArgument,
          reason: '"deviceTypes" must not be empty');
    }
    final n = await _turmsClient.driver.send(UpdateUserOnlineStatusRequest(
        userStatus: UserStatus.OFFLINE, deviceTypes: deviceTypes));
    return n.toNullResponse();
  }

  /// Update the password of the logged-in user.
  ///
  /// **Params**:
  /// * `password`: The new password.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updatePassword(String password) async {
    final n =
        await _turmsClient.driver.send(UpdateUserRequest(password: password));
    if (_storePassword) {
      _userInfo?.password = password;
    }
    return n.toNullResponse();
  }

  /// Update the profile of the logged-in user.
  ///
  /// **Params**:
  /// * `name`: The new name.
  /// If null, the name will not be updated.
  /// * `intro`: The new intro.
  /// If null, the intro will not be updated.
  /// * `profilePicture`: The new profile picture.
  /// If null, the profile picture will not be updated.
  /// The profile picture can be anything you want.
  /// e.g. an image URL or a base64 encoded string.
  /// Note: You can use [StorageService.uploadUserProfilePicture]
  /// to upload the profile picture and use the returned URL as [profilePicture].
  /// * `profileAccessStrategy`: The new profile access strategy.
  /// If null, the profile access strategy will not be updated.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateProfile(
      {String? name,
      String? intro,
      String? profilePicture,
      ProfileAccessStrategy? profileAccessStrategy}) async {
    if ([name, intro, profilePicture, profileAccessStrategy].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateUserRequest(
        name: name,
        intro: intro,
        profilePicture: profilePicture,
        profileAccessStrategy: profileAccessStrategy));
    return n.toNullResponse();
  }

  /// Find user profiles.
  ///
  /// **Params**:
  /// * `userIds`: The target user IDs.
  /// * `lastUpdatedDate`: The last updated date of user profiles stored locally.
  /// The server will only return user profiles that are updated after [lastUpdatedDate].
  /// If null, all user profiles will be returned.
  ///
  /// **Returns**: A list of user profiles.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<UserInfo>>> queryUserProfiles(Set<Int64> userIds,
      {DateTime? lastUpdatedDate}) async {
    if (userIds.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final n = await _turmsClient.driver.send(QueryUserProfilesRequest(
        userIds: userIds, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.userInfosWithVersion.userInfos);
  }

  /// Search for user profiles.
  ///
  /// **Params**:
  /// * `name`: Search for user profiles whose name matches [name].
  /// * `highlight`: Whether to highlight the name.
  /// If true, the highlighted parts of the name will be paired with '\u0002' and '\u0003'.
  /// * `skip`: The number of user profiles to skip.
  /// * `limit`: The max number of user profiles to return.
  ///
  /// **Returns**: A list of user profiles sorted in descending relevance.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<UserInfo>>> searchUserProfiles(String name,
      {bool highlight = false, int? skip, int? limit}) async {
    if (name.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final n = await _turmsClient.driver.send(QueryUserProfilesRequest(
        name: name,
        fieldsToHighlight: highlight ? [1] : null,
        skip: skip,
        limit: limit));
    return n.toResponse((data) => data.userInfosWithVersion.userInfos);
  }

  /// Upsert user settings, such as "preferred language", "new message alert", etc.
  /// Note that only the settings specified in `turms.service.user.settings.allowed-settings` can be upserted.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.user-setting-updated.notify-requester-other-online-sessions` is true (true by default),
  ///   the server will send a user settings updated notification to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `settings`: The user settings to upsert.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// * If trying to update any existing immutable setting, throws [ResponseException] with the code [ResponseStatusCode.illegalArgument]
  /// * If trying to upsert an unknown setting and the server property `turms.service.user.settings.ignore-unknown-settings-on-upsert` is
  ///   false (false by default), throws [ResponseException] with the code [ResponseStatusCode.illegalArgument].
  Future<Response<void>> upsertUserSettings(Map<String, Value> settings) async {
    if (settings.isEmpty) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver
        .send(UpdateUserSettingsRequest(settings: settings));
    return n.toNullResponse();
  }

  /// Delete user settings.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.user-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
  ///   the server will send a user settings deleted notification to all other online sessions of the logged-in user actively.
  ///
  /// **Params**:
  /// * `names`: The names of the user settings to delete. If null, all deletable user settings will be deleted.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// * If trying to delete any non-deletable setting, throws [ResponseException] with the code [ResponseStatusCode.illegalArgument].
  Future<Response<void>> deleteUserSettings(Set<String> names) async {
    if (names.isEmpty) {
      return Response.nullValue();
    }
    final n =
        await _turmsClient.driver.send(DeleteUserSettingsRequest(names: names));
    return n.toNullResponse();
  }

  /// Find user settings.
  ///
  /// **Params**:
  /// * `names`: The names of the user settings to query. If null, all user settings will be returned.
  /// * `lastUpdatedDate`: The last updated date of user settings stored locally.
  /// The server will only return user settings if a setting has been updated after [lastUpdatedDate].
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserSettings?>> queryUserSettings(
      {Set<String>? names, DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryUserSettingsRequest(
        names: names, lastUpdatedDateStart: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasUserSettings() ? data.userSettings : null);
  }

  /// Find nearby users.
  ///
  /// **Params**:
  /// * `latitude`: The latitude.
  /// * `longitude`: The longitude.
  /// * `maxCount`: The max count.
  /// * `maxDistance`: The max distance.
  /// * `withCoordinates`: Whether to include coordinates.
  /// * `withDistance`: Whether to include distance.
  /// * `withUserInfo`: Whether to include user info.
  ///
  /// **Returns**: A list of nearby users.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<NearbyUser>>> queryNearbyUsers(
      double latitude, double longitude,
      {int? maxCount,
      int? maxDistance,
      bool? withCoordinates,
      bool? withDistance,
      bool? withUserInfo}) async {
    final n = await _turmsClient.driver.send(QueryNearbyUsersRequest(
        latitude: latitude,
        longitude: longitude,
        maxCount: maxCount,
        maxDistance: maxDistance,
        withCoordinates: withCoordinates,
        withDistance: withDistance,
        withUserInfo: withUserInfo));
    return n.toResponse((data) => data.nearbyUsers.nearbyUsers);
  }

  /// Find online status of users.
  ///
  /// **Params**:
  /// * `userIds`: The target user IDs.
  ///
  /// **Returns**: A list of online status of users.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<UserOnlineStatus>>> queryOnlineStatuses(
      Set<Int64> userIds) async {
    final n = await _turmsClient.driver
        .send(QueryUserOnlineStatusesRequest(userIds: userIds));
    return n.toResponse((data) => data.userOnlineStatuses.statuses);
  }

  // Relationship

  /// Find relationships.
  ///
  /// **Params**:
  /// * `relatedUserIds`: The target related user IDs.
  /// * `isBlocked`: Whether to query blocked relationships.
  /// If null, all relationships will be returned.
  /// If true, only blocked relationships will be returned.
  /// If false, only non-blocked relationships will be returned.
  /// * `groupIndexes`: The target group indexes for querying.
  /// * `lastUpdatedDate`: The last updated date of user relationships stored locally.
  /// The server will only return relationships that are created after [lastUpdatedDate].
  /// If null, all relationships will be returned.
  ///
  /// **Returns**: Relationships and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserRelationshipsWithVersion?>> queryRelationships(
      {Set<Int64>? relatedUserIds,
      bool? isBlocked,
      Set<int>? groupIndexes,
      DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryRelationshipsRequest(
        userIds: relatedUserIds,
        blocked: isBlocked,
        groupIndexes: groupIndexes,
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasUserRelationshipsWithVersion()
        ? data.userRelationshipsWithVersion
        : null);
  }

  /// Find related user IDs.
  ///
  /// **Params**:
  /// * `isBlocked`: Whether to query blocked relationships.
  /// If null, all relationships will be returned.
  /// If true, only blocked relationships will be returned.
  /// If false, only non-blocked relationships will be returned.
  /// * `groupIndexes`: The target group indexes for querying.
  /// * `lastUpdatedDate`: The last updated date of related user IDs stored locally.
  /// The server will only return related user IDs that are created after [lastUpdatedDate].
  /// If null, all related user IDs will be returned.
  ///
  /// **Returns**: Related user IDs and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<LongsWithVersion?>> queryRelatedUserIds(
      {bool? isBlocked,
      Set<int>? groupIndexes,
      DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryRelatedUserIdsRequest(
        blocked: isBlocked,
        groupIndexes: groupIndexes,
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse(
        (data) => data.hasLongsWithVersion() ? data.longsWithVersion : null);
  }

  /// Find friends.
  ///
  /// **Params**:
  /// * `groupIndexes`: The target group indexes for finding.
  /// * `lastUpdatedDate`: The last updated date of friends stored locally.
  /// The server will only return friends that are created after [lastUpdatedDate].
  /// If null, all friends will be returned.
  ///
  /// **Returns**: Friends and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserRelationshipsWithVersion?>> queryFriends(
          {Set<int>? groupIndexes, DateTime? lastUpdatedDate}) =>
      queryRelationships(
          isBlocked: false,
          groupIndexes: groupIndexes,
          lastUpdatedDate: lastUpdatedDate);

  /// Find blocked users.
  ///
  /// **Params**:
  /// * `groupIndexes`: The target group indexes for finding.
  /// * `lastUpdatedDate`: The last updated date of blocked users stored locally.
  /// The server will only return friends that are created after [lastUpdatedDate].
  /// If null, all blocked users will be returned.
  ///
  /// **Returns**: Blocked users and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserRelationshipsWithVersion?>> queryBlockedUsers(
          {Set<int>? groupIndexes, DateTime? lastUpdatedDate}) =>
      queryRelationships(
          isBlocked: true,
          groupIndexes: groupIndexes,
          lastUpdatedDate: lastUpdatedDate);

  /// Create a relationship.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
  ///   is true (false by default), the server will send a new relationship notification to [userId] actively.
  ///
  /// **Params**:
  /// * `userId`: The target user ID.
  /// * `isBlocked`: Whether to create a blocked relationship.
  /// If true, a blocked relationship will be created,
  /// and the target user will not be able to send messages to the logged-in user.
  /// * `groupIndex`: The target group index in which create the relationship.
  /// If null, the relationship will be created in the default group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> createRelationship(Int64 userId, bool isBlocked,
      {int? groupIndex}) async {
    final n = await _turmsClient.driver.send(CreateRelationshipRequest(
        userId: userId, blocked: isBlocked, groupIndex: groupIndex));
    return n.toNullResponse();
  }

  /// Create a friend (non-blocked) relationship.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
  ///   is true (false by default), the server will send a new relationship notification to [userId] actively.
  ///
  /// **Params**:
  /// * `userId`: The target user ID.
  /// * `groupIndex`: The target group index in which create the relationship.
  /// If null, the relationship will be created in the default group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> createFriendRelationship(Int64 userId,
          {int? groupIndex}) =>
      createRelationship(userId, false, groupIndex: groupIndex);

  /// Create a blocked user relationship.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
  ///   is true (false by default), the server will send a new relationship notification to [userId] actively.
  ///
  /// **Params**:
  /// * `userId`: The target user ID.
  /// * `groupIndex`: The target group index in which create the relationship.
  /// If null, the relationship will be created in the default group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> createBlockedUserRelationship(Int64 userId,
          {int? groupIndex}) =>
      createRelationship(userId, true, groupIndex: groupIndex);

  /// Delete a relationship.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a delete relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.group-deleted.notify-group-members`,
  ///   is true (true by default), the server will send a delete relationship notification to all group members in groups.
  ///
  /// **Params**:
  /// * `relatedUserId`: The target user ID.
  /// * `deleteGroupIndex`: The target group index in which delete the relationship.
  /// If null, the relationship will be deleted in all groups.
  /// * `targetGroupIndex`: TODO: not implemented yet.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteRelationship(Int64 relatedUserId,
      {int? deleteGroupIndex, int? targetGroupIndex}) async {
    final n = await _turmsClient.driver.send(DeleteRelationshipRequest(
        userId: relatedUserId,
        groupIndex: deleteGroupIndex,
        targetGroupIndex: targetGroupIndex));
    return n.toNullResponse();
  }

  /// Update a relationship.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
  ///   is true (false by default), the server will send a update relationship notification to [relatedUserId] actively.
  ///
  /// **Params**:
  /// * `relatedUserId`: The target user ID.
  /// * `isBlocked`: Whether to update a blocked relationship.
  /// If null, the relationship will not be updated.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateRelationship(Int64 relatedUserId,
      {bool? isBlocked, int? groupIndex}) async {
    if ([isBlocked, groupIndex].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateRelationshipRequest(
        userId: relatedUserId, blocked: isBlocked, newGroupIndex: groupIndex));
    return n.toNullResponse();
  }

  /// Send a friend request.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.friend-request-created.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a new friend request notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.friend-request-created.notify-friend-request-recipient`,
  ///   is true (true by default), the server will send a new friend request notification to [recipientId] actively.
  ///
  /// **Params**:
  /// * `recipientId`: The target user ID.
  /// * `content`: The content of the friend request.
  ///
  /// **Returns**: The request ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> sendFriendRequest(
      Int64 recipientId, String content) async {
    final n = await _turmsClient.driver.send(
        CreateFriendRequestRequest(recipientId: recipientId, content: content));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Delete/Recall a friend request.
  ///
  /// Authorization:
  /// * If the server property `turms.service.user.friend-request.allow-recall-pending-friend-request-by-sender`
  ///   is true (false by default), the logged-in user can recall pending friend requests sent by themselves.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.recallingFriendRequestIsDisabled].
  /// * If the logged-in user is not the sender of the friend request,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notSenderToRecallFriendRequest].
  /// * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
  ///   throws [ResponseException] with the code [ResponseStatusCode.recallNonPendingFriendRequest].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.friend-request-recalled.notify-requester-other-online-sessions`
  ///   is true (true by default), the server will send a delete friend request notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.friend-request-recalled.notify-friend-request-recipient`
  ///   is true (true by default), the server will send a delete friend request notification to the recipient of the friend request actively.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> deleteFriendRequest(Int64 requestId) async {
    final n = await _turmsClient.driver
        .send(DeleteFriendRequestRequest(requestId: requestId));
    return n.toResponse((data) {});
  }

  /// Reply to a friend request.
  ///
  /// If the logged-in user accepts a friend request sent by another user,
  /// the server will create a relationship between the logged-in user and the friend request sender.
  ///
  /// Authorization:
  /// * If the logged-in user is not the recipient of the friend request,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notRecipientToUpdateFriendRequest].
  /// * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
  ///   throws [ResponseException] with the code [ResponseStatusCode.updateNonPendingFriendRequest].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.friend-request-replied.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a reply friend request notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.friend-request-replied.notify-friend-request-sender`,
  ///   is true (true by default), the server will send a reply friend request notification to the friend request sender actively.
  ///
  /// **Params**:
  /// * `requestId`: The target friend request ID.
  /// * `responseAction`: The response action.
  /// * `reason`: The reason of the response.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> replyFriendRequest(
      Int64 requestId, ResponseAction responseAction,
      {String? reason}) async {
    final n = await _turmsClient.driver.send(UpdateFriendRequestRequest(
        requestId: requestId, responseAction: responseAction, reason: reason));
    return n.toResponse((data) {});
  }

  /// Find friend requests.
  ///
  /// **Params**:
  /// * `areSentByMe`: Whether to find the friend requests sent by the logged-in user.
  /// If true, find the friend requests sent by the logged-in user.
  /// If false, find the friend requests not sent to the logged-in user.
  /// * `lastUpdatedDate`: The last updated date of friend requests stored locally.
  /// The server will only return friend requests that are updated after [lastUpdatedDate].
  /// If null, all friend requests will be returned.
  ///
  /// **Returns**: Friend requests and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserFriendRequestsWithVersion?>> queryFriendRequests(
      bool areSentByMe,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryFriendRequestsRequest(
        areSentByMe: areSentByMe, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasUserFriendRequestsWithVersion()
        ? data.userFriendRequestsWithVersion
        : null);
  }

  /// Create a relationship group.
  ///
  /// **Params**:
  /// * `name`: The name of the group.
  ///
  /// **Returns**: The index of the created group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<Int64>> createRelationshipGroup(String name) async {
    final n = await _turmsClient.driver
        .send(CreateRelationshipGroupRequest(name: name));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Delete relationship groups.
  ///
  /// Notifications:
  /// 1. If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-requester-other-online-sessions`,
  /// is true (true by default), the server will send a delete relationship groups relationship notification to all other online sessions of the logged-in user actively.
  /// 2. If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-relationship-group-members`,
  /// is true (false by default), the server will send a delete relationship groups relationship notification to all group members in groups.
  ///
  /// @param groupIndex the target group index to delete.
  /// @param targetGroupIndex move the group members of [groupIndex] to [targetGroupIndex]
  ///        when the group is deleted.
  ///        If null, the group members of [groupIndex] will be moved to the default group.
  /// @throws ResponseException if an error occurs.
  Future<Response<void>> deleteRelationshipGroups(int groupIndex,
      {int? targetGroupIndex}) async {
    final n = await _turmsClient.driver.send(DeleteRelationshipGroupRequest(
        groupIndex: groupIndex, targetGroupIndex: targetGroupIndex));
    return n.toNullResponse();
  }

  /// Update a relationship group.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a updated relationship groups relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-relationship-group-members`,
  ///   is true (false by default), the server will send a updated relationship groups relationship notification to all group members in groups.
  ///
  /// **Params**:
  /// * `groupIndex`: The target group index.
  /// * `newName`: The new name of the group.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateRelationshipGroup(
      int groupIndex, String newName) async {
    final n = await _turmsClient.driver.send(UpdateRelationshipGroupRequest(
        groupIndex: groupIndex, newName: newName));
    return n.toNullResponse();
  }

  /// Find relationship groups.
  ///
  /// **Params**:
  /// * `lastUpdatedDate`: The last updated date of relationship groups stored locally.
  /// The server will only return relationship groups that are updated after [lastUpdatedDate].
  /// If null, all relationship groups will be returned.
  ///
  /// **Returns**: Relationship groups and the version.
  /// Note: The version can be used to update the last updated date stored locally.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<UserRelationshipGroupsWithVersion?>> queryRelationshipGroups(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryRelationshipGroupsRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasUserRelationshipGroupsWithVersion()
        ? data.userRelationshipGroupsWithVersion
        : null);
  }

  /// Move a related user to a group.
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`,
  ///   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
  ///   is true (false by default), the server will send a update relationship notification to [relatedUserId] actively.
  ///
  /// **Params**:
  /// * `relatedUserId`: The target user ID.
  /// * `groupIndex`: The target group index to which move the user.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> moveRelatedUserToGroup(
      Int64 relatedUserId, int groupIndex) async {
    final n = await _turmsClient.driver.send(UpdateRelationshipRequest(
        userId: relatedUserId, newGroupIndex: groupIndex));
    return n.toNullResponse();
  }

  /// Update the location of the logged-in user.
  ///
  /// Note:
  /// * [UserService.updateLocation] is different from
  ///   [MessageService.sendMessage] with records of location.
  ///   [UserService.updateLocation] sends the location of user to
  ///   the server only.
  ///   [MessageService.sendMessage] with records of location sends the user's location
  ///   to both server and its recipients.
  ///
  /// **Params**:
  /// * `latitude`: The latitude.
  /// * `longitude`: The longitude.
  /// * `details`: The location details
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateLocation(double latitude, double longitude,
      {Map<String, String>? details}) async {
    final n = await _turmsClient.driver.send(UpdateUserLocationRequest(
        latitude: latitude, longitude: longitude, details: details));
    return n.toNullResponse();
  }

  void _changeToOnline() {
    if (!isLoggedIn) {
      _turmsClient.driver.stateStore.isSessionOpen = true;
      for (final listener in _onOnlineListeners) {
        listener.call();
      }
    }
  }

  void _changeToOffline(SessionCloseInfo sessionCloseInfo) {
    if (isLoggedIn) {
      _userInfo?.onlineStatus = UserStatus.OFFLINE;
      _turmsClient.driver.stateStore.isSessionOpen = false;
      for (final listener in _onOfflineListeners) {
        listener.call(sessionCloseInfo);
      }
    }
  }
}
