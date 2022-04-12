/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export enum UserStatus {
  AVAILABLE = 0,
  OFFLINE = 1,
  INVISIBLE = 2,
  BUSY = 3,
  DO_NOT_DISTURB = 4,
  AWAY = 5,
  BE_RIGHT_BACK = 6,
  UNRECOGNIZED = -1,
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
