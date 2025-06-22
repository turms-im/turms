import 'dart:io';
import 'dart:ui';

import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_svg/flutter_svg.dart';

Future<Uint8List> svgToPng(Uint8List svgData, int outputDimension) async {
  // 1. load
  final pictureInfo = await vg.loadPicture(SvgBytesLoader(svgData), null);
  final pictureSize = pictureInfo.size;
  final image = await pictureInfo.picture.toImage(
    pictureSize.width.toInt(),
    pictureSize.height.toInt(),
  );

  // 2. paint
  WidgetsFlutterBinding.ensureInitialized();
  final recorder = PictureRecorder();
  final canvas = Canvas(recorder);
  paintImage(
    canvas: canvas,
    rect: Rect.fromLTWH(
      0,
      0,
      outputDimension.toDouble(),
      outputDimension.toDouble(),
    ),
    image: image,
    filterQuality: FilterQuality.high,
    fit: BoxFit.contain,
  );
  final picture = recorder.endRecording();

  // 3. export
  final outputImage = await picture.toImage(outputDimension, outputDimension);
  final outputBytes = await outputImage.toByteData(format: ImageByteFormat.png);
  return outputBytes!.buffer.asUint8List();
}

Future<void> main() async {
  final currentDir = Directory.current;
  final baseDir =
      (currentDir.path.endsWith('tool') ? currentDir.parent : currentDir).path;

  final iconPath = '$baseDir/assets/images/icon.svg';
  final iconFile = File(iconPath);
  if (!await iconFile.exists()) {
    final srcIconFile = File('$baseDir/../turms-admin/ui/public/favicon.svg');
    if (!await srcIconFile.exists()) {
      throw Exception(
        'Unable to find the source icon file: ${srcIconFile.absolute.path}',
      );
    }
    await srcIconFile.copy(iconPath);
  }

  const outputDimension = 1024;
  final iconBytes = await iconFile.readAsBytes();
  final iconPngBytes = await svgToPng(iconBytes, outputDimension);
  final iconPngFile = File('$baseDir/assets/images/icon_$outputDimension.png');
  await iconPngFile.writeAsBytes(iconPngBytes);

  print('Generated the icon file: ${iconPngFile.absolute.path}');

  exit(0);
}
