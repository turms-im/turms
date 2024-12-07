enum LogLevel {
  fatal(0),
  error(1),
  warn(2),
  info(3),
  debug(4),
  trace(5);

  const LogLevel(this.value);

  final int value;

  static Map<int, LogLevel> valueToLevel =
      Map.fromEntries(LogLevel.values.map((e) => MapEntry(e.value, e)));

  static LogLevel? fromInt(int value) => valueToLevel[value];
}
