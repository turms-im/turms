// Code generated by protoc-gen-ts_proto. DO NOT EDIT.
// versions:
//   protoc-gen-ts_proto  v2.6.1
//   protoc               v5.29.1
// source: request/user/relationship/create_friend_request_request.proto

/* eslint-disable */
import { BinaryReader, BinaryWriter } from "@bufbuild/protobuf/wire";
import { Value } from "../../../model/common/value";

export const protobufPackage = "im.turms.proto";

export interface CreateFriendRequestRequest {
  recipientId: string;
  content: string;
  customAttributes: Value[];
}

function createBaseCreateFriendRequestRequest(): CreateFriendRequestRequest {
  return { recipientId: "0", content: "", customAttributes: [] };
}

export const CreateFriendRequestRequest: MessageFns<CreateFriendRequestRequest> = {
  encode(message: CreateFriendRequestRequest, writer: BinaryWriter = new BinaryWriter()): BinaryWriter {
    if (message.recipientId !== "0") {
      writer.uint32(8).int64(message.recipientId);
    }
    if (message.content !== "") {
      writer.uint32(18).string(message.content);
    }
    for (const v of message.customAttributes) {
      Value.encode(v!, writer.uint32(122).fork()).join();
    }
    return writer;
  },

  decode(input: BinaryReader | Uint8Array, length?: number): CreateFriendRequestRequest {
    const reader = input instanceof BinaryReader ? input : new BinaryReader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseCreateFriendRequestRequest();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1: {
          if (tag !== 8) {
            break;
          }

          message.recipientId = reader.int64().toString();
          continue;
        }
        case 2: {
          if (tag !== 18) {
            break;
          }

          message.content = reader.string();
          continue;
        }
        case 15: {
          if (tag !== 122) {
            break;
          }

          message.customAttributes.push(Value.decode(reader, reader.uint32()));
          continue;
        }
      }
      if ((tag & 7) === 4 || tag === 0) {
        break;
      }
      reader.skip(tag & 7);
    }
    return message;
  },
};

export interface MessageFns<T> {
  encode(message: T, writer?: BinaryWriter): BinaryWriter;
  decode(input: BinaryReader | Uint8Array, length?: number): T;
}