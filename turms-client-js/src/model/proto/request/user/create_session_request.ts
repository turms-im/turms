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
  deviceDetails: { [key: string]: string };
  location?: UserLocation | undefined;
}

export interface CreateSessionRequest_DeviceDetailsEntry {
  key: string;
  value: string;
}

function createBaseCreateSessionRequest(): CreateSessionRequest {
  return {
    version: 0,
    userId: "0",
    password: undefined,
    userStatus: undefined,
    deviceType: 0,
    deviceDetails: {},
    location: undefined,
  };
}

export const CreateSessionRequest = {
  encode(message: CreateSessionRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
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
    Object.entries(message.deviceDetails).forEach(([key, value]) => {
      CreateSessionRequest_DeviceDetailsEntry.encode({ key: key as any, value }, writer.uint32(50).fork()).ldelim();
    });
    if (message.location !== undefined) {
      UserLocation.encode(message.location, writer.uint32(58).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateSessionRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateSessionRequest();
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
          const entry6 = CreateSessionRequest_DeviceDetailsEntry.decode(reader, reader.uint32());
          if (entry6.value !== undefined) {
            message.deviceDetails[entry6.key] = entry6.value;
          }
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

function createBaseCreateSessionRequest_DeviceDetailsEntry(): CreateSessionRequest_DeviceDetailsEntry {
  return { key: "", value: "" };
}

export const CreateSessionRequest_DeviceDetailsEntry = {
  encode(message: CreateSessionRequest_DeviceDetailsEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== "") {
      writer.uint32(18).string(message.value);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateSessionRequest_DeviceDetailsEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateSessionRequest_DeviceDetailsEntry();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.key = reader.string();
          break;
        case 2:
          message.value = reader.string();
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
