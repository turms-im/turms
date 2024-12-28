#ifndef TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H
#define TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H

namespace turms::client::model::ResponseStatusCode {
// **********************************************************
// * Defined on the client side
// **********************************************************

// **********************************************************
// * For application error
// **********************************************************

// Client - Common
constexpr int kConnectTimeout = 1;
constexpr int kDataNotFound = 10;
constexpr int kHttpError = 90;
constexpr int kHttpNotSuccessfulResponse = 91;

// Client - Request
constexpr int kInvalidRequest = 100;
constexpr int kClientRequestsTooFrequent = 101;
constexpr int kRequestTimeout = 102;
constexpr int kIllegalArgument = 103;

// Server - Notification
constexpr int kInvalidNotification = 200;
constexpr int kInvalidResponse = 201;

// **********************************************************
// * For business error
// **********************************************************

// User
constexpr int kClientSessionAlreadyEstablished = 300;
constexpr int kClientSessionHasBeenClosed = 301;

// Group

// Conversation

// Message

// Storage

// **********************************************************
// * Defined on the server side
// **********************************************************

// Successful responses
constexpr int kOk = 1000;
constexpr int kNoContent = 1001;
constexpr int kAlreadyUpToDate = 1002;

// **********************************************************
// * For application error
// **********************************************************

// Client
constexpr int kInvalidRequestFromServer = 1100;
constexpr int kClientRequestsTooFrequentFromServer = 1101;
constexpr int kIllegalArgumentFromServer = 1102;
constexpr int kRecordContainsDuplicateKey = 1103;
constexpr int kRequestedRecordsTooMany = 1104;
constexpr int kSendRequestFromNonexistentSession = 1105;
constexpr int kUnauthorizedRequest = 1106;

// Server
constexpr int kServerInternalError = 1200;
constexpr int kServerUnavailable = 1201;

// **********************************************************
// * For business error
// **********************************************************

// User

// User - Login
constexpr int kUnsupportedClientVersion = 2000;

constexpr int kLoginTimeout = 2010;
constexpr int kLoginAuthenticationFailed = 2011;
constexpr int kLoggingInUserNotActive = 2012;
constexpr int kLoginFromForbiddenDeviceType = 2013;

// User - Session
constexpr int kSessionSimultaneousConflictsDecline = 2100;
constexpr int kSessionSimultaneousConflictsNotify = 2101;
constexpr int kSessionSimultaneousConflictsOffline = 2102;
constexpr int kCreateExistingSession = 2103;
constexpr int kUpdateHeartbeatOfNonexistentSession = 2104;

// User - Location
constexpr int kUserLocationRelatedFeaturesAreDisabled = 2200;
constexpr int kQueryingNearestUsersBySessionIdIsDisabled = 2201;

// User - Info
constexpr int kUpdateInfoOfNonexistentUser = 2300;
constexpr int kNotFriendToQueryUserProfile = 2301;
constexpr int kBlockedUserToQueryUserProfile = 2302;

// User - Permission
constexpr int kQueryPermissionOfNonexistentUser = 2400;

// User - Relationship
constexpr int kAddNonRelatedUserToGroup = 2500;
constexpr int kCreateExistingRelationship = 2501;
constexpr int kCannotBlockOneself = 2502;

// User - Friend Request
constexpr int kCreateExistingFriendRequest = 2600;
constexpr int kBlockedUserToSendFriendRequest = 2601;
constexpr int kRecallNonPendingFriendRequest = 2602;
constexpr int kRecallingFriendRequestIsDisabled = 2603;
constexpr int kNotSenderToRecallFriendRequest = 2604;
constexpr int kUpdateNonPendingFriendRequest = 2605;
constexpr int kNotRecipientToUpdateFriendRequest = 2606;

// Group

// Group - Info
constexpr int kUpdateInfoOfNonexistentGroup = 3000;
constexpr int kNotGroupOwnerToUpdateGroupInfo = 3001;
constexpr int kNotGroupOwnerOrManagerToUpdateGroupInfo = 3002;
constexpr int kNotGroupMemberToUpdateGroupInfo = 3003;

// Group - Type
constexpr int kNoPermissionToCreateGroupWithGroupType = 3100;
constexpr int kCreateGroupWithNonexistentGroupType = 3101;
constexpr int kUpdatingGroupTypeIsDisabled = 3102;
constexpr int kNotGroupOwnerToUpdateGroupType = 3103;
constexpr int kNoPermissionToUpdateGroupToGroupType = 3104;
constexpr int kUpdateGroupToNonexistentGroupType = 3105;

// Group - Ownership
constexpr int kNotActiveUserToCreateGroup = 3200;
constexpr int kNotGroupOwnerToTransferGroup = 3201;
constexpr int kNotGroupOwnerToDeleteGroup = 3202;
constexpr int kGroupSuccessorNotGroupMember = 3203;
constexpr int kGroupOwnerQuitWithoutSpecifyingSuccessor = 3204;
constexpr int kMaxOwnedGroupsReached = 3205;
constexpr int kTransferNonexistentGroup = 3206;

// Group - Question
constexpr int kNotGroupOwnerOrManagerToCreateGroupQuestion = 3300;
constexpr int kNotGroupOwnerOrManagerToDeleteGroupQuestion = 3301;
constexpr int kNotGroupOwnerOrManagerToUpdateGroupQuestion = 3302;
constexpr int kNotGroupOwnerOrManagerToQueryGroupQuestionAnswer = 3303;
constexpr int kCreateGroupQuestionForInactiveGroup = 3304;
constexpr int kCreateGroupQuestionForGroupUsingJoinRequest = 3305;
constexpr int kCreateGroupQuestionForGroupUsingInvitation = 3306;
constexpr int kCreateGroupQuestionForGroupUsingMembershipRequest = 3307;
constexpr int kGroupQuestionAnswererHasBeenBlocked = 3308;
constexpr int kGroupMemberAnswerGroupQuestion = 3309;
constexpr int kAnswerInactiveGroupQuestion = 3310;
constexpr int kAnswerGroupQuestionOfInactiveGroup = 3311;

// Group - Member
constexpr int kAddUserToGroupRequiringUsersApproval = 3400;
constexpr int kAddUserToInactiveGroup = 3401;
constexpr int kNotGroupOwnerToAddGroupManager = 3402;
constexpr int kAddUserToGroupWithSizeLimitReached = 3403;
constexpr int kAddBlockedUserToGroup = 3404;
constexpr int kNotGroupOwnerToAddGroupMember = 3405;
constexpr int kNotGroupOwnerOrManagerToAddGroupMember = 3406;
constexpr int kNotGroupMemberToAddGroupMember = 3407;
constexpr int kNotGroupOwnerOrManagerToRemoveGroupMember = 3408;
constexpr int kNotGroupOwnerToRemoveGroupOwnerOrManager = 3409;
constexpr int kNotGroupOwnerToUpdateGroupMemberRole = 3410;
constexpr int kUpdateGroupMemberRoleOfNonexistentGroup = 3411;
constexpr int kNotGroupOwnerToUpdateGroupMemberInfo = 3412;
constexpr int kNotGroupOwnerOrManagerToUpdateGroupMemberInfo = 3413;
constexpr int kNotGroupMemberToUpdateGroupMemberInfo = 3414;
constexpr int kUpdateGroupMemberInfoOfNonexistentGroup = 3415;
constexpr int kUpdateInfoOfNonexistentGroupMember = 3416;
constexpr int kNotGroupOwnerOrManagerToMuteGroupMember = 3417;
constexpr int kMuteGroupMemberWithRoleEqualToOrHigherThanRequester = 3418;
constexpr int kMuteGroupMemberOfNonexistentGroup = 3419;
constexpr int kMuteNonexistentGroupMember = 3420;
constexpr int kNotGroupMemberToQueryGroupMemberInfo = 3421;
constexpr int kUserJoinGroupWithoutAcceptingGroupInvitation = 3422;
constexpr int kUserJoinGroupWithoutAnsweringGroupQuestion = 3423;
constexpr int kUserJoinGroupWithoutSendingGroupJoinRequest = 3424;

// Group - Blocklist
constexpr int kNotGroupOwnerOrManagerToAddBlockedUser = 3500;
constexpr int kNotGroupOwnerOrManagerToRemoveBlockedUser = 3501;

// Group - Join Request
constexpr int kBlockedUserSendGroupJoinRequest = 3600;
constexpr int kGroupMemberSendGroupJoinRequest = 3601;
constexpr int kNotSenderToRecallGroupJoinRequest = 3602;
constexpr int kNotGroupOwnerOrManagerToQueryGroupJoinRequest = 3603;
constexpr int kRecallNonPendingGroupJoinRequest = 3604;
constexpr int kSendGroupJoinRequestToInactiveGroup = 3605;
constexpr int kSendGroupJoinRequestToGroupUsingMembershipRequest = 3606;
constexpr int kSendGroupJoinRequestToGroupUsingInvitation = 3607;
constexpr int kSendGroupJoinRequestToGroupUsingQuestion = 3608;
constexpr int kRecallingGroupJoinRequestIsDisabled = 3609;
constexpr int kUpdateNonPendingGroupJoinRequest = 3610;
constexpr int kNotGroupOwnerOrManagerToUpdateGroupJoinRequest = 3611;

// Group - Invitation
constexpr int kSendGroupInvitationToGroupMember = 3700;
constexpr int kSendGroupInvitationToBlockedUser = 3701;
constexpr int kSendGroupInvitationToGroupNotRequiringUsersApproval = 3702;
constexpr int kNotGroupOwnerToSendGroupInvitation = 3703;
constexpr int kNotGroupOwnerOrManagerToSendGroupInvitation = 3704;
constexpr int kNotGroupMemberToSendGroupInvitation = 3705;
constexpr int kRecallingGroupInvitationIsDisabled = 3706;
constexpr int kNotGroupOwnerOrManagerToRecallGroupInvitation = 3707;
constexpr int kNotGroupOwnerOrManagerOrSenderToRecallGroupInvitation = 3708;
constexpr int kRecallNonPendingGroupInvitation = 3709;
constexpr int kUpdateNonPendingGroupInvitation = 3710;
constexpr int kNotInviteeToUpdateGroupInvitation = 3711;
constexpr int kNotGroupOwnerOrManagerToQueryGroupInvitation = 3712;

// Conversation

// Conversation - Read Date
constexpr int kUpdatingReadDateIsDisabled = 4000;
constexpr int kUpdatingReadDateIsDisabledByGroup = 4001;
constexpr int kUpdatingReadDateOfNonexistentGroupConversation = 4002;
constexpr int kNotGroupMemberToUpdateReadDateOfGroupConversation = 4003;
constexpr int kMovingReadDateForwardIsDisabled = 4004;

// Conversation - Typing Status
constexpr int kUpdatingTypingStatusIsDisabled = 4100;
constexpr int kNotGroupMemberToSendTypingStatus = 4101;
constexpr int kNotFriendToSendTypingStatus = 4102;

// Conversation - Setting
constexpr int kNotRelatedUserToUpdatePrivateConversationSetting = 4200;
constexpr int kNotGroupMemberToUpdateGroupConversationSetting = 4201;

// Message

// Message - Send
constexpr int kMessageRecipientNotActive = 5000;
constexpr int kNotFriendToSendPrivateMessage = 5001;
constexpr int kBlockedUserSendPrivateMessage = 5002;
constexpr int kBlockedUserSendGroupMessage = 5003;
constexpr int kSendMessageToInactiveGroup = 5004;
constexpr int kSendMessageToMutedGroup = 5005;
constexpr int kSendMessageToNonexistentGroup = 5006;
constexpr int kSendingMessagesToOneselfIsDisabled = 5007;
constexpr int kMutedGroupMemberSendMessage = 5008;
constexpr int kNotSpeakableGroupGuestToSendMessage = 5009;
constexpr int kMessageIsIllegal = 5010;
constexpr int kNotMessageRecipientOrSenderToForwardMessage = 5011;

// Message - Update
constexpr int kUpdatingMessageBySenderIsDisabled = 5100;
constexpr int kNotSenderToUpdateMessage = 5101;
constexpr int kUpdateMessageOfNonexistentGroup = 5102;
constexpr int kUpdatingGroupMessageBySenderIsDisabled = 5103;

// Message - Recall
constexpr int kRecallNonexistentMessage = 5200;
constexpr int kRecallingMessageIsDisabled = 5201;
constexpr int kNotSenderToRecallMessage = 5202;
constexpr int kRecallMessageOfNonexistentGroup = 5203;
constexpr int kMessageRecallTimeout = 5204;

// Message - Query
constexpr int kNotGroupMemberToQueryGroupMessages = 5300;

// Storage
constexpr int kStorageNotImplemented = 6000;

// Storage - Message attachment
constexpr int kNotFriendToUploadMessageAttachmentInPrivateConversation = 6100;
constexpr int kNotGroupMemberToUploadMessageAttachmentInGroupConversation = 6101;
constexpr int kNotUploaderToShareMessageAttachment = 6102;
constexpr int kNotUploaderOrGroupManagerToUnshareMessageAttachmentInGroupConversation = 6103;
constexpr int kNotUploaderToUnshareMessageAttachmentInPrivateConversation = 6104;
constexpr int kNotUploaderOrGroupManagerToDeleteMessageAttachmentInGroupConversation = 6105;
constexpr int kNotUploaderToDeleteMessageAttachmentInPrivateConversation = 6106;
constexpr int kNotUploaderOrSharedWithUserToDownloadMessageAttachment = 6107;

// Storage - Message attachment info
constexpr int kNotFriendToQueryMessageAttachmentInfoInPrivateConversation = 6130;
constexpr int kNotGroupMemberToQueryMessageAttachmentInfoInGroupConversation = 6131;

// Search
constexpr int kSearchingUserIsDisabled = 7100;
constexpr int kSearchingGroupIsDisabled = 7200;

// Conference
constexpr int kConferenceNotImplemented = 8000;

// Conference - Meeting
constexpr int kCreateMeetingExceedingMaxActiveMeetingCount = 8100;
constexpr int kNotCreatorToCancelMeeting = 8101;
constexpr int kCancelingMeetingIsDisabled = 8102;
constexpr int kCancelNonexistentMeeting = 8103;
constexpr int kNotCreatorToUpdateMeetingPassword = 8104;
constexpr int kUpdateInfoOfNonexistentMeeting = 8105;

constexpr int kAcceptMeetingInvitationWithWrongPassword = 8200;
constexpr int kAcceptMeetingInvitationOfCanceledMeeting = 8201;
constexpr int kAcceptMeetingInvitationOfEndedMeeting = 8202;
constexpr int kAcceptMeetingInvitationOfExpiredMeeting = 8203;
constexpr int kAcceptMeetingInvitationOfPendingMeeting = 8204;
constexpr int kAcceptNonexistentMeetingInvitation = 8205;

auto isSuccessCode(int businessCode) -> bool;

auto isErrorCode(int businessCode) -> bool;
}  // namespace turms::client::model::ResponseStatusCode

#endif  // TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H
