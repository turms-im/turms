import 'package:flutter/material.dart';

import '../../../themes/index.dart';

const defaultListTile = 64.0;
const _animationDuration = Duration(milliseconds: 100);

class TListTile extends StatefulWidget {
  const TListTile(
      {Key? key,
      this.height = defaultListTile,
      this.padding = const EdgeInsets.symmetric(horizontal: 8, vertical: 12),
      this.focused = false,
      this.backgroundColor,
      this.backgroundFocusedColor,
      this.backgroundHoveredColor,
      this.mouseCursor = SystemMouseCursors.basic,
      this.onTap,
      this.onSecondaryTapUp,
      required this.child})
      : super(key: key);

  final bool focused;
  final double? height;
  final EdgeInsets padding;
  final Color? backgroundColor;
  final Color? backgroundFocusedColor;
  final Color? backgroundHoveredColor;
  final MouseCursor mouseCursor;
  final GestureTapCallback? onTap;
  final GestureTapUpCallback? onSecondaryTapUp;
  final Widget child;

  @override
  _TListTileState createState() => _TListTileState();
}

class _TListTileState extends State<TListTile> {
  bool _isHovered = false;

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    return MouseRegion(
        cursor: widget.mouseCursor,
        onEnter: (_) => setState(() => _isHovered = true),
        onExit: (_) => setState(() => _isHovered = false),
        child: GestureDetector(
            onTap: widget.onTap,
            onSecondaryTapUp: widget.onSecondaryTapUp,
            child: AnimatedContainer(
                height: widget.height,
                alignment: Alignment.center,
                color: widget.focused
                    ? (widget.backgroundFocusedColor ??
                        appThemeExtension.tileBackgroundFocusedColor)
                    : (_isHovered
                        ? (widget.backgroundHoveredColor ??
                            appThemeExtension.tileBackgroundHoveredColor)
                        : (widget.backgroundColor ??
                            appThemeExtension.tileBackgroundColor)),
                padding: widget.padding,
                duration: _animationDuration,
                child: widget.child)));
  }
}
