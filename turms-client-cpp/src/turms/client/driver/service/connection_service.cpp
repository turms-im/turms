#include "turms/client/driver/service/connection_service.h"

namespace turms {
namespace client {
namespace driver {
namespace service {
ConnectionService::ConnectionService(boost::asio::io_context& ioContext,
                                     StateStore& stateStore,
                                     const boost::optional<std::string>& host,
                                     const boost::optional<int>& port,
                                     const boost::optional<int>& connectTimeoutMillis)
    : BaseService(ioContext, stateStore),
      initialHost_((host && !host->empty()) ? *host : "127.0.0.1"),
      initialPort_(port ? *port : 11510),
      initialConnectTimeout_(
          (connectTimeoutMillis && *connectTimeoutMillis > 0) ? *connectTimeoutMillis : 30 * 1000) {
}

auto ConnectionService::resetStates() -> void {
    completeDisconnectPromises();
}

auto ConnectionService::notifyOnConnectedListeners() -> void {
    for (const auto& listener : onConnectedListeners_) {
        listener();
    }
}

auto ConnectionService::notifyOnDisconnectedListeners(const boost::optional<std::exception>& e)
    -> void {
    for (const auto& listener : onDisconnectedListeners_) {
        listener(e);
    }
}

auto ConnectionService::notifyMessageListeners(const std::vector<uint8_t>& message) -> void {
    for (const auto& listener : messageListeners_) {
        listener(message);
    }
}

auto ConnectionService::completeDisconnectPromises(const boost::optional<std::exception>& e)
    -> void {
    while (!disconnectPromises_.empty()) {
        boost::promise<boost::optional<std::exception>>& promise = disconnectPromises_.back();
        disconnectPromises_.pop_back();
        promise.set_value(e);
    }
}

auto ConnectionService::connect(const boost::optional<std::string>& host,
                                const boost::optional<int>& port,
                                const boost::optional<int>& connectTimeoutMillis)
    -> boost::future<void> {
    if (stateStore_.isConnected) {
        const auto& endpoint = stateStore_.tcp->remoteEndpoint();
        if (host && port && *host == endpoint.address().to_string() && *port == endpoint.port()) {
            return boost::make_ready_future();
        }
        return boost::make_exceptional_future<void>(exception::ResponseException{
            model::ResponseStatusCode::kClientSessionAlreadyEstablished});
    }
    resetStates();
    stateStore_.tcp = std::make_unique<transport::TcpClient>(
        ioContext_,
        [weakThis = std::weak_ptr<ConnectionService>(shared_from_this())](
            const boost::optional<std::exception>& e) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            sharedThis->onSocketClosed(e);
        },
        [weakThis = std::weak_ptr<ConnectionService>(shared_from_this())](
            boost::asio::streambuf& readBuffer) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            sharedThis->decodeMessages(readBuffer,
                                       [&sharedThis](const std::vector<uint8_t>& message) {
                                           sharedThis->notifyMessageListeners(message);
                                       });
        });
    return stateStore_.tcp
        ->connect(host.get_value_or(initialHost_),
                  port.get_value_or(initialPort_),
                  connectTimeoutMillis.get_value_or(initialConnectTimeout_))
        .then([weakThis = std::weak_ptr<ConnectionService>(shared_from_this())](
                  const boost::future<void>& response) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            if (!response.has_exception()) {
                sharedThis->onSocketOpened();
            }
        });
}

auto ConnectionService::disconnect() -> boost::future<void> {
    if (stateStore_.isConnected) {
        stateStore_.isConnected = false;
        return stateStore_.tcp->close();
    }
    return boost::make_ready_future();
}

auto ConnectionService::onSocketOpened() -> void {
    stateStore_.isConnected = true;
    notifyOnConnectedListeners();
}

auto ConnectionService::onSocketClosed(const boost::optional<std::exception>& e) -> void {
    stateStore_.isConnected = false;
    completeDisconnectPromises(e);
    notifyOnDisconnectedListeners(e);
}

auto ConnectionService::close() -> boost::future<void> {
    disconnect();
    return boost::make_ready_future();
}

auto ConnectionService::onDisconnected(const boost::optional<std::exception>& exception) -> void {
}

template <typename T>
auto ConnectionService::decodeMessages(boost::asio::streambuf& readBuffer, T&& messageHandler)
    -> void {
    if (payloadLength_ == -1) {
        payloadLength_ = tryReadVarInt(readBuffer);
        if (payloadLength_ == -1) {
            return;
        }
    }
    if (readBuffer.size() < payloadLength_) {
        return;
    }
    std::vector<uint8_t> message(payloadLength_);
    if (payloadLength_ > 0) {
        boost::asio::buffer_copy(boost::asio::buffer(message), readBuffer.data(), payloadLength_);
        readBuffer.consume(payloadLength_);
    }
    payloadLength_ = -1;
    messageHandler(message);
    decodeMessages(readBuffer, std::forward<T>(messageHandler));
}

auto ConnectionService::tryReadVarInt(boost::asio::streambuf& readBuffer) -> int {
    int length = readBuffer.size();
    const char* data = boost::asio::buffer_cast<const char*>(readBuffer.data());
    while (readIndex_ < 5) {
        if (readIndex_ >= length) {
            return -1;
        }
        const int byte = data[readIndex_];
        tempPayloadLength_ |= (byte & 0x7F) << (7 * readIndex_);
        ++readIndex_;
        if ((byte & 0x80) == 0) {
            const int varIntLength = tempPayloadLength_;
            tempPayloadLength_ = 0;
            readBuffer.consume(readIndex_);
            readIndex_ = 0;
            return varIntLength;
        }
    }
    throw std::runtime_error("VarInt input too big");
}

auto ConnectionService::clear() -> void {
    readIndex_ = 0;
    tempPayloadLength_ = 0;
    payloadLength_ = -1;
}
}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms