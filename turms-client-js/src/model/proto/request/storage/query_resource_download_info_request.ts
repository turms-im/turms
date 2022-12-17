/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { StorageResourceType } from "../../constant/storage_resource_type";

export const protobufPackage = "im.turms.proto";

export interface QueryResourceDownloadInfoRequest {
  type: StorageResourceType;
  idNum?: string | undefined;
  idStr?: string | undefined;
  extra: { [key: string]: string };
}

export interface QueryResourceDownloadInfoRequest_ExtraEntry {
  key: string;
  value: string;
}

function createBaseQueryResourceDownloadInfoRequest(): QueryResourceDownloadInfoRequest {
  return { type: 0, idNum: undefined, idStr: undefined, extra: {} };
}

export const QueryResourceDownloadInfoRequest = {
  encode(message: QueryResourceDownloadInfoRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.type !== 0) {
      writer.uint32(8).int32(message.type);
    }
    if (message.idNum !== undefined) {
      writer.uint32(16).int64(message.idNum);
    }
    if (message.idStr !== undefined) {
      writer.uint32(26).string(message.idStr);
    }
    Object.entries(message.extra).forEach(([key, value]) => {
      QueryResourceDownloadInfoRequest_ExtraEntry.encode({ key: key as any, value }, writer.uint32(34).fork()).ldelim();
    });
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryResourceDownloadInfoRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryResourceDownloadInfoRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.type = reader.int32() as any;
          break;
        case 2:
          message.idNum = longToString(reader.int64() as Long);
          break;
        case 3:
          message.idStr = reader.string();
          break;
        case 4:
          const entry4 = QueryResourceDownloadInfoRequest_ExtraEntry.decode(reader, reader.uint32());
          if (entry4.value !== undefined) {
            message.extra[entry4.key] = entry4.value;
          }
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function createBaseQueryResourceDownloadInfoRequest_ExtraEntry(): QueryResourceDownloadInfoRequest_ExtraEntry {
  return { key: "", value: "" };
}

export const QueryResourceDownloadInfoRequest_ExtraEntry = {
  encode(message: QueryResourceDownloadInfoRequest_ExtraEntry, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.key !== "") {
      writer.uint32(10).string(message.key);
    }
    if (message.value !== "") {
      writer.uint32(18).string(message.value);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryResourceDownloadInfoRequest_ExtraEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryResourceDownloadInfoRequest_ExtraEntry();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.key = reader.string();
          break;
        case 2:
          message.value = reader.string();
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
