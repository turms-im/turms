import Foundation
import SwiftProtobuf

public class RequestBuilder {
    private var fields: [String: Any] = [:]
    private var requestName: String = ""
    
    private init() {}
    
    public static func newInstance() -> RequestBuilder {
        return RequestBuilder()
    }
    
    public func request(_ name: String) -> RequestBuilder {
        requestName = name
        return self
    }
    
    public func id(_ id: Int64) -> RequestBuilder {
        return wrappedField("requestId", id)
    }
    
    public func field(_ name: String, _ value: Any?) -> RequestBuilder {
        if value != nil {
            fields.updateValue(value!, forKey: name)
        }
        return self
    }
    
    public func wrappedField(_ name: String, _ value: Any?) -> RequestBuilder {
        if value != nil {
            fields.updateValue(["value": value!], forKey: name)
        }
        return self
    }
    
    func build() -> TurmsRequest {
        let data = try! JSONSerialization.data(withJSONObject: [requestName: fields], options: [])
        let json = String(data: data, encoding: .utf8)
        return try! TurmsRequest(jsonString: json!)
    }
}
