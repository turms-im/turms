/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { StorageResourceType } from "../../constant/storage_resource_type";

export const protobufPackage = "im.turms.proto";

export interface DeleteResourceRequest {
  type: StorageResourceType;
  keyStr?: string | undefined;
  keyNum?: string | undefined;
}

function createBaseDeleteResourceRequest(): DeleteResourceRequest {
  return { type: 0, keyStr: undefined, keyNum: undefined };
}

export const DeleteResourceRequest = {
  encode(message: DeleteResourceRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.type !== 0) {
      writer.uint32(8).int32(message.type);
    }
    if (message.keyStr !== undefined) {
      writer.uint32(18).string(message.keyStr);
    }
    if (message.keyNum !== undefined) {
      writer.uint32(24).int64(message.keyNum);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteResourceRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteResourceRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.type = reader.int32() as any;
          break;
        case 2:
          message.keyStr = reader.string();
          break;
        case 3:
          message.keyNum = longToString(reader.int64() as Long);
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
