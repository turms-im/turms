#ifndef TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H
#define TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H

namespace turms {
namespace client {
namespace model {

namespace SessionCloseStatus {
const int kIllegalRequest = 100;
const int kHeartbeatTimeout = 110;
const int kLoginTimeout = 111;
const int kSwitch = 112;

const int kServerError = 200;
const int kServerClosed = 201;
const int kServerUnavailable = 202;

const int kConnectionClosed = 300;

const int kUnknownError = 400;

const int kDisconnectedByClient = 500;
const int kDisconnectedByOtherDevice = 501;

const int kDisconnectedByAdmin = 600;

const int kUserIsDeletedOrInactivated = 700;
const int kUserIsBlocked = 701;
}  // namespace SessionCloseStatus

}  // namespace model
}  // namespace client
}  // namespace turms

#endif  // TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H