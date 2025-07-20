import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../themes/index.dart';
import '../../../components/index.dart';
import '../../view_models/sub_navigation_rail_width_view_model.dart';
import 'chat_session_pane/chat_session_pane.dart';
import 'sub_navigation_rail/sub_navigation_rail.dart';

class ChatPage extends ConsumerStatefulWidget {
  const ChatPage({super.key});

  @override
  ConsumerState<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends ConsumerState<ChatPage> {
  double _widthOnPointDown = 0;

  @override
  Widget build(BuildContext context) {
    final subNavigationRailWidth = ref.watch(subNavigationRailWidthViewModel);
    final appThemeExtension = context.appThemeExtension;
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        Stack(
          clipBehavior: Clip.none,
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
        Expanded(child: _buildChatSessionPane()),
      ],
    );
  }

  Widget _buildSubNavigationRail(
    AppThemeExtension appThemeExtension,
    double subNavigationRailWidth,
  ) =>
      SizedBox(width: subNavigationRailWidth, child: const SubNavigationRail());

  Widget _buildChatSessionPane() => ChatSessionPane();
}
