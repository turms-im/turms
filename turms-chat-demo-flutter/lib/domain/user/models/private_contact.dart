part of 'contact.dart';

sealed class PrivateContact extends Contact {
  PrivateContact({
    required super.recordId,
    required super.id,
    required super.name,
    super.intro = '',
    super.imageUrl,
    super.imageBytes,
    super.icon,
  });
}
