import SwiftProtobuf

extension TurmsNotification.DataMessage.OneOf_Kind {
    public func getKindData<T>(_ messageType: T.Type) throws -> T? {
        var message: Any
        switch self {
        case .ids(let v):
            message = v
        case .idsWithVersion(let v):
            message = v
        case .url(let v):
            message = v

        case .conversations(let v):
            message = v

        case .messages(let v):
            message = v
        case .messagesWithTotalList(let v):
            message = v

        case .userSession(let v):
            message = v
        case .usersInfosWithVersion(let v):
            message = v
        case .usersOnlineStatuses(let v):
            message = v
        case .userFriendRequestsWithVersion(let v):
            message = v
        case .userRelationshipGroupsWithVersion(let v):
            message = v
        case .userRelationshipsWithVersion(let v):
            message = v
        case .nearbyUsers(let v):
            message = v

        case .groupInvitationsWithVersion(let v):
            message = v
        case .groupJoinQuestionAnswerResult(let v):
            message = v
        case .groupJoinRequestsWithVersion(let v):
            message = v
        case .groupJoinQuestionsWithVersion(let v):
            message = v
        case .groupMembersWithVersion(let v):
            message = v
        case .groupsWithVersion(let v):
            message = v
        }
        return message as? T
    }
}
