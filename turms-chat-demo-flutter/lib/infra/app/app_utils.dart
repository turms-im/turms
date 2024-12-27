import 'package:window_manager/window_manager.dart';

class AppUtils {
  AppUtils._();

  static Future<void> close() async {
    // Don't [SystemNavigator.pop()] as it won't work on desktop platforms.
    // Don't use [WidgetsBinding.instance.exitApplication(AppExitType.required)]
    // as it is buggy and will freeze the window for seconds, which is unacceptable.
    await windowManager.close();
  }
}
