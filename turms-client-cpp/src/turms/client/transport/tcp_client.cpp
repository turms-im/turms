#include "turms/client/transport/tcp_client.h"

#include <google/protobuf/io/coded_stream.h>

#include <boost/asio.hpp>

#include "google/protobuf/io/zero_copy_stream_impl_lite.h"

namespace turms {
namespace client {
namespace transport {

auto TcpClient::connect(const std::string& host, int port, int connectTimeoutMillis)
    -> boost::future<void> {
    if (socket_.is_open()) {
        return boost::make_exceptional_future<void>(
            std::runtime_error("The TCP client has connected"));
    }
    auto promise = std::make_shared<boost::promise<void>>();
    std::shared_ptr<boost::asio::steady_timer> connectTimeout;
    if (connectTimeoutMillis > 0) {
        connectTimeout = std::make_shared<boost::asio::steady_timer>(
            ioContext_, std::chrono::milliseconds{connectTimeoutMillis});
        connectTimeout->async_wait([promise](const boost::system::error_code& e) {
            if (e == boost::asio::error::operation_aborted) {
                return;
            }
            promise->set_exception(boost::system::system_error{boost::asio::error::timed_out});
        });
    }
    boost::asio::post(
        ioContext_,
        [weakThis = std::weak_ptr<TcpClient>(shared_from_this()),
         host,
         port,
         promise,
         connectTimeout]() mutable {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                if (connectTimeout) {
                    connectTimeout->cancel();
                }
                promise->set_exception(std::runtime_error("The TCP client has been destroyed"));
                return;
            }
            sharedThis->resolver_.async_resolve(
                host,
                std::to_string(port),
                [weakThis = std::weak_ptr<TcpClient>(sharedThis),
                 promise,
                 connectTimeout,
                 resolveStart = std::chrono::steady_clock::now()](
                    const boost::system::error_code& ec,
                    const boost::asio::ip::tcp::resolver::results_type& endpoints) mutable {
                    auto sharedThis = weakThis.lock();
                    if (sharedThis == nullptr) {
                        if (connectTimeout) {
                            connectTimeout->cancel();
                        }
                        promise->set_exception(
                            std::runtime_error("The TCP client has been destroyed"));
                        return;
                    }
                    if (ec) {
                        if (connectTimeout) {
                            connectTimeout->cancel();
                        }
                        promise->set_exception(boost::system::system_error(ec));
                        return;
                    }
                    const auto connectStart = std::chrono::steady_clock::now();
                    sharedThis->metrics_.addressResolverTime = connectStart - resolveStart;
                    boost::asio::async_connect(
                        sharedThis->socket_,
                        endpoints,
                        [weakThis = std::weak_ptr<TcpClient>(sharedThis),
                         promise,
                         connectTimeout,
                         connectStart](const boost::system::error_code& ec,
                                       const boost::asio::ip::tcp::resolver::results_type::
                                           endpoint_type&) mutable {
                            if (connectTimeout) {
                                connectTimeout->cancel();
                            }
                            auto sharedThis = weakThis.lock();
                            if (sharedThis == nullptr) {
                                promise->set_exception(
                                    std::runtime_error("The TCP client has been destroyed"));
                                return;
                            }
                            if (ec) {
                                promise->set_exception(boost::system::system_error(ec));
                                return;
                            }
                            const auto connectEnd = std::chrono::steady_clock::now();
                            sharedThis->metrics_.connectTime = connectEnd - connectStart;
                            sharedThis->socket_.set_option(boost::asio::ip::tcp::no_delay(true));
                            sharedThis->remoteEndpoint_ = sharedThis->socket_.remote_endpoint();
                            sharedThis->read();
                            promise->set_value();
                        });
                });
        });
    return promise->get_future();
}

auto TcpClient::close(const boost::optional<std::exception>& e) -> boost::future<void> {
    boost::promise<void> promise;
    boost::future<void> future = promise.get_future();
    boost::asio::post([weakThis = std::weak_ptr<TcpClient>(shared_from_this()),
                       e,
                       promise = std::move(promise)]() mutable {
        auto sharedThis = weakThis.lock();
        if (sharedThis == nullptr) {
            return;
        }
        sharedThis->socket_.shutdown(boost::asio::ip::tcp::socket::shutdown_both);
        sharedThis->socket_.close();
        sharedThis->onClose_(e);
        promise.set_value();
    });
    return future;
}

void TcpClient::read() {
    boost::asio::async_read(
        socket_,
        readBuffer_,
        [weakThis = std::weak_ptr<TcpClient>(shared_from_this())](
            const boost::system::error_code& error, std::size_t bytesTransferred) {
            auto sharedThis = weakThis.lock();
            if (sharedThis == nullptr) {
                return;
            }
            if (error) {
                sharedThis->close();
                return;
            }
            sharedThis->metrics_.dataReceived += bytesTransferred;
            sharedThis->onBytesReceived_(sharedThis->readBuffer_);
            sharedThis->read();
        });
}

template <size_t N>
auto TcpClient::write(const std::shared_ptr<std::array<uint8_t, N>>& bytes) -> boost::future<void> {
    boost::promise<void> promise;
    boost::future<void> future = promise.get_future();
    try {
        boost::asio::async_write(
            socket_,
            boost::asio::buffer(*bytes),
            [weakThis = std::weak_ptr<TcpClient>(shared_from_this()), promise = std::move(promise)](
                const boost::system::error_code& errorCode, std::size_t bytesTransferred) mutable {
                if (errorCode) {
                    boost::system::system_error e{boost::system::system_error(errorCode)};
                    promise.set_exception(std::move(e));
                    if (auto sharedThis = weakThis.lock()) {
                        sharedThis->close(e);
                    }
                } else {
                    if (auto sharedThis = weakThis.lock()) {
                        sharedThis->metrics_.dataSent += bytesTransferred;
                    }
                    promise.set_value();
                }
            });
    } catch (const std::exception& e) {
        close(e);
        return boost::make_exceptional_future<void>(e);
    }
    return future;
}

auto TcpClient::writeVarIntLengthAndBytes(const std::shared_ptr<std::vector<uint8_t>>& bytes)
    -> boost::future<void> {
    boost::promise<void> promise;
    boost::future<void> future = promise.get_future();
    uint32_t dataLength = bytes->size();
    size_t varintLengthByteCount =
        google::protobuf::io::CodedOutputStream::VarintSize32(dataLength);
    const auto varintLengthBytes = std::make_shared<std::vector<uint8_t>>(varintLengthByteCount);
    google::protobuf::io::CodedOutputStream::WriteVarint32ToArray(dataLength,
                                                                  (*varintLengthBytes).data());
    async_write(
        socket_,
        buffer(std::array<boost::asio::const_buffer, 2>{boost::asio::buffer(*varintLengthBytes),
                                                        boost::asio::buffer(*bytes)}),
        [weakThis = std::weak_ptr<TcpClient>(shared_from_this()), promise = std::move(promise)](
            const boost::system::error_code& errorCode, std::size_t bytesTransferred) mutable {
            if (errorCode) {
                promise.set_exception(boost::system::system_error(errorCode));
                if (auto sharedThis = weakThis.lock()) {
                    sharedThis->close(boost::system::system_error(errorCode));
                }
            } else {
                if (auto sharedThis = weakThis.lock()) {
                    sharedThis->metrics_.dataSent += bytesTransferred;
                }
                promise.set_value();
            }
        });
    return future;
}
}  // namespace transport
}  // namespace client
}  // namespace turms