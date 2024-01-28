/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateConversationRequest {
  /** Query filter */
  targetId?: string | undefined;
  groupId?:
    | string
    | undefined;
  /** Update */
  readDate: string;
}

function createBaseUpdateConversationRequest(): UpdateConversationRequest {
  return { targetId: undefined, groupId: undefined, readDate: "0" };
}

export const UpdateConversationRequest = {
  encode(message: UpdateConversationRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.targetId !== undefined) {
      writer.uint32(8).int64(message.targetId);
    }
    if (message.groupId !== undefined) {
      writer.uint32(16).int64(message.groupId);
    }
    if (message.readDate !== "0") {
      writer.uint32(24).int64(message.readDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateConversationRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateConversationRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.targetId = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.groupId = longToString(reader.int64() as Long);
          continue;
        case 3:
          if (tag !== 24) {
            break;
          }

          message.readDate = longToString(reader.int64() as Long);
          continue;
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skipType(tag & 7);
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