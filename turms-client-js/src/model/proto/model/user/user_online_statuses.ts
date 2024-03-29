/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { UserOnlineStatus } from "./user_online_status";

export const protobufPackage = "im.turms.proto";

export interface UserOnlineStatuses {
  statuses: UserOnlineStatus[];
}

function createBaseUserOnlineStatuses(): UserOnlineStatuses {
  return { statuses: [] };
}

export const UserOnlineStatuses = {
  encode(message: UserOnlineStatuses, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    for (const v of message.statuses) {
      UserOnlineStatus.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserOnlineStatuses {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserOnlineStatuses();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          message.statuses.push(UserOnlineStatus.decode(reader, reader.uint32()));
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