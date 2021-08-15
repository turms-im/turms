/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { UserStatus } from "../../constant/user_status";
import { UserLocation } from "../../model/user/user_location";

export const protobufPackage = "im.turms.proto";

export interface CreateSessionRequest {
  version: number;
  userId: string;
  password?: string | undefined;
  userStatus?: UserStatus | undefined;
  deviceType: DeviceType;
  deviceDetails?: string | undefined;
  location?: UserLocation | undefined;
}

const baseCreateSessionRequest: object = {
  version: 0,
  userId: "0",
  deviceType: 0,
};

export const CreateSessionRequest = {
  encode(
    message: CreateSessionRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.version !== 0) {
      writer.uint32(8).int32(message.version);
    }
    if (message.userId !== "0") {
      writer.uint32(16).int64(message.userId);
    }
    if (message.password !== undefined) {
      writer.uint32(26).string(message.password);
    }
    if (message.userStatus !== undefined) {
      writer.uint32(32).int32(message.userStatus);
    }
    if (message.deviceType !== 0) {
      writer.uint32(40).int32(message.deviceType);
    }
    if (message.deviceDetails !== undefined) {
      writer.uint32(50).string(message.deviceDetails);
    }
    if (message.location !== undefined) {
      UserLocation.encode(message.location, writer.uint32(58).fork()).ldelim();
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): CreateSessionRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseCreateSessionRequest } as CreateSessionRequest;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.version = reader.int32();
          break;
        case 2:
          message.userId = longToString(reader.int64() as Long);
          break;
        case 3:
          message.password = reader.string();
          break;
        case 4:
          message.userStatus = reader.int32() as any;
          break;
        case 5:
          message.deviceType = reader.int32() as any;
          break;
        case 6:
          message.deviceDetails = reader.string();
          break;
        case 7:
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
