#include "turms/client/service/group_service.h"

#include "turms/client/time/time_util.h"
#include "turms/client/turms_client.h"

namespace turms::client::service {
GroupService::GroupService(TurmsClient& turmsClient)
    : turmsClient_(turmsClient) {
}

auto GroupService::createGroup(absl::string_view name,
                               const std::optional<absl::string_view>& intro,
                               const std::optional<absl::string_view>& announcement,
                               const std::optional<int>& minScore,
                               const std::optional<time_point>& muteEndDate,
                               const std::optional<int64_t>& typeId,
                               const std::unordered_map<std::string, Value>& userDefinedAttributes)
    const -> boost::future<Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_request();
    request->set_name(name);
    if (intro) {
        request->set_intro(*intro);
    }
    if (announcement) {
        request->set_intro(*announcement);
    }
    if (minScore) {
        request->set_min_score(*minScore);
    }
    if (muteEndDate) {
        request->set_mute_end_date(time::toInt64(*muteEndDate));
    }
    if (typeId) {
        request->set_type_id(*typeId);
    }
    if (!userDefinedAttributes.empty()) {
        request->mutable_user_defined_attributes()->insert(userDefinedAttributes.cbegin(),
                                                           userDefinedAttributes.cend());
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto GroupService::deleteGroup(int64_t groupId) const -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_request();
    request->set_group_id(groupId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::updateGroup(int64_t groupId,
                               const std::optional<absl::string_view>& name,
                               const std::optional<absl::string_view>& intro,
                               const std::optional<absl::string_view>& announcement,
                               const std::optional<int>& minScore,
                               const std::optional<int64_t>& typeId,
                               const std::optional<time_point>& muteEndDate,
                               const std::optional<int64_t>& successorId,
                               const std::optional<bool>& quitAfterTransfer,
                               const std::unordered_map<std::string, Value>& userDefinedAttributes)
    const -> boost::future<Response<void>> {
    if (!name && !intro && !announcement && !minScore && !typeId && !muteEndDate && !successorId &&
        userDefinedAttributes.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_group_request();
    request->set_group_id(groupId);
    if (name) {
        request->set_name(*name);
    }
    if (intro) {
        request->set_intro(*intro);
    }
    if (announcement) {
        request->set_announcement(*announcement);
    }
    if (minScore) {
        request->set_min_score(*minScore);
    }
    if (typeId) {
        request->set_type_id(*typeId);
    }
    if (muteEndDate) {
        request->set_mute_end_date(time::toInt64(*muteEndDate));
    }
    if (successorId) {
        request->set_successor_id(*successorId);
    }
    if (quitAfterTransfer) {
        request->set_quit_after_transfer(*quitAfterTransfer);
    }
    if (!userDefinedAttributes.empty()) {
        request->mutable_user_defined_attributes()->insert(userDefinedAttributes.cbegin(),
                                                           userDefinedAttributes.cend());
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::transferOwnership(int64_t groupId,
                                     int64_t successorId,
                                     bool quitAfterTransfer) const
    -> boost::future<Response<void>> {
    return updateGroup(groupId,
                       std::nullopt,
                       std::nullopt,
                       std::nullopt,
                       std::nullopt,
                       std::nullopt,
                       std::nullopt,
                       successorId,
                       quitAfterTransfer);
}

auto GroupService::muteGroup(int64_t groupId, const time_point& muteEndDate) const
    -> boost::future<Response<void>> {
    return updateGroup(
        groupId, std::nullopt, std::nullopt, std::nullopt, std::nullopt, std::nullopt, muteEndDate);
}

auto GroupService::unmuteGroup(int64_t groupId) const -> boost::future<Response<void>> {
    return muteGroup(groupId, time::kEpoch);
}

auto GroupService::queryGroups(const std::unordered_set<int64_t>& groupIds,
                               const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::vector<Group>>> {
    if (groupIds.empty()) {
        return boost::make_ready_future<>(Response<Group>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_groups_request();
    request->mutable_group_ids()->Add(groupIds.cbegin(), groupIds.cend());
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<Group>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& groups = data.groups_with_version().groups();
                    return std::vector<Group>{groups.cbegin(), groups.cend()};
                }};
        });
}

auto GroupService::searchGroups(const std::string& name,
                                bool highlight,
                                const std::optional<int>& skip,
                                const std::optional<int>& limit) const
    -> boost::future<Response<std::vector<Group>>> {
    if (name.empty()) {
        return boost::make_ready_future<>(Response<Group>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_groups_request();
    request->set_name(name);
    if (highlight) {
        request->add_fields_to_highlight(1);
    }
    if (skip) {
        request->set_skip(*skip);
    }
    if (limit) {
        request->set_limit(*limit);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<Group>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& groups = data.groups_with_version().groups();
                    return std::vector<Group>{groups.cbegin(), groups.cend()};
                }};
        });
}

auto GroupService::queryJoinedGroupIds(const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<LongsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_joined_group_ids_request();
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<LongsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_longs_with_version()) {
                        return std::optional(data.longs_with_version());
                    }
                    return std::optional<LongsWithVersion>{};
                }};
        });
}

auto GroupService::queryJoinedGroupInfos(const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_joined_group_infos_request();
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_groups_with_version()) {
                        return std::optional(data.groups_with_version());
                    }
                    return std::optional<GroupsWithVersion>();
                }};
        });
}

auto GroupService::addGroupJoinQuestions(
    int64_t groupId, const std::vector<model::NewGroupJoinQuestion>& questions) const
    -> boost::future<Response<std::vector<int64_t>>> {
    if (questions.empty()) {
        return boost::make_ready_future<>(Response<int64_t>::emptyList());
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_join_questions_request();
    request->set_group_id(groupId);
    google::protobuf::RepeatedPtrField<model::proto::GroupJoinQuestion>* mutableQuestions =
        request->mutable_questions();
    mutableQuestions->Reserve(questions.size());
    for (const auto& [question, answers, score] : questions) {
        if (answers.empty()) {
            return boost::make_exceptional_future<Response<std::vector<int64_t>>>(
                ResponseException{model::ResponseStatusCode::kIllegalArgument,
                                  "The answers of group must not be empty"});
        }
        GroupJoinQuestion q;
        q.set_question(question);
        q.mutable_answers()->Add(answers.cbegin(), answers.cend());
        q.set_score(score);
        mutableQuestions->Add(std::move(q));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::vector<int64_t>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    const auto& longs = data.longs_with_version().longs();
                    return std::vector<int64_t>{longs.cbegin(), longs.cend()};
                }};
        });
}

auto GroupService::deleteGroupJoinQuestions(const std::unordered_set<int64_t>& questionIds) const
    -> boost::future<Response<void>> {
    if (questionIds.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_join_questions_request();
    request->mutable_question_ids()->Add(questionIds.cbegin(), questionIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::updateGroupJoinQuestion(int64_t questionId,
                                           const std::optional<absl::string_view>& question,
                                           const std::vector<std::string>& answers,
                                           const std::optional<int>& score) const
    -> boost::future<Response<void>> {
    if (!question && answers.empty() && !score) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_group_join_question_request();
    request->set_question_id(questionId);
    if (question) {
        request->set_question(*question);
    }
    if (!answers.empty()) {
        request->mutable_answers()->Add(answers.cbegin(), answers.cend());
    }
    if (score) {
        request->set_score(*score);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::blockUser(int64_t groupId, int64_t userId) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_blocked_user_request();
    request->set_group_id(groupId);
    request->set_user_id(userId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::unblockUser(int64_t groupId, int64_t userId) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_blocked_user_request();
    request->set_user_id(userId);
    request->set_group_id(groupId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::queryBlockedUserIds(int64_t groupId,
                                       const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<LongsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_blocked_user_ids_request();
    request->set_group_id(groupId);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<LongsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_longs_with_version()) {
                        return std::optional(data.longs_with_version());
                    }
                    return std::optional<LongsWithVersion>{};
                }};
        });
}

auto GroupService::queryBlockedUserInfos(int64_t groupId,
                                         const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<UserInfosWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_blocked_user_infos_request();
    request->set_group_id(groupId);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<UserInfosWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_user_infos_with_version()) {
                        return std::optional(data.user_infos_with_version());
                    }
                    return std::optional<UserInfosWithVersion>{};
                }};
        });
}

auto GroupService::createInvitation(int64_t groupId,
                                    int64_t inviteeId,
                                    absl::string_view content) const
    -> boost::future<Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_invitation_request();
    request->set_group_id(groupId);
    request->set_invitee_id(inviteeId);
    request->set_content(content);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto GroupService::deleteInvitation(int64_t invitationId) const -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_invitation_request();
    request->set_invitation_id(invitationId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::replyInvitation(int64_t invitationId,
                                   ResponseAction responseAction,
                                   absl::string_view reason) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_group_invitation_request();
    request->set_invitation_id(invitationId);
    request->set_response_action(responseAction);
    request->set_reason(reason);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::queryInvitations(int64_t groupId,
                                    const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupInvitationsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_invitations_request();
    request->set_group_id(groupId);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupInvitationsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_invitations_with_version()) {
                        return std::optional(data.group_invitations_with_version());
                    }
                    return std::optional<GroupInvitationsWithVersion>{};
                }};
        });
}

auto GroupService::queryInvitations(bool areSentByMe,
                                    const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupInvitationsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_invitations_request();
    request->set_are_sent_by_me(areSentByMe);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupInvitationsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_invitations_with_version()) {
                        return std::optional(data.group_invitations_with_version());
                    }
                    return std::optional<GroupInvitationsWithVersion>{};
                }};
        });
}

auto GroupService::createJoinRequest(int64_t groupId, absl::string_view content) const
    -> boost::future<Response<int64_t>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_join_request_request();
    request->set_group_id(groupId);
    request->set_content(content);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<int64_t>{response.get(), [](const TurmsNotification::Data& data) {
                                         return model::notification::getLongOrThrow(data);
                                     }};
        });
}

auto GroupService::deleteJoinRequest(int64_t requestId) const -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_join_request_request();
    request->set_request_id(requestId);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::replyJoinRequest(int64_t requestId,
                                    ResponseAction responseAction,
                                    absl::string_view reason) const
    -> boost::future<Response<void>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_group_join_request_request();
    request->set_request_id(requestId);
    request->set_response_action(responseAction);
    request->set_reason(reason);
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::queryJoinRequests(int64_t groupId,
                                     const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupJoinRequestsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_join_requests_request();
    request->set_group_id(groupId);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupJoinRequestsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_join_requests_with_version()) {
                        return std::optional(data.group_join_requests_with_version());
                    }
                    return std::optional<GroupJoinRequestsWithVersion>{};
                }};
        });
}

auto GroupService::querySentJoinRequests(const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupJoinRequestsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_join_requests_request();
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupJoinRequestsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_join_requests_with_version()) {
                        return std::optional(data.group_join_requests_with_version());
                    }
                    return std::optional<GroupJoinRequestsWithVersion>{};
                }};
        });
}

auto GroupService::queryGroupJoinQuestions(int64_t groupId,
                                           bool withAnswers,
                                           const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupJoinQuestionsWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_join_questions_request();
    request->set_group_id(groupId);
    request->set_with_answers(withAnswers);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupJoinQuestionsWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_join_questions_with_version()) {
                        return std::optional(data.group_join_questions_with_version());
                    }
                    return std::optional<GroupJoinQuestionsWithVersion>{};
                }};
        });
}

auto GroupService::answerGroupQuestions(
    const std::unordered_map<int64_t, std::string>& questionIdToAnswer) const
    -> boost::future<Response<GroupJoinQuestionsAnswerResult>> {
    if (questionIdToAnswer.empty()) {
        return boost::make_exceptional_future<Response<GroupJoinQuestionsAnswerResult>>(
            ResponseException{model::ResponseStatusCode::kIllegalArgument,
                              "\"questionIdToAnswer\" must not be empty"});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_check_group_join_questions_answers_request();
    request->mutable_question_id_to_answer()->insert(questionIdToAnswer.cbegin(),
                                                     questionIdToAnswer.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<GroupJoinQuestionsAnswerResult>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_join_question_answer_result()) {
                        return data.group_join_question_answer_result();
                    }
                    throw ResponseException{model::ResponseStatusCode::kInvalidResponse};
                }};
        });
}

auto GroupService::addGroupMembers(int64_t groupId,
                                   const std::unordered_set<int64_t>& userIds,
                                   const std::optional<absl::string_view>& name,
                                   const std::optional<GroupMemberRole>& role,
                                   const std::optional<time_point>& muteEndDate) const
    -> boost::future<Response<void>> {
    if (userIds.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_create_group_members_request();
    request->set_group_id(groupId);
    if (!userIds.empty()) {
        request->mutable_user_ids()->Add(userIds.cbegin(), userIds.cend());
    }
    if (name) {
        request->set_name(*name);
    }
    if (role) {
        request->set_role(*role);
    }
    if (muteEndDate) {
        request->set_mute_end_date(time::toInt64(*muteEndDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::joinGroup(int64_t groupId, const std::optional<absl::string_view>& name) const
    -> boost::future<Response<void>> {
    const std::optional<User>& info = turmsClient_.userService().userInfo();
    if (!info) {
        return boost::make_exceptional_future<Response<void>>(
            ResponseException{model::ResponseStatusCode::kClientSessionHasBeenClosed});
    }
    return addGroupMembers(groupId, std::unordered_set{info->userId}, name);
}

auto GroupService::quitGroup(int64_t groupId,
                             const std::optional<int64_t>& successorId,
                             const std::optional<bool>& quitAfterTransfer) const
    -> boost::future<Response<void>> {
    const std::optional<User>& info = turmsClient_.userService().userInfo();
    if (!info) {
        return boost::make_exceptional_future<Response<void>>(
            ResponseException{model::ResponseStatusCode::kClientSessionHasBeenClosed});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_members_request();
    request->set_group_id(groupId);
    request->add_member_ids((*info).userId);
    if (successorId) {
        request->set_successor_id(*successorId);
    }
    if (quitAfterTransfer) {
        request->set_quit_after_transfer(*quitAfterTransfer);
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::removeGroupMembers(int64_t groupId,
                                      const std::unordered_set<int64_t>& memberIds) const
    -> boost::future<Response<void>> {
    if (memberIds.empty()) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_delete_group_members_request();
    request->set_group_id(groupId);
    request->mutable_member_ids()->Add(memberIds.cbegin(), memberIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::updateGroupMemberInfo(int64_t groupId,
                                         int64_t memberId,
                                         const std::optional<absl::string_view>& name,
                                         const std::optional<GroupMemberRole>& role,
                                         const std::optional<time_point>& muteEndDate) const
    -> boost::future<Response<void>> {
    if (!name && !role && !muteEndDate) {
        return boost::make_ready_future<>(Response<void>{});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_update_group_member_request();
    request->set_group_id(groupId);
    request->set_member_id(memberId);
    if (name) {
        request->set_name(*name);
    }
    if (role) {
        request->set_role(*role);
    }
    if (muteEndDate) {
        request->set_mute_end_date(time::toInt64(*muteEndDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<void>{response.get()};
        });
}

auto GroupService::muteGroupMember(int64_t groupId,
                                   int64_t memberId,
                                   const time_point& muteEndDate) const
    -> boost::future<Response<void>> {
    return updateGroupMemberInfo(groupId, memberId, std::nullopt, std::nullopt, muteEndDate);
}

auto GroupService::unmuteGroupMember(int64_t groupId, int64_t memberId) const
    -> boost::future<Response<void>> {
    return muteGroupMember(groupId, memberId, time::kEpoch);
}

auto GroupService::queryGroupMembers(int64_t groupId,
                                     bool withStatus,
                                     const std::optional<time_point>& lastUpdatedDate) const
    -> boost::future<Response<std::optional<GroupMembersWithVersion>>> {
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_members_request();
    request->set_group_id(groupId);
    request->set_with_status(withStatus);
    if (lastUpdatedDate) {
        request->set_last_updated_date(time::toInt64(*lastUpdatedDate));
    }
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupMembersWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_members_with_version()) {
                        return std::optional(data.group_members_with_version());
                    }
                    return std::optional<GroupMembersWithVersion>{};
                }};
        });
}

auto GroupService::queryGroupMembersByMemberIds(int64_t groupId,
                                                const std::unordered_set<int64_t>& memberIds,
                                                bool withStatus) const
    -> boost::future<Response<std::optional<GroupMembersWithVersion>>> {
    if (memberIds.empty()) {
        return boost::make_ready_future<>(
            Response<std::optional<GroupMembersWithVersion>>{std::nullopt});
    }
    TurmsRequest turmsRequest;
    auto* request = turmsRequest.mutable_query_group_members_request();
    request->set_group_id(groupId);
    request->set_with_status(withStatus);
    request->mutable_member_ids()->Add(memberIds.cbegin(), memberIds.cend());
    return turmsClient_.driver()
        .send(turmsRequest)
        .then([](boost::future<TurmsNotification> response) {
            return Response<std::optional<GroupMembersWithVersion>>{
                response.get(), [](const TurmsNotification::Data& data) {
                    if (data.has_group_members_with_version()) {
                        return std::optional(data.group_members_with_version());
                    }
                    return std::optional<GroupMembersWithVersion>{};
                }};
        });
}
}  // namespace turms::client::service
