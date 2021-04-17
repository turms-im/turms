/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryUserInfosNearbyRequest {
  latitude: number;
  longitude: number;
  distance?: number | undefined;
  maxNumber?: number | undefined;
}

const baseQueryUserInfosNearbyRequest: object = { latitude: 0, longitude: 0 };

export const QueryUserInfosNearbyRequest = {
  encode(
    message: QueryUserInfosNearbyRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.latitude !== 0) {
      writer.uint32(13).float(message.latitude);
    }
    if (message.longitude !== 0) {
      writer.uint32(21).float(message.longitude);
    }
    if (message.distance !== undefined) {
      writer.uint32(29).float(message.distance);
    }
    if (message.maxNumber !== undefined) {
      writer.uint32(32).int32(message.maxNumber);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): QueryUserInfosNearbyRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseQueryUserInfosNearbyRequest,
    } as QueryUserInfosNearbyRequest;
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
          message.distance = reader.float();
          break;
        case 4:
          message.maxNumber = reader.int32();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
