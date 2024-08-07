@testable import TurmsClient
import XCTest

class TurmsDriverTests: XCTestCase {
    func test_system() async throws {
        let client = TurmsClient(Config.HOST, Config.PORT)
        let driver = client.driver
        try await assertCompleted("connect_shouldSucceed", await driver.connect())
        try await assertCompleted("login_shouldSucceed", await client.userService.login(userId: 1, password: "123"))
        try await assertCompleted("sendHeartbeat_shouldSucceed", await driver.sendHeartbeat())
        try await assertCompleted("sendTurmsRequest_shouldSucceed", await driver.send {
            $0.queryUserProfilesRequest = .with {
                $0.userIds = [1]
            }
        })
        try await assertCompleted("disconnect_shouldSucceed", await driver.disconnect())
    }
}
