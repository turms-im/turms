import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../components/index.dart';
import '../../../../components/t_menu/t_context_menu.dart';
import '../view_models/selected_conversation_view_model.dart';
import 'chat_session_pane.dart';

class ChatSessionPaneHeader extends ConsumerStatefulWidget {
  const ChatSessionPaneHeader({
    super.key,
    required this.drawerController,
    required this.supportDrawer,
  });

  final TDrawerController drawerController;
  final bool supportDrawer;

  @override
  ConsumerState<ChatSessionPaneHeader> createState() =>
      _ChatSessionPaneHeaderState();
}

class _ChatSessionPaneHeaderState extends ConsumerState<ChatSessionPaneHeader> {
  @override
  Widget build(BuildContext context) => Stack(
    children: [
      const TWindowControlZone(toggleMaximizeOnDoubleTap: true),
      Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            // Use Flexible to prevent overflow
            Flexible(
              child: Padding(
                padding: const EdgeInsets.only(left: 28, right: 128),
                child: SelectionArea(
                  contextMenuBuilder: buildContextMenuForSelectableRegion,
                  child: Text(
                    ref.watch(selectedConversationViewModel)?.contact.name ??
                        '',
                    maxLines: 1,
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.w500,
                    ),
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
              ),
            ),
            if (widget.supportDrawer)
              Column(
                crossAxisAlignment: CrossAxisAlignment.end,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  TapRegion(
                    groupId: chatSessionDetailsDrawerGroupId,
                    child: TIconButton(
                      onTap: () => widget.drawerController.toggle!.call(),
                      tooltip: ref.watch(appLocalizationsViewModel).chatInfo,
                      iconData: Symbols.more_horiz_rounded,
                    ),
                  ),
                ],
              ),
          ],
        ),
      ),
    ],
  );
}
