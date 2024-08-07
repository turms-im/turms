// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v1.181.1
//   protoc               v5.27.2
// source: model/common/value.proto

/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface Value {
  int32Value?: number | undefined;
  int64Value?: string | undefined;
  floatValue?: number | undefined;
  doubleValue?: number | undefined;
  boolValue?: boolean | undefined;
  bytesValue?: Uint8Array | undefined;
  stringValue?: string | undefined;
  listValue: Value[];
}

function createBaseValue(): Value {
  return {
    int32Value: undefined,
    int64Value: undefined,
    floatValue: undefined,
    doubleValue: undefined,
    boolValue: undefined,
    bytesValue: undefined,
    stringValue: undefined,
    listValue: [],
  };
}

export const Value = {
  encode(message: Value, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.int32Value !== undefined) {
      writer.uint32(8).int32(message.int32Value);
    }
    if (message.int64Value !== undefined) {
      writer.uint32(16).int64(message.int64Value);
    }
    if (message.floatValue !== undefined) {
      writer.uint32(29).float(message.floatValue);
    }
    if (message.doubleValue !== undefined) {
      writer.uint32(33).double(message.doubleValue);
    }
    if (message.boolValue !== undefined) {
      writer.uint32(40).bool(message.boolValue);
    }
    if (message.bytesValue !== undefined) {
      writer.uint32(50).bytes(message.bytesValue);
    }
    if (message.stringValue !== undefined) {
      writer.uint32(58).string(message.stringValue);
    }
    for (const v of message.listValue) {
      Value.encode(v!, writer.uint32(66).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): Value {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseValue();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.int32Value = reader.int32();
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.int64Value = longToString(reader.int64() as Long);
          continue;
        case 3:
          if (tag !== 29) {
            break;
          }

          message.floatValue = reader.float();
          continue;
        case 4:
          if (tag !== 33) {
            break;
          }

          message.doubleValue = reader.double();
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.boolValue = reader.bool();
          continue;
        case 6:
          if (tag !== 50) {
            break;
          }

          message.bytesValue = reader.bytes();
          continue;
        case 7:
          if (tag !== 58) {
            break;
          }

          message.stringValue = reader.string();
          continue;
        case 8:
          if (tag !== 66) {
            break;
          }

          message.listValue.push(Value.decode(reader, reader.uint32()));
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
