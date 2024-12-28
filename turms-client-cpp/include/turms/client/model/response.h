#ifndef TURMS_CLIENT_MODEL_RESPONSE_H
#define TURMS_CLIENT_MODEL_RESPONSE_H

#include <chrono>
#include <optional>
#include <vector>

#include "turms/client/model/notification_util.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response_status_code.h"

namespace turms::client::model {
template <typename T>
class Response {
   public:
    Response(const std::chrono::time_point<std::chrono::system_clock> timestamp,
             const std::optional<int64_t> requestId,
             int code,
             const T& data)
        : timestamp_(timestamp),
          requestId_(requestId),
          code_(code),
          data_(data) {
    }

    explicit Response(const T& data)
        : timestamp_(std::chrono::system_clock::now()),
          requestId_(std::nullopt),
          code_(ResponseStatusCode::kOk),
          data_(data) {
    }

    explicit Response(T&& data)
        : timestamp_(std::chrono::system_clock::now()),
          requestId_(std::nullopt),
          code_(ResponseStatusCode::kOk),
          data_(std::move(data)) {
    }

    Response(const proto::TurmsNotification& notification,
             const std::function<T(const proto::TurmsNotification_Data&)>& dataTransformer)
        : timestamp_(std::chrono::system_clock::from_time_t(notification.timestamp())),
          requestId_(notification.has_request_id() ? std::optional(notification.request_id())
                                                   : std::nullopt),
          code_(notification.code()) {
        if (!notification.has_code()) {
            throw std::runtime_error(
                "Could not parse a success response from a notification without code");
        }
        if (notification::isError(notification)) {
            throw std::runtime_error(
                "Could not parse a success response from non-success notification");
        }
        try {
            data_ = dataTransformer(notification.data());
        } catch (const std::exception& e) {
            throw std::runtime_error("Failed to transform notification data: " +
                                     notification.data().DebugString() + ". Error: " + e.what());
        }
    }

    auto timestamp() -> std::chrono::time_point<std::chrono::system_clock>& {
        return timestamp_;
    }

    auto requestId() -> std::optional<int64_t>& {
        return requestId_;
    }

    auto code() const -> int {
        return code_;
    }

    static auto emptyList() -> Response<std::vector<T>> {
        return Response<std::vector<T>>(
            std::chrono::system_clock::now(), std::nullopt, ResponseStatusCode::kOk, {});
    }

   private:
    std::chrono::time_point<std::chrono::system_clock> timestamp_;
    std::optional<int64_t> requestId_;
    int code_{};
    T data_;
};

template <>
class Response<void> {
   public:
    Response()
        : timestamp_(std::chrono::system_clock::now()),
          requestId_(std::nullopt) {
    }

    explicit Response(const proto::TurmsNotification& notification)
        : timestamp_(std::chrono::system_clock::from_time_t(notification.timestamp())),
          requestId_(notification.has_request_id() ? std::optional(notification.request_id())
                                                   : std::nullopt),
          code_(notification.code()) {
    }

    auto timestamp() -> std::chrono::time_point<std::chrono::system_clock>& {
        return timestamp_;
    }

    auto requestId() -> std::optional<int64_t>& {
        return requestId_;
    }

    auto code() const -> int {
        return code_;
    }

   private:
    std::chrono::time_point<std::chrono::system_clock> timestamp_;
    std::optional<int64_t> requestId_;
    int code_{ResponseStatusCode::kOk};
};
}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_RESPONSE_H
