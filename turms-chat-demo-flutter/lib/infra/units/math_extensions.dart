import 'dart:math';

const _degreesToRadians = pi / 180;

extension IntMathExtension on int {
  double degreesToRadians() => _degreesToRadians * this;
}

extension DoubleMathExtension on double {
  double degreesToRadians() => _degreesToRadians * this;
}
