import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class TCheckbox extends StatefulWidget {
  const TCheckbox(
    this.initialValue,
    this.text, {
    super.key,
    required this.onCheckedChanged,
  });

  final bool initialValue;
  final String text;

  final void Function(bool checked) onCheckedChanged;

  @override
  State<StatefulWidget> createState() => _TCheckboxState();
}

class _TCheckboxState extends State<TCheckbox> {
  bool? _isChecked;

  @override
  Widget build(BuildContext context) {
    _isChecked ??= widget.initialValue;
    final isChecked = _isChecked!;
    final appThemeExtension = context.appThemeExtension;
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTap: () {
          _isChecked = !isChecked;
          widget.onCheckedChanged(isChecked);
          setState(() {});
        },
        child: Row(
          spacing: 4,
          mainAxisSize: MainAxisSize.min,
          children: [
            AbsorbPointer(
              child: Checkbox(
                // focusNode: FocusNode(skipTraversal: true),
                materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                visualDensity: const VisualDensity(
                  horizontal: -4,
                  vertical: -4,
                ),
                splashRadius: 0,
                side: BorderSide(
                  color: appThemeExtension.checkboxColor,
                  width: 2,
                ),
                checkColor: Colors.white,
                value: _isChecked,
                onChanged: (bool? value) {
                  setState(() {
                    _isChecked = !isChecked;
                  });
                  widget.onCheckedChanged(isChecked);
                },
              ),
            ),
            Text(widget.text, style: appThemeExtension.checkboxTextStyle),
          ],
        ),
      ),
    );
  }
}
