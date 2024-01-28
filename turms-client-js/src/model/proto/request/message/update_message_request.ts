/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateMessageRequest {
  /** Query filter */
  messageId: string;
  /** Update */
  text?: string | undefined;
  records: Uint8Array[];
  recallDate?: string | undefined;
}

function createBaseUpdateMessageRequest(): UpdateMessageRequest {
  return { messageId: "0", text: undefined, records: [], recallDate: undefined };
}

export const UpdateMessageRequest = {
  encode(message: UpdateMessageRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.messageId !== "0") {
      writer.uint32(8).int64(message.messageId);
    }
    if (message.text !== undefined) {
      writer.uint32(18).string(message.text);
    }
    for (const v of message.records) {
      writer.uint32(26).bytes(v!);
    }
    if (message.recallDate !== undefined) {
      writer.uint32(32).int64(message.recallDate);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateMessageRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateMessageRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.messageId = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.text = reader.string();
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.records.push(reader.bytes());
          continue;
        case 4:
          if (tag !== 32) {
            break;
          }

          message.recallDate = longToString(reader.int64() as Long);
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