/* eslint-disable */
import Long from "long";
import { ProfileAccessStrategy } from "../../constant/profile_access_strategy";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UserInfo {
  id?: string | undefined;
  name?: string | undefined;
  intro?: string | undefined;
  registrationDate?: string | undefined;
  active?: boolean | undefined;
  profileAccessStrategy?: ProfileAccessStrategy | undefined;
}

function createBaseUserInfo(): UserInfo {
  return {
    id: undefined,
    name: undefined,
    intro: undefined,
    registrationDate: undefined,
    active: undefined,
    profileAccessStrategy: undefined,
  };
}

export const UserInfo = {
  encode(
    message: UserInfo,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.id !== undefined) {
      writer.uint32(8).int64(message.id);
    }
    if (message.name !== undefined) {
      writer.uint32(18).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(26).string(message.intro);
    }
    if (message.registrationDate !== undefined) {
      writer.uint32(32).int64(message.registrationDate);
    }
    if (message.active !== undefined) {
      writer.uint32(40).bool(message.active);
    }
    if (message.profileAccessStrategy !== undefined) {
      writer.uint32(48).int32(message.profileAccessStrategy);
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
          message.registrationDate = longToString(reader.int64() as Long);
          break;
        case 5:
          message.active = reader.bool();
          break;
        case 6:
          message.profileAccessStrategy = reader.int32() as any;
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
