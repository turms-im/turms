#ifndef TURMS_CLIENT_DRIVER_STATE_STORE_H
#define TURMS_CLIENT_DRIVER_STATE_STORE_H

#include <boost/optional.hpp>
#include <string>

#include "turms/client/transport/tcp_client.h"

namespace turms {
namespace client {
namespace driver {

struct StateStore {
    std::unique_ptr<transport::TcpClient> tcp;
    bool isConnected;
    bool isSessionOpen;
    boost::optional<std::string> sessionId;
    boost::optional<std::string> serverId;
    int64_t lastRequestDate;

    auto reset() -> void;
};

}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_STATE_STORE_H