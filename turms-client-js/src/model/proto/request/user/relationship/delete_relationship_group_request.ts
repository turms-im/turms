/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface DeleteRelationshipGroupRequest {
  groupIndex: number;
  targetGroupIndex?: number | undefined;
}

function createBaseDeleteRelationshipGroupRequest(): DeleteRelationshipGroupRequest {
  return { groupIndex: 0, targetGroupIndex: undefined };
}

export const DeleteRelationshipGroupRequest = {
  encode(message: DeleteRelationshipGroupRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupIndex !== 0) {
      writer.uint32(8).int32(message.groupIndex);
    }
    if (message.targetGroupIndex !== undefined) {
      writer.uint32(16).int32(message.targetGroupIndex);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteRelationshipGroupRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteRelationshipGroupRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupIndex = reader.int32();
          break;
        case 2:
          message.targetGroupIndex = reader.int32();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
