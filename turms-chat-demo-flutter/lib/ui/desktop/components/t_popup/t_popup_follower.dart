import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../../../../infra/animation/animation_extensions.dart';
import '../../../../infra/io/global_keyboard_listener.dart';

class TPopupFollowerController {
  bool visible = false;

  void Function()? show;
  void Function()? hide;
}

class TPopupFollower extends StatefulWidget {
  const TPopupFollower({
    super.key,
    this.animate = true,
    this.controller,
    required this.onDismissed,
    required this.child,
  });

  final bool animate;
  final TPopupFollowerController? controller;
  final void Function() onDismissed;
  final Widget child;

  @override
  State<TPopupFollower> createState() => _TPopupFollowerState();
}

class _TPopupFollowerState extends State<TPopupFollower>
    with SingleTickerProviderStateMixin {
  AnimationController? _animationController;

  Animation<double>? animation;
  AnimationStatus _animationStatus = AnimationStatus.dismissed;

  @override
  void initState() {
    super.initState();

    if (widget.animate) {
      final animationController = AnimationController(
        vsync: this,
        duration: const Duration(milliseconds: 150),
        reverseDuration: const Duration(milliseconds: 150),
      )..addStatusListener(_handleStatusChanged);
      _animationController = animationController;
      animation = CurvedAnimation(
        parent: animationController,
        curve: Curves.fastOutSlowIn,
      );
    }

    final controller = widget.controller;
    if (controller != null) {
      controller
        ..show = _show
        ..hide = _hide;
    }

    _show();
  }

  void _handleStatusChanged(AnimationStatus status) {
    assert(mounted);
    final isVisible = _isTooltipVisible(status);
    switch ((_isTooltipVisible(_animationStatus), isVisible)) {
      case (true, false):
        widget.onDismissed();
      case (false, true):
      // widget.onDisplay();
      case (true, true) || (false, false):
        break;
    }
    _animationStatus = status;
    widget.controller?.visible = isVisible;
  }

  static bool _isTooltipVisible(AnimationStatus status) =>
      status.isNotDismissed;

  @override
  Widget build(BuildContext context) {
    final localAnimation = animation;
    final child = SingleChildScrollView(child: widget.child);
    return GlobalKeyboardListener(
      onKeyEvent: (KeyEvent event) {
        if (event is KeyDownEvent &&
            event.logicalKey == LogicalKeyboardKey.escape) {
          final controller = _animationController;
          if (controller == null || controller.isCompleted) {
            _hide();
            return true;
          }
        }
        return false;
      },
      child: localAnimation == null
          ? child
          : FadeTransition(opacity: localAnimation, child: child),
    );
  }

  @override
  void dispose() {
    _animationController?.dispose();
    super.dispose();
  }

  @override
  void didUpdateWidget(TPopupFollower oldWidget) {
    super.didUpdateWidget(oldWidget);
    // TODO: handle the change of animate
    final oldController = oldWidget.controller;
    final currentController = widget.controller;
    if (currentController == oldController) {
      return;
    }
    if (oldController != null) {
      oldController
        ..show = null
        ..hide = null;
    }
    if (currentController != null) {
      currentController
        ..show = _show
        ..hide = _hide;
    }
  }

  Future<void> _show() async {
    if (mounted) {
      if (widget.animate) {
        await _animationController!.forward();
      } else {
        widget.controller?.visible = true;
      }
    }
  }

  Future<void> _hide() async {
    if (!mounted) {
      return;
    }

    if (widget.animate) {
      await _animationController!.reverse();
    } else {
      widget.controller?.visible = false;
      widget.onDismissed();
    }
  }
}
