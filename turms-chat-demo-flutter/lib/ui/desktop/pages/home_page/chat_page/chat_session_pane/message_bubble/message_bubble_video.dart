import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/material_symbols_icons.dart';
import 'package:path/path.dart';
import 'package:video_player/video_player.dart';

import '../../../../../../../infra/crypto/crypto_utils.dart';
import '../../../../../../../infra/exception/exception_extensions.dart';
import '../../../../../../../infra/exception/user_visible_exception.dart';
import '../../../../../../../infra/http/downloaded_file.dart';
import '../../../../../../../infra/http/file_too_large_exception.dart';
import '../../../../../../../infra/http/http_utils.dart';
import '../../../../../../../infra/io/path_utils.dart';
import '../../../../../../../infra/ui/size_utils.dart';
import '../../../../../../../infra/units/file_size_extensions.dart';
import '../../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../../themes/index.dart';

import '../../../../../components/index.dart';

const _maxAllowedMb = 100;
final _maxAllowedBytes = _maxAllowedMb.MB;

class MessageBubbleVideo extends ConsumerStatefulWidget {
  const MessageBubbleVideo(
      {Key? key, required this.url, required this.width, required this.height})
      : super(key: key);

  final Uri url;
  final double width;
  final double height;

  @override
  ConsumerState<MessageBubbleVideo> createState() => _MessageBubbleVideoState();
}

class _MessageBubbleVideoState extends ConsumerState<MessageBubbleVideo> {
  VideoPlayerController? _controller;
  late Future<void> _initializeVideoControllerFuture;
  late double _width;
  late double _height;

  @override
  void didUpdateWidget(MessageBubbleVideo oldWidget) {
    super.didUpdateWidget(oldWidget);
  }

  @override
  void initState() {
    super.initState();

    final size =
        SizeUtils.keepAspectRatio(Size(widget.width, widget.height), 200, 200);
    _width = size.width;
    _height = size.height;

    _initializeVideoControllerFuture = Future.microtask(() async {
      final url = widget.url;
      final urlStr = url.toString();
      final ext = extension(urlStr);
      final fileName = '${CryptoUtils.getSha256ByString(urlStr)}.$ext';
      final filePath = PathUtils.joinPathInUserScope(['files', fileName]);
      final file = File(filePath);
      final VideoPlayerController controller;
      if (await file.exists()) {
        controller = VideoPlayerController.file(File(filePath));
      } else {
        final DownloadedFile? downloadedFile;
        try {
          downloadedFile = await HttpUtils.downloadFile(
              taskId: filePath,
              uri: url,
              filePath: filePath,
              maxBytes: _maxAllowedBytes);
        } on FileTooLargeException catch (e) {
          throw UserVisibleException(
              e,
              (cause) => ref
                  .read(appLocalizationsViewModel)
                  .failedToDownloadFileTooLarge(_maxAllowedMb));
        } catch (e) {
          throw UserVisibleException(
              e, (_) => ref.read(appLocalizationsViewModel).failedToDownload);
        }
        if (downloadedFile == null) {
          throw UserVisibleException(
              null, (_) => ref.read(appLocalizationsViewModel).videoNotFound);
        }
        controller = VideoPlayerController.file(downloadedFile.file);
      }
      await controller.setVolume(1.0);
      controller.addListener(() {
        if (controller.value.position == controller.value.duration) {
          controller.seekTo(Duration.zero);
          setState(() {});
        }
      });
      await controller.initialize();
      _controller = controller;
    });
  }

  @override
  void dispose() {
    _controller?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => SizedBox(
      width: _width,
      height: _height,
      child: TAsyncBuilder(
          future: _initializeVideoControllerFuture,
          builder: (context, snapshot) => snapshot.when(
                data: (data) => _buildStack(),
                error: (error, stackTrace) {
                  final message = switch (error) {
                    UserVisibleException(:final message) => message,
                    final Exception e =>
                      '${ref.read(appLocalizationsViewModel).error}: ${e.message}',
                    _ => '${ref.read(appLocalizationsViewModel).error}: $error',
                  };
                  return DecoratedBox(
                    decoration: BoxDecoration(
                      color: context.appThemeExtension.maskColor,
                    ),
                    child: Center(
                      child: Row(
                        spacing: 16,
                        children: [
                          const Icon(
                            Symbols.info_i_rounded,
                            color: Colors.white,
                            size: 20,
                          ),
                          Text(
                            message,
                            style: const TextStyle(
                                color: Colors.white, fontSize: 16),
                          ),
                        ],
                      ),
                    ),
                  );
                },
                loading: () => const Center(
                    child: RepaintBoundary(child: CircularProgressIndicator())),
              )));

  Widget _buildStack() {
    final controller = _controller;
    return Stack(
      children: [
        AspectRatio(
          aspectRatio: controller!.value.aspectRatio,
          child: VideoPlayer(controller),
        ),
        if (!controller.value.isPlaying)
          Center(
            child: SizedBox(
              width: 36,
              height: 36,
              child: DecoratedBox(
                decoration: BoxDecoration(
                  color: const Color.fromARGB(128, 0, 0, 0),
                  shape: BoxShape.circle,
                  border: Border.all(color: Colors.white),
                ),
                child: const Center(
                  child: Icon(
                    Symbols.play_arrow_rounded,
                    color: Colors.white,
                    size: 20,
                  ),
                ),
              ),
            ),
          ),
        Positioned.fill(
          child: MouseRegion(
            cursor: SystemMouseCursors.click,
            child: GestureDetector(
              behavior: HitTestBehavior.translucent,
              onTap: () async {
                if (controller.value.isPlaying) {
                  await controller.pause();
                } else {
                  await controller.play();
                }
                setState(() {});
              },
            ),
          ),
        )
      ],
    );
  }
}
