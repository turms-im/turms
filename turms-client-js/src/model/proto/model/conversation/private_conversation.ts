/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface PrivateConversation {
  ownerId: string;
  targetId: string;
  readDate: string;
}

function createBasePrivateConversation(): PrivateConversation {
  return { ownerId: "0", targetId: "0", readDate: "0" };
}

export const PrivateConversation = {
  encode(message: PrivateConversation, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.ownerId !== "0") {
      writer.uint32(8).int64(message.ownerId);
    }
    if (message.targetId !== "0") {
      writer.uint32(16).int64(message.targetId);
    }
    if (message.readDate !== "0") {
      writer.uint32(24).int64(message.readDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): PrivateConversation {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBasePrivateConversation();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.ownerId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.targetId = longToString(reader.int64() as Long);
          break;
        case 3:
          message.readDate = longToString(reader.int64() as Long);
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
