#ifndef TURMS_CLIENT_SERVICE_NOTIFICATION_SERVICE_H
#define TURMS_CLIENT_SERVICE_NOTIFICATION_SERVICE_H

#include <boost/noncopyable.hpp>
#include <functional>

#include "turms/client/model/notification.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class NotificationService : private boost::noncopyable {
   private:
    using Notification = model::Notification;
    using TurmsNotification = model::proto::TurmsNotification;

   public:
    using NotificationListener = std::function<void(const Notification&)>;

    explicit NotificationService(TurmsClient& turmsClient);

    /**
     * Add a notification listener to receive notifications.
     * Note: This listener will receive all kinds of notifications excluding messages.
     * To listen to messages, use MessageService::addMessageListener() instead.
     */
    template <typename T>
    auto addNotificationListener(T&& listener) -> void {
        notificationListeners_.emplace_back(std::forward<T>(listener));
    }

   private:
    std::vector<NotificationListener> notificationListeners_;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_NOTIFICATION_SERVICE_H