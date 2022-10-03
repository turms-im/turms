/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface GroupConversation {
  groupId: string;
  memberIdToReadDate: { [key: string]: string };
}

export interface GroupConversation_MemberIdToReadDateEntry {
  key: string;
  value: string;
}

function createBaseGroupConversation(): GroupConversation {
  return { groupId: "0", memberIdToReadDate: {} };
}

export const GroupConversation = {
  encode(message: GroupConversation, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    Object.entries(message.memberIdToReadDate).forEach(([key, value]) => {
      GroupConversation_MemberIdToReadDateEntry.encode({ key: key as any, value }, writer.uint32(18).fork()).ldelim();
    });
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupConversation {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupConversation();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          const entry2 = GroupConversation_MemberIdToReadDateEntry.decode(reader, reader.uint32());
          if (entry2.value !== undefined) {
            message.memberIdToReadDate[entry2.key] = entry2.value;
          }
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function createBaseGroupConversation_MemberIdToReadDateEntry(): GroupConversation_MemberIdToReadDateEntry {
  return { key: "0", value: "0" };
}

export const GroupConversation_MemberIdToReadDateEntry = {
  encode(message: GroupConversation_MemberIdToReadDateEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "0") {
      writer.uint32(8).int64(message.key);
    }
    if (message.value !== "0") {
      writer.uint32(16).int64(message.value);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupConversation_MemberIdToReadDateEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupConversation_MemberIdToReadDateEntry();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.key = longToString(reader.int64() as Long);
          break;
        case 2:
          message.value = longToString(reader.int64() as Long);
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
