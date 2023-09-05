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
    using UserInfo = model::proto::UserInfo;
    using UserOnlineStatus = model::proto::UserOnlineStatus;
    using UserRelationshipGroupsWithVersion = model::proto::UserRelationshipGroupsWithVersion;
    using UserRelationshipsWithVersion = model::proto::UserRelationshipsWithVersion;
    using UserStatus = model::proto::UserStatus;

   public:
    UserService(TurmsClient& turmsClient);

    template <typename T>
    auto addOnOnlineListener(T&& listener) -> void {
        onOnlineListeners_.emplace_back(std::forward<T>(listener));
    }

    template <typename T>
    auto addOnOfflineListener(T&& listener) -> void {
        onOfflineListeners_.emplace_back(std::forward<T>(listener));
    }

    auto isLoggedIn() const -> bool;

    auto userInfo() const -> const boost::optional<User>&;

    auto login(int64_t userId,
               const boost::optional<std::string>& password = boost::none,
               DeviceType deviceType = DeviceType::DESKTOP,
               const std::unordered_map<std::string, std::string>& deviceDetails = {},
               const boost::optional<UserStatus>& onlineStatus = UserStatus::AVAILABLE,
               const boost::optional<UserLocation>& location = boost::none,
               bool storePassword = false) -> boost::future<Response<void>>;

    auto logout(bool disconnect = true) -> boost::future<Response<void>>;

    auto updateOnlineStatus(UserStatus onlineStatus) -> boost::future<Response<void>>;

    auto disconnectOnlineDevices(const std::unordered_set<DeviceType>& deviceTypes)
        -> boost::future<Response<void>>;

    auto updatePassword(const std::string& password) -> boost::future<Response<void>>;

    auto updateProfile(const boost::optional<absl::string_view>& name = boost::none,
                       const boost::optional<absl::string_view>& intro = boost::none,
                       const boost::optional<absl::string_view>& profilePicture = boost::none,
                       const boost::optional<ProfileAccessStrategy>& profileAccessStrategy =
                           boost::none) -> boost::future<Response<void>>;

    auto queryUserProfiles(const std::unordered_set<int64_t>& userIds,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<std::vector<UserInfo>>>;

    auto queryNearbyUsers(float latitude,
                          float longitude,
                          const boost::optional<int>& maxCount = boost::none,
                          const boost::optional<int>& maxDistance = boost::none,
                          const boost::optional<bool>& withCoordinates = boost::none,
                          const boost::optional<bool>& withDistance = boost::none,
                          const boost::optional<bool>& withUserInfo = boost::none)
        -> boost::future<Response<std::vector<NearbyUser>>>;

    auto queryOnlineStatusesRequest(const std::unordered_set<int64_t>& userIds)
        -> boost::future<Response<std::vector<UserOnlineStatus>>>;

    auto queryRelationships(const std::unordered_set<int64_t>& relatedUserIds = {},
                            const boost::optional<bool>& isBlocked = boost::none,
                            const std::unordered_set<int>& groupIndexes = {},
                            const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    auto queryRelatedUserIds(const boost::optional<bool>& isBlocked = boost::none,
                             const std::unordered_set<int>& groupIndexes = {},
                             const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    auto queryFriends(const std::unordered_set<int>& groupIndexes = {},
                      const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    auto queryBlockedUsers(const std::unordered_set<int>& groupIndexes = {},
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>>;

    auto createRelationship(int64_t userId,
                            bool isBlocked,
                            const boost::optional<int>& groupIndex = boost::none)
        -> boost::future<Response<void>>;

    auto deleteRelationshipGroups(int groupIndex,
                                  boost::optional<int> targetGroupIndex = boost::none)
        -> boost::future<Response<void>>;

    auto updateRelationshipGroup(int groupIndex, const absl::string_view& newName)
        -> boost::future<Response<void>>;

    auto queryRelationshipGroups(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserRelationshipGroupsWithVersion>>>;

    auto moveRelatedUserToGroup(int64_t relatedUserId, int groupIndex)
        -> boost::future<Response<void>>;

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