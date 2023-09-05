#ifndef TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H
#define TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <unordered_set>

#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class ConversationService : private boost::noncopyable {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;

    template <typename T>
    using Response = model::Response<T>;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using GroupConversation = model::proto::GroupConversation;
    using PrivateConversation = model::proto::PrivateConversation;

   public:
    explicit ConversationService(TurmsClient& turmsClient);

    auto queryPrivateConversations(const std::unordered_set<int64_t>& targetIds)
        -> boost::future<Response<std::vector<PrivateConversation>>>;

    auto queryGroupConversations(const std::unordered_set<int64_t>& groupIds)
        -> boost::future<Response<std::vector<GroupConversation>>>;

    auto updatePrivateConversationReadDate(
        int64_t targetId, const time_point& readDate = std::chrono::system_clock::now())
        -> boost::future<Response<void>>;

    auto updateGroupConversationReadDate(
        int64_t groupId, const time_point& readDate = std::chrono::system_clock::now())
        -> boost::future<Response<void>>;

    auto updatePrivateConversationTypingStatus(int64_t targetId) -> boost::future<Response<void>>;

    auto updateGroupConversationTypingStatus(int64_t groupId) -> boost::future<Response<void>>;

   private:
    TurmsClient& turmsClient_;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_CONVERSATION_SERVICE_H