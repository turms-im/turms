/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { UserStatus } from "../../constant/user_status";

export const protobufPackage = "im.turms.proto";

export interface UpdateUserOnlineStatusRequest {
  userStatus: UserStatus;
  deviceTypes: DeviceType[];
}

function createBaseUpdateUserOnlineStatusRequest(): UpdateUserOnlineStatusRequest {
  return { userStatus: 0, deviceTypes: [] };
}

export const UpdateUserOnlineStatusRequest = {
  encode(message: UpdateUserOnlineStatusRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userStatus !== 0) {
      writer.uint32(8).int32(message.userStatus);
    }
    writer.uint32(18).fork();
    for (const v of message.deviceTypes) {
      writer.int32(v);
    }
    writer.ldelim();
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserOnlineStatusRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateUserOnlineStatusRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userStatus = reader.int32() as any;
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.deviceTypes.push(reader.int32() as any);
            }
          } else {
            message.deviceTypes.push(reader.int32() as any);
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
