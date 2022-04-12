/* eslint-disable */
import Long from "long";
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "im.turms.proto";

export enum RequestStatus {
  PENDING = 0,
  ACCEPTED = 1,
  ACCEPTED_WITHOUT_CONFIRM = 2,
  DECLINED = 3,
  IGNORED = 4,
  EXPIRED = 5,
  CANCELED = 6,
  UNRECOGNIZED = -1,
}

if (_m0.util.Long !== Long) {
  _m0.util.Long = Long as any;
  _m0.configure();
}
