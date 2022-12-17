/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateGroupRequest {
  /** Query filter */
  groupId: string;
  /** Update options */
  quitAfterTransfer?:
    | boolean
    | undefined;
  /** Update */
  name?: string | undefined;
  intro?: string | undefined;
  announcement?: string | undefined;
  minScore?: number | undefined;
  typeId?: string | undefined;
  muteEndDate?: string | undefined;
  successorId?: string | undefined;
}

function createBaseUpdateGroupRequest(): UpdateGroupRequest {
  return {
    groupId: "0",
    quitAfterTransfer: undefined,
    name: undefined,
    intro: undefined,
    announcement: undefined,
    minScore: undefined,
    typeId: undefined,
    muteEndDate: undefined,
    successorId: undefined,
  };
}

export const UpdateGroupRequest = {
  encode(message: UpdateGroupRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.quitAfterTransfer !== undefined) {
      writer.uint32(16).bool(message.quitAfterTransfer);
    }
    if (message.name !== undefined) {
      writer.uint32(26).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(34).string(message.intro);
    }
    if (message.announcement !== undefined) {
      writer.uint32(42).string(message.announcement);
    }
    if (message.minScore !== undefined) {
      writer.uint32(48).int32(message.minScore);
    }
    if (message.typeId !== undefined) {
      writer.uint32(56).int64(message.typeId);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(64).int64(message.muteEndDate);
    }
    if (message.successorId !== undefined) {
      writer.uint32(72).int64(message.successorId);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateGroupRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateGroupRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.quitAfterTransfer = reader.bool();
          break;
        case 3:
          message.name = reader.string();
          break;
        case 4:
          message.intro = reader.string();
          break;
        case 5:
          message.announcement = reader.string();
          break;
        case 6:
          message.minScore = reader.int32();
          break;
        case 7:
          message.typeId = longToString(reader.int64() as Long);
          break;
        case 8:
          message.muteEndDate = longToString(reader.int64() as Long);
          break;
        case 9:
          message.successorId = longToString(reader.int64() as Long);
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
