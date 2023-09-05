#include "turms/client/time/time_util.h"

namespace turms {
namespace client {
namespace time {
auto toInt64(const std::chrono::time_point<std::chrono::system_clock>& timePoint) -> int64_t {
    return std::chrono::duration_cast<std::chrono::milliseconds>(timePoint.time_since_epoch())
        .count();
}

auto toTimePoint(int64_t timeSinceEpochMillis)
    -> std::chrono::time_point<std::chrono::system_clock> {
    return std::chrono::time_point<std::chrono::system_clock>(
        std::chrono::milliseconds(timeSinceEpochMillis));
}

auto nowMillis() -> int64_t {
    return std::chrono::duration_cast<std::chrono::milliseconds>(
               std::chrono::system_clock::now().time_since_epoch())
        .count();
}
}  // namespace time
}  // namespace client
}  // namespace turms