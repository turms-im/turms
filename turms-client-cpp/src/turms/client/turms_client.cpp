#include "turms/client/turms_client.h"

namespace turms::client {
TurmsClient::TurmsClient(const std::shared_ptr<boost::asio::io_context>& ioContext,
                         const std::optional<std::string>& host,
                         const std::optional<int>& port,
                         const std::optional<int>& connectTimeoutMillis,
                         const std::optional<int>& requestTimeoutMillis,
                         const std::optional<int>& minRequestIntervalMillis,
                         const std::optional<int>& heartbeatIntervalMillis)
    : driver_(ioContext,
              host,
              port,
              connectTimeoutMillis,
              requestTimeoutMillis,
              minRequestIntervalMillis,
              heartbeatIntervalMillis),
      userService_(*this),
      groupService_(*this),
      conversationService_(*this),
      messageService_(*this),
      conferenceService_(*this),
      notificationService_(*this) {
}

TurmsClient::~TurmsClient() {
    close();
}

auto TurmsClient::close() -> boost::future<void> {
    return driver_.close();
}

auto TurmsClient::driver() noexcept -> TurmsDriver& {
    return driver_;
}

auto TurmsClient::userService() noexcept -> UserService& {
    return userService_;
}

auto TurmsClient::groupService() noexcept -> GroupService& {
    return groupService_;
}

auto TurmsClient::conversationService() noexcept -> ConversationService& {
    return conversationService_;
}

auto TurmsClient::messageService() noexcept -> MessageService& {
    return messageService_;
}

auto TurmsClient::conferenceService() noexcept -> ConferenceService& {
    return conferenceService_;
}

auto TurmsClient::notificationService() noexcept -> NotificationService& {
    return notificationService_;
}
}  // namespace turms::client
