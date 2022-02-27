import Foundation
import PromiseKit
import TurmsClient
import XCTest

extension XCTestCase {
    func assertCompleted<T>(_ description: String, _ promise: Promise<T>) {
        let e = expectation(description: description)
        promise.done { _ in
            e.fulfill()
        }.catch { error in
            XCTFail("Failed: \(description): \(error)")
        }
        let result = XCTWaiter().wait(for: [e], timeout: 5)
        XCTAssertEqual(.completed, result, "Failed: \(description): \(result)")
    }

    func wait<T>(_ promise: Promise<T>) -> XCTWaiter.Result {
        let e = expectation(description: "")
        promise.done { _ in
            e.fulfill()
        }.catch { error in
            XCTFail("Failed: \(error)")
        }
        return XCTWaiter().wait(for: [e], timeout: 5)
    }
}
