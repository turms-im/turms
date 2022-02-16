/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateGroupRequest {
  groupId: string;
  groupName?: string | undefined;
  intro?: string | undefined;
  announcement?: string | undefined;
  minimumScore?: number | undefined;
  groupTypeId?: string | undefined;
  muteEndDate?: string | undefined;
  successorId?: string | undefined;
  quitAfterTransfer?: boolean | undefined;
}

function createBaseUpdateGroupRequest(): UpdateGroupRequest {
  return {
    groupId: "0",
    groupName: undefined,
    intro: undefined,
    announcement: undefined,
    minimumScore: undefined,
    groupTypeId: undefined,
    muteEndDate: undefined,
    successorId: undefined,
    quitAfterTransfer: undefined,
  };
}

export const UpdateGroupRequest = {
  encode(
    message: UpdateGroupRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.groupName !== undefined) {
      writer.uint32(18).string(message.groupName);
    }
    if (message.intro !== undefined) {
      writer.uint32(26).string(message.intro);
    }
    if (message.announcement !== undefined) {
      writer.uint32(34).string(message.announcement);
    }
    if (message.minimumScore !== undefined) {
      writer.uint32(40).int32(message.minimumScore);
    }
    if (message.groupTypeId !== undefined) {
      writer.uint32(48).int64(message.groupTypeId);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(56).int64(message.muteEndDate);
    }
    if (message.successorId !== undefined) {
      writer.uint32(64).int64(message.successorId);
    }
    if (message.quitAfterTransfer !== undefined) {
      writer.uint32(72).bool(message.quitAfterTransfer);
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
          message.groupName = reader.string();
          break;
        case 3:
          message.intro = reader.string();
          break;
        case 4:
          message.announcement = reader.string();
          break;
        case 5:
          message.minimumScore = reader.int32();
          break;
        case 6:
          message.groupTypeId = longToString(reader.int64() as Long);
          break;
        case 7:
          message.muteEndDate = longToString(reader.int64() as Long);
          break;
        case 8:
          message.successorId = longToString(reader.int64() as Long);
          break;
        case 9:
          message.quitAfterTransfer = reader.bool();
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
