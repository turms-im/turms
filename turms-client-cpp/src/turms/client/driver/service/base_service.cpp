#include "turms/client/driver/service/base_service.h"

namespace turms::client::driver::service {

BaseService::BaseService(boost::asio::io_context& ioContext, StateStore& stateStore)
    : ioContext_(ioContext),
      stateStore_(stateStore) {
}

}  // namespace turms::client::driver::service