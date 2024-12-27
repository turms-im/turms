import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../../../../../../infra/env/env_vars.dart';
import '../../../../../../../infra/ui/size_utils.dart';
import '../../../../../../themes/index.dart';
import '../../../../../components/index.dart';
import 'message_image_provider.dart';

const _imageBorderWidth = 1.0;

class MessageBubbleImage extends StatefulWidget {
  const MessageBubbleImage(
      {Key? key, required this.url, required this.width, required this.height})
      : super(key: key);

  final String url;
  final double width;
  final double height;

  @override
  State<MessageBubbleImage> createState() => _MessageBubbleImageState();
}

class _MessageBubbleImageState extends State<MessageBubbleImage> {
  late MessageImageProvider _originalImageProvider;
  late MessageImageProvider _thumbnailProvider;
  late double _width;
  late double _height;

  @override
  void initState() {
    super.initState();
    _originalImageProvider = MessageImageProvider(widget.url, false);
    _thumbnailProvider = MessageImageProvider(widget.url, true);

    final size = SizeUtils.keepAspectRatio(
        Size(widget.width, widget.height),
        EnvVars.messageImageThumbnailSizeWidth,
        EnvVars.messageImageThumbnailSizeHeight);
    _width = size.width;
    _height = size.height;
  }

  @override
  void dispose() {
    // Dispose the original image provider to save memory promptly.
    _originalImageProvider.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
          onTap: () async {
            // TODO: show a tip to let user know if the original image has been deleted.
            unawaited(showImageViewerDialog(context, _originalImageProvider));
          },
          child: _buildThumbnail()));

  Image _buildThumbnail() => Image(
        isAntiAlias: true,
        gaplessPlayback: true,
        image: _thumbnailProvider,
        fit: BoxFit.contain,
        loadingBuilder: (context, child, loadingProgress) {
          // FIXME: https://github.com/flutter/flutter/issues/85966
          if (loadingProgress == null &&
              ((child as Semantics).child as RawImage).image != null) {
            return ClipRRect(
              borderRadius: Sizes.borderRadiusCircular4,
              child: Padding(
                padding: const EdgeInsets.all(_imageBorderWidth),
                child: child,
              ),
            );
          }
          return SizedBox(
              width: _width,
              height: _height,
              child: const ClipRRect(
                borderRadius: Sizes.borderRadiusCircular4,
                child: DecoratedBox(
                    decoration: BoxDecoration(color: Colors.black12),
                    child:
                        RepaintBoundary(child: CupertinoActivityIndicator())),
              ));
        },
        errorBuilder: (context, error, stackTrace) => _buildError(),
      );

// todo: click to download
  // handle different cases
  Widget _buildError() => const SizedBox(
        width: 100,
        height: 100,
        child: TImageBroken(),
      );

// Stack _buildError() => Stack(
//       children: [
//         SizedBox(
//           width: EnvVars.messageImageThumbnailSizeWidth.toDouble(),
//           height: EnvVars.messageImageThumbnailSizeWidth.toDouble(),
//           child: const DecoratedBox(
//             decoration: BoxDecoration(color: Colors.black12),
//           ),
//         ),
//         const Center(
//           child: Icon(Symbols.image_not_supported_rounded),
//         )
//       ],
//     );
}
