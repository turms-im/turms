/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { UserStatus } from "../../constant/user_status";

export const protobufPackage = "im.turms.proto";

export interface UserOnlineStatus {
  userId: string;
  userStatus: UserStatus;
  usingDeviceTypes: DeviceType[];
}

function createBaseUserOnlineStatus(): UserOnlineStatus {
  return { userId: "0", userStatus: 0, usingDeviceTypes: [] };
}

export const UserOnlineStatus = {
  encode(message: UserOnlineStatus, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.userStatus !== 0) {
      writer.uint32(16).int32(message.userStatus);
    }
    writer.uint32(26).fork();
    for (const v of message.usingDeviceTypes) {
      writer.int32(v);
    }
    writer.ldelim();
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserOnlineStatus {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserOnlineStatus();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.userStatus = reader.int32() as any;
          break;
        case 3:
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
