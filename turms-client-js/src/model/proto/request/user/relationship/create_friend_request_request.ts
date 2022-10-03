/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CreateFriendRequestRequest {
  recipientId: string;
  content: string;
}

function createBaseCreateFriendRequestRequest(): CreateFriendRequestRequest {
  return { recipientId: "0", content: "" };
}

export const CreateFriendRequestRequest = {
  encode(message: CreateFriendRequestRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.recipientId !== "0") {
      writer.uint32(8).int64(message.recipientId);
    }
    if (message.content !== "") {
      writer.uint32(18).string(message.content);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateFriendRequestRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateFriendRequestRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.recipientId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.content = reader.string();
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
