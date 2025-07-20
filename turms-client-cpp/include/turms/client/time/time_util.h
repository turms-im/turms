#ifndef TURMS_CLIENT_TIME_TIME_UTIL_H
#define TURMS_CLIENT_TIME_TIME_UTIL_H

#include <chrono>
#include <cstdint>

namespace turms::client::time {
auto toInt64(const std::chrono::time_point<std::chrono::system_clock>& timePoint) -> int64_t;

auto toTimePoint(int64_t timeSinceEpochMillis)
    -> std::chrono::time_point<std::chrono::system_clock>;

auto nowMillis() -> int64_t;

constexpr auto kEpoch = std::chrono::time_point<std::chrono::system_clock>{};
}  // namespace turms::client::time

#endif  // TURMS_CLIENT_TIME_TIME_UTIL_H
