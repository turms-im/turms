import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/conversation/view_models/id_to_conversation_settings_view_model.dart';
import '../../../../../../domain/user/models/index.dart';

import '../../../../../../infra/collection/list_holder.dart';
import 'conversation_tile.dart';

class ConversationTiles extends ConsumerStatefulWidget {
  const ConversationTiles({
    super.key,
    required this.conversationTileItems,
    this.highlightedConversationTileItemIndex,
    this.selectedConversationId,
    required this.conversationTilesScrollController,
    required this.onConversationTilesBuildContextUpdated,
    required this.onConversationTileItemSelected,
    required this.onConversationDeleted,
  });

  final List<ConversationTileItem> conversationTileItems;
  final int? highlightedConversationTileItemIndex;
  final IntListHolder? selectedConversationId;
  final ScrollController conversationTilesScrollController;

  final ValueChanged<BuildContext?> onConversationTilesBuildContextUpdated;
  final ValueChanged<ConversationTileItem> onConversationTileItemSelected;
  final ValueChanged<IntListHolder> onConversationDeleted;

  @override
  ConsumerState<ConversationTiles> createState() => _ConversationTilesState();
}

class _ConversationTilesState extends ConsumerState<ConversationTiles> {
  @override
  Widget build(BuildContext context) {
    final idToConversationSettings = ref.watch(
      idToConversationSettingsViewModel,
    );
    widget.onConversationTilesBuildContextUpdated(null);
    // Don't use "ScrollablePositionedList" because it's buggy.
    // e.g. https://github.com/google/flutter.widgets/issues/276
    final items = widget.conversationTileItems;
    final itemCount = items.length;
    final recordIdToIndex = {
      for (var i = 0; i < itemCount; i++) items[i].contact.recordId: i,
    };
    return ListView.builder(
      controller: widget.conversationTilesScrollController,
      addAutomaticKeepAlives: false,
      padding: EdgeInsets.zero,
      itemCount: itemCount,
      prototypeItem: ConversationTile(
        item: ConversationTileItemForNormalMode(
          conversation: UserConversation(
            contact: UserContact(
              userId: Int64.ZERO,
              name: '',
              relationshipGroupId: Int64.ZERO,
            ),
            messages: [],
          ),
          nameTextSpans: [],
        ),
        onTap: () {},
        onSecondaryTap: () {},
        onDeleted: () {},
      ),
      findChildIndexCallback: (key) =>
          recordIdToIndex[(key as ValueKey<String>).value],
      itemBuilder: (context, index) {
        widget.onConversationTilesBuildContextUpdated(context);
        final item = items[index];
        final conversationId = item.conversationId;
        return ConversationTile(
          key: ValueKey(item.contact.recordId),
          item: item,
          conversationSettings: idToConversationSettings[conversationId],
          selected: widget.selectedConversationId == conversationId,
          highlighted: widget.highlightedConversationTileItemIndex == index,
          onTap: () {
            widget.onConversationTileItemSelected(item);
          },
          onSecondaryTap: () {
            // TODO
          },
          onDeleted: () {
            widget.onConversationDeleted(conversationId);
          },
        );
      },
    );
  }
}
