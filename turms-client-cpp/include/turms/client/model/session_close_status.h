#ifndef TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H
#define TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H

namespace turms::client::model::SessionCloseStatus {
constexpr int kIllegalRequest = 100;
constexpr int kHeartbeatTimeout = 110;
constexpr int kLoginTimeout = 111;
constexpr int kSwitch = 112;

constexpr int kServerError = 200;
constexpr int kServerClosed = 201;
constexpr int kServerUnavailable = 202;

constexpr int kConnectionClosed = 300;

constexpr int kUnknownError = 400;

constexpr int kDisconnectedByClient = 500;
constexpr int kDisconnectedByOtherDevice = 501;

constexpr int kDisconnectedByAdmin = 600;

constexpr int kUserIsDeletedOrInactivated = 700;
constexpr int kUserIsBlocked = 701;
}  // namespace turms::client::model::SessionCloseStatus

#endif  // TURMS_CLIENT_MODEL_SESSION_CLOSE_STATUS_H
