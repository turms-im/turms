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

    static func areAllNullOrNonNull(_ values: Any?...) -> Bool {
        let isFirstValueNull = values.first == nil
        for value in values {
            if (value == nil) != isFirstValueNull {
                return false
            }
        }
        return true
    }
}
