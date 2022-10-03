/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface GroupJoinQuestionsAnswerResult {
  score: number;
  questionIds: string[];
  joined: boolean;
}

function createBaseGroupJoinQuestionsAnswerResult(): GroupJoinQuestionsAnswerResult {
  return { score: 0, questionIds: [], joined: false };
}

export const GroupJoinQuestionsAnswerResult = {
  encode(message: GroupJoinQuestionsAnswerResult, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.score !== 0) {
      writer.uint32(8).int32(message.score);
    }
    writer.uint32(18).fork();
    for (const v of message.questionIds) {
      writer.int64(v);
    }
    writer.ldelim();
    if (message.joined === true) {
      writer.uint32(24).bool(message.joined);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupJoinQuestionsAnswerResult {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupJoinQuestionsAnswerResult();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.score = reader.int32();
          break;
        case 2:
          if ((tag & 7) === 2) {
            const end2 = reader.uint32() + reader.pos;
            while (reader.pos < end2) {
              message.questionIds.push(longToString(reader.int64() as Long));
            }
          } else {
            message.questionIds.push(longToString(reader.int64() as Long));
          }
          break;
        case 3:
          message.joined = reader.bool();
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
