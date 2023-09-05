#ifndef TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H

#include "base_service.h"
#include "turms/client/exception/response_exception.h"
#include "turms/client/model/response_status_code.h"
#include "turms/client/transport/tcp_client.h"

namespace turms {
namespace client {
namespace driver {
namespace service {

class ConnectionService : public BaseService,
                          private std::enable_shared_from_this<ConnectionService> {
   public:
    using MessageHandler = std::function<void(const std::vector<uint8_t>&)>;
    using OnConnectedHandler = std::function<void()>;
    using OnDisconnectedHandler = std::function<void(const boost::optional<std::exception>&)>;

    ConnectionService(boost::asio::io_context& ioContext,
                      StateStore& stateStore,
                      const boost::optional<std::string>& host,
                      const boost::optional<int>& port,
                      const boost::optional<int>& connectTimeoutMillis);

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

    auto notifyOnDisconnectedListeners(const boost::optional<std::exception>& e) -> void;

    auto notifyMessageListeners(const std::vector<uint8_t>& message) -> void;

    auto completeDisconnectPromises(const boost::optional<std::exception>& e = boost::none) -> void;

    auto connect(const boost::optional<std::string>& host = boost::none,
                 const boost::optional<int>& port = boost::none,
                 const boost::optional<int>& connectTimeoutMillis = boost::none)
        -> boost::future<void>;

    auto disconnect() -> boost::future<void>;

    auto onSocketOpened() -> void;

    auto onSocketClosed(const boost::optional<std::exception>& e) -> void;

    auto close() -> boost::future<void> override;

    auto onDisconnected(const boost::optional<std::exception>& exception) -> void override;

   private:
    std::string initialHost_;
    int initialPort_;
    int initialConnectTimeout_;
    std::vector<boost::promise<boost::optional<std::exception>>> disconnectPromises_;
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

}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_SERVICE_CONNECTION_SERVICE_H