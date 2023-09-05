#ifndef TURMS_CLIENT_TRANSPORT_TCP_CLIENT_H
#define TURMS_CLIENT_TRANSPORT_TCP_CLIENT_H

#include <boost/asio.hpp>
#include <boost/thread/future.hpp>
#include <functional>
#include <memory>
#include <vector>

#include "tcp_metrics.h"

namespace turms {
namespace client {
namespace transport {
class TcpClient : private std::enable_shared_from_this<TcpClient> {
   public:
    using OnBytesReceivedHandler = std::function<void(boost::asio::streambuf&)>;
    using OnCloseHandler = std::function<void(const boost::optional<std::exception>&)>;

    template <typename OnClose, typename OnBytesReceived>
    explicit TcpClient(boost::asio::io_context& ioContext,
                       OnClose&& onClose,
                       OnBytesReceived&& onBytesReceived)
        : ioContext_(ioContext),
          socket_(boost::asio::ip::tcp::socket{ioContext}),
          resolver_(ioContext),
          readBuffer_(TcpClient::kMaxReadBufferCapacity),
          onClose_(std::forward<OnClose>(onClose)),
          onBytesReceived_(std::forward<OnBytesReceived>(onBytesReceived)) {
    }

    auto connect(const std::string& host, int port, int connectTimeoutMillis)
        -> boost::future<void>;

    auto close(const boost::optional<std::exception>& e = boost::none) -> boost::future<void>;

    template <size_t N>
    auto write(const std::shared_ptr<std::array<uint8_t, N>>& errorCode) -> boost::future<void>;

    auto writeVarIntLengthAndBytes(const std::shared_ptr<std::vector<uint8_t>>& bytes)
        -> boost::future<void>;

    auto remoteEndpoint() -> const boost::asio::ip::basic_endpoint<boost::asio::ip::tcp>& {
        return remoteEndpoint_;
    }

    auto metrics() -> const TcpMetrics& {
        return metrics_;
    }

   private:
    static constexpr int kMaxReadBufferCapacity = 8 * 1024 * 1024;

    boost::asio::io_context& ioContext_;
    boost::asio::ip::tcp::socket socket_;
    boost::asio::ip::tcp::resolver resolver_;
    boost::asio::streambuf readBuffer_;

    boost::asio::ip::basic_endpoint<boost::asio::ip::tcp> remoteEndpoint_;
    TcpMetrics metrics_{};

    OnCloseHandler onClose_;
    OnBytesReceivedHandler onBytesReceived_;

    void read();
};
}  // namespace transport
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_TRANSPORT_TCP_CLIENT_H