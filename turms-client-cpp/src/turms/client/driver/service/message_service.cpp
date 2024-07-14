#include "turms/client/driver/service/message_service.h"

namespace turms {
namespace client {
namespace driver {
namespace service {
MessageService::MessageService(boost::asio::io_context& ioContext,
                               StateStore& stateStore,
                               const boost::optional<int>& requestTimeout,
                               const boost::optional<int>& minRequestInterval)
    : BaseService(ioContext, stateStore),
      requestTimeout_(std::chrono::milliseconds{requestTimeout.get_value_or(60 * 1000)}),
      enableRequestTimeout_(requestTimeout_.count() > 0),
      minRequestInterval_(minRequestInterval.get_value_or(0)) {
}

auto MessageService::sendRequest(model::proto::TurmsRequest& request)
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

    auto now = std::chrono::system_clock::now().time_since_epoch().count();
    auto difference = now - stateStore_.lastRequestDate;
    auto isFrequent = minRequestInterval_ > 0 && difference <= minRequestInterval_;
    if (isFrequent) {
        return boost::make_exceptional_future<TurmsNotification>(
            ResponseException{model::ResponseStatusCode::kClientRequestsTooFrequent});
    }

    while (true) {
        auto requestId = generateRandomId();
        // Use "try_emplace" when upgrading to C++17
        auto result = idToRequest_.emplace(
            requestId, TurmsRequestContext{boost::promise<TurmsNotification>(), boost::none});
        if (!result.second) {
            continue;
        }
        TurmsRequestContext& context = result.first->second;
        boost::promise<TurmsNotification>& promise = context.promise;
        stateStore_.lastRequestDate = now;
        try {
            request.set_request_id(requestId);
            auto payload = std::make_shared<std::vector<uint8_t>>(request.ByteSizeLong());
            request.SerializeToArray(payload->data(), payload->size());
            stateStore_.tcp->writeVarIntLengthAndBytes(payload);
        } catch (const std::exception& e) {
            idToRequest_.erase(requestId);
            promise.set_exception(e);
            return promise.get_future();
        }

        if (enableRequestTimeout_) {
            boost::asio::steady_timer timer{ioContext_, requestTimeout_};
            timer.async_wait([weakThis = std::weak_ptr<MessageService>(shared_from_this()),
                              requestId](const boost::system::error_code& e) {
                if (e == boost::asio::error::operation_aborted) {
                    return;
                }
                auto sharedThis = weakThis.lock();
                if (sharedThis == nullptr) {
                    return;
                }
                auto& idToRequest = sharedThis->idToRequest_;
                auto it = idToRequest.find(requestId);
                if (it == idToRequest.end()) {
                    return;
                }
                it->second.promise.set_exception(
                    ResponseException{model::ResponseStatusCode::kRequestTimeout});
                idToRequest.erase(it);
            });
            context.timeoutTimer = std::move(timer);
        }
        return promise.get_future();
    }
}

auto MessageService::didReceiveNotification(const MessageService::TurmsNotification& notification)
    -> void {
    const bool isResponse = !notification.has_relayed_request() && notification.has_request_id();
    if (isResponse) {
        auto it = idToRequest_.find(notification.request_id());
        if (it != idToRequest_.end()) {
            auto& context = it->second;
            auto& promise = context.promise;
            if (notification.has_code()) {
                auto& timer = context.timeoutTimer;
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

auto MessageService::close() -> boost::future<void> {
    onDisconnected(boost::none);
    return boost::make_ready_future();
}

auto MessageService::onDisconnected(const boost::optional<std::exception>& exception) -> void {
    rejectRequestPromises(exception::ResponseException{
        model::ResponseStatusCode::kClientSessionHasBeenClosed, "", exception});
}

auto MessageService::generateRandomId() const -> int64_t {
    int64_t id;
    do {
        id = random::nextPositiveInt64();
    } while (idToRequest_.count(id) > 0);
    return id;
}

auto MessageService::notifyNotificationListeners(
    const MessageService::TurmsNotification& notification) const -> void {
    for (const auto& listener : notificationListeners_) {
        listener(notification);
    }
}

auto MessageService::rejectRequestPromises(const std::exception& exception) -> void {
    for (auto it = idToRequest_.begin(); it != idToRequest_.end();) {
        it = idToRequest_.erase(it);
        TurmsRequestContext& context = it->second;
        context.promise.set_exception(exception);
        if (context.timeoutTimer) {
            context.timeoutTimer->cancel();
        }
    }
}
}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms