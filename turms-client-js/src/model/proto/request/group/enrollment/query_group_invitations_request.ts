/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryGroupInvitationsRequest {
  groupId?: string | undefined;
  areSentByMe?: boolean | undefined;
  lastUpdatedDate?: string | undefined;
}

function createBaseQueryGroupInvitationsRequest(): QueryGroupInvitationsRequest {
  return { groupId: undefined, areSentByMe: undefined, lastUpdatedDate: undefined };
}

export const QueryGroupInvitationsRequest = {
  encode(message: QueryGroupInvitationsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== undefined) {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.areSentByMe !== undefined) {
      writer.uint32(16).bool(message.areSentByMe);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(24).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryGroupInvitationsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryGroupInvitationsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.areSentByMe = reader.bool();
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
