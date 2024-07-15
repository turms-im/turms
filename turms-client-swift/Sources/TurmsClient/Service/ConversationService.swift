import Foundation
import PromiseKit

public class ConversationService {
    private weak var turmsClient: TurmsClient!

    init(_ turmsClient: TurmsClient) {
        self.turmsClient = turmsClient
    }

    /// Find private conversations between the logged-in user and another user.
    ///
    /// Common Scenarios:
    /// * If you want to find all private conversations between
    ///   the current logged-in user and other users,
    ///   you can call methods like ``UserService/queryRelatedUserIds``,
    ///   ``UserService/queryFriends`` to get all user IDs first,
    ///   and pass these user IDs as `userIds` to get all private conversations.
    /// * The returned ``PrivateConversation`` does NOT contain messages.
    ///   To find messages in conversations, you can use methods like
    ///   ``MessageService/queryMessages`` and ``MessageService/queryMessagesWithTotal``.
    ///
    /// - Parameters:
    ///   - userIds: The target user IDs.
    ///     If empty, an empty list of conversations is returned.
    ///
    /// - Returns: A list of private conversations.
    /// Note that the list only contains conversations in which the logged-in user is a participant.
    /// If the logged-in user is not a participant of a specified conversation,
    /// these conversations will be filtered out on the server, and no error will be thrown.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryPrivateConversations(_ userIds: [Int64]) -> Promise<Response<[PrivateConversation]>> {
        if userIds.isEmpty {
            return Promise.value(Response<[PrivateConversation]>.emptyArray())
        }
        return turmsClient.driver
            .send {
                $0.queryConversationsRequest = .with {
                    $0.userIds = userIds
                }
            }
            .map {
                try $0.toResponse {
                    $0.conversations.privateConversations
                }
            }
    }

    /// Find group conversations in which the logged-in user is a member.
    ///
    /// Common Scenarios:
    /// * If you want to find all group conversations between
    ///   the current logged-in user and groups in which the logged-in user is a member,
    ///   you can call methods like ``GroupService/queryJoinedGroupIds``,
    ///   ``GroupService/queryJoinedGroupInfos`` to get all group IDs first,
    ///   and pass these group IDs as `groupIds` to get all group conversations.
    /// * ``GroupConversation`` does NOT contain messages.
    ///   To find messages in conversations, you can use methods like
    ///   ``MessageService/queryMessages`` and ``MessageService/queryMessagesWithTotal``.
    ///
    /// - Parameters:
    ///   - groupIds: The target group IDs.
    ///     If empty, an empty list of conversations is returned.
    ///
    /// - Returns: A list of group conversations.
    /// Note that the list only contains conversations in which the logged-in user is a participant.
    /// If the logged-in user is not a participant of a specified conversation,
    /// these conversations will be filtered out on the server, and no error will be thrown.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Update the read date of the target private conversation.
    ///
    /// Common Scenarios:
    /// * To find the read date of a conversation actively (if no notification is received from the server),
    ///   you can call ``queryPrivateConversations``.
    ///
    /// Authorization:
    /// * If the server property `turms.service.conversation.read-receipt.enabled`
    ///   is false (true by default), throws ``ResponseError`` with the code ``ResponseStatusCode/updatingReadDateIsDisabled``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-contact`
    ///   is true (false by default),
    ///   the server will send a read date update notification to the participant actively.
    /// * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-requester-other-online-sessions`
    ///   is true (true by default),
    ///   the server will send a read date update notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///   - readDate: The read date.
    ///     If null, the current time is used.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updatePrivateConversationReadDate(_ userId: Int64, readDate: Date = Date()) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateConversationRequest = .with {
                    $0.userID = userId
                    $0.readDate = readDate.toMillis()
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Update the read date of the target group conversation.
    ///
    /// Common Scenarios:
    /// * To find the read date of a conversation actively (if no notification is received from the server),
    ///   you can call ``queryGroupConversations``.
    ///
    /// Authorization:
    /// * If the server property `turms.service.conversation.read-receipt.enabled`
    ///   is false (true by default), throws ``ResponseError`` with the code ``ResponseStatusCode/updatingReadDateIsDisabled``.
    /// * If the target group doesn't exist, throws ``ResponseError`` with the code ``ResponseStatusCode/updatingReadDateOfNonexistentGroupConversation``.
    /// * If the target group has disabled read receipts, throws ``ResponseError`` with the code ``ResponseStatusCode/updatingReadDateIsDisabledByGroup``.
    /// * If the logged-in user is not a member of the target group, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToUpdateReadDateOfGroupConversation``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-other-group-members`
    ///   is true (false by default),
    ///   the server will send a read date update notification to all participants actively.
    /// * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-requester-other-online-sessions`
    ///   is true (true by default),
    ///   the server will send a read date update notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - readDate: The read date.
    ///     If null, the current time is used.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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

    /// Upsert private conversation settings, such as "sticky", "new message alert", etc.
    /// Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.private-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
    ///   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///   - settings: The private conversation settings to upsert.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If trying to update any existing immutable setting, throws ``ResponseError`` with the code ``ResponseStatusCode/illegalArgument``
    /// * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
    ///   false (false by default), throws ``ResponseError`` with the code ``ResponseStatusCode/illegalArgument``.
    public func upsertPrivateConversationSettings(_ userId: Int64, _ settings: [String: Value]) -> Promise<Response<Void>> {
        if settings.isEmpty {
            return Promise.value(Response<Void>.empty())
        }
        return turmsClient.driver
            .send {
                $0.updateConversationSettingsRequest = .with {
                    $0.userID = userId
                    $0.settings = settings
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Upsert group conversation settings, such as "sticky", "new message alert", etc.
    /// Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.group-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
    ///   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///   - settings: The group conversation settings to upsert.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If trying to update any existing immutable setting, throws ``ResponseError`` with the code ``ResponseStatusCode/illegalArgument``
    /// * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
    ///   false (false by default), throws ``ResponseError`` with the code ``ResponseStatusCode/illegalArgument``.
    public func upsertGroupConversationSettings(_ groupId: Int64, _ settings: [String: Value]) -> Promise<Response<Void>> {
        if settings.isEmpty {
            return Promise.value(Response<Void>.empty())
        }
        return turmsClient.driver
            .send {
                $0.updateConversationSettingsRequest = .with {
                    $0.groupID = groupId
                    $0.settings = settings
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Delete conversation settings.
    ///
    /// Notifications:
    /// * If the server property `turms.service.notification.private-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
    ///   and a private conversation setting is deleted, the server will send a user settings deleted notification to all other online sessions of the logged-in user actively.
    /// * If the server property `turms.service.notification.group-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
    ///   and a group conversation setting is deleted, the server will send a group settings deleted notification to all other online sessions of the logged-in user actively.
    ///
    /// - Parameters:
    ///   - userIds: The target user IDs. If both `userIds` and `groupIds` are null, all deletable conversation settings will be deleted.
    ///   - groupIds: The target group IDs. If both `userIds` and `groupIds` are null, all deletable conversation settings will be deleted.
    ///   - names: The names of the conversation settings to delete. If null, all deletable conversation settings will be deleted.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    /// * If trying to delete any non-deletable setting, throws ``ResponseError`` with the code ``ResponseStatusCode/illegalArgument``.
    public func deleteConversationSettings(userIds: [Int64]? = nil, groupIds: [Int64]? = nil, names: [String]? = nil) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.deleteConversationSettingsRequest = .with {
                    if let v = userIds {
                        $0.userIds = v
                    }
                    if let v = groupIds {
                        $0.groupIds = v
                    }
                    if let v = names {
                        $0.names = v
                    }
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Find conversation settings.
    ///
    /// - Parameters:
    ///   - userIds: The target user IDs. If both `userIds` and `groupIds` are null, the settings of all private and group conversations will be returned.
    ///   - groupIds: The target group IDs. If both `userIds` and `groupIds` are null, the settings of all private and group conversations will be returned.
    ///   - names: The target setting names.
    ///   - lastUpdatedDate: The last updated date of conversation settings stored locally.
    ///     The server will only return conversation settings if a setting has been updated after `lastUpdatedDate`.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func queryConversationSettings(userIds: [Int64]? = nil, groupIds: [Int64]? = nil, names: [String]? = nil, lastUpdatedDate: Date? = nil) -> Promise<Response<[ConversationSettings]>> {
        return turmsClient.driver
            .send {
                $0.queryConversationSettingsRequest = .with {
                    if let v = userIds {
                        $0.userIds = v
                    }
                    if let v = groupIds {
                        $0.groupIds = v
                    }
                    if let v = names {
                        $0.names = v
                    }
                    if let v = lastUpdatedDate {
                        $0.lastUpdatedDateStart = v.toMillis()
                    }
                }
            }
            .map {
                try $0.toResponse {
                    $0.conversationSettingsList.conversationSettingsList
                }
            }
    }

    /// Update the typing status of the target private conversation.
    ///
    /// Authorization:
    /// * If the server property `turms.service.conversation.typing-status.enabled`
    ///   is true (true by default), throws ``ResponseError`` with the code ``ResponseStatusCode/updatingTypingStatusIsDisabled``.
    /// * If the logged-in user is not a friend of `userId`, throws ``ResponseError`` with the code ``ResponseStatusCode/notFriendToSendTypingStatus``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.conversation.typing-status.enabled`
    ///   is true (true by default),
    ///   the server will send a typing status update notification to the participant actively.
    ///
    /// - Parameters:
    ///   - userId: The target user ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
    public func updatePrivateConversationTypingStatus(_ userId: Int64) -> Promise<Response<Void>> {
        return turmsClient.driver
            .send {
                $0.updateTypingStatusRequest = .with {
                    $0.toID = userId
                    $0.isGroupMessage = false
                }
            }
            .map {
                try $0.toResponse()
            }
    }

    /// Update the typing status of the target group conversation.
    ///
    /// Authorization:
    /// * If the server property `turms.service.conversation.typing-status.enabled`
    ///   is true (true by default), throws ``ResponseError`` with the code ``ResponseStatusCode/updatingTypingStatusIsDisabled``.
    /// * If the logged-in user is not a member of the target group, throws ``ResponseError`` with the code ``ResponseStatusCode/notGroupMemberToSendTypingStatus``.
    ///
    /// Notifications:
    /// * If the server property `turms.service.conversation.typing-status.enabled`
    ///   is true (true by default),
    ///   the server will send a typing status update notification to all participants actively.
    ///
    /// - Parameters:
    ///   - groupId: The target group ID.
    ///
    /// - Throws: ``ResponseError`` if an error occurs.
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
