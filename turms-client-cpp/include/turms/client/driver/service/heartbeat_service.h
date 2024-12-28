#ifndef TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H
#define TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H

#include <boost/asio.hpp>
#include <boost/thread/future.hpp>

#include "base_service.h"
#include "turms/client/driver/state_store.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"

namespace turms::client::driver::service {
class HeartbeatService : public BaseService,
                         private std::enable_shared_from_this<HeartbeatService> {
   public:
    HeartbeatService(boost::asio::io_context& ioContext,
                     StateStore& stateStore,
                     const std::optional<int>& heartbeatIntervalMillis = std::nullopt);

    auto isRunning() const -> bool;

    auto start() -> void;

    auto stop(const std::optional<std::exception>& exception = std::nullopt) -> void;

    auto send() -> boost::future<void>;

    auto completeHeartbeatPromises() -> void;

    auto rejectHeartbeatPromisesIfFail(const model::proto::TurmsNotification& notification) -> bool;

    auto close() -> boost::future<void> override;

    auto onDisconnected(const std::optional<std::exception>& exception) -> void override;

   private:
    int heartbeatInterval_{0};
    std::chrono::duration<int, std::milli> heartbeatTimerInterval_;
    int64_t lastHeartbeatRequestDate_{0};
    std::optional<boost::asio::steady_timer> heartbeatTimer_;
    std::vector<boost::promise<void>> heartbeatPromises_;

    static constexpr int64_t kHeartbeatFailureRequestId = -100LL;
    static constexpr std::array<uint8_t, 1> kHeartbeat{0};

    auto sendHeartbeatForever() -> void;
    auto rejectHeartbeatRequests(const std::optional<std::exception>& exception) -> void;
};
}  // namespace turms::client::driver::service

#endif  // TURMS_CLIENT_DRIVER_SERVICE_HEARTBEAT_SERVICE_H