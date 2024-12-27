import 'package:collection/collection.dart';

enum SettingActionOnClose {
  minimizeToTray(0),
  exit(1);

  const SettingActionOnClose(this.id);

  final int id;

  static SettingActionOnClose? fromId(int id) =>
      values.firstWhereOrNull((e) => e.id == id);
}
