class SessionCloseStatus {
  static const illegalRequest = 100;
  static const heartbeatTimeout = 110;
  static const loginTimeout = 111;
  static const switchProtocol = 112;

  static const serverError = 200;
  static const serverClosed = 201;
  static const serverUnavailable = 202;

  static const connectionClosed = 300;

  static const unknownError = 400;

  static const disconnectedByClient = 500;
  static const disconnectedByOtherDevice = 501;

  static const disconnectedByAdmin = 600;

  static const userIsDeletedOrInactivated = 700;
  static const userIsBlocked = 701;
}
