class SessionCloseInfo {
  final int closeStatus;
  final int? businessStatus;
  final String? reason;
  final Object? cause;
  final StackTrace? stackTrace;

  SessionCloseInfo(this.closeStatus, this.businessStatus, this.reason,
      this.cause, this.stackTrace);

  SessionCloseInfo.from(
      {required this.closeStatus,
      this.businessStatus,
      this.reason,
      this.cause,
      this.stackTrace});

  @override
  int get hashCode =>
      closeStatus.hashCode ^
      businessStatus.hashCode ^
      reason.hashCode ^
      cause.hashCode ^
      stackTrace.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is SessionCloseInfo &&
          closeStatus == other.closeStatus &&
          businessStatus == other.businessStatus &&
          reason == other.reason &&
          cause == other.cause &&
          stackTrace == other.stackTrace;

  @override
  String toString() =>
      'SessionCloseInfo{closeStatus: $closeStatus, businessStatus: $businessStatus, reason: $reason, cause: $cause, stackTrace: $stackTrace}';
}
