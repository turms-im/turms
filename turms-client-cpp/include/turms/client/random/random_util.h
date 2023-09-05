#ifndef TURMS_CLIENT_RANDOM_RANDOM_UTIL_H
#define TURMS_CLIENT_RANDOM_RANDOM_UTIL_H

#include <array>
#include <random>

namespace turms {
namespace client {
namespace random {

namespace {
auto makeMt19937() -> std::mt19937_64;

auto makeUniformPositiveInt64Distribution();
}  // namespace

auto nextPositiveInt64() -> int64_t;

}  // namespace random
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_RANDOM_RANDOM_UTIL_H