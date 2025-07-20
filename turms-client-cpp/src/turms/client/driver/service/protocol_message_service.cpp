#include "turms/client/driver/service/protocol_message_service.h"

#include "turms/client/model/notification_util.h"
#include "turms/client/model/response_status_code.h"
#include "turms/client/random/random_util.h"

namespace turms::client::driver::service {
ProtocolMessageService::ProtocolMessageService(boost::asio::io_context& ioContext,
                                               StateStore& stateStore,
                                               const std::optional<int>& requestTimeout,
                                               const std::optional<int>& minRequestInterval)
    : BaseService(ioContext, stateStore),
      requestTimeout_(std::chrono::milliseconds{requestTimeout.value_or(60 * 1000)}),
      enableRequestTimeout_(requestTimeout_.count() > 0),
      minRequestInterval_(minRequestInterval.value_or(0)) {
}

auto ProtocolMessageService::sendRequest(model::proto::TurmsRequest& request)
    -> boost::future<TurmsNotification> {
    if (request.has_create_session_request()) {
        if (stateStore_.isSessionOpen) {
            return boost::make_exceptional_future<TurmsNotification>(
                ResponseException{model::ResponseStatusCode::kClientSessionAlreadyEstablished});
        }
    } else if (!stateStore_.isConnected || !stateStore_.isSessionOpen) {
        return boost::make_exceptional_future<TurmsNotification>(
            ResponseException{model::ResponseStatusCode::kClientSessionHasBeenClosed});
    }

    const auto now = std::chrono::system_clock::now().time_since_epoch().count();
    if (minRequestInterval_ > 0 && now - stateStore_.lastRequestDate <= minRequestInterval_) {
        return boost::make_exceptional_future<TurmsNotification>(
            ResponseException{model::ResponseStatusCode::kClientRequestsTooFrequent});
    }

    while (true) {
        auto requestId = generateRandomId();
        const auto& [pair, inserted] = idToRequest_.try_emplace(
            requestId, TurmsRequestContext{boost::promise<TurmsNotification>(), std::nullopt});
        if (!inserted) {
            continue;
        }
        auto& [promise, timeoutTimer] = pair->second;
        stateStore_.lastRequestDate = now;
        try {
            request.set_request_id(requestId);
            const auto payload = std::make_shared<std::vector<uint8_t>>(request.ByteSizeLong());
            request.SerializeToArray(payload->data(), payload->size());
            stateStore_.tcp->writeVarIntLengthAndBytes(payload);
        } catch (const std::exception& e) {
            idToRequest_.erase(requestId);
            promise.set_exception(e);
            return promise.get_future();
        }

        if (enableRequestTimeout_) {
            boost::asio::steady_timer timer{ioContext_, requestTimeout_};
            timer.async_wait([weakThis = std::weak_ptr(shared_from_this()),
                              requestId](const boost::system::error_code& e) {
                if (e == boost::asio::error::operation_aborted) {
                    return;
                }
                const auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    return;
                }
                auto& idToRequest = sharedThis->idToRequest_;
                const auto& it = idToRequest.find(requestId);
                if (it == idToRequest.end()) {
                    return;
                }
                it->second.promise.set_exception(
                    ResponseException{model::ResponseStatusCode::kRequestTimeout});
                idToRequest.erase(it);
            });
            timeoutTimer = std::move(timer);
        }
        return promise.get_future();
    }
}

auto ProtocolMessageService::didReceiveNotification(const TurmsNotification& notification) -> void {
    if (!notification.has_relayed_request() && notification.has_request_id()) {
        if (const auto& it = idToRequest_.find(notification.request_id());
            it != idToRequest_.end()) {
            auto& [promise, timer] = it->second;
            if (notification.has_code()) {
                if (timer) {
                    timer->cancel();
                }
                if (model::notification::isSuccess(notification)) {
                    promise.set_value(notification);
                } else {
                    promise.set_exception(ResponseException{notification});
                }
            } else {
                promise.set_exception(ResponseException{
                    model::ResponseStatusCode::kInvalidNotification, "The code is missing"});
            }
            idToRequest_.erase(it);
        }
    }
    notifyNotificationListeners(notification);
}

auto ProtocolMessageService::close() -> boost::future<void> {
    onDisconnected(std::nullopt);
    return boost::make_ready_future();
}

auto ProtocolMessageService::onDisconnected(const std::optional<std::exception>& exception)
    -> void {
    rejectRequestPromises(exception::ResponseException{
        model::ResponseStatusCode::kClientSessionHasBeenClosed, "", exception});
}

auto ProtocolMessageService::generateRandomId() const -> int64_t {
    int64_t id;
    do {
        id = random::nextPositiveInt64();
    } while (idToRequest_.count(id) > 0);
    return id;
}

auto ProtocolMessageService::notifyNotificationListeners(
    const TurmsNotification& notification) const -> void {
    for (const auto& listener : notificationListeners_) {
        listener(notification);
    }
}

auto ProtocolMessageService::rejectRequestPromises(const std::exception& exception) -> void {
    for (auto it = idToRequest_.begin(); it != idToRequest_.end();) {
        it = idToRequest_.erase(it);
        auto& [promise, timer] = it->second;
        promise.set_exception(exception);
        if (timer) {
            timer->cancel();
        }
    }
}
}  // namespace turms::client::driver::service