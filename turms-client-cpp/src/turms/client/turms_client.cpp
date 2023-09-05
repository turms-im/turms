#include "turms/client/turms_client.h"

namespace turms {
namespace client {

TurmsClient::TurmsClient(const std::shared_ptr<boost::asio::io_context>& ioContext,
                         const boost::optional<std::string>& host,
                         const boost::optional<int>& port,
                         const boost::optional<int>& connectTimeoutMillis,
                         const boost::optional<int>& requestTimeoutMillis,
                         const boost::optional<int>& minRequestIntervalMillis,
                         const boost::optional<int>& heartbeatIntervalMillis)
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
      notificationService_(*this) {
}

TurmsClient::~TurmsClient() {
    close();
}

auto TurmsClient::close() -> boost::future<void> {
    return driver_.close();
}

auto TurmsClient::driver() noexcept -> TurmsClient::TurmsDriver& {
    return driver_;
}

auto TurmsClient::userService() noexcept -> TurmsClient::UserService& {
    return userService_;
}

auto TurmsClient::groupService() noexcept -> TurmsClient::GroupService& {
    return groupService_;
}

auto TurmsClient::conversationService() noexcept -> TurmsClient::ConversationService& {
    return conversationService_;
}

auto TurmsClient::messageService() noexcept -> TurmsClient::MessageService& {
    return messageService_;
}

auto TurmsClient::notificationService() noexcept -> TurmsClient::NotificationService& {
    return notificationService_;
}
}  // namespace client
}  // namespace turms