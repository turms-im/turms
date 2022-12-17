import Foundation
import PromiseKit

public class BaseService {
    let stateStore: StateStore

    init(_ stateStore: StateStore) {
        self.stateStore = stateStore
    }

    func close() -> Promise<Void> {
        return Promise.value(())
    }

    func onDisconnected(_: Error? = nil) {}
}
