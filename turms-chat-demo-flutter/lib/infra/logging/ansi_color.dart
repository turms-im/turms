class AnsiColor {
  AnsiColor.bg(this.bg) : fg = null, str = format(bg: bg);

  AnsiColor.fg(this.fg) : bg = null, str = format(fg: fg);

  static const ansiEsc = '\x1B[';
  static const ansiDefault = '${ansiEsc}0m';

  final int? fg;
  final int? bg;

  final String str;

  static String format({int? fg, int? bg}) {
    if (fg != null) {
      return '${ansiEsc}38;5;${fg}m';
    } else if (bg != null) {
      return '${ansiEsc}48;5;${bg}m';
    } else {
      return '';
    }
  }

  @override
  String toString() {
    if (fg != null) {
      return '${ansiEsc}38;5;${fg}m';
    } else if (bg != null) {
      return '${ansiEsc}48;5;${bg}m';
    } else {
      return '';
    }
  }

  static int grey(double level) => 232 + (level.clamp(0.0, 1.0) * 23).round();
}
