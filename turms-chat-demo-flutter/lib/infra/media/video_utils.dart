import 'dart:io';

import 'package:video_player_media_kit/video_player_media_kit.dart';

final class VideoUtils {
  VideoUtils._();

  /// TODO: disable logs: https://github.com/media-kit/media-kit/issues/260
  static void ensureInitialized() {
    if (Platform.isAndroid || Platform.isFuchsia) {
      VideoPlayerMediaKit.ensureInitialized(
        android: true,
      );
    } else if (Platform.isIOS) {
      VideoPlayerMediaKit.ensureInitialized(
        iOS: true,
      );
    } else if (Platform.isWindows) {
      VideoPlayerMediaKit.ensureInitialized(
        windows: true,
      );
    } else if (Platform.isMacOS) {
      VideoPlayerMediaKit.ensureInitialized(
        macOS: true,
      );
    } else if (Platform.isLinux) {
      VideoPlayerMediaKit.ensureInitialized(
        linux: true,
      );
    }
  }

// static Future<MatrixImageFile?> getVideoThumbnail() async {
//   final tmpDir = await getTemporaryDirectory();
//   final tmpFile = File('${tmpDir.path}/$name');
//   Video
//   if (await tmpFile.exists() == false) {
//     await tmpFile.writeAsBytes(bytes);
//   }
//   try {
//     final bytes = await VideoCompress.getByteThumbnail(tmpFile.path);
//     if (bytes == null) return null;
//     return MatrixImageFile(
//       bytes: bytes,
//       name: name,
//     );
//   } catch (e, s) {
//     Logs().w('Error while compressing video', e, s);
//   }
//   return null;
// }
}
