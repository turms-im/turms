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
    case sendRequestFromNonExistingSession
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
    case updateNonExistingSessionHeartbeat

    // User - Location
    case userLocationRelatedFeaturesAreDisabled = 2200
    case queryingNearestUsersBySessionIdIsDisabled

    // User - Info
    case updateInfoOfNonExistingUser = 2300
    case userProfileNotFound
    case profileRequesterNotInContactsOrBlocked
    case profileRequesterHasBeenBlocked

    // User - Permission
    case queryPermissionOfNonExistingUser = 2400

    // User - Relationship
    case addNotRelatedUserToGroup = 2500
    case createExistingRelationship

    // User - Friend Request
    case requesterNotFriendRequestRecipient = 2600
    case createExistingFriendRequest
    case friendRequestSenderHasBeenBlocked

    // Group

    // Group - Info
    case updateInfoOfNonExistingGroup = 3000
    case notOwnerToUpdateGroupInfo
    case notOwnerOrManagerToUpdateGroupInfo
    case notMemberToUpdateGroupInfo

    // Group - Type
    case noPermissionToCreateGroupWithGroupType = 3100
    case createGroupWithNonExistingGroupType

    // Group - Ownership
    case notActiveUserToCreateGroup = 3200
    case notOwnerToTransferGroup
    case notOwnerToDeleteGroup
    case successorNotGroupMember
    case ownerQuitsWithoutSpecifyingSuccessor
    case maxOwnedGroupsReached
    case transferNonExistingGroup

    // Group - Question
    case notOwnerOrManagerToCreateGroupQuestion = 3300
    case notOwnerOrManagerToDeleteGroupQuestion
    case notOwnerOrManagerToUpdateGroupQuestion
    case notOwnerOrManagerToAccessGroupQuestionAnswer
    case createGroupQuestionForInactiveGroup
    case createGroupQuestionForGroupUsingJoinRequest
    case createGroupQuestionForGroupUsingInvitation
    case createGroupQuestionForGroupUsingMembershipRequest
    case groupQuestionAnswererHasBeenBlocked
    case memberCannotAnswerGroupQuestion
    case answerInactiveQuestion
    case answerQuestionOfInactiveGroup

    // Group - Member
    case addUserToGroupRequiringInvitation = 3400
    case addUserToInactiveGroup
    case addUserWithRoleHigherThanRequester
    case addBlockedUserToGroup
    case addBlockedUserToInactiveGroup
    case notOwnerOrManagerToRemoveGroupMember
    case notOwnerToRemoveGroupOwnerOrManager
    case notOwnerToUpdateGroupMemberInfo
    case notOwnerOrManagerToUpdateGroupMemberInfo
    case notMemberToQueryMemberInfo

    // Group - Blocklist
    case notOwnerOrManagerToAddBlockedUser = 3500
    case notOwnerOrManagerToRemoveBlockedUser

    // Group - Join Request
    case groupJoinRequestSenderHasBeenBlocked = 3600
    case notJoinRequestSenderToRecallRequest
    case notOwnerOrManagerToAccessGroupRequest
    case recallNotPendingGroupJoinRequest
    case sendJoinRequestToInactiveGroup
    case sendJoinRequestToGroupUsingMembershipRequest
    case sendJoinRequestToGroupUsingInvitation
    case sendJoinRequestToGroupUsingQuestion
    case recallingGroupJoinRequestIsDisabled

    // Group - Invitation
    case groupInviterNotMember = 3700
    case groupInviteeAlreadyGroupMember
    case notOwnerOrManagerToRecallInvitation
    case notOwnerOrManagerToAccessInvitation
    case notOwnerToSendInvitation
    case notOwnerOrManagerToSendInvitation
    case notMemberToSendInvitation
    case inviteeHasBeenBlocked
    case recallingGroupInvitationIsDisabled
    case sendGroupInvitationToGroupNotRequireInvitation
    case recallNotPendingGroupInvitation

    // Conversation
    case updatingTypingStatusIsDisabled = 4000
    case updatingReadDateIsDisabled
    case movingReadDateForwardIsDisabled

    // Message

    // Message - Send
    case messageRecipientNotActive = 5000
    case messageSenderNotInContactsOrBlocked
    case privateMessageSenderHasBeenBlocked
    case groupMessageSenderHasBeenBlocked
    case sendMessageToInactiveGroup
    case sendMessageToMutedGroup
    case sendingMessagesToOneselfIsDisabled
    case mutedMemberSendMessage
    case guestsHaveBeenMuted
    case messageIsIllegal

    // Message - Update
    case updatingMessageBySenderIsDisabled = 5100
    case notSenderToUpdateMessage
    case notMessageRecipientToUpdateMessageReadDate

    // Message - Recall
    case recallNonExistingMessage = 5200
    case recallingMessageIsDisabled
    case messageRecallTimeout

    case notMemberToQueryGroupMessages = 5300

    // Storage
    case storageNotImplemented = 6000
    case notFriendToUploadMessageAttachmentInPrivateConversation = 6100
    case notGroupMemberToUploadMessageAttachmentInGroupConversation
    case notUploaderToShareMessageAttachment
    case notUploaderOrGroupManagerToUnshareMessageAttachmentInGroupConversation
    case notUploaderToUnshareMessageAttachmentInPrivateConversation
    case notUploaderOrGroupManagerToDeleteMessageAttachmentInGroupConversation
    case notUploaderToDeleteMessageAttachmentInPrivateConversation
    case notUploaderOrSharedWithUserToDownloadMessageAttachment
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
