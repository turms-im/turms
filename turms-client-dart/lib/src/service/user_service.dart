import 'package:fixnum/fixnum.dart';

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';
import '../util/system.dart';

class Location {
  final double longitude;
  final double latitude;

  Location(this.longitude, this.latitude);
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

  User? get userInfo => _userInfo;

  bool get isLoggedIn =>
      _userInfo != null && _userInfo?.onlineStatus != UserStatus.OFFLINE;

  void addOnOnlineListener(OnOnlineListener listener) =>
      _onOnlineListeners.add(listener);

  void addOnOfflineListener(OnOfflineListener listener) =>
      _onOfflineListeners.add(listener);

  void removeOnOnlineListener(OnOnlineListener listener) =>
      _onOnlineListeners.remove(listener);

  void removeOnOfflineListener(OnOfflineListener listener) =>
      _onOfflineListeners.remove(listener);

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

  Future<Response<void>> updateOnlineStatus(UserStatus onlineStatus) async {
    final n = await _turmsClient.driver
        .send(UpdateUserOnlineStatusRequest(userStatus: onlineStatus));
    _userInfo?.onlineStatus = onlineStatus;
    return n.toNullResponse();
  }

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

  Future<Response<void>> updatePassword(String password) async {
    final n =
        await _turmsClient.driver.send(UpdateUserRequest(password: password));
    if (_storePassword) {
      _userInfo?.password = password;
    }
    return n.toNullResponse();
  }

  Future<Response<void>> updateProfile(
      {String? name,
      String? intro,
      String? profilePicture,
      ProfileAccessStrategy? profileAccessStrategy}) async {
    if ([name, intro, profileAccessStrategy].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateUserRequest(
        name: name,
        intro: intro,
        profilePicture: profilePicture,
        profileAccessStrategy: profileAccessStrategy));
    return n.toNullResponse();
  }

  Future<Response<List<UserInfo>>> queryUserProfiles(Set<Int64> userIds,
      {DateTime? lastUpdatedDate}) async {
    if (userIds.isEmpty) {
      return Future.value(Response.emptyList());
    }
    final n = await _turmsClient.driver.send(QueryUserProfilesRequest(
        userIds: userIds, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.userInfosWithVersion.userInfos);
  }

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

  Future<Response<List<UserOnlineStatus>>> queryOnlineStatusesRequest(
      Set<Int64> userIds) async {
    final n = await _turmsClient.driver
        .send(QueryUserOnlineStatusesRequest(userIds: userIds));
    return n.toResponse((data) => data.userOnlineStatuses.statuses);
  }

  // Relationship

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

  Future<Response<UserRelationshipsWithVersion?>> queryFriends(
          {Set<int>? groupIndexes, DateTime? lastUpdatedDate}) =>
      queryRelationships(
          isBlocked: false,
          groupIndexes: groupIndexes,
          lastUpdatedDate: lastUpdatedDate);

  Future<Response<UserRelationshipsWithVersion?>> queryBlockedUsers(
          {Set<int>? groupIndexes, DateTime? lastUpdatedDate}) =>
      queryRelationships(
          isBlocked: true,
          groupIndexes: groupIndexes,
          lastUpdatedDate: lastUpdatedDate);

  Future<Response<void>> createRelationship(Int64 userId, bool isBlocked,
      {int? groupIndex}) async {
    final n = await _turmsClient.driver.send(CreateRelationshipRequest(
        userId: userId, blocked: isBlocked, groupIndex: groupIndex));
    return n.toNullResponse();
  }

  Future<Response<void>> createFriendRelationship(Int64 userId,
          {int? groupIndex}) =>
      createRelationship(userId, false, groupIndex: groupIndex);

  Future<Response<void>> createBlockedUserRelationship(Int64 userId,
          {int? groupIndex}) =>
      createRelationship(userId, true, groupIndex: groupIndex);

  Future<Response<void>> deleteRelationship(Int64 relatedUserId,
      {int? deleteGroupIndex, int? targetGroupIndex}) async {
    final n = await _turmsClient.driver.send(DeleteRelationshipRequest(
        userId: relatedUserId,
        groupIndex: deleteGroupIndex,
        targetGroupIndex: targetGroupIndex));
    return n.toNullResponse();
  }

  Future<Response<void>> updateRelationship(Int64 relatedUserId,
      {bool? isBlocked, int? groupIndex}) async {
    if ([isBlocked, groupIndex].areAllNull) {
      return Response.nullValue();
    }
    final n = await _turmsClient.driver.send(UpdateRelationshipRequest(
        userId: relatedUserId, blocked: isBlocked, newGroupIndex: groupIndex));
    return n.toNullResponse();
  }

  Future<Response<Int64>> sendFriendRequest(
      Int64 recipientId, String content) async {
    final n = await _turmsClient.driver.send(
        CreateFriendRequestRequest(recipientId: recipientId, content: content));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  Future<Response<void>> replyFriendRequest(
      Int64 requestId, ResponseAction responseAction,
      {String? reason}) async {
    final n = await _turmsClient.driver.send(UpdateFriendRequestRequest(
        requestId: requestId, responseAction: responseAction, reason: reason));
    return n.toResponse((data) {});
  }

  Future<Response<UserFriendRequestsWithVersion?>> queryFriendRequests(
      bool areSentByMe,
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryFriendRequestsRequest(
        areSentByMe: areSentByMe, lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasUserFriendRequestsWithVersion()
        ? data.userFriendRequestsWithVersion
        : null);
  }

  Future<Response<Int64>> createRelationshipGroup(String name) async {
    final n = await _turmsClient.driver
        .send(CreateRelationshipGroupRequest(name: name));
    return n.toResponse((data) => data.getLongOrThrow());
  }

  Future<Response<void>> deleteRelationshipGroups(int groupIndex,
      {int? targetGroupIndex}) async {
    final n = await _turmsClient.driver.send(DeleteRelationshipGroupRequest(
        groupIndex: groupIndex, targetGroupIndex: targetGroupIndex));
    return n.toNullResponse();
  }

  Future<Response<void>> updateRelationshipGroup(
      int groupIndex, String newName) async {
    final n = await _turmsClient.driver.send(UpdateRelationshipGroupRequest(
        groupIndex: groupIndex, newName: newName));
    return n.toNullResponse();
  }

  Future<Response<UserRelationshipGroupsWithVersion?>> queryRelationshipGroups(
      {DateTime? lastUpdatedDate}) async {
    final n = await _turmsClient.driver.send(QueryRelationshipGroupsRequest(
        lastUpdatedDate: lastUpdatedDate?.toInt64()));
    return n.toResponse((data) => data.hasUserRelationshipGroupsWithVersion()
        ? data.userRelationshipGroupsWithVersion
        : null);
  }

  Future<Response<void>> moveRelatedUserToGroup(
      Int64 relatedUserId, int groupIndex) async {
    final n = await _turmsClient.driver.send(UpdateRelationshipRequest(
        userId: relatedUserId, newGroupIndex: groupIndex));
    return n.toNullResponse();
  }

  /// updateLocation() in UserService is different from sendMessage()
  /// with records of location in MessageService
  /// updateLocation() in UserService sends the location of user to
  /// the server only.
  /// sendMessage() with records of location sends user's location to
  /// both server and its recipients.
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
