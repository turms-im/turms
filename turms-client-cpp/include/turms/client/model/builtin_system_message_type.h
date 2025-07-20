#ifndef TURMS_CLIENT_MODEL_BUILTIN_SYSTEM_MESSAGE_TYPE_H
#define TURMS_CLIENT_MODEL_BUILTIN_SYSTEM_MESSAGE_TYPE_H

namespace turms::client::model {

enum class BuiltinSystemMessageType {
    kNormal = 0,
    kRecallMessage = 1,
};

}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_BUILTIN_SYSTEM_MESSAGE_TYPE_H