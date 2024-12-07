import 'package:flutter/foundation.dart';
import 'package:tray_manager/tray_manager.dart';

import '../platform/platform_utils.dart';
import '../window/window_utils.dart';

final class TrayUtils {
  TrayUtils._();

  static bool get supportsTray => PlatformUtils.isAnyTargetPlatform(
      [TargetPlatform.linux, TargetPlatform.macOS, TargetPlatform.windows]);

  static Future<void> initTray(
      String tooltip, String icon, List<TrayMenuItem> menuItems) async {
    if (!supportsTray) {
      return;
    }
    await trayManager.setIcon(icon);
    await trayManager.setContextMenu(Menu(
        items: menuItems
            .map((item) => MenuItem(
                  key: item.key,
                  label: item.label,
                ))
            .toList()));
    await trayManager.setToolTip(tooltip);
    final keyToOnTap = <String, void Function()>{};
    for (final item in menuItems) {
      keyToOnTap[item.key] = item.onTap;
    }
    trayManager.addListener(_TrayListener(onTrayMenuItemTap: (item) {
      keyToOnTap[item.key]!.call();
    }));
  }
}

class TrayMenuItem {
  const TrayMenuItem(
      {required this.key, required this.label, required this.onTap});

  final String key;
  final String label;
  final void Function() onTap;
}

class _TrayListener extends TrayListener {
  _TrayListener({required this.onTrayMenuItemTap});

  final void Function(MenuItem menuItem) onTrayMenuItemTap;

  @override
  Future<void> onTrayIconMouseDown() async {
    await WindowUtils.show();
  }

  @override
  Future<void> onTrayIconRightMouseDown() async =>
      trayManager.popUpContextMenu();

  @override
  void onTrayMenuItemClick(MenuItem menuItem) => onTrayMenuItemTap(menuItem);
}
