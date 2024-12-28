#ifndef TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H
#define TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H

#include <optional>
#include <string>

namespace turms::client::model {
struct SessionCloseInfo {
    int closeStatus;
    std::optional<int> businessStatus;
    std::optional<std::string> reason;
    std::optional<std::exception> cause;
};
}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H
