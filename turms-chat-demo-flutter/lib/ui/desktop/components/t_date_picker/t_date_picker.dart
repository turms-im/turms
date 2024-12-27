import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:material_symbols_icons/material_symbols_icons.dart';

import '../../../../infra/time/datetime_utils.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';
import '../index.dart';
import 't_date_cell.dart';

class TDatePicker extends ConsumerWidget {
  const TDatePicker(
      {Key? key,
      required this.calendarDate,
      this.availableStartDate,
      this.availableEndDate,
      this.selectedStartDate,
      this.selectedEndDate,
      this.hoveredStartDate,
      this.hoveredEndDate,
      this.showPrevButtons = true,
      this.showNextButtons = true,
      this.onCalendarDateChanged,
      this.onDateChanged,
      this.onMouseRegionEntered,
      this.onMouseRegionExited})
      : super(key: key);

  final DateTime calendarDate;
  final DateTime? availableStartDate;
  final DateTime? availableEndDate;
  final DateTime? selectedStartDate;
  final DateTime? selectedEndDate;
  final DateTime? hoveredStartDate;
  final DateTime? hoveredEndDate;
  final bool showPrevButtons;
  final bool showNextButtons;

  final ValueChanged<DateTime>? onCalendarDateChanged;
  final ValueChanged<DateTime>? onDateChanged;
  final ValueChanged<DateTime>? onMouseRegionEntered;
  final ValueChanged<DateTime>? onMouseRegionExited;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final localeName = ref.watch(appLocalizationsViewModel).localeName;
    final dateSymbols = DateFormat.EEEE(localeName).dateSymbols;
    final weekdays = dateSymbols.NARROWWEEKDAYS;
    final dateStr = DateFormat.yM(localeName).format(calendarDate);
    final thisMonthFirstDay = DateTime(calendarDate.year, calendarDate.month);
    final mostRecentWeekday = DateTimeUtils.getMostRecentWeekday(
        thisMonthFirstDay, dateSymbols.FIRSTDAYOFWEEK + 1);
    return Column(
      children: [
        _buildTitle(dateStr),
        const THorizontalDivider(),
        _buildBody(weekdays, mostRecentWeekday)
      ],
    );
  }

  Widget _buildTitle(String dateStr) => Padding(
        padding: Sizes.paddingV4H4,
        child: Row(children: [
          if (showPrevButtons)
            TIconButton(
              iconData: Symbols.keyboard_double_arrow_left_rounded,
              iconColor: Colors.black45,
              iconColorHovered: Colors.black,
              addContainer: false,
              onTap: () => onCalendarDateChanged?.call(
                DateTime(calendarDate.year - 1, calendarDate.month),
              ),
            ),
          if (showPrevButtons)
            TIconButton(
              iconData: Symbols.keyboard_arrow_left_rounded,
              iconColor: Colors.black45,
              iconColorHovered: Colors.black,
              addContainer: false,
              onTap: () => onCalendarDateChanged?.call(
                DateTime(calendarDate.year, calendarDate.month - 1),
              ),
            ),
          Expanded(
            child: Text(
              dateStr,
              textAlign: TextAlign.center,
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          if (showNextButtons)
            TIconButton(
              iconData: Symbols.keyboard_arrow_right_rounded,
              iconColor: Colors.black45,
              iconColorHovered: Colors.black,
              addContainer: false,
              onTap: () => onCalendarDateChanged?.call(
                DateTime(calendarDate.year, calendarDate.month + 1),
              ),
            ),
          if (showNextButtons)
            TIconButton(
              iconData: Symbols.keyboard_double_arrow_right_rounded,
              iconColor: Colors.black45,
              iconColorHovered: Colors.black,
              addContainer: false,
              onTap: () => onCalendarDateChanged?.call(
                DateTime(calendarDate.year + 1, calendarDate.month),
              ),
            ),
        ]),
      );

  Expanded _buildBody(List<String> weekdays, DateTime firstDay) {
    final now = DateTime.now();
    final children = <Widget>[
      for (final weekday in weekdays)
        Center(
          child: Text(
            weekday,
          ),
        ),
    ];
    DateTime date;
    final localAvailableStartDate = availableStartDate;
    final localAvailableEndDate = availableEndDate;
    final startDate = hoveredStartDate ?? selectedStartDate;
    final endDate = hoveredEndDate ?? selectedEndDate;
    for (var i = 0; i < DateTime.daysPerWeek * 6; i++) {
      date = DateTime(firstDay.year, firstDay.month, firstDay.day + i);
      children.add(TDateCell(
        date: date,
        isToday: DateUtils.isSameDay(date, now),
        inCurrentCalendarMonth: DateUtils.isSameMonth(date, calendarDate),
        selectRangePosition: _getSelectRangePosition(date, startDate, endDate),
        // hoverRangePosition: _getSelectRangePosition(
        //     date, localHoveredStartDate, localHoveredEndDate),
        disableRangePosition: _getDisableRangePosition(
            date, localAvailableStartDate, localAvailableEndDate),
        onTap: (DateTime value) {
          onDateChanged?.call(value);
        },
        onMouseRegionEntered: onMouseRegionEntered,
        onMouseRegionExited: onMouseRegionExited,
      ));
    }
    return Expanded(
      child: Padding(
        padding: Sizes.paddingH16,
        child: GridView.count(
          crossAxisCount: DateTime.daysPerWeek,
          children: children,
        ),
      ),
    );
  }

  RangePosition _getSelectRangePosition(
      DateTime date, DateTime? startDate, DateTime? endDate) {
    if (DateUtils.isSameDay(date, startDate)) {
      return RangePosition.start;
    }
    if (DateUtils.isSameDay(date, endDate)) {
      return RangePosition.end;
    }
    return DateTimeUtils.isBetween(date, startDate, endDate)
        ? RangePosition.middle
        : RangePosition.none;
  }

  RangePosition _getDisableRangePosition(
      DateTime date, DateTime? availableStartDate, DateTime? availableEndDate) {
    if (availableStartDate != null) {
      if (DateUtils.isSameDay(
          date, availableStartDate.subtract(const Duration(days: 1)))) {
        return RangePosition.end;
      } else if (date.isBefore(availableStartDate)) {
        return RangePosition.middle;
      }
    }
    if (availableEndDate != null) {
      if (DateUtils.isSameDay(
          date, availableEndDate.add(const Duration(days: 1)))) {
        return RangePosition.start;
      } else if (date.isAfter(availableEndDate)) {
        return RangePosition.middle;
      }
    }
    return RangePosition.none;
  }
}
