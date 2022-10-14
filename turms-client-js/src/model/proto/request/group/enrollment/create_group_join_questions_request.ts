/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";
import { GroupJoinQuestion } from "../../../model/group/group_join_question";

export const protobufPackage = "im.turms.proto";

export interface CreateGroupJoinQuestionsRequest {
  groupId: string;
  questions: GroupJoinQuestion[];
}

function createBaseCreateGroupJoinQuestionsRequest(): CreateGroupJoinQuestionsRequest {
  return { groupId: "0", questions: [] };
}

export const CreateGroupJoinQuestionsRequest = {
  encode(message: CreateGroupJoinQuestionsRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.groupId !== "0") {
      writer.uint32(8).int64(message.groupId);
    }
    for (const v of message.questions) {
      GroupJoinQuestion.encode(v!, writer.uint32(18).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CreateGroupJoinQuestionsRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateGroupJoinQuestionsRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.groupId = longToString(reader.int64() as Long);
          break;
        case 2:
          message.questions.push(GroupJoinQuestion.decode(reader, reader.uint32()));
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
