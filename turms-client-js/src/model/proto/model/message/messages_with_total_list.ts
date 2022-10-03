/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { MessagesWithTotal } from "./messages_with_total";

export const protobufPackage = "im.turms.proto";

export interface MessagesWithTotalList {
  messagesWithTotalList: MessagesWithTotal[];
}

function createBaseMessagesWithTotalList(): MessagesWithTotalList {
  return { messagesWithTotalList: [] };
}

export const MessagesWithTotalList = {
  encode(message: MessagesWithTotalList, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    for (const v of message.messagesWithTotalList) {
      MessagesWithTotal.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): MessagesWithTotalList {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseMessagesWithTotalList();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.messagesWithTotalList.push(MessagesWithTotal.decode(reader, reader.uint32()));
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
