import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../ui/desktop/components/index.dart';
import '../../../ui/l10n/app_localizations.dart';
import 'group_member.dart';
import 'user.dart';

part 'group_contact.dart';

part 'system_contact.dart';

part 'user_contact.dart';

part 'private_contact.dart';

sealed class Contact {
  Contact({
    required this.recordId,
    required this.id,
    required this.name,
    this.intro = '',
    this.imageUrl,
    this.imageBytes,
    this.icon,
  });

  final String recordId;

  /// user ID, group ID, or system contact type ID
  final Int64 id;
  final String name;
  final String intro;
  final String? imageUrl;
  final Uint8List? imageBytes;
  final IconData? icon;

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

  bool get isFileTransfer {
    final contact = this;
    return contact is SystemContact &&
        contact.type == SystemContactType.fileTransfer;
  }
}
