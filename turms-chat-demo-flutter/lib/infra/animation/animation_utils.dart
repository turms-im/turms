import 'package:flutter/animation.dart';

import 'dismissed_status_change_type.dart';

class AnimationUtils {
  AnimationUtils._();

  static DismissedStatusChangeType detectDismissedStatusChange(
          AnimationStatus previousStatus, AnimationStatus status) =>
      switch ((previousStatus.isDismissed, status.isDismissed)) {
        (false, true) => DismissedStatusChangeType.becomeDismissed,
        (true, false) => DismissedStatusChangeType.becomeNotDismissed,
        (true, true) || (false, false) => DismissedStatusChangeType.noChange
      };
}
