/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { GroupMemberRole } from "../../../constant/group_member_role";

export const protobufPackage = "im.turms.proto";

export interface UpdateGroupMemberRequest {
  /** Query filter */
  groupId: string;
  memberId: string;
  /** Update */
  name?: string | undefined;
  role?: GroupMemberRole | undefined;
  muteEndDate?: string | undefined;
}

function createBaseUpdateGroupMemberRequest(): UpdateGroupMemberRequest {
  return { groupId: "0", memberId: "0", name: undefined, role: undefined, muteEndDate: undefined };
}

export const UpdateGroupMemberRequest = {
  encode(message: UpdateGroupMemberRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.memberId !== "0") {
      writer.uint32(16).int64(message.memberId);
    }
    if (message.name !== undefined) {
      writer.uint32(26).string(message.name);
    }
    if (message.role !== undefined) {
      writer.uint32(32).int32(message.role);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(40).int64(message.muteEndDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateGroupMemberRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateGroupMemberRequest();
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
          message.name = reader.string();
          break;
        case 4:
          message.role = reader.int32() as any;
          break;
        case 5:
          message.muteEndDate = longToString(reader.int64() as Long);
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
