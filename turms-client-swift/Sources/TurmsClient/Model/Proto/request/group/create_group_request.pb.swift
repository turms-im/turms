// DO NOT EDIT.
// swift-format-ignore-file
//
// Generated by the Swift generator plugin for the protocol buffer compiler.
// Source: request/group/create_group_request.proto
//
// For information on using the generated types, please see the documentation:
//   https://github.com/apple/swift-protobuf/

import Foundation
import SwiftProtobuf

// If the compiler emits an error on this type, it is because this file
// was generated by a version of the `protoc` Swift plug-in that is
// incompatible with the version of SwiftProtobuf to which you are linking.
// Please ensure that you are building against the same version of the API
// that was used to generate this file.
private struct _GeneratedWithProtocGenSwiftVersion: SwiftProtobuf.ProtobufAPIVersionCheck {
    struct _2: SwiftProtobuf.ProtobufAPIVersion_2 {}
    typealias Version = _2
}

public struct CreateGroupRequest {
    // SwiftProtobuf.Message conformance is added in an extension below. See the
    // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
    // methods supported on all messages.

    public var name: String = .init()

    public var intro: String {
        get { return _intro ?? String() }
        set { _intro = newValue }
    }

    /// Returns true if `intro` has been explicitly set.
    public var hasIntro: Bool { return _intro != nil }
    /// Clears the value of `intro`. Subsequent reads from it will return its default value.
    public mutating func clearIntro() { _intro = nil }

    public var announcement: String {
        get { return _announcement ?? String() }
        set { _announcement = newValue }
    }

    /// Returns true if `announcement` has been explicitly set.
    public var hasAnnouncement: Bool { return _announcement != nil }
    /// Clears the value of `announcement`. Subsequent reads from it will return its default value.
    public mutating func clearAnnouncement() { _announcement = nil }

    public var minScore: Int32 {
        get { return _minScore ?? 0 }
        set { _minScore = newValue }
    }

    /// Returns true if `minScore` has been explicitly set.
    public var hasMinScore: Bool { return _minScore != nil }
    /// Clears the value of `minScore`. Subsequent reads from it will return its default value.
    public mutating func clearMinScore() { _minScore = nil }

    public var typeID: Int64 {
        get { return _typeID ?? 0 }
        set { _typeID = newValue }
    }

    /// Returns true if `typeID` has been explicitly set.
    public var hasTypeID: Bool { return _typeID != nil }
    /// Clears the value of `typeID`. Subsequent reads from it will return its default value.
    public mutating func clearTypeID() { _typeID = nil }

    public var muteEndDate: Int64 {
        get { return _muteEndDate ?? 0 }
        set { _muteEndDate = newValue }
    }

    /// Returns true if `muteEndDate` has been explicitly set.
    public var hasMuteEndDate: Bool { return _muteEndDate != nil }
    /// Clears the value of `muteEndDate`. Subsequent reads from it will return its default value.
    public mutating func clearMuteEndDate() { _muteEndDate = nil }

    public var userDefinedAttributes: [String: Value] = [:]

    public var customAttributes: [Value] = []

    public var unknownFields = SwiftProtobuf.UnknownStorage()

    public init() {}

    fileprivate var _intro: String?
    fileprivate var _announcement: String?
    fileprivate var _minScore: Int32?
    fileprivate var _typeID: Int64?
    fileprivate var _muteEndDate: Int64?
}

#if swift(>=5.5) && canImport(_Concurrency)
    extension CreateGroupRequest: @unchecked Sendable {}
#endif // swift(>=5.5) && canImport(_Concurrency)

// MARK: - Code below here is support for the SwiftProtobuf runtime.

private let _protobuf_package = "im.turms.proto"

extension CreateGroupRequest: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
    public static let protoMessageName: String = _protobuf_package + ".CreateGroupRequest"
    public static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
        1: .same(proto: "name"),
        2: .same(proto: "intro"),
        3: .same(proto: "announcement"),
        4: .standard(proto: "min_score"),
        5: .standard(proto: "type_id"),
        6: .standard(proto: "mute_end_date"),
        7: .standard(proto: "user_defined_attributes"),
        15: .standard(proto: "custom_attributes"),
    ]

    public mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
        while let fieldNumber = try decoder.nextFieldNumber() {
            // The use of inline closures is to circumvent an issue where the compiler
            // allocates stack space for every case branch when no optimizations are
            // enabled. https://github.com/apple/swift-protobuf/issues/1034
            switch fieldNumber {
            case 1: try decoder.decodeSingularStringField(value: &name)
            case 2: try decoder.decodeSingularStringField(value: &_intro)
            case 3: try decoder.decodeSingularStringField(value: &_announcement)
            case 4: try decoder.decodeSingularInt32Field(value: &_minScore)
            case 5: try decoder.decodeSingularInt64Field(value: &_typeID)
            case 6: try decoder.decodeSingularInt64Field(value: &_muteEndDate)
            case 7: try decoder.decodeMapField(fieldType: SwiftProtobuf._ProtobufMessageMap<SwiftProtobuf.ProtobufString, Value>.self, value: &userDefinedAttributes)
            case 15: try decoder.decodeRepeatedMessageField(value: &customAttributes)
            default: break
            }
        }
    }

    public func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
        // The use of inline closures is to circumvent an issue where the compiler
        // allocates stack space for every if/case branch local when no optimizations
        // are enabled. https://github.com/apple/swift-protobuf/issues/1034 and
        // https://github.com/apple/swift-protobuf/issues/1182
        if !name.isEmpty {
            try visitor.visitSingularStringField(value: name, fieldNumber: 1)
        }
        try { if let v = self._intro {
            try visitor.visitSingularStringField(value: v, fieldNumber: 2)
        } }()
        try { if let v = self._announcement {
            try visitor.visitSingularStringField(value: v, fieldNumber: 3)
        } }()
        try { if let v = self._minScore {
            try visitor.visitSingularInt32Field(value: v, fieldNumber: 4)
        } }()
        try { if let v = self._typeID {
            try visitor.visitSingularInt64Field(value: v, fieldNumber: 5)
        } }()
        try { if let v = self._muteEndDate {
            try visitor.visitSingularInt64Field(value: v, fieldNumber: 6)
        } }()
        if !userDefinedAttributes.isEmpty {
            try visitor.visitMapField(fieldType: SwiftProtobuf._ProtobufMessageMap<SwiftProtobuf.ProtobufString, Value>.self, value: userDefinedAttributes, fieldNumber: 7)
        }
        if !customAttributes.isEmpty {
            try visitor.visitRepeatedMessageField(value: customAttributes, fieldNumber: 15)
        }
        try unknownFields.traverse(visitor: &visitor)
    }

    public static func == (lhs: CreateGroupRequest, rhs: CreateGroupRequest) -> Bool {
        if lhs.name != rhs.name { return false }
        if lhs._intro != rhs._intro { return false }
        if lhs._announcement != rhs._announcement { return false }
        if lhs._minScore != rhs._minScore { return false }
        if lhs._typeID != rhs._typeID { return false }
        if lhs._muteEndDate != rhs._muteEndDate { return false }
        if lhs.userDefinedAttributes != rhs.userDefinedAttributes { return false }
        if lhs.customAttributes != rhs.customAttributes { return false }
        if lhs.unknownFields != rhs.unknownFields { return false }
        return true
    }
}
