#ifndef TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H
#define TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H

#include <set>

namespace turms {
namespace client {
namespace model {

struct MessageAddition {
    bool isMentioned;
    std::set<int64_t> mentionedUserIds;
    std::set<int64_t> recalledMessageIds;
};

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H