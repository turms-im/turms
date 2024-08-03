import Foundation
import os.log

class Logger {
    private init() {}

    private static let log = OSLog(subsystem: "im.turms.turmsclient", category: "turms")

    static func error(_ message: StaticString, _ args: CVarArg...) {
        os_log(message, log: log, type: .error, args)
    }
}
