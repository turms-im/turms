import 'package:animations/animations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../l10n/app_localizations.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';

import '../t_button/t_text_button.dart';
import '../t_title_bar/t_title_bar.dart';

const config = FadeScaleTransitionConfiguration(
  barrierColor: Colors.transparent,
  barrierDismissible: false,
);

const _routeSettingsArguments = Object();

bool isTDialogRoute(Route<dynamic> route) =>
    route.settings.arguments == _routeSettingsArguments;

Future<void> showCustomTDialog(
        {required String routeName,
        required BuildContext context,
        BorderRadiusGeometry borderRadius = Sizes.borderRadiusCircular4,
        required Widget child}) =>
    showModal(
        routeSettings:
            RouteSettings(name: routeName, arguments: _routeSettingsArguments),
        context: context,
        configuration: config,
        builder: (BuildContext context) => Align(
              child: Material(
                color: Colors.transparent,
                borderRadius: borderRadius,
                child: DecoratedBox(
                    decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: borderRadius,
                        boxShadow: Styles.boxShadow),
                    child: ClipRRect(
                        borderRadius: borderRadius,
                        child: RepaintBoundary(child: child))),
              ),
            ));

Future<void> showSimpleTDialog(
        {required String routeName,
        required BuildContext context,
        double? width,
        double? height,
        required Widget child}) =>
    showCustomTDialog(
        context: context,
        routeName: routeName,
        child: Consumer(
          builder: (BuildContext context, WidgetRef ref, Widget? _) => SizedBox(
            width: width ?? Sizes.dialogWidthMedium,
            height: height ?? Sizes.dialogHeightMedium,
            child: Stack(
              children: [
                Positioned.fill(
                  child: child,
                ),
                const TTitleBar(
                  displayCloseOnly: true,
                  popOnCloseTapped: true,
                )
              ],
            ),
          ),
        ));

Future<void> showAlertTDialog(
        {required String routeName,
        required BuildContext context,
        required String Function(AppLocalizations) contentTextProvider,
        required TDialogAction confirmAction}) =>
    showCustomTDialog(
        context: context,
        routeName: routeName,
        child: Consumer(
          builder: (BuildContext context, WidgetRef ref, Widget? _) {
            final appLocalizations = ref.watch(appLocalizationsViewModel);
            final theme = context.theme;
            final appThemeExtension = theme.appThemeExtension;
            return Padding(
              padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 24),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: [
                  Text(contentTextProvider(appLocalizations)),
                  Sizes.sizedBoxH16,
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      TTextButton(
                        containerWidth: 112,
                        containerHeight: 28,
                        onTap: () {
                          if (confirmAction.onPressed()) {
                            Navigator.of(context).pop();
                          }
                        },
                        containerColor:
                            confirmAction.style == TDialogActionStyle.danger
                                ? appThemeExtension.dangerColor
                                : null,
                        textStyle:
                            confirmAction.style == TDialogActionStyle.danger
                                ? theme.textTheme.bodyMedium!
                                    .copyWith(color: Colors.white)
                                : null,
                        text: confirmAction.textProvider!(appLocalizations),
                      ),
                      Sizes.sizedBoxW16,
                      TTextButton.outlined(
                        containerWidth: 112,
                        containerHeight: 28,
                        theme: theme,
                        onTap: () => Navigator.of(context).pop(),
                        text: appLocalizations.cancel,
                      )
                    ],
                  )
                ],
              ),
            );
          },
        ));

class TDialogAction {
  const TDialogAction({this.style, this.textProvider, required this.onPressed});

  final TDialogActionStyle? style;
  final String Function(AppLocalizations)? textProvider;
  final bool Function() onPressed;
}

enum TDialogActionStyle {
  primary,
  danger,
}
