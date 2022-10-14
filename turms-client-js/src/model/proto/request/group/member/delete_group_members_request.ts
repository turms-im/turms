/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface DeleteGroupMembersRequest {
  groupId: string;
  memberIds: string[];
  successorId?: string | undefined;
  quitAfterTransfer?: boolean | undefined;
}

function createBaseDeleteGroupMembersRequest(): DeleteGroupMembersRequest {
  return { groupId: "0", memberIds: [], successorId: undefined, quitAfterTransfer: undefined };
}

export const DeleteGroupMembersRequest = {
  encode(message: DeleteGroupMembersRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    writer.uint32(18).fork();
    for (const v of message.memberIds) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.successorId !== undefined) {
      writer.uint32(24).int64(message.successorId);
    }
    if (message.quitAfterTransfer !== undefined) {
      writer.uint32(32).bool(message.quitAfterTransfer);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteGroupMembersRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteGroupMembersRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.memberIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.memberIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 3:
          message.successorId = longToString(reader.int64() as Long);
          break;
        case 4:
          message.quitAfterTransfer = reader.bool();
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
