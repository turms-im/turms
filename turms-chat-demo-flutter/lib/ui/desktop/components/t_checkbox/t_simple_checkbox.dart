import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../themes/index.dart';

class TSimpleCheckbox extends StatefulWidget {
  const TSimpleCheckbox({
    super.key,
    this.size = 16,
    this.activeBackgroundColor = Colors.white,
    this.inactiveBackgroundColor = Colors.white,
    this.activeBorderColor,
    this.inactiveBorderColor,
    required this.value,
    this.activeIcon = const Icon(
      Symbols.check_rounded,
      size: 14,
      color: Color(0xff10DC60),
      weight: 800,
    ),
    this.inactiveIcon,
    this.customBgColor = const Color(0xff10DC60),
    this.label,
    required this.onChanged,
  });

  final double size;

  final Color activeBackgroundColor;

  final Color inactiveBackgroundColor;

  final Color? activeBorderColor;

  final Color? inactiveBorderColor;

  final ValueChanged<bool> onChanged;

  final bool value;

  final Widget activeIcon;

  final Widget? inactiveIcon;

  final Color customBgColor;

  final String? label;

  @override
  _TSimpleCheckboxState createState() => _TSimpleCheckboxState();
}

class _TSimpleCheckboxState extends State<TSimpleCheckbox> {
  @override
  Widget build(BuildContext context) {
    Widget child = SizedBox(
      height: widget.size,
      width: widget.size,
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: widget.value
              ? widget.activeBackgroundColor
              : widget.inactiveBackgroundColor,
          borderRadius: Sizes.borderRadiusCircular4,
          border: Border.all(
            color: widget.value
                ? (widget.activeBorderColor ?? context.theme.dividerColor)
                : (widget.inactiveBorderColor ?? context.theme.dividerColor),
          ),
        ),
        child: widget.value ? widget.activeIcon : widget.inactiveIcon,
      ),
    );
    if (widget.label case final label?) {
      child = Row(
        spacing: 8,
        mainAxisSize: MainAxisSize.min,
        children: [child, Text(label)],
      );
    }
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTap: () {
          widget.onChanged(!widget.value);
        },
        child: child,
      ),
    );
  }
}
