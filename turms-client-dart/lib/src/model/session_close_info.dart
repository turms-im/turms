class SessionCloseInfo {
  int closeStatus;
  int? businessStatus;
  String? reason;

  SessionCloseInfo(this.closeStatus, this.businessStatus, this.reason);
  SessionCloseInfo.fromCloseStatus(this.closeStatus);
}
