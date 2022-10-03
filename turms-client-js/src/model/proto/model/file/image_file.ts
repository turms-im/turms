/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface ImageFile {
  description?: ImageFile_Description | undefined;
  data?: Uint8Array | undefined;
}

export interface ImageFile_Description {
  url: string;
  original?: boolean | undefined;
  imageSize?: number | undefined;
  fileSize?: number | undefined;
}

function createBaseImageFile(): ImageFile {
  return { description: undefined, data: undefined };
}

export const ImageFile = {
  encode(message: ImageFile, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.description !== undefined) {
      ImageFile_Description.encode(message.description, writer.uint32(10).fork()).ldelim();
    }
    if (message.data !== undefined) {
      writer.uint32(18).bytes(message.data);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): ImageFile {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseImageFile();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.description = ImageFile_Description.decode(reader, reader.uint32());
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

function createBaseImageFile_Description(): ImageFile_Description {
  return { url: "", original: undefined, imageSize: undefined, fileSize: undefined };
}

export const ImageFile_Description = {
  encode(message: ImageFile_Description, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.url !== "") {
      writer.uint32(10).string(message.url);
    }
    if (message.original !== undefined) {
      writer.uint32(16).bool(message.original);
    }
    if (message.imageSize !== undefined) {
      writer.uint32(24).int32(message.imageSize);
    }
    if (message.fileSize !== undefined) {
      writer.uint32(32).int32(message.fileSize);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): ImageFile_Description {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseImageFile_Description();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.url = reader.string();
          break;
        case 2:
          message.original = reader.bool();
          break;
        case 3:
          message.imageSize = reader.int32();
          break;
        case 4:
          message.fileSize = reader.int32();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },
};
