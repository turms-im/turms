#ifndef TURMS_CLIENT_DRIVER_TURMS_DRIVER_H
#define TURMS_CLIENT_DRIVER_TURMS_DRIVER_H

#include <boost/asio/io_context.hpp>
#include <boost/thread/future.hpp>

#include "turms/client/driver/service/connection_service.h"
#include "turms/client/driver/service/heartbeat_service.h"
#include "turms/client/driver/service/protocol_message_service.h"
#include "turms/client/driver/state_store.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/transport/tcp_metrics.h"

namespace turms::client::driver {

class TurmsDriver : private boost::noncopyable, private std::enable_shared_from_this<TurmsDriver> {
   private:
    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;
    using ConnectionService = service::ConnectionService;
    using HeartbeatService = service::HeartbeatService;
    using ProtocolMessageService = service::ProtocolMessageService;
    using TcpMetrics = transport::TcpMetrics;

   public:
    TurmsDriver(const std::shared_ptr<boost::asio::io_context>& ioContext,
                const std::optional<std::string>& host,
                const std::optional<int>& port,
                const std::optional<int>& connectTimeoutMillis,
                const std::optional<int>& requestTimeoutMillis,
                const std::optional<int>& minRequestIntervalMillis,
                const std::optional<int>& heartbeatIntervalMillis);

    // Close
    auto close() -> boost::future<void>;

    // Heartbeat Service

    auto startHeartbeat() -> void;

    auto stopHeartbeat() -> void;

    auto sendHeartbeat() -> boost::future<void>;

    auto isHeartbeatRunning() const -> bool;

    // Connection Service

    auto connect(const std::optional<std::string>& host = std::nullopt,
                 const std::optional<int>& port = std::nullopt,
                 const std::optional<int>& connectTimeoutMillis = std::nullopt)
        -> boost::future<void>;

    auto disconnect() const -> boost::future<void>;

    auto isConnected() const -> bool;

    auto connectionMetrics() const -> std::optional<TcpMetrics>;

    // Connection Listeners

    template <typename T>
    auto addOnConnectedListener(T&& listener) -> void {
        connectionService_.addOnConnectedListener(std::forward<T>(listener));
    }

    template <typename T>
    auto addOnDisconnectedListener(T&& listener) -> void {
        connectionService_.addOnDisconnectedListener(std::forward<T>(listener));
    }

    // Message Service

    auto send(TurmsRequest& request) -> boost::future<TurmsNotification>;

    template <typename T>
    auto addNotificationListener(T&& listener) -> void {
        protocolMessageService_.addNotificationListener(std::forward<T>(listener));
    }

    // State
    auto stateStore() -> StateStore&;

   private:
    StateStore stateStore_{};
    std::shared_ptr<boost::asio::io_context> ioContext_;
    ConnectionService connectionService_;
    HeartbeatService heartbeatService_;
    ProtocolMessageService protocolMessageService_;

    // Intermediary functions as a mediator between services
    auto onConnectionDisconnected(const std::optional<std::exception>& e = std::nullopt) -> void;

    auto onMessage(const std::vector<uint8_t>& message) -> void;
};

}  // namespace turms::client::driver

#endif  // TURMS_CLIENT_DRIVER_TURMS_DRIVER_H