// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.1
//   protoc               v5.27.2
// source: request/user/relationship/query_related_user_ids_request.proto

/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { Value } from "../../../model/common/value";

export const protobufPackage = "im.turms.proto";

export interface QueryRelatedUserIdsRequest {
  blocked?: boolean | undefined;
  groupIndexes: number[];
  lastUpdatedDate?: string | undefined;
  customAttributes: Value[];
}

function createBaseQueryRelatedUserIdsRequest(): QueryRelatedUserIdsRequest {
  return { blocked: undefined, groupIndexes: [], lastUpdatedDate: undefined, customAttributes: [] };
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
    for (const v of message.customAttributes) {
      Value.encode(v!, writer.uint32(122).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryRelatedUserIdsRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryRelatedUserIdsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.blocked = reader.bool();
          continue;
        case 2:
          if (tag === 16) {
            message.groupIndexes.push(reader.int32());

            continue;
          }

          if (tag === 18) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.groupIndexes.push(reader.int32());
            }

            continue;
          }

          break;
        case 3:
          if (tag !== 24) {
            break;
          }

          message.lastUpdatedDate = longToString(reader.int64() as Long);
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
