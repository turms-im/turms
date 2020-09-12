import Foundation
import Starscream

public class StateStore {
    private var _webSocket: WebSocket?
    private var _isConnected: Bool = false
    private var _connectionRequestId: Int?
    private var _sessionId: String?
    private var _lastRequestDate = Date(timeIntervalSince1970: 0)
    
    init() {}
    
    var websocket: WebSocket? {
        get { return _webSocket }
        set { _webSocket = newValue }
    }
    
    var isConnected: Bool {
        get { return _isConnected }
        set { _isConnected = newValue }
    }
    
    var connectionRequestId: Int? {
        get { return _connectionRequestId }
        set { _connectionRequestId = newValue }
    }
    
    var sessionId: String? {
        get { return _sessionId }
        set { _sessionId = newValue }
    }
    
    var lastRequestDate: Date {
        get { return _lastRequestDate }
        set { _lastRequestDate = newValue }
    }
}
