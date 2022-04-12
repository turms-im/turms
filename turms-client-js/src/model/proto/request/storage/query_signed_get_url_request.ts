/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";
import { ContentType } from "../../constant/content_type";

export const protobufPackage = "im.turms.proto";

export interface QuerySignedGetUrlRequest {
  contentType: ContentType;
  keyStr?: string | undefined;
  keyNum?: string | undefined;
}

function createBaseQuerySignedGetUrlRequest(): QuerySignedGetUrlRequest {
  return { contentType: 0, keyStr: undefined, keyNum: undefined };
}

export const QuerySignedGetUrlRequest = {
  encode(
    message: QuerySignedGetUrlRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.contentType !== 0) {
      writer.uint32(8).int32(message.contentType);
    }
    if (message.keyStr !== undefined) {
      writer.uint32(18).string(message.keyStr);
    }
    if (message.keyNum !== undefined) {
      writer.uint32(24).int64(message.keyNum);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): QuerySignedGetUrlRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQuerySignedGetUrlRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.contentType = reader.int32() as any;
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
