/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface DeleteGroupMemberRequest {
  groupId: string;
  memberId: string;
  successorId?: string | undefined;
  quitAfterTransfer?: boolean | undefined;
}

function createBaseDeleteGroupMemberRequest(): DeleteGroupMemberRequest {
  return { groupId: "0", memberId: "0", successorId: undefined, quitAfterTransfer: undefined };
}

export const DeleteGroupMemberRequest = {
  encode(message: DeleteGroupMemberRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.memberId !== "0") {
      writer.uint32(16).int64(message.memberId);
    }
    if (message.successorId !== undefined) {
      writer.uint32(24).int64(message.successorId);
    }
    if (message.quitAfterTransfer !== undefined) {
      writer.uint32(32).bool(message.quitAfterTransfer);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteGroupMemberRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteGroupMemberRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.memberId = longToString(reader.int64() as Long);
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
