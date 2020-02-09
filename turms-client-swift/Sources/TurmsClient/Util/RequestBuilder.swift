import Foundation
import SwiftProtobuf

public class RequestBuilder {
    private var requestName: String?
    private var requestId: Int64?
    private var fields: [String: Any] = [:]
    
    public func request(_ name: String) -> RequestBuilder {
        requestName = name
        return self
    }
    
    public func id(_ id: Int64) -> RequestBuilder {
        requestId = id
        return self
    }
    
    public func field(_ name: String, _ value: Any?) -> RequestBuilder {
        if let unwrappedValue = value {
            if unwrappedValue is Date {
                fields.updateValue(Int64((unwrappedValue as! Date).timeIntervalSince1970 * 1000), forKey: name)
            } else {
                let mirror = Mirror(reflecting: unwrappedValue)
                if mirror.displayStyle == .enum {
                    let strValue = toSnakeCase(String(describing: unwrappedValue))
                    fields.updateValue(strValue, forKey: name)
                } else {
                    fields.updateValue(unwrappedValue, forKey: name)
                }
            }
        }
        return self
    }
    
    private func toSnakeCase(_ str: String) -> String {
        return String(str.flatMap {
            $0.isUppercase ? "_\($0)" : $0.uppercased()
        })
    }
    
    func build() -> TurmsRequest {
        // Use try! instead of try or try? because if errors like "NSInvalidArgumentException", "Invalid type in JSON write (__SwiftValue)" occur. It should be a bug of TurmsClient to fix
        let data = try! JSONSerialization.data(withJSONObject: [requestName!: fields, "requestId": requestId!], options: [])
        return try! TurmsRequest(jsonUTF8Data: data)
    }
}
