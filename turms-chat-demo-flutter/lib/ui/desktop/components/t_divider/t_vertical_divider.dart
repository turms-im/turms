import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class TVerticalDivider extends StatelessWidget {
  const TVerticalDivider({Key? key, this.color, this.thickness = 1.0})
      : super(key: key);

  final Color? color;
  final double thickness;

  @override
  Widget build(BuildContext context) => SizedBox(
      width: thickness,
      height: double.infinity,
      child: DecoratedBox(
          decoration: BoxDecoration(
        color: color ?? context.theme.dividerColor,
      )));
}

class TMovableVerticalDivider extends StatefulWidget {
  const TMovableVerticalDivider(
      {super.key,
      this.color,
      this.size = TMovableVerticalDividerSize.medium,
      this.onMove,
      required this.onMoved});

  final Color? color;
  final TMovableVerticalDividerSize size;
  final VoidCallback? onMove;
  final ValueChanged<double> onMoved;

  @override
  State<TMovableVerticalDivider> createState() =>
      _TMovableVerticalDividerState();
}

class _TMovableVerticalDividerState extends State<TMovableVerticalDivider> {
  bool _isResizing = false;
  double _dxOnPointerDown = 0;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final size = widget.size;
    return Listener(
      onPointerCancel: (event) {
        _isResizing = false;
        setState(() {});
      },
      onPointerUp: (event) {
        _isResizing = false;
        setState(() {});
      },
      onPointerDown: (PointerDownEvent event) {
        widget.onMove?.call();
        _dxOnPointerDown = event.position.dx;
        _isResizing = true;
        setState(() {});
      },
      onPointerMove: (event) {
        final delta = event.position.dx - _dxOnPointerDown;
        widget.onMoved(delta);
      },
      child: MouseRegion(
        cursor: SystemMouseCursors.resizeLeftRight,
        child: _isResizing
            ? Padding(
                padding: size.paddingOnResizing,
                child: TVerticalDivider(
                  color: theme.primaryColor,
                  thickness: size.thicknessOnResizing,
                ),
              )
            : Padding(
                padding: size.padding,
                child: TVerticalDivider(
                  color: widget.color,
                  thickness: size.thickness,
                ),
              ),
      ),
    );
  }
}

enum TMovableVerticalDividerSize {
  medium(Sizes.paddingH4, 1.0, Sizes.paddingH2, 5.0),
  small(Sizes.paddingH2, 1.0, Sizes.paddingH1, 3.0);

  const TMovableVerticalDividerSize(this.padding, this.thickness,
      this.paddingOnResizing, this.thicknessOnResizing);

  final EdgeInsets padding;
  final double thickness;

  final EdgeInsets paddingOnResizing;
  final double thicknessOnResizing;
}
