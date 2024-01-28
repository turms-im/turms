#ifndef TURMS_CLIENT_SERVICE_USER_SERVICE_H
#define TURMS_CLIENT_SERVICE_USER_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <unordered_set>

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/proto/constant/device_type.pb.h"
#include "turms/client/model/proto/constant/profile_access_strategy.pb.h"
#include "turms/client/model/proto/model/user/nearby_user.pb.h"
#include "turms/client/model/proto/model/user/user_info.pb.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"
#include "turms/client/model/session_close_info.h"
#include "turms/client/model/session_close_status.h"
#include "turms/client/model/user.h"
#include "turms/client/model/user_location.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class UserService : private boost::noncopyable, private std::enable_shared_from_this<UserService> {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;

    using ResponseException = exception::ResponseException;

    template <typename T>
    using Response = model::Response<T>;
    using SessionCloseInfo = model::SessionCloseInfo;
    using User = model::User;
    using UserLocation = model::UserLocation;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using DeviceType = model::proto::DeviceType;
    using LongsWithVersion = model::proto::LongsWithVersion;
    using NearbyUser = model::proto::NearbyUser;
    using ProfileAccessStrategy = model::proto::ProfileAccessStrategy;
    using ResponseAction = model::proto::ResponseAction;
    using UserFriendRequestsWithVersion = model::proto::UserFriendRequestsWithVersion;
    using UserInfo = model::proto::UserInfo;
    using UserOnlineStatus = model::proto::UserOnlineStatus;
    using UserRelationshipGroupsWithVersion = model::proto::UserRelationshipGroupsWithVersion;
    using UserRelationshipsWithVersion = model::proto::UserRelationshipsWithVersion;
    using UserStatus = model::proto::UserStatus;

   public:
    UserService(TurmsClient& turmsClient);

    /**
     * Add an online listener that will be called when the user becomes online.
     * A session is considered online when it has a TCP connection with the server,
     * and the user is logged in by login.
     */
    template <typename T>
    auto addOnOnlineListener(T&& listener) -> void {
        onOnlineListeners_.emplace_back(std::forward<T>(listener));
    }

    /**
     * Add an offline listener that will be called when the user becomes offline.
     * A session is considered offline when it has no TCP connection with the server,
     * or has a connected TCP connection with the server, but the user is not logged in by login.
     */
    template <typename T>
    auto addOnOfflineListener(T&& listener) -> void {
        onOfflineListeners_.emplace_back(std::forward<T>(listener));
    }

    auto isLoggedIn() const -> bool;

    auto userInfo() const -> const boost::optional<User>&;

    /**
     * Log in.
     *
     * * If the underlying TCP connection is not connected,
     *   the method will connect it first under the hood.
     * * If log in successfully, the session is considered online.
     *   And the listener registered by addOnOnlineListener() will be called.
     *
     * Related docs:
     * * Turms Identity and Access Management(https://turms-im.github.io/docs/server/module/identity-access-management.html)
     *
     * @param userId the user ID
     * @param password the user password.
     * @param deviceType the device type.
     * If null, the detected device type will be used.
     * Note: The device types of online session that conflicts with deviceType
     * will be closed by the server if logged in successfully.
     * @param deviceDetails the device details.
     * Some plugins use this to pass additional information about the device.
     * e.g. Push notification token.
     * @param onlineStatus the online status.
     * @param location the location of the user.
     * @param storePassword whether to store the password in userInfo.
     * @throws ResponseException if an error occurs.
     * 1. If the client is not compatible with the server, throws
     * with the code ResponseStatusCode::kUnsupportedClientVersion.
     * 2. Depending on the server property `turms.gateway.simultaneous-login.strategy`,
     * throws with the code ResponseStatusCode::kLoginFromForbiddenDeviceType
     * if the specified device type is forbidden.
     * 3. If provided credentials are invalid,
     * throws with the code ResponseStatusCode::kLoginAuthenticationFailed.
     */
    auto login(int64_t userId,
               const boost::optional<std::string>& password = boost::none,
               DeviceType deviceType = DeviceType::DESKTOP,
               const std::unordered_map<std::string, std::string>& deviceDetails = {},
               const boost::optional<UserStatus>& onlineStatus = UserStatus::AVAILABLE,
               const boost::optional<UserLocation>& location = boost::none,
               bool storePassword = false) -> boost::future<Response<void>>;

    /**
     * Log out.
     *
     * After logging out, the session is considered offline.
     * And the listener registered by addOnOfflineListener() will be called.
     *
     * @param disconnect whether to close the underlying TCP connection immediately
     * rather than sending a delete session request first and then closing the connection.
     * @throws ResponseException if an error occurs.
     */
    auto logout(bool disconnect = true) -> boost::future<Response<void>>;

    /**
     * Update the online status of the logged-in user.
     *
     * Notifications:
     * * If the server property `turms.service.notification.user-online-status-updated.notify-requester-other-online-sessions`
     *   is true （true by default）,
     *   the server will send an update online status notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.user-online-status-updated.notify-non-blocked-related-users`,
     *   is true (false by default),
     *   the server will send an update online status notification to all non-blocked related users of the logged-in user actively.
     *
     * @param onlineStatus the new online status.
     * @throws ResponseException if an error occurs.
     */
    auto updateOnlineStatus(UserStatus onlineStatus) -> boost::future<Response<void>>;

    /**
     * Disconnect the online devices of the logged-in user.
     *
     * If the specified device types are not online, nothing will happen and
     * no exception will be thrown.
     *
     * @param deviceTypes the device types to disconnect.
     * @throws ResponseException if an error occurs.
     */
    auto disconnectOnlineDevices(const std::unordered_set<DeviceType>& deviceTypes)
        -> boost::future<Response<void>>;

    /**
     * Update the password of the logged-in user.
     *
     * @param password the new password.
     * @throws ResponseException if an error occurs.
     */
    auto updatePassword(const std::string& password) -> boost::future<Response<void>>;

    /**
     * Update the profile of the logged-in user.
     *
     * @param name the new name.
     * If null, the name will not be updated.
     * @param intro the new intro.
     * If null, the intro will not be updated.
     * @param profilePicture the new profile picture.
     * If null, the profile picture will not be updated.
     * The profile picture can be anything you want.
     * e.g. an image URL or a base64 encoded string.
     * Note: You can use StorageService::uploadUserProfilePicture()
     * to upload the profile picture and use the returned URL as profilePicture.
     * @param profileAccessStrategy the new profile access strategy.
     * If null, the profile access strategy will not be updated.
     * @throws ResponseException if an error occurs.
     */
    auto updateProfile(const boost::optional<absl::string_view>& name = boost::none,
                       const boost::optional<absl::string_view>& intro = boost::none,
                       const boost::optional<absl::string_view>& profilePicture = boost::none,
                       const boost::optional<ProfileAccessStrategy>& profileAccessStrategy =
                           boost::none) -> boost::future<Response<void>>;

    /**
     * Find user profiles.
     *
     * @param userIds the target user IDs.
     * @param lastUpdatedDate the last updated date of user profiles stored locally.
     * The server will only return user profiles that are updated after lastUpdatedDate.
     * If null, all user profiles will be returned.
     * @return a list of user profiles.
     * @throws ResponseException if an error occurs.
     */
    auto queryUserProfiles(const std::unordered_set<int64_t>& userIds,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<std::vector<UserInfo>>>;

    /**
     * Find nearby users.
     *
     * @param latitude the latitude.
     * @param longitude the longitude.
     * @param maxCount the max count.
     * @param maxDistance the max distance.
     * @param withCoordinates whether to include coordinates.
     * @param withDistance whether to include distance.
     * @param withUserInfo whether to include user info.
     * @return a list of nearby users.
     * @throws ResponseException if an error occurs.
     */
    auto queryNearbyUsers(float latitude,
                          float longitude,
                          const boost::optional<int>& maxCount = boost::none,
                          const boost::optional<int>& maxDistance = boost::none,
                          const boost::optional<bool>& withCoordinates = boost::none,
                          const boost::optional<bool>& withDistance = boost::none,
                          const boost::optional<bool>& withUserInfo = boost::none)
        -> boost::future<Response<std::vector<NearbyUser>>>;

    /**
     * Find online status of users.
     *
     * @param userIds the target user IDs.
     * @return a list of online status of users.
     * @throws ResponseException if an error occurs.
     */
    auto queryOnlineStatusesRequest(const std::unordered_set<int64_t>& userIds)
        -> boost::future<Response<std::vector<UserOnlineStatus>>>;

    /**
     * Find relationships.
     *
     * @param relatedUserIds the target related user IDs.
     * @param isBlocked whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes the target group indexes for querying.
     * @param lastUpdatedDate the last updated date of user relationships stored locally.
     * The server will only return relationships that are created after lastUpdatedDate.
     * If null, all relationships will be returned.
     * @return relationships and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryRelationships(const std::unordered_set<int64_t>& relatedUserIds = {},
                            const boost::optional<bool>& isBlocked = boost::none,
                            const std::unordered_set<int>& groupIndexes = {},
                            const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    /**
     * Find related user IDs.
     *
     * @param isBlocked whether to query blocked relationships.
     * If null, all relationships will be returned.
     * If true, only blocked relationships will be returned.
     * If false, only non-blocked relationships will be returned.
     * @param groupIndexes the target group indexes for querying.
     * @param lastUpdatedDate the last updated date of related user IDs stored locally.
     * The server will only return related user IDs that are created after lastUpdatedDate.
     * If null, all related user IDs will be returned.
     * @return related user IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryRelatedUserIds(const boost::optional<bool>& isBlocked = boost::none,
                             const std::unordered_set<int>& groupIndexes = {},
                             const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    /**
     * Find friends.
     *
     * @param groupIndexes the target group indexes for finding.
     * @param lastUpdatedDate the last updated date of friends stored locally.
     * The server will only return friends that are created after lastUpdatedDate.
     * If null, all friends will be returned.
     * @return friends and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryFriends(const std::unordered_set<int>& groupIndexes = {},
                      const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    /**
     * Find blocked users.
     *
     * @param groupIndexes the target group indexes for finding.
     * @param lastUpdatedDate the last updated date of blocked users stored locally.
     * The server will only return friends that are created after lastUpdatedDate.
     * If null, all blocked users will be returned.
     * @return blocked users and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryBlockedUsers(const std::unordered_set<int>& groupIndexes = {},
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    /**
     * Create a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to userId actively.
     *
     * @param userId the target user ID.
     * @param isBlocked whether to create a blocked relationship.
     * If true, a blocked relationship will be created,
     * and the target user will not be able to send messages to the logged-in user.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    auto createRelationship(int64_t userId,
                            bool isBlocked,
                            const boost::optional<int>& groupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Create a friend (non-blocked) relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to userId actively.
     *
     * @param userId the target user ID.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    auto createFriendRelationship(int64_t userId,
                                  const boost::optional<int>& groupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Create a blocked user relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-member-added.notify-new-relationship-group-member`,
     *   is true (false by default), the server will send a new relationship notification to userId actively.
     *
     * @param userId the target user ID.
     * @param groupIndex the target group index in which create the relationship.
     * If null, the relationship will be created in the default group.
     * @throws ResponseException if an error occurs.
     */
    auto createBlockedUserRelationship(int64_t userId,
                                       const boost::optional<int>& groupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Delete a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`,
     *   is true (true by default), the server will send a delete relationship notification to all group members in groups.
     *
     * @param relatedUserId the target user ID.
     * @param deleteGroupIndex the target group index in which delete the relationship.
     * If null, the relationship will be deleted in all groups.
     * @param targetGroupIndex TODO: not implemented yet.
     * @throws ResponseException if an error occurs.
     */
    auto deleteRelationship(int64_t relatedUserId,
                            const boost::optional<int>& deleteGroupIndex = boost::none,
                            const boost::optional<int>& targetGroupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Update a relationship.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to relatedUserId actively.
     *
     * @param relatedUserId the target user ID.
     * @param isBlocked whether to update a blocked relationship.
     * If null, the relationship will not be updated.
     * @throws ResponseException if an error occurs.
     */
    auto updateRelationship(int64_t relatedUserId,
                            const boost::optional<bool>& isBlocked = boost::none,
                            const boost::optional<int>& groupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Send a friend request.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-created.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a new friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-created.notify-friend-request-recipient`,
     *   is true (true by default), the server will send a new friend request notification to recipientId actively.
     *
     * @param recipientId the target user ID.
     * @param content the content of the friend request.
     * @return the request ID.
     * @throws ResponseException if an error occurs.
     */
    auto sendFriendRequest(int64_t recipientId, const absl::string_view& content)
        -> boost::future<Response<int64_t>>;

    /**
     * Delete/Recall a friend request.
     *
     * Authorization:
     * * If the server property `turms.service.user.friend-request.allow-recall-pending-friend-request-by-sender`
     *   is true (false by default), the logged-in user can recall pending friend requests sent by themselves.
     *   Otherwise, throws ResponseException with the code ResponseStatusCode::kRecallingFriendRequestIsDisabled.
     * * If the logged-in user is not the sender of the friend request,
     *   throws ResponseException with the code ResponseStatusCode::kNotSenderToRecallFriendRequest.
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws ResponseException with the code ResponseStatusCode::kRecallNonPendingFriendRequest.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-recalled.notify-friend-request-recipient`
     *   is true (true by default), the server will send a delete friend request notification to the recipient of the friend request actively.
     *
     * @throws ResponseException if an error occurs.
     */
    auto deleteFriendRequest(int64_t requestId) -> boost::future<Response<void>>;

    /**
     * Reply to a friend request.
     *
     * If the logged-in user accepts a friend request sent by another user,
     * the server will create a relationship between the logged-in user and the friend request sender.
     *
     * Authorization:
     * * If the logged-in user is not the recipient of the friend request,
     *   throws ResponseException with the code ResponseStatusCode::kNotRecipientToUpdateFriendRequest.
     * * If the friend request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws ResponseException with the code ResponseStatusCode::kUpdateNonPendingFriendRequest.
     *
     * Notifications:
     * * If the server property `turms.service.notification.friend-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply friend request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.friend-request-replied.notify-friend-request-sender`,
     *   is true (true by default), the server will send a reply friend request notification to the friend request sender actively.
     *
     * @param requestId the target friend request ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    auto replyFriendRequest(int64_t requestId,
                            ResponseAction responseAction,
                            const boost::optional<absl::string_view>& reason = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Find friend requests.
     *
     * @param areSentByMe whether to find the friend requests sent by the logged-in user.
     * If true, find the friend requests sent by the logged-in user.
     * If false, find the friend requests not sent to the logged-in user.
     * @param lastUpdatedDate the last updated date of friend requests stored locally.
     * The server will only return friend requests that are updated after lastUpdatedDate.
     * If null, all friend requests will be returned.
     * @return friend requests and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryFriendRequests(bool areSentByMe,
                             const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserFriendRequestsWithVersion>>>;

    /**
     * Create a relationship group.
     *
     * @param name the name of the group.
     * @return the index of the created group.
     * @throws ResponseException if an error occurs.
     */
    auto createRelationshipGroup(const absl::string_view& name) -> boost::future<Response<int>>;

    /**
     * Delete relationship groups.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-deleted.notify-relationship-group-members`,
     *   is true (false by default), the server will send a delete relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex the target group index to delete.
     * @param targetGroupIndex move the group members of groupIndex to targetGroupIndex
     * when the group is deleted.
     * If null, the group members of groupIndex will be moved to the default group.
     * @throws ResponseException if an error occurs.
     */
    auto deleteRelationshipGroups(int groupIndex,
                                  boost::optional<int> targetGroupIndex = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Update a relationship group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a updated relationship groups relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-group-updated.notify-relationship-group-members`,
     *   is true (false by default), the server will send a updated relationship groups relationship notification to all group members in groups.
     *
     * @param groupIndex the target group index.
     * @param newName the new name of the group.
     * @throws ResponseException if an error occurs.
     */
    auto updateRelationshipGroup(int groupIndex, const absl::string_view& newName)
        -> boost::future<Response<void>>;

    /**
     * Find relationship groups.
     *
     * @param lastUpdatedDate the last updated date of relationship groups stored locally.
     * The server will only return relationship groups that are updated after lastUpdatedDate.
     * If null, all relationship groups will be returned.
     * @return relationship groups and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryRelationshipGroups(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipGroupsWithVersion>>>;

    /**
     * Move a related user to a group.
     *
     * Notifications:
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update relationship notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.one-sided-relationship-updated.notify-related-user`,
     *   is true (false by default), the server will send a update relationship notification to relatedUserId actively.
     *
     * @param relatedUserId the target user ID.
     * @param groupIndex the target group index to which move the user.
     * @throws ResponseException if an error occurs.
     */
    auto moveRelatedUserToGroup(int64_t relatedUserId, int groupIndex)
        -> boost::future<Response<void>>;

    /**
     * Update the location of the logged-in user.
     *
     * Note:
     * * UserService::updateLocation() is different from
     *   MessageService::sendMessage() with records of location.
     *   UserService::updateLocation() sends the location of user to
     *   the server only.
     *   MessageService::sendMessage() with records of location sends the user's location
     *   to both server and its recipients.
     *
     * @param latitude the latitude.
     * @param longitude the longitude.
     * @param details the location details
     * @throws ResponseException if an error occurs.
     */
    auto updateLocation(float latitude,
                        float longitude,
                        const std::unordered_map<std::string, std::string>& details = {})
        -> boost::future<Response<void>>;

   private:
    TurmsClient& turmsClient_;
    boost::optional<User> userInfo_;
    bool storePassword_{false};
    std::list<std::function<void()>> onOnlineListeners_;
    std::list<std::function<void(const SessionCloseInfo&)>> onOfflineListeners_;

    auto changeToOnline() -> void;

    auto changeToOffline(const SessionCloseInfo& sessionCloseInfo) -> void;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_USER_SERVICE_H