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
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateGroupRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 10) {
            break;
          }

          message.name = reader.string();
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.intro = reader.string();
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.announcement = reader.string();
          continue;
        case 4:
          if (tag !== 32) {
            break;
          }

          message.minScore = reader.int32();
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.typeId = longToString(reader.int64() as Long);
          continue;
        case 6:
          if (tag !== 48) {
            break;
          }

          message.muteEndDate = longToString(reader.int64() as Long);
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