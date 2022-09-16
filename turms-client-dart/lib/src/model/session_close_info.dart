class SessionCloseInfo {
  int closeStatus;
  int? businessStatus;
  String? reason;

  SessionCloseInfo(this.closeStatus, this.businessStatus, this.reason);

  SessionCloseInfo.fromCloseStatus(this.closeStatus);

  @override
  int get hashCode =>
      closeStatus.hashCode ^ businessStatus.hashCode ^ reason.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is SessionCloseInfo &&
          closeStatus == other.closeStatus &&
          businessStatus == other.businessStatus &&
          reason == other.reason;

  @override
  String toString() =>
      'SessionCloseInfo{closeStatus: $closeStatus, businessStatus: $businessStatus, reason: $reason}';
}
