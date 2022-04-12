/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export enum ProfileAccessStrategy {
  ALL = 0,
  ALL_EXCEPT_BLOCKED_USERS = 1,
  FRIENDS = 2,
  UNRECOGNIZED = -1,
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
