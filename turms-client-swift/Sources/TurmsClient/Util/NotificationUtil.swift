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