#include "turms/client/driver/state_store.h"

namespace turms {
namespace client {
namespace driver {

auto StateStore::reset() -> void {
    tcp = nullptr;
    isConnected = false;
    isSessionOpen = false;
    sessionId.reset();
    serverId.reset();
    lastRequestDate = 0;
}

} // namespace driver
} // namespace client
} // namespace turms