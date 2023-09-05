#ifndef TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H

#include <boost/core/noncopyable.hpp>
#include <boost/optional.hpp>
#include <boost/thread/future.hpp>

#include "../state_store.h"

namespace turms {
namespace client {
namespace driver {
namespace service {

class BaseService : private boost::noncopyable {
   public:
    explicit BaseService(boost::asio::io_context& ioContext, StateStore& stateStore);
    virtual ~BaseService() = default;

    virtual auto close() -> boost::future<void> = 0;
    virtual auto onDisconnected(const boost::optional<std::exception>& exception) -> void = 0;

   protected:
    boost::asio::io_context& ioContext_;
    StateStore& stateStore_;
};

}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_SERVICE_BASE_SERVICE_H