#ifndef TURMS_CLIENT_MODEL_NOTIFICATION_H
#define TURMS_CLIENT_MODEL_NOTIFICATION_H

#include <chrono>

#include "turms/client/model/proto/request/turms_request.pb.h"

namespace turms {
namespace client {
namespace model {

struct Notification {
    std::chrono::system_clock::time_point timestamp_;
    int64_t requesterId_;
    proto::TurmsRequest relayedRequest_;
};

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_NOTIFICATION_H