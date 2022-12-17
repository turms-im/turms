/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface StorageResourceInfo {
  idNum?: string | undefined;
  idStr?: string | undefined;
  name?: string | undefined;
  mediaType?: string | undefined;
  uploaderId: string;
  creationDate: string;
}

function createBaseStorageResourceInfo(): StorageResourceInfo {
  return {
    idNum: undefined,
    idStr: undefined,
    name: undefined,
    mediaType: undefined,
    uploaderId: "0",
    creationDate: "0",
  };
}

export const StorageResourceInfo = {
  encode(message: StorageResourceInfo, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.idNum !== undefined) {
      writer.uint32(8).int64(message.idNum);
    }
    if (message.idStr !== undefined) {
      writer.uint32(18).string(message.idStr);
    }
    if (message.name !== undefined) {
      writer.uint32(26).string(message.name);
    }
    if (message.mediaType !== undefined) {
      writer.uint32(34).string(message.mediaType);
    }
    if (message.uploaderId !== "0") {
      writer.uint32(40).int64(message.uploaderId);
    }
    if (message.creationDate !== "0") {
      writer.uint32(48).int64(message.creationDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): StorageResourceInfo {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseStorageResourceInfo();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.idNum = longToString(reader.int64() as Long);
          break;
        case 2:
          message.idStr = reader.string();
          break;
        case 3:
          message.name = reader.string();
          break;
        case 4:
          message.mediaType = reader.string();
          break;
        case 5:
          message.uploaderId = longToString(reader.int64() as Long);
          break;
        case 6:
          message.creationDate = longToString(reader.int64() as Long);
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
