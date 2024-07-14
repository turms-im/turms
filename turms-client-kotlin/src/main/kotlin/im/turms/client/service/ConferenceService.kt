/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.turms.client.service

import im.turms.client.TurmsClient
import im.turms.client.exception.ResponseException
import im.turms.client.extension.getLongOrThrow
import im.turms.client.extension.toResponse
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.constant.ResponseAction
import im.turms.client.model.proto.model.conference.Meeting
import im.turms.client.model.proto.request.conference.CreateMeetingRequest
import im.turms.client.model.proto.request.conference.DeleteMeetingRequest
import im.turms.client.model.proto.request.conference.QueryMeetingsRequest
import im.turms.client.model.proto.request.conference.UpdateMeetingInvitationRequest
import im.turms.client.model.proto.request.conference.UpdateMeetingRequest
import java.util.Date

/**
 * @author James Chen
 */
class ConferenceService(private val turmsClient: TurmsClient) {
    /**
     * Create a meeting, and send a meeting invitation as a message to the participants, which means:
     * * If the meeting is a private meeting, the meeting invitation message will be sent to the target user.
     * * If the meeting is a group meeting, the meeting invitation message will be sent to all group members.
     * * If the meeting is a public meeting, no meeting invitation message will be sent.
     *
     * To join a meeting, call [acceptMeetingInvitation].
     *
     * Authorization:
     * * If the logged-in user has created too many meetings,
     *   throws [ResponseException] with the code [ResponseStatusCode.CREATE_MEETING_EXCEEDING_MAX_ACTIVE_MEETING_COUNT].
     *
     * For private meetings,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send meeting invitation messages to itself.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.SENDING_MESSAGES_TO_ONESELF_IS_DISABLED].
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send meeting invitation messages to strangers
     *   (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws [ResponseException] with the code [ResponseStatusCode.BLOCKED_USER_SEND_PRIVATE_MESSAGE].
     *
     * For group meetings,
     * * If the logged-in user has blocked the target group,
     *   throws [ResponseException] with the code [ResponseStatusCode.BLOCKED_USER_SEND_GROUP_MESSAGE].
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE].
     * * If the logged-in user has been muted,
     *   throws [ResponseException] with the code [ResponseStatusCode.MUTED_GROUP_MEMBER_SEND_MESSAGE].
     * * If the target group has been deleted,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP].
     * * If the target group has been muted,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_MESSAGE_TO_MUTED_GROUP].
     *
     * @param userId the target user ID. If not null, the meeting will be a private meeting.
     * If both [userId] and [groupId] are null, the meeting will be a public meeting.
     * @param groupId the target group ID. If not null, the meeting will be a group meeting.
     * If both [userId] and [groupId] are null, the meeting will be a public meeting.
     * @param name the name of the meeting.
     * @param intro the intro of the meeting.
     * @param password the password of the meeting.
     * @param startDate the start date of the meeting. If not null, the meeting will be a scheduled meeting.
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.CONFERENCE_NOT_IMPLEMENTED].
     * @return the meeting ID.
     */
    suspend fun createMeeting(
        userId: Long? = null,
        groupId: Long? = null,
        name: String? = null,
        intro: String? = null,
        password: String? = null,
        startDate: Date? = null,
    ): Response<Long> {
        return turmsClient.driver.send(
            CreateMeetingRequest.newBuilder().apply {
                userId?.let { this.userId = it }
                groupId?.let { this.groupId = it }
                name?.let { this.name = it }
                intro?.let { this.intro = it }
                password?.let { this.password = it }
                startDate?.let { this.startDate = it.time }
            },
        ).toResponse {
            it.getLongOrThrow()
        }
    }

    /**
     * Cancel a meeting.
     *
     * Authorization:
     * * If the server property `turms.service.conference.meeting.scheduling.allow-cancel`
     *   is false (true by default), throw [ResponseException] with the code [ResponseStatusCode.CANCELING_MEETING_IS_DISABLED].
     * * If the logged-in user is not the creator of the meeting, but is the meeting participant,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_CREATOR_TO_CANCEL_MEETING].
     * * If the logged-in user is not the creator of the meeting, and is not the meeting participant,
     *   throws [ResponseException] with the code [ResponseStatusCode.CANCEL_NONEXISTENT_MEETING].
     *
     * Notifications:
     * * If the server property `turms.service.notification.meeting-canceled.notify-requester-other-online-sessions`
     *   is true (false by default), the server will send a meeting canceled notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.meeting-canceled.notify-meeting-participants`
     *   is true (false by default), the server will send a meeting canceled notification to all online sessions of the meeting participants.
     *
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.CONFERENCE_NOT_IMPLEMENTED].
     */
    suspend fun cancelMeeting(meetingId: Long): Response<Unit> {
        return turmsClient.driver.send(
            DeleteMeetingRequest.newBuilder().apply {
                this.id = meetingId
            },
        ).toResponse()
    }

    /**
     * Update a meeting.
     *
     * Authorization:
     * * All meeting participants can update the name and intro of the meeting,
     * but only the meeting creator can update the password of the meeting.
     * * If the meeting does not exist, or the logged-in user is not the meeting participant,
     * throws [ResponseException] with the code [ResponseStatusCode.UPDATE_INFO_OF_NONEXISTENT_MEETING].
     * * If [password] is not null, and the logged-in user is not the creator of the meeting,
     * throws [ResponseException] with the code [ResponseStatusCode.NOT_CREATOR_TO_UPDATE_MEETING_PASSWORD].
     *
     * Notifications:
     * * If the server property `turms.service.notification.meeting-updated.notify-requester-other-online-sessions`
     *   is true (false by default), the server will send a meeting updated notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.meeting-updated.notify-meeting-participants`
     *   is true (false by default), the server will send a meeting updated notification to all online sessions of the meeting participants.
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
    suspend fun updateMeeting(
        meetingId: Long,
        name: String? = null,
        intro: String? = null,
        password: String? = null,
    ): Response<Unit> {
        return turmsClient.driver.send(
            UpdateMeetingRequest.newBuilder().apply {
                this.id = meetingId
                name?.let { this.name = it }
                intro?.let { this.intro = it }
                password?.let { this.password = it }
            },
        ).toResponse()
    }

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
    suspend fun queryMeetings(
        meetingIds: Set<Long>? = null,
        creatorIds: Set<Long>? = null,
        userIds: Set<Long>? = null,
        groupIds: Set<Long>? = null,
        creationDateStart: Date? = null,
        creationDateEnd: Date? = null,
        skip: Int? = null,
        limit: Int? = null,
    ): Response<List<Meeting>> {
        return turmsClient.driver.send(
            QueryMeetingsRequest.newBuilder().apply {
                meetingIds?.let { addAllIds(it) }
                creatorIds?.let { addAllCreatorIds(it) }
                userIds?.let { addAllUserIds(it) }
                groupIds?.let { addAllGroupIds(it) }
                creationDateStart?.let { this.creationDateStart = it.time }
                creationDateEnd?.let { this.creationDateEnd = it.time }
                skip?.let { this.skip = it }
                limit?.let { this.limit = it }
            },
        ).toResponse {
            it.meetings.meetingsList
        }
    }

    /**
     * Accept a meeting invitation.
     *
     * Authorization:
     * * If the logged-in user is not the meeting participant, or the meeting does not exist,
     *   throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_NONEXISTENT_MEETING_INVITATION].
     * * If the password is incorrect, throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_MEETING_INVITATION_WITH_WRONG_PASSWORD].
     * * If the meeting has been canceled, throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_CANCELED_MEETING].
     * * If the meeting has expired, throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_EXPIRED_MEETING].
     * * If the meeting has ended, throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_ENDED_MEETING].
     * * If the meeting is pending, throws [ResponseException] with the code [ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_PENDING_MEETING].
     *
     * @param meetingId the target meeting ID.
     * @param password the password of the meeting.
     * @throws ResponseException if an error occurs.
     * * If the server hasn't implemented the feature, throws [ResponseException] with the code [ResponseStatusCode.CONFERENCE_NOT_IMPLEMENTED].
     * @return the access token.
     * To join a meeting, use the access token to communicate with the media server.
     * For example, if the conference service of turms-service is provided by
     * the "turms-plugin-livekit" plugin, the media server will be the livekit server.
     */
    suspend fun acceptMeetingInvitation(
        meetingId: Long,
        password: String? = null,
    ): Response<String> {
        return turmsClient.driver.send(
            UpdateMeetingInvitationRequest.newBuilder().apply {
                this.meetingId = meetingId
                this.responseAction = ResponseAction.ACCEPT
                password?.let { this.password = it }
            },
        ).toResponse {
            it.string
        }
    }
}
