import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../../../themes/index.dart';
import '../index.dart';

class TMenu<T> extends StatefulWidget {
  const TMenu({
    super.key,
    this.controller,
    this.value,
    required this.entries,
    this.onSelected,
    this.dense = false,
    this.focusNode,
    this.textAlign = TextAlign.start,
    this.padding = Sizes.paddingV8H8,
  });

  final TMenuController? controller;
  final T? value;
  final List<TMenuEntry<T>> entries;
  final void Function(TMenuEntry<T> item)? onSelected;
  final bool dense;
  final FocusNode? focusNode;
  final TextAlign textAlign;
  final EdgeInsets padding;

  @override
  State<TMenu<T>> createState() => _TMenuState<T>();
}

class _TMenuState<T> extends State<TMenu<T>> {
  int? _hoveredEntryIndex;

  @override
  void initState() {
    super.initState();
    widget.controller?.move = _move;
    widget.controller?.selectCurrentEntry = _selectCurrentEntry;
  }

  @override
  void dispose() {
    widget.controller?.move = null;
    widget.controller?.selectCurrentEntry = null;
    super.dispose();
  }

  @override
  void didUpdateWidget(TMenu<T> oldWidget) {
    super.didUpdateWidget(oldWidget);
    widget.controller?.move = _move;
    widget.controller?.selectCurrentEntry = _selectCurrentEntry;
  }

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    final menuDecoration = appThemeExtension.menuDecoration;
    if (widget.dense) {
      return LayoutBuilder(builder: (context, constraints) {
        final minWidth =
            constraints.minWidth - menuDecoration.padding.horizontal;
        if (minWidth > 0) {
          return _buildContent(
              context, appThemeExtension, menuDecoration, minWidth);
        }
        return _buildContent(context, appThemeExtension, menuDecoration, null);
      });
    }
    return _buildContent(context, appThemeExtension, menuDecoration, null);
  }

  Widget _buildContent(
      BuildContext context,
      AppThemeExtension appThemeExtension,
      BoxDecoration menuDecoration,
      double? minWidth) {
    final entries = widget.entries;
    assert(entries.isNotEmpty, 'menu entries must not be empty');
    assert(
        entries.length != 1 || !identical(entries.first, TMenuEntry.separator),
        'menu entries must not contain only separator');
    Widget content = DecoratedBox(
      decoration: menuDecoration,
      child: Padding(
        padding: menuDecoration.padding,
        child: IntrinsicWidth(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            mainAxisSize: MainAxisSize.min,
            children: [
              for (final (index, entry) in entries.indexed)
                _buildItem(context, appThemeExtension, index, entry, minWidth)
            ],
          ),
        ),
      ),
    );
    if (widget.focusNode case final focusNode?) {
      content = Focus(
        focusNode: focusNode,
        onKeyEvent: (node, event) {
          if (event is! KeyDownEvent && event is! KeyRepeatEvent) {
            return KeyEventResult.ignored;
          }
          switch (event.logicalKey) {
            case LogicalKeyboardKey.arrowDown:
              _move(true);
              return KeyEventResult.handled;
            case LogicalKeyboardKey.arrowUp:
              _move(false);
              return KeyEventResult.handled;
            case LogicalKeyboardKey.enter:
              if (_hoveredEntryIndex case final index?) {
                _select(entries[index]);
              }
              return KeyEventResult.handled;
            default:
              return KeyEventResult.ignored;
          }
        },
        child: content,
      );
    }
    return content;
  }

  Widget _buildItem(BuildContext context, AppThemeExtension appThemeExtension,
      int index, TMenuEntry<T> entry, double? minWidth) {
    if (identical(entry, TMenuEntry.separator)) {
      return const THorizontalDivider();
    }
    Widget content = Text(
      entry.label,
      textAlign: widget.textAlign,
      style: appThemeExtension.menuItemTextStyle,
      maxLines: 1,
      overflow: TextOverflow.ellipsis,
    );
    if (entry.prefix case final prefix?) {
      content = Row(spacing: 8, children: [prefix, content]);
    }
    content = Padding(
      padding: widget.padding,
      child: content,
    );
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      onEnter: (event) => setState(() {
        _hoveredEntryIndex = index;
      }),
      onExit: (event) => setState(() {
        if (_hoveredEntryIndex == index) {
          _hoveredEntryIndex = null;
        }
      }),
      child: GestureDetector(
        behavior: HitTestBehavior.translucent,
        onTap: () {
          _select(entry);
        },
        child: ColoredBox(
          color: _hoveredEntryIndex == index
              ? appThemeExtension.menuItemHoveredColor
              : appThemeExtension.menuItemColor,
          child: widget.dense
              ? minWidth == null
                  ? content
                  : SizedBox(
                      width: minWidth,
                      child: content,
                    )
              : SizedBox(
                  width: double.infinity,
                  child: content,
                ),
        ),
      ),
    );
  }

  void _move(bool down) {
    var currentIndex = _hoveredEntryIndex;
    final entries = widget.entries;
    final count = entries.length;
    while (true) {
      if (down) {
        currentIndex = currentIndex == null
            ? 0
            : currentIndex + 1 < count
                ? currentIndex + 1
                : 0;
      } else {
        currentIndex = currentIndex == null
            ? count - 1
            : currentIndex - 1 >= 0
                ? currentIndex - 1
                : count - 1;
      }
      if (!identical(entries[currentIndex], TMenuEntry.separator)) {
        break;
      }
    }
    if (_hoveredEntryIndex == currentIndex) {
      return;
    }
    _hoveredEntryIndex = currentIndex;
    setState(() {});
  }

  void _select(TMenuEntry<T> entry) {
    final onSelected = entry.onSelected;
    if (onSelected != null) {
      onSelected();
    }
    widget.onSelected?.call(entry);
  }

  void _selectCurrentEntry() {
    if (_hoveredEntryIndex case final index?) {
      _select(widget.entries[index]);
    }
  }
}

class TMenuEntry<T> {
  const TMenuEntry({
    required this.label,
    this.value,
    this.prefix,
    this.onSelected,
  });

  static TMenuEntry<dynamic> separator = const TMenuEntry(label: '');

  final String label;
  final T? value;
  final Widget? prefix;
  final VoidCallback? onSelected;
}

class TMenuController {
  void Function(bool down)? move;
  void Function()? selectCurrentEntry;
}
