public class MessageAddition {
    public let isMentioned: Bool
    public let mentionedUserIds: [Int64]

    init(isMentioned: Bool, mentionedUserIds: [Int64]) {
        self.isMentioned = isMentioned
        self.mentionedUserIds = mentionedUserIds
    }
}
