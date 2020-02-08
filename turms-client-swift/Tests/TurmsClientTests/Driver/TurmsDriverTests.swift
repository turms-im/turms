@testable import TurmsClient
import XCTest

class TurmsDriverTests: XCTestCase {
    func test_system() {
        let turmsDriver: TurmsDriver = TurmsDriver(Config.WS_URL)
        AssertionUtil.assertPromise("connect_shouldSucceed", turmsDriver.connect(userId: 1, password: "123"))
        AssertionUtil.assertPromise("sendHeartbeat_shouldSucceed", turmsDriver.sendHeartbeat())
        AssertionUtil.assertPromise("sendTurmsRequest_shouldSucceed", turmsDriver.send(TurmsRequest.with {
            $0.requestID.value = 1000
            $0.queryUserProfileRequest = QueryUserProfileRequest.with {
                $0.userID = 1
            }
        }))
        AssertionUtil.assertPromise("disconnect_shouldSucceed", turmsDriver.disconnect())
    }
}
