#include "turms/client/service/user_service.h"

#include "turms/client/turms_client.h"

namespace turms {
namespace client {
namespace service {
UserService::UserService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
    turmsClient_.driver().addOnDisconnectedListener(
        [weakThis = std::weak_ptr<UserService>(shared_from_this())](
            const boost::optional<std::exception>& e) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            sharedThis->changeToOffline(SessionCloseInfo{
                model::SessionCloseStatus::kConnectionClosed, boost::none, boost::none, e});
        });
    turmsClient_.driver().addNotificationListener(
        [weakThis = std::weak_ptr<UserService>(shared_from_this())](
            const TurmsNotification& notification) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            if (notification.has_close_status() && sharedThis->isLoggedIn()) {
                SessionCloseInfo info{notification.close_status()};
                if (notification.has_code()) {
                    info.businessStatus = notification.code();
                }
                if (notification.has_reason()) {
                    info.reason = notification.reason();
                }
                sharedThis->changeToOffline(info);
            }
        });
}

auto UserService::isLoggedIn() const -> bool {
    return userInfo_ && userInfo_->onlineStatus != UserStatus::OFFLINE;
}

auto UserService::userInfo() const -> const boost::optional<User>& {
    return userInfo_;
}

auto UserService::login(int64_t userId,
                        const boost::optional<std::string>& password,
                        UserService::DeviceType deviceType,
                        const std::unordered_map<std::string, std::string>& deviceDetails,
                        const boost::optional<UserStatus>& onlineStatus,
                        const boost::optional<UserLocation>& location,
                        bool storePassword) -> boost::future<Response<void>> {
    User user{};
    user.userId = userId;
    if (storePassword) {
        user.password = password;
    }
    user.deviceType = deviceType;
    user.onlineStatus = onlineStatus;
    user.location = location;

    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_session_request();
    request->set_version(1);
    request->set_user_id(userId);
    if (password) {
        request->set_password(*password);
    }
    request->set_device_type(deviceType);
    if (!deviceDetails.empty()) {
        request->mutable_device_details()->insert(deviceDetails.cbegin(), deviceDetails.cend());
    }
    if (onlineStatus) {
        request->set_user_status(*onlineStatus);
    }
    if (location) {
        auto* mutableLocation = request->mutable_location();
        mutableLocation->set_longitude(location->longitude);
        mutableLocation->set_latitude(location->latitude);
    }
    auto& turmsDriver = turmsClient_.driver();
    if (turmsDriver.isConnected()) {
        return turmsDriver.send(turmsRequest)
            .then([this, storePassword, user = std::move(user)](
                      boost::future<TurmsNotification> response) {
                const TurmsNotification& notification = response.get();
                changeToOnline();
                storePassword_ = storePassword;
                userInfo_ = user;
                return Response<void>{notification};
            });
    } else {
        return turmsDriver.connect()
            .then([weakThis = std::weak_ptr<UserService>(shared_from_this()),
                   turmsRequest = std::move(turmsRequest),
                   storePassword,
                   user = std::move(user)](boost::future<void> response) mutable {
                auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    throw std::runtime_error("The user service has been destroyed");
                }
                response.get();
                return sharedThis->turmsClient_.driver()
                    .send(turmsRequest)
                    .then([weakThis = std::weak_ptr<UserService>(sharedThis),
                           storePassword,
                           user = std::move(user)](boost::future<TurmsNotification> response) {
                        auto sharedThis = weakThis.lock();
                        if (sharedThis == nullptr) {
                            throw std::runtime_error("The user service has been destroyed");
                        }
                        const TurmsNotification& notification = response.get();
                        sharedThis->changeToOnline();
                        sharedThis->storePassword_ = storePassword;
                        sharedThis->userInfo_ = user;
                        return Response<void>{notification};
                    });
            })
            .unwrap();
    }
}

auto UserService::logout(bool disconnect) -> boost::future<Response<void>> {
    if (disconnect) {
        return turmsClient_.driver().disconnect().then(
            [weakThis =
                 std::weak_ptr<UserService>(shared_from_this())](boost::future<void> response) {
                auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    return Response<void>{};
                }
                sharedThis->changeToOffline(
                    SessionCloseInfo{model::SessionCloseStatus::kDisconnectedByClient});
                return Response<void>{};
            });
    } else {
        TurmsRequest turmsRequest;
        turmsRequest.delete_session_request();
        return turmsClient_.driver()
            .send(turmsRequest)
            .then([](boost::future<TurmsNotification> response) {
                try {
                    const TurmsNotification& notification = response.get();
                    return Response<void>{notification};
                } catch (const ResponseException& e) {
                    if (e.code() == model::ResponseStatusCode::kClientSessionHasBeenClosed) {
                        // TODO
                        return Response<void>{};
                    } else {
                        throw e;
                    }
                }
            });
    }
}

auto UserService::updateOnlineStatus(UserService::UserStatus onlineStatus)
    -> boost::future<Response<void>> {
    if (onlineStatus == UserStatus::OFFLINE) {
        return boost::make_exceptional_future<Response<void>>(ResponseException{
            model::ResponseStatusCode::kIllegalArgument,
            "The online status must not be " + model::proto::UserStatus_Name(UserStatus::OFFLINE)});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_online_status_request();
    request->set_user_status(onlineStatus);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([=](boost::future<TurmsNotification> response) {
            const TurmsNotification& notification = response.get();
            if (userInfo_) {
                userInfo_->onlineStatus = onlineStatus;
            }
            return Response<void>{notification};
        });
}

auto UserService::disconnectOnlineDevices(const std::unordered_set<DeviceType>& deviceTypes)
    -> boost::future<Response<void>> {
    if (deviceTypes.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_online_status_request();
    request->set_user_status(UserStatus::OFFLINE);
    request->mutable_device_types()->Add(deviceTypes.cbegin(), deviceTypes.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::updatePassword(const std::string& password) -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_request();
    request->set_password(password);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([=](boost::future<TurmsNotification> response) {
            const TurmsNotification& notification = response.get();
            if (storePassword_ && userInfo_) {
                userInfo_->password = password;
            }
            return Response<void>{notification};
        });
}

auto UserService::updateProfile(const boost::optional<absl::string_view>& name,
                                const boost::optional<absl::string_view>& intro,
                                const boost::optional<absl::string_view>& profilePicture,
                                const boost::optional<ProfileAccessStrategy>& profileAccessStrategy)
    -> boost::future<Response<void>> {
    if (!name && !intro && !profileAccessStrategy) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_request();
    if (name) {
        request->set_name(*name);
    }
    if (intro) {
        request->set_intro(*intro);
    }
    if (profilePicture) {
        request->set_profile_picture(*profilePicture);
    }
    if (profileAccessStrategy) {
        request->set_profile_access_strategy(*profileAccessStrategy);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::queryUserProfiles(const std::unordered_set<int64_t>& userIds,
                                    const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<std::vector<UserInfo>>> {
    if (userIds.empty()) {
        return boost::make_ready_future<>(Response<UserInfo>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_user_profiles_request();
    request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<UserInfo>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& userInfos = data.user_infos_with_version().user_infos();
                    return std::vector<UserInfo>{userInfos.cbegin(), userInfos.cend()};
                }};
        });
}

auto UserService::searchUserProfiles(const std::string& name,
                                     bool highlight,
                                     const boost::optional<int>& skip,
                                     const boost::optional<int>& limit)
    -> boost::future<Response<std::vector<UserInfo>>> {
    if (name.empty()) {
        return boost::make_ready_future<>(Response<UserInfo>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_user_profiles_request();
    request->set_name(name);
    if (highlight) {
        request->add_fields_to_highlight(1);
    }
    if (skip) {
        request->set_skip(*skip);
    }
    if (limit) {
        request->set_limit(*limit);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<UserInfo>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& userInfos = data.user_infos_with_version().user_infos();
                    return std::vector<UserInfo>{userInfos.cbegin(), userInfos.cend()};
                }};
        });
}

auto UserService::upsertUserSettings(const std::unordered_map<std::string, Value>& settings)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_settings_request();
    request->mutable_settings()->insert(settings.cbegin(), settings.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::deleteUserSettings(const std::unordered_set<std::string>& names)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_user_settings_request();
    request->mutable_names()->Add(names.cbegin(), names.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
};

auto UserService::queryUserSettings(const std::unordered_set<std::string>& names,
                                    const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserSettings>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_user_settings_request();
    request->mutable_names()->Add(names.cbegin(), names.cend());
    if (lastUpdatedDate) {
        request->set_last_updated_date_start(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<boost::optional<UserSettings>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    return data.has_user_settings()() ? boost::make_optional(data.user_settings())
                                                      : boost::none;
                }};
        });
}

auto UserService::queryNearbyUsers(float latitude,
                                   float longitude,
                                   const boost::optional<int>& maxCount,
                                   const boost::optional<int>& maxDistance,
                                   const boost::optional<bool>& withCoordinates,
                                   const boost::optional<bool>& withDistance,
                                   const boost::optional<bool>& withUserInfo)
    -> boost::future<Response<std::vector<NearbyUser>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_nearby_users_request();
    request->set_latitude(latitude);
    request->set_longitude(longitude);
    if (maxCount) {
        request->set_max_count(*maxCount);
    }
    if (maxDistance) {
        request->set_max_distance(*maxDistance);
    }
    if (withCoordinates) {
        request->set_with_coordinates(*withCoordinates);
    }
    if (withDistance) {
        request->set_with_distance(*withDistance);
    }
    if (withUserInfo) {
        request->set_with_user_info(*withUserInfo);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<NearbyUser>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& nearbyUsers = data.nearby_users().nearby_users();
                    return std::vector<NearbyUser>{nearbyUsers.cbegin(), nearbyUsers.cend()};
                }};
        });
}

auto UserService::queryOnlineStatuses(const std::unordered_set<int64_t>& userIds)
    -> boost::future<Response<std::vector<UserOnlineStatus>>> {
    if (userIds.empty()) {
        return boost::make_ready_future<>(Response<UserOnlineStatus>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_user_online_statuses_request();
    request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<UserOnlineStatus>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& statuses = data.user_online_statuses().statuses();
                    return std::vector<UserOnlineStatus>{statuses.cbegin(), statuses.cend()};
                }};
        });
}

auto UserService::queryRelationships(const std::unordered_set<int64_t>& relatedUserIds,
                                     const boost::optional<bool>& isBlocked,
                                     const std::unordered_set<int>& groupIndexes,
                                     const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_relationships_request();
    if (!relatedUserIds.empty()) {
        request->mutable_user_ids()->Add(relatedUserIds.cbegin(), relatedUserIds.cend());
    }
    if (isBlocked) {
        request->set_blocked(*isBlocked);
    }
    if (!groupIndexes.empty()) {
        request->mutable_group_indexes()->Add(groupIndexes.cbegin(), groupIndexes.cend());
    }
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<boost::optional<UserRelationshipsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_relationships_with_version()) {
                        return boost::make_optional(data.user_relationships_with_version());
                    }
                    return boost::optional<UserRelationshipsWithVersion>{};
                }};
        });
}

auto UserService::queryRelatedUserIds(const boost::optional<bool>& isBlocked,
                                      const std::unordered_set<int>& groupIndexes,
                                      const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<LongsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_related_user_ids_request();
    if (isBlocked) {
        request->set_blocked(*isBlocked);
    }
    if (!groupIndexes.empty()) {
        request->mutable_group_indexes()->Add(groupIndexes.cbegin(), groupIndexes.cend());
    }
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<boost::optional<LongsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_longs_with_version()) {
                        return boost::make_optional(data.longs_with_version());
                    }
                    return boost::optional<LongsWithVersion>{};
                }};
        });
}

auto UserService::queryFriends(const std::unordered_set<int>& groupIndexes,
                               const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>> {
    return queryRelationships({}, false, groupIndexes, lastUpdatedDate);
}

auto UserService::queryBlockedUsers(const std::unordered_set<int>& groupIndexes,
                                    const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserRelationshipsWithVersion>>> {
    return queryRelationships({}, true, groupIndexes, lastUpdatedDate);
}

auto UserService::createRelationship(int64_t userId,
                                     bool isBlocked,
                                     const boost::optional<int>& groupIndex)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_relationship_request();
    request->set_user_id(userId);
    request->set_blocked(isBlocked);
    if (groupIndex) {
        request->set_group_index(*groupIndex);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::createFriendRelationship(int64_t userId, const boost::optional<int>& groupIndex)
    -> boost::future<Response<void>> {
    return createRelationship(userId, false, groupIndex);
}

auto UserService::createBlockedUserRelationship(int64_t userId,
                                                const boost::optional<int>& groupIndex)
    -> boost::future<Response<void>> {
    return createRelationship(userId, true, groupIndex);
}

auto UserService::deleteRelationship(int64_t relatedUserId,
                                     const boost::optional<int>& deleteGroupIndex,
                                     const boost::optional<int>& targetGroupIndex)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_relationship_request();
    request->set_user_id(relatedUserId);
    if (deleteGroupIndex) {
        request->set_group_index(*deleteGroupIndex);
    }
    if (targetGroupIndex) {
        request->set_target_group_index(*targetGroupIndex);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::updateRelationship(int64_t relatedUserId,
                                     const boost::optional<bool>& isBlocked,
                                     const boost::optional<int>& groupIndex)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_relationship_request();
    request->set_user_id(relatedUserId);
    if (!isBlocked && !groupIndex) {
        return boost::make_ready_future<>(Response<void>{});
    }
    if (isBlocked) {
        request->set_blocked(*isBlocked);
    }
    if (groupIndex) {
        request->set_new_group_index(*groupIndex);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::sendFriendRequest(int64_t recipientId, const absl::string_view& content)
    -> boost::future<Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_friend_request_request();
    request->set_recipient_id(recipientId);
    request->set_content(content);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto UserService::deleteFriendRequest(int64_t requestId) -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_friend_request_request();
    request->set_request_id(requestId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::replyFriendRequest(int64_t requestId,
                                     ResponseAction responseAction,
                                     const boost::optional<absl::string_view>& reason)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_friend_request_request();
    request->set_request_id(requestId);
    request->set_response_action(responseAction);
    if (reason) {
        request->set_reason(*reason);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::queryFriendRequests(bool areSentByMe,
                                      const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserFriendRequestsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_friend_requests_request();
    request->set_are_sent_by_me(areSentByMe);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<boost::optional<UserFriendRequestsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_friend_requests_with_version()) {
                        return boost::make_optional(data.user_friend_requests_with_version());
                    }
                    return boost::optional<UserFriendRequestsWithVersion>{};
                }};
        });
}

auto UserService::createRelationshipGroup(const absl::string_view& name)
    -> boost::future<Response<int>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_relationship_group_request();
    request->set_name(name);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int>{
                response.get(), [](const TurmsNotification::Data& data) {
                    return static_cast<int>(model::notification::getLongOrThrow(data));
                }};
        });
}

auto UserService::deleteRelationshipGroups(int groupIndex, boost::optional<int> targetGroupIndex)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_relationship_group_request();
    request->set_group_index(groupIndex);
    if (targetGroupIndex) {
        request->set_target_group_index(*targetGroupIndex);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::updateRelationshipGroup(int groupIndex, const absl::string_view& newName)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_relationship_group_request();
    request->set_group_index(groupIndex);
    request->set_new_name(newName);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::queryRelationshipGroups(const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<boost::optional<UserRelationshipGroupsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_relationship_groups_request();
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<boost::optional<UserRelationshipGroupsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_relationship_groups_with_version()) {
                        return boost::make_optional(data.user_relationship_groups_with_version());
                    }
                    return boost::optional<UserRelationshipGroupsWithVersion>{};
                }};
        });
}

auto UserService::moveRelatedUserToGroup(int64_t relatedUserId, int groupIndex)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_relationship_request();
    request->set_user_id(relatedUserId);
    request->set_new_group_index(groupIndex);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::updateLocation(float latitude,
                                 float longitude,
                                 const std::unordered_map<std::string, std::string>& details)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_location_request();
    request->set_latitude(latitude);
    request->set_longitude(longitude);
    request->mutable_details()->insert(details.cbegin(), details.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::changeToOnline() -> void {
    if (!isLoggedIn()) {
        turmsClient_.driver().stateStore().isSessionOpen = true;
        for (const auto& listener : onOnlineListeners_) {
            listener();
        }
    }
}

auto UserService::changeToOffline(const SessionCloseInfo& sessionCloseInfo) -> void {
    if (isLoggedIn()) {
        if (userInfo_) {
            userInfo_->onlineStatus = UserStatus::OFFLINE;
        }
        turmsClient_.driver().stateStore().isSessionOpen = false;
        for (const auto& listener : onOfflineListeners_) {
            listener(sessionCloseInfo);
        }
    }
}
}  // namespace service
}  // namespace client
}  // namespace turms