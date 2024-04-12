#ifndef TURMS_CLIENT_TRANSPORT_TCP_METRICS_H
#define TURMS_CLIENT_TRANSPORT_TCP_METRICS_H

#include <chrono>

namespace turms {
namespace client {
namespace transport {
struct TcpMetrics {
    std::chrono::duration<double> connectTime{};
//    std::chrono::duration<int, std::milli> tlsHandshakeTime{};
    std::chrono::duration<double> addressResolverTime{};
    size_t dataReceived{0};
    size_t dataSent{0};
};
}  // namespace transport
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_TRANSPORT_TCP_METRICS_H