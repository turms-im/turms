#ifndef TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H

#include <boost/asio.hpp>
#include <boost/chrono.hpp>
#include <boost/thread.hpp>
#include <boost/thread/future.hpp>
#include <stdexcept>

#include "base_service.h"
#include "turms/client/driver/state_store.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {
namespace driver {
namespace service {
class HeartbeatService : public BaseService,
                         private std::enable_shared_from_this<HeartbeatService> {
   public:
    HeartbeatService(boost::asio::io_context& ioContext,
                     StateStore& stateStore,
                     const boost::optional<int>& heartbeatIntervalMillis = boost::none);

    auto isRunning() const -> bool;

    auto start() -> void;

    auto stop(const boost::optional<std::exception>& exception = boost::none) -> void;

    auto send() -> boost::future<void>;

    auto completeHeartbeatPromises() -> void;

    auto rejectHeartbeatPromisesIfFail(const model::proto::TurmsNotification& notification) -> bool;

    auto close() -> boost::future<void> override;

    auto onDisconnected(const boost::optional<std::exception>& exception) -> void override;

   private:
    int heartbeatInterval_{0};
    std::chrono::duration<int, std::milli> heartbeatTimerInterval_;
    int64_t lastHeartbeatRequestDate_{0};
    boost::optional<boost::asio::steady_timer> heartbeatTimer_;
    std::vector<boost::promise<void>> heartbeatPromises_;

    static const int64_t kHeartbeatFailureRequestId = -100LL;
    static constexpr std::array<uint8_t, 1> kHeartbeat{0};

    auto sendHeartbeatForever() -> void;
    auto rejectHeartbeatRequests(const boost::optional<std::exception>& exception) -> void;
};
}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H