#include "turms/client/model/notification_util.h"

namespace turms {
namespace client {
namespace model {
auto notification::isSuccess(const TurmsNotification& notification) -> bool {
    return notification.has_code() && ResponseStatusCode::isSuccessCode(notification.code());
}

auto notification::isError(const TurmsNotification& notification) -> bool {
    return notification.has_code() && ResponseStatusCode::isErrorCode(notification.code());
}

auto notification::getLongOrThrow(const proto::TurmsNotification::Data& data) -> int64_t {
    if (!data.has_long_()) {
        throw std::runtime_error("Could not get a long value from the invalid response: " +
                                 data.DebugString());
    }
    return data.long_();
}
}  // namespace model
}  // namespace client
}  // namespace turms