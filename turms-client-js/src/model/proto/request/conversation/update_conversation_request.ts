// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.1
//   protoc               v5.27.2
// source: request/conversation/update_conversation_request.proto

/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { Value } from "../../model/common/value";

export const protobufPackage = "im.turms.proto";

export interface UpdateConversationRequest {
  /** Query filter */
  userId?: string | undefined;
  groupId?:
    | string
    | undefined;
  /** Update */
  readDate: string;
  customAttributes: Value[];
}

function createBaseUpdateConversationRequest(): UpdateConversationRequest {
  return { userId: undefined, groupId: undefined, readDate: "0", customAttributes: [] };
}

export const UpdateConversationRequest = {
  encode(message: UpdateConversationRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== undefined) {
      writer.uint32(8).int64(message.userId);
    }
    if (message.groupId !== undefined) {
      writer.uint32(16).int64(message.groupId);
    }
    if (message.readDate !== "0") {
      writer.uint32(24).int64(message.readDate);
    }
    for (const v of message.customAttributes) {
      Value.encode(v!, writer.uint32(122).fork()).ldelim();
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

          message.userId = longToString(reader.int64() as Long);
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
        case 15:
          if (tag !== 122) {
            break;
          }

          message.customAttributes.push(Value.decode(reader, reader.uint32()));
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
