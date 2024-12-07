import 'package:flutter/cupertino.dart';

class TSwitch extends StatelessWidget {
  const TSwitch({super.key, required this.value, required this.onChanged});

  final bool value;
  final ValueChanged<bool> onChanged;

  @override
  Widget build(BuildContext context) => SizedBox(
        height: 24,
        child: FittedBox(
          child: CupertinoSwitch(
            value: value,
            onChanged: onChanged,
          ),
        ),
      );
}
