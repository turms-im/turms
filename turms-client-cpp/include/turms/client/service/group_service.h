#ifndef TURMS_CLIENT_SERVICE_GROUP_SERVICE_H
#define TURMS_CLIENT_SERVICE_GROUP_SERVICE_H

#include <absl/strings/string_view.h>

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
    using ResponseAction = model::proto::ResponseAction;
    using UserInfo = model::proto::UserInfo;
    using UserInfosWithVersion = model::proto::UserInfosWithVersion;

   public:
    explicit GroupService(TurmsClient& turmsClient);

    /**
     * Create a new group.
     * The logged-in user will become the group creator and owner.
     *
     * Common Scenarios:
     * * To add new group members, you can use methods like addGroupMembers.
     *
     * Authorization:
     * * If the groups owned by the logged-in user has exceeded the limit,
     *   throws ResponseException with the code ResponseStatusCode::kMaxOwnedGroupsReached.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-created.notify-requester-other-online-sessions` is true
     * (true by default), the server will send a create group notification to all other online
     * sessions of the logged-in user actively.
     *
     * @param name the group name.
     * @param intro the group introduction.
     * @param announcement the group announcement.
     * @param minScore the group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * @param typeId the group type ID.
     * If null, the default group type configured in turms-service will be used.
     *
     * Authorization:
     * * If the group type ID does not exist,
     *   throws ResponseException with the code
     * ResponseStatusCode::kCreateGroupWithNonexistentGroupType.
     * * If the logged-in user does not have the permission to create the group with typeId,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNoPermissionToCreateGroupWithGroupType.
     * @return the group ID.
     * @throws ResponseException if an error occurs.
     */
    auto createGroup(const absl::string_view& name,
                     const boost::optional<absl::string_view>& intro = boost::none,
                     const boost::optional<absl::string_view>& announcement = boost::none,
                     const boost::optional<int>& minScore = boost::none,
                     const boost::optional<time_point>& muteEndDate = boost::none,
                     const boost::optional<int64_t>& typeId = boost::none)
        -> boost::future<Response<int64_t>>;

    /**
     * Delete the target group.
     *
     * Authorization:
     * * If the logged-in user is not the group owner, or the target group does not exist,
     *   throws ResponseException with the code ResponseStatusCode::kNotGroupOwnerToDeleteGroup.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-deleted.notify-requester-other-online-sessions` is true
     * (true by default), the server will send a delete group notification to all other online
     * sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`
     *   is true (true by default),
     *   the server will send a delete group notification to all group members of the target group.
     *
     * @throws ResponseException if an error occurs.
     */
    auto deleteGroup(int64_t groupId) -> boost::future<Response<void>>;

    /**
     * Update the target group.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-updated.notify-requester-other-online-sessions` the server
     * will send an update group notification to all other online sessions of the logged-in user
     * actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send an update group notification to all group members of the target group
     * actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param name the new group name.
     * If null, the group name will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group name depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group name,
     *   throws ResponseException with the code ResponseStatusCode::kNotGroupMemberToUpdateGroupInfo
     *   or ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupInfo
     *   or ResponseStatusCode::kNotGroupOwnerToUpdateGroupInfo.
     * @param intro the new group introduction.
     * If null, the group introduction will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group introduction depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group
     * introduction, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupMemberToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerToUpdateGroupInfo.
     * @param announcement the new group announcement.
     * If null, the group announcement will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group announcement depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group
     * announcement, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupMemberToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerToUpdateGroupInfo.
     * @param minScore the new group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * If null, the group minimum score will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group minimum score depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group minimum
     * score, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupMemberToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupInfo or
     * ResponseStatusCode::kNotGroupOwnerToUpdateGroupInfo.
     * @param typeId the new group type ID.
     * If null, the group type ID will not be changed.
     *
     * Authorization:
     * * If the server property `turms.service.group.allow-group-owner-change-group-type`
     *   is true (false by default), the logged-in user can change the group type.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kUpdatingGroupTypeIsDisabled.
     * * If the logged-in user is not the group owner,
     *   throws ResponseException with the code ResponseStatusCode::kNotGroupOwnerToUpdateGroupType.
     * * If the logged-in user is not allowed to use the group type,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNoPermissionToUpdateGroupToGroupType.
     * * If typeId doesn't exist, throws ResponseException with the code
     * ResponseStatusCode::kUpdateGroupToNonexistentGroupType.
     * @param muteEndDate the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember will be thrown.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to
     * the successorId, throws ResponseException with the code
     * ResponseStatusCode::kGroupOwnerQuitWithoutSpecifyingSuccessor otherwise. And the successor
     * will become the group owner. The successor must already be a member of the group, throws
     * ResponseException with the code ResponseStatusCode::kGroupSuccessorNotGroupMember otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   throws ResponseException with the code ResponseStatusCode::kNotGroupOwnerToTransferGroup.
     * @throws ResponseException if an error occurs.
     */
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

    /**
     * Transfer the group ownership.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-updated.notify-requester-other-online-sessions` the server
     * will send a update group notification to all other online sessions of the logged-in user
     * actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group
     * actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to
     * the successorId, throws ResponseException with the code
     * ResponseStatusCode::kGroupOwnerQuitWithoutSpecifyingSuccessor otherwise. And the successor
     * will become the group owner. The successor must already be a member of the group, throws
     * ResponseException with the code ResponseStatusCode::kGroupSuccessorNotGroupMember otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     * Authorization: If the logged-in user is not the owner of the group,
     * throws ResponseException with the code ResponseStatusCode::kNotGroupOwnerToTransferGroup.
     * @throws ResponseException if an error occurs.
     */
    auto transferOwnership(int64_t groupId, int64_t successorId, bool quitAfterTransfer = false)
        -> boost::future<Response<void>>;

    /**
     * Mute the target group.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-updated.notify-requester-other-online-sessions` the server
     * will send a update group notification to all other online sessions of the logged-in user
     * actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group
     * actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @param muteEndDate the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember.
     * @throws ResponseException if an error occurs.
     */
    auto muteGroup(int64_t groupId, const time_point& muteEndDate) -> boost::future<Response<void>>;

    /**
     * Unmute the target group.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-updated.notify-requester-other-online-sessions` the server
     * will send a update group notification to all other online sessions of the logged-in user
     * actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group
     * actively.
     *
     * @param groupId the target group ID to find the group for updating.
     * @throws ResponseException if an error occurs.
     */
    auto unmuteGroup(int64_t groupId) -> boost::future<Response<void>>;

    /**
     * Find groups.
     *
     * @param groupIds the target group IDs for finding groups.
     * @param lastUpdatedDate the last updated date of groups on local.
     * The server will only return groups that are updated after lastUpdatedDate.
     * If null, all groups will be returned.
     * @return a list of groups.
     * @throws ResponseException if an error occurs.
     */
    auto queryGroups(const std::unordered_set<int64_t>& groupIds,
                     const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<std::vector<Group>>>;

    /**
     * Search for groups.
     *
     * @param name search for groups whose name matches name.
     * @param highlight whether to highlight the name.
     * If true, the highlighted parts of the name will be paired with '\u0002' and '\u0003'.
     * @param skip the number of groups to skip.
     * @param limit the max number of groups to return.
     * @return a list of groups sorted in descending relevance.
     * @throws ResponseException if an error occurs.
     */
    auto searchGroups(const std::string& name,
                      bool highlight = false,
                      const boost::optional<int>& skip = boost::none,
                      const boost::optional<int>& limit = boost::none)
        -> boost::future<Response<std::vector<Group>>>;

    /**
     * Find group IDs that the logged-in user has joined.
     *
     * @param lastUpdatedDate the last updated date of group IDs that the logged-in user has joined
     * stored locally. The server will only return group IDs that are updated after lastUpdatedDate.
     * If null, all group IDs will be returned.
     * @return a list of group IDs and the version.
     * Note: The version can be used to update the last updated date on local.
     * @throws ResponseException if an error occurs.
     */
    auto queryJoinedGroupIds(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    /**
     * Find groups that the logged-in user has joined.
     *
     * @param lastUpdatedDate the last updated date of groups that the logged-in user has joined
     * stored locally. The server will only return groups that are updated after lastUpdatedDate. If
     * null, all groups will be returned.
     * @throws ResponseException if an error occurs.
     */
    auto queryJoinedGroupInfos(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupsWithVersion>>>;

    /**
     * Add group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can add group membership questions.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToCreateGroupQuestion.
     * * Only the group that use `question` as the join strategy can add group membership questions.
     *   Otherwise, throws ResponseException with the code
     *   ResponseStatusCode::kCreateGroupQuestionForGroupUsingJoinRequest
     *   or ResponseStatusCode::kCreateGroupQuestionForGroupUsingInvitation
     *   or ResponseStatusCode::kCreateGroupQuestionForGroupUsingMembershipRequest.
     * * If the group has been deleted,
     *   throws ResponseException with the code
     * ResponseStatusCode::kCreateGroupQuestionForInactiveGroup.
     *
     * @param groupId the target group ID.
     * @param questions the group membership questions.
     * @return new group questions IDs.
     * @throws ResponseException if an error occurs.
     */
    auto addGroupJoinQuestions(int64_t groupId,
                               const std::vector<model::NewGroupJoinQuestion>& questions)
        -> boost::future<Response<std::vector<int64_t>>>;

    /**
     * Delete group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can delete group membership questions.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToDeleteGroupQuestion.
     *
     * @param groupId the target group ID.
     * @param questionIds the group membership question IDs.
     * @throws ResponseException if an error occurs.
     */
    auto deleteGroupJoinQuestions(const std::unordered_set<int64_t>& questionIds)
        -> boost::future<Response<void>>;

    /**
     * Update group join/membership questions.
     *
     * Authorization:
     * * Only the group owner or group managers can update group membership questions.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupQuestion.
     *
     * @param questionId the target question ID.
     * @param question the question.
     * If null, the question will not be updated.
     * @param answers the answers.
     * If null, the answers will not be updated.
     * @param score the score.
     * If null, the score will not be updated.
     * @throws ResponseException if an error occurs.
     */
    auto updateGroupJoinQuestion(int64_t questionId,
                                 const boost::optional<absl::string_view>& question,
                                 const std::vector<std::string>& answers = {},
                                 const boost::optional<int> score = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Block a user in the group.
     * If the logged-in user is a group member, the server will delete the group member
     * automatically.
     *
     * Authorization:
     * * Only the group owner or group managers can block users.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToAddBlockedUser.
     * * If the logged-in user trys to block themselves,
     *   throws ResponseException with the code ResponseStatusCode::kCannotBlockOneself.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-blocked-user-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a block user notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-blocked-user-added.notify-blocked-user`, is true (false by
     * default), the server will send a block user notification to the target user actively.
     * * If the server property
     * `turms.service.notification.group-blocked-user-added.notify-group-members` is true (false by
     * default), the server will send a block user notification to all group members of the target
     * group actively.
     *
     * @param groupId the target group ID.
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    auto blockUser(int64_t groupId, int64_t userId) -> boost::future<Response<void>>;

    /**
     * Unblock a user in the group.
     *
     * Authorization:
     * * Only the group owner or group managers can unblock users.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToRemoveBlockedUser.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-blocked-user-removed.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a unblock user notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-blocked-user-removed.notify-blocked-user`, is true (false
     * by default), the server will send a unblock user notification to the target user actively.
     * * If the server property
     * `turms.service.notification.group-blocked-user-removed.notify-group-members` is true (false
     * by default), the server will send a unblock user notification to all group members of the
     * target group actively.
     *
     * @param groupId the target group ID.
     * @param userId the target user ID.
     * @throws ResponseException if an error occurs.
     */
    auto unblockUser(int64_t groupId, int64_t userId) -> boost::future<Response<void>>;

    /**
     * Find blocked user IDs.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of blocked user IDs stored locally.
     * The server will only return blocked user IDs that are updated after lastUpdatedDate.
     * If null, all blocked user IDs will be returned.
     * @throws ResponseException if an error occurs.
     */
    auto queryBlockedUserIds(int64_t groupId,
                             const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<LongsWithVersion>>>;

    /**
     * Find blocked user infos.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of blocked user infos stored locally.
     * The server will only return blocked user infos that are updated after lastUpdatedDate.
     * If null, all blocked user infos will be returned.
     * @throws ResponseException if an error occurs.
     */
    auto queryBlockedUserInfos(int64_t groupId,
                               const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<UserInfosWithVersion>>>;

    // Group Enrollment

    /**
     * Create an invitation.
     *
     * Authorization:
     * * If inviteeId is already a group member,
     *   throws ResponseException with the code
     * ResponseStatusCode::kSendGroupInvitationToGroupMember.
     * * Depending on the group join strategy, if the group do not use the invitation strategy
     *   throws ResponseException with the code
     *   ResponseStatusCode::kNotGroupOwnerToSendGroupInvitation,
     *   ResponseStatusCode::kNotGroupOwnerOrManagerToSendGroupInvitation,
     *   or ResponseStatusCode::kNotGroupMemberToSendGroupInvitation.
     * * If the group allows adding users as new group members without users' approval,
     *   throws ResponseException with the code
     * ResponseStatusCode::kSendGroupInvitationToGroupNotRequiringUsersApproval.
     * * If the group does not exist,
     *   throws ResponseException with the code ResponseStatusCode::kAddUserToInactiveGroup.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-invitation-added.notify-requester-other-online-sessions` is
     * true (true by default), the server will send a new invitation notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-invitation-added.notify-group-owner-and-managers` is true
     * (true by default), the server will send a new invitation notification to the group owner and
     * managers actively.
     * * If the server property
     * `turms.service.notification.group-invitation-added.notify-group-members`, is true (false by
     * default), the server will send a new invitation notification to all group members of the
     * target group actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-invitee`,
     *   is true (true by default), the server will send a new invitation notification to the target
     * user actively.
     *
     * @param groupId the target group ID.
     * @param inviteeId the target user ID.
     * @param content the invitation content.
     * @return the invitation ID.
     * @throws ResponseException if an error occurs.
     */
    auto createInvitation(int64_t groupId, int64_t inviteeId, const absl::string_view& content)
        -> boost::future<Response<int64_t>>;

    /**
     * Delete/Recall an invitation.
     *
     * Authorization:
     * * If the server property
     * `turms.service.group.invitation.allow-recall-pending-invitation-by-sender` is true (false by
     * default), the logged-in user can recall pending invitations sent by themselves. Otherwise,
     * throws ResponseException.
     * * If the server property
     * `turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager` is true
     * (false by default), the logged-in user can recall pending invitations only if they are the
     * group owner or manager of the invitation. Otherwise, throws ResponseException.
     * * For the above two cases, the following codes will be thrown according to different
     * properties: ResponseStatusCode::kRecallingGroupInvitationIsDisabled if the above two
     * properties are false. ResponseStatusCode::kNotGroupOwnerOrManagerToRecallGroupInvitation,
     *   ResponseStatusCode::kNotGroupOwnerOrManagerOrSenderToRecallGroupInvitation
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws ResponseException with the code
     * ResponseStatusCode::kRecallNonPendingGroupInvitation.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-invitation-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete invitation notification to all
     * other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-invitation-recalled.notify-group-owner-and-managers` is
     * true (true by default), the server will send a delete invitation notification to the group
     * owner and managers actively.
     * * If the server property
     * `turms.service.notification.group-invitation-recalled.notify-group-members`, is true (false
     * by default), the server will send a delete invitation notification to all group members of
     * the target group actively.
     * * If the server property
     * `turms.service.notification.group-invitation-recalled.notify-invitee`, is true (true by
     * default), the server will send a delete invitation notification to the target user actively.
     *
     * @throws ResponseException if an error occurs.
     */
    auto deleteInvitation(int64_t invitationId) -> boost::future<Response<void>>;

    /**
     * Reply to a group invitation.
     *
     * If the logged-in user accepts an invitation sent by a group,
     * the user will become a group member automatically.
     *
     * Authorization:
     * * If the logged-in user is not the invitee of the group invitation,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotInviteeToUpdateGroupInvitation.
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws ResponseException with the code
     * ResponseStatusCode::kUpdateNonPendingGroupInvitation.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-invitation-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group invitation notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-invitation-replied.notify-group-invitation-inviter`, is
     * true (true by default), the server will send a reply group invitation notification to the
     * group join request sender actively.
     * * If the server property
     * `turms.service.notification.group-invitation-replied.notify-group-members`, is true (false by
     * default), the server will send a reply group invitation notification to all group members of
     * the target group actively.
     * * If the server property
     * `turms.service.notification.group-invitation-replied.notify-group-owner-and-managers`, is
     * true (true by default), the server will send a reply group invitation notification to the
     * group owner and managers actively.
     *
     * @param invitationId the invitation ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    auto replyInvitation(int64_t invitationId,
                         ResponseAction responseAction,
                         const absl::string_view& reason) -> boost::future<Response<void>>;

    /**
     * Find invitations.
     *
     * @param lastUpdatedDate the last updated date of invitations stored locally.
     * The server will only return invitations that are updated after lastUpdatedDate.
     * If null, all invitations will be returned.
     * @return a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryInvitations(int64_t groupId,
                          const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupInvitationsWithVersion>>>;

    /**
     * Find invitations.
     *
     * @param areSentByMe whether the invitations are sent by me.
     * @param lastUpdatedDate the last updated date of invitations stored locally.
     * The server will only return invitations that are updated after lastUpdatedDate.
     * If null, all invitations will be returned.
     * @return a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryInvitations(bool areSentByMe,
                          const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupInvitationsWithVersion>>>;

    /**
     * Create a group join/membership request.
     *
     * Authorization:
     * * If the logged-in user has been blocked by the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kBlockedUserSendGroupJoinRequest.
     * * If the logged-in user trys to send a join request to the group
     *   in which they are already a member,
     *   throws ResponseException with the code
     * ResponseStatusCode::kGroupMemberSendGroupJoinRequest.
     * * If the group does not allow group join requests,
     *   throws ResponseException with the code:
     *   ResponseStatusCode::kSendGroupJoinRequestToGroupUsingInvitation,
     *   ResponseStatusCode::kSendGroupJoinRequestToGroupUsingMembershipRequest,
     *   or ResponseStatusCode::kSendGroupJoinRequestToGroupUsingQuestion.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-join-request-created.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a group membership request notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-join-request-created.notify-group-owner-and-managers`, is
     * true (true by default), the server will send a group membership request notification to the
     * group owner and managers actively.
     * * If the server property
     * `turms.service.notification.group-join-request-created.notify-group-members` is true (false
     * by default), the server will send a group membership request notification to all group
     * members of the target group actively.
     *
     * @param groupId the target group ID.
     * @param content the request content.
     * @return the request ID.
     * @throws ResponseException if an error occurs.
     */
    auto createJoinRequest(int64_t groupId, const absl::string_view& content)
        -> boost::future<Response<int64_t>>;

    /**
     * Delete/Recall a group join/membership request.
     *
     * Authorization:
     * * If the server property
     * `turms.service.group.join-request.allow-recall-pending-join-request-by-sender` is true (false
     * by default), the logged-in user can recall pending join requests sent by themselves.
     *   Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kRecallingGroupJoinRequestIsDisabled.
     * * If the logged-in user is not the sender of the group join request,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotSenderToRecallGroupJoinRequest.
     * * If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws ResponseException with the code
     * ResponseStatusCode::kRecallNonPendingGroupJoinRequest.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-join-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete join request notification to all
     * other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-join-request-recalled.notify-group-owner-and-managers` is
     * true (true by default), the server will send a delete join request notification to the group
     * owner and managers actively.
     * * If the server property
     * `turms.service.notification.group-join-request-recalled.notify-group-members`, is true (false
     * by default), the server will send a delete join request notification to all group members of
     * the target group actively.
     *
     * @throws ResponseException if an error occurs.
     */
    auto deleteJoinRequest(int64_t requestId) -> boost::future<Response<void>>;

    /**
     * Reply a group join/membership request.
     *
     * If the logged-in user accepts/approves a join request sent by a user,
     * the user will become a group member automatically.
     *
     * Authorization:
     * 1. If the logged-in user is not the group owner or manager of the group,
     * throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupJoinRequest.
     * 2. If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     * throws ResponseException with the code ResponseStatusCode::kUpdateNonPendingGroupJoinRequest.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-join-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group join request notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-join-request-replied.notify-group-join-request-sender`, is
     * true (true by default), the server will send a reply group join request notification to the
     * group join request sender actively.
     * * If the server property
     * `turms.service.notification.group-join-request-replied.notify-group-members`, is true (false
     * by default), the server will send a reply group join request notification to all group
     * members of the target group actively.
     * * If the server property
     * `turms.service.notification.group-join-request-replied.notify-group-owner-and-managers`, is
     * true (true by default), the server will send a reply group join request notification to the
     * group owner and managers actively.
     *
     * @param requestId the target group join request ID.
     * @param responseAction the response action.
     * @param reason the reason of the response.
     * @throws ResponseException if an error occurs.
     */
    auto replyJoinRequest(int64_t requestId,
                          ResponseAction responseAction,
                          const absl::string_view& reason) -> boost::future<Response<void>>;

    /**
     * Find group join/membership requests.
     *
     * @param groupId the target group ID.
     * @param lastUpdatedDate the last updated date of requests stored locally.
     * The server will only return requests that are updated after lastUpdatedDate.
     * If null, all requests will be returned.
     * @return a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryJoinRequests(int64_t groupId,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinRequestsWithVersion>>>;

    /**
     * Find group join/membership requests sent by the logged-in user.
     *
     * @param lastUpdatedDate the last updated date of requests stored locally.
     * The server will only return requests that are updated after lastUpdatedDate.
     * If null, all requests will be returned.
     * @return a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto querySentJoinRequests(const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinRequestsWithVersion>>>;

    /**
     * Find group join/membership questions.
     *
     * Authorization:
     * * Only the owner and managers have the right to fetch questions with answers
     *
     * @param groupId the target group ID.
     * @param withAnswers Whether to return the answers.
     * @param lastUpdatedDate the last updated date of questions stored locally.
     * The server will only return questions that are updated after lastUpdatedDate.
     * If null, all questions will be returned.
     * @return a list of question IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryGroupJoinQuestions(int64_t groupId,
                                 bool withAnswers,
                                 const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupJoinQuestionsWithVersion>>>;

    /**
     * Answer group join/membership questions, and join the group automatically
     * if the logged-in user has answered some questions correctly
     * and acquire the minimum score to join.
     *
     * @param questionIdToAnswer the map of question ID to answer.
     * @return the group membership questions answer result.
     * @throws ResponseException if an error occurs.
     */
    auto answerGroupQuestions(const std::unordered_map<int64_t, std::string>& questionIdToAnswer)
        -> boost::future<Response<GroupJoinQuestionsAnswerResult>>;

    /**
     * Add group members.
     *
     * Authorization:
     * * If the group is inactive,
     *   throws ResponseException with the code ResponseStatusCode::kAddUserToInactiveGroup.
     * * If the group has reached the maximum number of group members,
     *   throws ResponseException with the code
     * ResponseStatusCode::kAddUserToGroupWithSizeLimitReached.
     * * If the group doesn't allow add users as group members directly,
     *   throws ResponseException with the code
     * ResponseStatusCode::kAddUserToGroupRequiringUsersApproval.
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   ResponseStatusCode::kUserJoinGroupWithoutAcceptingGroupInvitation,
     *   ResponseStatusCode::kUserJoinGroupWithoutAnsweringGroupQuestion,
     *   ResponseStatusCode::kUserJoinGroupWithoutSendingGroupJoinRequest.
     * * If the logged-in user has no permission to add new group members,
     *   throws ResponseException with one of the following codes:
     *   ResponseStatusCode::kNotGroupOwnerToAddGroupMember,
     *   ResponseStatusCode::kNotGroupOwnerOrManagerToAddGroupMember,
     *   ResponseStatusCode::kNotGroupMemberToAddGroupMember,
     *   ResponseStatusCode::kNotGroupOwnerToAddGroupManager.
     * * If userIds contains a blocked user ID,
     *   throws ResponseException with the code ResponseStatusCode::kAddBlockedUserToGroup.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-added.notify-requester-other-online-sessions` is
     * true (true by default), the server will send a add group member notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-added.notify-added-group-member`, is true (true by
     * default), the server will send a add group member notification to all other online sessions
     * of the added group member.
     * * If the server property
     * `turms.service.notification.group-member-added.notify-other-group-members`, is true (true by
     * default), the server will send a add group member notification to all other online sessions
     * of the other group members.
     *
     * @param groupId the target group ID.
     * @param userIds the target user IDs.
     * @param name the name of the group member.
     * @param role the role of the group member.
     * @param muteEndDate the mute end date of the group member.
     * @throws ResponseException if an error occurs.
     */
    auto addGroupMembers(int64_t groupId,
                         const std::unordered_set<int64_t>& userIds,
                         const boost::optional<absl::string_view>& name = boost::none,
                         const boost::optional<GroupMemberRole>& role = boost::none,
                         const boost::optional<time_point>& muteEndDate = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Join a group.
     *
     * Authorization:
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   ResponseStatusCode::kUserJoinGroupWithoutAcceptingGroupInvitation,
     *   ResponseStatusCode::kUserJoinGroupWithoutAnsweringGroupQuestion,
     *   ResponseStatusCode::kUserJoinGroupWithoutSendingGroupJoinRequest.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-added.notify-requester-other-online-sessions` is
     * true (true by default), the server will send a add group member notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-added.notify-added-group-member`, is true (true by
     * default), the server will send a add group member notification to all other online sessions
     * of the added group member.
     * * If the server property
     * `turms.service.notification.group-member-added.notify-other-group-members`, is true (true by
     * default), the server will send a add group member notification to all other online sessions
     * of the other group members.
     *
     * @param groupId the target group ID.
     * @param name the name as the group member.
     * @throws ResponseException if an error occurs.
     */
    auto joinGroup(int64_t groupId, const boost::optional<absl::string_view>& name = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Quit a group.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`, is
     * true (true by default), the server will send a delete group member notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-removed.notify-other-group-members`, is true (true
     * by default), the server will send a delete group member notification to all other group
     * members of the group actively.
     *
     * @param groupId the target group ID.
     * @param successorId the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to
     * the successorId, throws ResponseException with the code
     * ResponseStatusCode::kGroupOwnerQuitWithoutSpecifyingSuccessor otherwise. And the successor
     * will become the group owner. The successor must already be a member of the group, throws
     * ResponseException with the code ResponseStatusCode::kGroupSuccessorNotGroupMember otherwise.
     * @param quitAfterTransfer whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   ResponseException with the code ResponseStatusCode::kNotGroupOwnerToTransferGroup will be
     * thrown.
     * @throws ResponseException if an error occurs.
     */
    auto quitGroup(int64_t groupId,
                   const boost::optional<int64_t>& successorId = boost::none,
                   const boost::optional<bool>& quitAfterTransfer = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Remove group members.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToRemoveGroupMember.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`, is
     * true (true by default), the server will send a delete group member notification to all other
     * online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-removed.notify-removed-group-member`, is true (true
     * by default), the server will send a delete group member notification to the removed group
     * member actively.
     * * If the server property
     * `turms.service.notification.group-member-removed.notify-other-group-members`, is true (true
     * by default), the server will send a delete group member notification to all other group
     * members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberIds the target member IDs.
     * @throws ResponseException if an error occurs.
     */
    auto removeGroupMembers(int64_t groupId, const std::unordered_set<int64_t>& memberIds)
        -> boost::future<Response<void>>;

    /**
     * Update group member info.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToUpdateGroupInfo.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-updated-group-member`, is true
     * (false by default), the server will send a update group member info notification to the
     * updated group member actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-other-group-members`, is true
     * (false by default), the server will send a update group member info notification to all other
     * group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @param name the new name of the group member.
     * If null, the name will not be updated.
     * @param role the new role of the group member.
     * If null, the role will not be updated.
     * @param muteEndDate the new mute end date of the group member.
     * If null, the mute end date will not be updated.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember
     * * If the logged-in user is not the group owner,
     *   throws ResponseException with the code
     * ResponseStatusCode::kMuteGroupMemberWithRoleEqualToOrHigherThanRequester.
     * @throws ResponseException if an error occurs.
     */
    auto updateGroupMemberInfo(int64_t groupId,
                               int64_t memberId,
                               const boost::optional<absl::string_view>& name = boost::none,
                               const boost::optional<GroupMemberRole>& role = boost::none,
                               const boost::optional<time_point>& muteEndDate = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Mute group member.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember
     * * If the logged-in user is not the group owner,
     *   throws ResponseException with the code
     * ResponseStatusCode::kMuteGroupMemberWithRoleEqualToOrHigherThanRequester.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-updated-group-member`, is true
     * (false by default), the server will send a update group member info notification to the
     * updated group member actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-other-group-members`, is true
     * (false by default), the server will send a update group member info notification to all other
     * group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @param muteEndDate the new mute end date of the group member.
     * @throws ResponseException if an error occurs.
     */
    auto muteGroupMember(int64_t groupId, int64_t memberId, const time_point& muteEndDate)
        -> boost::future<Response<void>>;

    /**
     * Unmute group member.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws ResponseException with the code
     * ResponseStatusCode::kNotGroupOwnerOrManagerToMuteGroupMember
     * * If the logged-in user is not the group owner,
     *   throws ResponseException with the code
     * ResponseStatusCode::kMuteGroupMemberWithRoleEqualToOrHigherThanRequester.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to
     * all other online sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-updated-group-member`, is true
     * (false by default), the server will send a update group member info notification to the
     * updated group member actively.
     * * If the server property
     * `turms.service.notification.group-member-info-updated.notify-other-group-members`, is true
     * (false by default), the server will send a update group member info notification to all other
     * group members of the group actively.
     *
     * @param groupId the target group ID.
     * @param memberId the target member ID.
     * @throws ResponseException if an error occurs.
     */
    auto unmuteGroupMember(int64_t groupId, int64_t memberId) -> boost::future<Response<void>>;

    /**
     * Find group members.
     *
     * @param groupId the target group ID.
     * @param withStatus whether to return the session status of the group members.
     * @param lastUpdatedDate the last updated date of the group members stored locally.
     * The server will only return group members that are updated after lastUpdatedDate.
     * If null, all group members will be returned.
     * @return group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
    auto queryGroupMembers(int64_t groupId,
                           bool withStatus,
                           const boost::optional<time_point>& lastUpdatedDate = boost::none)
        -> boost::future<Response<boost::optional<GroupMembersWithVersion>>>;

    /**
     * Find group members.
     *
     * @param groupId the target group ID.
     * @param memberIds the target member IDs.
     * @param withStatus whether to return the session status of the group members.
     * @return group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws ResponseException if an error occurs.
     */
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