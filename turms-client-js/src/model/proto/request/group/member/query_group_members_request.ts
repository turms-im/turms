/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryGroupMembersRequest {
  groupId: string;
  lastUpdatedDate?: string | undefined;
  memberIds: string[];
  withStatus?: boolean | undefined;
}

function createBaseQueryGroupMembersRequest(): QueryGroupMembersRequest {
  return { groupId: "0", lastUpdatedDate: undefined, memberIds: [], withStatus: undefined };
}

export const QueryGroupMembersRequest = {
  encode(message: QueryGroupMembersRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(16).int64(message.lastUpdatedDate);
    }
    writer.uint32(26).fork();
    for (const v of message.memberIds) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.withStatus !== undefined) {
      writer.uint32(32).bool(message.withStatus);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryGroupMembersRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryGroupMembersRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.lastUpdatedDate = longToString(reader.int64() as Long);
          break;
        case 3:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.memberIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.memberIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 4:
          message.withStatus = reader.bool();
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
