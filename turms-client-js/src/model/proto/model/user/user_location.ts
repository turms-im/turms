/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UserLocation {
  latitude: number;
  longitude: number;
  timestamp?:
    | string
    | undefined;
  /** e.g. street address, city, state, country, etc. */
  details: { [key: string]: string };
}

export interface UserLocation_DetailsEntry {
  key: string;
  value: string;
}

function createBaseUserLocation(): UserLocation {
  return { latitude: 0, longitude: 0, timestamp: undefined, details: {} };
}

export const UserLocation = {
  encode(message: UserLocation, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.latitude !== 0) {
      writer.uint32(13).float(message.latitude);
    }
    if (message.longitude !== 0) {
      writer.uint32(21).float(message.longitude);
    }
    if (message.timestamp !== undefined) {
      writer.uint32(24).int64(message.timestamp);
    }
    Object.entries(message.details).forEach(([key, value]) => {
      UserLocation_DetailsEntry.encode({ key: key as any, value }, writer.uint32(34).fork()).ldelim();
    });
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserLocation {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserLocation();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.latitude = reader.float();
          break;
        case 2:
          message.longitude = reader.float();
          break;
        case 3:
          message.timestamp = longToString(reader.int64() as Long);
          break;
        case 4:
          const entry4 = UserLocation_DetailsEntry.decode(reader, reader.uint32());
          if (entry4.value !== undefined) {
            message.details[entry4.key] = entry4.value;
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

function createBaseUserLocation_DetailsEntry(): UserLocation_DetailsEntry {
  return { key: "", value: "" };
}

export const UserLocation_DetailsEntry = {
  encode(message: UserLocation_DetailsEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== "") {
      writer.uint32(18).string(message.value);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserLocation_DetailsEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserLocation_DetailsEntry();
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
