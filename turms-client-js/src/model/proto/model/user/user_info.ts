// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.1
//   protoc               v5.27.2
// source: model/user/user_info.proto

/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { ProfileAccessStrategy } from "../../constant/profile_access_strategy";
import { Value } from "../common/value";

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
  userDefinedAttributes: { [key: string]: Value };
  customAttributes: Value[];
}

export interface UserInfo_UserDefinedAttributesEntry {
  key: string;
  value?: Value | undefined;
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
    userDefinedAttributes: {},
    customAttributes: [],
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
    Object.entries(message.userDefinedAttributes).forEach(([key, value]) => {
      UserInfo_UserDefinedAttributesEntry.encode({ key: key as any, value }, writer.uint32(74).fork()).ldelim();
    });
    for (const v of message.customAttributes) {
      Value.encode(v!, writer.uint32(122).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserInfo {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserInfo();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.id = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.name = reader.string();
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.intro = reader.string();
          continue;
        case 4:
          if (tag !== 34) {
            break;
          }

          message.profilePicture = reader.string();
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.profileAccessStrategy = reader.int32() as any;
          continue;
        case 6:
          if (tag !== 48) {
            break;
          }

          message.registrationDate = longToString(reader.int64() as Long);
          continue;
        case 7:
          if (tag !== 56) {
            break;
          }

          message.lastUpdatedDate = longToString(reader.int64() as Long);
          continue;
        case 8:
          if (tag !== 64) {
            break;
          }

          message.active = reader.bool();
          continue;
        case 9:
          if (tag !== 74) {
            break;
          }

          const entry9 = UserInfo_UserDefinedAttributesEntry.decode(reader, reader.uint32());
          if (entry9.value !== undefined) {
            message.userDefinedAttributes[entry9.key] = entry9.value;
          }
          continue;
        case 15:
          if (tag !== 122) {
            break;
          }

          message.customAttributes.push(Value.decode(reader, reader.uint32()));
          continue;
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skipType(tag & 7);
    }
    return message;
  },
};

function createBaseUserInfo_UserDefinedAttributesEntry(): UserInfo_UserDefinedAttributesEntry {
  return { key: "", value: undefined };
}

export const UserInfo_UserDefinedAttributesEntry = {
  encode(message: UserInfo_UserDefinedAttributesEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== undefined) {
      Value.encode(message.value, writer.uint32(18).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserInfo_UserDefinedAttributesEntry {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserInfo_UserDefinedAttributesEntry();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          message.key = reader.string();
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.value = Value.decode(reader, reader.uint32());
          continue;
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skipType(tag & 7);
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
