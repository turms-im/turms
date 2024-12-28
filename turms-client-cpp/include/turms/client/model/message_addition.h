#ifndef TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H
#define TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H

#include <cstdint>
#include <set>

namespace turms::client::model {

struct MessageAddition {
    bool isMentioned;
    std::set<int64_t> mentionedUserIds;
    std::set<int64_t> recalledMessageIds;
};

}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_MESSAGE_ADDITION_H