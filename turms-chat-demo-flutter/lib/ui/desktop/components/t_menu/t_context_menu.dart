import 'package:flutter/material.dart';

import '../../../themes/sizes.dart';
import '../t_selection/t_selectable_region.dart';
import 't_menu.dart';

const _allowedContextButtonTypes = [
  ContextMenuButtonType.cut,
  ContextMenuButtonType.copy,
  ContextMenuButtonType.paste,
  ContextMenuButtonType.selectAll,
  ContextMenuButtonType.delete,
  ContextMenuButtonType.custom,
];

// TODO: 1. Fix the menu may not dismiss
// when the mouser interacts with other widgets.
// 2. Use custom overlay entry to control its animation.
Widget buildContextMenu({
  required BuildContext context,
  required List<ContextMenuButtonItem> items,
  required TextSelectionToolbarAnchors anchors,
}) =>
    CustomSingleChildLayout(
        delegate: DesktopTextSelectionToolbarLayoutDelegate(
            anchor: anchors.primaryAnchor),
        child: _buildContextMenu(context, items));

Widget buildContextMenuForSelectableRegion(
        BuildContext context, SelectableRegionState selectableRegionState) =>
    CustomSingleChildLayout(
        delegate: DesktopTextSelectionToolbarLayoutDelegate(
            anchor: selectableRegionState.contextMenuAnchors.primaryAnchor),
        child: _buildContextMenu(
            context, selectableRegionState.contextMenuButtonItems));

Widget buildContextMenuForTSelectableRegion(
        BuildContext context, TSelectableRegionState selectableRegionState) =>
    CustomSingleChildLayout(
        delegate: DesktopTextSelectionToolbarLayoutDelegate(
            anchor: selectableRegionState.contextMenuAnchors.primaryAnchor),
        child: _buildContextMenu(
            context, selectableRegionState.contextMenuButtonItems));

Widget _buildContextMenu(
    BuildContext context, List<ContextMenuButtonItem> items) {
  final menuEntries = <TMenuEntry<String>>[];
  for (final item in items) {
    final onPressed = item.onPressed;
    if (onPressed != null && _allowedContextButtonTypes.contains(item.type)) {
      final label = item.label ??
          AdaptiveTextSelectionToolbar.getButtonLabel(context, item);
      menuEntries
          .add(TMenuEntry(label: label, value: label, onSelected: onPressed));
    }
  }
  return TMenu(
    dense: true,
    entries: menuEntries,
    padding: Sizes.paddingV4H8,
    textAlign: TextAlign.center,
  );
}
