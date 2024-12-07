import 'dart:io';

import 'ansi_escape_codes.dart';
import 'log_appender.dart';
import 'log_entry.dart';
import 'log_level.dart';

class _LogLevelInfo {
  _LogLevelInfo(this.name, this.color);

  final String name;
  final AnsiColor? color;
}

class LogAppenderConsole extends LogAppender {
  static final Map<LogLevel, _LogLevelInfo> _levelToInfo = {
    LogLevel.trace: _LogLevelInfo('TRACE', AnsiColor.fg(AnsiColor.grey(0.5))),
    LogLevel.debug: _LogLevelInfo('DEBUG', null),
    LogLevel.info: _LogLevelInfo(' INFO', AnsiColor.fg(12)),
    LogLevel.warn: _LogLevelInfo(' WARN', AnsiColor.fg(208)),
    LogLevel.error: _LogLevelInfo('ERROR', AnsiColor.fg(196)),
    LogLevel.fatal: _LogLevelInfo('FATAL', AnsiColor.fg(199)),
  };

  @override
  void append(LogEntry entry) {
    final error = entry.error;
    final stackTrace = entry.stackTrace;
    final level = entry.level;
    final levelInfo = _levelToInfo[level]!;
    final levelName = levelInfo.name;
    final color = levelInfo.color;
    if (color != null) {
      if (error == null) {
        stdout.writeln(
            '$color${entry.timestamp.toIso8601String()} $levelName : ${entry.message}${AnsiColor.ansiDefault}');
      } else {
        stderr.writeln(
            '$color${entry.timestamp.toIso8601String()} $levelName : ${entry.message}\n${error.toString()}${AnsiColor.ansiDefault}');
        if (stackTrace != null) {
          stderr.writeln(
              '$color${stackTrace.toString()}${AnsiColor.ansiDefault}');
        }
      }
    } else {
      if (error == null) {
        stdout.writeln(
            '${entry.timestamp.toIso8601String()} $levelName : ${entry.message}');
      } else {
        stderr.writeln(
            '${entry.timestamp.toIso8601String()} $levelName : ${entry.message}\n${error.toString()}');
        if (stackTrace != null) {
          stderr.writeln(stackTrace.toString());
        }
      }
    }
  }
}
