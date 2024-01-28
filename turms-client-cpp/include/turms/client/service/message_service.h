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

    /**
     * Add a message listener that will be triggered when a new message arrives.
     * Note: To listen to notifications excluding messages,
     * use NotificationService::addNotificationListener() instead.
     */
    template <typename T>
    auto addMessageListener(T&& listener) -> void {
        messageListeners_.emplace_back(std::forward<T>(listener));
    }

    /**
     * Send a message.
     *
     * Common Scenarios:
     * * A client can call addMessageListener() to listen to incoming new messages.
     *
     * Authorization:
     *
     * For private messages,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send messages to itself.
     *   Otherwise, throws ResponseException with the code ResponseStatusCode::kSendingMessagesToOneselfIsDisabled.
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send messages to strangers
     *   (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws ResponseException with the code ResponseStatusCode::kBlockedUserSendPrivateMessage.
     *
     * For group messages,
     * * If the logged-in user has blocked the target group,
     *   throws ResponseException with the code ResponseStatusCode::kBlockedUserSendGroupMessage.
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
     *   throws ResponseException with the code ResponseStatusCode::kNotSpeakableGroupGuestToSendMessage.
     * * If the logged-in user has been muted,
     *   throws ResponseException with the code ResponseStatusCode::kMutedGroupMemberSendMessage.
     * * If the target group has been deleted,
     *   throws ResponseException with the code ResponseStatusCode::kSendMessageToInactiveGroup.
     * * If the target group has been muted,
     *   throws ResponseException with the code ResponseStatusCode::kSendMessageToMutedGroup.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param isGroupMessage whether the message is a group message.
     * @param targetId The target ID.
     * If isGroupMessage is true, the target ID is the group ID.
     * If isGroupMessage is false, the target ID is the user ID.
     * @param deliveryDate The delivery date.
     * Note that deliveryDate will only work if the server property
     * `turms.service.message.time-type` is `client_time` (`local_server_time` by default).
     * @param text the message text.
     * text can be anything you want. e.g. Markdown, base64 encoded bytes.
     * Note that if text is null, records must not be null.
     * @param records the message records.
     * records can be anything you want. e.g. base64 encoded images (it is highly not recommended).
     * Note that if records is null, text must not be null.
     * @param burnAfter The burn after the specified time.
     * Note that Turms server and client do NOT implement the `burn after` feature,
     * and they just store and pass burnAfter between server and clients.
     * @param preMessageId The pre-message ID.
     * preMessageId is mainly used to arrange the order of messages on UI.
     * If what you want is to ensure every message will not be lost, even if the server crashes,
     * you can set the server property `turms.service.message.use-conversation-id` to true
     * (false by default).
     * @return the message ID.
     * @throws ResponseException if an error occurs.
     */
    auto sendMessage(bool isGroupMessage,
                     int64_t targetId,
                     const boost::optional<time_point>& deliveryDate = boost::none,
                     const boost::optional<absl::string_view>& text = boost::none,
                     const std::vector<std::string>& records = {},
                     const boost::optional<int>& burnAfter = boost::none,
                     const boost::optional<int64_t>& preMessageId = boost::none)
        -> boost::future<Response<int64_t>>;

    /**
     * Forward a message.
     * In other words, create and send a new message based on a existing message
     * to the target user or group.
     *
     * Authorization:
     * * If the logged-in user is not allowed to view the message with messageId,
     *   throws ResponseException with the code ResponseStatusCode::kNotMessageRecipientOrSenderToForwardMessage.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId the message ID for copying.
     * @param isGroupMessage whether the message is a group message.
     * @param targetId the target ID.
     * If isGroupMessage is true, the target ID is the group ID.
     * If isGroupMessage is false, the target ID is the user ID.
     * @return the message ID.
     * @throws ResponseException if an error occurs.
     */
    auto forwardMessage(int64_t messageId, bool isGroupMessage, int64_t targetId)
        -> boost::future<Response<int64_t>>;

    /**
     * Update a sent message.
     *
     * Authorization:
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (true by default), the logged-in user can update sent messages.
     *   Otherwise, throws ResponseException with the code ResponseStatusCode::kUpdatingMessageBySenderIsDisabled.
     * * If the message is not sent by the logged-in user,
     *   throws ResponseException with the code ResponseStatusCode::kNotSenderToUpdateMessage.
     * * If the message is group message, and is sent by the logged-in user but the group
     *   has been deleted,
     *   throws ResponseException with the code ResponseStatusCode::kUpdateMessageOfNonexistentGroup.
     * * If the message is group message, and the group type has disabled updating messages,
     *   throws ResponseException with the code ResponseStatusCode::kUpdatingGroupMessageBySenderIsDisabled.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-updated.notify-message-recipients`
     *   is true (true by default), a message update notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-updated.notify-requester-other-online-sessions`
     *   is true (true by default), a message update notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId The sent message ID.
     * @param text The new message text.
     * If null, the message text will not be changed.
     * @param records The new message records.
     * If null, the message records will not be changed.
     * @throws ResponseException if an error occurs.
     */
    auto updateSentMessage(int64_t messageId,
                           const boost::optional<absl::string_view>& text = boost::none,
                           const std::vector<std::string>& records = {})
        -> boost::future<Response<void>>;

    /**
     * Find messages.
     *
     * @param ids the message IDs for querying.
     * @param areGroupMessages whether the messages are group messages.
     * If the logged-in user is not a group member,
     * throws ResponseException with the code ResponseStatusCode::kNotGroupMemberToQueryGroupMessages.
     * TODO: guest users of some group types should be able to query messages.
     * @param areSystemMessages whether the messages are system messages.
     * @param fromIds the from IDs.
     * If areGroupMessages is true, the from ID is the group ID.
     * If areGroupMessages is false, the from ID is the user ID.
     * @param deliveryDateStart the start delivery date for querying.
     * @param deliveryDateEnd the end delivery date for querying.
     * @param maxCount the maximum count for querying.
     * @param descending whether the messages are sorted in descending order.
     * @return list of messages.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for ResponseStatusCode::kNotGroupMemberToQueryGroupMessages mentioned above.
     * @throws ResponseException if an error occurs.
     */
    auto queryMessages(const std::unordered_set<int64_t>& ids = {},
                       const boost::optional<bool>& areGroupMessages = boost::none,
                       const boost::optional<bool>& areSystemMessages = boost::none,
                       const std::unordered_set<int64_t>& fromIds = {},
                       const boost::optional<time_point>& deliveryDateStart = boost::none,
                       const boost::optional<time_point>& deliveryDateEnd = boost::none,
                       int maxCount = 50,
                       const boost::optional<bool>& descending = boost::none)
        -> boost::future<Response<std::vector<Message>>>;

    /**
     * Find the pair of messages and the total count for each conversation.
     *
     * @param ids the message IDs for querying.
     * @param areGroupMessages whether the messages are group messages.
     * @param areSystemMessages whether the messages are system messages.
     * If the logged-in user is not a group member,
     * throws ResponseException with the code ResponseStatusCode::kNotGroupMemberToQueryGroupMessages.
     * TODO: guest users of some group types should be able to query messages.
     * @param fromIds The from IDs.
     * If areGroupMessages is true, the from ID is the group ID.
     * If areGroupMessages is false, the from ID is the user ID.
     * @param deliveryDateStart The start delivery date for querying.
     * @param deliveryDateEnd The end delivery date for querying.
     * @param maxCount The maximum count for querying.
     * @param descending Whether the messages are sorted in descending order.
     * @return list of the pair of messages and the total count for each conversation.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for ResponseStatusCode::kNotGroupMemberToQueryGroupMessages mentioned above.
     * @throws ResponseException if an error occurs.
     */
    auto queryMessagesWithTotal(const std::unordered_set<int64_t>& ids = {},
                                const boost::optional<bool>& areGroupMessages = boost::none,
                                const boost::optional<bool>& areSystemMessages = boost::none,
                                const std::unordered_set<int64_t>& fromIds = {},
                                const boost::optional<time_point>& deliveryDateStart = boost::none,
                                const boost::optional<time_point>& deliveryDateEnd = boost::none,
                                int maxCount = 1,
                                const boost::optional<bool>& descending = boost::none)
        -> boost::future<Response<std::vector<MessagesWithTotal>>>;

    /**
     * Recall a message.
     *
     * Authorization:
     * * If the server property `turms.service.message.allow-recall-message`
     *   is true (true by default), the logged-in user can recall sent messages.
     *   Otherwise, throws ResponseException with the code ResponseStatusCode::kRecallingMessageIsDisabled.
     * * If the message does not exist,
     *   throws ResponseException with the code ResponseStatusCode::kRecallNonexistentMessage.
     * * If the message is group message, but the group has been deleted,
     *   throws ResponseException with the code ResponseStatusCode::kRecallMessageOfNonexistentGroup.
     *
     * Common Scenarios:
     * * A client can call addMessageListener() to listen to recalled messages.
     *   The listener will receive a non-empty MessageAddition::recalledMessageIds() when a message is recalled.
     *
     * @param messageId the message ID.
     * @param recallDate the recall date.
     * If null, the current date will be used.
     * @throws ResponseException if an error occurs.
     */
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