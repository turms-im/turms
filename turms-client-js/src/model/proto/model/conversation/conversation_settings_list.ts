// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v2.6.1
//   protoc               v5.29.1
// source: model/conversation/conversation_settings_list.proto

/* eslint-disable */
import { BinaryReader, BinaryWriter } from "@bufbuild/protobuf/wire";
import { ConversationSettings } from "./conversation_settings";

export const protobufPackage = "im.turms.proto";

export interface ConversationSettingsList {
  conversationSettingsList: ConversationSettings[];
}

function createBaseConversationSettingsList(): ConversationSettingsList {
  return { conversationSettingsList: [] };
}

export const ConversationSettingsList: MessageFns<ConversationSettingsList> = {
  encode(message: ConversationSettingsList, writer: BinaryWriter = new BinaryWriter()): BinaryWriter {
    for (const v of message.conversationSettingsList) {
      ConversationSettings.encode(v!, writer.uint32(10).fork()).join();
    }
    return writer;
  },

  decode(input: BinaryReader | Uint8Array, length?: number): ConversationSettingsList {
    const reader = input instanceof BinaryReader ? input : new BinaryReader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseConversationSettingsList();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1: {
          if (tag !== 10) {
            break;
          }

          message.conversationSettingsList.push(ConversationSettings.decode(reader, reader.uint32()));
          continue;
        }
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skip(tag & 7);
    }
    return message;
  },
};

export interface MessageFns<T> {
  encode(message: T, writer?: BinaryWriter): BinaryWriter;
  decode(input: BinaryReader | Uint8Array, length?: number): T;
}
