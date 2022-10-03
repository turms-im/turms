/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryFriendRequestsRequest {
  areSentByMe: boolean;
  lastUpdatedDate?: string | undefined;
}

function createBaseQueryFriendRequestsRequest(): QueryFriendRequestsRequest {
  return { areSentByMe: false, lastUpdatedDate: undefined };
}

export const QueryFriendRequestsRequest = {
  encode(message: QueryFriendRequestsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.areSentByMe === true) {
      writer.uint32(8).bool(message.areSentByMe);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(16).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryFriendRequestsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryFriendRequestsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.areSentByMe = reader.bool();
          break;
        case 2:
          message.lastUpdatedDate = longToString(reader.int64() as Long);
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
