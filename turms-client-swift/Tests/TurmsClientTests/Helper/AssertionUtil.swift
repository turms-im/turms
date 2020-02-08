import Foundation
import PromiseKit
import XCTest

class AssertionUtil {
    private init() {}

    static func assertPromise<T>(_ description: String, _ promise: Promise<T>, assert: ((T) -> Void)? = nil) {
        let e = XCTestExpectation(description: description)
        promise.done { result in
            if assert != nil {
                assert!(result)
            }
            e.fulfill()
        }.catch { error in
            XCTFail("\(description): \(error.localizedDescription)")
        }
        let result = XCTWaiter().wait(for: [e], timeout: 5)
        switch result {
            case .incorrectOrder:
                XCTFail(description + ": incorrect order")
            case .interrupted:
                XCTFail(description + ": interrupted")
            case .timedOut:
                XCTFail(description + ": timedOut")
            default:
                XCTAssert(true, description)
        }
    }
}
