/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CreateGroupJoinQuestionRequest {
  groupId: string;
  question: string;
  answers: string[];
  score: number;
}

function createBaseCreateGroupJoinQuestionRequest(): CreateGroupJoinQuestionRequest {
  return { groupId: "0", question: "", answers: [], score: 0 };
}

export const CreateGroupJoinQuestionRequest = {
  encode(message: CreateGroupJoinQuestionRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    if (message.question !== "") {
      writer.uint32(18).string(message.question);
    }
    for (const v of message.answers) {
      writer.uint32(26).string(v!);
    }
    if (message.score !== 0) {
      writer.uint32(32).int32(message.score);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateGroupJoinQuestionRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateGroupJoinQuestionRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.question = reader.string();
          break;
        case 3:
          message.answers.push(reader.string());
          break;
        case 4:
          message.score = reader.int32();
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
