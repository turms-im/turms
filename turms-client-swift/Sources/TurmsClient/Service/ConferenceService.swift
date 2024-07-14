import Foundation
import PromiseKit

public class ConferenceService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    /// Create a meeting, and send a meeting invitation as a message to the participants, which means:
    /// * If the meeting is a private meeting, the meeting invitation message will be sent to the target user.
    /// * If the meeting is a group meeting, the meeting invitation message will be sent to all group members.
    /// * If the meeting is a public meeting, no meeting invitation message will be sent.
    ///
    /// To join a meeting, call ``acceptMeetingInvitation``.
    ///
    /// Authorization:
    /// * If the logged-in user has created too many meetings,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/createMeetingExceedingMaxActiveMeetingCount``.
    ///
    /// For private meetings,
    /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
    ///   is true (false by default), the logged-in user can send meeting invitation messages to itself.
    ///   Otherwise, throws ``ResponseError`` with the code ``ResponseStatusCode/sendingMessagesToOneselfIsDisabled``.
    /// * If the server property `turms.service.message.allow-send-messages-to-stranger`
    ///   is true (true by default), the logged-in user can send meeting invitation messages to strangers
    ///   (has no relationship with the logged-in user).
    /// * If the logged-in user has blocked the target user,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/blockedUserSendPrivateMessage``.
    ///
    /// For group meetings,
    /// * If the logged-in user has blocked the target group,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/blockedUserSendGroupMessage``.
    /// * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notSpeakableGroupGuestToSendMessage``.
    /// * If the logged-in user has been muted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/mutedGroupMemberSendMessage``.
    /// * If the target group has been deleted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendMessageToInactiveGroup``.
    /// * If the target group has been muted,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/sendMessageToMutedGroup``.
    ///
    /// - Parameters:
    ///   - userId: The target user ID. If not null, the meeting will be a private meeting.
    ///     If both `userId` and `groupId` are null, the meeting will be a public meeting.
    ///   - groupId: The target group ID. If not null, the meeting will be a group meeting.
    ///     If both `userId` and `groupId` are null, the meeting will be a public meeting.
    ///   - name: The name of the meeting.
    ///   - intro: The intro of the meeting.
    ///   - password: The password of the meeting.
    ///   - startDate: The start date of the meeting. If not null, the meeting will be a scheduled meeting.
    ///
    /// - Returns: The meeting ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If the server hasn't implemented the feature, throws ``ResponseError`` with the code ``ResponseStatusCode/conferenceNotImplemented``.
    public func createMeeting(userId: Int64? = nil,
                              groupId: Int64? = nil,
                              name: String? = nil,
                              intro: String? = nil,
                              password: String? = nil,
                              startDate: Date? = nil) -> Promise<Response<Int64>>
    {
        return turmsClient.driver
            .send {
                $0.createMeetingRequest = .with {
                    if let v = userId {
                        $0.userID = v
                    }
                    if let v = groupId {
                        $0.groupID = v
                    }
                    if let v = name {
                        $0.name = v
                    }
                    if let v = intro {
                        $0.intro = v
                    }
                    if let v = password {
                        $0.password = v
                    }
                    if let v = startDate {
                        $0.startDate = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Cancel a meeting.
    ///
    /// Authorization:
    /// * If the server property `turms.service.conference.meeting.scheduling.allow-cancel`
    ///   is false (true by default), throw ``ResponseError`` with the code ``ResponseStatusCode/cancelingMeetingIsDisabled``.
    /// * If the logged-in user is not the creator of the meeting, but is the meeting participant,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/notCreatorToCancelMeeting``.
    /// * If the logged-in user is not the creator of the meeting, and is not the meeting participant,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/cancelNonexistentMeeting``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.meeting-canceled.notify-requester-other-online-sessions`
    ///   is true (false by default), the server will send a meeting canceled notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.meeting-canceled.notify-meeting-participants`
    ///   is true (false by default), the server will send a meeting canceled notification to all online sessions of the meeting participants.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If the server hasn't implemented the feature, throws ``ResponseError`` with the code ``ResponseStatusCode/conferenceNotImplemented``.
    public func cancelMeeting(_ meetingId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteMeetingRequest = .with {
                    $0.id = meetingId
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Update a meeting.
    ///
    /// Authorization:
    /// * All meeting participants can update the name and intro of the meeting,
    /// but only the meeting creator can update the password of the meeting.
    /// * If the meeting does not exist, or the logged-in user is not the meeting participant,
    /// throws ``ResponseError`` with the code ``ResponseStatusCode/updateInfoOfNonexistentMeeting``.
    /// * If `password` is not null, and the logged-in user is not the creator of the meeting,
    /// throws ``ResponseError`` with the code ``ResponseStatusCode/notCreatorToUpdateMeetingPassword``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.meeting-updated.notify-requester-other-online-sessions`
    ///   is true (false by default), the server will send a meeting updated notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.meeting-updated.notify-meeting-participants`
    ///   is true (false by default), the server will send a meeting updated notification to all online sessions of the meeting participants.
    ///
    /// - Parameters:
    ///   - meetingId: The target meeting ID.
    ///   - name: A new name of the meeting.
    ///     If null, the meeting name will not be updated.
    ///   - intro: A new intro of the meeting.
    ///     If null, the meeting intro will not be updated.
    ///   - password: A new password of the meeting.
    ///     If null, the meeting password will not be updated.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updateMeeting(_ meetingId: Int64,
                              name: String? = nil,
                              intro: String? = nil,
                              password: String? = nil) -> Promise<Response<Void>>
    {
        return turmsClient.driver
            .send {
                $0.updateMeetingRequest = .with {
                    $0.id = meetingId
                    if let v = name {
                        $0.name = v
                    }
                    if let v = intro {
                        $0.intro = v
                    }
                    if let v = password {
                        $0.password = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Find meetings.
    ///
    /// - Parameters:
    ///   - meetingIds: The target meeting IDs.
    ///   - creatorIds: The target creator IDs.
    ///   - userIds: The target user IDs.
    ///   - groupIds: The target group IDs.
    ///   - creationDateStart: Find meetings created after the start date.
    ///   - creationDateEnd: Find meetings created before the end date.
    ///   - skip: The number of meetings to skip.
    ///   - limit: The maximum number of meetings to be returned.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryMeetings(meetingIds: [Int64]? = nil,
                              creatorIds: [Int64]? = nil,
                              userIds: [Int64]? = nil,
                              groupIds: [Int64]? = nil,
                              creationDateStart: Date? = nil,
                              creationDateEnd: Date? = nil,
                              skip: Int32? = nil,
                              limit: Int32? = nil) -> Promise<Response<[Meeting]>>
    {
        return turmsClient.driver
            .send {
                $0.queryMeetingsRequest = .with {
                    if let v = meetingIds {
                        $0.ids = v
                    }
                    if let v = creatorIds {
                        $0.creatorIds = v
                    }
                    if let v = userIds {
                        $0.userIds = v
                    }
                    if let v = groupIds {
                        $0.groupIds = v
                    }
                    if let v = creationDateStart {
                        $0.creationDateStart = v.toMillis()
                    }
                    if let v = creationDateEnd {
                        $0.creationDateEnd = v.toMillis()
                    }
                    if let v = skip {
                        $0.skip = v
                    }
                    if let v = limit {
                        $0.limit = v
                    }
                }
            }
            .map {
                try $0.toResponse {
                    try $0.meetings.meetings
                }
            }
    }

    /// Accept a meeting invitation.
    ///
    /// Authorization:
    /// * If the logged-in user is not the meeting participant, or the meeting does not exist,
    ///   throws ``ResponseError`` with the code ``ResponseStatusCode/acceptNonexistentMeetingInvitation``.
    /// * If the password is incorrect, throws ``ResponseError`` with the code ``ResponseStatusCode/acceptMeetingInvitationWithWrongPassword``.
    /// * If the meeting has been canceled, throws ``ResponseError`` with the code ``ResponseStatusCode/acceptMeetingInvitationOfCanceledMeeting``.
    /// * If the meeting has expired, throws ``ResponseError`` with the code ``ResponseStatusCode/acceptMeetingInvitationOfExpiredMeeting``.
    /// * If the meeting has ended, throws ``ResponseError`` with the code ``ResponseStatusCode/acceptMeetingInvitationOfEndedMeeting``.
    /// * If the meeting is pending, throws ``ResponseError`` with the code ``ResponseStatusCode/acceptMeetingInvitationOfPendingMeeting``.
    ///
    /// - Parameters:
    ///   - meetingId: The target meeting ID.
    ///   - password: The password of the meeting.
    ///
    /// - Returns: The access token.
    /// To join a meeting, use the access token to communicate with the media server.
    /// For example, if the conference service of turms-service is provided by
    /// the "turms-plugin-livekit" plugin, the media server will be the livekit server.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If the server hasn't implemented the feature, throws ``ResponseError`` with the code ``ResponseStatusCode/conferenceNotImplemented``.
    public func acceptMeetingInvitation(_ meetingId: Int64, password: String? = nil) -> Promise<Response<String>> {
        return turmsClient.driver
            .send {
                $0.updateMeetingInvitationRequest = .with {
                    $0.meetingID = meetingId
                    $0.responseAction = .accept
                    if let v = password {
                        $0.password = v
                    }
                }
            }
            .map { try $0.toResponse {
                try $0.string
            }
            }
    }
}
