/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { GroupInvitation } from "./group_invitation";

export const protobufPackage = "im.turms.proto";

export interface GroupInvitationsWithVersion {
  groupInvitations: GroupInvitation[];
  lastUpdatedDate?: string | undefined;
}

function createBaseGroupInvitationsWithVersion(): GroupInvitationsWithVersion {
  return { groupInvitations: [], lastUpdatedDate: undefined };
}

export const GroupInvitationsWithVersion = {
  encode(message: GroupInvitationsWithVersion, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    for (const v of message.groupInvitations) {
      GroupInvitation.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(16).int64(message.lastUpdatedDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupInvitationsWithVersion {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupInvitationsWithVersion();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupInvitations.push(GroupInvitation.decode(reader, reader.uint32()));
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
