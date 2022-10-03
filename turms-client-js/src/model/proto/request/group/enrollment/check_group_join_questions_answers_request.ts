/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CheckGroupJoinQuestionsAnswersRequest {
  questionIdToAnswer: { [key: string]: string };
}

export interface CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry {
  key: string;
  value: string;
}

function createBaseCheckGroupJoinQuestionsAnswersRequest(): CheckGroupJoinQuestionsAnswersRequest {
  return { questionIdToAnswer: {} };
}

export const CheckGroupJoinQuestionsAnswersRequest = {
  encode(message: CheckGroupJoinQuestionsAnswersRequest, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    Object.entries(message.questionIdToAnswer).forEach(([key, value]) => {
      CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry.encode(
        { key: key as any, value },
        writer.uint32(10).fork(),
      ).ldelim();
    });
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): CheckGroupJoinQuestionsAnswersRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCheckGroupJoinQuestionsAnswersRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          const entry1 = CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry.decode(reader, reader.uint32());
          if (entry1.value !== undefined) {
            message.questionIdToAnswer[entry1.key] = entry1.value;
          }
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function createBaseCheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry(): CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry {
  return { key: "0", value: "" };
}

export const CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry = {
  encode(
    message: CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry,
    writer: _m0.Writer = _m0.Writer.create(),
  ): _m0.Writer {
    if (message.key !== "0") {
      writer.uint32(8).int64(message.key);
    }
    if (message.value !== "") {
      writer.uint32(18).string(message.value);
    }
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number,
  ): CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.key = longToString(reader.int64() as Long);
          break;
        case 2:
          message.value = reader.string();
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
