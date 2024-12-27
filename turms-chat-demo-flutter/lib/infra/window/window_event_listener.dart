import 'package:window_manager/window_manager.dart';

class WindowEventListener extends WindowListener {
  WindowEventListener(
      {this.onClose, this.onFocus, this.onMaximize, this.onUnmaximize});

  final void Function()? onClose;
  final void Function()? onFocus;
  final void Function()? onMaximize;
  final void Function()? onUnmaximize;

  @override
  void onWindowClose() {
    onClose?.call();
  }

  @override
  void onWindowFocus() {
    onFocus?.call();
  }

  @override
  void onWindowMaximize() {
    onMaximize?.call();
  }

  @override
  void onWindowUnmaximize() {
    onUnmaximize?.call();
  }
}
