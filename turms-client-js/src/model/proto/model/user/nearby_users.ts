/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { NearbyUser } from "./nearby_user";

export const protobufPackage = "im.turms.proto";

export interface NearbyUsers {
  nearbyUsers: NearbyUser[];
}

function createBaseNearbyUsers(): NearbyUsers {
  return { nearbyUsers: [] };
}

export const NearbyUsers = {
  encode(message: NearbyUsers, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    for (const v of message.nearbyUsers) {
      NearbyUser.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): NearbyUsers {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseNearbyUsers();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.nearbyUsers.push(NearbyUser.decode(reader, reader.uint32()));
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
