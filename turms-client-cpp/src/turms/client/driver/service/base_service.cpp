#include "turms/client/driver/service/base_service.h"

namespace turms {
namespace client {
namespace driver {
namespace service {

BaseService::BaseService(boost::asio::io_context& ioContext, StateStore& stateStore)
    : ioContext_(ioContext),
      stateStore_(stateStore) {
}

}  // namespace service
}  // namespace driver
}  // namespace client
}  // namespace turms