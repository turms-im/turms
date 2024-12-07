import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';
import 'package:flutter/cupertino.dart';

import '../../../ui/desktop/components/index.dart';

class User {
  User({
    required this.userId,
    required this.name,
    this.intro = '',
    this.imageUrl,
    this.imageBytes,
    this.presence = UserPresence.none,
  });

  final Int64 userId;
  final String name;
  final String intro;
  final String? imageUrl;
  final Uint8List? imageBytes;
  final UserPresence presence;

  ImageProvider? _cachedImage;

  ImageProvider? get image {
    if (_cachedImage != null) {
      return _cachedImage;
    }
    if (imageBytes case final imageBytes?) {
      return _cachedImage = MemoryImage(imageBytes);
    }
    if (imageUrl case final imageUrl?) {
      return _cachedImage = NetworkImage(imageUrl);
    }
    return null;
  }

  User? copyWith({UserPresence? presence}) => User(
        userId: userId,
        name: name,
        intro: intro,
        imageUrl: imageUrl,
        imageBytes: imageBytes,
        presence: presence ?? this.presence,
      );
}
