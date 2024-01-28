/* eslint-disable */
import Long from "long";
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface GroupJoinQuestion {
  id?: string | undefined;
  groupId?: string | undefined;
  question?: string | undefined;
  answers: string[];
  score?: number | undefined;
}

function createBaseGroupJoinQuestion(): GroupJoinQuestion {
  return { id: undefined, groupId: undefined, question: undefined, answers: [], score: undefined };
}

export const GroupJoinQuestion = {
  encode(message: GroupJoinQuestion, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.id !== undefined) {
      writer.uint32(8).int64(message.id);
    }
    if (message.groupId !== undefined) {
      writer.uint32(16).int64(message.groupId);
    }
    if (message.question !== undefined) {
      writer.uint32(26).string(message.question);
    }
    for (const v of message.answers) {
      writer.uint32(34).string(v!);
    }
    if (message.score !== undefined) {
      writer.uint32(40).int32(message.score);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): GroupJoinQuestion {
    const reader = input instanceof _m0.Reader ? input : _m0.Reader.create(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseGroupJoinQuestion();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          if (tag !== 8) {
            break;
          }

          message.id = longToString(reader.int64() as Long);
          continue;
        case 2:
          if (tag !== 16) {
            break;
          }

          message.groupId = longToString(reader.int64() as Long);
          continue;
        case 3:
          if (tag !== 26) {
            break;
          }

          message.question = reader.string();
          continue;
        case 4:
          if (tag !== 34) {
            break;
          }

          message.answers.push(reader.string());
          continue;
        case 5:
          if (tag !== 40) {
            break;
          }

          message.score = reader.int32();
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