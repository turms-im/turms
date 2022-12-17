/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface QueryMessageAttachmentInfosRequest {
  userIds: string[];
  groupIds: string[];
  creationDateStart?: string | undefined;
  creationDateEnd?: string | undefined;
  inPrivateConversation?: boolean | undefined;
  areSharedByMe?: boolean | undefined;
}

function createBaseQueryMessageAttachmentInfosRequest(): QueryMessageAttachmentInfosRequest {
  return {
    userIds: [],
    groupIds: [],
    creationDateStart: undefined,
    creationDateEnd: undefined,
    inPrivateConversation: undefined,
    areSharedByMe: undefined,
  };
}

export const QueryMessageAttachmentInfosRequest = {
  encode(message: QueryMessageAttachmentInfosRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    writer.uint32(10).fork();
    for (const v of message.userIds) {
      writer.int64(v);
    }
    writer.ldelim();
    writer.uint32(18).fork();
    for (const v of message.groupIds) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.creationDateStart !== undefined) {
      writer.uint32(24).int64(message.creationDateStart);
    }
    if (message.creationDateEnd !== undefined) {
      writer.uint32(32).int64(message.creationDateEnd);
    }
    if (message.inPrivateConversation !== undefined) {
      writer.uint32(40).bool(message.inPrivateConversation);
    }
    if (message.areSharedByMe !== undefined) {
      writer.uint32(48).bool(message.areSharedByMe);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): QueryMessageAttachmentInfosRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseQueryMessageAttachmentInfosRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.userIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.userIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.groupIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.groupIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 3:
          message.creationDateStart = longToString(reader.int64() as Long);
          break;
        case 4:
          message.creationDateEnd = longToString(reader.int64() as Long);
          break;
        case 5:
          message.inPrivateConversation = reader.bool();
          break;
        case 6:
          message.areSharedByMe = reader.bool();
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
