import 'dart:io';

import '../app/app_config.dart';

/// Application folder structure:
/// - app
/// - user
///   - database
///   - files
class PathUtils {
  PathUtils._();

  static String joinAppPath(List<String> paths) =>
      '${AppConfig.appDir}${Platform.pathSeparator}${paths.join(Platform.pathSeparator)}';

  static String joinPathInAppScope(List<String> paths) =>
      '${AppConfig.appDir}${Platform.pathSeparator}app${Platform.pathSeparator}${paths.join(Platform.pathSeparator)}';

  // TODO
  static String joinPathInUserScope(List<String> paths) =>
      '${AppConfig.appDir}${Platform.pathSeparator}user${Platform.pathSeparator}${paths.join(Platform.pathSeparator)}';
}
