/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export enum GroupMemberRole {
  OWNER = 0,
  MANAGER = 1,
  MEMBER = 2,
  GUEST = 3,
  ANONYMOUS_GUEST = 4,
  UNRECOGNIZED = -1,
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
