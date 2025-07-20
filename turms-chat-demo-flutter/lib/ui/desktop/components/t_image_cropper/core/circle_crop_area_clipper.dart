import 'package:flutter/material.dart';

class CircleCropAreaClipper extends CustomClipper<Path> {
  const CircleCropAreaClipper(this.rect);

  final Rect rect;

  @override
  Path getClip(Size size) => Path()
    ..addOval(Rect.fromCircle(center: rect.center, radius: rect.width / 2))
    ..addRect(Rect.fromLTWH(0.0, 0.0, size.width, size.height))
    ..fillType = PathFillType.evenOdd;

  @override
  bool shouldReclip(CustomClipper<Path> oldClipper) => true;
}
