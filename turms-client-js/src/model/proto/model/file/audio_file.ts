/* eslint-disable */
import _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export interface AudioFile {
  description?: AudioFile_Description | undefined;
  data?: Uint8Array | undefined;
}

export interface AudioFile_Description {
  url: string;
  duration?: number | undefined;
  size?: number | undefined;
  format?: string | undefined;
}

function createBaseAudioFile(): AudioFile {
  return { description: undefined, data: undefined };
}

export const AudioFile = {
  encode(message: AudioFile, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.description !== undefined) {
      AudioFile_Description.encode(message.description, writer.uint32(10).fork()).ldelim();
    }
    if (message.data !== undefined) {
      writer.uint32(18).bytes(message.data);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): AudioFile {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseAudioFile();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.description = AudioFile_Description.decode(reader, reader.uint32());
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

function createBaseAudioFile_Description(): AudioFile_Description {
  return { url: "", duration: undefined, size: undefined, format: undefined };
}

export const AudioFile_Description = {
  encode(message: AudioFile_Description, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
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

  decode(input: _m0.Reader | Uint8Array, length?: number): AudioFile_Description {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseAudioFile_Description();
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
