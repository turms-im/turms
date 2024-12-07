import 'dart:async';
import 'dart:ui';

class Debouncer {
  Debouncer({required this.timeout});

  final Duration timeout;
  Timer? _timer;

  void run(VoidCallback callback) {
    _timer?.cancel();
    _timer = Timer(timeout, callback);
  }

  void cancel() {
    _timer?.cancel();
  }
}
