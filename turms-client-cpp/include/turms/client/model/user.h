#ifndef TURMS_CLIENT_MODEL_USER_H
#define TURMS_CLIENT_MODEL_USER_H

#include <optional>
#include <unordered_map>

#include "turms/client/model/proto/constant/device_type.pb.h"
#include "turms/client/model/proto/constant/user_status.pb.h"
#include "user_location.h"

namespace turms::client::model {
struct User {
    int64_t userId;
    std::optional<std::string> password;
    std::optional<proto::DeviceType> deviceType;
    std::optional<std::unordered_map<std::string, std::string>> deviceDetails;
    std::optional<proto::UserStatus> onlineStatus;
    std::optional<UserLocation> location;
};
}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_USER_H
