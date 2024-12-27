import 'package:flutter/material.dart';

import '../../../themes/index.dart';

class TRadio<T> extends StatefulWidget {
  const TRadio({
    Key? key,
    required this.value,
    required this.groupValue,
    required this.onChanged,
    this.size = 16,
    this.radioColor = const Color(0xff10DC60),
    this.activeBgColor = Colors.white,
    this.inactiveBgColor = Colors.white,
    this.activeBorderColor,
    this.inactiveBorderColor,
    this.toggleable = false,
    this.label,
  }) : super(key: key);

  final double size;

  final Color radioColor;

  final Color activeBgColor;

  final Color inactiveBgColor;

  final Color? activeBorderColor;

  final Color? inactiveBorderColor;

  final ValueChanged<T> onChanged;

  final T value;

  final T groupValue;

  final bool toggleable;
  final String? label;

  @override
  _TRadioState<T> createState() => _TRadioState<T>();
}

class _TRadioState<T> extends State<TRadio<T>> with TickerProviderStateMixin {
  bool _selected = false;
  T? _groupValue;

  void onStatusChange() {
    _groupValue = widget.value;
    _handleChanged(widget.value == _groupValue);
  }

  void _handleChanged(bool selected) {
    if (selected) {
      widget.onChanged(widget.value);
    }
  }

  @override
  Widget build(BuildContext context) {
    _selected = widget.value == widget.groupValue;
    final label = widget.label;
    final size = widget.size;
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTap: onStatusChange,
        child: Row(spacing: 8, mainAxisSize: MainAxisSize.min, children: [
          SizedBox(
            height: size,
            width: size,
            child: DecoratedBox(
              decoration: BoxDecoration(
                  color:
                      _selected ? widget.activeBgColor : widget.inactiveBgColor,
                  shape: BoxShape.circle,
                  border: Border.all(
                      color: _selected
                          ? (widget.activeBorderColor ??
                              context.theme.dividerColor)
                          : (widget.inactiveBorderColor ??
                              context.theme.dividerColor))),
              child: _selected
                  ? Center(
                      child: DecoratedBox(
                        decoration: BoxDecoration(
                            shape: BoxShape.circle, color: widget.radioColor),
                        child: SizedBox(width: size * 0.5, height: size * 0.5),
                      ),
                    )
                  : null,
            ),
          ),
          if (label != null) Text(label)
        ]),
      ),
    );
  }
}
