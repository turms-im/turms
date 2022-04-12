/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryMessagesRequest {
  ids: string[];
  size?: number | undefined;
  areGroupMessages?: boolean | undefined;
  areSystemMessages?: boolean | undefined;
  fromId?: string | undefined;
  deliveryDateAfter?: string | undefined;
  deliveryDateBefore?: string | undefined;
  withTotal: boolean;
}

function createBaseQueryMessagesRequest(): QueryMessagesRequest {
  return {
    ids: [],
    size: undefined,
    areGroupMessages: undefined,
    areSystemMessages: undefined,
    fromId: undefined,
    deliveryDateAfter: undefined,
    deliveryDateBefore: undefined,
    withTotal: false,
  };
}

export const QueryMessagesRequest = {
  encode(
    message: QueryMessagesRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    writer.uint32(10).fork();
    for (const v of message.ids) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.size !== undefined) {
      writer.uint32(16).int32(message.size);
    }
    if (message.areGroupMessages !== undefined) {
      writer.uint32(24).bool(message.areGroupMessages);
    }
    if (message.areSystemMessages !== undefined) {
      writer.uint32(32).bool(message.areSystemMessages);
    }
    if (message.fromId !== undefined) {
      writer.uint32(40).int64(message.fromId);
    }
    if (message.deliveryDateAfter !== undefined) {
      writer.uint32(48).int64(message.deliveryDateAfter);
    }
    if (message.deliveryDateBefore !== undefined) {
      writer.uint32(56).int64(message.deliveryDateBefore);
    }
    if (message.withTotal === true) {
      writer.uint32(64).bool(message.withTotal);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): QueryMessagesRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryMessagesRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.ids.push(longToString(reader.int64() as Long));
            }
          } else {
            message.ids.push(longToString(reader.int64() as Long));
          }
          break;
        case 2:
          message.size = reader.int32();
          break;
        case 3:
          message.areGroupMessages = reader.bool();
          break;
        case 4:
          message.areSystemMessages = reader.bool();
          break;
        case 5:
          message.fromId = longToString(reader.int64() as Long);
          break;
        case 6:
          message.deliveryDateAfter = longToString(reader.int64() as Long);
          break;
        case 7:
          message.deliveryDateBefore = longToString(reader.int64() as Long);
          break;
        case 8:
          message.withTotal = reader.bool();
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
