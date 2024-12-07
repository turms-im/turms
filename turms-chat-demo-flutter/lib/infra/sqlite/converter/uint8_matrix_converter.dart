import 'package:drift/drift.dart';

import '../../codec/codec_utils.dart';

class Uint8MatrixConverter extends TypeConverter<List<Uint8List>, Uint8List> {
  const Uint8MatrixConverter();

  @override
  List<Uint8List> fromSql(Uint8List fromDb) =>
      CodecUtils.deserialize2DArray(fromDb);

  @override
  Uint8List toSql(List<Uint8List> value) => CodecUtils.serialize2DArray(value);
}
