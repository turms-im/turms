@testable import TurmsClient
import XCTest

final class TurmsClientTests: XCTestCase {
    func test_init_shouldReturnNotNilClientInstance() {
        let turmsClient: TurmsClient = TurmsClient(Config.WS_URL)
        XCTAssertNotNil(turmsClient)
    }
}
