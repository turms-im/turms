import 'dart:typed_data';

import '../built_in_types/built_in_type_helpers.dart';

class CodecUtils {
  CodecUtils._();

  static Uint8List serialize2DArray(List<Uint8List> twoDArray) {
    var length = 0;
    final lengthBytesList = <Uint8List>[];
    for (final list in twoDArray) {
      final bytes = list.length.toVarInt64Bytes();
      lengthBytesList.add(bytes);
      length += bytes.length;
      length += list.length;
    }
    final flatList = Uint8List(length);
    final count = twoDArray.length;
    for (var i = 0; i < count; i++) {
      final list = twoDArray[i];
      flatList
        ..addAll(lengthBytesList[i])
        ..addAll(list);
    }
    return flatList;
  }

  static List<Uint8List> deserialize2DArray(Uint8List flatList) {
    final result = <Uint8List>[];
    var offset = 0;
    final bytesLength = flatList.length;
    while (offset < bytesLength) {
      final (length, readBytes) = convertVarInt64BytesToInt64(flatList);
      offset += readBytes;
      final lengthInt = length.toInt();
      final list = flatList.sublist(offset, offset + lengthInt);
      result.add(list);
      offset += lengthInt;
    }
    return result;
  }
}
