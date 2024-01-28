/* eslint-disable */
import _m0 from "protobufjs/minimal";
import { ProfileAccessStrategy } from "../../constant/profile_access_strategy";

export const protobufPackage = "im.turms.proto";

export interface UpdateUserRequest {
  /** Update */
  password?: string | undefined;
  name?: string | undefined;
  intro?: string | undefined;
  profilePicture?: string | undefined;
  profileAccessStrategy?: ProfileAccessStrategy | undefined;
}

function createBaseUpdateUserRequest(): UpdateUserRequest {
  return {
    password: undefined,
    name: undefined,
    intro: undefined,
    profilePicture: undefined,
    profileAccessStrategy: undefined,
  };
}

export const UpdateUserRequest = {
  encode(message: UpdateUserRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.password !== undefined) {
      writer.uint32(10).string(message.password);
    }
    if (message.name !== undefined) {
      writer.uint32(18).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(26).string(message.intro);
    }
    if (message.profilePicture !== undefined) {
      writer.uint32(34).string(message.profilePicture);
    }
    if (message.profileAccessStrategy !== undefined) {
      writer.uint32(40).int32(message.profileAccessStrategy);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateUserRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateUserRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          message.password = reader.string();
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.name = reader.string();
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.intro = reader.string();
          continue;
        case 4:
          if (tag !== 34) {
            break;
          }

          message.profilePicture = reader.string();
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.profileAccessStrategy = reader.int32() as any;
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