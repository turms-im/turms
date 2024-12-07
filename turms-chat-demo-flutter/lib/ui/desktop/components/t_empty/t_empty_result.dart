import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';

const _colorFilter = ColorFilter.matrix([
  0.2126, 0.7152, 0.0722, 0, 0, //
  0.2126, 0.7152, 0.0722, 0, 0,
  0.2126, 0.7152, 0.0722, 0, 0,
  0, 0, 0, 1, 0,
]);

class TEmptyResult extends ConsumerWidget {
  const TEmptyResult({super.key, this.icon = Symbols.description_rounded});

  final IconData icon;

  @override
  Widget build(BuildContext context, WidgetRef ref) => Center(
          child: ColorFiltered(
        colorFilter: _colorFilter,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Stack(
              clipBehavior: Clip.none,
              children: [
                Opacity(
                  opacity: 0.1,
                  child: Icon(
                    icon,
                    size: 100,
                  ),
                ),
                const Positioned(
                  left: 40,
                  top: 40,
                  child: Opacity(
                    opacity: 0.5,
                    child: Icon(
                      Symbols.search_rounded,
                      size: 80,
                    ),
                  ),
                ),
              ],
            ),
            Sizes.sizedBoxH16,
            Text(ref.watch(appLocalizationsViewModel).noResultsFound,
                style: context.appThemeExtension.descriptionTextStyle
                    .copyWith(fontSize: 18)),
            Sizes.sizedBoxH32,
          ],
        ),
      ));
}
