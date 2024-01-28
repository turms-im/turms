/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface DeleteRelationshipGroupMemberRequest {
  userId: string;
  groupIndex: number;
  targetGroupIndex?: number | undefined;
}

function createBaseDeleteRelationshipGroupMemberRequest(): DeleteRelationshipGroupMemberRequest {
  return { userId: "0", groupIndex: 0, targetGroupIndex: undefined };
}

export const DeleteRelationshipGroupMemberRequest = {
  encode(message: DeleteRelationshipGroupMemberRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "0") {
      writer.uint32(8).int64(message.userId);
    }
    if (message.groupIndex !== 0) {
      writer.uint32(16).int32(message.groupIndex);
    }
    if (message.targetGroupIndex !== undefined) {
      writer.uint32(24).int32(message.targetGroupIndex);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): DeleteRelationshipGroupMemberRequest {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseDeleteRelationshipGroupMemberRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.userId = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.groupIndex = reader.int32();
          continue;
        case 3:
          if (tag !== 24) {
            break;
          }

          message.targetGroupIndex = reader.int32();
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