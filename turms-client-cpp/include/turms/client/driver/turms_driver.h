#ifndef TURMS_CLIENT_DRIVER_TURMS_DRIVER_H
#define TURMS_CLIENT_DRIVER_TURMS_DRIVER_H

#include <atomic>
#include <boost/asio/io_context.hpp>
#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>

#include "turms/client/driver/service/connection_service.h"
#include "turms/client/driver/service/heartbeat_service.h"
#include "turms/client/driver/service/message_service.h"
#include "turms/client/driver/state_store.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/transport/tcp_metrics.h"

namespace turms {
namespace client {
namespace driver {

class TurmsDriver : private boost::noncopyable, private std::enable_shared_from_this<TurmsDriver> {
   private:
    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;
    using StateStore = driver::StateStore;
    using ConnectionService = service::ConnectionService;
    using HeartbeatService = service::HeartbeatService;
    using MessageService = service::MessageService;
    using TcpMetrics = transport::TcpMetrics;

   public:
    TurmsDriver(const std::shared_ptr<boost::asio::io_context>& ioContext,
                const boost::optional<std::string>& host,
                const boost::optional<int>& port,
                const boost::optional<int>& connectTimeoutMillis,
                const boost::optional<int>& requestTimeoutMillis,
                const boost::optional<int>& minRequestIntervalMillis,
                const boost::optional<int>& heartbeatIntervalMillis);

    // Close
    auto close() -> boost::future<void>;

    // Heartbeat Service

    auto startHeartbeat() -> void;

    auto stopHeartbeat() -> void;

    auto sendHeartbeat() -> boost::future<void>;

    auto isHeartbeatRunning() const -> bool;

    // Connection Service

    auto connect(const boost::optional<std::string>& host = boost::none,
                 const boost::optional<int>& port = boost::none,
                 const boost::optional<int>& connectTimeoutMillis = boost::none)
        -> boost::future<void>;

    auto disconnect() -> boost::future<void>;

    auto isConnected() const -> bool;

    auto connectionMetrics() const -> boost::optional<TcpMetrics>;

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
        messageService_.addNotificationListener(std::forward<T>(listener));
    }

    // State
    auto stateStore() -> StateStore&;

   private:
    StateStore stateStore_{};
    std::shared_ptr<boost::asio::io_context> ioContext_;
    ConnectionService connectionService_;
    HeartbeatService heartbeatService_;
    MessageService messageService_;

    // Intermediary functions as a mediator between services
    auto onConnectionDisconnected(const boost::optional<std::exception>& e = boost::none) -> void;

    auto onMessage(const std::vector<uint8_t>& message) -> void;
};

}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_TURMS_DRIVER_H