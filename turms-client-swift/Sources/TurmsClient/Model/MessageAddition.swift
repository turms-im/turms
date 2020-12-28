public class MessageAddition {
    public let isMentioned: Bool
    public let mentionedUserIds: [Int64]
    public let recalledMessageIds: [Int64]

    init(isMentioned: Bool, mentionedUserIds: [Int64], recalledMessageIds: [Int64]) {
        self.isMentioned = isMentioned
        self.mentionedUserIds = mentionedUserIds
        self.recalledMessageIds = recalledMessageIds
    }
}
