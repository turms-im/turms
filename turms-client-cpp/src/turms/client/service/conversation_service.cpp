#include "turms/client/service/conversation_service.h"

#include "turms/client/turms_client.h"

namespace turms {
namespace client {
namespace service {
ConversationService::ConversationService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
}

auto ConversationService::queryPrivateConversations(const std::unordered_set<int64_t>& userIds)
    -> boost::future<Response<std::vector<PrivateConversation>>> {
    if (userIds.empty()) {
        return boost::make_ready_future<>(Response<PrivateConversation>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_conversations_request();
    request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<PrivateConversation>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& privateConversations = data.conversations().private_conversations();
                    return std::vector<PrivateConversation>{privateConversations.cbegin(),
                                                            privateConversations.cend()};
                }};
        });
}

auto ConversationService::queryGroupConversations(const std::unordered_set<int64_t>& groupIds)
    -> boost::future<Response<std::vector<GroupConversation>>> {
    if (groupIds.empty()) {
        return boost::make_ready_future<>(Response<GroupConversation>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_conversations_request();
    request->mutable_group_ids()->Add(groupIds.cbegin(), groupIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<GroupConversation>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& groupConversations = data.conversations().group_conversations();
                    return std::vector<GroupConversation>{groupConversations.cbegin(),
                                                          groupConversations.cend()};
                }};
        });
}

auto ConversationService::updatePrivateConversationReadDate(int64_t userId,
                                                            const time_point& readDate)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_conversation_request();
    request->set_user_id(userId);
    request->set_read_date(time::toInt64(readDate));
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::updateGroupConversationReadDate(int64_t groupId,
                                                          const time_point& readDate)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_conversation_request();
    request->set_group_id(groupId);
    request->set_read_date(time::toInt64(readDate));
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::upsertPrivateConversationSettings(
    int64_t userId, const std::unordered_map<std::string, Value>& settings)
    -> boost::future<Response<void>> {
    if (settings.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_conversation_settings_request();
    request->set_user_id(userId);
    request->mutable_settings()->insert(settings.cbegin(), settings.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::upsertGroupConversationSettings(
    int64_t groupId, const std::unordered_map<std::string, Value>& settings)
    -> boost::future<Response<void>> {
    if (settings.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_conversation_settings_request();
    request->set_group_id(groupId);
    request->mutable_settings()->insert(settings.cbegin(), settings.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::deleteConversationSettings(const std::unordered_set<int64_t>& userIds,
                                                     const std::unordered_set<int64_t>& groupIds,
                                                     const std::unordered_set<std::string>& names)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_conversation_settings_request();
    request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    request->mutable_group_ids()->Add(groupIds.cbegin(), groupIds.cend());
    request->mutable_names()->Add(names.cbegin(), names.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::queryConversationSettings(
    const std::unordered_set<int64_t>& userIds,
    const std::unordered_set<int64_t>& groupIds,
    const std::unordered_set<std::string>& names,
    const boost::optional<time_point>& lastUpdatedDate)
    -> boost::future<Response<std::vector<ConversationSettings>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_conversation_settings_request();
    request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    request->mutable_group_ids()->Add(groupIds.cbegin(), groupIds.cend());
    request->mutable_names()->Add(names.cbegin(), names.cend());
    if (lastUpdatedDate) {
        request->set_last_updated_date_start(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<ConversationSettings>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& conversationSettings =
                        data.conversation_settings_list().conversation_settings_list();
                    return std::vector<ConversationSettings>{conversationSettings.cbegin(),
                                                             conversationSettings.cend()};
                }};
        });
}

auto ConversationService::updatePrivateConversationTypingStatus(int64_t userId)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_typing_status_request();
    request->set_to_id(userId);
    request->set_is_group_message(false);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::updateGroupConversationTypingStatus(int64_t groupId)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_typing_status_request();
    request->set_to_id(groupId);
    request->set_is_group_message(true);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}
}  // namespace service
}  // namespace client
}  // namespace turms