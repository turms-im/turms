/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface UpdateRelationshipGroupRequest {
  /** Query filter */
  groupIndex: number;
  /** Update */
  newName: string;
}

function createBaseUpdateRelationshipGroupRequest(): UpdateRelationshipGroupRequest {
  return { groupIndex: 0, newName: "" };
}

export const UpdateRelationshipGroupRequest = {
  encode(message: UpdateRelationshipGroupRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupIndex !== 0) {
      writer.uint32(8).int32(message.groupIndex);
    }
    if (message.newName !== "") {
      writer.uint32(18).string(message.newName);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): UpdateRelationshipGroupRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseUpdateRelationshipGroupRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.groupIndex = reader.int32();
          continue;
        case 2:
          if (tag !== 18) {
            break;
          }

          message.newName = reader.string();
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