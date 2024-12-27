import 'dart:math';

class MathUtils {
  MathUtils._();

  static Point<double> calculatePoint(double centerX, double centerY,
      double radius, double distanceFromEdge, double angleInDegrees) {
    final angleInRadians = angleInDegrees * (pi / 180);
    final x = centerX + (radius - distanceFromEdge) * cos(angleInRadians);
    final y = centerY + (radius - distanceFromEdge) * sin(angleInRadians);
    return Point(x, y);
  }
}
