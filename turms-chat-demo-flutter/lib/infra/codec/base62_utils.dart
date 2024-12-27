import 'dart:math';
import 'dart:typed_data';

class Base62Utils {
  Base62Utils._();

  static const _alphabet =
      '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  static final _baseMap = _initBaseMap();

  static Uint8List _initBaseMap() {
    final baseMap = Uint8List(256);
    baseMap.fillRange(0, baseMap.length, 255);
    for (var i = 0; i < _alphabet.length; i++) {
      final xc = _alphabet.codeUnitAt(i);
      if (baseMap[xc] != 255) {
        throw FormatException('${_alphabet[i]} is ambiguous');
      }
      baseMap[xc] = i;
    }
    return baseMap;
  }

  static String encode(List<int> input) {
    var zeroes = 0;
    var length = 0;
    var begin = 0;
    final end = input.length;
    while (begin != end && input[begin] == 0) {
      begin++;
      zeroes++;
    }

    final size =
        ((end - begin) * (log(256) / log(_alphabet.length)) + 1).toInt();
    final b58 = Uint8List(size);

    while (begin != end) {
      var carry = input[begin];

      var i = 0;
      for (var it1 = size - 1;
          (carry != 0 || i < length) && (it1 != -1);
          it1--, i++) {
        carry += 256 * b58[it1];
        b58[it1] = carry % _alphabet.length;
        carry = carry ~/ _alphabet.length;
      }
      if (carry != 0) {
        throw const FormatException('Non-zero carry');
      }
      length = i;
      begin++;
    }

    var it2 = size - length;
    while (it2 != size && b58[it2] == 0) {
      it2++;
    }

    final str = StringBuffer();
    final c = _alphabet[0];
    while (zeroes-- != 0) {
      str.write(c);
    }
    for (; it2 < size; ++it2) {
      str.write(_alphabet[b58[it2]]);
    }
    return str.toString();
  }

  static Uint8List decode(String input) {
    if (input.isEmpty) {
      return Uint8List(0);
    }
    var psz = 0;

    if (input[psz] == ' ') {
      throw ArgumentError.value(
          input, 'input', 'input cannot begin with a space.');
    }

    var zeroes = 0;
    var length = 0;
    while (input[psz] == _alphabet[0]) {
      zeroes++;
      psz++;
    }

    final size =
        (((input.length - psz) * (log(_alphabet.length) / log(256))) + 1)
            .toInt();
    final b256 = Uint8List(size);

    while (psz < input.length && input[psz].isNotEmpty) {
      var carry = _baseMap[input[psz].codeUnitAt(0)];

      if (carry == 255) {
        throw ArgumentError.value(input, 'input',
            'The character "${input[psz]}" at index $psz is invalid.');
      }
      var i = 0;
      for (var it3 = size - 1;
          (carry != 0 || i < length) && (it3 != -1);
          it3--, i++) {
        carry += _alphabet.length * b256[it3];
        b256[it3] = carry % 256;
        carry = carry ~/ 256;
      }
      if (carry != 0) {
        throw const FormatException('Non-zero carry');
      }
      length = i;
      psz++;
    }

    if (psz < input.length && input[psz] == ' ') {
      throw ArgumentError.value(
          input, 'input', 'input cannot end with a space.');
    }

    var it4 = size - length;
    while (it4 != size && b256[it4] == 0) {
      it4++;
    }
    final vch = Uint8List(zeroes + (size - it4));
    if (zeroes != 0) {
      vch.fillRange(0, zeroes, 0x00);
    }
    var j = zeroes;
    while (it4 != size) {
      vch[j++] = b256[it4++];
    }
    return vch;
  }
}
