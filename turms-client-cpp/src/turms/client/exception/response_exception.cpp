#include "turms/client/exception/response_exception.h"

namespace turms {
namespace client {
namespace exception {
ResponseException::ResponseException(int code,
                                     int64_t requestId,
                                     const std::string& reason,
                                     const boost::optional<std::exception>& cause)
    : std::runtime_error(reason.empty() ? "code: " + std::to_string(code)
                                        : "code: " + std::to_string(code) + ", reason: " + reason),
      code_(code),
      requestId_(requestId),
      reason_(reason),
      cause_(cause) {
}

ResponseException::ResponseException(int code,
                                     const std::string& reason,
                                     const boost::optional<std::exception>& cause)
    : std::runtime_error(reason.empty() ? "code: " + std::to_string(code)
                                        : "code: " + std::to_string(code) + ", reason: " + reason),
      code_(code),
      requestId_(boost::none),
      reason_(reason),
      cause_(cause) {
}

ResponseException::ResponseException(const model::proto::TurmsNotification& notification)
    : ResponseException{notification.code(),
                        notification.has_request_id() ? notification.request_id() : 0,
                        notification.has_reason() ? notification.reason() : ""} {
}

auto ResponseException::requestId() const noexcept -> const boost::optional<int64_t>& {
    return requestId_;
}

auto ResponseException::code() const noexcept -> int {
    return code_;
}

auto ResponseException::reason() const noexcept -> const std::string& {
    return reason_;
}

auto ResponseException::cause() const noexcept -> const boost::optional<std::exception>& {
    return cause_;
}

}  // namespace exception
}  // namespace client
}  // namespace turms