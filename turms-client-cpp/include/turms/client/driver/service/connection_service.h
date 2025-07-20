#ifndef TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H

#include "base_service.h"
#include "turms/client/exception/response_exception.h"
#include "turms/client/transport/tcp_client.h"

namespace turms::client::driver::service {

class ConnectionService : public BaseService,
                          private std::enable_shared_from_this<ConnectionService> {
   public:
    using MessageHandler = std::function<void(const std::vector<uint8_t>&)>;
    using OnConnectedHandler = std::function<void()>;
    using OnDisconnectedHandler = std::function<void(const std::optional<std::exception>&)>;

    ConnectionService(boost::asio::io_context& ioContext,
                      StateStore& stateStore,
                      const std::optional<std::string>& host,
                      const std::optional<int>& port,
                      const std::optional<int>& connectTimeoutMillis);

    auto resetStates() -> void;

    template <typename T>
    auto addOnConnectedListener(T&& listener) -> void {
        onConnectedListeners_.emplace_back(std::forward<T>(listener));
    }

    template <typename T>
    auto addOnDisconnectedListener(T&& listener) -> void {
        onDisconnectedListeners_.emplace_back(std::forward<T>(listener));
    }

    template <typename T>
    auto addMessageListener(T&& listener) -> void {
        messageListeners_.emplace_back(std::forward<T>(listener));
    }

    auto notifyOnConnectedListeners() -> void;

    auto notifyOnDisconnectedListeners(const std::optional<std::exception>& e) -> void;

    auto notifyMessageListeners(const std::vector<uint8_t>& message) -> void;

    auto completeDisconnectPromises(const std::optional<std::exception>& e = std::nullopt) -> void;

    auto connect(const std::optional<std::string>& host = std::nullopt,
                 const std::optional<int>& port = std::nullopt,
                 const std::optional<int>& connectTimeoutMillis = std::nullopt)
        -> boost::future<void>;

    auto disconnect() const -> boost::future<void>;

    auto onSocketOpened() -> void;

    auto onSocketClosed(const std::optional<std::exception>& e) -> void;

    auto close() -> boost::future<void> override;

    auto onDisconnected(const std::optional<std::exception>& exception) -> void override;

   private:
    std::string initialHost_;
    int initialPort_;
    int initialConnectTimeout_;
    std::vector<boost::promise<std::optional<std::exception>>> disconnectPromises_;
    std::vector<OnConnectedHandler> onConnectedListeners_;
    std::vector<OnDisconnectedHandler> onDisconnectedListeners_;
    std::vector<MessageHandler> messageListeners_;

    int readIndex_{0};
    int tempPayloadLength_{0};
    int payloadLength_{-1};

    template <typename T>
    auto decodeMessages(boost::asio::streambuf& readBuffer, T&& messageHandler) -> void;

    auto tryReadVarInt(boost::asio::streambuf& readBuffer) -> int;

    auto clear() -> void;
};

}  // namespace turms::client::driver::service

#endif  // TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H