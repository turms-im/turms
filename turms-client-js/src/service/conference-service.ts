import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import TurmsClient from '../turms-client';
import Validator from '../util/validator';
import DataParser from '../util/data-parser';
import { ResponseAction } from '../model/proto/constant/response_action';

export default class ConferenceService {

    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    /**
     * Create a meeting, and send a meeting invitation as a message to the participants, which means:
     * * If the meeting is a private meeting, the meeting invitation message will be sent to the target user.
     * * If the meeting is a group meeting, the meeting invitation message will be sent to all group members.
     * * If the meeting is a public meeting, no meeting invitation message will be sent.
     *
     * @remarks
     * To join a meeting, call {@link acceptMeetingInvitation}.
     *
     * Authorization:
     * * If the logged-in user has created too many meetings,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CREATE_MEETING_EXCEEDING_MAX_ACTIVE_MEETING_COUNT}.
     *
     * For private meetings,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send meeting invitation messages to itself.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SENDING_MESSAGES_TO_ONESELF_IS_DISABLED}.
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send meeting invitation messages to strangers
     *   (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#BLOCKED_USER_SEND_PRIVATE_MESSAGE}.
     *
     * For group meetings,
     * * If the logged-in user has blocked the target group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#BLOCKED_USER_SEND_GROUP_MESSAGE}.
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE}.
     * * If the logged-in user has been muted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MUTED_GROUP_MEMBER_SEND_MESSAGE}.
     * * If the target group has been deleted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_MESSAGE_TO_INACTIVE_GROUP}.
     * * If the target group has been muted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_MESSAGE_TO_MUTED_GROUP}.
     *
     * @param userId - the target user ID. If not null, the meeting will be a private meeting.
     * If both {@link userId} and {@link groupId} are null, the meeting will be a public meeting.
     * @param groupId - the target group ID. If not null, the meeting will be a group meeting.
     * If both {@link userId} and {@link groupId} are null, the meeting will be a public meeting.
     * @param name - the name of the meeting.
     * @param intro - the intro of the meeting.
     * @param password - the password of the meeting.
     * @param startDate - the start date of the meeting. If not null, the meeting will be a scheduled meeting.
     * @returns the meeting ID.
     * @throws {@link ResponseError} if an error occurs.
     * * If the server hasn't implemented the feature, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CONFERENCE_NOT_IMPLEMENTED}.
     */
    createMeeting({
        userId,
        groupId,
        name,
        intro,
        password,
        startDate
    }: {
        userId?: string,
        groupId?: string,
        name?: string,
        intro?: string,
        password?: string,
        startDate?: Date
    }): Promise<Response<string>> {
        return this._turmsClient.driver.send({
            createMeetingRequest: {
                userId: userId,
                groupId: groupId,
                name: name,
                intro: intro,
                password: password,
                startDate: DataParser.getDateTimeStr(startDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, NotificationUtil.getLongOrThrow));
    }

    /**
     * Cancel a meeting.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.conference.meeting.scheduling.allow-cancel`
     *   is false (true by default), throw {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CANCELING_MEETING_IS_DISABLED}.
     * * If the logged-in user is not the creator of the meeting, but is the meeting participant,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_CREATOR_TO_CANCEL_MEETING}.
     * * If the logged-in user is not the creator of the meeting, and is not the meeting participant,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CANCEL_NONEXISTENT_MEETING}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.meeting-canceled.notify-requester-other-online-sessions`
     *   is true (false by default), the server will send a meeting canceled notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.meeting-canceled.notify-meeting-participants`
     *   is true (false by default), the server will send a meeting canceled notification to all online sessions of the meeting participants.
     * @throws {@link ResponseError} if an error occurs.
     * * If the server hasn't implemented the feature, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CONFERENCE_NOT_IMPLEMENTED}.
     */
    cancelMeeting({
        meetingId
                  }: {
        meetingId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(meetingId)
        ) {
            return ResponseError.notFalsyPromise('meetingId');
        }
        return this._turmsClient.driver.send({
            deleteMeetingRequest: {
                id: meetingId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update a meeting.
     *
     * @remarks
     * Authorization:
     * * All meeting participants can update the name and intro of the meeting,
     * but only the meeting creator can update the password of the meeting.
     * * If the meeting does not exist, or the logged-in user is not the meeting participant,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_INFO_OF_NONEXISTENT_MEETING}.
     * * If {@link password} is not null, and the logged-in user is not the creator of the meeting,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_CREATOR_TO_UPDATE_MEETING_PASSWORD}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.meeting-updated.notify-requester-other-online-sessions`
     *   is true (false by default), the server will send a meeting updated notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.meeting-updated.notify-meeting-participants`
     *   is true (false by default), the server will send a meeting updated notification to all online sessions of the meeting participants.
     *
     * @param meetingId - the target meeting ID.
     * @param name - a new name of the meeting.
     * If null, the meeting name will not be updated.
     * @param intro - a new intro of the meeting.
     * If null, the meeting intro will not be updated.
     * @param password - a new password of the meeting.
     * If null, the meeting password will not be updated.
     * @throws {@link ResponseError} if an error occurs.
     */
    updateMeeting({
        meetingId,
        name,
        intro,
        password
    }: {
        meetingId: string,
        name?: string,
        intro?: string,
        password?: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(meetingId)
        ) {
            return ResponseError.notFalsyPromise('meetingId');
        }
        return this._turmsClient.driver.send({
            updateMeetingRequest: {
                id: meetingId,
                name: name,
                intro: intro,
                password: password,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find meetings.
     *
     * @param meetingIds - the target meeting IDs.
     * @param creatorIds - the target creator IDs.
     * @param userIds - the target user IDs.
     * @param groupIds - the target group IDs.
     * @param creationDateStart - find meetings created after the start date.
     * @param creationDateEnd - find meetings created before the end date.
     * @param skip - the number of meetings to skip.
     * @param limit - the maximum number of meetings to be returned.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryMeetings(
        {
            meetingIds,
            creatorIds,
            userIds,
            groupIds,
            creationDateStart,
            creationDateEnd,
            skip,
            limit
        }: {
            meetingIds?: string[],
            creatorIds?: string[],
            userIds?: string[],
            groupIds?: string[],
            creationDateStart?: Date,
            creationDateEnd?: Date,
            skip?: number,
            limit?: number
        }
    ): Promise<Response<ParsedModel.Meeting[]>> {
        return this._turmsClient.driver.send({
            queryMeetingsRequest: {
                ids: meetingIds,
                creatorIds: creatorIds,
                userIds: userIds,
                groupIds: groupIds,
                creationDateStart: DataParser.getDateTimeStr(creationDateStart),
                creationDateEnd: DataParser.getDateTimeStr(creationDateEnd),
                skip,
                limit,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.meetings.meetings)));
    }

    /**
     * Accept a meeting invitation.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the meeting participant, or the meeting does not exist,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_NONEXISTENT_MEETING_INVITATION}.
     * * If the password is incorrect, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_MEETING_INVITATION_WITH_WRONG_PASSWORD}.
     * * If the meeting has been canceled, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_MEETING_INVITATION_OF_CANCELED_MEETING}.
     * * If the meeting has expired, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_MEETING_INVITATION_OF_EXPIRED_MEETING}.
     * * If the meeting has ended, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_MEETING_INVITATION_OF_ENDED_MEETING}.
     * * If the meeting is pending, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ACCEPT_MEETING_INVITATION_OF_PENDING_MEETING}.
     *
     * @param meetingId - the target meeting ID.
     * @param password - the password of the meeting.
     * @returns the access token.
     * To join a meeting, use the access token to communicate with the media server.
     * For example, if the conference service of turms-service is provided by
     * the "turms-plugin-livekit" plugin, the media server will be the livekit server.
     * @throws {@link ResponseError} if an error occurs.
     * * If the server hasn't implemented the feature, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CONFERENCE_NOT_IMPLEMENTED}.
     */
    acceptMeetingInvitation({
        meetingId,
        password
                                 }: {
        meetingId: string,
        password?: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(meetingId)
        ) {
            return ResponseError.notFalsyPromise('meetingId');
        }
        return this._turmsClient.driver.send({
            updateMeetingInvitationRequest: {
                meetingId: meetingId,
                responseAction: ResponseAction.ACCEPT,
                password: password,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => data.string));
    }

}