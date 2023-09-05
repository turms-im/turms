#ifndef TURMS_CLIENT_DRIVER_SERVICE_MESSAGE_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_MESSAGE_SERVICE_H

#include <boost/thread/future.hpp>
#include <random>

#include "turms/client/driver/service/base_service.h"
#include "turms/client/driver/state_store.h"
#include "turms/client/exception/response_exception.h"
#include "turms/client/model/notification_util.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response_status_code.h"
#include "turms/client/random/random_util.h"

namespace turms {
namespace client {
namespace driver {
namespace service {

class MessageService : public BaseService, private std::enable_shared_from_this<MessageService> {
   private:
    using ResponseException = exception::ResponseException;
    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

   public:
    using NotificationHandler = std::function<void(const TurmsNotification&)>;

    MessageService(boost::asio::io_context& ioContext,
                   StateStore& stateStore,
                   const boost::optional<int>& requestTimeout,
                   const boost::optional<int>& minRequestInterval);

    // Listeners

    template <typename T>
    auto addNotificationListener(T&& listener) -> void {
        notificationListeners_.emplace_back(std::forward<T>(listener));
    }

    // Request and notification

    auto sendRequest(model::proto::TurmsRequest& request) -> boost::future<TurmsNotification>;

    auto didReceiveNotification(const TurmsNotification& notification) -> void;

    auto close() -> boost::future<void> override;

    auto onDisconnected(const boost::optional<std::exception>& exception) -> void override;

   private:
    struct TurmsRequestContext {
        boost::promise<TurmsNotification> promise;
        boost::optional<boost::asio::steady_timer> timeoutTimer;
    };

    std::map<int64_t, TurmsRequestContext> idToRequest_{};

    auto generateRandomId() const -> int64_t;

    auto notifyNotificationListeners(const TurmsNotification& notification) const -> void;

    auto rejectRequestPromises(const std::exception& exception) -> void;

   private:
    std::chrono::duration<int, std::milli> requestTimeout_;
    bool enableRequestTimeout_;
    int minRequestInterval_;
    std::vector<std::function<void(const TurmsNotification&)>> notificationListeners_;
};

}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_SERVICE_MESSAGE_SERVICE_H