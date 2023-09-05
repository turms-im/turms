#ifndef TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H
#define TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H

#include <boost/optional.hpp>

namespace turms {
namespace client {
namespace model {

struct SessionCloseInfo {
    int closeStatus;
    boost::optional<int> businessStatus;
    boost::optional<std::string> reason;
    boost::optional<std::exception> cause;
};

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_SESSION_CLOSE_INFO_H