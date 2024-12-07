import 'dart:ui' as ui;

import 'package:flutter/material.dart';

import '../../../../../../infra/ui/color_extensions.dart';

const _defaultColor = Colors.grey;
final _fileFormatToImage = <String, ui.Image>{};

class FileIcon extends StatelessWidget {
  FileIcon({super.key, required String fileFormat})
      : fileFormat = fileFormat.toUpperCase();

  final String fileFormat;

  @override
  Widget build(BuildContext context) =>
      RawImage(image: _getFileImage(fileFormat));
}

ui.Image _getFileImage(String fileFormat) =>
    _fileFormatToImage.putIfAbsent(fileFormat, () {
      final recorder = ui.PictureRecorder();
      _FileIconPainter(fileFormat).paint(Canvas(recorder), const Size(21, 28));
      final picture = recorder.endRecording();
      return picture.toImageSync(21, 28);
    });

class _FileIconPainter extends CustomPainter {
  const _FileIconPainter(this.fileFormat) : color = _defaultColor;

  final String fileFormat;
  final Color color;

  @override
  void paint(Canvas canvas, Size size) {
    final length = size.width * 0.35;
    final left = size.width - length;
    canvas
      // Clip the corner.
      ..clipPath(Path()
        ..lineTo(left, 0)
        ..lineTo(size.width, length)
        ..lineTo(size.width, size.height)
        ..lineTo(0, size.height)
        ..close())
      // Clip a rounded rectangle.
      ..drawRRect(
          RRect.fromRectAndRadius(Rect.fromLTWH(0, 0, size.width, size.height),
              const Radius.circular(4)),
          Paint()..color = color)
      // Draw the corner.
      ..drawRRect(
          RRect.fromRectAndRadius(
              Rect.fromLTWH(left, -length, length * 2, length * 2),
              const Radius.circular(2)),
          Paint()..color = color.darken(0.2));

    final paragraphBuilder = ui.ParagraphBuilder(
      ui.ParagraphStyle(
        textAlign: TextAlign.center,
        textDirection: TextDirection.ltr,
      ),
    )
      ..pushStyle(ui.TextStyle(
        color: Colors.white,
        fontSize: 10,
      ))
      ..addText(fileFormat);
    final paragraph = paragraphBuilder.build()
      ..layout(ui.ParagraphConstraints(width: size.width));
    canvas.drawParagraph(
        paragraph,
        Offset((size.width - paragraph.width) / 2,
            (size.height - paragraph.height) / 2));
  }

  @override
  bool shouldRepaint(_FileIconPainter oldDelegate) =>
      fileFormat != oldDelegate.fileFormat;
}
