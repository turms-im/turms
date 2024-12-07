import 'package:flutter/animation.dart';

extension AnimationStatusExtensions on AnimationStatus {
  bool get isDismissed => switch (this) {
        AnimationStatus.completed ||
        AnimationStatus.forward ||
        AnimationStatus.reverse =>
          false,
        AnimationStatus.dismissed => true,
      };

  bool get isNotDismissed => switch (this) {
        AnimationStatus.completed ||
        AnimationStatus.forward ||
        AnimationStatus.reverse =>
          true,
        AnimationStatus.dismissed => false,
      };
}
