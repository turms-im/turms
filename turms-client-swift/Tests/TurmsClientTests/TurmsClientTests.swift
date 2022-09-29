@testable import TurmsClient
import XCTest

final class TurmsClientTests: XCTestCase {
    func test_init_shouldReturnNotNilClientInstance() {
        let turmsClient = TurmsClient(Config.HOST, Config.PORT)
        XCTAssertNotNil(turmsClient)
    }
}
