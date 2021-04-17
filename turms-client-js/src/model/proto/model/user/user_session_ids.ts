/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { UserSessionId } from "../../model/user/user_session_id";

export const protobufPackage = "im.turms.proto";

export interface UserSessionIds {
  userSessionIds: UserSessionId[];
}

const baseUserSessionIds: object = {};

export const UserSessionIds = {
  encode(
    message: UserSessionIds,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    for (const v of message.userSessionIds) {
      UserSessionId.encode(v!, writer.uint32(10).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserSessionIds {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseUserSessionIds } as UserSessionIds;
    message.userSessionIds = [];
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userSessionIds.push(
            UserSessionId.decode(reader, reader.uint32())
          );
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
