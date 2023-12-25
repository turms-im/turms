public enum ResponseStatusCode: Int {
    // **********************************************************
    // * Defined on the client side
    // **********************************************************

    // **********************************************************
    // * For application error
    // **********************************************************

    // Client - Common
    case connectTimeout = 1
    case dataNotFound = 10
    case httpError = 90
    case httpNotSuccessfulResponse

    // Client - Request
    case invalidRequest = 100
    case clientRequestsTooFrequent
    case requestTimeout
    case illegalArgument

    // Server - Notification
    case invalidNotification = 200
    case invalidResponse

    // **********************************************************
    // * For business error
    // **********************************************************

    // User
    case clientSessionAlreadyEstablished = 300
    case clientSessionHasBeenClosed

    // Group

    // Conversation

    // Message

    // Storage

    // **********************************************************
    // * Defined on the server side
    // **********************************************************

    // Successful responses
    case ok = 1000
    case noContent
    case alreadyUpToDate

    // **********************************************************
    // * For application error
    // **********************************************************

    // Client
    case invalidRequestFromServer = 1100
    case clientRequestsTooFrequentFromServer
    case illegalArgumentFromServer
    case recordContainsDuplicateKey
    case requestedRecordsTooMany
    case sendRequestFromNonexistentSession
    case unauthorizedRequest

    // Server
    case serverInternalError = 1200
    case serverUnavailable

    // **********************************************************
    // * For business error
    // **********************************************************

    // User

    // User - Login
    case unsupportedClientVersion = 2000

    case loginTimeout = 2010
    case loginAuthenticationFailed
    case loggingInUserNotActive
    case loginFromForbiddenDeviceType

    // User - Session
    case sessionSimultaneousConflictsDecline = 2100
    case sessionSimultaneousConflictsNotify
    case sessionSimultaneousConflictsOffline
    case createExistingSession
    case updateHeartbeatOfNonexistentSession

    // User - Location
    case userLocationRelatedFeaturesAreDisabled = 2200
    case queryingNearestUsersBySessionIdIsDisabled

    // User - Info
    case updateInfoOfNonexistentUser = 2300
    case userProfileNotFound
    case profileRequesterNotInContactsOrBlocked
    case profileRequesterHasBeenBlocked

    // User - Permission
    case queryPermissionOfNonexistentUser = 2400

    // User - Relationship
    case addNonRelatedUserToGroup = 2500
    case createExistingRelationship

    // User - Friend Request
    case requesterNotFriendRequestRecipient = 2600
    case createExistingFriendRequest
    case friendRequestSenderHasBeenBlocked

    // Group

    // Group - Info
    case updateInfoOfNonexistentGroup = 3000
    case notGroupOwnerToUpdateGroupInfo
    case notGroupOwnerOrManagerToUpdateGroupInfo
    case notGroupMemberToUpdateGroupInfo

    // Group - Type
    case noPermissionToCreateGroupWithGroupType = 3100
    case createGroupWithNonexistentGroupType

    // Group - Ownership
    case notActiveUserToCreateGroup = 3200
    case notGroupOwnerToTransferGroup
    case notGroupOwnerToDeleteGroup
    case successorNotGroupMember
    case ownerQuitsWithoutSpecifyingSuccessor
    case maxOwnedGroupsReached
    case transferNonexistentGroup

    // Group - Question
    case notGroupOwnerOrManagerToCreateGroupQuestion = 3300
    case notGroupOwnerOrManagerToDeleteGroupQuestion
    case notGroupOwnerOrManagerToUpdateGroupQuestion
    case notGroupOwnerOrManagerToAccessGroupQuestionAnswer
    case createGroupQuestionForInactiveGroup
    case createGroupQuestionForGroupUsingJoinRequest
    case createGroupQuestionForGroupUsingInvitation
    case createGroupQuestionForGroupUsingMembershipRequest
    case groupQuestionAnswererHasBeenBlocked
    case groupMemberAnswerGroupQuestion
    case answerInactiveGroupQuestion
    case answerGroupQuestionOfInactiveGroup

    // Group - Member
    case addUserToGroupRequiringInvitation = 3400
    case addUserToInactiveGroup
    case addUserToGroupWithRoleHigherThanRequester
    case addUserToGroupWithSizeLimitReached
    case addBlockedUserToGroup
    case addBlockedUserToInactiveGroup
    case notGroupOwnerOrManagerToRemoveGroupMember
    case notGroupOwnerToRemoveGroupOwnerOrManager
    case notGroupOwnerToUpdateGroupMemberRole
    case updateGroupMemberRoleOfNonexistentGroup
    case notGroupOwnerToUpdateGroupMemberInfo
    case notGroupOwnerOrManagerToUpdateGroupMemberInfo
    case notGroupMemberToUpdateGroupMemberInfo
    case updateGroupMemberInfoOfNonexistentGroup
    case updateInfoOfNonexistentGroupMember
    case notGroupOwnerOrManagerToMuteGroupMember
    case muteGroupMemberWithRoleEqualToOrHigherThanRequester
    case muteGroupMemberOfNonexistentGroup
    case muteNonexistentGroupMember
    case notGroupMemberToQueryGroupMemberInfo

    // Group - Blocklist
    case notGroupOwnerOrManagerToAddBlockedUser = 3500
    case notGroupOwnerOrManagerToRemoveBlockedUser

    // Group - Join Request
    case groupJoinRequestSenderHasBeenBlocked = 3600
    case notGroupJoinRequestSenderToRecallRequest
    case notGroupOwnerOrManagerToAccessGroupJoinRequest
    case recallNonPendingGroupJoinRequest
    case sendGroupJoinRequestToInactiveGroup
    case sendGroupJoinRequestToGroupUsingMembershipRequest
    case sendGroupJoinRequestToGroupUsingInvitation
    case sendGroupJoinRequestToGroupUsingQuestion
    case recallingGroupJoinRequestIsDisabled

    // Group - Invitation
    case groupInviterNotGroupMember = 3700
    case groupInviteeAlreadyGroupMember
    case groupInviteeHasBeenBlockedByGroup
    case notGroupOwnerOrManagerToRecallGroupInvitation
    case notGroupOwnerOrManagerToAccessGroupInvitation
    case notGroupOwnerToSendGroupInvitation
    case notGroupOwnerOrManagerToSendGroupInvitation
    case notGroupMemberToSendGroupInvitation
    case recallingGroupInvitationIsDisabled
    case sendGroupInvitationToGroupNotRequireInvitation
    case recallNonPendingGroupInvitation

    // Conversation

    // Conversation - Read Date
    case updatingReadDateIsDisabled = 4000
    case updatingReadDateIsDisabledByGroup
    case updatingReadDateOfNonexistentGroupConversation
    case notGroupMemberToUpdateReadDateOfGroupConversation
    case movingReadDateForwardIsDisabled

    // Conversation - Typing Status
    case updatingTypingStatusIsDisabled = 4100
    case notGroupMemberToSendTypingStatus
    case notFriendToSendTypingStatus

    // Message

    // Message - Send
    case messageRecipientNotActive = 5000
    case messageSenderNotInContactsOrBlocked
    case privateMessageSenderHasBeenBlocked
    case groupMessageSenderHasBeenBlocked
    case sendMessageToInactiveGroup
    case sendMessageToMutedGroup
    case sendMessageToNonexistentGroup
    case sendingMessagesToOneselfIsDisabled
    case mutedGroupMemberSendMessage
    case guestsHaveBeenMuted
    case messageIsIllegal

    // Message - Update
    case updatingMessageBySenderIsDisabled = 5100
    case notSenderToUpdateMessage
    case updateMessageOfNonexistentGroup
    case updatingGroupMessageBySenderIsDisabled

    // Message - Recall
    case recallNonexistentMessage = 5200
    case recallingMessageIsDisabled
    case notSenderToRecallMessage
    case recallMessageOfNonexistentGroup
    case messageRecallTimeout

    // Message - Query
    case notGroupMemberToQueryGroupMessages = 5300

    // Storage
    case storageNotImplemented = 6000

    // Storage - Message attachment
    case notFriendToUploadMessageAttachmentInPrivateConversation = 6100
    case notGroupMemberToUploadMessageAttachmentInGroupConversation
    case notUploaderToShareMessageAttachment
    case notUploaderOrGroupManagerToUnshareMessageAttachmentInGroupConversation
    case notUploaderToUnshareMessageAttachmentInPrivateConversation
    case notUploaderOrGroupManagerToDeleteMessageAttachmentInGroupConversation
    case notUploaderToDeleteMessageAttachmentInPrivateConversation
    case notUploaderOrSharedWithUserToDownloadMessageAttachment

    // Storage - Message attachment info
    case notFriendToQueryMessageAttachmentInfoInPrivateConversation = 6130
    case notGroupMemberToQueryMessageAttachmentInfoInGroupConversation
}

public extension ResponseStatusCode {
    static func isSuccessCode(_ code: Int) -> Bool {
        return code >= 1000 && code < 1100
    }

    static func isErrorCode(_ code: Int) -> Bool {
        return !isSuccessCode(code)
    }
}