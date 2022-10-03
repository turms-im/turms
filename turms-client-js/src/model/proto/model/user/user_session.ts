/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UserSession {
  sessionId: string;
  serverId: string;
}

function createBaseUserSession(): UserSession {
  return { sessionId: "", serverId: "" };
}

export const UserSession = {
  encode(message: UserSession, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.sessionId !== "") {
      writer.uint32(10).string(message.sessionId);
    }
    if (message.serverId !== "") {
      writer.uint32(18).string(message.serverId);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UserSession {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUserSession();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.sessionId = reader.string();
          break;
        case 2:
          message.serverId = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
