#include "turms/client/driver/service/heartbeat_service.h"

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/response_status_code.h"

namespace turms {
namespace client {
namespace driver {
namespace service {

HeartbeatService::HeartbeatService(boost::asio::io_context& ioContext,
                                   StateStore& stateStore,
                                   const boost::optional<int>& heartbeatIntervalMillis)
    : BaseService(ioContext, stateStore),
      heartbeatInterval_((heartbeatIntervalMillis && *heartbeatIntervalMillis > 0)
                             ? *heartbeatIntervalMillis
                             : 120 * 1000),
      heartbeatTimerInterval_(std::max(1L, heartbeatInterval_ / 10L)) {
}

auto HeartbeatService::isRunning() const -> bool {
    return heartbeatTimer_.has_value();
}

void HeartbeatService::start() {
    if (isRunning()) {
        return;
    }
    heartbeatTimer_ = boost::asio::steady_timer{ioContext_, heartbeatTimerInterval_};
    sendHeartbeatForever();
}

void HeartbeatService::sendHeartbeatForever() {
    int64_t now = time::nowMillis();
    int64_t difference = now - std::max(stateStore_.lastRequestDate, lastHeartbeatRequestDate_);
    if (difference > heartbeatInterval_) {
        // TODO: handle exception
        send();
        lastHeartbeatRequestDate_ = now;
    }
    heartbeatTimer_->expires_after(std::chrono::milliseconds{heartbeatTimerInterval_});
    heartbeatTimer_->async_wait([weakThis = std::weak_ptr<HeartbeatService>(shared_from_this())](
                                    const boost::system::error_code& e) {
        if (e == boost::asio::error::operation_aborted) {
            return;
        }
        auto sharedThis = weakThis.lock();
        if (sharedThis == nullptr) {
            return;
        }
        sharedThis->sendHeartbeatForever();
    });
}

void HeartbeatService::stop(const boost::optional<std::exception>& exception) {
    if (heartbeatTimer_.has_value()) {
        heartbeatTimer_->cancel();
    }
    rejectHeartbeatRequests(exception);
}

auto HeartbeatService::send() -> boost::future<void> {
    if (!stateStore_.isConnected || !stateStore_.isSessionOpen) {
        return boost::make_exceptional_future<void>(
            exception::ResponseException{model::ResponseStatusCode::kClientSessionHasBeenClosed});
    }
    transport::TcpClient* tcp = stateStore_.tcp.get();
    if (tcp == nullptr) {
        return boost::make_exceptional_future<void>(
            exception::ResponseException{model::ResponseStatusCode::kClientSessionHasBeenClosed});
    }
    boost::promise<void> promise;
    boost::future<void> future = promise.get_future();
    heartbeatPromises_.push_back(std::move(promise));
    tcp->write(std::make_shared<std::array<uint8_t, 1>>(HeartbeatService::kHeartbeat));
    return future;
}

auto HeartbeatService::rejectHeartbeatRequests(const boost::optional<std::exception>& exception)
    -> void {
    while (!heartbeatPromises_.empty()) {
        boost::promise<void>& promise = heartbeatPromises_.back();
        heartbeatPromises_.pop_back();
        if (exception) {
            promise.set_exception(exception);
        } else {
            promise.set_value();
        }
    }
}

auto HeartbeatService::completeHeartbeatPromises() -> void {
    while (!heartbeatPromises_.empty()) {
        boost::promise<void>& promise = heartbeatPromises_.back();
        heartbeatPromises_.pop_back();
        promise.set_value();
    }
}

auto HeartbeatService::rejectHeartbeatPromisesIfFail(
    const model::proto::TurmsNotification& notification) -> bool {
    if (notification.has_request_id() && notification.request_id() == kHeartbeatFailureRequestId) {
        rejectHeartbeatRequests(exception::ResponseException(notification));
        return true;
    }
    return false;
}

boost::future<void> HeartbeatService::close() {
    onDisconnected(boost::none);
    return boost::make_ready_future();
}

void HeartbeatService::onDisconnected(const boost::optional<std::exception>& exception) {
    stop(exception);
    rejectHeartbeatRequests(exception);
}

}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms