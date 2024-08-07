// DO NOT EDIT.
// swift-format-ignore-file
//
// Generated by the Swift generator plugin for the protocol buffer compiler.
// Source: request/group/member/create_group_members_request.proto
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

public struct CreateGroupMembersRequest {
    // SwiftProtobuf.Message conformance is added in an extension below. See the
    // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
    // methods supported on all messages.

    public var groupID: Int64 = 0

    public var userIds: [Int64] = []

    public var name: String {
        get { return _name ?? String() }
        set { _name = newValue }
    }

    /// Returns true if `name` has been explicitly set.
    public var hasName: Bool { return _name != nil }
    /// Clears the value of `name`. Subsequent reads from it will return its default value.
    public mutating func clearName() { _name = nil }

    public var role: GroupMemberRole {
        get { return _role ?? .owner }
        set { _role = newValue }
    }

    /// Returns true if `role` has been explicitly set.
    public var hasRole: Bool { return _role != nil }
    /// Clears the value of `role`. Subsequent reads from it will return its default value.
    public mutating func clearRole() { _role = nil }

    public var muteEndDate: Int64 {
        get { return _muteEndDate ?? 0 }
        set { _muteEndDate = newValue }
    }

    /// Returns true if `muteEndDate` has been explicitly set.
    public var hasMuteEndDate: Bool { return _muteEndDate != nil }
    /// Clears the value of `muteEndDate`. Subsequent reads from it will return its default value.
    public mutating func clearMuteEndDate() { _muteEndDate = nil }

    public var customAttributes: [Value] = []

    public var unknownFields = SwiftProtobuf.UnknownStorage()

    public init() {}

    fileprivate var _name: String?
    fileprivate var _role: GroupMemberRole?
    fileprivate var _muteEndDate: Int64?
}

#if swift(>=5.5) && canImport(_Concurrency)
    extension CreateGroupMembersRequest: @unchecked Sendable {}
#endif // swift(>=5.5) && canImport(_Concurrency)

// MARK: - Code below here is support for the SwiftProtobuf runtime.

private let _protobuf_package = "im.turms.proto"

extension CreateGroupMembersRequest: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
    public static let protoMessageName: String = _protobuf_package + ".CreateGroupMembersRequest"
    public static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
        1: .standard(proto: "group_id"),
        2: .standard(proto: "user_ids"),
        3: .same(proto: "name"),
        4: .same(proto: "role"),
        5: .standard(proto: "mute_end_date"),
        15: .standard(proto: "custom_attributes"),
    ]

    public mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
        while let fieldNumber = try decoder.nextFieldNumber() {
            // The use of inline closures is to circumvent an issue where the compiler
            // allocates stack space for every case branch when no optimizations are
            // enabled. https://github.com/apple/swift-protobuf/issues/1034
            switch fieldNumber {
            case 1: try decoder.decodeSingularInt64Field(value: &groupID)
            case 2: try decoder.decodeRepeatedInt64Field(value: &userIds)
            case 3: try decoder.decodeSingularStringField(value: &_name)
            case 4: try decoder.decodeSingularEnumField(value: &_role)
            case 5: try decoder.decodeSingularInt64Field(value: &_muteEndDate)
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
        if groupID != 0 {
            try visitor.visitSingularInt64Field(value: groupID, fieldNumber: 1)
        }
        if !userIds.isEmpty {
            try visitor.visitPackedInt64Field(value: userIds, fieldNumber: 2)
        }
        try { if let v = self._name {
            try visitor.visitSingularStringField(value: v, fieldNumber: 3)
        } }()
        try { if let v = self._role {
            try visitor.visitSingularEnumField(value: v, fieldNumber: 4)
        } }()
        try { if let v = self._muteEndDate {
            try visitor.visitSingularInt64Field(value: v, fieldNumber: 5)
        } }()
        if !customAttributes.isEmpty {
            try visitor.visitRepeatedMessageField(value: customAttributes, fieldNumber: 15)
        }
        try unknownFields.traverse(visitor: &visitor)
    }

    public static func == (lhs: CreateGroupMembersRequest, rhs: CreateGroupMembersRequest) -> Bool {
        if lhs.groupID != rhs.groupID { return false }
        if lhs.userIds != rhs.userIds { return false }
        if lhs._name != rhs._name { return false }
        if lhs._role != rhs._role { return false }
        if lhs._muteEndDate != rhs._muteEndDate { return false }
        if lhs.customAttributes != rhs.customAttributes { return false }
        if lhs.unknownFields != rhs.unknownFields { return false }
        return true
    }
}
