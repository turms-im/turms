public class Validator {
    private init() {}

    static func areAllNil(_ values: Any?...) -> Bool {
        for value in values {
            if value != nil {
                return false
            }
        }
        return true
    }
}
