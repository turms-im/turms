import 'package:flutter/material.dart';

class TTableColumnOption {
  const TTableColumnOption({required this.width});

  final double width;
}

class TTableRow {
  const TTableRow({
    this.decoration,
    required this.cells,
    this.onTap,
    this.onDoubleTap,
  });

  final BoxDecoration? decoration;
  final List<TTableDataCell> cells;
  final VoidCallback? onTap;
  final VoidCallback? onDoubleTap;
}

class TTableDataCell {
  const TTableDataCell({
    this.alignment = Alignment.centerLeft,
    required this.widget,
  });

  final Alignment alignment;
  final Widget widget;
}

class TTable extends StatelessWidget {
  const TTable({
    super.key,
    required this.header,
    required this.rows,
    required this.columnOptions,
  });

  final TTableRow header;
  final List<TTableRow> rows;
  final List<TTableColumnOption> columnOptions;

  @override
  Widget build(BuildContext context) => LayoutBuilder(
    builder: (BuildContext context, BoxConstraints constraints) {
      final maxWidth = constraints.maxWidth;
      if (maxWidth == double.infinity) {
        throw StateError('The max width cannot be infinity');
      }
      final count = columnOptions.length;
      final widths = <double>[];
      var remainingWidth = maxWidth;
      for (var i = 0; i < count; i++) {
        if (i == count - 1) {
          widths.add(remainingWidth.truncateToDouble());
        } else {
          final width = (maxWidth * columnOptions[i].width).truncateToDouble();
          widths.add(width);
          remainingWidth -= width;
        }
      }
      return Column(
        children: <Widget>[
          _TTableRowView(row: header, widths: widths, columnCount: count),
          Expanded(
            child: ListView.builder(
              addAutomaticKeepAlives: false,
              itemCount: rows.length,
              itemBuilder: (context, index) => _TTableRowView(
                row: rows[index],
                widths: widths,
                columnCount: count,
              ),
            ),
          ),
        ],
      );
    },
  );
}

class _TTableRowView extends StatefulWidget {
  const _TTableRowView({
    required this.row,
    required this.widths,
    required this.columnCount,
  });

  final TTableRow row;
  final List<double> widths;
  final int columnCount;

  @override
  State<_TTableRowView> createState() => _TTableRowViewState();
}

class _TTableRowViewState extends State<_TTableRowView> {
  bool _isHovered = false;

  @override
  Widget build(BuildContext context) {
    final row = widget.row;
    final widths = widget.widths;
    final onTap = row.onTap;
    final onDoubleTap = row.onDoubleTap;
    final child = GestureDetector(
      onTap: () => onTap?.call(),
      onDoubleTap: () => onDoubleTap?.call(),
      child: MouseRegion(
        cursor: onTap == null && onDoubleTap == null
            ? SystemMouseCursors.basic
            : SystemMouseCursors.click,
        onEnter: (_) {
          _isHovered = true;
          setState(() {});
        },
        onExit: (_) {
          _isHovered = false;
          setState(() {});
        },
        child: Row(
          children: List.generate(
            widget.columnCount,
            (index) => _buildCell(row, index, widths[index]),
          ),
        ),
      ),
    );
    // TODO
    // final decoration = row.decoration;
    final decoration = _isHovered
        ? BoxDecoration(color: Colors.grey.shade200)
        : null;
    return SizedBox(
      height: 48,
      child: RepaintBoundary(
        child: decoration == null
            ? child
            : DecoratedBox(decoration: decoration, child: child),
      ),
    );
  }

  Widget _buildCell(TTableRow row, int index, double width) {
    final cell = row.cells[index];
    return Align(
      alignment: cell.alignment,
      child: SizedBox(
        width: width,
        child: Padding(
          // padding: const EdgeInsets.symmetric(horizontal: 10),
          padding: EdgeInsets.zero,
          child: cell.widget,
        ),
      ),
    );
  }
}
