/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface DeleteRelationshipRequest {
  userId: string;
  groupIndex?: number | undefined;
  targetGroupIndex?: number | undefined;
}

function createBaseDeleteRelationshipRequest(): DeleteRelationshipRequest {
  return { userId: "0", groupIndex: undefined, targetGroupIndex: undefined };
}

export const DeleteRelationshipRequest = {
  encode(message: DeleteRelationshipRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.groupIndex !== undefined) {
      writer.uint32(16).int32(message.groupIndex);
    }
    if (message.targetGroupIndex !== undefined) {
      writer.uint32(24).int32(message.targetGroupIndex);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteRelationshipRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteRelationshipRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.groupIndex = reader.int32();
          break;
        case 3:
          message.targetGroupIndex = reader.int32();
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
