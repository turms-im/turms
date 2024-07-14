import 'package:fixnum/fixnum.dart' show Int64;

import '../../turms_client.dart';
import '../extension/notification_extensions.dart';
import '../model/proto/model/conference/meeting.pb.dart';
import '../model/proto/request/conference/create_meeting_request.pb.dart';
import '../model/proto/request/conference/delete_meeting_request.pb.dart';
import '../model/proto/request/conference/query_meetings_request.pb.dart';
import '../model/proto/request/conference/update_meeting_invitation_request.pb.dart';
import '../model/proto/request/conference/update_meeting_request.pb.dart';

class ConferenceService {
  final TurmsClient _turmsClient;

  ConferenceService(this._turmsClient);

  /// Create a meeting, and send a meeting invitation as a message to the participants, which means:
  /// * If the meeting is a private meeting, the meeting invitation message will be sent to the target user.
  /// * If the meeting is a group meeting, the meeting invitation message will be sent to all group members.
  /// * If the meeting is a public meeting, no meeting invitation message will be sent.
  ///
  /// To join a meeting, call [acceptMeetingInvitation].
  ///
  /// Authorization:
  /// * If the logged-in user has created too many meetings,
  ///   throws [ResponseException] with the code [ResponseStatusCode.createMeetingExceedingMaxActiveMeetingCount].
  ///
  /// For private meetings,
  /// * If the server property `turms.service.message.allow-send-messages-to-oneself`
  ///   is true (false by default), the logged-in user can send meeting invitation messages to itself.
  ///   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.sendingMessagesToOneselfIsDisabled].
  /// * If the server property `turms.service.message.allow-send-messages-to-stranger`
  ///   is true (true by default), the logged-in user can send meeting invitation messages to strangers
  ///   (has no relationship with the logged-in user).
  /// * If the logged-in user has blocked the target user,
  ///   throws [ResponseException] with the code [ResponseStatusCode.blockedUserSendPrivateMessage].
  ///
  /// For group meetings,
  /// * If the logged-in user has blocked the target group,
  ///   throws [ResponseException] with the code [ResponseStatusCode.blockedUserSendGroupMessage].
  /// * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notSpeakableGroupGuestToSendMessage].
  /// * If the logged-in user has been muted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.mutedGroupMemberSendMessage].
  /// * If the target group has been deleted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendMessageToInactiveGroup].
  /// * If the target group has been muted,
  ///   throws [ResponseException] with the code [ResponseStatusCode.sendMessageToMutedGroup].
  ///
  /// **Params**:
  /// * `userId`: The target user ID. If not null, the meeting will be a private meeting.
  /// If both [userId] and [groupId] are null, the meeting will be a public meeting.
  /// * `groupId`: The target group ID. If not null, the meeting will be a group meeting.
  /// If both [userId] and [groupId] are null, the meeting will be a public meeting.
  /// * `name`: The name of the meeting.
  /// * `intro`: The intro of the meeting.
  /// * `password`: The password of the meeting.
  /// * `startDate`: The start date of the meeting. If not null, the meeting will be a scheduled meeting.
  ///
  /// **Returns**: The meeting ID.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.conferenceNotImplemented].
  Future<Response<Int64>> createMeeting({
    Int64? userId,
    Int64? groupId,
    String? name,
    String? intro,
    String? password,
    DateTime? startDate,
  }) async {
    final n = await _turmsClient.driver.send(
      CreateMeetingRequest(
        userId: userId,
        groupId: groupId,
        name: name,
        intro: intro,
        password: password,
        startDate: startDate?.toInt64(),
      ),
    );
    return n.toResponse((data) => data.getLongOrThrow());
  }

  /// Cancel a meeting.
  ///
  /// Authorization:
  /// * If the server property `turms.service.conference.meeting.scheduling.allow-cancel`
  ///   is false (true by default), throw [ResponseException] with the code [ResponseStatusCode.cancelingMeetingIsDisabled].
  /// * If the logged-in user is not the creator of the meeting, but is the meeting participant,
  ///   throws [ResponseException] with the code [ResponseStatusCode.notCreatorToCancelMeeting].
  /// * If the logged-in user is not the creator of the meeting, and is not the meeting participant,
  ///   throws [ResponseException] with the code [ResponseStatusCode.cancelNonexistentMeeting].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.meeting-canceled.notify-requester-other-online-sessions`
  ///   is true (false by default), the server will send a meeting canceled notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.meeting-canceled.notify-meeting-participants`
  ///   is true (false by default), the server will send a meeting canceled notification to all online sessions of the meeting participants.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.conferenceNotImplemented].
  Future<Response<void>> cancelMeeting(Int64 meetingId) async {
    final n =
        await _turmsClient.driver.send(DeleteMeetingRequest(id: meetingId));
    return n.toNullResponse();
  }

  /// Update a meeting.
  ///
  /// Authorization:
  /// * All meeting participants can update the name and intro of the meeting,
  /// but only the meeting creator can update the password of the meeting.
  /// * If the meeting does not exist, or the logged-in user is not the meeting participant,
  /// throws [ResponseException] with the code [ResponseStatusCode.updateInfoOfNonexistentMeeting].
  /// * If [password] is not null, and the logged-in user is not the creator of the meeting,
  /// throws [ResponseException] with the code [ResponseStatusCode.notCreatorToUpdateMeetingPassword].
  ///
  /// Notifications:
  /// * If the server property `turms.service.notification.meeting-updated.notify-requester-other-online-sessions`
  ///   is true (false by default), the server will send a meeting updated notification to all other online sessions of the logged-in user actively.
  /// * If the server property `turms.service.notification.meeting-updated.notify-meeting-participants`
  ///   is true (false by default), the server will send a meeting updated notification to all online sessions of the meeting participants.
  ///
  /// **Params**:
  /// * `meetingId`: The target meeting ID.
  /// * `name`: A new name of the meeting.
  /// If null, the meeting name will not be updated.
  /// * `intro`: A new intro of the meeting.
  /// If null, the meeting intro will not be updated.
  /// * `password`: A new password of the meeting.
  /// If null, the meeting password will not be updated.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<void>> updateMeeting(
    Int64 meetingId, {
    String? name,
    String? intro,
    String? password,
  }) async {
    final n = await _turmsClient.driver.send(UpdateMeetingRequest(
      id: meetingId,
      name: name,
      intro: intro,
      password: password,
    ));
    return n.toNullResponse();
  }

  /// Find meetings.
  ///
  /// **Params**:
  /// * `meetingIds`: The target meeting IDs.
  /// * `creatorIds`: The target creator IDs.
  /// * `userIds`: The target user IDs.
  /// * `groupIds`: The target group IDs.
  /// * `creationDateStart`: Find meetings created after the start date.
  /// * `creationDateEnd`: Find meetings created before the end date.
  /// * `skip`: The number of meetings to skip.
  /// * `limit`: The maximum number of meetings to be returned.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  Future<Response<List<Meeting>>> queryMeetings({
    Set<Int64>? meetingIds,
    Set<Int64>? creatorIds,
    Set<Int64>? userIds,
    Set<Int64>? groupIds,
    DateTime? creationDateStart,
    DateTime? creationDateEnd,
    int? skip,
    int? limit,
  }) async {
    final n = await _turmsClient.driver.send(QueryMeetingsRequest(
      ids: meetingIds,
      creatorIds: creatorIds,
      userIds: userIds,
      groupIds: groupIds,
      creationDateStart: creationDateStart?.toInt64(),
      creationDateEnd: creationDateEnd?.toInt64(),
      skip: skip,
      limit: limit,
    ));
    return n.toResponse((data) => data.meetings.meetings);
  }

  /// Accept a meeting invitation.
  ///
  /// Authorization:
  /// * If the logged-in user is not the meeting participant, or the meeting does not exist,
  ///   throws [ResponseException] with the code [ResponseStatusCode.acceptNonexistentMeetingInvitation].
  /// * If the password is incorrect, throws [ResponseException] with the code [ResponseStatusCode.acceptMeetingInvitationWithWrongPassword].
  /// * If the meeting has been canceled, throws [ResponseException] with the code [ResponseStatusCode.acceptMeetingInvitationOfCanceledMeeting].
  /// * If the meeting has expired, throws [ResponseException] with the code [ResponseStatusCode.acceptMeetingInvitationOfExpiredMeeting].
  /// * If the meeting has ended, throws [ResponseException] with the code [ResponseStatusCode.acceptMeetingInvitationOfEndedMeeting].
  /// * If the meeting is pending, throws [ResponseException] with the code [ResponseStatusCode.acceptMeetingInvitationOfPendingMeeting].
  ///
  /// **Params**:
  /// * `meetingId`: The target meeting ID.
  /// * `password`: The password of the meeting.
  ///
  /// **Returns**: The access token.
  /// To join a meeting, use the access token to communicate with the media server.
  /// For example, if the conference service of turms-service is provided by
  /// the "turms-plugin-livekit" plugin, the media server will be the livekit server.
  ///
  /// **Throws**: [ResponseException] if an error occurs.
  /// * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.conferenceNotImplemented].
  Future<Response<String>> acceptMeetingInvitation(
    Int64 meetingId, {
    String? password,
  }) async {
    final n = await _turmsClient.driver.send(UpdateMeetingInvitationRequest(
      meetingId: meetingId,
      responseAction: ResponseAction.ACCEPT,
      password: password,
    ));
    return n.toResponse((data) => data.string);
  }
}
