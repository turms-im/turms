#ifndef TURMS_CLIENT_SERVICE_MESSAGE_SERVICE_H
#define TURMS_CLIENT_SERVICE_MESSAGE_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <regex>
#include <unordered_set>

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/builtin_system_message_type.h"
#include "turms/client/model/message_addition.h"
#include "turms/client/model/notification_util.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"
#include "turms/client/model/response_status_code.h"
#include "turms/client/model/user.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class MessageService : private boost::noncopyable,
                       private std::enable_shared_from_this<MessageService> {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;

    using ResponseException = exception::ResponseException;

    using BuiltinSystemMessageType = model::BuiltinSystemMessageType;
    using MessageAddition = model::MessageAddition;
    using User = model::User;
    template <typename T>
    using Response = model::Response<T>;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using Message = model::proto::Message;
    using MessagesWithTotal = model::proto::MessagesWithTotal;

    using CreateMessageRequest = model::proto::CreateMessageRequest;

   public:
    using MentionedUserIdsParser = std::function<std::set<int64_t>(const Message&)>;
    using MessageListener = std::function<void(const Message&, const MessageAddition&)>;

    explicit MessageService(TurmsClient& turmsClient);

    template <typename T>
    auto addMessageListener(T&& listener) -> void {
        messageListeners_.emplace_back(std::forward<T>(listener));
    }

    auto sendMessage(bool isGroupMessage,
                     int64_t targetId,
                     const boost::optional<time_point>& deliveryDate = boost::none,
                     const boost::optional<absl::string_view>& text = boost::none,
                     const std::vector<std::string>& records = {},
                     const boost::optional<int>& burnAfter = boost::none,
                     const boost::optional<int64_t>& preMessageId = boost::none)
        -> boost::future<Response<int64_t>>;

    auto forwardMessage(int64_t messageId, bool isGroupMessage, int64_t targetId)
        -> boost::future<Response<int64_t>>;

    auto updateSentMessage(int64_t messageId,
                           const boost::optional<absl::string_view>& text = boost::none,
                           const std::vector<std::string>& records = {})
        -> boost::future<Response<void>>;

    auto queryMessages(const std::unordered_set<int64_t>& ids = {},
                       const boost::optional<bool>& areGroupMessages = boost::none,
                       const boost::optional<bool>& areSystemMessages = boost::none,
                       const std::unordered_set<int64_t>& fromIds = {},
                       const boost::optional<time_point>& deliveryDateStart = boost::none,
                       const boost::optional<time_point>& deliveryDateEnd = boost::none,
                       int maxCount = 50,
                       const boost::optional<bool>& descending = boost::none)
        -> boost::future<Response<std::vector<Message>>>;

    auto queryMessagesWithTotal(const std::unordered_set<int64_t>& ids = {},
                                const boost::optional<bool>& areGroupMessages = boost::none,
                                const boost::optional<bool>& areSystemMessages = boost::none,
                                const std::unordered_set<int64_t>& fromIds = {},
                                const boost::optional<time_point>& deliveryDateStart = boost::none,
                                const boost::optional<time_point>& deliveryDateEnd = boost::none,
                                int maxCount = 1,
                                const boost::optional<bool>& descending = boost::none)
        -> boost::future<Response<std::vector<MessagesWithTotal>>>;

    auto recallMessage(int64_t messageId,
                       const time_point& recallDate = std::chrono::system_clock::now())
        -> boost::future<Response<void>>;

    auto isMentionEnabled() const noexcept -> bool;

    auto enableMention() noexcept -> void;

    template <typename T>
    auto enableMention(T&& mentionedUserIdsParser) -> void {
        mentionedUserIdsParser_ = std::forward<T>(mentionedUserIdsParser);
        isMentionUserEnabled = true;
    }

    auto parseMessageAddition(const Message& message) -> MessageAddition;

    static auto createMessageRequest2Message(int64_t requesterId,
                                             const CreateMessageRequest& request) -> Message;

    static auto defaultMentionedUserIdsParser(const Message& message) -> std::set<int64_t>;

   private:
    TurmsClient& turmsClient_;
    std::vector<MessageListener> messageListeners_;
    boost::optional<MentionedUserIdsParser> mentionedUserIdsParser_;
    bool isMentionUserEnabled{false};
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_MESSAGE_SERVICE_H