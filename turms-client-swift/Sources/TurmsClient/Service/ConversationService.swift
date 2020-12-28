import Foundation
import PromiseKit

public class ConversationService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func queryPrivateConversations(_ targetIds: [Int64]) -> Promise<[PrivateConversation]> {
        return turmsClient.driver
            .send { $0
                .request("queryConversationsRequest")
                .field("targetIds", targetIds)
            }
            .map { $0.data.conversations.privateConversations }
    }

    public func queryGroupConversations(_ groupIds: [Int64]) -> Promise<[GroupConversation]> {
        return turmsClient.driver
            .send { $0
                .request("queryConversationsRequest")
                .field("groupIds", groupIds)
            }
            .map { $0.data.conversations.groupConversations }
    }

    public func updatePrivateConversationReadDate(_ targetId: Int64, readDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateConversationRequest")
                .field("targetId", targetId)
                .field("readDate", readDate)
            }
            .asVoid()
    }

    public func updateGroupConversationReadDate(_ groupId: Int64, readDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateConversationRequest")
                .field("groupId", groupId)
                .field("readDate", readDate)
            }
            .asVoid()
    }

    public func updatePrivateConversationTypingStatus(_ targetId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateTypingStatusRequest")
                .field("toId", targetId)
                .field("isGroupMessage", false)
            }
            .asVoid()
    }

    public func updateGroupConversationTypingStatus(_ groupId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send { $0
                .request("updateTypingStatusRequest")
                .field("toId", groupId)
                .field("isGroupMessage", true)
            }
            .asVoid()
    }
}
