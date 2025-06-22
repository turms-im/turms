import 'dart:collection';
import 'dart:typed_data';

import 'package:fixnum/fixnum.dart';

extension IntExtensions on int {
  bool toBool() => this != 0;

  Int64 toInt64() => Int64(this);

  Uint8List toVarInt64Bytes() {
    final bytes = Uint8List(9);
    var i = 0;
    for (var b = this; b != 0; b >>= 7) {
      bytes[i++] = b & 0x7F;
      if (b > 0x7F) {
        bytes[i - 1] |= 0x80;
      }
    }
    return bytes.sublist(0, i);
  }
}

extension BoolExtensions on bool {
  int toInt() => this ? 1 : 0;

  String toIntString() => this ? '1' : '0';
}

extension StringExtensions on String {
  bool? toBool() => switch (this) {
    '1' => true,
    '0' => false,
    _ => null,
  };

  (String, String) splitFirst(String separator) {
    final separatorPosition = indexOf(separator);
    if (separatorPosition == -1) {
      return (this, '');
    }
    return (
      substring(0, separatorPosition),
      substring(separatorPosition + separator.length),
    );
  }

  bool get isBlank => trim().isEmpty;

  bool get isNotBlank => !isBlank;

  String constCaseToCamelCase() {
    final parts = toLowerCase().split('_');
    final camelCase = StringBuffer(parts[0]);
    final partCount = parts.length;
    for (var i = 1; i < partCount; i++) {
      final part = parts[i];
      camelCase
        ..write(part[0].toUpperCase())
        ..write(part.substring(1));
    }
    return camelCase.toString();
  }
}

extension EnumExtensionsIterable<T extends Enum> on Iterable<T> {
  T? firstOrNullByName(String? name) {
    if (name == null) {
      return null;
    }
    for (final value in this) {
      if (value.name == name) {
        return value;
      }
    }
    return null;
  }
}

extension Iterables<E> on Iterable<E> {
  Map<K, List<E>> groupBy<K>(K Function(E) keyFunction) => fold(
    <K, List<E>>{},
    (Map<K, List<E>> map, E element) =>
        map..putIfAbsent(keyFunction(element), () => <E>[]).add(element),
  );

  LinkedHashMap<K, List<E>> groupByAsLinkedHashMap<K>(
    K Function(E) keyFunction,
  ) => fold(
    LinkedHashMap<K, List<E>>(),
    (LinkedHashMap<K, List<E>> map, E element) =>
        map..putIfAbsent(keyFunction(element), () => <E>[]).add(element),
  );
}

extension ListExtensions<T> on List<T> {
  void swap(int index1, int index2) {
    if (index1 != index2) {
      final tmp1 = this[index1];
      this[index1] = this[index2];
      this[index2] = tmp1;
    }
  }

  void replace(T oldElement, T newElement) {
    final index = indexWhere((element) => element == oldElement);
    if (index != -1) {
      this[index] = newElement;
    }
  }
}

extension Int32Extensions on Int32 {
  BigInt toBigInt() => BigInt.parse(toString());

  Uint8List toVarInt32Bytes() {
    final bytes = Uint8List(5);
    var i = 0;
    for (var b = toInt(); b != 0; b >>= 7) {
      bytes[i++] = b & 0x7F;
      if (b > 0x7F) {
        bytes[i - 1] |= 0x80;
      }
    }
    return bytes.sublist(0, i);
  }
}

extension Int64Extensions on Int64 {
  BigInt toBigInt() => BigInt.parse(toString());

  Uint8List toVarInt64Bytes() {
    final bytes = Uint8List(9);
    var i = 0;
    for (var b = toInt(); b != 0; b >>= 7) {
      bytes[i++] = b & 0x7F;
      if (b > 0x7F) {
        bytes[i - 1] |= 0x80;
      }
    }
    return bytes.sublist(0, i);
  }
}

extension BigIntExtensions on BigInt {
  Int64 toInt64() => Int64.parseInt(toString());
}

(Int32, int) convertVarInt32BytesToInt32(Uint8List bytes) {
  var result = 0;
  final length = bytes.length;
  for (var i = 0; i < length; i++) {
    final b = bytes[i];
    result |= (b & 0x7F) << (7 * i);
    if (b < 0x80) {
      return (Int32(result), i + 1);
    }
  }
  throw ArgumentError('Invalid varint encoding');
}

(Int64, int) convertVarInt64BytesToInt64(Uint8List bytes) {
  var result = 0;
  final length = bytes.length;
  for (var i = 0; i < length; i++) {
    final b = bytes[i];
    result |= (b & 0x7F) << (7 * i);
    if (b < 0x80) {
      return (Int64(result), i + 1);
    }
  }
  throw ArgumentError('Invalid varint encoding');
}
