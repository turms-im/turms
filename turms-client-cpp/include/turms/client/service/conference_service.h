#ifndef TURMS_CLIENT_SERVICE_CONFERENCE_SERVICE_H
#define TURMS_CLIENT_SERVICE_CONFERENCE_SERVICE_H

#include <boost/noncopyable.hpp>
#include <boost/thread/future.hpp>
#include <chrono>
#include <unordered_set>

#include "turms/client/exception/response_exception.h"
#include "turms/client/model/proto/notification/turms_notification.pb.h"
#include "turms/client/model/response.h"

namespace turms {
namespace client {

class TurmsClient;

namespace service {

class ConferenceService : private boost::noncopyable {
   private:
    using time_point = std::chrono::time_point<std::chrono::system_clock>;
    using ResponseException = exception::ResponseException;

    template <typename T>
    using Response = model::Response<T>;

    using TurmsNotification = model::proto::TurmsNotification;
    using TurmsRequest = model::proto::TurmsRequest;

    using Meeting = model::proto::Meeting;

    using Value = model::proto::Value;

   public:
    explicit ConferenceService(TurmsClient& turmsClient);

    /**
     * Create a meeting, and send a meeting invitation as a message to the participants, which
     * means:
     * * If the meeting is a private meeting, the meeting invitation message will be sent to the
     * target user.
     * * If the meeting is a group meeting, the meeting invitation message will be sent to all group
     * members.
     * * If the meeting is a public meeting, no meeting invitation message will be sent.
     *
     * To join a meeting, call acceptMeetingInvitation.
     *
     * Authorization:
     * * If the logged-in user has created too many meetings,
     *   throws ResponseException with the code
     * ResponseStatusCode::kCreateMeetingExceedingMaxActiveMeetingCount.
     *
     * For private meetings,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send meeting invitation messages to
     * itself. Otherwise, throws ResponseException with the code
     * ResponseStatusCode::kSendingMessagesToOneselfIsDisabled.
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send meeting invitation messages to
     * strangers (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws ResponseException with the code ResponseStatusCode::kBlockedUserSendPrivateMessage.
     *
     * For group meetings,
     * * If the logged-in user has blocked the target group,
     *   throws ResponseException with the code ResponseStatusCode::kBlockedUserSendGroupMessage.
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to
     * send messages, throws ResponseException with the code
     * ResponseStatusCode::kNotSpeakableGroupGuestToSendMessage.
     * * If the logged-in user has been muted,
     *   throws ResponseException with the code ResponseStatusCode::kMutedGroupMemberSendMessage.
     * * If the target group has been deleted,
     *   throws ResponseException with the code ResponseStatusCode::kSendMessageToInactiveGroup.
     * * If the target group has been muted,
     *   throws ResponseException with the code ResponseStatusCode::kSendMessageToMutedGroup.
     *
     * @param userId the target user ID. If not null, the meeting will be a private meeting.
     * If both userId and groupId are null, the meeting will be a public meeting.
     * @param groupId the target group ID. If not null, the meeting will be a group meeting.
     * If both userId and groupId are null, the meeting will be a public meeting.
     * @param name the name of the meeting.
     * @param intro the intro of the meeting.
     * @param password the password of the meeting.
     * @param startDate the start date of the meeting. If not null, the meeting will be a scheduled
     * meeting.
     * @return the meeting ID.
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws ResponseException with the code
     * ResponseStatusCode::kConferenceNotImplemented.
     */
    auto createMeeting(const boost::optional<int64_t>& userId = boost::none,
                       const boost::optional<int64_t>& groupId = boost::none,
                       const boost::optional<absl::string_view>& name = boost::none,
                       const boost::optional<absl::string_view>& intro = boost::none,
                       const boost::optional<absl::string_view>& password = boost::none,
                       const boost::optional<time_point>& startDate = boost::none)
        -> boost::future<Response<int64_t>>;

    /**
     * Cancel a meeting.
     *
     * Authorization:
     * * If the server property `turms.service.conference.meeting.scheduling.allow-cancel`
     *   is false (true by default), throw ResponseException with the code
     * ResponseStatusCode::kCancelingMeetingIsDisabled.
     * * If the logged-in user is not the creator of the meeting, but is the meeting participant,
     *   throws ResponseException with the code ResponseStatusCode::kNotCreatorToCancelMeeting.
     * * If the logged-in user is not the creator of the meeting, and is not the meeting
     * participant, throws ResponseException with the code
     * ResponseStatusCode::kCancelNonexistentMeeting.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.meeting-canceled.notify-requester-other-online-sessions` is true
     * (false by default), the server will send a meeting canceled notification to all other online
     * sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.meeting-canceled.notify-meeting-participants` is true (false by
     * default), the server will send a meeting canceled notification to all online sessions of the
     * meeting participants.
     *
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws ResponseException with the code
     * ResponseStatusCode::kConferenceNotImplemented.
     */
    auto cancelMeeting(int64_t meetingId) -> boost::future<Response<void>>;

    /**
     * Update a meeting.
     *
     * Authorization:
     * * All meeting participants can update the name and intro of the meeting,
     * but only the meeting creator can update the password of the meeting.
     * * If the meeting does not exist, or the logged-in user is not the meeting participant,
     * throws ResponseException with the code ResponseStatusCode::kUpdateInfoOfNonexistentMeeting.
     * * If password is not null, and the logged-in user is not the creator of the meeting,
     * throws ResponseException with the code
     * ResponseStatusCode::kNotCreatorToUpdateMeetingPassword.
     *
     * Notifications:
     * * If the server property
     * `turms.service.notification.meeting-updated.notify-requester-other-online-sessions` is true
     * (false by default), the server will send a meeting updated notification to all other online
     * sessions of the logged-in user actively.
     * * If the server property
     * `turms.service.notification.meeting-updated.notify-meeting-participants` is true (false by
     * default), the server will send a meeting updated notification to all online sessions of the
     * meeting participants.
     *
     * @param meetingId the target meeting ID.
     * @param name a new name of the meeting.
     * If null, the meeting name will not be updated.
     * @param intro a new intro of the meeting.
     * If null, the meeting intro will not be updated.
     * @param password a new password of the meeting.
     * If null, the meeting password will not be updated.
     * @throws ResponseException if an error occurs.
     */
    auto updateMeeting(int64_t meetingId,
                       const boost::optional<absl::string_view>& name = boost::none,
                       const boost::optional<absl::string_view>& intro = boost::none,
                       const boost::optional<absl::string_view>& password = boost::none)
        -> boost::future<Response<void>>;

    /**
     * Find meetings.
     *
     * @param meetingIds the target meeting IDs.
     * @param creatorIds the target creator IDs.
     * @param userIds the target user IDs.
     * @param groupIds the target group IDs.
     * @param creationDateStart find meetings created after the start date.
     * @param creationDateEnd find meetings created before the end date.
     * @param skip the number of meetings to skip.
     * @param limit the maximum number of meetings to be returned.
     * @throws ResponseException if an error occurs.
     */
    auto queryMeetings(const std::unordered_set<int64_t>& meetingIds = {},
                       const std::unordered_set<int64_t>& creatorIds = {},
                       const std::unordered_set<int64_t>& userIds = {},
                       const std::unordered_set<int64_t>& groupIds = {},
                       const boost::optional<time_point>& creationDateStart = boost::none,
                       const boost::optional<time_point>& creationDateEnd = boost::none,
                       const boost::optional<int>& skip = boost::none,
                       const boost::optional<int>& limit = boost::none)
        -> boost::future<Response<std::vector<Meeting>>>;

    /**
     * Accept a meeting invitation.
     *
     * Authorization:
     * * If the logged-in user is not the meeting participant, or the meeting does not exist,
     *   throws ResponseException with the code
     * ResponseStatusCode::kAcceptNonexistentMeetingInvitation.
     * * If the password is incorrect, throws ResponseException with the code
     * ResponseStatusCode::kAcceptMeetingInvitationWithWrongPassword.
     * * If the meeting has been canceled, throws ResponseException with the code
     * ResponseStatusCode::kAcceptMeetingInvitationOfCanceledMeeting.
     * * If the meeting has expired, throws ResponseException with the code
     * ResponseStatusCode::kAcceptMeetingInvitationOfExpiredMeeting.
     * * If the meeting has ended, throws ResponseException with the code
     * ResponseStatusCode::kAcceptMeetingInvitationOfEndedMeeting.
     * * If the meeting is pending, throws ResponseException with the code
     * ResponseStatusCode::kAcceptMeetingInvitationOfPendingMeeting.
     *
     * @param meetingId the target meeting ID.
     * @param password the password of the meeting.
     * @return the access token.
     * To join a meeting, use the access token to communicate with the media server.
     * For example, if the conference service of turms-service is provided by
     * the "turms-plugin-livekit" plugin, the media server will be the livekit server.
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws ResponseException with the code
     * ResponseStatusCode::kConferenceNotImplemented.
     */
    auto acceptMeetingInvitation(int64_t meetingId,
                                 const boost::optional<absl::string_view>& password = boost::none)
        -> boost::future<Response<std::string>>;

   private:
    TurmsClient& turmsClient_;
};

}  // namespace service
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_SERVICE_CONFERENCE_SERVICE_H