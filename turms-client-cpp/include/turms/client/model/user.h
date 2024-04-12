#ifndef TURMS_CLIENT_MODEL_USER_H
#define TURMS_CLIENT_MODEL_USER_H

#include <boost/optional.hpp>
#include <unordered_map>

#include "turms/client/model/proto/constant/device_type.pb.h"
#include "turms/client/model/proto/constant/user_status.pb.h"
#include "user_location.h"

namespace turms {
namespace client {
namespace model {

struct User {
    int64_t userId;
    boost::optional<std::string> password;
    boost::optional<proto::DeviceType> deviceType;
    boost::optional<std::unordered_map<std::string, std::string>> deviceDetails;
    boost::optional<proto::UserStatus> onlineStatus;
    boost::optional<UserLocation> location;
};

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_USER_H