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

    static func areAllNilOrNotNil(_ values: Any?...) -> Bool {
        let isFirstValueNil = values.first == nil
        for value in values {
            if (value == nil) != isFirstValueNil {
                return false
            }
        }
        return true
    }
}
