/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryRelatedUserIdsRequest {
  blocked?: boolean | undefined;
  groupIndex?: number | undefined;
  lastUpdatedDate?: string | undefined;
}

const baseQueryRelatedUserIdsRequest: object = {};

export const QueryRelatedUserIdsRequest = {
  encode(
    message: QueryRelatedUserIdsRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.blocked !== undefined) {
      writer.uint32(8).bool(message.blocked);
    }
    if (message.groupIndex !== undefined) {
      writer.uint32(16).int32(message.groupIndex);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(24).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): QueryRelatedUserIdsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseQueryRelatedUserIdsRequest,
    } as QueryRelatedUserIdsRequest;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.blocked = reader.bool();
          break;
        case 2:
          message.groupIndex = reader.int32();
          break;
        case 3:
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
