/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CreateRelationshipRequest {
  userId: string;
  blocked: boolean;
  groupIndex?: number | undefined;
}

function createBaseCreateRelationshipRequest(): CreateRelationshipRequest {
  return { userId: "0", blocked: false, groupIndex: undefined };
}

export const CreateRelationshipRequest = {
  encode(message: CreateRelationshipRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.blocked === true) {
      writer.uint32(16).bool(message.blocked);
    }
    if (message.groupIndex !== undefined) {
      writer.uint32(24).int32(message.groupIndex);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateRelationshipRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateRelationshipRequest();
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
          message.groupIndex = reader.int32();
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
