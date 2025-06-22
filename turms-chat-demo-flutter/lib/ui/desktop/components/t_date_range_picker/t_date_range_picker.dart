import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../l10n/view_models/date_format_view_models.dart';
import '../../../themes/index.dart';

import '../index.dart';

part 't_date_range_input.dart';

part 't_date_range_picker_panel.dart';

class TDateRangePicker extends StatefulWidget {
  TDateRangePicker({
    super.key,
    required this.firstDate,
    required this.lastDate,
    required this.initialDateRange,
  });

  final DateTime firstDate;
  final DateTime lastDate;
  final DateTimeRange initialDateRange;

  final _popupController = TPopupController();

  @override
  State<TDateRangePicker> createState() => _TDateRangePickerState();
}

const _dateRangePickerGroupId = 'dateRangePicker';

class _TDateRangePickerState extends State<TDateRangePicker> {
  late FocusNode _startDateFocusNode;
  late FocusNode _endDateFocusNode;

  DateTime? _selectedStartDate;
  DateTime? _selectedEndDate;

  DateTime? _hoveredStartDate;
  DateTime? _hoveredEndDate;

  @override
  void initState() {
    super.initState();
    _startDateFocusNode = FocusNode()..addListener(_onFocusChanged);
    _endDateFocusNode = FocusNode()..addListener(_onFocusChanged);
    _selectedStartDate = widget.initialDateRange.start;
    _selectedEndDate = widget.initialDateRange.end;
  }

  @override
  void dispose() {
    _startDateFocusNode.dispose();
    _endDateFocusNode.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => TPopup(
    controller: widget._popupController,
    targetAnchor: Alignment.bottomCenter,
    followerAnchor: Alignment.topCenter,
    offset: const Offset(0, 4),
    target: TapRegion(
      groupId: _dateRangePickerGroupId,
      child: _TDateRangeInput(
        startDate: _selectedStartDate,
        startDateFocusNode: _startDateFocusNode,
        previewStartDate: _hoveredStartDate,
        endDate: _selectedEndDate,
        endDateFocusNode: _endDateFocusNode,
        previewEndDate: _hoveredEndDate,
      ),
    ),
    follower: TapRegion(
      groupId: _dateRangePickerGroupId,
      child: _TDateRangePickerPanel(
        availableStartDate: widget.firstDate,
        availableEndDate: widget.lastDate,
        hoveredStartDate: _hoveredStartDate,
        hoveredEndDate: _hoveredEndDate,
        initialDateRange: widget.initialDateRange,
        onDateChanged: (DateTime value) {
          if (_startDateFocusNode.hasFocus) {
            _selectedStartDate = value;
            if (_selectedEndDate != null && value.isAfter(_selectedEndDate!)) {
              _selectedEndDate = null;
              _endDateFocusNode.requestFocus();
            } else {
              widget._popupController.hidePopover?.call();
            }
          } else {
            _selectedEndDate = value;
            if (_selectedStartDate != null &&
                value.isBefore(_selectedStartDate!)) {
              _selectedStartDate = null;
              _startDateFocusNode.requestFocus();
            } else {
              widget._popupController.hidePopover?.call();
            }
          }
          setState(() {});
        },
        onMouseRegionEntered: (DateTime value) {
          if (_startDateFocusNode.hasFocus) {
            _hoveredStartDate = value;
          } else {
            _hoveredEndDate = value;
          }
          setState(() {});
        },
        onMouseRegionExited: (DateTime value) {
          if (_startDateFocusNode.hasFocus) {
            if (_hoveredStartDate == value) {
              _hoveredStartDate = null;
            }
          } else {
            if (_hoveredEndDate == value) {
              _hoveredEndDate = null;
            }
          }
          setState(() {});
        },
      ),
    ),
    onDismissed: () {
      _startDateFocusNode.unfocus();
      _endDateFocusNode.unfocus();
    },
  );

  void _onFocusChanged() {
    if (_startDateFocusNode.hasFocus || _endDateFocusNode.hasFocus) {
      widget._popupController.showPopover?.call();
    } else {
      widget._popupController.hidePopover?.call();
    }
  }
}
