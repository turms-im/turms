import 'package:flutter/material.dart';

class TFloating extends StatefulWidget {
  const TFloating({
    super.key,
    this.left,
    this.right,
    this.top,
    this.bottom,
    required this.child,
  });

  final double? left;
  final double? right;
  final double? top;
  final double? bottom;

  final Widget child;

  @override
  State<TFloating> createState() => _TFloatingState();
}

class _TFloatingState extends State<TFloating> {
  double _dxOnPointerDown = 0.0;
  double _dyOnPointerDown = 0.0;
  Offset _offset = Offset.zero;
  Offset _lastOffset = Offset.zero;

  @override
  Widget build(BuildContext context) => Positioned(
        left: widget.left,
        right: widget.right,
        top: widget.top,
        bottom: widget.bottom,
        child: Transform.translate(
          offset: _offset,
          child: Listener(
              onPointerDown: (PointerDownEvent event) {
                final position = event.position;
                _dxOnPointerDown = position.dx;
                _dyOnPointerDown = position.dy;
              },
              onPointerUp: (event) {
                _lastOffset = _offset;
              },
              onPointerMove: (event) {
                final position = event.position;
                final deltaX = position.dx - _dxOnPointerDown;
                final deltaY = position.dy - _dyOnPointerDown;
                _offset = _lastOffset + Offset(deltaX, deltaY);
                setState(() {});
              },
              child: Material(color: Colors.transparent, child: widget.child)),
        ),
      );
}

OverlayEntry showFloating({
  double? left,
  double? right,
  double? top,
  double? bottom,
  required Widget child,
}) =>
    OverlayEntry(
        builder: (BuildContext context) => TFloating(
            left: left, right: right, top: top, bottom: bottom, child: child));
