import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../themes/index.dart';
import '../t_popup/t_popup.dart';
import '../t_text_field/t_text_field.dart';
import 't_menu.dart';

class TMenuPopup<T> extends StatefulWidget {
  TMenuPopup(
      {super.key,
      this.value,
      required this.entries,
      this.constrainFollowerWithTargetWidth = true,
      this.targetAnchor = Alignment.bottomCenter,
      this.followerAnchor = Alignment.topCenter,
      this.offset = Offset.zero,
      this.padding = Sizes.paddingV8H8,
      this.onSelected,
      required this.anchor});

  final T? value;
  final List<TMenuEntry<T>> entries;
  final bool constrainFollowerWithTargetWidth;
  final Alignment targetAnchor;
  final Alignment followerAnchor;
  final Offset offset;
  final EdgeInsets padding;
  final void Function(TMenuEntry<T> item)? onSelected;
  final Widget anchor;

  @override
  State<TMenuPopup<T>> createState() => _TMenuPopupState<T>();
}

class _TMenuPopupState<T> extends State<TMenuPopup<T>> {
  late TPopupController _popupController;
  late FocusNode _focusNode;

  @override
  void initState() {
    super.initState();
    _popupController = TPopupController();
    _focusNode = FocusNode();
  }

  @override
  void dispose() {
    _focusNode.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => TPopup(
      controller: _popupController,
      constrainFollowerWithTargetWidth: widget.constrainFollowerWithTargetWidth,
      targetAnchor: widget.targetAnchor,
      followerAnchor: widget.followerAnchor,
      offset: widget.offset,
      target: widget.anchor,
      onShow: () {
        _focusNode.requestFocus();
      },
      followerBorderRadius:
          context.appThemeExtension.popupDecoration.borderRadius!,
      follower: TMenu(
        focusNode: _focusNode,
        value: widget.value,
        entries: widget.entries,
        padding: widget.padding,
        onSelected: (item) {
          _popupController.hidePopover?.call();
          widget.onSelected?.call(item);
        },
      ));
}

class TTextFieldMenuPopup<T> extends StatefulWidget {
  const TTextFieldMenuPopup(
      {super.key, this.value, required this.entries, required this.onSelected});

  final T? value;
  final List<TMenuEntry<T>> entries;
  final void Function(TMenuEntry<T> item) onSelected;

  @override
  State<TTextFieldMenuPopup<T>> createState() => _TTextFieldMenuPopupState<T>();
}

class _TTextFieldMenuPopupState<T> extends State<TTextFieldMenuPopup<T>> {
  late TextEditingController _textEditingController;

  @override
  void initState() {
    super.initState();
    _textEditingController = TextEditingController();
    _updateEditorToValue();
  }

  @override
  void didUpdateWidget(TTextFieldMenuPopup<T> oldWidget) {
    super.didUpdateWidget(oldWidget);
    _updateEditorToValue();
  }

  @override
  void dispose() {
    _textEditingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => TMenuPopup(
      value: widget.value,
      entries: widget.entries,
      onSelected: widget.onSelected,
      anchor: IgnorePointer(
        child: TTextField(
          textEditingController: _textEditingController,
          readOnly: true,
          showCursor: false,
          mouseCursor: SystemMouseCursors.basic,
          suffixIcon: const Icon(
            Symbols.arrow_drop_down_rounded,
          ),
        ),
      ));

  void _updateEditorToValue() {
    if (widget.value case final value?) {
      _textEditingController.text =
          widget.entries.firstWhere((entry) => entry.value == value).label;
    }
  }
}
