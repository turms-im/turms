/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { GroupMemberRole } from "../../constant/group_member_role";
import { UserStatus } from "../../constant/user_status";

export const protobufPackage = "im.turms.proto";

export interface GroupMember {
  groupId?: string | undefined;
  userId?: string | undefined;
  name?: string | undefined;
  role?: GroupMemberRole | undefined;
  joinDate?: string | undefined;
  muteEndDate?: string | undefined;
  userStatus?: UserStatus | undefined;
  usingDeviceTypes: DeviceType[];
}

function createBaseGroupMember(): GroupMember {
  return {
    groupId: undefined,
    userId: undefined,
    name: undefined,
    role: undefined,
    joinDate: undefined,
    muteEndDate: undefined,
    userStatus: undefined,
    usingDeviceTypes: [],
  };
}

export const GroupMember = {
  encode(message: GroupMember, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== undefined) {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.userId !== undefined) {
      writer.uint32(16).int64(message.userId);
    }
    if (message.name !== undefined) {
      writer.uint32(26).string(message.name);
    }
    if (message.role !== undefined) {
      writer.uint32(32).int32(message.role);
    }
    if (message.joinDate !== undefined) {
      writer.uint32(40).int64(message.joinDate);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(48).int64(message.muteEndDate);
    }
    if (message.userStatus !== undefined) {
      writer.uint32(56).int32(message.userStatus);
    }
    writer.uint32(66).fork();
    for (const v of message.usingDeviceTypes) {
      writer.int32(v);
    }
    writer.ldelim();
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupMember {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupMember();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 3:
          message.name = reader.string();
          break;
        case 4:
          message.role = reader.int32() as any;
          break;
        case 5:
          message.joinDate = longToString(reader.int64() as Long);
          break;
        case 6:
          message.muteEndDate = longToString(reader.int64() as Long);
          break;
        case 7:
          message.userStatus = reader.int32() as any;
          break;
        case 8:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.usingDeviceTypes.push(reader.int32() as any);
            }
          } else {
            message.usingDeviceTypes.push(reader.int32() as any);
          }
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
