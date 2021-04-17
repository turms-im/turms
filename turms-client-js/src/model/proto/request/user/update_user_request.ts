/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { ProfileAccessStrategy } from "../../constant/profile_access_strategy";

export const protobufPackage = "im.turms.proto";

export interface UpdateUserRequest {
  password?: string | undefined;
  name?: string | undefined;
  intro?: string | undefined;
  profileAccessStrategy?: ProfileAccessStrategy | undefined;
}

const baseUpdateUserRequest: object = {};

export const UpdateUserRequest = {
  encode(
    message: UpdateUserRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.password !== undefined) {
      writer.uint32(10).string(message.password);
    }
    if (message.name !== undefined) {
      writer.uint32(18).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(26).string(message.intro);
    }
    if (message.profileAccessStrategy !== undefined) {
      writer.uint32(32).int32(message.profileAccessStrategy);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = { ...baseUpdateUserRequest } as UpdateUserRequest;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.password = reader.string();
          break;
        case 2:
          message.name = reader.string();
          break;
        case 3:
          message.intro = reader.string();
          break;
        case 4:
          message.profileAccessStrategy = reader.int32() as any;
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
