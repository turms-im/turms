import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../themes/index.dart';
import '../../../components/t_divider/t_vertical_divider.dart';
import '../../view_models/sub_navigation_rail_width_view_model.dart';
import 'contact_profile_page.dart';
import 'sub_navigation_rail.dart';

class ContactsPage extends ConsumerStatefulWidget {
  const ContactsPage({super.key});

  @override
  ConsumerState<ContactsPage> createState() => _ContactsPageState();
}

class _ContactsPageState extends ConsumerState<ContactsPage> {
  double _widthOnPointDown = 0;

  @override
  Widget build(BuildContext context) {
    final subNavigationRailWidth = ref.watch(subNavigationRailWidthViewModel);
    final appThemeExtension = context.appThemeExtension;
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        Stack(
          children: [
            _buildSubNavigationRail(appThemeExtension, subNavigationRailWidth),
            Positioned(
              top: 0,
              bottom: 0,
              right: -Sizes.subNavigationRailDividerSize.padding.right,
              child: TMovableVerticalDivider(
                color: appThemeExtension.subNavigationRailDividerColor,
                onMove: () {
                  _widthOnPointDown = subNavigationRailWidth;
                },
                onMoved: (delta) {
                  ref
                      .read(subNavigationRailWidthViewModel.notifier)
                      .update(_widthOnPointDown + delta);
                },
              ),
            ),
          ],
        ),
        _buildContactProfilePage(appThemeExtension),
      ],
    );
  }

  Widget _buildSubNavigationRail(
    AppThemeExtension appThemeExtension,
    double subNavigationRailWidth,
  ) =>
      SizedBox(width: subNavigationRailWidth, child: const SubNavigationRail());

  Widget _buildContactProfilePage(AppThemeExtension appThemeExtension) =>
      Expanded(
        child: ColoredBox(
          color: appThemeExtension.homePageBackgroundColor,
          child: const ContactProfilePage(),
        ),
      );
}
