import 'package:flutter/material.dart';

import '../../../themes/sizes.dart';

class TLazyIndexedStack extends StatefulWidget {
  const TLazyIndexedStack({
    super.key,
    this.alignment = AlignmentDirectional.topStart,
    this.textDirection,
    this.sizing = StackFit.loose,
    this.index = 0,
    this.children = const [],
  });

  final AlignmentGeometry alignment;
  final TextDirection? textDirection;
  final StackFit sizing;
  final int index;
  final List<Widget> children;

  @override
  TLazyIndexedStackState createState() => TLazyIndexedStackState();
}

class TLazyIndexedStackState extends State<TLazyIndexedStack> {
  late List<bool> _indexToActiveState = _initIndexToActiveStateList();

  List<bool> _initIndexToActiveStateList() =>
      List<bool>.generate(widget.children.length, (i) => i == widget.index);

  @override
  void didUpdateWidget(TLazyIndexedStack oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (oldWidget.children.length != widget.children.length) {
      _indexToActiveState = _initIndexToActiveStateList();
    }
  }

  @override
  Widget build(BuildContext context) {
    final indexToActiveState = _indexToActiveState;
    final index = widget.index;
    indexToActiveState[index] = true;
    final children = List.generate(indexToActiveState.length,
        (i) => indexToActiveState[i] ? widget.children[i] : Sizes.sizedBox0);
    return IndexedStack(
      alignment: widget.alignment,
      sizing: widget.sizing,
      textDirection: widget.textDirection,
      index: index,
      children: children,
    );
  }
}
