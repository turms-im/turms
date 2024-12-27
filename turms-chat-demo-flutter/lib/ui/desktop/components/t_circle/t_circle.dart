import 'package:flutter/material.dart';

class TCircle extends StatelessWidget {
  const TCircle(
      {super.key,
      this.size = 18,
      this.backgroundColor = Colors.blue,
      required this.child});

  final double size;
  final Color backgroundColor;
  final Widget child;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: size,
        width: size,
        child: DecoratedBox(
          decoration: BoxDecoration(
            color: backgroundColor,
            shape: BoxShape.circle,
          ),
          child: Center(
            child: child,
          ),
        ),
      );
}
