import Foundation

import TurmsClient
import XCTest

extension XCTestCase {
    func assertCompleted<T>(_ description: String, _ taskProvider: @autoclosure @escaping () async throws -> T) async throws {
        return try await assertCompleted(description, taskProvider)
    }

    func assertCompleted<T>(_ description: String, _ taskProvider: @escaping () async throws -> T) async throws {
        let e = expectation(description: description)
        let task = Task {
            do {
                let result = try await taskProvider()
                e.fulfill()
            } catch {
                XCTFail("Failed: \(description): \(error)")
            }
        }
        await fulfillment(of: [e], timeout: 5)
        task.cancel()
    }

    func wait<T>(_ taskProvider: @autoclosure @escaping () async throws -> T) async throws {
        return try await wait(taskProvider)
    }

    func wait<T>(_ taskProvider: @escaping () async throws -> T) async throws {
        let e = expectation(description: "")
        let task = Task {
            do {
                let result = try await taskProvider()
                e.fulfill()
            } catch {
                XCTFail("Failed: \(error)")
            }
        }
        await fulfillment(of: [e], timeout: 5)
        task.cancel()
    }
}
