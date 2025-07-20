import 'dart:math';
import 'dart:ui';

import '../units/math_extensions.dart';

extension RectExtensions on Rect {
  Rect scale(double scale) =>
      Rect.fromLTRB(left * scale, top * scale, right * scale, bottom * scale);

  Rect flip(Offset containerCenter) => Rect.fromLTRB(
    containerCenter.dx - (right - containerCenter.dx),
    containerCenter.dy - (bottom - containerCenter.dy),
    containerCenter.dx - (left - containerCenter.dx),
    containerCenter.dy - (top - containerCenter.dy),
  );

  Rect flipX(Offset containerCenter) => Rect.fromLTRB(
    containerCenter.dx - (right - containerCenter.dx),
    top,
    containerCenter.dx - (left - containerCenter.dx),
    bottom,
  );

  Rect flipY(Offset containerCenter) => Rect.fromLTRB(
    left,
    containerCenter.dy - (bottom - containerCenter.dy),
    right,
    containerCenter.dy - (top - containerCenter.dy),
  );

  Rect rotate(Offset containerCenter, double angleDegrees) {
    final angle = angleDegrees.degreesToRadians();
    final cosVal = cos(angle);
    final sinVal = sin(angle);
    final x1 =
        containerCenter.dx +
        (left - containerCenter.dx) * cosVal -
        (top - containerCenter.dy) * sinVal;
    final y1 =
        containerCenter.dy +
        (left - containerCenter.dx) * sinVal +
        (top - containerCenter.dy) * cosVal;
    final x2 =
        containerCenter.dx +
        (right - containerCenter.dx) * cosVal -
        (top - containerCenter.dy) * sinVal;
    final y2 =
        containerCenter.dy +
        (right - containerCenter.dx) * sinVal +
        (top - containerCenter.dy) * cosVal;
    final x3 =
        containerCenter.dx +
        (right - containerCenter.dx) * cosVal -
        (bottom - containerCenter.dy) * sinVal;
    final y3 =
        containerCenter.dy +
        (right - containerCenter.dx) * sinVal +
        (bottom - containerCenter.dy) * cosVal;
    final x4 =
        containerCenter.dx +
        (left - containerCenter.dx) * cosVal -
        (bottom - containerCenter.dy) * sinVal;
    final y4 =
        containerCenter.dy +
        (left - containerCenter.dx) * sinVal +
        (bottom - containerCenter.dy) * cosVal;
    return Rect.fromLTRB(
      min(x1, min(x2, min(x3, x4))),
      min(y1, min(y2, min(y3, y4))),
      max(x1, max(x2, max(x3, x4))),
      max(y1, max(y2, max(y3, y4))),
    );
  }
}
