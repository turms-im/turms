import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../themes/index.dart';

const _defaultAnimationDuration = Duration(milliseconds: 200);

class TAccordion extends StatefulWidget {
  const TAccordion({
    super.key,
    this.controller,
    this.showAccordion = false,
    required this.titleChild,
    required this.contentChild,
    this.collapsedTitleBackgroundColor = Colors.transparent,
    this.expandedTitleBackgroundColor = Colors.transparent,
    this.titlePadding = Sizes.paddingV4H4,
    this.contentBackgroundColor,
    this.contentPadding = EdgeInsets.zero,
    this.titleBorder,
    this.contentBorder,
    this.onToggleCollapsed,
    this.titleBorderRadius = BorderRadius.zero,
    this.contentBorderRadius = BorderRadius.zero,
  });

  final TAccordionController? controller;

  final bool showAccordion;

  final Widget titleChild;

  final Widget contentChild;

  final Color collapsedTitleBackgroundColor;

  final Color expandedTitleBackgroundColor;

  final EdgeInsets titlePadding;

  final EdgeInsets contentPadding;

  final Color? contentBackgroundColor;

  final Border? titleBorder;

  final Border? contentBorder;

  final BorderRadius titleBorderRadius;

  final BorderRadius contentBorderRadius;

  final void Function(bool)? onToggleCollapsed;

  @override
  _TAccordionState createState() => _TAccordionState();
}

class _TAccordionState extends State<TAccordion>
    with SingleTickerProviderStateMixin {
  late AnimationController _animationController;
  late Animation<double> _turnsAnimation;
  late CurvedAnimation _sizeAnimation;
  late bool _showAccordion;
  bool _buildChild = true;

  final List<VoidCallback> _onOpenCompletedCallbacks = [];

  @override
  void initState() {
    super.initState();
    widget.controller?.open = _open;
    widget.controller?.close = _close;
    _showAccordion = widget.showAccordion;
    _animationController =
        AnimationController(duration: _defaultAnimationDuration, vsync: this)
          ..addListener(() {
            if (_animationController.isDismissed) {
              _buildChild = false;
              setState(() {});
            } else if (_animationController.isCompleted) {
              for (final callback in _onOpenCompletedCallbacks) {
                callback();
              }
              _onOpenCompletedCallbacks.clear();
            }
          });
    _turnsAnimation = _animationController.drive(
      Tween(begin: 0, end: 90 / 360),
    );
    _sizeAnimation = CurvedAnimation(
      parent: _animationController,
      curve: Curves.fastOutSlowIn,
    );
  }

  @override
  void dispose() {
    widget.controller?.open = null;
    widget.controller?.close = null;
    _animationController.dispose();
    super.dispose();
  }

  @override
  void didUpdateWidget(TAccordion oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (oldWidget.controller != widget.controller) {
      widget.controller?.open = _open;
      widget.controller?.close = _close;
    }
  }

  @override
  Widget build(BuildContext context) => Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      GestureDetector(
        onTap: _toggleCollapsed,
        child: MouseRegion(
          cursor: SystemMouseCursors.click,
          child: DecoratedBox(
            decoration: BoxDecoration(
              borderRadius: widget.titleBorderRadius,
              border: widget.titleBorder,
              color: _showAccordion
                  ? widget.expandedTitleBackgroundColor
                  : widget.collapsedTitleBackgroundColor,
            ),
            child: Padding(
              padding: widget.titlePadding,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  RotationTransition(
                    turns: _turnsAnimation,
                    child: const Icon(Symbols.keyboard_arrow_right_rounded),
                  ),
                  Expanded(child: widget.titleChild),
                ],
              ),
            ),
          ),
        ),
      ),
      if (_buildChild)
        SizeTransition(
          sizeFactor: _sizeAnimation,
          child: DecoratedBox(
            decoration: BoxDecoration(
              borderRadius: widget.contentBorderRadius,
              border: widget.contentBorder,
              color: widget.contentBackgroundColor ?? Colors.white70,
            ),
            child: Padding(
              padding: widget.contentPadding,
              child: widget.contentChild,
            ),
          ),
        ),
    ],
  );

  void _toggleCollapsed() {
    switch (_animationController.status) {
      case AnimationStatus.completed:
        _close();
        break;
      case AnimationStatus.dismissed:
        _open();
        break;
      default:
    }
  }

  bool _open({VoidCallback? onOpenCompleted}) {
    if (_showAccordion) {
      return false;
    }
    if (onOpenCompleted != null) {
      _onOpenCompletedCallbacks.add(onOpenCompleted);
    }
    _animationController.forward();
    _buildChild = true;
    _showAccordion = true;
    widget.onToggleCollapsed?.call(true);
    setState(() {});
    return true;
  }

  bool _close() {
    if (!_showAccordion) {
      return false;
    }
    _animationController.reverse();
    _showAccordion = false;
    widget.onToggleCollapsed?.call(false);
    setState(() {});
    return true;
  }
}

class TAccordionController {
  TAccordionController({this.open, this.close});

  bool Function({VoidCallback? onOpenCompleted})? open;
  bool Function()? close;
}
