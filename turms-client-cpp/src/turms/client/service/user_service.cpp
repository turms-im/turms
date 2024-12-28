#include "turms/client/service/user_service.h"

#include "turms/client/model/session_close_status.h"
#include "turms/client/time/time_util.h"
#include "turms/client/turms_client.h"

namespace turms::client::service {
UserService::UserService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
    turmsClient_.driver().addOnDisconnectedListener(
        [weakThis = std::weak_ptr(shared_from_this())](const std::optional<std::exception>& e) {
            const auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            sharedThis->changeToOffline(SessionCloseInfo{
                model::SessionCloseStatus::kConnectionClosed, std::nullopt, std::nullopt, e});
        });
    turmsClient_.driver().addNotificationListener(
        [weakThis = std::weak_ptr(shared_from_this())](const TurmsNotification& notification) {
            const auto sharedThis = weakThis.lock();
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

auto UserService::userInfo() const -> const std::optional<User>& {
    return userInfo_;
}

auto UserService::login(int64_t userId,
                        const std::optional<std::string>& password,
                        DeviceType deviceType,
                        const std::unordered_map<std::string, std::string>& deviceDetails,
                        const std::optional<UserStatus>& onlineStatus,
                        const std::optional<UserLocation>& location,
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
            .then([weakThis = std::weak_ptr(shared_from_this()),
                   storePassword,
                   user = std::move(user)](boost::future<TurmsNotification> response) {
                const TurmsNotification& notification = response.get();
                if (const auto sharedThis = weakThis.lock()) {
                    sharedThis->changeToOnline();
                    sharedThis->storePassword_ = storePassword;
                    sharedThis->userInfo_ = user;
                }
                return Response<void>{notification};
            });
    } else {
        return turmsDriver.connect()
            .then([weakThis = std::weak_ptr(shared_from_this()),
                   turmsRequest = std::move(turmsRequest),
                   storePassword,
                   user = std::move(user)](boost::future<void> response) mutable {
                const auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    throw std::runtime_error("The user service has been destroyed");
                }
                response.get();
                return sharedThis->turmsClient_.driver()
                    .send(turmsRequest)
                    .then([weakThis = std::weak_ptr(sharedThis),
                           storePassword,
                           user = std::move(user)](boost::future<TurmsNotification> response) {
                        const auto sharedThis = weakThis.lock();
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
            [weakThis = std::weak_ptr(shared_from_this())](boost::future<void> response) {
                const auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    return Response<void>{};
                }
                sharedThis->changeToOffline(
                    SessionCloseInfo{model::SessionCloseStatus::kDisconnectedByClient});
                return Response<void>{};
            });
    } else {
        TurmsRequest turmsRequest;
        turmsRequest.mutable_delete_session_request();
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

auto UserService::updateOnlineStatus(UserStatus onlineStatus) -> boost::future<Response<void>> {
    if (onlineStatus == UserStatus::OFFLINE) {
        return boost::make_exceptional_future<Response<void>>(ResponseException{
            model::ResponseStatusCode::kIllegalArgument,
            "The online status must not be " + UserStatus_Name(UserStatus::OFFLINE)});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_user_online_status_request();
    request->set_user_status(onlineStatus);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([weakThis = std::weak_ptr(shared_from_this()),
               onlineStatus](boost::future<TurmsNotification> response) {
            const TurmsNotification& notification = response.get();
            if (const auto sharedThis = weakThis.lock()) {
                if (sharedThis->userInfo_) {
                    sharedThis->userInfo_->onlineStatus = onlineStatus;
                }
            }
            return Response<void>{notification};
        });
}

auto UserService::disconnectOnlineDevices(const std::unordered_set<DeviceType>& deviceTypes) const
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
        .then([weakThis = std::weak_ptr(shared_from_this()),
               password](boost::future<TurmsNotification> response) {
            const TurmsNotification& notification = response.get();
            if (const auto sharedThis = weakThis.lock()) {
                if (sharedThis->storePassword_ && sharedThis->userInfo_) {
                    sharedThis->userInfo_->password = password;
                }
            }
            return Response<void>{notification};
        });
}

auto UserService::updateProfile(const std::optional<absl::string_view>& name,
                                const std::optional<absl::string_view>& intro,
                                const std::optional<absl::string_view>& profilePicture,
                                const std::optional<ProfileAccessStrategy>& profileAccessStrategy,
                                const std::unordered_map<std::string, Value>& userDefinedAttributes)
    const -> boost::future<Response<void>> {
    if (!name && !intro && !profilePicture && !profileAccessStrategy &&
        userDefinedAttributes.empty()) {
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
    if (!userDefinedAttributes.empty()) {
        request->mutable_user_defined_attributes()->insert(userDefinedAttributes.cbegin(),
                                                           userDefinedAttributes.cend());
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto UserService::queryUserProfiles(const std::unordered_set<int64_t>& userIds,
                                    const std::optional<time_point>& lastUpdatedDate) const
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
                                     const std::optional<int>& skip,
                                     const std::optional<int>& limit) const
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

auto UserService::upsertUserSettings(const std::unordered_map<std::string, Value>& settings) const
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

auto UserService::deleteUserSettings(const std::unordered_set<std::string>& names) const
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
                                    const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserSettings>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_user_settings_request();
    request->mutable_names()->Add(names.cbegin(), names.cend());
    if (lastUpdatedDate) {
        request->set_last_updated_date_start(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<UserSettings>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    return data.has_user_settings() ? std::optional(data.user_settings())
                                                    : std::nullopt;
                }};
        });
}

auto UserService::queryNearbyUsers(float latitude,
                                   float longitude,
                                   const std::optional<int>& maxCount,
                                   const std::optional<int>& maxDistance,
                                   const std::optional<bool>& withCoordinates,
                                   const std::optional<bool>& withDistance,
                                   const std::optional<bool>& withUserInfo) const
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

auto UserService::queryOnlineStatuses(const std::unordered_set<int64_t>& userIds) const
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
                                     const std::optional<bool>& isBlocked,
                                     const std::unordered_set<int>& groupIndexes,
                                     const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserRelationshipsWithVersion>>> {
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
            return Response<std::optional<UserRelationshipsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_relationships_with_version()) {
                        return std::optional(data.user_relationships_with_version());
                    }
                    return std::optional<UserRelationshipsWithVersion>{};
                }};
        });
}

auto UserService::queryRelatedUserIds(const std::optional<bool>& isBlocked,
                                      const std::unordered_set<int>& groupIndexes,
                                      const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<LongsWithVersion>>> {
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
            return Response<std::optional<LongsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_longs_with_version()) {
                        return std::optional(data.longs_with_version());
                    }
                    return std::optional<LongsWithVersion>{};
                }};
        });
}

auto UserService::queryFriends(const std::unordered_set<int>& groupIndexes,
                               const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserRelationshipsWithVersion>>> {
    return queryRelationships({}, false, groupIndexes, lastUpdatedDate);
}

auto UserService::queryBlockedUsers(const std::unordered_set<int>& groupIndexes,
                                    const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserRelationshipsWithVersion>>> {
    return queryRelationships({}, true, groupIndexes, lastUpdatedDate);
}

auto UserService::createRelationship(int64_t userId,
                                     bool isBlocked,
                                     const std::optional<int>& groupIndex) const
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

auto UserService::createFriendRelationship(int64_t userId,
                                           const std::optional<int>& groupIndex) const
    -> boost::future<Response<void>> {
    return createRelationship(userId, false, groupIndex);
}

auto UserService::createBlockedUserRelationship(int64_t userId,
                                                const std::optional<int>& groupIndex) const
    -> boost::future<Response<void>> {
    return createRelationship(userId, true, groupIndex);
}

auto UserService::deleteRelationship(int64_t relatedUserId,
                                     const std::optional<int>& deleteGroupIndex,
                                     const std::optional<int>& targetGroupIndex) const
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
                                     const std::optional<bool>& isBlocked,
                                     const std::optional<int>& groupIndex) const
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

auto UserService::sendFriendRequest(int64_t recipientId, absl::string_view content) const
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

auto UserService::deleteFriendRequest(int64_t requestId) const -> boost::future<Response<void>> {
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
                                     const std::optional<absl::string_view>& reason) const
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
                                      const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserFriendRequestsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_friend_requests_request();
    request->set_are_sent_by_me(areSentByMe);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<UserFriendRequestsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_friend_requests_with_version()) {
                        return std::optional(data.user_friend_requests_with_version());
                    }
                    return std::optional<UserFriendRequestsWithVersion>{};
                }};
        });
}

auto UserService::createRelationshipGroup(absl::string_view name) const
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

auto UserService::deleteRelationshipGroups(int groupIndex,
                                           const std::optional<int>& targetGroupIndex) const
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

auto UserService::updateRelationshipGroup(int groupIndex, absl::string_view newName) const
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

auto UserService::queryRelationshipGroups(const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserRelationshipGroupsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_relationship_groups_request();
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<UserRelationshipGroupsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_relationship_groups_with_version()) {
                        return std::optional(data.user_relationship_groups_with_version());
                    }
                    return std::optional<UserRelationshipGroupsWithVersion>{};
                }};
        });
}

auto UserService::moveRelatedUserToGroup(int64_t relatedUserId, int groupIndex) const
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
                                 const std::unordered_map<std::string, std::string>& details) const
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

auto UserService::changeToOnline() const -> void {
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
}  // namespace turms::client::service
