/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { Message } from "./message";

export const protobufPackage = "im.turms.proto";

export interface MessagesWithTotal {
  total: number;
  isGroupMessage: boolean;
  fromId: string;
  messages: Message[];
}

function createBaseMessagesWithTotal(): MessagesWithTotal {
  return { total: 0, isGroupMessage: false, fromId: "0", messages: [] };
}

export const MessagesWithTotal = {
  encode(message: MessagesWithTotal, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.total !== 0) {
      writer.uint32(8).int32(message.total);
    }
    if (message.isGroupMessage === true) {
      writer.uint32(16).bool(message.isGroupMessage);
    }
    if (message.fromId !== "0") {
      writer.uint32(24).int64(message.fromId);
    }
    for (const v of message.messages) {
      Message.encode(v!, writer.uint32(34).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): MessagesWithTotal {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseMessagesWithTotal();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.total = reader.int32();
          break;
        case 2:
          message.isGroupMessage = reader.bool();
          break;
        case 3:
          message.fromId = longToString(reader.int64() as Long);
          break;
        case 4:
          message.messages.push(Message.decode(reader, reader.uint32()));
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
