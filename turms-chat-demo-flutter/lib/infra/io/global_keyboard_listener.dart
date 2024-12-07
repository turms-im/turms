import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/// Used to listen to the keyboard without the interfere of the focus system.
class GlobalKeyboardListener extends StatefulWidget {
  const GlobalKeyboardListener({
    super.key,
    required this.onKeyEvent,
    required this.child,
  });

  final KeyEventCallback onKeyEvent;
  final Widget child;

  @override
  State<GlobalKeyboardListener> createState() => _GlobalKeyboardListenerState();
}

class _GlobalKeyboardListenerState extends State<GlobalKeyboardListener> {
  @override
  void initState() {
    super.initState();
    ServicesBinding.instance.keyboard.addHandler(widget.onKeyEvent);
  }

  @override
  void dispose() {
    ServicesBinding.instance.keyboard.removeHandler(widget.onKeyEvent);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => widget.child;
}
