/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { ProfileAccessStrategy } from "../../constant/profile_access_strategy";

export const protobufPackage = "im.turms.proto";

export interface UserInfo {
  id?: string | undefined;
  name?: string | undefined;
  intro?: string | undefined;
  profilePicture?: string | undefined;
  profileAccessStrategy?: ProfileAccessStrategy | undefined;
  registrationDate?: string | undefined;
  lastUpdatedDate?: string | undefined;
  active?: boolean | undefined;
}

function createBaseUserInfo(): UserInfo {
  return {
    id: undefined,
    name: undefined,
    intro: undefined,
    profilePicture: undefined,
    profileAccessStrategy: undefined,
    registrationDate: undefined,
    lastUpdatedDate: undefined,
    active: undefined,
  };
}

export const UserInfo = {
  encode(message: UserInfo, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.id !== undefined) {
      writer.uint32(8).int64(message.id);
    }
    if (message.name !== undefined) {
      writer.uint32(18).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(26).string(message.intro);
    }
    if (message.profilePicture !== undefined) {
      writer.uint32(34).string(message.profilePicture);
    }
    if (message.profileAccessStrategy !== undefined) {
      writer.uint32(40).int32(message.profileAccessStrategy);
    }
    if (message.registrationDate !== undefined) {
      writer.uint32(48).int64(message.registrationDate);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(56).int64(message.lastUpdatedDate);
    }
    if (message.active !== undefined) {
      writer.uint32(64).bool(message.active);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserInfo {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserInfo();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.id = longToString(reader.int64() as Long);
          break;
        case 2:
          message.name = reader.string();
          break;
        case 3:
          message.intro = reader.string();
          break;
        case 4:
          message.profilePicture = reader.string();
          break;
        case 5:
          message.profileAccessStrategy = reader.int32() as any;
          break;
        case 6:
          message.registrationDate = longToString(reader.int64() as Long);
          break;
        case 7:
          message.lastUpdatedDate = longToString(reader.int64() as Long);
          break;
        case 8:
          message.active = reader.bool();
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
