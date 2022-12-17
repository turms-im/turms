/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CreateGroupRequest {
  name: string;
  intro?: string | undefined;
  announcement?: string | undefined;
  minScore?: number | undefined;
  typeId?: string | undefined;
  muteEndDate?: string | undefined;
}

function createBaseCreateGroupRequest(): CreateGroupRequest {
  return {
    name: "",
    intro: undefined,
    announcement: undefined,
    minScore: undefined,
    typeId: undefined,
    muteEndDate: undefined,
  };
}

export const CreateGroupRequest = {
  encode(message: CreateGroupRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.name !== "") {
      writer.uint32(10).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(18).string(message.intro);
    }
    if (message.announcement !== undefined) {
      writer.uint32(26).string(message.announcement);
    }
    if (message.minScore !== undefined) {
      writer.uint32(32).int32(message.minScore);
    }
    if (message.typeId !== undefined) {
      writer.uint32(40).int64(message.typeId);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(48).int64(message.muteEndDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateGroupRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateGroupRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.name = reader.string();
          break;
        case 2:
          message.intro = reader.string();
          break;
        case 3:
          message.announcement = reader.string();
          break;
        case 4:
          message.minScore = reader.int32();
          break;
        case 5:
          message.typeId = longToString(reader.int64() as Long);
          break;
        case 6:
          message.muteEndDate = longToString(reader.int64() as Long);
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
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
