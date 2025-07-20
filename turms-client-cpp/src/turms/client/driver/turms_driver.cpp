#include "turms/client/driver/turms_driver.h"

namespace turms::client::driver {
TurmsDriver::TurmsDriver(const std::shared_ptr<boost::asio::io_context>& ioContext,
                         const std::optional<std::string>& host,
                         const std::optional<int>& port,
                         const std::optional<int>& connectTimeoutMillis,
                         const std::optional<int>& requestTimeoutMillis,
                         const std::optional<int>& minRequestIntervalMillis,
                         const std::optional<int>& heartbeatIntervalMillis)
    : ioContext_(ioContext),
      connectionService_(*ioContext, stateStore_, host, port, connectTimeoutMillis),
      heartbeatService_(*ioContext, stateStore_, heartbeatIntervalMillis),
      protocolMessageService_(
          *ioContext, stateStore_, requestTimeoutMillis, minRequestIntervalMillis) {
    connectionService_.addOnDisconnectedListener([this](const std::optional<std::exception>& e) {
        onConnectionDisconnected(e);
    });
    connectionService_.addMessageListener([this](const std::vector<uint8_t>& message) {
        onMessage(message);
    });
}

auto TurmsDriver::close() -> boost::future<void> {
    auto promise = std::make_shared<boost::promise<void>>();
    post(*ioContext_, [this, promise]() {
        auto count = std::make_shared<std::atomic_int>(3);
        connectionService_.close().then([count, promise](const boost::future<void>&) mutable {
            if (--(*count) == 0) {
                promise->set_value();
            }
        });
        heartbeatService_.close().then([count, promise](const boost::future<void>&) mutable {
            if (--(*count) == 0) {
                promise->set_value();
            }
        });
        protocolMessageService_.close().then([count, promise](const boost::future<void>&) mutable {
            if (--(*count) == 0) {
                promise->set_value();
            }
        });
    });
    return promise->get_future();
}

auto TurmsDriver::startHeartbeat() -> void {
    heartbeatService_.start();
}

auto TurmsDriver::stopHeartbeat() -> void {
    heartbeatService_.stop();
}

auto TurmsDriver::sendHeartbeat() -> boost::future<void> {
    return heartbeatService_.send();
}

auto TurmsDriver::isHeartbeatRunning() const -> bool {
    return heartbeatService_.isRunning();
}

auto TurmsDriver::connect(const std::optional<std::string>& host,
                          const std::optional<int>& port,
                          const std::optional<int>& connectTimeoutMillis) -> boost::future<void> {
    return connectionService_.connect(host, port, connectTimeoutMillis);
}

auto TurmsDriver::disconnect() const -> boost::future<void> {
    return connectionService_.disconnect();
}

auto TurmsDriver::isConnected() const -> bool {
    return stateStore_.isConnected;
}

auto TurmsDriver::connectionMetrics() const -> std::optional<TcpMetrics> {
    const auto* ptr = stateStore_.tcp.get();
    return ptr == nullptr ? std::nullopt : std::optional(ptr->metrics());
}

auto TurmsDriver::send(TurmsRequest& request) -> boost::future<TurmsNotification> {
    const bool isCreateSessionRequest = request.has_create_session_request();
    return protocolMessageService_.sendRequest(request).then(
        [weakThis = std::weak_ptr(shared_from_this()),
         isCreateSessionRequest](boost::future<TurmsNotification> response) {
            if (const auto sharedThis = weakThis.lock()) {
                if (isCreateSessionRequest) {
                    sharedThis->heartbeatService_.start();
                }
            }
            return response.get();
        });
}

auto TurmsDriver::stateStore() -> StateStore& {
    return stateStore_;
}

auto TurmsDriver::onConnectionDisconnected(const std::optional<std::exception>& e) -> void {
    stateStore_.reset();
    heartbeatService_.onDisconnected(e);
    protocolMessageService_.onDisconnected(e);
}

auto TurmsDriver::onMessage(const std::vector<uint8_t>& message) -> void {
    if (message.empty()) {
        heartbeatService_.completeHeartbeatPromises();
    } else {
        TurmsNotification notification;
        try {
            notification.ParseFromArray(message.data(), message.size());
        } catch (const std::exception& e) {
            std::cerr << "Failed to parse TurmsNotification: " << e.what() << '\n';
            return;
        }
        if (heartbeatService_.rejectHeartbeatPromisesIfFail(notification)) {
            return;
        }
        if (notification.has_close_status()) {
            stateStore_.isSessionOpen = false;
            protocolMessageService_.didReceiveNotification(notification);
            // We must close the connection after finishing handling the notification
            // to ensure notification handlers will always be triggered before connection close
            // handlers.
            connectionService_.disconnect();
            return;
        }
        if (notification.has_data() && notification.data().has_user_session()) {
            const auto& session = notification.data().user_session();
            stateStore_.sessionId = session.session_id();
            stateStore_.serverId = session.server_id();
        }
        protocolMessageService_.didReceiveNotification(notification);
    }
}
}  // namespace turms::client::driver