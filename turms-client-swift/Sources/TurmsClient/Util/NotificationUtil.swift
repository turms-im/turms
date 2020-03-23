import SwiftProtobuf

public class NotificationUtil {
    private init() {}

    static func getFirstId(_ notification: TurmsNotification) throws -> Int64 {
        let ids = notification.data.ids
        if ids.values.count > 0 {
            return ids.values[0]
        } else {
            throw TurmsBusinessError(TurmsStatusCode.missingData)
        }
    }
}

extension TurmsNotification.DataMessage.OneOf_Kind {
    public func getKindData<T>(_ messageType: T.Type) throws -> T? {
        var message: SwiftProtobuf.Message?
        switch self {
            case .ids(let v):
                message = v
            case .idsWithVersion(let v):
                message = v
            case .url(let v):
                message = v
            case .acknowledge(let v):
                message = v
            case .session(let v):
                message = v
            case .messages(let v):
                message = v
            case .messageStatuses(let v):
                message = v
            case .messagesWithTotalList(let v):
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
            case .userSessionIds(let v):
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
