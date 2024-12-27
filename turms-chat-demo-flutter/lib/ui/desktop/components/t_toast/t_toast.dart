import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../themes/index.dart';
import '../t_circle/t_circle.dart';
import 't_toast_type.dart';
import 't_toast_view.dart';

class TToast {
  TToast._();

  static OverlayState? _overlayState;
  static OverlayEntry? _overlayEntry;
  static bool _isVisible = false;

  static Future<void> showToast(
    BuildContext context,
    String text, {
    Duration toastDuration = const Duration(seconds: 3),
    TToastType type = TToastType.info,
  }) async {
    // TODO: support displaying multiple toasts at the same time.
    dismiss();
    _overlayState = Overlay.of(context);
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;

    final Widget toastChild = TToastView(
      child: DecoratedBox(
        decoration: appThemeExtension.toastDecoration,
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 12),
          child: Row(
            mainAxisSize: MainAxisSize.min,
            spacing: 8,
            children: [
              switch (type) {
                TToastType.info => TCircle(
                    backgroundColor: appThemeExtension.infoColor,
                    child: const Icon(
                      Symbols.info_i_rounded,
                      color: Colors.white,
                      size: 14,
                    )),
                TToastType.success => TCircle(
                    backgroundColor: appThemeExtension.successColor,
                    child: const Icon(
                      Symbols.done_rounded,
                      color: Colors.white,
                      size: 14,
                    )),
                TToastType.error => TCircle(
                    backgroundColor: theme.colorScheme.error,
                    child: const Icon(
                      Symbols.close_rounded,
                      color: Colors.white,
                      size: 14,
                    )),
                TToastType.warning => TCircle(
                    backgroundColor: appThemeExtension.warningColor,
                    child: const Icon(
                      Symbols.priority_high_rounded,
                      color: Colors.white,
                      size: 14,
                    )),
              },
              Text(text, softWrap: true, style: const TextStyle(fontSize: 14)),
            ],
          ),
        ),
      ),
      duration: toastDuration,
      onDismissed: dismiss,
    );

    _overlayEntry = OverlayEntry(
        builder: (BuildContext context) =>
            Positioned(bottom: 60, left: 18, right: 18, child: toastChild));

    _isVisible = true;
    _overlayState!.insert(_overlayEntry!);
  }

  static void dismiss() {
    if (!_isVisible) {
      return;
    }
    _isVisible = false;
    _overlayEntry?.remove();
  }
}
