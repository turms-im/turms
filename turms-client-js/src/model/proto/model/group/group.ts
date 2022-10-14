/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface Group {
  id?: string | undefined;
  typeId?: string | undefined;
  creatorId?: string | undefined;
  ownerId?: string | undefined;
  name?: string | undefined;
  intro?: string | undefined;
  announcement?: string | undefined;
  creationDate?: string | undefined;
  lastUpdatedDate?: string | undefined;
  muteEndDate?: string | undefined;
  active?: boolean | undefined;
}

function createBaseGroup(): Group {
  return {
    id: undefined,
    typeId: undefined,
    creatorId: undefined,
    ownerId: undefined,
    name: undefined,
    intro: undefined,
    announcement: undefined,
    creationDate: undefined,
    lastUpdatedDate: undefined,
    muteEndDate: undefined,
    active: undefined,
  };
}

export const Group = {
  encode(message: Group, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.id !== undefined) {
      writer.uint32(8).int64(message.id);
    }
    if (message.typeId !== undefined) {
      writer.uint32(16).int64(message.typeId);
    }
    if (message.creatorId !== undefined) {
      writer.uint32(24).int64(message.creatorId);
    }
    if (message.ownerId !== undefined) {
      writer.uint32(32).int64(message.ownerId);
    }
    if (message.name !== undefined) {
      writer.uint32(42).string(message.name);
    }
    if (message.intro !== undefined) {
      writer.uint32(50).string(message.intro);
    }
    if (message.announcement !== undefined) {
      writer.uint32(58).string(message.announcement);
    }
    if (message.creationDate !== undefined) {
      writer.uint32(64).int64(message.creationDate);
    }
    if (message.lastUpdatedDate !== undefined) {
      writer.uint32(72).int64(message.lastUpdatedDate);
    }
    if (message.muteEndDate !== undefined) {
      writer.uint32(80).int64(message.muteEndDate);
    }
    if (message.active !== undefined) {
      writer.uint32(88).bool(message.active);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): Group {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroup();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.id = longToString(reader.int64() as Long);
          break;
        case 2:
          message.typeId = longToString(reader.int64() as Long);
          break;
        case 3:
          message.creatorId = longToString(reader.int64() as Long);
          break;
        case 4:
          message.ownerId = longToString(reader.int64() as Long);
          break;
        case 5:
          message.name = reader.string();
          break;
        case 6:
          message.intro = reader.string();
          break;
        case 7:
          message.announcement = reader.string();
          break;
        case 8:
          message.creationDate = longToString(reader.int64() as Long);
          break;
        case 9:
          message.lastUpdatedDate = longToString(reader.int64() as Long);
          break;
        case 10:
          message.muteEndDate = longToString(reader.int64() as Long);
          break;
        case 11:
          message.active = reader.bool();
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
