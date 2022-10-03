/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { GroupConversation } from "./group_conversation";
import { PrivateConversation } from "./private_conversation";

export const protobufPackage = "im.turms.proto";

export interface Conversations {
  privateConversations: PrivateConversation[];
  groupConversations: GroupConversation[];
}

function createBaseConversations(): Conversations {
  return { privateConversations: [], groupConversations: [] };
}

export const Conversations = {
  encode(message: Conversations, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    for (const v of message.privateConversations) {
      PrivateConversation.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    for (const v of message.groupConversations) {
      GroupConversation.encode(v!, writer.uint32(18).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): Conversations {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseConversations();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.privateConversations.push(PrivateConversation.decode(reader, reader.uint32()));
          break;
        case 2:
          message.groupConversations.push(GroupConversation.decode(reader, reader.uint32()));
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
