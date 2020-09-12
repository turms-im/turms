import Foundation

public struct SessionDisconnectInfo {
    private var _wasConnected: Bool
    private var _isClosedByClient: Bool
    private var _closeStatus: TurmsCloseStatus?
    private var _webSocketStatusCode: Int?
    private var _webSocketReason: String?
    private var _error: Error?
    
    public var wasConnected: Bool {
        get { return _wasConnected }
        set { _wasConnected = newValue }
    }
    
    public var isClosedByClient: Bool {
        get { return _isClosedByClient }
        set { _isClosedByClient = newValue }
    }
    
    public var closeStatus: TurmsCloseStatus? {
        get { return _closeStatus }
        set { _closeStatus = newValue }
    }
    
    public var webSocketStatusCode: Int? {
        get { return _webSocketStatusCode }
        set { _webSocketStatusCode = newValue }
    }
    
    public var webSocketReason: String? {
        get { return _webSocketReason }
        set { _webSocketReason = newValue }
    }
    
    public var error: Error? {
        get { return _error }
        set { _error = newValue }
    }
    
    public init(wasConnected: Bool, isClosedByClient: Bool, closeStatus: TurmsCloseStatus? = nil, webSocketStatusCode: Int? = nil, webSocketReason: String? = nil, error: Error? = nil) {
        self._wasConnected = wasConnected
        self._isClosedByClient = isClosedByClient
        self._closeStatus = closeStatus
        self._webSocketStatusCode = webSocketStatusCode
        self._webSocketReason = webSocketReason
        self._error = error
    }
}
