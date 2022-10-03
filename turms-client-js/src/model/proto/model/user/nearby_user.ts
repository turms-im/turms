/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { UserInfo } from "./user_info";
import { UserLocation } from "./user_location";

export const protobufPackage = "im.turms.proto";

export interface NearbyUser {
  /** session info */
  userId: string;
  deviceType?:
    | DeviceType
    | undefined;
  /** user info */
  info?:
    | UserInfo
    | undefined;
  /** geo info */
  distance?: number | undefined;
  location?: UserLocation | undefined;
}

function createBaseNearbyUser(): NearbyUser {
  return { userId: "0", deviceType: undefined, info: undefined, distance: undefined, location: undefined };
}

export const NearbyUser = {
  encode(message: NearbyUser, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.deviceType !== undefined) {
      writer.uint32(16).int32(message.deviceType);
    }
    if (message.info !== undefined) {
      UserInfo.encode(message.info, writer.uint32(26).fork()).ldelim();
    }
    if (message.distance !== undefined) {
      writer.uint32(32).int32(message.distance);
    }
    if (message.location !== undefined) {
      UserLocation.encode(message.location, writer.uint32(42).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): NearbyUser {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseNearbyUser();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.deviceType = reader.int32() as any;
          break;
        case 3:
          message.info = UserInfo.decode(reader, reader.uint32());
          break;
        case 4:
          message.distance = reader.int32();
          break;
        case 5:
          message.location = UserLocation.decode(reader, reader.uint32());
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
