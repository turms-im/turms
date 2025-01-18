import 'package:flutter/material.dart';

import '../../../../infra/units/math_extensions.dart';

class TTransform extends StatelessWidget {
  const TTransform(
      {Key? key,
      required this.flipX,
      required this.flipY,
      required this.rotationAngle,
      required this.child})
      : super(key: key);

  final bool flipX;
  final bool flipY;
  final double rotationAngle;
  final Widget child;

  @override
  Widget build(BuildContext context) {
    var content = child;
    if (rotationAngle != 0) {
      content = Transform.rotate(
        angle: rotationAngle.degreesToRadians(),
        child: content,
      );
    }
    if (flipX || flipY) {
      content = Transform.flip(
        flipX: flipX,
        flipY: flipY,
        child: content,
      );
    }
    return content;
  }
}
