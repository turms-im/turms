/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryNearbyUsersRequest {
  latitude: number;
  longitude: number;
  maxCount?: number | undefined;
  maxDistance?: number | undefined;
  withCoordinates?: boolean | undefined;
  withDistance?: boolean | undefined;
  withUserInfo?: boolean | undefined;
}

function createBaseQueryNearbyUsersRequest(): QueryNearbyUsersRequest {
  return {
    latitude: 0,
    longitude: 0,
    maxCount: undefined,
    maxDistance: undefined,
    withCoordinates: undefined,
    withDistance: undefined,
    withUserInfo: undefined,
  };
}

export const QueryNearbyUsersRequest = {
  encode(message: QueryNearbyUsersRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.latitude !== 0) {
      writer.uint32(13).float(message.latitude);
    }
    if (message.longitude !== 0) {
      writer.uint32(21).float(message.longitude);
    }
    if (message.maxCount !== undefined) {
      writer.uint32(24).int32(message.maxCount);
    }
    if (message.maxDistance !== undefined) {
      writer.uint32(32).int32(message.maxDistance);
    }
    if (message.withCoordinates !== undefined) {
      writer.uint32(40).bool(message.withCoordinates);
    }
    if (message.withDistance !== undefined) {
      writer.uint32(48).bool(message.withDistance);
    }
    if (message.withUserInfo !== undefined) {
      writer.uint32(56).bool(message.withUserInfo);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryNearbyUsersRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryNearbyUsersRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 13) {
            break;
          }

          message.latitude = reader.float();
          continue;
        case 2:
          if (tag !== 21) {
            break;
          }

          message.longitude = reader.float();
          continue;
        case 3:
          if (tag !== 24) {
            break;
          }

          message.maxCount = reader.int32();
          continue;
        case 4:
          if (tag !== 32) {
            break;
          }

          message.maxDistance = reader.int32();
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.withCoordinates = reader.bool();
          continue;
        case 6:
          if (tag !== 48) {
            break;
          }

          message.withDistance = reader.bool();
          continue;
        case 7:
          if (tag !== 56) {
            break;
          }

          message.withUserInfo = reader.bool();
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