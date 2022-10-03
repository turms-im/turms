/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryConversationsRequest {
  /** Private conversations */
  targetIds: string[];
  /** Group conversations */
  groupIds: string[];
}

function createBaseQueryConversationsRequest(): QueryConversationsRequest {
  return { targetIds: [], groupIds: [] };
}

export const QueryConversationsRequest = {
  encode(message: QueryConversationsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    writer.uint32(10).fork();
    for (const v of message.targetIds) {
      writer.int64(v);
    }
    writer.ldelim();
    writer.uint32(18).fork();
    for (const v of message.groupIds) {
      writer.int64(v);
    }
    writer.ldelim();
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryConversationsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryConversationsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.targetIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.targetIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.groupIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.groupIds.push(longToString(reader.int64() as Long));
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

function longToString(long: Long) {
  return long.toString();
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
