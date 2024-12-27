import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';
import '../t_button/t_text_button.dart';
import '../t_dialog/t_dialog.dart';

class TAlert extends ConsumerWidget {
  const TAlert({
    super.key,
    this.title,
    required this.content,
    this.width,
    this.onTapCancel,
    required this.onTapConfirm,
  });

  final String? title;
  final Widget content;
  final double? width;
  final VoidCallback? onTapCancel;
  final VoidCallback onTapConfirm;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final theme = context.theme;
    return SizedBox(
      width: Sizes.alertWidth,
      height: Sizes.alertHeight,
      child: Padding(
        padding: Sizes.paddingV16H16,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            if (title case final title?) ...[
              Text(
                title,
                style: theme.textTheme.titleMedium,
              ),
            ],
            content,
            Align(
              alignment: Alignment.bottomRight,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  if (onTapCancel != null)
                    TTextButton(
                      text: appLocalizations.cancel,
                      containerPadding: Sizes.paddingV4H8,
                      onTap: onTapCancel,
                    ),
                  TTextButton(
                      text: appLocalizations.confirm,
                      containerPadding: Sizes.paddingV4H8,
                      onTap: onTapConfirm),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

Future<void> showAlertDialog(BuildContext context,
        {String? title,
        required String content,
        VoidCallback? onTapCancel,
        required VoidCallback onTapConfirm}) =>
    showCustomTDialog(
        routeName: '/t-alert',
        context: context,
        borderRadius: Sizes.borderRadiusCircular8,
        child: TAlert(
          title: title,
          content: Text(
            content,
          ),
          onTapCancel: onTapCancel,
          onTapConfirm: onTapConfirm,
        ));
