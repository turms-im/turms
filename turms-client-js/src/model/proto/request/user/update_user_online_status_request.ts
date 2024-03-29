/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { DeviceType } from "../../constant/device_type";
import { UserStatus } from "../../constant/user_status";

export const protobufPackage = "im.turms.proto";

export interface UpdateUserOnlineStatusRequest {
  /** Query filter */
  deviceTypes: DeviceType[];
  /** Update */
  userStatus: UserStatus;
}

function createBaseUpdateUserOnlineStatusRequest(): UpdateUserOnlineStatusRequest {
  return { deviceTypes: [], userStatus: 0 };
}

export const UpdateUserOnlineStatusRequest = {
  encode(message: UpdateUserOnlineStatusRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    writer.uint32(10).fork();
    for (const v of message.deviceTypes) {
      writer.int32(v);
    }
    writer.ldelim();
    if (message.userStatus !== 0) {
      writer.uint32(16).int32(message.userStatus);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserOnlineStatusRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateUserOnlineStatusRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag === 8) {
            message.deviceTypes.push(reader.int32() as any);

            continue;
          }

          if (tag === 10) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.deviceTypes.push(reader.int32() as any);
            }

            continue;
          }

          break;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.userStatus = reader.int32() as any;
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