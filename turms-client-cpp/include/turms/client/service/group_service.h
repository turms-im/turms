#ifndef TURMS_CLIENT_SERVICE_GROUP_SERVICE_H
#define TURMS_CLIENT_SERVICE_GROUP_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <unordered_set>

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/new_group_join_question.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"
#include "turms/client/model/user.h"
#include "turms/client/time/time_util.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class GroupService : private boost::noncopyable {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;

    using ResponseException = exception::ResponseException;

    using User = model::User;
    template <typename T>
    using Response = model::Response<T>;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using Group = model::proto::Group;
    using GroupsWithVersion = model::proto::GroupsWithVersion;
    using GroupInvitationsWithVersion = model::proto::GroupInvitationsWithVersion;
    using GroupJoinRequestsWithVersion = model::proto::GroupJoinRequestsWithVersion;
    using GroupJoinQuestionsAnswerResult = model::proto::GroupJoinQuestionsAnswerResult;
    using GroupJoinQuestionsWithVersion = model::proto::GroupJoinQuestionsWithVersion;
    using GroupJoinQuestion = model::proto::GroupJoinQuestion;
    using GroupMemberRole = model::proto::GroupMemberRole;
    using GroupMembersWithVersion = model::proto::GroupMembersWithVersion;
    using LongsWithVersion = model::proto::LongsWithVersion;
    using UserInfo = model::proto::UserInfo;
    using UserInfosWithVersion = model::proto::UserInfosWithVersion;

   public:
    explicit GroupService(TurmsClient& turmsClient);

    auto createGroup(const absl::string_view& name,
                     const boost::optional<absl::string_view>& intro = boost::none,
                     const boost::optional<absl::string_view>& announcement = boost::none,
                     const boost::optional<int>& minScore = boost::none,
                     const boost::optional<time_point>& muteEndDate = boost::none,
                     const boost::optional<int64_t>& typeId = boost::none)
        -> boost::future<Response<int64_t>>;

    auto deleteGroup(int64_t groupId) -> boost::future<Response<void>>;

    auto updateGroup(int64_t groupId,
                     const boost::optional<absl::string_view>& name = boost::none,
                     const boost::optional<absl::string_view>& intro = boost::none,
                     const boost::optional<absl::string_view>& announcement = boost::none,
                     const boost::optional<int>& minScore = boost::none,
                     const boost::optional<int64_t>& typeId = boost::none,
                     const boost::optional<time_point>& muteEndDate = boost::none,
                     const boost::optional<int64_t>& successorId = boost::none,
                     const boost::optional<bool>& quitAfterTransfer = boost::none)
        -> boost::future<Response<void>>;

    auto transferOwnership(int64_t groupId, int64_t successorId, bool quitAfterTransfer = false)
        -> boost::future<Response<void>>;

    auto muteGroup(int64_t groupId, const time_point& muteEndDate) -> boost::future<Response<void>>;

    auto unmuteGroup(int64_t groupId) -> boost::future<Response<void>>;

    auto queryGroups(const std::unordered_set<int64_t>& groupIds,
                     const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<std::vector<Group>>>;

    auto queryJoinedGroupIds(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    auto queryJoinedGroupInfos(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupsWithVersion>>>;

    auto addGroupJoinQuestions(int64_t groupId,
                               const std::vector<model::NewGroupJoinQuestion>& questions)
        -> boost::future<Response<std::vector<int64_t>>>;

    auto deleteGroupJoinQuestions(const std::unordered_set<int64_t>& questionIds)
        -> boost::future<Response<void>>;

    auto updateGroupJoinQuestion(int64_t questionId,
                                 const boost::optional<absl::string_view>& question,
                                 const std::vector<std::string>& answers = {},
                                 const boost::optional<int> score = boost::none)
        -> boost::future<Response<void>>;

    auto blockUser(int64_t groupId, int64_t userId) -> boost::future<Response<void>>;

    auto unblockUser(int64_t groupId, int64_t userId) -> boost::future<Response<void>>;

    auto queryBlockedUserIds(int64_t groupId,
                             const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    auto queryBlockedUserInfos(int64_t groupId,
                               const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserInfosWithVersion>>>;

    // Group Enrollment

    auto createInvitation(int64_t groupId, int64_t inviteeId, const absl::string_view& content)
        -> boost::future<Response<int64_t>>;

    auto deleteInvitation(int64_t invitationId) -> boost::future<Response<void>>;

    auto queryInvitations(int64_t groupId,
                          const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupInvitationsWithVersion>>>;

    auto queryInvitations(bool areSentByMe,
                          const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupInvitationsWithVersion>>>;

    auto createJoinRequest(int64_t groupId, const absl::string_view& content)
        -> boost::future<Response<int64_t>>;

    auto deleteJoinRequest(int64_t requestId) -> boost::future<Response<void>>;

    auto queryJoinRequests(int64_t groupId,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinRequestsWithVersion>>>;

    auto querySentJoinRequests(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinRequestsWithVersion>>>;

    auto queryGroupJoinQuestions(int64_t groupId,
                                 bool withAnswers,
                                 const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinQuestionsWithVersion>>>;

    auto answerGroupQuestions(const std::unordered_map<int64_t, std::string>& questionIdToAnswer)
        -> boost::future<Response<GroupJoinQuestionsAnswerResult>>;

    auto addGroupMembers(int64_t groupId,
                         const std::unordered_set<int64_t>& userIds,
                         const boost::optional<absl::string_view>& name = boost::none,
                         const boost::optional<GroupMemberRole>& role = boost::none,
                         const boost::optional<time_point>& muteEndDate = boost::none)
        -> boost::future<Response<void>>;

    auto joinGroup(int64_t groupId, const boost::optional<absl::string_view>& name = boost::none)
        -> boost::future<Response<void>>;

    auto quitGroup(int64_t groupId,
                   const boost::optional<int64_t>& successorId = boost::none,
                   const boost::optional<bool>& quitAfterTransfer = boost::none)
        -> boost::future<Response<void>>;

    auto removeGroupMembers(int64_t groupId, const std::unordered_set<int64_t>& memberIds)
        -> boost::future<Response<void>>;

    auto updateGroupMemberInfo(int64_t groupId,
                               int64_t memberId,
                               const boost::optional<absl::string_view>& name = boost::none,
                               const boost::optional<GroupMemberRole>& role = boost::none,
                               const boost::optional<time_point>& muteEndDate = boost::none)
        -> boost::future<Response<void>>;

    auto muteGroupMember(int64_t groupId, int64_t memberId, const time_point& muteEndDate)
        -> boost::future<Response<void>>;

    auto unmuteGroupMember(int64_t groupId, int64_t memberId) -> boost::future<Response<void>>;

    auto queryGroupMembers(int64_t groupId,
                           bool withStatus,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupMembersWithVersion>>>;

    auto queryGroupMembersByMemberIds(int64_t groupId,
                                      const std::unordered_set<int64_t>& memberIds,
                                      bool withStatus = false)
        -> boost::future<Response<boost::optional<GroupMembersWithVersion>>>;

   private:
    TurmsClient& turmsClient_;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_GROUP_SERVICE_H