/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateRelationshipGroupRequest {
  groupIndex: number;
  newName: string;
}

const baseUpdateRelationshipGroupRequest: object = {
  groupIndex: 0,
  newName: "",
};

export const UpdateRelationshipGroupRequest = {
  encode(
    message: UpdateRelationshipGroupRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    if (message.groupIndex !== 0) {
      writer.uint32(8).int32(message.groupIndex);
    }
    if (message.newName !== "") {
      writer.uint32(18).string(message.newName);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): UpdateRelationshipGroupRequest {
    const reader = input instanceof Uint8Array ? new _m0.Reader(input) : input;
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseUpdateRelationshipGroupRequest,
    } as UpdateRelationshipGroupRequest;
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupIndex = reader.int32();
          break;
        case 2:
          message.newName = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
