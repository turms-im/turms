/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";

export const protobufPackage = "im.turms.proto";

export interface UserSessionId {
  userId: string;
  deviceType: DeviceType;
}

const baseUserSessionId: object = { userId: "0", deviceType: 0 };

export const UserSessionId = {
  encode(
    message: UserSessionId,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.deviceType !== 0) {
      writer.uint32(16).int32(message.deviceType);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserSessionId {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseUserSessionId } as UserSessionId;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.deviceType = reader.int32() as any;
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
