import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../domain/user/services/user_service.dart';
import '../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../themes/app_theme_extension.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import '../shared_components/user_profile_popup.dart';
import 'tabs.dart';

class MainNavigationRail extends ConsumerWidget {
  const MainNavigationRail({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final loggedInUser = ref.watch(loggedInUserViewModel)!;
    return ColoredBox(
      color: context.appThemeExtension.mainNavigationRailBackgroundColor,
      child: Padding(
        padding: const EdgeInsets.only(top: 32, bottom: 16),
        child: Column(
          spacing: 24,
          children: [
            UserProfilePopup(
              user: loggedInUser,
              imageEditable: true,
              presence: loggedInUser.presence,
              presencePopupOffset: Offset(
                  (Sizes.mainNavigationRailWidth -
                              TAvatarSize.medium.containerSize) /
                          2 +
                      Sizes.mainNavigationRailElementPopupOffsetX,
                  0),
              onPresenceSelected: (value) {
                ref.read(userServiceProvider)!.updatePresence(value);
              },
            ),
            const Expanded(child: Tabs())
          ],
        ),
      ),
    );
  }
}
