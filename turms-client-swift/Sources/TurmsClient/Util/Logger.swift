import Foundation
import os.log

class Logger {
    private init() {}

    @available(OSX 10.12, iOS 10.0, *)
    private static let log = OSLog(subsystem: "im.turms.turmsclient", category: "turms")

    static func error(_ message: StaticString, _ args: CVarArg...) {
        if #available(OSX 10.12, iOS 10.0, *) {
            os_log(message, log: log, type: .error, args)
        } else {
            NSLog("\(message)", args)
        }
    }
}
