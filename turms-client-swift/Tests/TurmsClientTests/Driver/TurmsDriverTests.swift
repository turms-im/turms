@testable import TurmsClient
import XCTest

class TurmsDriverTests: XCTestCase {
    func test_system() {
        let turmsDriver: TurmsDriver = TurmsDriver(Config.WS_URL)
        TestUtil.assertCompleted("connect_shouldSucceed", turmsDriver.connect(userId: 1, password: "123"))
        TestUtil.assertCompleted("sendHeartbeat_shouldSucceed", turmsDriver.sendHeartbeat())
        TestUtil.assertCompleted("sendTurmsRequest_shouldSucceed", turmsDriver.send(.with {
            $0.requestID.value = 1000
            $0.queryUserProfileRequest = QueryUserProfileRequest.with {
                $0.userID = 1
            }
        }))
        TestUtil.assertCompleted("disconnect_shouldSucceed", turmsDriver.disconnect())
    }
}
