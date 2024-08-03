import Foundation

public class BaseService {
    let stateStore: StateStore

    init(_ stateStore: StateStore) {
        self.stateStore = stateStore
    }

    func close() async {}

    func onDisconnected(_: Error? = nil) {}
}
