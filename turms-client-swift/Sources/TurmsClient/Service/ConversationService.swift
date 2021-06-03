import Foundation
import PromiseKit

public class ConversationService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func queryPrivateConversations(_ targetIds: [Int64]) -> Promise<[PrivateConversation]> {
        return turmsClient.driver
            .send {
                $0.queryConversationsRequest = .with {
                    $0.targetIds = targetIds
                }
            }
            .map {
                $0.data.conversations.privateConversations
            }
    }

    public func queryGroupConversations(_ groupIds: [Int64]) -> Promise<[GroupConversation]> {
        return turmsClient.driver
            .send {
                $0.queryConversationsRequest = .with {
                    $0.groupIds = groupIds
                }
            }
            .map {
                $0.data.conversations.groupConversations
            }
    }

    public func updatePrivateConversationReadDate(_ targetId: Int64, readDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateConversationRequest = .with {
                    $0.targetID = targetId
                    $0.readDate = readDate.toMillis()
                }
            }
            .asVoid()
    }

    public func updateGroupConversationReadDate(_ groupId: Int64, readDate: Date = Date()) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateConversationRequest = .with {
                    $0.groupID = groupId
                    $0.readDate = readDate.toMillis()
                }
            }
            .asVoid()
    }

    public func updatePrivateConversationTypingStatus(_ targetId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateTypingStatusRequest = .with {
                    $0.toID = targetId
                    $0.isGroupMessage = false
                }
            }
            .asVoid()
    }

    public func updateGroupConversationTypingStatus(_ groupId: Int64) -> Promise<Void> {
        return turmsClient.driver
            .send {
                $0.updateTypingStatusRequest = .with {
                    $0.toID = groupId
                    $0.isGroupMessage = true
                }
            }
            .asVoid()
    }
}
