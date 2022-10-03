/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface VideoFile {
  description?: VideoFile_Description | undefined;
  data?: Uint8Array | undefined;
}

export interface VideoFile_Description {
  url: string;
  duration?: number | undefined;
  size?: number | undefined;
  format?: string | undefined;
}

function createBaseVideoFile(): VideoFile {
  return { description: undefined, data: undefined };
}

export const VideoFile = {
  encode(message: VideoFile, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.description !== undefined) {
      VideoFile_Description.encode(message.description, writer.uint32(10).fork()).ldelim();
    }
    if (message.data !== undefined) {
      writer.uint32(18).bytes(message.data);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): VideoFile {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseVideoFile();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.description = VideoFile_Description.decode(reader, reader.uint32());
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

function createBaseVideoFile_Description(): VideoFile_Description {
  return { url: "", duration: undefined, size: undefined, format: undefined };
}

export const VideoFile_Description = {
  encode(message: VideoFile_Description, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.url !== "") {
      writer.uint32(10).string(message.url);
    }
    if (message.duration !== undefined) {
      writer.uint32(16).int32(message.duration);
    }
    if (message.size !== undefined) {
      writer.uint32(24).int32(message.size);
    }
    if (message.format !== undefined) {
      writer.uint32(34).string(message.format);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): VideoFile_Description {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseVideoFile_Description();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.url = reader.string();
          break;
        case 2:
          message.duration = reader.int32();
          break;
        case 3:
          message.size = reader.int32();
          break;
        case 4:
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
