import 'dart:convert';
import 'dart:io';

import 'package:async/async.dart';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';

import '../codec/base62_utils.dart';

const _chunkSize = 4096;

class CryptoUtils {
  CryptoUtils._();

  static String getSha256ByBytes(List<int> bytes) {
    final digest = sha256.convert(bytes);
    return Base62Utils.encode(digest.bytes);
  }

  static String getSha256ByString(String str) =>
      getSha256ByBytes(utf8.encode(str));

  static Future<Digest> getFileSha256(String path) async {
    final reader = ChunkedStreamReader(File(path).openRead());
    final output = AccumulatorSink<Digest>();
    final input = sha256.startChunkedConversion(output);
    try {
      while (true) {
        final chunk = await reader.readChunk(_chunkSize);
        if (chunk.isEmpty) {
          // indicate end of file
          break;
        }
        input.add(chunk);
      }
    } finally {
      // We always cancel the ChunkedStreamReader,
      // this ensures the underlying stream is cancelled.
      await reader.cancel();
    }

    input.close();

    return output.events.single;
  }
}
