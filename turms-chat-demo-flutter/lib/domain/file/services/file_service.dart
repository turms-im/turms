import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../common/fixtures/fixtures.dart';
import '../../user/models/user.dart';
import '../fixtures/files.dart';
import '../models/file_info.dart';

class FileService {
  const FileService(this._loggedInUser);

  final User _loggedInUser;

  Future<List<FileInfo>> queryFiles() async {
    await Future<void>.delayed(const Duration(seconds: 3));
    return Fixtures.instance.files;
  }
}

final fileServiceProvider = StateProvider<FileService?>((ref) => null);
