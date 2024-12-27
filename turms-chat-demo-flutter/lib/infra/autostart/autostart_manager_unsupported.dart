import 'autostart_manager.dart';

AutostartManager getWorker() => AutostartManagerUnsupported();

class AutostartManagerUnsupported extends AutostartManager {
  AutostartManagerUnsupported() : super(appName: '', appPath: '');

  @override
  Future<bool> isEnabled() async {
    throw UnsupportedError('isEnabled');
  }

  @override
  Future<void> enable() async {
    throw UnsupportedError('enable');
  }

  @override
  Future<void> disable() async {
    throw UnsupportedError('disable');
  }
}
