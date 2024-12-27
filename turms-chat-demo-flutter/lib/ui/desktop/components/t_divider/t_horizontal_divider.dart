import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class THorizontalDivider extends StatelessWidget {
  const THorizontalDivider({Key? key, this.color, this.thickness = 1.0})
      : super(key: key);

  final Color? color;
  final double thickness;

  @override
  Widget build(BuildContext context) => SizedBox(
      width: double.infinity,
      height: thickness,
      child: DecoratedBox(
          decoration: BoxDecoration(
        color: color ?? context.theme.dividerColor,
      )));
}

class TMovableHorizontalDivider extends StatefulWidget {
  const TMovableHorizontalDivider(
      {super.key, this.color, this.onMove, required this.onMoved});

  final Color? color;
  final VoidCallback? onMove;
  final ValueChanged<double> onMoved;

  @override
  State<TMovableHorizontalDivider> createState() =>
      _TMovableHorizontalDividerState();
}

class _TMovableHorizontalDividerState extends State<TMovableHorizontalDivider> {
  bool _isResizing = false;
  double _dyOnPointerDown = 0;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
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
        _dyOnPointerDown = event.position.dy;
        _isResizing = true;
        setState(() {});
      },
      onPointerMove: (event) {
        final delta = ((event.position.dy - _dyOnPointerDown) / Sizes.unit)
                .floorToDouble() *
            Sizes.unit;
        widget.onMoved(delta);
      },
      child: MouseRegion(
        cursor: SystemMouseCursors.resizeUpDown,
        child: _isResizing
            ? Padding(
                padding: Sizes.paddingV2,
                child: THorizontalDivider(
                  color: theme.primaryColor,
                  thickness: 5,
                ),
              )
            : Padding(
                padding: Sizes.paddingV4,
                child: THorizontalDivider(
                  color: widget.color,
                ),
              ),
      ),
    );
  }
}
