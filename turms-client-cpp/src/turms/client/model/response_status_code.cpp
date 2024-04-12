#include "turms/client/model/response_status_code.h"

namespace turms {
namespace client {
namespace model {

auto ResponseStatusCode::isSuccessCode(int businessCode) -> bool {
    return businessCode >= 1000 && businessCode <= 1099;
}

auto ResponseStatusCode::isErrorCode(int businessCode) -> bool {
    return !isSuccessCode(businessCode);
}

}  // namespace model
}  // namespace client
}  // namespace turms