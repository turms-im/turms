#ifndef TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H
#define TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <unordered_set>

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class ConversationService : private boost::noncopyable {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;
    using ResponseException = exception::ResponseException;

    template <typename T>
    using Response = model::Response<T>;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using ConversationSettings = model::proto::ConversationSettings;
    using GroupConversation = model::proto::GroupConversation;
    using PrivateConversation = model::proto::PrivateConversation;

    using Value = model::proto::Value;

   public:
    explicit ConversationService(TurmsClient& turmsClient);

    /**
     * Find private conversations between the logged-in user and another user.
     *
     * Common Scenarios:
     * * If you want to find all private conversations between
     *   the current logged-in user and other users,
     *   you can call methods like UserService::queryRelatedUserIds(),
     *   UserService::queryFriends() to get all user IDs first,
     *   and pass these user IDs as userIds to get all private conversations.
     * * The returned PrivateConversation does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   MessageService::queryMessages() and MessageService::queryMessagesWithTotal().
     *
     * @param userIds the target user IDs.
     * If empty, an empty list of conversations is returned.
     * @return a list of private conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws ResponseException if an error occurs.
     */
    auto queryPrivateConversations(const std::unordered_set<int64_t>& userIds)
        -> boost::future<Response<std::vector<PrivateConversation>>>;

    /**
     * Find group conversations in which the logged-in user is a member.
     *
     * Common Scenarios:
     * * If you want to find all group conversations between
     *   the current logged-in user and groups in which the logged-in user is a member,
     *   you can call methods like GroupService::queryJoinedGroupIds(),
     *   GroupService::queryJoinedGroupInfos() to get all group IDs first,
     *   and pass these group IDs as groupIds to get all group conversations.
     * * GroupConversation does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   MessageService::queryMessages() and MessageService::queryMessagesWithTotal().
     *
     * @param groupIds the target group IDs.
     * If empty, an empty list of conversations is returned.
     * @return a list of group conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws ResponseException if an error occurs.
     */
    auto queryGroupConversations(const std::unordered_set<int64_t>& groupIds)
        -> boost::future<Response<std::vector<GroupConversation>>>;

    /**
     * Update the read date of the target private conversation.
     *
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the
     * server), you can call queryPrivateConversations().
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws ResponseException with the code
     * ResponseStatusCode::kUpdatingReadDateIsDisabled.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.private-conversation-read-date-updated.notify-contact` is true
     * (false by default), the server will send a read date update notification to the participant
     * actively.
     * * If the server property
     * `turms.service.notification.private-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the
     * logged-in user actively.
     *
     * @param userId the target user ID.
     * @param readDate the read date.
     * If null, the current time is used.
     * @throws ResponseException if an error occurs.
     */
    auto updatePrivateConversationReadDate(
        int64_t userId, const time_point& readDate = std::chrono::system_clock::now())
        -> boost::future<Response<void>>;

    /**
     * Update the read date of the target group conversation.
     *
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the
     * server), you can call queryGroupConversations().
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws ResponseException with the code
     * ResponseStatusCode::kUpdatingReadDateIsDisabled.
     * * If the target group doesn't exist, throws ResponseException with the code
     * ResponseStatusCode::kUpdatingReadDateOfNonexistentGroupConversation.
     * * If the target group has disabled read receipts, throws ResponseException with the code
     * ResponseStatusCode::kUpdatingReadDateIsDisabledByGroup.
     * * If the logged-in user is not a member of the target group, throws ResponseException with
     * the code ResponseStatusCode::kNotGroupMemberToUpdateReadDateOfGroupConversation.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-conversation-read-date-updated.notify-other-group-members`
     *   is true (false by default),
     *   the server will send a read date update notification to all participants actively.
     * * If the server property
     * `turms.service.notification.group-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the
     * logged-in user actively.
     *
     * @param groupId the target group ID.
     * @param readDate the read date.
     * If null, the current time is used.
     * @throws ResponseException if an error occurs.
     */
    auto updateGroupConversationReadDate(
        int64_t groupId, const time_point& readDate = std::chrono::system_clock::now())
        -> boost::future<Response<void>>;

    /**
     * Upsert private conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in
     * `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.private-conversation-setting-updated.notify-requester-other-online-sessions`
     * is true (true by default), the server will send a conversation settings updated notification
     * to all other online sessions of the logged-in user actively.
     *
     * @param userId the target user ID.
     * @param settings the private conversation settings to upsert.
     * @throws ResponseException if an error occurs.
     * * If trying to update any existing immutable setting, throws ResponseException with the code
     * ResponseStatusCode::kIllegalArgument
     * * If trying to upsert an unknown setting and the server property
     * `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is false (false by
     * default), throws ResponseException with the code ResponseStatusCode::kIllegalArgument.
     */
    auto upsertPrivateConversationSettings(int64_t userId,
                                           const std::unordered_map<std::string, Value>& settings)
        -> boost::future<Response<void>>;

    /**
     * Upsert group conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in
     * `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-conversation-setting-updated.notify-requester-other-online-sessions`
     * is true (true by default), the server will send a conversation settings updated notification
     * to all other online sessions of the logged-in user actively.
     *
     * @param groupId the target group ID.
     * @param settings the group conversation settings to upsert.
     * @throws ResponseException if an error occurs.
     * * If trying to update any existing immutable setting, throws ResponseException with the code
     * ResponseStatusCode::kIllegalArgument
     * * If trying to upsert an unknown setting and the server property
     * `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is false (false by
     * default), throws ResponseException with the code ResponseStatusCode::kIllegalArgument.
     */
    auto upsertGroupConversationSettings(int64_t groupId,
                                         const std::unordered_map<std::string, Value>& settings)
        -> boost::future<Response<void>>;

    /**
     * Delete conversation settings.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.private-conversation-setting-deleted.notify-requester-other-online-sessions`
     * is true (true by default), and a private conversation setting is deleted, the server will
     * send a user settings deleted notification to all other online sessions of the logged-in user
     * actively.
     * * If the server property
     * `turms.service.notification.group-conversation-setting-deleted.notify-requester-other-online-sessions`
     * is true (true by default), and a group conversation setting is deleted, the server will send
     * a group settings deleted notification to all other online sessions of the logged-in user
     * actively.
     *
     * @param userIds the target user IDs. If both userIds and groupIds are null, all deletable
     * conversation settings will be deleted.
     * @param groupIds the target group IDs. If both userIds and groupIds are null, all deletable
     * conversation settings will be deleted.
     * @param names the names of the conversation settings to delete. If null, all deletable
     * conversation settings will be deleted.
     * @throws ResponseException if an error occurs.
     * * If trying to delete any non-deletable setting, throws ResponseException with the code
     * ResponseStatusCode::kIllegalArgument.
     */
    auto deleteConversationSettings(const std::unordered_set<int64_t>& userIds,
                                    const std::unordered_set<int64_t>& groupIds,
                                    const std::unordered_set<std::string>& names)
        -> boost::future<Response<void>>;

    /**
     * Find conversation settings.
     *
     * @param userIds the target user IDs. If both userIds and groupIds are null, the settings of
     * all private and group conversations will be returned.
     * @param groupIds the target group IDs. If both userIds and groupIds are null, the settings of
     * all private and group conversations will be returned.
     * @param names the target setting names.
     * @param lastUpdatedDate the last updated date of conversation settings stored locally.
     * The server will only return conversation settings if a setting has been updated after
     * lastUpdatedDate.
     * @throws ResponseException if an error occurs.
     */
    auto queryConversationSettings(const std::unordered_set<int64_t>& userIds,
                                   const std::unordered_set<int64_t>& groupIds,
                                   const std::unordered_set<std::string>& names,
                                   const boost::optional<time_point>& lastUpdatedDate)
        -> boost::future<Response<std::vector<ConversationSettings>>>;

    /**
     * Update the typing status of the target private conversation.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws ResponseException with the code
     * ResponseStatusCode::kUpdatingTypingStatusIsDisabled.
     * * If the logged-in user is not a friend of userId, throws ResponseException with the code
     * ResponseStatusCode::kNotFriendToSendTypingStatus.
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to the participant actively.
     *
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    auto updatePrivateConversationTypingStatus(int64_t userId) -> boost::future<Response<void>>;

    /**
     * Update the typing status of the target group conversation.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws ResponseException with the code
     * ResponseStatusCode::kUpdatingTypingStatusIsDisabled.
     * * If the logged-in user is not a member of the target group, throws ResponseException with
     * the code ResponseStatusCode::kNotGroupMemberToSendTypingStatus.
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to all participants actively.
     *
     * @param groupId the target group ID.
     * @throws ResponseException if an error occurs.
     */
    auto updateGroupConversationTypingStatus(int64_t groupId) -> boost::future<Response<void>>;

   private:
    TurmsClient& turmsClient_;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H