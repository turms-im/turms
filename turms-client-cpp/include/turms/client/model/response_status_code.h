#ifndef TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H
#define TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H

namespace turms {
namespace client {
namespace model {

namespace ResponseStatusCode {

// **********************************************************
// * Defined on the client side
// **********************************************************

// **********************************************************
// * For application error
// **********************************************************

// Client - Common
const int kConnectTimeout = 1;
const int kDataNotFound = 10;
const int kHttpError = 90;
const int kHttpNotSuccessfulResponse = 91;

// Client - Request
const int kInvalidRequest = 100;
const int kClientRequestsTooFrequent = 101;
const int kRequestTimeout = 102;
const int kIllegalArgument = 103;

// Server - Notification
const int kInvalidNotification = 200;
const int kInvalidResponse = 201;

// **********************************************************
// * For business error
// **********************************************************

// User
const int kClientSessionAlreadyEstablished = 300;
const int kClientSessionHasBeenClosed = 301;

// Group

// Conversation

// Message

// Storage

// **********************************************************
// * Defined on the server side
// **********************************************************

// Successful responses
const int kOk = 1000;
const int kNoContent = 1001;
const int kAlreadyUpToDate = 1002;

// **********************************************************
// * For application error
// **********************************************************

// Client
const int kInvalidRequestFromServer = 1100;
const int kClientRequestsTooFrequentFromServer = 1101;
const int kIllegalArgumentFromServer = 1102;
const int kRecordContainsDuplicateKey = 1103;
const int kRequestedRecordsTooMany = 1104;
const int kSendRequestFromNonExistingSession = 1105;
const int kUnauthorizedRequest = 1106;

// Server
const int kServerInternalError = 1200;
const int kServerUnavailable = 1201;

// **********************************************************
// * For business error
// **********************************************************

// User

// User - Login
const int kUnsupportedClientVersion = 2000;

const int kLoginTimeout = 2010;
const int kLoginAuthenticationFailed = 2011;
const int kLoggingInUserNotActive = 2012;
const int kLoginFromForbiddenDeviceType = 2013;

// User - Session
const int kSessionSimultaneousConflictsDecline = 2100;
const int kSessionSimultaneousConflictsNotify = 2101;
const int kSessionSimultaneousConflictsOffline = 2102;
const int kCreateExistingSession = 2103;
const int kUpdateNonExistingSessionHeartbeat = 2104;

// User - Location
const int kUserLocationRelatedFeaturesAreDisabled = 2200;
const int kQueryingNearestUsersBySessionIdIsDisabled = 2201;

// User - Info
const int kUpdateInfoOfNonExistingUser = 2300;
const int kUserProfileNotFound = 2301;
const int kProfileRequesterNotInContactsOrBlocked = 2302;
const int kProfileRequesterHasBeenBlocked = 2303;

// User - Permission
const int kQueryPermissionOfNonExistingUser = 2400;

// User - Relationship
const int kAddNotRelatedUserToGroup = 2500;
const int kCreateExistingRelationship = 2501;

// User - Friend Request
const int kRequesterNotFriendRequestRecipient = 2600;
const int kCreateExistingFriendRequest = 2601;
const int kFriendRequestSenderHasBeenBlocked = 2602;

// Group

// Group - Info
const int kUpdateInfoOfNonExistingGroup = 3000;
const int kNotOwnerToUpdateGroupInfo = 3001;
const int kNotOwnerOrManagerToUpdateGroupInfo = 3002;
const int kNotMemberToUpdateGroupInfo = 3003;

// Group - Type
const int kNoPermissionToCreateGroupWithGroupType = 3100;
const int kCreateGroupWithNonExistingGroupType = 3101;

// Group - Ownership
const int kNotActiveUserToCreateGroup = 3200;
const int kNotOwnerToTransferGroup = 3201;
const int kNotOwnerToDeleteGroup = 3202;
const int kSuccessorNotGroupMember = 3203;
const int kOwnerQuitsWithoutSpecifyingSuccessor = 3204;
const int kMaxOwnedGroupsReached = 3205;
const int kTransferNonExistingGroup = 3206;

// Group - Question
const int kNotOwnerOrManagerToCreateGroupQuestion = 3300;
const int kNotOwnerOrManagerToDeleteGroupQuestion = 3301;
const int kNotOwnerOrManagerToUpdateGroupQuestion = 3302;
const int kNotOwnerOrManagerToAccessGroupQuestionAnswer = 3303;
const int kCreateGroupQuestionForInactiveGroup = 3304;
const int kCreateGroupQuestionForGroupUsingJoinRequest = 3305;
const int kCreateGroupQuestionForGroupUsingInvitation = 3306;
const int kCreateGroupQuestionForGroupUsingMembershipRequest = 3307;
const int kGroupQuestionAnswererHasBeenBlocked = 3308;
const int kMemberCannotAnswerGroupQuestion = 3309;
const int kAnswerInactiveQuestion = 3310;
const int kAnswerQuestionOfInactiveGroup = 3311;

// Group - Member
const int kAddUserToGroupRequiringInvitation = 3400;
const int kAddUserToInactiveGroup = 3401;
const int kAddUserWithRoleHigherThanRequester = 3402;
const int kAddBlockedUserToGroup = 3403;
const int kAddBlockedUserToInactiveGroup = 3404;
const int kNotOwnerOrManagerToRemoveGroupMember = 3405;
const int kNotOwnerToRemoveGroupOwnerOrManager = 3406;
const int kNotOwnerToUpdateGroupMemberInfo = 3407;
const int kNotOwnerOrManagerToUpdateGroupMemberInfo = 3408;
const int kNotMemberToQueryMemberInfo = 3409;

// Group - Blocklist
const int kNotOwnerOrManagerToAddBlockedUser = 3500;
const int kNotOwnerOrManagerToRemoveBlockedUser = 3501;

// Group - Join Request
const int kGroupJoinRequestSenderHasBeenBlocked = 3600;
const int kNotJoinRequestSenderToRecallRequest = 3601;
const int kNotOwnerOrManagerToAccessGroupRequest = 3602;
const int kRecallNotPendingGroupJoinRequest = 3603;
const int kSendJoinRequestToInactiveGroup = 3604;
const int kSendJoinRequestToGroupUsingMembershipRequest = 3605;
const int kSendJoinRequestToGroupUsingInvitation = 3606;
const int kSendJoinRequestToGroupUsingQuestion = 3607;
const int kRecallingGroupJoinRequestIsDisabled = 3608;

// Group - Invitation
const int kGroupInviterNotMember = 3700;
const int kGroupInviteeAlreadyGroupMember = 3701;
const int kNotOwnerOrManagerToRecallInvitation = 3702;
const int kNotOwnerOrManagerToAccessInvitation = 3703;
const int kNotOwnerToSendInvitation = 3704;
const int kNotOwnerOrManagerToSendInvitation = 3705;
const int kNotMemberToSendInvitation = 3706;
const int kInviteeHasBeenBlocked = 3707;
const int kRecallingGroupInvitationIsDisabled = 3708;
const int kSendGroupInvitationToGroupNotRequireInvitation = 3709;
const int kRecallNotPendingGroupInvitation = 3710;

// Conversation
const int kUpdatingTypingStatusIsDisabled = 4000;
const int kUpdatingReadDateIsDisabled = 4001;
const int kMovingReadDateForwardIsDisabled = 4002;

// Message

// Message - Send
const int kMessageRecipientNotActive = 5000;
const int kMessageSenderNotInContactsOrBlocked = 5001;
const int kPrivateMessageSenderHasBeenBlocked = 5002;
const int kGroupMessageSenderHasBeenBlocked = 5003;
const int kSendMessageToInactiveGroup = 5004;
const int kSendMessageToMutedGroup = 5005;
const int kSendingMessagesToOneselfIsDisabled = 5006;
const int kMutedMemberSendMessage = 5007;
const int kGuestsHaveBeenMuted = 5008;
const int kMessageIsIllegal = 5009;

// Message - Update
const int kUpdatingMessageBySenderIsDisabled = 5100;
const int kNotSenderToUpdateMessage = 5101;
const int kNotMessageRecipientToUpdateMessageReadDate = 5102;

// Message - Recall
const int kRecallNonExistingMessage = 5200;
const int kRecallingMessageIsDisabled = 5201;
const int kMessageRecallTimeout = 5202;

// Message - Query
const int kNotMemberToQueryGroupMessages = 5300;

// Storage
const int kStorageNotImplemented = 6000;
const int kNotFriendToUploadMessageAttachmentInPrivateConversation = 6100;
const int kNotGroupMemberToUploadMessageAttachmentInGroupConversation = 6101;
const int kNotUploaderToShareMessageAttachment = 6102;
const int kNotUploaderOrGroupManagerToUnshareMessageAttachmentInGroupConversation = 6103;
const int kNotUploaderToUnshareMessageAttachmentInPrivateConversation = 6104;
const int kNotUploaderOrGroupManagerToDeleteMessageAttachmentInGroupConversation = 6105;
const int kNotUploaderToDeleteMessageAttachmentInPrivateConversation = 6106;
const int kNotUploaderOrSharedWithUserToDownloadMessageAttachment = 6107;
const int kNotFriendToQueryMessageAttachmentInfoInPrivateConversation = 6130;
const int kNotGroupMemberToQueryMessageAttachmentInfoInGroupConversation = 6131;

auto isSuccessCode(int businessCode) -> bool;

auto isErrorCode(int businessCode) -> bool;

}  // namespace ResponseStatusCode

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_RESPONSE_STATUS_CODE_H