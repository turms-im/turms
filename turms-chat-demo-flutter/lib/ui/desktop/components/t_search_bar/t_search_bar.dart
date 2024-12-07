import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../t_text_field/t_text_field.dart';

class TSearchBar extends StatelessWidget {
  const TSearchBar(
      {super.key,
      this.style,
      required this.hintText,
      this.textEditingController,
      this.autofocus = false,
      this.keepFocusOnSubmit = false,
      this.focusNode,
      this.prefixIcon = const Icon(Symbols.search_rounded, size: 16),
      this.debounceTimeout,
      this.transformValue,
      this.onChanged,
      this.onSubmitted});

  final TextEditingController? textEditingController;
  final TextStyle? style;
  final String hintText;
  final Widget prefixIcon;
  final bool autofocus;
  final bool keepFocusOnSubmit;
  final FocusNode? focusNode;
  final Duration? debounceTimeout;
  final String Function(String)? transformValue;
  final ValueChanged<String>? onChanged;
  final ValueChanged<String>? onSubmitted;

  @override
  Widget build(BuildContext context) => TTextField(
        textEditingController: textEditingController,
        autofocus: autofocus,
        keepFocusOnSubmit: keepFocusOnSubmit,
        focusNode: focusNode,
        style: style,
        hintText: hintText,
        prefixIcon: prefixIcon,
        prefixIconConstraints: const BoxConstraints.tightFor(width: 24),
        showDeleteButtonIfHasText: true,
        debounceTimeout: debounceTimeout,
        transformValue: transformValue,
        onChanged: onChanged,
        onSubmitted: onSubmitted,
      );
}
