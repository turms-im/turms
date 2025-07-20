#include "turms/client/service/conference_service.h"

#include "turms/client/time/time_util.h"
#include "turms/client/turms_client.h"

namespace turms::client::service {
ConferenceService::ConferenceService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
}

auto ConferenceService::createMeeting(const std::optional<int64_t>& userId,
                                      const std::optional<int64_t>& groupId,
                                      const std::optional<absl::string_view>& name,
                                      const std::optional<absl::string_view>& intro,
                                      const std::optional<absl::string_view>& password,
                                      const std::optional<time_point>& startDate) const
    -> boost::future<Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_meeting_request();
    if (userId) {
        request->set_user_id(*userId);
    }
    if (groupId) {
        request->set_group_id(*groupId);
    }
    if (name) {
        request->set_name(*name);
    }
    if (intro) {
        request->set_intro(*intro);
    }
    if (password) {
        request->set_password(*password);
    }
    if (startDate) {
        request->set_start_date(time::toInt64(*startDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto ConferenceService::cancelMeeting(const int64_t meetingId) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_meeting_request();
    request->set_id(meetingId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConferenceService::updateMeeting(int64_t meetingId,
                                      const std::optional<absl::string_view>& name,
                                      const std::optional<absl::string_view>& intro,
                                      const std::optional<absl::string_view>& password) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_meeting_request();
    request->set_id(meetingId);
    if (name) {
        request->set_name(*name);
    }
    if (intro) {
        request->set_intro(*intro);
    }
    if (password) {
        request->set_password(*password);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConferenceService::queryMeetings(const std::unordered_set<int64_t>& meetingIds,
                                      const std::unordered_set<int64_t>& creatorIds,
                                      const std::unordered_set<int64_t>& userIds,
                                      const std::unordered_set<int64_t>& groupIds,
                                      const std::optional<time_point>& creationDateStart,
                                      const std::optional<time_point>& creationDateEnd,
                                      const std::optional<int>& skip,
                                      const std::optional<int>& limit) const
    -> boost::future<Response<std::vector<Meeting>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_meetings_request();
    if (!meetingIds.empty()) {
        request->mutable_ids()->Add(meetingIds.cbegin(), meetingIds.cend());
    }
    if (!creatorIds.empty()) {
        request->mutable_creator_ids()->Add(creatorIds.cbegin(), creatorIds.cend());
    }
    if (!userIds.empty()) {
        request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    }
    if (!groupIds.empty()) {
        request->mutable_group_ids()->Add(groupIds.cbegin(), groupIds.cend());
    }
    if (creationDateStart) {
        request->set_creation_date_start(time::toInt64(*creationDateStart));
    }
    if (creationDateEnd) {
        request->set_creation_date_end(time::toInt64(*creationDateEnd));
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
            return Response<std::vector<Meeting>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& meetings = data.meetings().meetings();
                    return std::vector<Meeting>{meetings.cbegin(), meetings.cend()};
                }};
        });
}

auto ConferenceService::acceptMeetingInvitation(
    int64_t meetingId, const std::optional<absl::string_view>& password) const
    -> boost::future<Response<std::string>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_meeting_invitation_request();
    request->set_meeting_id(meetingId);
    if (password) {
        request->set_password(*password);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::string>{response.get(), [](const TurmsNotification::Data& data) {
                                             return data.string();
                                         }};
        });
}
}  // namespace turms::client::service
