import 'dart:io';
import 'dart:typed_data';
import 'dart:ui';

import 'package:flutter_svg/flutter_svg.dart';

Future<Uint8List> svgToPng(Uint8List svg) async {
  final pictureInfo = await vg.loadPicture(SvgBytesLoader(svg), null);
  final image = await pictureInfo.picture.toImage(1024, 1024);
  final byteData = await image.toByteData(format: ImageByteFormat.png);
  if (byteData == null) {
    throw Exception('Unable to convert SVG to PNG');
  }
  return byteData.buffer.asUint8List();
}

Future<void> main() async {
  final iconFile = File.fromUri(Uri.parse('../assets/images/icon.svg'));
  final iconBytes = await iconFile.readAsBytes();
  final iconPngBytes = await svgToPng(iconBytes);
  final iconPngFile =
      File.fromUri(Uri.parse('../assets/images/icon_rectangle.png'));
  await iconPngFile.writeAsBytes(iconPngBytes);
}
