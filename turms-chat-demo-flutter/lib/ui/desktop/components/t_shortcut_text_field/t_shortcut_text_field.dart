import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../infra/keyboard/logical_keyboard_key_extensions.dart';
import '../../../../infra/keyboard/shortcut_extensions.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../t_text_field/t_text_field.dart';

final _allowedKeys = <LogicalKeyboardKey>{
  // A-Z
  LogicalKeyboardKey.keyA,
  LogicalKeyboardKey.keyB,
  LogicalKeyboardKey.keyC,
  LogicalKeyboardKey.keyD,
  LogicalKeyboardKey.keyE,
  LogicalKeyboardKey.keyF,
  LogicalKeyboardKey.keyG,
  LogicalKeyboardKey.keyH,
  LogicalKeyboardKey.keyI,
  LogicalKeyboardKey.keyJ,
  LogicalKeyboardKey.keyK,
  LogicalKeyboardKey.keyL,
  LogicalKeyboardKey.keyM,
  LogicalKeyboardKey.keyN,
  LogicalKeyboardKey.keyO,
  LogicalKeyboardKey.keyP,
  LogicalKeyboardKey.keyQ,
  LogicalKeyboardKey.keyR,
  LogicalKeyboardKey.keyS,
  LogicalKeyboardKey.keyT,
  LogicalKeyboardKey.keyU,
  LogicalKeyboardKey.keyV,
  LogicalKeyboardKey.keyW,
  LogicalKeyboardKey.keyX,
  LogicalKeyboardKey.keyY,
  LogicalKeyboardKey.keyZ,
  // 0-9
  LogicalKeyboardKey.digit0,
  LogicalKeyboardKey.digit1,
  LogicalKeyboardKey.digit2,
  LogicalKeyboardKey.digit3,
  LogicalKeyboardKey.digit4,
  LogicalKeyboardKey.digit5,
  LogicalKeyboardKey.digit6,
  LogicalKeyboardKey.digit7,
  LogicalKeyboardKey.digit8,
  LogicalKeyboardKey.digit9,
  LogicalKeyboardKey.numpad0,
  LogicalKeyboardKey.numpad1,
  LogicalKeyboardKey.numpad2,
  LogicalKeyboardKey.numpad3,
  LogicalKeyboardKey.numpad4,
  LogicalKeyboardKey.numpad5,
  LogicalKeyboardKey.numpad6,
  LogicalKeyboardKey.numpad7,
  LogicalKeyboardKey.numpad8,
  LogicalKeyboardKey.numpad9,
  // Special keys
  LogicalKeyboardKey.comma,
  LogicalKeyboardKey.period,
  LogicalKeyboardKey.space,
  // Navigation keys
  LogicalKeyboardKey.home,
  LogicalKeyboardKey.end,
  LogicalKeyboardKey.pageUp,
  LogicalKeyboardKey.pageDown,
  // Editing keys
  LogicalKeyboardKey.insert,
  LogicalKeyboardKey.delete,
  // Arrow keys
  LogicalKeyboardKey.arrowLeft,
  LogicalKeyboardKey.arrowRight,
  LogicalKeyboardKey.arrowUp,
  LogicalKeyboardKey.arrowDown,
  // Modifier keys
  LogicalKeyboardKey.shift,
  LogicalKeyboardKey.shiftLeft,
  LogicalKeyboardKey.shiftRight,
  LogicalKeyboardKey.alt,
  LogicalKeyboardKey.altLeft,
  LogicalKeyboardKey.altRight,
  LogicalKeyboardKey.control,
  LogicalKeyboardKey.controlLeft,
  LogicalKeyboardKey.controlRight,
  LogicalKeyboardKey.meta,
  LogicalKeyboardKey.metaLeft,
  LogicalKeyboardKey.metaRight,
};

class TShortcutTextField extends ConsumerStatefulWidget {
  const TShortcutTextField(
      {super.key, this.initialKeys, required this.onShortcutChanged});

  final List<LogicalKeyboardKey>? initialKeys;
  final void Function(List<LogicalKeyboardKey> keys) onShortcutChanged;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() =>
      _TShortcutTextFieldState();
}

class _TShortcutTextFieldState extends ConsumerState<TShortcutTextField> {
  late TextEditingController _textEditingController;
  late FocusNode _focusNode;
  late List<LogicalKeyboardKey> _keys;

  @override
  void initState() {
    super.initState();
    _focusNode = FocusNode();
    _textEditingController = TextEditingController();
    final initialKeys = widget.initialKeys;
    _keys = initialKeys == null || initialKeys.isEmpty ? [] : initialKeys
      ..sortKeys();
    _textEditingController.text = _formatKeys();
  }

  @override
  void dispose() {
    _focusNode.dispose();
    _textEditingController.dispose();
    super.dispose();
  }

  @override
  void didUpdateWidget(TShortcutTextField oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (widget.initialKeys != oldWidget.initialKeys) {
      final initialKeys = widget.initialKeys;
      _keys = initialKeys == null || initialKeys.isEmpty ? [] : initialKeys
        ..sortKeys();
      _textEditingController.text = _formatKeys();
    }
  }

  @override
  Widget build(BuildContext context) => KeyboardListener(
        focusNode: _focusNode,
        onKeyEvent: _onKeyEvent,
        child: TTextField(
          textEditingController: _textEditingController,
          showCursor: false,
          readOnly: true,
          enableInteractiveSelection: false,
        ),
      );

  void _onKeyEvent(KeyEvent event) {
    switch (event) {
      case KeyUpEvent():
        if (HardwareKeyboard.instance.physicalKeysPressed.isNotEmpty) {
          return;
        }
        if (_keys.length == 1) {
          _textEditingController.text =
              ref.read(appLocalizationsViewModel).none;
          widget.onShortcutChanged([]);
        } else if (_keys.any((element) => element.isModifier) &&
            _keys.any((element) => !element.isModifier)) {
          widget.onShortcutChanged(_keys);
        } else {
          _textEditingController.text =
              ref.read(appLocalizationsViewModel).none;
          widget.onShortcutChanged([]);
        }
      case KeyDownEvent():
        _keys = HardwareKeyboard.instance.logicalKeysPressed
            .map((key) => key.normalizedKey)
            .where(_allowedKeys.contains)
            .toSet()
            .take(4)
            .toList()
          ..sortKeys();
        _textEditingController.text = _formatKeys();
    }
  }

  String _formatKeys() {
    if (_keys.isEmpty) {
      return ref.read(appLocalizationsViewModel).none;
    }
    final buffer = StringBuffer();
    for (final key in _keys) {
      if (buffer.isNotEmpty) {
        buffer.write(' + ');
      }
      if (key == LogicalKeyboardKey.control) {
        buffer.write('Ctrl');
      } else {
        buffer.write(key.keyLabel);
      }
    }
    return buffer.toString();
  }
}
