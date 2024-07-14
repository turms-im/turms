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

package im.turms.service.domain.conference.service;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.lang.StringPattern;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.ExtensionPointEventListener;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.MeetingIdType;
import im.turms.server.common.infra.property.constant.PasswordPolicy;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.IdProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.IntroProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.MeetingProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.NameProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.PasswordProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.QuotaProperties;
import im.turms.server.common.infra.property.env.service.business.conference.meeting.SchedulingProperties;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.unit.TimeUnit;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.conference.bo.CancelMeetingResult;
import im.turms.service.domain.conference.bo.UpdateMeetingInvitationResult;
import im.turms.service.domain.conference.bo.UpdateMeetingResult;
import im.turms.service.domain.conference.po.Meeting;
import im.turms.service.domain.conference.repository.MeetingRepository;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.message.bo.BuiltinSystemMessageType;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.infra.plugin.extension.ConferenceServiceProvider;
import im.turms.service.infra.plugin.extension.model.CreateMeetingOptions;
import im.turms.service.infra.plugin.extension.model.MeetingEndedEvent;
import im.turms.service.infra.proto.ProtoModelConvertor;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class ConferenceService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceService.class);

    private static final Method CREATE_MEETING_METHOD;
    private static final Method CANCEL_MEETING_METHOD;
    private static final Method COUNT_ACTIVE_MEETINGS_BY_USER_ID_METHOD;
    private static final Method ACCEPT_MEETING_INVITATION_METHOD;

    private static final Mono CONFERENCE_NOT_IMPLEMENTED =
            Mono.error(ResponseException.get(ResponseStatusCode.CONFERENCE_NOT_IMPLEMENTED));
    private static final Mono<im.turms.server.common.access.client.dto.model.conference.Meeting> CREATE_MEETING_NOT_IMPLEMENTED =
            CONFERENCE_NOT_IMPLEMENTED;
    private static final Mono<Void> CANCEL_MEETING_NOT_IMPLEMENTED = CONFERENCE_NOT_IMPLEMENTED;
    private static final Mono<Void> COUNT_ACTIVE_MEETINGS_BY_USER_ID_NOT_IMPLEMENTED =
            CONFERENCE_NOT_IMPLEMENTED;
    private static final Mono<UpdateMeetingInvitationResult> ACCEPT_MEETING_INVITATION_NOT_IMPLEMENTED =
            CONFERENCE_NOT_IMPLEMENTED;

    public static final Mono<Void> EXCEPTION_CREATE_MEETING_EXCEEDING_MAX_ACTIVE_MEETING_COUNT =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.CREATE_MEETING_EXCEEDING_MAX_ACTIVE_MEETING_COUNT));

    public static final Mono<CancelMeetingResult> EXCEPTION_CANCEL_NONEXISTENT_MEETING =
            Mono.error(ResponseException.get(ResponseStatusCode.CANCEL_NONEXISTENT_MEETING));
    public static final Mono<CancelMeetingResult> EXCEPTION_NOT_CREATOR_TO_CANCEL_MEETING =
            Mono.error(ResponseException.get(ResponseStatusCode.NOT_CREATOR_TO_CANCEL_MEETING));

    public static final Mono<UpdateMeetingResult> EXCEPTION_NOT_CREATOR_TO_UPDATE_MEETING_PASSWORD =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.NOT_CREATOR_TO_UPDATE_MEETING_PASSWORD));
    public static final Mono<UpdateMeetingResult> EXCEPTION_UPDATE_INFO_OF_NONEXISTENT_MEETING =
            Mono.error(
                    ResponseException.get(ResponseStatusCode.UPDATE_INFO_OF_NONEXISTENT_MEETING));

    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_MEETING_INVITATION_OF_EXPIRED_MEETING =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_EXPIRED_MEETING));
    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_MEETING_INVITATION_WITH_WRONG_PASSWORD =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_MEETING_INVITATION_WITH_WRONG_PASSWORD));
    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_MEETING_INVITATION_OF_CANCELED_MEETING =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_CANCELED_MEETING));
    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_MEETING_INVITATION_OF_ENDED_MEETING =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_ENDED_MEETING));
    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_MEETING_INVITATION_OF_PENDING_MEETING =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_MEETING_INVITATION_OF_PENDING_MEETING));
    public static final Mono<UpdateMeetingInvitationResult> EXCEPTION_ACCEPT_NONEXISTENT_MEETING_INVITATION =
            Mono.error(ResponseException
                    .get(ResponseStatusCode.ACCEPT_NONEXISTENT_MEETING_INVITATION));

    public static final Mono<Void> EXCEPTION_USER_ID_AND_GROUP_ID_MUST_NOT_BOTH_NON_NULL =
            Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "\"userId\" and \"groupId\" must not both be non-null"));
    public static final Mono<Void> EXCEPTION_USER_ID_AND_GROUP_ID_MUST_NOT_BOTH_NULL =
            Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "\"userId\" and \"groupId\" must not both be null"));

    private static final CreateMeetingOptions CREATE_MEETING_OPTIONS_FOR_PRIVATE_MEETING =
            new CreateMeetingOptions(2, TimeUnit.HOURS);
    // TODO: make configurable.
    private static final CreateMeetingOptions CREATE_MEETING_OPTIONS_FOR_GROUP_MEETING =
            new CreateMeetingOptions(100, TimeUnit.HOURS);

    /**
     * Use {@link SecureRandom} because users usually input meeting IDs to join meetings, so we
     * should make sure they are unpredictable.
     */
    private final SecureRandom randomForMeetingId;

    private final AtomicLong lastSeedTimeMillis = new AtomicLong();

    private final MeetingRepository meetingRepository;
    private final PluginManager pluginManager;

    private final MessageService messageService;
    private final UserService userService;
    private final GroupMemberService groupMemberService;

    private MeetingIdType idType;
    private int nameMinLength;
    private int nameMaxLength;
    private int introMinLength;
    private int introMaxLength;
    private long maxAllowedStartDateOffsetMillis;
    private boolean allowCancel;
    private boolean isMaxActiveMeetingCountPerUserEnabled;
    private int maxActiveMeetingCountPerUser;
    private PasswordPolicy passwordPolicy;
    private StringPattern passwordPattern;
    private int passwordMinLength;
    private int passwordMaxLength;

    static {
        try {
            CREATE_MEETING_METHOD =
                    ConferenceServiceProvider.class.getDeclaredMethod("createMeeting",
                            Long.class,
                            Meeting.class,
                            CreateMeetingOptions.class);
            CANCEL_MEETING_METHOD = ConferenceServiceProvider.class
                    .getDeclaredMethod("cancelMeeting", Long.class, Long.class);
            COUNT_ACTIVE_MEETINGS_BY_USER_ID_METHOD = ConferenceServiceProvider.class
                    .getDeclaredMethod("countActiveMeetingsByUserId", Long.class);
            ACCEPT_MEETING_INVITATION_METHOD = ConferenceServiceProvider.class
                    .getDeclaredMethod("acceptMeetingInvitation", Long.class, Long.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public ConferenceService(
            MeetingRepository meetingRepository,
            TurmsPropertiesManager propertiesManager,
            PluginManager pluginManager,
            MessageService messageService,
            UserService userService,
            GroupMemberService groupMemberService) {
        this.meetingRepository = meetingRepository;
        this.pluginManager = pluginManager;
        this.messageService = messageService;
        this.userService = userService;
        this.groupMemberService = groupMemberService;
        try {
            // Use DRBG instead of NativePRNG for a consistent and stable implementation,
            // and DRGB supports reseeding.
            randomForMeetingId = SecureRandom.getInstance("DRBG");
        } catch (NoSuchAlgorithmException e) {
            throw new IncompatibleJvmException("The SecureRandom (DRBG) is not supported", e);
        }
        try {
            randomForMeetingId.reseed();
        } catch (UnsupportedOperationException e) {
            throw new IncompatibleJvmException(
                    "The SecureRandom (DRBG) does not support reseed()",
                    e);
        }
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
        pluginManager.addExtensionPointEventListener(ConferenceServiceProvider.class,
                new ExtensionPointEventListener<>() {
                    @Override
                    public void onExtensionStarted(ConferenceServiceProvider extension) {
                        extension
                                .addMeetingEndedEventListener(
                                        ConferenceService.this::handleMeetingEndedEvent)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Failed to add the meeting ended event listener",
                                                t));
                    }
                });
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        MeetingProperties meetingProperties = properties.getService()
                .getConference()
                .getMeeting();
        IdProperties idProperties = meetingProperties.getId();
        NameProperties nameProperties = meetingProperties.getName();
        IntroProperties introProperties = meetingProperties.getIntro();
        PasswordProperties passwordProperties = meetingProperties.getPassword();
        QuotaProperties quotaProperties = meetingProperties.getQuota();
        SchedulingProperties schedulingProperties = meetingProperties.getScheduling();

        idType = idProperties.getType();
        nameMinLength = nameProperties.getMinLength();
        nameMaxLength = nameProperties.getMaxLength();
        introMinLength = introProperties.getMinLength();
        introMaxLength = introProperties.getMaxLength();
        passwordPolicy = passwordProperties.getPolicy();
        passwordPattern = passwordProperties.getType();
        passwordMinLength = passwordProperties.getMinLength();
        passwordMaxLength = passwordProperties.getMaxLength();
        maxAllowedStartDateOffsetMillis =
                schedulingProperties.getMaxAllowedStartDateOffsetSeconds() * 1000L;
        allowCancel = schedulingProperties.isAllowCancel();
        int maxActiveMeetingCountPerUser = quotaProperties.getMaxActiveMeetingCountPerUser();
        isMaxActiveMeetingCountPerUserEnabled = maxActiveMeetingCountPerUser > 0;
        this.maxActiveMeetingCountPerUser = maxActiveMeetingCountPerUser;
    }

    private void handleMeetingEndedEvent(MeetingEndedEvent meetingEndedEvent) {
        Long meetingId = meetingEndedEvent.getMeetingId();
        Date timestamp = meetingEndedEvent.getTimestamp();
        meetingRepository.updateEndDate(meetingId, timestamp)
                .subscribe(null,
                        t -> LOGGER.error("Failed to update the end date of the meeting ("
                                + +meetingId
                                + ") to: "
                                + timestamp.getTime(), t));
    }

    public Mono<im.turms.server.common.access.client.dto.model.conference.Meeting> authAndCreateMeeting(
            @NotNull Long requesterId,
            @NotNull byte[] senderIp,
            @Nullable Long userId,
            @Nullable Long groupId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String password,
            @Nullable @FutureOrPresent Date startDate) {
        Date now = new Date();
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(senderIp, "senderIp");
            Validator.inOptionalLengthRange(name, "name", nameMinLength, nameMaxLength);
            Validator.inOptionalLengthRange(intro, "intro", introMinLength, introMaxLength);
            password = validateAndGeneratePassword(password);
            if (startDate != null) {
                long startDateTime = startDate.getTime();
                if (startDateTime > now.getTime() + maxAllowedStartDateOffsetMillis) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The meeting start date is too far in the future");
                }
            }
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (!hasConferenceServiceProvider()) {
            return CONFERENCE_NOT_IMPLEMENTED;
        }
        Mono<Void> check;
        if (userId == null) {
            if (groupId == null) {
                check = Mono.empty();
            } else {
                check = userService
                        .isAllowedToSendMessageToTarget(true, false, requesterId, groupId)
                        .flatMap(permission -> permission == ServicePermission.OK
                                ? Mono.empty()
                                : Mono.error(ResponseException.get(permission.code(),
                                        permission.reason())));
            }
        } else {
            check = userService.isAllowedToSendMessageToTarget(false, false, requesterId, userId)
                    .flatMap(permission -> permission == ServicePermission.OK
                            ? Mono.empty()
                            : Mono.error(
                                    ResponseException.get(permission.code(), permission.reason())));
        }
        if (isMaxActiveMeetingCountPerUserEnabled) {
            check = check.then(Mono.defer(() -> pluginManager.invokeFirstExtensionPoint(
                    ConferenceServiceProvider.class,
                    COUNT_ACTIVE_MEETINGS_BY_USER_ID_METHOD,
                    COUNT_ACTIVE_MEETINGS_BY_USER_ID_NOT_IMPLEMENTED,
                    extensionPoint -> extensionPoint.countActiveMeetingsByUserId(requesterId)
                            .flatMap(count -> count > maxActiveMeetingCountPerUser
                                    ? EXCEPTION_CREATE_MEETING_EXCEEDING_MAX_ACTIVE_MEETING_COUNT
                                    : Mono.empty()))));
        }
        String finalPassword = password;
        return check.then(Mono.defer(() -> {
            if (startDate == null || now.after(startDate)) {
                return meetingRepository.inTransaction(clientSession -> insertMeetingRecord(now,
                        0,
                        requesterId,
                        name,
                        intro,
                        finalPassword,
                        now,
                        userId,
                        groupId,
                        clientSession)
                        .flatMap(meeting -> pluginManager.invokeFirstExtensionPoint(
                                ConferenceServiceProvider.class,
                                CREATE_MEETING_METHOD,
                                CREATE_MEETING_NOT_IMPLEMENTED,
                                extensionPoint -> extensionPoint.createMeeting(requesterId,
                                        meeting,
                                        userId == null
                                                ? CREATE_MEETING_OPTIONS_FOR_GROUP_MEETING
                                                : CREATE_MEETING_OPTIONS_FOR_PRIVATE_MEETING)
                                        .map(result -> ProtoModelConvertor
                                                .meeting2proto(meeting, result.accessToken())
                                                .build()))));
            }
            return insertMeetingRecord(now,
                    0,
                    requesterId,
                    name,
                    intro,
                    finalPassword,
                    startDate,
                    userId,
                    groupId,
                    null).map(
                            meeting -> ProtoModelConvertor.meeting2proto(meeting, null)
                                    .build());
        }))
                .flatMap(meeting -> sendMeetingInvitationMessage(requesterId,
                        senderIp,
                        meeting,
                        userId,
                        groupId).thenReturn(meeting));
    }

    private Mono<Meeting> insertMeetingRecord(
            Date now,
            int retryTimes,
            @NotNull Long requesterId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String password,
            @NotNull Date startDate,
            @Nullable Long userId,
            @Nullable Long groupId,
            @Nullable ClientSession clientSession) {
        long time = now.getTime();
        Long id = switch (idType) {
            case DIGIT_9 -> generate9DigitId(time);
            case DIGIT_10 -> generate10DigitId(time);
        };
        Meeting meeting = new Meeting(
                id,
                requesterId,
                userId,
                groupId,
                name,
                intro,
                password,
                now,
                startDate,
                null,
                null);
        return meetingRepository.insert(meeting, clientSession)
                .thenReturn(meeting)
                .onErrorResume(DuplicateKeyException.class, e -> {
                    if (retryTimes >= 32) {
                        // If the error occurs, it means it is the time to extend the ID range.
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                "Generated a duplicate key: "
                                        + id
                                        + ", retry times exhausted. The ID range should be extended",
                                e));
                    }
                    // Note that it is fine and expected to generate some duplicate IDs,
                    // but the ID range should be extended if duplicate IDs are generated
                    // frequently.
                    LOGGER.warn("Generated a duplicate key: {}, retry times: {}",
                            id,
                            retryTimes,
                            e);
                    return insertMeetingRecord(now,
                            retryTimes + 1,
                            requesterId,
                            name,
                            intro,
                            password,
                            startDate,
                            userId,
                            groupId,
                            clientSession);
                });
    }

    public Mono<CancelMeetingResult> authAndCancelMeeting(
            @NotNull Long requesterId,
            @NotNull Long meetingId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(meetingId, "meetingId");
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (!allowCancel) {
            return Mono
                    .error(ResponseException.get(ResponseStatusCode.CANCELING_MEETING_IS_DISABLED));
        }
        if (!hasConferenceServiceProvider()) {
            return CONFERENCE_NOT_IMPLEMENTED;
        }
        return meetingRepository.findById(meetingId)
                .flatMap(meeting -> {
                    if (!requesterId.equals(meeting.getCreatorId())) {
                        return isAllowedToViewMeetingInfo(requesterId, meeting)
                                .flatMap(allowed -> allowed
                                        ? EXCEPTION_NOT_CREATOR_TO_CANCEL_MEETING
                                        : EXCEPTION_CANCEL_NONEXISTENT_MEETING);
                    }
                    return meetingRepository.inTransaction(clientSession -> meetingRepository
                            .updateCancelDateIfNotCanceled(meetingId, new Date())
                            .flatMap(updateResult -> {
                                if (updateResult.getModifiedCount() == 0) {
                                    return Mono.just(CancelMeetingResult.FAILED);
                                }
                                return pluginManager
                                        .invokeFirstExtensionPoint(ConferenceServiceProvider.class,
                                                CANCEL_MEETING_METHOD,
                                                CANCEL_MEETING_NOT_IMPLEMENTED,
                                                extensionPoint -> extensionPoint
                                                        .cancelMeeting(requesterId, meetingId))
                                        .thenReturn(new CancelMeetingResult(true, meeting));
                            }));
                })
                .switchIfEmpty(EXCEPTION_CANCEL_NONEXISTENT_MEETING);
    }

    public Mono<Set<Long>> queryMeetingParticipants(@Nullable Long userId, @Nullable Long groupId) {
        if (userId != null) {
            return Mono.just(Set.of(userId));
        }
        if (groupId != null) {
            return groupMemberService.queryGroupMemberIds(groupId, false);
        }
        return PublisherPool.emptySet();
    }

    public Mono<UpdateMeetingResult> authAndUpdateMeeting(
            @NotNull Long requesterId,
            @NotNull Long meetingId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String password) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(meetingId, "meetingId");
            Validator.inOptionalLengthRange(name, "name", nameMinLength, nameMaxLength);
            Validator.inOptionalLengthRange(intro, "intro", introMinLength, introMaxLength);
            if (password != null) {
                validatePassword(password);
            }
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (name == null && intro == null && password == null) {
            return Mono.just(UpdateMeetingResult.FAILED);
        }
        return meetingRepository.findById(meetingId)
                .flatMap(meeting -> {
                    // TODO: support configuring the logic of authorization.
                    if (password != null) {
                        if (requesterId.equals(meeting.getCreatorId())) {
                            return meetingRepository.updateMeeting(meetingId, name, intro, password)
                                    .map(updateResult -> updateResult.getModifiedCount() > 0
                                            ? new UpdateMeetingResult(true, meeting)
                                            : UpdateMeetingResult.FAILED);
                        }
                        return isAllowedToViewMeetingInfo(requesterId, meeting)
                                .flatMap(allowed -> allowed
                                        ? EXCEPTION_NOT_CREATOR_TO_UPDATE_MEETING_PASSWORD
                                        : EXCEPTION_UPDATE_INFO_OF_NONEXISTENT_MEETING);
                    }
                    return isAllowedToViewMeetingInfo(requesterId, meeting)
                            .flatMap(allowed -> allowed
                                    ? meetingRepository.updateMeeting(meetingId, name, intro, null)
                                            .map(updateResult -> updateResult.getModifiedCount() > 0
                                                    ? new UpdateMeetingResult(true, meeting)
                                                    : UpdateMeetingResult.FAILED)
                                    : EXCEPTION_UPDATE_INFO_OF_NONEXISTENT_MEETING);
                });
    }

    private Mono<Void> sendMeetingInvitationMessage(
            @NotNull Long requesterId,
            @NotNull byte[] senderIp,
            @NotNull im.turms.server.common.access.client.dto.model.conference.Meeting meeting,
            @Nullable Long userId,
            @Nullable Long groupId) {
        if (userId != null) {
            if (groupId != null) {
                return EXCEPTION_USER_ID_AND_GROUP_ID_MUST_NOT_BOTH_NON_NULL;
            }
            var meetingInfoForParticipant = getMeetingInfoForParticipant(meeting);
            return messageService.saveAndSendMessage(null,
                    requesterId,
                    null,
                    senderIp,
                    null,
                    true,
                    true,
                    null,
                    List.of(BuiltinSystemMessageType.MEETING_INVITATION_BYTES,
                            meetingInfoForParticipant.toByteArray()),
                    userId,
                    null,
                    null);
        } else if (groupId == null) {
            return EXCEPTION_USER_ID_AND_GROUP_ID_MUST_NOT_BOTH_NULL;
        }
        var meetingInfoForParticipant = getMeetingInfoForParticipant(meeting);
        return messageService.saveAndSendMessage(null,
                requesterId,
                null,
                senderIp,
                null,
                false,
                true,
                null,
                List.of(BuiltinSystemMessageType.MEETING_INVITATION_BYTES,
                        meetingInfoForParticipant.toByteArray()),
                groupId,
                null,
                null);
    }

    /**
     * TODO: Allow developers to configuring whether a meeting invitation can only be updated once.
     */
    public Mono<UpdateMeetingInvitationResult> authAndUpdateMeetingInvitation(
            @NotNull Long requesterId,
            @NotNull Long meetingId,
            @Nullable String password,
            @NotNull ResponseAction responseAction) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(meetingId, "meetingId");
            Validator.notNull(responseAction, "responseAction");
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (!hasConferenceServiceProvider()) {
            return CONFERENCE_NOT_IMPLEMENTED;
        }
        if (ResponseAction.IGNORE == responseAction
                || ResponseAction.UNRECOGNIZED == responseAction) {
            return Mono.just(new UpdateMeetingInvitationResult(false, null, null));
        }
        return meetingRepository.findById(meetingId)
                .flatMap(meeting -> {
                    Long userId = meeting.getUserId();
                    Long groupId = meeting.getGroupId();
                    // TODO: check if the user/group is active
                    Mono<UpdateMeetingInvitationResult> check = Mono.defer(() -> {
                        if (!isPasswordMatched(password, meeting.getPassword())) {
                            return EXCEPTION_ACCEPT_MEETING_INVITATION_WITH_WRONG_PASSWORD;
                        } else if (ResponseAction.DECLINE == responseAction) {
                            return Mono
                                    .just(new UpdateMeetingInvitationResult(true, null, meeting));
                        }
                        // Check for accepting.
                        Date startDate = meeting.getStartDate();
                        long now = System.currentTimeMillis();
                        if (startDate == null) {
                            long idleTimeoutMillis = userId == null
                                    ? CREATE_MEETING_OPTIONS_FOR_GROUP_MEETING.idleTimeoutMillis()
                                    : CREATE_MEETING_OPTIONS_FOR_PRIVATE_MEETING
                                            .idleTimeoutMillis();
                            long expiredDate = meeting.getCreationDate()
                                    .getTime() + idleTimeoutMillis;
                            if (expiredDate <= now) {
                                return EXCEPTION_ACCEPT_MEETING_INVITATION_OF_EXPIRED_MEETING;
                            }
                        }
                        if (isMeetingCanceled(meeting.getCancelDate(), now)) {
                            return EXCEPTION_ACCEPT_MEETING_INVITATION_OF_CANCELED_MEETING;
                        }
                        if (!isMeetingStarted(startDate, now)) {
                            return EXCEPTION_ACCEPT_MEETING_INVITATION_OF_PENDING_MEETING;
                        }
                        if (isMeetingEnded(meeting.getEndDate(), now)) {
                            return EXCEPTION_ACCEPT_MEETING_INVITATION_OF_ENDED_MEETING;
                        }
                        return pluginManager.invokeFirstExtensionPoint(
                                ConferenceServiceProvider.class,
                                ACCEPT_MEETING_INVITATION_METHOD,
                                ACCEPT_MEETING_INVITATION_NOT_IMPLEMENTED,
                                extensionPoint -> extensionPoint
                                        .acceptMeetingInvitation(requesterId, meeting.getId())
                                        .map(result -> new UpdateMeetingInvitationResult(
                                                true,
                                                result.accessToken(),
                                                meeting)));
                    });
                    if (userId != null) {
                        return userId.equals(requesterId)
                                ? check
                                : EXCEPTION_ACCEPT_NONEXISTENT_MEETING_INVITATION;
                    } else if (groupId != null) {
                        return groupMemberService.isGroupMember(groupId, requesterId, false)
                                .flatMap(isGroupMember -> isGroupMember
                                        ? check
                                        : EXCEPTION_ACCEPT_NONEXISTENT_MEETING_INVITATION);
                    } else {
                        return check;
                    }
                })
                .switchIfEmpty(EXCEPTION_ACCEPT_NONEXISTENT_MEETING_INVITATION);
    }

    public Flux<Meeting> authAndQueryMeetings(
            @NotNull Long requesterId,
            @Nullable Set<Long> ids,
            @Nullable Set<Long> creatorIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<Long> groupIds,
            @Nullable Date creationDateStart,
            @Nullable Date creationDateEnd,
            @Nullable Integer skip,
            @Nullable Integer limit) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (Exception e) {
            return Flux.error(e);
        }
        int creatorIdCount = CollectionUtil.getSize(creatorIds);
        int userIdCount = CollectionUtil.getSize(userIds);
        int groupIdCount = CollectionUtil.getSize(groupIds);
        if (userIdCount > 0) {
            if (groupIdCount > 0) {
                return Flux.empty();
            }
            if (userIdCount == 1 && userIds.contains(requesterId)) {
                return meetingRepository.find(ids,
                        creatorIds,
                        userIds,
                        null,
                        creationDateStart,
                        creationDateEnd,
                        skip,
                        limit);
            }
            return switch (creatorIdCount) {
                case 0 -> meetingRepository.find(ids,
                        Set.of(requesterId),
                        userIds,
                        null,
                        creationDateStart,
                        creationDateEnd,
                        skip,
                        limit);
                case 1 -> {
                    if (creatorIds.contains(requesterId)) {
                        yield meetingRepository.find(ids,
                                creatorIds,
                                userIds,
                                null,
                                creationDateStart,
                                creationDateEnd,
                                skip,
                                limit);
                    } else {
                        yield Flux.empty();
                    }
                }
                default -> Flux.empty();
            };
        }
        if (groupIdCount == 0) {
            if (creatorIdCount == 0) {
                return meetingRepository.find(ids,
                        requesterId,
                        requesterId,
                        creationDateStart,
                        creationDateEnd,
                        skip,
                        limit);
            } else if (creatorIdCount == 1 && creatorIds.contains(requesterId)) {
                return meetingRepository.find(ids,
                        creatorIds,
                        null,
                        null,
                        creationDateStart,
                        creationDateEnd,
                        skip,
                        limit);
            } else {
                return Flux.empty();
            }
        }
        Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
        return groupMemberService.queryUserJoinedGroupIds(requesterId)
                .collect(Collectors.toCollection(recyclableList::getValue))
                .flatMapMany(joinedGroupIds -> {
                    if (joinedGroupIds.isEmpty()) {
                        return Flux.empty();
                    }
                    joinedGroupIds.clear();
                    return meetingRepository.find(ids,
                            creatorIds,
                            null,
                            joinedGroupIds,
                            creationDateStart,
                            creationDateEnd,
                            skip,
                            limit);
                })
                .doFinally(signalType -> recyclableList.recycle());
    }

    private Mono<Boolean> isAllowedToViewMeetingInfo(
            @NotNull Long requesterId,
            @NotNull Meeting meeting) {
        if (requesterId.equals(meeting.getCreatorId()) || requesterId.equals(meeting.getUserId())) {
            return PublisherPool.TRUE;
        }
        Long groupId = meeting.getGroupId();
        if (groupId == null) {
            return PublisherPool.TRUE;
        }
        return groupMemberService.isGroupMember(groupId, requesterId, false);
    }

    private boolean isMeetingCanceled(@Nullable Date cancelDate, long now) {
        return cancelDate != null && cancelDate.getTime() >= now;
    }

    private boolean isMeetingEnded(@Nullable Date endDate, long now) {
        return endDate != null && endDate.getTime() <= now;
    }

    private boolean isMeetingStarted(@NotNull Date startDate, long now) {
        return startDate.getTime() <= now;
    }

    private boolean hasConferenceServiceProvider() {
        return pluginManager.hasRunningExtensions(ConferenceServiceProvider.class);
    }

    private im.turms.server.common.access.client.dto.model.conference.Meeting getMeetingInfoForParticipant(
            im.turms.server.common.access.client.dto.model.conference.Meeting meeting) {
        long id = meeting.getId();
        Long groupId = meeting.hasGroupId()
                ? meeting.getGroupId()
                : null;
        long creatorId = meeting.getCreatorId();
        String name = meeting.hasName()
                ? meeting.getName()
                : null;
        String intro = meeting.hasIntro()
                ? meeting.getIntro()
                : null;
        String password = meeting.hasPassword()
                ? meeting.getPassword()
                : null;
        long startDate = meeting.getStartDate();
        Long endDate = meeting.hasEndDate()
                ? meeting.getEndDate()
                : null;
        Long cancelDate = meeting.hasCancelDate()
                ? meeting.getCancelDate()
                : null;

        var builder = ClientMessagePool.getMeetingBuilder()
                .setId(id)
                .setCreatorId(creatorId)
                .setStartDate(startDate);
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        if (name != null) {
            builder.setName(name);
        }
        if (intro != null) {
            builder.setIntro(intro);
        }
        if (password != null) {
            builder.setPassword(password);
        }
        if (endDate != null) {
            builder.setEndDate(endDate);
        }
        if (cancelDate != null) {
            builder.setCancelDate(cancelDate);
        }
        return builder.setId(meeting.getId())
                .build();
    }

    /**
     * @implNote Though the range of the ID is [0, 1,000,000,000), we still use long as it is easy
     *           to extend the ID range if we need in the future, e.g.: extend to 10 digits.
     */
    private long generate9DigitId(long now) {
        long lastSeedTime = lastSeedTimeMillis.get();
        if (now - lastSeedTime > 1000 * 60 * 60 * 24) {
            if (lastSeedTimeMillis.compareAndSet(lastSeedTime, now)) {
                randomForMeetingId.reseed();
            } else {
                return generate9DigitId(now);
            }
        }
        return randomForMeetingId.nextInt(1_000_000_000);
    }

    private long generate10DigitId(long now) {
        long lastSeedTime = lastSeedTimeMillis.get();
        if (now - lastSeedTime > 1000 * 60 * 60 * 24) {
            if (lastSeedTimeMillis.compareAndSet(lastSeedTime, now)) {
                randomForMeetingId.reseed();
            } else {
                return generate10DigitId(now);
            }
        }
        return randomForMeetingId.nextLong(10_000_000_000L);
    }

    private boolean isPasswordMatched(@Nullable String password, @Nullable String actualPassword) {
        return actualPassword == null || actualPassword.isEmpty()
                ? password == null || password.isEmpty()
                : actualPassword.equals(password);
    }

    @Nullable
    private String validateAndGeneratePassword(@Nullable String password) {
        return switch (passwordPolicy) {
            case USER_INPUT_REQUIRED -> {
                if (password == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The password is required");
                } else {
                    Validator.inOptionalLengthRange(password,
                            "password",
                            passwordMinLength,
                            passwordMaxLength);
                    yield password;
                }
            }
            case USER_INPUT_OPTIONAL -> {
                Validator.inOptionalLengthRange(password,
                        "password",
                        passwordMinLength,
                        passwordMaxLength);
                yield password;
            }
            case USER_INPUT_OR_SYSTEM_GENERATED -> {
                if (password == null) {
                    yield generatePassword();
                }
                Validator.inOptionalLengthRange(password,
                        "password",
                        passwordMinLength,
                        passwordMaxLength);
                yield password;
            }
            case SYSTEM_GENERATED -> generatePassword();
            case PROHIBITED -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The password is prohibited");
        };
    }

    private void validatePassword(String password) {
        switch (passwordPolicy) {
            case USER_INPUT_REQUIRED, USER_INPUT_OPTIONAL,
                    USER_INPUT_OR_SYSTEM_GENERATED ->
                Validator.inOptionalLengthRange(password,
                        "password",
                        passwordMinLength,
                        passwordMaxLength);
            case SYSTEM_GENERATED ->
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The user cannot update the password");
            case PROHIBITED -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The password is prohibited");
        }
    }

    private String generatePassword() {
        return switch (passwordPattern) {
            case ALPHANUMERIC ->
                RandomUtil.nextAlphanumericString(passwordMinLength, passwordMaxLength);
            case NUMERIC -> RandomUtil.nextNumericString(passwordMinLength, passwordMaxLength);
            case ALPHABETIC ->
                RandomUtil.nextAlphabeticString(passwordMinLength, passwordMaxLength);
        };
    }
}