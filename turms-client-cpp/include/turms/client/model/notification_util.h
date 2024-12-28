#ifndef TURMS_CLIENT_MODEL_NOTIFICATION_UTIL_H
#define TURMS_CLIENT_MODEL_NOTIFICATION_UTIL_H

#include "turms/client/model/proto/notification/turms_notification.pb.h"

namespace turms::client::model {
namespace notification {
using TurmsNotification = proto::TurmsNotification;

auto isSuccess(const TurmsNotification& notification) -> bool;

auto isError(const TurmsNotification& notification) -> bool;

auto getLongOrThrow(const TurmsNotification::Data& data) -> int64_t;
};  // namespace notification
}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_NOTIFICATION_UTIL_H