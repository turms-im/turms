/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface File {
  description?: File_Description | undefined;
  data?: Uint8Array | undefined;
}

export interface File_Description {
  url: string;
  size?: number | undefined;
  format?: string | undefined;
}

function createBaseFile(): File {
  return { description: undefined, data: undefined };
}

export const File = {
  encode(message: File, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.description !== undefined) {
      File_Description.encode(message.description, writer.uint32(10).fork()).ldelim();
    }
    if (message.data !== undefined) {
      writer.uint32(18).bytes(message.data);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): File {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseFile();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.description = File_Description.decode(reader, reader.uint32());
          break;
        case 2:
          message.data = reader.bytes();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};

function createBaseFile_Description(): File_Description {
  return { url: "", size: undefined, format: undefined };
}

export const File_Description = {
  encode(message: File_Description, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.url !== "") {
      writer.uint32(10).string(message.url);
    }
    if (message.size !== undefined) {
      writer.uint32(16).int32(message.size);
    }
    if (message.format !== undefined) {
      writer.uint32(26).string(message.format);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): File_Description {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseFile_Description();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.url = reader.string();
          break;
        case 2:
          message.size = reader.int32();
          break;
        case 3:
          message.format = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
