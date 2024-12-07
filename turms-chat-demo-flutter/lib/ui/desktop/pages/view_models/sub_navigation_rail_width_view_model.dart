import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../themes/sizes.dart';

class SubNavigationRailWidthViewModelNotifier extends Notifier<double> {
  @override
  double build() => Sizes.subNavigationRailWidth;

  void update(double width) {
    final newWidth = width
        .clamp(Sizes.subNavigationRailMinWidth, Sizes.subNavigationRailMaxWidth)
        .roundToDouble();
    if (newWidth != state) {
      state = newWidth;
    }
  }
}

final subNavigationRailWidthViewModel =
    NotifierProvider<SubNavigationRailWidthViewModelNotifier, double>(
        SubNavigationRailWidthViewModelNotifier.new);
