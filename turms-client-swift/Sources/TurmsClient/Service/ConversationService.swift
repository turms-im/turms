import Foundation
import PromiseKit

public class ConversationService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    public func queryPrivateConversations(_ targetIds: [Int64]) -> Promise<Response<[PrivateConversation]>> {
        if targetIds.isEmpty {
            return Promise.value(Response<[PrivateConversation]>.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.queryConversationsRequest = .with {
                    $0.targetIds = targetIds
                }
            }
            .map {
                try $0.toResponse {
                    $0.conversations.privateConversations
                }
            }
    }

    public func queryGroupConversations(_ groupIds: [Int64]) -> Promise<Response<[GroupConversation]>> {
        if groupIds.isEmpty {
            return Promise.value(Response<[GroupConversation]>.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.queryConversationsRequest = .with {
                    $0.groupIds = groupIds
                }
            }
            .map {
                try $0.toResponse {
                    $0.conversations.groupConversations
                }
            }
    }

    public func updatePrivateConversationReadDate(_ targetId: Int64, readDate: Date = Date()) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateConversationRequest = .with {
                    $0.targetID = targetId
                    $0.readDate = readDate.toMillis()
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updateGroupConversationReadDate(_ groupId: Int64, readDate: Date = Date()) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateConversationRequest = .with {
                    $0.groupID = groupId
                    $0.readDate = readDate.toMillis()
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updatePrivateConversationTypingStatus(_ targetId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateTypingStatusRequest = .with {
                    $0.toID = targetId
                    $0.isGroupMessage = false
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    public func updateGroupConversationTypingStatus(_ groupId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateTypingStatusRequest = .with {
                    $0.toID = groupId
                    $0.isGroupMessage = true
                }
            }
            .map {
                try $0.toResponse()
            }
    }
}
