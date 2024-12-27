import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class TTabs extends StatefulWidget {
  const TTabs(
      {super.key,
      required this.tabs,
      this.selectedTabId,
      required this.onTabSelected});

  final List<TTab> tabs;
  final Object? selectedTabId;
  final void Function(int index, TTab tab) onTabSelected;

  @override
  State<TTabs> createState() => _TTabsState();
}

class _TTabsState extends State<TTabs> {
  Object? _hoveringTabId;

  @override
  Widget build(BuildContext context) => Column(
        children: widget.tabs.indexed.map((item) {
          final (index, tab) = item;
          final isSelected = widget.selectedTabId == tab.id;
          return _buildTab(context.theme, tab, index, isSelected);
        }).toList(),
      );

  Widget _buildTab(ThemeData theme, TTab tab, int index, bool isSelected) {
    final child = MouseRegion(
        cursor: SystemMouseCursors.click,
        onEnter: (_) => setState(() => _hoveringTabId = tab.id),
        onExit: (_) => setState(() {
              if (_hoveringTabId == tab.id) {
                _hoveringTabId = null;
              }
            }),
        child: GestureDetector(
          onTap: () {
            widget.onTabSelected(index, tab);
            setState(() {});
          },
          child: SizedBox(
              width: double.maxFinite,
              child: DecoratedBox(
                decoration: BoxDecoration(
                  borderRadius: Sizes.borderRadiusCircular4,
                  color: isSelected || _hoveringTabId == tab.id
                      ? const Color.fromARGB(255, 246, 246, 246)
                      : null,
                ),
                child: Padding(
                  padding:
                      const EdgeInsets.symmetric(vertical: 8, horizontal: 24),
                  child: Text(
                    tab.text,
                    style: isSelected
                        ? TextStyle(color: theme.primaryColor)
                        : theme.appThemeExtension.tabTextStyle,
                  ),
                ),
              )),
        ));
    if (index == 0) {
      return child;
    }
    return Padding(
      padding: const EdgeInsets.only(top: 8),
      child: child,
    );
  }
}

class TTab {
  const TTab({required this.id, required this.text});

  final Object id;
  final String text;
}
