import 'package:flutter/material.dart';

const badgeDimension = 10.0;

class TBadge extends StatelessWidget {
  const TBadge({super.key, this.count});

  final int? count;

  @override
  Widget build(BuildContext context) {
    Widget? child;
    if (count case final count?) {
      child = Center(
        child: Text(
          count.toString(),
          style: const TextStyle(color: Colors.white, fontSize: 12),
        ),
      );
    }
    return ClipOval(
      child: ColoredBox(
        color: Colors.red,
        child: SizedBox.square(dimension: badgeDimension, child: child),
      ),
    );
  }
}
