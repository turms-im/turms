import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../l10n/view_models/date_format_view_models.dart';
import '../../../themes/index.dart';

import '../index.dart';

enum RangePosition { start, end, middle, none }

class TDateCell extends ConsumerStatefulWidget {
  TDateCell({
    super.key,
    required this.date,
    required this.isToday,
    required this.inCurrentCalendarMonth,
    required this.selectRangePosition,
    required this.disableRangePosition,
    required this.onTap,
    this.onMouseRegionEntered,
    this.onMouseRegionExited,
  }) : day = date.day.toString();

  final DateTime date;
  final String day;
  final bool isToday;
  final bool inCurrentCalendarMonth;
  final RangePosition selectRangePosition;

  final RangePosition disableRangePosition;
  final ValueChanged<DateTime> onTap;
  final ValueChanged<DateTime>? onMouseRegionEntered;
  final ValueChanged<DateTime>? onMouseRegionExited;

  @override
  ConsumerState<TDateCell> createState() => _TDateCellState();
}

class _TDateCellState extends ConsumerState<TDateCell> {
  bool _isHovered = false;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    Widget child;
    final disabled = widget.disableRangePosition != RangePosition.none;
    if (disabled) {
      child = Row(
        children: [
          Expanded(
            child: SizedBox(
              height: 24,
              child: DecoratedBox(
                decoration: BoxDecoration(
                  color: const Color.fromARGB(255, 245, 245, 245),
                  borderRadius: switch (widget.disableRangePosition) {
                    RangePosition.middle => null,
                    RangePosition.start => const BorderRadius.horizontal(
                      left: Radius.circular(4),
                    ),
                    RangePosition.end => const BorderRadius.horizontal(
                      right: Radius.circular(4),
                    ),
                    RangePosition.none => throw AssertionError(
                      'Should not reach here',
                    ),
                  },
                ),
                child: SizedBox(
                  width: 24,
                  height: 24,
                  child: DecoratedBox(
                    decoration: BoxDecoration(
                      border: widget.isToday
                          ? Border.all(color: theme.disabledColor)
                          : null,
                      borderRadius: Sizes.borderRadiusCircular4,
                    ),
                    child: Center(
                      child: Text(
                        widget.day,
                        style: TextStyle(color: theme.disabledColor),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      );
    } else if (!widget.inCurrentCalendarMonth) {
      child = Row(
        children: [
          Expanded(
            child: SizedBox(
              height: 24,
              child: DecoratedBox(
                decoration: const BoxDecoration(),
                child: UnconstrainedBox(
                  child: SizedBox(
                    width: 24,
                    height: 24,
                    child: DecoratedBox(
                      decoration: BoxDecoration(
                        borderRadius: Sizes.borderRadiusCircular4,
                        color: _isHovered
                            ? const Color.fromARGB(255, 245, 245, 245)
                            : null,
                      ),
                      child: Center(
                        child: Text(
                          widget.day,
                          style: const TextStyle(color: Colors.black26),
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      );
    } else {
      const colorSelectionRangeMiddle = Color.fromARGB(255, 230, 244, 255);
      child = Row(
        children: [
          Expanded(
            child: SizedBox(
              height: 24,
              child: DecoratedBox(
                decoration: switch (widget.selectRangePosition) {
                  RangePosition.none => const BoxDecoration(),
                  RangePosition.middle => const BoxDecoration(
                    color: colorSelectionRangeMiddle,
                  ),
                  RangePosition.start => const BoxDecoration(
                    gradient: LinearGradient(
                      stops: [0.5, 0.5],
                      colors: [Colors.transparent, colorSelectionRangeMiddle],
                    ),
                    borderRadius: BorderRadius.horizontal(
                      left: Radius.circular(4),
                    ),
                  ),
                  RangePosition.end => const BoxDecoration(
                    gradient: LinearGradient(
                      stops: [0.5, 0.5],
                      colors: [colorSelectionRangeMiddle, Colors.transparent],
                    ),
                    borderRadius: BorderRadius.horizontal(
                      right: Radius.circular(4),
                    ),
                  ),
                },
                child: UnconstrainedBox(
                  child: SizedBox(
                    width: 24,
                    height: 24,
                    child: DecoratedBox(
                      decoration: BoxDecoration(
                        border: widget.isToday
                            ? Border.all(color: theme.primaryColor)
                            : null,
                        borderRadius: switch (widget.selectRangePosition) {
                          RangePosition.middle => null,
                          RangePosition.start => const BorderRadius.horizontal(
                            left: Radius.circular(4),
                          ),
                          RangePosition.end => const BorderRadius.horizontal(
                            right: Radius.circular(4),
                          ),
                          RangePosition.none => Sizes.borderRadiusCircular4,
                        },
                        color: _isHovered
                            ? theme.primaryColor
                            : switch (widget.selectRangePosition) {
                                RangePosition.none => null,
                                RangePosition.middle => const Color.fromARGB(
                                  255,
                                  230,
                                  244,
                                  255,
                                ),
                                RangePosition.start => theme.primaryColor,
                                RangePosition.end => theme.primaryColor,
                              },
                      ),
                      child: Center(
                        child: Text(
                          widget.day,
                          style: TextStyle(
                            color: _isHovered
                                ? Colors.white
                                : switch (widget.selectRangePosition) {
                                    RangePosition.none => null,
                                    RangePosition.middle => null,
                                    RangePosition.start => Colors.white,
                                    RangePosition.end => Colors.white,
                                  },
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      );
    }
    return MouseRegion(
      cursor: disabled
          ? SystemMouseCursors.forbidden
          : SystemMouseCursors.click,
      onEnter: (event) {
        if (!disabled) {
          setState(() {
            _isHovered = true;
          });
          if (widget.inCurrentCalendarMonth) {
            widget.onMouseRegionEntered?.call(widget.date);
          }
        }
      },
      onExit: (event) {
        if (!disabled) {
          setState(() {
            _isHovered = false;
          });
          if (widget.inCurrentCalendarMonth) {
            widget.onMouseRegionExited?.call(widget.date);
          }
        }
      },
      child: GestureDetector(
        onTap: () {
          if (!disabled) {
            widget.onTap(widget.date);
          }
        },
        child: TTooltip(
          message: ref.watch(dateFormatViewModel_yMd).format(widget.date),
          waitDuration: const Duration(milliseconds: 1000),
          child: child,
        ),
      ),
    );
  }
}
