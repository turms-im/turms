import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../domain/user/models/setting_action_on_close.dart';
import '../../../../domain/user/view_models/user_settings_view_model.dart';
import '../../../../infra/app/app_utils.dart';
import '../../../../infra/ui/color_extensions.dart';
import '../../../../infra/window/window_utils.dart';
import '../../../l10n/app_localizations.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';

import '../t_button/t_icon_button.dart';

// TODO: Support MacOS design
class TTitleBar extends ConsumerStatefulWidget {
  const TTitleBar(
      {super.key,
      this.displayCloseOnly = false,
      this.popOnCloseTapped = false,
      this.usePositioned = true,
      this.backgroundColor = AppColors.transparentWhite});

  final bool displayCloseOnly;
  final bool popOnCloseTapped;
  final bool usePositioned;
  final Color backgroundColor;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _TTitleBarState();
}

class _TTitleBarState extends ConsumerState<TTitleBar> {
  bool _isAlwaysOnTop = false;

  @override
  Widget build(BuildContext context) {
    final localizations = ref.watch(appLocalizationsViewModel);
    final child = widget.displayCloseOnly
        ? _buildCloseButton(context, localizations)
        : Row(children: [
            _buildSetAlwaysOnTopButton(context.theme, localizations),
            _buildMinimizeButton(localizations),
            _buildMaximizeButton(localizations),
            _buildCloseButton(context, localizations),
          ]);
    return widget.usePositioned
        ? Positioned(top: 0, right: 0, child: child)
        : child;
  }

  TIconButton _buildSetAlwaysOnTopButton(
          ThemeData theme, AppLocalizations localizations) =>
      TIconButton(
          containerSize: Sizes.titleBarSize,
          containerColor: _isAlwaysOnTop
              ? widget.backgroundColor.darken()
              : widget.backgroundColor,
          containerColorHovered: const Color.fromARGB(255, 226, 226, 226),
          containerBorderRadius: BorderRadius.zero,
          iconData: Symbols.push_pin_rounded,
          iconSize: 16,
          iconColor: _isAlwaysOnTop
              ? theme.primaryColor
              : const Color.fromARGB(255, 67, 67, 67),
          onTap: () async {
            setState(() => _isAlwaysOnTop = !_isAlwaysOnTop);
            await WindowUtils.setAlwaysOnTop(_isAlwaysOnTop);
          },
          tooltip: _isAlwaysOnTop
              ? localizations.alwaysOnTopDisable
              : localizations.alwaysOnTopEnable);

  TIconButton _buildMinimizeButton(AppLocalizations localizations) =>
      TIconButton(
          containerSize: Sizes.titleBarSize,
          containerColor: widget.backgroundColor,
          containerColorHovered: const Color.fromARGB(255, 226, 226, 226),
          containerBorderRadius: BorderRadius.zero,
          iconData: Symbols.horizontal_rule_rounded,
          iconSize: 16,
          iconColor: const Color.fromARGB(255, 67, 67, 67),
          onTap: WindowUtils.minimize,
          tooltip: localizations.minimize);

  TIconButton _buildMaximizeButton(AppLocalizations localizations) {
    final isWindowMaximized = ref.watch(isWindowMaximizedViewModel);
    return TIconButton(
      containerSize: Sizes.titleBarSize,
      containerColor: widget.backgroundColor,
      containerColorHovered: const Color.fromARGB(255, 226, 226, 226),
      containerBorderRadius: BorderRadius.zero,
      iconData: isWindowMaximized
          ? Symbols.stack_rounded
          : Symbols.crop_square_rounded,
      iconSize: 16,
      iconColor: const Color.fromARGB(255, 67, 67, 67),
      iconFlipX: isWindowMaximized,
      onTap: () async {
        if (isWindowMaximized) {
          await WindowUtils.unmaximize();
        } else {
          await WindowUtils.maximize();
        }
      },
      tooltip:
          isWindowMaximized ? localizations.restore : localizations.maximize,
    );
  }

  TIconButton _buildCloseButton(
          BuildContext context, AppLocalizations localizations) =>
      TIconButton(
        containerSize: Sizes.titleBarSize,
        containerColor: widget.backgroundColor,
        containerColorHovered: Colors.red,
        containerBorderRadius: BorderRadius.zero,
        iconData: Symbols.close_rounded,
        iconSize: 16,
        iconColor: const Color.fromARGB(255, 67, 67, 67),
        iconColorHovered: Colors.white,
        tooltip: localizations.close,
        onTap: widget.popOnCloseTapped
            ? () => Navigator.of(context).pop()
            : () => switch (ref.read(userSettingsViewModel)?.actionOnClose ??
                    SettingActionOnClose.exit) {
                  SettingActionOnClose.minimizeToTray => WindowUtils.hide(),
                  SettingActionOnClose.exit => AppUtils.close(),
                },
      );
}
