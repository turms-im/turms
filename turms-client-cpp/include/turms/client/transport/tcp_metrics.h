#ifndef TURMS_CLIENT_TRANSPORT_TCP_METRICS_H
#define TURMS_CLIENT_TRANSPORT_TCP_METRICS_H

#include <chrono>

namespace turms::client::transport {
struct TcpMetrics {
    std::chrono::duration<double> connectTime{};
    //    std::chrono::duration<int, std::milli> tlsHandshakeTime{};
    std::chrono::duration<double> addressResolverTime{};
    size_t dataReceived{0};
    size_t dataSent{0};
};
}  // namespace turms::client::transport

#endif  // TURMS_CLIENT_TRANSPORT_TCP_METRICS_H