// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.1
//   protoc               v5.27.2
// source: model/user/user_settings.proto

/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { Value } from "../common/value";

export const protobufPackage = "im.turms.proto";

export interface UserSettings {
  settings: { [key: string]: Value };
  lastUpdatedDate?: string | undefined;
  customAttributes: Value[];
}

export interface UserSettings_SettingsEntry {
  key: string;
  value?: Value | undefined;
}

function createBaseUserSettings(): UserSettings {
  return { settings: {}, lastUpdatedDate: undefined, customAttributes: [] };
}

export const UserSettings = {
  encode(message: UserSettings, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    Object.entries(message.settings).forEach(([key, value]) => {
      UserSettings_SettingsEntry.encode({ key: key as any, value }, writer.uint32(10).fork()).ldelim();
    });
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(16).int64(message.lastUpdatedDate);
    }
    for (const v of message.customAttributes) {
      Value.encode(v!, writer.uint32(122).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserSettings {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserSettings();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          const entry1 = UserSettings_SettingsEntry.decode(reader, reader.uint32());
          if (entry1.value !== undefined) {
            message.settings[entry1.key] = entry1.value;
          }
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.lastUpdatedDate = longToString(reader.int64() as Long);
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

function createBaseUserSettings_SettingsEntry(): UserSettings_SettingsEntry {
  return { key: "", value: undefined };
}

export const UserSettings_SettingsEntry = {
  encode(message: UserSettings_SettingsEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== undefined) {
      Value.encode(message.value, writer.uint32(18).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserSettings_SettingsEntry {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserSettings_SettingsEntry();
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
