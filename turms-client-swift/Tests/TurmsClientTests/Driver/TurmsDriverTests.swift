@testable import TurmsClient
import XCTest

class TurmsDriverTests: XCTestCase {
    func test_system() {
        let client = TurmsClient(Config.WS_URL)
        let driver = client.driver
        assertCompleted("connect_shouldSucceed", driver.connect())
        assertCompleted("login_shouldSucceed", client.userService.login(userId: 1, password: "123"))
        assertCompleted("sendHeartbeat_shouldSucceed", driver.sendHeartbeat())
        assertCompleted("sendTurmsRequest_shouldSucceed", driver.send {
            $0.queryUserProfileRequest = QueryUserProfileRequest.with {
                $0.userID = 1
            }
        })
        assertCompleted("disconnect_shouldSucceed", driver.disconnect())
    }
}
