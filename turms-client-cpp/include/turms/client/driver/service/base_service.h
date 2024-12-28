#ifndef TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H

#include <boost/core/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <optional>

#include "../state_store.h"

namespace turms::client::driver::service {

class BaseService : private boost::noncopyable {
   public:
    explicit BaseService(boost::asio::io_context& ioContext, StateStore& stateStore);
    virtual ~BaseService() = default;

    virtual auto close() -> boost::future<void> = 0;
    virtual auto onDisconnected(const std::optional<std::exception>& exception) -> void = 0;

   protected:
    boost::asio::io_context& ioContext_;
    StateStore& stateStore_;
};

}  // namespace turms::client::driver::service

#endif  // TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H