#ifndef TURMS_CLIENT_RANDOM_RANDOM_UTIL_H
#define TURMS_CLIENT_RANDOM_RANDOM_UTIL_H

#include <array>
#include <random>

namespace turms::client::random {
auto nextPositiveInt64() -> int64_t;
}

#endif  // TURMS_CLIENT_RANDOM_RANDOM_UTIL_H
