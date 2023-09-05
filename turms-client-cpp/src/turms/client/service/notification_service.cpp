#include "turms/client/service/notification_service.h"

#include "turms/client/turms_client.h"

namespace turms {
namespace client {
namespace service {
NotificationService::NotificationService(TurmsClient& turmsClient) {
    turmsClient.driver().addNotificationListener([this](const TurmsNotification& notification) {
        const bool isBusinessNotification =
            notification.has_relayed_request() &&
            !notification.relayed_request().has_create_message_request() &&
            !notification.has_close_status();
        if (isBusinessNotification) {
            const Notification n{time::toTimePoint(notification.timestamp()),
                                 notification.requester_id(),
                                 notification.relayed_request()};
            for (const auto& listener : notificationListeners_) {
                listener(n);
            }
        }
    });
}

}  // namespace service
}  // namespace client
}  // namespace turms