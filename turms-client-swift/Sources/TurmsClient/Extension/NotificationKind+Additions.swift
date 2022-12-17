import SwiftProtobuf

public extension TurmsNotification.DataMessage.OneOf_Kind {
    func getKindData<T>(_: T.Type) throws -> T? {
        var message: Any
        switch self {
        case let .long(v):
            message = v
        case let .longsWithVersion(v):
            message = v
        case let .string(v):
            message = v
        case let .stringsWithVersion(v):
            message = v

        case let .conversations(v):
            message = v

        case let .messages(v):
            message = v
        case let .messagesWithTotalList(v):
            message = v

        case let .userSession(v):
            message = v
        case let .userInfosWithVersion(v):
            message = v
        case let .userOnlineStatuses(v):
            message = v
        case let .userFriendRequestsWithVersion(v):
            message = v
        case let .userRelationshipGroupsWithVersion(v):
            message = v
        case let .userRelationshipsWithVersion(v):
            message = v
        case let .nearbyUsers(v):
            message = v

        case let .groupInvitationsWithVersion(v):
            message = v
        case let .groupJoinQuestionAnswerResult(v):
            message = v
        case let .groupJoinRequestsWithVersion(v):
            message = v
        case let .groupJoinQuestionsWithVersion(v):
            message = v
        case let .groupMembersWithVersion(v):
            message = v
        case let .groupsWithVersion(v):
            message = v
        case let .storageResourceInfos(v):
            message = v
        }
        return message as? T
    }
}
