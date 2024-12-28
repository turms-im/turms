#ifndef TURMS_CLIENT_DRIVER_STATE_STORE_H
#define TURMS_CLIENT_DRIVER_STATE_STORE_H

#include <optional>
#include <string>

#include "turms/client/transport/tcp_client.h"

namespace turms::client::driver {

struct StateStore {
    std::unique_ptr<transport::TcpClient> tcp;
    bool isConnected;
    bool isSessionOpen;
    std::optional<std::string> sessionId;
    std::optional<std::string> serverId;
    int64_t lastRequestDate;

    auto reset() -> void;
};

}  // namespace turms::client::driver

#endif  // TURMS_CLIENT_DRIVER_STATE_STORE_H