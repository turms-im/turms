public class Validator {
    private init() {}

    static func isNilOrEmpty(_ value: Any?) -> Bool {
        return value == nil || (value as? any Collection)?.isEmpty == true
    }

    static func isNotNilOrEmpty(_ value: Any?) -> Bool {
        return !isNilOrEmpty(value)
    }

    static func areAllNil(_ values: Any?...) -> Bool {
        for value in values {
            if value != nil {
                return false
            }
        }
        return true
    }

    static func areAllNilOrEmpty(_ values: Any?...) -> Bool {
        for value in values {
            if !isNilOrEmpty(value) {
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
