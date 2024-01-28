class ResponseStatusCode {
  ResponseStatusCode._();

  // **********************************************************
  // * Defined on the client side
  // **********************************************************

  // **********************************************************
  // * For application error
  // **********************************************************

  // Client - Common
  static const connectTimeout = 1;
  static const dataNotFound = 10;
  static const httpError = 90;
  static const httpNotSuccessfulResponse = 91;

  // Client - Request
  static const invalidRequest = 100;
  static const clientRequestsTooFrequent = 101;
  static const requestTimeout = 102;
  static const illegalArgument = 103;

  // Server - Notification
  static const invalidNotification = 200;
  static const invalidResponse = 201;

  // **********************************************************
  // * For business error
  // **********************************************************

  // User
  static const clientSessionAlreadyEstablished = 300;
  static const clientSessionHasBeenClosed = 301;

  // Group

  // Conversation

  // Message

  // Storage

  // **********************************************************
  // * Defined on the server side
  // **********************************************************

  // Successful responses
  static const ok = 1000;
  static const noContent = 1001;
  static const alreadyUpToDate = 1002;

  // **********************************************************
  // * For application error
  // **********************************************************

  // Client
  static const invalidRequestFromServer = 1100;
  static const clientRequestsTooFrequentFromServer = 1101;
  static const illegalArgumentFromServer = 1102;
  static const recordContainsDuplicateKey = 1103;
  static const requestedRecordsTooMany = 1104;
  static const sendRequestFromNonexistentSession = 1105;
  static const unauthorizedRequest = 1106;

  // Server
  static const serverInternalError = 1200;
  static const serverUnavailable = 1201;

  // **********************************************************
  // * For business error
  // **********************************************************

  // User

  // User - Login
  static const unsupportedClientVersion = 2000;

  static const loginTimeout = 2010;
  static const loginAuthenticationFailed = 2011;
  static const loggingInUserNotActive = 2012;
  static const loginFromForbiddenDeviceType = 2013;

  // User - Session
  static const sessionSimultaneousConflictsDecline = 2100;
  static const sessionSimultaneousConflictsNotify = 2101;
  static const sessionSimultaneousConflictsOffline = 2102;
  static const createExistingSession = 2103;
  static const updateHeartbeatOfNonexistentSession = 2104;

  // User - Location
  static const userLocationRelatedFeaturesAreDisabled = 2200;
  static const queryingNearestUsersBySessionIdIsDisabled = 2201;

  // User - Info
  static const updateInfoOfNonexistentUser = 2300;
  static const notFriendToQueryUserProfile = 2301;
  static const blockedUserToQueryUserProfile = 2302;

  // User - Permission
  static const queryPermissionOfNonexistentUser = 2400;

  // User - Relationship
  static const addNonRelatedUserToGroup = 2500;
  static const createExistingRelationship = 2501;
  static const cannotBlockOneself = 2502;

  // User - Friend Request
  static const createExistingFriendRequest = 2600;
  static const blockedUserToSendFriendRequest = 2601;
  static const recallNonPendingFriendRequest = 2602;
  static const recallingFriendRequestIsDisabled = 2603;
  static const notSenderToRecallFriendRequest = 2604;
  static const updateNonPendingFriendRequest = 2605;
  static const notRecipientToUpdateFriendRequest = 2606;

  // Group

  // Group - Info
  static const updateInfoOfNonexistentGroup = 3000;
  static const notGroupOwnerToUpdateGroupInfo = 3001;
  static const notGroupOwnerOrManagerToUpdateGroupInfo = 3002;
  static const notGroupMemberToUpdateGroupInfo = 3003;
  static const notGroupOwnerToUpdateGroupType = 3004;
  static const updatingGroupTypeIsDisabled = 3005;

  // Group - Type
  static const noPermissionToCreateGroupWithGroupType = 3100;
  static const createGroupWithNonexistentGroupType = 3101;

  // Group - Ownership
  static const notActiveUserToCreateGroup = 3200;
  static const notGroupOwnerToTransferGroup = 3201;
  static const notGroupOwnerToDeleteGroup = 3202;
  static const groupSuccessorNotGroupMember = 3203;
  static const groupOwnerQuitWithoutSpecifyingSuccessor = 3204;
  static const maxOwnedGroupsReached = 3205;
  static const transferNonexistentGroup = 3206;

  // Group - Question
  static const notGroupOwnerOrManagerToCreateGroupQuestion = 3300;
  static const notGroupOwnerOrManagerToDeleteGroupQuestion = 3301;
  static const notGroupOwnerOrManagerToUpdateGroupQuestion = 3302;
  static const notGroupOwnerOrManagerToQueryGroupQuestionAnswer = 3303;
  static const createGroupQuestionForInactiveGroup = 3304;
  static const createGroupQuestionForGroupUsingJoinRequest = 3305;
  static const createGroupQuestionForGroupUsingInvitation = 3306;
  static const createGroupQuestionForGroupUsingMembershipRequest = 3307;
  static const groupQuestionAnswererHasBeenBlocked = 3308;
  static const groupMemberAnswerGroupQuestion = 3309;
  static const answerInactiveGroupQuestion = 3310;
  static const answerGroupQuestionOfInactiveGroup = 3311;

  // Group - Member
  static const addUserToGroupRequiringUsersApproval = 3400;
  static const addUserToInactiveGroup = 3401;
  static const notGroupOwnerToAddGroupManager = 3402;
  static const addUserToGroupWithSizeLimitReached = 3403;
  static const addBlockedUserToGroup = 3404;
  static const notGroupOwnerToAddGroupMember = 3405;
  static const notGroupOwnerOrManagerToAddGroupMember = 3406;
  static const notGroupMemberToAddGroupMember = 3407;
  static const notGroupOwnerOrManagerToRemoveGroupMember = 3408;
  static const notGroupOwnerToRemoveGroupOwnerOrManager = 3409;
  static const notGroupOwnerToUpdateGroupMemberRole = 3410;
  static const updateGroupMemberRoleOfNonexistentGroup = 3411;
  static const notGroupOwnerToUpdateGroupMemberInfo = 3412;
  static const notGroupOwnerOrManagerToUpdateGroupMemberInfo = 3413;
  static const notGroupMemberToUpdateGroupMemberInfo = 3414;
  static const updateGroupMemberInfoOfNonexistentGroup = 3415;
  static const updateInfoOfNonexistentGroupMember = 3416;
  static const notGroupOwnerOrManagerToMuteGroupMember = 3417;
  static const muteGroupMemberWithRoleEqualToOrHigherThanRequester = 3418;
  static const muteGroupMemberOfNonexistentGroup = 3419;
  static const muteNonexistentGroupMember = 3420;
  static const notGroupMemberToQueryGroupMemberInfo = 3421;
  static const userJoinGroupWithoutAcceptingGroupInvitation = 3422;
  static const userJoinGroupWithoutAnsweringGroupQuestion = 3423;
  static const userJoinGroupWithoutSendingGroupJoinRequest = 3424;

  // Group - Blocklist
  static const notGroupOwnerOrManagerToAddBlockedUser = 3500;
  static const notGroupOwnerOrManagerToRemoveBlockedUser = 3501;

  // Group - Join Request
  static const blockedUserSendGroupJoinRequest = 3600;
  static const groupMemberSendGroupJoinRequest = 3601;
  static const notSenderToRecallGroupJoinRequest = 3602;
  static const notGroupOwnerOrManagerToQueryGroupJoinRequest = 3603;
  static const recallNonPendingGroupJoinRequest = 3604;
  static const sendGroupJoinRequestToInactiveGroup = 3605;
  static const sendGroupJoinRequestToGroupUsingMembershipRequest = 3606;
  static const sendGroupJoinRequestToGroupUsingInvitation = 3607;
  static const sendGroupJoinRequestToGroupUsingQuestion = 3608;
  static const recallingGroupJoinRequestIsDisabled = 3609;
  static const updateNonPendingGroupJoinRequest = 3610;
  static const notGroupOwnerOrManagerToUpdateGroupJoinRequest = 3611;

  // Group - Invitation
  static const sendGroupInvitationToGroupMember = 3700;
  static const sendGroupInvitationToBlockedUser = 3701;
  static const sendGroupInvitationToGroupNotRequiringUsersApproval = 3702;
  static const notGroupOwnerToSendGroupInvitation = 3703;
  static const notGroupOwnerOrManagerToSendGroupInvitation = 3704;
  static const notGroupMemberToSendGroupInvitation = 3705;
  static const recallingGroupInvitationIsDisabled = 3706;
  static const notGroupOwnerOrManagerToRecallGroupInvitation = 3707;
  static const notGroupOwnerOrManagerOrSenderToRecallGroupInvitation = 3708;
  static const recallNonPendingGroupInvitation = 3709;
  static const updateNonPendingGroupInvitation = 3710;
  static const notInviteeToUpdateGroupInvitation = 3711;
  static const notGroupOwnerOrManagerToQueryGroupInvitation = 3712;

  // Conversation

  // Conversation - Read Date
  static const updatingReadDateIsDisabled = 4000;
  static const updatingReadDateIsDisabledByGroup = 4001;
  static const updatingReadDateOfNonexistentGroupConversation = 4002;
  static const notGroupMemberToUpdateReadDateOfGroupConversation = 4003;
  static const movingReadDateForwardIsDisabled = 4004;

  // Conversation - Typing Status
  static const updatingTypingStatusIsDisabled = 4100;
  static const notGroupMemberToSendTypingStatus = 4101;
  static const notFriendToSendTypingStatus = 4102;

  // Message

  // Message - Send
  static const messageRecipientNotActive = 5000;
  static const notFriendToSendPrivateMessage = 5001;
  static const blockedUserSendPrivateMessage = 5002;
  static const blockedUserSendGroupMessage = 5003;
  static const sendMessageToInactiveGroup = 5004;
  static const sendMessageToMutedGroup = 5005;
  static const sendMessageToNonexistentGroup = 5006;
  static const sendingMessagesToOneselfIsDisabled = 5007;
  static const mutedGroupMemberSendMessage = 5008;
  static const notSpeakableGroupGuestToSendMessage = 5009;
  static const messageIsIllegal = 5010;
  static const notMessageRecipientOrSenderToForwardMessage = 5011;

  // Message - Update
  static const updatingMessageBySenderIsDisabled = 5100;
  static const notSenderToUpdateMessage = 5101;
  static const updateMessageOfNonexistentGroup = 5102;
  static const updatingGroupMessageBySenderIsDisabled = 5103;

  // Message - Recall
  static const recallNonexistentMessage = 5200;
  static const recallingMessageIsDisabled = 5201;
  static const notSenderToRecallMessage = 5202;
  static const recallMessageOfNonexistentGroup = 5203;
  static const messageRecallTimeout = 5204;

  // Message - Query
  static const notGroupMemberToQueryGroupMessages = 5300;

  // Storage
  static const storageNotImplemented = 6000;

  // Storage - Message attachment
  static const notFriendToUploadMessageAttachmentInPrivateConversation = 6100;
  static const notGroupMemberToUploadMessageAttachmentInGroupConversation =
      6101;
  static const notUploaderToShareMessageAttachment = 6102;
  static const notUploaderOrGroupManagerToUnshareMessageAttachmentInGroupConversation =
      6103;
  static const notUploaderToUnshareMessageAttachmentInPrivateConversation =
      6104;
  static const notUploaderOrGroupManagerToDeleteMessageAttachmentInGroupConversation =
      6105;
  static const notUploaderToDeleteMessageAttachmentInPrivateConversation = 6106;
  static const notUploaderOrSharedWithUserToDownloadMessageAttachment = 6107;

  // Storage - Message attachment info
  static const notFriendToQueryMessageAttachmentInfoInPrivateConversation =
      6130;
  static const notGroupMemberToQueryMessageAttachmentInfoInGroupConversation =
      6131;

  static bool isSuccessCode(int code) => 1000 <= code && code < 1100;

  static bool isErrorCode(int code) => !isSuccessCode(code);
}
