import '../../common/fixtures/fixtures.dart';
import '../models/file_info.dart';

final _files = [
  FileInfo(
      name: 'cat.gif',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'gif',
      size: 7832457),
  FileInfo(
      name: 'dog.gif',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'gif',
      size: 123456),
  FileInfo(
      name: 'cat.png',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'png',
      size: 4452),
  FileInfo(
      name: 'dog.png',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'png',
      size: 7831237),
  FileInfo(
      name: 'cat.jpg',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'jpg',
      size: 123786),
  FileInfo(
      name: 'dog.jpg',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'jpg',
      size: 879453),
  FileInfo(
      name: 'cat.pdf',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'pdf',
      size: 8735437454),
  FileInfo(
      name: 'dog.pdf',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'pdf',
      size: 12345378),
  FileInfo(
      name: 'cat.doc',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'doc',
      size: 651378),
  FileInfo(
      name: 'dog.doc',
      uploadDate: DateTime.now(),
      uploader: 'myself',
      type: 'doc',
      size: 783),
];

extension FixturesExtensions on Fixtures {
  List<FileInfo> get files => _files;
}
