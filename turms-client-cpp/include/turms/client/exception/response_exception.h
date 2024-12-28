#ifndef TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H
#define TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H

#include <cstdint>
#include <optional>
#include <stdexcept>
#include <string>

#include "turms/client/model/proto/notification/turms_notification.pb.h"

namespace turms::client::exception {
class ResponseException : public std::runtime_error {
   public:
    ResponseException(int code,
                      int64_t requestId,
                      const std::string& reason = "",
                      const std::optional<std::exception>& cause = std::nullopt);

    explicit ResponseException(int code,
                               const std::string& reason = "",
                               const std::optional<std::exception>& cause = std::nullopt);

    explicit ResponseException(const model::proto::TurmsNotification& notification);

    auto requestId() const noexcept -> const std::optional<int64_t>&;

    auto code() const noexcept -> int;

    auto reason() const noexcept -> const std::string&;

    auto cause() const noexcept -> const std::optional<std::exception>&;

   private:
    int code_;
    std::optional<int64_t> requestId_;
    std::string reason_;
    std::optional<std::exception> cause_;
};
}  // namespace turms::client::exception

#endif  // TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H