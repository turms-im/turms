/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface CheckGroupJoinQuestionsAnswersRequest {
  questionIdAndAnswer: { [key: string]: string };
}

export interface CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry {
  key: string;
  value: string;
}

const baseCheckGroupJoinQuestionsAnswersRequest: object = {};

export const CheckGroupJoinQuestionsAnswersRequest = {
  encode(
    message: CheckGroupJoinQuestionsAnswersRequest,
    writer: _m0.Writer = _m0.Writer.create()
  ): _m0.Writer {
    Object.entries(message.questionIdAndAnswer).forEach(([key, value]) => {
      CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry.encode(
        { key: key as any, value },
        writer.uint32(10).fork()
      ).ldelim();
    });
    return writer;
  },

  decode(
    input: _m0.Reader | Uint8Array,
    length?: number
  ): CheckGroupJoinQuestionsAnswersRequest {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseCheckGroupJoinQuestionsAnswersRequest,
    } as CheckGroupJoinQuestionsAnswersRequest;
    message.questionIdAndAnswer = {};
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          const entry1 = CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry.decode(
            reader,
            reader.uint32()
          );
          if (entry1.value !== undefined) {
            message.questionIdAndAnswer[entry1.key] = entry1.value;
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

const baseCheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry: object = {
  key: "0",
  value: "",
};

export const CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry = {
  encode(
    message: CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry,
    writer: _m0.Writer = _m0.Writer.create()
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
    length?: number
  ): CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = {
      ...baseCheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry,
    } as CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry;
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
