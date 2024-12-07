import 'package:media_kit/media_kit.dart';

class AudioUtils {
  AudioUtils._();

  static Future<void> play(String path) async {
    final media = Media(path);
    final player = Player();
    await player.stop();
    await player.open(media);
    await player.play();
  }
}
