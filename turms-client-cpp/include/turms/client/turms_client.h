#ifndef TURMS_CLIENT_TURMS_CLIENT_H
#define TURMS_CLIENT_TURMS_CLIENT_H

#include <boost/asio/io_context.hpp>
#include <boost/thread/future.hpp>

#include "turms/client/driver/turms_driver.h"
#include "turms/client/service/conference_service.h"
#include "turms/client/service/conversation_service.h"
#include "turms/client/service/group_service.h"
#include "turms/client/service/message_service.h"
#include "turms/client/service/notification_service.h"
#include "turms/client/service/user_service.h"

namespace turms::client {
/**
 * non-thread safe implementation
 */
class TurmsClient {
   private:
    using TurmsDriver = driver::TurmsDriver;
    using UserService = service::UserService;
    using GroupService = service::GroupService;
    using ConversationService = service::ConversationService;
    using MessageService = service::MessageService;
    using ConferenceService = service::ConferenceService;
    using NotificationService = service::NotificationService;

   public:
    /**
     * @param ioContext We use std::shared_ptr because:
     * 1. Avoid user using wrong order of members' destructor call that will crash turms client if
     * the ioContext is destructed first.
     * 2. User can pass rvalue if they don't want to maintain io_context.
     */
    explicit TurmsClient(const std::shared_ptr<boost::asio::io_context>& ioContext,
                         const std::optional<std::string>& host = std::nullopt,
                         const std::optional<int>& port = std::nullopt,
                         const std::optional<int>& connectTimeoutMillis = std::nullopt,
                         const std::optional<int>& requestTimeoutMillis = std::nullopt,
                         const std::optional<int>& minRequestIntervalMillis = std::nullopt,
                         const std::optional<int>& heartbeatIntervalMillis = std::nullopt);

    ~TurmsClient();

    auto close() -> boost::future<void>;

    auto driver() noexcept -> TurmsDriver&;

    auto userService() noexcept -> UserService&;

    auto groupService() noexcept -> GroupService&;

    auto conversationService() noexcept -> ConversationService&;

    auto messageService() noexcept -> MessageService&;

    auto conferenceService() noexcept -> ConferenceService&;

    auto notificationService() noexcept -> NotificationService&;

   private:
    TurmsDriver driver_;
    UserService userService_;
    GroupService groupService_;
    ConversationService conversationService_;
    MessageService messageService_;
    ConferenceService conferenceService_;
    NotificationService notificationService_;
};
}  // namespace turms::client

#endif  // TURMS_CLIENT_TURMS_CLIENT_H