#include "turms/client/service/conversation_service.h"

#include "turms/client/turms_client.h"

namespace turms {
namespace client {
namespace service {
ConversationService::ConversationService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
}

auto ConversationService::queryPrivateConversations(const std::unordered_set<int64_t>& targetIds)
    -> boost::future<Response<std::vector<PrivateConversation>>> {
    if (targetIds.empty()) {
        return boost::make_ready_future<>(Response<PrivateConversation>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_conversations_request();
    request->mutable_target_ids()->Add(targetIds.cbegin(), targetIds.cend());
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

auto ConversationService::updatePrivateConversationReadDate(
    int64_t targetId, const ConversationService::time_point& readDate)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_conversation_request();
    request->set_target_id(targetId);
    request->set_read_date(time::toInt64(readDate));
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto ConversationService::updateGroupConversationReadDate(
    int64_t groupId, const ConversationService::time_point& readDate)
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

auto ConversationService::updatePrivateConversationTypingStatus(int64_t targetId)
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_typing_status_request();
    request->set_to_id(targetId);
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