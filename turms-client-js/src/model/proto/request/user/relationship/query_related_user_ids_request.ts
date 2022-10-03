/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryRelatedUserIdsRequest {
  blocked?: boolean | undefined;
  groupIndexes: number[];
  lastUpdatedDate?: string | undefined;
}

function createBaseQueryRelatedUserIdsRequest(): QueryRelatedUserIdsRequest {
  return { blocked: undefined, groupIndexes: [], lastUpdatedDate: undefined };
}

export const QueryRelatedUserIdsRequest = {
  encode(message: QueryRelatedUserIdsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.blocked !== undefined) {
      writer.uint32(8).bool(message.blocked);
    }
    writer.uint32(18).fork();
    for (const v of message.groupIndexes) {
      writer.int32(v);
    }
    writer.ldelim();
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(24).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryRelatedUserIdsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryRelatedUserIdsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.blocked = reader.bool();
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.groupIndexes.push(reader.int32());
            }
          } else {
            message.groupIndexes.push(reader.int32());
          }
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
