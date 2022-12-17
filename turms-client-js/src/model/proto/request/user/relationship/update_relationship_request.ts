/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateRelationshipRequest {
  /** Query filter */
  userId: string;
  /** Update */
  blocked?: boolean | undefined;
  newGroupIndex?: number | undefined;
  deleteGroupIndex?: number | undefined;
}

function createBaseUpdateRelationshipRequest(): UpdateRelationshipRequest {
  return { userId: "0", blocked: undefined, newGroupIndex: undefined, deleteGroupIndex: undefined };
}

export const UpdateRelationshipRequest = {
  encode(message: UpdateRelationshipRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.blocked !== undefined) {
      writer.uint32(16).bool(message.blocked);
    }
    if (message.newGroupIndex !== undefined) {
      writer.uint32(24).int32(message.newGroupIndex);
    }
    if (message.deleteGroupIndex !== undefined) {
      writer.uint32(32).int32(message.deleteGroupIndex);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateRelationshipRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateRelationshipRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.blocked = reader.bool();
          break;
        case 3:
          message.newGroupIndex = reader.int32();
          break;
        case 4:
          message.deleteGroupIndex = reader.int32();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function longToString(long: Long) {
  return long.toString();
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
