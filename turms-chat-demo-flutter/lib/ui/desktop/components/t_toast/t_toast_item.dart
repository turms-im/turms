import 't_toast_type.dart';

class TToastItem {
  TToastItem({required this.text, required this.duration, required this.type});

  final String text;
  final Duration duration;
  final TToastType type;
}
