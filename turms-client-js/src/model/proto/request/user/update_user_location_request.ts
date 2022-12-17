/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateUserLocationRequest {
  /** Update */
  latitude: number;
  longitude: number;
  details: { [key: string]: string };
}

export interface UpdateUserLocationRequest_DetailsEntry {
  key: string;
  value: string;
}

function createBaseUpdateUserLocationRequest(): UpdateUserLocationRequest {
  return { latitude: 0, longitude: 0, details: {} };
}

export const UpdateUserLocationRequest = {
  encode(message: UpdateUserLocationRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.latitude !== 0) {
      writer.uint32(13).float(message.latitude);
    }
    if (message.longitude !== 0) {
      writer.uint32(21).float(message.longitude);
    }
    Object.entries(message.details).forEach(([key, value]) => {
      UpdateUserLocationRequest_DetailsEntry.encode({ key: key as any, value }, writer.uint32(26).fork()).ldelim();
    });
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserLocationRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateUserLocationRequest();
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
          const entry3 = UpdateUserLocationRequest_DetailsEntry.decode(reader, reader.uint32());
          if (entry3.value !== undefined) {
            message.details[entry3.key] = entry3.value;
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

function createBaseUpdateUserLocationRequest_DetailsEntry(): UpdateUserLocationRequest_DetailsEntry {
  return { key: "", value: "" };
}

export const UpdateUserLocationRequest_DetailsEntry = {
  encode(message: UpdateUserLocationRequest_DetailsEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== "") {
      writer.uint32(18).string(message.value);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserLocationRequest_DetailsEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateUserLocationRequest_DetailsEntry();
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
