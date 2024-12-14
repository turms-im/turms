import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../infra/task/debouncer.dart';
import '../../../themes/app_theme_extension.dart';
import '../index.dart';
import '../t_menu/t_context_menu.dart';

class TTextField extends ConsumerStatefulWidget {
  const TTextField(
      {super.key,
      this.textEditingController,
      this.autofocus = false,
      this.keepFocusOnSubmit = false,
      this.focusNode,
      this.mouseCursor,
      this.hintText,
      this.prefixIcon,
      this.prefixIconConstraints,
      this.suffixIcon,
      this.showDeleteButtonIfHasText = false,
      this.showCursor = true,
      this.readOnly = false,
      this.enableInteractiveSelection,
      this.maxLength,
      this.expands = false,
      this.style,
      this.textAlign = TextAlign.start,
      TextAlignVertical? textAlignVertical,
      this.debounceTimeout,
      this.transformValue,
      this.onChanged,
      this.onSubmitted,
      this.onCaretMoved,
      this.onTapOutside})
      : assert(!showDeleteButtonIfHasText || suffixIcon == null),
        textAlignVertical = textAlignVertical ??
            (expands ? TextAlignVertical.top : TextAlignVertical.center);

  final TextEditingController? textEditingController;
  final bool autofocus;
  final bool keepFocusOnSubmit;
  final FocusNode? focusNode;
  final SystemMouseCursor? mouseCursor;
  final String? hintText;
  final Widget? prefixIcon;
  final BoxConstraints? prefixIconConstraints;
  final Widget? suffixIcon;
  final bool showDeleteButtonIfHasText;
  final bool showCursor;
  final bool readOnly;
  final bool? enableInteractiveSelection;
  final int? maxLength;
  final bool expands;
  final TextAlign textAlign;
  final TextAlignVertical textAlignVertical;
  final TextStyle? style;
  final Duration? debounceTimeout;
  final String Function(String value)? transformValue;
  final ValueChanged<String>? onChanged;
  final ValueChanged<String>? onSubmitted;
  final ValueChanged<Rect>? onCaretMoved;
  final ValueChanged<PointerDownEvent>? onTapOutside;

  @override
  ConsumerState<TTextField> createState() => _TTextFieldState();
}

class _TTextFieldState extends ConsumerState<TTextField> {
  GlobalKey? _textFieldKey;
  TextEditingController? _textEditingController;
  Debouncer? _onChangedDebouncer;
  FocusNode? _focusNode;

  @override
  void initState() {
    super.initState();
    if (widget.textEditingController == null) {
      _textEditingController = TextEditingController();
    }
    final debounceTimeout = widget.debounceTimeout;
    if (debounceTimeout != null) {
      _onChangedDebouncer = Debouncer(timeout: debounceTimeout);
    }
    if (widget.onCaretMoved != null) {
      _textFieldKey = GlobalKey();
    }
    _focusNode = widget.focusNode == null && widget.keepFocusOnSubmit
        ? FocusNode()
        : null;
  }

  @override
  void dispose() {
    _textEditingController?.dispose();
    _onChangedDebouncer?.cancel();
    _focusNode?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    final prefixIcon = widget.prefixIcon;
    final controller =
        (widget.textEditingController ?? _textEditingController)!;
    final showSuffixIcon = (widget.suffixIcon != null) ||
        (widget.showDeleteButtonIfHasText && controller.text.isNotEmpty);
    final suffixIcon = widget.suffixIcon;
    return TextField(
      key: _textFieldKey,
      controller: controller,
      contextMenuBuilder: buildTextFieldContextMenu,
      autofocus: widget.autofocus,
      focusNode: widget.focusNode ?? _focusNode,
      mouseCursor: widget.mouseCursor,
      showCursor: widget.showCursor,
      readOnly: widget.readOnly,
      maxLines: widget.expands ? null : 1,
      maxLength: widget.maxLength,
      textAlign: widget.textAlign,
      textAlignVertical: widget.textAlignVertical,
      enableInteractiveSelection: widget.enableInteractiveSelection,
      expands: widget.expands,
      onChanged: (value) {
        // To get an accurate "controller.value.composing",
        // we have to use "addPostFrameCallback".
        // FIXME: https://github.com/flutter/flutter/issues/128565
        WidgetsBinding.instance.addPostFrameCallback((_) {
          if (controller.value.composing != TextRange.empty) {
            return;
          }
          final transformValue = widget.transformValue;
          if (transformValue != null) {
            final result = transformValue(value);
            if (result != value) {
              controller.value = TextEditingValue(
                text: result,
                selection: TextSelection.collapsed(offset: result.length),
              );
              value = result;
            }
          }
          final debouncer = _onChangedDebouncer;
          if (debouncer != null) {
            debouncer.run(() {
              widget.onChanged?.call(value);
              _tryNotifyCaretMoved();
            });
          } else {
            widget.onChanged?.call(value);
            _tryNotifyCaretMoved();
          }
          setState(() {});
        });
      },
      onSubmitted: _onChangedDebouncer == null
          ? widget.keepFocusOnSubmit
              ? (value) {
                  widget.onSubmitted?.call(value);
                  if (widget.keepFocusOnSubmit) {
                    _focusNode?.requestFocus();
                  }
                }
              : widget.onSubmitted
          : (value) {
              _onChangedDebouncer?.cancel();
              widget.onSubmitted?.call(value);
              if (widget.keepFocusOnSubmit) {
                _focusNode?.requestFocus();
              }
            },
      onTapOutside: widget.onTapOutside,
      style: widget.style ??
          const TextStyle(
              fontSize: 14,
              // cursor height
              height: 1.2),
      decoration: InputDecoration(
        hintText: widget.hintText,
        hintStyle: appThemeExtension.textFieldHintTextStyle,
        filled: true,
        fillColor: const Color.fromARGB(255, 226, 226, 226),
        contentPadding: const EdgeInsets.symmetric(vertical: 10, horizontal: 8),
        prefixIcon: prefixIcon,
        prefixIconConstraints: prefixIcon == null
            ? null
            : const BoxConstraints.tightFor(width: 24),
        suffixIcon: suffixIcon ??
            (showSuffixIcon
                ? TIconButton(
                    addContainer: false,
                    iconData: Symbols.close_rounded,
                    iconSize: 20,
                    iconColor:
                        context.appThemeExtension.textFieldHintTextStyle.color,
                    onTap: () {
                      controller.clear();
                      widget.onChanged?.call('');
                      setState(() {});
                    },
                  )
                : null),
        suffixIconConstraints:
            showSuffixIcon ? const BoxConstraints.tightFor(width: 30) : null,
        isCollapsed: true,
        focusedBorder: const OutlineInputBorder(
          borderSide: BorderSide(color: Colors.blue),
        ),
        border: const OutlineInputBorder(
          borderSide: BorderSide.none,
        ),
      ),
    );
  }

  void _tryNotifyCaretMoved() {
    final onCaretMoved = widget.onCaretMoved;
    if (onCaretMoved != null) {
      final rect = getCaretRect(_textFieldKey!);
      if (rect != null) {
        onCaretMoved(rect);
      }
    }
  }
}

Rect? getCaretRect(GlobalKey textFieldKey) {
  final currentContext = textFieldKey.currentContext;
  if (currentContext == null) {
    return null;
  }
  final fieldBox = currentContext.findRenderObject();
  final caretRect = fieldBox is RenderBox ? _getCaretRect(fieldBox) : null;
  if (caretRect == null) {
    return null;
  }
  return caretRect;
}

RenderEditable? _findRenderEditable(RenderObject root) {
  RenderEditable? renderEditable;
  void recursiveFinder(RenderObject child) {
    if (child is RenderEditable) {
      renderEditable = child;
      return;
    }
    child.visitChildren(recursiveFinder);
  }

  root.visitChildren(recursiveFinder);
  return renderEditable;
}

Rect? _getCaretRect(RenderBox box) {
  final renderEditable = _findRenderEditable(box);
  if (renderEditable == null || !renderEditable.hasFocus) {
    return null;
  }
  final selection = renderEditable.selection;
  if (selection == null) {
    return null;
  }
  final firstEndpoint =
      renderEditable.getEndpointsForSelection(selection).firstOrNull;
  if (firstEndpoint == null) {
    return null;
  }

  final point = TextSelectionPoint(
    box.localToGlobal(firstEndpoint.point),
    firstEndpoint.direction,
  );

  final p = point.point;
  final cursorHeight = renderEditable.cursorHeight;
  return Rect.fromLTWH(
    p.dx,
    p.dy - cursorHeight,
    renderEditable.cursorWidth,
    cursorHeight,
  );
}

Widget buildTextFieldContextMenu(
  BuildContext context,
  EditableTextState editableTextState,
) =>
    buildContextMenu(
        context: context,
        items: editableTextState.contextMenuButtonItems,
        anchors: editableTextState.contextMenuAnchors);
