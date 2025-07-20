part of 't_date_range_picker.dart';

class _TDateRangePickerPanel extends StatefulWidget {
  const _TDateRangePickerPanel({
    required this.availableStartDate,
    required this.availableEndDate,
    required this.hoveredStartDate,
    required this.hoveredEndDate,
    required this.initialDateRange,
    required this.onDateChanged,
    required this.onMouseRegionEntered,
    required this.onMouseRegionExited,
  });

  final DateTime availableStartDate;
  final DateTime availableEndDate;
  final DateTimeRange initialDateRange;
  final DateTime? hoveredStartDate;
  final DateTime? hoveredEndDate;

  final ValueChanged<DateTime> onDateChanged;
  final ValueChanged<DateTime> onMouseRegionEntered;
  final ValueChanged<DateTime> onMouseRegionExited;

  @override
  State<_TDateRangePickerPanel> createState() => _TDateRangePickerPanelState();
}

class _TDateRangePickerPanelState extends State<_TDateRangePickerPanel> {
  late DateTime _calenderDate;
  late DateTime _selectedStartDate;
  late DateTime _selectedEndDate;

  @override
  void initState() {
    super.initState();
    final initialDateRange = widget.initialDateRange;
    _calenderDate = initialDateRange.start;
    _selectedStartDate = initialDateRange.start;
    _selectedEndDate = initialDateRange.end;
  }

  @override
  Widget build(BuildContext context) => Material(
    child: SizedBox(
      width: Sizes.dateRangePickerWidth,
      height: Sizes.dateRangePickerHeight,
      child: DecoratedBox(
        decoration: context.appThemeExtension.popupDecoration,
        child: Row(
          children: [
            Expanded(
              child: TDatePicker(
                calendarDate: _calenderDate,
                availableStartDate: widget.availableStartDate,
                availableEndDate: widget.availableEndDate,
                selectedStartDate: _selectedStartDate,
                selectedEndDate: _selectedEndDate,
                hoveredStartDate: widget.hoveredStartDate,
                hoveredEndDate: widget.hoveredEndDate,
                showNextButtons: false,
                onCalendarDateChanged: (value) =>
                    setState(() => _calenderDate = value),
                onDateChanged: widget.onDateChanged,
                onMouseRegionEntered: widget.onMouseRegionEntered,
                onMouseRegionExited: widget.onMouseRegionExited,
              ),
            ),
            Expanded(
              child: TDatePicker(
                calendarDate: DateUtils.addMonthsToMonthDate(_calenderDate, 1),
                availableStartDate: widget.availableStartDate,
                availableEndDate: widget.availableEndDate,
                selectedStartDate: _selectedStartDate,
                selectedEndDate: _selectedEndDate,
                hoveredStartDate: widget.hoveredStartDate,
                hoveredEndDate: widget.hoveredEndDate,
                showPrevButtons: false,
                onCalendarDateChanged: (value) => setState(
                  () => _calenderDate = DateTime(value.year, value.month - 1),
                ),
                onDateChanged: widget.onDateChanged,
                onMouseRegionEntered: widget.onMouseRegionEntered,
                onMouseRegionExited: widget.onMouseRegionExited,
              ),
            ),
          ],
        ),
      ),
    ),
  );
}
