#include "turms/client/random/random_util.h"

#include <algorithm>

namespace turms {
namespace client {
namespace random {
namespace {
auto makeMt19937() -> std::mt19937_64 {
    std::random_device rd;
    std::array<int, std::mt19937_64::state_size> seed{};
    std::generate(std::begin(seed), std::end(seed), std::ref(rd));
    std::seed_seq seq{std::begin(seed), std::end(seed)};
    return std::mt19937_64{seq};
}

auto makeUniformPositiveInt64Distribution() {
    return std::uniform_int_distribution<int64_t>{1, std::numeric_limits<int64_t>::max()};
}
}  // namespace

auto nextPositiveInt64() -> int64_t {
    static std::mt19937_64 randomEngine = makeMt19937();
    static std::uniform_int_distribution<int64_t> distribution =
        makeUniformPositiveInt64Distribution();
    return distribution(randomEngine);
}
}  // namespace random
}  // namespace client
}  // namespace turms