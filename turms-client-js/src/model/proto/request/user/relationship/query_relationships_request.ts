/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryRelationshipsRequest {
  userIds: string[];
  blocked?: boolean | undefined;
  groupIndex?: number | undefined;
  lastUpdatedDate?: string | undefined;
}

const baseQueryRelationshipsRequest: object = { userIds: "0" };

export const QueryRelationshipsRequest = {
  encode(
    message: QueryRelationshipsRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    writer.uint32(10).fork();
    for (const v of message.userIds) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.blocked !== undefined) {
      writer.uint32(16).bool(message.blocked);
    }
    if (message.groupIndex !== undefined) {
      writer.uint32(24).int32(message.groupIndex);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(32).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): QueryRelationshipsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseQueryRelationshipsRequest,
    } as QueryRelationshipsRequest;
    message.userIds = [];
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.userIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.userIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 2:
          message.blocked = reader.bool();
          break;
        case 3:
          message.groupIndex = reader.int32();
          break;
        case 4:
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
