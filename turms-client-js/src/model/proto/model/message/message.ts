/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface Message {
  id?: string | undefined;
  deliveryDate?: string | undefined;
  modificationDate?: string | undefined;
  text?: string | undefined;
  senderId?: string | undefined;
  groupId?: string | undefined;
  isSystemMessage?: boolean | undefined;
  recipientId?: string | undefined;
  records: Uint8Array[];
  sequenceId?: number | undefined;
  preMessageId?: string | undefined;
}

function createBaseMessage(): Message {
  return {
    id: undefined,
    deliveryDate: undefined,
    modificationDate: undefined,
    text: undefined,
    senderId: undefined,
    groupId: undefined,
    isSystemMessage: undefined,
    recipientId: undefined,
    records: [],
    sequenceId: undefined,
    preMessageId: undefined,
  };
}

export const Message = {
  encode(message: Message, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.id !== undefined) {
      writer.uint32(8).int64(message.id);
    }
    if (message.deliveryDate !== undefined) {
      writer.uint32(16).int64(message.deliveryDate);
    }
    if (message.modificationDate !== undefined) {
      writer.uint32(24).int64(message.modificationDate);
    }
    if (message.text !== undefined) {
      writer.uint32(34).string(message.text);
    }
    if (message.senderId !== undefined) {
      writer.uint32(40).int64(message.senderId);
    }
    if (message.groupId !== undefined) {
      writer.uint32(48).int64(message.groupId);
    }
    if (message.isSystemMessage !== undefined) {
      writer.uint32(56).bool(message.isSystemMessage);
    }
    if (message.recipientId !== undefined) {
      writer.uint32(64).int64(message.recipientId);
    }
    for (const v of message.records) {
      writer.uint32(74).bytes(v!);
    }
    if (message.sequenceId !== undefined) {
      writer.uint32(80).int32(message.sequenceId);
    }
    if (message.preMessageId !== undefined) {
      writer.uint32(88).int64(message.preMessageId);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): Message {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseMessage();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.id = longToString(reader.int64() as Long);
          break;
        case 2:
          message.deliveryDate = longToString(reader.int64() as Long);
          break;
        case 3:
          message.modificationDate = longToString(reader.int64() as Long);
          break;
        case 4:
          message.text = reader.string();
          break;
        case 5:
          message.senderId = longToString(reader.int64() as Long);
          break;
        case 6:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 7:
          message.isSystemMessage = reader.bool();
          break;
        case 8:
          message.recipientId = longToString(reader.int64() as Long);
          break;
        case 9:
          message.records.push(reader.bytes());
          break;
        case 10:
          message.sequenceId = reader.int32();
          break;
        case 11:
          message.preMessageId = longToString(reader.int64() as Long);
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
