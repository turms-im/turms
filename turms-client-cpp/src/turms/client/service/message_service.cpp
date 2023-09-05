#include "turms/client/service/message_service.h"

#include "turms/client/turms_client.h"

namespace turms {
namespace client {
namespace service {
MessageService::MessageService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
    turmsClient.driver().addNotificationListener(
        [weakThis = std::weak_ptr<MessageService>(shared_from_this())](
            const TurmsNotification& notification) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            const std::vector<MessageListener>& messageListeners = sharedThis->messageListeners_;
            if (messageListeners.empty() || !notification.has_relayed_request()) {
                return;
            }
            const TurmsRequest& relayedRequest = notification.relayed_request();
            if (!relayedRequest.has_create_message_request()) {
                return;
            }
            Message message = createMessageRequest2Message(notification.requester_id(),
                                                           relayedRequest.create_message_request());
            const MessageAddition& addition = sharedThis->parseMessageAddition(message);
            for (const auto& listener : messageListeners) {
                listener(message, addition);
            }
        });
}
auto MessageService::sendMessage(bool isGroupMessage,
                                 int64_t targetId,
                                 const boost::optional<time_point>& deliveryDate,
                                 const boost::optional<absl::string_view>& text,
                                 const std::vector<std::string>& records,
                                 const boost::optional<int>& burnAfter,
                                 const boost::optional<int64_t>& preMessageId)
    -> boost::future<model::Response<int64_t>> {
    if (!text && records.empty()) {
        throw ResponseException(model::ResponseStatusCode::kIllegalArgument,
                                R"("text" and "records" must not all be empty)");
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_message_request();
    if (isGroupMessage) {
        request->set_group_id(targetId);
    } else {
        request->set_recipient_id(targetId);
    }
    if (deliveryDate) {
        request->set_delivery_date(time::toInt64(*deliveryDate));
    }
    if (text) {
        request->set_text(*text);
    }
    if (!records.empty()) {
        request->mutable_records()->Add(records.cbegin(), records.cend());
    }
    if (burnAfter) {
        request->set_burn_after(*burnAfter);
    }
    if (preMessageId) {
        request->set_pre_message_id(*preMessageId);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto MessageService::forwardMessage(int64_t messageId, bool isGroupMessage, int64_t targetId)
    -> boost::future<model::Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_message_request();
    request->set_message_id(messageId);
    if (isGroupMessage) {
        request->set_group_id(targetId);
    } else {
        request->set_recipient_id(targetId);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto MessageService::updateSentMessage(int64_t messageId,
                                       const boost::optional<absl::string_view>& text,
                                       const std::vector<std::string>& records)
    -> boost::future<model::Response<void>> {
    if (!text && records.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_message_request();
    request->set_message_id(messageId);
    if (text) {
        request->set_text(*text);
    }
    if (!records.empty()) {
        request->mutable_records()->Add(records.cbegin(), records.cend());
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto MessageService::queryMessages(const std::unordered_set<int64_t>& ids,
                                   const boost::optional<bool>& areGroupMessages,
                                   const boost::optional<bool>& areSystemMessages,
                                   const std::unordered_set<int64_t>& fromIds,
                                   const boost::optional<time_point>& deliveryDateStart,
                                   const boost::optional<time_point>& deliveryDateEnd,
                                   int maxCount,
                                   const boost::optional<bool>& descending)
    -> boost::future<model::Response<std::vector<Message>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_messages_request();
    if (!ids.empty()) {
        request->mutable_ids()->Add(ids.cbegin(), ids.cend());
    }
    if (areGroupMessages) {
        request->set_are_group_messages(*areGroupMessages);
    }
    if (areSystemMessages) {
        request->set_are_system_messages(*areSystemMessages);
    }
    if (!fromIds.empty()) {
        request->mutable_from_ids()->Add(fromIds.cbegin(), fromIds.cend());
    }
    if (deliveryDateStart) {
        request->set_delivery_date_start(time::toInt64(*deliveryDateStart));
    }
    if (deliveryDateEnd) {
        request->set_delivery_date_end(time::toInt64(*deliveryDateEnd));
    }
    request->set_max_count(maxCount);
    if (descending) {
        request->set_descending(*descending);
    }
    request->set_with_total(false);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<Message>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& messages = data.messages().messages();
                    return std::vector<Message>{messages.cbegin(), messages.cend()};
                }};
        });
}

auto MessageService::queryMessagesWithTotal(const std::unordered_set<int64_t>& ids,
                                            const boost::optional<bool>& areGroupMessages,
                                            const boost::optional<bool>& areSystemMessages,
                                            const std::unordered_set<int64_t>& fromIds,
                                            const boost::optional<time_point>& deliveryDateStart,
                                            const boost::optional<time_point>& deliveryDateEnd,
                                            int maxCount,
                                            const boost::optional<bool>& descending)
    -> boost::future<model::Response<std::vector<MessagesWithTotal>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_messages_request();
    if (!ids.empty()) {
        request->mutable_ids()->Add(ids.cbegin(), ids.cend());
    }
    if (areGroupMessages) {
        request->set_are_group_messages(*areGroupMessages);
    }
    if (areSystemMessages) {
        request->set_are_system_messages(*areSystemMessages);
    }
    if (!fromIds.empty()) {
        request->mutable_from_ids()->Add(fromIds.cbegin(), fromIds.cend());
    }
    if (deliveryDateStart) {
        request->set_delivery_date_start(time::toInt64(*deliveryDateStart));
    }
    if (deliveryDateEnd) {
        request->set_delivery_date_end(time::toInt64(*deliveryDateEnd));
    }
    request->set_max_count(maxCount);
    if (descending) {
        request->set_descending(*descending);
    }
    request->set_with_total(true);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<MessagesWithTotal>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& messagesWithTotalList =
                        data.messages_with_total_list().messages_with_total_list();
                    return std::vector<MessagesWithTotal>{messagesWithTotalList.cbegin(),
                                                          messagesWithTotalList.cend()};
                }};
        });
}

auto MessageService::recallMessage(
    int64_t messageId, const std::chrono::time_point<std::chrono::system_clock>& recallDate)
    -> boost::future<model::Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_message_request();
    request->set_message_id(messageId);
    request->set_recall_date(time::toInt64(recallDate));
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto MessageService::isMentionEnabled() const noexcept -> bool {
    return isMentionUserEnabled;
}

auto MessageService::enableMention() noexcept -> void {
    isMentionUserEnabled = true;
}

auto MessageService::parseMessageAddition(const model::proto::Message& message)
    -> model::MessageAddition {
    std::set<int64_t> mentionedUserIds;
    if (isMentionUserEnabled) {
        mentionedUserIds = mentionedUserIdsParser_.has_value()
                               ? mentionedUserIdsParser_.get()(message)
                               : defaultMentionedUserIdsParser(message);
    }
    const boost::optional<User>& userInfo = turmsClient_.userService().userInfo();
    bool isMentioned =
        userInfo && mentionedUserIds.find((*userInfo).userId) != mentionedUserIds.end();
    const auto& records = message.records();
    bool isRecallMessage = false;
    if (message.is_system_message() && !records.empty()) {
        const auto& bytes = records[0];
        if (!bytes.empty() &&
            static_cast<int>(BuiltinSystemMessageType::kRecallMessage) == bytes[0]) {
            isRecallMessage = true;
        }
    }
    std::set<int64_t> recalledMessageIds;
    if (isRecallMessage) {
        for (int i = 1; i < records.size(); ++i) {
            const std::string& record = records[i];
            int64_t value = 0;
            for (int j = 0; j < 8; ++j) {
                value <<= 8;
                value |= static_cast<uint8_t>(record[j]);
            }
            recalledMessageIds.insert(value);
        }
    }
    return MessageAddition{isMentioned, mentionedUserIds, recalledMessageIds};
}

auto MessageService::createMessageRequest2Message(int64_t requesterId,
                                                  const model::proto::CreateMessageRequest& request)
    -> model::proto::Message {
    Message message;
    if (request.has_message_id()) {
        message.set_id(request.message_id());
    }
    message.set_is_system_message(request.is_system_message());
    message.set_delivery_date(request.delivery_date());
    if (request.has_text()) {
        message.set_text(request.text());
    }
    const auto& records = request.records();
    message.mutable_records()->Add(records.cbegin(), records.cend());
    message.set_sender_id(requesterId);
    if (request.has_group_id()) {
        message.set_group_id(request.group_id());
    }
    if (request.has_recipient_id()) {
        message.set_recipient_id(request.recipient_id());
    }
    return message;
}

auto MessageService::defaultMentionedUserIdsParser(const model::proto::Message& message)
    -> std::set<int64_t> {
    if (!message.has_text()) {
        return {};
    }
    const std::string& text = message.text();
    std::regex const regex{R"(@\{(\d+?)\})"};
    std::smatch matches;
    std::set<int64_t> userIds;
    auto it = text.cbegin();
    while (std::regex_search(it, text.cend(), matches, regex)) {
        try {
            userIds.insert(std::stoll(matches[1]));
        } catch (const std::invalid_argument&) {
        } catch (const std::out_of_range&) {
        }
        it = matches.suffix().first;
    }
    return userIds;
}
}  // namespace service
}  // namespace client
}  // namespace turms