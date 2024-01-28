/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { ResponseAction } from "../../../constant/response_action";

export const protobufPackage = "im.turms.proto";

export interface UpdateFriendRequestRequest {
  /** Query filter */
  requestId: string;
  /** Update */
  responseAction: ResponseAction;
  reason?: string | undefined;
}

function createBaseUpdateFriendRequestRequest(): UpdateFriendRequestRequest {
  return { requestId: "0", responseAction: 0, reason: undefined };
}

export const UpdateFriendRequestRequest = {
  encode(message: UpdateFriendRequestRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.requestId !== "0") {
      writer.uint32(8).int64(message.requestId);
    }
    if (message.responseAction !== 0) {
      writer.uint32(16).int32(message.responseAction);
    }
    if (message.reason !== undefined) {
      writer.uint32(26).string(message.reason);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateFriendRequestRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateFriendRequestRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.requestId = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.responseAction = reader.int32() as any;
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.reason = reader.string();
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

function longToString(long: Long) {
  return long.toString();
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}