import 'package:flutter/material.dart';

import '../../../../infra/animation/animation_utils.dart';
import '../../../../infra/animation/dismissed_status_change_type.dart';

class TToastView extends StatefulWidget {
  const TToastView({
    super.key,
    required this.duration,
    required this.onDismissed,
    this.fadeDuration = 500,
    required this.child,
  });

  final Widget child;
  final Duration duration;
  final int fadeDuration;
  final void Function() onDismissed;

  @override
  TToastViewState createState() => TToastViewState();
}

class TToastViewState extends State<TToastView>
    with SingleTickerProviderStateMixin {
  AnimationController? _animationController;
  late Animation<double> _fadeAnimation;
  AnimationStatus _animationStatus = AnimationStatus.dismissed;

  @override
  void initState() {
    super.initState();
    _animationController =
        AnimationController(
          vsync: this,
          duration: Duration(milliseconds: widget.fadeDuration),
        )..addStatusListener((status) {
          switch (AnimationUtils.detectDismissedStatusChange(
            _animationStatus,
            status,
          )) {
            case DismissedStatusChangeType.becomeDismissed:
              widget.onDismissed();
            case DismissedStatusChangeType.becomeNotDismissed:
              Future<void>.delayed(widget.duration, () {
                if (mounted &&
                    _animationController?.status == AnimationStatus.completed) {
                  _hideAnimation();
                }
              });
            case DismissedStatusChangeType.noChange:
              break;
          }
          _animationStatus = status;
        });
    _fadeAnimation = CurvedAnimation(
      parent: _animationController!,
      curve: Curves.easeIn,
    );
    super.initState();

    _showAnimation();
  }

  @override
  void deactivate() {
    _animationController?.stop();
    super.deactivate();
  }

  @override
  void dispose() {
    _animationController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => FadeTransition(
    opacity: _fadeAnimation,
    child: Center(
      child: Material(color: Colors.transparent, child: widget.child),
    ),
  );

  void _showAnimation() {
    _animationController!.forward();
  }

  void _hideAnimation() {
    _animationController!.reverse();
  }
}
