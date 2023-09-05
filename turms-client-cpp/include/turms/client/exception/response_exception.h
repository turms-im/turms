#ifndef TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H
#define TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H

#include <boost/optional.hpp>
#include <cstdint>
#include <stdexcept>
#include <string>

#include "turms/client/model/proto/notification/turms_notification.pb.h"

namespace turms {
namespace client {
namespace exception {
class ResponseException : public std::runtime_error {
   public:
    ResponseException(int code,
                      int64_t requestId,
                      const std::string& reason = "",
                      const boost::optional<std::exception>& cause = boost::none);

    ResponseException(int code,
                      const std::string& reason = "",
                      const boost::optional<std::exception>& cause = boost::none);

    explicit ResponseException(const model::proto::TurmsNotification& notification);

    auto requestId() const noexcept -> const boost::optional<int64_t>&;

    auto code() const noexcept -> int;

    auto reason() const noexcept -> const std::string&;

    auto cause() const noexcept -> const boost::optional<std::exception>&;

   private:
    int code_;
    boost::optional<int64_t> requestId_;
    std::string reason_;
    boost::optional<std::exception> cause_;
};
}  // namespace exception
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_EXCEPTION_RESPONSE_EXCEPTION_H